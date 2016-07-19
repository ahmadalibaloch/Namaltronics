package UILayer;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import reports.JobsReports;
import reports.TechsReports;
import DALayer.Jobs;
import DALayer.Users;
import DomainLayer.DateMethods;
import DomainLayer.User;
import UI_Utils.FormType;
import UI_Utils.Prompt;
import java.awt.Toolkit;
import javax.swing.border.EtchedBorder;
import java.awt.Dimension;

/**
 * Main UI class holding manue and controls and toolbars
 * 
 * @author Administrator. Created March 16, 2012.
 */
public class MainForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frmNamaltronicsApplication;
	/**
	 * User which loggs in gets used in whole project classes for role selection
	 */
	public static User user;
	private Login login;
	//
	private static JLabel lblNotify1;
	private static JLabel lblNotify2;
	private static JLabel lblNotify3;
	private static JLabel lblNotify11;
	// temp references for logout close forms all
	ViewUsers vUsers1;
	ViewJobs vJobs1;
	ViewItems vItems1;
	ViewCustomers vCusts1;
	JobsReports vJobsReports1;
	TechsReports vTechsReports1;

	AddTechnician addTech1;
	AddSkill addSkill1;
	AddReceptionist addRece1;
	AddModel addMode1;
	AddManufacturer addManu1;
	AddJob addJob1;
	AddItem addItem1;
	AddCustomer addCusts1;

	/**
	 * Create the application main form containg manu items and controll buttons
	 * and tool bar
	 * 
	 * @param login
	 * 
	 */
	public MainForm(Login login) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				MainForm.class.getResource("/small/namaltronics16.png")));
		getContentPane().setBackground(SystemColor.control);
		this.login = login;
		MainForm.user = login.user;
		initialize();
		setVisible(true);
		setTitle("Namaltronics Application - "
				+ MainForm.user.getName().toUpperCase() + " ("
				+ MainForm.user.getRole().toUpperCase() + ")");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JToolBar toolBar = new JToolBar();
		toolBar.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		toolBar.setFloatable(false);
		toolBar.setBackground(SystemColor.control);
		toolBar.setForeground(Color.BLUE);
		toolBar.setBounds(0, 0, 1028, 41);
		getContentPane().add(toolBar);

		JButton btnAddItem = new JButton("");
		btnAddItem.setOpaque(false);
		btnAddItem
				.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnAddItem.setBackground(Color.WHITE);
		btnAddItem.setIcon(new ImageIcon(MainForm.class
				.getResource("/big/addItem.png")));
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addItemHandler();
			}
		});

		JSeparator separator_4 = new JSeparator();
		separator_4.setBorder(null);
		separator_4.setMaximumSize(new Dimension(20, 40));
		toolBar.add(separator_4);
		// btnAddItem.setIcon(new
		// ImageIcon(MainForm.class.getResource("/big/addItem.png")));
		btnAddItem.setToolTipText("Add Item");
		toolBar.add(btnAddItem);

		JButton btnAddCust = new JButton("");
		btnAddCust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addCustHandler();
			}
		});
		btnAddCust
				.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnAddCust.setIcon(new ImageIcon(MainForm.class
				.getResource("/big/addCust.png")));
		btnAddCust.setToolTipText("Add Item");
		btnAddCust.setOpaque(false);
		btnAddCust.setBackground(Color.WHITE);
		toolBar.add(btnAddCust);

		JButton btnAddJob = new JButton("");
		btnAddJob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addJobHandler();
			}
		});
		btnAddJob.setOpaque(false);
		btnAddJob.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnAddJob.setBackground(Color.WHITE);
		btnAddJob.setIcon(new ImageIcon(MainForm.class
				.getResource("/big/addJob.png")));
		btnAddJob.setToolTipText("Assign a new repair Job");
		toolBar.add(btnAddJob);

		JButton btnViewTechs = new JButton("");
		btnViewTechs.setOpaque(false);
		btnViewTechs.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
				null));
		btnViewTechs.setBackground(Color.WHITE);
		btnViewTechs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				viewTechHandler();
			}
		});

		JSeparator separator_5 = new JSeparator();
		separator_5.setMaximumSize(new Dimension(20, 40));
		toolBar.add(separator_5);
		btnViewTechs.setIcon(new ImageIcon(MainForm.class
				.getResource("/big/editCust.png")));
		btnViewTechs.setToolTipText("View Technicians");
		toolBar.add(btnViewTechs);

		JButton btnViewJobs = new JButton("");
		btnViewJobs.setOpaque(false);
		btnViewJobs
				.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnViewJobs.setBackground(Color.WHITE);
		btnViewJobs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ViewJobs();
			}
		});
		btnViewJobs.setIcon(new ImageIcon(MainForm.class
				.getResource("/big/editJob.png")));
		btnViewJobs.setToolTipText("View Jobs");
		toolBar.add(btnViewJobs);

		JButton btnJobReport = new JButton("");
		btnJobReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new JobsReports();
			}
		});
		btnJobReport.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
				null));
		btnJobReport.setIcon(new ImageIcon(MainForm.class
				.getResource("/big/viewJobsRep.png")));
		btnJobReport.setToolTipText("View Jobs");
		btnJobReport.setOpaque(false);
		btnJobReport.setBackground(Color.WHITE);
		toolBar.add(btnJobReport);

		JButton btnTechReport = new JButton("");
		btnTechReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new TechsReports();
			}
		});
		btnTechReport.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
				null));
		btnTechReport.setIcon(new ImageIcon(MainForm.class
				.getResource("/big/viewTechRep.png")));
		btnTechReport.setToolTipText("View Jobs");
		btnTechReport.setOpaque(false);
		btnTechReport.setBackground(Color.WHITE);
		toolBar.add(btnTechReport);

		JToolBar statusBar = new JToolBar();
		statusBar.setFloatable(false);
		statusBar.setBorder(new LineBorder(new Color(0, 0, 0)));
		statusBar.setBackground(SystemColor.control);
		statusBar.setBounds(0, 555, 1028, 28);
		getContentPane().add(statusBar);

		JLabel lblDate = new JLabel("Date : 27 April 2012");
		statusBar.add(lblDate);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(SystemColor.windowText);
		statusBar.add(separator);

		lblNotify1 = new JLabel("notify1");
		statusBar.add(lblNotify1);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(Color.WHITE);
		statusBar.add(separator_1);

		lblNotify2 = new JLabel("notify2");
		statusBar.add(lblNotify2);

		JSeparator separator_3 = new JSeparator();
		statusBar.add(separator_3);

		lblNotify11 = new JLabel("Jobs Finished : 5");
		statusBar.add(lblNotify11);

		JSeparator separator_2 = new JSeparator();
		statusBar.add(separator_2);

		lblNotify3 = new JLabel("notify3");
		statusBar.add(lblNotify3);

		// new Login();
		lblDate.setText("  " + DateMethods.getDate("dd MMM, yyyy") + "  ");

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(MainForm.class
				.getResource("/small/namaltronics552.gif")));
		lblNewLabel.setBounds(10, 140, 812, 272);
		getContentPane().add(lblNewLabel);

		setNotify1();
		updateJobsPending("");
		updateTechAvailable("");
		updateJobsFinished("");
	}


	/**
	 * set the notifier field in status bar numbered as 1
	 * 
	 */
	public static void setNotify1() {
		lblNotify1.setText(MainForm.user.getRole().toUpperCase() + " "
				+ MainForm.user.getName().toUpperCase() + " logged in");
	}

	/**
	 * set the notifier field in status bar numbered as 1 to text given
	 * 
	 * @param text
	 */
	public static void updateJobsPending(String text) {
		lblNotify2.setText("Jobs Pending: "
				+ Jobs.getJobs("monthly", new Date(), "pending").size());
	}

	/**
	 * set the notifier field in status bar numbered as 1 to text given
	 * 
	 * @param text
	 */
	public static void updateTechAvailable(String text) {
		lblNotify3.setText(" Technicians Available: "
				+ Users.getTechsAvailable().size());
	}

	/**
	 * set the notifier field in status bar numbered as 1 to text given
	 * 
	 * @param text
	 */
	public static void updateJobsFinished(String text) {
		lblNotify11.setText("Jobs Finished : "
				+ Jobs.getJobs("monthly", new Date(), "finished").size());
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.frmNamaltronicsApplication = new JFrame();
		setBounds(100, 100, 840, 636);
		this.frmNamaltronicsApplication
				.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(SystemColor.control);
		menuBar.setBorderPainted(false);
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		mnFile.setBackground(SystemColor.control);
		menuBar.add(mnFile);

		JMenuItem mntmLogout = new JMenuItem("Logout");
		mntmLogout
				.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		mntmLogout.setBackground(SystemColor.control);
		mntmLogout.setIcon(new ImageIcon(MainForm.class
				.getResource("/small/logout.png")));
		mntmLogout.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MainForm.this.login.show();
				if (vUsers1 != null)
					vUsers1.dispose();
				if (vJobs1 != null)
					vJobs1.dispose();
				if (vItems1 != null)
					vItems1.dispose();
				if (vCusts1 != null)
					vCusts1.dispose();

				if (addTech1 != null)
					addTech1.dispose();
				if (addSkill1 != null)
					addSkill1.dispose();
				if (addRece1 != null)
					addRece1.dispose();
				if (addMode1 != null)
					addMode1.dispose();
				if (addManu1 != null)
					addManu1.dispose();
				if (addJob1 != null)
					addJob1.dispose();
				if (addItem1 != null)
					addItem1.dispose();
				if (addCusts1 != null)
					addCusts1.dispose();
				MainForm.this.dispose();
			}
		});
		mnFile.add(mntmLogout);

		JMenuItem mntmBackupDatabase = new JMenuItem("Backup Database");
		mntmBackupDatabase.setEnabled(false);
		mntmBackupDatabase.setBorder(new EtchedBorder(EtchedBorder.LOWERED,
				null, null));
		mntmBackupDatabase.setBackground(SystemColor.control);
		mntmBackupDatabase.setIcon(new ImageIcon(MainForm.class
				.getResource("/small/database.png")));
		mnFile.add(mntmBackupDatabase);

		JSeparator separator = new JSeparator();
		mnFile.add(separator);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setBackground(SystemColor.control);
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainForm.this.login.dispose();
				MainForm.this.dispose();
			}
		});
		mnFile.add(mntmExit);

		JMenu mnJob = new JMenu("Job");
		mnJob.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		mnJob.setBackground(SystemColor.control);
		menuBar.add(mnJob);

		JMenuItem mntmAddJob = new JMenuItem("Add Job");
		mntmAddJob
				.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		mntmAddJob.setBackground(SystemColor.control);
		mntmAddJob.setIcon(new ImageIcon(MainForm.class
				.getResource("/small/addJob.png")));
		mnJob.add(mntmAddJob);

		JMenuItem mntmShuffleJob = new JMenuItem("Shuffle Job");
		mntmShuffleJob.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
				null));
		mntmShuffleJob.setBackground(SystemColor.control);
		mntmShuffleJob.setIcon(new ImageIcon(MainForm.class
				.getResource("/small/shuffle.png")));
		mnJob.add(mntmShuffleJob);

		JMenuItem mntmViewJobs = new JMenuItem("View Jobs");
		mntmViewJobs.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
				null));
		mntmViewJobs.setBackground(SystemColor.control);
		mntmViewJobs.setIcon(new ImageIcon(MainForm.class
				.getResource("/small/editJob.png")));
		mntmViewJobs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				vJobs1 = new ViewJobs();
			}
		});
		mnJob.add(mntmViewJobs);

		JMenu mnItem = new JMenu("Item");
		mnItem.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		mnItem.setBackground(SystemColor.control);
		mnItem.setIcon(new ImageIcon("user.ico"));
		menuBar.add(mnItem);

		JMenuItem mntmAddItem = new JMenuItem("Add Item");
		mntmAddItem
				.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		mntmAddItem.setBackground(SystemColor.control);
		mntmAddItem.setIcon(new ImageIcon(MainForm.class
				.getResource("/small/addItem.png")));
		mntmAddItem.setMnemonic('I');
		mntmAddItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addItemHandler();
			}
		});
		mnItem.add(mntmAddItem);
		//
		JMenuItem mntmViewItems = new JMenuItem("View Items");
		mntmViewItems.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
				null));
		mntmViewItems.setBackground(SystemColor.control);
		mntmViewItems.setIcon(new ImageIcon(MainForm.class
				.getResource("/small/editItem.png")));
		mntmViewItems.setMnemonic('V');
		mntmViewItems.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				vItems1 = new ViewItems();
			}
		});
		mnItem.add(mntmViewItems);

		JMenuItem mntmAddModel = new JMenuItem("Add Model");
		mntmAddModel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
				null));
		mntmAddModel.setBackground(SystemColor.control);
		mntmAddModel.setIcon(new ImageIcon(MainForm.class
				.getResource("/small/addModel.png")));
		mntmAddModel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (MainForm.user.getRole().equalsIgnoreCase("manager"))
					addMode1 = new AddModel();
				else
					Prompt.showMessage("Only manager can add a model detils.",
							"Add Model", "info");

			}
		});
		mntmAddModel.setMnemonic('m');
		mnItem.add(mntmAddModel);

		JMenuItem mntmAddManufaturer = new JMenuItem("Add Manufaturer");
		mntmAddManufaturer.setBorder(new EtchedBorder(EtchedBorder.LOWERED,
				null, null));
		mntmAddManufaturer.setBackground(SystemColor.control);
		mntmAddManufaturer.setIcon(new ImageIcon(MainForm.class
				.getResource("/small/addManu.png")));
		mntmAddManufaturer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (MainForm.user.getRole().equalsIgnoreCase("manager"))
					addManu1 = new AddManufacturer();
				else
					Prompt.showMessage(
							"Only manager can add manufacturer detils.",
							"Add Manufacturer", "info");

			}
		});
		mntmAddManufaturer.setMnemonic('f');
		mnItem.add(mntmAddManufaturer);

		JMenu mnCustomer = new JMenu("Customer");
		mnCustomer
				.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		mnCustomer.setBackground(SystemColor.control);
		menuBar.add(mnCustomer);

		JMenuItem mntmAddCustomer = new JMenuItem("Add Customer");
		mntmAddCustomer.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
				null));
		mntmAddCustomer.setBackground(SystemColor.control);
		mntmAddCustomer.setIcon(new ImageIcon(MainForm.class
				.getResource("/small/addCust.png")));
		mntmAddCustomer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addCustHandler();
			}
		});
		mnCustomer.add(mntmAddCustomer);

		JMenuItem mntmViewCustomers = new JMenuItem("View Customers");
		mntmViewCustomers.setBorder(new EtchedBorder(EtchedBorder.LOWERED,
				null, null));
		mntmViewCustomers.setBackground(SystemColor.control);
		mntmViewCustomers.setIcon(new ImageIcon(MainForm.class
				.getResource("/small/editCust.png")));
		mntmViewCustomers.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (MainForm.user.getRole().equalsIgnoreCase("receptionist")
						|| MainForm.user.getRole().equalsIgnoreCase("manager"))
					vCusts1 = new ViewCustomers();
				else
					Prompt.showMessage(
							"Only manager or a receptionist view customers detils.",
							"View Customers", "info");

			}
		});
		mnCustomer.add(mntmViewCustomers);

		JMenu mnTechnician = new JMenu("Technician");
		mnTechnician.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
				null));
		mnTechnician.setBackground(SystemColor.control);
		menuBar.add(mnTechnician);

		JMenuItem mntmAddTechnician = new JMenuItem("Add Technician");
		mntmAddTechnician.setBorder(new EtchedBorder(EtchedBorder.LOWERED,
				null, null));
		mntmAddTechnician.setBackground(SystemColor.control);
		mntmAddTechnician.setIcon(new ImageIcon(MainForm.class
				.getResource("/small/addCust.png")));
		mntmAddTechnician.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (MainForm.user.getRole().equalsIgnoreCase("manager")) {
					addTech1 = new AddTechnician(FormType.saving);
				} else
					Prompt.showMessage("Only manager can add a technician.",
							"Add Technicians", "info");

			}
		});
		mnTechnician.add(mntmAddTechnician);

		JMenuItem mntmViewTechnicians = new JMenuItem("View Technicians");
		mntmViewTechnicians.setIcon(new ImageIcon(MainForm.class
				.getResource("/small/editCust.png")));
		mntmViewTechnicians.setBorder(new EtchedBorder(EtchedBorder.LOWERED,
				null, null));
		mntmViewTechnicians.setBackground(SystemColor.control);
		mntmViewTechnicians.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				viewTechHandler();
			}
		});
		mnTechnician.add(mntmViewTechnicians);

		JMenuItem mntmAddSkill = new JMenuItem("Add Skill");
		mntmAddSkill.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
				null));
		mntmAddSkill.setBackground(SystemColor.control);
		mntmAddSkill.setIcon(new ImageIcon(MainForm.class
				.getResource("/small/addSkill.png")));
		mntmAddSkill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (MainForm.user.getRole().equalsIgnoreCase("manager")) {
					addSkill1 = new AddSkill();
				} else
					Prompt.showMessage(
							"Only manager can add a skill category.",
							"Add Skill", "info");

			}
		});
		mnTechnician.add(mntmAddSkill);

		JMenu mnReceptionist = new JMenu("Receptionist");
		mnReceptionist.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
				null));
		mnReceptionist.setBackground(SystemColor.control);
		menuBar.add(mnReceptionist);

		JMenuItem mntmAddReceptionist = new JMenuItem("Add Receptionist");
		mntmAddReceptionist.setBorder(new EtchedBorder(EtchedBorder.LOWERED,
				null, null));
		mntmAddReceptionist.setBackground(SystemColor.control);
		mntmAddReceptionist.setIcon(new ImageIcon(MainForm.class
				.getResource("/small/addCust.png")));
		mntmAddReceptionist.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addReceHanlder();
			}
		});
		mnReceptionist.add(mntmAddReceptionist);

		JMenuItem mntmViewReceptionists = new JMenuItem("View Receptionists");
		mntmViewReceptionists.setIcon(new ImageIcon(MainForm.class
				.getResource("/small/editCust.png")));
		mntmViewReceptionists.setBorder(new EtchedBorder(EtchedBorder.LOWERED,
				null, null));
		mntmViewReceptionists.setBackground(SystemColor.control);
		mntmViewReceptionists.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				viewRecepHandler();
			}
		});
		mnReceptionist.add(mntmViewReceptionists);

		JMenu mnReports = new JMenu("Reports");
		mnReports.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		mnReports.setBackground(SystemColor.control);
		menuBar.add(mnReports);

		JMenuItem mntmDailyReport = new JMenuItem("View Jobs Reports");
		mntmDailyReport.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
				null));
		mntmDailyReport.setBackground(SystemColor.control);
		mntmDailyReport.setIcon(new ImageIcon(MainForm.class
				.getResource("/small/viewJobsRep.png")));
		mntmDailyReport.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				vJobsReports1 = new JobsReports();
			}
		});
		mnReports.add(mntmDailyReport);

		JMenuItem mntmTechniciansReport = new JMenuItem("Technicians Report");
		mntmTechniciansReport.setBorder(new EtchedBorder(EtchedBorder.LOWERED,
				null, null));
		mntmTechniciansReport.setBackground(SystemColor.control);
		mntmTechniciansReport.setIcon(new ImageIcon(MainForm.class
				.getResource("/small/viewTechRep.png")));
		mntmTechniciansReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vTechsReports1 = new TechsReports();
			}
		});
		mnReports.add(mntmTechniciansReport);

		JMenuItem mntmCustomersReport = new JMenuItem("Customers Report");
		mntmCustomersReport.setBorder(new EtchedBorder(EtchedBorder.LOWERED,
				null, null));
		mntmCustomersReport.setBackground(SystemColor.control);
		mntmCustomersReport.setEnabled(false);
		mnReports.add(mntmCustomersReport);

		JMenuItem mntmViewEmployees = new JMenuItem("View Employees");
		mntmViewEmployees.setBorder(new EtchedBorder(EtchedBorder.LOWERED,
				null, null));
		mntmViewEmployees.setBackground(SystemColor.control);
		mntmViewEmployees.setEnabled(false);
		mnReports.add(mntmViewEmployees);

		JMenu mnHelp = new JMenu("Help");
		mnHelp.setHorizontalAlignment(SwingConstants.LEFT);
		mnHelp.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		mnHelp.setBackground(SystemColor.control);
		menuBar.add(mnHelp);

		JMenuItem mntmAboutNamaltronics = new JMenuItem("About Namaltronics");
		mntmAboutNamaltronics.setHorizontalAlignment(SwingConstants.LEFT);
		mntmAboutNamaltronics.setIcon(new ImageIcon(MainForm.class
				.getResource("/small/namaltronics16.png")));
		mntmAboutNamaltronics.setBorder(new EtchedBorder(EtchedBorder.LOWERED,
				null, null));
		mntmAboutNamaltronics.setBackground(SystemColor.control);
		mnHelp.add(mntmAboutNamaltronics);

		JMenuItem mntmAboutThisSoftware = new JMenuItem("About This Software");
		mntmAboutThisSoftware.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Prompt.showMessage("See the user manual pdf file for usage help.", "Help", "info");
			}
		});
		mntmAboutThisSoftware.setIcon(new ImageIcon(MainForm.class
				.getResource("/small/about.png")));
		mntmAboutThisSoftware.setBorder(new EtchedBorder(EtchedBorder.LOWERED,
				null, null));
		mntmAboutThisSoftware.setBackground(SystemColor.control);
		mnHelp.add(mntmAboutThisSoftware);

		// actions
		mntmAddJob.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addJobHandler();
			}
		});

	}

	protected void addJobHandler() {
		if (MainForm.user.getRole().equalsIgnoreCase("manager")
				|| MainForm.user.getRole().equalsIgnoreCase(
						"receptionist")) {
			addJob1 = new AddJob(FormType.saving);
		} else
			Prompt.showMessage(
					"Only manager or a receptionist can add bookings.",
					"Add Job", "info");
	}


	protected void viewTechHandler() {
		if (MainForm.user.getRole().equalsIgnoreCase("manager")
				|| MainForm.user.getRole().equalsIgnoreCase(
						"receptionist")) {
			vUsers1 = new ViewUsers("technician");
		} else
			Prompt.showMessage(
					"Only manager or a receptionist can view technicians details.",
					"Vew Technicians", "info");
	}

	protected void addReceHanlder() {
		if (MainForm.user.getRole().equalsIgnoreCase("manager")) {
			addRece1 = new AddReceptionist(FormType.saving);
		} else
			Prompt.showMessage("Only manager can add a receptionist.",
					"Add Receptionist", "info");

	}

	protected void viewRecepHandler() {
		if (MainForm.user.getRole().equalsIgnoreCase("manager")) {
			vUsers1 = new ViewUsers("receptionist");

		} else
			Prompt.showMessage("Only manager can view receptionists details.",
					"View Receptionists", "info");
	}

	protected void addCustHandler() {
		if (MainForm.user.getRole().equalsIgnoreCase("receptionist")
				|| MainForm.user.getRole().equalsIgnoreCase("manager"))
			addCusts1 = new AddCustomer(FormType.saving);
		else
			Prompt.showMessage(
					"Only manager or a receptionist can add customers detils.",
					"Add Customers", "info");

	}

	protected void addItemHandler() {
		if (MainForm.user.getRole().equalsIgnoreCase("manager")
				|| MainForm.user.getRole().equalsIgnoreCase("receptionist"))
			addItem1 = new AddItem(FormType.saving);
		else
			Prompt.showMessage(
					"Only manager or a receptionist can add a item detils.",
					"Add Item", "info");

	}
}
