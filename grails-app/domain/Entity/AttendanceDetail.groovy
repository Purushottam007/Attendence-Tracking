package Entity

import java.sql.Time

class AttendanceDetail {

    Date attendanceDate
    Time attendanceIntime
    Time attendanceOuttime

    static belongsTo = Employee
    //static hasOne = [employee:Employee]
    static constraints = {
    }
}
