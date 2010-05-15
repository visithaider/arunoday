package net.arunoday.web.book;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * Panel to provide book search functionality.
 * 
 * @author Aparna Chaudhary (aparna.chaudhary@gmail.com)
 */
public class SearchBooksPanel extends Panel {

	public SearchBooksPanel(String id, final IModel searchBookModel) {
		super(id);
		setOutputMarkupId(true);

		Form form = new Form("form") {
			protected void onSubmit() {
				setResponsePage(new SearchBooksPage(searchBookModel));
			}
		};
		add(form);
		TextField searchTerm = new TextField("searchTerm", searchBookModel);
		form.add(searchTerm);
	}

	public SearchBooksPanel(String id) {
		this(id, new Model(""));
	}
}
