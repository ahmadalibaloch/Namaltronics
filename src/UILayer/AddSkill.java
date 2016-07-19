package UILayer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

import DALayer.SkillCategories;
import DomainLayer.SkillCategory;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

/**
 * UI class for adding skills
 * @author Administrator
 *
 */
public class AddSkill extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtSkillName;
	private JTextArea txtSkillDesc ;


	/**
	 * Create the frame.
	 */
	public AddSkill() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddSkill.class.getResource("/small/addSkill.png")));
		setTitle("Add Technician Skill");
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 530, 426);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddSkillCategory = new JLabel("Add Skill Category");
		lblAddSkillCategory.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddSkillCategory.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblAddSkillCategory.setBounds(10, 11, 502, 50);
		contentPane.add(lblAddSkillCategory);
		
		txtSkillName = new JTextField();
		txtSkillName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSkillName.setColumns(10);
		txtSkillName.setBounds(178, 91, 136, 20);
		contentPane.add(txtSkillName);
		
		JLabel lblSkillName = new JLabel("Skill Name");
		lblSkillName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSkillName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSkillName.setBounds(88, 94, 80, 14);
		contentPane.add(lblSkillName);
		
		txtSkillDesc = new JTextArea();
		txtSkillDesc.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtSkillDesc.setRows(4);
		txtSkillDesc.setBounds(178, 135, 136, 81);
		contentPane.add(txtSkillDesc);
		
		JButton button = new JButton("Save");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnSaveEventHandler();
			}
		});
		button.setBounds(135, 310, 95, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Delete");
		button_1.setEnabled(false);
		button_1.setBounds(240, 310, 95, 23);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("Cancel");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnCancelEventHandler();
			}
		});
		button_2.setBounds(345, 310, 95, 23);
		contentPane.add(button_2);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDescription.setBounds(88, 138, 80, 14);
		contentPane.add(lblDescription);
	}


	protected void btnCancelEventHandler() {
		this.dispose();
	}


	protected void btnSaveEventHandler() {
		SkillCategories.saveSkillCategory(new SkillCategory(0, txtSkillName.getText(), txtSkillDesc.getText()));
		txtSkillName.setText("");
		txtSkillDesc.setText("");
		
	}

}
