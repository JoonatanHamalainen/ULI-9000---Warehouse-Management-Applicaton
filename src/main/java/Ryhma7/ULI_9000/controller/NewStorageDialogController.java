package Ryhma7.ULI_9000.controller;

import Ryhma7.ULI_9000.App;
import Ryhma7.ULI_9000.model.Storage;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewStorageDialogController {
	@FXML
	private TextField name;
	@FXML
	private TextField address;
	@FXML
	private TextField width;
	@FXML
	private TextField length;

	private Stage dialogStage;
	private Storage storage;
	private boolean isOkClicked = false;
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public void setStorage(Storage storage) {
		this.storage = storage;
		
		this.storage.setName(name.getText());
		this.storage.setAddress(address.getText());
		try {
			this.storage.setDimensions(Integer.parseInt(width.getText()), Integer.parseInt(length.getText()));
		}catch(NumberFormatException error) {
			System.out.print(error);
		}
		
	}
	
	public boolean getIsOkClicked() {
		return this.isOkClicked;
	}
	
	@FXML
	private void handleCreateNewStorage() {
		setStorage(storage);
		this.isOkClicked = true;
		this.dialogStage.close();
		System.out.println("Storage Created");
	}
	
	@FXML
	private void handleCancel() {
		dialogStage.close();
		System.out.println("Cancel");
	}
}
