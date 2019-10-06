package Ryhma7.ULI_9000.controller;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;

import Ryhma7.ULI_9000.App;
import Ryhma7.ULI_9000.model.Shelf;
import Ryhma7.ULI_9000.model.Storage;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

public class StorageEditController {
	@FXML
	private TextField shelfID;
	@FXML
	private TextField shelfSize;
	@FXML
	private TextField shelfSpace;
	@FXML 
	private Label warning;
	
	private ArrayList<Point> selectedCells = new ArrayList<Point>();
	
	private App mainApp;
	private BorderPane page;
	private Storage storage;
	private Shelf shelf;
	private ArrayList<Shelf> shelves;
	private Shelf selectedShelf;
	
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
	
	public void setShelves(ArrayList<Shelf> shelves) {
		this.shelves = shelves;
	}
	
	@FXML
	public void handleNewShelf() {
		Shelf shelf = new Shelf(this.selectedCells);
		this.selectedCells.clear();
		this.displaySelectedShelf(shelf);
		this.selectedShelf = shelf;
		System.out.println("New shelf created");
	}
	
	@FXML
	public void handleRemoveShelf() {
		this.shelfID.setText("Bing");
		this.shelfSpace.setText("Bong");
		this.shelfSize.setText("Blip");
		System.out.println("Shelf removed");
		
		//TODO
	}
	
	@FXML
	public void handleEditShelf() {
		this.saveChanges(this.selectedShelf);
		System.out.println("Saved Changes");
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
			int gridColumns = this.storage.getDimensions().get(0);
			int gridRows = this.storage.getDimensions().get(1);
			double cellWallLength = calculateCellWallLength(gridColumns, gridRows);
			
			final GridPane grid = new GridPane();
			grid.getStyleClass().add("storage-grid");
			
			for (int i = 0;i<gridColumns;i++) {
				ColumnConstraints column = new ColumnConstraints(cellWallLength);
				grid.getColumnConstraints().add(column);
			}
			
			for (int i = 0; i<gridRows; i++) {
				RowConstraints row = new RowConstraints(cellWallLength);
				grid.getRowConstraints().add(row);
			}
			
			for (int i = 0; i<gridColumns;i++) {
				for(int j = 0; j<gridRows; j++) {					
					final Pane pane = new Pane();
	
					pane.getStyleClass().add("storage-grid-cell");
										
					pane.setOnMouseClicked(new EventHandler<MouseEvent>(){
						public void handle(MouseEvent event) {
							if(isShelf(new Point(grid.getColumnIndex(pane), grid.getRowIndex(pane)))) {
								System.out.println("works");
							}else if(pane.getStyleClass().contains("storage-grid-cell")) {
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
	
	private boolean isShelf(Point coordinateXY) {
		for(Shelf shelf : shelves) {
			if (shelf.getCellCoordinates().contains(coordinateXY)) {
				this.selectedShelf = shelf;
				this.displaySelectedShelf(shelf);
				return true;
			}
		}
		return false;
	}
	
	private void displaySelectedShelf(Shelf shelf) {
		this.shelfID.setText(String.valueOf(shelf.getID()));
		this.shelfSpace.setText(String.valueOf(shelf.getSize()));
		this.shelfSize.setText(String.valueOf(shelf.getSize()));		
	}
	
	private void saveChanges(Shelf shelf) {
		shelf.setID(Integer.parseInt(this.shelfID.getText()));
	}
	
	private double calculateCellWallLength(int columns, int rows) {
		double maxGridWidthPixels  = 550;
		double maxGridHeightPixels = 300;
		ArrayList<Double> maxCellWallLengthPx = new ArrayList<Double>();
		
		maxCellWallLengthPx.add(maxGridWidthPixels / columns);
		maxCellWallLengthPx.add(maxGridHeightPixels / rows);
		
		return Collections.min(maxCellWallLengthPx);
	}
}
