package DALayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Database class for major connection establishmen with the database. a singleton pattern class
 *
 * @author Administrator.
 *         Created March 16, 2012.
 */
public class Database {
	/**
	 * Singleton pattern database class instance issued if not already clreated
	 * 
	 */
	static Database instance = null;
	
	/**
	 * connection object used for database connectivity
	 */
	public Connection con;
	/**
	 * Singleton pattern database class constructor which is made private
	 * 
	 */
	private Database() {
		try {
			//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Class.forName("com.mysql.jdbc.Driver");
			//File f = new File("");
			//String path=f.getAbsolutePath()+"\\";
			//System.out.println("The Database should reside in this path "+path);
			//String filename = path+"namaltronics.sql";
			String database = "jdbc:mysql://localhost/namaltronics";//+filename;
			//String filename = path+"tourism.mdb";
			//String database = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=";
			//database += filename.trim() + ";DriverID=22;READONLY=true}";
			this.con = DriverManager.getConnection(database, "root", "");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * Gets the database instaance only if not alreayd created. only 1 connection open with database
	 *
	 * @return Database instance, singleton pattern class assures only one working instance.
	 */
	public static Database getInstance() {
		/**
		 * This method is used to get the database instance
		 * 
		 */
		if (Database.instance == null)
			Database.instance = new Database();
		return Database.instance;
	}

	/**
	 * gets the query result in a ResultSet
	 *
	 * @param sql
	 * @return result set containing the result of query (a list of records as per given query).
	 */
	public ResultSet query(String sql) {
		/**
		 * gets a list of records from the database
		 * 
		 */
		try {
			Statement stm=this.con.createStatement();
			stm.execute(sql);
			return stm.getResultSet();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}
	/**
	 * execute the query and return boolean
	 *
	 * @param sql
	 * @return whether the execute query was successful or not
	 */
	public boolean execute(String sql) {
		/**
		 * executes the query and return boolean
		 * 
		 */
		try {
			Statement stm=this.con.createStatement();
			return stm.execute(sql);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return false;
	}
	/**
	 * returns single object value of the query
	 *
	 * @param sql
	 * @return a single record of the query given(if more than one records selected, first record is returned).
	 */
	public Object executeScaler(String sql){
		/**
		 * Scalar output of the query record
		 * 
		 */
		try {
			ResultSet rs = this.query(sql);
			if(rs.next())
				return rs.getObject(1);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}
	@Override
	public void finalize(){
		/**
		 * Close the connection in this method
		 * 
		 */
		try{
		this.con.close();
		}
		catch(Exception ex){
			/**
			 * 
			 */
		}
	}
}
