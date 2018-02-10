package com.test.adan.adan.config;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.test.adan.adan.MicrosoftGraph.OneDriveAccount;
import com.test.adan.adan.MicrosoftGraph.OneDriveAPI;

import de.tuberlin.onedrivesdk.OneDriveFactory;
import de.tuberlin.onedrivesdk.OneDriveSDK;
import de.tuberlin.onedrivesdk.common.OneDriveScope;


@SpringBootApplication
@ComponentScan(basePackages = {"com.test.adan"})
@EnableAutoConfiguration
@EnableJpaRepositories("com.test.adan.adan.repositories")
@EnableTransactionManagement
@EntityScan(basePackages = "com.test.adan.adan.jpa")
@EnableScheduling
@EnableAsync
public class AdanApplication {

	@Autowired
	ServletContext servletContext;
	
	public static String ApplicationId = "7de29f75-164c-4d0e-84dd-7351b3e5b86e";
	
	public static String ApplicationSecret = "fmcXYCH19=+:rfwqZSB383[";
	
	public static OneDriveAccount OneDriveSession;	
	
	
	public static void main(String[] args) {
		SpringApplication.run(AdanApplication.class, args);
		OneDriveSession = OneDriveAPI.OneDriveAccount(ApplicationId, new String[]{"offline_access","files.readwrite.all"}, "https://10localoffice.tallerdelsoftware.com/api/oneDrive/oAuthCode", ApplicationSecret);
	}
}
