package com.user

import Entity.AttendanceDetail
import Entity.Employee
import grails.plugin.springsecurity.annotation.Secured


class SecureController {
    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
        def index(Integer max) {
        params.max = Math.min(max ?: 10, 10)
        Integer from = 0;
        Integer to = 0;
        if (!params.offset) {
            params.offset = "0"
        }
        to = Integer.parseInt(params.offset) + params.max
        for (int i = from; i <= AttendanceDetail.count(); i++) {
            if (from != AttendanceDetail.count()) {
                from = Integer.parseInt(params.offset) + 1
            }
        }
        if (to > AttendanceDetail.count()) {
            to = AttendanceDetail.count()
        }


        def employeeCriteria = Employee.createCriteria()
        println";;;;;;;;;;;;;"+params?.employeeName
        def atdresults = employeeCriteria.list(params) {
            if (params?.employeeName) {
                //  println"WWWWWWWWWWWWWWWWWWWW"+params
               // println"KKKKKKKKKKKK"+
                ilike("employeeName", "%${params.employeeName}%")

            }

            if (params?.employeeParam) {
              //  println"WWWWWWWWWWWWWWWWWWWW"+params
                eq("id",params.employeeParam as Long)

            }

        }
        println"LLLLLLLLLLLL"+atdresults
        println"LLLLLLLLLLLL"+atdresults?.attendance.id
        def oo = []
        for(def jj:atdresults.attendance.id){
                jj.each{ j ->
                    oo.add(j)
                }
        }
        def attendanceCriteria = AttendanceDetail.createCriteria()
         atdresults = attendanceCriteria.list(params) {
            if (params?.attendanceDate) {
                eq("attendanceDate", params.attendanceDate)
            }
            if (params?.employeeParam || params?.employeeName) {
                'in'("id",oo)
            }

        }
        render view: 'menu', model: [attendanceList: atdresults, attendanceDetailCount: AttendanceDetail.count(), empList: Employee.list(params), employeeCount: Employee.count(), from: from, to: to]
    }


}