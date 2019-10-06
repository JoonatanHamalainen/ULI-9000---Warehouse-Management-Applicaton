package Ryhma7.ULI_9000.controller;

import java.io.IOException;
import java.util.ArrayList;

import Ryhma7.ULI_9000.App;
import Ryhma7.ULI_9000.model.Storage;
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

public class AltRootLayoutController {
	
	private App mainApp;
	private ArrayList<Storage> storages;
	private VBox vbox;
	
	public void setMainApp(App mainApp) {
		this.mainApp = mainApp;
	}
	
	public void setStorages(ArrayList<Storage> storages) {
		this.storages = storages;
	}
	
	public void setVBox(VBox vbox) {
		this.vbox = vbox;		
	}
	/*
	 * loadStorages-funktio hallinnoi varaston muutos- ja poisto operaatioita. 
	 */
	public void loadStorages(final VBox vbox) {
		final Accordion accordion = new Accordion();
		for(final Storage storage : storages) {
			try {
				//ladataan infoboxin resurssit, ja tallennetaan ne muuttujiin
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(App.class.getResource("view/StorageInfoBox.fxml"));
				
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
						try {
							Accordion tempAccordion = (Accordion) vbox.getChildren().get(2);
							vbox.getChildren().remove(2);
						}catch(Exception e){	
						}
						//tyhjennetään varastonäkymä, ja ladataan uusi tilalle
						mainApp.clearCenterPane();
						loadStorages(vbox);
					}
				});
				//Määritetään toiminnallisuus save(storage) painikkeelle
				save.setOnMouseClicked((new EventHandler<MouseEvent>() {
					public void handle(MouseEvent event) {
						storage.setAddress(addressField.getText());
						try {
							storage.setDimensions(Integer.parseInt(widthField.getText()), Integer.parseInt(lengthField.getText()));
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
		vbox.getChildren().add(2, accordion);
	}
	/*
	 * handleCreateStorage-funktiossa määritetään, mitä tapahtuu, kun käyttäjä painaa "Create New Storage"-painiketta
	 */
	@FXML
	private void handleCreateStorage() {
		//luodaan väliaikainen varasto-olio, ja annetaan se parametrinä showNewStorage funktiolle, joka palauttaa boolean arvon riippuen siitä luotiinko varastoa
		Storage tempStorage = new Storage();

		boolean isOkClicked = mainApp.showNewStorageDialog(tempStorage);
		if (isOkClicked) {
			mainApp.getStorages().add(tempStorage);
			try {
				Accordion tempAccordion = (Accordion) vbox.getChildren().get(2);
				vbox.getChildren().remove(2);
			}catch(Exception e){	
			}			
			loadStorages(this.vbox);
		}
		System.out.println("New Storage Created!");
	}
	
	@FXML
	private void handleRemoveStorage() {
		
	}
	
	@FXML
	private void handleShowStorageLayout() {
		System.out.println("Storage Layout");
	}
	
	@FXML
	private void handleShowStorageEdit() {
		System.out.println("Edit Storage");
	}
	
	@FXML
	private void handleExit() {
		System.exit(0);
	}
}

