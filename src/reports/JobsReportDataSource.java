package reports;

import java.util.Date;
import java.util.LinkedList;

import DALayer.Jobs;
import DomainLayer.Job;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 * @author Ahmad Ali
 * @version 1
 */
public class JobsReportDataSource implements JRDataSource {

	/**
	 *
	 */
	private LinkedList<Job> jobs;
	private Date date;

	private int index = -1;

	/**
	 * @param reportType 
	 * @param date 
	 * @param filter pending, inprogress or finished
	 *
	 */
	public JobsReportDataSource(String reportType, Date date, String filter) {
		this.date = date;
		jobs = Jobs.getJobs(reportType, date,filter);
	}
	/**
	 *
	 */
	public boolean next() throws JRException {
		index++;
		return (index < jobs.size());

	}
	/**
	 *
	 */
	public Object getFieldValue(JRField field) throws JRException {
		Object value = null;

		String fieldName = field.getName();

		if ("jobId".equals(fieldName)) {
			value = jobs.get(index).getJobId();
		} else if ("status".equals(fieldName)) {
			value = Job.getStatus(jobs.get(index), date);
		} else if ("item".equals(fieldName)) {
			value = jobs.get(index).getItem().getName();
		} else if ("tech".equals(fieldName)) {
			value = jobs.get(index).getTech().getName();
		} else if ("startDate".equals(fieldName)) {
			value = jobs.get(index).getStartDate();
		} else if ("endDate".equals(fieldName)) {
			value = jobs.get(index).getEndDate();
		}
		return value;
	}
}
