package Ryhma7.ULI_9000.controller;

import Ryhma7.ULI_9000.model.Item;
import javafx.stage.Stage;

/**Controller Interface
 *
 */
public interface ControllerInterfaceModalView {
	
	boolean isOkClicked = false;
	
	/**Setter for the DialogStage
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage);
	
	/**Getter for IsOkClicked attributed
	 * @return
	 */
	public boolean getIsOkClicked();
	
}
