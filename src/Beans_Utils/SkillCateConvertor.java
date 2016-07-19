package Beans_Utils;

import java.util.LinkedList;

import org.jdesktop.beansbinding.Converter;

import DALayer.SkillCategories;
import DomainLayer.SkillCategory;

/**
 * xconversion class 'beansbinding' from date to strin and vice versa.
 *
 * @author Administrator.
 *         Created Apr 5, 2012.
 */
public class SkillCateConvertor extends Converter<SkillCategory, Object> {

	@Override
	public Object convertForward(SkillCategory arg0) {
		return arg0.name;
	}

	@Override
	public SkillCategory convertReverse(Object arg0) {
		LinkedList<SkillCategory> skillCates = SkillCategories.getSkillCategories("");
		for (SkillCategory s : skillCates) {
			if (s.name.equals(arg0)) {
				return s;
			}
		}
		return null;
	}


}
