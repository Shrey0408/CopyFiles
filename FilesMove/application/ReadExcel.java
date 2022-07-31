package application;

import java.io.File;  
import java.io.FileInputStream;  
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFSheet;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;  
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;  
import org.apache.poi.ss.usermodel.Row;  
/*
 * Excel format
 * Dest.Folder name, source path, file name
 * 
 */
public class ReadExcel  
{  
	public static List<ArrayList<String>> getExcelData(String path) throws IOException  
	{  

		FileInputStream fis=new FileInputStream(new File(path));  
		String cellData = null;
		List<ArrayList<String>> data = new ArrayList<>();

		try (XSSFWorkbook wb = new XSSFWorkbook(fis)) {
			XSSFSheet sheet=wb.getSheetAt(0);
			boolean isFirstRow =true;
			FormulaEvaluator formulaEvaluator=wb.getCreationHelper().createFormulaEvaluator();  
			for(Row row: sheet)     
			{  
				if(isFirstRow) {
					isFirstRow =false;
					continue;
				}
				ArrayList<String> items = new ArrayList<>();
				for(Cell cell: row)    
				{  
				
					switch(formulaEvaluator.evaluateInCell(cell).getCellType())  
					{  
					case NUMERIC:   
						cellData = ""+cell.getNumericCellValue();
						System.out.print(cellData+ "\t\t");
						items.add(cellData);
						break;  
					case STRING:
						cellData = ""+cell.getStringCellValue();
						System.out.print(cellData+ "\t\t");
						items.add(cellData);
						System.out.print(cellData+ "\t\t");  
						break;
					default:
						break;  
					}  
					
				}  
				data.add(items);
				System.out.println();  
			}
		}
		return data;  
	}  
}  
