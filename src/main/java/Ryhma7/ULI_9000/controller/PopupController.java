package Ryhma7.ULI_9000.controller;

import Ryhma7.ULI_9000.model.Item;
import Ryhma7.ULI_9000.model.Shelf;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PopupController {
	@FXML
	private Label shelfID;
	@FXML
	private Label itemName;
	@FXML
	private Label salesprice;
	@FXML
	private Label unitPrice;
	@FXML
	private Label amount;
	@FXML
	private Label weight;
	
	private Stage popupStage;
	
	/**Sets the dialog stage for the controller
	 * @param dialogStage is the stage currently in use
	 */
	public void setPopupStage(Stage popupStage) {
		this.popupStage =popupStage;
	}
	
	public void setShelf(Shelf shelf) {
		this.shelfID.setText(Integer.toString(shelf.getShelfID()));
		Item tempItem = shelf.getItem();
		if(tempItem != null) {
			this.itemName.setText(tempItem.getName());
			this.salesprice.setText(Double.toString(tempItem.getSalesprice()));
			this.unitPrice.setText(Double.toString(tempItem.getUnitprice()));
			this.amount.setText(Integer.toString(tempItem.getAmount()));
			this.weight.setText(Integer.toString(tempItem.getWeight()));
		}	
	}
	
	public void close() {
		if(popupStage.isFocused() == false) {
			
		}
	}
}
