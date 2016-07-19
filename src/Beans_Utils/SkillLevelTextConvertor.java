package Beans_Utils;

import org.jdesktop.beansbinding.Converter;

/**
 * Converts the slider value to skill level text
 *
 * @author Administrator.
 *         Created Apr 9, 2012.
 */
public class SkillLevelTextConvertor extends Converter<String, Integer> {

	@Override
	public Integer convertForward(String arg0) {
		if(arg0.equals("Normal"))
			return 0;
		else if(arg0.equals("Good"))
			return 1;
		else if(arg0.equals("Efficient"))
			return 2;
		else
			return 3;
	}

	@Override
	public String convertReverse(Integer arg0) {
		if(arg0==0)
			return "Normal";
		else if(arg0==1)
			return "Good";
		else if(arg0==2)
			return "Efficient";
		else
			return "Best";
	}

}
