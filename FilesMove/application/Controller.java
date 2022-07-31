package application;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

public class Controller implements Initializable {
	
	@FXML
	private Hyperlink templateLink;
	
	@FXML
	private Button selectFileBtn;
	
	@FXML
	private TextField diplayFileField;
	
	@FXML
	private Label successMessage;
	
	File dataFile; 
	
	FileChooser fileChooser = new FileChooser();
	FileChooser.ExtensionFilter extFilter2 = new FileChooser.ExtensionFilter("Excel files (*.xlsx)", "*.xlsx");
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}
	
	public void openTemplate() {
		File template = new File("src\\resources\\template.xlsx");
		System.out.println(template.exists());
		Desktop desktop = Desktop.getDesktop();
		try {
			desktop.open(template);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void selectExcelFile() {
		fileChooser.getExtensionFilters().addAll(extFilter2);
		dataFile  = fileChooser.showOpenDialog(selectFileBtn.getScene().getWindow());		
		diplayFileField.setText(dataFile.getAbsolutePath());
	}
	
	public void copyFiles() throws InterruptedException {
		String excelPath = dataFile.getAbsolutePath();
		try {
			CopyFiles.copyAllFiles(excelPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			successMessage.setText("Failed to copy the files");
			Thread.sleep(2000);
			successMessage.setText("");
		}
		successMessage.setText("Successfully copied all the files");
		Thread.sleep(10000);
		successMessage.setText("");
	}

}
