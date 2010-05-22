package net.arunoday.web.book;

import net.arunoday.model.Book;
import net.arunoday.repository.BookRepository;
import net.arunoday.web.BasePage;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;
import org.wicketstuff.annotation.strategy.MountMixedParam;

@MountPath(path = "books")
@MountMixedParam(parameterNames = { "id" })
public class BookDetailPage extends BasePage {

    @SpringBean
    private BookRepository bookRepository;

    public BookDetailPage(PageParameters parameters) {
        this(parameters.getLong("id"));
    }

    public BookDetailPage(Long id) {
        Book p = bookRepository.getById(id);
        add(new Label("title", p.getTitle()));
        add(new Label("isbn", p.getIsbn()));
        add(new Label("pageCount", p.getPageCount().toString()));
        add(new Label("price", p.getPrice().toString()));
    }

    @Override
    protected IModel<?> getTitleModel() {
        return new Model("BookDetails");
    }

}
