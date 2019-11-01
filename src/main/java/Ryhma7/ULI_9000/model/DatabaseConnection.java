package Ryhma7.ULI_9000.model;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

/**
 * Class used for adding information to database tables and creating database queries
 *
 */
public class DatabaseConnection {

	public static SessionFactory factory;
	
	/**
	 * Constructor used for creating a connection to hibernate
	 */
	public DatabaseConnection() {
		
		try {
	         factory = new Configuration().configure().buildSessionFactory();
	      } catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
	}
	public Item getItem(int itemID) {
		Session session = factory.openSession();
	    Transaction tx = null;
	    Item tempItem = null;
	    
	    try {
	    	tx = session.beginTransaction();
	    	String hql = ("FROM Item WHERE itemID = :itemID");
	    	Query query = session.createQuery(hql);
	    	query.setParameter("itemID", itemID);
	    	List item = query.list();
	        System.out.println(item);
	        tempItem = (Item) item.get(0);
	    } catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		      } finally {
		         session.close(); 
		      }
	    
		return tempItem;
	}
	/**
	 * Gets the specified shelf from database
	 * 
	 * @param n is used to identify the Point (x,y) of the shelf that needs to be returned
	 * @param storageID is used to identify the storage, the shelf needs to be returned from
	 * @return returns the specified shelf
	 */
	public Shelf getShelf(Point n, int storageID) {
		Session session = factory.openSession();
	    Transaction tx = null;
	    int coordinateX = (int) n.getX();
	    int coordinateY = (int) n.getY();
	    
	    Shelf tempShelf = null;
	      
	      try {
	         tx = session.beginTransaction();
	         String hql = ("FROM Shelf WHERE storageID = :storageID AND coordinateX = :coordinateX AND coordinateY = :coordinateY");
	         Query query = session.createQuery(hql);
	         query.setParameter("storageID", storageID);
	         query.setParameter("coordinateX", coordinateX);
	         query.setParameter("coordinateY", coordinateY);
	         List shelf = query.list();
	         System.out.println(shelf);
	         tempShelf = (Shelf) shelf.get(0);
	         tempShelf.setCellCoordinates(new Point(tempShelf.getCoordinateX(), tempShelf.getCoordinateY()));
	         
	      } catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		      } finally {
		         session.close(); 
		      }
		
		return tempShelf;
	}
	/**
	 * Gets the specified storage from database
	 * 
	 * @param name is used to identify the storage that needs to be returned
	 * @return returns the storage specified
	 */
	public Storage getStorage(String name) {
		Session session = factory.openSession();
	    Transaction tx = null;
	    Storage tempStorage = null;
	      
	      try {
	         tx = session.beginTransaction();
	         String hql = ("FROM Storage WHERE name = :name");
	         Query query = session.createQuery(hql);
	         query.setParameter("name", name);
	         List storage = query.list();
	         tempStorage = (Storage) storage.get(0);
	         
	      } catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		      } finally {
		         session.close(); 
		      }
		
		return tempStorage;
	}
	/**
	 * Creates a new item and adds it to database
	 * 
	 * @param item is used to add the item to database
	 */
	public void addItem(Item item) {
	      Session session = factory.openSession();
	      Transaction tx = null;
	      Integer itemID = null;
	      
	      try {
	         tx = session.beginTransaction();
	         itemID = (Integer) session.save(item); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	   }
	/**
	 * Creates a new storage and adds it to database
	 * 
	 * @param shelf is used to add the shelf to database
	 */
	public void addShelf(Shelf shelf) {
	      Session session = factory.openSession();
	      Transaction tx = null;
	      Integer shelfID = null;
	      shelf.setCoordinateX((int) shelf.getCellCoordinates().getX());
	      shelf.setCoordinateY((int) shelf.getCellCoordinates().getY());
	      
	      try {
	         tx = session.beginTransaction();
	         shelfID = (Integer) session.save(shelf); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	   }
	/**
	 * Creates a new storage and adds it to database
	 * 
	 * @param storage is used to add the storage to database
	 */
	public void addStorage(Storage storage) {
	      Session session = factory.openSession();
	      Transaction tx = null;
	      Integer storageID = null;
	      
	      try {
	         tx = session.beginTransaction();
	         storageID = (Integer) session.save(storage); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	   }
	public void addItemToShelf(Item item, Shelf shelf) {
		Session session = factory.openSession();
	      Transaction tx = null;
	      int itemID = item.getItemID();
	      int shelfID = shelf.getShelfID();
	      
	      try {
	    	  tx = session.beginTransaction();
		         String hql = "UPDATE Item set shelfID = :shelfID WHERE itemID = :itemID";
		         Query query = session.createQuery(hql);
		         query.setParameter("shelfID", shelfID);
		         query.setParameter("itemID", itemID);
		         int result = query.executeUpdate(); 
		         System.out.println("Rows affected: " + result);
		      } catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		      } finally {
		         session.close(); 
		      }
		   }
	public void deleteItemFromShelf(Item item) {
		Session session = factory.openSession();
	      Transaction tx = null;
	      int itemID = item.getItemID();
	      int shelfID = 0;
	      
	      try {
	    	  tx = session.beginTransaction();
		         String hql = "UPDATE Item set shelfID = :shelfID WHERE itemID = :itemID";
		         Query query = session.createQuery(hql);
		         query.setParameter("shelfID", shelfID);
		         query.setParameter("itemID", itemID);
		         int result = query.executeUpdate(); 
		         System.out.println("Rows affected: " + result);
		      } catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		      } finally {
		         session.close(); 
		      }
		   }
	/**
	 * Method for updating item name with a new one
	 * 
	 * @param itemID is used to identify what item needs to be modified
	 * @param name is used to identify the attribute that needs to be modified
	 */
	public void updateName(int itemID, String name){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      
	      try {
	    	  tx = session.beginTransaction();
		         String hql = "UPDATE Item set name = :name WHERE itemID = :itemID";
		         Query query = session.createQuery(hql);
		         query.setParameter("name", name);
		         query.setParameter("itemID", itemID);
		         int result = query.executeUpdate(); 
		         System.out.println("Rows affected: " + result);
		      } catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		      } finally {
		         session.close(); 
		      }
		   }
	/**
	 * Method for updating item location with a new one
	 * 
	 * @param itemID is used to identify what item needs to be modified
	 * @param shelfID is used to identify within what shelf item needs to be modified
	 * @param storageID is used to identify within what storage item needs to be modified
	 */
	public void updateLocation(int itemID, int shelfID, int storageID){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      
	      try {
	    	  tx = session.beginTransaction();
		      String hql = "UPDATE Item set shelfID = :shelfID, storageID = :storageID WHERE itemID = :itemID";
		      Query query = session.createQuery(hql);
		      query.setParameter("shelfID", shelfID);
		      query.setParameter("storageID", storageID);
		      query.setParameter("itemID", itemID);
		      int result = query.executeUpdate(); 
		      System.out.println("Rows affected: " + result);
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	   }
	/**
	 * Method for updating item weight with a new one
	 * 
	 * @param itemID is used to identify what item needs to be modified
	 * @param weight is used to identify the attribute that needs to be modified
	 */
	public void updateWeight(int itemID, int weight){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      
	      try {
	    	  tx = session.beginTransaction();
		         String hql = "UPDATE Item set weight = :weight WHERE itemID = :itemID";
		         Query query = session.createQuery(hql);
		         query.setParameter("weight", weight);
		         query.setParameter("itemID", itemID);
		         int result = query.executeUpdate(); 
		         System.out.println("Rows affected: " + result);
		      } catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		      } finally {
		         session.close(); 
		      }
		   }
	/**
	 * Method for updating item salesprice with a new one
	 * 
	 * @param itemID is used to identify what item needs to be modified
	 * @param salesprice is used to identify the attribute that needs to be modified
	 */
	public void updateSalesprice(int itemID, double salesprice){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      
	      try {
	    	  tx = session.beginTransaction();
		         String hql = "UPDATE Item set salesprice = :salesprice WHERE itemID = :itemID";
		         Query query = session.createQuery(hql);
		         query.setParameter("salesprice", salesprice);
		         query.setParameter("itemID", itemID);
		         int result = query.executeUpdate(); 
		         System.out.println("Rows affected: " + result);
		      } catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		      } finally {
		         session.close(); 
		      }
		   }
	/**
	 * Method for updating item unitprice with a new one
	 * 
	 * @param itemID is used to identify what item needs to be modified
	 * @param unitprice is used to identify the attribute that needs to be modified
	 */
	public void updateUnitprice(int itemID, double unitprice){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         String hql = "UPDATE Item set unitprice = :unitprice WHERE itemID = :itemID";
	         Query query = session.createQuery(hql);
	         query.setParameter("unitprice", unitprice);
	         query.setParameter("itemID", itemID);
	         int result = query.executeUpdate(); 
	         System.out.println("Rows affected: " + result);
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	   }
	public int getAmount(int itemID) {
		Session session = factory.openSession();
	    Transaction tx = null;
	    int amount = -1;
	      
	      try {
	    	  tx = session.beginTransaction();
	    	  String hql = ("FROM Item WHERE shelfID = :shelfID AND storageID = :storageID");
	    	  Query query = session.createQuery(hql);
	    	  query.setParameter("itemID", itemID);
	    	  List result = query.list();
	    	  amount = (Integer) result.get(0);
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	      
	      return amount;
	}
	/**
	 * Method for updating item amount with a new one
	 * 
	 * @param itemID is used to identify what item needs to be modified
	 * @param amount is used to identify the attribute that needs to be modified
	 */
	public void updateAmount(int itemID, int amount){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         String hql = "UPDATE Item set amount = :amount WHERE itemID = :itemID";
	         Query query = session.createQuery(hql);
	         query.setParameter("amount", amount);
	         query.setParameter("itemID", itemID);
	         int result = query.executeUpdate(); 
	         System.out.println("Rows affected: " + result);
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	   }
	/**
	 * Method for updating storage address with a new one
	 * 
	 * @param storageID is used to identify what storage needs to be modified
	 * @param address is used to identify the attribute that needs to be modified
	 */
	public void updateStorageAddress(int storageID, String address) {
		Session session = factory.openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         String hql = "UPDATE Storage set address = :address WHERE storageID = :storageID";
	         Query query = session.createQuery(hql);
	         query.setParameter("address", address);
	         query.setParameter("storageID", storageID);
	         int result = query.executeUpdate(); 
	         System.out.println("Rows affected: " + result);
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	   }
	/**
	 * Method for updating storage width with a new one
	 * 
	 * @param storageID is used to identify what storage needs to be modified
	 * @param width is used to identify the attribute that needs to be modified
	 */
	public void updateStorageWidth(int storageID, int width) {
		Session session = factory.openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         String hql = "UPDATE Storage set width = :width WHERE storageID = :storageID";
	         Query query = session.createQuery(hql);
	         query.setParameter("width", width);
	         query.setParameter("storageID", storageID);
	         int result = query.executeUpdate(); 
	         System.out.println("Rows affected: " + result);
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	   }
	/**
	 * Method for updating storage length with a new one
	 * 
	 * @param storageID is used to identify what storage needs to be modified
	 * @param length is used to identify the attribute that needs to be modified
	 */
	public void updateStorageLength(int storageID, int length) {
		Session session = factory.openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         String hql = "UPDATE Storage set length = :length WHERE storageID = :storageID";
	         Query query = session.createQuery(hql);
	         query.setParameter("length", length);
	         query.setParameter("storageID", storageID);
	         int result = query.executeUpdate(); 
	         System.out.println("Rows affected: " + result);
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	   }
	/**
	 * Method for deleting an item
	 * 
	 * @param item is used to identify what item needs to be deleted
	 */
	public void deleteItem(Item item){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         session.delete(item); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	   }
	public void deleteShelf(Shelf shelf){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         session.delete(shelf); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	   }
	/**
	 * Method for deleting a storage
	 * 
	 * @param storage is used to identify what storage needs to be deleted
	 */
	public void deleteStorage(Storage storage) {
		Session session = factory.openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         session.delete(storage); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	}
	/**
	 * Method for getting all items within specified shelf
	 * 
	 * @param shelfID is used to identify the shelf, the items are wanted from.
	 * @param storageID is used to identify the storage, the items are wanted from.
	 */
	public void getItemsInShelf(int shelfID, int storageID) {
		Session session = factory.openSession();
	    Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         String hql = ("FROM Item WHERE shelfID = :shelfID AND storageID = :storageID");
	         Query query = session.createQuery(hql);
	         query.setParameter("shelfID", shelfID);
	         query.setParameter("storageID", storageID);
	         List items = query.list();
	         for (Iterator iterator = items.iterator(); iterator.hasNext();){
	            Item item = (Item) iterator.next(); 
	            System.out.print("Name: " + item.getName()); 
	            System.out.print("  Amount: " + item.getAmount());
	            System.out.print("  Weight: " + item.getWeight());
	            System.out.print("  Salesprice: " + item.getSalesprice());
	            System.out.println("  Unitprice: " + item.getUnitprice());
	         }
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	      }
	/**
	 * Method for getting all shelves within specified storage
	 * 
	 * @param storageID is used to identify the storage, the shelves are wanted from.
	 */
	public ArrayList<Shelf> getShelvesInStorage(Storage storage) {
		Session session = factory.openSession();
	    Transaction tx = null;
	    int storageID = storage.getStorageID();
	    ArrayList<Shelf> shelves = null;
	      
	      try {
	         tx = session.beginTransaction();
			List list = session.createQuery("FROM Shelf WHERE storageID = :storageID").setParameter("storageID", storageID).list();
			for (Iterator iterator = list.iterator(); iterator.hasNext();){
	            Shelf shelf = (Shelf) iterator.next();
	            shelf.setCellCoordinates(new Point(shelf.getCoordinateX(), shelf.getCoordinateY()));    
	         }
			shelves = (ArrayList<Shelf>) list;
			
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }	return shelves;
	}
	/**
	 * Method for listing all items in database
	 * 
	 */
	public void listItems(){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         List items = session.createQuery("FROM Item").list(); 
	         for (Iterator iterator = items.iterator(); iterator.hasNext();){
	            Item item = (Item) iterator.next(); 
	            System.out.println();
	            System.out.print("Name: " + item.getName()); 
	            System.out.print("  Amount: " + item.getAmount());
	            System.out.print("  Salesprice: " + item.getSalesprice());
	            System.out.println("  Unitprice: " + item.getUnitprice());
	            System.out.print("  Weight: " + item.getWeight());
	            System.out.print("  ShelfID: " + item.getShelfID());
	            System.out.print("  StorageID: " + item.getStorageID());
	            System.out.println();
	            
	         }
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	   }
	/**
	 * Method for listing all shelves in database
	 * 
	 */
	public void listShelves() {
		Session session = factory.openSession();
	    Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         List shelves = session.createQuery("FROM Shelf").list();
	         for (Iterator iterator = shelves.iterator(); iterator.hasNext();){
	            Shelf shelf = (Shelf) iterator.next(); 
	            System.out.println();
	            System.out.println("ShelfID: " + shelf.getShelfID());
	            System.out.print("  Capacity: " + shelf.getCapacity()); 
	            System.out.print("  Coordinate X: " + shelf.getCoordinateX());
	            System.out.print("  Coordinate Y: " + shelf.getCoordinateY());
	            System.out.print("  StorageID: " + shelf.getStorageID());
	            System.out.println();
	            
	         }
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	}
	/**
	 * Gets all storages from the database
	 * 
	 * @return returns arrayList of Storages within the database.
	 */
	public ArrayList<Storage> getStorages() {
		Session session = factory.openSession();
	    Transaction tx = null;
	    ArrayList<Storage> storages = null;
	      
	      try {
	         tx = session.beginTransaction();
			storages = (ArrayList<Storage>) session.createQuery("FROM Storage").list(); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }	return storages;
	}
	/**
	 * Gets all shelfs from the specified storage from database.
	 * 
	 * @param storageID is used to identify the storage, the shelves are wanted from.
	 * @return returns arrayList of Points from all the shelves in the database.
	 */
	public ArrayList<Point> getShelves(int storageID) {
		Session session = factory.openSession();
	    Transaction tx = null;
	    ArrayList<Point> shelves = null;
	      
	      try {
	        tx = session.beginTransaction();
			List tempShelves = session.createQuery("FROM Shelf WHERE storageID = :storageID").setParameter("storageID", storageID).list();
			for (Iterator iterator = tempShelves.iterator(); iterator.hasNext();){
				Shelf shelf = (Shelf) iterator.next();
				System.out.println(shelf.getCoordinateX());
				System.out.println(shelf.getCoordinateY());
				Point tempPoint = new Point(shelf.getCoordinateX(), shelf.getCoordinateY());
				System.out.println(tempPoint);
				shelves.add(tempPoint);
				System.out.println(shelves.get(0));
			}
	        tx.commit();
	        return shelves;
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close();
	      }
	      return shelves;
	}
	/**
	 * Method for listing all storages in database
	 * 
	 */
	public void listStorages() {
		Session session = factory.openSession();
	    Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         List storages = session.createQuery("FROM Storage").list(); 
	         for (Iterator iterator = storages.iterator(); iterator.hasNext();){
	            Storage storage = (Storage) iterator.next(); 
	            System.out.println();
	            System.out.println("StorageID: " + storage.getStorageID());
	            System.out.print("  Name: " + storage.getName()); 
	            System.out.print("   Address: " + storage.getAddress());
	            System.out.print("  Width: " + storage.getWidth());
	            System.out.print("  Length: " + storage.getLength());
	            System.out.println();
	            
	         }
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	}
	public void increaseAmount (int itemID, int addedAmount) {
		int amount = getAmount(itemID);
		amount += addedAmount;
		updateAmount(itemID, amount);
	}
	public void decreaseAmount (int itemID, int takenAmount) {
		int amount = getAmount(itemID);
		if (amount - takenAmount < 0) {
			System.out.println("Not enough units");
		}
		else {
			amount -= takenAmount;
			updateAmount(itemID, amount);
		}
	}
	}