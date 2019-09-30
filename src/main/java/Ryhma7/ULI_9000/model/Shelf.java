package Ryhma7.ULI_9000.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Shelf {
	
	private int id;
	private ArrayList<Item> items;
	private ArrayList<Point> cellCoordinates;

	//Kopioidaan konstruktorin parametrinä saatu lista solukoordinaateista ja ID
	public Shelf(ArrayList<Point> cellCoordinates, int id) {
		this.items = new ArrayList<Item>();
		this.cellCoordinates = cellCoordinates;
		this.id = id;
	}
	/*
	 * addItem-funktio vaatii parametrinä tavaran (item), joka lisätään hyllyn sisältöön
	 */
	public void addItem(Item item) {
		this.items.add(item);
	}
	/*
	 * removeItem-funktio ottaa vastaan parametrinä poistettavan tuotteen ID:n, minkä jälkeen
	 * tarkistetaan löytyykö kyseinen tuote hyllyltä. Jos tuote löytyy metodi palauttaa boolean arvon true,
	 * mikäli tuotteen poisto epäonnistui metodi palauttaa boolean arvon false. 
	 */
	public boolean removeItem(int itemID) {
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
	}
	/*
	 * getItem-funktio ottaa vastaan parametrinä haettavan tuotteen ID:n,minkä jälkeen
	 *  tarkistetaan löytyykö kyseinen tuote hyllyltä. Jos tuote löytyy metodi palauttaa kyseisen tavara,
	 *  mikäli tuotetta ei lyöydy metodi palauttaa arvon null.
	 */
	public Item getItem(int itemID) {
		for(Item item : items) {
			if(item.getItemID() == itemID) {
				return item;
			}
		}
		return null;
	}
	/*
	 * addCell-funktio lisää hyllyyn yksittäisen solun, jos lisäys epäonnistuu palauttaa 
	 * funktio boolean arvon false
	 */
	public boolean addCell(Point coordinates) {
		if(coordinates != null) {
			this.cellCoordinates.add(coordinates);
			return true;
		}else {
			return false;
		}
	}
	/*
	 * removeCell-funktio poistaa hyllystä yksittäisen solun. Mikäli toiminto epäonnistuu
	 *  funktio palauttaa boolean arvon false
	 */
	public boolean removeCell(Point coordinates) {
		if(coordinates != null) {
			this.cellCoordinates.remove(coordinates);
			return true;
		}else {
			return false;
		}
	}
	/*
	 * getCellCoordinates-funktio palauttaa hyllyn solukoordinaatit ArrayList<Point> muodossa
	 */
	public ArrayList<Point> getCellCoordinates(){
		return this.cellCoordinates;
	}
	
	public int getID() {
		return this.id;
	}
	public void setID(int id) {
		this.id = id;
	}
	
}