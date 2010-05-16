package net.arunoday.web;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.arunoday.web.book.SearchBooksPage;

import org.apache.log4j.Logger;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.wicketstuff.annotation.scan.AnnotatedMountScanner;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start
 * class.
 * 
 * @see net.arunoday.Start#main(String[])
 */
public class WicketApplication extends WebApplication {
    /** The log4j logger for this class. */
    private static final Logger logger = Logger.getLogger(WicketApplication.class);

    /**
     * Constructor
     */
    public WicketApplication() {
    }

    @PersistenceContext
    EntityManager entityManager;

    @Override
    protected void init() {
        super.init();

        // Make injection of spring beans in wicket-related classes possible
        // using @SpringBean.
        addComponentInstantiationListener(new SpringComponentInjector(this));

        new AnnotatedMountScanner().scanPackage("net.arunoday.web").mount(this);

        // Wicket markup setting.
        getMarkupSettings().setStripComments(true);
        getMarkupSettings().setStripWicketTags(true);
        getMarkupSettings().setDefaultBeforeDisabledLink("");
        getMarkupSettings().setDefaultAfterDisabledLink("");

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        try {
            fullTextEntityManager.createIndexer().startAndWait();
        }
        catch (InterruptedException e) {
            logger.error("Indexing of data failed", e);
        }

    }

    /**
     * @see org.apache.wicket.Application#getHomePage()
     */
    public Class<SearchBooksPage> getHomePage() {
        return SearchBooksPage.class;
    }

}
