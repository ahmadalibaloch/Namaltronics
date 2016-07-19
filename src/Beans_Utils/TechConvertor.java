package Beans_Utils;

import java.util.LinkedList;

import org.jdesktop.beansbinding.Converter;

import DALayer.Users;
import DomainLayer.User;

/**
 * Conversion class 'beansbinding' from date to strin and vice versa.
 *
 * @author Ahmad Ali.
 *         Created Apr 5, 2012.
 */
public class TechConvertor extends Converter<User, Object> {

	@Override
	public Object convertForward(User arg0) {
		return arg0.getName();
	}

	@Override
	public User convertReverse(Object arg0) {
		LinkedList<User> techs = Users.getUsers("role='technician'");
		for (User c : techs) {
			if (c.getName().equals(arg0)) {
				return c;
			}
		}
		return null;
	}


}
