package br.ufes.inf.nemo.oled.ui;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.ocl2alloy.OCLOptions;
import br.ufes.inf.nemo.oled.model.AlloySpecification;
import br.ufes.inf.nemo.oled.model.AntiPatternList;
import br.ufes.inf.nemo.oled.model.OCLDocument;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.ontouml2alloy.Onto2AlloyOptions;


//FIXME REDO this implementation
public class ModelTree extends JPanel {

	private static final long serialVersionUID = 5598591779372431118L;
	
	//Keeps track of the trees instantiated in order to not re-instantite them 
	private static Map<UmlProject, ModelTree> treeMap = new HashMap<UmlProject, ModelTree>();
	
	private JScrollPane scroll;
	
	private OntoUMLTree tree; 
	private UmlProject project;	
	private OntoUMLParser refparser;	
	private AlloySpecification alloySpec;
	private OCLDocument oclmodel;
	private AntiPatternList antipatterns;	
	private Onto2AlloyOptions refOptions;
	private OCLOptions oclOptions;
	
	
	
	public ModelTree(UmlProject project)
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
				
		alloySpec = new AlloySpecification(project.getTempDir()+"spec.als");
		oclmodel = new OCLDocument();
		oclOptions = new OCLOptions();
		refOptions = new Onto2AlloyOptions();
		antipatterns = new AntiPatternList();
		
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
	
	public static void setParserFor(UmlProject project, OntoUMLParser refparser) 
	{		
		ModelTree.getTreeFor(project).refparser = refparser;
	}
	
	public static AlloySpecification getAlloySpecFor(UmlProject project) 
	{		
		return ModelTree.getTreeFor(project).alloySpec;
	}
	
	public static void setAlloySpecFor(UmlProject project, AlloySpecification alloySpec) 
	{		
		ModelTree.getTreeFor(project).alloySpec = alloySpec;
	}
	
	public static OCLDocument getOCLModelFor(UmlProject project)
	{
		return ModelTree.getTreeFor(project).oclmodel;
	}
	
	public static void setOCLOptionsFor(UmlProject project, OCLOptions oclOptions)
	{
		ModelTree.getTreeFor(project).oclOptions = oclOptions;
	}
	
	public static OCLOptions getOCLOptionsFor(UmlProject project)
	{
		return ModelTree.getTreeFor(project).oclOptions;
	}

	public static Onto2AlloyOptions getOntoUMLOptionsFor(UmlProject project)
	{
		return ModelTree.getTreeFor(project).refOptions;
	}

	public static void setOntoUMLOptionsFor(UmlProject project, Onto2AlloyOptions refOptions)
	{
		ModelTree.getTreeFor(project).refOptions = refOptions;
	}
	
	public static AntiPatternList getAntiPatternListFor(UmlProject project)
	{
		return ModelTree.getTreeFor(project).antipatterns;
	}

	public static void setAntiPatternListFor(UmlProject project, AntiPatternList antipatterns)
	{
		ModelTree.getTreeFor(project).antipatterns = antipatterns;
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
	
	public OntoUMLTree getTree() 
	{
		return tree;
	}

	public UmlProject getProject() {
		return project;
	}
}
