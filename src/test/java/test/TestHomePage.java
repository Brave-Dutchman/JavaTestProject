package test;

import junit.framework.TestCase;
import nl.topicus.members.web.WicketApplication;
import nl.topicus.members.web.views.RegisterMemberPage;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;

/**
 * Simple test using the WicketTester
 */
public class TestHomePage extends TestCase {
	private WicketTester tester;

	@Override
	public void setUp() {
		tester = new WicketTester(new WicketApplication());
	}

	@Test
	public void testRenderMyPage() {
		//start and render the test page
		tester.startPage(RegisterMemberPage.class);

		//assert rendered page class
		tester.assertRenderedPage(RegisterMemberPage.class);

		//assert rendered label component
		tester.assertLabel("message", "If you see this message wicket is properly configured and running");
	}
}
