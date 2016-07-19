package UI_Utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JTextField;

import com.qt.datapicker.DatePicker;

/**
 *DatePicker class gives a builtin GUI for picking date and synchronizing it with a field (Observable).
 * 
 * @author Ahmad Ali. Created Apr 3, 2012.
 */
public class DateChooser {
	/**
	 * shows the date pickter tool for selecting date
	 * 
	 * @param otf
	 *            TextField implementing the Observer
	 */
	public static void showDatePicker(ObservingTextField otf) {
		// instantiate the DatePicker
		DatePicker dp = new DatePicker(otf, Locale.US);
		// previously selected date
		Date selectedDate = dp.parseDate(otf.getText());
		dp.setSelectedDate(selectedDate);
		dp.start(otf);
	}
	/**
	 * JTextField implementing the Observer, to be used with attachDatePicker method.
	 *
	 * @author Ahmad Ali.
	 *         Created Apr 3, 2012.
	 */
	public static class ObservingTextField extends JTextField implements Observer {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String dateFormat="MMM dd, yyyy";
		/**
		 *  constructor getting the date format.
		 *
		 * @param dateFormat is the format of date
		 */
		ObservingTextField(String dateFormat){
			this.dateFormat=dateFormat;
		}
		@SuppressWarnings("javadoc")
		public
		ObservingTextField(){}
		@Override
		 public void update(Observable o, Object arg) {
	        Calendar calendar = (Calendar) arg;
	        DatePicker dp = (DatePicker) o;
	        //System.out.println("picked=" + dp.formatDate(calendar));
	        setText(dp.formatDate(calendar, this.dateFormat ));
	    }
	}
}
