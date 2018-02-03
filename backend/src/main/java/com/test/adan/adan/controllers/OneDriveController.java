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
import java.util.List;
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
import com.test.adan.adan.MicrosoftGraph.HTTPApi;
import com.test.adan.adan.MicrosoftGraph.ODFile;
import com.test.adan.adan.MicrosoftGraph.ODFolder;
import com.test.adan.adan.MicrosoftGraph.ODItem;
import com.test.adan.adan.MicrosoftGraph.OneDriveAPI;
import com.test.adan.adan.config.AdanApplication;

import de.tuberlin.onedrivesdk.OneDriveFactory;
import de.tuberlin.onedrivesdk.OneDriveSDK;
import de.tuberlin.onedrivesdk.common.OneDriveScope;
import de.tuberlin.onedrivesdk.folder.OneFolder;

@RestController
@RequestMapping(value = "api/oneDrive")
public class OneDriveController {
	

	@Autowired
	ServletContext servletContext;
	
	@Autowired
	public OneDriveController( ServletContext pServletContext) {
		servletContext=pServletContext;
	}
	
	@RequestMapping(value = "/oAuthCode", method = RequestMethod.GET)
	public BaseResponse editPerson(@RequestParam("code") String code) throws Exception {
	
		try{
			 
			OneDriveAPI.createSession(code, AdanApplication.OneDriveSession);

		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			throw e;
		}
		return null;

	}
	
	@RequestMapping(value = "/reload", method = RequestMethod.GET)
	public BaseResponse reload() throws Exception {
	
		try{
			 
			OneDriveAPI.reloadSession (AdanApplication.OneDriveSession);

		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			throw e;
		}
		return null;

	}
	
	@RequestMapping(value = "/getRoot", method = RequestMethod.GET)
	public BaseResponse getRoot() throws Exception {
	
		try{
			 
			List<ODItem> list = OneDriveAPI.getRoot(AdanApplication.OneDriveSession);

			System.out.println("**********************************************");
			for(ODItem item:list){
				if(item.getType().equals("File")){
					ODFile file = (ODFile)item;
					System.out.println("Type: "+file.getType());
					System.out.println("Name: "+file.getName());
					System.out.println("Creator: "+file.getCreatorName());
					System.out.println("Size: "+file.getSize());
					System.out.println("File Type: "+file.getMimeType());
				}else if(item.getType().equals("Folder")){
					ODFolder folder = (ODFolder)item;
					System.out.println("Type: "+folder.getType());
					System.out.println("Name: "+folder.getName());
					System.out.println("Creator: "+folder.getCreatorName());
					System.out.println("Size: "+folder.getSize());
					System.out.println("Cant Items: "+folder.getChildCount());
				}
				System.out.println("---------------------------");
			}
			System.out.println("**********************************************");
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			throw e;
		}
		return null;

	}

}
