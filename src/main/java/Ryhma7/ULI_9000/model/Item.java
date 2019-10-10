package Ryhma7.ULI_9000.model;

public class Item {
	
	public Item() {
		
	}
	
	private String name, itemNumber;
	private int itemID, weight, amount, shelfID, storageID;
	
	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	private double salesprice, unitprice;
	
	public void setName(String name) {
		this.name = name;
	}

	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	/**
	 * 
	 * Constructor creates a new item with given parameters
	 * 
	 * @param name
	 * @param weight
	 * @param amount
	 * @param salesprice
	 * @param unitprice
	 * @param shelfID
	 * @param storageID
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
	}

	public String getName() {
		return name;
	}

	public int getWeight() {
		return weight;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getShelfID() {
		return shelfID;
	}

	public void setShelfID(int shelfID) {
		this.shelfID = shelfID;
	}

	public int getStorageID() {
		return storageID;
	}

	public void setStorageID(int storageID) {
		this.storageID = storageID;
	}

	public double getSalesprice() {
		return salesprice;
	}	

	public void setSalesprice(double salesprice) {
		this.salesprice = salesprice;
	}

	public double getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(double unitprice) {
		this.unitprice = unitprice;
	}
	/**
	 * Method for increasing the amount variable
	 * 
	 * @param itemID
	 * @param amount
	 */
	public void increaseAmount(int itemID, int amount) {
		this.amount += amount;
	}
	/**
	 * Method for decreasing the amount variable
	 * 
	 * @param itemID
	 * @param amount
	 * @return
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
