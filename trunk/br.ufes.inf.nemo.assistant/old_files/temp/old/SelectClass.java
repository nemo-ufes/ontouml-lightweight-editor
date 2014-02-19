package temp.old;

import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;

import br.ufes.inf.nemo.assistant.manager.ManagerNode;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SelectClass extends AbstractWindow{

	private JComboBox<String> cbOptions = new JComboBox<String>();
	/**
	 * Create the application.
	 */
	public SelectClass() {
		instance = this;
		initialize();
	}

	/**
	 * Initialize the contents of the this.
	 */
	private void initialize() {
		this.setBounds(100, 100, 558, 183);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		JLabel lblSelectTheClass = new JLabel("Select the class");
		lblSelectTheClass.setBounds(29, 22, 131, 15);
		this.getContentPane().add(lblSelectTheClass);
		
		JLabel lblClass = new JLabel("Class:");
		lblClass.setBounds(29, 68, 70, 15);
		this.getContentPane().add(lblClass);
		
		
		cbOptions.setBounds(96, 63, 381, 24);
		this.getContentPane().add(cbOptions);
		
		JButton btnSelect = new JButton("Select");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myNode.getTree().getManagerPatern().callback_selectClass((String)cbOptions.getSelectedItem());
				ManagerNode.goNext(myNode);
				instance.killMySelf();
			}
		});
		btnSelect.setBounds(231, 103, 117, 25);
		this.getContentPane().add(btnSelect);
	}
	
	public void setClassesOptions(String[] options){
		cbOptions.setModel(new DefaultComboBoxModel<String>(options));
	}

}
