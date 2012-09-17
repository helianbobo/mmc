package mmc

class FlowController {
    def endpointService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

//    def index() {
//        redirect(action: "list", params: params)
//    }

    def list() {
        def serverId   = params.id
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        def server = endpointService.getServer(serverId)
        def rs = []
        server.queryNames(null, endpointService.flow_queryExp).each{println it
            def mbean = new GroovyMBean(server,it)
            println mbean.getProperty("Name")

            rs<<mbean
        }
        def flowProperties = rs.get(0)?.listAttributeNames()

        [flowProperties:flowProperties,flowInstanceList: rs, flowInstanceTotal:rs?.size()]
//        [endpointInstanceList: Endpoint.list(params), endpointInstanceTotal: Endpoint.count()]
    }

    def disconnect(){
        endpointService.disconnect(params.id,params.objectName)
        flash.message = objectName + " disconnected"
        redirect(action: "list",id: params.id)
    }
    def connect(){
        endpointService.connect(params.id,params.objectName)
        flash.message = objectName + " connected"
        redirect(action: "list",id: params.id)
    }
}
