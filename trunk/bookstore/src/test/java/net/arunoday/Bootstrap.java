package net.arunoday;

import net.arunoday.model.Book;
import net.arunoday.repository.BookRepository;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Bootstrap {

	/** The log4j logger for this class. */
	private static final Logger logger = Logger.getLogger(Bootstrap.class);

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "context/applicationContext.xml",
						"context/data-access-layer.xml" });

		// CountryRepository countryRepository = (CountryRepository)
		// context.getBean("countryRepository");
		// logger.info(countryRepository.findAll().size());

		BookRepository bookRepository = (BookRepository) context
				.getBean("bookRepository");
		logger.info(bookRepository.findAll().size());

		Book book = new Book("DDD");
//		bookRepository.removeById(3);

		logger.info(bookRepository.findBooksByTitle("DD").size());

	}

}
