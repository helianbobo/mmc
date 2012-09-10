package mmc

class Server {
    String agentUrl
    String username
    String password

    static constraints = {
        agentUrl(validator: {
            if (!it.startsWith("service:jmx:"))
                return ['invalid.jmxaddr']
        })
    }
}
