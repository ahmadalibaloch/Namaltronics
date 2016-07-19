package DALayer;

import java.sql.ResultSet;
import java.util.LinkedList;

import DomainLayer.*;

/**
 * Data Acces class for manipulating skills of the techs
 *
 * @author Administrator.
 *         Created March 16, 2012.
 */
public class Skills {
	/**
	 * Domain Layer Places class for object translation from classes to database
	 * @param skill 
	 * 
	 */
	public static void saveSkill(Skill skill) {
		/**
		 * Save booking into the database
		 * 
		 */
		String sql = "INSERT INTO techs_skills (tech_skilId,skillCateId,skillLevel, skillDetail) VALUES ("+
				 skill.tech_skilId
				+ ", "
				+ skill.skillCategory.skillCateId
				+ ", '"
				+ skill.skillLevel.toString()
				+ "', '"
				+ skill.skillDetail
				+ "');";
		Database.getInstance().execute(sql);
	}

	/**
	 * delete the skil from techskills table
	 * @param tech_skilId 
	 *
	 * @return whether the deletion was successful or not
	 */
	public static boolean deleteSkill(int tech_skilId) {
		/**
		 * Delete place into the database
		 * 
		 */
		String sql = "DELETE FROM techs_skills where tech_skilId="+tech_skilId;
		Database.getInstance().execute(sql);
		return false;
	}

	/**
	 * edit skill in the tble
	 *
	 * @param skill
	 * @return whether the edition was successful or not.
	 */
	public static boolean editSkill(Skill skill) {
		/**
		 * Edit technician skill in the database
		 * 
		 */
		String sql = "UPDATE techs_skills SET" +
		 " skillCateId="+ skill.skillCategory.skillCateId+
		 ",skillLevel='"+skill.skillLevel.toString()+
		 "',skillDetail='"+skill.skillDetail+
		 "' WHERE tech_skilId="+skill.tech_skilId+";";
		return Database.getInstance().execute(sql);
	}
	/**
	 * get a linkedlist of the skills of techs given the condition query
	 *
	 * @param condition
	 * @return a list of technicians
	 */
	public static LinkedList<Skill> getSkills(String condition) {
		/**
		 * gets a list of skill Categories from the database
		 * 
		 */
		LinkedList<Skill> skills = new LinkedList<Skill>();
		if (!condition.equals(""))
			condition = " WHERE " + condition+";";
		String sql = "SELECT tech_skilId,skillCateId,skillLevel,skillDetail FROM techs_skills"
				+ condition;
		ResultSet rs = Database.getInstance().query(sql);
		try {
			while (rs.next()) {
				SkillCategory sc = SkillCategories.getSkillCategories("skillCateId="+rs.getInt(2)).getFirst();
				skills.add(new Skill(rs.getInt(1),sc, SkillLevel.valueOf(rs.getString(3)), rs.getString(4)));
			}
			rs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return skills;
	}
}
