package temp.old;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class XXX_SetClassName {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					XXX_SetClassName window = new XXX_SetClassName();
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
	public XXX_SetClassName() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 382, 153);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblSetTheName = new JLabel("Set the name for the new <ConceptStereotype>");
		lblSetTheName.setBounds(12, 12, 356, 15);
		frame.getContentPane().add(lblSetTheName);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(22, 39, 70, 15);
		frame.getContentPane().add(lblName);
		
		textField = new JTextField();
		textField.setBounds(99, 39, 156, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnSetName = new JButton("Set name");
		btnSetName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSetName.setBounds(99, 66, 117, 25);
		frame.getContentPane().add(btnSetName);
	}

}
