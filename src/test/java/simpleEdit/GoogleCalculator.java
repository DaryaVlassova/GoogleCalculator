package simpleEdit;

import java.time.LocalDateTime;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import pages.CalculatorPage;
import utilities.ConfigurationReader;
import utilities.Driver;
import utilities.ExcelUtils;

public class GoogleCalculator {
	CalculatorPage page = new CalculatorPage();
	String excelFilePath = "./src/test/resources/testData/GoogleCalculator.xlsx";

	@Given("^I input the key word \"([^\"]*)\"$")
	public void i_input_the_key_word(String arg1) throws Throwable {
		Driver.getInstance().get(ConfigurationReader.getProperty("url"));
	}

	@Given("^I navigate to calculator$")
	public void i_navigate_to_calculator() throws Throwable {

		page.googleSearch.sendKeys(ConfigurationReader.getProperty("word") + Keys.ENTER);
	}

	@Given("^I test the calculator throgh excelsheet$")
	public void i_test_the_calculator_throgh_excelsheet() throws Throwable {
		ExcelUtils.openExcelFile(excelFilePath, "Sheet1");
		int rowsCount = ExcelUtils.getUsedRowsCount();
		System.out.println(rowsCount);
		for (int num = 1; num < rowsCount; num++) {
			String execute = ExcelUtils.getCellData(num, 0);
			if (execute.equalsIgnoreCase("N")) {
				ExcelUtils.setCellData("Skipped", num, 3);
				continue;
			} else {
				String operator = ExcelUtils.getCellData(num, 1);
				System.out.println(operator);
				String number1 = ExcelUtils.getCellData(num, 2).replace(".0", "");
				System.out.println(number1);
				String number2 = ExcelUtils.getCellData(num, 3).replace(".0", "");
				System.out.println(number2);
				if (operator.equals("addition")) {
					// WebElement input = page.calculatorInput;

					Thread.sleep(2000);
					page.calculatorInput.sendKeys(number1);

					page.plus.click();

					page.calculatorInput.sendKeys(number2);
					page.equals.click();
					String Actualresult = page.calculatorInput.getText();
					ExcelUtils.setCellData(Actualresult, num, 5);

				} else if (operator.equals("multiplication")) {
					WebElement input = page.calculatorInput;

					input.sendKeys(number1);
					Thread.sleep(2000);
					page.multiplication.click();
					Thread.sleep(2000);
					input.sendKeys(number2);
					page.equals.click();
					String Actualresult = input.getText();
					ExcelUtils.setCellData(Actualresult, num, 5);

				} else if (operator.equals("subtraction")) {
					WebElement input = page.calculatorInput;

					Thread.sleep(2000);
					input.sendKeys(number1);
					Thread.sleep(2000);
					page.minus.click();
					Thread.sleep(2000);
					input.sendKeys(number2);
					page.equals.click();
					String Actualresult = input.getText();
					ExcelUtils.setCellData(Actualresult, num, 5);

				} else if (operator.equals("division")) {
					WebElement input = page.calculatorInput;

					Thread.sleep(2000);
					input.sendKeys(number1);
					Thread.sleep(2000);
					page.division.click();
					Thread.sleep(2000);
					input.sendKeys(number2);
					page.equals.click();
					String Actualresult = input.getText();
					ExcelUtils.setCellData(Actualresult, num, 5);

				}

			}
			Driver.getInstance().findElement(By.cssSelector("div.cwbtpl.cwdgb-tpl.cwsbts")).click();
			String expResult = ExcelUtils.getCellData(num, 4).replace(".0", "");

			String actualResult = ExcelUtils.getCellData(num, 5);
			if (expResult.equals(actualResult)) {
				System.out.println("Pass");
				ExcelUtils.setCellData("Pass", num, 6);
			} else {
				System.out.println("Fail");
				ExcelUtils.setCellData("Fail", num, 6);
			}
			String time = LocalDateTime.now().toString();
			ExcelUtils.setCellData(time, num, 7);
		}

	}

	@Then("^I verify the result displayed on the calculaor matches expected result$")
	public void i_verify_the_result_displayed_on_the_calculaor_matches_expected_result() throws Throwable {

	}

}
