package UI_Utils;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * Utility class for easing with showing messages 
 *
 * @author Administrator.
 *         Created Apr 9, 2012.
 */
public class Prompt{
	/**
	 * show message bos method
	 *
	 * @param message to be displayed in GUI message window box.
	 * @param title of the message
	 * @param alertType can be 'info', 'warning', 'error', 'question' or 'plain'
	 */
	public static void showMessage(String message,String title,String alertType){
		if(alertType.equalsIgnoreCase("info"))
			JOptionPane.showMessageDialog(null, message,title,JOptionPane.INFORMATION_MESSAGE);
		else if(alertType.equalsIgnoreCase("warning"))
			JOptionPane.showMessageDialog(null, message,title,JOptionPane.WARNING_MESSAGE);
		else if(alertType.equalsIgnoreCase("error"))
			JOptionPane.showMessageDialog(null, message,title,JOptionPane.ERROR_MESSAGE);
		else if(alertType.equalsIgnoreCase("question"))
			JOptionPane.showMessageDialog(null, message,title,JOptionPane.QUESTION_MESSAGE);
		else if(alertType.equalsIgnoreCase("plain"))
			JOptionPane.showMessageDialog(null, message,title,JOptionPane.PLAIN_MESSAGE);
	}
	/**
	 * Erro message showed
	 */
	static JLabel lblError = new JLabel();
	/**
	 * shows a label next to the component i.e field.
	 *
	 * @param message to be displayed next to component.
	 * @param component having error
	 * @param container of the component and message lable.
	 */
	public static void showError(String message,JComponent component,Container container){
		component.setForeground(Color.red);
		//lblError.setBounds(component.getBounds().x+component.getBounds().width+2, component.getBounds().y, 82, 100);
		lblError.setBounds(10,20,30,40);
		lblError.setText(message);
		container.add(lblError);
	}

}
