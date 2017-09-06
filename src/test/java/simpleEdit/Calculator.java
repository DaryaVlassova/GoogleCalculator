package simpleEdit;

import java.io.IOException;
import java.time.LocalDateTime;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import utilities.ExcelUtils;

public class Calculator {
	public static void main(String[] args) throws EncryptedDocumentException, InvalidFormatException, IOException {

		String ExcelFilePath = "./src/test/resources/testData/calculator.xlsx";
		ExcelUtils.openExcelFile(ExcelFilePath, "Sheet1");
		int rownum = ExcelUtils.getUsedRowsCount();
		System.out.println(rownum);

		for (int num = 1; num < rownum; num++) {
			String searchOperator = ExcelUtils.getCellData(num, 1);
			String execute = ExcelUtils.getCellData(num, 0);
			if (execute.equalsIgnoreCase("N")) {
				ExcelUtils.setCellData("Skipped", num, 6);
				continue;
			}
			double colNum1 = Double.parseDouble(ExcelUtils.getCellData(num, 2));
			System.out.println(colNum1);
			double colNum2 = Double.parseDouble(ExcelUtils.getCellData(num, 3));
			double result = Calculator.operate(searchOperator, colNum1, colNum2);
			System.out.println(result);
			double expResult = Double.parseDouble(ExcelUtils.getCellData(num, 4));
			ExcelUtils.setCellData(String.valueOf(result), num, 5);
			double actualResult = Double.parseDouble(ExcelUtils.getCellData(num, 5));
			System.out.println("_______");
			System.out.println(actualResult);
			if (expResult == actualResult) {
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

	public static double operate(String operation, double num1, double num2) {
		double actualResult = 0;
		if (operation.equals("addition")) {
			actualResult = num1 + num2;
		} else if (operation.equals("subtraction")) {
			actualResult = num1 - num2;
		} else if (operation.contains("multiplication")) {
			actualResult = num1 * num2;

		} else if (operation.contains("division")) {
			actualResult = num1 / num2;
		}
		return actualResult;
	}
}