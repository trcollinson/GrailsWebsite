package website



import org.hawkmicrotek.SecureController
import spock.lang.*

/**
 *
 */
class MyIntegrationTestSpec extends GroovyTestCase
{

	void testThis()
	{
		log.info "starting integration tests"
		def controller = new SecureController()

		controller.index()
		assertEquals "/blog/index", controller.response.redirectedUrl
	}
}
