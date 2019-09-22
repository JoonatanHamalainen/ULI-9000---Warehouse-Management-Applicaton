package Ryhma7.ULI_9000.model;

import java.util.ArrayList;
import java.util.List;

public class Shelf {
	private String id;
	//Hyllyn sisältämät tavarat
	private ArrayList<Item> items = new ArrayList<Item>();
	//Lista, joka sisältää hyllyn solujen koordinaatit varastossa
	private ArrayList<List<Integer>> cellCoordinates = new ArrayList<List<Integer>>();

	//Kopioidaan konstruktorin parametrinä saatu lista solukoordinaateista
	public Shelf(ArrayList<List<Integer>> cellCoordinates, String id) {
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
	
	public boolean addCell(List<Integer> coordinates) {
		if(coordinates.size() == 2) {
			this.cellCoordinates.add(coordinates);
			return true;
		}else {
			return false;
		}
	}
	
	public boolean removeCell(List<Integer> coordinates) {
		if(coordinates.size() == 2) {
			this.cellCoordinates.remove(coordinates);
			return true;
		}else {
			return false;
		}
	}
	
	public ArrayList<List<Integer>> getCellCoordinates(){
		return this.cellCoordinates;
	}
	
	public String getID() {
		return this.id;
	}
	public void setID(String id) {
		this.id = id;
	}
	
}