package demo1

import com.user.*
import grails.converters.JSON

class BootStrap {

    def init = {servletContext -> registerMarshallers() }    //changes start


    private void registerMarshallers(){
        JSON.registerObjectMarshaller(User) {

            def map = [

                    'id'                        : it.id?:"",
                    'username'                  : it.username?:"",
                    'password'                  : it.password?:"",
            ]
            return map
        }

    }                                                                    //end









        def adminRole = new Role(authority: 'ROLE_ADMIN').save()
        def userRole = new Role(authority: 'ROLE_USER').save()

       /* def testUser = new User(username: 'me', password: 'password').save()

        UserRole.create testUser, adminRole

        UserRole.withSession {
            it.flush()
            it.clear()


        }
        def destroy = {
            assert User.count() == 1
            assert Role.count() == 1
            assert UserRole.count() == 1*/
}