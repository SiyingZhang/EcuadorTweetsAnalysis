package edu.siying.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Normalization {
	
	private static String FILE_PATH = "/Users/siying/Documents/pro/eclipseWorkspace/EcuatorData/data/tweets.json";

	FileInputStream inFile = null;
	BufferedReader reader = null;
	
	public Normalization() throws IOException {
		inFile = new FileInputStream(FILE_PATH);
		reader = new BufferedReader(new InputStreamReader(inFile));	
	}
	
	public void ConvertLocation() throws IOException {
		String line = reader.readLine();
	}
}
