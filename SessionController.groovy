package kungfuschool

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class SessionController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Session.list(params), model:[sessionCount: Session.count()]
    }

    def show(Session session) {
        respond session
    }

    def create() {
        respond new Session(params)
    }

    @Transactional
    def save(Session session) {
        if (session == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (session.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond session.errors, view:'create'
            return
        }

        session.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'session.label', default: 'Session'), session.id])
                redirect session
            }
            '*' { respond session, [status: CREATED] }
        }
    }

    def edit(Session session) {
        respond session
    }

    @Transactional
    def update(Session session) {
        if (session == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (session.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond session.errors, view:'edit'
            return
        }

        session.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'session.label', default: 'Session'), session.id])
                redirect session
            }
            '*'{ respond session, [status: OK] }
        }
    }

    @Transactional
    def delete(Session session) {

        if (session == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        session.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'session.label', default: 'Session'), session.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'session.label', default: 'Session'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
