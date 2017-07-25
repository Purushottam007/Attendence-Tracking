package com.user

import Entity.AttendanceDetail
import Entity.Employee
import grails.plugin.springsecurity.annotation.Secured


class SecureController {
    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
    // @Secured('ROLE_ADMIN')
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

        def results = employeeCriteria.list {


            if (params?.id) {
                like("id", "%${params.id}%")

            }

        }

        def attendanceCriteria = AttendanceDetail.createCriteria()
        //   println"ooooooooooooooo"+params
        def atdresults = attendanceCriteria.list(params) {
            if (params?.attendanceDate) {
                println "ooooooooooooooo"
                eq("attendanceDate", params.attendanceDate)

            }
        }
        render view: 'menu', model: [attendanceList: atdresults, attendanceDetailCount: AttendanceDetail.count(), empList: Employee.list(params), employeeCount: Employee.count(), from: from, to: to]
    }


}