package com.BrowserstackAssignment;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import browserstack.shaded.org.json.simple.JSONObject;

public class testcase {
	WebDriver driver;
	final JavascriptExecutor jse = (JavascriptExecutor) driver;
	JSONObject executorObject = new JSONObject();
	JSONObject argumentsObject = new JSONObject();
	argumentsObject.put("name", "<test-name>");
	executorObject.put("action", "setSessionName");
	executorObject.put("arguments", argumentsObject);
	jse.executeScript(String.format("browserstack_executor: %s", executorObject));

}
