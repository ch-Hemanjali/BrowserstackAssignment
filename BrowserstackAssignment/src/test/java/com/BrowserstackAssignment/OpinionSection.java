package com.BrowserstackAssignment;
import com.BrowserstackAssignment.Translation;
import java.awt.print.Printable;
import java.lang.Math;


import java.nio.file.StandardCopyOption;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.file.*;
import java.util.*;
import java.time.Duration;

import org.openqa.selenium.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//import com.google.common.io.Files;


public class OpinionSection {
	public void DowonloadImage(String imgurl, String filename) {
        System.out.println(imgurl);
		try {
             URI uri = URI.create(imgurl);
             InputStream in = uri.toURL().openStream();
             Path targetPath = Paths.get("download images",filename);
             Files.createDirectories(targetPath.getParent());
             Files.copy(in, targetPath, StandardCopyOption.REPLACE_EXISTING);
             System.out.println("Image downloaded as: " + filename);
		} catch (Exception e) {
			System.out.println("No image found for this article ");
		}
	}
		
		
	
	protected void OpinionData(WebDriver driver) throws InterruptedException, IOException {
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.findElement(By.xpath("//*[@id=\"csw\"]/div[1]/nav/div/a[2]")).click();
		List<WebElement> articles = driver.findElements(By.tagName("article"));
		int len = articles.size();
		List<String> dataList = new ArrayList<>();
		if (len > 0 ) {
			for (int i=0; i < Math.min(5, len); i++ ) {
				WebElement article = articles.get(i);
				System.out.println("Article " + i +"Printing------");
				WebElement headingElement = article.findElement(By.cssSelector("header > h2 > a"));
				String page = headingElement.getAttribute("href");
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				WebElement headingElement2 = wait.until(ExpectedConditions.elementToBeClickable(headingElement));
				headingElement2.click();
				Thread.sleep(2000);
				System.out.print("Calling Download Function ---");
				try {
					 WebElement imgElement = driver.findElement(By.tagName("img"));
		             String imgUrl = imgElement.getAttribute("src");
		             String fileName = "article_img"+(i+1)+".png";
		             DowonloadImage(imgUrl,fileName);
				} catch (Exception e) {
					System.out.println("No Image");
				}
				try {
					WebElement headerDataElement = driver.findElement(By.className("a_t"));
					System.out.println(headerDataElement.getText());
					dataList.add(headerDataElement.getText());
					System.out.println("Refer this link to get more details:  "+ page);
					WebElement overView = driver.findElement(By.cssSelector(".a_st"));
					System.out.println(overView.getText());
					try {
						WebElement homeTag = driver.findElement(By.cssSelector(".a_c.clearfix"));
						List<WebElement> detailEle = homeTag.findElements(By.cssSelector("p"));
						for (WebElement detailElement : detailEle) {
							System.out.println(detailElement.getText());
						}
					}
					catch(NoSuchElementException e) {
						System.out.println("catch block");
						List<WebElement> figureCaptionsElement =  driver.findElements(By.tagName("figcaption"));
						for ( int j=0; j< figureCaptionsElement.size(); j++) {
							WebElement fig = figureCaptionsElement.get(j);
							System.out.println(fig.getText());
						}
						
					}
				}
				catch (NoSuchElementException e) {
					System.out.print("Exception");
					WebElement headerDataElement1 = driver.findElement(By.cssSelector("header > div >h2"));
					System.out.println(headerDataElement1.getText());
					System.out.println("Refer this link to get more details:  "+ page);
					WebElement overView1 = driver.findElement(By.cssSelector(".a_st"));
					System.out.println(overView1.getText());
					WebElement homeTag = driver.findElement(By.cssSelector(".a_c.clearfix"));
					List<WebElement> detailEle = homeTag.findElements(By.cssSelector("p"));
					for (WebElement detailElement : detailEle) {
						System.out.println(detailElement.getText());
					}
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				finally {
					System.out.println("Nagivate back to home page");
					driver.navigate().back();
					System.out.println("-------------------------------------------------------------------------------");
					System.out.println("-------------------------------------------------------------------------------");
					Thread.sleep(2000);
				}
			}
			System.out.println("ARTICLES PRINTED");
			Translation translator = new Translation();
			translator.Translate(dataList);
			
		}else {
			System.out.println("No articles are available in the section right now, Please come back later!");
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

	}

}
