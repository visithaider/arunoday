package net.arunoday.activiti.demo.web;

import net.arunoday.activiti.demo.web.defect.CreateDefectPage;
import net.arunoday.activiti.demo.web.defect.TaskListPage;
import net.arunoday.activiti.demo.web.login.LoginPage;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;

/**
 * Base Page for all the application pages
 * 
 * @author Aparna Chaudhary (aparna.chaudhary@gmail.com)
 */
public abstract class BasePage extends WebPage {

	@SuppressWarnings("serial")
	public BasePage() {
		add(new Label("username", getUserName()));
		add(new Link("logout") {
			@Override
			public void onClick() {
				getSession().invalidateNow();
				setRedirect(true);
				setResponsePage(LoginPage.class);
			}
		});
		add(new Label("pageTitle", getTitleModel()));

		Link<Object> listLink = new Link<Object>("list") {
			@Override
			public void onClick() {
				setResponsePage(new TaskListPage());
			}
		};
		add(listLink);

		Link<Object> createLink = new Link<Object>("create") {
			@Override
			public void onClick() {
				setResponsePage(new CreateDefectPage());
			}
		};
		add(createLink);
	}

	/**
	 * @return
	 */
	protected abstract IModel<?> getTitleModel();

	protected String getUserName() {
		return WicketJBPMSession.get().getUser().getUsername();
	}

}
