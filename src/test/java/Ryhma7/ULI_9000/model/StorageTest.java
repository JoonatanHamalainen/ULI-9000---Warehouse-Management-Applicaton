package Ryhma7.ULI_9000.model;


import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class StorageTest {
	
	private static Storage storage;
	private static Shelf shelf;
	private static Item item;
	
	@BeforeAll
	@Test
	public static void createStorage() {
		try {
			storage = new Storage();
		}catch(Exception e) {
			fail("Failed to create new storage!");
		}	
	}
	
	@Test
	void removeShelfTest() {
		shelf = new Shelf();
		storage.addShelf(shelf);
		assertTrue(storage.removeShelf(shelf), "Shelf removal unsuccesful!");
	}
	
	@Test
	void removeItemFromStorageTest() {
		item = new Item();
		storage.addItemToStorage(item);
		storage.removeItemFromStorage(item);
		assertEquals(0, storage.getItems().size(), "Item removal unsuccesful!");
	}
	
	@Test
	void getDimensionsTest() {
		storage = new Storage(5, 6);
		List<Integer> dimensions = new ArrayList<>();
		dimensions.add(5);
		dimensions.add(6);
		
		assertEquals(dimensions, storage.getDimensions(), "Didn't return the right dimensions!");
	}
	
	
	

}
