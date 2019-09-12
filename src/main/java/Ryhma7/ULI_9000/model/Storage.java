package Ryhma7.ULI_9000.model;

import java.util.ArrayList;
import java.util.List;

public class Storage {
	private List<Integer> size;
	private ArrayList<Shelf> shelves;
	
	public Storage(int widthX, int lengthY) {
		this.shelves = new ArrayList<Shelf>();
		this.size.add(widthX);
		this.size.add(lengthY);
	}
	
	public void setSize(int widthX, int lengthY) {
		this.size.set(0, widthX);
		this.size.set(1, lengthY);
	}
	
	public List<Integer> getSize(){
		return this.size;
	}
	
	public void addShelf(Shelf shelf) {
		this.shelves.add(shelf);
	}
	
	public void removeShelf(Shelf shelf) {
		this.shelves.remove(shelf);
	}
	
	public Shelf getShelf(List<Integer> coordinate) {
		for(Shelf shelf:this.shelves) {
			if(shelf.getCellCoordinates().contains(coordinate)) {
				return shelf;
			}
		}
		
		return null;
	}
}
