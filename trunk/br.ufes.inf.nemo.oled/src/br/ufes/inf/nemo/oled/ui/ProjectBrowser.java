package br.ufes.inf.nemo.oled.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import org.eclipse.emf.ecore.EObject;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.ocl2alloy.OCL2AlloyOptions;
import br.ufes.inf.nemo.oled.model.AlloySpecification;
import br.ufes.inf.nemo.oled.model.AntiPatternList;
import br.ufes.inf.nemo.oled.model.InferenceList;
import br.ufes.inf.nemo.oled.model.OCLDocument;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.ontouml2alloy.OntoUML2AlloyOptions;

public class ProjectBrowser extends JPanel{

	private static final long serialVersionUID = 5598591779372431118L;
	
	//Keeps track of the trees instantiated in order to not re-instantite them 
	private static Map<UmlProject, ProjectBrowser> treeMap = new HashMap<UmlProject, ProjectBrowser>();
	
	private JScrollPane scroll;
	private OntoUMLTree tree; 
	private UmlProject project;	
	private OntoUMLParser refparser;	
	private AlloySpecification alloySpec;
	private OCLDocument oclmodel;
	private AntiPatternList antipatterns;	
	private InferenceList inferences;
	private OntoUML2AlloyOptions refOptions;
	private OCL2AlloyOptions oclOptions;
	public static AppFrame frame;
		
	public void setProject(UmlProject project)
	{
		this.project = project;

		DefaultMutableTreeNode root = new DefaultMutableTreeNode(new OntoUMLElement(project.getModel(),""));
		refparser = new OntoUMLParser(project.getModel());
		tree = new OntoUMLTree(frame, root,project.getModel(),refparser);
		tree.setBorder(new EmptyBorder(2,2,2,2));
		tree.addTreeSelectionListener(new OntoUMLTreeSelectionListener());
		
		String name = ((RefOntoUML.Package)project.getResource().getContents().get(0)).getName();
		if (name==null || name.isEmpty()) name = "model";
		alloySpec = new AlloySpecification(project.getTempDir()+File.separator+name.toLowerCase()+".als");
		
		oclmodel = new OCLDocument();
		oclOptions = new OCL2AlloyOptions();
		refOptions = new OntoUML2AlloyOptions();
		antipatterns = new AntiPatternList();
		inferences = new InferenceList();
		
		scroll.setViewportView(tree);
		
		treeMap.put(project, this);
		updateUI();
	}
	
	public void eraseProject()
	{
		this.project = null;
		
		JPanel emptyTempPanel = new JPanel();
		emptyTempPanel.setBackground(Color.WHITE);
		emptyTempPanel.setBorder(new EmptyBorder(0,0, 0, 0));
		scroll.setViewportView(emptyTempPanel);
		
		emptyTempPanel.setPreferredSize(new Dimension(200,250));
		
		updateUI();
	}
	public ProjectBrowser(AppFrame appframe, UmlProject project)
	{
		super(new BorderLayout());
		this.project = project;
		frame = appframe;
		
		scroll = new JScrollPane();
		
		if (project!=null)
		{
			setProject(project);
		}
				
		add(scroll, BorderLayout.CENTER);	
		
		JPanel emptyTempPanel = new JPanel();
		emptyTempPanel.setBackground(Color.WHITE);
		emptyTempPanel.setBorder(new EmptyBorder(0,0, 0, 0));
		scroll.setViewportView(emptyTempPanel);
		
		emptyTempPanel.setPreferredSize(new Dimension(200,250));
		scroll.setPreferredSize(new Dimension(200,250));
		setPreferredSize(new Dimension(200,250));
	}
	
	public static ProjectBrowser getTreeFor(AppFrame frame, UmlProject project) 
	{
		ProjectBrowser modelTree = treeMap.get(project);
		if(modelTree == null)
		{
			modelTree = new ProjectBrowser(frame, project);
			treeMap.put(project, modelTree);			
		}
		return modelTree;
	}
	
	public static OntoUMLParser getParserFor(UmlProject project) 
	{		
		return ProjectBrowser.getTreeFor(frame, project).refparser;
	}
	
	public static void setParserFor(UmlProject project, OntoUMLParser refparser) 
	{		
		ProjectBrowser.getTreeFor(frame,project).refparser = refparser;
	}
	
	public static AlloySpecification getAlloySpecFor(UmlProject project) 
	{		
		return ProjectBrowser.getTreeFor(frame,project).alloySpec;
	}
	
	public static void setAlloySpecFor(UmlProject project, AlloySpecification alloySpec) 
	{		
		ProjectBrowser.getTreeFor(frame,project).alloySpec = alloySpec;
	}
	
	public static OCLDocument getOCLModelFor(UmlProject project)
	{
		return ProjectBrowser.getTreeFor(frame,project).oclmodel;
	}
	
	public static void setOCLOptionsFor(UmlProject project, OCL2AlloyOptions oclOptions)
	{
		ProjectBrowser.getTreeFor(frame,project).oclOptions = oclOptions;
	}
	
	public static OCL2AlloyOptions getOCLOptionsFor(UmlProject project)
	{
		return ProjectBrowser.getTreeFor(frame,project).oclOptions;
	}

	public static OntoUML2AlloyOptions getOntoUMLOptionsFor(UmlProject project)
	{
		return ProjectBrowser.getTreeFor(frame,project).refOptions;
	}

	public static void setOntoUMLOptionsFor(UmlProject project, OntoUML2AlloyOptions refOptions)
	{
		ProjectBrowser.getTreeFor(frame,project).refOptions = refOptions;
	}
	
	public static AntiPatternList getAntiPatternListFor(UmlProject project)
	{
		return ProjectBrowser.getTreeFor(frame,project).antipatterns;
	}

	public static void setAntiPatternListFor(UmlProject project, AntiPatternList antipatterns)
	{
		ProjectBrowser.getTreeFor(frame,project).antipatterns = antipatterns;
	}
	
	public static InferenceList getInferences(UmlProject project) {
		return ProjectBrowser.getTreeFor(frame,project).inferences;
	}

	
	public static void setDerivations(UmlProject project, InferenceList inferences) {
		ProjectBrowser.getTreeFor(frame,project).inferences = inferences;
	}
	
	/**
	 * Refresh the Model Tree.
	 */
	public static void refreshModelTree(UmlProject project)
	{
		ProjectBrowser modeltree = ProjectBrowser.getTreeFor(frame,project);
		
		modeltree.tree.updateUI();		
		
		modeltree.validate();
		modeltree.repaint();		
	}
	
	/**
	 * Update the Model Tree
	 */
	public static void updateModelTree(UmlProject project)
	{
		ProjectBrowser modeltree = ProjectBrowser.getTreeFor(frame,project);
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(new OntoUMLElement(project.getModel(),""));
		OntoUMLParser refparser = new OntoUMLParser(project.getModel());
		
		ArrayList<EObject> selected = (ArrayList<EObject>)modeltree.getTree().getCheckedElements();
		refparser.selectThisElements(selected, true);
		
		modeltree.setParser(refparser);
		
		modeltree.setTree(new OntoUMLTree(frame, root,project.getModel(),refparser));
		modeltree.getTree().checkElements(selected, true);			
		modeltree.getTree().updateUI();    	
				
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
				InfoManager.getProperties().setData(node);
				frame.focusOnProperties();
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
