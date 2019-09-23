package Ryhma7.ULI_9000.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Shelf {
	
	private String id;
	private ArrayList<Item> items;
	private ArrayList<Point> cellCoordinates;

	//Kopioidaan konstruktorin parametrinä saatu lista solukoordinaateista ja ID
	public Shelf(ArrayList<Point> cellCoordinates, String id) {
		this.items = new ArrayList<Item>();
		this.cellCoordinates = cellCoordinates;
		this.id = id;
	}
	/*
	 * addItem-metodi vaatii parametrinä tavaran (item), joka lisätään hyllyn sisältöön
	 */
	public void addItem(Item item) {
		this.items.add(item);
	}
	/*
	 * removeItem-metodi ottaa vastaan parametrinä poistettavan tuotteen ID:n, minkä jälkeen
	 * tarkistetaan löytyykö kyseinen tuote hyllyltä. Jos tuote löytyy metodi palauttaa boolean arvon true,
	 * mikäli tuotteen poisto epäonnistui metodi palauttaa boolean arvon false. 
	 */
	public boolean removeItem(String itemID) {
		Item tempItem = null;
		for (Item item : items) {
			if(item.getProductID() == itemID) {
				tempItem = item;
			}
		}
		
		if(tempItem != null) {
			items.remove(tempItem);
			return true;
		}else {
			return false;
		}
	}
	/*
	 * getItem-metodi ottaa vastaan parametrinä haettavan tuotteen ID:n,minkä jälkeen
	 *  tarkistetaan löytyykö kyseinen tuote hyllyltä. Jos tuote löytyy metodi palauttaa kyseisen tavara,
	 *  mikäli tuotetta ei lyöydy metodi palauttaa arvon null.
	 */
	public Item getItem(String itemID) {
		for(Item item : items) {
			if(item.getProductID() == itemID) {
				return item;
			}
		}
		return null;
	}
	
	public boolean addCell(Point coordinates) {
		if(coordinates != null) {
			this.cellCoordinates.add(coordinates);
			return true;
		}else {
			return false;
		}
	}
	
	public boolean removeCell(Point coordinates) {
		if(coordinates != null) {
			this.cellCoordinates.remove(coordinates);
			return true;
		}else {
			return false;
		}
	}
	
	public ArrayList<Point> getCellCoordinates(){
		return this.cellCoordinates;
	}
	
	public String getID() {
		return this.id;
	}
	public void setID(String id) {
		this.id = id;
	}
	
}