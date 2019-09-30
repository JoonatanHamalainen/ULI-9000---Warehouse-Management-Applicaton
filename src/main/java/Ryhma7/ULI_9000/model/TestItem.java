package Ryhma7.ULI_9000.model;

import java.util.List; 
import java.util.Iterator; 
 
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestItem {
   private static SessionFactory factory; 
   public static void main(String[] args) {
      
      try {
         factory = new Configuration().configure().buildSessionFactory();
      } catch (Throwable ex) { 
         System.err.println("Failed to create sessionFactory object." + ex);
         throw new ExceptionInInitializerError(ex); 
      }
      
      TestItem ME = new TestItem();

      /* Add few employee records in database */
      int empID1 = ME.addItem("Olut", 500, 1000, 4, 6, 3.5, 1.1);
      int empID2 = ME.addItem("Kalja", 500, 1000, 5, 8, 4.2, 1.1);
      int empID3 = ME.addItem("Bisse", 500, 1000, 1, 2, 2.6, 0.8);

      /* List down all the employees */
      ME.listItems();
      /* Update employee's records */
      ME.updateItem(empID1, 0.27);

      /* Delete an employee from the database */
      ME.deleteItem(empID2);

      /* List down new list of the employees */
      ME.listItems();
   }
   
   /* Method to CREATE an employee in the database */
   public int addItem(String name, int weight, int amount, int coordinateX, int coordinateY, double salesprice, double unitprice) {
      Session session = factory.openSession();
      Transaction tx = null;
      Integer itemID = null;
      
      try {
         tx = session.beginTransaction();
         Item item = new Item(name, weight, amount, coordinateX, coordinateY, salesprice, unitprice);
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
   
   /* Method to  READ all the employees */
   public void listItems( ){
      Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         List items = session.createQuery("FROM Item").list(); 
         for (Iterator iterator = items.iterator(); iterator.hasNext();){
            Item item = (Item) iterator.next(); 
            System.out.print("Name: " + item.getName()); 
            System.out.print("  Weight: " + item.getWeight());
            System.out.print("  Amount: " + item.getAmount());
            System.out.print("  CoordinateX: " + item.getCoordinateX());
            System.out.print("  CoordinateY: " + item.getCoordinateY());
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
   
   /* Method to UPDATE salary for an employee */
   public void updateItem(Integer ItemID, double unitprice){
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
   
   /* Method to DELETE an employee from the records */
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