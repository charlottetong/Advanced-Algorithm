package kungfuschool

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PurchaseController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Purchase.list(params), model:[purchaseCount: Purchase.count()]
    }

    def show(Purchase purchase) {
        respond purchase
    }

    def create() {
        respond new Purchase(params)
    }

    @Transactional
    def save(Purchase purchase) {
        if (purchase == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (purchase.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond purchase.errors, view:'create'
            return
        }

        purchase.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'purchase.label', default: 'Purchase'), purchase.id])
                redirect purchase
            }
            '*' { respond purchase, [status: CREATED] }
        }
    }

    def edit(Purchase purchase) {
        respond purchase
    }

    @Transactional
    def update(Purchase purchase) {
        if (purchase == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (purchase.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond purchase.errors, view:'edit'
            return
        }

        purchase.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'purchase.label', default: 'Purchase'), purchase.id])
                redirect purchase
            }
            '*'{ respond purchase, [status: OK] }
        }
    }

    @Transactional
    def delete(Purchase purchase) {

        if (purchase == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        purchase.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'purchase.label', default: 'Purchase'), purchase.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'purchase.label', default: 'Purchase'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
