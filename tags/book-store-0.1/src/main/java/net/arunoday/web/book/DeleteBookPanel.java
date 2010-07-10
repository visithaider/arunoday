package net.arunoday.web.book;

import net.arunoday.entity.Book;
import net.arunoday.repository.BookRepository;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 *
 * @author Aparna Chaudhary (aparna.chaudhary@gmail.com)
 */
@SuppressWarnings("serial")
public class DeleteBookPanel extends Panel {

    @SpringBean
    private BookRepository bookRepository;

    public DeleteBookPanel(String id, final ModalWindow window, final Book book) {
        super(id);
        window.setInitialHeight(150);
        window.setInitialWidth(400);
        window.setResizable(false);
        setDefaultModel(new CompoundPropertyModel<Book>(book));
        add(new Label("title"));
        add(new Label("isbn"));

        AjaxLink<Book> yes = new AjaxLink<Book>("yes") {
            public void onClick(AjaxRequestTarget target) {
                bookRepository.removeById(book.getId());
                onYes(target);
                window.close(target);
            }
        };
        add(yes);

        AjaxLink<Book> no = new AjaxLink<Book>("no") {
            public void onClick(AjaxRequestTarget target) {
                window.close(target);
            }
        };
        add(no);

    }

    protected void onYes(AjaxRequestTarget target) {

    }

}
