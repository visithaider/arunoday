/*
 * Copyright (C) 2008 ProSyst Software GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.arunoday.web.book;

import net.arunoday.model.Book;
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

        add(form);

    }

    @Override
    protected IModel<?> getTitleModel() {
        return new Model("Create / Edit Book");
    }

}
