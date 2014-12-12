package org.hawkmicrotek

class Blog {

	String category
	String series
	Date createdDate = new Date()
	String blogText
	String author
	String title
	
    static constraints = {
		category(blank:false)
		series(blank:false)
		blogText(blank:false)
		author(blank:false)
		title(blank:false)
    }
}

