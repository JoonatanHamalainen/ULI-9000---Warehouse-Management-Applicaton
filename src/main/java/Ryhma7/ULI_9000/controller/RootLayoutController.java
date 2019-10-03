package Ryhma7.ULI_9000.controller;

import Ryhma7.ULI_9000.App;
import Ryhma7.ULI_9000.model.Storage;
import javafx.fxml.FXML;

public class RootLayoutController {
	private App mainApp;
	private Storage storage;
	
	public void setMainApp(App mainApp) {
		this.mainApp = mainApp;
	}
	
	public void setStorage(Storage storage){
		this.storage = storage;
	}
	
	@FXML
	private void handleShowStorageLayout() {
		System.out.println("Storage Layout");
		mainApp.showStorageLayout(storage);
	}
	
	@FXML
	private void handleShowStorageEdit() {
		System.out.println("Edit Storage");
		mainApp.showEditStorageLayout();
	}
	
	@FXML
	private void handleExit() {
		System.exit(0);
	}
}
