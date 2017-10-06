package controllers;

import com.jfoenix.controls.JFXButton;

import elements.Plan;
import elements.Utility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class Controller {
	Canvas canvas;
	@FXML
	JFXButton runButton;
	
	private Utility util;
	private Plan plan;
	private GraphicsContext gc;
	
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
		plan.RRT();
	}
}
