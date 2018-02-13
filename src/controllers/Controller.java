package controllers;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.jfoenix.controls.JFXButton;

import elements.Plan;
import elements.Simulator;
import elements.Utility;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

public class Controller {
	Canvas canvas;
	@FXML
	JFXButton runButton;
	@FXML
	Label reward;
	
	private Utility util;
	private Plan plan;
	private GraphicsContext gc;
	private Simulator sim;
	
	
	public Controller(){
	}
	
	@FXML
	public void onRunClicked(ActionEvent event) throws Exception{
		util = new Utility();
		canvas = new Canvas(Utility.DIM_X, Utility.DIM_Y);
		BorderPane pane = (BorderPane)(runButton.getParent().getParent().getParent());
		pane.setCenter(canvas);
		gc = canvas.getGraphicsContext2D();
		System.out.println("Trying to plan");
		plan = new Plan(gc, util);	
//		Thread build = new Thread(() ->{
			plan.RRT();
//			try{Thread.sleep(10);}catch(Exception e){}
//		});
//		
//		build.start();
	}
	
	@FXML
	public void onKeyEvent (KeyEvent event)throws Exception{
		if(event.getCode().equals(KeyCode.LEFT))
			if(util.agent.velx>-4)
			util.agent.velx--;
		if(event.getCode().equals(KeyCode.RIGHT))
			if(util.agent.velx<4)
				util.agent.velx++;
		if(event.getCode().equals(KeyCode.UP))
			if(util.agent.vely>-4)
				util.agent.vely--;
		if(event.getCode().equals(KeyCode.DOWN))
			if(util.agent.vely<4)
				util.agent.vely++;
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
		WritableImage image = canvas.snapshot(null, null);
		BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
		ImageIO.write(bImage, "png", new File("C:\\DataBM\\canvas_image.png"));
	}
}
