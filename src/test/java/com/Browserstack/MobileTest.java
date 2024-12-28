package com.Browserstack;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class MobileTest {

    public static void main(String[] args) {
        // Set the path to the ChromeDriver executable
    	System.setProperty("webdriver.chrome.driver", "C:\\Users\\heman\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");


//        // Set up ChromeOptions for mobile emulation
        ChromeOptions options = new ChromeOptions();
    	Map<String, Object> deviceMetrics = new HashMap<>();
        deviceMetrics.put("width", 360);  // Samsung Galaxy S20 width in pixels
        deviceMetrics.put("height", 800); // Samsung Galaxy S20 height in pixels
        deviceMetrics.put("pixelRatio", 3.0); // Pixel ratio for the Samsung Galaxy S20

        // Define user agent for Samsung Galaxy S20 (custom user-agent string)
        String userAgent = "Mozilla/5.0 (Linux; Android 10; SM-G980F) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.91 Mobile Safari/537.36";

        Map<String, Object> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceMetrics", deviceMetrics);
        mobileEmulation.put("userAgent", userAgent);

        options.setExperimentalOption("mobileEmulation", mobileEmulation);
        WebDriver driver = new ChromeDriver(options);

        try {
        	HomePage home = new HomePage();
        	home.Home(driver, "mobile");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
