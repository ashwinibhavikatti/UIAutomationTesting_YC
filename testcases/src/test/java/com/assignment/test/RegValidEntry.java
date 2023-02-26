package com.assignment.test;

import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class RegValidEntry extends TestBase {

	@Test(testName="Insert valid Values",dataProvider = "newRegistrationValidData",groups = {"NewRegistration","edit"})
	public void addValidRegValues(String sName, String sSurname, String sEmail, String sPhone) {
		SoftAssert softAssert = new SoftAssert();
		setNewRegFormValues(sName, sSurname, sEmail, sPhone);
		oDriver.findElement(By.xpath("//button[@type='submit']")).click();
		new WebDriverWait(oDriver, Duration.ofSeconds(5))
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table/tbody")));
		List<WebElement> newRowData = oDriver.findElements(By.xpath("//table/tbody/tr[1]/td"));
		softAssert.assertEquals(newRowData.get(0).getText(), sName,
				"Newly registered data is not reflecting in overview page");
		softAssert.assertEquals(newRowData.get(1).getText(), sSurname,
				"Newly registered data is not reflecting in overview page");
		softAssert.assertEquals(newRowData.get(2).getText(), sEmail,
				"Newly registered data is not reflecting in overview page");
		softAssert.assertAll();
	}
}