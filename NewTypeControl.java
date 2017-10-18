package address.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import address.model.kind;
public class NewTypeControl {
	@FXML
    private TextField typeField;
    @FXML
    private TextField velocityField;
    @FXML
    private TextField tempartureField;
    @FXML
    private TextField altitudeField;
    @FXML
    private TextField lengthField;
    @FXML
    private TextField densityField;
    @FXML
    private TextField viscosityField;
    @FXML
	private Label C;
	@FXML
	private Label D;
    
    private Stage dialogStage;
    private kind kind;
    private boolean okClicked=false;
    
    @FXML
    public void initialize() {
    	int ascii1=176;
		char c=(char) ascii1;
		int ascii2=179;
		char m3=(char) ascii2;
        C.setText(c+"C");
        D.setText("kg/m"+m3);
    }
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    public void setkind(kind kind){
    	this.kind=kind;
    	typeField.setText(""+kind.gettype());
    	velocityField.setText(""+kind.getvelocity());
    	tempartureField.setText(""+kind.gettemparture());
    	altitudeField.setText(""+kind.getaltitude());
    	lengthField.setText(""+kind.getlength());
    	densityField.setText(""+kind.getdensity());
    	viscosityField.setText(""+kind.getviscosity());
    }
    public boolean isOkClicked() {
        return okClicked;
    }
    @FXML
    private void handleOk() {
    	    kind.settype(typeField.getText());
    	    kind.setvelocity(Float.valueOf(velocityField.getText()));
    	    kind.settemparture(Float.valueOf(tempartureField.getText()));
    	    kind.setaltitude(Float.valueOf(altitudeField.getText()));
    	    kind.setlength(Float.valueOf(lengthField.getText()));
    	    kind.setdensity(Float.valueOf(densityField.getText()));
    	    kind.setviscosity(Float.valueOf(viscosityField.getText()));
    	    kind.setRe();
    	    kind.setSonic();
    	    kind.setMach();
            okClicked = true;            
            dialogStage.close();
        }
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

}
