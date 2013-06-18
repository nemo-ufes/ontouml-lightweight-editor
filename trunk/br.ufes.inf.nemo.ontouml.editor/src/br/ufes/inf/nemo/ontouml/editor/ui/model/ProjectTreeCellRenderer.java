package br.ufes.inf.nemo.ontouml.editor.ui.model;

import it.cnr.imaa.essi.lablib.gui.checkboxtree.CheckboxTree;
import it.cnr.imaa.essi.lablib.gui.checkboxtree.CheckboxTreeCellRenderer;
import it.cnr.imaa.essi.lablib.gui.checkboxtree.TreeCheckingModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import br.ufes.inf.nemo.ontouml.editor.util.Resources;

public class ProjectTreeCellRenderer implements CheckboxTreeCellRenderer {

   	public JCheckBox checkbox = new JCheckBox();
   	public JPanel panel = new JPanel();   	
   	public JLabel opensymLabel = new JLabel();    	
   	public JLabel closesymLabel = new JLabel();
   	public JLabel typeLabel = new JLabel();
   	public JPanel stereotypePanel = new JPanel();
   	public JLabel nameLabel = new JLabel();
    	
	@Override
	public boolean isOnHotspot(int x, int y) 
	{
		return (checkbox.getBounds().contains(x, y));
	}
   	
	public ProjectTreeCellRenderer() 
	{
		super();    		
		
		opensymLabel.setFocusable(true);
		opensymLabel.setOpaque(true);
		opensymLabel.setText(" <<");
		opensymLabel.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 12));
		closesymLabel.setFocusable(true);
		closesymLabel.setOpaque(true);
		closesymLabel.setText(">> ");
		closesymLabel.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 12));
		typeLabel.setFocusable(true);
		typeLabel.setOpaque(true);	
		stereotypePanel = new JPanel();
		stereotypePanel.setLayout(new BorderLayout(0, 0));
		stereotypePanel.add(BorderLayout.WEST,opensymLabel);
		stereotypePanel.add(BorderLayout.CENTER,typeLabel);
		stereotypePanel.add(BorderLayout.EAST,closesymLabel);
		
		nameLabel.setFocusable(true);
		nameLabel.setOpaque(true);		
		panel.setLayout(new BorderLayout(0, 0));
		panel.add(BorderLayout.WEST,checkbox);
		panel.add(BorderLayout.CENTER,stereotypePanel);
		panel.add(BorderLayout.EAST,nameLabel);
		checkbox.setBackground(UIManager.getColor("Tree.textBackground"));    		
		panel.setBackground(UIManager.getColor("Tree.textBackground"));		    		
	}
       
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) 
	{		
		Object object = ((DefaultMutableTreeNode)value).getUserObject();
		ElementAdapter elemAdapt = ((ElementAdapter)object);
		if (object instanceof OntoReferenceElement)
		{		
			OntoReferenceElement ontoElem = (OntoReferenceElement)elemAdapt;
			
			typeLabel.setText(elemAdapt.getType());
			nameLabel.setText(ontoElem.getName()+" "+ontoElem.getCustomInfo());
			
			if (ontoElem.getType().equals("Package") || ontoElem.getType().equals("Model")) 
			{
				opensymLabel.setIcon(Resources.getIcon("tree.package.icon"));
			}
		}
		else if (object instanceof OntoPrimeElement)
		{
			OntoPrimeElement ontoElem = (OntoPrimeElement)elemAdapt;
			
			typeLabel.setText(elemAdapt.getType());
			nameLabel.setText(ontoElem.getName());
		}
		
		TreeCheckingModel checkingModel = ((CheckboxTree)tree).getCheckingModel();
	   	TreePath path = tree.getPathForRow(row);	   	
	   	
	   	boolean enabled = checkingModel.isPathEnabled(path);
	   	boolean checked = checkingModel.isPathChecked(path);
	   	boolean grayed = checkingModel.isPathGreyed(path);	
	   	
	   	checkbox.setEnabled(enabled);  	    
	   	
	   	if (selected)
	   	{
			opensymLabel.setBackground(UIManager.getColor("Tree.selectionBackground"));
			opensymLabel.setForeground(Color.white);
			closesymLabel.setBackground(UIManager.getColor("Tree.selectionBackground"));
			closesymLabel.setForeground(Color.white);
			typeLabel.setBackground(UIManager.getColor("Tree.selectionBackground"));
			typeLabel.setForeground(Color.white);
			nameLabel.setBackground(UIManager.getColor("Tree.selectionBackground"));
			nameLabel.setForeground(Color.white);
			
		}else{
			
			opensymLabel.setBackground(UIManager.getColor("Tree.textBackground"));
			closesymLabel.setBackground(UIManager.getColor("Tree.textBackground"));
			typeLabel.setBackground(UIManager.getColor("Tree.textBackground"));
			nameLabel.setBackground(UIManager.getColor("Tree.textBackground"));
			
			if (grayed) {
				opensymLabel.setForeground(Color.lightGray);
				closesymLabel.setForeground(Color.lightGray);
				typeLabel.setForeground(Color.lightGray);
				nameLabel.setForeground(Color.lightGray);
		   	} else {
		   		opensymLabel.setForeground(Color.black);
		   		closesymLabel.setForeground(Color.black);
				typeLabel.setForeground(Color.black);
				nameLabel.setForeground(Color.black);
		   	}	   	
		}
	   	
	   	checkbox.setSelected(checked);
	   	
	   	return panel;
	}
}
