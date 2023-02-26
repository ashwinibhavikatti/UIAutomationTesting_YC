package com.assignment.test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import com.assignment.data.DataSupplier;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class TestBase extends DataSupplier {
	public static WebDriver oDriver; // for listener to take screenshots
	// public WebDriver oDriver; // for parallel execution of tests
	public static String currentExecutionDateTime;
	public String sAppUrl;
	public static String sBrowser;

	@Parameters({ "url", "browserName" })
	@BeforeTest(groups = { "NewRegistration", "edit","delete" })
	public void beforeTest(String sAppUrl, String browserName) {
		sBrowser = browserName;
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			oDriver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			oDriver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			oDriver = new EdgeDriver();
		} else {
			WebDriverManager.chromedriver().setup();
			oDriver = new ChromeDriver();
		}
		oDriver.get(sAppUrl);
		oDriver.manage().window().maximize();

	}

	@AfterTest(groups = { "NewRegistration", "edit","delete" })
	public void afterTest() {
		oDriver.quit();
	}

	@BeforeMethod(groups = { "NewRegistration" })
	public void beforeMethod() {
		new WebDriverWait(oDriver, Duration.ofSeconds(5))
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='New registration']")))
				.click();
		new WebDriverWait(oDriver, Duration.ofSeconds(5))
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
	}

	@AfterMethod(groups = { "NewRegistration","edit"  })
	public void afterMethod() {
		new WebDriverWait(oDriver, Duration.ofSeconds(5))
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Overview']"))).click();
	}

	@BeforeTest(groups = { "edit","delete" })
	public void beforeTestEdit() {
		new WebDriverWait(oDriver, Duration.ofSeconds(5))
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='New registration']")))
				.click();
		new WebDriverWait(oDriver, Duration.ofSeconds(5))
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
		setNewRegFormValues("Ashwini", "Bhavikatti", "Ashwini.Bhavikatti@ggc.com", "+31676778787");
		oDriver.findElement(By.xpath("//button[@type='submit']")).click();
	}

	@BeforeMethod(groups = { "edit" })
	public void beforeMethodEdit() {

		new WebDriverWait(oDriver, Duration.ofSeconds(5))
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table/tbody/tr[1]/td[4]/a"))).click();
		new WebDriverWait(oDriver, Duration.ofSeconds(5))
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
	}

	public void captureScreenshot(String TestName, String fileName, String folderName) {
		if (currentExecutionDateTime == null) {
			currentExecutionDateTime = getCurrentDateForFileOrFolderName();
		}
		TakesScreenshot takesScreenshot = (TakesScreenshot) oDriver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		File destFile = new File("./Screenshots/" + currentExecutionDateTime + "/" + sBrowser + "/" + TestName + "/"
				+ folderName + "/" + fileName + getCurrentDateForFileOrFolderName() + ".jpg");
		try {
			FileUtils.copyFile(sourceFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Screenshot saved successfully");
	}

	public String getCurrentDateForFileOrFolderName() {
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm_ss");
		return myDateObj.format(myFormatObj);
	}

	public void setNewRegFormValues(String sName, String sSurname, String sEmail, String sPhone) {
		oDriver.findElement(By.id("name")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
		oDriver.findElement(By.id("name")).sendKeys(Keys.chord(Keys.BACK_SPACE));
		oDriver.findElement(By.id("name")).sendKeys(sName);
		oDriver.findElement(By.id("surname")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
		oDriver.findElement(By.id("surname")).sendKeys(Keys.chord(Keys.BACK_SPACE));
		oDriver.findElement(By.id("surname")).sendKeys(sSurname);
		oDriver.findElement(By.id("email")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
		oDriver.findElement(By.id("email")).sendKeys(Keys.chord(Keys.BACK_SPACE));
		oDriver.findElement(By.id("email")).sendKeys(sEmail);
		oDriver.findElement(By.id("phone")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
		oDriver.findElement(By.id("phone")).sendKeys(Keys.chord(Keys.BACK_SPACE));
		oDriver.findElement(By.id("phone")).sendKeys(sPhone);
	}

}
