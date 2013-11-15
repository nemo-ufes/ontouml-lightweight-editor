package gui;

import graph.GraphManager;
import graph.NodeLegendManager;
import graph.NodeManager;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import RefOntoUML.Classifier;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

import xml.XMLFile;

public class StyleWindowOld extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;
	private boolean mode;
	
	private String typeName;
	private GraphManager graphManager;
	
	/**
	 * Create the frame.
	 */
	/*
	public StyleWindowOld(String typeName, GraphManager graphManager, boolean modeVar) {
		this.typeName = typeName;
		this.graphManager = graphManager;
		this.mode = modeVar;
		XMLFile xmlFile = graphManager.getXmlFile();
		
		if(true) {
			setTitle(typeName + "«" + getGraphManager().getLegendManager().getStereoType(typeName) + "»" + " style editor");
		}else{
			setTitle(typeName + "«" + getGraphManager().getEdgeManager().getEdgeLegendManager().getStereotype(typeName) + "»" + " style editor");
		}
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 450, 300);
			setVisible(true);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			
			JButton btnOk = new JButton("Apply");
			btnOk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(mode) {
						getGraphManager().updateNodesStyle(getGraphManager().getSelectedGraph(), getTypeName(), getTextAreaText());
						getGraphManager().getLegendManager().updateTypeStyle(getTypeName(), getTextAreaText());
					}else{
						getGraphManager().updateEdgesStyle(getGraphManager().getSelectedGraph(), getTypeName(), getTextAreaText());
						getGraphManager().getEdgeManager().getEdgeLegendManager().updateTypeStyle(getTypeName(), getTextAreaText());
					}
				}
			});
			
			JButton btnCancel = new JButton("Cancel");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
					setVisible(false);
				}
			});
			
			textArea = new JTextArea(graphManager.getLegendManager().getTypeStyle(typeName));
			
			JButton btnOk_1 = new JButton("OK");
			btnOk_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(mode) {
						getGraphManager().updateNodesStyle(getGraphManager().getSelectedGraph(), getTypeName(), getTextAreaText());
						getGraphManager().getLegendManager().updateTypeStyle(getTypeName(), getTextAreaText());
					}else{
						getGraphManager().updateEdgesStyle(getGraphManager().getSelectedGraph(), getTypeName(), getTextAreaText());
						getGraphManager().getEdgeManager().getEdgeLegendManager().updateTypeStyle(getTypeName(), getTextAreaText());
					}
					dispose();
				}
			});
			
			GroupLayout gl_contentPane = new GroupLayout(contentPane);
			gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap(61, Short.MAX_VALUE)
						.addComponent(btnOk_1)
						.addGap(18)
						.addComponent(btnCancel)
						.addGap(18)
						.addComponent(btnOk)
						.addGap(114))
					.addComponent(textArea, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
			);
			gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnCancel)
							.addComponent(btnOk)
							.addComponent(btnOk_1))
						.addGap(5))
			);
			contentPane.setLayout(gl_contentPane);
	}

	public boolean isMode() {
		return mode;
	}
	
	public GraphManager getGraphManager() {
		return graphManager;
	}
	
	public String getTextAreaText() {
		return textArea.getText();
	}
	
	public String getTypeName() {
		return typeName;
	}*/
}
