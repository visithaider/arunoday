package net.arunoday.web.book;

import java.util.List;

import net.arunoday.entity.Book;
import net.arunoday.repository.BookRepository;
import net.arunoday.web.BasePage;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 *
 * @author Aparna Chaudhary (aparna.chaudhary@gmail.com)
 */
@SuppressWarnings("serial")
public class ListBooksPage extends BasePage {

    @SpringBean
    private BookRepository bookRepository;

    private ModalWindow modal;

    public ListBooksPage() {
        List<Book> books = bookRepository.findAll();
        WebMarkupContainer wmc = new WebMarkupContainer("booksContainer");
        wmc.setOutputMarkupId(true);

        ListView<Book> bookListView = new ListView<Book>("book", books) {
            @Override
            protected void populateItem(final ListItem<Book> item) {
                item.setOutputMarkupId(true);

                final Book book = item.getModelObject();
                item.setModel(new CompoundPropertyModel<Book>(book));

                item.add(new Label("title"));
                item.add(new Label("isbn"));
                item.add(new Label("pageCount"));
                item.add(new Label("price"));
                item.add(new Label("categories"));

                Link<Object> details = new Link<Object>("details") {
                    @Override
                    public void onClick() {
                        BookDetailPage pd = new BookDetailPage(book.getId());
                        setResponsePage(pd);
                    }
                };
                item.add(details);

                Link<Object> edit = new Link<Object>("edit") {
                    @Override
                    public void onClick() {
                        EditBookPage ep = new EditBookPage(book.getId());
                        setResponsePage(ep);
                    }
                };
                item.add(edit);

                AjaxLink<Object> delete = new AjaxLink<Object>("delete") {
                    public void onClick(AjaxRequestTarget target) {
                        DeleteBookPanel deletePanel = new DeleteBookPanel(modal.getContentId(), modal, book) {
                            protected void onYes(AjaxRequestTarget target) {
                                item.setVisible(false);
                                target.addComponent(item);
                            }
                        };
                        modal.setContent(deletePanel);
                        modal.show(target);
                    }
                };
                item.add(delete);
            }
        };
        wmc.add(bookListView);

        add(wmc);

        modal = new ModalWindow("modal");
        add(modal);

    }

    @Override
    protected IModel<String> getTitleModel() {
        return new Model<String>("ListBook");
    }
}
