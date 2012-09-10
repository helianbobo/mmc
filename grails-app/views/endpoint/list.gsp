
<%@ page import="mmc.Endpoint" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'endpoint.label', default: 'Endpoint')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-endpoint" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				%{--<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>--}%
			</ul>
		</div>
		<div id="list-endpoint" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="address" title="${message(code: 'endpoint.address.label', default: 'Address')}" />
					
						<g:sortableColumn property="componentName" title="${message(code: 'endpoint.componentName.label', default: 'Component Name')}" />
					
						<g:sortableColumn property="connected" title="${message(code: 'endpoint.connected.label', default: 'Connected')}" />
					
						<g:sortableColumn property="inbound" title="${message(code: 'endpoint.inbound.label', default: 'Inbound')}" />
					
						<g:sortableColumn property="name" title="${message(code: 'endpoint.name.label', default: 'Name')}" />
                        <g:sortableColumn property="action" title="Action"/>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${endpointInstanceList}" status="i" var="endpointInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td> ${fieldValue(bean: endpointInstance, field: "address")} </td>
					
						<td>${fieldValue(bean: endpointInstance, field: "componentName")}</td>
					
						<td><g:formatBoolean boolean="${endpointInstance.connected}" /></td>
					
						<td><g:formatBoolean boolean="${endpointInstance.inbound}" /></td>
					
						<td>${fieldValue(bean: endpointInstance, field: "name")}</td>
                        <td>

                            <fieldset class="buttons">
                                <g:if test="${endpointInstance.connected}">
                                  <a   href  ="${createLink(  action: "disconnect",  params:[id: params.id,objectName:endpointInstance.objectName])}">disconnect</a>
                                %{--<g:link name="disconnect"   action="disconnect" params="[objectName:${endpointInstance.objectName}]" > disconnect--}%
                                %{--</g:link>--}%
                                </g:if>
                                <g:else>
                                    <a   href  ="${createLink(  action: "connect",   params:[id: params.id,objectName:endpointInstance.objectName])}">connect</a>
                                %{--<g:link name="connect"   action="connect" params="[objectName:${endpointInstance.objectName}]" > connect</g:link>--}%
                                </g:else>


                            </fieldset>
                        </td>

					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${endpointInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
