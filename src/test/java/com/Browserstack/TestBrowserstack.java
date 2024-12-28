package com.Browserstack;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestBrowserstack {
	public static final String user = "hemanjalich_yfcRai";
    public static final String key = "VfLHaxjoddpzcKByJR2Q";
    public static final String bkey = "https://" + user + ":" + key + "@hub-cloud.browserstack.com/wd/hub/";


    @Test
    @Parameters({"os", "deviceName", "osVersion","browserName","browserVersion","deviceOrientation"})
    public void testBrowserStack(@Optional("Windows") String os,
						    	 @Optional("") String deviceName,
						    	 @Optional("10") String osVersion,
						    	 @Optional("chrome") String browserName,
                                 @Optional("latest") String browserVersion,
                                 @Optional("") String deviceOrientation)
            throws URISyntaxException, InterruptedException, IOException {
        MutableCapabilities capabilities = new MutableCapabilities();
        String s="";
        if (deviceName == "" || deviceName.trim().isEmpty()) {
            // Desktop test case configuration
        	s="web";
            capabilities.setCapability("browserName", browserName);
            capabilities.setCapability("browserVersion", browserVersion);
            HashMap<String, Object> browserstackOptions = new HashMap<>();
            browserstackOptions.put("os", os);
            browserstackOptions.put("osVersion", osVersion);
            browserstackOptions.put("sessionName", "BrowserStack Automation Test");
            capabilities.setCapability("bstack:options", browserstackOptions);
        } else {
            // Mobile test case configuration
        	s="mobile";
        	System.out.print("Mobile device");
            capabilities.setCapability("deviceName", deviceName);
            capabilities.setCapability("osVersion", osVersion);
            capabilities.setCapability("browserName", "Chrome");  
            capabilities.setCapability("deviceOrientation", deviceOrientation);
            HashMap<String, Object> browserstackOptions = new HashMap<>();
            browserstackOptions.put("realMobile", "true");
            browserstackOptions.put("deviceOrientation", deviceOrientation);
            browserstackOptions.put("sessionName", "BrowserStack Mobile Test");
            capabilities.setCapability("bstack:options", browserstackOptions);
        }

        WebDriver driver = new RemoteWebDriver(new URI(bkey).toURL(), capabilities);

        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
            
            HomePage home = new HomePage();
            home.Home(driver,s);
        } finally {
            driver.quit();
        }
    }
}
