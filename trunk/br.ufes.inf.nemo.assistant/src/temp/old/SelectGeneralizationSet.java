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

public class SelectGeneralizationSet extends AbstractWindow{

	private JComboBox<String> cbGeneralizationSet = new JComboBox<String>();
	/**
	 * Create the application.
	 */
	public SelectGeneralizationSet() {
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
		
		JLabel lblSelectTheClass = new JLabel("Select the GeneralizationSet");
		lblSelectTheClass.setBounds(29, 22, 131, 15);
		this.getContentPane().add(lblSelectTheClass);
		
		JLabel lblClass = new JLabel("GeneralizationSet:");
		lblClass.setBounds(29, 68, 70, 15);
		this.getContentPane().add(lblClass);
		
		
		cbGeneralizationSet.setBounds(96, 63, 381, 24);
		this.getContentPane().add(cbGeneralizationSet);
		
		JButton btnSelect = new JButton("Select");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myNode.getTree().getManagerPatern().callback_selectGeneralizationSet((String)cbGeneralizationSet.getSelectedItem());
				ManagerNode.goNext(myNode);
				instance.killMySelf();
			}
		});
		btnSelect.setBounds(231, 103, 117, 25);
		this.getContentPane().add(btnSelect);
	}
	
	public void setGeneralizationSets(String[] gs){
		cbGeneralizationSet.setModel(new DefaultComboBoxModel<String>(gs));
	}

}
