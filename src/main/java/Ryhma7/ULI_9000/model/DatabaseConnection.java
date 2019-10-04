package Ryhma7.ULI_9000.model;

import java.awt.Window.Type;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.type.IntegerType;

public class DatabaseConnection {
	private static SessionFactory factory;
	public DatabaseConnection() {
		try {
	         factory = new Configuration().configure().buildSessionFactory();
	      } catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
	}
	public void showItemInformation(int itemID) {
		
	}
	
	public int addItem(int itemID, String name, int weight, int amount, int coordinateX, int coordinateY, double salesprice, double unitprice) {
	      Session session = factory.openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         Item item = new Item(itemID, name, weight, amount, coordinateX, coordinateY, salesprice, unitprice);
	         itemID = (Integer) session.save(item); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	      return itemID;
	   }
	
	public void updateUnitprice(Integer ItemID, double unitprice){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         Item item = (Item)session.get(Item.class, ItemID); 
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
	
	public void updateLocation(Integer ItemID, int coordinateX, int coordinateY){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         Item item = (Item)session.get(Item.class, ItemID); 
	         item.setCoordinateX(coordinateX);
	         item.setCoordinateY(coordinateY);
			 session.update(item); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	   }
	
	public void updateWeight(Integer ItemID, int weight){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         Item item = (Item)session.get(Item.class, ItemID); 
	         item.setUnitprice(weight);
			 session.update(item); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	   }
	
	public void updateName(Integer ItemID, double salesprice){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         Item item = (Item)session.get(Item.class, ItemID); 
	         item.setUnitprice(salesprice);
			 session.update(item); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	   }
	
	public void updateSalesprice(Integer ItemID, double salesprice){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         Item item = (Item)session.get(Item.class, ItemID); 
	         item.setUnitprice(salesprice);
			 session.update(item); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	   }
	
	public void updateSalesprice(Integer ItemID, double salesprice){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         Item item = (Item)session.get(Item.class, ItemID); 
	         item.setUnitprice(salesprice);
			 session.update(item); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	   }
	
	public boolean decreaseAmount(int itemID, int decrease) {
		Session session = factory.openSession();
	      Transaction tx = null;
		
		try {
		tx = session.beginTransaction();
		Item item = (Item)session.get(Item.class, itemID);
        Query query = session.createQuery("SELECT Amount as num_results FROM Item WHERE ItemID=" + itemID);
        int amount = (int) query.uniqueResult();
		//Jos koittaa vähentää enemmän kuin tuotetta on, palauttaa false.
		if (amount > decrease) {
			amount -= decrease;
		}
		} catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	}
	
	public void deleteItem(Integer ItemID){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         Item item = (Item)session.get(Item.class, ItemID); 
	         session.delete(item); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	   }
}
