package com.assignment.test;

import org.testng.annotations.*;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class RegInvalidEntry extends TestBase {

	@Test(testName="Insert invalid Name",dataProvider = "InvalidRegNameData",groups = {"NewRegistration","edit"})
	public void regWithInvalidName(String sName) {
		System.out.println("Name:"+sName);
		oDriver.findElement(By.id("name")).sendKeys(sName);
		oDriver.findElement(By.xpath("//button[@type='submit']")).click();
		new WebDriverWait(oDriver, Duration.ofSeconds(5))
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form/div[1]/p")));
	}

	@Test(testName="Insert invalid Surname",dataProvider = "InvalidRegSurnameData",groups = {"NewRegistration","edit"})
	public void regWithInvalidSurname(String sSurname) {
		System.out.println("Surname: "+sSurname);
		oDriver.findElement(By.id("surname")).sendKeys(sSurname);
		oDriver.findElement(By.xpath("//button[@type='submit']")).click();
		new WebDriverWait(oDriver, Duration.ofSeconds(5))
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form/div[2]/p")));
	}

	@Test(testName="Insert invalid Email",dataProvider = "InvalidRegEmailData",groups = {"NewRegistration","edit"})
	public void regWithInvalidEmail(String sEmail) {
		System.out.println("email "+sEmail);
		oDriver.findElement(By.id("email")).sendKeys(sEmail);
		oDriver.findElement(By.xpath("//button[@type='submit']")).click();
		new WebDriverWait(oDriver, Duration.ofSeconds(5))
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form/div[3]/p")));
	}

	@Test(testName="Insert invalid Phone",dataProvider = "InvalidRegPhoneData",groups = {"NewRegistration","edit"}, priority = 2)
	public void regWithInvalidPhone(String sPhone) {
		System.out.println("Phone:"+sPhone);
		oDriver.findElement(By.id("phone")).sendKeys(sPhone);
		oDriver.findElement(By.xpath("//button[@type='submit']")).click();
		new WebDriverWait(oDriver, Duration.ofSeconds(5))
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form/div[4]/p")));
	}
}
