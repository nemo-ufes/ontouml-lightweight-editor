package br.ufes.inf.nemo.oled.ui;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.move.OntoUMLElement;
import br.ufes.inf.nemo.oled.move.OntoUMLTree;


//FIXME REDO this implementation
public class ModelTree extends JPanel {

	private static final long serialVersionUID = 5598591779372431118L;
	
	//Keeps track of the trees instantiated in order to not re-instantite them 
	private static Map<UmlProject, ModelTree> treeMap = new HashMap<UmlProject, ModelTree>();
	
	private OntoUMLTree tree; 
	private UmlProject project;
	
	private ModelTree(UmlProject project)
	{
		super(new BorderLayout());
		this.project = project;
		
		//modelAdapter.addModelListener(this);
		//buildTree(project);
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(new OntoUMLElement(project.getModel(),""));
		tree = new OntoUMLTree(root,project.getModel(),project.getParser());
		tree.setBorder(new EmptyBorder(2,2,2,2));
		
		JScrollPane scroll = new JScrollPane(tree);
		add(scroll, BorderLayout.CENTER);		
	}
	
	public static ModelTree getTreeFor(UmlProject project) 
	{
		ModelTree modelTree = treeMap.get(project);
		if(modelTree == null)
		{
			modelTree = new ModelTree(project);
			treeMap.put(project, modelTree);
		}
		//System.out.println("Modelo selecionado: " + modelTree.getTree().getRowCount());
		return modelTree;
	}
	
	/**
	 * Refresh the Model Tree.
	 */
	public static void refreshModelTree(UmlProject project)
	{
		ModelTree modeltree = ModelTree.getTreeFor(project);
		modeltree.getTree().updateUI();		
		modeltree.validate();
		modeltree.repaint();		
	}
	
	public void addTreeSelectionListener(TreeSelectionListener selectionListener)
	{
		tree.addTreeSelectionListener(selectionListener);
	}	
	
	public JTree getTree() 
	{
		return tree;
	}
}
