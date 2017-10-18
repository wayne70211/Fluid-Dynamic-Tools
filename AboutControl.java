package address.view;

import address.MainApp;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class AboutControl {
	private Stage dialogStage;

	public boolean okclicked = false;
	@FXML
	public ImageView image;

	public AboutControl() {

	}

	@FXML
	public void initialize() {
		image.setImage(new Image("file:resources/FluidDynamicsTools.png"));
		;
	}

	@FXML
	public void handleOK() {

		dialogStage.close();
	}

	@FXML
	public void setAboutControl(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public boolean isOkClicked() {
		return okclicked;
	}

}
