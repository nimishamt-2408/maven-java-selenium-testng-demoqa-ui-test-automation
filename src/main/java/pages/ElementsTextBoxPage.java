package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.FormUtilities;

public class ElementsTextBoxPage {
	
	WebDriver driver;
	
	public ElementsTextBoxPage(WebDriver driver) {
	    this.driver = driver;
	    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	// ===========================
    // Locators
    // ===========================
	
	private By userName = By.id("userName");
	private By userEmail = By.id("userEmail");
	private By currentAddress = By.id("currentAddress");
	private By permanentAddress = By.id("permanentAddress");
	private By submitBtn = By.id("submit");
	
	private By elementsModule = By.xpath("//h5[text()='Elements']/ancestor::div[contains(@class,'top-card')]");
	private By textBoxElement = By.id("item-0");
	
	private By outputName = By.id("name");
	
	// ===========================
    // Actions
    // ===========================
	public void navigateToTextBoxPage() {
		wait.until(ExpectedConditions.elementToBeClickable(elementsModule)).click();
		FormUtilities.safeClick(driver, driver.findElement((textBoxElement));
    }
	
	public void enterUserName(String name) { 
		 driver.findElement(userName).sendKeys(name);
    }
	
	public void enterUserEmail(String email) { 
		driver.findElement(userEmail).sendKeys(email);
	}
	
	public void enterCurrentAddress(String curr) { 
		driver.findElement(currentAddress).sendKeys(curr);
	}
	
	public void enterPermanentAddress(String perm) {
		driver.findElement(permanentAddress).sendKeys(perm);
	}
	public void clickSubmit() { 
		FormUtilities.safeClick(driver, driver.findElement(submitBtn)); 
    }
	
	// ===========================
    // form fill
    // ===========================
	
	public void fillForm(String name, String email, String curr, String perm) {
		navigateToTextBoxPage();
		enterUserName(name);
	    enterUserEmail(email);
	    enterCurrentAddress(curr);
	    enterPermanentAddress(perm);
	    clickSubmit();
	}
	
	public String getOutputName() {
	    return driver.findElement(outputName).getText();
	}
	
}
