package Ryhma7.ULI_9000.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Class for model Storage
 *
 */
public class Storage {
	
	private int storageID, width, length;
	private String name, address;
	private ArrayList<Shelf> shelves;
	private ArrayList<Item> items;

	public Storage () {
		this.shelves = new ArrayList<Shelf>();
		this.items = new ArrayList<Item>();
	}
	
	/**
	 * @param widthX
	 * @param lengthY
	 */
	public Storage(int widthX, int lengthY) {
		this.shelves = new ArrayList<Shelf>();
		this.items = new ArrayList<Item>();
		this.width = widthX;
		this.length = lengthY;		
	}
	
	/**Returns storage dimensions
	 * @return
	 */
	public List<Integer> getDimensions(){
		List<Integer> storageDimensions = new ArrayList<Integer>();
		storageDimensions.add(this.width);
		storageDimensions.add(this.length);
		return storageDimensions;
	}
	
	/** Adds a shelf to the storage
	 * @param shelf
	 */
	public void addShelf(Shelf shelf) {
		this.shelves.add(shelf);
	}
	
	/** Removes a shelf from the storage
	 * @param shelf
	 * @return
	 */
	public boolean removeShelf(Shelf shelf) {
		if(this.shelves.contains(shelf)) {
			this.shelves.remove(shelf);
			return true;
		}else {
			return false;
		}
		
	}
	
	/**Returns a shelf of given ID-attribute
	 * @param shelfID
	 * @return
	 */
	public Shelf getShelf(int shelfID) {
		for(Shelf shelf : this.shelves) {
			if(shelf.getShelfID() == shelfID) {
				return shelf;
			}
		}
		return null;
	}
	
	/**
	 * @return
	 */
	public ArrayList<Shelf> getShelves(){
		return this.shelves;
	}
	/**
	 * @param width
	 * @param length
	 */
	public void setDimensions(int width, int length) {
		this.width = width;
		this.length = length;
	}

	/**
	 * @return
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @param length
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * @param shelves
	 */
	public void setShelves(ArrayList<Shelf> shelves) {
		this.shelves = shelves;
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
	
	/**
	 * @param item
	 */
	public void addItemToStorage(Item item) {
		this.items.add(item);
	}
	
	/**
	 * @param item
	 */
	public void removeItemFromStorage(Item item) {
		items.remove(item);
	}
	/**
	 * 
	 * @return
	 */
	public int getStorageID() {
		return storageID;
	}
	/**
	 * 
	 * @param storageID
	 */
	public void setStorageID(int storageID) {
		this.storageID = storageID;
	}
}
