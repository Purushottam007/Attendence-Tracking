package Entity

class Department {

    Integer departmentId
    String departmentName

    static hasMany=[employee:Employee]
    static belongsTo = Employee

    static constraints ={


    }
}
