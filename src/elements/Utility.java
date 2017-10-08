package elements;

import java.util.ArrayList;

public class Utility {
	
	public static final int SIZE = 30000;
	
	public static final int DIM_X = 800;
	public static final int DIM_Y = 550;
	public static final int INIT_X = 0;
	public static final int INIT_Y = 0;	
	public static final int DIST = 20;
	public static final int FIN_X = DIM_X;
	public static final int FIN_Y = DIM_Y;
	public static final int MARGIN = 30;
	
	public ArrayList<Obstacle> obs;

	public Utility(){
		obs = new ArrayList<Obstacle>();
		addObstacles();
	}
	
	public void addObstacles(){
		obs.add(new Obstacle(500, 100, 80));
		obs.add(new Obstacle(200, 400, 150));
		obs.add(new Obstacle(680, 400, 100));
		obs.add(new Obstacle(450, 300, 100));
	}
	
	public Node getRandomNode(int id){
		Node result = new Node((int)(Math.random()*DIM_X), (int)(Math.random()*DIM_Y), id);
		return result;
	}
	
	public int dist(Node a, Node b){
		return (int)Math.sqrt((a.x-b.x)*(a.x-b.x)+(a.y-b.y)*(a.y-b.y));
	}
	
}