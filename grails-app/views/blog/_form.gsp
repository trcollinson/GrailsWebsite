<%@ page import="org.hawkmicrotek.Blog" %>



<div class="fieldcontain ${hasErrors(bean: blogInstance, field: 'category', 'error')} required">
	<label for="category">
		<g:message code="blog.category.label" default="Category" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="category" required="" value="${blogInstance?.category}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: blogInstance, field: 'series', 'error')} required">
	<label for="series">
		<g:message code="blog.series.label" default="Series" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="series" required="" value="${blogInstance?.series}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: blogInstance, field: 'blogText', 'error')} required">
	<label for="blogText">
		<g:message code="blog.blogText.label" default="Blog Text" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="blogText" required="" value="${blogInstance?.blogText}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: blogInstance, field: 'author', 'error')} required">
	<label for="author">
		<g:message code="blog.author.label" default="Author" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="author" required="" value="${blogInstance?.author}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: blogInstance, field: 'title', 'error')} required">
	<label for="title">
		<g:message code="blog.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="title" required="" value="${blogInstance?.title}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: blogInstance, field: 'createdDate', 'error')} required">
	<label for="createdDate">
		<g:message code="blog.createdDate.label" default="Created Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="createdDate" precision="day"  value="${blogInstance?.createdDate}"  />

</div>

