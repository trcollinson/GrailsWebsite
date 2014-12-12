package org.hawkmicrotek

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.converters.*
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured

@Transactional(readOnly = true)
class BlogController
{

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(Integer max)
	{
		params.max = Math.min(max ?: 10, 100)
		params.sort = "title"
		params.order = "desc"
		respond Blog.list(params), model:[blogInstanceCount: Blog.count()]
	}

	def show(Blog blogInstance)
	{
		respond blogInstance
	}

	def blogsByType()
	{
		def results = Blog.createCriteria().list
		{
			eq(params["field"], params["value"])
		}

		respond results, model:[blogInstanceCount:results.size()]
	}

	def createFromJson()
	{
		def data = request.JSON
		respond new Blog(data), model:[blog:data]
	}

	@Secured(['ROLE_ADMIN'])
	def create()
	{
		respond new Blog(params)
	}

	def show_as_json(Blog blogInstance)
	{
		render blogInstance as JSON
	}

	@Secured(['ROLE_ADMIN'])
	@Transactional
	def save(Blog blogInstance)
	{
		if (blogInstance == null)
		{
			notFound()
			return
		}

		if (blogInstance.hasErrors())
		{
			respond blogInstance.errors, view:'create'
			return
		}

		blogInstance.save flush:true

		request.withFormat
		{
			form multipartForm{
				flash.message = message(code: 'default.created.message', args: [
					message(code: 'blog.label', default: 'Blog'),
					blogInstance.id
				])
				redirect blogInstance
			}
			'*'{ respond blogInstance, [status: CREATED] }
		}
	}

	@Secured(['ROLE_ADMIN'])
	def edit(Blog blogInstance)
	{
		respond blogInstance
	}

	@Secured(['ROLE_ADMIN'])
	@Transactional
	def update(Blog blogInstance)
	{
		if (blogInstance == null)
		{
			notFound()
			return
		}

		if (blogInstance.hasErrors())
		{
			respond blogInstance.errors, view:'edit'
			return
		}

		blogInstance.save flush:true

		request.withFormat
		{
			form multipartForm{
				flash.message = message(code: 'default.updated.message', args: [
					message(code: 'Blog.label', default: 'Blog'),
					blogInstance.id
				])
				redirect blogInstance
			}
			'*'{ respond blogInstance, [status: OK] }
		}
	}

	@Secured(['ROLE_ADMIN'])
	@Transactional
	def delete(Blog blogInstance)
	{

		if (blogInstance == null)
		{
			notFound()
			return
		}

		blogInstance.delete flush:true

		request.withFormat
		{
			form multipartForm{
				flash.message = message(code: 'default.deleted.message', args: [
					message(code: 'Blog.label', default: 'Blog'),
					blogInstance.id
				])
				redirect action:"index", method:"GET"
			}
			'*'{ render status: NO_CONTENT }
		}
	}

	protected void notFound()
	{
		request.withFormat
		{
			form multipartForm{
				flash.message = message(code: 'default.not.found.message', args: [
					message(code: 'blog.label', default: 'Blog'),
					params.id
				])
				redirect action: "index", method: "GET"
			}
			'*'{ render status: NOT_FOUND }
		}
	}
}
