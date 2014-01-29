package br.ufes.inf.nemo.assistant.window.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import br.ufes.inf.nemo.assistant.manager.ManagerNode;

public class NewClass extends AbstractWindow{

	private JTextField tfNameCls;
	private JComboBox<String> cbStereotypes = new JComboBox<String>();
	private JButton btnCreateNewConcept = new JButton("Create new concept");

	/**
	 * Create the application.
	 */
	public NewClass() {
		instance = this;
		initialize();
	}

	/**
	 * Initialize the contents of the this.
	 */
	private void initialize() {
		this.setBounds(100, 100, 351, 181);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.getContentPane().setLayout(null);

		JLabel lblCreateNewConcept = new JLabel("Create a new concept");
		lblCreateNewConcept.setBounds(12, 12, 168, 15);
		this.getContentPane().add(lblCreateNewConcept);

		JLabel lblConceptName = new JLabel("Concept name:");
		lblConceptName.setBounds(22, 41, 121, 15);
		this.getContentPane().add(lblConceptName);

		tfNameCls = new JTextField();
		tfNameCls.setBounds(141, 39, 139, 19);
		this.getContentPane().add(tfNameCls);
		tfNameCls.setColumns(10);

		JLabel lblConceptStereotype = new JLabel("Concept stereotype:");
		lblConceptStereotype.setBounds(12, 75, 161, 15);
		this.getContentPane().add(lblConceptStereotype);

		cbStereotypes.setBounds(179, 70, 145, 24);
		this.getContentPane().add(cbStereotypes);

		btnCreateNewConcept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(this);
				myNode.getTree().getManagerPatern().callback_newClass(tfNameCls.getText(), (String)cbStereotypes.getSelectedItem());
				ManagerNode.goNext(myNode);
				instance.killMySelf();
			}
		});
		btnCreateNewConcept.setBounds(60, 106, 206, 25);
		this.getContentPane().add(btnCreateNewConcept);
	}

	public void setStereotypes(String[] sts){
		cbStereotypes.setModel(new DefaultComboBoxModel<String>(sts));
	}
}
