package br.ufes.inf.nemo.move.ui.ontouml;

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
import br.ufes.inf.nemo.move.util.ColorPalette;
import br.ufes.inf.nemo.move.util.ColorPalette.ThemeColor;

/**
 * @author John Guerson
 */

public class OntoUMLCheckBoxTree {
	
	/**
	 * Creates a CheckboxTree from a RefOntoUML.Package root Model.
	 * 
	 * @param refmodel
	 */
	public static CheckboxTree createCheckBoxTree(RefOntoUML.Package refmodel, OntoUMLParser refparser)
	{				
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(new OntoUMLTreeNodeElem(refmodel,""));
		
		CheckboxTree modelTree = new CheckboxTree(rootNode);
		
		modelTree.getCheckingModel().setCheckingMode(TreeCheckingModel.CheckingMode.PROPAGATE);
		
		OntoUMLTreeCellRenderer ontoCellRenderer = new OntoUMLTreeCellRenderer();
		modelTree.setCellRenderer(ontoCellRenderer);
		
		TreeCheckingModel checkingModel = modelTree.getCheckingModel();
		
		drawTree(rootNode, refmodel,checkingModel,modelTree, refparser);	
		
		modelTree.addCheckingPath(new TreePath(rootNode.getPath()));		
		modelTree.expandPath(new TreePath(rootNode.getPath()));	
		
		return modelTree;
	}
		
	/**
	 * Auxiliary function. It runs the Elements from the model creating the tree nodes.
	 */	
	private static void drawTree(DefaultMutableTreeNode parent, RefOntoUML.Element refElement,TreeCheckingModel checkingModel,CheckboxTree modelTree, OntoUMLParser refparser) 
	{
		/* Model */
		if (refElement instanceof RefOntoUML.Model) 
		{
			EList<EObject> contents = refElement.eContents();
			for (EObject eobj : contents) 
			{
				drawTree(parent, (RefOntoUML.Element) eobj,checkingModel,modelTree,refparser);
			}
			
		/* Package */
		} else if (refElement instanceof RefOntoUML.Package) 
		{
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(new OntoUMLTreeNodeElem(refElement,""));
			parent.add(newNode);
			
			EList<EObject> contents = refElement.eContents();
			for (EObject eobj : contents) 
			{
				drawTree(newNode, (RefOntoUML.Element) eobj,checkingModel,modelTree,refparser);
			}
			
		/* Classifier */
		} else if (refElement instanceof RefOntoUML.Classifier)		
		{
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(new OntoUMLTreeNodeElem(refElement,refparser.getAlias((RefOntoUML.Classifier)refElement)));			
			parent.add(newNode);			
			//modelTree.collapsePath(new TreePath(newNode.getPath()));
			
			for(Generalization gen: ((RefOntoUML.Classifier)refElement).getGeneralization())
			{
				drawTree(newNode, (RefOntoUML.Element) gen,checkingModel,modelTree,refparser);					
			}
			
			if (refElement instanceof RefOntoUML.Association)
			{
				drawTree(newNode, ((RefOntoUML.Association) refElement).getMemberEnd().get(0),checkingModel,modelTree,refparser);
				drawTree(newNode, ((RefOntoUML.Association) refElement).getMemberEnd().get(1),checkingModel,modelTree,refparser);
			}
			
		/* Generalization */
		} else if (refElement instanceof RefOntoUML.Generalization)
		{
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(new OntoUMLTreeNodeElem(refElement,""));			
			parent.add(newNode);			
		
		/* GeneralizationSet */
		}else if (refElement instanceof RefOntoUML.GeneralizationSet)
		{
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(new OntoUMLTreeNodeElem(refElement,""));			
			parent.add(newNode);
			
		/* Property */
		}else if (refElement instanceof RefOntoUML.Property)
		{
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(new OntoUMLTreeNodeElem(refElement,refparser.getAlias((RefOntoUML.Property)refElement)));			
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
    		
    		OntoUMLTreeNodeElem treeNodeElem = ((OntoUMLTreeNodeElem)((DefaultMutableTreeNode)value).getUserObject());
    		RefOntoUML.Element element = treeNodeElem.getElement();
    		    		    		
    		if (element != null) {
    			elementType = element.getClass().toString().replace("class " +"RefOntoUML.impl.", "").replace("Impl", "");
    		} else
    			elementType = "ERROR";
    	    		
    		label.setText(value.toString());
    		    		
    		uniqueName.setForeground(Color.gray);
    		
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
    		
    		label.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/br/ufes/inf/nemo/move/ontouml/"+elementType.toLowerCase()+".png")));
    		
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
	public static List<EObject> getCheckedElements (CheckboxTree modeltree)
	{
		List<EObject> uncheckedNodes = new ArrayList<EObject>();
		List<EObject> checkedNodes = new ArrayList<EObject>();
	    TreePath[] treepathList = modeltree.getCheckingPaths();
	    	
	    for (TreePath treepath : treepathList) 
	    {
	    	checkedNodes.add(((OntoUMLTreeNodeElem)((DefaultMutableTreeNode)treepath.getLastPathComponent()).getUserObject()).getElement());
	    }
	    	
	    DefaultMutableTreeNode root = (DefaultMutableTreeNode) modeltree.getModel().getRoot();
		OntoUMLTreeNodeElem rootObject = (OntoUMLTreeNodeElem) root.getUserObject();
	    	    
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
	public static void checkNode(DefaultMutableTreeNode node, boolean safe,CheckboxTree modeltree)
	{
		EObject childObject;		
		modeltree.addCheckingPath(new TreePath(node.getPath()));		
		EObject obj = ((OntoUMLTreeNodeElem)node.getUserObject()).getElement();
		
		//unselected children only if was different than Association
    	if(safe && node.getChildCount()>0 && !(obj instanceof Association)) 
    	{
			Enumeration e = node.breadthFirstEnumeration();
			DefaultMutableTreeNode childNode = (DefaultMutableTreeNode)e.nextElement();		
						
			while (e.hasMoreElements()) 
	    	{
				childNode = (DefaultMutableTreeNode)e.nextElement();
				childObject = ((OntoUMLTreeNodeElem)childNode.getUserObject()).getElement();
				modeltree.getCheckingModel().removeCheckingPath(new TreePath(childNode.getPath()));				
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
	public static void checkElements(List<EObject> elements, boolean safe, CheckboxTree modeltree) 
	{
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) modeltree.getModel().getRoot();
	    
		List<EObject> alreadyChecked = getCheckedElements(modeltree);
	    alreadyChecked.addAll(elements);
	    
	    Enumeration e = root.breadthFirstEnumeration();
	    DefaultMutableTreeNode  node = (DefaultMutableTreeNode)e.nextElement();
	    while (e.hasMoreElements()) 
	    {
	    	EObject obj = ((OntoUMLTreeNodeElem)node.getUserObject()).getElement();
	    	if (alreadyChecked.contains(obj)) checkNode(node,true,modeltree);
	    	node = (DefaultMutableTreeNode)e.nextElement();
	    }
	}	
	 
	/**
	 * Initialize Unchecked Nodes.
	 */
	private static void initUncheckeNodes(EObject element, List<EObject> checkedElements,List<EObject> uncheckedElements) 
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
