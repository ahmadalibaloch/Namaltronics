package DALayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

import DomainLayer.DateMethods;
import DomainLayer.Item;
import DomainLayer.Job;
import DomainLayer.User;

/**
 * Jobs Data acces class for manipulating jobs 
 * 
 * @author Administrator. Created March 16, 2012.
 */
public class Jobs {
	/**
	 * Domain Layer job class for object translation from classes to database
	 * 
	 * @param job
	 * 
	 */
	public static void saveJob(Job job) {
		/**
		 * Save booking into the database
		 * 
		 */
		String sql = "INSERT INTO jobs (jobId,startDate,expEndDate,endDate,techId,itemId,callTime) VALUES ('"
				+ job.jobId
				+ "', '"
				+ DateMethods.getDate("yyyy-MM-dd", job.startDate)
				+ "', '"
				+ DateMethods.getDate("yyyy-MM-dd", job.expEndDate)
				+ "', '"
				+ DateMethods.getDate("yyyy-MM-dd", job.endDate)
				+ "', "
				+ job.tech.getUserId()
				+ ", "
				+ job.item.getItemId()
				+ ", '"
				+ job.getCallTime() + "');";
		Database.getInstance().execute(sql);
	}

	/**
	 * Edit job in databse
	 * 
	 * @param job
	 */
	public static void editJob(Job job) {
		/**
		 * Edit the booking where booking id equals bk
		 * 
		 */
		String sql = "UPDATE jobs SET startDate='"
				+ DateMethods.getDate("yyyy-MM-dd", job.startDate)
				+ "',expEndDate='"
				+ DateMethods.getDate("yyyy-MM-dd", job.expEndDate)
				+ "' ,endDate='"
				+ DateMethods.getDate("yyyy-MM-dd", job.endDate) + "',techId="
				+ job.tech.getUserId() + ",itemId=" + job.item.getItemId()
				+ ",callTime='" + job.getCallTime() + "' WHERE jobId='"
				+ job.jobId + "';";
		Database.getInstance().execute(sql);
	}

	/**
	 * Delete job form data base with job id given
	 * 
	 * @param jobId
	 * @return whether job deletion was succussful or not
	 */
	public static boolean deleteJob(String jobId) {
		/**
		 * Deleting booking with id
		 * 
		 */
		String sql = "DELETE FROM jobs where jobId='" + jobId + "'";
		return Database.getInstance().execute(sql);
	}

	/**
	 * Get a linkedlist of jobs on given condition query
	 * 
	 * @param condition
	 * @return a list of jobs
	 */
	public static LinkedList<Job> getJobs(String condition) {
		/**
		 * gets a list of jobs from the database
		 * 
		 */
		LinkedList<Job> jobs = new LinkedList<Job>();
		if (!condition.equals(""))
			condition = " WHERE " + condition + ";";
		String sql = "SELECT jobId,startDate,expEndDate,endDate,techId,itemId,callTime FROM jobs"
				+ condition;
		ResultSet rs = Database.getInstance().query(sql);
		try {
			while (rs.next()) {
				LinkedList<Item> items = Items.getItems("itemId="
						+ rs.getInt(6));
				LinkedList<User> techs = Users.getUsers("userId="
						+ rs.getInt(5));
				jobs.add(new Job(rs.getString(1), rs.getDate(2), rs.getDate(3),
						rs.getDate(4), techs.get(0), items.get(0), rs
								.getString(7)));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jobs;
	}

	/**
	 * Get jobs according to given filter. like pending, in progress or finished. with date duration to weekly, monthly or daily yearly
	 * @param reportType 
	 * @param date 
	 * @param filter 
	 * 
	 * @return a list of jobs
	 */
	public static LinkedList<Job> getJobs(String reportType, Date date,
			String filter) {
		// -----------------GET DATA ITEMS AND BASIC QUERY
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		int month = c.get(Calendar.MONTH) + 1;
		int year = c.get(Calendar.YEAR);
		//
		String bQuery = "", query = "", strDate = "'"
				+ DateMethods.getDate("yyyy-MM-dd", date) + "'";
		if (filter.equalsIgnoreCase("finished"))
			bQuery = " endDate <= " + strDate;
		else if (filter.equalsIgnoreCase("inprogress"))
			bQuery = " endDate > " + strDate + " AND startDate <= " + strDate;
		else if (filter.equalsIgnoreCase("pending"))
			bQuery = "startDate > " + strDate;
		query = bQuery.equalsIgnoreCase("") ? "" : bQuery + " AND ";
		// -----------------EXTENDED QUERY AND GET JOBS
		if ("monthly".equalsIgnoreCase(reportType)) {
			query += " MONTH(startDate) >= " + month
					+ " AND MONTH(endDate) <= " + month;
			return Jobs.getJobs(query);
		} else if ("daily".equalsIgnoreCase(reportType)) {
			query += " DAY(startDate) >= " + day + " AND DAY(endDate) <= "
					+ day;
			return Jobs.getJobs(query);
		} else if ("weekly".equalsIgnoreCase(reportType)) {
			String startDate = null;
			try {
				String givenDate = DateMethods.getDate("dd MMM, yyyy", date);
				startDate = DateMethods.getDate("yyyy-MM-dd", DateMethods
						.getNextDate(givenDate, "days", -7).getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			query += "startDate >= '" + startDate + "' AND endDate <= '"
					+ DateMethods.getDate("yyyy-MM-dd", date) + "'";
			return Jobs.getJobs(query);
		} else if ("yearly".equalsIgnoreCase(reportType)) {
			query += " YEAR(startDate) >= " + year + " AND YEAR(endDate) <= "
					+ year;
			return Jobs.getJobs(query);
		}
		return Jobs.getJobs("");
	}
}
