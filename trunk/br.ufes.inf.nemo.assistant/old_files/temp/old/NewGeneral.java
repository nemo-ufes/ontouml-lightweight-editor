package temp.old;

import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewGeneral extends AbstractWindow{

	private JTextField textField;
	private JComboBox<String> cbStereotypes = new JComboBox<String>();

	/**
	 * Create the application.
	 */
	public NewGeneral() {
		instance = this;
		initialize();
	}

	/**
	 * Initialize the contents of the this.
	 */
	private void initialize() {
		this.setBounds(100, 100, 467, 219);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Select the new General for the concept: <ConceptName>");
		lblNewLabel.setBounds(45, 12, 599, 15);
		this.getContentPane().add(lblNewLabel);
		
		cbStereotypes.setBounds(249, 74, 151, 24);
		this.getContentPane().add(cbStereotypes);
		
		JLabel lblNameOfThe = new JLabel("Name of the General:");
		lblNameOfThe.setBounds(45, 39, 204, 15);
		this.getContentPane().add(lblNameOfThe);
		
		textField = new JTextField();
		textField.setBounds(213, 37, 114, 19);
		this.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblStereotypeOfThe = new JLabel("Stereotype of the General:");
		lblStereotypeOfThe.setBounds(45, 79, 252, 15);
		this.getContentPane().add(lblStereotypeOfThe);
		
		JButton btnCreateSpecialization = new JButton("Create new general");
		btnCreateSpecialization.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCreateSpecialization.setBounds(116, 126, 211, 25);
		this.getContentPane().add(btnCreateSpecialization);
	}
	
	public void setStereotypes(String[] stereotypes){
		cbStereotypes.setModel(new DefaultComboBoxModel<String>(stereotypes));
	}
}
