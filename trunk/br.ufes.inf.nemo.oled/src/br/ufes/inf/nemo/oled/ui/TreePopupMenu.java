package br.ufes.inf.nemo.oled.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class TreePopupMenu extends JPopupMenu {
 
	private static final long serialVersionUID = 1L;
		
	public JMenuItem deleteItem = new JMenuItem("Delete");
	public JMenuItem autoCompleteItem = new JMenuItem("Complete Selection...");
	public JMenuItem refreshItem = new JMenuItem("Refresh");
	
    public TreePopupMenu(final AppFrame frame, final OntoUMLTree tree)
    {           	
    	if (((DefaultMutableTreeNode)tree.getSelectionPath().getLastPathComponent()).isRoot()){
	    	add(autoCompleteItem);
	    	addSeparator();
	    	
	    	autoCompleteItem.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					frame.getDiagramManager().getEditorDispatcher().autoComplete();
				}
			});
	    }
    	
    	add(refreshItem);
    	addSeparator();
    	
    	refreshItem.addActionListener(new ActionListener() {				
			@Override
			public void actionPerformed(ActionEvent e) {
				//FIXME every modification creates a new tree
				ModelTree.updateModelTree(frame.getDiagramManager().getCurrentProject());
			}
		});
    	
    	add(deleteItem);
    	deleteItem.setIcon(new ImageIcon(TreePopupMenu.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/delete.png")));
    	    	
    	deleteItem.addActionListener(new ActionListener() {				
			@Override
			public void actionPerformed(ActionEvent e) {			
												
				OntoUMLElement ontoElem = (OntoUMLElement) ((DefaultMutableTreeNode)tree.getSelectionPath().getLastPathComponent()).getUserObject();
				frame.getDiagramManager().deleteElementOfProject((RefOntoUML.Element)ontoElem.getElement());
												
				// FIXME every modification creates a new tree
				ModelTree.updateModelTree(frame.getDiagramManager().getCurrentProject());
				
				tree.setSelectionPath(new TreePath(tree.getRootNode().getPath()));
			}
		});
    }
    
    
}
