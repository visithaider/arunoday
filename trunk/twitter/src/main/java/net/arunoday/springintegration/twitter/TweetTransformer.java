package net.arunoday.springintegration.twitter;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;

/**
 * Transforms the Tweet to MailMessage
 * 
 * @author Aparna Chaudhary
 * 
 */
public class TweetTransformer {

	@Autowired
	SimpleMailMessage mailMessage;

	public void setMailMessage(SimpleMailMessage mailMessage) {
		this.mailMessage = mailMessage;
	}

	public MailMessage transform(Tweet tweet) {
		MailMessage message = new SimpleMailMessage(mailMessage);
		message.setSentDate(new Date());
		message.setText(tweet.getMessage());
		return message;
	}
}
