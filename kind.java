package address.model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class kind {
	private final StringProperty type;
	private final FloatProperty velocity;
    private final FloatProperty density;
    private final FloatProperty temparture;
    private final FloatProperty viscosity;
    private final FloatProperty length;
    private final FloatProperty altitude;
    private final FloatProperty Re;
    private final FloatProperty Sonic;
    private final FloatProperty Mach;
    
    public kind(){
    	this(null);
    }
    public kind(String type){
    	this.type=new SimpleStringProperty("Dry Air");
    	this.velocity=new SimpleFloatProperty();
    	this.temparture=new SimpleFloatProperty();
    	this.altitude=new SimpleFloatProperty();
    	this.density=new SimpleFloatProperty();
    	this.viscosity=new SimpleFloatProperty();
    	this.length=new SimpleFloatProperty();
    	this.Re=new SimpleFloatProperty();
    	this.Sonic=new SimpleFloatProperty();
    	this.Mach=new SimpleFloatProperty();
    	
    }
    public String gettype(){
    	return type.get();
    }
    public void settype(String type){
    	this.type.set(type);
    }
    public StringProperty typeproperty(){
    	return type;
    }
    public float getvelocity(){
    	return velocity.get();
    }
    public void setvelocity(float velocity){
    	this.velocity.set(velocity);
    }
    public FloatProperty velocityproperty(){
    	return velocity;
    }
    public float gettemparture(){
    	return temparture.get();
    }
    public void settemparture(float temparture){
    	this.temparture.set(temparture);
    }
    public FloatProperty tempartureproperty(){
    	return temparture;
    }
    public float getaltitude(){
    	return altitude.get();
    }
    public void setaltitude(float altitude){
    	this.altitude.set(altitude);
    }
    public FloatProperty altitudeproperty(){
    	return altitude;
    }
    public void setdensity(float density){
    	float input=density;
    	if(input==0.0){
    		float A=altitude.get();
        	double T=temparture.get()+273.15;
        	double pressure=(101325*Math.pow((1-2.25577*A*Math.pow(10, -5)),5.25588));
    		this.density.set((float) (pressure/(287.05*T)));
    	}else{
    	    this.density.set(density);
    	}
    }
    public FloatProperty densityproperty(){
    	return density;
    }
    public float getdensity(){
    	return density.get();
    }
    public void setviscosity(float viscosity){
    	float input=viscosity;
    	if(input==0.0){
    	double T=temparture.get()+273.15;
    	this.viscosity.set( (float) ((1.458*T*Math.sqrt(T)*0.000001)/(T+110.4)));
    	}else{
    		this.viscosity.set(viscosity);
    	}
    }
    public float getviscosity(){
    	return viscosity.get();
    }
    public FloatProperty viscosityproperty(){
    	return viscosity;
    }
    public float getlength(){
    	return length.get();
    }
    public void setlength(float length){
    	this.length.set(length);
    }
    public FloatProperty lengthproperty(){
    	return length;
    }
    public void setRe(){
    	float D=density.get();
    	float V=velocity.get();
    	float L=length.get();
    	float Vis=viscosity.get();
		double Re=D*V*L/Vis;
		this.Re.set((float) Re);
    }
    public float getRe(){
    	return Re.get();
    }
    public FloatProperty Reproperty(){
    	return Re;
    }
    public FloatProperty Sonicproperty(){
    	return Sonic;
    }
    public float getSonic(){
    	return Sonic.get();
    }
    public void setSonic(){
    	double T=temparture.get()+273.15;
    	this.Sonic.set((float) Math.sqrt(1.4*287*T));
    }
    public FloatProperty Machproperty(){
    	return Mach;
    }
    public float getMach(){
    	return Mach.get();
    }
    public void setMach(){
    	float V=velocity.get();
    	float a=Sonic.get();
    	this.Mach.set(+V/a);
    }

}
