package br.ufes.inf.nemo.ontouml.editor.ui.model;

import it.cnr.imaa.essi.lablib.gui.checkboxtree.CheckboxTree;
import it.cnr.imaa.essi.lablib.gui.checkboxtree.CheckboxTreeCellRenderer;
import it.cnr.imaa.essi.lablib.gui.checkboxtree.TreeCheckingModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.TreePath;

public class ProjectTreeCellRenderer implements CheckboxTreeCellRenderer {

   	public JCheckBox checkbox = new JCheckBox();
   	public JPanel panel = new JPanel();
   	public JLabel label = new JLabel();    	
    	
	@Override
	public boolean isOnHotspot(int x, int y) 
	{
		return (checkbox.getBounds().contains(x, y));
	}
   	
	public ProjectTreeCellRenderer() 
	{
		super();    		
		label.setFocusable(true);
		label.setOpaque(true);		  		 
		panel.setLayout(new BorderLayout(0, 0));
		panel.add(BorderLayout.WEST,checkbox);
		panel.add(BorderLayout.CENTER,label);		    		
		checkbox.setBackground(UIManager.getColor("Tree.textBackground"));    		
		panel.setBackground(UIManager.getColor("Tree.textBackground"));		    		
	}
       
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) 
	{		
		label.setText(value.toString());	
		
		TreeCheckingModel checkingModel = ((CheckboxTree)tree).getCheckingModel();
	   	TreePath path = tree.getPathForRow(row);	   	
	   	
	   	boolean enabled = checkingModel.isPathEnabled(path);
	   	boolean checked = checkingModel.isPathChecked(path);
	   	boolean grayed = checkingModel.isPathGreyed(path);	
	   	
	   	checkbox.setEnabled(enabled);	   	    

	   	if (selected){
			label.setBackground(UIManager.getColor("Tree.selectionBackground"));
			label.setForeground(Color.white);
		}else{
			label.setBackground(UIManager.getColor("Tree.textBackground"));
			
			if (grayed) {
		   		label.setForeground(Color.lightGray);
		   	} else {
		   		label.setForeground(Color.black);
		   	}	   	
		}
	   	
	   	checkbox.setSelected(checked);
	   	
	   	return panel;
	}
}
