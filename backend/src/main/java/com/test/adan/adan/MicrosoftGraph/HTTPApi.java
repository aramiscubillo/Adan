package com.test.adan.adan.MicrosoftGraph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

public class HTTPApi {
	
	private static String USER_AGENT = "Mozilla/5.0";

	public static HTTPResponse sendGET(TreeMap<String, String> configuration) throws IOException, JSONException {		
		String url = configuration.get("URL");
		HttpGet request = new HttpGet(url);

		request.addHeader("User-Agent", USER_AGENT);
		request.addHeader("Content-Type",configuration.get("Content-Type"));
		
		return doRequest(request);
	}
	
	public static HTTPResponse sendGET(TreeMap<String, String> configuration, TreeMap<String, String> parameters) throws IOException, JSONException {
		String url = configuration.get("URL");
		if(parameters!=null&&parameters.size()>0){
			url+="?"+getDataString(parameters);
		}	
		
		HttpGet request = new HttpGet(url);
		
		request.addHeader("User-Agent", USER_AGENT);
		request.addHeader("Content-Type",configuration.get("Content-Type"));
		
		return doRequest(request);
	}
	
	public static HTTPResponse sendGET(TreeMap<String, String> configuration, TreeMap<String, String> parameters, TreeMap<String, String> customHeaders) throws IOException, JSONException {
		String url = configuration.get("URL");
		if(parameters!=null&&parameters.size()>0){
			url+="?"+getDataString(parameters);
		}			
		
		HttpGet request = new HttpGet(url);
		
		request.addHeader("User-Agent", USER_AGENT);
		request.addHeader("Content-Type",configuration.get("Content-Type"));
		
		if(customHeaders!=null&&customHeaders.size()>0){
			for(Map.Entry<String, String> entry : customHeaders.entrySet()){
				request.addHeader(entry.getKey(), entry.getValue());
		    }    
		}	
		
		return doRequest(request);
	}
	
	public static HTTPResponse sendPOST(TreeMap<String, String> configuration) throws IOException, JSONException{
		String url = configuration.get("URL");
		HttpPost request = new HttpPost(url);
		
		request.addHeader("User-Agent", USER_AGENT);
		request.addHeader("Content-Type",configuration.get("Content-Type"));
		
		return doRequest(request);
	}
	
	public static HTTPResponse sendPOST(TreeMap<String, String> configuration, TreeMap<String, String> parameters) throws IOException, JSONException{
		String url = configuration.get("URL");
		String params =getDataString(parameters);
		byte [] paramsByte = params.getBytes();
		
		HttpPost request = new HttpPost(url);
		
		request.addHeader("User-Agent", USER_AGENT);
		request.addHeader("Content-Type",configuration.get("Content-Type"));
		
		if(parameters!=null&&parameters.size()>0){
			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
			for(Map.Entry<String, String> entry : parameters.entrySet()){
				urlParameters.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
			}
			request.setEntity(new UrlEncodedFormEntity(urlParameters));
		}	
		
		return doRequest(request);
	}
	
	public static HTTPResponse sendPOST(TreeMap<String, String> configuration, TreeMap<String, String> parameters, TreeMap<String, String> customHeaders) throws IOException, JSONException{
		String url = configuration.get("URL");
		String params =getDataString(parameters);
		byte [] paramsByte = params.getBytes();
		
		HttpPost request = new HttpPost(url);
		
		request.addHeader("User-Agent", USER_AGENT);
		request.addHeader("Content-Type",configuration.get("Content-Type"));
		
		if(customHeaders!=null&&customHeaders.size()>0){
			for(Map.Entry<String, String> entry : customHeaders.entrySet()){
				request.addHeader(entry.getKey(), entry.getValue());
		    }    
		}
		
		if(parameters!=null&&parameters.size()>0){
			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
			for(Map.Entry<String, String> entry : parameters.entrySet()){
				urlParameters.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
			}
			request.setEntity(new UrlEncodedFormEntity(urlParameters));
		}	
		
		return doRequest(request);
	}
	
	private static HTTPResponse doRequest(HttpRequestBase request) throws IOException, JSONException{

		HttpClient client = HttpClientBuilder.create().build();
		
		org.apache.http.HttpResponse responseEntity= client.execute(request);
		
		int responseCode = responseEntity.getStatusLine().getStatusCode();
		String responseMessage = responseEntity.getStatusLine().getReasonPhrase();
		System.out.println("Code: "+responseCode+" - Message Code: "+responseMessage);
		BufferedReader in;
		if(responseCode==200){
			in = new BufferedReader(new InputStreamReader(responseEntity.getEntity().getContent()));
		}else{
			in = new BufferedReader(new InputStreamReader(responseEntity.getEntity().getContent()));			
		}
		String inputLine;
		StringBuffer response = new StringBuffer();
		String newLine = System.getProperty("line.separator");
		
		while((inputLine = in.readLine())!= null){
			response.append(inputLine);
			response.append(newLine);
		}
		
		in.close();
		
		System.out.println(response.toString());

		return new HTTPResponse(responseCode,responseMessage,new JSONObject(response.toString()));
	}
	
	/*private static HTTPResponse doRequest(HttpURLConnection connection) throws IOException, JSONException{
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
		String newLine = System.getProperty("line.separator");
		
		while((inputLine = in.readLine())!= null){
			response.append(inputLine);
			response.append(newLine);
		}
		
		in.close();
		
		System.out.println(response.toString());

		return new HTTPResponse(responseCode,responseMessage,new JSONObject(response.toString()));
	}*/
	
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
