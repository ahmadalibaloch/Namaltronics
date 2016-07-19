package UILayer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JProgressBar;

/**
 * UI class for splash screen design
 * @author Ahmad Ali
 *
 */
public class Welcome extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the frame.
	 */
	public Welcome() {
		
		JFrame f = new JFrame();
		f.setResizable(false);
		f.getContentPane().setLayout(new BorderLayout());
		f.setBounds(400, 200, 472, 310);
        f.setUndecorated(true);

        JProgressBar progressBar = new JProgressBar();
        progressBar.setMaximum(190);
		progressBar.setForeground(new Color(255, 165, 0));
		progressBar.setBounds(400, 498, 472, 13);
		JLabel lblSplash=null;
		try {
			lblSplash = new JLabel(new ImageIcon(ImageIO.read(new File("splash.jpg"))));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		f.getContentPane().add(progressBar,BorderLayout.SOUTH);
		f.getContentPane().add(lblSplash,BorderLayout.NORTH);
        f.setVisible(true);
        int i =1;
        while(i<200){
        	try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
			}
        	progressBar.setValue(i++);
        }
		new Login();
		f.dispose();
		this.dispose();
	}

}
