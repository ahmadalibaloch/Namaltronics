package UILayer;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.MaskFormatter;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Bindings;

import Beans_Utils.DateConvertor;
import Beans_Utils.ItemConvertor;
import Beans_Utils.TechConvertor;
import DALayer.Items;
import DALayer.Jobs;
import DALayer.Users;
import DomainLayer.DateMethods;
import DomainLayer.Item;
import DomainLayer.Job;
import DomainLayer.User;
import UI_Utils.DateChooser;
import UI_Utils.DateChooser.ObservingTextField;
import UI_Utils.FormType;
import UI_Utils.Prompt;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;

/**
 * TODO Main form to add and edit jobs assigned to technicians.
 * 
 * @author Ahmad Ali. Created March 15, 2012.
 */
public class AddJob extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtJobId;
	private DateChooser.ObservingTextField txtStartDate;
	private DateChooser.ObservingTextField txtExpEndDate;
	private DateChooser.ObservingTextField txtActualEndDate;

	/**
	 * Create the frame.
	 */
	private Job job = new Job();
	private ViewJobs vJobs = null;
	private FormType frmType = FormType.saving;
	private JComboBox cmbItems;
	private JComboBox cmbTechs;

	// ViewJobs vJobs;

	/**
	 * TODO Put here a description of what this constructor does.
	 * 
	 * @param frmType
	 * 
	 */
	
	public AddJob(FormType frmType) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddJob.class.getResource("/small/addJob.png")));
		initDataBindings();
		setBounds(100, 100, 545, 445);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JLabel lblJobId = new JLabel("Job ID");
		lblJobId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblJobId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblJobId.setBounds(79, 116, 46, 14);
		getContentPane().add(lblJobId);

		this.txtJobId = new JTextField();
		this.txtJobId
				.setToolTipText("job id is assigned on selection of start date");
		this.txtJobId.setEnabled(false);
		this.txtJobId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.txtJobId.setBounds(139, 113, 155, 20);
		getContentPane().add(this.txtJobId);
		this.txtJobId.setColumns(10);

		JLabel lblTitle = new JLabel("Assign a Job");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblTitle.setBounds(10, 11, 517, 50);
		getContentPane().add(lblTitle);

		JLabel lblItem = new JLabel("Item");
		lblItem.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblItem.setHorizontalAlignment(SwingConstants.RIGHT);
		lblItem.setBounds(79, 147, 46, 14);
		getContentPane().add(lblItem);

		this.txtStartDate = new DateChooser.ObservingTextField();
		this.txtStartDate.setBackground(Color.WHITE);
		this.txtStartDate.setEditable(false);
		this.txtStartDate.setDisabledTextColor(SystemColor.activeCaptionText);
		this.txtStartDate
				.setToolTipText("click on the field to select date from date picker");
		this.txtStartDate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				DateChooser.showDatePicker(AddJob.this.txtStartDate);
			}
		});
		this.txtStartDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.txtStartDate.setColumns(10);
		this.txtStartDate.setBounds(139, 206, 155, 20);
		getContentPane().add(this.txtStartDate);
		this.txtStartDate.getDocument().addDocumentListener(
				new DocumentListener() {
					@Override
					public void insertUpdate(DocumentEvent arg0) {
						//
						String jobid;
						if (AddJob.this.frmType.equals(FormType.saving))
							jobid = AddJob.this.job
									.createJobId(AddJob.this.txtStartDate
											.getText());
						else
							jobid = AddJob.this.job.getJobId();
						AddJob.this.txtJobId.setText(jobid);
						AddJob.this.job.setJobId(jobid);
						hideError();

					}

					@Override
					public void changedUpdate(DocumentEvent arg0) {
						// TODO Auto-generated method stub.

					}

					@Override
					public void removeUpdate(DocumentEvent arg0) {
						// TODO Auto-generated method stub.

					}

				});

		JLabel lblStartDate = new JLabel("Start Date");
		lblStartDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblStartDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStartDate.setBounds(63, 209, 62, 14);
		getContentPane().add(lblStartDate);

		this.txtExpEndDate = new DateChooser.ObservingTextField();
		this.txtExpEndDate.setBackground(Color.WHITE);
		this.txtExpEndDate
				.setToolTipText("click on the field to select date from date picker");
		this.txtExpEndDate.setEditable(false);
		this.txtExpEndDate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				DateChooser.showDatePicker(AddJob.this.txtExpEndDate);
			}
		});
		this.txtExpEndDate.getDocument().addDocumentListener(
				new DocumentListener() {
					@Override
					public void insertUpdate(DocumentEvent arg0) {
						hideError();
					}

					@Override
					public void changedUpdate(DocumentEvent arg0) {
						// ;
					}

					@Override
					public void removeUpdate(DocumentEvent arg0) {
						// ;
					}
				});
		this.txtExpEndDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.txtExpEndDate.setColumns(10);
		this.txtExpEndDate.setBounds(139, 237, 155, 20);
		getContentPane().add(this.txtExpEndDate);

		JLabel lblExpectedEndDate = new JLabel("Expected End Date");
		lblExpectedEndDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblExpectedEndDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblExpectedEndDate.setBounds(18, 240, 107, 14);
		getContentPane().add(lblExpectedEndDate);

		this.txtActualEndDate = new DateChooser.ObservingTextField();
		this.txtActualEndDate.setBackground(Color.WHITE);
		this.txtActualEndDate
				.setToolTipText("click on the field to select date from date picker");
		this.txtActualEndDate.setEditable(false);
		this.txtActualEndDate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				DateChooser.showDatePicker(AddJob.this.txtActualEndDate);
			}
		});
		this.txtActualEndDate.getDocument().addDocumentListener(
				new DocumentListener() {
					@Override
					public void insertUpdate(DocumentEvent arg0) {
						hideError();
					}

					@Override
					public void changedUpdate(DocumentEvent arg0) {
						// ;
					}

					@Override
					public void removeUpdate(DocumentEvent arg0) {
						// ;
					}
				});
		this.txtActualEndDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.txtActualEndDate.setColumns(10);
		this.txtActualEndDate.setBounds(139, 268, 155, 20);
		getContentPane().add(this.txtActualEndDate);

		this.lblActualEndDate = new JLabel("Actual End Date");
		this.lblActualEndDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.lblActualEndDate.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblActualEndDate.setBounds(33, 271, 92, 14);
		getContentPane().add(this.lblActualEndDate);

		JButton btnAssign = new JButton("Assign");
		btnAssign.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnAssignEventHandler();
			}
		});
		btnAssign.setBounds(79, 347, 91, 23);
		getContentPane().add(btnAssign);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddJob.this.dispose();
			}
		});
		btnCancel.setBounds(203, 347, 91, 23);
		getContentPane().add(btnCancel);

		JLabel lblTechnician = new JLabel("Technician");
		lblTechnician.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTechnician.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTechnician.setBounds(63, 178, 62, 14);
		getContentPane().add(lblTechnician);

		this.cmbItems = new JComboBox();
		this.cmbItems.setToolTipText("click to select from drop down");
		this.cmbItems.setBounds(139, 144, 155, 22);
		getContentPane().add(this.cmbItems);

		this.cmbTechs = new JComboBox();
		this.cmbTechs.setToolTipText("click to select from drop down");
		this.cmbTechs.setBounds(139, 175, 155, 22);
		getContentPane().add(this.cmbTechs);

		try {
			this.txtCallTime = new JFormattedTextField(new MaskFormatter(
					"##:##:##"));
			this.txtCallTime.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					hideError();
				}
			});
			this.txtCallTime.setHorizontalAlignment(SwingConstants.CENTER);
			this.txtCallTime.setText("00:00:00");
		} catch (ParseException exception) {
			// TODO Auto-generated catch-block stub.
			exception.printStackTrace();
		}
		this.txtCallTime.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.txtCallTime.setColumns(10);
		this.txtCallTime.setBounds(139, 299, 155, 20);
		getContentPane().add(this.txtCallTime);

		this.label = new JLabel("Call Time");
		this.label.setToolTipText("Model or Model #");
		this.label.setHorizontalAlignment(SwingConstants.RIGHT);
		this.label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.label.setBounds(51, 299, 74, 14);
		getContentPane().add(this.label);

		this.lblError = new JLabel("New label");
		this.lblError.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblError.setForeground(SystemColor.control);
		this.lblError.setBounds(304, 114, 223, 20);
		getContentPane().add(this.lblError);
		this.setVisible(true);
		//
		// set the form mood
		this.frmType = frmType;
		if (this.frmType == FormType.editing) {
			lblTitle.setText("Edit Job");
			// initDataBindings();
		}
		// load default values
		initDataBindings();
		loadItems();
		loadTechs();
	}

	/**
	 * TODO Put here a description of what this method does.
	 * 
	 * @param job
	 * @param viewJobs
	 */
	public void editJob(Job job, ViewJobs viewJobs) {
		this.vJobs = viewJobs;
		this.job = job;
		this.txtCallTime.setText(this.job.getCallTime());
		initDataBindings();
	}

	// items
	/**
	 * TODO Put here a description of this field.
	 */
	LinkedList<Item> items;
	private JLabel lblActualEndDate;

	/**
	 * TODO load items from database into the combobox items
	 * 
	 */
	public void loadItems() {

		if ((this.items = Items.getItems("")).size() > 0) {
			this.cmbItems.removeAllItems();
			for (Item c : this.items) {
				this.cmbItems.addItem(c.getName());
			}
			this.cmbItems.setSelectedIndex(0);
		}
	}

	// techs
	private LinkedList<User> techs;
	private JTextField txtCallTime;
	private JLabel label;
	private JLabel lblError;

	/**
	 * TODO Put here a description of what this method does.
	 * 
	 */
	public void loadTechs() {
		if ((this.techs = Users.getUsers("role='technician'")).size() > 0) {
			this.cmbTechs.removeAllItems();
			for (User c : this.techs) {
				this.cmbTechs.addItem(c.getName());
			}
			this.cmbTechs.setSelectedIndex(0);
		}
	}

	private void btnAssignEventHandler() {
		if (validateDateFields())
			if (this.frmType == FormType.saving) {
				Jobs.saveJob(this.job);
				MainForm.updateJobsPending("");
				MainForm.updateTechAvailable("");
				JOptionPane.showMessageDialog(this,
						"Job assignation was successfull.", "Job Assign",
						JOptionPane.INFORMATION_MESSAGE, null);
				clearFields();
			} else {
				Jobs.editJob(this.job);
				this.vJobs.updateTable();
				this.dispose();
			}
	}

	private void clearFields() {
		this.txtJobId.setText("");
		this.txtStartDate.setText("");
		this.txtExpEndDate.setText("");
		this.txtActualEndDate.setText("");
		this.txtCallTime.setText("00:00:00");

	}

	private boolean validateDateFields() {
		String pattern = "MMM dd, yyyy";
		if (DateMethods.validate(this.txtStartDate.getText(), pattern))
			if (DateMethods.validate(this.txtExpEndDate.getText(), pattern))
				if (DateMethods.validate(this.txtActualEndDate.getText(),
						pattern))
					return validateTech() && validateDates() && validateCmbBoxes()
							&& validateTimeField() && true;
				else
					showError("Selecte End Date here", this.txtActualEndDate);
			else
				showError("Selecte Expected End Date here", this.txtExpEndDate);
		else
			showError("Selecte Expected End Date here", this.txtStartDate);
		return false;
	}

	private boolean validateCmbBoxes() {
		if (this.cmbItems.getSelectedItem() != null)
			if (this.cmbTechs.getSelectedItem() != null)
				return true;
			else
				showError("Select a technician here", this.cmbTechs);
		else
			showError("Select an item here", this.cmbItems);
		return false;
	}
	private boolean validateTech(){
		if(this.job.tech.getUserStatus().toString().equalsIgnoreCase("active"))
			return true;
		Prompt.showMessage("The technician is "+this.job.getTech().getUserStatus()+",\n technician status should be active to assign a job", "Add Job", "error");
		return false;
	}
	private boolean validateTimeField() {
		String time = this.txtCallTime.getText();
		int hours = Integer.parseInt(time.substring(0, 2));
		int minutes = Integer.parseInt(time.substring(3, 5));
		int seconds = Integer.parseInt(time.substring(6, 8));
		// return hour < 24 && minute < 60 && second < 60;
		if (hours < 24)
			if (minutes < 60)
				if (seconds < 60) {
					this.job.setCallTime(time);
					return true;
				} else
					showError("Seconds value invalid", this.txtCallTime);
			else
				showError("Minutes value invalid", this.txtCallTime);
		else
			showError("Hours value invalid", this.txtCallTime);
		return false;
	}

	private boolean validateDates() {
		if (!this.job.getStartDate().after(this.job.getEndDate()))
			if (!this.job.getStartDate().after(this.job.getExpEndDate()))
				if (!this.job.getExpEndDate().after(this.job.getEndDate()))
					return true;
				else
					showError(
							"'Expected End Date' cannot be after 'End Date!'",
							this.txtActualEndDate);
			else
				showError("'Start Date' cannot be after 'Expected End Date'!",
						this.txtStartDate);
		else
			showError("'Start Date' cannot be after 'End Date'!",
					this.txtStartDate);
		return false;
	}

	// ///
	private void showError(String message, JComponent component) {
		this.lblError.setBounds(component.getBounds().x
				+ component.getBounds().width + 5, component.getBounds().y,
				500, this.lblError.getBounds().height);
		this.lblError.setForeground(Color.red);
		this.lblError.setText(message);
	}

	private void hideError() {
		this.lblError.setForeground(SystemColor.control);
	}

	/**
	 * TODO Put here a description of what this method does.
	 * 
	 */
	protected void initDataBindings() {
		BeanProperty<Job, String> jobBeanProperty = BeanProperty
				.create("jobId");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty
				.create("text");
		AutoBinding<Job, String, JTextField, String> autoBinding = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, this.job,
						jobBeanProperty, this.txtJobId, jTextFieldBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<Job, Date> jobBeanProperty_1 = BeanProperty
				.create("startDate");
		BeanProperty<ObservingTextField, String> observingTextFieldBeanProperty = BeanProperty
				.create("text");
		AutoBinding<Job, Date, ObservingTextField, String> autoBinding_1 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, this.job,
						jobBeanProperty_1, this.txtStartDate,
						observingTextFieldBeanProperty);
		autoBinding_1.setConverter(new DateConvertor());
		autoBinding_1.bind();
		//
		BeanProperty<Job, Date> jobBeanProperty_2 = BeanProperty
				.create("expEndDate");
		BeanProperty<ObservingTextField, String> observingTextFieldBeanProperty_1 = BeanProperty
				.create("text");
		AutoBinding<Job, Date, ObservingTextField, String> autoBinding_2 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, this.job,
						jobBeanProperty_2, this.txtExpEndDate,
						observingTextFieldBeanProperty_1);
		autoBinding_2.setConverter(new DateConvertor());
		autoBinding_2.bind();
		//
		BeanProperty<Job, Date> jobBeanProperty_3 = BeanProperty
				.create("endDate");
		BeanProperty<ObservingTextField, String> observingTextFieldBeanProperty_2 = BeanProperty
				.create("text");
		AutoBinding<Job, Date, ObservingTextField, String> autoBinding_3 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, this.job,
						jobBeanProperty_3, this.txtActualEndDate,
						observingTextFieldBeanProperty_2);
		autoBinding_3.setConverter(new DateConvertor());
		autoBinding_3.bind();
		//
		BeanProperty<Job, Item> jobBeanProperty_4 = BeanProperty.create("item");
		BeanProperty<JComboBox, Object> jComboBoxBeanProperty = BeanProperty
				.create("selectedItem");
		AutoBinding<Job, Item, JComboBox, Object> autoBinding_4 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, this.job,
						jobBeanProperty_4, this.cmbItems, jComboBoxBeanProperty);
		autoBinding_4.setConverter(new ItemConvertor());
		autoBinding_4.bind();
		//
		BeanProperty<Job, User> jobBeanProperty_5 = BeanProperty.create("tech");
		AutoBinding<Job, User, JComboBox, Object> autoBinding_5 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, this.job,
						jobBeanProperty_5, this.cmbTechs, jComboBoxBeanProperty);
		autoBinding_5.setConverter(new TechConvertor());
		autoBinding_5.bind();
		//
	}
}
