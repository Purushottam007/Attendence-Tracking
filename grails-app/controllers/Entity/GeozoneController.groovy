package Entity

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class GeozoneController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        println"oooooooooooooooo"+Geozone
        println":::::::::::::::::::"+Geozone.list(params)
       // render  model:[geozoneList: Geozone.list(params),geozoneCount: Geozone.count()]
    }

    @Secured(['ROLE_USER','ROLE_ADMIN'])
    def geozone(Integer max){
        params.max = Math.min(max ?: 10, 100)
        println":::::::::::::::::::"+Geozone.list(params)
        render view: '/geozone', model:[geozoneList: Geozone.list(params), geozoneCount: Geozone.count()]
    }
    @Secured(['ROLE_USER','ROLE_ADMIN'])
    def ft(){
        def path=params.str;
        def bound=params.st;
     //   println"gggggggggggggg"+bound
        println"sssssssssssssssssssssss"+path

       
println"hhhhhhhhhhh"+(bound!='undefined')
        println"sddddddddddddd"+(path!='undefined')
        def ar
        if(path!='undefined'){
             ar=path.trim().split(" ")
            println("XXXXXXXXXXXXXXXXXX"+(ar).toString())

        }



    //    println"ddddddddd"+ar[0]



           // println"oooooooooooooooooooooo"+bound
        else if(bound!='undefined'){

             ar=bound.trim().split(" ")
           
        }

        //println"bounds"+arr[0]


        String[] lat = new String[10];
        String[] lng = new String[10];
        int i =0;
        println"OOOOOOOOOOOOOOOOOOOOO"+ar.size()
        println("array value"+Arrays.toString(ar))
        for(int j=0 ;j<ar.size(); j++){
         //   lat[j] = "fffffffffffffffff"+ar[j]
            Geozone geozone=new Geozone();
            println"///////////"+ar[j]
           def g= ar[j].split(",")
            println"Lat: $j "+g[0]+", Lng:  "+g[1]
            lat[i]=g[0]
            println("lat[$j]"+lat[0])
            lng[i]=g[1]
            println("lng[$j]"+lng[0])

            geozone.latitude=lat[i]
            geozone.longitude=lng[i]


            geozone.setCompany(Company.findById(1))
            geozone.save(failOnError:true);



            //println"oooooooo"+ar[j+1 ] +"            "+ar[j]
         //   lng[j] = "qqqqqqqqqqqqqqqq"+ar[j+1]
           // println"lllllllll"+lat[j]
           // println"gggggggggg"+lng[j]
            j=j+1
            i = i+1
        }
        /*def long = new String[10]
        Geozone geozone=new Geozone();
        for(int i=0;i<ar.size()-1;i++){
            println"ooooooooooooo"+ar[i]
            lat.putAt(i) ar[i];
            println("jjj"+geozone.latitude)
            geozone.longitude = ar[i+1];
            println("kkkkkkkkkkk"+geozone.longitude)
            //  geozone
            i=i+1;

        }*/



        /*for(int i=1;i<ar.size()-2;i++){

            geozone.longitude[i+1];


        }*/

       /* geozone.latitude1=ar[0];
        println "uuuuuuuuuuuuuuuuuuu"+geozone.latitude1
        geozone.longitude1=ar[1]
          println "sds"+geozone.longitude1
        geozone.latitude2=ar[0];

        geozone.longitude2=ar[1]
        geozone.latitude3=ar[0];
        geozone.longitude3=ar[1]
        geozone.latitude4=ar[0];
        geozone.longitude4=ar[1]
        geozone.latitude5=ar[0];
        geozone.longitude5=ar[1]
      //  println "aaaaaaaaaaaa"+geozone.longitude5
        geozone.latitude6=ar[0];
        geozone.longitude6=ar[1]
        geozone.latitude7=ar[0];
        geozone.longitude7=ar[1]
        geozone.latitude8=ar[0];
        geozone.longitude8=ar[1]
        geozone.latitude9=ar[0];
        geozone.longitude9=ar[1]
        geozone.latitude10=ar[0];
        geozone.longitude10=ar[1]*/


  //     println"ggggggggggggggggg"+ geozone.getLatitude()

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

        render view: 'form'
    }




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
