package Ryhma7.ULI_9000;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.*;
import org.testfx.matcher.control.LabeledMatchers;

import com.sun.xml.bind.CycleRecoverable.Context;

import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;

//Import dependencies
@ExtendWith(ApplicationExtension.class)
public class GUITest {
	
	@BeforeAll
	public static void setupSpec(FxRobot robot) throws Exception {
	    FxToolkit.registerPrimaryStage();
	    FxToolkit.setupApplication(App.class);
	    
	    /*
	    robot.clickOn("#newstorage");
		robot.write("testivarasto");
		robot.clickOn("#address");
		robot.write("testikuja 1");
		robot.clickOn("#width");
		robot.write("7");
		robot.clickOn("#length");
		robot.write("5");
		robot.clickOn("#newstoragecreate");
		*/
	}

	@BeforeEach
	public void setUp() throws Exception {
	    FxToolkit.setupApplication(App.class);
	}
	/*
	@Test
    void testMainPageEnglishLanguage(FxRobot robot) {
		robot.clickOn("#eng");
        FxAssert.verifyThat("#newstorage", LabeledMatchers.hasText("Create New Storage"));
        FxAssert.verifyThat("#welcome", LabeledMatchers.hasText("Welcome to ULI-9000! Please start by clicking on the Create New Storage button!"));
        FxAssert.verifyThat("#yourstorages", LabeledMatchers.hasText("Your Storages"));
        FxAssert.verifyThat("#exit", LabeledMatchers.hasText("Exit"));
    }
	@Test
	void testNewStorageDialogEnglishLanguage(FxRobot robot) {
		robot.clickOn("#eng");
		robot.clickOn("#newstorage");
		FxAssert.verifyThat("#nametxt", LabeledMatchers.hasText("Storage Name"));
        FxAssert.verifyThat("#addresstxt", LabeledMatchers.hasText("Storage Address"));
        FxAssert.verifyThat("#widthtxt", LabeledMatchers.hasText("Storage Width"));
        FxAssert.verifyThat("#lengthtxt", LabeledMatchers.hasText("Storage Length"));
        FxAssert.verifyThat("#newstoragecreate", LabeledMatchers.hasText("Create New Storage"));
        FxAssert.verifyThat("#newstoragecancel", LabeledMatchers.hasText("Cancel"));
        robot.clickOn("#newstoragecancel");
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
	void testNewStorageDialogFinnishLanguage(FxRobot robot) {
		robot.clickOn("#fin");
		robot.clickOn("#newstorage");
		FxAssert.verifyThat("#nametxt", LabeledMatchers.hasText("Varaston Nimi"));
        FxAssert.verifyThat("#addresstxt", LabeledMatchers.hasText("Varaston Katuosoite"));
        FxAssert.verifyThat("#widthtxt", LabeledMatchers.hasText("Varaston Leveys"));
        FxAssert.verifyThat("#lengthtxt", LabeledMatchers.hasText("Varaston Pituus"));
        FxAssert.verifyThat("#newstoragecreate", LabeledMatchers.hasText("Luo Uusi Varasto"));
        FxAssert.verifyThat("#newstoragecancel", LabeledMatchers.hasText("Peruuta"));
        robot.clickOn("#newstoragecancel");
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
	void testNewStorageDialogSwedishLanguage(FxRobot robot) {
		robot.clickOn("#swe");
		robot.clickOn("#newstorage");
		FxAssert.verifyThat("#nametxt", LabeledMatchers.hasText("Förådets Namn"));
        FxAssert.verifyThat("#addresstxt", LabeledMatchers.hasText("Adress"));
        FxAssert.verifyThat("#widthtxt", LabeledMatchers.hasText("Bredd på Förådet"));
        FxAssert.verifyThat("#lengthtxt", LabeledMatchers.hasText("Längdet på Förådet"));
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
	}*/
	@Test
	void testStorageLayoutSwedishLanguage(FxRobot robot) {
		robot.clickOn("#eng");
		robot.clickOn("#neweststorage");
		robot.clickOn("#newItem");
		FxAssert.verifyThat("#addressLabel", LabeledMatchers.hasText("Adress"));
	}
	
	@Test
	void testOpeningStorage() {
		
	}
}
