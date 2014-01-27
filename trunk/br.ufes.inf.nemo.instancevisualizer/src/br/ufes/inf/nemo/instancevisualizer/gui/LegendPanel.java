package br.ufes.inf.nemo.instancevisualizer.gui;

import br.ufes.inf.nemo.instancevisualizer.graph.EdgeLegendManager;
import br.ufes.inf.nemo.instancevisualizer.graph.EdgeLegend;
import br.ufes.inf.nemo.instancevisualizer.graph.GraphManager;
import br.ufes.inf.nemo.instancevisualizer.graph.NodeLegend;
import br.ufes.inf.nemo.instancevisualizer.graph.NodeLegendManager;
import br.ufes.inf.nemo.instancevisualizer.graph.NodeManager;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.common.resource.TypeName;

public class LegendPanel extends JPanel {

	GraphManager graphManager;
	
	public LegendPanel(GraphManager graphManager) {
		super();
		this.graphManager = graphManager;
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(this);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
		);
		this.setLayout(gl_contentPane);
		
		JPanel panel = new JPanel();

		NodeLegendManager nodeLegendManager = graphManager.getLegendManager();
		int i=0, j=0;
		Iterator iter = nodeLegendManager.getLegendIterator();
		//for(i=0; i<nodeLegendManager.getListSize(); i++) {
		while(iter.hasNext()) {
			NodeLegend legend = (NodeLegend) iter.next();
			JButton button = new JButton();
			final String imagePath = legend.getImagePath();
			button.setIcon(new ImageIcon(imagePath));
			//System.out.println(imagePath);
			button.setBounds(8, 8+(32*(i)), 32, 32);
			
			final String typeName = legend.getType();
			
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(!(getGraphManager().getMainWindow().getTabbedPane().getTabCount() == 1)) {
						getGraphManager().getMainWindow().getTabbedPane().removeTabAt(1);
        			}
					getGraphManager().getMainWindow().getTabbedPane().addTab(typeName, new NodeLegendPropertiesPanel(typeName, getGraphManager()));
					getGraphManager().getMainWindow().getTabbedPane().setSelectedIndex(getGraphManager().getMainWindow().getTabbedPane().getTabCount()-1);
					/*
					JFrame legendPropertiesWindow = new JFrame();
					legendPropertiesWindow.setTitle(typeName + "«" + getGraphManager().getLegendManager().getStereoType(typeName) + "»");
					legendPropertiesWindow.setContentPane(new LegendPropertiesPanel(typeName, getGraphManager()));
					legendPropertiesWindow.setVisible(true);
					legendPropertiesWindow.setBounds(0, 50, 180, 360);
					*/
				}
			});
			
			JLabel label = new JLabel(typeName + "«" + legend.getStereotype() + "»");
			label.setBounds(48, 16+(32*(i)), 256, 16);
			
			panel.add(label);
			panel.add(button);
			i++;
		}
		
		EdgeLegendManager edgeLegendManager = graphManager.getEdgeManager().getEdgeLegendManager();
		iter = edgeLegendManager.getLegendIterator();
		while(iter.hasNext()) {
			EdgeLegend legend = (EdgeLegend) iter.next();
			JButton button = new JButton();
			final String type = legend.getType();
			final String stereotype = graphManager.getXmlFile().getFieldStereotype(type);
			//final String imagePath = edgeTypeLegend.getImagePath();
			String imagePath = legend.getImagePath();
			button.setIcon(new ImageIcon(imagePath));
			//System.out.println(imagePath);
			button.setBounds(8, 8+(32*(i) + 32*(j+1)), 32, 32);
			
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(!(getGraphManager().getMainWindow().getTabbedPane().getTabCount() == 1)) {
						getGraphManager().getMainWindow().getTabbedPane().removeTabAt(1);
        			}
					getGraphManager().getMainWindow().getTabbedPane().addTab(type, new EdgeLegendPropertiesPanel(type, stereotype, getGraphManager()));
					getGraphManager().getMainWindow().getTabbedPane().setSelectedIndex(getGraphManager().getMainWindow().getTabbedPane().getTabCount()-1);
					
				}
			});
			
			JLabel label = new JLabel(type + "«" + graphManager.getXmlFile().getFieldStereotype(type) + "»");
			label.setBounds(48, 16+(32*(i) + 32*(j+1)), 256, 16);
			
			panel.add(label);
			panel.add(button);
			j++;
		}
		
		scrollPane.setViewportView(panel);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 360, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 8+(32*(i) + 32*(j+1)), Short.MAX_VALUE)
		);
		panel.setLayout(gl_panel);
	}
	
	public GraphManager getGraphManager() {
		return graphManager;
	}
}
