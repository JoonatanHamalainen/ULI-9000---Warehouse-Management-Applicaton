package Ryhma7.ULI_9000.controller;

import java.awt.Point;
import java.util.ArrayList;
import Ryhma7.ULI_9000.App;

import Ryhma7.ULI_9000.model.Shelf;
import Ryhma7.ULI_9000.model.Storage;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class StorageController {
	
	@FXML
	private TextField storageWidth;
	
	@FXML
	private TextField storageLength;
	
	private ArrayList<Point> selectedCells = new ArrayList<Point>();
	
	private App mainApp;
	
	private Storage storage;
	private Shelf shelf;
	// Varaston koko
	// poista hylly
	// muokkaa hyllyä. (lisää/poista ruutuja)
	
	public StorageController() {	
	}
	
	public void setMainApp(App mainApp) {
		this.mainApp = mainApp;
	}
	
	@FXML
	public void createStorage() {
		System.out.println("Storage created!");	
		System.out.println("Dimensions: " + storageWidth.getText() + "x" + storageLength.getText());
		//storage = new Storage(4,4);
	}
	
	@FXML
	public void handleCreateShelf() {
		Shelf shelf = new Shelf(this.selectedCells, "123");
		System.out.println(shelf.getCellCoordinates() + ", " + shelf.getID());
	}
	
	public void createShelf(ArrayList<Point> cellCoordinates) {
		
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
	
	public void cellSelected(Point coordinateXY) {
		if(this.selectedCells.contains(coordinateXY)) {
			this.selectedCells.remove(coordinateXY);
		}else{
			this.selectedCells.add(coordinateXY);
		}
		System.out.println(this.selectedCells.size());
		System.out.println(this.selectedCells);
	}
}
