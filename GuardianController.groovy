package kungfuschool

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class GuardianController {

    def guardianService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Guardian.list(params), model:[guardianCount: Guardian.count()]
    }

    def show(Guardian guardian) {
        respond guardian
    }

    def create() {
        respond guardianService.createWithStuId(params.studentId)
    }

    @Transactional
    def save(Guardian guardian) {
        if (guardian == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (guardian.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond guardian.errors, view:'create'
            return
        }

        guardianService.save(guardian)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'guardian.label', default: 'Guardian'), guardian.id])
                redirect guardian
            }
            '*' { respond guardian, [status: CREATED] }
        }
    }

    def edit(Guardian guardian) {
        respond guardian
    }

    @Transactional
    def update(Guardian guardian) {
        if (guardian == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (guardian.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond guardian.errors, view:'edit'
            return
        }

        guardian.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'guardian.label', default: 'Guardian'), guardian.id])
                redirect guardian
            }
            '*'{ respond guardian, [status: OK] }
        }
    }

    @Transactional
    def delete(Guardian guardian) {

        if (guardian == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        guardian.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'guardian.label', default: 'Guardian'), guardian.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'guardian.label', default: 'Guardian'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
