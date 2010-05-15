/*
 * Copyright 2008-2009 Stichting JoiningTracks, The Netherlands
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.arunoday.web.book;

import java.util.List;

import net.arunoday.model.Book;
import net.arunoday.repository.BookRepository;

import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.inmethod.grid.IDataSource;

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
        List<Book> resultList = bookRepository.findBooksByTitle(searchTerm);

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