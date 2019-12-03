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

	@Test
    void should_contain_buttons_with_text(FxRobot robot) {
        FxAssert.verifyThat("#newstorage", LabeledMatchers.hasText("Luo Uusi Varasto"));
        FxAssert.verifyThat("#exit", LabeledMatchers.hasText("Poistu"));
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
	}
	
	@Test
    void should_contain_texts(FxRobot robot) {
        FxAssert.verifyThat("#welcome", LabeledMatchers.hasText("Tervetuloa varastonhallintaohjelmaan ULI-9000! Aloita klikkaamalla Luo Uusi Varasto -painiketta!"));
        FxAssert.verifyThat("#yourstorages", LabeledMatchers.hasText("Sinun Varastosi"));
    }
	
}
