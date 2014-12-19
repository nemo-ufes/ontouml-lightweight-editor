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

import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.oled.AppFrame;
import br.ufes.inf.nemo.oled.Main;
import br.ufes.inf.nemo.oled.model.AlloySpecification;
import br.ufes.inf.nemo.oled.model.AntiPatternList;
import br.ufes.inf.nemo.oled.model.InferenceList;
import br.ufes.inf.nemo.oled.model.OCLDocument;
import br.ufes.inf.nemo.oled.model.UmlProject;
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
			
	public void addTreeSelectionListener(TreeSelectionListener selectionListener) {
		tree.addTreeSelectionListener(selectionListener);
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
	
	public void refreshTree()
	{				
		tree.updateUI();				
		validate();
		repaint();		
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
	
	public OntoUMLParser getParser() { return refparser; }
	public void setParser(OntoUMLParser refparser) { this.refparser = refparser; }
	public ArrayList<OCLDocument> getOCLDocuments(){ return oclDocList; }
	public UmlProject getProject(){ return project; }	
	public ProjectTree getTree() { return tree; }
	
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
	
	public AlloySpecification getAlloySpec() { return alloySpec; }
	public void setAlloySpec(AlloySpecification alloySpec) { this.alloySpec = alloySpec; }
	
	public TOCL2AlloyOption getOCLOption() { return oclOptions; }
	public void setOCLOption(TOCL2AlloyOption oclOptions) { this.oclOptions = oclOptions; }
	
	public OntoUML2AlloyOptions getOntoUMLOption() { return refOptions; }
	public void setOntoUMLOption(OntoUML2AlloyOptions refOptions) { this.refOptions = refOptions; }
	
	public AntiPatternList getAntiPatternList() { return antipatterns; }
	public void setAntiPatternList(AntiPatternList antipatterns) { this.antipatterns = antipatterns; }
	
	public InferenceList getInferences() { return inferences; }	
	public void setInferences(InferenceList inferences) {this.inferences = inferences; }
		
	class ProjectTreeSelectionListener implements TreeSelectionListener 
	{
		@Override
		public void valueChanged(TreeSelectionEvent e) 
		{
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
			if(node!=null)
			{				
				if (node.getUserObject()!=null && node.getParent() != null && (node.getUserObject() instanceof StructureDiagram) && !(((DefaultMutableTreeNode)node.getParent()).getUserObject() instanceof UmlProject))
				{
					frame.getDiagramManager().select((StructureDiagram)node.getUserObject());
				}
				else if(node.getUserObject()!=null && node.getParent() != null && (node.getUserObject() instanceof OCLDocument) && (((DefaultMutableTreeNode)node.getParent()).getUserObject() instanceof OCLDocument))
				{					
					frame.getDiagramManager().select((OCLDocument)node.getUserObject());
				}
			}
		}		
	}
}
