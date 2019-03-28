package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ReadExcel {
	
	static ReadExcel rd;
	
	public static void main (String args[]) throws IOException 
	{
	ReadExcel.readExcel("TestData.xlsx");	
	}

	public static void readExcel(String excelName) throws IOException
	
	{
		String excelPath = Paths.get(".").toFile().getCanonicalPath()+"/src/test/resources/"+excelName;
		
		FileInputStream file;
		
		try {
			
			file = new FileInputStream(new File(excelPath));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			Sheet sheet = workbook.getSheetAt(0);
			
			int lastRow = sheet.getLastRowNum();
			
			ReadExcelPOJO rep = new ReadExcelPOJO();
			
			Map<String, ReadExcelPOJO> excelMap = new HashMap<String, ReadExcelPOJO>();   
			//Map<String, String> dataMap = new HashMap<String, String>();
			String cellValue = "";
			String cellKey ="";
			
			Iterator<Row> rowIterator = sheet.iterator();
			
			while(rowIterator.hasNext()) 
			{
				Row row = rowIterator.next();
				if(row.getRowNum()!=0)
				{
				 Iterator<Cell> cellIterator = row.iterator();
				 while (cellIterator.hasNext()) 
				{
					
					Cell cell =cellIterator.next();
					
					switch(cell.getCellType()) { 
					
					case Cell.CELL_TYPE_BOOLEAN:	
					System.out.print(cell.getBooleanCellValue() + "\t\t"); 
					break; 
					
					case Cell.CELL_TYPE_NUMERIC: 
					System.out.print(cell.getNumericCellValue() + "\t\t"); 
					break; 
					
					case Cell.CELL_TYPE_STRING: 
					System.out.print(cell.getStringCellValue() + "\t\t"); 
					break; 
					
					} 
					} 	
				}
				else { 
					System.out.println("first row hence ignored");
				}
					cellKey = rep.getTestScript();
					excelMap.put(cellKey,rep);
					System.out.println(""); 
					
					}
					
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}