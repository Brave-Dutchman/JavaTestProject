package nl.topicus.members.web;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import nl.topicus.members.web.views.RegisterMemberPage;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 *
 * @see test.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{
    public WicketApplication() {}

	@Override
	protected void init()
	{
		super.init();
		addComponentInstantiationListener(new SpringComponentInjector(this));
    }

	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	public Class<RegisterMemberPage> getHomePage()
	{
		return RegisterMemberPage.class;
	}
}
