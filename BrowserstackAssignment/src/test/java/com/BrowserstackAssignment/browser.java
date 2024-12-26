package com.BrowserstackAssignment;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class browser {

	@Test
    public static void main(String[] args) throws Exception {
        // Get BrowserStack credentials from environment variables or directly
        String userName = "hemanjalichinta_cAGhlB";
        String accessKey = "KEg8zUuhvLod2bU6zE4V";

        // Set DesiredCapabilities for BrowserStack
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserName", System.getProperty("browser", "chrome"));
        caps.setCapability("browser_version", System.getProperty("browser_version", "latest"));
        caps.setCapability("os", System.getProperty("os", "Windows"));
        caps.setCapability("os_version", System.getProperty("os_version", "10"));
        caps.setCapability("browserstack.user", userName);
        caps.setCapability("browserstack.key", accessKey);

        
        HomePage homePage = new HomePage();
        homePage.Home();
    }
}
