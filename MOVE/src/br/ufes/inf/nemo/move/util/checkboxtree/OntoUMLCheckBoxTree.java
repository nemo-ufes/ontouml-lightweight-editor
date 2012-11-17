package br.ufes.inf.nemo.move.util.checkboxtree;

import it.cnr.imaa.essi.lablib.gui.checkboxtree.CheckboxTree;
import it.cnr.imaa.essi.lablib.gui.checkboxtree.CheckboxTreeCellRenderer;
import it.cnr.imaa.essi.lablib.gui.checkboxtree.TreeCheckingModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.util.ArrayList;
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

import RefOntoUML.Generalization;
import RefOntoUML.Package;

/**
 * @author John Guerson
 */

public class OntoUMLCheckBoxTree {
	
	/**
	 * Creates a CheckboxTree from a RefOntoUML.Package root Model.
	 * 
	 * @param refmodel
	 */
	public static CheckboxTree createCheckBoxTree(RefOntoUML.Package refmodel)
	{				
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(new OntoUMLTreeNodeElem(refmodel));
		CheckboxTree modelTree = new CheckboxTree(rootNode);		
		modelTree.getCheckingModel().setCheckingMode(TreeCheckingModel.CheckingMode.PROPAGATE);		
		OntoUMLTreeCellRenderer ontoCellRenderer = new OntoUMLTreeCellRenderer();
		modelTree.setCellRenderer(ontoCellRenderer);
		TreeCheckingModel checkingModel = modelTree.getCheckingModel();
		drawTree(rootNode, refmodel,checkingModel);		
		modelTree.addCheckingPath(new TreePath(rootNode.getPath()));		
		return modelTree;
	}
	
	/**
	 * Auxiliary function. It runs the Elements from the model creating the tree nodes.
	 */	
	private static void drawTree(DefaultMutableTreeNode parent, RefOntoUML.Element refElement,TreeCheckingModel checkingModel) 
	{
		/* Model */
		if (refElement instanceof RefOntoUML.Model) 
		{
			EList<EObject> contents = refElement.eContents();
			for (EObject eobj : contents) 
			{
				drawTree(parent, (RefOntoUML.Element) eobj,checkingModel);
			}
			
		/* Package */
		} else if (refElement instanceof RefOntoUML.Package) 
		{
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(new OntoUMLTreeNodeElem(refElement));
			parent.add(newNode);
			
			EList<EObject> contents = refElement.eContents();
			for (EObject eobj : contents) 
			{
				drawTree(newNode, (RefOntoUML.Element) eobj,checkingModel);
			}
			
		/* Classifier */
		} else if (refElement instanceof RefOntoUML.Classifier)		
		{
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(new OntoUMLTreeNodeElem(refElement));			
			parent.add(newNode);			
			
			for(Generalization gen: ((RefOntoUML.Classifier)refElement).getGeneralization())
			{
				drawTree(newNode, (RefOntoUML.Element) gen,checkingModel);					
			}
			
			if (refElement instanceof RefOntoUML.Association)
			{
				drawTree(newNode, ((RefOntoUML.Association) refElement).getMemberEnd().get(0),checkingModel);
				drawTree(newNode, ((RefOntoUML.Association) refElement).getMemberEnd().get(1),checkingModel);
			}
			
		/* Generalization */
		} else if (refElement instanceof RefOntoUML.Generalization)
		{
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(new OntoUMLTreeNodeElem(refElement));			
			parent.add(newNode);			
		
		/* GeneralizationSet */
		}else if (refElement instanceof RefOntoUML.GeneralizationSet)
		{
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(new OntoUMLTreeNodeElem(refElement));			
			parent.add(newNode);		
			
		/* Property */
		}else if (refElement instanceof RefOntoUML.Property)
		{
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(new OntoUMLTreeNodeElem(refElement));			
			parent.add(newNode);		
			checkingModel.setPathEnabled(new TreePath(newNode.getPath()),false);
		}
	}
	
	/**
	 * OntoUML Cell Renderer for CheckBox Tree
	 */
	static class OntoUMLTreeCellRenderer implements CheckboxTreeCellRenderer {

    	public JCheckBox checkbox = new JCheckBox();
    	public JPanel panel = new JPanel();
    	public JLabel label = new JLabel();
    	
    	@Override
		public boolean isOnHotspot(int x, int y) 
    	{
			return (checkbox.getBounds().contains(x, y));
		}
    	
    	public OntoUMLTreeCellRenderer() 
    	{
    		super();
    		label.setFocusable(true);
    		label.setOpaque(true);
    		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
    		panel.add(checkbox);
    		panel.add(label);    		
    		checkbox.setBackground(UIManager.getColor("Tree.textBackground"));    		
    		panel.setBackground(UIManager.getColor("Tree.textBackground"));    		  		
    	}
       
    	@Override
    	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) 
    	{    	   
    		label.setText(value.toString());
    		
    		String elementType;
    		
    		RefOntoUML.Element element = ((OntoUMLTreeNodeElem)((DefaultMutableTreeNode)value).getUserObject()).getElement();
    		
    		if (element != null) {
    			elementType = element.getClass().toString().replace("class " +"RefOntoUML.impl.", "").replace("Impl", "");
    		} else
    			elementType = "diagram";
    		    		    		
    		label.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/br/ufes/inf/nemo/move/checkboxtree/"+elementType.toLowerCase()+".png")));
    		
    		if (selected)
    			//label.setBackground(ColorPalette.getInstance().getColor(ThemeColor.GREEN_MEDIUM));
    			//label.setBackground(UIManager.getColor("Tree.selectionBackground"));
    			label.setBackground(UIManager.getColor("Tree.textBackground"));
    		else
    			label.setBackground(UIManager.getColor("Tree.textBackground"));
    	   
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
	 * Get Unchecked Elements.
	 * 
	 * @param modeltree
	 * @return
	 */
	public static List<EObject> getUncheckedElements (CheckboxTree modeltree)
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
    	    	
	    return uncheckedNodes;
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
