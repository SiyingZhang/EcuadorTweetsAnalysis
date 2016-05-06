package edu.siying.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import twitter4j.Status;
import edu.siying.util.DBConnector;

public class TweetsDao {
	
	private Connection connection;
	private String query = "";
	
	public TweetsDao() {
		connection = DBConnector.getConnection();
		System.out.println("=========== TweetsDao connected. ===========");
	}
	
	/**
	 * Insert tweets into DB
	 * @param Status tweet
	 * @return long tweetID
	 */
	public long insertTweets(Status tweet) {
		
		String location = "";
		if(tweet.getGeoLocation() == null) {
			
			location = tweet.getUser().getLocation();
			
			//Only leave the country substring
			if(location.contains(", ")) {
				location = location.substring(location.indexOf(",")+2);
			} else if(location.contains(",")){
				location = location.substring(location.indexOf(",")+1);
			}
			
			location = location.toUpperCase();	//Convert to Uppercase
			//country = tweet.getPlace().getCountry();		
		} else {
			location = tweet.getGeoLocation().toString();
			
			//Only leave the country substring
			if(location.contains(", ")) {
				location = location.substring(location.indexOf(",")+2);
			} else if(location.contains(",")){
				location = location.substring(location.indexOf(",")+1);
			}
			
			location = location.toUpperCase();	//Convert to Uppercase
			//country = tweet.getPlace().getCountry();		
		}
		
		try {
			query = "INSERT INTO ecuadordata.tweetswithLocation(tweetID, time, location) values(?, ?, ?)";
			PreparedStatement ps = connection.prepareStatement(query);

			ps.setLong(1, tweet.getId());
			ps.setString(2, tweet.getCreatedAt().toString()); //yyyy-mm-dd.
			ps.setString(3, location);
				
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			
			if(rs.next()) {
				return rs.getLong(1);
			} else {
				return -1;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return -1;
		}
	}
	
	//Get all data items
	public ResultSet selectResultSet() {
		ResultSet rs = null;
		
		try {
			query = "SELECT * FROM ecuadordata.tweetswithLocation";
			PreparedStatement ps = connection.prepareStatement(query);
			
			rs = ps.executeQuery();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public void Normalization() {
		try {
			
			query = "UPDATE ecuadordata.tweets SET `location`='Ecuador' WHERE `tweetID`='726558296132468737'";
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}
