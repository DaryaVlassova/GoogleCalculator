package simpleEdit;

import java.util.concurrent.TimeUnit;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import utilities.ExcelUtils;

public class CalculatorGoogle {
	static WebDriver driver;
	WebElement search;
	WebElement results;
	String excelFilePath = "./src/test/resources/testData/GoogleCalculator.xlsx";

	@BeforeClass
	public static void setUp() {
		System.setProperty("webdriver.chrome.driver", "/Users/daryavlassova/Documents/Libraries/drivers/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://www.google.com");
	}

	@Test
	public void searchTest() throws Exception {
		driver.findElement(By.id("lst-ib")).sendKeys("Calculator" + Keys.ENTER);
		ExcelUtils.openExcelFile(excelFilePath, "Sheet1");
		int rowsCount = ExcelUtils.getUsedRowsCount();
		for (int num = 1; num < rowsCount; num++) {

			String execute = ExcelUtils.getCellData(num, 0);
			if (execute.equalsIgnoreCase("N")) {
				ExcelUtils.setCellData("Skipped", num, 3);
				continue;
			}
			String Operator = ExcelUtils.getCellData(num, 1);

			String number1 = ExcelUtils.getCellData(num, 2).replace(".0", "");

			String number2 = ExcelUtils.getCellData(num, 3).replace(".0", "");

			if (Operator.equals("addition")) {
				WebElement input = driver.findElement(By.cssSelector("div#cwtltblr"));
				Thread.sleep(2000);
				input.sendKeys(number1);
				Thread.sleep(2000);
				driver.findElement(By.xpath("//div[@class='cwbtpl cwdgb-tpl cwbbts']//span[.='+']")).click();
				Thread.sleep(2000);
				input.sendKeys(number2);
				driver.findElement(By.cssSelector("div.cwbtpl.cwbb-tpl.cwbbts")).click();
				String Actualresult = input.getText();
				ExcelUtils.setCellData(Actualresult, num, 5);
			}
			driver.findElement(By.cssSelector("div.cwbtpl.cwdgb-tpl.cwsbts")).click();
		}

	}
}
