package org.hawkmicrotek
import grails.plugin.springsecurity.annotation.Secured

class SecureController
{
	def index()
	{
		redirect (controller: "Blog", action: "index")
	}
}
