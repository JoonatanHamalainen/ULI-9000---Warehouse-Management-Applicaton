package Ryhma7.ULI_9000.model;

import java.util.ArrayList;
import java.util.List;

public class Storage {
	
	
	private String name;
	private String address;
	private int width;
	private int length;
	private ArrayList<Shelf> shelves;
	
	public Storage () {
		this.shelves = new ArrayList<Shelf>();
	}
	
	public Storage(int widthX, int lengthY) {
		this.shelves = new ArrayList<Shelf>();
		this.width = widthX;
		this.length = lengthY;
	}
	/*
	 * getDimensions-funktio palauttaa varaston koon x,y -koodrdinaatteina
	 */
	public List<Integer> getDimensions(){
		List<Integer> storageDimensions = new ArrayList<Integer>();
		storageDimensions.add(this.width);
		storageDimensions.add(this.length);
		return storageDimensions;
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
	public void setDimensions(int width, int length) {
		this.width = width;
		this.length = length;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
