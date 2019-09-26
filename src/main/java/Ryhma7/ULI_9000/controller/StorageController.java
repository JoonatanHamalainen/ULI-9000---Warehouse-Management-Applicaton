package Ryhma7.ULI_9000.controller;

import java.awt.Point;
import java.util.ArrayList;
import Ryhma7.ULI_9000.App;

import Ryhma7.ULI_9000.model.Shelf;
import Ryhma7.ULI_9000.model.Storage;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

public class StorageController {
	
	@FXML
	private TextField storageWidth;
	
	@FXML
	private TextField storageLength;
	
	private ArrayList<Point> selectedCells = new ArrayList<Point>();
	
	private App mainApp;
	private BorderPane page;
	private Storage storage;
	private Shelf shelf;
	
	public StorageController() {	
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
	public void handleCreateStorage() {
		try {
			this.storage.setDimensions(Integer.parseInt(storageWidth.getText()), Integer.parseInt(storageLength.getText()));
			loadStorageLayout();
		}catch(NumberFormatException e) {
			
		}
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
	
	public void loadStorageLayout() {
		if(this.storage.getDimensions().get(0) != null && this.storage.getDimensions().get(1) != null) {
			int gridColums = this.storage.getDimensions().get(0);
			int gridRows = this.storage.getDimensions().get(1);
			
			final GridPane grid = new GridPane();
			grid.getStyleClass().add("storage-grid");
			
			for (int i = 0;i<gridColums;i++) {
				ColumnConstraints column = new ColumnConstraints(30);
				grid.getColumnConstraints().add(column);
			}
			
			for (int i = 0; i<gridRows; i++) {
				RowConstraints row = new RowConstraints(30);
				grid.getRowConstraints().add(row);
			}
			
			for (int i = 0; i<gridColums;i++) {
				for(int j = 0; j<gridRows; j++) {					
					final Pane pane = new Pane();
	
					pane.getStyleClass().add("storage-grid-cell");
										
					pane.setOnMouseClicked(new EventHandler<MouseEvent>(){
						public void handle(MouseEvent event) {
							if(pane.getStyleClass().contains("storage-grid-cell")) {
								pane.getStyleClass().remove("storage-grid-cell");
								pane.getStyleClass().add("storage-grid-cell-selected");
								Point coordinateXY = new Point(grid.getColumnIndex(pane), grid.getRowIndex(pane));
								cellSelected(coordinateXY);
							}else if(pane.getStyleClass().contains("storage-grid-cell-selected")) {
								pane.getStyleClass().remove("storage-grid-cell-selected");
								pane.getStyleClass().add("storage-grid-cell");
								cellSelected(new Point(grid.getColumnIndex(pane), grid.getRowIndex(pane)));
							}
						}						
					});
					
					grid.add(pane, i, j);
				}
			}
			page.setCenter(grid);
		}
	}
}
