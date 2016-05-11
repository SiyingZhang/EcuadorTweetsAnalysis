package edu.siying.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.LinkedList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Read data from MySQL and write to json file
 * @author siying
 *
 */
public class SqlToJson {
	
	private ResultSet resultSet;
	private static String FILE_PATH = "/Users/siying/Documents/pro/eclipseWorkspace/EcuatorData/WebContent/tweets.json";
	FileWriter fw = null;
	File file = null;
	
	public SqlToJson(ResultSet rs) throws IOException{
		resultSet = rs;
		file = new File(FILE_PATH);
		
		fw = new FileWriter(file, false);
	}
	
	public void writeToJson() throws Exception{
		
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		int count = 0;
		
		try {
			while(resultSet.next()) {
				JSONObject object = new JSONObject();
				object.put("ID", resultSet.getLong(1));
				object.put("CreatedAt", resultSet.getString(2).substring(4, 10));  //only get mm-dd
				object.put("Location", resultSet.getString(3));
				
				jsonArray.add(object);
				
				//System.out.println(jsonArray.toJSONString());
				System.out.println("Write successfully " + "====== " + (count++) + " ======");
			}
			
			jsonObject.put("tweets", jsonArray);
			
			fw.write(jsonObject.toJSONString());  // + System.getProperty("line.separator")
			
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			fw.flush();
			fw.close();
		}
	
	}
}
