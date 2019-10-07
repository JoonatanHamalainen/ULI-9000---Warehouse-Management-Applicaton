package Ryhma7.ULI_9000.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Shelf {
	
	private int id;
	private Item item;
	private Point cellCoordinates;

	//Kopioidaan konstruktorin parametrinä saatu lista solukoordinaateista ja ID
	public Shelf() {
	}
	
	public Shelf(Point cellCoordinates) {
		this.cellCoordinates = cellCoordinates;
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
		return this.cellCoordinates;
	}
	
	public int getID() {
		return this.id;
	}
	public void setID(int id) {
		this.id = id;
	}
	
}