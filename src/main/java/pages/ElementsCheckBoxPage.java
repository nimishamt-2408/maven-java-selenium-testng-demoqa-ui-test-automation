package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.FormUtilities;

public class ElementsCheckBoxPage {
	
	WebDriver driver;
	
	public ElementsCheckBoxPage(WebDriver driver) {	
		this.driver = driver;
	}
	
	// ===========================
    // Locators
    // ===========================
	
	private By elementsModule = By.xpath("//h5[text()='Elements']/ancestor::div[contains(@class,'top-card')]");
	private By checkBoxElement = By.id("item-1");
	private By homeCheckboxLabel = By.cssSelector("label[for='tree-node-home']");
	private By result = By.id("result");
	
	// ===========================
    // Actions
    // ===========================
	
	public String clickCheckBoxElement() {
		FormUtilities.safeClick(driver.findElement(elementsModule));
		driver.findElement(checkBoxElement).click();
		driver.findElement(homeCheckboxLabel).click();
		
	    return driver.findElement(result).getText();
    }

}
