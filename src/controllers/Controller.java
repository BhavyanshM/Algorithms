package controllers;

import com.jfoenix.controls.JFXButton;

import elements.Plan;
import elements.Simulator;
import elements.Utility;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class Controller {
	Canvas canvas;
	@FXML
	JFXButton runButton;
	
	private Utility util;
	private Plan plan;
	private GraphicsContext gc;
	private Simulator sim;
	
	Thread simulation;
	
	public Controller(){
	}
	
	@FXML
	public void onRunClicked(ActionEvent event) throws Exception{
		util = new Utility();
		canvas = new Canvas(Utility.DIM_X, Utility.DIM_Y);
		BorderPane pane = (BorderPane)(runButton.getParent().getParent().getParent());
		pane.setCenter(canvas);
		gc = canvas.getGraphicsContext2D();
		plan = new Plan(gc, util);	
//		Thread build = new Thread(() ->{
			plan.RRT();
//			try{Thread.sleep(10);}catch(Exception e){}
//		});
//		
//		build.start();
	}
	
	@FXML
	public void onSimulateClicked(ActionEvent event) throws Exception{
		util = new Utility();
		canvas = new Canvas(Utility.DIM_X, Utility.DIM_Y);
		gc = canvas.getGraphicsContext2D();
		sim = new Simulator(gc, util);
		BorderPane pane = (BorderPane)(runButton.getParent().getParent().getParent());
		pane.setCenter(canvas);
		sim.simulate();

	}
	

	
	@FXML
	public void onCancel(ActionEvent event) throws Exception{
		this.simulation.interrupt();
	}
}
