package Ryhma7.ULI_9000.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Class for model Shelf
 *
 */
public class Shelf {

	private int shelfID, capacity, coordinateX, coordinateY, storageID;
	
	

	private ArrayList<Item> items;
	private Item item = null;
	private Point cellCoordinates;
	/**Empty Constructor
	 * 
	 */
	public Shelf() {
	}
	
	/**Constructor
	 * @param cellCoordinates sets the cellCoordinates-attribute of the shelf
	 */
	public Shelf(Point cellCoordinates) {
		this.cellCoordinates = (Point) cellCoordinates;
	}
	
	/**Adds an Item to the Shelf
	 * @param item sets the Item-object to be added to item-attribute of the shelf
	 */
	public void addItem(Item item) {
		this.item = item;
	}
	/**Removes the item from the shelf
	 * @return true if the operation was successful or false if not
	 */
	public boolean removeItem() {
		if(this.item != null) {
			this.item = null;
			return true;
		}
		return false;
	}
	

	/**Returns the item on the shelf
	 * @return item returns the item-attribute of the shelf
	 */
	public Item getItem() {
		return this.item;
	}
	
	/**Returns the shelf's coordinates as Point
	 * @return returns the cellCoordinates-attribute of the shelf
	 */
	public Point getCellCoordinates(){
		return (Point) this.cellCoordinates;
	}
	
	/**
	 * @param n-Point sets the cellCoordinates-attribute of the shelf
	 */
	public void setCellCoordinates(Point n) {
		this.cellCoordinates = n;
	}
	/**
	 * @return returns the coordinateX-attribute of the shelf
	 */
	public int getCoordinateX() {
		return coordinateX;
	}

	/**
	 * @param coordinateX sets the coordinateX-attribute of the shelf
	 */
	public void setCoordinateX(int coordinateX) {
		this.coordinateX = coordinateX;
	}

	/**
	 * @return returns the coordinateY-attribute of the shelf
	 */
	public int getCoordinateY() {
		return coordinateY;
	}

	/**
	 * @param coordinateY sets the coordinateY-attribute of the shelf
	 */
	public void setCoordinateY(int coordinateY) {
		this.coordinateY = coordinateY;
	}

	/**
	 * @return return the shelfID-attribute of the shelf
	 */
	public int getShelfID() {
		return shelfID;
	}

	/**
	 * @param shelfID sets the shelfID-attribute of the shelf
	 */
	public void setShelfID(int shelfID) {
		this.shelfID = shelfID;
	}

	/**
	 * @return returns the item-attribute of the shelf
	 */
	public ArrayList<Item> getItems() {
		return items;
	}

	/**
	 * @param items sets the items-attribute of the shelf
	 */
	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
	
	public int getStorageID() {
		return storageID;
	}

	public void setStorageID(int storageID) {
		this.storageID = storageID;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
}