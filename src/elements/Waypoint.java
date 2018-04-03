package elements;

public class Waypoint {
	private int index, coordFrame, command, currentWP, autoContinue;
	private double param1, param2, param3, param4;
	private double longitude, latitude, altitude;
	
	public Waypoint(double lon, double lat, double alt){
		this.longitude = lon;
		this.latitude = lat;
		this.altitude = alt;
	}
	
	public String toString(){
		String result = index + "\t"
				+ currentWP + "\t" 
				+ coordFrame + "\t"
				+ command + "\t"
				+ param1 + "\t" + param2 + "\t" + param3 + "\t" + param4 + "\t"
				+ longitude + "\t" + latitude + "\t" + altitude + "\t"
				+ autoContinue + "\n";
		return result;
	}
	
	public void setAutoContinue(int auto){
		this.autoContinue = auto;
	}
	
	public int getAutoContinue(){
		return this.autoContinue;
	}
	
	public void setCurrent(int cur){
		this.currentWP = cur;
	}
	
	public int isCurrent(){
		return currentWP;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getCoordFrame() {
		return coordFrame;
	}

	public void setCoordFrame(int coordFrame) {
		this.coordFrame = coordFrame;
	}

	public int getCommand() {
		return command;
	}

	public void setCommand(int command) {
		this.command = command;
	}

	public double getParam1() {
		return param1;
	}

	public void setParam1(double param1) {
		this.param1 = param1;
	}

	public double getParam2() {
		return param2;
	}

	public void setParam2(double param2) {
		this.param2 = param2;
	}

	public double getParam3() {
		return param3;
	}

	public void setParam3(double param3) {
		this.param3 = param3;
	}

	public double getParam4() {
		return param4;
	}

	public void setParam4(double param4) {
		this.param4 = param4;
	}
	
	
	
}
