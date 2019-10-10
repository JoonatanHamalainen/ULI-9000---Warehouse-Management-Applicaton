package Ryhma7.ULI_9000.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Shelf {

	private int shelfID, capacity, coordinateX, coordinateY, storageID;
	
	

	private ArrayList<Item> items;
	private Item item;
	private Point cellCoordinates;
	/**Empty Constructor
	 * 
	 */
	public Shelf() {
	}
	
	/**Constructor
	 * @param cellCoordinates
	 */
	public Shelf(Point cellCoordinates) {
		this.cellCoordinates = (Point) cellCoordinates;
	}
	
	/**Adds an Item to the Shelf
	 * @param item
	 */
	public void addItem(Item item) {
		this.item = item;
	}
	/**Removes the item from the shelf
	 * @return
	 */
	public boolean removeItem() {
		if(this.item != null) {
			this.item = null;
			return true;
		}
		return false;
	}
	
	/**Returns the item on the shelf
	 * @return
	 */
	public Item getItem() {
		return this.item;
	}
	
	/**Returns the shelf's coordinates as Point
	 * @return
	 */
	public Point getCellCoordinates(){
		return (Point) this.cellCoordinates;
	}
	
	/**
	 * @param n
	 */
	public void setCellCoordinates(Point n) {
		this.cellCoordinates = n;
	}
	/**
	 * @return
	 */
	public int getCoordinateX() {
		return coordinateX;
	}

	/**
	 * @param coordinateX
	 */
	public void setCoordinateX(int coordinateX) {
		this.coordinateX = coordinateX;
	}

	/**
	 * @return
	 */
	public int getCoordinateY() {
		return coordinateY;
	}

	/**
	 * @param coordinateY
	 */
	public void setCoordinateY(int coordinateY) {
		this.coordinateY = coordinateY;
	}

	/**
	 * @return
	 */
	public int getShelfID() {
		return shelfID;
	}

	/**
	 * @param shelfID
	 */
	public void setShelfID(int shelfID) {
		this.shelfID = shelfID;
	}

	/**
	 * @return
	 */
	public ArrayList<Item> getItems() {
		return items;
	}

	/**
	 * @param items
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