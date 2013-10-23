package br.ufes.inf.nemo.oled.modellingassistant.ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelShowFatherOfClass extends JPanel {

	/**
	 * Create the panel.
	 */

	JComboBox<String> cbSortal;

	public PanelShowFatherOfClass(String cls, String[] fathers) {
		setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lbTop = new JLabel(" The "+cls+" class necessity of a identity principle ");
		add(lbTop);

		JLabel lblSelectOne = new JLabel("Select one:");
		add(lblSelectOne);

		cbSortal = new JComboBox<String>(fathers);		
		//		cbSortal = new JComboBox<String>();
		add(cbSortal);

		JLabel lblOr = new JLabel("OR");
		lblOr.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblOr);

		JButton btNewClass = new JButton("Create a new Class");
		final String acls = cls;
		btNewClass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ManagerUserInterface.getInstance().showCreateNewClass(acls);
			}
		});
		add(btNewClass);

		JButton btOK = new JButton("OK");
		btOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String clsName = (String) cbSortal.getSelectedItem();
				String ontoType = clsName.substring(0, clsName.indexOf(" "));
				clsName = clsName.substring(clsName.indexOf(" ")+1);
				ManagerUserInterface.getInstance().callBackForSelect(ontoType, clsName);
			}
		});
		add(btOK);
	}
}
