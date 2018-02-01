package com.test.adan.adan.MicrosoftGraph;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.ServletContext;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test.adan.adan.MicrosoftGraph.HTTPApi;

public class OneDriveAPI {
	


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
		
		System.out.println("Please go to the following URL to authorize the account to be used: "+url);
		
		new OneDriveAccount(clientId, clientSecret, redirectURI);
		
		return new OneDriveAccount(clientId, clientSecret, redirectURI);
	}
	
	public static void createSession(String code, OneDriveAccount account) throws IOException, JSONException{
		HTTPResponse response;
		
		TreeMap<String, String> parameters = new TreeMap<String, String>();
		parameters.put("client_id", account.getApplicationId());
		parameters.put("redirect_uri", account.getRedirectURI());
		parameters.put("client_secret", URLEncoder.encode(account.getApplicationSecret(),"UTF-8"));
		parameters.put("code", code);
		parameters.put("grant_type", "authorization_code");
		
		response=HTTPApi.sendPOST("https://login.microsoftonline.com/common/oauth2/v2.0/token", parameters);
		
		account.createSession(response.getBody().getString("access_token"), response.getBody().getString("refresh_token"));
	}
	
	public static void reloadSession(OneDriveAccount account) throws IOException, JSONException{
		HTTPResponse response;
		
		TreeMap<String, String> parameters = new TreeMap<String, String>();
		parameters.put("client_id", account.getApplicationId());
		parameters.put("redirect_uri", account.getRedirectURI());
		parameters.put("client_secret", URLEncoder.encode(account.getApplicationSecret(),"UTF-8"));
		parameters.put("refresh_token", account.getRefreshToken());
		parameters.put("grant_type", "refresh_token");
		
		response=HTTPApi.sendPOST("https://login.microsoftonline.com/common/oauth2/v2.0/token", parameters);
		
		account.createSession(response.getBody().getString("access_token"), response.getBody().getString("refresh_token"));
	}
	
	public List<ODItem> getRoot(OneDriveAccount account){
		List<ODItem> items = new ArrayList<ODItem>();
		
		return items;
	}
	
	private List<ODItem> serializeItems(JSONObject object){
		List<ODItem> items = new ArrayList<ODItem>();
		
		return items;
	}
	
	private ODFile serializeFile(JSONObject object){
		ODFile file = new ODFile();
		
		return file;
	}
	
	private ODFolder serializeFolder(JSONObject object){
		ODFolder folder = new ODFolder();
		
		return folder;
	}
}
