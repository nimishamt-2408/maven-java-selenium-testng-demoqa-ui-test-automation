package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.FormUtilities;

import java.time.Duration;
import java.util.Set;

public class AlertsFramesWindowsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public AlertsFramesWindowsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    // ===========================
    // Locators
    // ===========================
    
    private By AlertsFramesWindowsModule = By.xpath("//h5[text()='Alerts, Frame & Windows']/ancestor::div[contains(@class,'top-card')]");
    private By alertPage = By.xpath("//span[text()='Alerts']/ancestor::li");
    private By framesPage = By.xpath("//span[text()='Frames']/ancestor::li");
    private By browserWindowsPage = By.xpath("//span[text()='Browser Windows']/ancestor::li");
    

    // ==== ALERTS Locators ====
    private By alertButton = By.id("alertButton");
    private By confirmButton = By.id("confirmButton");
    private By promptButton = By.id("promtButton");
    private By promptResult = By.id("promptResult");

    // ==== FRAMES / IFRAME Locators ====
    private By firstFrame = By.id("frame1");  
    private By frameHeading = By.id("sampleHeading");  

    // ==== WINDOWS / TABS Locators ====
    private By newTabButton = By.id("tabButton");
    private By newWindowButton = By.id("windowButton");
    
    private By messageWindowButton = By.id("messageWindowButton");

    // --- ALERT methods ---
    public String handleSimpleAlert() {
    	NavigateToAlertPage();
        FormUtilities.safeClick(driver, driver.findElement(alertButton));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();
        alert.accept();
        return text;
    }

    public String handleConfirmAlert(boolean accept) {
    	NavigateToAlertPage();
		FormUtilities.safeClick(driver, driver.findElement(confirmButton));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();
        if (accept) alert.accept();
        else alert.dismiss();
        return text;
    }

    public String handlePromptAlert(String inputText) {
    	NavigateToAlertPage();
		FormUtilities.safeClick(driver, driver.findElement(promptButton));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.sendKeys(inputText);
        alert.accept();
        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(promptResult));
        return result.getText();
    }

    // --- FRAME / IFRAME methods ---
    public String getTextFromFrame() {
    	NavigateToFramesPage();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(firstFrame));
        String text = driver.findElement(frameHeading).getText();
        driver.switchTo().defaultContent();
        return text;
    }

    // --- WINDOWS / TABS methods ---
    public void openNewTab() {
		FormUtilities.safeClick(driver, driver.findElement(newTabButton));
    }

    public void openNewWindow() {
		FormUtilities.safeClick(driver, driver.findElement(newWindowButton));
    }

    public void openNewMessageWindow() {
		FormUtilities.safeClick(driver, driver.findElement(messageWindowButton));
    }

    public void switchToWindowExcept(String parentWindow) {
        Set<String> handles = driver.getWindowHandles();
        for (String h : handles) {
            if (!h.equals(parentWindow)) {
                driver.switchTo().window(h);
                break;
            }
        }
    }

    public void switchBackToWindow(String windowHandle) {
        driver.switchTo().window(windowHandle);
    }
    
	public void NavigateToAlertsFramesWindowsModule() {
		FormUtilities.safeClick(driver, driver.findElement(AlertsFramesWindowsModule));
	}
	
	public void NavigateToAlertPage() {
		NavigateToAlertsFramesWindowsModule();
		FormUtilities.safeClick(driver, driver.findElement(alertPage));
	}
	
	public void NavigateToFramesPage() {
		NavigateToAlertsFramesWindowsModule();
		FormUtilities.safeClick(driver, driver.findElement(framesPage));
	}
	
	public void NavigateToBrowserWindowsPage() {
		NavigateToAlertsFramesWindowsModule();
		FormUtilities.safeClick(driver, driver.findElement(browserWindowsPage));
	}
}
