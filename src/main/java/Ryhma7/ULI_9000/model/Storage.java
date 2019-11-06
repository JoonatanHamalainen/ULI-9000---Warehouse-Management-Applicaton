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
	 * @param widthX is used to set the width attribute of the storage
	 * @param lengthY is used to set the length attribute of the storage
	 */
	public Storage(int widthX, int lengthY) {
		this.shelves = new ArrayList<Shelf>();
		this.items = new ArrayList<Item>();
		this.width = widthX;
		this.length = lengthY;		
	}
	
	/**Returns storage dimensions
	 * @return storageDimensions is a list of integers that contains the width and length attributes of the storage
	 */
	public List<Integer> getDimensions(){
		List<Integer> storageDimensions = new ArrayList<Integer>();
		storageDimensions.add(this.width);
		storageDimensions.add(this.length);
		return storageDimensions;
	}
	
	/** Adds a shelf to the storage
	 * @param shelf is the Shelf-object that will be added to the shelves-list of the storage
	 */
	public void addShelf(Shelf shelf) {
		this.shelves.add(shelf);
	}
	
	/** Removes a shelf from the storage
	 * @param shelf is the Shelf-object that will be removed from the shelves-list of the storage
	 * @return true if operatiion was successful false if not
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
	 * @param shelfID is the ID-attribute used to identify the correct shelf
	 * @return shelf with the given ID-attribute or null if there is no such shelf
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
	 * @return shelves-ArrayList which contains all the shelves in this storage
	 */
	public ArrayList<Shelf> getShelves(){
		return this.shelves;
	}
	/**
	 * @param width is used to set the width attribute of this storage
	 * @param length is used to set the width attribute of this storage
	 */
	public void setDimensions(int width, int length) {
		this.width = width;
		this.length = length;
	}

	/**
	 * @return address-String is the address-attribute of this storage
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address sets the address-attribute of this storage
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return name-String is the name-attribute of the storage
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name sets the name-attribute of this storage
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return width is the width-attribute of the storage
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width sets the width-attribute of the storage
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return length is the length-attribute of the storage
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @param length sets the length-attribute of the storage
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * @param shelves-ArrayList sets the shelves-attribute of the storage
	 */
	public void setShelves(ArrayList<Shelf> shelves) {
		this.shelves = shelves;
	}
	
	/**
	 * @return items-ArrayList is the items-attribute of the storage
	 */
	public ArrayList<Item> getItems() {
		return items;
	}

	/**
	 * @param items sets the items-attribute of the storage
	 */
	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
	
	/**
	 * @param item is the Item-object that will be added to the items-attribute of the storage
	 */
	public void addItemToStorage(Item item) {
		this.items.add(item);
	}
	
	/**
	 * @param item is Item-object that will be removed from the items-attribute of the storage
	 */
	public void removeItemFromStorage(Item item) {
		items.remove(item);
	}
	
	/**
	 * @return storageID is the storageIDattribute of the storage
	 */
	public int getStorageID() {
		return storageID;
	}
	
	/**
	 * @param storageID sets the storageID-attribute of the storage
	 */
	public void setStorageID(int storageID) {
		this.storageID = storageID;
	}
}
