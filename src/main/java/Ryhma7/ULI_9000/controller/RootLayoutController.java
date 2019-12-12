package Ryhma7.ULI_9000.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;
import Ryhma7.ULI_9000.App;
import Ryhma7.ULI_9000.model.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**Controller for RootLayout
*
*/
public class RootLayoutController implements ControllerInterfaceView {
	
	private App mainApp;
	private ArrayList<Storage> storages;
	private VBox vbox;
	
	DatabaseConnection database = new DatabaseConnection();
	/** Sets main application for the controller
	 * @param mainApp is the main app
	 */
	public void setMainApp(App mainApp) {
		this.mainApp = mainApp;
	}
	/**
	 * Sets storages-list for the controller
	 * @param storages is the list of all storages
	 */
	public void setStorages(ArrayList<Storage> storages) {
		this.storages = storages;
	}
	/** Sets the VBox in which the storage contents will be displayed
	 * @param vbox is the box in which storages-list will be displayed
	 */
	public void setVBox(VBox vbox) {
		this.vbox = vbox;		
	}
	/** Generates content for vbox-attribute and populates the textfields with the storage information 
	 * @param vbox
	 */
	public void loadStorages(final VBox vbox, ResourceBundle bundle) {
		final Accordion accordion = new Accordion();
		storages = database.getStorages();
		for(final Storage storage : storages) {
			try {
				//ladataan infoboxin resurssit, ja tallennetaan ne muuttujiin
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(App.class.getResource("view/StorageInfoBox.fxml"));
				loader.setResources(bundle);
				
				TitledPane storageInfo = (TitledPane) loader.load();
				AnchorPane content = (AnchorPane) storageInfo.getContent();
				final CheckBox statusBox = (CheckBox) content.getChildren().get(0);
				final TextField addressField = (TextField) content.getChildren().get(2);
				final TextField widthField = (TextField) content.getChildren().get(4);
				final TextField lengthField = (TextField) content.getChildren().get(6);
				final Button remove = (Button) content.getChildren().get(7);
				final Button save = (Button) content.getChildren().get(8);
				
				//päivitetään varaston tiedot tekstikenttiin
				
				storageInfo.setText(storage.getName());
				addressField.setText(storage.getAddress());
				widthField.setText("" + storage.getDimensions().get(0));
				lengthField.setText("" + storage.getDimensions().get(1));
				//merkitään CheckBox, ja pistetään sisällön muokkaus alustavasti poispäältä
				statusBox.setSelected(true);
				addressField.setDisable(true);
				widthField.setDisable(true);
				lengthField.setDisable(true);
				remove.setDisable(true);
				save.setDisable(true);
				
				//Listener CheckBoxille (seurataan Boxin tilaa): merkittynä tietojen muokkaus ei ole mahdollista tyhjänä puolestaan on
				statusBox.selectedProperty().addListener(new ChangeListener<Boolean>() {

					public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
						if(statusBox.isSelected()) {
							widthField.setDisable(true);
							lengthField.setDisable(true);
							addressField.setDisable(true);
							remove.setDisable(true);
							save.setDisable(true);
						}else {
							addressField.setDisable(false);
							widthField.setDisable(false);
							lengthField.setDisable(false);
							remove.setDisable(false);
							save.setDisable(false);
						}					
					}
				});
				
				//samalla kuin TilePanea painetaan hiirellä, sen oikealle puolelle avautuu varastonäkymä
				storageInfo.setOnMouseClicked(new EventHandler<MouseEvent>() {
					public void handle(MouseEvent event) {
						mainApp.showStorageLayout(storage);
					}
				});
				//Määritetään toiminnallisuus remove(storage) painikkeelle
				remove.setOnMouseClicked(new EventHandler<MouseEvent>() {
					public void handle(MouseEvent event) {
						accordion.getPanes().remove(storage);
						storages.remove(storage);
						database.deleteStorage(storage);
						try {
							Accordion tempAccordion = (Accordion) vbox.getChildren().get(3);
							vbox.getChildren().remove(3);
						}catch(Exception e){	
						}
						//tyhjennetään varastonäkymä, ja ladataan uusi tilalle
						mainApp.clearCenterPane();
						loadStorages(vbox, bundle);
					}
				});
				//Määritetään toiminnallisuus save(storage) painikkeelle
				save.setOnMouseClicked((new EventHandler<MouseEvent>() {
					public void handle(MouseEvent event) {
						storage.setAddress(addressField.getText());
						try {
							storage.setDimensions(Integer.parseInt(widthField.getText()), Integer.parseInt(lengthField.getText()));
							database.updateStorageAddress(storage.getStorageID(), storage.getAddress());
							database.updateStorageWidth(storage.getStorageID(), storage.getWidth());
							database.updateStorageLength(storage.getStorageID(), storage.getLength());
							//päivitetään varastonäkymä vastaamaan esim. uusia varaston mittoja
							
							mainApp.showStorageLayout(storage);
						}catch(NumberFormatException e) {
						}
						
					}
				}));
				
				accordion.getPanes().add(storageInfo);			
			}catch(IOException e){
				System.out.println(e);
			}
		}
		//lisätään luotu accordion "your storages"-labelin ja "exit" buttonin väliin
		vbox.getChildren().add(3, accordion);
	}
	/** Handler function for the interface
	 * Creates a temporary storage object if its attributes are set isOkClicked receives value true,
	 * and the temporary storage is made permanent
	 */
	@FXML
	private void handleCreateStorage() {
		//luodaan väliaikainen varasto-olio, ja annetaan se parametrinä showNewStorage funktiolle, joka palauttaa boolean arvon riippuen siitä luotiinko varastoa
		Storage tempStorage = new Storage();

		boolean isOkClicked = mainApp.showNewStorageDialog(tempStorage);
		if (isOkClicked) {
			database.addStorage(tempStorage);
			mainApp.getStorages().add(database.getStorage(tempStorage.getName()));
			try {
				Accordion tempAccordion = (Accordion) vbox.getChildren().get(3);
				vbox.getChildren().remove(3);
			}catch(Exception e){	
			}			
			loadStorages(this.vbox, mainApp.getResourceBundle());
		}
		System.out.println("New Storage Created!");
	}
	/** Changes the application's language to Finnish by editing the language.properties-file,
	 * and initializing the root layout of the interface again.
	 */
	@FXML
	private void handleLanguageFi() {
		String appConfigPath = "src/main/resources/language.properties";
		Properties appProps = new Properties();
		try {
			appProps.load(new FileInputStream(appConfigPath));
			appProps.setProperty("language", "fi");
			appProps.setProperty("country", "FI");
			appProps.store(new FileOutputStream(appConfigPath), null);
		} catch (Exception e) {
			System.err.println("language.properties file not found!");
		}
		mainApp.selectLanguage();
		mainApp.initRootLayout();
		System.out.println("Fin");
	}
	/** Changes the application's language to Swedish by editing the language.properties-file,
	 * and initializing the root layout of the interface again.
	 */
	@FXML
	private void handleLanguageSwe() {
		String appConfigPath = "src/main/resources/language.properties";
		Properties appProps = new Properties();
		try {
			appProps.load(new FileInputStream(appConfigPath));
			appProps.setProperty("language", "sv");
			appProps.setProperty("country", "SE");
			appProps.store(new FileOutputStream(appConfigPath), null);
		} catch (Exception e) {
			System.err.println("language.properties file not found!");
		}
		mainApp.selectLanguage();
		mainApp.initRootLayout();
		System.out.println("Swe");
	}
	/** Changes the application's language to English by editing the language.properties-file,
	 * and initializing the root layout of the interface again.
	 */
	@FXML
	private void handleLanguageEn() {
		String appConfigPath = "src/main/resources/language.properties";
		Properties appProps = new Properties();
		try {
			appProps.load(new FileInputStream(appConfigPath));
			appProps.setProperty("language", "en");
			appProps.setProperty("country", "US");
			appProps.store(new FileOutputStream(appConfigPath), null);
		} catch (Exception e) {
			System.err.println("language.properties file not found!");
		}
		mainApp.selectLanguage();
		mainApp.initRootLayout();
		System.out.println("Eng");
	}
	/** Handler function for the interface
	 * 
	 */
	@FXML
	private void handleExit() {
		System.exit(0);
	}
}

