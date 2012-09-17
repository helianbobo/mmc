package mmc

import javax.management.MBeanServerConnection
import javax.management.Query
import javax.management.QueryExp
import javax.management.remote.JMXConnector
import javax.management.remote.JMXConnectorFactory
import javax.management.remote.JMXServiceURL
import javax.management.ObjectName

class EndpointService {
//    def agentUrl = "service:jmx:rmi:///jndi/rmi://localhost:1099/server"




    QueryExp flow_queryExp = Query.eq(Query.attr("Type"),Query.value("Flow"))
   def server_map =  [:]
    def getServer(def serverId){
        def server
        def connector
        Server ser =   Server.get(serverId)
        if (!server_map.containsKey(ser.agentUrl))  {
            Map<String ,Object> env = new HashMap<String, Object>()
            if(ser.getUsername()){
                String[] user_pass = [ser.getUsername(),ser.getPassword()]
                env.put(JMXConnector.CREDENTIALS, user_pass);
            }
            connector = JMXConnectorFactory.connect(new JMXServiceURL(ser.agentUrl),env)
            server = connector.getMBeanServerConnection()

            server_map[ser.agentUrl] =  server

        }
        else{
            server = server_map[ser.agentUrl]
        }
        return server
    }



    def listEndpoints(def serverId) {
//        return listEndpoints(serverId,'*:type=Endpoint,*',null)
        Hashtable<String, String> properties  = new Hashtable<String, String>()
        properties.put("type","Endpoint")
        return listEndpoints(serverId, properties,null)
    }

    def listEndpointsByFlow(def serverId,String flowName) {
        QueryExp flow_queryExp = Query.eq(Query.attr("ComponentName"),Query.value(flowName))
        Hashtable<String, String> properties  = new Hashtable<String, String>()
        properties.put("type","Endpoint")
//        properties.put("service","\""+flowName+"\"")
        return listEndpoints(serverId,properties,flow_queryExp)
    }



    def listEndpoints(def serverId, Hashtable<String, String> properties,QueryExp queryExp ) {


        MBeanServerConnection server = getServer(serverId)
        def rs = []
        ObjectName ss =   ObjectName.getInstance("*",properties)

       //  server.queryNames(ObjectName.getInstance("*",properties ), queryExp).each{
        server.queryNames(new ObjectName(ss.toString()+",*"), queryExp).each{
            def mbean = new GroovyMBean(server,it)

            Endpoint ep = new Endpoint()
            ep.setObjectName(it)
            ep.setAddress(mbean.getProperty("Address"))
            ep.setName(mbean.getProperty("Name"))
            ep.setComponentName(mbean.getProperty("ComponentName"))
            ep.setConnected(mbean.getProperty("Connected"))
            rs << ep
        }
        return rs

    }


    def getMbaen(serverId,String objectName){
        Server ser =   Server.get(serverId)
        return new GroovyMBean(server_map[ser.agentUrl], objectName)

    }

    def disconnect(serverId,String objectName){
        getMbaen(serverId, objectName)?.invokeMethod("disconnect", new Object[0])
    }



    def connect(serverId,String objectName ){
        getMbaen(serverId, objectName)?.invokeMethod("connect", new Object[0])
    }
}
