package UILayer;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DALayer.Jobs;
import DALayer.Skills;
import DALayer.Users;
import DomainLayer.Skill;
import DomainLayer.User;
import UI_Utils.FormType;
import java.awt.Toolkit;

/**
 * View Users of the sofware form containing JTable for grid view. changes view on techs and receptionsts
 * 
 * @author Ahmad Ali. Created Mar 16, 2012.
 */
public class ViewUsers extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel mdlTable;
	private LinkedList<User> users;
	private User user;
	private int selectedUserId = -1;
	private String role;

	private JComboBox cmbTechStatus;
	private JComboBox cmbSkills;

	//
	//
	/**
	 * Create the frame.
	 * 
	 * @param role
	 */
	public ViewUsers(String role) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ViewUsers.class.getResource("/small/editCust.png")));
		this.role = role;
		setTitle("View " + role + "s");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 709, 512);
		setVisible(true);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		this.contentPane.setLayout(null);

		this.table = new JTable();
		String[] techHeadings = { "Full Name", "User Name", "Email", "Status",
				"Skill", "Skill Level", "Jobs Assigned" };
		String[] receHeadings = { "Full Name", "User Name", "Phone", "Email",
				"Last Login", "Added", "Status" };
		this.mdlTable = new DefaultTableModel(
				this.role.equalsIgnoreCase("technician") ? techHeadings
						: receHeadings, 0) {
			/**
							 * 
							 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		this.table.setModel(this.mdlTable);
		// "Name", "Phone", "Email", "Post Code", "Card Type", "Address"
		this.table.setBounds(52, 23, 556, 347);
		this.table.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				tblMouseClinkHandler();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

			}

			@Override
			public void mousePressed(MouseEvent arg0) {

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {

			}

		});
		JScrollPane scrTable = new JScrollPane(this.table);
		scrTable.setBounds(52, 150, 602, 283);
		this.contentPane.add(scrTable);

		JButton btnEdit = new JButton("Edit Record");
		btnEdit.setBounds(82, 444, 91, 23);
		this.contentPane.add(btnEdit);
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editUserEventHandler();
			}
		});

		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(201, 444, 91, 23);
		this.contentPane.add(btnDelete);

		JLabel lblTitle = new JLabel("View " + this.role + " Details");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblTitle.setBounds(10, 11, 681, 50);
		this.contentPane.add(lblTitle);

		JLabel lblFilters = new JLabel("Filters: ");
		lblFilters.setBounds(52, 72, 46, 14);
		contentPane.add(lblFilters);

		cmbTechStatus = new JComboBox();
		cmbTechStatus.setModel(new DefaultComboBoxModel(new String[] {
				"OFF this filter", "Active", "InActive", "Pending" }));
		cmbTechStatus.setBounds(123, 96, 106, 22);
		contentPane.add(cmbTechStatus);

		JLabel lblStatus = new JLabel("Status :");
		lblStatus.setBounds(52, 100, 46, 14);
		contentPane.add(lblStatus);

		JLabel lblSkills = new JLabel("Skill Level :");
		lblSkills.setBounds(252, 100, 61, 14);
		if (this.role.equalsIgnoreCase("technician"))
			contentPane.add(lblSkills);

		cmbSkills = new JComboBox();
		cmbSkills.setModel(new DefaultComboBoxModel(new String[] {
				"OFF this filter", "Best", "Good", "Efficient", "Normal" }));
		cmbSkills.setBounds(323, 96, 106, 22);
		if (this.role.equalsIgnoreCase("technician"))
			contentPane.add(cmbSkills);

		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listAllUsers();
			}
		});
		btnRefresh.setBounds(480, 96, 91, 23);
		contentPane.add(btnRefresh);

		JButton btnPrintAvailable = new JButton("Print Available");
		btnPrintAvailable.setEnabled(false);
		btnPrintAvailable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnPrintAvailable();
			}
		});
		btnPrintAvailable.setToolTipText("Technicians with no jobs assigned");
		btnPrintAvailable.setBounds(326, 444, 130, 23);
		if (this.role.equalsIgnoreCase("technician"))
			contentPane.add(btnPrintAvailable);

		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				deleteUserEventHandler();
			}
		});
		//
		listAllUsers();

	}

	protected void btnPrintAvailable() {
		//
	}

	/**
	 * Deletes the user selected in table.
	 * 
	 */
	public void deleteUser() {
		int id = this.users.get(this.selectedUserId).getUserId();
		if (this.role.equalsIgnoreCase("technician")) {
			Users.deleteTechnician(
					id,
					Skills.getSkills(
							"tech_skilId=(Select tech_skilId from technicians where techId="
									+ this.selectedUserId + ")").getFirst()
							.getTech_skilId());
			MainForm.updateTechAvailable("");
		} else if (this.role.equalsIgnoreCase("receptionist"))
			Users.deleteUser(id);
		listAllUsers();
		// MainForm.status("Custoemr deleted successfully.");
	}

	private void deleteUserEventHandler() {
		if (JOptionPane.showConfirmDialog(this,
				"Do you want to really delete this " + this.role.toLowerCase()
						+ " ?", "Warning!", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
			deleteUser();
			listAllUsers();
		}
	}

	//

	/**
	 * List all the users in the table (JTable) view JMOdel
	 * 
	 */
	private void listAllUsers() {
		String query = "";
		if (!cmbSkills.getSelectedItem().toString()
				.equalsIgnoreCase("OFF this filter"))
			query = "userId = (SELECT techId FROM technicians WHERE"
					+ " tech_skilId=(SELECT tech_skilId FROM techs_skills WHERE"
					+ " skillLevel='"
					+ cmbSkills.getSelectedItem().toString().toLowerCase()
					+ "')) AND ";
		if (!cmbTechStatus.getSelectedItem().toString()
				.equalsIgnoreCase("OFF this filter"))
			query += "status ='"
					+ cmbTechStatus.getSelectedItem().toString().toLowerCase()
					+ "' AND ";
		query += "role='" + this.role.toLowerCase() + "'";
		this.users = Users.getUsers(query);
		int size = this.users.size() - 1;
		// if (mdlTable.getRowCount() < size+1)
		Skill skill;
		this.mdlTable.setRowCount(size + 1);
		int i = 0;
		for (Iterator<User> iterator = this.users.iterator(); iterator
				.hasNext();) {
			this.user = iterator.next();
			if (this.role.equalsIgnoreCase("technician")) {
				skill = Skills
						.getSkills(
								"tech_skilId="
										+ "(SELECT tech_skilId FROM technicians WHERE techId="
										+ this.user.getUserId() + ")")
						.getFirst();
				this.mdlTable.setValueAt(this.user.getName(), i, 0);
				this.mdlTable.setValueAt(this.user.getUserName(), i, 1);
				this.mdlTable.setValueAt(this.user.getEmail(), i, 2);
				this.mdlTable.setValueAt(this.user.getUserStatus(), i, 3);
				this.mdlTable.setValueAt(skill.getSkillCategory().name, i, 4);
				this.mdlTable.setValueAt(skill.getSkillLevel(), i, 5);
				this.mdlTable.setValueAt(
						Jobs.getJobs("techId=" + this.user.getUserId()).size(),
						i, 6);
			} else if (this.role.equalsIgnoreCase("receptionist")) {
				this.mdlTable.setValueAt(this.user.getName(), i, 0);
				this.mdlTable.setValueAt(this.user.getUserName(), i, 1);
				this.mdlTable.setValueAt(this.user.getPhone(), i, 2);
				this.mdlTable.setValueAt(this.user.getEmail(), i, 3);
				this.mdlTable.setValueAt(this.user.getLastLogin(), i, 4);
				this.mdlTable.setValueAt(this.user.getAdded(), i, 5);
				this.mdlTable.setValueAt(this.user.getUserStatus(), i, 6);
			}
			i++;
		}
	}

	private void editUserEventHandler() {
		if (this.selectedUserId == -1) {
			JOptionPane.showMessageDialog(this,
					"Select a " + this.role.toLowerCase() + " to edit",
					"Edit Receptionist", JOptionPane.WARNING_MESSAGE);
			return;
		}
		if (this.role.equalsIgnoreCase("receptionist")) {
			AddReceptionist receEditFrm = new AddReceptionist(FormType.editing);
			receEditFrm.editReceptionist(this.users.get(this.selectedUserId),
					this);
			receEditFrm
					.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		} else if (this.role.equalsIgnoreCase("technician")) {
			AddTechnician techEditFrm = new AddTechnician(FormType.editing);
			techEditFrm.editTechnician(this.users.get(this.selectedUserId),
					this);
			techEditFrm
					.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		}
	}

	private void tblMouseClinkHandler() {
		this.selectedUserId = this.table.getSelectedRow();
	}

	/**
	 * Updates the customers table from out side. (made especially for edit
	 * event completion)
	 * 
	 */
	public void updateTable() {
		listAllUsers();
	}

}
