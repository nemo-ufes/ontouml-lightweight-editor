package gui;
import graph.EdgeLegendManager;
import graph.GraphManager;
import graph.NodeLegendManager;
import graph.NodeManager;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		EdgeLegendManager edgeLegendManager = graphManager.getEdgeManager().getEdgeLegendManager();
		int i, j;
		for(i=0; i<nodeLegendManager.getListSize(); i++) {
			JButton button = new JButton();
			final String imagePath = nodeLegendManager.getTypeImage(i);
			button.setIcon(new ImageIcon(imagePath));
			System.out.println(imagePath);
			button.setBounds(8, 8+(32*(i)), 32, 32);
			
			final String typeName = nodeLegendManager.getType(i);
			
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
			
			JLabel label = new JLabel(typeName + "«" + nodeLegendManager.getStereoType(typeName) + "»");
			label.setBounds(48, 16+(32*(i)), 256, 16);
			
			panel.add(label);
			panel.add(button);
		}
		
		for(j=0; j<edgeLegendManager.getListSize(); j++) {
			JButton button = new JButton();
			final String imagePath = edgeLegendManager.getTypeImage(j);
			button.setIcon(new ImageIcon(imagePath));
			System.out.println(imagePath);
			button.setBounds(8, 8+(32*(i) + 32*(j+1)), 32, 32);
			
			final String typeName = edgeLegendManager.getType(j);
			
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(!(getGraphManager().getMainWindow().getTabbedPane().getTabCount() == 1)) {
						getGraphManager().getMainWindow().getTabbedPane().removeTabAt(1);
        			}
					getGraphManager().getMainWindow().getTabbedPane().addTab(typeName, new EdgeLegendPropertiesPanel(typeName, getGraphManager()));
					getGraphManager().getMainWindow().getTabbedPane().setSelectedIndex(getGraphManager().getMainWindow().getTabbedPane().getTabCount()-1);
					
				}
			});
			
			JLabel label = new JLabel(typeName + "«" + edgeLegendManager.getStereoType(typeName) + "»");
			label.setBounds(48, 16+(32*(i) + 32*(j+1)), 256, 16);
			
			panel.add(label);
			panel.add(button);
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
