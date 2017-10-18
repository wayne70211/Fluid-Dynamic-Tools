package address.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import address.MainApp;
import address.model.kind;

public class OverviewControl {
	@FXML
    public TableView<kind> kindTable;
    @FXML
    public TableColumn<kind, String> kindColumn;
    @FXML
	private Label velocity;
	@FXML
	private Label temparture;
	@FXML
	private Label altitude;
	@FXML
	private Label density;
	@FXML
	private Label viscosity;
	@FXML
	private Label length;
	@FXML
	public Label Re;
	@FXML
	private Label Sonic;
	@FXML
	private Label Mach;
	@FXML
	private Label C;
	@FXML
	private Label D;
	@FXML
	private Label FlowTypeLabel;
	@FXML
	private Label FlowType;
	@FXML
	private Label SonicL;
	@FXML
	private Label MachL;
	@FXML
	private Label SonicM;
	
	
	
	
   
	// Reference to the main application.
	private MainApp mainApp;
	
	public OverviewControl(){		
	}
	// initial the control class
	@FXML
	public void initialize(){
		// Initialize the person table with the two columns.
        kindColumn.setCellValueFactory(
                cellData -> cellData.getValue().typeproperty());
        int ascii1=176;
		char c=(char) ascii1;
		int ascii2=179;
		char m3=(char) ascii2;
        C.setText(c+"C");
        D.setText("kg/m"+m3);
        

        // Clear person details.
        showproperty(null);

        // Listen for selection changes and show the person details when changed.
        kindTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showproperty(newValue));
	}
	public void setMainApp(MainApp mainApp){
		this.mainApp=mainApp;
		kindTable.setItems(mainApp.getpropertyData());
	}
    public void showproperty(kind kind){
    	if (kind != null) {
            // Fill the labels with info from the person object.
    		velocity.setText(""+kind.getvelocity());
    		temparture.setText(""+kind.gettemparture());
    		density.setText(""+kind.getdensity());
    		altitude.setText(""+kind.getaltitude());
    		viscosity.setText(""+kind.getviscosity());
    		length.setText(""+kind.getlength());
    		Re.setText(""+kind.getRe());
    		if(kind.gettype().equals("Dry Air")){
    			Sonic.setText(""+kind.getSonic());
        		Mach.setText(""+kind.getMach());
        		SonicL.setVisible(true);
        		MachL.setVisible(true);
        		SonicM.setVisible(true);

    		}else{
    			Sonic.setText("");
        		Mach.setText("");
        		SonicL.setVisible(false);
        		MachL.setVisible(false);
        		SonicM.setVisible(false);

    		}
    		
    		
           } else {
            // kind is null, remove all the text.
            velocity.setText("");
       		temparture.setText("");
       		density.setText("");
       		altitude.setText("");
       		viscosity.setText("");
       		length.setText("");
       		Re.setText("");
       		Sonic.setText("");
       		Mach.setText("");
       		
        }
    }
    @FXML
    private void handleNewkind() {
        kind temp = new kind();
        boolean okClicked = mainApp.showEditDialog(temp);
        if (okClicked) {
            mainApp.getpropertyData().add(temp);
        }
    }
    @FXML
    private void handleDeletekind() {
    	int selectedIndex = kindTable.getSelectionModel().getSelectedIndex();
        kindTable.getItems().remove(selectedIndex);
    }
    @FXML
    private void handleEditkind() {
        kind selected = kindTable.getSelectionModel().getSelectedItem();
        
        if (selected !=null) {
            boolean okClicked = mainApp.showEditDialog(selected);
            if(okClicked){
            	showproperty(selected);
            }
        }
    }
    
    public void handleAnalysis() {
        kind selected = kindTable.getSelectionModel().getSelectedItem();
        if(selected!=null){        	
        mainApp.showAnalysis(selected);
        }
    }
    
    
    
	
   

}
