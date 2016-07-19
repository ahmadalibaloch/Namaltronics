package DALayer;

import java.sql.ResultSet;
import java.util.LinkedList;

import DomainLayer.*;

/**
 * Data Acces class for manipulating problems of teh items
 *
 * @author Administrator.
 *         Created March 16, 2012.
 */
public class Problems {
	/**
	 * Domain Layer Places class for object translation from classes to database
	 * @param prob 
	 * 
	 */
	public static void saveProblem(Problem prob) {
		/**
		 * Save booking into the database
		 * 
		 */
		String sql = "INSERT INTO items_probs (item_probId,probCateId,probLevel, description) VALUES ("+
				 prob.getProbId()
				+ ", "
				+ prob.getProbCateId()
				+ ", '"
				+ prob.getProbDifficultyLevel()
				+ "', '"
				+ prob.getProbDetail()
				+ "');";
		Database.getInstance().execute(sql);
	}

	/**
	 * 
	 * @param item_probId 
	 *
	 * @return whether the deletion was successful or not
	 */
	public static boolean deleteProb(int item_probId) {
		/**
		 * Delete place into the database
		 * 
		 */
		String sql = "DELETE FROM items_probs where item_probId="+item_probId;
		Database.getInstance().execute(sql);
		return false;
	}

	/**
	 * edit
	 *
	 * @param prob
	 * @return whether the edition was successful or not.
	 */
	public static boolean editProb(Problem prob) {
		/**
		 * Edit technicians in the database
		 * 
		 */
		String sql = "UPDATE items_probs SET" +
		 "',probCateId="+ prob.getProbCateId()+
		 ",probLevel='"+prob.getProbDifficultyLevel()+
		 ",description='"+prob.getProbDetail()+
		 "' WHERE item_probId="+prob.getProbId()+";";
		return Database.getInstance().execute(sql);
	}
	/**
	 * ge a linkedlist of the problems
	 *
	 * @param condition
	 * @return a list of technicians
	 */
	public static LinkedList<Problem> getProblems(String condition) {
		/**
		 * gets a list of skill Categories from the database
		 * 
		 */
		LinkedList<Problem> problems = new LinkedList<Problem>();
		if (!condition.equals(""))
			condition = " WHERE " + condition+";";
		String sql = "SELECT item_probId,probCateId,probLevel,description FROM items_probs"
				+ condition;
		ResultSet rs = Database.getInstance().query(sql);
		try {
			while (rs.next()) {
				problems.add(new Problem(rs.getInt(1),rs.getInt(2),DifficultyLevel.valueOf(rs.getString(3)), rs.getString(4)));
			}
			rs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return problems;
	}
	
}
