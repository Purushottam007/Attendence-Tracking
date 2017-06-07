package demo1

import com.user.*

class BootStrap {

    def init = {
        def adminRole = new Role(authority: 'ROLE_ADMIN').save()

        def testUser = new User(username: 'me', password: 'password').save()

        UserRole.create testUser, adminRole

        UserRole.withSession {
            it.flush()
            it.clear()


        }
        def destroy = {
            assert User.count() == 1
            assert Role.count() == 1
            assert UserRole.count() == 1
        }
    }
}