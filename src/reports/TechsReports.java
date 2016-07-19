package reports;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import DALayer.Users;
import DomainLayer.DateMethods;
import UI_Utils.DateChooser;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;

/**
 * 	Form to handle the generation of technicians report
 * 
 */
public class TechsReports extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DateChooser.ObservingTextField  txtDate;
	JComboBox cmbStatus;
	private String[] filter = new String[3];
	private Date date = new Date();
	private JComboBox cmbWithJobs;
	private JComboBox cmbPeformance;


	/**
	 * Create the frame.
	 */
	public TechsReports() {
		setResizable(false);
		setVisible(true);
		setTitle("View Technicians Reports");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 387);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblViewJobsForm = new JLabel("View Technicians Report Form");
		lblViewJobsForm.setForeground(new Color(0, 0, 0));
		lblViewJobsForm.setHorizontalAlignment(SwingConstants.CENTER);
		lblViewJobsForm.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblViewJobsForm.setBounds(0, 0, 492, 104);
		contentPane.add(lblViewJobsForm);
		
		JLabel lblJobReportType = new JLabel("Technician Status");
		lblJobReportType.setHorizontalAlignment(SwingConstants.RIGHT);
		lblJobReportType.setBounds(15, 152, 105, 22);
		contentPane.add(lblJobReportType);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDate.setBounds(70, 114, 46, 20);
		contentPane.add(lblDate);
		
		JLabel lblFilters = new JLabel("Filters :");
		lblFilters.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFilters.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFilters.setBounds(74, 185, 46, 14);
		contentPane.add(lblFilters);
		
		JButton btnViewReport = new JButton("View Report");
		btnViewReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				viewReportButtonEventHandler();
			}
		});
		btnViewReport.setBounds(126, 282, 105, 23);
		contentPane.add(btnViewReport);
		
		JButton btnSavePdf = new JButton("Save PDF");
		btnSavePdf.setEnabled(false);
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
		
		cmbStatus = new JComboBox();
		cmbStatus.setModel(new DefaultComboBoxModel(new String[] {"OFF this filter", "Active", "InActive", "Pending"}));
		cmbStatus.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {

			}
		});
		cmbStatus.setBounds(146, 152, 115, 22);
		contentPane.add(cmbStatus);
		
		JLabel lblWithReports = new JLabel("With Jobs");
		lblWithReports.setHorizontalAlignment(SwingConstants.RIGHT);
		lblWithReports.setBounds(15, 204, 105, 22);
		contentPane.add(lblWithReports);
		
		cmbWithJobs = new JComboBox(new Object[]{});
		cmbWithJobs.setModel(new DefaultComboBoxModel(new String[] {"OFF this filter", "More", "0 (Available)", "1", "2", "3"}));
		cmbWithJobs.setBounds(146, 204, 115, 22);
		contentPane.add(cmbWithJobs);
		
		JLabel lblPerformance = new JLabel("Performance *");
		lblPerformance.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPerformance.setBounds(15, 236, 105, 22);
		contentPane.add(lblPerformance);
		
		cmbPeformance = new JComboBox(new Object[]{});
		cmbPeformance.setEnabled(false);
		cmbPeformance.setModel(new DefaultComboBoxModel(new String[] {"OFF this filter", "Excellent", "Good", "Normal", "Below Normal", "Bad"}));
		cmbPeformance.setBounds(146, 236, 115, 22);
		contentPane.add(cmbPeformance);
		
		JLabel lblNewLabel = new JLabel("* see user manual for performace calculation method");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel.setBounds(57, 328, 270, 14);
		contentPane.add(lblNewLabel);
	}
	//--------------------
	private void viewReportButtonEventHandler() {
			this.date = DateMethods.parseDate(txtDate.getText(), "MMM dd, yyyy");
			filter[0] = cmbStatus.getSelectedItem().toString().toLowerCase();
			filter[1] = cmbWithJobs.getSelectedItem().toString().toLowerCase();
			filter[2] = cmbPeformance.getSelectedItem().toString().toLowerCase();
			showReport(this.date,filter,false);
	}

	private void btnSavePdfEventHanlder() {
		this.date = DateMethods.parseDate(txtDate.getText(), "MMM dd, yyyy");
		filter[0] = cmbStatus.getSelectedItem().toString().toLowerCase();
		filter[1] = cmbWithJobs.getSelectedItem().toString().toLowerCase();
		filter[2] = cmbPeformance.getSelectedItem().toString().toLowerCase();
		showReport(this.date,filter,true);
	}
	/*
	 * Generate the report to screen or file PDF
	 * @param date of the report
	 * @param filter finished jobs , in progress or pending
	 */
	private void showReport(Date date,String[] filter,boolean save){
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("available", Users.getTechsAvailable().size());
		parameters.put("busy", Users.getTechsBusy().size());
		parameters.put("pending",Users.getUsers("status='pending' AND userId IN (Select techId from technicians)").size());
		//
		parameters.put("date", DateMethods.getDate("dd MMM, yyyy",date));
		parameters.put("title", "Technicians Report");
		//
		JasperReport jc;
		try {
			jc = JasperCompileManager.compileReport("src\\reports\\TechsReport.jrxml");
			TechsReportDataSource jrds = new TechsReportDataSource(this.filter);
			//jrds.
			JasperPrint jp = JasperFillManager.fillReport(jc, parameters,
					jrds);
			if(!save)
			JasperViewer.viewReport(jp);
			else{
				JasperExportManager.exportReportToPdfFile(jp,"Technicians Report.pdf");
			}
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
}
