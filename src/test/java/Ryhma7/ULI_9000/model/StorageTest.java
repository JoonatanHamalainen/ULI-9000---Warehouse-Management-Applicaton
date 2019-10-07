package Ryhma7.ULI_9000.model;

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
			fail("Failed to create new shelf!");
		}	
	}
	
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
