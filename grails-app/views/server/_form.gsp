<%@ page import="mmc.Server" %>



<div class="fieldcontain ${hasErrors(bean: serverInstance, field: 'agentUrl', 'error')} ">
	<label for="agentUrl">
		<g:message code="server.agentUrl.label" default="Agent Url" />
		
	</label>
	<g:textField name="agentUrl" value="${serverInstance?.agentUrl}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: serverInstance, field: 'username', 'error')} ">
    <label for="username">
        <g:message code="server.username.label" default="Username" />

    </label>
    <g:textField name="username" value="${serverInstance?.username}"/>
</div>


<div class="fieldcontain ${hasErrors(bean: serverInstance, field: 'password', 'error')} ">
	<label for="password">
		<g:message code="server.password.label" default="Password" />
		
	</label>
	<g:textField name="password" value="${serverInstance?.password}"/>
</div>


