package application;
	
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

	private FXMLLoader loader;
	private Parent root;
	private Scanner scan;
	
	@Override
	public void start(Stage primaryStage) throws Exception{
			scan = new Scanner(System.in);
			loader = new FXMLLoader();
			root = loader.load(getClass().getResource("/resources/interface.fxml").openStream());
			Scene scene = new Scene(root, 1000, 800);		
			primaryStage.setScene(scene);
			primaryStage.setMaximized(true);
			primaryStage.show();
			
//			Thread build = new Thread(() ->{
//				String s = "";
//				while(true){
//					if(scan.hasNext()){
//						s = scan.nextLine();
//						System.out.println("String: " + s);
//					}
//				}
//			});
//			
//			build.start();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
