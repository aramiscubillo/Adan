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
import com.test.adan.adan.MicrosoftGraph.HTTPApi;
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
			 
			OneDriveAPI.reloadSession(AdanApplication.OneDriveSession);

		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			throw e;
		}
		return null;

	}

}
