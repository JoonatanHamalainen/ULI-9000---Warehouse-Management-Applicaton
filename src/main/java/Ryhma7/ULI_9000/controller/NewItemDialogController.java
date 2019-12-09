package Ryhma7.ULI_9000.controller;

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
	
	/**Sets the dialog stage for the controller
	 * @param dialogStage is the stage currently in use
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	/**Sets item for the Controller and updates the item's attributes to the ones given in the interface
	 * @param item is the item that is being created
	 * @return 
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
	
	/**Verifies the content of the input fields and gives a warning to the user if there is erraneous inputs
	 * 
	 * @return true if the inputs are acceptable
	 */
	private boolean verifyInput() {
		
		Alert alert = new Alert(AlertType.WARNING);
		boolean pass = true;
		String errorMessage="Please insert proper values to:";
		
		if((name.getText().replace("\\s", "")).length() < 1) {
			errorMessage += "\nName (length must be greater than 0)";
			pass = false;
		}
		
		try {
			int temp = Integer.parseInt(weight.getText());
			if(temp < 0) {
				errorMessage += "\nWeight (integer of value 0 or greater)";
				pass = false;
			}
		}catch(Exception e) {
			errorMessage += "\nWeight (integer)";
			pass = false;
		}
			
		try {
			int temp = Integer.parseInt(amount.getText());
			if(temp < 0) {
				errorMessage += "\nWeight (integer of value 0 or greater)";
				pass = false;
			}
		}catch(Exception e) {
			errorMessage += "\nAmount (integer)";
			pass = false;
		}
		
		try {
			Double temp = Double.parseDouble(unitPrice.getText());
			if(temp < 0) {
				errorMessage += "\nWeight (double of value 0 or greater)";
				pass = false;
			}
		}catch(Exception e) {
			errorMessage += "\nUnit price (double)";
			pass = false;
		}
		
		try {
			Double temp = Double.parseDouble(salesPrice.getText());
			if(temp < 0) {
				errorMessage += "\nWeight (double of value 0 or greater)";
				pass = false;
			}
		}catch(Exception e) {
			errorMessage += "\nSalesPrice (double)";
			pass = false;
		}
		
		if(pass == false) {
			alert.setContentText(errorMessage);
			alert.show();
			return false;
		}
		return pass;
	}
}


