package Ryhma7.ULI_9000.controller;

import java.util.ResourceBundle;

import Ryhma7.ULI_9000.App;
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
	private App mainApp;
	
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
		
		ResourceBundle bundle = mainApp.getResourceBundle();
		Alert alert = new Alert(AlertType.WARNING);
		boolean pass = true;
		String errorMessage = bundle.getString("warning.pleaseinsert");
		
		if((name.getText().replace("\\s", "")).length() < 1) {
			errorMessage += bundle.getString("warning.name");
			pass = false;
		}
		
		if((address.getText().replace("\\s", "")).length() < 1) {
			errorMessage += bundle.getString("warning.address");
			pass = false;
		}
		
		try {
			int temp = Integer.parseInt(width.getText());
			if(temp < 1) {
				errorMessage += bundle.getString("warning.width1");
				pass = false;
			}
		}catch(Exception e) {
			errorMessage += bundle.getString("warning.width2");
			pass = false;
		}
			
		try {
			int temp = Integer.parseInt(length.getText());
			if(temp < 1) {
				errorMessage += bundle.getString("warning.length1");
				pass = false;
			}
		}catch(Exception e) {
			errorMessage += bundle.getString("warning.length2");
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
