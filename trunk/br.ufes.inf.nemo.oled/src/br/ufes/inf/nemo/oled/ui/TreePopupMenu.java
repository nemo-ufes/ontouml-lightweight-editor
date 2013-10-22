package br.ufes.inf.nemo.oled.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;

public class TreePopupMenu extends JPopupMenu {
 
	private static final long serialVersionUID = 1L;
		
	public JMenuItem deleteItem = new JMenuItem("Delete");
	public JMenuItem autoCompleteItem = new JMenuItem("Complete selection...");
	
    public TreePopupMenu(final AppFrame frame, OntoUMLTree tree)
    {           	
    	if (((DefaultMutableTreeNode)tree.getSelectionPath().getLastPathComponent()).isRoot()){
	    	add(autoCompleteItem);
	    	//autoCompleteItem.setIcon(new ImageIcon(TreePopupMenu.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/completion.png")));
	    	addSeparator();
	    	
	    	autoCompleteItem.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					frame.getDiagramManager().getEditorDispatcher().autoComplete();
				}
			});
	    }
    	
    	add(deleteItem);
    	deleteItem.setIcon(new ImageIcon(TreePopupMenu.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/delete.png")));
    	deleteItem.setEnabled(false);
    }
}
