
<%@ page import="org.hawkmicrotek.Blog" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'blog.label', default: 'Blog')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-blog" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<sec:ifLoggedIn>
					<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
				</sec:ifLoggedIn>
			</ul>
		</div>
		<div id="show-blog" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list blog">
			
				<g:if test="${blogInstance?.category}">
				<li class="fieldcontain">
					<span id="category-label" class="property-label"><g:message code="blog.category.label" default="Category" /></span>
					
						<span class="property-value" aria-labelledby="category-label"><g:fieldValue bean="${blogInstance}" field="category"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${blogInstance?.series}">
				<li class="fieldcontain">
					<span id="series-label" class="property-label"><g:message code="blog.series.label" default="Series" /></span>
					
						<span class="property-value" aria-labelledby="series-label"><g:fieldValue bean="${blogInstance}" field="series"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${blogInstance?.blogText}">
				<li class="fieldcontain">
					<span id="blogText-label" class="property-label"><g:message code="blog.blogText.label" default="Blog Text" /></span>
					
						<span class="property-value" aria-labelledby="blogText-label"><g:fieldValue bean="${blogInstance}" field="blogText"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${blogInstance?.author}">
				<li class="fieldcontain">
					<span id="author-label" class="property-label"><g:message code="blog.author.label" default="Author" /></span>
					
						<span class="property-value" aria-labelledby="author-label"><g:fieldValue bean="${blogInstance}" field="author"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${blogInstance?.title}">
				<li class="fieldcontain">
					<span id="title-label" class="property-label"><g:message code="blog.title.label" default="Title" /></span>
					
						<span class="property-value" aria-labelledby="title-label"><g:fieldValue bean="${blogInstance}" field="title"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${blogInstance?.createdDate}">
				<li class="fieldcontain">
					<span id="createdDate-label" class="property-label"><g:message code="blog.createdDate.label" default="Created Date" /></span>
					
						<span class="property-value" aria-labelledby="createdDate-label"><g:formatDate date="${blogInstance?.createdDate}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<sec:ifLoggedIn>
				<g:form url="[resource:blogInstance, action:'delete']" method="DELETE">
					<fieldset class="buttons">
						<g:link class="edit" action="edit" resource="${blogInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
						<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
					</fieldset>
				</g:form>
			</sec:ifLoggedIn>
		</div>
	</body>
</html>
