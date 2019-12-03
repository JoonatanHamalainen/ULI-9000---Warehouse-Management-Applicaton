package Ryhma7.ULI_9000.controller;

import Ryhma7.ULI_9000.model.Item;
import Ryhma7.ULI_9000.model.Shelf;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
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
	private Spinner<Integer> amount;
	@FXML
	private Label weight;
	
	private Stage popupStage;
	
	/**Sets the dialog stage for the controller
	 * @param dialogStage is the stage currently in use
	 */
	public void setPopupStage(Stage popupStage) {
		this.popupStage =popupStage;
	}
	
	/**Retrieves the shelf information for popup-window
	 * 
	 * @param shelf
	 */
	public void setShelf(Shelf shelf) {
		this.shelfID.setText(Integer.toString(shelf.getShelfID()));
		Item tempItem = shelf.getItem();
		if(tempItem != null) {
			this.itemName.setText(tempItem.getName());
			this.salesprice.setText(Double.toString(tempItem.getSalesprice()));
			this.unitPrice.setText(Double.toString(tempItem.getUnitprice()));
			System.out.println(tempItem.getAmount());
			this.amount.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, tempItem.getAmount()));
			this.amount.valueProperty().addListener(new ChangeListener<Integer>() {

				@Override
				public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
					System.out.println("Kikkelbyr!!!");
					// TODO Auto-generated method stub
					
				}
				
			});
			this.weight.setText(Integer.toString(tempItem.getWeight()));
		}	
	}
	/**Closes the popup-window when it is no no longer focused
	 * 
	 */
	public void close() {
		if(popupStage.isFocused() == false) {
			
		}
	}
}
