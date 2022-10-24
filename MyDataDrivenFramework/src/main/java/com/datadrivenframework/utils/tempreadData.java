package com.datadrivenframework.utils;

public class tempreadData {
	public static void main(String args[]) {
		ReadExcelDataFile readData = new ReadExcelDataFile(
				System.getProperty("user.dir") + "\\src\\main\\java\\TestData\\TestData.xlsx");

		int totalRows = readData.getRowCount("SampleData");
		System.out.println("Total number of rows:" + totalRows);
		System.out.println(readData.getCellData("SampleData", 0, 3));
		System.out.println(readData.getCellData("SampleData", 1, 4));

		System.out.println(readData.getColumnCount("SampleData"));
	}
}
