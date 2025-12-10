package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.ElementsCheckBoxPage;
import utilities.FormUtilities;

public class CheckBoxPageTests extends BaseClass {
	
	@Test
	public void checkBoxTest() {
		test = extent.createTest("CheckBox Test");

        try {
        	
			ElementsCheckBoxPage cb = new ElementsCheckBoxPage(driver);
			
			test.info("Clicking the CheckBox element...");
			
			String result = cb.clickCheckBoxElement();
			test.info("Result text: " + result);
			
		    Assert.assertTrue(result.contains("documents"), "CheckBox selection result incorrect.");
		    test.pass("CheckBox test passed successfully.");
		    
        } catch (Exception e) {

            String path = FormUtilities.takeScreenshot(driver, "CheckBoxTestFail");

            test.fail("Test failed: " + e.getMessage());
            test.addScreenCaptureFromPath(path);
            throw e;
        }
	}
}
