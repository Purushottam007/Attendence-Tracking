package Entity

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class EventDataController {

    static allowedMethods = [index:"GET", search:"GET", save: "POST", update: "PUT", delete: "DELETE"]  //changes
    static responseFormats=['json','xml']                                                               //changes
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond EventData.list(params), model:[eventDataCount: EventData.count()]
    }

    def show(EventData eventData) {
        respond eventData
    }

    def create() {
        respond new EventData(params)
    }

    @Transactional
    def save(EventData eventData) {
        if (eventData == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (eventData.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond eventData.errors, view:'create'
            return
        }

        eventData.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'eventData.label', default: 'EventData'), eventData.id])
                redirect eventData
            }
            '*' { respond eventData, [status: CREATED] }
        }
    }

    def edit(EventData eventData) {
        respond eventData
    }

    @Transactional
    def update(EventData eventData) {
        if (eventData == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (eventData.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond eventData.errors, view:'edit'
            return
        }

        eventData.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'eventData.label', default: 'EventData'), eventData.id])
                redirect eventData
            }
            '*'{ respond eventData, [status: OK] }
        }
    }

    @Transactional
    def delete(EventData eventData) {

        if (eventData == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        eventData.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'eventData.label', default: 'EventData'), eventData.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'eventData.label', default: 'EventData'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
