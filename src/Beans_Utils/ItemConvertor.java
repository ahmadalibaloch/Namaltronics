package Beans_Utils;

import java.util.LinkedList;

import org.jdesktop.beansbinding.Converter;

import DALayer.Items;
import DomainLayer.Item;

/**
 * Conversion class 'beansbinding' from date to strin and vice versa.
 *
 * @author Administrator.
 *         Created Apr 5, 2012.
 */
public class ItemConvertor extends Converter<Item, Object> {

	@Override
	public Object convertForward(Item arg0) {
		return arg0.getName();
	}

	@Override
	public Item convertReverse(Object arg0) {
		LinkedList<Item> items = Items.getItems("");
		for (Item c : items) {
			if (c.getName().equals(arg0)) {
				return c;
			}
		}
		return null;
	}


}
