package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.internal.collections.Pair;



public class ReadExcel {
	
	static ReadExcel rd = new ReadExcel();
	
	static Map<String,Map<String,?>> testDataMap = new HashMap<String, Map<String,?>>();
	static Map<String,Map<String,?>> tcSelectMap = new HashMap<String, Map<String,?>>();
	
	public static void main (String args[]) throws IOException 
	
	{	
		rd.Process();
	}

	public void Process() throws IOException 
	{
		
		testDataMap.putAll(readExcel("TestData.xlsx"));
		System.out.println("This is TestDataMap:\n" + testDataMap.get("BusinessModule4")+ "\n");
		
		//define filter criteria here.
		Pair <Object,Object> filterCriteria = new Pair<Object,Object>("Execute Flag",true);
		System.out.println("First :\t"+filterCriteria.first()+"\nSecond:\t"+filterCriteria.second());
		tcSelectMap = returnFilteredMap(readExcel("ScenarioSelectionSheet.xlsx"),filterCriteria);
		//tcSelectMap.putAll(readExcel("ScenarioSelectionSheet.xlsx"));
		System.out.println("Number of testcases to be executed :\t" + tcSelectMap.size());
	}
	
	public static Map<String, Map<String,?>> readExcel(String excelName) throws IOException
	
	{
		String excelPath = Paths.get(".").toFile().getCanonicalPath()+"/src/test/resources/"+excelName;
		
		FileInputStream file;
		
		try {
			
			file = new FileInputStream(new File(excelPath));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			Sheet sheet = workbook.getSheetAt(0);
			String colHeaders [] = null;
			
			int lastRow = sheet.getLastRowNum();
			
			
			Map<String , Map<String,?>> map = new LinkedHashMap<String, Map<String, ?>>(); 
			
			
			String cellKey ="";
			int lastCol =0 ;
			
			Iterator<Row> rowIterator = sheet.iterator();
			
		while(rowIterator.hasNext()) 
		{
				Row row = rowIterator.next();
				
				
			if (row.getRowNum()==0) 
			{
				lastCol = row.getLastCellNum();
				colHeaders = new String[lastCol];
				System.out.println("All headers of this sheet are :");
				Iterator<Cell> cellIterator = row.iterator();
				for (int i=0; i<lastCol;i++)
				{
					Cell cell = cellIterator.next();
					colHeaders[i] = cell.getStringCellValue();
					System.out.println(colHeaders[i]);	
				}	
				
				
				
			}	
			else if(row.getRowNum()!=0)
			{	
				Map<String , Object> innerMap = new HashMap<String, Object>();
	
				Iterator<Cell> cellIterator = row.iterator();
				int c = 0;
					
				 while (cellIterator.hasNext()) 
				{		 
					Cell cell =cellIterator.next();
					
					switch (cell.getCellType())
				  {
					case Cell.CELL_TYPE_STRING:
					innerMap.put(colHeaders[c],cell.getStringCellValue());
					c++;
					break;
					
					case Cell.CELL_TYPE_NUMERIC:
					innerMap.put(colHeaders[c],cell.getNumericCellValue());
					c++;
					break;
					
					case Cell.CELL_TYPE_BOOLEAN:
					innerMap.put(colHeaders[c],cell.getBooleanCellValue());
					c++;
					break;
					
					case Cell.CELL_TYPE_BLANK:
					innerMap.put(colHeaders[c],null);
					c++;
					break;
					
				   }
					
				}
				 
			// first cellValue of each row will become key of Map & remaining all fields as Map Value.
				 
				 map.put(row.getCell(0).getStringCellValue(), innerMap);
				 
			}
			else 
			{ 
				System.out.println("Else loop executed. Need to check with Row Iterator.");
			}
					// Map While loop Completed 
					
		}
				
				System.out.println("This is Map :\t" + map);
				return map;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static Map<String,Map<String,?>> returnFilteredMap(Map<String,Map<String,?>> dumpMap,Pair<?,?> whereClause)
	{
		
		HashMap<String, Map<String, ?>> filteredMap = new HashMap<String, Map<String,?>>();
		
		for (Entry<String, Map<String,?>> entry : dumpMap.entrySet()) 
		{
			Map <String, Object> tempMap = new HashMap<String, Object>();
			tempMap.putAll(entry.getValue());
			
			//apply filter Criteria Here
		
			if (tempMap.get(whereClause.first()).equals(whereClause.second()))
			{
			filteredMap.put(entry.getKey(), entry.getValue());
			}	
		}
		System.out.println("This is my Filtered Map with size:\t" +filteredMap.size() +"\n"+filteredMap);
		
		return filteredMap;
	}
}