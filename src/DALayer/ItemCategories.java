package DALayer;

import java.sql.ResultSet;
import java.util.LinkedList;

import DomainLayer.*;

/**
 * Data Acces class for manipulating Item Categories
 *
 * @author Administrator.
 *         Created March 16, 2012.
 */
public class ItemCategories {
	/**
	 * Domain Layer Places class for object translation from classes to database
	 * @param itemCate 
	 * 
	 */
	public static void saveItemCategory(ItemCategory itemCate) {
		/**
		 * Save booking into the database
		 * 
		 */
		String sql = "INSERT INTO ItemCategories (itemCateId,name,storage,description) VALUES ("+
				 itemCate.itemCateId
				+ ", '"
				+ itemCate.name
				+ "', '"
				+ itemCate.storage
				+ "', '"
				+ itemCate.description
				+ "');";
		Database.getInstance().execute(sql);
	}

	/**
	 * delete item
	 * @param itemCateId 
	 *
	 * @return whether the deletion was successful or not
	 */
	public static boolean deleteItemCategory(int itemCateId) {
		/**
		 * Delete place into the database
		 * 
		 */
		String sql = "DELETE FROM itemCategories where itemCateId="+itemCateId;
		Database.getInstance().execute(sql);
		return false;
	}

	/**
	 * edit eitem
	 *
	 * @param itemCate
	 * @return whether the edition was successful or not.
	 */
	public static boolean editItemCategory(ItemCategory itemCate) {
		/**
		 * Edit technicians in the database
		 * 
		 */
		String sql = "UPDATE itemCategories SET name='"+ itemCate.name+"',itemCateId="+ itemCate.itemCateId+",storage='"+ itemCate.storage+"',description='"+itemCate.description+"' WHERE itemCateId="+itemCate.itemCateId+";";
		return Database.getInstance().execute(sql);
	}
	/**
	 * Gets a linkedlist of the items from database given the query condtion
	 *
	 * @param condition
	 * @return a list of technicians
	 */
	public static LinkedList<ItemCategory> getItemCategories(String condition) {
		/**
		 * gets a list of Item Categories from the database
		 * 
		 */
		LinkedList<ItemCategory> itemCategories = new LinkedList<ItemCategory>();
		if (!condition.equals(""))
			condition = " WHERE " + condition+";";
		String sql = "SELECT itemCateId,name,storage,description FROM itemCategories"
				+ condition;
		ResultSet rs = Database.getInstance().query(sql);
		try {
			while (rs.next()) {
				itemCategories.add(new ItemCategory(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getString(4)));
			}
			rs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return itemCategories;
	}
}
