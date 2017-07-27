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
        respond EventData.list(params), model:[eventDataCount: EventData.count()]
    }
    @Secured(['ROLE_USER','ROLE_ADMIN'])
    def eventList(Integer max) {
        params.max = Math.min(max ?: 10, 100)
       render view: 'event', model:[eventList: EventData.list(params),eventDataCount: EventData.count(),glist:Geozone.list(params), geozoneCount: Geozone.count()]

    }



    @Secured(['ROLE_USER','ROLE_ADMIN'])
    def event() {
        def point=params.ps
        def id=params.geozone
        def empid=params.employeeId
        def ar

        def lt;
        def lot;


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
        eventData.geozoneId=id


        Geozone geozone= Geozone.get(id)
        Employee employee1=Employee.findById(empid)
        Device device = employee1.activeDevice

        eventData.setDevice(device)
        eventData.save(failOnError:true);

        def st=eventData.getStatusCode()

        def intime
        def outime

        Device dev = Device.findById(eventData.deviceId)
        def index = dev.employeeId
        Employee employee = Employee.findById(dev.employeeId)



        def t2=new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());

        if(!employee.activeId && st=='in'){
            AttendanceDetail attendanceDetail=new AttendanceDetail()
            if (attendanceDetail)
            intime=new SimpleDateFormat(" HH:mm:ss").format(Calendar.getInstance().getTime());
            lt=intime

            attendanceDetail.logIntime=lt

            attendanceDetail.attendanceDate=new Date()

            attendanceDetail.save(flush: true,failOnError:true)

           employee.addToAttendance(attendanceDetail)
            employee.active = attendanceDetail
        }else if(employee.activeId && st=="out"){
            AttendanceDetail attendanceDetail = employee.active
                if(attendanceDetail){
                    outime=new SimpleDateFormat(" HH:mm:ss").format(Calendar.getInstance().getTime());
                attendanceDetail.logOuttime = outime
                attendanceDetail.save(flush:true)
                    employee.active = null
                            }

        }
        println("GGGGGGGGGGGGGGGGG"+lt)
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
