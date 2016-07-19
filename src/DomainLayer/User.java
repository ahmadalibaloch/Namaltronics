package DomainLayer;

import java.util.Date;
import java.util.LinkedList;

import DALayer.Users;

/**
 * Class domain layer for user with attributes
 *
 * @author Administrator.
 *         Created March 16, 2012.
 */
public class User {
	private int userId;
	private String name;
	private String phone;
	private String email;
	private String userName;
	private String password;

	private String role;
	private Date lastLogin;
	private Date added;
	private UserStatus userStatus;

	
	
	/**
	 * constrcustoemr
	 *
	 * @param userId
	 * @param name
	 * @param phone
	 * @param email
	 * @param password
	 * @param userName
	 * @param role
	 * @param lastLogin
	 * @param added
	 * @param userStatus
	 */
	public User(int userId, String name, String phone, String email,
			String userName, String password, Date lastLogin, Date added,
			UserStatus userStatus,String role) {
		this.userId = userId;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.userName = userName;
		this.password=password;
		this.role = role;
		this.lastLogin = lastLogin;
		this.added = added;
		this.userStatus = userStatus;
	}



	/**
	 * Returns the value of the field called 'userId'.
	 * @return Returns the userId.
	 */
	public int getUserId() {
		return this.userId;
	}



	/**
	 * Sets the field called 'userId' to the given value.
	 * @param userId The userId to set.
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}



	/**
	 * Returns the value of the field called 'name'.
	 * @return Returns the name.
	 */
	public String getName() {
		return this.name;
	}



	/**
	 * Sets the field called 'name' to the given value.
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}



	/**
	 * Returns the value of the field called 'phone'.
	 * @return Returns the phone.
	 */
	public String getPhone() {
		return this.phone;
	}



	/**
	 * Sets the field called 'phone' to the given value.
	 * @param phone The phone to set.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}



	/**
	 * Returns the value of the field called 'email'.
	 * @return Returns the email.
	 */
	public String getEmail() {
		return this.email;
	}



	/**
	 * Sets the field called 'email' to the given value.
	 * @param email The email to set.
	 */
	public void setEmail(String email) {
		this.email = email;
	}



	/**
	 * Returns the value of the field called 'userName'.
	 * @return Returns the userName.
	 */
	public String getUserName() {
		return this.userName;
	}



	/**
	 * Sets the field called 'userName' to the given value.
	 * @param userName The userName to set.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Returns the value of the field called 'password'.
	 * @return Returns the password.
	 */
	public String getPassword() {
		return this.password;
	}



	/**
	 * Sets the field called 'password' to the given value.
	 * @param password The password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	

	/**
	 * Returns the value of the field called 'role'.
	 * @return Returns the role.
	 */
	public String getRole() {
		return this.role;
	}



	/**
	 * Sets the field called 'role' to the given value.
	 * @param role The role to set.
	 */
	public void setRole(String role) {
		this.role = role;
	}



	/**
	 * Returns the value of the field called 'lastLogin'.
	 * @return Returns the lastLogin.
	 */
	public Date getLastLogin() {
		return this.lastLogin;
	}



	/**
	 * Sets the field called 'lastLogin' to the given value.
	 * @param lastLogin The lastLogin to set.
	 */
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}



	/**
	 * Returns the value of the field called 'added'.
	 * @return Returns the added.
	 */
	public Date getAdded() {
		return this.added;
	}



	/**
	 * Sets the field called 'added' to the given value.
	 * @param added The added to set.
	 */
	public void setAdded(Date added) {
		this.added = added;
	}



	/**
	 * Returns the value of the field called 'userStatus'.
	 * @return Returns the userStatus.
	 */
	public UserStatus getUserStatus() {
		return this.userStatus;
	}



	/**
	 * Sets the field called 'userStatus' to the given value.
	 * @param userStatus The userStatus to set.
	 */
	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}



	/**
	 * empty constructor
	 *
	 */
	public User(){}
	
	/**
	 * create next integer id for user to save
	 *
	 * @return receptionist id incremented by 1 to the last entry in database table of receptionists.
	 */
	public int createId(){
		LinkedList<User> reces = Users.getUsers("");
		if(reces.size()>0){
			this.userId=reces.getLast().userId+1;
			return reces.getLast().userId+1;
		}
		this.userId=0;
		return 0;
	}
	//
	@Override
	public String toString(){
		return this.getName();
	}
}
