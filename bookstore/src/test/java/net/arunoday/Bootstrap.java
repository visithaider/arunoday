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

        BookRepository bookRepository = context.getBean("bookRepository", BookRepository.class);
        // logger.info(bookRepository.findAll().size());

        // logger.info(bookRepository.findBooks("aparna").size());

        // EntityManagerFactory emf = (EntityManagerFactory) context.getBean("entityManagerFactory");
        // EntityManager entityManager = emf.createEntityManager();
        // FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        // try {
        // fullTextEntityManager.createIndexer().startAndWait();
        // }
        // catch (InterruptedException e) {
        // logger.error("Indexing of data failed", e);
        // }

        logger.info(bookRepository.findBookTitles("action"));

        // Book book = new Book("New Book");
        // bookRepository.save(book);

    }
}
