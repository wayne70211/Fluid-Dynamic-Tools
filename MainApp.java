package address;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


import address.model.kind;
import address.model.kindOverwrite;
import address.view.FlowAnalysisControl;
import address.view.NewTypeControl;
import address.view.OverviewControl;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {

	private BorderPane rootLayout;
	private Stage primaryStage;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Fluid Dynamics Tools");
		this.primaryStage.getIcons().add(new Image("file:resources/FluidDynamicsTools.png"));

		initRootLayout();

		showOverview();

	}

	private ObservableList<kind> propertydata = FXCollections.observableArrayList();

	public MainApp() {
		propertydata.add(new kind());
	}

	public ObservableList<kind> getpropertyData() {
		return propertydata;
	}

	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		File file = getFilePath();
		if (file != null) {
			loadkindDataFromFile(file);
		}
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void showOverview() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/Overview.fxml"));
			AnchorPane Overview = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(Overview);

			// Give the controller access to the main app.
			OverviewControl controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean showEditDialog(kind kind) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/NewType.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Kind");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			NewTypeControl controller = loader.getController();
			controller.initialize();
			controller.setDialogStage(dialogStage);
			controller.setkind(kind);
			dialogStage.setResizable(false);
			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public File getFilePath() {
		Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
		String filePath = prefs.get("filePath", null);
		if (filePath != null) {
			return new File(filePath);
		} else {
			return null;
		}
	}

	public void setFilePath(File file) {
		Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
		if (file != null) {
			prefs.put("filePath", file.getPath());

			// Update the stage title.
			primaryStage.setTitle("ToolsApp - " + file.getName());
		} else {
			prefs.remove("filePath");

			// Update the stage title.
			primaryStage.setTitle("ToolsApp");
		}
	}

	public void showAnalysis(kind kind) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/FlowAnalysis.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Flow Analysis");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			// Set the person into the controller.
			FlowAnalysisControl controller = loader.getController();
			controller.setRe(kind);
			controller.setName(kind);
			controller.setFlowAnalysisControl(dialogStage);
			dialogStage.setResizable(false);
			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void loadkindDataFromFile(File file) {
		try {
			JAXBContext context = JAXBContext.newInstance(kindOverwrite.class);
			Unmarshaller um = context.createUnmarshaller();

			kindOverwrite wrapper = (kindOverwrite) um.unmarshal(file);

			propertydata.clear();
			propertydata.addAll(wrapper.getkind());

			// Save the file path to the registry.
			setFilePath(file);

		} catch (Exception e) { // catches ANY exception
			
		}
	}

	/**
	 * Saves the current person data to the specified file.
	 * 
	 * @param file
	 */
	public void savekindDataToFile(File file) {
		try {
			JAXBContext context = JAXBContext.newInstance(kindOverwrite.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			// Wrapping our person data.
			kindOverwrite wrapper = new kindOverwrite();
			wrapper.setkind(propertydata);

			m.marshal(wrapper, file);

			// Save the file path to the registry.
			setFilePath(file);
		} catch (Exception e) { // catches ANY exception
			
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
