package UILayer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

import DALayer.Manufacturers;
import DALayer.Models;
import DomainLayer.Manufacturer;
import DomainLayer.Model;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.LinkedList;

import javax.swing.JComboBox;
import java.awt.Toolkit;

/**
 * UI class for adding new Model
 * @author Ahmad Ali
 *
 */
public class AddModel extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtModelName;
	private JTextField txtModelYear;
	private JComboBox cmbManus;
	private LinkedList<Manufacturer> manus;
	private int model;
	/**
	 * Create the frame.
	 */
	public AddModel() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddModel.class.getResource("/small/addModel.png")));
		setTitle("Add Model");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setBounds(100, 100, 575, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAddModel = new JLabel("Add Model");
		lblAddModel.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddModel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblAddModel.setBounds(10, 11, 547, 50);
		contentPane.add(lblAddModel);

		JLabel lblModelName = new JLabel("Model Name");
		lblModelName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblModelName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblModelName.setBounds(88, 94, 80, 14);
		contentPane.add(lblModelName);

		txtModelName = new JTextField();
		txtModelName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtModelName.setColumns(10);
		txtModelName.setBounds(178, 91, 136, 20);
		contentPane.add(txtModelName);

		JLabel lblYear = new JLabel("Year");
		lblYear.setHorizontalAlignment(SwingConstants.RIGHT);
		lblYear.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblYear.setBounds(104, 128, 64, 14);
		contentPane.add(lblYear);

		txtModelYear = new JTextField();
		txtModelYear.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtModelYear.setColumns(10);
		txtModelYear.setBounds(178, 125, 136, 20);
		contentPane.add(txtModelYear);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnSaveEventHandler();
			}
		});
		btnSave.setBounds(135, 310, 95, 23);
		contentPane.add(btnSave);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnDeleteEventHandler();
			}
		});
		btnDelete.setBounds(240, 310, 95, 23);
		contentPane.add(btnDelete);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnCancelEventHanlder();
			}
		});
		btnCancel.setBounds(345, 310, 95, 23);
		contentPane.add(btnCancel);

		JTextArea txtModelDesc = new JTextArea();
		txtModelDesc.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null,
				null, null, null));
		txtModelDesc.setRows(4);
		txtModelDesc.setBounds(178, 196, 136, 81);
		contentPane.add(txtModelDesc);

		JLabel label = new JLabel("Description");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(104, 201, 64, 14);
		contentPane.add(label);

		JLabel lblManu = new JLabel("Manufacturer");
		lblManu.setHorizontalAlignment(SwingConstants.RIGHT);
		lblManu.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblManu.setBounds(88, 156, 80, 14);
		contentPane.add(lblManu);

		cmbManus = new JComboBox();
		cmbManus.setBounds(178, 156, 136, 22);
		contentPane.add(cmbManus);
		//
		
		loadManus();
	}

	protected void btnCancelEventHanlder() {
		this.dispose();
	}

	protected void btnDeleteEventHandler() {
		Models.deleteModel(this.model);
		
	}

	protected void btnSaveEventHandler() {
		Model model = new Model(0, txtModelName.getText(),
				Integer.parseInt(txtModelYear.getText()),this.manus.get(this.cmbManus.getSelectedIndex()).getManufacturerId());
		Models.saveModel(model);
		txtModelName.setText("");
		txtModelYear.setText("");

	}

	private void loadManus() {
		if ((this.manus = Manufacturers.getManufacturers("")).size() > 0) {
			this.cmbManus.removeAllItems();
			for (Manufacturer m : this.manus) {
				this.cmbManus.addItem(m.getName());
			}
			this.cmbManus.setSelectedIndex(0);
		}
	}
}
