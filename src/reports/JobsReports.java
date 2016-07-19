package reports;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import DALayer.Jobs;
import DomainLayer.DateMethods;
import UI_Utils.DateChooser;
import java.awt.Color;

/**
 * Ui Class for job report options and filter optionality before generating
 * @author Ahmad Ali, Created 20 April 2012
 *
 */
public class JobsReports extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DateChooser.ObservingTextField  txtDate;
	JComboBox comboBox;
	//
	private String reportType = "monthly";
	private String filter = "finsished";
	private Date date = new Date();
	private final ButtonGroup buttonGroup = new ButtonGroup();


	/**
	 * Create the frame.
	 */
	public JobsReports() {
		setResizable(false);
		setVisible(true);
		setTitle("View Jobs Reports");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 387);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblViewJobsForm = new JLabel("View Jobs Report Form");
		lblViewJobsForm.setHorizontalAlignment(SwingConstants.CENTER);
		lblViewJobsForm.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblViewJobsForm.setBounds(0, 0, 492, 104);
		contentPane.add(lblViewJobsForm);
		
		JLabel lblJobReportType = new JLabel("Job Report Type");
		lblJobReportType.setHorizontalAlignment(SwingConstants.RIGHT);
		lblJobReportType.setBounds(15, 152, 105, 22);
		contentPane.add(lblJobReportType);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDate.setBounds(70, 114, 46, 20);
		contentPane.add(lblDate);
		
		JLabel lblFilters = new JLabel("Filters :");
		lblFilters.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFilters.setBounds(70, 194, 46, 14);
		contentPane.add(lblFilters);
		
		JRadioButton rdbtnFinished = new JRadioButton("Finished");
		buttonGroup.add(rdbtnFinished);
		rdbtnFinished.setBounds(211, 190, 65, 23);
		contentPane.add(rdbtnFinished);
		
		JRadioButton rdbtnInProgress = new JRadioButton("In Progress");
		buttonGroup.add(rdbtnInProgress);
		rdbtnInProgress.setBounds(288, 190, 81, 23);
		contentPane.add(rdbtnInProgress);
		
		JRadioButton rdbtnPending = new JRadioButton("Pending");
		buttonGroup.add(rdbtnPending);
		rdbtnPending.setBounds(371, 190, 65, 23);
		contentPane.add(rdbtnPending);
		
		JButton btnViewReport = new JButton("View Report");
		btnViewReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				viewReportButtonEventHandler();
			}
		});
		btnViewReport.setBounds(126, 282, 105, 23);
		contentPane.add(btnViewReport);
		
		JButton btnSavePdf = new JButton("Save PDF");
		btnSavePdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnSavePdfEventHanlder();
			}
		});
		btnSavePdf.setBounds(263, 282, 105, 23);
		contentPane.add(btnSavePdf);
		
		txtDate = new DateChooser.ObservingTextField ();
		txtDate.setBackground(Color.WHITE);
		txtDate.setEditable(false);
		txtDate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				DateChooser.showDatePicker(txtDate);
			}
		});
		txtDate.setText("Apr 26, 2012");
		txtDate.setBounds(146, 114, 115, 20);
		contentPane.add(txtDate);
		txtDate.setColumns(10);
		
		Object[] items = {"Monthly","Daily","Weekly","Yearly"};
		comboBox = new JComboBox(items);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {

			}
		});
		comboBox.setBounds(146, 152, 115, 22);
		contentPane.add(comboBox);
		
		JRadioButton rdbtnNone = new JRadioButton("NONE");
		buttonGroup.add(rdbtnNone);
		rdbtnNone.setBounds(146, 190, 65, 23);
		contentPane.add(rdbtnNone);
		setSelection(buttonGroup, "NONE");
	}
	//--------------------
	private void viewReportButtonEventHandler() {
			this.date = DateMethods.parseDate(txtDate.getText(), "MMM dd, yyyy");
			filter = getSelection(buttonGroup).getText().toLowerCase().replace(" ","" );
			reportType = comboBox.getSelectedItem().toString();
			System.out.println("Date "+this.date);
			showReport(this.date,filter,false);
	}

	private void btnSavePdfEventHanlder() {
		this.date = DateMethods.parseDate(txtDate.getText(), "MMM dd, yyyy");
		filter = getSelection(buttonGroup).getText().toLowerCase().replace(" ","" );
		reportType = comboBox.getSelectedItem().toString();
		System.out.println("Date "+this.date);
		showReport(this.date,filter,true);
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
	 * sets the selected radio button to seleted if the button has text given in
	 * value
	 * 
	 * @param date of the report
	 * @param filter finished jobs , in progress or pending
	 */
	private void showReport(Date date,String filter,boolean save){
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("finishedJobs", Jobs.getJobs(this.reportType, date, "finished").size());
		parameters.put("inprogressJobs", Jobs.getJobs(this.reportType, date, "inprogress").size());
		parameters.put("pendingJobs",Jobs.getJobs(this.reportType, date, "pending").size());
		//
		parameters.put("date", DateMethods.getDate("dd MMM, yyyy",date));
		parameters.put("title", this.reportType.toUpperCase() + " REPORT");
		//
		JasperReport jc;
		try {
			jc = JasperCompileManager.compileReport("src\\reports\\jobsReport.jrxml");
			JobsReportDataSource jrds = new JobsReportDataSource(this.reportType,date,this.filter);
			//jrds.
			JasperPrint jp = JasperFillManager.fillReport(jc, parameters,
					jrds);
			if(!save)
			JasperViewer.viewReport(jp);
			else{
				JasperExportManager.exportReportToPdfFile(jp,"Monthly Jobs Report.pdf");
			}
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
}
