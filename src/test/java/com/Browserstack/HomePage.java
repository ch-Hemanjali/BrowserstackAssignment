package com.Browserstack;

import java.io.IOException;
import java.time.Duration;
import java.util.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class HomePage {
	// Open Website

	public void Home(WebDriver driver, String s) throws InterruptedException, IOException {
		String url="https://elpais.com/";
		driver.get(url);
		if (Objects.equals(s, "web")) {
			try {
				driver.manage().window().maximize();
			}
			catch(Exception e) {
				System.out.print("It is mobile App");
			}
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			try {
				driver.findElement(By.xpath("//span[contains(text(),'Configuration')]")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//span[contains(text(),'Disagree to all')]")).click();
				Thread.sleep(1000);
			}catch (Exception e) {
				driver.findElement(By.xpath("//a[@class='pmConsentWall-button' and @onclick='acceptConsentWall(); return false;']")).click();
			}
		}else {
			System.out.print("Mobile device Home");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			try {
				driver.findElement(By.xpath("//span[contains(text(),'Accept')]")).click();
			}catch(Exception e) {
				driver.findElement(By.xpath("//span[contains(text(),'Aceptar')]")).click();
			}
			Thread.sleep(2000);
		}
		try {
			driver.findElement(By.xpath("//button[span[text()='Save']]")).click();
			Thread.sleep(1000);
		}
		catch (Exception e) {
			System.out.println("No save button");
		}
		
			OpinionSection opinionSection = new OpinionSection();
			System.out.println("Calling Opinion Section");
			opinionSection.OpinionData(driver);	
		
	}

	public static void main(String[] args) throws InterruptedException, IOException {


	}

}
