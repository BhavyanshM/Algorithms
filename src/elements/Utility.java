package elements;

import java.util.ArrayList;
import java.util.Random;

public class Utility {
	
	public static final int SIZE = 10000;
	
	public static final int DIM_X = 800;
	public static final int DIM_Y = 600;
	public static final int INIT_X = 0;
	public static final int INIT_Y = 0;	
	public static final int DIST = 20;
	public static final int FIN_X = DIM_X;
	public static final int FIN_Y = DIM_Y;
	public static final int MARGIN = 30;
	
	public ArrayList<Obstacle> obs;
	public ArrayList<Obstacle> mobs;
	public Agent agent;
	
	public Utility(){
		obs = new ArrayList<Obstacle>();
		mobs = new ArrayList<Obstacle>();
		addObstacles();
	}
	
	public void addObstacles(){
		//X-coordinate, Y-coordinate, Radius
		obs.add(new Obstacle(500, 100, 80));
		obs.add(new Obstacle(200, 400, 150));
		obs.add(new Obstacle(680, 400, 100));
		obs.add(new Obstacle(450, 300, 100));
		
		
		//X-coordinate, Y-coordinate, Radius, VelX, VelY
		mobs.add(new Obstacle(500, 150, 130, r(-4,8), r(-4,8)));
		mobs.add(new Obstacle(180, 350, 140, r(-4,8), r(-4,8)));
		mobs.add(new Obstacle(150, 150, 120, r(-4,8), r(-4,8)));
		mobs.add(new Obstacle(500, 350, 150, r(-4,8), r(-4,8)));
		
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
