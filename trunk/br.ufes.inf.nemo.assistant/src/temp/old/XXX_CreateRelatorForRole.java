package temp.old;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class XXX_CreateRelatorForRole {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					XXX_CreateRelatorForRole window = new XXX_CreateRelatorForRole();
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
	public XXX_CreateRelatorForRole() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 424, 166);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblCreateARelator = new JLabel("Create a Relator to connect with <ConceptName>");
		lblCreateARelator.setBounds(12, 12, 365, 15);
		frame.getContentPane().add(lblCreateARelator);
		
		JLabel lblNameOfNew = new JLabel("Name of new Relator:");
		lblNameOfNew.setBounds(22, 39, 175, 15);
		frame.getContentPane().add(lblNameOfNew);
		
		textField = new JTextField();
		textField.setBounds(186, 37, 152, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnCreateRelator = new JButton("Create Relator");
		btnCreateRelator.setBounds(104, 79, 175, 25);
		frame.getContentPane().add(btnCreateRelator);
	}

}
