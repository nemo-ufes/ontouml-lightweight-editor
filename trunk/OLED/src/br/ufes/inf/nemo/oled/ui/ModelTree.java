package br.ufes.inf.nemo.oled.ui;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;

import br.ufes.inf.nemo.oled.model.UmlProject;


//FIXME REDO this implementation
public class ModelTree extends JPanel {

	private static final long serialVersionUID = 5598591779372431118L;
	//Keeps track of the trees instantiated in order to not re-instantite them 
	private static Map<UmlProject, ModelTree> treeMap = new HashMap<UmlProject, ModelTree>();
	private JTree tree = new JTree();
		
	private ModelTree(UmlProject project)
	{
		super(new BorderLayout());
		//modelAdapter.addModelListener(this);
		buildTree(project);
		tree.setBorder(new EmptyBorder(2,2,2,2));
		JScrollPane scroll = new JScrollPane(tree);
		add(scroll, BorderLayout.CENTER);		
	}
	
	/**
	 * Builds the model tree 
	 * */
	private void buildTree(UmlProject project)
	{
		//ModelElementWrapper root = new ModelElementWrapper(model.getName());
		
		//for (NamedElement element : model.getElements()) {
		//	ModelElementWrapper child = new ModelElementWrapper(element.getName());
		//	root.add(child);
		//}
	
		///tree.setModel(new DefaultTreeModel(root));
	}
	
	public static ModelTree getTreeFor(UmlProject project) {
		ModelTree modelTree = treeMap.get(project);
		if(modelTree == null)
		{
			modelTree = new ModelTree(project);
			treeMap.put(project, modelTree);
		}
		//System.out.println("Modelo selecionado: " + modelTree.getTree().getRowCount());
		return modelTree;
	}
		
	public JTree getTree() {
		return tree;
	}

	/**
	 * {@inheritDoc}
	 */
	//@Override
	//public void modelChanged(UmlModel source) {
	//	buildTree(source);		
	//}
	
	@SuppressWarnings("unused")
	private class ModelElementWrapper extends DefaultMutableTreeNode
	{
		private static final long serialVersionUID = -8295824591047732619L;
		
		public ModelElementWrapper(String name)
		{
			super(name);
		}
	}

	/*private class CustomIconRenderer extends DefaultTreeCellRenderer {
	    ImageIcon defaultIcon;
	    ImageIcon specialIcon;

	    public CustomIconRenderer() {
	        defaultIcon = new ImageIcon(CustomIconRenderer.class.getResource("/images/defaultIcon.gif"));
	        specialIcon = new ImageIcon(CustomIconRenderer.class.getResource("/images/specialIcon.gif"));
	    }

	    public Component getTreeCellRendererComponent(JTree tree, Object value,boolean sel,boolean expanded,boolean leaf,
	      int row,boolean hasFocus) {

	        super.getTreeCellRendererComponent(tree, value, sel, 
	          expanded, leaf, row, hasFocus);

	        Object nodeObj = ((DefaultMutableTreeNode)value).getUserObject();
	        // check whatever you need to on the node user object
	        if (((WhateverNodeObjectClass)nodeObj).someProperty) {
	            setIcon(specialIcon);
	        } else {
	            setIcon(defaultIcon);
	        } 
	        return this;
	    }
	}*/
}
