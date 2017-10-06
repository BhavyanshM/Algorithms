package application;
	
import elements.Utility;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

	private FXMLLoader loader;
	private Parent root;
	
	@Override
	public void start(Stage primaryStage) throws Exception{
			loader = new FXMLLoader();
			root = loader.load(getClass().getResource("/resources/interface.fxml").openStream());
			Scene scene = new Scene(root, 1000, 800);		
			primaryStage.setScene(scene);
			primaryStage.setMaximized(true);
			primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
