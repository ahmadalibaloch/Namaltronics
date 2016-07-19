package Beans_Utils;

import org.jdesktop.beansbinding.Converter;

import DomainLayer.DifficultyLevel;

/**
 * difficulty level slider value to enum convertor
 *
 * @author Ahmad Ali.
 *         Created Apr 9, 2012.
 */
public class DifficultyLevelConvertor extends Converter<DifficultyLevel, Integer> {
	@Override
	public DifficultyLevel convertReverse(Integer arg0) {
		System.out.println("in difficulty conversion.");
		return  DifficultyLevel.values()[arg0];
	}

	@Override
	public Integer convertForward(DifficultyLevel arg0) {
		System.out.println("in difficulty conversion.");
		return arg0.ordinal();
	}

}
