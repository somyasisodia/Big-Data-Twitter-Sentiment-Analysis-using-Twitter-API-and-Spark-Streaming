package Homework3;

import java.io.Serializable;

//import java.util.Date;

public class Tweet implements Serializable {

	String text;
	String Location;
	Long PostDate;
	String Sentiment;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String string) {
		Location = string;
	}

	public long getPostDate() {
		return PostDate;
	}

	public void setPostDate(long l) {
		PostDate = l;
	}

	public String getSentiment() {
		return Sentiment;
	}

	public void setSentiment(String tweetWithSentiment) {
		Sentiment = tweetWithSentiment;
	}

	@Override
	public String toString() {
		return new String(text + "," + Location + "," + Sentiment + "," + PostDate);
	}

}
