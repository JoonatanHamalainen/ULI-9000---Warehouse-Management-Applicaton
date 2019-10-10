package Ryhma7.ULI_9000.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Shelf {
	
	private int shelfID, capacity, coordinateX, coordinateY, storageID;
	
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

	private ArrayList<Item> items;
	
	private Item item;
	private Point cellCoordinates;

	//Kopioidaan konstruktorin parametrinä saatu lista solukoordinaateista ja ID
	public Shelf() {
	}
	
	public Shelf(Point cellCoordinates) {
		this.cellCoordinates = (Point) cellCoordinates;
	}
	/*
	 * addItem-funktio vaatii parametrinä tavaran (item), joka lisätään hyllyn sisältöön
	 */
	public void addItem(Item item) {
		this.item = item;
	}
	/*
	 * removeItem-funktio ottaa vastaan parametrinä poistettavan tuotteen ID:n, minkä jälkeen
	 * tarkistetaan löytyykö kyseinen tuote hyllyltä. Jos tuote löytyy metodi palauttaa boolean arvon true,
	 * mikäli tuotteen poisto epäonnistui metodi palauttaa boolean arvon false. 
	 */
	public boolean removeItem() {
		if(this.item != null) {
			this.item = null;
			return true;
		}
		return false;
	}
	
	
	/*public boolean removeItem(int itemID) {
		Item tempItem = null;
		for (Item item : items) {
			if(item.getItemID() == itemID) {
				tempItem = item;
			}
		}
		
		if(tempItem != null) {
			items.remove(tempItem);
			return true;
		}else {
			return false;
		}
	}*/
	
	
	/*
	 * getItem-funktio ottaa vastaan parametrinä haettavan tuotteen ID:n,minkä jälkeen
	 *  tarkistetaan löytyykö kyseinen tuote hyllyltä. Jos tuote löytyy metodi palauttaa kyseisen tavara,
	 *  mikäli tuotetta ei lyöydy metodi palauttaa arvon null.
	 */
	public Item getItem() {
		return this.item;
	}
	
	/*
	 * getCellCoordinates-funktio palauttaa hyllyn solukoordinaatit ArrayList<Point> muodossa
	 */
	public Point getCellCoordinates(){
		return (Point) this.cellCoordinates;
	}
	
	public void setCellCoordinates(Point n) {
		this.cellCoordinates = n;
	}
	public int getCoordinateX() {
		return coordinateX;
	}

	public void setCoordinateX(int coordinateX) {
		this.coordinateX = coordinateX;
	}

	public int getCoordinateY() {
		return coordinateY;
	}

	public void setCoordinateY(int coordinateY) {
		this.coordinateY = coordinateY;
	}

	public int getShelfID() {
		return shelfID;
	}

	public void setShelfID(int shelfID) {
		this.shelfID = shelfID;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
	
}