package Beans_Utils;

import org.jdesktop.beansbinding.Converter;

import DomainLayer.SkillLevel;

/**
 * Converts slider value to skill level enum
 *
 * @author Administrator.
 *         Created Apr 9, 2012.
 */
public class SkillLevelConvertor extends Converter<SkillLevel, Integer> {
	@Override
	public SkillLevel convertReverse(Integer arg0) {
		return  SkillLevel.values()[arg0];
	}

	@Override
	public Integer convertForward(SkillLevel arg0) {
		return arg0.ordinal();
	}

}
