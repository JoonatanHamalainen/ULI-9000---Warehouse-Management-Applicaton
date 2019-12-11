package Ryhma7.ULI_9000.controller;

import Ryhma7.ULI_9000.model.Storage;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
/**Controller for NewStorageDialog
*
*/
public class NewStorageDialogController implements ControllerInterfaceModalView {

	@FXML
	private TextField name;
	@FXML
	private TextField address;
	@FXML
	private TextField width;
	@FXML
	private TextField length;

	private Stage dialogStage;
	private Storage storage;
	private boolean isOkClicked = false;
	
	/**Sets the dialogStage for the controller
	 * @param dialogStage is the current stage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	/**Sets the storage for the controller
	 * @param storage is the new storage being created
	 */
	public void setStorage(Storage storage) {
		this.storage = storage;
	}
	
	/**Sets storage for the Controller and updates the storage's attributes to the ones given in the interface
	 * @return true if successful
	 */
	private boolean verifyStorage() {
		boolean pass = false;
		if(verifyInput()) {
			this.storage.setName(name.getText());
			this.storage.setAddress(address.getText());
			this.storage.setDimensions(Integer.parseInt(width.getText()), Integer.parseInt(length.getText()));
			pass = true;
		}
		return pass;
	}
	
	/**Returns the value of isOkClicked
	 * 
	 */
	public boolean getIsOkClicked() {
		return this.isOkClicked;
	}
	
	/**Handler function for the user interface
	 * sets isOkClicked value to true
	 */
	@FXML
	private void handleCreateNewStorage() {
		if(verifyStorage()) {
			this.isOkClicked = true;
			this.dialogStage.close();
		}
	}
	
	/**Handler function for the user interface
	 * Closes the DialogStage
	 */
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}
	
	/**Verifies the content of the input fields and gives a warning to the user if there is erroneous inputs
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
		
		if((address.getText().replace("\\s", "")).length() < 1) {
			errorMessage += "\nAddress (length must be greater than 0)";
			pass = false;
		}
		
		try {
			int temp = Integer.parseInt(width.getText());
			if(temp < 1) {
				errorMessage += "\nWidth (integer of value 1 or greater)";
				pass = false;
			}
		}catch(Exception e) {
			errorMessage += "\nWidth (integer)";
			pass = false;
		}
			
		try {
			int temp = Integer.parseInt(length.getText());
			if(temp < 1) {
				errorMessage += "\nLength (integer of value 1 or greater)";
				pass = false;
			}
		}catch(Exception e) {
			errorMessage += "\nLength (integer)";
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
