package UILayer;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Bindings;

import DALayer.Customers;
import DomainLayer.Customer;
import DomainLayer.Payment;
import UI_Utils.FormType;
import java.awt.Toolkit;

/**
 * Add Customer domain layer class
 * 
 * @author Administrator. Created March 16, 2012.
 */

public class AddCustomer extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCustName;
	private JTextField txtCustPhone;
	private JTextField txtEmail;

	/**
	 * Create the frame.
	 */
	private Customer cust = new Customer();
	private FormType frmType = FormType.saving;
	private JTextArea txtAddress;
	private JTextField txtPostCode;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnCredit;
	/**
	 * Reference to View Customers form.
	 */
	ViewCustomers vCusts;
	private JRadioButton rdbtnDebit;
	private JRadioButton rdbtnCash;
	private JLabel lblError;

	// Radio Button manipulation(binding not working, or how is works? :P)
	/**
	 * with initializing tells the form perspective to open in saving or
	 * editing mood.
	 * 
	 * @param frmType
	 */
	public AddCustomer(FormType frmType) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddCustomer.class.getResource("/small/addCust.png")));
		setVisible(true);
		setTitle("Add Customer");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 580, 459);
		this.contentPane = new JPanel();
		this.contentPane.setToolTipText("Add Customer Details");
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		this.contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("Add a Customer");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblTitle.setBounds(10, 11, 517, 50);
		this.contentPane.add(lblTitle);

		JLabel lblCustName = new JLabel("Customer Name");
		lblCustName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCustName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCustName.setBounds(38, 116, 87, 14);
		this.contentPane.add(lblCustName);

		this.txtCustName = new JTextField();
		this.txtCustName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				hideError();
			}
		});
		this.txtCustName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.txtCustName.setColumns(10);
		this.txtCustName.setBounds(139, 113, 163, 20);
		this.contentPane.add(this.txtCustName);

		JLabel lblCustPhone = new JLabel("Phone");
		lblCustPhone.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCustPhone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCustPhone.setBounds(38, 144, 87, 14);
		this.contentPane.add(lblCustPhone);

		this.txtCustPhone = new JTextField();
		this.txtCustPhone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				hideError();
			}
		});
		this.txtCustPhone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.txtCustPhone.setColumns(10);
		this.txtCustPhone.setBounds(139, 141, 163, 20);
		this.contentPane.add(this.txtCustPhone);

		this.txtEmail = new JTextField();
		this.txtEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				hideError();
			}
		});
		this.txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.txtEmail.setColumns(10);
		this.txtEmail.setBounds(139, 169, 163, 20);
		this.contentPane.add(this.txtEmail);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEmail.setBounds(38, 172, 87, 14);
		this.contentPane.add(lblEmail);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAddress.setBounds(38, 234, 87, 14);
		this.contentPane.add(lblAddress);

		this.txtAddress = new JTextArea();
		this.txtAddress.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				hideError();
			}
		});
		this.txtAddress.setWrapStyleWord(true);
		this.txtAddress.setRows(3);
		this.txtAddress.setLineWrap(true);
		this.txtAddress.setBounds(139, 231, 163, 66);
		this.contentPane.add(this.txtAddress);

		this.txtPostCode = new JTextField();
		this.txtPostCode.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				hideError();
			}
		});
		this.txtPostCode.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.txtPostCode.setColumns(10);
		this.txtPostCode.setBounds(139, 200, 163, 20);
		this.contentPane.add(this.txtPostCode);

		JLabel lblPostCode = new JLabel("Post Code");
		lblPostCode.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPostCode.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPostCode.setBounds(38, 203, 87, 14);
		this.contentPane.add(lblPostCode);

		JButton btnAddCustomer = new JButton("Save");
		btnAddCustomer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnSaveEventHandler();
			}
		});
		btnAddCustomer.setBounds(160, 379, 91, 23);
		this.contentPane.add(btnAddCustomer);

		JLabel lblPaymentType = new JLabel("Payment Type");
		lblPaymentType.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPaymentType.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPaymentType.setBounds(38, 311, 87, 14);
		this.contentPane.add(lblPaymentType);

		this.rdbtnCredit = new JRadioButton("Credit");
		this.rdbtnCredit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddCustomer.this.cust.payment = Payment.valueOf(getSelection(
						AddCustomer.this.buttonGroup).getText().toLowerCase());
			}
		});
		this.buttonGroup.add(this.rdbtnCredit);
		this.rdbtnCredit.setBounds(139, 308, 55, 23);
		this.contentPane.add(this.rdbtnCredit);
		this.rdbtnDebit = new JRadioButton("Debit");
		this.rdbtnDebit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddCustomer.this.cust.payment = Payment.valueOf(getSelection(
						AddCustomer.this.buttonGroup).getText().toLowerCase());
			}
		});
		this.buttonGroup.add(this.rdbtnDebit);
		this.rdbtnDebit.setBounds(196, 308, 55, 23);
		this.contentPane.add(this.rdbtnDebit);

		this.rdbtnCash = new JRadioButton("Cash");
		this.rdbtnCash.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddCustomer.this.cust.payment = Payment.valueOf(getSelection(
						AddCustomer.this.buttonGroup).getText().toLowerCase());
			}
		});
		this.buttonGroup.add(this.rdbtnCash);
		this.rdbtnCash.setBounds(247, 308, 55, 23);
		this.contentPane.add(this.rdbtnCash);
		this.rdbtnCredit.setSelected(true);
		
		this.lblError = new JLabel("lblErro");
		this.lblError.setForeground(SystemColor.control);
		this.lblError.setBounds(312, 113, 178, 20);
		this.contentPane.add(this.lblError);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddCustomer.this.dispose();
			}
		});
		btnCancel.setBounds(280, 379, 91, 23);
		this.contentPane.add(btnCancel);
		initDataBindings();

		//
		this.frmType = frmType;
		if (this.frmType == FormType.editing) {
			lblTitle.setText("Edit Customer");

		}
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

	//
	//
	/**
	 * 
	 * @param cust
	 *            the customer going edit
	 * @param vCusts
	 *            list of customers to be refreshed
	 */
	public void editCustomer(Customer cust, ViewCustomers vCusts) {
		this.vCusts = vCusts;
		this.cust = cust;
		initDataBindings();
		setSelection(this.buttonGroup, this.cust.payment.toString());
		// System.out.println(cust.name);
	}

	private void btnSaveEventHandler() {
		if (validateFields())
			if (this.frmType == FormType.saving) {
				Customers.saveCustomer(this.cust);
				clearFields();
			} else {
				Customers.editCustomer(this.cust);
				this.vCusts.updateTable();
				this.dispose();
			}

	}

	/**
	 * clear field to emty
	 * 
	 */
	private void clearFields() {
		this.txtAddress.setText("");
		this.txtCustName.setText("");
		this.txtCustPhone.setText("");
		this.txtEmail.setText("");
		this.txtPostCode.setText("");

	}

	private boolean validateFields() {
		if (this.txtCustName.getText() != null
				&& !this.txtCustName.getText().equalsIgnoreCase(""))
			if (this.txtCustPhone.getText() != null
					&& !this.txtCustPhone.getText().equalsIgnoreCase(""))
				if (this.txtEmail.getText() != null
						&& !this.txtEmail.getText().equalsIgnoreCase(""))
					if (this.txtPostCode.getText() != null
							&& !this.txtPostCode.getText().equalsIgnoreCase(""))
						if (this.txtAddress.getText() != null
								&& !this.txtAddress.getText().equalsIgnoreCase(
										"")) {
							this.cust.setPayment(Payment.valueOf(getSelection(
									this.buttonGroup).getText().toLowerCase()));
							return true;
						} else
							showError("Address is required for customer",this.txtAddress);
					else
						showError("Post code is required for customer",this.txtPostCode);
				else
					showError("Email is required",this.txtEmail);
			else
				showError("Phone number is required",this.txtCustPhone);
		else
			showError("Customer name is required ",this.txtCustName);
		return false;
	}

	/**
	 * initialize the bindings
	 * 
	 */
	protected void initDataBindings() {
		BeanProperty<Customer, String> customerBeanProperty = BeanProperty
				.create("name");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty
				.create("text");
		AutoBinding<Customer, String, JTextField, String> autoBinding = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, this.cust,
						customerBeanProperty, this.txtCustName,
						jTextFieldBeanProperty, "bndCustName");
		autoBinding.bind();
		//
		BeanProperty<Customer, String> customerBeanProperty_1 = BeanProperty
				.create("phone");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_1 = BeanProperty
				.create("text");
		AutoBinding<Customer, String, JTextField, String> autoBinding_1 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, this.cust,
						customerBeanProperty_1, this.txtCustPhone,
						jTextFieldBeanProperty_1, "bndCustPhone");
		autoBinding_1.bind();
		//
		BeanProperty<Customer, String> customerBeanProperty_2 = BeanProperty
				.create("email");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_2 = BeanProperty
				.create("text");
		AutoBinding<Customer, String, JTextField, String> autoBinding_2 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, this.cust,
						customerBeanProperty_2, this.txtEmail,
						jTextFieldBeanProperty_2, "bndCustEmail");
		autoBinding_2.bind();
		//
		BeanProperty<Customer, String> customerBeanProperty_3 = BeanProperty
				.create("address");
		BeanProperty<JTextArea, String> jTextAreaBeanProperty = BeanProperty
				.create("text");
		AutoBinding<Customer, String, JTextArea, String> autoBinding_3 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, this.cust,
						customerBeanProperty_3, this.txtAddress,
						jTextAreaBeanProperty, "bndCustAddress");
		autoBinding_3.bind();
		//
		BeanProperty<Customer, String> customerBeanProperty_4 = BeanProperty
				.create("postcode");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_3 = BeanProperty
				.create("text");
		AutoBinding<Customer, String, JTextField, String> autoBinding_4 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, this.cust,
						customerBeanProperty_4, this.txtPostCode,
						jTextFieldBeanProperty_3, "bndCustPostCode");
		autoBinding_4.bind();
		//
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