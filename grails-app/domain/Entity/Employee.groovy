package Entity
import com.user.User
class Employee extends User{

    Integer employeeId
    String employeeName
    //String employeeCompanyName
    String employeeAddress
    String employeeMail
    String employeeMobile
    String employeeDob
    String employeeDeptName
    AttendanceDetail active
    Device activeDevice
 //   String deviceId
    Company company

  // static belongsTo=[Company:Company]
    static hasMany=[deptname:Department,
                    addresses:Address,
                    attendance:AttendanceDetail,
                    device:Device]



    static namedQueries = {

        findByAttendance { attendanceId ->

            attendance { eq 'id', attendanceId }

        }


    }

    static constraints = {

        employeeAddress(nullable: true,blank: true)
        employeeMail(nullable: true)
        employeeMobile(nullable: true)
        employeeDob(nullable: true,blank: true)
        employeeId(nullable: true,blank: true)
        employeeName(nullable: true,blank: true)
        employeeDeptName(nullable: true,blank: true)
        active(nullable: true,blank: true)
        company(nullable: true,blank: true)
        activeDevice(nullable: true,blank: true)
   //     deviceId(nullable: true,blank: true)
    }

    static mapping = {

        discriminator "e"
    }
}
