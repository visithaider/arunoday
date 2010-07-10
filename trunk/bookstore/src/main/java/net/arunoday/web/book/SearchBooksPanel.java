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
@SuppressWarnings("serial")
public class SearchBooksPanel extends Panel {

    public SearchBooksPanel(String id, final IModel<String> searchBookModel) {
        super(id);
        setOutputMarkupId(true);

        Form<String> form = new Form<String>("form") {
            protected void onSubmit() {
                setResponsePage(new SearchBooksPage(searchBookModel));
            }
        };
        add(form);
        TextField<String> searchTerm = new TextField<String>("searchTerm", searchBookModel);
        form.add(searchTerm);
    }

    public SearchBooksPanel(String id) {
        this(id, new Model<String>(""));
    }
}
