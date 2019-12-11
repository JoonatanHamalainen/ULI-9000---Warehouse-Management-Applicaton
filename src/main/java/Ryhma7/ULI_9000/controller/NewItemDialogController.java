package Ryhma7.ULI_9000.controller;

import java.util.ResourceBundle;

import Ryhma7.ULI_9000.App;
import Ryhma7.ULI_9000.model.Item;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**Controller for NewItemDialog
 *
 */
public class NewItemDialogController {

	@FXML
	private TextField name;
	@FXML
	private TextField weight;
	@FXML
	private TextField amount;
	@FXML
	private TextField salesPrice;
	@FXML
	private TextField unitPrice;

	private Stage dialogStage;
	private Item item;
	private boolean isOkClicked = false;
	private App mainApp;
	
	/**Sets the dialog stage for the controller
	 * @param dialogStage is the stage currently in use
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	/**Sets item for the Controller and updates the item's attributes to the ones given in the interface
	 * @return true if successful
	 */
	public boolean verifyItem() {
		boolean pass = false;		
		if(verifyInput()) {
			this.item.setName(name.getText());
			this.item.setAmount(Integer.parseInt(amount.getText()));
			this.item.setHighestAmount(Integer.parseInt(amount.getText()));
			this.item.setUnitprice(Double.parseDouble(unitPrice.getText()));
			this.item.setSalesprice(Double.parseDouble(salesPrice.getText()));
			this.item.setWeight(Integer.parseInt(weight.getText()));
			pass = true;
		}
		return pass;
	}
	/**
	 * Sets item
	 * @param item
	 */
	public void setItem(Item item) {
		this.item = item;	
	}
	
	/**Returns isOkClicked attribute
	 * @return boolean
	 */
	public boolean getIsOkClicked() {
		return this.isOkClicked;
	}
	
	/**Handler function for the User Interface
	 * changes the  isOkClicked attribute to true and closes the dialogstage
	 */
	@FXML
	private void handleCreateNewItem() {
		if(verifyItem()) {
			this.isOkClicked = true;
			this.dialogStage.close();
		};	
	}
	
	/**Handler function for the User Interface
	 * 
	 */
	@FXML
	private void handleCancel() {
		dialogStage.close();
		System.out.println("Cancel");
	}
	
	/**Verifies the content of the input fields and gives a warning to the user if there is erroneous inputs
	 * 
	 * @return true if the inputs are acceptable
	 */
	private boolean verifyInput() {
		
		ResourceBundle bundle = mainApp.getResourceBundle();
		Alert alert = new Alert(AlertType.WARNING);
		boolean pass = true;
		String errorMessage = bundle.getString("warning.pleaseinsert");
		
		if((name.getText().replace("\\s", "")).length() < 1) {
			errorMessage += bundle.getString("warning.name");
			pass = false;
		}
		
		try {
			int temp = Integer.parseInt(weight.getText());
			if(temp < 0) {
				errorMessage += bundle.getString("warning.weight1");
				pass = false;
			}
		}catch(Exception e) {
			errorMessage += bundle.getString("warning.weight2");
			pass = false;
		}
			
		try {
			int temp = Integer.parseInt(amount.getText());
			if(temp < 0) {
				errorMessage += bundle.getString("warning.weight1");
				pass = false;
			}
		}catch(Exception e) {
			errorMessage += bundle.getString("warning.amount");
			pass = false;
		}
		
		try {
			Double temp = Double.parseDouble(unitPrice.getText());
			if(temp < 0) {
				errorMessage += bundle.getString("warning.weightd");
				pass = false;
			}
		}catch(Exception e) {
			errorMessage += bundle.getString("warning.unitprice");
			pass = false;
		}
		
		try {
			Double temp = Double.parseDouble(salesPrice.getText());
			if(temp < 0) {
				errorMessage += bundle.getString("warning.weightd");
				pass = false;
			}
		}catch(Exception e) {
			errorMessage += bundle.getString("warning.salesprice");
			pass = false;
		}
		
		if(pass == false) {
			alert.setContentText(errorMessage);
			alert.show();
			return false;
		}
		return pass;
	}
	/**Sets the mainApp for the controller
	 * @param mainApp is the main application
	 */
	public void setMainApp(App mainApp) {
		this.mainApp = mainApp;
	}
}


