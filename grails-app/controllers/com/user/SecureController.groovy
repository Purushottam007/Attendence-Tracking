package com.user

import grails.plugin.springsecurity.annotation.Secured
@Secured(['ROLE_USER'])
class SecureController {

    def index() {
        println"PPPPPPPPPPPPPPPPPPPPPPPPPPP"+params
        render view: 'menu'
    }
}
