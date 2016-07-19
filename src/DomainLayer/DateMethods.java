package DomainLayer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Utility Date conversion, parsing and comparing methods.
 * 
 * @author Administrator. Created Apr 4, 2012.
 */
public class DateMethods {
	private static Calendar c;
	private static Date d;

	/**
	 * Try to parse the date with given pattern nad locale
	 * 
	 * @param date
	 *            to be parsed.
	 * @param pattern
	 *            of the date being input to method.
	 * @param loc 
	 * @return the date object parsed from date string in the given pattern.
	 */
	public static Date parseDate(String date, String pattern, Locale loc) {
		/**
		 * Parses the date from string and returns date, in a special pattern
		 * 
		 */
		d = null;
		try {
			// we specify Locale.US since months are in english
			SimpleDateFormat sdf = new SimpleDateFormat(pattern, loc);
			d = sdf.parse(date);
		} catch (Exception ex) {
			System.out.println("Error in parsing date.\n" + ex.getMessage());
		}
		return d;
	}

	/**
	 * Try to parse the date in given pattern.
	 * 
	 * @param date
	 *            to be parsed.
	 * @param pattern
	 *            of the date being input to method.
	 * @return the date object parsed from date string in the given pattern.
	 */
	public static Date parseDate(String date, String pattern) {
		/**
		 * Parses the date from string and returns date, in a special pattern
		 * 
		 */
		return parseDate(date, pattern, Locale.US);
	}

	/**
	 * Validate the string date in given pattern and locale like US
	 * 
	 * @param date
	 * @param pattern
	 * @param loc
	 * @return boolean value true if date is parseable.
	 */
	public static boolean validate(String date, String pattern, Locale loc) {
		/**
		 * Parses the date from string and returns date, in a special pattern
		 * 
		 */
		boolean result = true;
		try {
			// we specify Locale.US since months are in english
			SimpleDateFormat sdf = new SimpleDateFormat(pattern, loc);
			sdf.parse(date);
		} catch (Exception ex) {
			System.out.println("Error in parsing date.\n" + ex.getMessage());
			result = false;
		}
		return result;
	}

	/**
	 * Validate the given pattern string
	 * 
	 * @param date
	 * @param pattern
	 * @return boolean value true if date is parseable.
	 */
	public static boolean validate(String date, String pattern) {
		/**
		 * Parses the date from string and returns date, in a special pattern
		 * 
		 */
		return validate(date, pattern, Locale.US);
	}

	/**
	 * Get date string with separtor given
	 * 
	 * @param separator
	 * @return Date String with separtor in the arguments
	 */
	public static String getDate(char separator) {
		c = Calendar.getInstance();
		int day = c.get(Calendar.DATE);
		int month = c.get(Calendar.MONTH) + 1;
		int year = c.get(Calendar.YEAR);
		String date = (year < 10 ? "0" + year : year + "") + separator
				+ (month < 10 ? "0" + month : month + "") + separator
				+ (day < 10 ? "0" + day : day + "");
		return date;
	}

	/**
	 * convert current date in formate and out the string
	 * 
	 * @param df
	 *            is DateFormat
	 * @param date
	 *            is the Date Object
	 * @return Date String
	 */
	public static String getDate(int df, Date date) {
		return DateFormat.getDateInstance(df, Locale.US).format(date);
	}

	/**
	 * Gives the date formatted in the pattern given in format string or dd MMM,
	 * yyyy for 'null' string, 'dd/MM/yy' for short and 'dd MMM, yyyy' for
	 * medium and 'EEE MMM dd, yyyy' for long. where EEE stands for three word
	 * day of week name MM or MMM for month in two digits or three words and
	 * yyyy for year in four digits.
	 * 
	 * @param format
	 *            of the date to be output in string type.
	 * @param date
	 *            to be formatted.
	 * @return String of the format given in string 'format'
	 */
	public static String getDate(String format, Date date) {
		SimpleDateFormat df = new SimpleDateFormat();
		if (format.equals(null))
			df.applyPattern("dd MMM, yyyy");
		else if (format.equals("short"))
			df.applyPattern("dd/MM/yy");
		else if (format.equals("medium"))
			df.applyPattern("dd MMM, yyyy");
		else if (format.equals("long"))
			df.applyPattern("EEE MMM dd, yyyy");
		else
			df.applyPattern(format);
		return df.format(date);
	}

	/**
	 * converts the new Date() into formate given and out put a string of date
	 * 
	 * @param format
	 * @return current date in the formate string given. or short, medium or
	 *         long can be used instead of pattern.
	 */
	public static String getDate(String format) {
		return getDate(format, new Date());
	}

	/**
	 * Adds the given amount of type months, days or years int the given
	 * date and returns new date.
	 * 
	 * @param Date
	 *            string to be added days, months or years into
	 * @param type
	 *            can be the addition amount, that is days, months or years
	 * @param amount
	 *            the amount of type mentioned to be added into the given Date
	 * 
	 * @return Calendar object containing new date
	 * @throws ParseException
	 *             if the input date is not in format
	 */
	public static Calendar getNextDate(String Date, String type, int amount)
			throws ParseException {
		/**
		 * Gets the checkOut date when checkin date is given with duration.
		 * date+duratoin=output
		 * 
		 */
		Calendar newDate = Calendar.getInstance();
		newDate.setTime(parseDate(Date, "dd MMM, yyyy"));
		newDate.add(
				type.equalsIgnoreCase("days") ? Calendar.DATE : type
						.equalsIgnoreCase("months") ? Calendar.MONTH
						: Calendar.YEAR, amount);
		return newDate;
	}
	/**
	 * Method which gets the pas week data of the passed parameter date
	 * @param today
	 * @return the Date object pas the days passed in parameter
	 */
	public static Date getPastWeekDate(Date today){
		Calendar todayC = Calendar.getInstance();
		todayC.setTime(today);
		todayC.add(Calendar.DATE, -7);
		Date lastWeekDate = todayC.getTime();
		return lastWeekDate;
	}
}
