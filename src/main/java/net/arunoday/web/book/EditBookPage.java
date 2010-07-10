package net.arunoday.web.book;

import net.arunoday.entity.Book;
import net.arunoday.repository.BookRepository;
import net.arunoday.web.BasePage;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

/**
 *
 * @author Aparna Chaudhary (aparna.chaudhary@gmail.com)
 */
@SuppressWarnings("serial")
@MountPath(path = "create")
public class EditBookPage extends BasePage {

    @SpringBean
    private BookRepository bookRepository;

    private Book book;

    public EditBookPage() {
        this(null);
    }

    public EditBookPage(Long id) {
        if (id != null) this.book = bookRepository.getById(id);
        else book = new Book();

        FeedbackPanel msgs = new FeedbackPanel("msgs");
        add(msgs);

        Form<Book> form = new Form<Book>("form") {
            protected void onSubmit() {
                Book saved = bookRepository.update(EditBookPage.this.book);

                setResponsePage(new BookDetailPage(saved.getId()));
                // setResponsePage(ListBooksPage.class);
            }
        };
        form.setModel(new CompoundPropertyModel<Book>(book));

        TextField<String> title = new TextField<String>("title");
        title.setRequired(true);
        form.add(title);

        TextField<String> isbn = new TextField<String>("isbn");
        isbn.setRequired(true);
        form.add(isbn);

        TextField<String> pageCount = new TextField<String>("pageCount");
        pageCount.setRequired(true);
        form.add(pageCount);

        TextField<String> price = new TextField<String>("price");
        price.setRequired(true);
        form.add(price);
        
        TextField<String> categories = new TextField<String>("categories");
        categories.setRequired(true);
        form.add(categories);
        

        add(form);

    }

    @Override
    protected IModel<String> getTitleModel() {
        return new Model<String>("Create / Edit Book");
    }

}
