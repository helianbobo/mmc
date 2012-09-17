<%@ page import="mmc.Flow" %>



<div class="fieldcontain ${hasErrors(bean: flowInstance, field: 'averageProcessingTime', 'error')} required">
	<label for="averageProcessingTime">
		<g:message code="flow.averageProcessingTime.label" default="Average Processing Time" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="averageProcessingTime" required="" value="${fieldValue(bean: flowInstance, field: 'averageProcessingTime')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: flowInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="flow.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${flowInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: flowInstance, field: 'objectName', 'error')} required">
	<label for="objectName">
		<g:message code="flow.objectName.label" default="Object Name" />
		<span class="required-indicator">*</span>
	</label>
	
</div>

<div class="fieldcontain ${hasErrors(bean: flowInstance, field: 'statistics', 'error')} required">
	<label for="statistics">
		<g:message code="flow.statistics.label" default="Statistics" />
		<span class="required-indicator">*</span>
	</label>
	
</div>

<div class="fieldcontain ${hasErrors(bean: flowInstance, field: 'type', 'error')} ">
	<label for="type">
		<g:message code="flow.type.label" default="Type" />
		
	</label>
	<g:textField name="type" value="${flowInstance?.type}"/>
</div>

