package mmc

class EndpointController {
    def endpointService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

//    def index() {
//        redirect(action: "list", params: params)
//    }

    def list() {
        def serverId = params.id
        try {
            def rs = endpointService.listEndpoints(serverId)
            [endpointInstanceList: rs, endpointInstanceTotal: rs?.size()]
        } catch (e) {
            e.printStackTrace()
            flash.message = "Connection refused"
            [endpointInstanceList: [], endpointInstanceTotal: 0]
        }

    }

    def disconnect() {
        def objectName = params.objectName
        endpointService.disconnect(params.id, objectName)
        flash.message = objectName + " disconnected"
        redirect(action: "list", id: params.id)
    }

    def connect() {
        def objectName = params.objectName
        endpointService.connect(params.id, objectName)
        flash.message = objectName + " connected"
        redirect(action: "list", id: params.id)
    }
}
