package Ryhma7.ULI_9000.controller;

import Ryhma7.ULI_9000.model.Shelf;
import Ryhma7.ULI_9000.model.Storage;

public class StorageController {
	
	Storage storage;
	Shelf shelf;
	// Varaston koko
	// poista hylly
	// muokkaa hyllyä. (lisää/poista ruutuja)
	
	public void createStorage(int width, int length) {
		storage = new Storage(width, length);
	}
	
	public void createShelf() {
		shelf = new Shelf();
	}
	
	/*public void changeStorageSize(int width, int length) {
		storage.setStorageSize(width, length);
	}*/
	
	public void deleteShelf() {
		
	}
	
	public void addShelfCell() {
		
	}
	
	public void removeShelfCell() {
		
	}
}
