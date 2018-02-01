package com.test.adan.adan.MicrosoftGraph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.TreeMap;

import org.json.JSONException;
import org.json.JSONObject;

public class HTTPApi {
	
	private static String USER_AGENT = "Mozilla/5.0";

	public static HTTPResponse sendGET(String url) throws IOException, JSONException {
		
		URL urlTarget = new URL(url);
		HttpURLConnection connection = (HttpURLConnection)urlTarget.openConnection();
		
		connection.setRequestMethod("GET");
		connection.setRequestProperty("User-Agent", USER_AGENT);
		
		return doRequest(connection);
	}
	
	public static HTTPResponse sendGET(String url, TreeMap<String, String> parameters) throws IOException, JSONException {
		
		if(parameters!=null&&parameters.size()>0){
			url+="?"+getDataString(parameters);
		}	
		
		URL urlTarget = new URL(url);
		HttpURLConnection connection = (HttpURLConnection)urlTarget.openConnection();
		
		connection.setRequestMethod("GET");
		connection.setRequestProperty("User-Agent", USER_AGENT);
		
		return doRequest(connection);
	}
	
	public static HTTPResponse sendGET(String url, TreeMap<String, String> parameters, TreeMap<String, String> customHeaders) throws IOException, JSONException {
		
		if(parameters!=null&&parameters.size()>0){
			url+="?"+getDataString(parameters);
		}	
		
		
		
		URL urlTarget = new URL(url);
		HttpURLConnection connection = (HttpURLConnection)urlTarget.openConnection();
		
		connection.setRequestMethod("GET");
		connection.setRequestProperty("User-Agent", USER_AGENT);
		if(customHeaders!=null&&customHeaders.size()>0){
			for(Map.Entry<String, String> entry : customHeaders.entrySet()){
				connection.setRequestProperty(entry.getKey(), entry.getValue());
		    }    
		}	
		
		return doRequest(connection);
	}
	

	
	public static HTTPResponse sendPOST(String url, TreeMap<String, String> parameters) throws IOException, JSONException{
		String params =getDataString(parameters);
		byte [] paramsByte = params.getBytes();
		URL urlTarget = new URL(url);
		HttpURLConnection connection = (HttpURLConnection)urlTarget.openConnection();
		
		connection.setRequestMethod("POST");
		connection.setRequestProperty("User-Agent", USER_AGENT);
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
		connection.setRequestProperty("Content-Length", String.valueOf(paramsByte.length));
		connection.setDoOutput(true);
		connection.getOutputStream().write(paramsByte);
		
		return doRequest(connection);
	}
	
	private static HTTPResponse doRequest(HttpURLConnection connection) throws IOException, JSONException{
		int responseCode = connection.getResponseCode();
		String responseMessage = connection.getResponseMessage();
		System.out.println("Code: "+responseCode+" - Message Code: "+responseMessage);
		BufferedReader in;
		if(responseCode==HttpURLConnection.HTTP_OK){
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		}else{
			in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));			
		}
		String inputLine;
		StringBuffer response = new StringBuffer();
		
		while((inputLine = in.readLine())!= null){
			response.append(inputLine);
		}
		
		in.close();
		
		System.out.println(response.toString());

		return new HTTPResponse(responseCode,responseMessage,new JSONObject(response.toString()));
	}
	
	private static String getDataString(TreeMap<String, String> params) throws UnsupportedEncodingException{
	    StringBuilder result = new StringBuilder();
	    boolean first = true;
	    for(Map.Entry<String, String> entry : params.entrySet()){
	        if (first){
	            first = false;
	        }else{
	            result.append("&");    
	        }
	        result.append(entry.getKey());
	        result.append("=");
	        result.append(entry.getValue());
	    }    
	    return result.toString();
	}

}
