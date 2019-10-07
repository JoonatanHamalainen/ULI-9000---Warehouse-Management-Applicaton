package Ryhma7.ULI_9000.model;

public class Test {
	
	public static void main(String[] args) {
	
	DatabaseConnection connection = new DatabaseConnection();
	
	System.out.println("Listataan tuotteet");
	connection.listItems();
	
	System.out.println();
	System.out.println("Lisataan tuote");
	connection.addItem("1", "Pulla", 10, 900, 1.00, 0.5, 1, 1);
	System.out.println("Lisataan tuote");
	connection.addItem("3", "Viineri", 28, 90, 1.00, 0.9, 2, 2);
	System.out.println("Lisataan tuote");
	connection.addItem("2", "Makkara", 3, 7, 8.99, 4.99, 1, 1);
	
	System.out.println("Haetaan tuotteet 2 hyllysta");
	connection.getItemsInShelf(1, 1);
	
	System.out.println("Vaihdetaan makkaran unitprice");
	connection.updateUnitprice("2", 1000.000);
	
	System.out.println("Haetaan tuotteet hyllysta");
	connection.getItemsInShelf(1, 1);
	
	System.out.println("Vaihdetaan makkaran sijainti hyllyyn 2 varastossa 2");
	connection.updateLocation("2", 2, 2);
	
	System.out.println("Haetaan tuotteet hyllysta (Pitaisi loytya vain alkuperaiset)");
	connection.getItemsInShelf(1, 1);
	
	System.out.println("Poistetaan makkara");
	connection.deleteItem("2");
	
	connection.listItems();
	System.out.println("Poistetaan muutkin");
	connection.deleteItem("1");
	connection.deleteItem("3");
	System.out.println("Pitaisi olla tyhja");
	connection.listItems();
	
	
	
}

}
