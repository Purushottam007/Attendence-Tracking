package demo1

import com.user.Role
import com.user.User
import com.user.UserRole
import grails.converters.JSON

class BootStrap {

    def init = {servletContext -> registerMarshallers() }


    private void registerMarshallers(){
        JSON.registerObjectMarshaller(User) {

            def map = [

                    'id'                        : it.id?:"",
                    'username'                  : it.username?:"",
                    'password'                  : it.password?:"",
            ]
            return map
        }

    }
    def userRole = new Role(authority: 'ROLE_USER').save()
    def adminRole = new Role(authority: 'ROLE_ADMIN').save()

   /* def testUser = new User(username: 'admin', password: 'pass').save()

    UserRole.create testUser, adminRole

    UserRole.withSession {
        it.flush()
        it.clear()
    }

    assert User.count() == 1
    assert Role.count() == 1
    assert UserRole.count() == 1
*/

}