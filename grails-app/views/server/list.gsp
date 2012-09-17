
<%@ page import="mmc.Server" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'server.label', default: 'Server')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-server" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-server" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="agentUrl" title="${message(code: 'server.agentUrl.label', default: 'Agent Url')}" />
					
						<g:sortableColumn property="password" title="${message(code: 'server.password.label', default: 'Password')}" />
					
						<g:sortableColumn property="username" title="${message(code: 'server.username.label', default: 'Username')}" />

                        <g:sortableColumn property="goto" title="Goto" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${serverInstanceList}" status="i" var="serverInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${serverInstance.id}">${fieldValue(bean: serverInstance, field: "agentUrl")}</g:link></td>
					
						<td>${fieldValue(bean: serverInstance, field: "password")}</td>
					
						<td>${fieldValue(bean: serverInstance, field: "username")}</td>

                        <td>
                            <g:link controller="flow" action="list" id="${serverInstance.id}">flows</g:link>
                            <g:link controller="endpoint" action="list" id="${serverInstance.id}">endpoints</g:link>

                        </td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${serverInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
