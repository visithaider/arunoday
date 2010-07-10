/**
 * 
 */

package net.arunoday.web.book.model;

import net.arunoday.entity.Book;
import net.arunoday.repository.BookRepository;

import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * 
 * @author Aparna Chaudhary (aparna.chaudhary@gmail.com)
 */
@SuppressWarnings("unchecked")
public class BookDetachableModel extends LoadableDetachableModel {

    @SpringBean
    private BookRepository bookRepository;

    private final long id;

    /**
     * @param book that is to be detachable (not null)
     */
    public BookDetachableModel(Book book) {
        super(book);
        this.id = book.getId();

        init();
    }

    /**
     * @param id id of the Book that is to be detachable
     */
    public BookDetachableModel(long id) {
        this.id = id;

        init();
    }

    private void init() {
        InjectorHolder.getInjector().inject(this);
    }

    /**
     * @return the book
     */
    public final Book getBook() {
        return (Book) getObject();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public final int hashCode() {
        return Long.valueOf(id).hashCode();
    }

    /**
     * used for dataview with ReuseIfModelsEqualStrategy item reuse strategy
     * 
     * @see org.apache.wicket.markup.repeater.ReuseIfModelsEqualStrategy
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        else if (obj == null) {
            return false;
        }
        else if (obj instanceof BookDetachableModel) {
            BookDetachableModel other = (BookDetachableModel) obj;
            return other.id == this.id;
        }
        return false;
    }

    /**
     * @see org.apache.wicket.model.LoadableDetachableModel#load()
     */
    @Override
    protected final Object load() {
        return bookRepository.getById(id);
    }
}
