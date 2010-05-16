package net.arunoday.web.book;

import java.util.List;

import net.arunoday.model.Book;
import net.arunoday.repository.BookRepository;

import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.inmethod.grid.IDataSource;

/**
 *
 * @author Aparna Chaudhary (aparna.chaudhary@gmail.com)
 */
@SuppressWarnings("unchecked")
public class SearchBooksDataSource implements IDataSource {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private BookRepository bookRepository;

    private final IModel searchEntryModel;

    /**
     * Constructor.
     */
    public SearchBooksDataSource(IModel nameTermModel) {
        this.searchEntryModel = nameTermModel;

        InjectorHolder.getInjector().inject(this);
    }

    /**
     * {@inheritDoc}
     */
    public IModel model(Object object) {
        return new Model((Book) object);
    }

    /**
     * {@inheritDoc}
     */
    public void query(IQuery query, IQueryResult result) {
        // Determine whether we are searching on date or on name
        String searchTerm = (searchEntryModel.getObject() == null ? "" : ((String) searchEntryModel.getObject()));

        // get the actual items
        List<Book> resultList = bookRepository.findBooks(searchTerm);

        result.setItems(resultList.iterator());

        if (resultList.size() == query.getCount() + 1) {
            // if we managed to load n + 1 items (thus there will be another
            // page)
            result.setTotalCount(IQueryResult.MORE_ITEMS);
        }
        else {
            // no more items - this is the latest page
            result.setTotalCount(IQueryResult.NO_MORE_ITEMS);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void detach() {
        // EMPTY
    }
}