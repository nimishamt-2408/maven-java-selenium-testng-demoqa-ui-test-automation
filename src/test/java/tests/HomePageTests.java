package tests;

import base.BaseClass;
import pages.HomePage;
import utilities.FormUtilities;

import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTests extends BaseClass {
	
	@Test
    public void verifyTitle() {
		try {
			test = extent.createTest("verifyPageTitle", "Verify DemoQA homepage title");
	        HomePage home = new HomePage(driver);
	        String actualTitle = home.getTitle();
	        test.info("Fetched page title: " + actualTitle);
	        Assert.assertEquals(actualTitle, "DEMOQA");
	        test.pass("Page title verified successfully");
		} catch (Exception e) {
			String screenshotPath = FormUtilities.takeScreenshot(driver, "verifyTitle_Fail");
            test.fail("Page title verification failed: " + e.getMessage());
            test.addScreenCaptureFromPath(screenshotPath);
            throw e;
        }
    }

}
