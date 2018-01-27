package com.test.adan.adan.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.jdbc.core.metadata.GenericTableMetaDataProvider;

public class HTTPApi {
	
	private static String USER_AGENT = "Mozilla/5.0";

	public static void sendGET(String url, TreeMap<String, String> parameters) throws IOException {
		
		if(parameters!=null&&parameters.size()>0){
			url+="?"+getDataString(parameters);
		}	
		
		URL urlTarget = new URL(url);
		HttpURLConnection connection = (HttpURLConnection)urlTarget.openConnection();
		
		connection.setRequestMethod("GET");
		connection.setRequestProperty("User-Agent", USER_AGENT);
		
		doRequest(connection);
	}
	
	public static void sendPOST(String url, TreeMap<String, String> parameters) throws IOException{
		String params =getDataString(parameters);
		if(parameters!=null&&parameters.size()>0){
			url+= "?"+params;
		}		
		URL urlTarget = new URL(url);
		HttpURLConnection connection = (HttpURLConnection)urlTarget.openConnection();
		
		connection.setRequestMethod("POST");
		connection.setRequestProperty("User-Agent", USER_AGENT);
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
		
		connection.setDoOutput(true);
		OutputStream os = connection.getOutputStream();
		os.write(params.getBytes());
		os.flush();
		os.close();
		//connection.setRequestProperty("Content-Length", longParamS);
		
		doRequest(connection);
	}
	
	private static void doRequest(HttpURLConnection connection) throws IOException{
		int responseCode = connection.getResponseCode();
		String responseMessage = connection.getResponseMessage();
		if(responseCode==HttpURLConnection.HTTP_OK){
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			String inputLine;
			StringBuffer response = new StringBuffer();
			
			while((inputLine = in.readLine())!= null){
				response.append(inputLine);
			}
			
			in.close();
			
			System.out.println(response.toString());
		}else{
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			String inputLine;
			StringBuffer response = new StringBuffer();
			
			while((inputLine = in.readLine())!= null){
				response.append(inputLine);
			}
			
			in.close();
			
			System.out.println(response.toString());
		}
	}
	
	private static String getDataString(TreeMap<String, String> params) throws UnsupportedEncodingException{
	    StringBuilder result = new StringBuilder();
	    boolean first = true;
	    for(Map.Entry<String, String> entry : params.entrySet()){
	        if (first)
	            first = false;
	        else
	            result.append("&");    
	        result.append(entry.getKey());
	        result.append("=");
	        result.append(entry.getValue());
	    }    
	    return result.toString();
	}

}
