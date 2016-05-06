package edu.siying.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;

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
		int count = 0;
		
		try {
			while(resultSet.next()) {
				jsonObject.put("ID", resultSet.getLong(1));
				jsonObject.put("CreatedAt", resultSet.getString(2).substring(4, 10));
				jsonObject.put("Location", resultSet.getString(3));
				
				fw.write(jsonObject.toJSONString() + System.getProperty("line.separator"));
				System.out.println("Write successfully " + "====== " + (count++) + " ======");
			}
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			fw.flush();
			fw.close();
		}
		
	}
}
