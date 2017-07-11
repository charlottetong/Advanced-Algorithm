package kungfuschool

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class EnrollController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Enroll.list(params), model:[enrollCount: Enroll.count()]
    }

    def show(Enroll enroll) {
        respond enroll
    }

    def create() {
        respond new Enroll(params)
    }

    @Transactional
    def save(Enroll enroll) {
        if (enroll == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (enroll.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond enroll.errors, view:'create'
            return
        }

        enroll.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'enroll.label', default: 'Enroll'), enroll.id])
                redirect enroll
            }
            '*' { respond enroll, [status: CREATED] }
        }
    }

    def edit(Enroll enroll) {
        respond enroll
    }

    @Transactional
    def update(Enroll enroll) {
        if (enroll == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (enroll.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond enroll.errors, view:'edit'
            return
        }

        enroll.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'enroll.label', default: 'Enroll'), enroll.id])
                redirect enroll
            }
            '*'{ respond enroll, [status: OK] }
        }
    }

    @Transactional
    def delete(Enroll enroll) {

        if (enroll == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        enroll.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'enroll.label', default: 'Enroll'), enroll.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'enroll.label', default: 'Enroll'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
