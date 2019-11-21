package Ryhma7.ULI_9000.controller;

import javafx.stage.Stage;

public class PopupController {
	private Stage dialogStage;
	private Item item;
	/**Sets the dialog stage for the controller
	 * @param dialogStage is the stage currently in use
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	public void setItem(Item item) {
		this.item = item;
	}
}
