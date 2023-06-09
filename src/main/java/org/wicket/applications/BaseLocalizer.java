package org.wicket.applications;

import org.apache.wicket.Component;
import org.apache.wicket.Localizer;
import org.apache.wicket.model.IModel;

import java.util.Locale;

public class BaseLocalizer extends Localizer {

	@Override
	public String getString(final String key, final Component component, final IModel<?> model,
			final Locale locale, final String style, final String defaultValue)
	{
		System.out.println(" Key : " + key + " defaultValue : " + defaultValue);
		return super.getString(key, component, model, locale, style, key);
	}
}
