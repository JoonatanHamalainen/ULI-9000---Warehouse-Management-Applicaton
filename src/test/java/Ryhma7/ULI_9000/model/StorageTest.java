package Ryhma7.ULI_9000.model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class StorageTest {

	private static Storage storage;
	
	@BeforeAll
	public static void createStorage() {
		try {
			storage = new Storage();
		}catch(Exception e) {
			fail("Failed to create new storage!");
		}	
	}
	
	@Test
	void removeShelfTest() {
		Shelf shelf = new Shelf();
		storage.addShelf(shelf);
		assertTrue(storage.removeShelf(shelf), "Shelf removal unsuccesful!");
	}
	
	@Test
	void removeItemFromStorageTest() {
		Item item = new Item();
		storage.addItemToStorage(item);
		storage.removeItemFromStorage(item);
		assertEquals(0, storage.getItems().size(), "Item removal unsuccesful!");
	}

}
