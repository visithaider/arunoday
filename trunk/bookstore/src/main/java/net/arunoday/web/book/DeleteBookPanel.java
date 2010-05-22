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

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

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
