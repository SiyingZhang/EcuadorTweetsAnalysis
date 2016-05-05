package edu.siying.util;

import java.util.ArrayList;
import java.util.List;

import twitter4j.Place;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;

import org.apache.commons.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class TwitterConnector {
	
	private static String CONSUMER_KEY = "BprpAFrDturAQA3Gjs6U0fJfV";
	private static String CONSUMER_SECRET = "pSqEHJ9HK66rL2FiU4u3V2XQ3Z3pg5nAAPM7QVkVEiGClz2Ncd";
	private static String ACCESS_TOKEN_KEY = "3958965555-UCkyrV0Aau9KVYXm7tiSUIN36aMlWwQxkVRZLRX";
	private static String ACCESS_TOKEN_SECRET = "ZaRUemD0cvBukdPRqlM7XgyffBRddirtIMSUWMQ5FsRSS";
	
	private Twitter twitter;
	private String tag;
	private HttpClient client;
	private HttpResponse response;
	
//	public TwitterConnector(String hashtag) throws Exception {
//		
//		//Set consumer key & secret key
//		OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
//		consumer.setTokenWithSecret(ACCESS_TOKEN_KEY, ACCESS_TOKEN_SECRET);
//		
//		this.tag = hashtag;
//		
//		HttpGet request = new HttpGet("http://api.twitter.com/1.1/search/tweets.json?q=%23" + tag + "&lang=en&include_entities=true&count=500");
//		consumer.sign(request);
//		
//		client = new DefaultHttpClient();
//		response = client.execute(request);
//	}
//	
//	public List<Status> getEcuadorTweets(String tag) {
//		response.getStatusLine().toString();
//	}
	
	public TwitterConnector() {
		ConfigurationBuilder cf = new ConfigurationBuilder();
		cf.setDebugEnabled(true)
		.setOAuthConsumerKey(CONSUMER_KEY)
		.setOAuthConsumerSecret(CONSUMER_SECRET)
		.setOAuthAccessToken(ACCESS_TOKEN_KEY)
		.setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);
		
		TwitterFactory tf = new TwitterFactory(cf.build());
		twitter = tf.getInstance();
		
	}
	
	/**
	 * Connect using REST api
	 * @return twitter
	 */
	public Twitter getConnection() {
		return twitter;
	}
	
	/**
	 * Fetch tweets by hashtag
	 * @return tweets list
	 */
	public List<Status> getEcuadorTweets(String tag) {
		
		List<Status> tweetsList = new ArrayList<Status>();
		
		try {
			Query query = new Query(tag);
			query.setLang("en");
			query.setCount(200);
			//query.setUntil("2016-05-03");
			//query.setCount(1000);
			
			QueryResult result = twitter.search(query);
			tweetsList = result.getTweets();
				
			//Print tweet information
			for(Status tweet : tweetsList) {
				
				String location = "";
				String country = "";
				
				if(tweet.getGeoLocation() == null) {
					
					location = tweet.getUser().getLocation();
					country = tweet.getPlace().getCountry();
					
				} else {
					location = tweet.getGeoLocation().toString();
				}
				
				System.out.println("ID: " + tweet.getId() + " /// Created Time: " + tweet.getCreatedAt().toString() + 
						" /// Location: " + location + "[" + country + "]");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Failed to search tweets: " + e.getMessage());
			System.exit(-1);
		}
		return tweetsList;	
	}
}
