package mmc

import javax.management.ObjectName

class Endpoint {
    transient ObjectName objectName
    String name
    String componentName
    String address
    Boolean connected
    Boolean inbound = true

    static constraints = {
    }
}
