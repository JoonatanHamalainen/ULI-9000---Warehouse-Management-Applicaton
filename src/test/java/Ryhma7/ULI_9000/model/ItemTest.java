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
	void increaseAmountTest() {
		item.setAmount(5);
		item.increaseAmount(1, 2);
		assertEquals(7, item.getAmount(), "Increasing amount not working!");
	}

	@Test
	void decreaseAmountTest() {
		item.setAmount(5);
		item.decreaseAmount(1, 3);
		assertEquals(2, item.getAmount(), "Decreasing amount not working!");
		assertFalse(item.decreaseAmount(1, 3), "Amount decreased past 0!");
	}
	
}
