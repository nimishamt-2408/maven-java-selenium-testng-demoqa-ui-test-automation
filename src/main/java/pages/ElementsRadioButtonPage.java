package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.FormUtilities;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementsRadioButtonPage {
	
	WebDriver driver;
	WebDriverWait wait;

	public ElementsRadioButtonPage(WebDriver driver) {
		this.driver = driver;
	    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// ===========================
	// Locators
	// ===========================
	
	private By elementsModule = By.xpath("//h5[text()='Elements']/ancestor::div[contains(@class,'top-card')]");
	private By radioButtonElement = By.id("item-2");
	private By radioBtnLabel = By.xpath("//label[text()='Impressive']");
	private By result = By.className("text-success");
	
	// ===========================
	// Actions
	// ===========================
	
	public String clickRadioButtonElement() {
		wait.until(ExpectedConditions.elementToBeClickable(elementsModule)).click();
		FormUtilities.safeClick(driver, driver.findElement(radioButtonElement));
		FormUtilities.safeClick(driver, driver.findElement(radioBtnLabel));
		return driver.findElement(result).getText();
	}
}
