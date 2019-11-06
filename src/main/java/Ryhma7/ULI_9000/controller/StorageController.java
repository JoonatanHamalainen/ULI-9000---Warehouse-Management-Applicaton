package Ryhma7.ULI_9000.controller;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;

import Ryhma7.ULI_9000.App;
import Ryhma7.ULI_9000.model.Item;
import Ryhma7.ULI_9000.model.Shelf;
import Ryhma7.ULI_9000.model.Storage;
import Ryhma7.ULI_9000.model.DatabaseConnection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
	
	private ArrayList<Point> selectedCells = new ArrayList<Point>();
	
	private App mainApp;
	private AnchorPane page;
	private Storage storage;
	private Shelf selectedShelf;
	private GridPane storageGrid;
	//private Popup infoBox;
	
	/** Empty Constructor
	 * 
	 */
	public StorageController() {	
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
			this.shelvesInStorageBox.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					if(shelvesInStorageBox.getValue().getItem() != null && shelvesInStorageBox.getValue() != null) {
						containedItem.setText(shelvesInStorageBox.getValue().getItem().getName());
					}
				}
			});
		}
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
	public void handleAddShelf() {
		for(Point cellCoordinate : this.selectedCells) {
			updateCellColor(cellCoordinate);
			Shelf tempShelf = new Shelf(cellCoordinate);
			tempShelf.setStorageID(this.storage.getStorageID());
			database.addShelf(tempShelf);
			this.storage.getShelves().add(database.getShelf(tempShelf.getCellCoordinates(), tempShelf.getStorageID()));
			
		}
		this.selectedCells.clear();
		System.out.println("Storage contains: " + this.storage.getShelves().size() + " shelves");
	}
	
	private void updateCellColor(Point point) {
			Node cell = getNode(point);
			if(cell != null) {
				cell.getStyleClass().clear();
				cell.getStyleClass().add("storage-grid-cell-shelf");
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
	
	/**Handler function for the userinterface 
	 * 
	 */
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
	
	/**Handler function for the userinterface 
	 * 
	 */
	@FXML
	public void handleAddItemToShelf() {
		if(this.itemsInStorageBox.getValue() != null && this.shelvesInStorageBox.getValue() != null) {
			Item tempItem = (Item) this.itemsInStorageBox.getValue();
			Shelf tempShelf = (Shelf) this.shelvesInStorageBox.getValue();
			tempShelf.addItem(tempItem);
			System.out.println(tempShelf.getItem().getName());
		}else {
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
			tempShelf.removeItem();
			tempShelf.getItem();
			System.out.println(tempShelf.getItem());
		}
		System.out.println("Remove");
	}
	
	/**Handler function for the userinterface 
	 * 
	 */
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
	
	/**Handler function for the userinterface 
	 * 
	 */
	@FXML
	public void handleRemoveItemFromStrorage() {
		if(this.itemsInStorageBox.getValue() != null) {
			Item tempItem = (Item) this.itemsInStorageBox.getValue();
			this.storage.removeItemFromStorage(tempItem);
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
	
	/**Loads the Storage to be displayed in the Userinterface and loads all the
	 * associated shelves and intems to e displayed aswell.
	 * Sets on click functionality of grid cells
	 * 
	 */
	public void loadStorageLayout() {
		if(this.storage.getDimensions().get(0) != null && this.storage.getDimensions().get(1) != null) {
			ArrayList<Point> shelves = new ArrayList<Point>();
			//Haetaan varastossa olevien hyllyjen koordinaatit shelves-listaan
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
					final Point coordinate = new Point(i,j);
					//Tarkistetaan sijaitseeko kyseisessä solulla hylly. Jos sijaitsee, tehdään hylly merkintä
					if(shelves.contains(coordinate)) {
						pane.getStyleClass().add("storage-grid-cell-shelf");
					}else {
						pane.getStyleClass().add("storage-grid-cell");
					}
					//add on click funtionality to each pane
					pane.setOnMouseClicked(new EventHandler<MouseEvent>(){
						public void handle(MouseEvent event) {
							paneClicked(storageGrid, pane, coordinate);
						}						
					});				
					storageGrid.add(pane, i, j);
				}
			}
			BorderPane tempPane = (BorderPane) page.getChildren().get(0);
			tempPane.setTop(storageGrid);
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
		if(pane.getStyleClass().contains("storage-grid-cell-shelf")) {
			for(Shelf shelf:storage.getShelves()) {
				if(shelf.getCellCoordinates().equals(coordinates)) {
					System.out.println("test");
					Popup infoBox = new Popup();
					Label test = new Label("test");
					test.getStyleClass().add("info-box");
					infoBox.getContent().add(test);
					test.setMinWidth(60);
					test.setMinHeight(60);
					
					infoBox.show(pane, pane.getLayoutX(), pane.getLayoutY());
					System.out.println(infoBox.getOwnerWindow());
				};
			};
					
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
	
	/**Manages add and remove feature for selectedCells list.
	 *Used primarily when the user selects / deselects cells in the storage layout 
	 * @param coordinateXY is the Point that has been clicked on
	 */
	private void cellSelected(Point coordinateXY) {
		if(this.selectedCells.contains(coordinateXY)) {
			this.selectedCells.remove(coordinateXY);
		}else{
			this.selectedCells.add(coordinateXY);
		}
		System.out.println(this.selectedCells.size());
		System.out.println(this.selectedCells);
	}

}

