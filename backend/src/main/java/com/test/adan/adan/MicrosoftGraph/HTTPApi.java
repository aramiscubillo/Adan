package com.test.adan.adan.MicrosoftGraph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.catalina.util.URLEncoder;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
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
			setParametersFromContentType(parameters, configuration.get("Content-Type"), request);
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
			setParametersFromContentType(parameters, configuration.get("Content-Type"), request);
		}	
		
		return doRequest(request);
	}
	
	private static HTTPResponse doRequest(HttpRequestBase request) throws IOException, JSONException{

		HttpClient client = HttpClientBuilder.create().build();
		HTTPResponse MSResponse; 
		
		HttpResponse responseEntity= client.execute(request);
		
		
		JSONObject responseContent = getResponseJSONObject(responseEntity.getEntity().getContent());
		MSResponse = new HTTPResponse(responseEntity.getStatusLine().getStatusCode(),responseEntity.getStatusLine().getReasonPhrase(),responseContent);

		System.out.println("Code: "+MSResponse.getCode()+" - Message Code: "+MSResponse.getMessage());
		System.out.println(responseContent);
		
		if(MSResponse.getCode()==200){
			MSResponse.setNeedRenew(false);
		}else{
			try {
				if(responseContent.getJSONObject("error").getString("code").equals("InvalidAuthenticationToken")){
					MSResponse.setNeedRenew(true);
				}
			} catch (Exception e) {
				MSResponse.setNeedRenew(false);
				throw e;
			}
		}
		return MSResponse;
	}
	
	private static JSONObject getResponseJSONObject(InputStream responseContent) throws IOException, JSONException{
		JSONObject response;
		
		BufferedReader in;
		in = new BufferedReader(new InputStreamReader(responseContent));
		
		String inputLine;
		StringBuffer responseS = new StringBuffer();
		String newLine = System.getProperty("line.separator");
		
		while((inputLine = in.readLine())!= null){
			responseS.append(inputLine);
			responseS.append(newLine);
		}
		
		in.close();
		
		response = new JSONObject(responseS.toString());
		
		return response;
	}
	
	private static void setParametersFromContentType(TreeMap<String,String> parameters, String content, HttpPost request) throws UnsupportedEncodingException{
		if(content.equals("application/x-www-form-urlencoded")){
			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
			for(Map.Entry<String, String> entry : parameters.entrySet()){
				urlParameters.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
			}
			request.setEntity(new UrlEncodedFormEntity(urlParameters));
		}else if(content.equals("application/json")){
			for(Map.Entry<String, String> entry : parameters.entrySet()){
				entry.setValue((new URLEncoder()).encode(entry.getValue(),"UTF-8"));
			}
			String json = new JSONObject(parameters).toString();
			request.setEntity(new StringEntity(json));
		}
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
