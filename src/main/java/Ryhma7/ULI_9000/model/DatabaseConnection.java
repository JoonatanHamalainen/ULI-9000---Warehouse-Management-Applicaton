package Ryhma7.ULI_9000.model;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class DatabaseConnection {
	public static SessionFactory factory;
	
	public DatabaseConnection() {
		
	}
	
	public DatabaseConnection(SessionFactory factory) {
		try {
	         factory = new Configuration().configure().buildSessionFactory();
	      } catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
	}
	public void addItem(int itemID, String name, int weight, int amount, double salesprice, double unitprice, int shelfID, int storageID) {
	      Session session = factory.openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         Item item = new Item(itemID, name, weight, amount, salesprice, unitprice, shelfID, storageID);
	         itemID = (Integer) session.save(item); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	   }
	public void updateName(Integer ItemID, String name){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         Item item = (Item)session.get(Item.class, ItemID); 
	         item.setName(name);
			 session.update(item); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	   }
	public void updateLocation(int itemID, int shelfID, int storageID){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         Item item = (Item)session.get(Item.class, itemID); 
	         item.setShelfID(shelfID);
	         item.setStorageID(storageID);
			 session.update(item); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	   }
	public void updateWeight(int itemID, int weight){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         Item item = (Item)session.get(Item.class, itemID); 
	         item.setWeight(weight);
			 session.update(item); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	   }
	public void updateSalesprice(int itemID, double salesprice){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         Item item = (Item)session.get(Item.class, itemID); 
	         item.setSalesprice(salesprice);
			 session.update(item); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	   }
	public void updateUnitprice(int itemID, double unitprice){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         Item item = (Item)session.get(Item.class, itemID); 
	         item.setUnitprice(unitprice);
			 session.update(item); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	   }
	public void deleteItem(int itemID){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         Item item = (Item)session.get(Item.class, itemID); 
	         session.delete(item); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	}
	// funktiolle annetaan parametriksi sen hyllyn ID ja varaston ID, jonka tuotteet halutaan listata.
	public void getItemsInShelf(int shelfID, int storageID) {
		Session session = factory.openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         List items = session.createQuery("SELECT Item.Name, Item.Amount, Item.Weight, Item.Salesprice, Item.Unitprice FROM Item, Shelf WHERE Item.ShelfID=" + shelfID + " AND Item.StorageID=" + storageID).list(); 
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
	public void listItems( ){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         List items = session.createQuery("FROM Item").list(); 
	         for (Iterator iterator = items.iterator(); iterator.hasNext();){
	            Item item = (Item) iterator.next(); 
	            System.out.print("Name: " + item.getName()); 
	            System.out.print("  Amount: " + item.getAmount());
	            System.out.print("  Salesprice: " + item.getSalesprice());
	            System.out.println("  Unitprice: " + item.getUnitprice());
	            System.out.print("  Weight: " + item.getWeight());
	            System.out.print("  ShelfID: " + item.getShelfID());
	            System.out.print("  StorageID: " + item.getStorageID());
	            
	         }
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	   }
	}