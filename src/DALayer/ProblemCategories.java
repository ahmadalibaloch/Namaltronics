package DALayer;

import java.sql.ResultSet;
import java.util.LinkedList;

import DomainLayer.*;

/**
 * Data Acces class for manipulating problem cateogires of items
 *
 * @author Administrator.
 *         Created March 16, 2012.
 */
public class ProblemCategories {
	/**
	 * Domain Layer Places class for object translation from classes to database
	 * @param probCate 
	 * 
	 */
	public static void saveProblemCategory(ProblemCategory probCate) {
		/**
		 * Save booking into the database
		 * 
		 */
		String sql = "INSERT INTO problemCategories (probCateId,name,description) VALUES ("+
				 probCate.getProbCateId()
				+ ", '"
				+ probCate.getName()
				+ "', '"
				+ probCate.getDescription()
				+ "');";
		Database.getInstance().execute(sql);
	}

	/**
	 * delete
	 * @param probCateId 
	 *
	 * @return whether the deletion was successful or not
	 */
	public static boolean deleteProblemCategory(int probCateId) {
		/**
		 * Delete place into the database
		 * 
		 */
		String sql = "DELETE FROM problemCategories where probCateId="+probCateId;
		Database.getInstance().execute(sql);
		return false;
	}

	/**
	 * edit
	 *
	 * @param probCate
	 * @return whether the edition was successful or not.
	 */
	public static boolean editSkillCategory(ProblemCategory probCate) {
		/**
		 * Edit technicians in the database
		 * 
		 */
		String sql = "UPDATE problemCategories SET name='"+ probCate.getName()+"',probCateId="+ probCate.getProbCateId()+",description='"+probCate.getDescription()+"' WHERE probCateId="+probCate.getProbCateId()+";";
		return Database.getInstance().execute(sql);
	}
	/**
	 * get a linkedlist of the problem categorieis for problems
	 *
	 * @param condition
	 * @return a list of technicians
	 */
	public static LinkedList<ProblemCategory> getProbCategories(String condition) {
		/**
		 * gets a list of problem Categories from the database
		 * 
		 */
		LinkedList<ProblemCategory> probCategories = new LinkedList<ProblemCategory>();
		if (!condition.equals(""))
			condition = " WHERE " + condition+";";
		String sql = "SELECT probCateId,name,description FROM problemCategories"
				+ condition;
		ResultSet rs = Database.getInstance().query(sql);
		try {
			while (rs.next()) {
				probCategories.add(new ProblemCategory(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
			rs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return probCategories;
	}
}
