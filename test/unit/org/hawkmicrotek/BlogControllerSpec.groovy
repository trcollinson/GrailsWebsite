package org.hawkmicrotek



import grails.test.mixin.*
import org.codehaus.groovy.grails.web.json.JSONObject
import spock.lang.*
import grails.converters.JSON

@TestFor(BlogController)
@Mock(Blog)
class BlogControllerSpec extends Specification
{

	def populateValidParams(params)
	{
		assert params != null
		params["category"] = 'Sample'
		params["series"] = 'Raspberry pi'
		params["blogText"] = 'Text of blog'
		params["author"] = 'I Am Author'
		params["title"] = 'My first blog'
	}

	void "Test the index action returns the correct model"()
	{

		when:"The index action is executed"
		controller.index()

		then:"The model is correct"
		!model.blogInstanceList
		model.blogInstanceCount == 0
	}

	void "Test the create action returns the correct model"()
	{

		when:"The create action is executed"
		controller.create()

		then:"The model is correctly created"
		model.blogInstance!= null
	}

	void "Test the save action correctly persists an instance"()
	{

		when:"The save action is executed with an invalid instance"
		request.contentType = FORM_CONTENT_TYPE
		request.method = 'POST'
		def blog = new Blog()
		blog.validate()
		controller.save(blog)

		then:"The create view is rendered again with the correct model"
		model.blogInstance!= null
		view == 'create'

		when:"The save action is executed with a valid instance"
		response.reset()
		populateValidParams(params)
		blog = new Blog(params)

		controller.save(blog)

		then:"A redirect is issued to the show action"
		response.redirectedUrl == '/blog/show/1'
		controller.flash.message != null
		Blog.count() == 1
	}

	void "Test that the show action returns the correct model"()
	{
		when:"The show action is executed with a null domain"
		controller.show(null)

		then:"A 404 error is returned"
		response.status == 404

		when:"A domain instance is passed to the show action"
		populateValidParams(params)
		def blog = new Blog(params)
		controller.show(blog)

		then:"A model is populated containing the domain instance"
		model.blogInstance == blog

		when:"An instance is passed it renders JSON"
		controller.show_as_json(blog)

		then:
		JSONObject data = JSON.parse(response.text)
		data != null
		data.category.equals("Sample")
		data.series.equals("Raspberry pi")
		data.blogText.equals("Text of blog")
		data.author.equals("I Am Author")
		data.title.equals("My first blog")
	}

	void "Test that the edit action returns the correct model"()
	{
		when:"The edit action is executed with a null domain"
		controller.edit(null)

		then:"A 404 error is returned"
		response.status == 404

		when:"A domain instance is passed to the edit action"
		populateValidParams(params)
		def blog = new Blog(params)
		controller.edit(blog)

		then:"A model is populated containing the domain instance"
		model.blogInstance == blog
	}

	void "Test the update action performs an update on a valid domain instance"()
	{
		when:"Update is called for a domain instance that doesn't exist"
		request.contentType = FORM_CONTENT_TYPE
		request.method = 'PUT'
		controller.update(null)

		then:"A 404 error is returned"
		response.redirectedUrl == '/'
		flash.message != null

		when:"An invalid domain instance is passed to the update action"
		response.reset()
		def blog = new Blog()
		blog.validate()
		controller.update(blog)

		then:"The edit view is rendered again with the invalid instance"
		view == 'edit'
		model.blogInstance == blog

		when:"A valid domain instance is passed to the update action"
		response.reset()
		populateValidParams(params)
		blog = new Blog(params).save(flush: true)
		controller.update(blog)

		then:"A redirect is issues to the show action"
		response.redirectedUrl == "/blog/show/$blog.id"
		flash.message != null
	}

	void "Test that will bring back a list of blogs based on a parameter"()
	{
		given:"Blogs are created"
		def blogArray = new Blog[5]
		def blog
		for(i in 0..2)
		{
			blog = new Blog(category:'Programming',series:'Raspberry Pi',blogText:'This is the text',author:'Me',title:i.toString())
			blogArray[i] = blog
		}

		blogArray[3] = new Blog(category:'Electronics',series:'Cameras',blogText:'This is the text',author:'Somebody',title:'3')
		blogArray[4] = new Blog(category:'Astrophotography',series:'Telescopes',blogText:'This is the text',author:'Another',title:'4')

		for (i in 0..blogArray.size()-1)
		{
			blogArray[i].save(flush:true)
		}

		when:"index is called"
		controller.index()

		then:"it should return all blogs"
		model.blogInstanceList[0] == blogArray[4]
		model.blogInstanceCount == 5
		model.blogInstanceList != null

		when:"blogsByType is call with parameters"
		params["field"] = "category"
		params["value"] = "Programming"
		controller.blogsByType()

		then:"it should return filtered results"
		def blogs = Blog.findAllByCategory("Programming")
		blogs.size() == 3
		model.blogInstanceCount == 3
		model.blogInstanceList[0].category.equals("Programming")
	}

	void "Test that the delete action deletes an instance if it exists"()
	{
		when:"The delete action is called for a null instance"
		request.contentType = FORM_CONTENT_TYPE
		request.method = 'DELETE'
		controller.delete(null)

		then:"A 404 is returned"
		response.redirectedUrl == '/'
		flash.message != null

		when:"A domain instance is created"
		response.reset()
		populateValidParams(params)
		def blog = new Blog(params).save(flush: true)

		then:"It exists"
		Blog.count() == 1

		when:"The domain instance is passed to the delete action"
		controller.delete(blog)

		then:"The instance is deleted"
		Blog.count() == 0
		response.redirectedUrl == '/'
		flash.message != null
	}
}
