package Beans_Utils;

import java.sql.Time;

import org.jdesktop.beansbinding.Converter;

/**
 * Converits time for beans bidnigns
 *
 * @author Administrator.
 *         Created Apr 22, 2012.
 */
public class timeConvertor extends Converter<Time , String>{

	@Override
	public String convertForward(Time arg0) {
		return "00:00:00";
	}

	@Override
	public Time convertReverse(String arg0) {
		return new Time(0);
	}

}
