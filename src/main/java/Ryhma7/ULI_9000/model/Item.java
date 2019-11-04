package Ryhma7.ULI_9000.model;

/**
 * Class for model Item
 *
 */
public class Item {
	
	/**
	 * Empty constructor
	 */
	public Item() {
		
	}
	
	private String name, itemNumber;

	private int itemID, weight, amount, highestAmount = 0, shelfID, storageID;
	
	/**
	 * @return returns itemID of Item-object
	 */
	public int getItemID() {
		return itemID;
	}

	/**
	 * @param itemID sets itemID of Item-object
	 */
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	private double salesprice, unitprice;
	
	/**
	 * @param name sets name of Item-object
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return returns itemNumber of Item-object
	 */
	public String getItemNumber() {
		return itemNumber;
	}

	/**
	 * @param itemNumber sets itemNumber of Item-object
	 */
	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	/**
	 * @param weight sets weight of Item-object
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

	/**
	 * 
	 * Constructor creates a new item with given parameters
	 * 
	 * @param name describes the name being set
	 * @param weight describes the weight being set
	 * @param amount describes the amount being set
	 * @param salesprice describes the salesprice being set
	 * @param unitprice describes the unitprice being set
	 * @param shelfID describes the shelfID being set
	 * @param storageID describes the storageID being set
	 */
	/**
	 * @param name sets name of Item-object
	 * @param weight sets weight of Item-object
	 * @param amount sets amount of Item-object
	 * @param salesprice sets salesprice of Item-object
	 * @param unitprice sets unitprice of Item-object
	 * @param shelfID sets shelfID of Item-object
	 * @param storageID sets storageID of Item-object
	 * @param highestAmount sets the highestAmount of Item-object
	 */
	public Item(String name, int weight, int amount, double salesprice, double unitprice, int shelfID, int storageID) {
		super();
		this.name = name;
		this.weight = weight;
		this.amount = amount;
		this.salesprice = salesprice;
		this.unitprice = unitprice;
		this.shelfID = shelfID;
		this.storageID = storageID;
		this.highestAmount = amount;
	}

	/**
	 * @return returns highestAmount of Item-object
	 */
	public int getHighestAmount() {
		return highestAmount;
	}

	/**
	 * @return returns name of Item-object
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return returns weight of Item-object
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * @return returns amount of Item-object
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param amount sets amount of Item-object
	 */
	public void setAmount(int amount) {
		this.amount = amount;
		if(amount > highestAmount) {
			highestAmount = amount;
		}
	}

	/**
	 * @return returns shelfID of Item-object
	 */
	public int getShelfID() {
		return shelfID;
	}

	/**
	 * @param shelfID sets shelfID of Item-object
	 */
	public void setShelfID(int shelfID) {
		this.shelfID = shelfID;
	}

	/**
	 * @return returns storageID of Item-object
	 */
	public int getStorageID() {
		return storageID;
	}

	/**
	 * @param storageID sets storageID of Item-object
	 */
	public void setStorageID(int storageID) {
		this.storageID = storageID;
	}

	/**
	 * @return returns salesprice of Item-object
	 */
	public double getSalesprice() {
		return salesprice;
	}	

	/**
	 * @param salesprice sets salesprice of Item-object
	 */
	public void setSalesprice(double salesprice) {
		this.salesprice = salesprice;
	}

	/**
	 * @return returns unitprice of Item-object
	 */
	public double getUnitprice() {
		return unitprice;
	}

	/**
	 * @param unitprice sets unitprice of Item-object
	 */
	public void setUnitprice(double unitprice) {
		this.unitprice = unitprice;
	}
	/**
	 * Method for increasing the amount variable
	 * 
	 * @param itemID is used to identify the item whose amount needs to be increased
	 * @param amount is used to identify how much item amount needs to be increased
	 */
	public void increaseAmount(int itemID, int amount) {
		this.amount += amount;
		if(this.amount > highestAmount) {
			highestAmount = this.amount;
		}
	}
	/**
	 * Method for decreasing the amount variable
	 * 
	 * @param itemID is used to identify the item whose amount needs to be decreased
	 * @param amount is used to identify how much item amount needs to be decreased
	 * @return returns false if amount is tried to be decreased more than there is in stock. Otherwise it returns true.
	 */
	public boolean decreaseAmount(int itemID, int amount) {
		//Jos koittaa vähentää enemmän kuin tuotetta on, palauttaa false.
		if(this.amount < amount) {
			return false;
		} else {
			this.amount -= amount;
			return true;
		}
	}
}
