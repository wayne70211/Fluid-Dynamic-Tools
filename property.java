package address.model;



public class property {
	
	
	public double vis;
	public double density;
	public void water(int t){
		switch(t){
		case 0:
			this.vis=1.792;
			this.density=999.8675;
			break;
		case 4:
			this.vis=1.602;
			this.density=1000;
			break;
		case 5:
			this.vis=1.519;
			this.density=999.9881;
			break;
		case 10:
			this.density=999.7281;
			this.vis=1.308;
			break;
		case 15:
			this.density=999.1285;
			this.vis=1.140;
			break;
		case 20:
			this.density=998.2336;
			this.vis=1.005;
			break;
		case 25:
			this.density=997.0751;
			this.vis=0.894;
			break;
		case 30:
			this.density=995.6783;
			this.vis=0.801;
			break;
		case 35:
			this.density=994;
			this.vis=0.729;
			break;
		case 40:
			this.density=992.2;
			this.vis=0.656;
			break;
		case 45:
			this.density=990.2;
			this.vis=0.599;
			break;
		case 50:
			this.density=988;
			this.vis=0.549;
			break;
		case 60:
			this.density=983.2;
			this.vis=0.469;
			break;
		case 70:
			this.density=977.8;
			this.vis=0.409;
			break;
		case 80:
			this.density=971.8;
			this.vis=0.357;
			break;
		case 90:
			this.density=965.3;
			this.vis=0.317;
			break;
		case 100:
			this.density=959.1;
			this.vis=0.284;
			break;
		default	:
			this.density=-1;
			this.vis=-1000;
			break;		
		}		
	}
	
	double getvis(){
		return vis/1000;
	}
	double getdensity(){
		return density;
	}
	

	
}


 
