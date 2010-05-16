package net.arunoday;

import net.arunoday.repository.BookRepository;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Bootstrap {

    /** The log4j logger for this class. */
    private static final Logger logger = Logger.getLogger(Bootstrap.class);

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {
                "context/applicationContext.xml", "context/data-access-layer.xml" });

        BookRepository bookRepository = (BookRepository) context.getBean("bookRepository");
        logger.info(bookRepository.findAll().size());

        logger.info(bookRepository.findBooks("aparna").size());

        // Book book = new Book("New Book");
        // bookRepository.save(book);

    }
}
