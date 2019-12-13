package Ryhma7.ULI_9000;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.*;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.matcher.control.TextInputControlMatchers;
import Ryhma7.ULI_9000.model.DatabaseConnection;
import Ryhma7.ULI_9000.model.Shelf;
import javafx.scene.input.KeyCode;


import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;

//Import dependencies
@ExtendWith(ApplicationExtension.class)
public class GUITest {
	
	static DatabaseConnection database;
	
	@BeforeAll
	public static void setupSpec(FxRobot robot) throws Exception {
		database = new DatabaseConnection();
	    FxToolkit.registerPrimaryStage();
	    FxToolkit.setupApplication(App.class);
	    
	    robot.clickOn("#newstorage");
		robot.write("Test Storage");
		robot.clickOn("#address");
		robot.write("Test Road 1");
		robot.clickOn("#width");
		robot.write("7");
		robot.clickOn("#length");
		robot.write("5");
		robot.clickOn("#newstoragecreate");
	}

	@Test
    void testMainPageEnglishLanguage(FxRobot robot) {
		robot.clickOn("#eng");
        FxAssert.verifyThat("#newstorage", LabeledMatchers.hasText("Create New Storage"));
        FxAssert.verifyThat("#welcome", LabeledMatchers.hasText("Welcome to ULI-9000! Please start by clicking on the Create New Storage button!"));
        FxAssert.verifyThat("#yourstorages", LabeledMatchers.hasText("Your Storages"));
        FxAssert.verifyThat("#exit", LabeledMatchers.hasText("Exit"));
    }

	@Test
    void testMainPageFinnishLanguage(FxRobot robot) {
		robot.clickOn("#fin");
        FxAssert.verifyThat("#newstorage", LabeledMatchers.hasText("Luo Uusi Varasto"));
        FxAssert.verifyThat("#welcome", LabeledMatchers.hasText("Tervetuloa varastonhallintaohjelmaan ULI-9000! Aloita klikkaamalla Luo Uusi Varasto -painiketta!"));
        FxAssert.verifyThat("#yourstorages", LabeledMatchers.hasText("Sinun Varastosi"));
        FxAssert.verifyThat("#exit", LabeledMatchers.hasText("Poistu"));
    }
	
	@Test
    void testMainPageSwedishLanguage(FxRobot robot) {
		robot.clickOn("#swe");
        FxAssert.verifyThat("#newstorage", LabeledMatchers.hasText("Skapa ny Föråd"));
        FxAssert.verifyThat("#welcome", LabeledMatchers.hasText("Welkommen till ULI-9000! Vänligen börja med att clicka Skapa Ny Föråd -Knapp!"));
        FxAssert.verifyThat("#yourstorages", LabeledMatchers.hasText("Dina Föråd"));
        FxAssert.verifyThat("#exit", LabeledMatchers.hasText("Avsluta"));
    }
	
	@Test
	void testNewStorageDialogEnglishLanguage(FxRobot robot) {
		robot.clickOn("#eng");
		robot.clickOn("#newstorage");
		FxAssert.verifyThat("#nametxt", LabeledMatchers.hasText("Storage Name"));
        FxAssert.verifyThat("#addresstxt", LabeledMatchers.hasText("Storage Address"));
        FxAssert.verifyThat("#widthtxt", LabeledMatchers.hasText("Storage Width (m)"));
        FxAssert.verifyThat("#lengthtxt", LabeledMatchers.hasText("Storage Length (m)"));
        FxAssert.verifyThat("#newstoragecreate", LabeledMatchers.hasText("Create New Storage"));
        FxAssert.verifyThat("#newstoragecancel", LabeledMatchers.hasText("Cancel"));
        robot.clickOn("#newstoragecancel");
	}
    
	@Test
	void testNewStorageDialogFinnishLanguage(FxRobot robot) {
		robot.clickOn("#fin");
		robot.clickOn("#newstorage");
		FxAssert.verifyThat("#nametxt", LabeledMatchers.hasText("Varaston Nimi"));
        FxAssert.verifyThat("#addresstxt", LabeledMatchers.hasText("Varaston Katuosoite"));
        FxAssert.verifyThat("#widthtxt", LabeledMatchers.hasText("Varaston Leveys (m)"));
        FxAssert.verifyThat("#lengthtxt", LabeledMatchers.hasText("Varaston Pituus (m)"));
        FxAssert.verifyThat("#newstoragecreate", LabeledMatchers.hasText("Luo Uusi Varasto"));
        FxAssert.verifyThat("#newstoragecancel", LabeledMatchers.hasText("Peruuta"));
        robot.clickOn("#newstoragecancel");
    }
    
	@Test
	void testNewStorageDialogSwedishLanguage(FxRobot robot) {
		robot.clickOn("#swe");
		robot.clickOn("#newstorage");
		FxAssert.verifyThat("#nametxt", LabeledMatchers.hasText("Förådets Namn"));
        FxAssert.verifyThat("#addresstxt", LabeledMatchers.hasText("Adress"));
        FxAssert.verifyThat("#widthtxt", LabeledMatchers.hasText("Bredd på Förådet (m)"));
        FxAssert.verifyThat("#lengthtxt", LabeledMatchers.hasText("Längdet på Förådet (m)"));
        FxAssert.verifyThat("#newstoragecreate", LabeledMatchers.hasText("Skapa ny Föråd"));
        FxAssert.verifyThat("#newstoragecancel", LabeledMatchers.hasText("Cancelera"));
        robot.clickOn("#newstoragecancel");
	}
	
	@Test
	void testStorageLayoutEnglishLanguage(FxRobot robot) {
		robot.clickOn("#eng");
		robot.clickOn("#neweststorage");
		
		FxAssert.verifyThat("#addressLabel", LabeledMatchers.hasText("Address"));
		FxAssert.verifyThat("#widthLabel", LabeledMatchers.hasText("Width (m)"));
		FxAssert.verifyThat("#lengthLabel", LabeledMatchers.hasText("Length (m)"));
		FxAssert.verifyThat("#removeStorage", LabeledMatchers.hasText("Remove"));
		FxAssert.verifyThat("#saveStorageChanges", LabeledMatchers.hasText("Save Changes"));
		FxAssert.verifyThat("#locked", LabeledMatchers.hasText("Locked"));
		
		FxAssert.verifyThat("#walls", LabeledMatchers.hasText("Edit Walls"));
		FxAssert.verifyThat("#storage", LabeledMatchers.hasText("Storage"));
		FxAssert.verifyThat("#addShelf", LabeledMatchers.hasText("Add Shelf"));
		FxAssert.verifyThat("#newItem", LabeledMatchers.hasText("New Item"));
		FxAssert.verifyThat("#removeItem", LabeledMatchers.hasText("Remove Item from Storage"));
		FxAssert.verifyThat("#shelf", LabeledMatchers.hasText("Shelf"));
		FxAssert.verifyThat("#addItemToShelf", LabeledMatchers.hasText("Add Item to Shelf:"));
		FxAssert.verifyThat("#removeItemFromShelf", LabeledMatchers.hasText("Remove Item from Shelf"));
		FxAssert.verifyThat("#removeShelf", LabeledMatchers.hasText("Remove Shelf"));
		FxAssert.verifyThat("#contains", LabeledMatchers.hasText("Contains:"));
	}
	
	@Test
	void testStorageLayoutFinnishLanguage(FxRobot robot) {
		robot.clickOn("#fin");
		robot.clickOn("#neweststorage");
		
		FxAssert.verifyThat("#addressLabel", LabeledMatchers.hasText("Katuosoite"));
		FxAssert.verifyThat("#widthLabel", LabeledMatchers.hasText("Leveys (m)"));
		FxAssert.verifyThat("#lengthLabel", LabeledMatchers.hasText("Pituus (m)"));
		FxAssert.verifyThat("#removeStorage", LabeledMatchers.hasText("Poista"));
		FxAssert.verifyThat("#saveStorageChanges", LabeledMatchers.hasText("Tallenna Muutokset"));
		FxAssert.verifyThat("#locked", LabeledMatchers.hasText("Lukittu"));
		
		FxAssert.verifyThat("#walls", LabeledMatchers.hasText("Muokkaa Seiniä"));
		FxAssert.verifyThat("#storage", LabeledMatchers.hasText("Varasto"));
		FxAssert.verifyThat("#addShelf", LabeledMatchers.hasText("Lisää Hylly"));
		FxAssert.verifyThat("#newItem", LabeledMatchers.hasText("Uusi Tuote"));
		FxAssert.verifyThat("#removeItem", LabeledMatchers.hasText("Poista Tuote Varastosta"));
		FxAssert.verifyThat("#shelf", LabeledMatchers.hasText("Hylly"));
		FxAssert.verifyThat("#addItemToShelf", LabeledMatchers.hasText("Lisää Tuote Hyllyyn:"));
		FxAssert.verifyThat("#removeItemFromShelf", LabeledMatchers.hasText("Poista Tuote Hyllystä"));
		FxAssert.verifyThat("#removeShelf", LabeledMatchers.hasText("Poista Hylly"));
		FxAssert.verifyThat("#contains", LabeledMatchers.hasText("Sisältää:"));
	}
	
	@Test
	void testStorageLayoutSwedishLanguage(FxRobot robot) {
		robot.clickOn("#swe");
		robot.clickOn("#neweststorage");
		
		FxAssert.verifyThat("#addressLabel", LabeledMatchers.hasText("Adress"));
		FxAssert.verifyThat("#widthLabel", LabeledMatchers.hasText("Bredd (m)"));
		FxAssert.verifyThat("#lengthLabel", LabeledMatchers.hasText("Längd (m)"));
		FxAssert.verifyThat("#removeStorage", LabeledMatchers.hasText("Ta Bort"));
		FxAssert.verifyThat("#saveStorageChanges", LabeledMatchers.hasText("Spara ändringarna"));
		FxAssert.verifyThat("#locked", LabeledMatchers.hasText("Låst"));
		
		FxAssert.verifyThat("#walls", LabeledMatchers.hasText("Redigera Väggar"));
		FxAssert.verifyThat("#storage", LabeledMatchers.hasText("Föråd"));
		FxAssert.verifyThat("#addShelf", LabeledMatchers.hasText("Lägg till Hylla"));
		FxAssert.verifyThat("#newItem", LabeledMatchers.hasText("Ny Produkt"));
		FxAssert.verifyThat("#removeItem", LabeledMatchers.hasText("Ta Bort från Förådet"));
		FxAssert.verifyThat("#shelf", LabeledMatchers.hasText("Hylla"));
		FxAssert.verifyThat("#addItemToShelf", LabeledMatchers.hasText("Lägg till Produkt i Hyllan:"));
		FxAssert.verifyThat("#removeItemFromShelf", LabeledMatchers.hasText("Ta Bort från Hyllan"));
		FxAssert.verifyThat("#removeShelf", LabeledMatchers.hasText("Ta Bort Hyllan"));
		FxAssert.verifyThat("#contains", LabeledMatchers.hasText("Innehåller:"));
	}
	
	@Test
	void testNewItemDialogEnglishLanguage(FxRobot robot) {
		robot.clickOn("#eng");
		robot.clickOn("#neweststorage");
		robot.clickOn("#newItem");
		
		FxAssert.verifyThat("#itemName", LabeledMatchers.hasText("Item Name"));
		FxAssert.verifyThat("#itemWeight", LabeledMatchers.hasText("Item Weight (g)"));
		FxAssert.verifyThat("#itemAmount", LabeledMatchers.hasText("Item Amount"));
		FxAssert.verifyThat("#itemUnitprice", LabeledMatchers.hasText("Unit Price (€)"));
		FxAssert.verifyThat("#itemSalesprice", LabeledMatchers.hasText("Sales Price (€)"));
		FxAssert.verifyThat("#createNewItem", LabeledMatchers.hasText("Create"));
		FxAssert.verifyThat("#cancelItemCreation", LabeledMatchers.hasText("Cancel"));
		
		robot.clickOn("#cancelItemCreation");
	}
	
	@Test
	void testNewItemDialogFinnishLanguage(FxRobot robot) {
		robot.clickOn("#fin");
		robot.clickOn("#neweststorage");
		robot.clickOn("#newItem");
		
		FxAssert.verifyThat("#itemName", LabeledMatchers.hasText("Tuotenimi"));
		FxAssert.verifyThat("#itemWeight", LabeledMatchers.hasText("Tuotteen Paino (g)"));
		FxAssert.verifyThat("#itemAmount", LabeledMatchers.hasText("Tuotemäärä"));
		FxAssert.verifyThat("#itemUnitprice", LabeledMatchers.hasText("Yksikköhinta (€)"));
		FxAssert.verifyThat("#itemSalesprice", LabeledMatchers.hasText("Myyntihinta (€)"));
		FxAssert.verifyThat("#createNewItem", LabeledMatchers.hasText("Luo"));
		FxAssert.verifyThat("#cancelItemCreation", LabeledMatchers.hasText("Peruuta"));
		
		robot.clickOn("#cancelItemCreation");
	}
	
	@Test
	void testNewItemDialogSwedishLanguage(FxRobot robot) {
		robot.clickOn("#swe");
		robot.clickOn("#neweststorage");
		robot.clickOn("#newItem");
		
		FxAssert.verifyThat("#itemName", LabeledMatchers.hasText("Produkt Namn"));
		FxAssert.verifyThat("#itemWeight", LabeledMatchers.hasText("Produkt Vikt (g)"));
		FxAssert.verifyThat("#itemAmount", LabeledMatchers.hasText("Produkt Mängd"));
		FxAssert.verifyThat("#itemUnitprice", LabeledMatchers.hasText("Enhetspris (€)"));
		FxAssert.verifyThat("#itemSalesprice", LabeledMatchers.hasText("Försäljningspris (€)"));
		FxAssert.verifyThat("#createNewItem", LabeledMatchers.hasText("Skapa"));
		FxAssert.verifyThat("#cancelItemCreation", LabeledMatchers.hasText("Avbryt"));
		
		robot.clickOn("#cancelItemCreation");
	}
	@Test
	void testStorageCustomization(FxRobot robot) {
		robot.clickOn("#neweststorage");
		assertEquals("Test Storage", database.getStorages().get(database.getStorages().size()-1).getName());
		assertEquals("Test Road 1", database.getStorages().get(database.getStorages().size()-1).getAddress());
		assertEquals(7, database.getStorages().get(database.getStorages().size()-1).getWidth());
		assertEquals(5, database.getStorages().get(database.getStorages().size()-1).getLength());
		robot.clickOn("#address");
		robot.clickOn("#locked");
		robot.clickOn("#address");
		robot.doubleClickOn("#address");
		robot.write("Changed Address");
		robot.doubleClickOn("#width");
		robot.write("9");
		robot.doubleClickOn("#length");
		robot.write("15");
		robot.clickOn("#saveStorageChanges");
		assertEquals("Changed Address", database.getStorages().get(database.getStorages().size()-1).getAddress());
		assertEquals(9, database.getStorages().get(database.getStorages().size()-1).getWidth());
		assertEquals(15, database.getStorages().get(database.getStorages().size()-1).getLength());
		robot.clickOn("#locked");
	}
	@Test
	void testItemCreationAndShelfCreationAndWallCreationAndAddingItemToShelf(FxRobot robot) {
		int itemAmount = database.getItemsInStorage(database.getStorage("Test Storage")).size();
		int shelfAmount = database.getShelvesInStorage(database.getStorage("Test Storage")).size();
		int wallAmount = database.getWallsInStorage(database.getStorage("Test Storage")).size();
		
		robot.clickOn("#neweststorage");
		robot.clickOn("#newItem");
		robot.clickOn("#name");
		robot.write("Test Item");
		robot.clickOn("#weight");
		robot.write("150");
		robot.clickOn("#amount");
		robot.write("69");
		robot.clickOn("#unitPrice");
		robot.write("5.95");
		robot.clickOn("#salesPrice");
		robot.write("7.85");
		robot.clickOn("#createNewItem");
		assertEquals(itemAmount + 1, database.getItemsInStorage(database.getStorage("Test Storage")).size());
		itemAmount = database.getItemsInStorage(database.getStorage("Test Storage")).size();
		robot.clickOn("#itemsInStorageBox");
		for (int i=0; i<itemAmount; i++) {
		robot.type(KeyCode.DOWN);
		}
		robot.type(KeyCode.ENTER);
		robot.clickOn("#cell");
		robot.clickOn("#addShelf");
		assertEquals(shelfAmount + 1, database.getShelvesInStorage(database.getStorage("Test Storage")).size());
		shelfAmount = database.getShelvesInStorage(database.getStorage("Test Storage")).size();
		robot.clickOn("#shelvesInStorageBox");
		for (int i=0; i<shelfAmount; i++) {
			robot.type(KeyCode.DOWN);
			}
		robot.type(KeyCode.ENTER);
		robot.clickOn("#addItemToShelf");
		FxAssert.verifyThat("#containedItem", TextInputControlMatchers.hasText("Test Item"));
		robot.doubleClickOn("#neweststorage");
		robot.clickOn("#cell");
		ArrayList<Shelf> shelves = database.getShelvesInStorage(database.getStorage("Test Storage"));
		Shelf shelf = shelves.get(shelves.size()-1);
		FxAssert.verifyThat("#shelfID", LabeledMatchers.hasText(String.valueOf(shelf.getShelfID())));
		FxAssert.verifyThat("#itemName", LabeledMatchers.hasText("Test Item"));
		FxAssert.verifyThat("#salesprice", LabeledMatchers.hasText("7.85"));
		FxAssert.verifyThat("#unitPrice", LabeledMatchers.hasText("5.95"));
		FxAssert.verifyThat("#weight", LabeledMatchers.hasText("150"));
		robot.doubleClickOn("#removeShelf");
		robot.clickOn("#walls");
		robot.clickOn("#cell");
		assertEquals(wallAmount + 1, database.getWallsInStorage(database.getStorage("Test Storage")).size());
		robot.clickOn("#cell");
		assertEquals(wallAmount, database.getWallsInStorage(database.getStorage("Test Storage")).size());
		robot.clickOn("#walls");
		assertEquals(shelfAmount-1, database.getShelvesInStorage(database.getStorage("Test Storage")).size());
		robot.clickOn("#removeItem");
		assertEquals(itemAmount-1, database.getItemsInStorage(database.getStorage("Test Storage")).size());
	}
	
	@AfterAll
	static void testDeletingStorageAndEndingProgram(FxRobot robot) {
		robot.clickOn("#neweststorage");
		robot.clickOn("#locked");
		robot.clickOn("#removeStorage");
		robot.clickOn("#exit");
	}
}
