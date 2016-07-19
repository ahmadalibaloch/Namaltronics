package Beans_Utils;

import org.jdesktop.beansbinding.Converter;

/**
 * Difficulty level slider value to text convertory
 *
 * @author Administrator.
 *         Created Apr 9, 2012.
 */
public class DifficultyLevelTextConvertor extends Converter<String, Integer> {

	@Override
	public Integer convertForward(String arg0) {
		if(arg0.equals("Easy"))
			return 0;
		else if(arg0.equals("Normal"))
			return 1;
		else if(arg0.equals("Difficult"))
			return 2;
		else
			return -1;
	}

	@Override
	public String convertReverse(Integer arg0) {
		if(arg0==0)
			return "Easy";
		else if(arg0==1)
			return "Normal";
		else if(arg0==2)
			return "Difficult";
		else
			return "NA";
	}

}
