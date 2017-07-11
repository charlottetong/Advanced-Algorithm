package kungfuschool

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class RankHistoryController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond RankHistory.list(params), model:[rankHistoryCount: RankHistory.count()]
    }

    def show(RankHistory rankHistory) {
        respond rankHistory
    }

    def create() {
        respond new RankHistory(params)
    }

    @Transactional
    def save(RankHistory rankHistory) {
        if (rankHistory == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (rankHistory.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond rankHistory.errors, view:'create'
            return
        }

        rankHistory.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'rankHistory.label', default: 'RankHistory'), rankHistory.id])
                redirect rankHistory
            }
            '*' { respond rankHistory, [status: CREATED] }
        }
    }

    def edit(RankHistory rankHistory) {
        respond rankHistory
    }

    @Transactional
    def update(RankHistory rankHistory) {
        if (rankHistory == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (rankHistory.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond rankHistory.errors, view:'edit'
            return
        }

        rankHistory.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'rankHistory.label', default: 'RankHistory'), rankHistory.id])
                redirect rankHistory
            }
            '*'{ respond rankHistory, [status: OK] }
        }
    }

    @Transactional
    def delete(RankHistory rankHistory) {

        if (rankHistory == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        rankHistory.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'rankHistory.label', default: 'RankHistory'), rankHistory.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'rankHistory.label', default: 'RankHistory'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
