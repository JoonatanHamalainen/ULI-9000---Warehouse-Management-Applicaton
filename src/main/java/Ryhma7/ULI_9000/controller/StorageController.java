package Ryhma7.ULI_9000.controller;

import Ryhma7.ULI_9000.model.Shelf;
import Ryhma7.ULI_9000.model.Storage;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class StorageController {
	
	@FXML
	private TextField storageWidth;
	
	@FXML
	private TextField storageLength;
	
	Storage storage;
	Shelf shelf;
	// Varaston koko
	// poista hylly
	// muokkaa hyllyä. (lisää/poista ruutuja)
	
	@FXML
	public void createStorage() {
		System.out.println("Storage created!");
		System.out.println("Dimensions:" + this.storageWidth.getText() + "x" + this.storageLength.getText());
		//storage = new Storage(4,4);
	}
	
	@FXML
	public void createShelf() {
		System.out.println("Shelf Created!");
		//shelf = new Shelf();
	}
	
	/*public void changeStorageSize(int width, int length) {
		storage.setStorageSize(width, length);
	}*/
	@FXML
	public void removeShelf() {
		System.out.println("Shelf removed!");
	}
	
	public void addShelfCell() {
		
	}
	
	public void removeShelfCell() {
		
	}
}
