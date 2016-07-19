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
import DomainLayer.Manufacturer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

/**
 * UI class for adding new manufacturer
 * 
 * @author Ahmad Ali
 *
 */
public class AddManufacturer extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtManuName;
	private JTextField txtManuEmail;
	private JTextField txtManuWebSite;

	/**
	 * Create the frame.
	 */
	public AddManufacturer() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddManufacturer.class.getResource("/small/addManu.png")));
		setTitle("Add Model");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setBounds(100, 100, 575, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddModel = new JLabel("Add Manufacturer");
		lblAddModel.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddModel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblAddModel.setBounds(10, 11, 547, 50);
		contentPane.add(lblAddModel);
		
		JLabel lblModelName = new JLabel("Manufacturer Name");
		lblModelName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblModelName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblModelName.setBounds(53, 94, 115, 14);
		contentPane.add(lblModelName);
		
		txtManuName = new JTextField();
		txtManuName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtManuName.setColumns(10);
		txtManuName.setBounds(178, 91, 136, 20);
		contentPane.add(txtManuName);
		
		JLabel lblYear = new JLabel("Email");
		lblYear.setHorizontalAlignment(SwingConstants.RIGHT);
		lblYear.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblYear.setBounds(104, 128, 64, 14);
		contentPane.add(lblYear);
		
		txtManuEmail = new JTextField();
		txtManuEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtManuEmail.setColumns(10);
		txtManuEmail.setBounds(178, 125, 136, 20);
		contentPane.add(txtManuEmail);
		
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
		btnDelete.setBounds(240, 310, 95, 23);
		contentPane.add(btnDelete);
		
		JButton button_2 = new JButton("Cancel");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddManufacturer.this.dispose();
			}
		});
		button_2.setBounds(345, 310, 95, 23);
		contentPane.add(button_2);
		
		JLabel lblWebsite = new JLabel("Website");
		lblWebsite.setHorizontalAlignment(SwingConstants.RIGHT);
		lblWebsite.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblWebsite.setBounds(104, 164, 64, 14);
		contentPane.add(lblWebsite);
		
		txtManuWebSite = new JTextField();
		txtManuWebSite.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtManuWebSite.setColumns(10);
		txtManuWebSite.setBounds(178, 161, 136, 20);
		contentPane.add(txtManuWebSite);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDescription.setBounds(104, 197, 64, 14);
		contentPane.add(lblDescription);
		
		JTextArea txtManuDesc = new JTextArea();
		txtManuDesc.setRows(4);
		txtManuDesc.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtManuDesc.setBounds(178, 192, 136, 81);
		contentPane.add(txtManuDesc);
	}

	protected void btnSaveEventHandler() {
		Manufacturer manu = new Manufacturer(0,txtManuName.getText(),txtManuWebSite.getText(),txtManuEmail.getText());
		Manufacturers.saveManufacturer(manu);
		txtManuName.setText("");
		txtManuEmail.setText("");
		txtManuWebSite.setText("");
		
	}
}
