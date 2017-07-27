package com.user

import Entity.AttendanceDetail
import Entity.Employee
import grails.plugin.springsecurity.annotation.Secured

import java.text.DateFormat
import java.text.SimpleDateFormat


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
        def atdresults = employeeCriteria.list(params) {
            if (params?.employeeName) {
                ilike("employeeName", "%${params.employeeName}%")

            }

            if (params?.employeeParam) {
                eq("id",params.employeeParam as Long)

            }

        }
        def oo = []
        for(def jj:atdresults.attendance.id){
                jj.each{ j ->
                    oo.add(j)
                }
        }

        def attendanceCriteria = AttendanceDetail.createCriteria()
         atdresults = attendanceCriteria.list(params) {
            if (params?.attendanceDate) {
                def string = params.attendanceDate;
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date = format.parse(string);
                eq("attendanceDate",date)
            }
            if (params?.employeeParam || params?.employeeName) {
                if(oo){
                    'in'("id",oo)
                }

            }

        }
       // println("OOOOOOOOOO"+atdresults)
        render view: 'menu', model: [attendanceList: atdresults, attendanceDetailCount: AttendanceDetail.count(), empList: Employee.list(params), employeeCount: Employee.count(), from: from, to: to]
    }

}