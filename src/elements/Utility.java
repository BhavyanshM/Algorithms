package elements;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.control.Label;

public class Utility {
	
	public static final int SIZE = 20000;
	
	public static final int DIM_X = 640;
	public static final int DIM_Y = 480;
	public static final int INIT_X = 0;
	public static final int INIT_Y = 0;	
	public static final int DIST = 20;
	public static final int FIN_X = DIM_X;
	public static final int FIN_Y = DIM_Y;
	public static final int GOAL_MARGIN = 30;
	
	public ArrayList<Obstacle> obs;
	public ArrayList<Obstacle> mobs;
	public Agent agent;
	
	public static Label label;
	
	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		Utility.label = label;
	}

	public Utility(){
		obs = new ArrayList<Obstacle>();
		mobs = new ArrayList<Obstacle>();
		addObstacles();
	}
	
	public void addObstacles(){
		//X-coordinate, Y-coordinate, Radius
		obs.add(new Obstacle(70, 100, 170));
		obs.add(new Obstacle(270, 160, 150));
		obs.add(new Obstacle(150, 300, 100));
		obs.add(new Obstacle(330, 360, 100));
		obs.add(new Obstacle(410, 30, 100));
		
		
		//X-coordinate, Y-coordinate, Diameter, VelX, VelY
		mobs.add(new Obstacle(400, 150, 120, r(-4,8), r(-4,8)));
		mobs.add(new Obstacle(180, 320, 150, r(-4,8), r(-4,8)));
		mobs.add(new Obstacle(150, 150, 110, r(-4,8), r(-4,8)));
		mobs.add(new Obstacle(400, 320, 140, r(-4,8), r(-4,8)));
		
		//X-coordinate, Y-coordinate, VelX, VelY
		this.agent = new Agent(0, 0, 0, 0);
		
		
	}
	
	static public int r(int min, int max){
		int x = min + new Random().nextInt(max);
		if(x!=0)return x;
		else return x+1;
	}
	
	public Node getRandomNode(int id){
		Node result = new Node((int)(Math.random()*DIM_X), (int)(Math.random()*DIM_Y), id);
		return result;
	}
	
	public int dist(Node a, Node b){
		return (int)Math.sqrt((a.x-b.x)*(a.x-b.x)+(a.y-b.y)*(a.y-b.y));
	}
	
}
