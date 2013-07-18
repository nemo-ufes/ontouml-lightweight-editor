package br.ufes.inf.nemo.ontouml.editor.ui.model;

import it.cnr.imaa.essi.lablib.gui.checkboxtree.CheckboxTree;
import it.cnr.imaa.essi.lablib.gui.checkboxtree.TreeCheckingModel;

import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * This class represents a project tree of our application. For now, it only shows
 * the OntoUML model (i.e., the instance of the OntoUML metamodel).
 * 
 * @author John
 *
 */
public class ProjectTree extends CheckboxTree {

	private static final long serialVersionUID = 1L;
	private int metamodelVersion; // Carraretto=1; Prime=2
	
	private DefaultTreeModel treeModel;
	private DefaultMutableTreeNode rootNode;
	private Toolkit toolkit = Toolkit.getDefaultToolkit();
	 
	public ProjectTree() {}
	
	/**
	 * Create a project tree based on OntoUML language's version.
	 * <br><br>
	 * Carraretto's is equal to 1. And the Prime version of the language is equal to 2.
	 * <br><br>
	 * 
	 * @param rootNode
	 */
	public ProjectTree (DefaultMutableTreeNode rootNode, int metamodelVersion)
	{
		super(rootNode);
				
		this.metamodelVersion = metamodelVersion;
		this.rootNode = rootNode;
				
		getCheckingModel().setCheckingMode(TreeCheckingModel.CheckingMode.PROPAGATE);
		
		// set cell renderer
		ProjectTreeCellRenderer cellRenderer = new ProjectTreeCellRenderer();
		setCellRenderer(cellRenderer);
		
		// drawing the tree based on Ecore Objects...
		drawTree(rootNode, getCheckingModel());	
		
		this.treeModel = new DefaultTreeModel(rootNode);
		setModel(treeModel);
		
		setShowsRootHandles(true);
		
		addCheckingPath(new TreePath(rootNode.getPath()));		
		expandPath(new TreePath(rootNode.getPath()));
		
		/** Right Click Mouse Listener */
		addMouseListener(new MouseAdapter()
	    {
	        public void mousePressed ( MouseEvent e )
	        {	        	
	            if (SwingUtilities.isRightMouseButton(e))
	            {
	            	TreePath path = getPathForLocation ( e.getX (), e.getY () );
	                Rectangle pathBounds = getUI().getPathBounds(ProjectTree.this, path);
	                if (pathBounds != null && pathBounds.contains(e.getX (),e.getY()))
	                {	                	
	                	doPopup(e);
	                }
	            }
	        }
	    });		
	}
	
	/**
	 * Show the Popup Menu
	 */
	public void doPopup (MouseEvent e)
	{
		/*TreePath path = */getPathForLocation(e.getX(), e.getY());
		ToolboxPopupMenu menu = new ToolboxPopupMenu();
	    menu.show(e.getComponent(), e.getX(), e.getY());	
	}
	
	/** 
	 * Remove all nodes except the root node. 
	 */
    public void clear() 
    {
        rootNode.removeAllChildren();
        treeModel.reload();
    }    
    
    /** Remove the currently selected node. */
    public void removeCurrentNode() 
    {
        TreePath currentSelection = getSelectionPath();
        if (currentSelection != null) 
        {
            DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode)(currentSelection.getLastPathComponent());
            MutableTreeNode parent = (MutableTreeNode)(currentNode.getParent());
            if (parent != null) 
            {
                treeModel.removeNodeFromParent(currentNode);
                return;
            }
        }  
        // Either there was no selection, or the root was selected.
        toolkit.beep();
    }
 
    /** Add child to the currently selected node. */
    public DefaultMutableTreeNode addObject(Object child) 
    {
        DefaultMutableTreeNode parentNode = null;
        TreePath parentPath = getSelectionPath(); 
        if (parentPath == null) 
        {
            parentNode = rootNode;
        } else {
            parentNode = (DefaultMutableTreeNode)(parentPath.getLastPathComponent());
        } 
        return addObject(parentNode, child, true);
    }
    
    public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent, Object child) 
    {
    	return addObject(parent, child, false);
    }
    
    public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent, Object child, boolean shouldBeVisible) 
    {
		DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);		
		
		if (parent == null) parent = rootNode;		
		
		//It is key to invoke this on the TreeModel, and NOT DefaultMutableTreeNode
		treeModel.insertNodeInto(childNode, parent, parent.getChildCount());
		
		//Make sure the user can see the lovely new node.
		if (shouldBeVisible) scrollPathToVisible(new TreePath(childNode.getPath()));
		
		return childNode;
    }
    
    /**
	 * Draw the Tree based on the OntoUML metamodel's version.
	 */	
	private void drawTree(DefaultMutableTreeNode parent, TreeCheckingModel checkingModel) 
	{		
		ElementAdapter eobject = (ElementAdapter)(parent.getUserObject());		
		EList<EObject> contents = eobject.getElement().eContents();
		
		for (EObject e : contents) 
		{			
			if (metamodelVersion==1) 
			{
				OntoReferenceElement elem = new OntoReferenceElement(e);

				// new node
				DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(elem);
				parent.add(newNode);
				
				drawTree(newNode, checkingModel);
			}
			else if (metamodelVersion==2) {
				
				OntoPrimeElement elem = new OntoPrimeElement(e);
				
				// new node
				DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(elem);				
				parent.add(newNode);
				
				drawTree(newNode, checkingModel);
			}
		}	
	}
		
}
