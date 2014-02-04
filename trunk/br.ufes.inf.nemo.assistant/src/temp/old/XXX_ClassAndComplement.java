package temp.old;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class XXX_ClassAndComplement {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					XXX_ClassAndComplement window = new XXX_ClassAndComplement();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public XXX_ClassAndComplement() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 499, 289);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Create the complement for <ConceptName>");
		lblNewLabel.setBounds(37, 12, 487, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNameForNew = new JLabel("Name of new <NewStereotype>:");
		lblNameForNew.setBounds(37, 52, 380, 15);
		frame.getContentPane().add(lblNameForNew);
		
		JLabel lblNameOfComplement = new JLabel("Name of the complement:");
		lblNameOfComplement.setBounds(37, 80, 163, 15);
		frame.getContentPane().add(lblNameOfComplement);
		
		textField = new JTextField();
		textField.setBounds(293, 50, 179, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(293, 79, 179, 19);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnAddMoreComplements = new JButton("Add more complements");
		btnAddMoreComplements.setBounds(74, 107, 209, 25);
		frame.getContentPane().add(btnAddMoreComplements);
		
		JButton btnCreateGeneralizationset = new JButton("Create GeneralizationSet");
		btnCreateGeneralizationset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCreateGeneralizationset.setBounds(129, 209, 224, 25);
		frame.getContentPane().add(btnCreateGeneralizationset);
		
		JLabel lblSetMetaproperties = new JLabel("Set meta-properties:");
		lblSetMetaproperties.setBounds(37, 160, 163, 15);
		frame.getContentPane().add(lblSetMetaproperties);
		
		JCheckBox chckbxDisjoint = new JCheckBox("Disjoint");
		chckbxDisjoint.setBounds(203, 140, 129, 23);
		frame.getContentPane().add(chckbxDisjoint);
		
		JCheckBox chckbxComplete = new JCheckBox("Complete");
		chckbxComplete.setBounds(203, 167, 129, 23);
		frame.getContentPane().add(chckbxComplete);
	}
}
