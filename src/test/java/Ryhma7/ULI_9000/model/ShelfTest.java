package Ryhma7.ULI_9000.model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


class ShelfTest {
	private static Shelf shelf;

	@BeforeAll
	public static void createShelf() {
		ArrayList<Point> coordinatesXY  = new ArrayList<Point>();
		coordinatesXY.add(new Point(1,1));
		try {
			shelf = new Shelf(coordinatesXY, "123");
		}catch(Exception e) {
			fail("Failed to create new shelf!");
		}	
	}
	
	@Test
	void testGetCellCoordinates() {
		ArrayList<Point> tempList  = new ArrayList<Point>();
		tempList.add(new Point(1,1));
		assertEquals("Hyllyn koordinaatit väärin", tempList, shelf.getCellCoordinates());
		
	}

}
