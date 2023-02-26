package com.assignment.test;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class RegDeleteEntry extends TestBase {
	@Test(groups = { "delete" }, priority = 2)
	public void deleteEntryYes() throws Exception {

		new WebDriverWait(oDriver, Duration.ofSeconds(5))
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table/tbody/tr[1]/td[4]/button")))
				.click();
		int expRowCount = oDriver.findElements(By.xpath("//table/tbody/tr")).size() - 1;
		new WebDriverWait(oDriver, Duration.ofSeconds(5))
				.until(ExpectedConditions.visibilityOfElementLocated(By.className("-active")));
		oDriver.findElement(By.xpath("//div[@class='modal -medium -active']/div/div/div/button[1]")).click();
		int actRowCount = oDriver.findElements(By.xpath("//table/tbody/tr")).size();
		if (expRowCount != 0) {
			assertEquals(actRowCount, expRowCount, "Entry could not be deleted");
		}
		else {
			List<WebElement> noRegAvailableMsg = oDriver.findElements(By.xpath("//table/tbody/tr[1]/td/div/label"));
			assertEquals(noRegAvailableMsg.get(0).getText(), "No registration available!", "Entry could not be deleted");
		}

	}

	@Test(groups = { "delete" }, priority = 1)
	public void deleteEntryNo() {
		new WebDriverWait(oDriver, Duration.ofSeconds(5))
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table/tbody/tr[1]/td[4]/button")))
				.click();
		int expRowCount = oDriver.findElements(By.xpath("//table/tbody/tr")).size();
		new WebDriverWait(oDriver, Duration.ofSeconds(5))
				.until(ExpectedConditions.visibilityOfElementLocated(By.className("-active")));
		oDriver.findElement(By.xpath("//div[@class='modal -medium -active']/div/div/div/button[2]")).click();

		int actRowCount = oDriver.findElements(By.xpath("//table/tbody/tr")).size();
		assertEquals(actRowCount, expRowCount, "Entry might have been deleted");
	}

}
