package com.BrowserstackAssignment;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class HomePage {
	
	private static WebDriver driver;
	public void Home() throws InterruptedException, IOException {
		String url="https://elpais.com/";
		WebDriver driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.findElement(By.xpath("//span[contains(text(),'Configuration')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains(text(),'Disagree to all')]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"didomi-consent-popup\"]/div/div/div/div/div[4]/div/button/span")).click();
		Thread.sleep(2000);
		OpinionSection opinionSection = new OpinionSection();
		System.out.println("Calling Opinion Section");
		opinionSection.OpinionData(driver);
		
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		String url="https://elpais.com/";
		WebDriver driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.findElement(By.xpath("//span[contains(text(),'Configuration')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains(text(),'Disagree to all')]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"didomi-consent-popup\"]/div/div/div/div/div[4]/div/button/span")).click();
		Thread.sleep(2000);
		OpinionSection opinionSection = new OpinionSection();
		System.out.println("Calling Opinion Section");
		opinionSection.OpinionData(driver);
		
	}
	


}
