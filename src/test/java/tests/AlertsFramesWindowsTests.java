package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AlertsFramesWindowsPage;
import utilities.FormUtilities;
import base.BaseClass;

public class AlertsFramesWindowsTests extends BaseClass {

    @Test
    public void testSimpleAlert() {
    	test = extent.createTest("Test Simple Alert");
    	try {
    		
    		test.info("Handling simple alert...");
    		AlertsFramesWindowsPage page = new AlertsFramesWindowsPage(driver);

            String alertText = page.handleSimpleAlert();
            
            test.info("Alert text received: " + alertText);
            Assert.assertTrue(alertText != null && !alertText.isEmpty(), "Alert text should not be empty");
            
            test.pass("Simple alert handled successfully.");
    	} catch (Exception e) {
            String path = FormUtilities.takeScreenshot(driver, "SimpleAlertFail");
            test.fail("Test failed: " + e.getMessage());
            test.addScreenCaptureFromPath(path);
            throw e;
        }
        
    }

    @Test
    public void testConfirmAlert() {
    	test = extent.createTest("Test Confirm Alert");
    	try {
    		AlertsFramesWindowsPage page = new AlertsFramesWindowsPage(driver);

    		test.info("Handling confirm alert (dismiss)...");
            String confirmText = page.handleConfirmAlert(false);

            test.info("Confirm alert text: " + confirmText);
            Assert.assertTrue(confirmText.contains("Do you confirm action?"), "Unexpected confirm alert text");
            
            WebElement resultElem = driver.findElement(By.id("confirmResult"));
            test.info("Confirm result displayed: " + resultElem.getText());
            Assert.assertTrue(resultElem.getText().contains("Cancel"), "Confirm alert dismiss result incorrect");
            
            test.pass("Confirm alert handled successfully.");
    	 } catch (Exception e) {
             String path = FormUtilities.takeScreenshot(driver, "ConfirmAlertFail");
             test.fail("Test failed: " + e.getMessage());
             test.addScreenCaptureFromPath(path);
             throw e;
         }
        
    }

    @Test
    public void testPromptAlert() {
    	test = extent.createTest("Test Prompt Alert");
		try {
			AlertsFramesWindowsPage page = new AlertsFramesWindowsPage(driver);
			test.info("Handling prompt alert with input: TestUser");
	
	        String result = page.handlePromptAlert("TestUser");
	        test.info("Prompt result: " + result);
	        
	        Assert.assertTrue(result.contains("TestUser"), "Prompt alert input not reflected");
	        test.pass("Prompt alert handled successfully.");

		} catch (Exception e) {
			String path = FormUtilities.takeScreenshot(driver, "PromptAlertFail");
			test.fail("Test failed: " + e.getMessage());
			test.addScreenCaptureFromPath(path);
			throw e;
		}
		        
    }

    @Test
    public void testFrames() {
    	test = extent.createTest("Test Frames");
		try {
			AlertsFramesWindowsPage page = new AlertsFramesWindowsPage(driver);
			test.info("Switching to frame and reading text...");
			
	        String frameText = page.getTextFromFrame();
	        test.info("Text from frame: " + frameText);
	        
	        Assert.assertEquals(frameText, "This is a sample page", "Frame content mismatch");
	        test.pass("Frame content verified successfully.");
	        
		} catch (Exception e) {
			String path = FormUtilities.takeScreenshot(driver, "FrameTestFail");
			test.fail("Test failed: " + e.getMessage());
			test.addScreenCaptureFromPath(path);
			throw e;
		}
        
    }

    @Test
    public void testWindowsTabs() {
    	test = extent.createTest("Test Windows & Tabs");
    	FormUtilities.removeAds(driver);
    	try {
    		AlertsFramesWindowsPage page = new AlertsFramesWindowsPage(driver);
    		
    		test.info("Navigating to Browser Windows page...");
            page.NavigateToBrowserWindowsPage();

            String parent = driver.getWindowHandle();
            
            test.info("Opening new tab...");
            page.openNewTab();
            page.switchToWindowExcept(parent);
            Assert.assertTrue(driver.getTitle() != null, "New tab did not open properly");
            test.pass("New tab opened successfully.");
            
            driver.close();
            page.switchBackToWindow(parent);

            test.info("Opening new window...");
            page.openNewWindow();
            page.switchToWindowExcept(parent);
            Assert.assertTrue(driver.getTitle() != null, "New window did not open properly");
            test.pass("New window opened successfully.");
            
            driver.close();
            page.switchBackToWindow(parent);
    	} catch (Exception e) {
			String path = FormUtilities.takeScreenshot(driver, "WindowsTabsFail");
			test.fail("Test failed: " + e.getMessage());
			test.addScreenCaptureFromPath(path);
			throw e;
		}
    }
}
