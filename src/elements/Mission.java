package elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Mission {	
	private ArrayList<Waypoint> waypoints;
	private String fileName;
	
	public Mission(){
		this.waypoints = new ArrayList<Waypoint>();
		fileName = "RRT_JAVA_WPS.txt";
		
		waypoints.add(new Waypoint(-35.363261, 149.165235, 584.219971));
		waypoints.add(new Waypoint(-35.362474, 149.164746, 15.240000));
		waypoints.add(new Waypoint(-35.361922, 149.163737, 18.288000));
		waypoints.add(new Waypoint(-35.361984, 149.162600, 21.336000));
		waypoints.add(new Waypoint(-35.362710, 149.161871, 24.384000));
		waypoints.add(new Waypoint(-35.363322, 149.161602, 27.432000));
		waypoints.add(new Waypoint(-35.363996, 149.161667, 30.480000));
		waypoints.add(new Waypoint(-35.364678, 149.162192, 27.432000));
		waypoints.add(new Waypoint(-35.364792, 149.162943, 21.336000));
		waypoints.add(new Waypoint(-35.364775, 149.163737, 15.240000));
		waypoints.add(new Waypoint(-35.364512, 149.164875, 9.144000));
		waypoints.add(new Waypoint(-35.363943, 149.165057, 3.048000));		
		for(int i = 0; i<waypoints.size(); i++){
			waypoints.get(i).setIndex(i);
		}
		
		writeMission();
	}
	
	public void addWaypoint(Waypoint wp){
		this.waypoints.add(wp);		
	}
	
	public void resetMission(){
		this.waypoints = new ArrayList<Waypoint>();
	}
	
	public Waypoint getWaypoint(int index){
		return this.waypoints.get(index);
	}
	
	public void writeMission(){
		File file = new File(this.fileName);
		try {
			FileWriter fw = new FileWriter(file);
			fw.write("QGC WPL 110\n");
			for(Waypoint wp : waypoints){
				fw.write(wp.toString());
			}
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.print("ERROR !!!\n");
			e.printStackTrace();
		}
	}
	

}
