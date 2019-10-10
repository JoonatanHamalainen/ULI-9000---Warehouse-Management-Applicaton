package Ryhma7.ULI_9000.controller;

import Ryhma7.ULI_9000.model.Storage;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
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
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	/**Sets the storage for the controller
	 * @param storage
	 */
	public void setStorage(Storage storage) {
		this.storage = storage;
		
		this.storage.setName(name.getText());
		this.storage.setAddress(address.getText());
		try {
			this.storage.setDimensions(Integer.parseInt(width.getText()), Integer.parseInt(length.getText()));
		}catch(NumberFormatException error) {
			System.out.print(error);
		}
		
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
		setStorage(storage);
		this.isOkClicked = true;
		this.dialogStage.close();
	}
	
	/**Handler function for the user interface
	 * Closes the DialogStage
	 */
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}
}
