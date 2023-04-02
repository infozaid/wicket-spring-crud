package org.wicket.applications;


import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.wicket.pages.Index;

public class RepeaterApplication extends WebApplication {

	@Override
	public Class<? extends Page> getHomePage() {
		// TODO Auto-generated method stub
		return Index.class;
	}
}
