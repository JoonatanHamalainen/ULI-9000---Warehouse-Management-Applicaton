package Ryhma7.ULI_9000.controller;

import Ryhma7.ULI_9000.model.Item;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	/**Sets item for the Controller and updates the item's attributes to the ones given in the interface
	 * @param item
	 */
	public void setItem(Item item) {
		this.item = item;		
		this.item.setName(name.getText());
		try {
			this.item.setWeight(Integer.parseInt(weight.getText()));
			this.item.setAmount(Integer.parseInt(amount.getText()));
			this.item.setUnitprice(Double.parseDouble(unitPrice.getText()));
			this.item.setSalesprice(Double.parseDouble(salesPrice.getText()));
		}catch(NumberFormatException e) {
			System.out.println(e);
		}
		
	}
	
	/**Returns isOkClicked attribute
	 * @return
	 */
	public boolean getIsOkClicked() {
		return this.isOkClicked;
	}
	
	/**Handler function for the User Interface
	 * changes the  isOkClicked attribute to true and closes the dialogstage
	 */
	@FXML
	private void handleCreateNewItem() {
		setItem(item);
		this.isOkClicked = true;
		this.dialogStage.close();
	}
	
	/**Handler function for the User Interface
	 * 
	 */
	@FXML
	private void handleCancel() {
		dialogStage.close();
		System.out.println("Cancel");
	}
}


