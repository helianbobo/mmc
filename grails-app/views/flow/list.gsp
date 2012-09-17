
 <!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'flow.label', default: 'Flow')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-flow" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-flow" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
                        <g:each in="${flowProperties}" status="i" var="flowProperty">
                            <g:sortableColumn property="${flowProperty}" title="${flowProperty}"/>
                        </g:each>

					
					</tr>
				</thead>
				<tbody>
				<g:each in="${flowInstanceList}" status="i" var="flowInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                        <g:each in="${flowProperties}" status="j" var="flowProperty">
                            <td>
                           <g:if test="${flowProperty.equals("Name")}">

                               <g:link controller="endpoint" action="list" id="${params.id}" params="[flow:flowInstance.getAt(flowProperty)]">
                               ${flowInstance.getAt(flowProperty)}  </g:link>
                           </g:if>
                            <g:else>
                                ${flowInstance.getAt(flowProperty)}
                            </g:else>
                              </td>
                        </g:each>

					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${flowInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
