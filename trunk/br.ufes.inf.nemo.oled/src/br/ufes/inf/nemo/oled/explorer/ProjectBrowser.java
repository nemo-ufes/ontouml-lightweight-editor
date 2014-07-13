/**
 * Copyright(C) 2011-2014 by John Guerson, Tiago Prince, Antognoni Albuquerque
 *
 * This file is part of OLED (OntoUML Lightweight BaseEditor).
 * OLED is based on TinyUML and so is distributed under the same
 * license terms.
 *
 * OLED is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * OLED is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OLED; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package br.ufes.inf.nemo.oled.explorer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import br.ufes.inf.nemo.assistant.ModellingAssistant;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.oled.AppFrame;
import br.ufes.inf.nemo.oled.Main;
import br.ufes.inf.nemo.oled.model.AlloySpecification;
import br.ufes.inf.nemo.oled.model.AntiPatternList;
import br.ufes.inf.nemo.oled.model.InferenceList;
import br.ufes.inf.nemo.oled.model.OCLDocument;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.ui.diagram.DiagramEditorWrapper;
import br.ufes.inf.nemo.oled.umldraw.structure.StructureDiagram;
import br.ufes.inf.nemo.ontouml2alloy.OntoUML2AlloyOptions;
import br.ufes.inf.nemo.tocl.tocl2alloy.TOCL2AlloyOption;

/**
 * @author John Guerson
 */
public class ProjectBrowser extends JPanel{

	//Keeps track of the trees instantiated in order to not re-instantite them 
	private static Map<UmlProject, ProjectBrowser> treeMap = new HashMap<UmlProject, ProjectBrowser>();
	
	private static final long serialVersionUID = 5598591779372431118L;	
	public static AppFrame frame;
	private ProjectToolBar ptoolbar;
	private JScrollPane scroll;
	private ProjectTree tree;
	//======
	private UmlProject project;	
	private OntoUMLParser refparser;	
	private ArrayList<OCLDocument> oclDocList = new ArrayList<OCLDocument>();
	//======
	private AlloySpecification alloySpec;	
	private AntiPatternList antipatterns;	
	private InferenceList inferences;
	private OntoUML2AlloyOptions refOptions;
	private TOCL2AlloyOption oclOptions;	
	private ModellingAssistant assistant;
		
	public void addTreeSelectionListener(TreeSelectionListener selectionListener)
	{
		tree.addTreeSelectionListener(selectionListener);
	}	
	
	public ProjectTree getTree() 
	{
		return tree;
	}

	public OntoUMLParser getParser()
	{
		return refparser;
	}
	public void setParser(OntoUMLParser refparser)
	{
		this.refparser = refparser;
	}
	
	public UmlProject getProject() 
	{
		return project;
	}
	
	public ArrayList<OCLDocument> getOCLDocuments() 
	{ 
		return oclDocList; 
	}

	public ProjectBrowser(AppFrame appframe, UmlProject project, OCLDocument oclDoc)
	{
		super(new BorderLayout());
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.project = project;
		frame = appframe;		
		scroll = new JScrollPane();		
		if (project!=null) setProject(project);
		add(scroll, BorderLayout.CENTER);			
		JPanel emptyTempPanel = new JPanel();
		emptyTempPanel.setBackground(Color.WHITE);
		emptyTempPanel.setBorder(new EmptyBorder(0,0, 0, 0));
		scroll.setViewportView(emptyTempPanel);		
		emptyTempPanel.setPreferredSize(new Dimension(200,250));
		scroll.setPreferredSize(new Dimension(200,250));
		setPreferredSize(new Dimension(216, 317));
	}
	
	public void setProject(UmlProject project)
	{
		this.project = project;		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(project);
		Main.printOutLine("Creating OntoUML parser");
		refparser = new OntoUMLParser(project.getModel());				
		Main.printOutLine("Creating project browser tree");		
		tree = new ProjectTree(frame, root,project,refparser,oclDocList);
		tree.setBorder(new EmptyBorder(2,2,2,2));
		tree.addTreeSelectionListener(new ProjectTreeSelectionListener());		
		String name = ((RefOntoUML.Package)project.getResource().getContents().get(0)).getName();
		if (name==null || name.isEmpty()) name = "model";		
		alloySpec = new AlloySpecification(project.getTempDir()+File.separator+name.toLowerCase()+".als");				
		oclOptions = new TOCL2AlloyOption();		
		refOptions = new OntoUML2AlloyOptions();		
		antipatterns = new AntiPatternList();		
		inferences = new InferenceList();		
		Main.printOutLine("Creating modeling assistant");
		assistant = new ModellingAssistant(project.getModel());			
		ptoolbar = new ProjectToolBar(tree,frame.getDiagramManager());
		add(ptoolbar, BorderLayout.NORTH);		
		scroll.setViewportView(tree);		
		treeMap.put(project, this);		
		updateUI();		
	}

	public void setTree(ProjectTree tree)
	{
		remove(scroll);		
		this.tree = tree;
		this.tree.setBorder(new EmptyBorder(2,2,2,2));		
		this.addTreeSelectionListener(new ProjectTreeSelectionListener());	
		scroll = new JScrollPane();
		scroll.setViewportView(tree);
		add(scroll, BorderLayout.CENTER);				
		scroll.validate();
		scroll.repaint();
		this.validate();
		this.repaint();
	}
	
	public void clear()
	{
		this.project = null;
		this.refparser=null;
		this.oclDocList.clear();
		JPanel emptyTempPanel = new JPanel();
		emptyTempPanel.setBackground(Color.WHITE);
		emptyTempPanel.setBorder(new EmptyBorder(0,0, 0, 0));
		scroll.setViewportView(emptyTempPanel);		
		emptyTempPanel.setPreferredSize(new Dimension(200,250));		
		updateUI();
	}
		
	public static ProjectBrowser getProjectBrowserFor(AppFrame frame, UmlProject project) 
	{
		ProjectBrowser browser = treeMap.get(project);
		if(browser == null){
			browser = new ProjectBrowser(frame, project,null);
			treeMap.put(project, browser);			
		}
		return browser;
	}
			
	public static AlloySpecification getAlloySpecFor(UmlProject project) 
	{		
		return ProjectBrowser.getProjectBrowserFor(frame,project).alloySpec;
	}
	
	public static void setAlloySpecFor(UmlProject project, AlloySpecification alloySpec) 
	{		
		ProjectBrowser.getProjectBrowserFor(frame,project).alloySpec = alloySpec;
	}
		
	public static ArrayList<OCLDocument> getOCLDocuments(UmlProject project)
	{
		return ProjectBrowser.getProjectBrowserFor(frame,project).oclDocList;
	}
		
	public static void setOCLOptionsFor(UmlProject project, TOCL2AlloyOption oclOptions)
	{
		ProjectBrowser.getProjectBrowserFor(frame,project).oclOptions = oclOptions;
	}
	
	public static TOCL2AlloyOption getOCLOptionsFor(UmlProject project)
	{
		return ProjectBrowser.getProjectBrowserFor(frame,project).oclOptions;
	}

	public static OntoUML2AlloyOptions getOntoUMLOptionsFor(UmlProject project)
	{
		return ProjectBrowser.getProjectBrowserFor(frame,project).refOptions;
	}

	public static void setOntoUMLOptionsFor(UmlProject project, OntoUML2AlloyOptions refOptions)
	{
		ProjectBrowser.getProjectBrowserFor(frame,project).refOptions = refOptions;
	}
	
	public static AntiPatternList getAntiPatternListFor(UmlProject project)
	{
		return ProjectBrowser.getProjectBrowserFor(frame,project).antipatterns;
	}

	public static void setAntiPatternListFor(UmlProject project, AntiPatternList antipatterns)
	{
		ProjectBrowser.getProjectBrowserFor(frame,project).antipatterns = antipatterns;
	}
	
	public static ModellingAssistant getAssistantFor(UmlProject project)
	{
		return ProjectBrowser.getProjectBrowserFor(frame,project).assistant;
	}	
	
	public static InferenceList getInferences(UmlProject project) {
		return ProjectBrowser.getProjectBrowserFor(frame,project).inferences;
	}

	
	public static void setDerivations(UmlProject project, InferenceList inferences) {
		ProjectBrowser.getProjectBrowserFor(frame,project).inferences = inferences;
	}
	
	public void refreshTree()
	{				
		tree.updateUI();				
		validate();
		repaint();		
	}
	
	class ProjectTreeSelectionListener implements TreeSelectionListener 
	 {
		@Override
		public void valueChanged(TreeSelectionEvent e) 
		{
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
			if(node!=null)
			{
				if (node.getUserObject() instanceof OntoUMLElement){
					
				}
				else if (node.getUserObject()!=null && node.getParent() != null && (node.getUserObject() instanceof StructureDiagram) && !(((DefaultMutableTreeNode)node.getParent()).getUserObject() instanceof UmlProject))
				{
					 StructureDiagram diagram = ((StructureDiagram)node.getUserObject());
					 for(Component c: frame.getDiagramManager().getComponents())
					 {
						 if (c instanceof DiagramEditorWrapper){
							 if (((DiagramEditorWrapper)c).getDiagram().equals(diagram)) frame.getDiagramManager().setSelectedComponent(c);
						 }
					 }
				}
			}
		}		
	 }
}
