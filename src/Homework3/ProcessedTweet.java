package Homework3;

import java.util.Date;

import org.apache.spark.api.java.function.Function;

import scala.Serializable;

public class ProcessedTweet implements Function<String, Tweet>, Serializable {
	private static final long serialVersionUID = -1814450874634020500L;
	private static final String DELIMETER = "::";

	SentimentAnalyzer sentimentAnalyzer = new SentimentAnalyzer();
	TweetWithSentiment tweetWithSentiment = new TweetWithSentiment();

	@Override
	public Tweet call(String tweet) throws Exception {

		System.out.println("My Tweet " + tweet);

		Tweet tweet2 = new Tweet();

		String[] arr = tweet.split(DELIMETER);

		System.out.println("aar[0]" + arr[0]);
		try {
			tweet2.setText(arr[0]);
			tweet2.setLocation(arr[2] + "," + arr[1]);
			// tweet2.setLocation("43, -79");
			tweet2.setPostDate(new Date().getTime());
			tweet2.setSentiment(sentimentAnalyzer.findSentiment(arr[0]));

			String json = "{" + "\"message\":\"  " + tweet2.getText() + " \"," + "\"postDate\":\" "
					+ tweet2.getPostDate() + " \"," + "\"Location\":\" " + tweet2.getLocation() + " \","
					+ "\"analysis\":\"" + tweetWithSentiment.getCssClass() + "\"" + "}";
			// System.out.println(json);

		} catch (Exception e) {
			System.out.println("Error" + e);
			e.printStackTrace();
		}
		return tweet2;
	}

}