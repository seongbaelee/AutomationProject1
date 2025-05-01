package com.automation.selenium_automation.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {
	WebDriverWait wait;
	WebDriver driver;

	public Utils(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public WebElement waitElementToAppearByLocator(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public void waitElementsToAppearByElement(List<WebElement> elements) {
		 wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}
	
	public Boolean waitElementToDisapearByLocator(By locator) {
		return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	public List<WebElement> waitElementAllToAppearByLocator(By locator) {
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	public WebElement waitElementToBeClickableByLocator(By locator) {
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	public WebElement waitElementToBeClickableByElement(WebElement element) {
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void waitElementToAppearAndDisappear(By locator) {
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
	
	public void scrollToShowElement(WebElement element) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);
		Thread.sleep(500);
	}
	
	public static List<HashMap<String,Object>> getJsonDataToMap(String filePath) throws IOException {
		//read json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath), "UTF-8");
		
		//string to HashMap (Jackson Databind dependency)
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, Object>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, Object>>>() {});
		return data;
	}
	
	public static Object[][] getDataByExcel(String filePath) throws IOException {
		FileInputStream fis = new FileInputStream(filePath);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet target_sheet = workbook.getSheet("userinfo");
		
		Iterator<Row> rowIt = target_sheet.rowIterator();
		List<List<Object>> allData = new ArrayList<>(); //array of row array
		
		//skip first row (header part)
		if(rowIt.hasNext()) {
			rowIt.next();
		}

		while (rowIt.hasNext()) {
			Row row = rowIt.next();
			List<Object> rowData = new ArrayList(); //array of cell array on a row

			for (Cell cell : row) {
				if (cell.getCellType() == CellType.STRING) {
					String cellValue = cell.getStringCellValue();
					if (cellValue.contains(",")) {
						String[] products = cellValue.split(",");
						List<String> productsList = Arrays.stream(products).map(product -> product.trim())
								.collect(Collectors.toList());
						rowData.add(productsList);
					} else {
						rowData.add(cell.getStringCellValue());
					}
				}

				if (cell.getCellType() == CellType.NUMERIC) {
					rowData.add(cell.getNumericCellValue());
				}
			}

			allData.add(rowData);
		}
		
		//change array to multi-dimensional object
		Object[][] arrayData = new Object[allData.size()][];
		for(int i = 0; i < allData.size(); i++) {
			arrayData[i] = allData.get(i).toArray();
		}
		
		return arrayData;
	}
	
	public static ExtentReports getReportObject() {
		String path = System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "SBL");
		
		return extent;
	}
	
	public static String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
	}
	
}
