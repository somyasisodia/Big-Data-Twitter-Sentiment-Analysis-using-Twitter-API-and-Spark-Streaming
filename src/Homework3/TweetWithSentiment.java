package Homework3;

import java.io.Serializable;


public class TweetWithSentiment implements Serializable {

	private String line;
	private String cssClass;

	public TweetWithSentiment() {
	}

	public TweetWithSentiment(String line, String cssClass) {
		super();
		this.line = line;
		this.cssClass = cssClass;
	}

	public String getLine() {
		return line;
	}

	public String getCssClass() {
		return cssClass;
	}

	@Override
	public String toString() {
		return "TweetWithSentiment [line=" + line + ", class=" + cssClass + "]";
	}

}
