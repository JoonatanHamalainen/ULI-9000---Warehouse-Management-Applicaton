package Ryhma7.ULI_9000.controller;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.sun.javafx.geom.Point2D;

import Ryhma7.ULI_9000.App;
import Ryhma7.ULI_9000.model.Item;
import Ryhma7.ULI_9000.model.Shelf;
import Ryhma7.ULI_9000.model.Storage;
import Ryhma7.ULI_9000.model.DatabaseConnection;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.util.Callback;
import net.bytebuddy.asm.Advice.This;
/**Controller for StorageLayout
*
*/
public class StorageController implements ControllerInterfaceView {

	DatabaseConnection database = new DatabaseConnection();
	
	/**Inner class which defines value of a Item-object shown inside a combobox
	 *
	 */
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
	
	/**Inner class which defines value of a shelf-object shown inside a combobox
	 *
	 */
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
	
	private ArrayList<Point> selectedCells;
	
	private App mainApp;
	private AnchorPane page;
	private Storage storage;
	private Shelf selectedShelf;
	private GridPane storageGrid;
	private boolean enableWalls;
	
	/** Empty Constructor
	 * 
	 */
	public StorageController() {
		this.enableWalls = false;
		this.selectedCells = new ArrayList<Point>();
	}
	
	/**Sets the mainApp for the controller
	 * @param mainApp is the main application
	 */
	public void setMainApp(App mainApp) {
		this.mainApp = mainApp;
	}
	
	/**sets the storage for the controller
	 * @param storage is the storage the controller is for
	 */
	public void setStorage(Storage storage) {
		this.storage = storage;

		this.storageItemList = FXCollections.observableArrayList(database.getItemsInStorage(this.storage));
		this.storageItemList.addListener(new ListChangeListener<Item>() {
			@Override
			public void onChanged(Change<? extends Item> arg0) {
				itemsInStorageBox.setItems(storageItemList);
			}
		});
		this.itemsInStorageBox.setCellFactory(new Callback<ListView<Item>, ListCell<Item>>(){		
			public ListCell<Item> call(ListView<Item> list) {
				return new ItemCellList();
			}
		});
		//Eventhandleri, joka ajetaan Comboboxin arvon muututtua
		this.itemsInStorageBox.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(itemsInStorageBox.getValue() != null) {
					//tarkistetaan onko tavara hyllyllä
					if(getShelfByID(itemsInStorageBox.getValue().getShelfID()) != null){
						shelvesInStorageBox.setValue(getShelfByID(itemsInStorageBox.getValue().getShelfID()));
					}else if(shelvesInStorageBox.getValue() != null && shelvesInStorageBox.getValue().getItem() != null){
						shelvesInStorageBox.setValue(null);
						containedItem.setText("");
					}else {
						containedItem.setText("");
					}
					
				}
			}
			
		});
			
		this.itemsInStorageBox.setItems(this.storageItemList);
		this.itemsInStorageBox.setButtonCell(new ItemCellList());
		
		
		this.storageShelfList = FXCollections.observableList(database.getShelvesInStorage(this.storage));
		if(this.storageShelfList != null && this.storageItemList != null) {
			for(Shelf shelf: this.storageShelfList) {
				for(Item item: this.storageItemList) {
					if(item.getShelfID() == shelf.getShelfID()) {
						shelf.addItem(item);
					}
				}					
			}
		}
		
		this.storageShelfList.addListener(new ListChangeListener<Shelf>(){
			@Override
			public void onChanged(Change<? extends Shelf> arg0) {
				shelvesInStorageBox.setItems(storageShelfList);					
			}				
		});

		this.shelvesInStorageBox.setCellFactory(new Callback<ListView<Shelf>, ListCell<Shelf>>(){	
			public ListCell<Shelf> call(ListView<Shelf> list) {
				return new ShelfCellList();
			}
		});

		this.shelvesInStorageBox.setItems(this.storageShelfList);
		this.shelvesInStorageBox.setButtonCell(new ShelfCellList());
		this.shelvesInStorageBox.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if(shelvesInStorageBox.getValue() != null) {
					if(shelvesInStorageBox.getValue().getItem() != null) {						
						Item tempItem = getItemByID(shelvesInStorageBox.getValue().getItem().getItemID());
						containedItem.setText(tempItem.getName());
						itemsInStorageBox.setValue(tempItem);
					}else if(itemsInStorageBox.getValue() != null && getShelfByID(itemsInStorageBox.getValue().getShelfID()) != null){
						itemsInStorageBox.setValue(null);
						containedItem.setText("");
					}else {
						containedItem.setText("");
					}
				}
			}
		});
	}
	
	private Item getItemByID(int itemID) {
		if(itemsInStorageBox.getChildrenUnmodifiable() != null) {
			for(Item item:itemsInStorageBox.getItems()) {
				if(item.getItemID() == itemID) {
					return item;
				}
			}
		}
		return null;
	}
	
	private Shelf getShelfByID(int shelfID) {
		if(shelvesInStorageBox.getChildrenUnmodifiable() != null) {
			for(Shelf shelf:shelvesInStorageBox.getItems()) {
				if(shelf.getShelfID() == shelfID) {
					return shelf;
				};
			}
		}
		return null;
	}
	
	/**sets the page for the controller
	 * @param page is the current page
	 */
	public void setPane(AnchorPane page) {
		this.page = page;
	}
	
	/**Handler function for the userinterface 
	 * 
	 */
	@FXML
	private void handleEnableWalls() {
		if(this.enableWalls) {
			this.enableWalls = false;
		}else {
			this.enableWalls = true;
		}
	}
	
	/**Handler function for the userinterface 
	 * 
	 */
	@FXML
	private void handleSaveLayoutChanges() {
		
	}
	
	/**Handler function for the userinterface 
	 * 
	 */
	@FXML
	public void handleAddShelf() {
		for(Point cellCoordinate : this.selectedCells) {
			updateCellColor(cellCoordinate);
			Shelf tempShelf = new Shelf(cellCoordinate);
			tempShelf.setStorageID(this.storage.getStorageID());
			database.addShelf(tempShelf);
			this.storage.getShelves().add(database.getShelf(tempShelf.getCellCoordinates(), tempShelf.getStorageID()));
			this.storageShelfList.add(tempShelf);
		}
		this.selectedCells.clear();
		System.out.println("Storage contains: " + this.storage.getShelves().size() + " shelves");
	}
	
	/**Handler function for the userinterface 
	 * 
	 */
	@FXML
	public void handleRemoveShelf() {
		System.out.println("Shelf removed");
		if(this.shelvesInStorageBox.getItems() != null) {
			Shelf tempShelf = (Shelf) this.shelvesInStorageBox.getValue();
			database.deleteShelf(tempShelf);
			Node shelfTile = getNode(tempShelf.getCellCoordinates());
			if(shelfTile != null) {
				shelfTile.getStyleClass().clear();
				shelfTile.getStyleClass().add("storage-grid-cell");
			}
			this.storageShelfList.remove(tempShelf);
			this.storage.removeShelf(tempShelf);
		}
		System.out.println("Shelf removed");		
	}
	
	/**Handler function for the userinterface 
	 * 
	 */
	@FXML
	public void handleAddItemToShelf() {
		if(this.itemsInStorageBox.getValue() != null && this.shelvesInStorageBox.getValue() != null) {
			Item tempItem = (Item) this.itemsInStorageBox.getValue();
			Shelf tempShelf = (Shelf) this.shelvesInStorageBox.getValue();
			database.addItemToShelf(tempItem, tempShelf);
			tempShelf.addItem(tempItem);
			tempItem.setShelfID(tempShelf.getShelfID());
			System.out.println(tempShelf.getItem().getName());
			this.containedItem.setText(tempItem.getName());
			updateCellColor(tempShelf.getCellCoordinates());
		}else {
			System.out.println(this.itemsInStorageBox.getValue());
			System.out.println(this.itemsInStorageBox.getChildrenUnmodifiable());
			System.out.println(this.shelvesInStorageBox.getValue());
			System.out.println(this.shelvesInStorageBox.getChildrenUnmodifiable());
			System.out.println("Select an Item and a Shelf");
		}
	}
	
	/**Handler function for the userinterface 
	 * 
	 */
	@FXML
	public void handleRemoveItemFromShelf() {
		if(this.shelvesInStorageBox.getValue() != null && this.shelvesInStorageBox.getValue().getItem() != null) {
			Shelf tempShelf = this.shelvesInStorageBox.getValue();
			Item tempItem = this.shelvesInStorageBox.getValue().getItem();
			database.deleteItemFromShelf(tempItem);
			System.out.println(tempShelf.getItem());
			tempItem.removeFromShelf();
			tempShelf.removeItem();
			this.containedItem.setText("");
			updateCellColor(tempShelf.getCellCoordinates());
		}
		System.out.println("Remove");
	}
	
	/**Handler function for the userinterface 
	 * 
	 */
	@FXML
	public void handleNewItem() {
		Item tempItem = new Item();
		tempItem.setStorageID(this.storage.getStorageID());
		boolean isOkClicked = mainApp.showNewItemDialog(tempItem);
		if(isOkClicked) {
			this.storage.addItemToStorage(tempItem);
			database.addItem(tempItem);
			this.storageItemList.add(tempItem);
			System.out.println(this.storage.getItems().get(0).getName());
			System.out.println("New Item Created!");
		}
	}
	
	/**Handler function for the userinterface 
	 * 
	 */
	@FXML
	public void handleRemoveItemFromStrorage() {
		if(this.itemsInStorageBox.getValue() != null) {
			Item tempItem = (Item) this.itemsInStorageBox.getValue();
			this.storage.removeItemFromStorage(tempItem);
			database.deleteItem(tempItem);
			this.storageItemList.remove(tempItem);
		}else {
			System.out.println("No item selected");
		}
	}
	
	/**Handler function for the userinterface 
	 * 
	 */
	@FXML
	public void handleEditShelf() {
		this.saveChanges(this.selectedShelf);
		System.out.println("Saved Changes");
	}
	
	/**Unimplemented handler function for the userinterface 
	 * 
	 */
	@FXML
	public void handleSelectShelf() {
		//TODO
	}
	
	/**Manages add and remove feature for selectedCells list.
	 *Used primarily when the user selects / deselects cells in the storage layout 
	 * @param coordinateXY is the Point that has been clicked on
	 */
	public void cellSelected(Point coordinateXY) {
		if(this.selectedCells.contains(coordinateXY)) {
			this.selectedCells.remove(coordinateXY);
		}else{
			this.selectedCells.add(coordinateXY);
		}
		//System.out.println(this.selectedCells.size());
		//System.out.println(this.selectedCells);
	}

	private void updateCellColor(Point point) {
		Node cell = getNode(point);
		if(cell != null) {
			cell.getStyleClass().clear();
			cell.getStyleClass().add("storage-grid-cell-shelf-fifty");
		}
}
	/**Retrieves a node in specified coordinates from the storageGrid
	 * 
	 * @param point contains the coordinates of the node in storageGrid gridPane
	 * @return a node from given coordinates if it exists
	 */
	private Node getNode(Point point) {
		for(Node node : this.storageGrid.getChildren()) {
			if(this.storageGrid.getColumnIndex(node) == point.getX() && this.storageGrid.getRowIndex(node) == point.getY()) {
				return node;
			}
		}
		return null;
	}
	/**Loads the Storage to be displayed in the Userinterface and loads all the
	 * associated shelves and intems to e displayed aswell.
	 * Sets on click functionality of grid cells
	 * 
	 */
	public void loadStorageLayout() {
		if(this.storage.getDimensions().get(0) != null && this.storage.getDimensions().get(1) != null) {
			ArrayList<Point> shelves = new ArrayList<Point>();
			ArrayList<Point> walls = new ArrayList<Point>();
			//Haetaan varastossa olevien hyllyjen koordinaatit shelves-listaan
			for (Shelf shelf: database.getShelvesInStorage(this.storage)) {
				shelves.add(shelf.getCellCoordinates());
			}
			for (Point point: database.getWallsInStorage(this.storage)) {
				walls.add(point);
			}
			int gridColumns = this.storage.getDimensions().get(0);
			int gridRows = this.storage.getDimensions().get(1);
			
			double cellWallLength = calculateCellWallLength(gridColumns, gridRows);
			//Create the gridpane, which represets the storage(number of columns represents width, and rows - length)
			this.storageGrid = new GridPane();
			this.storageGrid.getStyleClass().add("storage-grid");
			//create the columns
			for (int i = 0;i<gridColumns;i++) {
				ColumnConstraints column = new ColumnConstraints(cellWallLength);
				this.storageGrid.getColumnConstraints().add(column);
			}
			//create the rows
			for (int i = 0; i<gridRows; i++) {
				RowConstraints row = new RowConstraints(cellWallLength);
				this.storageGrid.getRowConstraints().add(row);
			}
			
			//add panes to the gridcells
			for (int i = 0; i<gridColumns;i++) {
				for(int j = 0; j<gridRows; j++) {					
					final Pane pane = new Pane();
					final Point coordinate = new Point(i,j);
					//Tarkistetaan sijaitseeko kyseisessä solulla hylly. Jos sijaitsee, tehdään hylly merkintä
					if (walls.contains(coordinate)) {
						pane.getStyleClass().add("storage-grid-cell-wall");
					}
					else if(shelves.contains(coordinate)) {
						pane.getStyleClass().add(checkAmount(coordinate));
					}else {
						pane.getStyleClass().add("storage-grid-cell");
					}
					//add on click funtionality to each pane
					pane.setOnMouseClicked(new EventHandler<MouseEvent>(){
						public void handle(MouseEvent event) {
							paneClicked(storageGrid, pane, coordinate);
						}						
					});				
					this.storageGrid.add(pane, i, j);
				}
			}
			BorderPane tempPane = (BorderPane) page.getChildren().get(0);
			tempPane.setCenter(this.storageGrid);
		}
	}
	
	/**Updates the selected item on the selected shelf
	 * @param shelf is the shelf to be displayed
	 */
	private void displaySelectedShelf(Shelf shelf) {
		this.containedItem.setText(shelf.getItem().getName());		
	}
	/**Saves changed made to contents of a shelf
	 * @param shelf is the current shelf
	 */
	private void saveChanges(Shelf shelf) {
		shelf.setShelfID(Integer.parseInt(this.shelfID.getText()));
		
	}
	
	/**Calculates the maximum cell wall length to be used in storage grid
	 * @param columns
	 * @param rows
	 * @return double is the maximum wall length that fits the view
	 */
	private double calculateCellWallLength(int columns, int rows) {
		
		VBox vBox = (VBox)mainApp.getRootLayout().getChildren().get(0);
		HBox hBox = (HBox) page.getChildren().get(1);

		double maxGridWidth  = (mainApp.getRootLayout().getWidth() - vBox.getWidth());
		double maxGridHeight = mainApp.getRootLayout().getHeight() - hBox.getHeight()-10;

		ArrayList<Double> maxCellWallLengthPx = new ArrayList<Double>();
		
		maxCellWallLengthPx.add(maxGridWidth / columns);
		maxCellWallLengthPx.add(maxGridHeight / rows);
		
		return Collections.min(maxCellWallLengthPx);
	}
	
	/**
	 * 
	 * @param storageGrid
	 * @param pane clicked node
	 * @param coordinates coordinates of the clicked node
	 */
	private void paneClicked(GridPane storageGrid, Pane pane, Point coordinates) {
		if(pane.getStyleClass().contains("storage-grid-cell-shelf") || pane.getStyleClass().contains("storage-grid-cell-shelf-seventyfive") || pane.getStyleClass().contains("storage-grid-cell-shelf-fifty") || pane.getStyleClass().contains("storage-grid-cell-shelf-twentyfive") || pane.getStyleClass().contains("storage-grid-cell-shelf-zero")) {
			for(Shelf shelf:storageShelfList) {
				if(shelf.getCellCoordinates().equals(coordinates)) {
					shelvesInStorageBox.setValue(getShelfByID(shelf.getShelfID()));
					javafx.geometry.Point2D point = pane.localToScreen(0.0,0.0);
					this.mainApp.showInfoBox(shelf, point.getX() + pane.getWidth(), point.getY());
				};
			};
		}else if(this.enableWalls && (pane.getStyleClass().contains("storage-grid-cell")||pane.getStyleClass().contains("storage-grid-cell-wall"))){
			this.toggleCellToWall(pane, coordinates);
		//or if the cell is not a shelf and is not selected
		}else if(pane.getStyleClass().contains("storage-grid-cell")) {
			pane.getStyleClass().remove("storage-grid-cell");
			pane.getStyleClass().add("storage-grid-cell-selected");
			Point coordinateXY = new Point(GridPane.getColumnIndex(pane), GridPane.getRowIndex(pane));
			cellSelected(coordinateXY);
		//or if the cell is not a shelf but is selected
		}else if(pane.getStyleClass().contains("storage-grid-cell-selected")) {
			pane.getStyleClass().remove("storage-grid-cell-selected");
			pane.getStyleClass().add("storage-grid-cell");
			cellSelected(new Point(GridPane.getColumnIndex(pane), GridPane.getRowIndex(pane)));
		}
	}
	
	private String checkAmount(Point point) {
		int pX = point.x;
		int pY = point.y;
		for (Shelf shelf: database.getShelvesInStorage(this.storage)) {
			int x = shelf.getCoordinateX();
			int y = shelf.getCoordinateY();
			if(x == pX && y == pY) {
				if (database.getItemsInShelf(shelf) != null) {
					double highestAmount = (double) database.getHighestAmount(database.getItemsInShelf(shelf).get(0).getItemID());
					double amount = (double) database.getItemsInShelf(shelf).get(0).getAmount();
					
					if(amount != 0) {
						if((amount/highestAmount) > 0.75) {
							return "storage-grid-cell-shelf";
						} else if((amount/highestAmount) > 0.5) {
							return "storage-grid-cell-shelf-seventyfive";
						} else if((amount/highestAmount) > 0.25) {
							return "storage-grid-cell-shelf-fifty";
						} else if((amount/highestAmount) > 0) {
							return "storage-grid-cell-shelf-twentyfive";
						}
					} else {
						return "storage-grid-cell-shelf-zero";
					}
				}
				return "storage-grid-cell-shelf-zero";
			}
		}
		return null;
	}
	/**This function handles the creation of wall cells in storageGrid
	 * 
	 * @param pane is a pane in a grid-cell of storageGrid (GridPane)
	 * @param coordinates is the pane's location in the aforementioned storageGrid
	 */
	private void toggleCellToWall(Pane pane, Point coordinates) {
		if(pane.getStyleClass().contains("storage-grid-cell")) {
			pane.getStyleClass().clear();
			pane.getStyleClass().add("storage-grid-cell-wall");
			database.addWall(coordinates, this.storage);
			//this.selectedCells.add(coordinates);
		}else {
			pane.getStyleClass().clear();
			pane.getStyleClass().add("storage-grid-cell");
			database.deleteWall(coordinates, this.storage);
			//this.selectedCells.remove(coordinates);
		}
	}
}

