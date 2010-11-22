package net.arunoday.activiti.demo.web.login;

import net.arunoday.activiti.demo.entity.User;
import net.arunoday.activiti.demo.entity.UserRole;
import net.arunoday.activiti.demo.service.UserService;
import net.arunoday.activiti.demo.web.WicketJBPMSession;
import net.arunoday.activiti.demo.web.defect.CreateDefectPage;
import net.arunoday.activiti.demo.web.defect.TaskListPage;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.IndicatingAjaxButton;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.value.ValueMap;

/**
 * 
 * @author Aparna Chaudhary
 */
public class LoginPage extends WebPage {

	@SpringBean
	private UserService userService;

	private final ValueMap prop = new ValueMap();

	@SuppressWarnings("serial")
	public LoginPage() {

		final FeedbackPanel feedback = new FeedbackPanel("feedback");
		add(feedback.setOutputMarkupId(true));

		final Form form = new StatelessForm("login");
		add(form);

		final RequiredTextField<String> username = new RequiredTextField<String>(
				"username", new PropertyModel(prop, "username"));
		form.add(username);
		final PasswordTextField password = new PasswordTextField("password",
				new PropertyModel(prop, "password"));
		form.add(password);

		final IndicatingAjaxButton submit = new IndicatingAjaxButton("submit",
				form) {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form form) {
				final String username = prop.getString("username");
				final String password = prop.getString("password");
				boolean authenticated = userService.authenticateUser(username,
						password);
				if (authenticated) {
					final User user = userService.findUser(username);
					WicketJBPMSession.get().setUser(user);

					if (UserRole.TESTER.equals(user.getUserRole())) {
						setResponsePage(CreateDefectPage.class);
					} else {
						setResponsePage(TaskListPage.class);
					}
				}
				feedback.error("User does not exist.");
				target.addComponent(feedback);
			}

			@Override
			protected void onError(AjaxRequestTarget target, Form form) {
				target.addComponent(feedback);
				super.onError(target, form);
			}
		};

		form.add(submit);
	}

}
