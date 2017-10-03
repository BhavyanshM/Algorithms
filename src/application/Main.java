package application;
	
import elements.*;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;


public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			Canvas canvas = new Canvas(Utility.DIM_X, Utility.DIM_Y);
			Group root = new Group();
			Scene scene = new Scene(root, Utility.DIM_X, Utility.DIM_Y);
			
			GraphicsContext gc = canvas.getGraphicsContext2D();
			Utility util = new Utility();
			Plan plan = new Plan(gc, util);
			
			plan.RRT();
			
			root.getChildren().add(canvas);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	

	
	public static void main(String[] args) {
		launch(args);
	}
}
