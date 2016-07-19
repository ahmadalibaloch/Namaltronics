package UILayer;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JButton;
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
import DomainLayer.DateMethods;
import DomainLayer.Job;
import UI_Utils.FormType;
import java.awt.Toolkit;

/**
 * View jobs form containg JTable for grid data view
 * 
 * @author Ahmad Ali. Created Mar 16, 2012.
 */
public class ViewJobs extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel mdlTable;
	private LinkedList<Job> jobs;
	private Job job;
	private int selectedJobId = -1;
	//
	/**
	 * Create the frame.
	 * 
	 */
	public ViewJobs() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ViewJobs.class.getResource("/small/editJob.png")));
		//net.sf.jasperreports.
		setTitle("View Jobs");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 709, 512);
		setVisible(true);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		this.contentPane.setLayout(null);

		this.table = new JTable();
		String[] jobHeadings = { "Job Id", "Item", "Technician", "Status",
				"Start Date","End Date", "Problem Category" };
		this.mdlTable = new DefaultTableModel(jobHeadings,0) {
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
		scrTable.setBounds(52, 105, 602, 265);
		this.contentPane.add(scrTable);

		JButton btnEdit = new JButton("Edit Record");
		btnEdit.setBounds(83, 408, 91, 23);
		this.contentPane.add(btnEdit);
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editJobEventHandler();
			}
		});

		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(202, 408, 91, 23);
		this.contentPane.add(btnDelete);

		JLabel lblTitle = new JLabel("View Job Details");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblTitle.setBounds(10, 11, 681, 50);
		this.contentPane.add(lblTitle);

		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				deleteJobEventHandler();
			}
		});
		//
		listAllReceptionists();

	}

	/**
	 * Deletes the customer selected in table.
	 * 
	 */
	public void deleteJob() {
		Jobs.deleteJob(this.jobs.get(this.selectedJobId)
				.getJobId());
		MainForm.updateJobsFinished("");
		MainForm.updateJobsPending("");
		MainForm.updateTechAvailable("");
		listAllReceptionists();
		// MainForm.status("Custoemr deleted successfully.");
	}

	private void deleteJobEventHandler() {
		if (JOptionPane.showConfirmDialog(this,
				"Do you want to really delete this job ?", "Warning!", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
			deleteJob();
			listAllReceptionists();
		}
	}

	//

	private void listAllReceptionists() {
		this.jobs = Jobs.getJobs("");
		int size = this.jobs.size() - 1;
		// if (mdlTable.getRowCount() < size+1)
		this.mdlTable.setRowCount(size + 1);
		int i = 0;
		for (Iterator<Job> iterator = this.jobs.iterator(); iterator
				.hasNext();) {
			this.job = iterator.next();
			
				this.mdlTable.setValueAt(this.job.getJobId(), i, 0);
				this.mdlTable.setValueAt(this.job.getItem().getName(), i, 1);
				this.mdlTable.setValueAt(this.job.getTech().getName(), i, 2);
				this.mdlTable.setValueAt(Job.getStatus(this.job,new Date()), i, 3);
				this.mdlTable.setValueAt(DateMethods.getDate("dd MMM, yyyy",this.job.getStartDate()), i, 4);
				this.mdlTable.setValueAt(DateMethods.getDate("dd MMM, yyyy",this.job.getEndDate()), i, 5);
				this.mdlTable.setValueAt(this.job.getItem().getProblem().getProblemCategory().getName(), i, 6);
				i++;
		}
	}
	private void editJobEventHandler() {
		if (this.selectedJobId == -1) {
			JOptionPane.showMessageDialog(this,
					"Select a job to edit",
					"Edit Job", JOptionPane.WARNING_MESSAGE);
			return;
		}
			AddJob jobEditFrm = new AddJob(FormType.editing);
			jobEditFrm.editJob(this.jobs.get(this.selectedJobId),
					this);
			jobEditFrm
					.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	private void tblMouseClinkHandler() {
		this.selectedJobId = this.table.getSelectedRow();
	}

	/**
	 * Updates the customers table from out side. (made especially for edit
	 * event completion)
	 * 
	 */
	public void updateTable() {
		listAllReceptionists();
	}
}
