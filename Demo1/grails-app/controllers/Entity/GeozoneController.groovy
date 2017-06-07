package Entity

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class GeozoneController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Geozone.list(params), model:[geozoneCount: Geozone.count()]
    }

    def show(Geozone geozone) {
        respond geozone
    }

    def create() {
        respond new Geozone(params)
    }

    @Transactional
    def save(Geozone geozone) {
        if (geozone == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (geozone.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond geozone.errors, view:'create'
            return
        }

        geozone.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'geozone.label', default: 'Geozone'), geozone.id])
                redirect geozone
            }
            '*' { respond geozone, [status: CREATED] }
        }
    }

    def edit(Geozone geozone) {
        respond geozone
    }

    @Transactional
    def update(Geozone geozone) {
        if (geozone == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (geozone.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond geozone.errors, view:'edit'
            return
        }

        geozone.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'geozone.label', default: 'Geozone'), geozone.id])
                redirect geozone
            }
            '*'{ respond geozone, [status: OK] }
        }
    }

    @Transactional
    def delete(Geozone geozone) {

        if (geozone == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        geozone.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'geozone.label', default: 'Geozone'), geozone.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'geozone.label', default: 'Geozone'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
