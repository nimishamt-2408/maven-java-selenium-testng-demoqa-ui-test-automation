package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.FormUtilities;

import java.io.File;
import java.time.Duration;

public class UploadDownloadPage {

    private WebDriver driver;
    private WebDriverWait wait;
    
    public UploadDownloadPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // LOCATORS
    private By elementsModule = By.xpath("//h5[text()='Elements']/ancestor::div[contains(@class,'top-card')]");
    private By fileUploadAndDownload = By.id("item-7");
    private By uploadInput = By.id("uploadFile");
    private By uploadedFilePath = By.id("uploadedFilePath");

    private By downloadButton = By.id("downloadButton");
    
	public void NavigateToUploadDownload() {
		FormUtilities.safeClick(driver, driver.findElement(elementsModule)); 
		FormUtilities.safeClick(driver, driver.findElement(fileUploadAndDownload)); 
	}

    // ===== FILE UPLOAD =====
    public void uploadFile(String filePath) {
        WebElement input = wait.until(ExpectedConditions.presenceOfElementLocated(uploadInput));
        input.sendKeys(filePath);
    }

    public String getUploadedFileName() {
        WebElement uploadedText = wait.until(ExpectedConditions.visibilityOfElementLocated(uploadedFilePath));
        return uploadedText.getText();
    }

    // ===== FILE DOWNLOAD =====
    public void clickDownload() {
        WebElement downloadBtn = wait.until(ExpectedConditions.elementToBeClickable(downloadButton));
        downloadBtn.click();
    }

    // Optional: Verify file exists in download folder
    public boolean isFileDownloaded(String downloadDir, String fileName) {
        File dir = new File(downloadDir);
        File[] files = dir.listFiles();
        if (files == null) return false;

        for (File file : files) {
            if (file.getName().equals(fileName)) {
                return true;
            }
        }
        return false;
    }
}
