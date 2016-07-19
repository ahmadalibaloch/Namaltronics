package UILayer;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import UI_Utils.Prompt;

import DALayer.Users;
import DomainLayer.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;

/**
 * Login dialoge.
 * 
 * @author Ahmad Ali. Created Apr 21, 2012.
 */
@SuppressWarnings("deprecation")
public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUserName;
	private JPasswordField txtPassword;

	/**
	 * Create the frame.
	 */
	public Login() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/small/namaltronics16.png")));
		setTitle("Namaltronics");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 300, 450, 300);

		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		this.contentPane.setLayout(null);

		this.txtUserName = new JTextField();
		this.txtUserName.setText("ahm");
		this.txtUserName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.txtUserName.setColumns(10);
		this.txtUserName.setBounds(141, 115, 155, 20);
		this.contentPane.add(this.txtUserName);

		JLabel label = new JLabel("Login");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 40));
		label.setBounds(10, 11, 413, 50);
		this.contentPane.add(label);

		JLabel label_1 = new JLabel("User Name ");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setBounds(61, 117, 70, 14);
		this.contentPane.add(label_1);

		JLabel label_2 = new JLabel("Password ");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_2.setBounds(61, 153, 70, 14);
		this.contentPane.add(label_2);

		this.txtPassword = new JPasswordField();
		this.txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(arg0.isActionKey())
					btnLoginEventHandler();
			}
		});
		this.txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.txtPassword.setBounds(141, 151, 155, 20);
		this.contentPane.add(this.txtPassword);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnLoginEventHandler();
			}
		});
		btnLogin.setActionCommand("OK");
		btnLogin.setBounds(139, 182, 67, 23);
		this.contentPane.add(btnLogin);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login.this.dispose();
			}
		});
		btnCancel.setActionCommand("Cancel");
		btnCancel.setBounds(229, 182, 67, 23);
		this.contentPane.add(btnCancel);
		setVisible(true);
	}

	/**
	 * validate the fields username and password
	 * 
	 * @return true false
	 */

	boolean validateFields() {
		if (this.txtUserName.getText() != null
				&& !this.txtUserName.getText().equalsIgnoreCase(""))
			if (this.txtPassword.getPassword() != null
					&& !this.txtPassword.getText().equalsIgnoreCase(""))
				return true;
			else {
				this.txtPassword.setBackground(Color.red);
				//Prompt.showError("*user name required", this.txtUserName,this.getContentPane());
			}
		else {
			this.txtUserName.setBackground(Color.red);
			//Prompt.showError("*password required", this.txtPassword,this.getContentPane());
		}
		return false;

	}

	private void btnLoginEventHandler() {
		LinkedList<User> users;
		if (validateFields())
			if ((users = Users.getUsers("userName='"
					+ this.txtUserName.getText() + "' AND password='"
					+ this.txtPassword.getText().toString() + "'")).size() > 0 ) {
				this.user = users.getFirst();
				login();
			}
			else
				Prompt.showMessage("user name or password incorrect", "Login", "error");
	}

	/**
	 * user to be logged in.
	 */
	public User user = null;

	/**
	 * login action. how ?
	 * 
	 * @return user
	 */
	public boolean login() {
		if (this.user != null) {
			this.hide();
			new MainForm(this);
			return true;
		}
		return false;
	}

}
