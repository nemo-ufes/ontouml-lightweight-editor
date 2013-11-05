package br.ufes.inf.nemo.oled.ui;

import it.cnr.imaa.essi.lablib.gui.checkboxtree.CheckboxTree;
import it.cnr.imaa.essi.lablib.gui.checkboxtree.TreeCheckingModel;

import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Association;
import RefOntoUML.Package;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class OntoUMLTree extends CheckboxTree {

	private static final long serialVersionUID = 1L;
	public DefaultMutableTreeNode rootNode;
	private DefaultTreeModel treeModel;
	private Toolkit toolkit = Toolkit.getDefaultToolkit();
	public AppFrame frame;
	
	/**
	 * Constructor.
	 * 
	 * Call the constructor like this:
	 * 
	 * DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(new OntoUMLElem(refmodel,""))
	 * OntoUMLCheckBoxTree tree = new OntoUMLCheckBoxTree(rootNode,refmodel,refparser);
	 * 
	 * @param refmodel
	 * @param refparser
	 */
	public OntoUMLTree (AppFrame frame, DefaultMutableTreeNode rootNode, RefOntoUML.Package refmodel, OntoUMLParser refparser)
	{
		super(rootNode);
		
		this.frame = frame;
		this.rootNode = rootNode;
		this.treeModel = new DefaultTreeModel(rootNode);
		setModel(treeModel);
		
		getCheckingModel().setCheckingMode(TreeCheckingModel.CheckingMode.PROPAGATE);
		
		OntoUMLTreeCellRenderer ontoCellRenderer = new OntoUMLTreeCellRenderer();
		setCellRenderer(ontoCellRenderer);
				
		TreeCheckingModel checkingModel = getCheckingModel();
		
		drawTree(rootNode, refmodel,checkingModel, refparser);	
		
		addCheckingPath(new TreePath(rootNode.getPath()));		
		expandPath(new TreePath(rootNode.getPath()));
		
		/** Right Click Mouse Listener */
		addMouseListener(new MouseAdapter()
	    {
	        public void mouseClicked ( MouseEvent e )
	        {	        	
	            if (SwingUtilities.isRightMouseButton(e))
	            {	            	
	            	TreePath path = getPathForLocation ( e.getX (), e.getY () );
	            	setSelectionPath(path);
	                Rectangle pathBounds = getUI().getPathBounds(OntoUMLTree.this, path);
	                if (pathBounds != null && pathBounds.contains(e.getX (),e.getY()))
	                {	                	
	                	doPopup(e,OntoUMLTree.this);
	                }	                
	            }
	        }
	    });	
	
	}	
	
	/** 
	 * Remove all nodes except the root node. 
	 */
    public void clear() 
    {
        rootNode.removeAllChildren();
        treeModel.reload();
    }    
    	
	/**
	 * Show the Popup Menu
	 */
	public void doPopup (MouseEvent e, OntoUMLTree tree)
	{
		/*TreePath path = */getPathForLocation(e.getX(), e.getY());
		TreePopupMenu menu = new TreePopupMenu(frame, tree);
	    menu.show(e.getComponent(), e.getX(), e.getY());	
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
    
	public DefaultMutableTreeNode getRootNode() {
		return rootNode;
	}

	/**
	 * Auxiliary function. It runs the Elements from the model creating the tree nodes.
	 */	
	private void drawTree(DefaultMutableTreeNode parent, EObject refElement,TreeCheckingModel checkingModel,OntoUMLParser refparser) 
	{
		/* Model */
		if (refElement instanceof RefOntoUML.Model) 
		{
			EList<EObject> contents = refElement.eContents();
			for (EObject eobj : contents) 
			{
				drawTree(parent, (RefOntoUML.Element) eobj,checkingModel,refparser);
			}
			
		/* Package */
		} else if (refElement instanceof RefOntoUML.Package) 
		{
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(new OntoUMLElement(refElement,""));
			parent.add(newNode);
			
			EList<EObject> contents = refElement.eContents();
			for (EObject eobj : contents) 
			{
				drawTree(newNode, (RefOntoUML.Element) eobj,checkingModel,refparser);
			}
		
			/* Generalization */
		} else if (refElement instanceof RefOntoUML.Generalization)
		{
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(new OntoUMLElement(refElement,""));			
			parent.add(newNode);			
		
			/* GeneralizationSet */
		}else if (refElement instanceof RefOntoUML.GeneralizationSet)
		{
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(new OntoUMLElement(refElement,""));			
			parent.add(newNode);
			
		/* Property */
		}else if (refElement instanceof RefOntoUML.Property)
		{
			String alias = new String();
			if (refparser!=null) alias = refparser.getAlias((RefOntoUML.Property)refElement);
			else alias = "";
			
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(new OntoUMLElement(refElement,alias));			
			parent.add(newNode);
			checkingModel.setPathEnabled(new TreePath(newNode.getPath()),false);		
		
		/* Classifier */
		} else if (refElement instanceof RefOntoUML.Classifier)		
		{
			String alias = new String();
			if (refparser!=null) alias = refparser.getAlias((RefOntoUML.Classifier)refElement);
			else alias = "";
			
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(new OntoUMLElement(refElement,alias));			
			parent.add(newNode);					
						
			//modelTree.collapsePath(new TreePath(newNode.getPath()));
						
			if (refElement instanceof RefOntoUML.Association || refElement instanceof RefOntoUML.Class || refElement instanceof RefOntoUML.DataType)
			{
				for (EObject o: refElement.eContents())
				{
					drawTree(newNode,o,checkingModel,refparser);
				}			
			}		
		}
		
	}
		
	/**
	 * Get Checked Elements.
	 * 
	 * @param modeltree
	 * @return
	 */
	public List<EObject> getCheckedElements ()
	{
		List<EObject> uncheckedNodes = new ArrayList<EObject>();
		List<EObject> checkedNodes = new ArrayList<EObject>();
	    TreePath[] treepathList = getCheckingPaths();
	    	
	    for (TreePath treepath : treepathList) 
	    {	    	
	    	checkedNodes.add(((OntoUMLElement)((DefaultMutableTreeNode)treepath.getLastPathComponent()).getUserObject()).getElement());	    		    	
	    }
	
	    DefaultMutableTreeNode root = (DefaultMutableTreeNode) getModel().getRoot();
		OntoUMLElement rootObject = (OntoUMLElement) root.getUserObject();
	    	    
		initUncheckeNodes(rootObject.getElement(), checkedNodes, uncheckedNodes);
    	    	
	    return checkedNodes;
	}
	
	/**
	 * Check Node.
	 * 
	 * @param node
	 * @param safe
	 * @param modeltree
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	public void checkNode(DefaultMutableTreeNode node, boolean safe)
	{		
		EObject childObject;		
		addCheckingPath(new TreePath(node.getPath()));		
		
		EObject obj = ((OntoUMLElement)node.getUserObject()).getElement();
		
		//unselected children only if was different than Association
    	if(safe && node.getChildCount()>0 && !(obj instanceof Association)) 
    	{
			Enumeration e = node.breadthFirstEnumeration();
			DefaultMutableTreeNode childNode = (DefaultMutableTreeNode)e.nextElement();		
			
			while (e.hasMoreElements()) 
	    	{
				childNode = (DefaultMutableTreeNode)e.nextElement();
				childObject = ((OntoUMLElement)childNode.getUserObject()).getElement();		    		
				getCheckingModel().removeCheckingPath(new TreePath(childNode.getPath()));				
			}
    	}
	}
	
	@SuppressWarnings("rawtypes")
	public void selectThisElement(EObject element)
	{
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) getModel().getRoot();
		Enumeration e = root.breadthFirstEnumeration();
	    DefaultMutableTreeNode  node = (DefaultMutableTreeNode)e.nextElement();
	    while (e.hasMoreElements()) 
	    {
	    	EObject obj = ((OntoUMLElement)node.getUserObject()).getElement();
	    	if (obj.equals(element)) this.setSelectionPath(new TreePath(node.getPath()));
	    		
	    	node = (DefaultMutableTreeNode)e.nextElement();
	    }
	    //last element
	    EObject obj = ((OntoUMLElement)node.getUserObject()).getElement();
	    if (obj.equals(element)) this.setSelectionPath(new TreePath(node.getPath()));
	}
	
	/**
	 * Check this elements.
	 * 
	 * @param elements
	 * @param modeltree
	 */
	@SuppressWarnings("rawtypes")
	public void checkElements(List<EObject> elements, boolean safe) 
	{
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) getModel().getRoot();	   
		List<EObject> alreadyChecked = getCheckedElements();		
	    
		alreadyChecked.removeAll(elements);
		alreadyChecked.addAll(elements);		
		
	    Enumeration e = root.breadthFirstEnumeration();
	    DefaultMutableTreeNode  node = (DefaultMutableTreeNode)e.nextElement();
	    while (e.hasMoreElements()) 
	    {
	    	EObject obj = ((OntoUMLElement)node.getUserObject()).getElement();
	    	if (alreadyChecked.contains(obj)) { checkNode(node,true); }	    			
	    		    		
	    	node = (DefaultMutableTreeNode)e.nextElement();	    
	    }
	    //last element
	    EObject obj = ((OntoUMLElement)node.getUserObject()).getElement();
	    if (alreadyChecked.contains(obj)) { checkNode(node,true); }    	    	
	}	
	 
	/**
	 * Initialize Unchecked Nodes.
	 */
	private void initUncheckeNodes(EObject element, List<EObject> checkedElements,List<EObject> uncheckedElements) 
	{    	
    	if (element == null) return;    	
    	
    	Object[] elemArray = element.eContents().toArray();
		for (Object obj : elemArray) 
		{
			initUncheckeNodes((EObject)obj, checkedElements, uncheckedElements);
		}
    	
    	if (!checkedElements.contains(element)) 
    	{
    		if (element instanceof Package) 
    		{    			
    			// nothing to do in this case...
    		} else {    			
    			uncheckedElements.add(element);    			
    		}
    	}	
    }
}
