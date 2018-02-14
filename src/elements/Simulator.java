package elements;
import java.util.ArrayList;

import controllers.Controller;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Simulator {
	public ArrayList<Node> G;
	public boolean good;	
	public GraphicsContext gc;
	public Utility util;
	public double reward;
	public Controller con;
	
	public Simulator(GraphicsContext g, Utility u){
		this.util = u;
		this.gc = g;
		reward = 0;
	}
	
	public void simulate(){
		gc.setFill(Color.CHOCOLATE);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(1);
        System.out.println("Simulation Started");
    	

	    new AnimationTimer()
	    {
	    	int time = 0;
	    	Agent agent = util.agent;
	        public void handle(long currentNanoTime)
	        {	
	        	time++;
	        	gc.clearRect(0, 0, Utility.DIM_X, Utility.DIM_Y);

	        	drawBoundary(gc);
	        	for(Obstacle sob : util.obs){
	        		gc.setFill(Color.LIGHTSEAGREEN);
	        		gc.fillOval(sob.getX(), sob.getY(), sob.getRadius(), sob.getRadius());
	        		int obx = sob.radius/2 + sob.x;
	    			int oby = sob.radius/2 + sob.y;
	    			int agx = agent.getX() + 5;
	    			int agy = agent.getY() + 5;	 
		        	gc.setLineWidth(1);
		        	gc.setStroke(Color.BISQUE);
	    			gc.strokeLine(agx, agy, obx, oby);
	        	}
	        	
	        	for(Obstacle obs : util.mobs){
	        		gc.setFill(Color.CHOCOLATE);
	        		gc.fillOval(obs.getX(), obs.getY(), obs.getRadius(), obs.getRadius());
	        		gc.setFill(Color.BLACK);
	        		gc.fillOval(agent.getX(), agent.getY(), 10, 10);
	        		obs = move(obs);
	        		
	        		if(time==2){
	        			agent = act(agent);
	        			reward = eval(agent);
	        			time = 0;
	        		}
	        		System.out.printf("%.2f\n", reward);
	        		int obx,oby,agx,agy;
	    			obx = obs.radius/2 + obs.x;
	    			oby = obs.radius/2 + obs.y;
	    			agx = agent.getX() + 5;
	    			agy = agent.getY() + 5;
	    			gc.setLineWidth(1);
		        	gc.setStroke(Color.BISQUE);
	    			gc.strokeLine(agx, agy, obx, oby);
	    			
	    			
	        	}
	        }
	    }.start();
        
	}
	
	public double eval(Agent agent){
		double result = reward;
		if(agent.getX()<Utility.DIM_X && agent.getY()<Utility.DIM_Y
				&& agent.getX()>0 && agent.getY()>0){
			result += 0.1;
			System.out.println("Good");
		}else if(agent.getX()>Utility.DIM_X || agent.getY()>Utility.DIM_Y
				|| agent.getX()<0 || agent.getY()<0){
			result -= 5;
			if(result<0)result=0;
			System.out.println("Outside");
		}
		
		for(Obstacle o : util.mobs){
			int obx,oby,agx,agy;
			obx = o.radius/2 + o.x;
			oby = o.radius/2 + o.y;
			agx = agent.getX() + 5;
			agy = agent.getY() + 5;
			if(util.dist(new Node(agx,  agy), new Node(obx, oby)) <=o.radius/2){
				result--;
				if(result<0)result = 0;
				System.out.println("InsideObstacleBoundary");
				break;				
			}			
		}
		
		for(Obstacle o : util.obs){
			int obx,oby,agx,agy;
			obx = o.radius/2 + o.x;
			oby = o.radius/2 + o.y;
			agx = agent.getX() + 5;
			agy = agent.getY() + 5;
			if(util.dist(new Node(agx,  agy), new Node(obx, oby)) <=o.radius/2){
				result--;
				if(result<0)result = 0;
				System.out.println("InsideObstacleBoundary");
				break;				
			}
		}
		
		return result;
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
	
	public Agent act(Agent agent){
//		System.out.println("Agent:" + agent.x + "," + agent.y);
		agent.x+=agent.velx;
		agent.y+=agent.vely;
		return agent;
	}

}
