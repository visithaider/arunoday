package net.arunoday.springintegration.twitter;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author Josh Long
 * 
 *         Simple struct/dto for a Tweet from users.
 * 
 */
public class Tweet implements Serializable, Comparable<Tweet> {

	private static final long serialVersionUID = 1L;

	private long tweetId;

	public long getTweetId() {
		return tweetId;
	}

	public void setTweetId(long tweetId) {
		this.tweetId = tweetId;
	}

	private String message;
	private Date received;
	private String user;
	private String source;

	public Tweet(long tweetId, String fromUser, Date received, String msg) {
		this.received = received;
		this.tweetId = tweetId;
		this.message = msg;
		this.user = fromUser;
	}

	public Tweet() {
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getReceived() {
		return received;
	}

	public void setReceived(Date received) {
		this.received = received;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.tweetId).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Tweet))
			return false;
		Tweet other = (Tweet) obj;
		return new EqualsBuilder().append(this.tweetId, other.tweetId)
				.isEquals();
	}

	public int compareTo(Tweet o) {
		return ((Long) this.tweetId).compareTo(o.tweetId);
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSource() {
		return source;
	}
}
