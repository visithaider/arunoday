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

import java.util.HashMap;

import net.arunoday.model.Book;
import net.arunoday.web.BasePage;
import net.arunoday.web.book.model.BookDetachableModel;

import org.apache.wicket.PageParameters;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;
import org.wicketstuff.annotation.mount.MountPath;

/**
 * Book search page
 * 
 * @author Aparna Chaudhary (aparna.chaudhary@gmail.com)
 */
@MountPath(path = "books/search")
public class SearchBooksPage extends BasePage {

    private static final long serialVersionUID = 1L;

    public SearchBooksPage() {
        add(new SearchBooksPanel("searchPanel"));
        add(new EmptyPanel("resultPanel"));
    }

    /**
     * Constructor that is invoked when page is invoked without a session.
     * 
     * @param parameters Page parameters
     */
    public SearchBooksPage(PageParameters parameters) {
        add(new SearchBooksPanel("searchPanel"));
        add(new EmptyPanel("resultPanel"));
    }

    public SearchBooksPage(IModel searchBooksModel) {
        add(new SearchBooksPanel("searchPanel", searchBooksModel));

        add(new BooksSearchResultPanel("resultPanel", searchBooksModel) {
            protected void onBookSelect(AjaxRequestTarget target, BookDetachableModel model) {
                Long id = ((Book) model.getObject()).getId();

                HashMap map = new HashMap();
                map.put("id", id);

                // setResponsePage(BookPage.class, new PageParameters(map));

            }
        });
    }

    protected IModel getTitleModel() {
        return new ResourceModel("searchBooksPage.title");
    }

}