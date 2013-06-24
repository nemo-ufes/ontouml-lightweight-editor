package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Window.Type;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.common.resource.TypeName;

import obj.XGraph;
import obj.XMLFile;

import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class PropertiesWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	
	private Node node;
	private JTable table;
	private JTable table_1;
	private JTable table_2;

	/**
	 * Create the frame.
	 */
	public PropertiesWindow(int x, int y, String imagePath, Node nodeVar, XMLFile xmlFile, OntoUMLParser ontoUmlParser, String selectedWorld) {
		node = nodeVar;
		setUndecorated(true);  
		this.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);  
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(x, y, 205, 254);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel(" ");
		lblNewLabel.setIcon(new ImageIcon(imagePath));
		
		textField = new JTextField(node.getAttribute("ui.label").toString());
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				node.addAttribute("ui.label", textField.getText());
			}
		});
		textField.setColumns(10);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(2)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textField, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
					.addGap(18))
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE))
		);
		
		ArrayList<String> typeList = xmlFile.getAtomTypeOnWorld(node.getId(), selectedWorld);
		ArrayList<String> dataTypeList = xmlFile.getDataTypesOnWorld(node.getId(), selectedWorld);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Types", null, panel, null);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Stereotypes",
					"Types"
			}
		));
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.addRow(new Object[]{"<html><b>Stereotype</b></html>", "<html><b>Type</b></html>"});
		for(int i=0; i<typeList.size(); i++) {
			String stereoType = TypeName.getTypeName(ontoUmlParser.getElement(typeList.get(i)));
        	model.addRow(new Object[]{stereoType, typeList.get(i)});
		}
		table.setModel(model);
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(table, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(table, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Relations", null, panel_1, null);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"",
						""
				}
			));
			
		DefaultTableModel model_1 = (DefaultTableModel) table_1.getModel();
		//model.addRow(new Object[]{"<html><b>Stereotype</b></html>", "<html><b>Type</b></html>"});
		Iterator neighbors = node.getNeighborNodeIterator();
		while(neighbors.hasNext()) {
			Node n = (Node) neighbors.next();
			if(n.getId().contains("satOut")) {
				Edge relation = n.getEdge(1);
				Node planet = relation.getNode1().getEdge(1).getNode0();
				System.out.println(planet);
				model_1.addRow(new Object[]{relation.getId().substring(0, relation.getId().indexOf('$')), "<html><b>"+planet.getAttribute("ui.label")+"</b></html>"});
			}
			if(n.getId().contains("satIn")) {
				Edge relation = n.getEdge(0);
				Node planet = relation.getNode0().getEdge(0).getNode0();
				System.out.println("NODE:"+relation.getNode0().getAttribute("ui.label"));
				model_1.addRow(new Object[]{"<html><b>"+planet.getAttribute("ui.label")+"</b></html>", relation.getId().substring(0, relation.getId().indexOf('$'))});
			}
		}
		table_1.setModel(model_1);
		/*
		for(int i=0; i<typeList.size(); i++) {
			String stereoType = TypeName.getTypeName(ontoUmlParser.getElement(typeList.get(i)));
			model.addRow(new Object[]{stereoType, typeList.get(i)});
		}
		table.setModel(model);
		*/
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addComponent(table_1, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addComponent(table_1, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
		);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Attributes", null, panel_2, null);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Data Types", null, panel_3, null);
		
		table_2 = new JTable();
		table_2.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"DataType",
						"Value"
				}
			));
			
		DefaultTableModel model_2 = (DefaultTableModel) table_2.getModel();
			//model.addRow(new Object[]{"<html><b>Stereotype</b></html>", "<html><b>Type</b></html>"});
		//for(int i=1; i<typeList.size(); i++) {
		System.out.println("EHISSO>"+node.hasAttribute("attr"));
		String attrName = "attr$";
		int attrId = 0;
		while(node.hasAttribute(attrName + attrId)) {
			String attr = node.getAttribute(attrName + attrId);
			String attrLabel = attr.substring(0, attr.indexOf('\n'));
			String attrValue = attr.substring(attr.indexOf('\n') + 1);
			model_2.addRow(new Object[]{attrLabel, attrValue});
			attrId++;
		}
		
		//}
		table_2.setModel(model_2);
		
		
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addComponent(table_2, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addComponent(table_2, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
		);
		panel_3.setLayout(gl_panel_3);
		contentPane.setLayout(gl_contentPane);
		/*
		 * try {
			BufferedImage myPicture;
			myPicture = ImageIO.read(new File("path-to-file"));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			add(picLabel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 */
		
	}
}
