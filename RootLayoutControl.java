package address.view;

import java.io.File;
import java.io.IOException;

import address.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RootLayoutControl {
	// Reference to the main application
    private MainApp mainApp;
    
	private Stage primaryStage;
	

    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

	@FXML
	public void handleclose(){
		System.exit(0);
	}
	 @FXML
	    private void handleNew() {
	        mainApp.getpropertyData().clear();
	        mainApp.setFilePath(null);
	    }

	    /**
	     * Opens a FileChooser to let the user select an address book to load.
	     */
	    @FXML
	    private void handleOpen() {
	    	
	        FileChooser fileChooser = new FileChooser();

	        // Set extension filter
	        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
	                "XML files (*.xml)", "*.xml");
	        fileChooser.getExtensionFilters().add(extFilter);

	        // Show save file dialog
	        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

	        if (file != null) {
	            mainApp.loadkindDataFromFile(file);
	        }
	    }

	    
	    @FXML
	    private void handleSave() {
	        File File = mainApp.getFilePath();
	        if (File != null) {
	            mainApp.savekindDataToFile(File);
	        } else {
	            handleSaveAs();
	        }
	    }

	    /**
	     * Opens a FileChooser to let the user select a file to save to.
	     */
	    @FXML
	    private void handleSaveAs() {
	        FileChooser fileChooser = new FileChooser();

	        // Set extension filter
	        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
	                "XML files (*.xml)", "*.xml");
	        fileChooser.getExtensionFilters().add(extFilter);

	        // Show save file dialog
	        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

	        if (file != null) {
	            // Make sure it has the correct extension
	            if (!file.getPath().endsWith(".xml")) {
	                file = new File(file.getPath() + ".xml");
	            }
	            mainApp.savekindDataToFile(file);
	        }
	    }
	@FXML
    private void handleAbout(){
		try {
        	FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/About.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
            dialogStage.setTitle("About");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);           
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
         // Set the person into the controller.
            AboutControl controller = loader.getController();
            controller.initialize();
            controller.setAboutControl(dialogStage);
            dialogStage.setResizable(false);
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
           
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
	
	

}
