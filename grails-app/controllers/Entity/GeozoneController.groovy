package Entity

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class GeozoneController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
 //       println"oooooooooooooooo"+Geozone
        println":::::::::::::::::::"+Geozone.list(params)
       // render  model:[geozoneList: Geozone.list(params),geozoneCount: Geozone.count()]
    }
/*
    @Secured(['ROLE_USER','ROLE_ADMIN'])
    def geo(){
        render view: 'geozone'
    }*/

    @Secured(['ROLE_USER','ROLE_ADMIN'])
    def geozone(Integer max){
        params.max = Math.min(max ?: 10, 100)
    //    println":::::::::::::::::::"+Geozone.list(params)
        render view: 'geozone', model:[geozoneList: Geozone.list(params), geozoneCount: Geozone.count()]
    }
   /* @Secured(['ROLE_USER','ROLE_ADMIN'])
    def gt() {
        def g=params.gtt

        println("ttttttttttttttttt"+g)
        render "hello"
    }*/


    @Secured(['ROLE_USER','ROLE_ADMIN'])
    def ft(){
        def path=params.str;
        def cr=params.st;
        def rad=params.stt
        def rect=params.stcnt;
        println"gggggggggggggg"+rect
//        println"sssssssssssssssssssssss"+path


//println"hhhhhhhhhhh"+(bound!='undefined')
   //     println"sddddddddddddd"+(path!='undefined')
        def ar
        if(path!='undefined'){
             ar=path.trim().split(" ")
      //      println("XXXXXXXXXXXXXXXXXX"+(ar).toString())

        }



    //    println"ddddddddd"+ar[0]



           // println"oooooooooooooooooooooo"+bound
        else if(cr!='undefined'){

             ar=cr.trim().split(" ")

        }
        else if(rect!='undefined'){
            ar=rect.trim().split(" ")
        }


        //println"bounds"+arr[0]
        Geozone geozone=new Geozone();


        String[] lat = new String[10];
        String[] lng = new String[10];
        int i =0;
       // println"OOOOOOOOOOOOOOOOOOOOO"+ar.size()
        //println("array value"+Arrays.toString(ar))
        for(int j=0 ;j<ar.size(); j++){
         //   lat[j] = "fffffffffffffffff"+ar[j]




            println"///////////"+ar[j]
           def g= ar[j].split(",")
            println"Lat: $j "+g[0]+", Lng:  "+g[1]
            lat[i]=g[0]
            println("lat[$j]"+lat[0])
            lng[i]=g[1]
            println("lng[$j]"+lng[0])

            j=j+1
            i = i+1




        }


        geozone.latitude1=lat[0]
        geozone.longitude1=lng[0]

        geozone.latitude2=lat[1]
        geozone.longitude2=lng[1]

        geozone.latitude3=lat[2]
        geozone.longitude3=lng[2]

        geozone.latitude4=lat[3]
        geozone.longitude4=lng[3]

        geozone.latitude5=lat[4]
        geozone.longitude5=lng[4]

        geozone.latitude6=lat[5]
        geozone.longitude6=lng[5]

        geozone.latitude7=lat[6]
        geozone.longitude7=lng[6]

        geozone.latitude8=lat[7]
        geozone.longitude8=lng[7]

        geozone.latitude9=lat[8]
        geozone.longitude9=lng[8]

        geozone.latitude10=lat[9]
        geozone.longitude10=lng[9]

        geozone.radious=rad



        geozone.setCompany(Company.findById(1))
        geozone.save(failOnError:true);

        render status:200
    }









/*
    def save() {
        def geozone = new Geozone()
        geozone.save()
        render view: 'geozoone'
    }*/
   @Secured(['ROLE_USER','ROLE_ADMIN'])
    def form() {
        println"kkkkkkk"+params

       Geozone geozone=Geozone.findById(params.id)

       println"jjjjjjjjjjj"+geozone?.latitude1

       render view: 'form', model:[geozoneInstance: geozone]
    }

   /* @Secured(['ROLE_USER','ROLE_ADMIN'])
    def forms() {


        render view: 'form'
    }
*/



    def show(Geozone geozone) {
        respond geozone
    }

    def create() {
        respond new Geozone(params)
    }

    @Transactional
    def save(Geozone geozone) {
        if (geozone == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (geozone.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond geozone.errors, view:'create'
            return
        }

        geozone.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'geozone.label', default: 'Geozone'), geozone.id])
                redirect geozone
            }
            '*' { respond geozone, [status: CREATED] }
        }
    }

    def edit(Geozone geozone) {
        respond geozone
    }

    @Transactional
    def update(Geozone geozone) {
        if (geozone == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (geozone.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond geozone.errors, view:'edit'
            return
        }

        geozone.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'geozone.label', default: 'Geozone'), geozone.id])
                redirect geozone
            }
            '*'{ respond geozone, [status: OK] }
        }
    }

    @Transactional
    def delete(Geozone geozone) {

        if (geozone == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        geozone.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'geozone.label', default: 'Geozone'), geozone.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'geozone.label', default: 'Geozone'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }





}
