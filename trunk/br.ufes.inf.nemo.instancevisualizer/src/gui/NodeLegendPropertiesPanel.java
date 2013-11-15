package gui;

import graph.GraphManager;
import graph.NodeLegendManager;
import graph.NodeManager;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;


import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

public class NodeLegendPropertiesPanel extends JScrollPane {
	
	private String typeName;
	private GraphManager graphManager;
	private JTextField textField;
	private JCheckBox chckbxVisible;
	
	public NodeLegendPropertiesPanel(final String typeName, GraphManager graphManager) {
		super();
		this.typeName = typeName;
		this.graphManager = graphManager;
		
		JPanel contentPane = new JPanel();
		setViewportView(contentPane);
		
		JLabel label = new JLabel("");
		label.setBounds(10, 11, 32, 32);
		
		final String imagePath = graphManager.getLegendManager().getTypeImagePath(typeName);
		label.setIcon(new ImageIcon(imagePath));
		
		JLabel lblNewLabel = new JLabel(typeName + "«" + graphManager.getLegendManager().getStereoType(typeName) + "»");
		lblNewLabel.setBounds(52, 20, 109, 14);
		
		JButton btnEditStyle = new JButton("Edit Style");
		btnEditStyle.setBounds(10, 61, 88, 23);
		btnEditStyle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new StyleWindow(imagePath, getGraphManager().getLegendManager().getTypeStyle(typeName), getTypeName(), getGraphManager());//(getTypeName(), getGraphManager(), true);
			}
		});
		
		JLabel lblPrefix = new JLabel("Prefix:");
		lblPrefix.setBounds(10, 98, 45, 14);
		
		textField = new JTextField(graphManager.getLegendManager().getPrefix(typeName));
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		textField.setBounds(65, 95, 96, 20);
		textField.setColumns(10);
		contentPane.setLayout(null);
		contentPane.add(label);
		contentPane.add(lblNewLabel);
		contentPane.add(btnEditStyle);
		contentPane.add(lblPrefix);
		contentPane.add(textField);
		
		chckbxVisible = new JCheckBox("Visible");
		chckbxVisible.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxVisible.isSelected()) {
					System.out.println("FOI");
					getGraphManager().updateNodesStyle(getGraphManager().getSelectedGraph(), typeName, "visibility-mode: normal;");
					getGraphManager().updateNodeEdgesStyle(getGraphManager().getSelectedGraph(), typeName, "visibility-mode: normal;");
					//getGraphManager().hideNodes(typeName);
				}else{
					//getGraphManager().hideNodes(typeName);
					getGraphManager().updateNodesStyle(getGraphManager().getSelectedGraph(), typeName, "visibility-mode: hidden;");
					getGraphManager().updateNodeEdgesStyle(getGraphManager().getSelectedGraph(), typeName, "visibility-mode: hidden;");
				}
			}
		});
		chckbxVisible.setSelected(true);
		chckbxVisible.setBounds(10, 133, 97, 23);
		contentPane.add(chckbxVisible);
		/*
		GroupLayout gl_panel = new GroupLayout(contentPane);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 175, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 360, Short.MAX_VALUE)
		);
		contentPane.setLayout(gl_panel);
		*/
	}

	public String getTypeName() {
		return typeName;
	}

	public GraphManager getGraphManager() {
		return graphManager;
	}
}
