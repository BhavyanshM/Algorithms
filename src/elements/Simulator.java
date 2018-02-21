package elements;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import controllers.Controller;
import javafx.animation.AnimationTimer;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class Simulator {
	public ArrayList<Node> G;
	public boolean good;	
	public GraphicsContext gc;
	public Utility util;
	public double reward;
	public Controller con;
	public Canvas canvas;
	public Thread click;
	public BufferedReader actionIn;
	public BufferedWriter rewardOut;
	File actionFile;
	File rewardFile;
	String actionString;
	
	public Simulator(Canvas can, Utility u) throws Exception{
		this.util = u;
		this.canvas = can;
		this.gc = can.getGraphicsContext2D();
		actionString = "NOP";
		reward = 0;
		actionFile = new File("C:/DataBM/Research/ML/Images/action.txt");
		actionFile.createNewFile();
		rewardFile = new File("C:/DataBM/Research/ML/Images/reward.txt");
		rewardFile.createNewFile();
		actionIn = new BufferedReader(new FileReader(actionFile));
		rewardOut = new BufferedWriter(new FileWriter(rewardFile, false));
	}
	
	public void simulate(){
		gc.setFill(Color.CHOCOLATE);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(1);
        System.out.println("Simulation Started");
    	

	    new AnimationTimer()
	    {
	    	int time = 0;
	    	int p = 0;
	    	Agent agent = util.agent;
	    	
	        public void handle (long currentNanoTime) 
	        {	
	        	time++;
	        	gc.clearRect(0, 0, Utility.DIM_X, Utility.DIM_Y);

	        	drawBoundary(gc);
	        	drawStaticObstacles();
	        	drawMovingObstalces();	        	
	        	
//        		if(time%2==0){
        	try{	actionString = getActionString();	} catch (Exception e1) {}
        			System.out.println(actionString);
        			setAction(actionString);
        			agent = act(agent);
        			reward = eval(agent);
        	try{	outputReward();						}catch(Exception e){}
//		        	if(time%10==0){
		        		clickImage(p++);
//		        	}
		        	if(time==100000)time=0;
//        		}		
	        }
	    }.start();
        

	
		}
	
	public String getActionString() throws Exception{
		actionIn = new BufferedReader(new FileReader(actionFile));
		String result = actionIn.readLine();
		actionIn.close();
		return result;
	}
	
	public void joinCenters(){
//		int obx,oby,agx,agy;
//		obx = obs.radius/2 + obs.x;
//		oby = obs.radius/2 + obs.y;
//		agx = agent.getX() + 5;
//		agy = agent.getY() + 5;
//		gc.setLineWidth(1);
//    	gc.setStroke(Color.BISQUE);
//		gc.strokeLine(agx, agy, obx, oby);
		
	}
	
	public void drawMovingObstalces(){
		for(Obstacle obs : util.mobs){
    		obs = move(obs); 
    		gc.setFill(Color.CHOCOLATE);
    		gc.fillOval(obs.getX(), obs.getY(), obs.getRadius(), obs.getRadius());
    		gc.setFill(Color.BLACK);
    		gc.fillOval(util.agent.getX(), util.agent.getY(), 10, 10);	        		   			
    	}	
	}
	
	public void drawStaticObstacles(){
		for(Obstacle sob : util.obs){
    		gc.setFill(Color.LIGHTSEAGREEN);
    		gc.fillOval(sob.getX(), sob.getY(), sob.getRadius(), sob.getRadius());
    	}
	}
	
	public void outputReward() throws Exception{
		System.out.printf("%.1f\n", reward);
		rewardOut = new BufferedWriter(new FileWriter(rewardFile, false));
		rewardOut.flush();
		rewardOut.write(String.format("%.1f\n\n", reward));
		rewardOut.close();
	}
	
	public void clickImage(int p){
		WritableImage image = canvas.snapshot(null, null);
		BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
		try{
			ImageIO.write(bImage, "png", new File("C:/DataBM/Research/ML/Images/Output/canvas_image_"+p+".png"));
		}catch(Exception e){
			System.out.println("File canvas_image_" + p + ".png not created.");
		}
	}
	
	public double eval(Agent agent){
		double result = 0;
		if(agent.getX()>Utility.DIM_X || agent.getY()>Utility.DIM_Y
				|| agent.getX()<0 || agent.getY()<0){
			result = -5;
//			if(result<0)result=0;
//			System.out.println("Outside");
		}
		else if(agent.velx==0 && agent.vely==0 && agent.x !=0 && agent.y!=0){	
//			System.out.println("STOPPED");
			result = -1;
//			if(result<0)result=0;
		}
		else if(agent.getX()<Utility.DIM_X && agent.getY()<Utility.DIM_Y
				&& agent.getX()>0 && agent.getY()>0){
			result = 0.1;
//			System.out.println("Good");
		}
		
		
		
		for(Obstacle o : util.mobs){
			int obx,oby,agx,agy;
			obx = o.radius/2 + o.x;
			oby = o.radius/2 + o.y;
			agx = agent.getX() + 5;
			agy = agent.getY() + 5;
			if(util.dist(new Node(agx,  agy), new Node(obx, oby)) <=o.radius/2){
				result = -1;
//				if(result<0)result = 0;
//				System.out.println("InsideObstacleBoundary");
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
				result = -1;
//				if(result<0)result = 0;
//				System.out.println("InsideObstacleBoundary");
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

	public void setAction(String action){
		util.agent.state = Agent.STARTING;
		
		if(action.equals(""))return;
		
		if(action.contains("STOP")){
				util.agent.velx=0;
				util.agent.vely=0;
		}
		
		if(action.contains("LEFT")){
			if(util.agent.velx>-4){
				util.agent.velx--;
			}
		}			
			
		if(action.contains("RIGHT")){
			if(util.agent.velx<4){
				util.agent.velx++;
			}
		}
		
		if(action.contains("UP")){
			if(util.agent.vely>-4){
				util.agent.vely--;
			}
		}
				
		if(action.contains("DOWN")){
			if(util.agent.vely<4){
				util.agent.vely++;
			}
		}
	}
	
}
