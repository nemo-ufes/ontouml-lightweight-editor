package br.ufes.inf.nemo.oled.ui;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.move.OntoUMLElement;
import br.ufes.inf.nemo.oled.move.OntoUMLTree;


//FIXME REDO this implementation
public class ModelTree extends JPanel {

	private static final long serialVersionUID = 5598591779372431118L;
	
	//Keeps track of the trees instantiated in order to not re-instantite them 
	private static Map<UmlProject, ModelTree> treeMap = new HashMap<UmlProject, ModelTree>();
	
	private JScrollPane scroll;
	private OntoUMLTree tree; 
	private UmlProject project;
	private OntoUMLParser refparser;
		
	private ModelTree(UmlProject project)
	{
		super(new BorderLayout());
		this.project = project;
		
		//modelAdapter.addModelListener(this);
		//buildTree(project);
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(new OntoUMLElement(project.getModel(),""));
		refparser = new OntoUMLParser(project.getModel());
		tree = new OntoUMLTree(root,project.getModel(),refparser);
		tree.setBorder(new EmptyBorder(2,2,2,2));
		tree.addTreeSelectionListener(new OntoUMLTreeSelectionListener());
		
		scroll = new JScrollPane();
		scroll.setViewportView(tree);
		
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
	
	public static OntoUMLParser getParserFor(UmlProject project) 
	{		
		return ModelTree.getTreeFor(project).refparser;
	}
	
	/**
	 * Refresh the Model Tree.
	 */
	public static void refreshModelTree(UmlProject project)
	{
		ModelTree modeltree = ModelTree.getTreeFor(project);
		
		modeltree.tree.updateUI();		
		
		modeltree.validate();
		modeltree.repaint();		
	}
	
	/**
	 * Update the Model Tree
	 */
	public static void updateModelTree(UmlProject project)
	{
		ModelTree modeltree = ModelTree.getTreeFor(project);
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(new OntoUMLElement(project.getModel(),""));
		OntoUMLParser refparser = new OntoUMLParser(project.getModel());
		
		modeltree.setParser(refparser);
		modeltree.setTree(new OntoUMLTree(root,project.getModel(),refparser));
		
		modeltree.validate();
		modeltree.repaint();
	}
	
	public void setTree(OntoUMLTree tree)
	{
		remove(scroll);
		
		this.tree = tree;
		this.tree.setBorder(new EmptyBorder(2,2,2,2));
		
		this.addTreeSelectionListener(new OntoUMLTreeSelectionListener());
		
		scroll = new JScrollPane();
		scroll.setViewportView(tree);
		add(scroll, BorderLayout.CENTER);		
		
		scroll.validate();
		scroll.repaint();
		this.validate();
		this.repaint();
	}
	
	class OntoUMLTreeSelectionListener implements TreeSelectionListener 
	 {
		 @Override
			public void valueChanged(TreeSelectionEvent e) 
			{
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();				
				DiagramEditorWrapper.getProperties().setData(node);
				DiagramEditorWrapper.focusOnProperties();
			}
	 }
	
	public void setParser(OntoUMLParser refparser)
	{
		this.refparser = refparser;
	}
	
	public void addTreeSelectionListener(TreeSelectionListener selectionListener)
	{
		tree.addTreeSelectionListener(selectionListener);
	}	
	
	public JTree getTree() 
	{
		return tree;
	}

	public UmlProject getProject() {
		return project;
	}
}
