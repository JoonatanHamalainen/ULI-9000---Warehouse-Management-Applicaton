package Ryhma7.ULI_9000.controller;

import Ryhma7.ULI_9000.App;
import javafx.fxml.FXML;

public class RootLayoutController {
	private App mainApp;
	
	public void setMainApp(App mainApp) {
		this.mainApp = mainApp;
	}
	
	@FXML
	private void handleShowStorageLayout() {
		System.out.println("Storage Layout");
		mainApp.showStorageLayout();
	}
	
	@FXML
	private void handleExit() {
		System.exit(0);
	}
}
