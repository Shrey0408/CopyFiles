package application;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/*
 * Copy list of files mentioned in excel sheet from mentioned destination to target path. 
 *
 */

public class CopyFiles {
	/*
	 * Full path of Excel sheet in following format
	 * (Destination name, Absolute Source file path , File name)
	 */
	/*
	 * Root destination directory name
	 */
	//private static final String fixedDestPath = "C:\\movedFiles"; 

	public static void copyAllFiles(String excelPath) throws IOException {

		List<ArrayList<String>> data = ReadExcel.getExcelData(excelPath);
	
		String destCompletePath = "";
		String destCompleteFolder = "";
		String sourceCompletePath = "";
		
		System.out.println(data.size());
		for(ArrayList<String> i : data) {
			destCompleteFolder = i.get(0);
			sourceCompletePath = i.get(1)+"\\"+i.get(2);
			destCompletePath = destCompleteFolder+"\\"+i.get(2);
			
			System.out.println(destCompleteFolder+"   "+destCompletePath+"   "+sourceCompletePath);
			//Create the destination folder if it doesn't exists
			
			File dest = new File(destCompleteFolder);
			dest.mkdir();
	
			copyFile(sourceCompletePath, destCompletePath); 
			
		}
		
		
	}
	public static void copyFile(String from, String to) throws IOException{ 
		File source = new File(from);
		File dest = new File(to);
		
		Files.copy(source.toPath(), dest.toPath());
        
		}

	
}