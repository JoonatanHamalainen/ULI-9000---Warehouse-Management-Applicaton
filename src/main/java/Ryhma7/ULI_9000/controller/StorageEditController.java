package Ryhma7.ULI_9000.controller;

import Ryhma7.ULI_9000.App;
import Ryhma7.ULI_9000.model.Shelf;
import Ryhma7.ULI_9000.model.Storage;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class StorageEditController {
	@FXML
	private TextField shelfID;
	@FXML
	private TextField shelfSize;
	@FXML
	private TextField shelfCapacity;
	@FXML 
	private Label warning;
	
	private App mainApp;
	private BorderPane page;
	private Storage storage;
	private Shelf shelf;
	
	public StorageEditController() {	
	}
	
	public void setMainApp(App mainApp) {
		this.mainApp = mainApp;
	}
	
	public void setStorage(Storage storage) {
		this.storage = storage;
	}
	
	public void setPane(BorderPane page) {
		this.page = page;
	}
	
	@FXML
	public void handleNewShelf() {
		System.out.println("New shelf created");
		//TODO
	}
	
	@FXML
	public void handleRemoveShelf() {
		System.out.println("Shelf removed");
		//TODO
	}
	
	@FXML
	public void handleEditShelf() {
		System.out.println("Saved Changes");
		//TODO
	}
}
