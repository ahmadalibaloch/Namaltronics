package Beans_Utils;

import java.sql.Time;

import org.jdesktop.beansbinding.Validator;

import UI_Utils.Prompt;

/**
 * Validates the tiem formate for beans biding
 *
 * @author Administrator.
 *         Created Apr 22, 2012.
 */
@SuppressWarnings("rawtypes")
public class TimeValidator extends Validator<Time> {
	@Override
	public org.jdesktop.beansbinding.Validator.Result validate(Time arg0) {
			Prompt.showMessage("error! enter some text ", "eror", "error");
		return null;
	}

}
