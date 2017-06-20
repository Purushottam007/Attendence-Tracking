package com.user

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString


import grails.rest.*                                     //changes


@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)

//@Resource(uri='/users', formats=['json'])                          //changes



class User implements Serializable {

    private static final long serialVersionUID = 1
    transient springSecurityService

    String username
    String password
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired
    User(String username, String password){
        this()
        this.username=username
        this.password=password

    }

    Set<Role> getAuthorities() {
        (UserRole.findAllByUser(this) as List<UserRole>)*.role as Set<Role>
    }

    /*static constraints = {
        password nullable: false, blank: false, password: true
        username nullable: false, blank: false, unique: true
    }*/static constraints = {
        username blank: false, unique: true
        password blank: false
    }
    /*def beforeInsert() {
        encodePassword()
    }

    def beforeUpdate() {
        if (isDirty('password')) {
            encodePassword()
        }
    }*/

   /* protected void encodePassword() {
        password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
    }*/

    static transients = ['springSecurityService']


    static mapping = {
	    password column: '`password`'
    }
}
