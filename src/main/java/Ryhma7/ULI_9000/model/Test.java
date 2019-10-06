package Ryhma7.ULI_9000.model;

import org.hibernate.SessionFactory;

public class Test {
	public static SessionFactory factory;
	
	public static void main(String[] args) {
	DatabaseConnection connection = new DatabaseConnection(factory);
	connection.listItems();
}

}
