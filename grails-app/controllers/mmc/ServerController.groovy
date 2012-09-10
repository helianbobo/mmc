package mmc

import org.springframework.dao.DataIntegrityViolationException

class ServerController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [serverInstanceList: Server.list(params), serverInstanceTotal: Server.count()]
    }

    def create() {
        [serverInstance: new Server(params)]
    }

    def save() {
        def serverInstance = new Server(params)
        if (!serverInstance.save(flush: true)) {
            render(view: "create", model: [serverInstance: serverInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'server.label', default: 'Server'), serverInstance.id])
        redirect(action: "show", id: serverInstance.id)
    }

    def show() {
        def serverInstance = Server.get(params.id)
        if (!serverInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'server.label', default: 'Server'), params.id])
            redirect(action: "list")
            return
        }

        [serverInstance: serverInstance]
    }

    def edit() {
        def serverInstance = Server.get(params.id)
        if (!serverInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'server.label', default: 'Server'), params.id])
            redirect(action: "list")
            return
        }

        [serverInstance: serverInstance]
    }

    def update() {
        def serverInstance = Server.get(params.id)
        if (!serverInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'server.label', default: 'Server'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (serverInstance.version > version) {
                serverInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'server.label', default: 'Server')] as Object[],
                        "Another user has updated this Server while you were editing")
                render(view: "edit", model: [serverInstance: serverInstance])
                return
            }
        }

        serverInstance.properties = params

        if (!serverInstance.save(flush: true)) {
            render(view: "edit", model: [serverInstance: serverInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'server.label', default: 'Server'), serverInstance.id])
        redirect(action: "show", id: serverInstance.id)
    }

    def delete() {
        def serverInstance = Server.get(params.id)
        if (!serverInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'server.label', default: 'Server'), params.id])
            redirect(action: "list")
            return
        }

        try {
            serverInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'server.label', default: 'Server'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'server.label', default: 'Server'), params.id])
            redirect(action: "show", id: params.id)
        }
    }

    def go(){
        println params.id

            def serverInstance = Server.get(params.id)
            if (!serverInstance) {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'server.label', default: 'Server'), params.id])
                redirect(action: "list")
                return
            }
            redirect(controller: "endpoint",action: "list", id: params.id)

    }


}
