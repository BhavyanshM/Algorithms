package elements;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Plan {
	
	static final int DEL_RATIO = 10;
//	static final int DEL = (int)(Math.sqrt(DIM_X*DIM_X + DIM_Y*DIM_Y)/DEL_RATIO);
	static final int DEL = 1200;
	static final boolean REACHED = false;
		
	static ArrayList<Node> G = new ArrayList<Node>();

	static boolean good = true;
	
	public GraphicsContext gc;
	public Utility util;
	
	public Plan(GraphicsContext gc, Utility util){
		this.gc = gc;
		this.util = util;
	}
	
	public void RRT(){
		gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(1);
        
        int k = 0;
        
        Node init = new Node(Utility.INIT_X, Utility.INIT_Y, k++);
        G.add(init);
        boolean done = false;
        for(int i = 0; i<Utility.SIZE; i++){
        	Node rand = util.getRandomNode(k);
        	Node nearest = getNearestNode(rand);
        	Node newNode;
        	if(util.dist(rand, nearest)>Plan.DEL){
        		double r = Plan.DEL/util.dist(rand, nearest);
            	newNode = new Node((int)(r*rand.x + (1-r)*nearest.x), (int)(r*rand.y + (1-r)*nearest.y),k++);
        	}else{
        		newNode = rand;
        	}
        	newNode.setPrev(nearest);
        	nearest.setNext(newNode);
        	Line2D line = new Line2D.Double(newNode.x, newNode.y, nearest.x, nearest.y);
       	
           	for(Obstacle o : util.obs){
        		if(line.ptSegDist(new Point2D.Double(o.x, o.y)) <= o.radius){
        			good = false;
        		}
        	}
        	if(good==true){
    			G.add(rand);
            	gc.strokeLine(rand.x, rand.y, nearest.x, nearest.y);
    		}
        	if(line.ptSegDist(Utility.FIN_X, Utility.FIN_Y) <= Utility.MARGIN && good){
        		gc.setStroke(Color.RED);
        		gc.setLineWidth(2);
        		Node current = rand;
        		Node previous, m1, m2;
        		int count = 0;
        		while(current.prev.prev != null & !done){
        			System.out.println(current);        			
        			m1 = new Node((int)((current.prev.x + current.prev.prev.x)/2),
        					(int)((current.prev.y + current.prev.prev.y)/2), 0);
        			m2 = new Node((int)((current.prev.x + current.x)/2), 
        					(int)((current.y + current.prev.y)/2), 0);
      
        			if(count==0){
        				gc.strokeLine(m2.x, m2.y, Utility.FIN_X, Utility.FIN_Y);
        			}else if(current.prev.prev.prev == null){
        				gc.strokeLine(m1.x, m1.y, Utility.INIT_X, Utility.INIT_Y);
        			}
        			count++;
        			
        			previous = current.prev;
        			gc.setStroke(Color.YELLOW);
        			gc.strokeLine(previous.x, previous.y, current.x, current.y);
        			gc.setStroke(Color.RED);
//        			gc.setLineWidth(3);
        			gc.strokeLine(m1.x, m1.y, m2.x, m2.y);
        			current = previous;
        		}
        		gc.setStroke(Color.BLUE);
        		gc.setLineWidth(1);
        		done = true;
//        		break;
        	}
        	
        	good = true;
        }
        
	}
	
	public Node getNearestNode(Node node){
		Node near = new Node(Integer.MAX_VALUE, Integer.MAX_VALUE, -1);
		int min = Integer.MAX_VALUE;
		for(Node N : G){
			if(util.dist(node, N)<min ){
				min = util.dist(node, N);
				near = N;
			}
		}
		return near;
	}
}
