package Ryhma7.ULI_9000;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;

import Ryhma7.ULI_9000.controller.RootLayoutController;
import Ryhma7.ULI_9000.controller.StorageController;
import Ryhma7.ULI_9000.model.Storage;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class App extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	
	//Väliaikaiset muuttujat tallennusta varten(Kunnes tietokanta pelittää).
	private Storage storage;
	public App() {
		
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Uli-9000");

		this.storage = new Storage();
		
		initRootLayout();
		
	}
	
	public void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(App.class.getResource("view/Rootlayout.fxml"));
		
			this.rootLayout =(BorderPane) loader.load();
			Scene scene = new Scene(this.rootLayout);
			scene.getStylesheets().add("StoragePalette.css");
			
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
			int height = 300;
			int width = 600;
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(App.class.getResource("view/StorageLayout.fxml"));
			
			BorderPane page = (BorderPane) loader.load();
			
			this.rootLayout.setCenter(page);
			
			final StorageController controller = loader.getController();
			controller.setMainApp(this);
			controller.setStorage(this.storage);
			controller.setPane(page);
			controller.loadStorageLayout();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[]args) {
		launch();
	}
}
