package Ryhma7.ULI_9000.controller;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;

import Ryhma7.ULI_9000.App;
import Ryhma7.ULI_9000.model.Item;
import Ryhma7.ULI_9000.model.Shelf;
import Ryhma7.ULI_9000.model.Storage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.util.Callback;
import net.bytebuddy.asm.Advice.This;

public class StorageEditController implements ControllerInterfaceView {
	
	private static class ItemCellList extends ListCell<Item> {		
		@Override
		protected void updateItem(Item item, boolean empty) {
			super.updateItem(item, empty);
			if(item == null || empty) {				
			}else {
				setText(item.getName());
			}
		}
	}
	
	private static class ShelfCellList extends ListCell<Shelf>{
		@Override
		protected void updateItem (Shelf shelf, boolean empty) {
			super.updateItem(shelf, empty);
			if (shelf == null || empty) {
				
			}else {
				setText(Integer.toString(shelf.getShelfID()));
			}
		}
	}
	private ObservableList<Item> storageItemList;
	private ObservableList<Shelf> storageShelfList;
	@FXML
	private TextField shelfID;
	@FXML
	private TextField shelfSize;
	@FXML
	private TextField shelfSpace;
	@FXML 
	private Label warning;
	@FXML
	private TextField containedItem;
	@FXML
	private ComboBox<Item> itemsInStorageBox;
	@FXML
	private ComboBox<Shelf> shelvesInStorageBox;
	
	//väliaikainen muuttuja:
	private int shelfIdCounter = 0;
	
	private ArrayList<Point> selectedCells = new ArrayList<Point>();
	
	private App mainApp;
	private AnchorPane page;
	private Storage storage;
	private Shelf selectedShelf;
	private GridPane storageGrid;
	
	public StorageEditController() {	
	}
	
	public void setMainApp(App mainApp) {
		this.mainApp = mainApp;
	}
	
	public void setStorage(Storage storage) {
		this.storage = storage;
		if(this.storage.getItems().size() != 0) {	
			this.storageItemList = FXCollections.observableArrayList(this.storage.getItems());
			this.itemsInStorageBox.setCellFactory(new Callback<ListView<Item>, ListCell<Item>>(){
				
				public ListCell<Item> call(ListView<Item> list) {
					return new ItemCellList();
				}
				
			});
			
			this.itemsInStorageBox.setItems(this.storageItemList);
			this.itemsInStorageBox.setButtonCell(new ItemCellList());
		}
		if(this.storage.getShelves().size() != 0) {
			this.storageShelfList = FXCollections.observableArrayList(this.storage.getShelves());
			System.out.println(this.shelvesInStorageBox);
			this.shelvesInStorageBox.setCellFactory(new Callback<ListView<Shelf>, ListCell<Shelf>>(){
				
				public ListCell<Shelf> call(ListView<Shelf> list) {
					return new ShelfCellList();
				}
				
			});
			this.shelvesInStorageBox.setItems(this.storageShelfList);
			this.shelvesInStorageBox.setButtonCell(new ShelfCellList());
		}
	}
	
	public void setPane(AnchorPane page) {
		this.page = page;
	}
	
	@FXML
	public void handleAddShelf() {
		for(Point cellCoordinate : this.selectedCells) {
			Shelf tempShelf = new Shelf(cellCoordinate);
			tempShelf.setShelfID(this.shelfIdCounter);
			this.shelfIdCounter++;
			System.out.println(tempShelf.getShelfID());
			this.storage.getShelves().add(tempShelf);
		}
		this.selectedCells.clear();
		System.out.println("Storage contains: " + this.storage.getShelves().size() + " shelves");
	}
	
	@FXML
	public void handleRemoveShelf() {
		System.out.println("Shelf removed");
		if(this.shelvesInStorageBox.getItems() != null) {
			Shelf tempShelf = (Shelf) this.shelvesInStorageBox.getValue();
			int column = (int) tempShelf.getCellCoordinates().getX();
			int row = (int) tempShelf.getCellCoordinates().getY();
			for (Node node : this.storageGrid.getChildren()) {
				if(this.storageGrid.getColumnIndex(node) == column && this.storageGrid.getRowIndex(node) == row) {
					node.getStyleClass().clear();;
					node.getStyleClass().add("storage-grid-cell");
				}
			}
			this.storage.removeShelf(tempShelf);
		}
		System.out.println("Shelf removed");		
	}
	
	@FXML
	public void handleNewItem() {
		Item tempItem = new Item();
		boolean isOkClicked = mainApp.showNewItemDialog(tempItem);
		if(isOkClicked) {
			this.storage.addItemToStorage(tempItem);
			System.out.println(this.storage.getItems().get(0).getName());
			System.out.println("New Item Created!");
		}
	}
	
	@FXML
	public void handleRemoveItemFromStrorage() {
		if(this.itemsInStorageBox.getValue() != null) {
			Item tempItem = (Item) this.itemsInStorageBox.getValue();
			this.storage.removeItemFromStorage(tempItem);
		}else {
			System.out.println("No item selected");
		}
	}
	
	@FXML
	public void handleEditShelf() {
		this.saveChanges(this.selectedShelf);
		System.out.println("Saved Changes");
	}
	
	@FXML
	public void handleSelectShelf() {
		
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
			ArrayList<Point> shelves = new ArrayList<Point>();
			for (Shelf shelf: this.storage.getShelves()) {
				shelves.add(shelf.getCellCoordinates());
			}
			int gridColumns = this.storage.getDimensions().get(0);
			int gridRows = this.storage.getDimensions().get(1);
			double cellWallLength = calculateCellWallLength(gridColumns, gridRows);
			//Create the gridpane, which represets the storage(number of columns represents width, and rows - length)
			this.storageGrid = new GridPane();
			storageGrid.getStyleClass().add("storage-grid");
			//create the columns
			for (int i = 0;i<gridColumns;i++) {
				ColumnConstraints column = new ColumnConstraints(cellWallLength);
				storageGrid.getColumnConstraints().add(column);
			}
			//create the rows
			for (int i = 0; i<gridRows; i++) {
				RowConstraints row = new RowConstraints(cellWallLength);
				storageGrid.getRowConstraints().add(row);
			}
			//add panes to the gridcells
			for (int i = 0; i<gridColumns;i++) {
				for(int j = 0; j<gridRows; j++) {					
					final Pane pane = new Pane();
					Point point = new Point(i,j);
					if(shelves.contains(point)) {
						pane.getStyleClass().add("storage-grid-cell-shelf");
					}else {
						pane.getStyleClass().add("storage-grid-cell");
					}
					//pane.getStyleClass().add("storage-grid-cell");
					//add on click funtionality to each pane
					pane.setOnMouseClicked(new EventHandler<MouseEvent>(){
						public void handle(MouseEvent event) {
							//if the clicked cell is a cell
							if(pane.getStyleClass().contains("storage-grid-cell-shelf")) {
								
								System.out.println("works");
										
							//or if the cell is not a shelf and is not selected
							}else if(pane.getStyleClass().contains("storage-grid-cell")) {
								pane.getStyleClass().remove("storage-grid-cell");
								pane.getStyleClass().add("storage-grid-cell-selected");
								Point coordinateXY = new Point(storageGrid.getColumnIndex(pane), storageGrid.getRowIndex(pane));
								cellSelected(coordinateXY);
							//or if the cell is not a shelf but is selected
							}else if(pane.getStyleClass().contains("storage-grid-cell-selected")) {
								pane.getStyleClass().remove("storage-grid-cell-selected");
								pane.getStyleClass().add("storage-grid-cell");
								cellSelected(new Point(storageGrid.getColumnIndex(pane), storageGrid.getRowIndex(pane)));
							}
						}						
					});
					
					storageGrid.add(pane, i, j);
				}
			}
			BorderPane tempPane = (BorderPane) page.getChildren().get(0);
			tempPane.setTop(storageGrid);
		}
	}
	/*
	 * isShelf-funtiota käytetään tarkistamaan on kyseisessä solussa hylly
	 * palauttaa true, jos solussa on hylly, ja false, jos ei.
	 */
	private boolean isShelf(Point coordinateXY) {
		if(this.storage.getShelves() != null) {
			for(Shelf shelf : this.storage.getShelves()) {
				if (shelf.getCellCoordinates() == coordinateXY) {
					this.selectedShelf = shelf;
					this.displaySelectedShelf(shelf);
					return true;
				}
			}
		}
		return false;
	}
	/*
	 * displaySelectedShelf-funktio päivittää valittuna olevan hyllyn tiedot näkymään
	 */
	private void displaySelectedShelf(Shelf shelf) {
		this.containedItem.setText(shelf.getItem().getName());		
	}
	/*
	 * saveChanges-funktio tallentaa hyllyyn tehdyt muutokset
	 */
	private void saveChanges(Shelf shelf) {
		shelf.setShelfID(Integer.parseInt(this.shelfID.getText()));
	}
	
	private double calculateCellWallLength(int columns, int rows) {
		double maxGridWidthPixels  = 790;
		double maxGridHeightPixels = 370;
		ArrayList<Double> maxCellWallLengthPx = new ArrayList<Double>();
		
		maxCellWallLengthPx.add(maxGridWidthPixels / columns);
		maxCellWallLengthPx.add(maxGridHeightPixels / rows);
		
		return Collections.min(maxCellWallLengthPx);
	}
}

