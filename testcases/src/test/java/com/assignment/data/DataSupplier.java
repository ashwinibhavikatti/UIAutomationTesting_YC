package com.assignment.data;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class DataSupplier {

	@DataProvider(name = "ValidRegData")
	public String[][] newRegistrationValidData() throws Exception {
		String[][] data = getDataFromExcelSheet("./YC_Reg_Data.xlsx", "ValidFormData");
		return data;
// to read data from local data provider method
//		return new String[][] { { "TEST", "One", "TestOne@gmail.com", "+31630008866" },
//				{ "Ash", "Test2", "TestTwo@gmail.com", "+31630008866" },
//				{ "Ashu", "Test3", "Test3@gmail.com", "0630008866" },
//				{ "Ash", "Test2", "TestTwo@gmail.com", "NL+31630008866" }, { "", "Four", "Test+1@gmail.com", "" },
//				{ "Art", "Five5", "Test.1@gmail.com", "" }, { "", "Six", "Test.1@gmail.com", "" } };

	}

	@DataProvider(name = "InvalidRegNameData")
	public String[][] invalidNameData() throws Exception {
		String[][] data = getDataFromExcelSheet("./YC_Reg_Data.xlsx", "InvalidNames");
		return data;
		// return new String[][] { { "$*&" }, { "123" }, { "% Ashu" } };
	}

	@DataProvider(name = "InvalidRegSurnameData")
	public String[][] invalidSurnameData() throws Exception {
		String[][] data = getDataFromExcelSheet("./YC_Reg_Data.xlsx", "InvalidSurnames");
		return data;
		// return new String[][] { { "" }, { "^&*" }, { "% Ashu" } };
	}

	@DataProvider(name = "InvalidRegEmailData")
	public String[][] invalidEmailData() throws Exception {
		String[][] data = getDataFromExcelSheet("./YC_Reg_Data.xlsx", "InvalidEmails");
		return data;
		// return new String[][] { { "ash@" }, { "ash.com" }, { "@#%^and." } };
	}

	@DataProvider(name = "InvalidRegPhoneData")
	public String[][] invalidPhoneData() throws Exception {
		String[][] data = getDataFromExcelSheet("./YC_Reg_Data.xlsx", "InvalidPhones");
		return data;
		// return new String[][] { { "+31-45677788" }, { "@#$^&*" }, { "-- " } };

	}

	public String[][] getDataFromExcelSheet(String sFileName, String sSheetName) throws Exception {
		File excelFile = new File(sFileName);
		FileInputStream fis = new FileInputStream(excelFile);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sfs = workbook.getSheet(sSheetName);
		int noOfRows = sfs.getLastRowNum();
		int noOfCols = sfs.getRow(0).getPhysicalNumberOfCells();
		String[][] data = new String[noOfRows - 1][noOfCols - 1];
		for (int i = 1; i < noOfRows; i++) {
			for (int j = 1; j < noOfCols; j++) {
				DataFormatter df = new DataFormatter();
				data[i - 1][j - 1] = df.formatCellValue(sfs.getRow(i).getCell(j));
			}
		}
		workbook.close();
		fis.close();
		return data;
	}
}
