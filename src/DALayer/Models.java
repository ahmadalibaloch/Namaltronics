package DALayer;

import java.sql.ResultSet;
import java.util.LinkedList;

import DomainLayer.*;

/**
 * Data Acces class for manipulating models
 *
 * @author Administrator.
 *         Created March 16, 2012.
 */
public class Models {
	/**
	 * Domain Layer Places class for object translation from classes to database
	 * @param mode 
	 * 
	 */
	public static void saveModel(Model mode) {
		/**
		 * Save booking into the database
		 * 
		 */
		String sql = "INSERT INTO models (modeId,name,year,manuId) VALUES ("+
				 mode.getModelId()
				+ ", '"
				+ mode.getName()
				+ "', '"
				+ mode.getYear()
				+ "', '"
				+ mode.getManufacturerId()
				+ "');";
		Database.getInstance().execute(sql);
	}

	/**
	 * delete
	 * @param modeId 
	 *
	 * @return whether the deletion was successful or not
	 */
	public static boolean deleteModel(int modeId) {
		/**
		 * Delete place into the database
		 * 
		 */
		String sql = "DELETE FROM models where modeId="+modeId;
		Database.getInstance().execute(sql);
		return false;
	}

	/**
	 * edit
	 *
	 * @param mode
	 * @return whether the edition was successful or not.
	 */
	public static boolean editModel(Model mode) {
		/**
		 * Edit technicians in the database
		 * 
		 */
		String sql = "UPDATE modes SET name='"+ mode.getName()+",year='"+mode.getYear()+",manuId='"+mode.getManufacturerId()+"' WHERE modeId="+mode.getModelId()+";";
		return Database.getInstance().execute(sql);
	}
	/**
	 * get a linkedlist of models of items given the query condition
	 *
	 * @param condition
	 * @return a list of technicians
	 */
	public static LinkedList<Model> getModels(String condition) {
		/**
		 * gets a list of mode Categories from the database
		 * 
		 */
		LinkedList<Model> models = new LinkedList<Model>();
		if (!condition.equals(""))
			condition = " WHERE " + condition+";";
		String sql = "SELECT modeId,name,year,manuId FROM models"
				+ condition;
		ResultSet rs = Database.getInstance().query(sql);
		try {
			while (rs.next()) {
				models.add(new Model(rs.getInt(1), rs.getString(2),rs.getInt(3), rs.getInt(4)));
			}
			rs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return models;
	}
}
