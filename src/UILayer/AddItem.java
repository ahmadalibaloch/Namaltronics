package UILayer;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import UI_Utils.FormType;
import UI_Utils.Prompt;

import DALayer.Customers;
import DALayer.ItemCategories;
import DALayer.Items;
import DALayer.Manufacturers;
import DALayer.Models;
import DALayer.ProblemCategories;
import DALayer.Problems;
import DomainLayer.Customer;
import DomainLayer.Item;
import DomainLayer.ItemCategory;
import DomainLayer.Manufacturer;
import DomainLayer.Model;
import DomainLayer.ProblemCategory;
import java.awt.Color;
import java.util.LinkedList;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;

import Beans_Utils.CmbConvertor;
import Beans_Utils.DifficultyLevelTextConvertor;
import DomainLayer.DifficultyLevel;
import Beans_Utils.DifficultyLevelConvertor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Toolkit;

/**
 * To add a new Item and also edit when selected from view items form
 * 
 * @author Ahmad Ali. Created March 16, 2012.
 */
public class AddItem extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtItemName;
	private JTextField txtProbLevel;
	private final JTabbedPane tabbedPane;
	private JComboBox cmbItemCate;
	private JComboBox cmbItemManu;
	private JComboBox cmbItemMode;
	private JComboBox cmbItemCust;
	private JComboBox cmbProblemCate;
	private FormType frmType = FormType.saving;
	private ViewItems vItems;
	//

	private Item item = new Item();
	private JLabel lblError;
	private JLabel lblError1;

	/**
	 * Create the frame.
	 * 
	 * @param frmType
	 */
	public AddItem(FormType frmType) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddItem.class.getResource("/small/addItem.png")));
		setVisible(true);
		setResizable(false);
		setTitle("Add Item");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 614, 521);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		this.contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("Add an Item");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblTitle.setBounds(3, 11, 606, 50);
		this.contentPane.add(lblTitle);

		JButton btnItemSave = new JButton("Save Record");
		btnItemSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnSaveEventHandler();
			}
		});
		btnItemSave.setBounds(360, 424, 95, 23);
		this.contentPane.add(btnItemSave);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddItem.this.dispose();
			}
		});
		btnCancel.setBounds(465, 424, 95, 23);
		this.contentPane.add(btnCancel);

		this.tabbedPane = new JTabbedPane(SwingConstants.LEFT);
		this.tabbedPane.setBorder(null);
		this.tabbedPane.setBounds(27, 85, 536, 332);
		this.contentPane.add(this.tabbedPane);

		JPanel panel = new JPanel();
		this.tabbedPane.addTab("Item Information", null, panel, null);
		panel.setLayout(null);

		JLabel label_1 = new JLabel("Model");
		label_1.setToolTipText("Model or Model #");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setBounds(42, 130, 72, 14);
		panel.add(label_1);

		JLabel label_2 = new JLabel("Manufacturer");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_2.setBounds(42, 100, 72, 14);
		panel.add(label_2);

		JLabel lblCategory = new JLabel("Category");
		lblCategory.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCategory.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCategory.setBounds(42, 70, 72, 14);
		panel.add(lblCategory);

		this.txtItemName = new JTextField();
		this.txtItemName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				hideError();
			}
		});
		this.txtItemName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.txtItemName.setColumns(10);
		this.txtItemName.setBounds(124, 40, 162, 20);
		panel.add(this.txtItemName);

		JLabel label_4 = new JLabel("Item Name");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_4.setBounds(42, 40, 72, 14);
		panel.add(label_4);

		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (validateItemCmbs())
					AddItem.this.tabbedPane.setSelectedIndex(1);

			}
		});
		btnNext.setBounds(10, 293, 91, 23);
		panel.add(btnNext);

		this.cmbItemCate = new JComboBox();
		this.cmbItemCate.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				hideError();
			}
		});
		this.cmbItemCate.setBounds(124, 67, 162, 22);
		panel.add(this.cmbItemCate);

		this.cmbItemManu = new JComboBox();
		this.cmbItemManu.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				hideError();
			}
		});
		this.cmbItemManu.setBounds(124, 97, 162, 22);
		panel.add(this.cmbItemManu);

		this.cmbItemMode = new JComboBox();
		this.cmbItemMode.setBounds(124, 127, 162, 22);
		panel.add(this.cmbItemMode);

		JLabel lblDescription = new JLabel("Description");
		lblDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescription.setBounds(53, 166, 61, 14);
		panel.add(lblDescription);

		this.txtItemDesc = new JTextArea();
		this.txtItemDesc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				hideError();
			}
		});
		this.txtItemDesc.setBorder(new SoftBevelBorder(BevelBorder.LOWERED,
				null, null, null, null));
		this.txtItemDesc.setRows(5);
		this.txtItemDesc.setBounds(124, 160, 162, 75);
		panel.add(this.txtItemDesc);

		JLabel label = new JLabel("Customer");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		label.setBounds(42, 248, 72, 14);
		panel.add(label);

		this.cmbItemCust = new JComboBox();
		this.cmbItemCust.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				hideError();
			}
		});
		this.cmbItemCust.setBounds(124, 246, 162, 22);
		panel.add(this.cmbItemCust);

		this.lblError = new JLabel("*Error");
		this.lblError.setForeground(SystemColor.control);
		this.lblError.setBounds(296, 44, 130, 20);
		panel.add(this.lblError);

		JPanel panel_1 = new JPanel();
		this.tabbedPane.addTab("Problem Info", null, panel_1, null);
		panel_1.setBorder(null);
		panel_1.setLayout(null);

		JLabel lblPrblmCategory = new JLabel("Category");
		lblPrblmCategory.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrblmCategory.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPrblmCategory.setBounds(40, 40, 74, 14);
		panel_1.add(lblPrblmCategory);

		JLabel lblPrblmLevel = new JLabel("Difficult Level");
		lblPrblmLevel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrblmLevel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPrblmLevel.setBounds(40, 71, 74, 14);
		panel_1.add(lblPrblmLevel);

		JLabel lblPrblmDetail = new JLabel("Detail");
		lblPrblmDetail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrblmDetail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPrblmDetail.setBounds(40, 102, 74, 14);
		panel_1.add(lblPrblmDetail);

		this.txtProbDetail = new JTextArea();
		this.txtProbDetail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				hideError();
			}
		});
		this.txtProbDetail.setBorder(new SoftBevelBorder(BevelBorder.LOWERED,
				null, null, null, null));
		this.txtProbDetail.setRows(5);
		this.txtProbDetail.setBounds(124, 102, 162, 99);
		panel_1.add(this.txtProbDetail);

		this.sldProbLevel = new JSlider();
		this.sldProbLevel.setValue(1);
		this.sldProbLevel
				.setToolTipText("Difficulty level of this problem expected.");
		this.sldProbLevel.setSnapToTicks(true);
		this.sldProbLevel.setMaximum(2);
		this.sldProbLevel.setMajorTickSpacing(1);
		this.sldProbLevel.setBounds(124, 68, 91, 24);
		panel_1.add(this.sldProbLevel);

		this.txtProbLevel = new JTextField();
		txtProbLevel.setText("Normal");
		this.txtProbLevel.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.txtProbLevel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.txtProbLevel.setEditable(false);
		this.txtProbLevel.setColumns(2);
		this.txtProbLevel.setBounds(225, 68, 61, 20);
		panel_1.add(this.txtProbLevel);

		this.cmbProblemCate = new JComboBox();
		this.cmbProblemCate.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				hideError();
			}
		});
		this.cmbProblemCate.setBounds(124, 40, 162, 22);
		panel_1.add(this.cmbProblemCate);
		
		this.lblError1 = new JLabel("lblError1");
		this.lblError1.setForeground(SystemColor.control);
		this.lblError1.setBounds(289, 41, 137, 20);
		panel_1.add(this.lblError1);
		//
		this.frmType = frmType;
		if (this.frmType == FormType.editing) {
			lblTitle.setText("Edit Item");
		}
		// loadings
		loadCustomers();
		loadItemCates();
		loadManus();
		loadModels();
		loadProbCates();
		initDataBindings();

	}

	//
	private LinkedList<Customer> custs = null;
	private LinkedList<Model> models = null;
	private LinkedList<ItemCategory> itemCates = null;
	private LinkedList<Manufacturer> manus = null;
	private LinkedList<ProblemCategory> probCates = null;
	private JSlider sldProbLevel;
	private JTextArea txtProbDetail;
	private JTextArea txtItemDesc;

	private void loadCustomers() {
		if ((this.custs = Customers.getCustomers("")).size() > 0) {
			this.cmbItemCust.removeAllItems();
			for (Customer c : this.custs) {
				this.cmbItemCust.addItem(c.name);
			}
			this.cmbItemCust.setSelectedIndex(0);
		}
	}

	private void loadModels() {
		if ((this.models = Models.getModels("")).size() > 0) {
			this.cmbItemMode.removeAllItems();
			for (Model m : this.models) {
				this.cmbItemMode.addItem(m.getName());
			}
			this.cmbItemMode.setSelectedIndex(0);
		}
	}

	private void loadItemCates() {
		if ((this.itemCates = ItemCategories.getItemCategories("")).size() > 0) {
			this.cmbItemCate.removeAllItems();
			for (ItemCategory i : this.itemCates) {
				this.cmbItemCate.addItem(i.name);
			}
			this.cmbItemCate.setSelectedIndex(0);
		}
	}

	private void loadManus() {
		if ((this.manus = Manufacturers.getManufacturers("")).size() > 0) {
			this.cmbItemManu.removeAllItems();
			for (Manufacturer m : this.manus) {
				this.cmbItemManu.addItem(m.getName());
			}
			this.cmbItemManu.setSelectedIndex(0);
		}
	}

	private void loadProbCates() {
		if ((this.probCates = ProblemCategories.getProbCategories("")).size() > 0) {
			this.cmbProblemCate.removeAllItems();
			for (ProblemCategory p : this.probCates) {
				this.cmbProblemCate.addItem(p.getName());
			}
			this.cmbProblemCate.setSelectedIndex(0);
		}
	}

	private boolean validateItemCmbs() {
		if (!this.txtItemName.getText().equalsIgnoreCase(""))
			if (this.cmbItemCate.getSelectedItem() != null)
				if (this.cmbItemCust.getSelectedItem() != null)
					return true;
				else
					showError("Select a customer here", this.cmbItemCust);
			else
				showError("Select an item category", this.cmbItemCate);
		else
			showError("Item name is required", this.txtItemName);
		return false;
	}

	private boolean validateProbCmbs() {
		if (this.cmbProblemCate.getSelectedItem() != null)
			if (!this.txtProbDetail.getText().equalsIgnoreCase(""))
				return true;
			else
				showError1("Enter details of the problem", this.txtProbDetail);
		else
			showError1("Select a problem category", this.cmbProblemCate);
		return false;
	}

	private void btnSaveEventHandler() {
		//
		if (validateItemCmbs())
			if (validateProbCmbs()) {
				if (this.frmType == FormType.saving) {
					Problems.saveProblem(this.item.getProblem());
					Items.saveItem(this.item);
					Prompt.showMessage("Item saved succussfully.", "Item Save",
							"plain");
				} else {
					Problems.editProb(this.item.getProblem());
					Items.editItem(this.item);
					this.vItems.updateTable();
					this.dispose();
				}
			} else
				AddItem.this.tabbedPane.setSelectedIndex(1);
		else
			AddItem.this.tabbedPane.setSelectedIndex(0);
	}

	/**
	 * When opened as edit item, this method is called to set binding and update
	 * reference of calling form.
	 * 
	 * @param item
	 *            to be edited.
	 * @param vItems
	 *            the calling form reference to update the table list.
	 */
	public void editItem(Item item, ViewItems vItems) {
		this.vItems = vItems;
		this.item = item;
		initDataBindings();
	}

	/**
	 * Bindings. Beans binding created method.
	 * 
	 */
	protected void initDataBindings() {
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty
				.create("text");
		BeanProperty<JSlider, Integer> jSliderBeanProperty = BeanProperty
				.create("value");
		AutoBinding<JTextField, String, JSlider, Integer> autoBinding = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE,
						this.txtProbLevel, jTextFieldBeanProperty,
						this.sldProbLevel, jSliderBeanProperty);
		autoBinding.setConverter(new DifficultyLevelTextConvertor());
		autoBinding.bind();
		//
		BeanProperty<Item, String> itemBeanProperty = BeanProperty
				.create("desc");
		BeanProperty<JTextArea, String> jTextAreaBeanProperty_1 = BeanProperty
				.create("text");
		AutoBinding<Item, String, JTextArea, String> autoBinding_3 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, this.item,
						itemBeanProperty, this.txtItemDesc,
						jTextAreaBeanProperty_1);
		autoBinding_3.bind();
		//
		BeanProperty<Item, String> itemBeanProperty_1 = BeanProperty
				.create("name");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_1 = BeanProperty
				.create("text");
		AutoBinding<Item, String, JTextField, String> autoBinding_4 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, this.item,
						itemBeanProperty_1, this.txtItemName,
						jTextFieldBeanProperty_1);
		autoBinding_4.bind();
		//
		BeanProperty<Item, Integer> itemBeanProperty_2 = BeanProperty
				.create("modeId");
		BeanProperty<JComboBox, Object> jComboBoxBeanProperty = BeanProperty
				.create("selectedItem");
		AutoBinding<Item, Integer, JComboBox, Object> autoBinding_5 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, this.item,
						itemBeanProperty_2, this.cmbItemMode,
						jComboBoxBeanProperty);
		autoBinding_5.setConverter(new CmbConvertor("model"));
		autoBinding_5.bind();
		//
		BeanProperty<Item, String> itemBeanProperty_3 = BeanProperty
				.create("prob.probDetail");
		BeanProperty<JTextArea, String> jTextAreaBeanProperty = BeanProperty
				.create("text");
		AutoBinding<Item, String, JTextArea, String> autoBinding_1 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, this.item,
						itemBeanProperty_3, this.txtProbDetail,
						jTextAreaBeanProperty);
		autoBinding_1.bind();
		//
		BeanProperty<Item, Integer> itemBeanProperty_5 = BeanProperty
				.create("manuId");
		AutoBinding<Item, Integer, JComboBox, Object> autoBinding_6 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, this.item,
						itemBeanProperty_5, this.cmbItemManu,
						jComboBoxBeanProperty);
		autoBinding_6.setConverter(new CmbConvertor("manufacturer"));
		autoBinding_6.bind();
		//
		BeanProperty<Item, Integer> itemBeanProperty_6 = BeanProperty
				.create("itemCateId");
		AutoBinding<Item, Integer, JComboBox, Object> autoBinding_7 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, this.item,
						itemBeanProperty_6, this.cmbItemCate,
						jComboBoxBeanProperty);
		autoBinding_7.setConverter(new CmbConvertor("itemCategory"));
		autoBinding_7.bind();
		//
		BeanProperty<Item, Integer> itemBeanProperty_7 = BeanProperty
				.create("custId");
		AutoBinding<Item, Integer, JComboBox, Object> autoBinding_8 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, this.item,
						itemBeanProperty_7, this.cmbItemCust,
						jComboBoxBeanProperty);
		autoBinding_8.setConverter(new CmbConvertor("customer"));
		autoBinding_8.bind();
		//
		BeanProperty<Item, Integer> itemBeanProperty_4 = BeanProperty
				.create("problem.probCateId");
		AutoBinding<Item, Integer, JComboBox, Object> autoBinding_2 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, this.item,
						itemBeanProperty_4, this.cmbProblemCate,
						jComboBoxBeanProperty);
		autoBinding_2.setConverter(new CmbConvertor("problemCategory"));
		autoBinding_2.bind();
		//
		BeanProperty<Item, DifficultyLevel> itemBeanProperty_8 = BeanProperty
				.create("problem.probDifficultyLevel");
		BeanProperty<JSlider, Integer> jSliderBeanProperty_1 = BeanProperty
				.create("value");
		AutoBinding<Item, DifficultyLevel, JSlider, Integer> autoBinding_9 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, this.item,
						itemBeanProperty_8, this.sldProbLevel,
						jSliderBeanProperty_1);
		autoBinding_9.setConverter(new DifficultyLevelConvertor());
		autoBinding_9.bind();
	}

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
