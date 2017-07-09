package Entity

import com.user.Point
import com.user.Position_Point_WRT_Polygon
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


        Geozone geozone= Geozone.findById(id)

        def lt=Double.parseDouble(geozone.latitude1)
        def lg=Double.parseDouble(geozone.longitude1)
        def rd
        def inout
        def tf
        if(geozone.radious!='undefined') {
            rd = Double.parseDouble(geozone.radious)
            inout= geogoneService.isInside(rd, elt, elg, lt, lg)
           println inout
            eventData.statusCode=inout
        }else {
            println "polyyyyyyyyyyyyyyy"
            Position_Point_WRT_Polygon psp = new Position_Point_WRT_Polygon()
            println "poxxxxxxxxxxxxxxxxxxxxx"
            Point[] polygon1 = null;

            if (lt != null && lg != null) {
                //polygon1.apedd(new Point(lt, lg))
                polygon1 = [new Point(lt, lg)];
            }
            println "OOOOOOOOOOOOOOO" + polygon1
            if (geozone.latitude2 != null && geozone.longitude2 != null) {
                //polygon1.add(new Point(Double.parseDouble(geozone.latitude2), Double.parseDouble(geozone.longitude2)))
                polygon1 = appendValue(polygon1, new Point(Double.parseDouble(geozone.latitude2), Double.parseDouble(geozone.longitude2)))
            }

            if (geozone.latitude3 != null && geozone.longitude3 != null) {
                //polygon1.add(new Point(Double.parseDouble(geozone.latitude2), Double.parseDouble(geozone.longitude2)))
                polygon1 = appendValue(polygon1, new Point(Double.parseDouble(geozone.latitude3), Double.parseDouble(geozone.longitude3)))
            }
            if (geozone.latitude4 != null && geozone.longitude4 != null) {
                //polygon1.add(new Point(Double.parseDouble(geozone.latitude2), Double.parseDouble(geozone.longitude2)))
                polygon1 = appendValue(polygon1, new Point(Double.parseDouble(geozone.latitude4), Double.parseDouble(geozone.longitude4)))
            }

            if (geozone.latitude5 != null && geozone.longitude5 != null) {
                // polygon1.add(new Point(Double.parseDouble(geozone.latitude5), Double.parseDouble(geozone.longitude5)))
                polygon1 = appendValue(polygon1, new Point(Double.parseDouble(geozone.latitude5), Double.parseDouble(geozone.longitude5)))
            }
            if (geozone.latitude6 != null && geozone.longitude6 != null) {
                //polygon1.add(new Point(Double.parseDouble(geozone.latitude6), Double.parseDouble(geozone.longitude6)))
                polygon1 = appendValue(polygon1, new Point(Double.parseDouble(geozone.latitude6), Double.parseDouble(geozone.longitude6)))
            }
            if (geozone.latitude7 != null && geozone.longitude7 != null) {
                //polygon1.add(new Point(Double.parseDouble(geozone.latitude6), Double.parseDouble(geozone.longitude6)))
                polygon1 = appendValue(polygon1, new Point(Double.parseDouble(geozone.latitude7), Double.parseDouble(geozone.longitude7)))
            }
            if (geozone.latitude8 != null && geozone.longitude8 != null) {
                //polygon1.add(new Point(Double.parseDouble(geozone.latitude6), Double.parseDouble(geozone.longitude6)))
                polygon1 = appendValue(polygon1, new Point(Double.parseDouble(geozone.latitude8), Double.parseDouble(geozone.longitude8)))
            }
            if (geozone.latitude9 != null && geozone.longitude9 != null) {
                //polygon1.add(new Point(Double.parseDouble(geozone.latitude6), Double.parseDouble(geozone.longitude6)))
                polygon1 = appendValue(polygon1, new Point(Double.parseDouble(geozone.latitude9), Double.parseDouble(geozone.longitude9)))
            }
            if (geozone.latitude10 != null && geozone.longitude10 != null) {
                //polygon1.add(new Point(Double.parseDouble(geozone.latitude6), Double.parseDouble(geozone.longitude6)))
                polygon1 = appendValue(polygon1, new Point(Double.parseDouble(geozone.latitude10), Double.parseDouble(geozone.longitude10)))
            }

            println "size=" + polygon1.size()
            int n = polygon1.size();
            println "nnnnnnnnnnn" + n
            Point p = new Point(elt, elg);
            println("Point P(" + p.x + ", " + p.y
                    + ") lies inside polygon1: " + psp.isInside(polygon1, n, p));
            tf = psp.isInside(polygon1, n, p)
            println "ZZZZZZZZZZZZ" + tf


            if (tf == true) {
                println "xxxxxxxxxxxxxxxxxx" + tf
                eventData.statusCode = "in"
            } else {
                eventData.statusCode = "out"
            }
        }
        eventData.setDevice(Device.findById(1))
        eventData.save(failOnError:true);

        def st=eventData.getStatusCode()

        def intime
        def outime

        Device dev = Device.findById(eventData.deviceId)
        def index = dev.employeeId
        Employee emp = new Employee()

        Employee employee = Employee.get(dev.employeeId)


        def t2=new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());

        if(st=='in'){
            AttendanceDetail attendanceDetail=new AttendanceDetail()
            intime=new SimpleDateFormat(" HH:mm:ss").format(Calendar.getInstance().getTime());
           // println"XXXXXXXXXXXXXXXX"+intt

            attendanceDetail.logIntime=intime

            attendanceDetail.attendanceDate=t2


           // println";;;;;;;dddddd"+attendanceDetail.validate()
            attendanceDetail.save(flush: true,failOnError:true)

            employee.addToAttendance(attendanceDetail)
            employee.active = attendanceDetail
        }else{
            AttendanceDetail attendanceDetail = employee.active
                if(attendanceDetail){
                    outime=new SimpleDateFormat(" HH:mm:ss").format(Calendar.getInstance().getTime());
                attendanceDetail.logOuttime = outime
                attendanceDetail.save(flush:true)
                            }

        }

         employee.save(flush: true,failOnError:true)

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

    private Object[] appendValue(Object[] obj, Object newObj) {

        ArrayList<Object> temp = new ArrayList<Object>(Arrays.asList(obj));
        temp.add(newObj);
        return temp.toArray();

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
