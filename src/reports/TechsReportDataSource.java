package reports;

import java.util.LinkedList;

import DALayer.Jobs;
import DALayer.Skills;
import DALayer.Users;
import DomainLayer.User;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 * @author Ahmad Ali
 * @version $1
 */
public class TechsReportDataSource implements JRDataSource {

	/**
	 *
	 */
	private LinkedList<User> users;
	private int index = -1;

	/**
	 * @param filter containing at [0] status, [1] how many jobs 
	 *
	 */
	public TechsReportDataSource(String[] filter) {
		String query ="";
		
		query = !filter[0].equalsIgnoreCase("OFF this filter")?"status='"+filter[0]+"' AND ":"";
		query += !filter[1].equalsIgnoreCase("OFF this filter")?
						filter[1].equalsIgnoreCase("more")?
							" (SELECT COUNT(*) FROM jobs WHERE techId=userid) >3 AND ":
						"(SELECT COUNT(*) FROM jobs WHERE techId=userid) ="+
							(filter[1].equalsIgnoreCase("0 (available)")?"0 AND ":filter[1]+" AND ")
				 :"";//first if
		
		query +="userId IN(Select techId from technicians)";
		users = Users.getUsers(query);
		//System.out.println("users "+users.size());
	}
	/**
	 *
	 */
	public boolean next() throws JRException {
		index++;
		return (index < users.size());

	}
	/**
	 *
	 */
	public Object getFieldValue(JRField field) throws JRException {
		Object value = null;

		String fieldName = field.getName();

		if ("techId".equals(fieldName)) {
			value = users.get(index).getUserId();
		} else if ("status".equals(fieldName)) {
			value = users.get(index).getUserStatus().toString();
		} else if ("skilLevel".equals(fieldName)) {
			value = Skills.getSkills("tech_skilId=(SELECT tech_skilId FROM technicians WHERE techId="+users.get(index).getUserId()+")").getFirst().getSkillLevel().toString();
		} else if ("jobsCount".equals(fieldName)) {
			value = Jobs.getJobs("techId="+users.get(index).getUserId()).size();
		} else if ("performance".equals(fieldName)) {
			value = "to be counted";//users.get(index).getStartDate();
		} else if ("name".equals(fieldName)) {
			value = users.get(index).getName();
		}
		return value;
	}
}
