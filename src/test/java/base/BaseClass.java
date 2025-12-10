package base;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class BaseClass {
	
    protected WebDriver driver;
    public static ExtentReports extent;
    public static ExtentTest test;
	
	/**
	 * setUp
	 *
	 * Executed before each TestNG test method.
	 * - Creates a new ChromeDriver instance.
	 * - Maximizes the browser window.
	 * - Applies a 5 second implicit wait for element lookups.
	 * - Navigates to the DemoQA homepage.
	 *
	 */

	@BeforeMethod
	public void setUp() {
		
		// Initialize ExtentReports
        if (extent == null) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String reportPath = System.getProperty("user.dir") + "/reports/ExtentReport_" + timestamp + ".html";
            ExtentSparkReporter spark = new ExtentSparkReporter(new File(reportPath));
            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
        
		driver = new ChromeDriver();
		driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://demoqa.com/");
	}
	
	/**
	 * tearDown
	 *
	 * Executed after each TestNG test method (alwaysRun = true).
	 * - If the WebDriver exists, attempts to quit the browser to free resources.
	 * - Uses a try/finally to ensure the driver reference is cleared even if quit() fails.
	 * - Safe to call when driver is null.
	 */
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		if (driver != null) {
            try {
                driver.quit();
            } finally {
                driver = null;
            }
        }
		
		if (extent != null) {
			extent.flush(); // Writes everything to HTML
	    }
	}
}
