package DALayer;

import java.sql.ResultSet;
import java.util.LinkedList;

import DomainLayer.Customer;
import DomainLayer.Payment;

/**
 * data access layer class for manipulating customers
 *
 * @author Administrator.
 *         Created March 16, 2012.
 */
public class Customers {
	/**
	 * Domain Layer Customers class for object translation from classes to database
	 * @param cust 
	 * 
	 */
	public static void saveCustomer(Customer cust) {
		/**
		 * Save booking into the database
		 * 
		 */
		String sql = "INSERT INTO customers (custId,name,address,postcode,phone,email,payment) VALUES ("+
				+ cust.custId
				+ ", '"
				+ cust.name
				+ "', '"
				+ cust.address
				+ "', '"
				+ cust.postcode
				+ "', '"
				+ cust.phone
				+ "', '"
				+ cust.email 
				+ "', '" 
				+ cust.payment
				+ "');";
		Database.getInstance().execute(sql);
	}
	/**
	 * edit customer in database
	 *
	 * @param cust
	 */
	public static void editCustomer(Customer cust) {
		/**
		 * Edit the customer where customer id equals cust.custid
		 * 
		 */
		String sql = "UPDATE customers SET name='"+ cust.name+"',address='"+ cust.address+"',postcode='"+cust.postcode+
				"',phone='"+cust.phone+"',email='"+cust.email+"' ,payment='"+cust.payment+"' WHERE custId="+cust.custId+";";
		System.out.println(sql);
		Database.getInstance().execute(sql);
	}
	/**
	 * delte in database
	 *
	 * @param custId
	 * @return whether deletion succeeded or not
	 */
	public static boolean deleteCustomer(int custId) {
		/**
		 * Deleting customer with id
		 * 
		 */
		String sql = "DELETE FROM customers where custId="+custId;
		return Database.getInstance().execute(sql);
	}

	/**
	 * get a linkedlist of custoemrs given condtion query
	 *
	 * @param condition
	 * @return list of customers
	 */
	public static LinkedList<Customer> getCustomers(String condition) {
		/**
		 * gets a list of customers from the database
		 * 
		 */
		LinkedList<Customer> custs = new LinkedList<Customer>();
		if (!condition.equals(""))
			condition = " WHERE " + condition;
		String sql = "SELECT * FROM customers" + condition;
		ResultSet rs = Database.getInstance().query(sql);
		try {
			while (rs.next()) {
				custs.add(new Customer(rs.getInt(1), rs.getString(2), rs
						.getString(3), rs.getString(4), rs.getString(5), rs
						.getString(6), Payment.valueOf(rs.getString(7)==null?"NA":rs.getString(7))));
			}
			rs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return custs;
	}
}
