package Ryhma7.ULI_9000.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ItemTest {

	private static Item item;
	
	@BeforeAll
	public static void createItem() {
		try {
			item = new Item();
		}catch(Exception e) {
			fail("Failed to create new item!");
		}
	}
	
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
