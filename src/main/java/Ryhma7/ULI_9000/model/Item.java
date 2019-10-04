package Ryhma7.ULI_9000.model;

public class Item {
	
	public Item() {
		
	}
	
	private String name;
	private int weight, amount, coordinateX, coordinateY;
	
	private double salesprice, unitprice;
	private int itemID;
	
	public void setName(String name) {
		this.name = name;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public Item(int itemID, String name, int weight, int amount, int coordinateX, int coordinateY, double salesprice,
			double unitprice) {
		super();
		this.name = name;
		this.weight = weight;
		this.amount = amount;
		this.coordinateX = coordinateX;
		this.coordinateY = coordinateY;
		this.salesprice = salesprice;
		this.unitprice = unitprice;
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

	public int getItemID() {
		return itemID;
	}

	public int getCoordinateX() {
		return coordinateX;
	}

	public void setCoordinateX(int coordinateX) {
		this.coordinateX = coordinateX;
	}

	public int getCoordinateY() {
		return coordinateY;
	}

	public void setCoordinateY(int coordinateY) {
		this.coordinateY = coordinateY;
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
	
	public void increaseAmount(int itemID, int amount) {
		this.amount += amount;
	}
	
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
