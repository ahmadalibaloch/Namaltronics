package UILayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.LinkedList;

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
import UI_Utils.Prompt;

import DALayer.Customers;
import DALayer.ItemCategories;
import DALayer.Items;
import DALayer.Jobs;
import DomainLayer.Customer;
import DomainLayer.Item;
import DomainLayer.ItemCategory;
import DomainLayer.Job;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.Toolkit;

/**
 * View detail of all the items with filters available in a JTable.
 * 
 * @author Ahmad Ali. Created April 15, 2012.
 */
public class ViewItems extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	// cmbs
	private JComboBox cmbItemCates;
	private JComboBox cmbItemCusts;
	//
	/**
	 * table element for showing the items details in culumns
	 */
	DefaultTableModel mdlTable;

	private LinkedList<Item> items;
	private LinkedList<Customer> custs = null;
	// private LinkedList<Model> models = null;
	private LinkedList<ItemCategory> itemCates = null;
	// private LinkedList<Manufacturer> manus = null;
	//private LinkedList<ProblemCategory> probCates = null;
	//private LinkedList<Technician> techs = null;
	//
	/**
	 * current item to be deployed in table at some time and also used for
	 * editing.
	 */
	Item item;
	/**
	 * this item selected in table.
	 */
	int selectedItemId = -1;

	//
	//
	/**
	 * Create the frame.
	 */
	public ViewItems() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ViewItems.class.getResource("/small/editItem.png")));
		setTitle("View Items");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 783, 601);
		setVisible(true);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		this.contentPane.setLayout(null);

		this.table = new JTable();
		this.mdlTable = new DefaultTableModel(new String[] { "Item Name",
				"Customer", "Item Problem", "Item Category", "Manufacturer",
				"Model", "Assigned to Technician" }, 0) {
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
		// "Name", "Phone", "Email", "Skill Category", "Skill Level",
		// "Jobs Assigned"
		this.table.setBounds(52, 23, 556, 347);
		this.table.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				tblMouseClinkHandler();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				//

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				//

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				//

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				//

			}

		});
		JScrollPane scrTable = new JScrollPane(this.table);
		scrTable.setBounds(24, 164, 720, 331);
		this.contentPane.add(scrTable);

		JButton btnEdit = new JButton("Edit Record");
		btnEdit.setBounds(68, 533, 91, 23);
		this.contentPane.add(btnEdit);
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editItemEventHandler();
			}
		});

		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(187, 533, 91, 23);
		this.contentPane.add(btnDelete);

		JLabel lblTitle = new JLabel("View Items Details");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblTitle.setBounds(10, 11, 681, 50);
		this.contentPane.add(lblTitle);

		JLabel lblFilters = new JLabel("Filters:");
		lblFilters.setBounds(24, 72, 46, 14);
		this.contentPane.add(lblFilters);

		JLabel lblItemCategory = new JLabel("Item Category");
		lblItemCategory.setHorizontalAlignment(SwingConstants.RIGHT);
		lblItemCategory.setBounds(187, 113, 76, 14);
		this.contentPane.add(lblItemCategory);

		this.cmbItemCates = new JComboBox();
		this.cmbItemCates.setBounds(273, 109, 97, 22);
		this.contentPane.add(this.cmbItemCates);

		JLabel lblCustomer = new JLabel("Customer");
		lblCustomer.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCustomer.setBounds(24, 113, 46, 14);
		this.contentPane.add(lblCustomer);

		this.cmbItemCusts = new JComboBox();
		this.cmbItemCusts.setBounds(80, 109, 97, 22);
		this.contentPane.add(this.cmbItemCusts);

		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				listAllItems();
			}
		});
		btnRefresh.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null,
				null, null, null));
		btnRefresh.setMnemonic('R');
		btnRefresh.setBounds(424, 109, 91, 23);
		this.contentPane.add(btnRefresh);

		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				deleteItemEventHandler();
			}
		});

		loadCustomers();
		loadItemCates();
		// loadManus();
		// loadModels();
		// loadProbCates();

		//loadItemTechs();

		listAllItems();

	}

	/**
	 * Deletes the customer selected in table.
	 * 
	 */
	public void deleteItem() {
		Items.deleteItem(this.items.get(this.selectedItemId).getItemId());
		listAllItems();
		// MainForm.status("Custoemr deleted successfully.");
	}

	private void deleteItemEventHandler() {
		if (JOptionPane
				.showConfirmDialog(
						this,
						"Do you want to really delete this Item?\nAll records associated with this Item will also be deleted.",
						"Warning!", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
			deleteItem();
			listAllItems();
		}
	}

	//
	/**
	 * List items in Jtable
	 * 
	 */
	private void listAllItems() {
		// this.mdlTable.
		this.items = getItems();
		int size = this.items.size() - 1;
		// if (mdlTable.getRowCount() < size+1)
		this.mdlTable.setRowCount(size + 1);
		int i = 0;
		for (Iterator<Item> iterator = this.items.iterator(); iterator
				.hasNext();) {
			this.item = iterator.next();
			this.mdlTable.setValueAt(this.item.getName(), i, 0);
			this.mdlTable.setValueAt(this.item.getCustomer().getName(), i, 1);
			this.mdlTable.setValueAt(this.item.getProblem()
					.getProblemCategory().getName(), i, 2);
			this.mdlTable.setValueAt(this.item.getItemCategory().name, i, 3);
			this.mdlTable.setValueAt(this.item.getManufacturer().getName(), i,
					4);
			this.mdlTable.setValueAt(this.item.getModel().getName(), i, 5);
			LinkedList<Job> jobs = Jobs.getJobs("itemId="
					+ this.item.getItemId());
			this.mdlTable.setValueAt(
					jobs.size() > 0 ? jobs.getFirst().getTech().getName() : "[NULL]",
					i, 6);
			i++;
		}
	}

	/**
	 * Get a list of items from the database, linkedlist.
	 * 
	 * @return a linkedlist
	 */
	private LinkedList<Item> getItems() {
		LinkedList<Item> items = new LinkedList<Item>();
		int custId=-1;
		int itemCateId=-1;
		//Item Cate and Item Cust query
		if (!(this.cmbItemCusts.getSelectedIndex() <= 0)) {
			custId = this.custs.get(this.cmbItemCusts.getSelectedIndex()-1).getCustId();
		}
		if (!(this.cmbItemCates.getSelectedIndex() == 0)) {
			itemCateId = this.itemCates.get(this.cmbItemCates.getSelectedIndex()-1).itemCateId;
		}
		if(custId!=-1 && itemCateId!=-1){
			items.addAll(Items.getItems("custId="+custId+" AND itemCateId="+itemCateId));
		}
		else if(custId!=-1 && itemCateId==-1){
			items.addAll(Items.getItems("custId="+custId));
		}
		else if(custId==-1 && itemCateId==-1){
			items.addAll(Items.getItems(""));
		}
		else
			items.addAll(Items.getItems("itemCateId="+itemCateId));
		return items;
	}

	private void editItemEventHandler() {
		if (this.selectedItemId == -1) {
			Prompt.showMessage("Select an item to edit",
					"Edit Item", "warning");
			return;
		}
		AddItem itemEditFrm = new AddItem(FormType.editing);
		itemEditFrm.editItem(this.items.get(this.selectedItemId),this);
		itemEditFrm.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	

	private void tblMouseClinkHandler() {
		this.selectedItemId = this.table.getSelectedRow();
	}

	/**
	 * Updates the technicians table from out side. (made especially for edit
	 * event completion)
	 * 
	 */
	public void updateTable() {
		listAllItems();
	}

	// //loadingsss

	private void loadCustomers() {
		if ((this.custs = Customers.getCustomers("")).size() > 0) {
			this.cmbItemCusts.removeAllItems();
			this.cmbItemCusts.addItem("OFF this filter");
			for (Customer c : this.custs) {
				this.cmbItemCusts.addItem(c.name);
			}
			this.cmbItemCusts.setSelectedIndex(0);
		}
	}
	private void loadItemCates() {
		if ((this.itemCates = ItemCategories.getItemCategories("")).size() > 0) {
			this.cmbItemCates.removeAllItems();
			this.cmbItemCates.addItem("OFF this filter");
			for (ItemCategory i : this.itemCates) {
				this.cmbItemCates.addItem(i.name);
			}
			this.cmbItemCates.setSelectedIndex(0);
		}
	}

	// private void loadModels() {
	// if ((this.models = Models.getModels("")).size() > 0) {
	// this.cmbItemModes.removeAllItems();
	// for (Model m : this.models) {
	// this.cmbItemModes.addItem(m.getName());
	// }
	// this.cmbItemModes.setSelectedIndex(0);
	// }
	// }
	//	private void loadProbCates() {
	//		if ((this.probCates = ProblemCategories.getProbCategories("")).size() > 0) {
	//			this.cmbProbCates.removeAllItems();
	//			this.cmbProbCates.addItem("OFF this filter");
	//			for (ProblemCategory i : this.probCates) {
	//				this.cmbProbCates.addItem(i.getName());
	//			}
	//			this.cmbProbCates.setSelectedIndex(0);
	//		}
	//	}
	//	private void loadItemTechs() {
	//		System.out.println("int techsl oad method load techs");
	//		if ((this.techs = Technicians.getTechnicians("")).size() > 0) {
	//			this.cmbItemTechs.removeAllItems();
	//			this.cmbItemTechs.addItem("OFF this filter");
	//			for (Technician i : this.techs) {
	//				this.cmbItemTechs.addItem(i.name);
	//			}
	//			this.cmbItemTechs.setSelectedIndex(0);
	//		}
	//	}
}
