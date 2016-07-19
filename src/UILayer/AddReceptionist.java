package UILayer;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Bindings;

import DALayer.Users;
import DomainLayer.User;
import DomainLayer.UserStatus;
import UI_Utils.FormType;
import java.awt.Toolkit;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author Ahmad Ali. Created March 16, 2012.
 */
public class AddReceptionist extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtReceName;
	private JTextField txtUserName;
	private JTextField txtPassword;

	/**
	 * Create the frame.
	 */
	private User rece = new User();
	private FormType frmType = FormType.saving;
	/**
	 * TODO the reference to form ViewUsers.
	 */
	ViewUsers vReces;
	private JTextField txtPhone;
	private JTextField txtEmail;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblError;

	/**
	 * TODO With initialzing tells what perspective ot open form in.
	 * 
	 * @param frmType
	 *            is can be FormType.saving or editing
	 */
	public AddReceptionist(FormType frmType) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddReceptionist.class.getResource("/small/addCust.png")));
		setVisible(true);
		setTitle("Add Receptionist");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 580, 459);
		this.contentPane = new JPanel();
		this.contentPane.setToolTipText("Add Receptionist Details");
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		this.contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("Add a Receptionist");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblTitle.setBounds(10, 11, 517, 50);
		this.contentPane.add(lblTitle);

		JLabel lblName = new JLabel("Receptionist Name");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblName.setBounds(23, 116, 102, 14);
		this.contentPane.add(lblName);
		this.lblError = new JLabel("*error");
		this.lblError.setForeground(SystemColor.control);
		this.lblError.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblError.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.lblError.setBounds(312, 113, 250, 20);
		this.contentPane.add(this.lblError);

		JLabel lblStatus = new JLabel("Status");
		lblStatus.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblStatus.setBounds(38, 310, 87, 14);
		this.contentPane.add(lblStatus);
		this.txtReceName = new JTextField();
		this.txtReceName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				hideError();
			}
		});
		this.txtReceName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.txtReceName.setColumns(10);
		this.txtReceName.setBounds(139, 113, 163, 20);
		this.contentPane.add(this.txtReceName);

		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUserName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUserName.setBounds(38, 155, 87, 14);
		this.contentPane.add(lblUserName);

		this.txtUserName = new JTextField();
		this.txtUserName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				hideError();
			}
		});
		this.txtUserName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.txtUserName.setColumns(10);
		this.txtUserName.setBounds(139, 152, 163, 20);
		this.contentPane.add(this.txtUserName);

		this.txtPassword = new JTextField();
		this.txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				hideError();
			}
		});
		this.txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.txtPassword.setColumns(10);
		this.txtPassword.setBounds(139, 191, 163, 20);
		this.contentPane.add(this.txtPassword);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPassword.setBounds(38, 191, 87, 14);
		this.contentPane.add(lblPassword);

		JButton btnAddReceptionist = new JButton("Save");
		btnAddReceptionist.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnSaveEventHandler();
			}
		});
		btnAddReceptionist.setBounds(139, 363, 91, 23);
		this.contentPane.add(btnAddReceptionist);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddReceptionist.this.dispose();
			}
		});
		btnCancel.setBounds(261, 363, 91, 23);
		this.contentPane.add(btnCancel);

		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPhone.setBounds(38, 230, 87, 14);
		this.contentPane.add(lblPhone);

		this.txtPhone = new JTextField();
		this.txtPhone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.txtPhone.setColumns(10);
		this.txtPhone.setBounds(139, 230, 163, 20);
		this.contentPane.add(this.txtPhone);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEmail.setBounds(38, 272, 87, 14);
		this.contentPane.add(lblEmail);

		this.txtEmail = new JTextField();
		this.txtEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				AddReceptionist.this.lblError.setForeground(SystemColor.control);
			}
		});
		this.txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.txtEmail.setColumns(10);
		this.txtEmail.setBounds(139, 269, 163, 20);
		this.contentPane.add(this.txtEmail);

		JRadioButton rdoActive = new JRadioButton("Active");
		this.buttonGroup.add(rdoActive);
		rdoActive.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddReceptionist.this.rece.setUserStatus(UserStatus
						.valueOf(getSelection(AddReceptionist.this.buttonGroup)
								.getText().toLowerCase()));
			}
		});
		rdoActive.setSelected(true);
		rdoActive.setBounds(139, 305, 55, 23);
		this.contentPane.add(rdoActive);

		JRadioButton tdoInActive = new JRadioButton("InActive");
		tdoInActive.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddReceptionist.this.rece.setUserStatus(UserStatus
						.valueOf(getSelection(AddReceptionist.this.buttonGroup)
								.getText().toLowerCase()));
			}
		});
		this.buttonGroup.add(tdoInActive);
		tdoInActive.setBounds(196, 305, 65, 23);
		this.contentPane.add(tdoInActive);

		JRadioButton rdoPending = new JRadioButton("Pending");
		rdoPending.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddReceptionist.this.rece.setUserStatus(UserStatus
						.valueOf(getSelection(AddReceptionist.this.buttonGroup)
								.getText().toLowerCase()));
			}
		});
		this.buttonGroup.add(rdoPending);
		rdoPending.setBounds(261, 305, 74, 23);
		this.contentPane.add(rdoPending);
		initDataBindings();

		//
		this.frmType = frmType;
		if (this.frmType == FormType.editing) {
			lblTitle.setText("Edit Receptionist");
		}
	}

	//
	//
	/**
	 * 
	 * @param rece
	 * @param vReces
	 *            reference is to send back the update event when save button
	 *            clicked.
	 */
	public void editReceptionist(User rece, ViewUsers vReces) {
		this.vReces = vReces;
		this.rece = rece;
		setSelection(this.buttonGroup, this.rece.getUserStatus().toString());
		initDataBindings();
		// System.out.println(Rece.name);
	}

	private void btnSaveEventHandler() {
		if (validateFields())
			if (this.frmType == FormType.saving) {
				Users.saveUser(this.rece);
				clearFields();
			} else {
				Users.editUser(this.rece);
				this.vReces.updateTable();
				this.dispose();
			}
	}

	private void clearFields() {
		this.txtReceName.setText("");
		this.txtUserName.setText("");
		this.txtPassword.setText("");
	}

	private boolean validateFields() {
		if (this.txtReceName.getText() != null
				&& !this.txtReceName.getText().equalsIgnoreCase(""))
			if (this.txtUserName.getText() != null
					&& !this.txtUserName.getText().equalsIgnoreCase(""))
				if (this.txtPassword.getText() != null
						&& !this.txtPassword.getText().equalsIgnoreCase("")) {
					this.rece.setAdded(new Date());
					this.rece.setRole("receptionist");
					this.rece.setLastLogin(new Date());
					this.rece.setUserStatus(UserStatus.valueOf(getSelection(this.buttonGroup).getText().toLowerCase()));
					return true;
				} else {
					showError("password is required",this.txtPassword);
				}
			else
				showError("user name is required",this.txtUserName);
		else
			showError("receptionist name is required",this.txtReceName);
		return false;
	}

	//
	/**
	 * Returns the selected JRadioButton in this group
	 * 
	 * @param group
	 *            of radio buttons
	 * @return JRadiou Button selected by this group at this time or null if no
	 *         selection
	 */
	public JRadioButton getSelection(ButtonGroup group) {
		for (Enumeration<AbstractButton> e = group.getElements(); e
				.hasMoreElements();) {
			JRadioButton b = (JRadioButton) e.nextElement();
			if (b.getModel() == group.getSelection())
				return b;
		}
		return null;
	}

	//
	/**
	 * sets the selected radio button to seleted if the button has text given in
	 * value
	 * 
	 * @param group
	 *            to be selecte the button in
	 * @param value
	 *            of the item to be seleted
	 */
	public void setSelection(ButtonGroup group, Object value) {
		for (Enumeration<AbstractButton> e = group.getElements(); e
				.hasMoreElements();) {
			JRadioButton b = (JRadioButton) e.nextElement();
			if (b.getText().equalsIgnoreCase((String) value)) {
				group.setSelected(b.getModel(), true);
				return;
			}
		}
	}

	/**
	 * TODO Put here a description of what this method does.
	 * 
	 */
	protected void initDataBindings() {
		BeanProperty<User, String> userBeanProperty = BeanProperty
				.create("name");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty
				.create("text");
		AutoBinding<User, String, JTextField, String> autoBinding = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, this.rece,
						userBeanProperty, this.txtReceName,
						jTextFieldBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<User, String> userBeanProperty_1 = BeanProperty
				.create("userName");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_1 = BeanProperty
				.create("text");
		AutoBinding<User, String, JTextField, String> autoBinding_1 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, this.rece,
						userBeanProperty_1, this.txtUserName,
						jTextFieldBeanProperty_1);
		autoBinding_1.bind();
		//
		BeanProperty<User, String> userBeanProperty_2 = BeanProperty
				.create("password");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_2 = BeanProperty
				.create("text");
		AutoBinding<User, String, JTextField, String> autoBinding_2 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, this.rece,
						userBeanProperty_2, this.txtPassword,
						jTextFieldBeanProperty_2);
		autoBinding_2.bind();
		//
		BeanProperty<User, String> userBeanProperty_3 = BeanProperty
				.create("phone");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_3 = BeanProperty
				.create("text");
		AutoBinding<User, String, JTextField, String> autoBinding_3 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, this.rece,
						userBeanProperty_3, this.txtPhone,
						jTextFieldBeanProperty_3);
		autoBinding_3.bind();
		//
		BeanProperty<User, String> userBeanProperty_4 = BeanProperty
				.create("email");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_4 = BeanProperty
				.create("text");
		AutoBinding<User, String, JTextField, String> autoBinding_4 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, this.rece,
						userBeanProperty_4, this.txtEmail,
						jTextFieldBeanProperty_4);
		autoBinding_4.bind();
	}
	/////
	private void showError(String message,JComponent component){
		this.lblError.setBounds(component.getBounds().x+component.getBounds().width+5, component.getBounds().y, 500, this.lblError.getBounds().height);
		this.lblError.setForeground(Color.red);
		this.lblError.setText(message);
	}
	private void hideError(){
		this.lblError.setForeground(SystemColor.control);
	}
}