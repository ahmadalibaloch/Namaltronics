package DALayer;

//import org.hi
import java.sql.ResultSet;
import java.util.LinkedList;


import DomainLayer.DateMethods;
import DomainLayer.Skill;
import DomainLayer.User;
import DomainLayer.UserStatus;

/**
 * Users class for manipulating users in database in users table
 *
 * @author Administrator.
 *         Created March 16, 2012.
 */
public class Users {
	/**
	 * Domain Layer Destinations class for object translation from classes to database
	 * @param user 
	 * 
	 */
	public static void saveUser(User user) {
		/**
		 * Save receptionist into the database
		 * 
		 */
		String sql = "INSERT INTO users (name,phone, email, userName,password,lastLogin,added,status,role) VALUES ('"+
				user.getName()
				+ "', '"
				+ user.getPhone()
				+ "', '"
				+ user.getEmail()
				+ "', '"
				+ user.getUserName()
				+ "', '"
				+ user.getPassword()
				+ "', '"
				+ DateMethods.getDate("yyyy-MM-dd",user.getLastLogin())
				+ "', '"
				+ DateMethods.getDate("yyyy-MM-dd",user.getAdded())
				+ "', '"
				+ user.getUserStatus()
				+ "', '"
				+ user.getRole()
				+ "');";
				Database.getInstance().execute(sql);
	}
	/**
	 * adds the technician also to users table and technicians table
	 *
	 * @param user technician
	 * @param skill of the technician
	 */
	public static void saveTechnician(User user,Skill skill){
		Skills.saveSkill(skill);
		saveUser(user);
		long techId = (Long) Database.getInstance().executeScaler("SELECT MAX(userId) from users");
		String sql = "INSERT INTO technicians (techId,tech_skilId) VALUES ("+
				+ techId
				+ ", "
				+ skill.getTech_skilId()
				+ ")";
		Database.getInstance().execute(sql);

		
	}
	/**
	 * Delete user from the database
	 *
	 * @param userId
	 * @return whether the deletion was successful or not
	 */
	public static boolean deleteUser(int userId) {
		/**
		 * Delete receptionist from the database
		 * 
		 */
		String sql = "DELETE FROM users where userId="+userId;
		return Database.getInstance().execute(sql);
	}
	/**
	 * Delete techncian with techncian id and skill id in techs_skills table
	 *
	 * @param userId
	 * @param tech_skilId 
	 * @return whether the deletion was successful or not
	 */
	public static boolean deleteTechnician(int userId,int tech_skilId) {
		Skills.deleteSkill(tech_skilId);
		Users.deleteUser(userId);
		//Database.getInstance().execute("DELETE FROM technicians WHERE techId="+userId);
		return true;
	}
	/**
	 * Edit the technician in database . updates 2 tables in database in constraints added
	 *
	 * @param user
	 * @param skill 
	 */
	public static void editTechnician(User user,Skill skill) {
		Skills.editSkill(skill);
		editUser(user);
	}
	/**
	 * Edit teh user in database given User as parameter
	 *
	 * @param user
	 * @return whether the edition was succussful or not
	 */
	public static boolean editUser(User user) {
		String sql = "UPDATE users SET " +
				"name='"+ user.getName()+
				"',phone='"+user.getPhone()+
				"',email='"+user.getEmail()+
				"',userName='"+user.getUserName()+
				"',password='"+user.getPassword()+
				"',status='"+user.getUserStatus()+
				"',added='"+DateMethods.getDate("yyyy-MM-dd",user.getAdded())+
				"',lastLogin='"+DateMethods.getDate("yyyy-MM-dd",user.getLastLogin())+
				"' WHERE userId="+user.getUserId()+";";
		return Database.getInstance().execute(sql);
	}
	/**
	 * Geths a list of users from database given the condition in parameter
	 *
	 * @param condition
	 * @return a list of receptionists
	 */
	public static LinkedList<User> getUsers(String condition) {
		/**
		 * gets a list of receptionists from the database
		 * 
		 */
		LinkedList<User> receptionists = new LinkedList<User>();
		if (!condition.equals(""))
			condition = " WHERE " + condition;
		String sql = "SELECT * FROM users"
				+ condition;
		ResultSet rs = Database.getInstance().query(sql);
		try {
			while (rs.next()) {
				receptionists.add(new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),
						rs.getString(6),rs.getDate(7), rs.getDate(8),UserStatus.valueOf(rs.getString(9)), rs.getString(10)));
			}
			rs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage()+"error in db class");
		}
		return receptionists;
	}
	/**
	 * @return available techs with 0 jobs assigned
	 */
	public static LinkedList<User> getTechsAvailable(){
		return Users.getUsers("userId IN(SELECT techId FROM technicians) AND (SELECT COUNT(*) FROM jobs WHERE techId=userid) =0");
	}
	/**
	 * @return techs with at least 1 job
	 */
	public static LinkedList<User> getTechsBusy(){
		return Users.getUsers("userId IN(SELECT techId FROM jobs)");
	}
}
