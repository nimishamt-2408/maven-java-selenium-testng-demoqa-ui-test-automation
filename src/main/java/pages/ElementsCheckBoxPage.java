package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementsCheckBoxPage {
	
	WebDriver driver;
	WebDriverWait wait;
	
	public ElementsCheckBoxPage(WebDriver driver) {	
		this.driver = driver;
	    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
		wait.until(ExpectedConditions.elementToBeClickable(elementsModule)).click();
		driver.findElement(checkBoxElement).click();
		driver.findElement(homeCheckboxLabel).click();
		
	    return driver.findElement(result).getText();
    }

}
