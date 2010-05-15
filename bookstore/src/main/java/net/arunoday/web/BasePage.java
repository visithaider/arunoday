package net.arunoday.web;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;

/**
 * Base Page for all the application pages
 * 
 * @author Aparna Chaudhary (aparna.chaudhary@gmail.com)
 */
public abstract class BasePage extends WebPage {

	/**
	 * Constructor
	 */
	public BasePage() {
		add(new Label("title", getTitleModel()));

	}

	/**
	 * @return
	 */
	protected abstract IModel<?> getTitleModel();

}
