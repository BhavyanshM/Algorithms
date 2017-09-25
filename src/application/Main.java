package application;
	
import java.awt.geom.Point2D;
import java.util.ArrayList;

import java.awt.geom.Line2D;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application {
	
	static final int SIZE = 20000;
	
	static final int DIM_X = 800;
	static final int DIM_Y = 600;
	static final boolean REACHED = false;
		
	static ArrayList<Node> G = new ArrayList<Node>();
	static ArrayList<Obstacle> obs = new ArrayList<Obstacle>();
	
	static final int INIT_X = 0;
	static final int INIT_Y = 0;	
	static final int DIST = 20;
	
	static boolean good = true;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Group root = new Group();
			Scene scene = new Scene(root,this.DIM_X,this.DIM_Y);
			
			Canvas canvas = new Canvas(DIM_X,DIM_Y);
			
			GraphicsContext gc = canvas.getGraphicsContext2D();
			
			addObstacles();
			
			RRT(gc);
			
			root.getChildren().add(canvas);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addObstacles(){
		obs.add(new Obstacle(500, 100, 80));
		obs.add(new Obstacle(200, 400, 150));
		obs.add(new Obstacle(600, 450, 100));
	}
	
	public void RRT(GraphicsContext gc){
		gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(1);
        
        int k = 0;
        
        Node init = new Node(INIT_X, INIT_Y, k++);
        G.add(init);
        
        for(int i = 0; i<SIZE; i++){
        	Node rand = getRandomNode(k);
        	Node nearest = getNearestNode(rand);
        	rand.setPrev(nearest);
        	nearest.setNext(rand);
        	
        	Line2D line = new Line2D.Double(rand.x, rand.y, nearest.x, nearest.y);
       	
        	
        	for(Obstacle o : obs){
        		if(line.ptSegDist(new Point2D.Double(o.x, o.y)) <= o.radius){
        			good = false;
        		}
        	}
        	if(good==true){
    			G.add(rand);
            	gc.strokeLine(rand.x, rand.y, nearest.x, nearest.y);
    		}
        	good = true;
        }
        
	}
	
	public int dist(Node a, Node b){
		return (int)Math.sqrt((a.x-b.x)*(a.x-b.x)+(a.y-b.y)*(a.y-b.y));
	}
	
	public Node getNearestNode(Node node){
		Node near = new Node(Integer.MAX_VALUE, Integer.MAX_VALUE, -1);
		int min = Integer.MAX_VALUE;
		for(Node N : G){
			if(dist(node, N)<min ){
				min = dist(node, N);
				near = N;
			}
		}
		return near;
	}
	
	public Node getRandomNode(int id){
		Node result = new Node((int)(Math.random()*DIM_X), (int)(Math.random()*DIM_Y), id);
		return result;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	static class Node{
		int x;
		int y;
		int n;
		Node next;
		Node prev;
		
		public Node(int a, int b, int c){
			this.x = a;
			this.y = b;
			this.n = c;
		}
		
		public void setNext(Node a){
			this.next = a;
		}
		
		public void setPrev(Node a){
			this.prev = a;
		}
		
		public Node getNext(){
			return this.next;
		}
		
		public Node getPrev(){
			return this.prev;
		}
		
		public String toString(){
			return "(" + this.x + ", " + this.y + ")"; 
		}
	}
	
	static class Obstacle{
		int x;
		int y;
		int radius;
		
		public Obstacle(int x, int y, int r){
			this.x = x;
			this.y = y;
			this.radius = r;
		}
		
		public int getX() {
			return x;
		}
		public int getY() {
			return y;
		}
		public int getRadius() {
			return radius;
		}
		public void setRadius(int radius) {
			this.radius = radius;
		}		
	}
}
