
<%@ page import="org.hawkmicrotek.Blog" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'blog.label', default: 'Blog')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-blog" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<sec:ifLoggedIn>
					<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
					<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
				</sec:ifLoggedIn>
			</ul>
		</div>
		<div id="list-blog" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="category" title="${message(code: 'blog.category.label', default: 'Category')}" />
					
						<g:sortableColumn property="series" title="${message(code: 'blog.series.label', default: 'Series')}" />
					
						<g:sortableColumn property="blogText" title="${message(code: 'blog.blogText.label', default: 'Blog Text')}" />
					
						<g:sortableColumn property="author" title="${message(code: 'blog.author.label', default: 'Author')}" />
					
						<g:sortableColumn property="title" title="${message(code: 'blog.title.label', default: 'Title')}" />
					
						<g:sortableColumn property="createdDate" title="${message(code: 'blog.createdDate.label', default: 'Created Date')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${blogInstanceList}" status="i" var="blogInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${blogInstance.id}">${fieldValue(bean: blogInstance, field: "category")}</g:link></td>
					
						<td>${fieldValue(bean: blogInstance, field: "series")}</td>
					
						<td>${fieldValue(bean: blogInstance, field: "blogText")}</td>
					
						<td>${fieldValue(bean: blogInstance, field: "author")}</td>
					
						<td>${fieldValue(bean: blogInstance, field: "title")}</td>
					
						<td><g:formatDate date="${blogInstance.createdDate}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${blogInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
