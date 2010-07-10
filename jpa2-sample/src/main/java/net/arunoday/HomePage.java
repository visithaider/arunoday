package net.arunoday;

import net.arunoday.repository.CountryRepository;

import org.apache.log4j.Logger;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * Homepage
 */
public class HomePage extends WebPage {

    private static final Logger logger = Logger.getLogger(HomePage.class);

    private static final long serialVersionUID = 1L;

    @SpringBean
    private CountryRepository countryDao;

    /**
     * Constructor that is invoked when page is invoked without a session.
     * 
     * @param parameters Page parameters
     */
    public HomePage(final PageParameters parameters) {

        add(new Label("message", "If you see this message wicket is properly configured and running"));
        if (countryDao != null) {
            countryDao.findAll();
        }
        else {
            logger.error("No Bean found for countruDao");
        }
    }
}
