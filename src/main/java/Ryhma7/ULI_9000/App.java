package Ryhma7.ULI_9000;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import Ryhma7.ULI_9000.controller.RootLayoutController;
import Ryhma7.ULI_9000.controller.NewItemDialogController;
import Ryhma7.ULI_9000.controller.NewStorageDialogController;
import Ryhma7.ULI_9000.controller.StorageController;
import Ryhma7.ULI_9000.model.Item;
import Ryhma7.ULI_9000.model.Shelf;
import Ryhma7.ULI_9000.model.Storage;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class App extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	private Storage storage;
	private ArrayList<Storage> storages;
	private ArrayList<Shelf> shelves;

	public App() {
	}

	/**Initial setup for the program
	 * 
	 */
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Uli-9000");

		this.storage = new Storage();
		this.shelves = new ArrayList<Shelf>();
		this.storages = new ArrayList<Storage>();
		
		this.storages.add(new Storage(5,5));
		this.storages.add(new Storage(10,10));
		
		this.storages.get(0).setName("Iso varasto");
		this.storages.get(0).setAddress("Hienolan tie 6 B");
		this.storages.get(1).setName("Pieni Varasto");
		this.storages.get(1).setAddress("Pikkulan kuja 1");
		
		
		initRootLayout();
		
	}
	
	/**Brings the Storage Layout of a specific storage to center of primaryStage
	 * @param storage
	 */
	public void showStorageLayout(Storage storage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(App.class.getResource("view/StorageLayout.fxml"));
			
			AnchorPane page = (AnchorPane) loader.load();
			this.rootLayout.setCenter(page);
			
			StorageController controller = loader.getController();
			
			controller.setMainApp(this);
			controller.setStorage(storage);
			controller.setPane(page);
			controller.loadStorageLayout();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**Brings the StorageLayout to center of primaryStage
	 * 
	 */
	public void showEditStorageLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(App.class.getResource("view/StorageLayout.fxml"));
			
			AnchorPane page = (AnchorPane) loader.load();
			
			this.rootLayout.setCenter(page);
			
			StorageController controller = loader.getController();
			controller.setMainApp(this);
			controller.setStorage(this.storage);
			controller.setPane(page);
			controller.loadStorageLayout();
		}catch(IOException e) {
			System.out.println(e);
		}
	}
	
	/**Opens NewStorage Modal window
	 * @param storage
	 * @return true if new storage is created, false if not
	 */
	public boolean showNewStorageDialog(Storage storage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(App.class.getResource("view/NewStorageDialog.fxml"));
			
			AnchorPane page = (AnchorPane) loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Create New Storage");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			NewStorageDialogController controller = loader.getController();
			controller.setStorage(storage);
			controller.setDialogStage(dialogStage);
			
			dialogStage.showAndWait();
			
			return controller.getIsOkClicked();			
		}catch(IOException e) {
			System.out.println(e);
			return false;
		}
	}
	
	/**Opens new Item modal window
	 * @param item
	 * @return true if new item is created, false if not
	 */
	public boolean showNewItemDialog(Item item) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(App.class.getResource("view/NewItemDialog.fxml"));
			
			AnchorPane page = (AnchorPane) loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Create New Item");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			NewItemDialogController controller = loader.getController();
			controller.setItem(item);
			controller.setDialogStage(dialogStage);
			
			dialogStage.showAndWait();
			
			return controller.getIsOkClicked();			
		}catch(IOException e) {
			System.out.println(e);
			return false;
		}
	}
	
	/** Initializes the root layout
	 * 
	 */
	public void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(App.class.getResource("view/Rootlayout.fxml"));
		
			this.rootLayout =(BorderPane) loader.load();
			Scene scene = new Scene(this.rootLayout);
			VBox vbox =(VBox) this.rootLayout.getChildren().get(0);
			scene.getStylesheets().add("StoragePalette.css");
			
			this.primaryStage.setScene(scene);
			
			RootLayoutController controller = loader.getController();
			
			controller.setMainApp(this);
			controller.setStorages(this.storages);
			controller.setVBox(vbox);
			
			controller.loadStorages(vbox);
			
			this.primaryStage.show();
				
			
		}catch(IOException e) {
			System.out.println("Noworks");
		}
	}
	
	/**clears the center of border pane
	 * 
	 */
	public void clearCenterPane() {
		BorderPane page = new BorderPane();
		this.rootLayout.setCenter(page);	
	}
	
	/**Returns storages-List
	 * @return storages
	 */
	public ArrayList<Storage> getStorages() {
		return storages;
	}
	
	/**Launches the program
	 * @param args
	 */
	public static void main(String[]args) {
		launch();
	}
}
