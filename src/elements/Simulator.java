package elements;
import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Simulator {
	public ArrayList<Node> G;
	public boolean good;	
	public GraphicsContext gc;
	public Utility util;
	
	public Simulator(GraphicsContext g, Utility u){
		this.util = u;
		this.gc = g;
	}
	
	public void simulate(){
		gc.setFill(Color.CHOCOLATE);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(1);
        
        System.out.println("Simulation Started");
    	

	    new AnimationTimer()
	    {
	    	int x = 100;
	        public void handle(long currentNanoTime)
	        {
	        	
	        	
	        	gc.clearRect(0, 0, Utility.DIM_X, Utility.DIM_Y);
	        	drawBoundary(gc);
	        	for(Obstacle obs : util.mobs){
	        		gc.fillOval(obs.getX(), obs.getY(), obs.getRadius(), obs.getRadius());	          
	        		obs = move(obs);
	        	}
	        }
	    }.start();
        
	}
	
	public void drawBoundary(GraphicsContext g){
		g.setStroke(Color.BLACK);
		g.setLineWidth(2);
		g.strokeLine(0, 0, 0, Utility.DIM_Y);
		g.strokeLine(0, Utility.DIM_Y, Utility.DIM_X, Utility.DIM_Y);
		g.strokeLine(Utility.DIM_X, 0, Utility.DIM_X, Utility.DIM_Y);
		g.strokeLine(0, 0, Utility.DIM_X, 0);
	}
	
	public Obstacle move(Obstacle obs){
		if(obs.x<0)obs.velx*=-1;
		if(obs.x>Utility.DIM_X-obs.radius)obs.velx*=-1;
		if(obs.y<0)obs.vely*=-1;
		if(obs.y>Utility.DIM_Y-obs.radius)obs.vely*=-1;
		obs.x+=obs.velx;
		obs.y+=obs.vely;
		return obs;
	}
}
