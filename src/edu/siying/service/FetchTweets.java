package edu.siying.service;

import java.sql.ResultSet;
import java.util.List;

import twitter4j.Status;
import edu.siying.dao.TweetsDao;
import edu.siying.util.SqlToJson;
import edu.siying.util.TwitterConnector;

/**
 * Get twitter data -> store into DB -> write into JSON file
 * @author siying
 */
public class FetchTweets {
	
	private static String EARTHQUAKE_TAG = "ecuadorearthquake";
	private static String ECUADOR_TAG = "ecuador";
//	private static String URL = "https://api.twitter.com/1.1/search/tweets.json?q=%23";
//	private static String COUNT = "&count=500";

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub		
		
//		String url_earchquake = URL + ECUADOR_TAG + COUNT;
//		String url_ecuador = URL + EARTHQUAKE_TAG + COUNT;
		
		TwitterConnector twitterConnector = new TwitterConnector();
		TweetsDao td = new TweetsDao();
		
		List<Status> eqTweets = twitterConnector.getEcuadorTweets(EARTHQUAKE_TAG);
		List<Status> noeqTweets = twitterConnector.getEcuadorTweets(ECUADOR_TAG);
			
		for(Status tweet : eqTweets) {
			Long id = td.insertTweets(tweet);
			//System.out.println("===========" + id + "Inserted Successfully!");
		}
		
		for(Status tweet : noeqTweets) {
			Long id = td.insertTweets(tweet);
			//System.out.println("===========" + id + "Inserted Successfully!");
		}
		
		//write data into json file
		/*
		ResultSet rs = td.selectResultSet();
		
		SqlToJson toJson = new SqlToJson(rs);
		toJson.writeToJson();*/

	}

}
