package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.PracticeFormPage;
import utilities.FormUtilities;

public class PracticeFormTests extends BaseClass {

    @Test(dataProvider = "formData")
    public void fillPracticeFormTest(String firstName, String lastName, String email,
                                     String gender, String mobile) {

    	test = extent.createTest("Practice Form Test - " + firstName + " " + lastName);
    	FormUtilities.removeAds(driver);
    	
    	try {
            test.info("Starting test with data:");
            test.info("First Name: " + firstName);
            test.info("Last Name: " + lastName);
            test.info("Email: " + email);
            test.info("Gender: " + gender);
            test.info("Mobile: " + mobile);
    	
	        PracticeFormPage formPage = new PracticeFormPage(driver);
	        
	        test.info("Filling the form");
	
	        // Fill the form
	        formPage.fillPracticeForm(firstName, lastName, email, gender, mobile);
	
	        
	        Assert.assertTrue(formPage.isConfirmationModalDisplayed(), "Confirmation popup not displayed");
	        test.pass("Form successfully submitted and confirmation popup displayed.");
        
    	} catch (Exception e) {
    		
			String screenshotPath = FormUtilities.takeScreenshot(driver, "PracticeForm_Fail");
		    test.fail("Test Failed: " + e.getMessage());
		    test.addScreenCaptureFromPath(screenshotPath);
		    throw e;
    	}
    }
    
    // ===========================
    // DataProvider for TestNG
    // ===========================
    @DataProvider(name = "formData")
    public Object[][] formDataProvider() {
        String filePath = System.getProperty("user.dir") + "/src/test/resources/PracticeFormData.xlsx";
        return FormUtilities.getExcelData(filePath, "FormData");
    }
}
