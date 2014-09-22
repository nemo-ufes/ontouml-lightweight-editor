package br.ufes.inf.nemo.instancevisualizer.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import br.ufes.inf.nemo.instancevisualizer.graph.EdgeLegend;
import br.ufes.inf.nemo.instancevisualizer.graph.EdgeLegendManager;
import br.ufes.inf.nemo.instancevisualizer.graph.GraphManager;
import br.ufes.inf.nemo.instancevisualizer.graph.NodeLegend;
import br.ufes.inf.nemo.instancevisualizer.graph.NodeLegendManager;

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
		
		final JPanel panel = new JPanel();
		
		
		//JPopupMenu popupMenu = new JPopupMenu();
		//addPopup(selectedWorld, popupMenu);
		
		NodeLegendManager nodeLegendManager = graphManager.getLegendManager();
		int i=0, j=0;
		Iterator iter = nodeLegendManager.getLegendIterator();
		//for(i=0; i<nodeLegendManager.getListSize(); i++) {
		
		while(iter.hasNext()) {
			final NodeLegend legend = (NodeLegend) iter.next();
			
			final String typeName = legend.getType();
			final JButton button = new JButton();
			final String imagePath = legend.getFillImage();
			
			final JLabel typeLabel = new JLabel(typeName);
			final JLabel stereotypeLabel = new JLabel("«"+legend.getStereotype()+"»");
			
			button.setIcon(new ImageIcon(imagePath));
			button.setBounds(8, 8+(36*(i)), 32, 32);
			
			JPopupMenu menu = new JPopupMenu("Test");
			menu.setBounds(8, 8+(32*(i)), 129, 129); 
			
			button.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                if (e.getButton() == MouseEvent.BUTTON1) {
	                	JPopupMenu popupMenu;
						try {
							popupMenu = new LegendEditorMenu(typeLabel, button, getGraphManager(), legend, null);
							panel.add(popupMenu);
		                    popupMenu.show(button, e.getX(), e.getY());
						} catch (NoSuchMethodException | SecurityException
								| IllegalAccessException
								| IllegalArgumentException
								| InvocationTargetException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	                }
	            }
	        });

			
			
			
			typeLabel.setBounds(48, 22+(36*(i)), 256, 16);
			stereotypeLabel.setBounds(48, 10+(36*(i)), 256, 16);
			
			panel.add(typeLabel);
			panel.add(stereotypeLabel);
			
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
			button.setBounds(8, 8+(36*(i) + 36*(j+1)), 32, 32);
			
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(!(getGraphManager().getMainWindow().getTabbedPane().getTabCount() == 1)) {
						getGraphManager().getMainWindow().getTabbedPane().removeTabAt(1);
        			}
					getGraphManager().getMainWindow().getTabbedPane().addTab(type, new EdgeLegendPropertiesPanel(type, stereotype, getGraphManager()));
					getGraphManager().getMainWindow().getTabbedPane().setSelectedIndex(getGraphManager().getMainWindow().getTabbedPane().getTabCount()-1);
					
				}
			});
			
			JLabel typeLabel = new JLabel(type);
			JLabel stereotypeLabel = new JLabel("«" + graphManager.getXmlFile().getFieldStereotype(type) + "»");
			
			//typeLabel.setBounds(48, 16+(32*(i) + 32*(j+1)), 256, 16);
			typeLabel.setBounds(48, 22+(36*(i) + 36*(j+1)), 256, 16);
			stereotypeLabel.setBounds(48, 10+(36*(i) + 36*(j+1)), 256, 16);
			
			panel.add(typeLabel);
			panel.add(stereotypeLabel);
			panel.add(button);
			j++;
		}
		
		scrollPane.setViewportView(panel);
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 100, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 8+(36*(i) + 36*(j+1)), Short.MAX_VALUE)
		);
		panel.setLayout(gl_panel);
	}
	
	public GraphManager getGraphManager() {
		return graphManager;
	}
	/*
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}*/
}
