package com.user

import grails.transaction.Transactional

@Transactional
class DataService {

    def createUser(User user) {
        println(user.toString())
        def testuser = user.save(failOnError:true)
        def testRole = Role.findByAuthority('ROLE_USER')
        UserRole.create(testuser, testRole,true)

        println(Role.get(1))
    }

}
