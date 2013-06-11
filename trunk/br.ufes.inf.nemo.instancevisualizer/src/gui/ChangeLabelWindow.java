package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JTextField;

import obj.XGraph;

import org.graphstream.graph.Node;

public class ChangeLabelWindow extends JFrame {

	private XGraph xGraph;
	private JTable table;
	private JPanel contentPane;
	private JComboBox comboBox;
	private JTextField textField;
	private JTextField txtNotWorking;

	/**
	 * Create the frame.
	 */
	public ChangeLabelWindow(XGraph xGraphVar, JTable tableVar) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 247, 198);
		xGraph = xGraphVar;
		table = tableVar;
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		comboBox = new JComboBox();
		
		JButton btnNewButton = new JButton("Ok");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!textField.getText().isEmpty()) {
					if(table.getValueAt(0, 1).equals(comboBox.getSelectedItem().toString())) {
						table.setValueAt(textField.getText(), 1, 1);
					}
					xGraph.getSelectedGraph().getNode(comboBox.getSelectedItem().toString()).changeAttribute("ui.label", textField.getText());
					//xGraph.getSelectedGraph().getNode(comboBox.getSelectedItem().toString()).addAttribute("ui.style", "fill-mode: image-scaled; fill-image: url('" + textField_1.getText() + "');");
					dispose();
				}
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JLabel lblNewLabel = new JLabel("New Label:");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblImage = new JLabel("Image:");
		
		txtNotWorking = new JTextField();
		txtNotWorking.setText("DEACTIVATED");
		txtNotWorking.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(btnCancel)
							.addPreferredGap(ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
							.addComponent(btnNewButton))
						.addComponent(comboBox, Alignment.TRAILING, 0, 211, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textField, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblImage)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtNotWorking, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblImage)
						.addComponent(txtNotWorking, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnCancel))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		
		initComboBox();
	}
	
	public void initComboBox() {
		int i;
		Collection<Node> nodeList = xGraph.getSelectedGraph().getNodeSet();
		Iterator node = nodeList.iterator();
		for(i=0; i<nodeList.size(); i++) {
			//Node n = node.next();
			comboBox.addItem(node.next());
		}
	}
}
