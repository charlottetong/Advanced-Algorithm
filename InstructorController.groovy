package kungfuschool

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class InstructorController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Instructor.list(params), model:[instructorCount: Instructor.count()]
    }

    def show(Instructor instructor) {
        respond instructor
    }

    def create() {
        respond new Instructor(params)
    }

    @Transactional
    def save(Instructor instructor) {
        if (instructor == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (instructor.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond instructor.errors, view:'create'
            return
        }

        instructor.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'instructor.label', default: 'Instructor'), instructor.id])
                redirect instructor
            }
            '*' { respond instructor, [status: CREATED] }
        }
    }

    def edit(Instructor instructor) {
        respond instructor
    }

    @Transactional
    def update(Instructor instructor) {
        if (instructor == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (instructor.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond instructor.errors, view:'edit'
            return
        }

        instructor.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'instructor.label', default: 'Instructor'), instructor.id])
                redirect instructor
            }
            '*'{ respond instructor, [status: OK] }
        }
    }

    @Transactional
    def delete(Instructor instructor) {

        if (instructor == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        instructor.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'instructor.label', default: 'Instructor'), instructor.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'instructor.label', default: 'Instructor'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
