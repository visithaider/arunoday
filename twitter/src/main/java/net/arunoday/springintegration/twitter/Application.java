package net.arunoday.springintegration.twitter;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.Message;
import org.springframework.integration.message.MessageHandler;

/**
 * Main class to run the application.
 * 
 * @author Aparna Chaudhary
 * 
 */
public class Application {

	static private Logger logger = Logger.getLogger(Application.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {

			ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(
					"twitter.xml");
			classPathXmlApplicationContext.start();

			DirectChannel channel = (DirectChannel) classPathXmlApplicationContext
					.getBean("inboundTweetsChannel");

			channel.subscribe(new MessageHandler() {
				public void handleMessage(Message<?> message) {
					Tweet tweet = (Tweet) message.getPayload();
					logger.info(String.format("%s :: %s :: %s ", tweet
							.getSource(), tweet.getUser(), tweet.getMessage()));
				}
			});
		} catch (Throwable th) {
			logger.warn(ExceptionUtils.getFullStackTrace(th));
		}

	}
}
