package Ryhma7.ULI_9000.model;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class DatabaseConnectionTest {
	
	static DatabaseConnection database;
	
	@Test
	void databaseConnectionTest() {
		try {
		database = new DatabaseConnection();
		}
		catch (Exception e) {
			fail("Not able to connect to database! Make sure you are connected to \"shell.metropolia.fi\"");
		}
	}
}
