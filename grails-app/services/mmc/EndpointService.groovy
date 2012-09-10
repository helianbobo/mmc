package mmc

import javax.management.Query
import javax.management.QueryExp
import javax.management.remote.JMXConnector
import javax.management.remote.JMXConnectorFactory
import javax.management.remote.JMXServiceURL

class EndpointService {
//    def agentUrl = "service:jmx:rmi:///jndi/rmi://localhost:1099/server"

    QueryExp queryExp = Query.eq(Query.attr("Inbound"), Query.value(true))
    def server_map = [:]


    def listEndpoints(def serverId) {
        def server
        def connector
        Server ser = Server.get(serverId)
        if (!server_map.containsKey(ser.agentUrl)) {
            Map<String, Object> env = new HashMap<String, Object>()
            if (ser.getUsername()) {
                String[] user_pass = [ser.getUsername(), ser.getPassword()]
                env.put(JMXConnector.CREDENTIALS, user_pass);
            }
            connector = JMXConnectorFactory.connect(new JMXServiceURL(ser.agentUrl), env)
            server = connector.getMBeanServerConnection()
            server_map[ser.agentUrl] = server

        }
        else {
            server = server_map[ser.agentUrl]
        }

        def rs = []
        server.queryNames(null, queryExp).each {
            println it
            def mbean = new GroovyMBean(server, it)
            println mbean.getProperty("Name")

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

    def getMBean(serverId, String objectName) {
        Server ser = Server.get(serverId)
        return new GroovyMBean(server_map[ser.agentUrl], objectName)

    }

    def disconnect(serverId, String objectName) {

        getMBean(serverId, objectName)?.invokeMethod("disconnect", new Object[0])
    }



    def connect(serverId, String objectName) {

        getMBean(serverId, objectName)?.invokeMethod("connect", new Object[0])
    }
}
