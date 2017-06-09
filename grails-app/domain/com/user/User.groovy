package com.user

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
//import grails.compiler.GrailsCompileStatic
import grails.rest.*                                     //changes

//@GrailsCompileStatic
@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
@Resource(uri='/users', formats=['json'])                          //changes



class User implements Serializable {

    private static final long serialVersionUID = 1

    transient springSecurityService

    String username
    String password

    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    User(String username, String password) {
        this()
        this.username = username
        this.password = password
    }


    Set<Role> getAuthorities() {
        //(UserRole.findAllByUser(this) as List<UserRole>)*.role as Set<Role>
        UserRole.findAllByUser(this)*.role
    }

    static constraints = {
        password nullable: false, blank: false, password: true
        username nullable: false, blank: false, unique: true
    }

    static mapping = {
	    password column: '`password`'
    }
}
