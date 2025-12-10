package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.UploadDownloadPage;
import utilities.FormUtilities;
import base.BaseClass;

public class UploadDownloadTests extends BaseClass {

    @Test
    public void testFileUpload() {
    	test = extent.createTest("Upload Test - File Upload");

        try {
	        UploadDownloadPage page = new UploadDownloadPage(driver);
	        test.info("Navigating to Upload & Download page...");
	
	        page.NavigateToUploadDownload();
	        // Absolute path of file to upload
	        String filePath = System.getProperty("user.dir") + "/src/test/resources/sampleFile.txt";
	        test.info("Uploading file: " + filePath);
	        
	        page.uploadFile(filePath);
	        String uploadedFile = page.getUploadedFileName();
	        
	        test.info("Uploaded File Displayed: " + uploadedFile);
	
	        Assert.assertTrue(uploadedFile.contains("sampleFile.txt"), "Uploaded file not displayed correctly");
	        test.pass("File Upload Test Passed Successfully!");
	       
        } catch (Exception e) {
        	String screenshotPath = FormUtilities.takeScreenshot(driver, "UploadTestFail");

            test.fail("Test failed: " + e.getMessage());
            test.addScreenCaptureFromPath(screenshotPath);
            throw e;
        }
    }

    @Test
    public void testFileDownload() throws InterruptedException {
    	test = extent.createTest("Download Test - File Download");
    	FormUtilities.removeAds(driver);

        try {
        	
	        UploadDownloadPage page = new UploadDownloadPage(driver);
	        test.info("Navigating to Upload & Download page...");
	        
	        page.NavigateToUploadDownload();
	        
	        test.info("Clicking on Download button...");
	        page.clickDownload();
	        
	        Thread.sleep(3000);
	
	        // verify file in Downloads folder
	        String downloadDir = System.getProperty("user.home") + "/Downloads";
	        test.info("Checking if downloaded file exists in: " + downloadDir);
	        
	        String downloadedFileName = "sampleFile.jpeg"; // default downloaded file name
	        boolean isDownloaded = page.isFileDownloaded(downloadDir, downloadedFileName);
	        
	        Assert.assertTrue(isDownloaded, "File was not downloaded successfully");
	        test.pass("File Download Test Passed Successfully!");
        
        } catch (Exception e) {
        	String path = FormUtilities.takeScreenshot(driver, "DownloadTestFail");

            test.fail("Test failed: " + e.getMessage());
            test.addScreenCaptureFromPath(path);
            throw e;
        }
    }
}
