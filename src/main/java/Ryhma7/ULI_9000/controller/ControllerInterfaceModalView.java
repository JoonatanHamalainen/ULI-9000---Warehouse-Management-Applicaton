package Ryhma7.ULI_9000.controller;

import Ryhma7.ULI_9000.model.Item;
import javafx.stage.Stage;

public interface ControllerInterfaceModalView {
	
	boolean isOkClicked = false;
	
	public void setDialogStage(Stage dialogStage);
	
	public boolean getIsOkClicked();
	
}
