<%@ page import="mmc.Endpoint" %>



<div class="fieldcontain ${hasErrors(bean: endpointInstance, field: 'address', 'error')} ">
	<label for="address">
		<g:message code="endpoint.address.label" default="Address" />
		
	</label>
	<g:textField name="address" value="${endpointInstance?.address}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: endpointInstance, field: 'componentName', 'error')} ">
	<label for="componentName">
		<g:message code="endpoint.componentName.label" default="Component Name" />
		
	</label>
	<g:textField name="componentName" value="${endpointInstance?.componentName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: endpointInstance, field: 'connected', 'error')} ">
	<label for="connected">
		<g:message code="endpoint.connected.label" default="Connected" />
		
	</label>
	<g:checkBox name="connected" value="${endpointInstance?.connected}" />
</div>

<div class="fieldcontain ${hasErrors(bean: endpointInstance, field: 'inbound', 'error')} ">
	<label for="inbound">
		<g:message code="endpoint.inbound.label" default="Inbound" />
		
	</label>
	<g:checkBox name="inbound" value="${endpointInstance?.inbound}" />
</div>

<div class="fieldcontain ${hasErrors(bean: endpointInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="endpoint.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${endpointInstance?.name}"/>
</div>

