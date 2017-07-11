package kungfuschool

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ClassesController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Classes.list(params), model:[classesCount: Classes.count()]
    }

    def show(Classes classes) {
        respond classes
    }

    def create() {
        respond new Classes(params)
    }

    @Transactional
    def save(Classes classes) {
        if (classes == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (classes.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond classes.errors, view:'create'
            return
        }

        classes.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'classes.label', default: 'Classes'), classes.id])
                redirect classes
            }
            '*' { respond classes, [status: CREATED] }
        }
    }

    def edit(Classes classes) {
        respond classes
    }

    @Transactional
    def update(Classes classes) {
        if (classes == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (classes.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond classes.errors, view:'edit'
            return
        }

        classes.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'classes.label', default: 'Classes'), classes.id])
                redirect classes
            }
            '*'{ respond classes, [status: OK] }
        }
    }

    @Transactional
    def delete(Classes classes) {

        if (classes == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        classes.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'classes.label', default: 'Classes'), classes.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'classes.label', default: 'Classes'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
