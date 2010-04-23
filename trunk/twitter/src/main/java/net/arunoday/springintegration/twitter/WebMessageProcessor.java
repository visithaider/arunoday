package net.arunoday.springintegration.twitter;

import java.util.HashMap;

import org.apache.log4j.Logger;

/**
 * Keeps a count of tweets sent using Web and logs the tweet details
 * 
 * @author Aparna Chaudhary
 * 
 */
public class WebMessageProcessor {

	static private Logger logger = Logger.getLogger(WebMessageProcessor.class);

	private int tweetCount;
	private HashMap<String, Integer> tweetCountMap = new HashMap<String, Integer>();;

	public void announce(Tweet tweet) {
		if (tweetCountMap.get(tweet.getSource()) != null) {
			tweetCountMap.put(tweet.getSource(), tweetCountMap.get(tweet
					.getSource()) + 1);
		} else {
			tweetCountMap.put(tweet.getSource(), 1);
		}
		tweetCount++;
		logger.info(String.format("%s :: %s :: %s ", tweet.getSource(), tweet
				.getUser(), tweet.getMessage()));
	}

}
