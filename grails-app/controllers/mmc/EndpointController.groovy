package mmc

class EndpointController {
    def endpointService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

//    def index() {
//        redirect(action: "list", params: params)
//    }

    def list() {
        def serverId   = params.id
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        def rs
        if (params.flow){
              rs = endpointService.listEndpointsByFlow(serverId,params.flow)
        }
        else{
              rs = endpointService.listEndpoints(serverId)
        }
        [endpointInstanceList: rs, endpointInstanceTotal:rs?.size()]

    }

    def disconnect(){
        println params
        def objectName = params.objectName
        endpointService.disconnect(params.id,objectName)
        flash.message = objectName + " connected"
        if (params.flow)
            redirect(action: "list",id: params.id,  params:[flow:params.flow])
        else
            redirect(action: "list",id: params.id)
    }
    def connect(){
        println params
        def objectName = params.objectName
        endpointService.connect(params.id,objectName)
        flash.message = objectName + " connected"
        if (params.flow)
            redirect(action: "list",id: params.id,  params:[flow:params.flow])
        else
            redirect(action: "list",id: params.id)
    }
}
