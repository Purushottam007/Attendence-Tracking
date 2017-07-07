package Entity

import com.user.Point
import com.user.geozoneService.GeogoneService
import grails.plugin.springsecurity.annotation.Secured

import java.text.SimpleDateFormat

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
       // println"yyyyyyyyyyyyyyyy"+point
       // println"xxxxxxxxxxxxxxxxxxxxx"+id

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
        println"diddddddd="+eventData.id
        println"diddddddd="+eventData.deviceId


        Geozone geozone= Geozone.findById(id)

        def lt=Double.parseDouble(geozone.latitude1)
        def lg=Double.parseDouble(geozone.longitude1)
        def rd
        def inout
        if(geozone.radious!='undefined') {
            rd = Double.parseDouble(geozone.radious)
            inout= geogoneService.isInside(rd, elt, elg, lt, lg)
           println inout

        }else {
            Point pt=new Point()
            Point[] polygon1 = [new Point(lt,lg), new Point(Double.parseDouble(geozone.latitude2),Double.parseDouble(geozone.longitude2)),
                                new Point(Double.parseDouble(geozone.latitude3),Double.parseDouble(geozone.longitude3)), new Point(Double.parseDouble(geozone.latitude4),Double.parseDouble(geozone.longitude4))];

            int n = 10;
            Point p = new Point(elt,elg);
            println("Point P(" + p.x + ", " + p.y
                    + ") lies inside polygon1: " +pt.isInside(polygon1, n, p));

            }

        eventData.statusCode=inout
        eventData.setDevice(Device.findById(1))
        eventData.save(failOnError:true);

        println"eid="+ eventData.getId()
        def st=eventData.getStatusCode()

        def intt
        def outt



        println"did="+ eventData.deviceId.getClass()
        Device dev = Device.findById(eventData.deviceId)
        println"emppppppppp="+dev
        println"kkkkkk"+dev.employeeId
        def index = dev.employeeId
        Employee emp = new Employee()
        /*println("#########"+Employee.list().size())
        def empList = Employee.list()
        println"listttttttttt="+empList*/

        println"fffffff"+emp
       // println("@@@@@@@@@@"+emp.getEmployeeName())
        println"llllllllllllll"+Employee.get(dev.employeeId)
        Employee employee = Employee.get(dev.employeeId)


        def t2=new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());



        if(st=='in'){
            AttendanceDetail attendanceDetail=new AttendanceDetail()
            intt=new SimpleDateFormat(" HH:mm:ss").format(Calendar.getInstance().getTime());
            println"XXXXXXXXXXXXXXXX"+intt

            attendanceDetail.logIntime=intt

            attendanceDetail.attendanceDate=t2


            println";;;;;;;dddddd"+attendanceDetail.validate()
            attendanceDetail.save(flush: true,failOnError:true)

            employee.addToAttendance(attendanceDetail)
            employee.active = attendanceDetail
        }else{
            println"sttttttttt"
            AttendanceDetail attendanceDetail = employee.active
            println"LLLLLLLLLLLLLLLLLLLLLLL"+attendanceDetail
            if(attendanceDetail){
                outt=new SimpleDateFormat(" HH:mm:ss").format(Calendar.getInstance().getTime());
                println"outimeeeeeeeeee"+outt
                attendanceDetail.logOuttime = outt
                attendanceDetail.save(flush:true)
                println"outttttttttttttttttt"+attendanceDetail.logOuttime
            }

        }




       /* attendanceDetail.logIntime=intt
        attendanceDetail.logOuttime=outt
        attendanceDetail.attendanceDate=t2


        println";;;;;;;dddddd"+attendanceDetail.validate()
        attendanceDetail.save(flush: true,failOnError:true)

        employee.addToAttendance(attendanceDetail)*/
      //  employee.active = attendanceDetail

        employee.save(flush: true,failOnError:true)

      //  println";;;;;;;;;;;;;;;;;"+employee.attendance


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
