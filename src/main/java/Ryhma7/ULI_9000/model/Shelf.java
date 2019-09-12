package Ryhma7.ULI_9000.model;

import java.util.ArrayList;
import java.util.List;

public class Shelf {
	
	//Hyllyn sisältämät tavarat
	private ArrayList<Item> items = new ArrayList<Item>();
	//Lista, joka sisältää hyllyn solujen koordinaatit varastossa
	private ArrayList<List<Integer>> cellCoordinates = new ArrayList<List<Integer>>();

	//Kopioidaan konstruktorin parametrinä saatu lista solukoordinaateista
	public Shelf(ArrayList<List<Integer>> cellCoordinates) {
		this.cellCoordinates = cellCoordinates;
	}
	/*
	 * addItem-metodi vaatii parametrinä tavaran (item), joka lisätään hyllyn sisältöön
	 */
	public void addItem(Item item) {
		this.items.add(item);
	}
	
	public void removeItem() {
		//TODO
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
}