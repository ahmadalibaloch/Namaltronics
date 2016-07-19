package Beans_Utils;

import java.text.DateFormat;
import java.util.Date;

import org.jdesktop.beansbinding.Converter;

import DomainLayer.DateMethods;


/**
 * Conversion class 'beansbinding' from date to strin and vice versa.
 *
 * @author Administrator.
 *         Created Apr 5, 2012.
 */
public class DateConvertor extends Converter<Date, String> {

	@Override
	public String convertForward(Date arg0) {
		//System.out.println("Date output "+DateMethods.getDate(DateFormat.MEDIUM, arg0));
		return DateMethods.getDate(DateFormat.MEDIUM, arg0);
	}

	@Override
	public Date convertReverse(String arg0) {
		//System.out.println(" Date String came : "+arg0);
		//System.out.println(" date going " + DateMethods.parseDate(arg0));
		return DateMethods.parseDate(arg0,"MMM dd, yyyy");
		
	}
}
