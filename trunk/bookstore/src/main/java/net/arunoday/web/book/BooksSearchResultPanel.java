package net.arunoday.web.book;

import net.arunoday.web.book.model.BookDetachableModel;
import net.arunoday.web.common.wicket.component.DataGridFactory;
import net.arunoday.web.common.wicket.component.ItemSelectionChangedCallback;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import com.inmethod.grid.datagrid.DefaultDataGrid;

/**
 * This panel displays the search results of the book to find.
 * 
 * @author Aparna Chaudhary (aparna.chaudhary@gmail.com)
 */
@SuppressWarnings("serial")
public abstract class BooksSearchResultPanel extends Panel {

    /**
     * @param id comp id (not null)
     * @param searchTermModel model that give the book title to search for
     */
    public BooksSearchResultPanel(String id, IModel<BookDetachableModel> searchTermModel) {
        super(id);
        setOutputMarkupId(true);
        initGUI(searchTermModel);
    }

    /**
     * Initialize the UI.
     * 
     * @param searchTermModel the model that contains the search term
     */
    private void initGUI(IModel<BookDetachableModel> searchTermModel) {

        DataGridFactory dataGridFactory = new DataGridFactory(new SearchBooksDataSource(searchTermModel));
        dataGridFactory.setItemSelectionChangedCallback(new ItemSelectionChangedCallback() {
            @SuppressWarnings("unchecked")
            public void onItemSelectionChanged(AjaxRequestTarget target, IModel item) {
                onBookSelect(target, (BookDetachableModel) item);
            }
        });

        dataGridFactory.addDefaultPropertyColumn("title", "title", 130, true, true);
        dataGridFactory.addDefaultPropertyColumn("isbn", "isbn", 100, true, true);
        dataGridFactory.addDefaultPropertyColumn("price", "price", 100, false, false);
        dataGridFactory.addDefaultPropertyColumn("pageCount", "pageCount", 100, false, false);
        dataGridFactory.addDefaultPropertyColumn("categories", "categories", 100, false, false);
        

        DefaultDataGrid defaultDataGrid = dataGridFactory.createDefaultDataGrid("grid");
        defaultDataGrid.setCleanSelectionOnPageChange(true);
        defaultDataGrid.setClickRowToSelect(true);
        defaultDataGrid.setAllowSelectMultiple(false);
        defaultDataGrid.setRowsPerPage(10);
        defaultDataGrid.setOutputMarkupId(true);
        defaultDataGrid.setMarkupId("booksSearchResults");
        add(defaultDataGrid);
    }

    abstract protected void onBookSelect(AjaxRequestTarget target, BookDetachableModel model);
}