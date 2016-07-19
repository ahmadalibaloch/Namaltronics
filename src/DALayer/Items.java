package DALayer;

import java.sql.ResultSet;
import java.util.LinkedList;

import DomainLayer.Item;
import DomainLayer.Problem;

/**
 * data access layer class for manipulating items  
 * 
 * @author Administrator. Created March 16, 2012.
 */
public class Items {
	/**
	 * save item to database
	 * 
	 * @param item
	 */
	public static void saveItem(Item item) {
		/**
		 * Save visitor into the database
		 * 
		 */
		String sql = "INSERT INTO items (itemId,name,description,itemCateId,item_probId,custId,manuId,modeId) VALUES ("
				+ +item.getItemId()
				+ ", '"
				+ item.getName()
				+ "', '"
				+ item.getDesc()
				+ "', '"
				+ item.getItemCateId()
				+ "', '"
				+ item.getProblem().getProbCateId()
				+ "', '"
				+ item.getCustId()
				+ "', '"
				+ item.getManuId()
				+ "', '"
				+ item.getModeId()
				+ "');";
		Database.getInstance().execute(sql);
	}

	/**
	 * edit item in database
	 * 
	 * @param item
	 * @return whether edition was succussful or not
	 */
	public static boolean editItem(Item item) {
		/**
		 * Edit Visitor in the database
		 * 
		 */
		String sql = "UPDATE items SET name='" + item.getName()
				+"',description='" + item.getDesc()
				+"',itemCateId="+ item.getItemCateId()
				+",item_probId="+ item.getProblem() 
				+",custId="+ item.getCustId() 
				+",manuId=" + item.getManuId()
				+",modeId="+ item.getModeId()
				+" WHERE itemId="
				+ item.getItemId() + ";";
		return Database.getInstance().execute(sql);
	}

	/**
	 * delete item from database
	 * 
	 * @param itemId
	 * @return whether deletion was succussful or not
	 */
	public static boolean deleteItem(int itemId) {
		/**
		 * Delete visitor into the database
		 * 
		 */
		String sql = "Delete FROM items where itemId=" + itemId;
		return Database.getInstance().execute(sql);
	}

	/**
	 * get a linkedlist of item give the query condtion
	 * 
	 * @param condition
	 * @return a list of items
	 */
	public static LinkedList<Item> getItems(String condition) {
		/**
		 * gets a list of visitors from the database
		 * 
		 */
		LinkedList<Item> items = new LinkedList<Item>();
		if (!condition.equals(""))
			condition = " WHERE " + condition;
		String sql = "SELECT itemId, name, description, itemCateId, item_probId, custId, manuId, modeId FROM items"
				+ condition;
		ResultSet rs = Database.getInstance().query(sql);

		try {
			while (rs.next()) {
				Problem prob = Problems.getProblems("item_probId="+rs.getInt(5)).getFirst();
				items.add(new Item(rs.getInt(1), rs.getString(2), rs
						.getString(3), rs.getInt(4), prob, rs.getInt(6),rs.getInt(7),rs.getInt(8)));
			}

			rs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return items;
	}
}
