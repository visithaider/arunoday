package net.arunoday.web;

import net.arunoday.web.book.SearchBooksPage;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.wicketstuff.annotation.scan.AnnotatedMountScanner;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start
 * class.
 * 
 * @see net.arunoday.Start#main(String[])
 */
public class WicketApplication extends WebApplication {
    /**
     * Constructor
     */
    public WicketApplication() {
    }

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

    }

    /**
     * @see org.apache.wicket.Application#getHomePage()
     */
    public Class<SearchBooksPage> getHomePage() {
        return SearchBooksPage.class;
    }

}
