package UILayer;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.Enumeration;
import java.util.LinkedList;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Bindings;

import Beans_Utils.SkillCateConvertor;
import Beans_Utils.SkillLevelConvertor;
import Beans_Utils.SkillLevelTextConvertor;
import DALayer.SkillCategories;
import DALayer.Skills;
import DALayer.Users;
import DomainLayer.Skill;
import DomainLayer.SkillCategory;
import DomainLayer.SkillLevel;
import DomainLayer.User;
import DomainLayer.UserStatus;
import UI_Utils.FormType;
import java.awt.Toolkit;

/**
 * UI class for getting input for adding a new Technician
 * 
 * @author Ahmad Ali. Created March 16, 2012.
 */
public class AddTechnician extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtPassword;
	private JTextField txtTechPhone;
	private JTextField txtTechEmail;
	private JTextField txtTechSkillLevel;

	private JTextArea txtTechSkillDetail;
	private JSlider sldrTechSkillLevel;
	private JComboBox cmbSkillCategory;

	private User tech = new User();
	private Skill skill = new Skill();
	private LinkedList<SkillCategory> skillCategories;
	private ViewUsers vTechs;
	private FormType frmType = FormType.saving;
	private JLabel lblError;
	private JLabel lblError1;
	//
	private final JTabbedPane tabbedPane;
	private JTextField txtName;
	private JTextField txtUserName;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Create the frame.
	 * 
	 * @param frmType
	 */
	public AddTechnician(FormType frmType) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddTechnician.class.getResource("/small/addCust.png")));
		setVisible(true);
		setTitle("Add Technician");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 660, 510);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		this.contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("Add Technician");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblTitle.setBounds(10, 5, 630, 50);
		this.contentPane.add(lblTitle);

		JButton btnSaveTechnician = new JButton("Save Technician");
		btnSaveTechnician.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SaveTechnicianEventHandler();
			}
		});
		btnSaveTechnician.setBounds(353, 424, 111, 23);
		this.contentPane.add(btnSaveTechnician);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddTechnician.this.dispose();
			}
		});
		btnCancel.setBounds(474, 424, 95, 23);
		this.contentPane.add(btnCancel);

		this.tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		this.tabbedPane.setBounds(31, 57, 548, 343);
		this.contentPane.add(this.tabbedPane);

		JPanel panel = new JPanel();
		this.tabbedPane.addTab("Technician Detail", null, panel, null);
		panel.setBorder(null);
		panel.setLayout(null);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPassword.setBounds(36, 102, 64, 14);
		panel.add(lblPassword);

		this.txtPassword = new JTextField();
		this.txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				hideError();
			}
		});
		this.txtPassword.setBorder(new SoftBevelBorder(BevelBorder.LOWERED,
				null, null, null, null));
		this.txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.txtPassword.setColumns(10);
		this.txtPassword.setBounds(110, 99, 136, 20);
		panel.add(this.txtPassword);

		this.txtTechPhone = new JTextField();
		this.txtTechPhone.setBorder(new SoftBevelBorder(BevelBorder.LOWERED,
				null, null, null, null));
		this.txtTechPhone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.txtTechPhone.setColumns(10);
		this.txtTechPhone.setBounds(110, 133, 136, 20);
		panel.add(this.txtTechPhone);

		JLabel label_1 = new JLabel("Phone");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setBounds(36, 136, 64, 14);
		panel.add(label_1);

		this.txtTechEmail = new JTextField();
		this.txtTechEmail.setBorder(new SoftBevelBorder(BevelBorder.LOWERED,
				null, null, null, null));
		this.txtTechEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.txtTechEmail.setColumns(10);
		this.txtTechEmail.setBounds(110, 167, 136, 20);
		panel.add(this.txtTechEmail);

		JLabel label_2 = new JLabel("Email");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_2.setBounds(36, 170, 58, 14);
		panel.add(label_2);

		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (validateTechFields()) {
					AddTechnician.this.tabbedPane.setSelectedIndex(1);
				}
			}
		});
		btnNext.setBounds(10, 304, 91, 23);
		panel.add(btnNext);

		this.txtName = new JTextField();
		this.txtName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				hideError();
			}
		});
		this.txtName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.txtName.setColumns(10);
		this.txtName.setBounds(110, 31, 136, 20);
		panel.add(this.txtName);

		JLabel lblFullName = new JLabel("Full Name");
		lblFullName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFullName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFullName.setBounds(36, 34, 64, 14);
		panel.add(lblFullName);

		this.txtUserName = new JTextField();
		this.txtUserName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				hideError();
			}
		});
		this.txtUserName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.txtUserName.setColumns(10);
		this.txtUserName.setBounds(110, 65, 136, 20);
		panel.add(this.txtUserName);

		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUserName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUserName.setBounds(36, 68, 64, 14);
		panel.add(lblUserName);

		JLabel label_3 = new JLabel("Status");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_3.setBounds(10, 204, 87, 14);
		panel.add(label_3);

		JRadioButton radioButton = new JRadioButton("Active");
		radioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddTechnician.this.tech.setUserStatus(UserStatus
						.valueOf(getSelection(AddTechnician.this.buttonGroup)
								.getText().toLowerCase()));
			}
		});
		this.buttonGroup.add(radioButton);
		radioButton.setSelected(true);
		radioButton.setBounds(111, 201, 55, 23);
		panel.add(radioButton);

		JRadioButton radioButton_1 = new JRadioButton("InActive");
		radioButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddTechnician.this.tech.setUserStatus(UserStatus
						.valueOf(getSelection(AddTechnician.this.buttonGroup)
								.getText().toLowerCase()));
			}
		});
		this.buttonGroup.add(radioButton_1);
		radioButton_1.setBounds(168, 201, 65, 23);
		panel.add(radioButton_1);

		JRadioButton radioButton_2 = new JRadioButton("Pending");
		radioButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddTechnician.this.tech.setUserStatus(UserStatus
						.valueOf(getSelection(AddTechnician.this.buttonGroup)
								.getText().toLowerCase()));
			}
		});
		this.buttonGroup.add(radioButton_2);
		radioButton_2.setBounds(233, 201, 74, 23);
		panel.add(radioButton_2);
		
		this.lblError = new JLabel("*error");
		this.lblError.setBounds(256, 32, 183, 20);
		panel.add(this.lblError);
		this.lblError.setForeground(SystemColor.control);

		JPanel panel_1 = new JPanel();
		this.tabbedPane.addTab("Skill Detail", null, panel_1, null);
		panel_1.setBorder(null);
		panel_1.setLayout(null);

		JLabel lblSkilId = new JLabel("Skill Category");
		lblSkilId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSkilId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSkilId.setBounds(13, 32, 87, 14);
		panel_1.add(lblSkilId);

		JLabel lblLevel = new JLabel("Skills Level");
		lblLevel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLevel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLevel.setBounds(13, 63, 87, 14);
		panel_1.add(lblLevel);

		JLabel lblDetail = new JLabel("Detail");
		lblDetail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDetail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDetail.setBounds(13, 102, 87, 14);
		panel_1.add(lblDetail);

		this.txtTechSkillDetail = new JTextArea();
		this.txtTechSkillDetail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				hideError();
			}
		});
		this.txtTechSkillDetail
				.setToolTipText("any details related to the skill or skill level.");
		this.txtTechSkillDetail.setBorder(new SoftBevelBorder(
				BevelBorder.LOWERED, null, null, null, null));
		this.txtTechSkillDetail.setBounds(110, 97, 136, 83);
		panel_1.add(this.txtTechSkillDetail);

		this.sldrTechSkillLevel = new JSlider();
		this.sldrTechSkillLevel
				.setToolTipText("Select the level of skill current technician has.");
		this.sldrTechSkillLevel.setMaximum(3);
		this.sldrTechSkillLevel.setMajorTickSpacing(1);
		this.sldrTechSkillLevel.setSnapToTicks(true);
		this.sldrTechSkillLevel.setValue(1);
		this.sldrTechSkillLevel.setBounds(110, 63, 73, 24);
		panel_1.add(this.sldrTechSkillLevel);

		this.txtTechSkillLevel = new JTextField();
		this.txtTechSkillLevel.setText("Normal");
		this.txtTechSkillLevel.setHorizontalAlignment(SwingConstants.CENTER);
		this.txtTechSkillLevel.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.txtTechSkillLevel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.txtTechSkillLevel.setColumns(2);
		this.txtTechSkillLevel.setBounds(185, 63, 61, 20);
		panel_1.add(this.txtTechSkillLevel);

		this.cmbSkillCategory = new JComboBox();
		this.cmbSkillCategory.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				hideError();
			}
		});
		this.cmbSkillCategory
				.setToolTipText("Category in which the skill lies");
		this.cmbSkillCategory.setBounds(110, 29, 136, 22);
		panel_1.add(this.cmbSkillCategory);

		this.lblError1 = new JLabel("*error");
		this.lblError1.setForeground(SystemColor.control);
		this.lblError1.setBounds(256, 33, 183, 20);
		panel_1.add(this.lblError1);
		//
		//
		this.frmType = frmType;
		if (this.frmType == FormType.editing) {
			lblTitle.setText("Edit Technician");
		}
		//
		loadSkillCategories();
		//
		initDataBindings();
	}

	private boolean validateTechFields() {
		if (!this.txtName.getText().equalsIgnoreCase(""))
			if (!this.txtUserName.getText().equalsIgnoreCase(""))
				if (!this.txtPassword.getText().equalsIgnoreCase(""))
					return true;
				else
					showError("Password is required", this.txtPassword);
			else
				showError("Username is required", this.txtUserName);
		else
			showError("Name is required", this.txtName);
		return false;
	}

	private boolean validateSkillFields() {
		if ((this.cmbSkillCategory.getSelectedItem() != null))
			if (!this.txtTechSkillDetail.getText().equalsIgnoreCase("")) {
					return true;
			} else
				showError1("Enter some details of the skill", this.txtTechSkillDetail);
		else
			showError1("Select a skill category", this.cmbSkillCategory);
		return false;
	}

	private void setSkillId() {
		this.skill.tech_skilId = ++Skills.getSkills("").getLast().tech_skilId;
	}

	private void SaveTechnicianEventHandler() {
		if (validateTechFields())
			if (validateSkillFields()) {
				{
					//defaults
					setSkillId();
					this.tech.setUserStatus(UserStatus.valueOf(getSelection(
							this.buttonGroup).getText().toLowerCase()));
					this.tech.setAdded(new Date());
					this.tech.setLastLogin(new Date());
					this.tech.setRole("technician");
					//
					if (this.frmType == FormType.saving) {
						Users.saveTechnician(this.tech, this.skill);
						MainForm.updateTechAvailable("");
					} else {
						Users.editTechnician(this.tech, this.skill);
						this.vTechs.updateTable();
						this.dispose();
					}
				}
			} else
				this.tabbedPane.setSelectedIndex(1);
		else
			this.tabbedPane.setSelectedIndex(0);
	}

	//
	/**
	 * 
	 * @param tech
	 *            is the technician to be edited
	 * @param vTechs
	 *            is the reference variable for view techncians form
	 */
	public void editTechnician(User tech, ViewUsers vTechs) {
		this.vTechs = vTechs;
		this.tech = tech;
		this.skill = Skills.getSkills(
				"tech_skilId="
						+ "(SELECT tech_skilId FROM technicians WHERE techId="
						+ this.tech.getUserId() + ")").getFirst();
		setSelection(this.buttonGroup, this.tech.getUserStatus().toString());
		initDataBindings();
	}

	private void loadSkillCategories() {
		if ((this.skillCategories = SkillCategories.getSkillCategories(""))
				.size() > 0) {
			this.cmbSkillCategory.removeAllItems();
			for (SkillCategory s : this.skillCategories) {
				this.cmbSkillCategory.addItem(s.name);
			}
			this.cmbSkillCategory.setSelectedIndex(0);
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

	/**
	 * bind the domain layer models with UI elements
	 * 
	 */
	protected void initDataBindings() {
		BeanProperty<User, String> userBeanProperty = BeanProperty
				.create("name");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_4 = BeanProperty
				.create("text");
		AutoBinding<User, String, JTextField, String> autoBinding_4 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, this.tech,
						userBeanProperty, this.txtName,
						jTextFieldBeanProperty_4);
		autoBinding_4.bind();
		//
		BeanProperty<User, String> userBeanProperty_3 = BeanProperty
				.create("userName");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_3 = BeanProperty
				.create("text");
		AutoBinding<User, String, JTextField, String> autoBinding_3 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, this.tech,
						userBeanProperty_3, this.txtUserName,
						jTextFieldBeanProperty_3);
		autoBinding_3.bind();
		//
		BeanProperty<User, String> userBeanProperty_4 = BeanProperty
				.create("password");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty
				.create("text");
		AutoBinding<User, String, JTextField, String> autoBinding = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, this.tech,
						userBeanProperty_4, this.txtPassword,
						jTextFieldBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<User, String> userBeanProperty_1 = BeanProperty
				.create("phone");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_1 = BeanProperty
				.create("text");
		AutoBinding<User, String, JTextField, String> autoBinding_1 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, this.tech,
						userBeanProperty_1, this.txtTechPhone,
						jTextFieldBeanProperty_1);
		autoBinding_1.bind();
		//
		BeanProperty<User, String> userBeanProperty_2 = BeanProperty
				.create("email");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_2 = BeanProperty
				.create("text");
		AutoBinding<User, String, JTextField, String> autoBinding_2 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, this.tech,
						userBeanProperty_2, this.txtTechEmail,
						jTextFieldBeanProperty_2);
		autoBinding_2.bind();
		//
		BeanProperty<Skill, String> skillBeanProperty = BeanProperty
				.create("skillDetail");
		BeanProperty<JTextArea, String> jTextAreaBeanProperty = BeanProperty
				.create("text");
		AutoBinding<Skill, String, JTextArea, String> autoBinding_5 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, this.skill,
						skillBeanProperty, this.txtTechSkillDetail,
						jTextAreaBeanProperty);
		autoBinding_5.bind();
		//
		BeanProperty<JTextField, String> jTextFieldBeanProperty_5 = BeanProperty
				.create("text");
		BeanProperty<JSlider, Integer> jSliderBeanProperty = BeanProperty
				.create("value");
		AutoBinding<JTextField, String, JSlider, Integer> autoBinding_6 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE,
						this.txtTechSkillLevel, jTextFieldBeanProperty_5,
						this.sldrTechSkillLevel, jSliderBeanProperty);
		autoBinding_6.setConverter(new SkillLevelTextConvertor());
		autoBinding_6.bind();
		//
		BeanProperty<Skill, SkillLevel> skillBeanProperty_1 = BeanProperty
				.create("skillLevel");
		BeanProperty<JSlider, Integer> jSliderBeanProperty_1 = BeanProperty
				.create("value");
		AutoBinding<Skill, SkillLevel, JSlider, Integer> autoBinding_7 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, this.skill,
						skillBeanProperty_1, this.sldrTechSkillLevel,
						jSliderBeanProperty_1);
		autoBinding_7.setConverter(new SkillLevelConvertor());
		autoBinding_7.bind();
		//
		BeanProperty<Skill, SkillCategory> skillBeanProperty_2 = BeanProperty
				.create("skillCategory");
		BeanProperty<JComboBox, Object> jComboBoxBeanProperty = BeanProperty
				.create("selectedItem");
		AutoBinding<Skill, SkillCategory, JComboBox, Object> autoBinding_8 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, this.skill,
						skillBeanProperty_2, this.cmbSkillCategory,
						jComboBoxBeanProperty);
		autoBinding_8.setConverter(new SkillCateConvertor());
		autoBinding_8.bind();
	}

	// ////
	// ///
	private void showError(String message, JComponent component) {
		this.lblError.setBounds(component.getBounds().x
				+ component.getBounds().width + 5, component.getBounds().y,
				500, this.lblError.getBounds().height);
		this.lblError.setForeground(Color.red);
		this.lblError.setText(message);
	}
	private void showError1(String message, JComponent component) {
		this.lblError1.setBounds(component.getBounds().x
				+ component.getBounds().width + 5, component.getBounds().y,
				500, this.lblError1.getBounds().height);
		this.lblError1.setForeground(Color.red);
		this.lblError1.setText(message);
	}

	private void hideError() {
		this.lblError.setForeground(SystemColor.control);
		this.lblError1.setForeground(SystemColor.control);
	}
}
