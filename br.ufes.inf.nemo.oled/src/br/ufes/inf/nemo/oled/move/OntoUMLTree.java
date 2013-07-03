package br.ufes.inf.nemo.oled.move;

import it.cnr.imaa.essi.lablib.gui.checkboxtree.CheckboxTree;
import it.cnr.imaa.essi.lablib.gui.checkboxtree.CheckboxTreeCellRenderer;
import it.cnr.imaa.essi.lablib.gui.checkboxtree.TreeCheckingModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.Package;
import RefOntoUML.Property;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

import br.ufes.inf.nemo.oled.util.ColorPalette;
import br.ufes.inf.nemo.oled.util.ColorPalette.ThemeColor;

public class OntoUMLTree extends CheckboxTree {

	private static final long serialVersionUID = 1L;
	
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
	public OntoUMLTree (DefaultMutableTreeNode rootNode, RefOntoUML.Package refmodel, OntoUMLParser refparser)
	{
		super(rootNode);
		
		getCheckingModel().setCheckingMode(TreeCheckingModel.CheckingMode.PROPAGATE);
		
		OntoUMLTreeCellRenderer ontoCellRenderer = new OntoUMLTreeCellRenderer();
		setCellRenderer(ontoCellRenderer);
		
		TreeCheckingModel checkingModel = getCheckingModel();
		
		drawTree(rootNode, refmodel,checkingModel, refparser);	
		
		addCheckingPath(new TreePath(rootNode.getPath()));		
		expandPath(new TreePath(rootNode.getPath()));
	
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
			
		/* Classifier */
		} else if (refElement instanceof RefOntoUML.Classifier)		
		{
			String alias = new String();
			if (refparser!=null) alias = refparser.getAlias((RefOntoUML.Classifier)refElement);
			else alias = "";
			
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(new OntoUMLElement(refElement,alias));			
			parent.add(newNode);					
						
			//modelTree.collapsePath(new TreePath(newNode.getPath()));
			
			for(Generalization gen: ((RefOntoUML.Classifier)refElement).getGeneralization())
			{
				drawTree(newNode, (RefOntoUML.Element) gen,checkingModel,refparser);					
			}
			
			for (EObject o: refElement.eContents())
			{
				drawTree(newNode,o,checkingModel,refparser);
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
			if (refparser!=null) alias = refparser.getAlias((RefOntoUML.Classifier)refElement);
			else alias = "";
			
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(new OntoUMLElement(refElement,alias));			
			parent.add(newNode);
			checkingModel.setPathEnabled(new TreePath(newNode.getPath()),false);
		}
	}
	
	/**
	 * OntoUML Cell Renderer for CheckBox Tree
	 */
	public static class OntoUMLTreeCellRenderer implements CheckboxTreeCellRenderer {
		
    	public JCheckBox checkbox = new JCheckBox();
    	public JPanel panel = new JPanel();
    	public JLabel label = new JLabel();    	
    	public JLabel uniqueName = new JLabel();
    	public Boolean showUniqueName = false;     	
    	
    	@Override
		public boolean isOnHotspot(int x, int y) 
    	{
			return (checkbox.getBounds().contains(x, y));
		}
    	
    	public void showOrHideUniqueName()
    	{
    		if(showUniqueName) showUniqueName=false;
    		else showUniqueName=true;
    	}
    	
    	public OntoUMLTreeCellRenderer() 
    	{
    		super();    		
    		label.setFocusable(true);
    		label.setOpaque(true);
    		uniqueName.setFocusable(true);
    		uniqueName.setOpaque(true);    		 
    		panel.setLayout(new BorderLayout(0, 0));
    		panel.add(BorderLayout.WEST,checkbox);
    		panel.add(BorderLayout.CENTER,label);
    		panel.add(BorderLayout.EAST,uniqueName);    		
    		checkbox.setBackground(UIManager.getColor("Tree.textBackground"));    		
    		panel.setBackground(UIManager.getColor("Tree.textBackground"));
    		uniqueName.setBackground(UIManager.getColor("Tree.textBackground"));    		
    	}
       
    	@Override
    	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) 
    	{    		
    		String elementType;
    		OntoUMLElement treeNodeElem = null;    		
    		
    		if( ((DefaultMutableTreeNode)value).getUserObject() instanceof OntoUMLElement ){
    			
    			treeNodeElem = ((OntoUMLElement)((DefaultMutableTreeNode)value).getUserObject());
    			
    			EObject element = treeNodeElem.getElement();
    			
        		if (element != null) {
        			elementType = element.getClass().toString().replace("class " +"RefOntoUML.impl.", "").replace("Impl", "");
        		} else
        			elementType = "ERROR";
        		
        		if (showUniqueName)
        		{
        			if(element instanceof Generalization) uniqueName.setText("");
        			else if(element instanceof GeneralizationSet) uniqueName.setText("");
        			else if(element instanceof Package) uniqueName.setText("");
        			else if(element instanceof Classifier) uniqueName.setText(" ("+treeNodeElem.getUniqueName()+")");
        			else if(element instanceof Association) uniqueName.setText(" ("+treeNodeElem.getUniqueName()+")");
        			else if(element instanceof Property) uniqueName.setText(" ("+treeNodeElem.getUniqueName()+")");      			
        		} else {
        			uniqueName.setText("");        			
        		}
        		
    		    if (elementType.toLowerCase().equals("property"))
        			label.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/br/ufes/inf/nemo/oled/move/ontouml/property.gif")));
        		else
        			label.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/br/ufes/inf/nemo/oled/move/ontouml/"+elementType.toLowerCase()+".png")));
        		
    		}else{

    		}    		
    	    
    		label.setText(value.toString());
    		    		
    		uniqueName.setForeground(Color.gray);    		 		
   		    		
    		if (selected){
    			label.setBackground(ColorPalette.getInstance().getColor(ThemeColor.GREEN_LIGHTEST));
    			//label.setBackground(UIManager.getColor("Tree.selectionBackground"));
    			//label.setBackground(UIManager.getColor("Tree.textBackground"));    			
    		}else{
    			label.setBackground(UIManager.getColor("Tree.textBackground"));    			
    		}
    		    		
    		TreeCheckingModel checkingModel = ((CheckboxTree)tree).getCheckingModel();
    	   	TreePath path = tree.getPathForRow(row);
    	   	
    	   	boolean enabled = checkingModel.isPathEnabled(path);
    	   	boolean checked = checkingModel.isPathChecked(path);
    	   	boolean grayed = checkingModel.isPathGreyed(path);
    	   	
    	   	checkbox.setEnabled(enabled);
    	   	    	   	
    	   	if (grayed) {
    	   		label.setForeground(Color.lightGray);
    	   	} else {
    	   		label.setForeground(Color.black);
    	   	}
    	   	    	   	
    	   	checkbox.setSelected(checked);
    	   	
    	   	return panel;
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
