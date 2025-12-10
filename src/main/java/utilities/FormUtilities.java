package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FormUtilities {

    // Read Excel data
    public static Object[][] getExcelData(String filePath, String sheetName) {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new RuntimeException("Excel file not found at: " + filePath);
        }

        try (FileInputStream fis = new FileInputStream(file)) {
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheet(sheetName);

            int rowCount = sheet.getPhysicalNumberOfRows();
            int colCount = sheet.getRow(0).getLastCellNum();
            Object[][] data = new Object[rowCount - 1][colCount];

            for (int i = 1; i < rowCount; i++) {
                XSSFRow row = sheet.getRow(i);
                for (int j = 0; j < colCount; j++) {
                    XSSFCell cell = row.getCell(j);
                    if (cell == null) {
                        data[i - 1][j] = "";
                    } else {
                        switch (cell.getCellType()) {
                            case STRING:
                                data[i - 1][j] = cell.getStringCellValue();
                                break;
                            case NUMERIC:
                                data[i - 1][j] = String.valueOf((long) cell.getNumericCellValue());
                                break;
                            case BOOLEAN:
                                data[i - 1][j] = cell.getBooleanCellValue();
                                break;
                            case FORMULA:
                                data[i - 1][j] = cell.getCellFormula();
                                break;
                            default:
                                data[i - 1][j] = cell.toString();
                        }
                    }
                }
            }
            workbook.close();
            return data;

        } catch (Exception e) {
            throw new RuntimeException("Error reading Excel file: " + e.getMessage(), e);
        }
    }

    // Safe click using JS
    public static void safeClick(WebDriver driver, WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }
    
    /**
     * Removes Google Ads iframes from the current page
     * @param driver WebDriver instance
     */
    public static void removeAds(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = "var ads = document.querySelectorAll('iframe[id^=\"google_ads_iframe\"]');" +
                        "for(var i=0; i<ads.length; i++) { ads[i].remove(); }";
        js.executeScript(script);
    }

    // Take screenshot
    public static String takeScreenshot(WebDriver driver, String fileName) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String path = System.getProperty("user.dir") + "/screenshots/" + fileName + ".png";
            FileUtils.copyFile(src, new File(path));
            return path;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
