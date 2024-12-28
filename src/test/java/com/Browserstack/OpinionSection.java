package com.Browserstack;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OpinionSection {

    private static final String DOWNLOAD_DIRECTORY = "download_images";

    // Method to download images
    public void downloadImage(String imgUrl, String filename) {
        System.out.println("Downloading image from URL: " + imgUrl);
        try {
            URI uri = URI.create(imgUrl);
            try (InputStream in = uri.toURL().openStream()) {
                Path targetPath = Paths.get(DOWNLOAD_DIRECTORY, filename);
                Files.createDirectories(targetPath.getParent());
                Files.copy(in, targetPath, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Image downloaded as: " + filename);
            }
        } catch (Exception e) {
            System.out.println("Failed to download image: " + e.getMessage());
        }
    }

    // Method to process each article
    private void processArticle(WebDriver driver, WebElement article, int index, List<String> dataList) {
        try {
            WebElement headingElement = article.findElement(By.cssSelector("header > h2 > a"));
            String articleUrl = headingElement.getAttribute("href");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(headingElement)).click();

            // Extract image and details
            handleArticleDetails(driver, index, articleUrl, dataList);

            // Navigate back
            driver.navigate().back();
        } catch (Exception e) {
            System.out.println("Error processing article: " + e.getMessage());
        }
    }

    // Method to handle article details
    private void handleArticleDetails(WebDriver driver, int index, String articleUrl, List<String> dataList) {
        try {
            try {
                WebElement imgElement = driver.findElement(By.tagName("img"));
                String imgUrl = imgElement.getAttribute("src");
                String filename = "article_img" + (index + 1) + ".png";
                downloadImage(imgUrl, filename);
            } catch (NoSuchElementException e) {
                System.out.println("No image found for this article.");
            }

            // Extract and print article data
            WebElement headerElement = driver.findElement(By.className("a_t"));
            System.out.println("Header: " + headerElement.getText());
            dataList.add(headerElement.getText());
            System.out.println("More details at: " + articleUrl);

            WebElement overviewElement = driver.findElement(By.cssSelector(".a_st"));
            System.out.println("Overview: " + overviewElement.getText());

            try {
                WebElement homeTag = driver.findElement(By.cssSelector(".a_c.clearfix"));
                List<WebElement> detailElements = homeTag.findElements(By.cssSelector("p"));
                for (WebElement detail : detailElements) {
                    System.out.println(detail.getText());
                }
            } catch (NoSuchElementException e) {
                System.out.println("Details section not found. Extracting captions...");
                List<WebElement> captions = driver.findElements(By.tagName("figcaption"));
                for (WebElement caption : captions) {
                    System.out.println("Caption: " + caption.getText());
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println("Minimal details available for this article.");
        }
    }

    // Main method to fetch Opinion Section data
    public void OpinionData(WebDriver driver) {
        try {
            // Navigate to the Opinion Section
            driver.findElement(By.linkText("Opinión")).click();

            List<WebElement> articles = driver.findElements(By.tagName("article"));
            int articleCount = articles.size();

            if (articleCount > 0) {
                List<String> dataList = new ArrayList<>();
                for (int i = 0; i < Math.min(5, articleCount); i++) {
                    System.out.println("Processing Article " + (i + 1));
                    processArticle(driver, articles.get(i), i, dataList);
                    System.out.println("--------------------------------------------------");
                }
                System.out.println("All articles processed.");

                Translation translator = new Translation();
                translator.Translate(dataList);
            } else {
                System.out.println("No articles available in the Opinion section. Please come back later!");
            }
        } catch (Exception e) {
            System.out.println("Error navigating Opinion Section: " + e.getMessage());
        }
    }
}
