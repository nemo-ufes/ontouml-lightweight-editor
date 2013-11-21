package br.ufes.inf.nemo.xmi2ontouml.util;

import it.cnr.imaa.essi.lablib.gui.checkboxtree.CheckboxTree;
import it.cnr.imaa.essi.lablib.gui.checkboxtree.CheckboxTreeCellRenderer;
import it.cnr.imaa.essi.lablib.gui.checkboxtree.TreeCheckingModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Collection;
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
import RefOntoUML.Class;
import RefOntoUML.DataType;
import RefOntoUML.Element;
import RefOntoUML.Model;
import RefOntoUML.Package;
import RefOntoUML.PackageableElement;
import RefOntoUML.PrimitiveType;
import RefOntoUML.Property;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.xmi2ontouml.framework.XMI2RefElement;
import br.ufes.inf.nemo.xmi2ontouml.mapper.Mapper;


public class RefOntoUMLUtil {
	/**
	 * Creates a CheckboxTree from a RefOntoUML.Model to serve
	 * as a element selection to the XMI2RefOntoUML transformation.
	 * @param model the RefOntoUML Model
	 * @return the CheckboxTree with the Model elements to be selected.
	 */	
	public static CheckboxTree createSelectionTreeFromModel(RefOntoUML.Model model) {
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(new ChckBoxTreeNodeElem(model));
		CheckboxTree modelTree = new CheckboxTree(rootNode);
		modelTree.setCellRenderer(new OntoUMLTreeCellRenderer());
		
		drawTree(rootNode, model);
		
		return modelTree;
	}
	
	/**
	 * Auxiliary function for the createSelectionTreeFromModel.
	 * It runs the Elements from the model creating the tree nodes.
	 * @param parent the direct parent node of the element that will be created.
	 * @param refElement the RefOntoUML Element for which a node will be created.
	 */	
	private static void drawTree(DefaultMutableTreeNode parent, RefOntoUML.Element refElement) {
		if (refElement instanceof RefOntoUML.Model) {
			EList<EObject> contents = refElement.eContents();
			for (EObject eobj : contents) {
				drawTree(parent, (RefOntoUML.Element) eobj);
			}
			
		} else if (refElement instanceof RefOntoUML.Package) {
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(new ChckBoxTreeNodeElem(refElement));
			parent.add(newNode);
			
			EList<EObject> contents = refElement.eContents();
			for (EObject eobj : contents) {
				drawTree(newNode, (RefOntoUML.Element) eobj);
			}
			
		} else if (refElement instanceof RefOntoUML.Classifier) {
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(new ChckBoxTreeNodeElem(refElement));
			parent.add(newNode);
			
		}
	}
	
	/**
	 * Creates a CheckboxTree organizing the elements by diagrams
	 * instead of by the Model structure (Packages). It uses the
	 * Mapper to find the diagrams and their classes.
	 * @param mapper the bridge that in responsible for reading the
	 * tool specific XMI and returning the information that is needed.
	 * @return the CheckboxTree with the elements organized by diagram.
	 * @throws Exception 
	 */	
	public static CheckboxTree createSelectionTreeByDiagram(Mapper mapper, Model model) throws Exception {
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(new ChckBoxTreeNodeElem(model));
		CheckboxTree modelTree = new CheckboxTree(rootNode);
		modelTree.setCellRenderer(new OntoUMLTreeCellRenderer());
		
		for (PackageableElement pel : model.getPackagedElement()) {
			if (pel instanceof PrimitiveType) {
				DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(
	    				new ChckBoxTreeNodeElem(pel));
	    		rootNode.add(newNode);
			}
		}
		
		List<Object> diagramList = mapper.getDiagramList();
    	for (Object diagram : diagramList) {
    		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(
    				new ChckBoxTreeNodeElem(mapper.getName(diagram)));
    		rootNode.add(newNode);
    		List<String> elemIdList = mapper.getDiagramElements(diagram);
    		for (String id : elemIdList) {
    			try {
    				DefaultMutableTreeNode newElemNode = new DefaultMutableTreeNode(
    					new ChckBoxTreeNodeElem((Element) XMI2RefElement.getElemMap().get(id)));
    				newNode.add(newElemNode);
    			} catch (NullPointerException npe) {
    				continue;
    			}
    			
    		}
    	}
    	
    	return modelTree;
	}
	
	public static Model Filter(CheckboxTree modelTree) {
		
		Collection<EObject> checkedNodes = new ArrayList<EObject>();
    	TreePath[] treepathList = modelTree.getCheckingPaths();
    	
    	for (TreePath treepath : treepathList) {
    		DefaultMutableTreeNode elemNode = (DefaultMutableTreeNode) treepath.getLastPathComponent();
    		ChckBoxTreeNodeElem checkBoxElemNode = (ChckBoxTreeNodeElem) elemNode.getUserObject();
    		Element element = checkBoxElemNode.getElement();
    		
    		if (!checkedNodes.contains(element) && element != null)
    		{
    			checkedNodes.add(element);
    		}
    		
    		if (element instanceof PackageableElement)
    		{
    			addDependentElementsToList(element, checkedNodes);
    		}
    		
    	}
    	
    	DefaultMutableTreeNode root = (DefaultMutableTreeNode) modelTree.getModel().getRoot();
    	ChckBoxTreeNodeElem rootObject = (ChckBoxTreeNodeElem) root.getUserObject();
    	
    	OntoUMLCopier copier = new OntoUMLCopier(checkedNodes);    	
    	copier.copy(rootObject.getElement());
    	copier.copyReferences();
    	return (Model) copier.get(rootObject.getElement());
	}
	
	// Adds Elements not selected to the "not to exclude" list
	public static void addDependentElementsToList(Element element, Collection<EObject> list)
	{
		// Adds every parent package
		if (element == null)
			return;
		
		Package parent = (Package) element.eContainer();
		while (parent != null && !list.contains(parent))
		{
			list.add(parent);
			parent = (Package) parent.eContainer();
		}
		// Adds non selected classes participating in a selected association
		if (element instanceof Association)
		{
			Association assoc = (Association) element;
			if (assoc.getMemberEnd().size() < 2 || assoc.getMemberEnd().get(0).getType() == null || assoc.getMemberEnd().get(1).getType() == null)
			{
				System.out.println("Debug: removing association with error ("+assoc.getName()+")");
				list.remove(assoc);
			}
			
			else
			{
				if (!list.contains(assoc.getMemberEnd().get(0).getType()))
				{
					list.add(assoc.getMemberEnd().get(0).getType());
					addDependentElementsToList(assoc.getMemberEnd().get(0).getType(), list);
				}
				if (!list.contains(assoc.getMemberEnd().get(1).getType()))
				{
					list.add(assoc.getMemberEnd().get(1).getType());
					addDependentElementsToList(assoc.getMemberEnd().get(0).getType(), list);
				}
			}
		}
		// Adds classes referenced by attributes as their types
		else if (element instanceof Class)
		{
			Class cl = (Class) element;
			for (Property att : cl.getOwnedAttribute())
			{
				if (att.getType() != null && !list.contains(att.getType()))
				{
					list.add(att.getType());
					addDependentElementsToList(att.getType(), list);
				}
			}
		}
		else if (element instanceof DataType)
		{
			DataType dt = (DataType) element;
			for (Property att : dt.getOwnedAttribute())
			{
				if (att.getType() != null && !list.contains(att.getType()))
				{
					list.add(att.getType());
					addDependentElementsToList(att.getType(), list);
				}
			}
		}
	}
    
    static class OntoUMLTreeCellRenderer implements CheckboxTreeCellRenderer {

    	JCheckBox button = new JCheckBox();
    	JPanel panel = new JPanel();
    	JLabel label = new JLabel();
    	
    	@Override
		public boolean isOnHotspot(int x, int y) {
			return (button.getBounds().contains(x, y));
		}

    	public OntoUMLTreeCellRenderer() {
    		label.setFocusable(true);
    		label.setOpaque(true);
    		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
    		panel.add(button);
    		panel.add(label);
    		button.setBackground(UIManager.getColor("Tree.textBackground"));
    		panel.setBackground(UIManager.getColor("Tree.textBackground"));
    	}
       
    	@Override
    	public Component getTreeCellRendererComponent(JTree tree, Object value, 
    			boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
    	   
    		label.setText(value.toString());
    		String elementType;
    		RefOntoUML.Element element = ((ChckBoxTreeNodeElem)((DefaultMutableTreeNode)value).
    				getUserObject()).getElement();
    		if (element != null) {
    			elementType = element.getClass().toString().replace("class " +
    					"RefOntoUML.impl.", "").replace("Impl", "");
    		} else
    			elementType = "diagram";
    		
    		label.setIcon(new ImageIcon(getClass().getClassLoader().
    				getResource("resources/br/ufes/inf/nemo/oled/ui/xmi2ontoref/"+
    				elementType.toLowerCase()+".png")));
    		
    		if (selected)
    			label.setBackground(UIManager.getColor("Tree.selectionBackground"));
    		else
    			label.setBackground(UIManager.getColor("Tree.textBackground"));
    	   
    		TreeCheckingModel checkingModel = ((CheckboxTree)tree).getCheckingModel();
    	   	TreePath path = tree.getPathForRow(row);
    	   	boolean enabled = checkingModel.isPathEnabled(path);
    	   	boolean checked = checkingModel.isPathChecked(path);
    	   	boolean grayed = checkingModel.isPathGreyed(path);
    	   	button.setEnabled(enabled);
    	   	if (grayed) {
    	   		label.setForeground(Color.lightGray);
    	   	} else {
    	   		label.setForeground(Color.black);
    	   	}
    	   	button.setSelected(checked);
    	   	return panel;
    	}
    }
    
    public static String verifyInconsistency(Package pack)
    {
    	String log = "";
    	
    	OntoUMLParser parser = new OntoUMLParser(pack);
    	for (Property prop : parser.getAllInstances(Property.class))
    	{
    		if (prop.getType() == null)
    		{
    			log += OntoUMLError.undefinedTypeError(prop);
    		}
    	}
    	
    	return log;
    }
	
}
