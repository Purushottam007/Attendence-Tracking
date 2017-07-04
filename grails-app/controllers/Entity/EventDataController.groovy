package Entity

import com.user.Point
import com.user.geozoneService.GeogoneService
import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class EventDataController {
    def geogoneService
    def point

    static allowedMethods = [index:"GET", search:"GET", save: "POST", update: "PUT", delete: "DELETE"]  //changes
    static responseFormats=['json','xml']                                                               //changes
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
       // respond EventData.list(params), model:[eventDataCount: EventData.count()]
    }
    @Secured(['ROLE_USER','ROLE_ADMIN'])
    def eventList(Integer max) {
        params.max = Math.min(max ?: 10, 100)
       render view: 'event', model:[eventList: EventData.list(params),eventDataCount: EventData.count()]

    }



    @Secured(['ROLE_USER','ROLE_ADMIN'])
    def event() {
        def point=params.ps
        def id=params.geozone
        println"yyyyyyyyyyyyyyyy"+point
        println"xxxxxxxxxxxxxxxxxxxxx"+id

        def ar

        if(point!='undefined') {
            ar = point.trim().split(" ")
        }
        EventData eventData=new EventData()

        String[] lat = new String[10];
        String[] lng = new String[10];
        int i =0;

        for(int j=0 ;j<ar.size(); j++){

            def g= ar[j].split(",")
            lat[i]=g[0]
            lng[i]=g[1]
            j=j+1
            i = i+1
        }
        eventData.latitude=lat[0]
        eventData.longititude=lng[0]

        def elt=Double.parseDouble(eventData.latitude)
        def elg=Double.parseDouble(eventData.longititude)

        println"eltttttttttttt"+elt
        println"elgggggggggg"+elg
        println"jjjjjjjjjjjjjj"+id

        Geozone geozone= Geozone.findById(id)
        println"ddddddddddddddd"+geozone
        def lt=Double.parseDouble(geozone.latitude1)
        println"ltttttttttttt"+lt
        def lg=Double.parseDouble(geozone.longitude1)
        println"lggggggggggg"+lg
        def rd


        if(geozone.radious!='undefined') {
            rd = Double.parseDouble(geozone.radious)
            geogoneService.isInside(rd, elt, elg, lt, lg)
            println "rdddddddddddddd" + rd
            //println"rdssssssssssssss"+rds
        }

else {
    //        println"hhhhhhhhhhh"
//           def lt2=Double.parseDouble(geozone.latitude2)
  //          println"uuuuuuuuuu="+lt2

            Point pt=new Point()
            Point[] polygon1 = [new Point(lt,lg), new Point(Double.parseDouble(geozone.latitude2),Double.parseDouble(geozone.longitude2)),
                                new Point(Double.parseDouble(geozone.latitude3),Double.parseDouble(geozone.longitude3)), new Point(Double.parseDouble(geozone.latitude4),Double.parseDouble(geozone.longitude4))];

            int n = 10;
            println"aaaaaaaaaaaaaaaaaaaaa"+Double.parseDouble(geozone.latitude3)

          //  println"pollllllll"+polygon1[0]
            Point p = new Point(elt,elg);
            println"kkkkkkkkkkk"+p.x+"jjjjjjjjjjjjjjj"+p.y
            println("Point P(" + p.x + ", " + p.y
                    + ") lies inside polygon1: " +pt.isInside(polygon1, n, p));




   println"sdddddddd"

}


        eventData.setDevice(Device.findById(1))



        eventData.save(failOnError:true);

        render status:200


    }



    def show(EventData eventData) {
        respond eventData
    }

    def create() {
        respond new EventData(params)
    }

    @Transactional
    def save(EventData eventData) {
        if (eventData == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (eventData.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond eventData.errors, view:'create'
            return
        }

        eventData.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'eventData.label', default: 'EventData'), eventData.id])
                redirect eventData
            }
            '*' { respond eventData, [status: CREATED] }
        }
    }

    def edit(EventData eventData) {
        respond eventData
    }

    @Transactional
    def update(EventData eventData) {
        if (eventData == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (eventData.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond eventData.errors, view:'edit'
            return
        }

        eventData.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'eventData.label', default: 'EventData'), eventData.id])
                redirect eventData
            }
            '*'{ respond eventData, [status: OK] }
        }
    }

    @Transactional
    def delete(EventData eventData) {

        if (eventData == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        eventData.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'eventData.label', default: 'EventData'), eventData.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'eventData.label', default: 'EventData'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
