package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.ElementsRadioButtonPage;
import utilities.FormUtilities;

public class RadioButtonPageTests extends BaseClass {
	
	@Test
	public void radioButtonTest() {
		FormUtilities.removeAds(driver);
		test = extent.createTest("Radio Button Test");

        try {
        	
			ElementsRadioButtonPage cb = new ElementsRadioButtonPage(driver);
			test.info("Clicking the Radio Button element...");
			
			String result = cb.clickRadioButtonElement();
			test.info("Result text: " + result);
			
		    Assert.assertTrue(result.contains("Impressive"), "Radio Button selection result incorrect.");
		    test.pass("Radio Button test passed successfully.");
		    
        } catch (Exception e) {

            String path = FormUtilities.takeScreenshot(driver, "RadioButtonTestFail");

            test.fail("Test failed: " + e.getMessage());
            test.addScreenCaptureFromPath(path);
            throw e;
        }
	}

}
