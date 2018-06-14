package Homework3;

import java.util.HashMap;
import java.util.Map;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;
import org.elasticsearch.spark.streaming.api.java.JavaEsSparkStreaming;

import scala.Tuple2;

public final class TwitterConsumer {
	public static void main(String[] args) throws Exception {

		SparkConf sparkConf = new SparkConf().setAppName("TwitterConsumer").setMaster("local[*]")
				.set("es.index.auto.create", "true").set("es.resource", "twitter/tweet");
		JavaStreamingContext jssc = new JavaStreamingContext(sparkConf, new Duration(2000));

		int numThreads = Integer.parseInt("1");
		Map<String, Integer> topicMap = new HashMap<>();
		String[] topics = "topic".split(",");
		System.out.println(topics);
		for (String topic : topics) {
			topicMap.put(topic, numThreads);
		}

		JavaPairReceiverInputDStream<String, String> messages = KafkaUtils.createStream(jssc, "127.0.0.1:2181",
				"my_consumer_group", topicMap);

		JavaDStream<String> tweet_json = messages.map(new Function<Tuple2<String, String>, String>() {
			@Override
			public String call(Tuple2<String, String> tuple2) {
				return tuple2._2();
			}
		});
		ProcessedTweet p = new ProcessedTweet();
		JavaDStream<Tweet> tweet = tweet_json.map(p);
		tweet.print();
		JavaEsSparkStreaming.saveToEs(tweet, "twitter/tweet");

		jssc.start();
		jssc.awaitTermination();
	}
}