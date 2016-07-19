package DALayer;

import java.sql.ResultSet;
import java.util.LinkedList;

import DomainLayer.*;

/**
 *Data Acces class for manipulating skills of the technicians
 *
 * @author Administrator.
 *         Created March 16, 2012.
 */
public class SkillCategories {
	/**
	 * Domain Layer Places class for object translation from classes to database
	 * @param skillCate 
	 * 
	 */
	public static void saveSkillCategory(SkillCategory skillCate) {
		/**
		 * Save booking into the database
		 * 
		 */
		String sql = "INSERT INTO skillCategories (skillCateId,name,description) VALUES ("+
				 skillCate.skillCateId
				+ ", '"
				+ skillCate.name
				+ "', '"
				+ skillCate.description
				+ "');";
		Database.getInstance().execute(sql);
	}

	/**
	 * TODO Put here a description of what this method does.
	 * @param skillCateId 
	 *
	 * @return whether the deletion was successful or not
	 */
	public static boolean deleteSkillCategory(int skillCateId) {
		/**
		 * Delete place into the database
		 * 
		 */
		String sql = "DELETE FROM skillCategories where skillCateId="+skillCateId;
		Database.getInstance().execute(sql);
		return false;
	}

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @param skillCate
	 * @return whether the edition was successful or not.
	 */
	public static boolean editSkillCategory(SkillCategory skillCate) {
		/**
		 * Edit technicians in the database
		 * 
		 */
		String sql = "UPDATE skillCategories SET name='"+ skillCate.name+"',skillCateId="+ skillCate.skillCateId+",description='"+skillCate.description+"' WHERE skilId="+skillCate.skillCateId+";";
		return Database.getInstance().execute(sql);
	}
	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @param condition
	 * @return a list of technicians
	 */
	public static LinkedList<SkillCategory> getSkillCategories(String condition) {
		/**
		 * gets a list of skill Categories from the database
		 * 
		 */
		LinkedList<SkillCategory> skillCategories = new LinkedList<SkillCategory>();
		if (!condition.equals(""))
			condition = " WHERE " + condition+";";
		String sql = "SELECT skillCateId,name,description FROM skillCategories"
				+ condition;
		ResultSet rs = Database.getInstance().query(sql);
		try {
			while (rs.next()) {
				skillCategories.add(new SkillCategory(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
			rs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return skillCategories;
	}
}
