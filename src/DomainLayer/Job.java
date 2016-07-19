package DomainLayer;

import java.util.*;

import DALayer.Jobs;

/**
 * Booking Domain Layer Class for object translation between layers
 * 
 * @author Administrator. Created March 16, 2012.
 */
public class Job {
	/**
	 * 
	 * job id in format date+nextId(of todays job) like 120305-01
	 */
	public String jobId;
	/**
	 * start date of job
	 */
	public Date startDate;
	/**
	 * expected end date
	 */
	public Date expEndDate;
	/**
	 * end date of the job
	 */
	public Date endDate;
	/**
	 * call time in format 00:00:00
	 */
	public String callTime;
	/**
	 * technician of the job. assigend job to.
	 */
	public User tech;
	/**
	 * item of the job
	 */
	public Item item;

	/**
	 * Job constructor with parameters
	 * 
	 * @param jobId
	 * @param startDate
	 * @param expEndDate
	 * @param endDate
	 * @param tech
	 * @param item
	 * @param callTime
	 */
	public Job(String jobId, Date startDate, Date expEndDate, Date endDate,
			User tech, Item item, String callTime) {
		this.jobId = jobId;
		this.startDate = startDate;
		this.expEndDate = expEndDate;
		this.endDate = endDate;
		this.tech = tech;
		this.item = item;
		this.callTime = callTime;
	}

	/**
	 * empty constructor
	 * 
	 */
	public Job() {
	}

	// getters and setters

	/**
	 * Returns the value of the field called 'jobId'.
	 * 
	 * @return Returns the jobId.
	 */
	public String getJobId() {
		return this.jobId;
	}

	/**
	 * Sets the field called 'jobId' to the given value.
	 * 
	 * @param jobId
	 *            The jobId to set.
	 */
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	/**
	 * Returns the value of the field called 'startDate'.
	 * 
	 * @return Returns the startDate.
	 */
	public Date getStartDate() {
		return this.startDate;
	}

	/**
	 * Sets the field called 'startDate' to the given value.
	 * 
	 * @param startDate
	 *            The startDate to set.
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Returns the value of the field called 'expEndDate'.
	 * 
	 * @return Returns the expEndDate.
	 */
	public Date getExpEndDate() {
		return this.expEndDate;
	}

	/**
	 * Sets the field called 'expEndDate' to the given value.
	 * 
	 * @param expEndDate
	 *            The expEndDate to set.
	 */
	public void setExpEndDate(Date expEndDate) {
		this.expEndDate = expEndDate;
	}

	/**
	 * Returns the value of the field called 'endDate'.
	 * 
	 * @return Returns the endDate.
	 */
	public Date getEndDate() {
		return this.endDate;
	}

	/**
	 * Sets the field called 'endDate' to the given value.
	 * 
	 * @param endDate
	 *            The endDate to set.
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Returns the value of the field called 'tec'.
	 * 
	 * @return Returns the tec.
	 */
	public User getTech() {
		return this.tech;
	}

	/**
	 * Sets the field called 'tec' to the given value.
	 * 
	 * @param tec
	 *            The tec to set.
	 */
	public void setTech(User tec) {
		this.tech = tec;
	}

	/**
	 * Returns the value of the field called 'item'.
	 * 
	 * @return Returns the item.
	 */
	public Item getItem() {
		return this.item;
	}

	/**
	 * Sets the field called 'item' to the given value.
	 * 
	 * @param item
	 *            The item to set.
	 */
	public void setItem(Item item) {
		this.item = item;
	}

	/**
	 * Returns the value of the field called 'callTime'.
	 * 
	 * @return Returns the callTime.
	 */
	public String getCallTime() {
		return this.callTime;
	}

	/**
	 * Sets the field called 'callTime' to the given value.
	 * 
	 * @param callTime
	 *            The callTime to set.
	 */
	public void setCallTime(String callTime) {
		this.callTime = callTime;
	}

	/**
	 * Creates the job id in formate given in return parameter.
	 * 
	 * @param date
	 * 
	 * @return String of job id format date+nextId(of todays job) like 120305-01
	 *         for second job on the date March 05, 2012.
	 */
	public String createJobId(String date) {
		// if not date is specified take todaays date. that is not usable in a
		// same software.
		// otherwise database foramt for id would change. its only for using in
		// some other context.
		if (date.equals(null) || date.equals(""))
			if (!this.startDate.equals(null))
				date = DateMethods.getDate("yyyy-MM-dd", this.startDate);
			else
				date = DateMethods.getDate("-");
		else
			date = DateMethods.getDate("yyyy-MM-dd",
					DateMethods.parseDate(date, "MMM dd, yyyy"));
		// get jobs to get the last digit
		LinkedList<Job> jobs = Jobs.getJobs("startDate='" + date + "'");
		int lastId = 0;
		if (jobs.size() > 0) {
			Job lastJob = jobs.getLast();
			lastId = (Integer.parseInt((lastJob.jobId.substring(7, 9))));
			// making next id
			lastId++;
		}
		date = DateMethods.getDate("yyMMdd",
				DateMethods.parseDate(date, "yyyy-MM-dd"));
		String id = date + "-" + (lastId < 10 ? "0" + lastId : lastId);
		return id;
	}

	/**
	 * Gets the status of job on base of given date to pending , finished or in progress
	 * 
	 * @param job
	 *            to get the status of in 'In Progress' or 'Pending' or
	 *            'Finished'.
	 * @param date to be calculated status in
	 * 
	 * @return String in 'In Progress' or 'Pending' or 'Finished' depending on
	 *         the dates in job
	 */
	public static String getStatus(Job job, Date date) {
		if (job.getEndDate().before(date) || job.getEndDate().equals(date))
			return "Finished";
		else if ((job.getStartDate().before(date) || (job.getStartDate()
				.equals(date))) && (job.endDate.after(date)))
			return "In Progress";
		else
			return "Pending";
	}

}
