package Ryhma7.ULI_9000;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.*;
import org.testfx.matcher.control.LabeledMatchers;

import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;

//Import dependencies
@ExtendWith(ApplicationExtension.class)
public class GUITest {
	
	@BeforeAll
	public static void setupSpec() throws Exception {
	    FxToolkit.registerPrimaryStage();
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
	void should_be_able_to_create_storage(FxRobot robot) {
		robot.clickOn("#newstorage");
		robot.write("testivarasto");
		robot.clickOn("#address");
		robot.write("testikuja 1");
		robot.clickOn("#width");
		robot.write("7");
		robot.clickOn("#length");
		robot.write("5");
		robot.clickOn("#newstoragecreate");
	}*/
	@Test
	void testOpeningStorage() {
		
	}
}
