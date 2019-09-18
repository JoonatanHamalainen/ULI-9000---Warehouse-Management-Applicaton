package Ryhma7.ULI_9000;

import java.io.IOException;

import Ryhma7.ULI_9000.controller.RootLayoutController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class App extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	
	public App() {
		
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Uli-9000");

		initRootLayout();
		
		showStorageLayout();
		
	}
	
	public void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(App.class.getResource("view/Rootlayout.fxml"));
		
			this.rootLayout =(BorderPane) loader.load();
			Scene scene = new Scene(this.rootLayout);
			
			this.primaryStage.setScene(scene);
			
			RootLayoutController controller = loader.getController();
			controller.setMainApp(this);
			
			this.primaryStage.show();
				
			
		}catch(IOException e) {
			System.out.println("Noworks");
		}
	}
	
	public void showStorageLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(App.class.getResource("view/StorageLayout.fxml"));
			BorderPane page = (BorderPane) loader.load();
			GridPane grid = (GridPane) page.getChildren().get(1);
			for (int i = 0; i<10;i++) {
				for(int j = 0; j<10; j++) {
					Rectangle rectangle = new Rectangle();
					Color paint = Color.GREY;
					rectangle.setFill(paint);
					grid.add(rectangle, i, j);
				}
			}
			Stage storageStage = new Stage();
			storageStage.setTitle("Storage Layout");
			storageStage.initModality(Modality.WINDOW_MODAL);
			storageStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			storageStage.setScene(scene);
			
			storageStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[]args) {
		launch();
	}
}
