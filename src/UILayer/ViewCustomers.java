package UILayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import UI_Utils.FormType;

import DALayer.Customers;
import DALayer.Items;
import DomainLayer.Customer;
import DomainLayer.Item;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.Toolkit;

/**
 * Show customers records in Jtable
 * 
 * @author Ahmad Ali. Created Mar 16, 2012.
 */
public class ViewCustomers extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	//
	private DefaultTableModel mdlTable;
	private JList lstCustItems;
	private DefaultListModel lstMdl = new DefaultListModel();
	private LinkedList<Customer> custs;
	private Customer cust;
	private int selectedCustId = -1;

	//
	/**
	 * Create the frame.
	 */
	public ViewCustomers() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ViewCustomers.class.getResource("/small/editCust.png")));
		setTitle("View Customers Details");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 709, 512);
		setVisible(true);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		this.contentPane.setLayout(null);

		this.table = new JTable();
		this.mdlTable = new DefaultTableModel(new String[] { "Name", "Phone",
				"Email", "Post Code", "Card Type", "Address", "Items Count" },
				0) {
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
		scrTable.setBounds(51, 88, 620, 258);
		this.contentPane.add(scrTable);

		JButton btnEdit = new JButton("Edit Record");
		btnEdit.setBounds(51, 398, 91, 23);
		this.contentPane.add(btnEdit);
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editCustomerEventHandler();
			}
		});

		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(165, 398, 91, 23);
		this.contentPane.add(btnDelete);

		JLabel lblViewCustomersDetails = new JLabel("View Customers Details");
		lblViewCustomersDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblViewCustomersDetails.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblViewCustomersDetails.setBounds(10, 11, 681, 50);
		contentPane.add(lblViewCustomersDetails);

		lstCustItems = new JList();
		lstCustItems.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null,
				null, null, null));
		lstCustItems.setBounds(334, 385, 337, 82);
		contentPane.add(lstCustItems);
		lstCustItems.setModel(lstMdl);

		JLabel lblItems = new JLabel("Items :");
		lblItems.setBounds(334, 357, 46, 14);
		contentPane.add(lblItems);

		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				deleteCustomerEventHandler();
			}
		});
		//
		listAllCustomers();

	}

	/**
	 * Deletes the customer selected in table.
	 * 
	 */
	public void deleteCustomer() {
		Customers.deleteCustomer(this.custs.get(this.selectedCustId).custId);
		listAllCustomers();
		// MainForm.status("Custoemr deleted successfully.");
	}

	private void deleteCustomerEventHandler() {
		if (JOptionPane.showConfirmDialog(this,
				"Do you want to really delete this customer?", "Warning!",
				JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
			deleteCustomer();
			listAllCustomers();
		}
	}

	//
	/**
	 * view customers in table.
	 * 
	 */
	private void listAllCustomers() {
		this.custs = Customers.getCustomers("");
		int size = this.custs.size() - 1;
		// if (mdlTable.getRowCount() < size+1)
		this.mdlTable.setRowCount(size + 1);
		int i = 0;
		for (Iterator<Customer> iterator = this.custs.iterator(); iterator
				.hasNext();) {
			this.cust = iterator.next();
			this.mdlTable.setValueAt(this.cust.name, i, 0);
			this.mdlTable.setValueAt(this.cust.phone, i, 1);
			this.mdlTable.setValueAt(this.cust.email, i, 2);
			this.mdlTable.setValueAt(this.cust.postcode, i, 3);
			this.mdlTable.setValueAt(this.cust.payment, i, 4);
			this.mdlTable.setValueAt(this.cust.address, i, 5);
			this.mdlTable.setValueAt(
					Items.getItems("custId=" + this.cust.custId).size(), i, 6);
			i++;
		}
	}

	private void editCustomerEventHandler() {
		if (this.selectedCustId == -1) {
			JOptionPane.showMessageDialog(this, "Select a customer to edit",
					"Edit Customer", JOptionPane.WARNING_MESSAGE);
			return;
		}
		AddCustomer custEditFrm = new AddCustomer(FormType.editing);
		custEditFrm.editCustomer(this.custs.get(this.selectedCustId), this);
		custEditFrm.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	private void tblMouseClinkHandler() {
		this.selectedCustId = this.table.getSelectedRow();
		LinkedList<Item> items = Items.getItems("custId="
				+ custs.get(this.selectedCustId).custId);
		this.lstMdl.removeAllElements();
		for (Iterator<Item> itemIt = items.iterator(); itemIt.hasNext();) {
			Item item1 = itemIt.next();
			this.lstMdl.addElement(item1.getName() + "       ("
					+ item1.getProblem().getProblemCategory().getName()
					+ ")   (" + item1.getItemTechnician() + ")");

		}
	}

	/**
	 * Updates the customers table from out side. (made especially for edit
	 * event completion)
	 * 
	 */
	public void updateTable() {
		listAllCustomers();
	}
}
