package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excelutils {
	
	/* Ý tưởng
	 * tạo ra một mảng 2 chiều với kích thước mảng 1 là tổng số dòng, mảng 2 là tổng số cột
	 * để lưu data đọc được từ excel vào.
	 * Thứ tự lấy: workbook--> sheet--> row (last row chính là số dòng) --> cell (last cell là số cột)-->data
	 * Sau đó cho chạy vòng for để add giá trị vào mảng đã tạo bên trên
	 */

	public String[][] getDataFromExcel(String filePath, String sheetName) throws IOException {
		Workbook workbook;
		Sheet sheet;
		Row row;
		Cell cell;
		
		//get workbook
		File file= new File(filePath);
		FileInputStream inputStream= new FileInputStream(file);
		workbook= new XSSFWorkbook(inputStream);
		
		//get Sheet
		sheet=workbook.getSheet(sheetName);
		
		//get number of Row
		 int numberOfRow= sheet.getLastRowNum();
		 row=sheet.getRow(0);
		
		//get number of Cell
		 int numberOfColumn= row.getLastCellNum();
		
//For loops to add data in
		 String[][] data= new String[numberOfRow][numberOfColumn];
		 
		 //Vòng for đầu để add các giá trị của mỗi dòng
		 for (int i=0;i<numberOfRow;i++) {
			 //Vòng for 2 để lấy lưu giá trị của mỗi dòng
			 for (int j=0;j<numberOfColumn;j++) {
				 row=sheet.getRow(i+1);
				 data[i][j]=row.getCell(j).toString();
			 }	 
		 }
		 return data;
	}
}
