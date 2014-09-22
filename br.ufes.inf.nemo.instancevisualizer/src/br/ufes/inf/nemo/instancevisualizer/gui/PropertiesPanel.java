package br.ufes.inf.nemo.instancevisualizer.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;

import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.common.resource.TypeName;
import br.ufes.inf.nemo.instancevisualizer.graph.DataType;
import br.ufes.inf.nemo.instancevisualizer.graph.EdgeManager;
import br.ufes.inf.nemo.instancevisualizer.graph.NodeManager;
import br.ufes.inf.nemo.instancevisualizer.xml.XMLFile;

public class PropertiesPanel extends JScrollPane {

	private JTextField textField;
	
	private Node node;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JButton btnTypes;
	private JButton btnRelations;
	private JButton btnDataTypes;
	private String selectedWorld;
	
	private NodeManager nodeManager;
	private EdgeManager edgeManager;

	/**
	 * Create the frame.
	 */
	public PropertiesPanel(String imagePath, Node nodeVar, XMLFile xmlFile, OntoUMLParser ontoUmlParser, String selectedWorldVar, NodeManager nodeManagerVar, EdgeManager edgeManagerVar) {
		super();
		selectedWorld = selectedWorldVar;
		nodeManager = nodeManagerVar;
		edgeManager = edgeManagerVar;
		
		node = nodeVar;
		setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JPanel contentPane = new JPanel();
		this.setViewportView(contentPane);
		JLabel lblNewLabel = new JLabel(" ");
		lblNewLabel.setIcon(new ImageIcon(imagePath));
		
		textField = new JTextField(node.getAttribute("ui.label").toString());
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				node.addAttribute("ui.label", textField.getText());
				nodeManager.getNode(node.getId()).addAttribute(node.getId(), "ui.label", textField.getText());
			}
		});
		textField.setColumns(10);
		
		ArrayList<String> typeList = xmlFile.getAtomTypeOnWorld(node.getId(), selectedWorld);
		ArrayList<String> dataTypeList = xmlFile.getDataTypesOnWorld(node.getId(), selectedWorld);
		
		table = new JTable();
		contentPane.add(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"column0",
					"column1"
			}
		));
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		// Getting types and stereotypes:
		for(int i=0; i<typeList.size(); i++) {
			String stereoType = TypeName.getTypeName(ontoUmlParser.getElement(typeList.get(i)));
        	model.addRow(new Object[]{stereoType, typeList.get(i)});
		}
		table.setModel(model);
		
		table_1 = new JTable();
		contentPane.add(table_1);
		table_1.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"column0",
						"column1"
				}
			));
		DefaultTableModel model_1 = (DefaultTableModel) table_1.getModel();
		// Getting relations:
		Iterator neighbors = node.getNeighborNodeIterator();
		boolean gonnaBreak = false;
		while(neighbors.hasNext()) {
			Node neighbor = (Node) neighbors.next();
			if(!neighbor.getId().contains(node.getId() + "_sat$")) {
				neighbor = node;
				gonnaBreak = true;
			}
			
			Iterator neighbors2 = neighbor.getEdgeIterator();
			while(neighbors2.hasNext()) {
				Edge edge = (Edge) neighbors2.next();
				//System.out.println(edge.getId());
				if(edge.getNode0().equals(neighbor)) {	// edge outcoming
					Node nodeOut = edge.getNode1();
					if(nodeOut.getId().contains("_sat$")) {
						Iterator findOrbit = nodeOut.getEdgeIterator();
						Edge orbit = null;
						while(findOrbit.hasNext()) {
							orbit = (Edge) findOrbit.next();
							if(orbit.getId().contains("_orbit$")) {
								break;
							}
						}
						nodeOut = orbit.getNode0();
					}
					model_1.addRow(new Object[]{edge.getId().substring(0, edge.getId().indexOf('$')), "<html><b>" + nodeOut.getAttribute("ui.label")+"</b></html>"});
					
				}else{	// edge incoming
					Node nodeIn = edge.getNode0();
					if(!nodeIn.equals(node)) {
						if(nodeIn.getId().contains("_sat$")) {
							Iterator findOrbit = nodeIn.getEdgeIterator();
							Edge orbit = null;
							while(findOrbit.hasNext()) {
								orbit = (Edge) findOrbit.next();
								if(orbit.getId().contains("_orbit$")) {
									break;
								}
							}
							nodeIn = orbit.getNode0();
							
						}
						model_1.addRow(new Object[]{"<html><b>"+nodeIn.getAttribute("ui.label")+"</b></html>", edge.getId().substring(0, edge.getId().indexOf('$'))});
						
					}
				}
			}
			if(gonnaBreak) {
				break;
			}
			
			
		}
		
		table_2 = new JTable();
		contentPane.add(table_2);
		table_2.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"column0",
						"column1"
				}
			));
		DefaultTableModel model_2 = (DefaultTableModel) table_2.getModel();
		
		// Getting datatypes
		Iterator<DataType> dts = nodeManager.getNode(node.getId()).getDataTypeIterator(selectedWorld);
		while(dts.hasNext()) {
			DataType dt = dts.next();
			model_2.addRow(new Object[]{dt.getName(), dt.getValue()});
		}
		
		
		btnTypes = new JButton("Types -");
		btnTypes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(btnTypes.getText() == "Types -") {
					table.setVisible(false);
					btnTypes.setText("Types +");
				}else{
					table.setVisible(true);
					btnTypes.setText("Types -");
				}
			}
		});
		
		btnRelations = new JButton("Relations -");
		btnRelations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(btnRelations.getText() == "Relations -") {
					table_1.setVisible(false);
					btnRelations.setText("Relations +");
				}else{
					table_1.setVisible(true);
					btnRelations.setText("Relations -");
				}
			}
		});
		
		btnDataTypes = new JButton("Data Types -");
		btnDataTypes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(btnDataTypes.getText() == "Data Types -") {
					table_2.setVisible(false);
					btnDataTypes.setText("Data Types +");
				}else{
					table_2.setVisible(true);
					btnDataTypes.setText("Data Types -");
				}
			}
		});
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textField, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE))
				.addComponent(table, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(btnTypes)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(btnRelations)
					.addContainerGap())
				.addComponent(table_1, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
				.addComponent(table_2, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(btnDataTypes)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(4)
					.addComponent(btnTypes)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(table, GroupLayout.PREFERRED_SIZE, 16*table.getRowCount(), GroupLayout.PREFERRED_SIZE)
					.addGap(8)
					.addComponent(btnRelations)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(table_1, GroupLayout.PREFERRED_SIZE, 16*table_1.getRowCount(), GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnDataTypes)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(table_2, GroupLayout.PREFERRED_SIZE, 16*table_2.getRowCount(), GroupLayout.PREFERRED_SIZE)
					.addContainerGap(64, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
