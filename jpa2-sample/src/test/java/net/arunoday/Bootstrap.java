package net.arunoday;

import net.arunoday.repository.CountryRepository;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Bootstrap {

    private static final Logger logger = Logger.getLogger(Bootstrap.class);

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {
                "context/applicationContext.xml", "context/data-access-layer.xml" });

        CountryRepository countryRepository = (CountryRepository) context.getBean("countryRepository");
        logger.info(countryRepository.findAll().size());

    }

}
