package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ElementsRadioButtonPage {
	
	WebDriver driver;

	public ElementsRadioButtonPage(WebDriver driver) {
		this.driver = driver;
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
		FormUtilities.safeClick(driver.findElement(elementsModule));
		driver.findElement(radioButtonElement).click();
		driver.findElement(radioBtnLabel).click();

		return driver.findElement(result).getText();
	}
}
