package com.test.adan.adan.MicrosoftGraph;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test.adan.adan.MicrosoftGraph.HTTPApi;


public class OneDriveAPI {
	
	private static final TreeMap<String,TreeMap<String,String>> URLS = new TreeMap<String,TreeMap<String,String>>(){{
		put("ACCESS-TOKEN",new TreeMap<String,String>(){{
			put("URL","https://login.microsoftonline.com/common/oauth2/v2.0/token");
			put("Content-Type","application/x-www-form-urlencoded");
		}});
		put("ROOT",new TreeMap<String,String>(){{
			put("URL","https://graph.microsoft.com/v1.0/drive/root/children");
			put("Content-Type","application/json");
		}});
		put("FOLDER",new TreeMap<String,String>(){{
			put("URL","https://graph.microsoft.com/v1.0/drive/root:/[Folder]:/children");
			put("Content-Type","application/json");
		}});
		put("SUBSCRIPTION",new TreeMap<String,String>(){{
			put("URL","https://graph.microsoft.com/v1.0/subscriptions");
			put("Content-Type","application/json");
		}});
		
	}};
	
	public static OneDriveAccount OneDriveAccount(String clientId, String[] scope, String redirectURI, String clientSecret){
		String url="https://login.microsoftonline.com/common/oauth2/v2.0/authorize?client_id=";
		url+=clientId+"&scope=";
		if(scope.length>1){
			for(int x=0;x<scope.length;x++){
				url+=scope[x];
				if(x!=scope.length-1){
					url+="+";
				}
			}
		}else{
			url+=scope[0];
		}
		url+="&response_type=code&redirect_uri="+redirectURI;
		
		System.out.println("Please go to the following URL to authorize the account to be used:");
		System.out.println(url);
		
		new OneDriveAccount(clientId, clientSecret, redirectURI);
		
		return new OneDriveAccount(clientId, clientSecret, redirectURI);
	}
	
	public static void createSession(String code, OneDriveAccount account) throws IOException, JSONException{
		HTTPResponse response;
		
		TreeMap<String, String> parameters = new TreeMap<String, String>();
		parameters.put("client_id", account.getApplicationId());
		parameters.put("redirect_uri", account.getRedirectURI());
		parameters.put("client_secret", account.getApplicationSecret());
		parameters.put("code", code);
		parameters.put("grant_type", "authorization_code");
		
		response=HTTPApi.sendPOST(URLS.get("ACCESS-TOKEN"), parameters);
		
		account.createSession(response.getBody().getString("access_token"), response.getBody().getString("refresh_token"));
		
	}
	
	public static void reloadSession(OneDriveAccount account) throws IOException, JSONException{
		HTTPResponse response;
		
		TreeMap<String, String> parameters = new TreeMap<String, String>();
		parameters.put("client_id", account.getApplicationId());
		parameters.put("redirect_uri", account.getRedirectURI());
		parameters.put("client_secret", account.getApplicationSecret());
		parameters.put("refresh_token", account.getRefreshToken());
		parameters.put("grant_type", "refresh_token");
		
		response=HTTPApi.sendPOST(URLS.get("ACCESS-TOKEN"), parameters);
		
		account.createSession(response.getBody().getString("access_token"), response.getBody().getString("refresh_token"));
	}
	
	public static List<ODItem> getRoot(OneDriveAccount account) throws IOException, JSONException{
		List<ODItem> items = new ArrayList<ODItem>();
		boolean error=false;
		HTTPResponse response = HTTPApi.sendGET(URLS.get("ROOT"), null, getBaseAuthHeaders(account));
		
		if(response.getCode()==200){
			items=serializeItems(response.getBody().getJSONArray("value"));
		}else if(response.isNeedRenew()){
			reloadSession(account);
			items=getRoot(account);
		}
			
		return items;
	}
	
	public static List<ODItem> subscribeWebHook(OneDriveAccount account, String subscribeItemURL, String webHookURL) throws IOException, JSONException{
		List<ODItem> items = new ArrayList<ODItem>();
		
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.YEAR, 100);
		Date newDate = c.getTime();
		
		TreeMap<String, String> parameters = new TreeMap<String, String>();
		parameters.put("changeType", "updated");
		parameters.put("notificationUrl", webHookURL);
		parameters.put("resource", subscribeItemURL);
		parameters.put("expirationDateTime", newDate.toString());
		parameters.put("clientState", "client-specific string");
		
		HTTPResponse response = HTTPApi.sendPOST(URLS.get("SUBSCRIPTION"), parameters, getBaseAuthHeaders(account));
		
		if(response.getCode()==200){
			items=serializeItems(response.getBody().getJSONArray("value"));
		}else if(response.isNeedRenew()){
			reloadSession(account);
			items=getRoot(account);
		}
			
		return items;
	}
	
	private static List<ODItem> serializeItems(JSONArray objects) throws NumberFormatException, JSONException{
		List<ODItem> items = new ArrayList<ODItem>();
		for(int x=0;x<objects.length();x++){
			JSONObject object=objects.getJSONObject(x);
			try {
				items.add(serializeFile(object));
			} catch (JSONException e) {
				if(e.getMessage().equals("JSONObject[\"file\"] not found.")){
					try {
						items.add(serializeFolder(object));	
					} catch (JSONException ex) {
						if(!e.getMessage().equals("JSONObject[\"folder\"] not found.")){
							throw ex;
						}
					}
				}else{
					throw e;
				}
			}
		}
		
		return items;
	}
	
	private static ODFile serializeFile(JSONObject object) throws NumberFormatException, JSONException{
		String parentName;
		try {
			parentName=object.getJSONObject("parentReference").getString("name");
		} catch (JSONException ex) {
			if(ex.getMessage().equals("JSONObject[\"name\"] not found.")){
				parentName="root";
			}else{
				throw ex;
			}
		}
		ODFile file = new ODFile(object.getJSONObject("createdBy").getJSONObject("user").getString("displayName"),
				object.getJSONObject("createdBy").getJSONObject("user").getString("id"),
				object.getString("id"),
				object.getString("createdDateTime"),
				object.getString("lastModifiedDateTime"),
				object.getJSONObject("lastModifiedBy").getJSONObject("user").getString("displayName"),
				object.getJSONObject("createdBy").getJSONObject("user").getString("id"),
				object.getString("name"),
				object.getJSONObject("parentReference").getString("driveId"),
				object.getJSONObject("parentReference").getString("id"),
				parentName,
				object.getJSONObject("parentReference").getString("path"),
				object.getInt("size"),
				object.getString("webUrl"),
				"File",
				object.getJSONObject("file").getString("mimeType"));
		
		return file;
	}
	
	private static ODFolder serializeFolder(JSONObject object) throws NumberFormatException, JSONException{
		String parentName;
		try {
			parentName=object.getJSONObject("parentReference").getString("name");
		} catch (JSONException ex) {
			if(ex.getMessage().equals("JSONObject[\"name\"] not found.")){
				parentName="root";
			}else{
				throw ex;
			}
		}
		ODFolder folder = new ODFolder(object.getJSONObject("createdBy").getJSONObject("user").getString("displayName"),
				object.getJSONObject("createdBy").getJSONObject("user").getString("id"),
				object.getString("id"),
				object.getString("createdDateTime"),
				object.getString("lastModifiedDateTime"),
				object.getJSONObject("lastModifiedBy").getJSONObject("user").getString("displayName"),
				object.getJSONObject("createdBy").getJSONObject("user").getString("id"),
				object.getString("name"),
				object.getJSONObject("parentReference").getString("driveId"),
				object.getJSONObject("parentReference").getString("id"),
				parentName,
				object.getJSONObject("parentReference").getString("path"),
				object.getInt("size"),
				object.getString("webUrl"),
				"Folder",
				object.getJSONObject("folder").getInt("childCount"));
		return folder;
	}
	
	private static TreeMap<String, String> getBaseAuthHeaders(OneDriveAccount oneDriveAccount){
		TreeMap<String, String> BASE_AUTH_HEADER = new TreeMap<String,String>();

		BASE_AUTH_HEADER.put("Authorization", "bearer "+oneDriveAccount.getAccessToken());
		BASE_AUTH_HEADER.put("Accept", "application/json, text/html, text/plain, image/gif, image/jpeg, */*; q=0.2, */*; q=0.2");
		
		return BASE_AUTH_HEADER;
	}
}
