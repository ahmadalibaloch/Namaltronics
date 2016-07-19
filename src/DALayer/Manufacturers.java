package DALayer;

import java.sql.ResultSet;
import java.util.LinkedList;

import DomainLayer.*;

/**
 * Data Acces class for manipulating manufacturer
 *
 * @author Administrator.
 *         Created March 16, 2012.
 */
public class Manufacturers {
	/**
	 * Domain Layer Places class for object translation from classes to database
	 * @param manu 
	 * 
	 */
	public static void saveManufacturer(Manufacturer manu) {
		/**
		 * Save booking into the database
		 * 
		 */
		String sql = "INSERT INTO manufacturers (manuId,name,email,website) VALUES ("+
				 manu.getManufacturerId()
				+ ", '"
				+ manu.getName()
				+ "', '"
				+ manu.getEmail()
				+ "', '"
				+ manu.getWebsite()
				+ "');";
		Database.getInstance().execute(sql);
	}

	/**
	 * delete
	 * @param manuId 
	 *
	 * @return whether the deletion was successful or not
	 */
	public static boolean deleteManufacturer(int manuId) {
		/**
		 * Delete place into the database
		 * 
		 */
		String sql = "DELETE FROM manufacturers where manuId="+manuId;
		Database.getInstance().execute(sql);
		return false;
	}

	/**
	 * edit
	 *
	 * @param manu
	 * @return whether the edition was successful or not.
	 */
	public static boolean editManufacturer(Manufacturer manu) {
		/**
		 * Edit technicians in the database
		 * 
		 */
		String sql = "UPDATE manufacturers SET name='"+ manu.getName()+",email='"+manu.getEmail()+",website='"+manu.getWebsite()+"' WHERE manuId="+manu.getManufacturerId()+";";
		return Database.getInstance().execute(sql);
	}
	/**
	 * get a linkedlist of manfuacturere of the item
	 *
	 * @param condition
	 * @return a list of technicians
	 */
	public static LinkedList<Manufacturer> getManufacturers(String condition) {
		/**
		 * gets a list of manu Categories from the database
		 * 
		 */
		LinkedList<Manufacturer> manufacturers = new LinkedList<Manufacturer>();
		if (!condition.equals(""))
			condition = " WHERE " + condition+";";
		String sql = "SELECT manuId,name,email,website FROM manufacturers"
				+ condition;
		ResultSet rs = Database.getInstance().query(sql);
		try {
			while (rs.next()) {
				manufacturers.add(new Manufacturer(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getString(4)));
			}
			rs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return manufacturers;
	}
}
