package Entity

import java.sql.Time

class AttendanceDetail {

    Date attendanceDate
    String logIntime
    String logOuttime

    static belongsTo = Employee
    //static hasOne = [employee:Employee]
    static constraints = {
        attendanceDate(nullable: true,blank: true)
        logIntime(nullable: true,blank: true)
        logOuttime(nullable: true,blank: true)
    }

}
