package org.wicket.applications;


import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.wicket.pages.Upload;

public class CrudApplication extends WebApplication {

	@Override
	public Class<? extends Page> getHomePage() {
		return Upload.class;
	}
	
	public CrudApplication() {
		
		// this.mountPage("test.html", Test.class);
	}
	
	@Override
	protected void init() {
		super.init();
		getResourceSettings().setLocalizer(new BaseLocalizer());
		System.out.print("ffff Localier --------------");
		
	}
	

	
}
