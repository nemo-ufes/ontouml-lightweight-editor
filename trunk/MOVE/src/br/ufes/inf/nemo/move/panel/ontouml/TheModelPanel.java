package br.ufes.inf.nemo.move.panel.ontouml;

import it.cnr.imaa.essi.lablib.gui.checkboxtree.CheckboxTree;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import org.eclipse.emf.ecore.EObject;

/**
 * @author John Guerson
 */

public class TheModelPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JTextField txtOntoumlModelTree;	
	private JScrollPane scrollPane;
	private JPanel treePanel;
	private static CheckboxTree modeltree;	
	private JPanel endmodeltree; 
	private JPanel beginmodeltree;	
	private JTextPane txtpnCheckBelow;
		
	/**
	 * Set Model Tree.
	 * 
	 * @param refmodel
	 */
	public void setModelTree (RefOntoUML.Package refmodel)
	{
		modeltree = OntoUMLCheckBoxTree.createCheckBoxTree(refmodel);
		modeltree.expandAll();
		
		treePanel.add(BorderLayout.CENTER,modeltree);					
	}
	
	/**
	 * Get Unchecked Elements
	 */
	public static List<EObject> getUncheckedElements()
	{
		return OntoUMLCheckBoxTree.getUncheckedElements(modeltree);
	}
	
	/**
	 * Create the panel.
	 */
	public TheModelPanel() 
	{
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setLayout(new BorderLayout(0, 0));
		
		txtOntoumlModelTree = new JTextField();
		txtOntoumlModelTree.setHorizontalAlignment(SwingConstants.CENTER);
		txtOntoumlModelTree.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtOntoumlModelTree.setForeground(SystemColor.window);
		txtOntoumlModelTree.setText("OntoUML Model");
		txtOntoumlModelTree.setEditable(false);
		txtOntoumlModelTree.setBackground(Color.BLACK);
		txtOntoumlModelTree.setColumns(10);
		txtOntoumlModelTree.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		treePanel = new JPanel();
		treePanel.setBackground(Color.WHITE);
		treePanel.setLayout(new BorderLayout(0, 0));
		treePanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new java.awt.Dimension(400, 360));
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);		
		scrollPane.setViewportView(treePanel);
						
		endmodeltree = new JPanel();
		endmodeltree.setBackground(Color.WHITE);
		endmodeltree.setPreferredSize(new Dimension(300, 50));
						
		beginmodeltree = new JPanel();
		beginmodeltree.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		beginmodeltree.setPreferredSize(new Dimension(280, 95));
		
		treePanel.add(BorderLayout.NORTH,beginmodeltree);
		
		txtpnCheckBelow = new JTextPane();
		txtpnCheckBelow.setEditable(false);
		txtpnCheckBelow.setBackground(UIManager.getColor("TextPane.disabledBackground"));
		txtpnCheckBelow.setText("Select below which elements do you want to simulate.\r\n\r\nYou should know the implications of unchecking a specific element. If not, please select all the elements by clicking on the root element.\r\n");
		txtpnCheckBelow.setPreferredSize(new Dimension(280, 80));
		
		GroupLayout gl_beginmodeltree = new GroupLayout(beginmodeltree);
		gl_beginmodeltree.setHorizontalGroup(
			gl_beginmodeltree.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_beginmodeltree.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtpnCheckBelow, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_beginmodeltree.setVerticalGroup(
			gl_beginmodeltree.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_beginmodeltree.createSequentialGroup()
					.addGap(6)
					.addComponent(txtpnCheckBelow, GroupLayout.PREFERRED_SIZE, 69, Short.MAX_VALUE)
					.addContainerGap())
		);
		beginmodeltree.setLayout(gl_beginmodeltree);
		
		treePanel.add(BorderLayout.SOUTH,endmodeltree);
		
		add(txtOntoumlModelTree, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);		
	}
}
