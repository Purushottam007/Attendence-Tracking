package com.user

import Entity.AttendanceDetail
import Entity.Employee
import grails.plugin.springsecurity.annotation.Secured


class SecureController {
   @Secured(['ROLE_USER','ROLE_ADMIN'])
   // @Secured('ROLE_ADMIN')
    def index() {

        render view:  'menu',model:[attendanceList: AttendanceDetail.list(params), attendanceDetailCount: AttendanceDetail.count(),empList:Employee.list(params),employeeCount: Employee.count()]

    }
   /* @Secured(['ROLE_USER','ROLE_ADMIN'])
    def geozone() {
        render view: 'geozone'
    }*/

}

