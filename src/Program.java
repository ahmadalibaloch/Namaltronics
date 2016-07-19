import javax.swing.UIManager;

import UILayer.Welcome;


/**
 * Main form. The start of applicaiton. opens the login window.
 *
 * @author Administrator.
 *         Created March 16, 2012.
 */
public class Program {

	/**
	 * Main Class of the program. Starting point.
	 * @param args 
	 * 
	 */
	public static void main(String[] args) {
		/**
		 * This method sets the GUI style of the application( windows look and feel) and starts main form with menus.
		 * @author 09032287 Ahmad Ali
		 * @version 1.0
		 */
		//JFrame.setDefaultLookAndFeelDecorated(true);
		//JDialog.setDefaultLookAndFeelDecorated(true);
		try
		{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}
		catch (Exception ex)
		{
			System.out.println("Failed loading L&F: ");
			System.out.println(ex);
		}
		new Welcome();
		//new Login();
		//new MainForm(new Login());
		
	}

}
