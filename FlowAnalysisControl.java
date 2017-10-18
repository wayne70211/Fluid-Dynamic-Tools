package address.view;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

import address.model.kind;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.paint.LinearGradient;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FlowAnalysisControl {
	
	@FXML
	public Stage stage;
	@FXML
	public Label Name;
	@FXML
	public Label Re;
	@FXML
	public Label Viscid;
	@FXML
	public Label TranspointFrom;
	@FXML
	public Label TranspointTo;
	
	@FXML
	public ChoiceBox<String> FlowType;
	
	@FXML
	public Rectangle Initial;
	@FXML
	public Rectangle Laminar;
	@FXML
	public Rectangle Transmit;
	@FXML
	public Rectangle Turbulent;
	
	@FXML
	public Line TransLineFrom;
	@FXML
	public Line TransLineTo;
	@FXML
	public Slider zoom;
	@FXML
	public Text from;
	@FXML
	public Text to;
	@FXML
	public Text totallength;
	@FXML
	public Label Scale;
	@FXML
	public Button run;
		
	private kind kind=null;
	private float TPF=0,TPT=0;

	@FXML
    public void initialize() {
		FlowType.getItems().add("----------");
		FlowType.getItems().add("Pipe Flow");
		FlowType.getItems().add("Plant Flow");
		Viscid.setText("");
		TranspointFrom.setText("");
		TranspointTo.setText("");
		Scale.setText("");
		totallength.setText("");
		TransLineFrom.setVisible(false);
		TransLineTo.setVisible(false);
		from.setVisible(false);
		to.setVisible(false);
		Laminar.setVisible(false);
		Transmit.setVisible(false);
		Turbulent.setVisible(false);
		zoom.setMin(1);
		zoom.setMax(1000);
    }
	public void setRe(kind kind){
		this.kind=kind;
		Re.setText(""+kind.getRe());
	}
	public void setName(kind kind){
		this.kind=kind;
		Name.setText(kind.gettype());
	}
	public void setFlowAnalysisControl(Stage dialogStage) {
		// TODO Auto-generated method stub
		this.stage=dialogStage;
	}
	@FXML
	public void handleAnalysis(){
		setViscid();
		settranspoint();
		setanimation();
	}
	public float getRe(){
		return kind.getRe();
	}
	
	public void setViscid(){
		
		float Reynolds=getRe();
		int select =FlowType.getSelectionModel().getSelectedIndex();
		switch(select){
		case 0:
			Viscid.setText("N/A");
			break;
		case 1:
			if(0<Reynolds&&Reynolds<2100){
				Viscid.setText("Laminar Flow");
			}
			else if(2100<=Reynolds&&Reynolds<4000){
				Viscid.setText("Transmit Flow");
			}
			else if(Reynolds>4000){
				Viscid.setText("Turbulent Flow");
			}else{
				Viscid.setText("Unknown");
			}
			break;
		case 2:
			if(0<Reynolds&&Reynolds<500000){
				Viscid.setText("Laminar Flow");
			}
			else if(500000<=Reynolds&&Reynolds<550000){
				Viscid.setText("Transmit Flow");
			}
			else if(Reynolds>550000){
				Viscid.setText("Turbulent Flow");
			}else{
				Viscid.setText("Unknown");
			}
			break;
		default:		
			Viscid.setText("Unknown");
			break;
		}
	}
	public void settranspoint(){
		float D=kind.getdensity();
		float vis=kind.getviscosity();
		float V=kind.getvelocity();
		int select =FlowType.getSelectionModel().getSelectedIndex();
		
		switch(select){
		case 1:
			TPF=2100*vis/(D*V);
			TPT=4000*vis/(D*V);
			TranspointFrom.setText(TPF+" m");	
			TranspointTo.setText(TPT+" m");
			break;
		case 2:
			TPF=500000*vis/(D*V);
			TPT=550000*vis/(D*V);
			TranspointFrom.setText(TPF+" m");	
			TranspointTo.setText(TPT+" m");
			break;
		default:
			TranspointFrom.setText("Unknown");
			TranspointTo.setText("");
		}
	}
	public void setanimation(){
		int select =FlowType.getSelectionModel().getSelectedIndex();
		if(select==1){
		double scale=350*zoom.getValue()/kind.getlength();
		TransLineFrom.setLayoutX(scale*TPF+50);
		TransLineTo.setLayoutX(scale*TPT+50);
		float S=(float) (kind.getlength()/zoom.getValue());
		totallength.setText(""+(float)Math.round(100000*S)/100000+" m");
		from.setLayoutX(scale*TPF+35);
		to.setLayoutX(scale*TPT+42);
		Laminar.setWidth(scale*TPF);
		Transmit.setLayoutX(scale*TPF+50);
		Transmit.setWidth(scale*TPT-scale*TPF);
		Turbulent.setLayoutX(scale*TPT+50);
		Turbulent.setWidth(350-scale*TPT);
		TransLineFrom.setVisible(true);
		TransLineTo.setVisible(true);
		from.setVisible(true);
		to.setVisible(true);
		Laminar.setVisible(true);
		Transmit.setVisible(true);
		Turbulent.setVisible(true);
		Scale.setText(""+(int)zoom.getValue());
		if(scale*TPT+50>=400){
			TransLineTo.setVisible(false);
			to.setVisible(false);
		
			if(scale*TPF+50>=400){
				TransLineFrom.setVisible(false);
				from.setVisible(false);
			}
		}
		}
	}
	@FXML
	public void run() throws InterruptedException{
		for(int i=1;i<=1000;i++){
			zoom.setValue(i);			
		    setanimation();
		    }
	}
	@FXML
	public void handleCancel(){
		
		stage.close();
	}
	
}
