package tests;

import base.BaseClass;
import pages.ElementsTextBoxPage;
import utilities.FormUtilities;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TextBoxPageTests extends BaseClass {

	@Test
    public void fillTextBox() {
		test = extent.createTest("Text Box Page Test");

        try {
        	
        	test.info("Filling Text Box form with user details...");
        	
	        ElementsTextBoxPage tb = new ElementsTextBoxPage(driver);
	        tb.fillForm("John Sam", "john@test.com", "New York", "USA");
	        
	        String outputName = tb.getOutputName();
	        test.info("Output Name Received: " + outputName);
	        
	        Assert.assertTrue(outputName.contains("John Sam"), "Text Box output name incorrect.");
	        test.pass("TextBox test passed successfully!");
	        
        } catch (AssertionError ae) {
        	
            String screenshot = FormUtilities.takeScreenshot(driver, "Upload_Assertion_Fail");
            test.fail("Assertion Error: " + ae.getMessage());
            test.addScreenCaptureFromPath(screenshot);
            throw ae;
            
        } 
        catch (Exception e) {

            String screenshotPath = FormUtilities.takeScreenshot(driver, "TextBoxTestFail");

            test.fail("Test failed: " + e.getMessage());
            test.addScreenCaptureFromPath(screenshotPath);
            throw e;
        }
    }
}
