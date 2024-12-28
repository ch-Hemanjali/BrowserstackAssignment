# BrowserstackAssignment


## BrowserStack Web Scraping, API Integration Project and Text processing using the Selenium framework 

This project demonstrates a combination of skills in web scraping, API integration, and text processing using the Selenium framework, along with cross-browser testing on BrowserStack. The solution scrapes articles from the Spanish news website El País, translates article headers, analyzes the translated text for repeated words, and validates the functionality across multiple browser and device combinations.

## Features

## 1. Web Scraping

Navigates to the Opinion section of the El País website.

Scrapes the first five articles, fetching:

Title and content (in Spanish).

Cover images, which are downloaded to the local machine.

## 2. API Integration

Integrates with the Rapid Translate Multi Traduction API to:  https://rapidapi.com/sibaridev/api/rapid-translate-multi-traduction

Translate article titles from Spanish to English.

Handle API responses and errors gracefully.


## 3. Text Analysis

Analyzes the translated headers to identify repeated words.

Prints words that appear more than twice across all headers, along with their count.

## 4. Cross-Browser Testing

Tested the solution locally.

Validates functionality across desktop and mobile browsers using BrowserStack’s cloud infrastructure with 5 parallel threads.

## Prerequisites

Software Requirements

Java 8+

Selenium WebDriver

TestNG

BrowserStack Account

BrowserStack username and access key.

Ensure the BrowserStack credentials are set in the TestBrowserstack class.

API Key

Obtain an API key from the Rapid Translate Multi Traduction API.

Setup and Execution

Step 1: Create Browserstack Account

Step 2: Install Dependencies

Ensure you have the following libraries and drivers installed:

Selenium WebDriver

TestNG

Step 3: Configure API Key and BrowserStack Credentials

Update the API key in the Translation class.

Set the BrowserStack credentials in the TestBrowserstack class.

Step 4: Run Tests

Local Execution

Run the tests locally using:

$ mvn test

BrowserStack Execution

To execute tests on BrowserStack:

Set desired capabilities in the TestBrowserstack class.

Run the tests with:

$ mvn test

Project Structure

src/main/java/com/Browserstack/x
|-- TestBrowserstack.java    # Main entry point for BrowserStack execution.
|-- HomePage.java            # Handles navigation and scraping logic.
|-- OpinionSection.java      # Extracts articles, images, and details from the Opinion section.
|-- Translation.java         # Integrates with Rapid Translate API for header translation.
|-- Occurrences.java         # Analyzes translated headers for word repetitions.

## Key Functions

## 1. TestBrowserstack

Handles cross-browser testing on BrowserStack, setting up capabilities for both desktop and mobile devices.

## 2. HomePage

Navigates to the El País homepage.

Configures settings based on the platform (desktop or mobile).

## 3. OpinionSection

Extracts article titles, content, and images.

Downloads images locally.

## 4. Translation

Translates article headers from Spanish to English using the Rapid Translate Multi Traduction API.

## 5. Occurrences

Counts words across all translated headers.

Identifies and prints words that appear more than twice.

### Sample Output

Scraped Articles

Article 1:
Title (Spanish): Título del artículo 1
Content: Este es el contenido del artículo.
Image downloaded as: article_img1.png

...

Translated Headers

Original Text: Título del artículo 1
Translated Text: Title of article 1
...

Word Repetitions

Repeated Words:
article: 3
title: 2
...


