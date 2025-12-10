package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.FormUtilities;

public class PracticeFormPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public PracticeFormPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
    }

    // ===========================
    // Locators
    // ===========================
    
    private By formsModule = By.xpath("//h5[text()='Forms']/ancestor::div[contains(@class,'top-card')]");
    private By practiceFormMenu = By.xpath("//span[text()='Practice Form']/ancestor::li");
    private By firstNameField = By.id("firstName");
    private By lastNameField = By.id("lastName");
    private By emailField = By.id("userEmail");
    private By genderOption(String gender) { return By.xpath("//label[text()='" + gender + "']"); }
    private By mobileField = By.id("userNumber");
    private By submitButton = By.id("submit");
    private By confirmationModal = By.id("example-modal-sizes-title-lg");
    private By closeModalButton = By.id("closeLargeModal");

    // ===========================
    // Actions
    // ===========================
    public void navigateToPracticeForm() {
        wait.until(ExpectedConditions.elementToBeClickable(formsModule)).click();
        wait.until(ExpectedConditions.elementToBeClickable(practiceFormMenu)).click();
    }

    public void enterFirstName(String firstName) { 
    	driver.findElement(firstNameField).sendKeys(firstName); 
    }
    public void enterLastName(String lastName) { 
    	driver.findElement(lastNameField).sendKeys(lastName); 
    }
    public void enterEmail(String email) { 
    	driver.findElement(emailField).sendKeys(email); 
    }
    public void selectGender(String gender) { 
    	FormUtilities.safeClick(driver, driver.findElement(genderOption(gender))); 
    }
    public void enterMobile(String mobile) { 
    	driver.findElement(mobileField).sendKeys(mobile); 
    }
    public void clickSubmit() { 
    	FormUtilities.safeClick(driver, driver.findElement(submitButton)); 
    }
    public boolean isConfirmationModalDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationModal)).isDisplayed();
    }
    public void closeConfirmationModal() { 
    	FormUtilities.safeClick(driver, driver.findElement(closeModalButton)); 
    }

    // ===========================
    // form fill
    // ===========================
    public void fillPracticeForm(String firstName, String lastName, String email,
                                 String gender, String mobile) {
        navigateToPracticeForm();
        enterFirstName(firstName);
        enterLastName(lastName);
        enterEmail(email);
        selectGender(gender);
        enterMobile(mobile);
        clickSubmit();
        FormUtilities.takeScreenshot(driver, "PracticeForm_Submission_" +firstName + "_" + lastName);
        closeConfirmationModal();
    }

}
