package br.ufes.inf.nemo.instancevisualizer.gui;

import br.ufes.inf.nemo.instancevisualizer.graph.EdgeLegendManager;
import br.ufes.inf.nemo.instancevisualizer.graph.GraphManager;
import br.ufes.inf.nemo.instancevisualizer.graph.NodeLegendManager;
import br.ufes.inf.nemo.instancevisualizer.graph.NodeManager;

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

public class EdgeLegendPropertiesPanel extends JScrollPane {
	
	private String typeName;
	private GraphManager graphManager;
	private JCheckBox chckbxVisible;
	private String imagePath;
	
	public EdgeLegendPropertiesPanel(final String typeName, String stereotype, GraphManager graphManager) {
		super();
		this.typeName = typeName;
		this.graphManager = graphManager;
		
		JPanel contentPane = new JPanel();
		setViewportView(contentPane);
		
		JLabel label = new JLabel("");
		label.setBounds(10, 11, 32, 32);
		
		//String imagePath = graphManager.getEdgeManager().getEdgeLegendManager().getEdgeStereotype(typeName);
		EdgeLegendManager em = graphManager.getEdgeManager().getEdgeLegendManager();
		imagePath = graphManager.getEdgeManager().getEdgeLegendManager().getEdgeLegendWithStereotype(stereotype).getImagePath();
		if(em.getEdgeTypeLegend(typeName).getImagePath() != null)
			imagePath = em.getEdgeTypeLegend(typeName).getImagePath();
		label.setIcon(new ImageIcon(imagePath));
		
		JLabel lblNewLabel = new JLabel(typeName + "«" + graphManager.getXmlFile().getFieldStereotype(typeName) + "»");
		lblNewLabel.setBounds(52, 20, 109, 14);
		
		JButton btnEditStyle = new JButton("Edit Style");
		btnEditStyle.setBounds(10, 61, 88, 23);
		btnEditStyle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//new StyleWindow(imagePath, getGraphManager().getEdgeManager().getEdgeLegendManager().getEdgeTypeLegend(typeName).getStyle(), typeName, getGraphManager(), "edge");//Old(getTypeName(), getGraphManager(), false);
			}
		});
		contentPane.setLayout(null);
		contentPane.add(label);
		contentPane.add(lblNewLabel);
		contentPane.add(btnEditStyle);
		
		chckbxVisible = new JCheckBox("Visible");
		chckbxVisible.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//if(chckbxVisible.isSelected()) {
					getGraphManager().getEdgeManager().getEdgeLegendManager().setVisible(getTypeName(), chckbxVisible.isSelected());
					getGraphManager().update();
					///getGraphManager().updateEdgesStyle(getGraphManager().getSelectedGraph(), typeName, "visibility-mode: normal;");
				//}else{
					///getGraphManager().updateEdgesStyle(getGraphManager().getSelectedGraph(), typeName, "visibility-mode: hidden;");
				//}
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
	
	public String getImagePath() {
		return imagePath;
	}
}
