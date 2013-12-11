package br.ufes.inf.nemo.oled.ui.popup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import br.ufes.inf.nemo.oled.ui.AppFrame;
import br.ufes.inf.nemo.oled.ui.OntoUMLElement;
import br.ufes.inf.nemo.oled.ui.ProjectBrowser;
import br.ufes.inf.nemo.oled.ui.ProjectTree;
import br.ufes.inf.nemo.oled.umldraw.structure.StructureDiagram;

public class TreePopupMenu extends JPopupMenu {
 
	private static final long serialVersionUID = 1L;
		
	public JMenuItem deleteItem = new JMenuItem("Delete");
	public JMenuItem autoCompleteItem = new JMenuItem("Complete Selection...");
	public JMenuItem refreshItem = new JMenuItem("Refresh");
	public JMenuItem addDiagramItem = new JMenuItem("Add Diagram");
	
	public Object element;
	
    public TreePopupMenu(final AppFrame frame, final ProjectTree tree, Object element)
    {       
    	this.element = element;    	
    	DefaultMutableTreeNode node = ((DefaultMutableTreeNode)tree.getSelectionPath().getLastPathComponent());
    	
    	// Auto Complete Selection
    	if (tree.getModelRootNode().equals(node)){
	    	add(autoCompleteItem);	    	
	    	autoCompleteItem.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					frame.getDiagramManager().getEditorDispatcher().autoComplete();
				}
			});
	    }
    	
    	// Refresh Tree
    	if (tree.getRootNode().equals(node)){
    		add(refreshItem);
    		refreshItem.addActionListener(new ActionListener() {				
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				//FIXME every modification creates a new tree
    				ProjectBrowser.rebuildTree(frame.getDiagramManager().getCurrentProject());
    			}
    		});
    	}    	
    	
    	//Add Diagram
    	if(tree.getDiagramRootNode().equals(node)){
    		add(addDiagramItem);
    		addDiagramItem.addActionListener(new ActionListener() {				
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				frame.getDiagramManager().newDiagram();    				
    			}
    		});
    	}
    	
    	// Delete 
    	if (!tree.getModelRootNode().equals(node) && !tree.getDiagramRootNode().equals(node) && !tree.getRootNode().equals(node)){    		
    		add(deleteItem);
    		deleteItem.setIcon(new ImageIcon(TreePopupMenu.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/delete.png")));
    		deleteItem.addActionListener(new ActionListener() {				
    			@Override
    			public void actionPerformed(ActionEvent e) {			
    							
    				if (TreePopupMenu.this.element instanceof OntoUMLElement)
    				{
    					OntoUMLElement ontoElem = (OntoUMLElement) ((DefaultMutableTreeNode)tree.getSelectionPath().getLastPathComponent()).getUserObject();
    					frame.getDiagramManager().deleteElementOfProject((RefOntoUML.Element)ontoElem.getElement());													
    					// FIXME every modification creates a new tree
    					ProjectBrowser.rebuildTree(frame.getDiagramManager().getCurrentProject());					
    					tree.setSelectionPath(new TreePath(tree.getModelRootNode().getPath()));
    				}else if (TreePopupMenu.this.element instanceof StructureDiagram)
    				{
    					frame.getDiagramManager().removeDiagram((StructureDiagram)TreePopupMenu.this.element);
    					// FIXME every modification creates a new tree
    					ProjectBrowser.rebuildTree(frame.getDiagramManager().getCurrentProject());
    				}
    			}
    		});
    	}   	    	
    	
    }
    
    
}
