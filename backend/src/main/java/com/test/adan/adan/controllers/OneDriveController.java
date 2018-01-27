package com.test.adan.adan.controllers;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.URL;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.adan.adan.contracts.BaseResponse;
import com.test.adan.adan.jpa.Person;
import com.test.adan.adan.pojos.PersonPOJO;
import com.test.adan.adan.utils.HTTPApi;

import de.tuberlin.onedrivesdk.OneDriveFactory;
import de.tuberlin.onedrivesdk.OneDriveSDK;
import de.tuberlin.onedrivesdk.common.OneDriveScope;
import de.tuberlin.onedrivesdk.folder.OneFolder;

@RestController
@RequestMapping(value = "api/oneDrive")
public class OneDriveController {
	 private final String html_response = "HTTP/1.x 200 OK\n" +
	            "Connection: close\n" +
	            "Pragma: public\n" +
	            "Cache-Control: max-age=3600, public\n" +
	            "Content-Type: text/html; charset=UTF-8\n" +
	            "Vary: Accept-Encoding, Cookie, User-Agent\n" +
	            "\n" +
	            "<!DOCTYPE html><html><head><title>This Message will autodestroy itself in 10 seconds</title></head><body><h1 id='shit'></h1><script type='text/javascript'>var x=location.search;document.getElementById(\"shit\").innerHTML=x.substr(x.indexOf(\"code=\")+5);/script></body></html>";
	    
	
	String ApplicationId = "7de29f75-164c-4d0e-84dd-7351b3e5b86e";
	
	String ApplicationSecret = "fmcXYCH19=+:rfwqZSB383[";
	
	String clientId = "7de29f75-164c-4d0e-84dd-7351b3e5b86e";
	
	String context = "https://e2f1ee4d.ngrok.io";
	
	String code = "M742e8833-768d-90a7-4c16-ae06ce53935b";
	
	ServletContext servletContext;
	
	@Autowired
	public OneDriveController( ServletContext pServletContext) {
		servletContext=pServletContext;
	}
	
	@RequestMapping(value = "/startUp")
	public BaseResponse startUp(@RequestBody PersonPOJO pojo) throws Exception {
	
		try{
			/*servletContext.setAttribute("OneDriveSDK", OneDriveFactory.createOneDriveSDK(ApplicationId, ApplicationSecret, "http://localhost",OneDriveScope.READWRITE));
			
			openWebpage(((OneDriveSDK) servletContext.getAttribute("OneDriveSDK")).getAuthenticationURL());
			
			//intercepts redirect end automatically enters the oAuth Code
	        ServerSocket serverSocket = null;
	        try {
	            serverSocket = new ServerSocket(80);
	            while (!((OneDriveSDK) servletContext.getAttribute("OneDriveSDK")).isAuthenticated()) {
	                Socket s = serverSocket.accept();
	                BufferedReader bs = new BufferedReader(new InputStreamReader(s.getInputStream()));
	                String line;
	                while ((line = bs.readLine()) != null) {
	                    Matcher m = Pattern.compile("\\?code=([^ ]+) HTTP").matcher(line);
	                    if (m.find()) {
	                    	((OneDriveSDK) servletContext.getAttribute("OneDriveSDK")).authenticate(m.group(1));
	                        OutputStream os = s.getOutputStream();
	                        os.write(new String(html_response).getBytes());
	                        os.close();
	                        break;
	                    }
	                }

	                s.close();
	            }
	            serverSocket.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }*/
			
			TreeMap<String, String> parameters = new TreeMap<String, String>();
			parameters.put("client_id", clientId);
			parameters.put("scope", "offline_access");
			parameters.put("response_type", "code");
			parameters.put("redirect_uri", context+"/api/oneDrive/oAuthCode");
			
			HTTPApi.sendGET("https://login.microsoftonline.com/common/oauth2/v2.0/authorize", parameters);
		
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			throw e;
		}
		return null;

	}
	/*
	private static void openWebpage(URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
            } catch (Exception e) {}
        }
    }
	
	private static void openWebpage(String url) {
        try {
            openWebpage(new URL(url).toURI());
        } catch (Exception e) {}
    }
	*/
	@RequestMapping(value = "/oAuthCode", method = RequestMethod.GET)
	public BaseResponse editPerson(@RequestParam("code") String code) throws Exception {
	
		try{
			/*((OneDriveSDK) servletContext.getAttribute("OneDriveSDK")).authenticate(code);
			
			OneFolder rootFolder = ((OneDriveSDK) servletContext.getAttribute("OneDriveSDK")).getRootFolder();   */          
			System.out.println(code);
			
			TreeMap<String, String> parameters = new TreeMap<String, String>();
			parameters.put("client_id", clientId);
			parameters.put("redirect_uri", context+"/api/oneDrive/oAuthCode");
			parameters.put("client_secret", ApplicationSecret);
			parameters.put("code", code);
			parameters.put("grant_type", "authorization_code");
			
			HTTPApi.sendPOST("https://login.microsoftonline.com/common/oauth2/v2.0/token", parameters);

		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			throw e;
		}
		return null;

	}

}
