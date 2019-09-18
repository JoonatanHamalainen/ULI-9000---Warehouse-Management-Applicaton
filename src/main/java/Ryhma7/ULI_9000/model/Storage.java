package Ryhma7.ULI_9000.model;

import java.util.ArrayList;
import java.util.List;

public class Storage {
	
	private List<Integer> storageSize;
	private ArrayList<Shelf> shelves;
	
	public Storage(int widthX, int lengthY) {
		this.shelves = new ArrayList<Shelf>();
		this.storageSize.add(widthX);
		this.storageSize.add(lengthY);
	}
	/*
	 * setSize-metodi ottaa vastaan varaston uudet mitat parametreinä
	 */
	public void setStorageSize(int widthX, int lengthY) {
		this.storageSize.set(0, widthX);
		this.storageSize.set(1, lengthY);
	}
	/*
	 * getSize-metodi palauttaa varaston koon x,y -koodrdinaatteina
	 */
	public List<Integer> getSize(){
		return this.storageSize;
	}
	/*
	 * addShelf-metodi ottaa vastaan parametrina hyllyn ja lisää sen shelves-listaan
	 */
	public void addShelf(Shelf shelf) {
		this.shelves.add(shelf);
	}
	/*
	 * removeShelf-metodi ottaa poistettavan hyllyn paramatrina, minkä jälkeen tarkastetaan
	 * lötyykö hyllyä shelves-listalta. Jos hylly löytyy se poistetaan ja palautetaan true, muutoin palautetaan false.
	 */
	public boolean removeShelf(Shelf shelf) {
		if(this.shelves.contains(shelf)) {
			this.shelves.remove(shelf);
			return true;
		}else {
			return false;
		}
		
	}
	/*
	 * getShelf-metodi ottaa vastaan haettavan hyllyn ID:n, minkä jälkeen se etsii kyseistä hyllyä
	 * shelves-listalta. Jos haku tuottaa tulosta, palautetaan haettu hylly, muutoin palautetaan null.
	 */
	public Shelf getShelf(int shelfID) {
		for(Shelf shelf : this.shelves) {
			if(shelf.getID() == shelfID) {
				return shelf;
			}
		}
		return null;
	}
}
