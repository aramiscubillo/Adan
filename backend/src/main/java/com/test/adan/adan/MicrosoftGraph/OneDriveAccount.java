package com.test.adan.adan.MicrosoftGraph;

public class OneDriveAccount {

	private String applicationId;

	private String applicationSecret;
	
	private String accessToken;
	
	private String refreshToken;
	
	private String redirectURI;
	
	public OneDriveAccount(String applicationId,String applicationSecret,String redirectURI){
		this.applicationId=applicationId;
		this.applicationSecret=applicationSecret;
		this.redirectURI=redirectURI;
	}
	
	public void createSession(String token, String refreshToken){
		setAccessToken(token);
		setRefreshToken(refreshToken);
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getApplicationSecret() {
		return applicationSecret;
	}

	public void setApplicationSecret(String applicationSecret) {
		this.applicationSecret = applicationSecret;
	}

	public String getAccessToken() {
		return accessToken;
	}

	private void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	private void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getRedirectURI() {
		return redirectURI;
	}

	public void setRedirectURI(String redirectURI) {
		this.redirectURI = redirectURI;
	}
}
