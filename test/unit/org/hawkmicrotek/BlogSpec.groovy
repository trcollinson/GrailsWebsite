package org.hawkmicrotek

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Blog)
class BlogSpec extends Specification {

    void "has main blog properties"() {
		when: 'the blog has appropriate data'
		def blog = new Blog(category:'Coding',
							series: 'Raspberry Pi',
							blogText: 'Text of the blog',
							author: 'Me',
							title: 'A title for blog')
		
		then: 'validation should pass'
		blog.validate()
		blog.category.equals('Coding')
		blog.series.equals('Raspberry Pi')
		blog.blogText.equals('Text of the blog')
		blog.author.equals('Me')
		blog.title.equals('A title for blog')
					
		when: 'category is not blank'
		blog = new Blog()
		
		then: 'validation should fail'
		!blog.validate()
		blog.hasErrors()
		blog.errors.errorCount == 5
    }
}
