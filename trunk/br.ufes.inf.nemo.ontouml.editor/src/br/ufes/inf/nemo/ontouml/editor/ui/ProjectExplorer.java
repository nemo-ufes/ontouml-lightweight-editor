package br.ufes.inf.nemo.ontouml.editor.ui;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.tree.DefaultMutableTreeNode;

import org.eclipse.emf.ecore.EObject;

import br.ufes.inf.nemo.ontouml.editor.plugin.ApplicationHandler;
import br.ufes.inf.nemo.ontouml.editor.plugin.Tool;
import br.ufes.inf.nemo.ontouml.editor.struct.Project;
import br.ufes.inf.nemo.ontouml.editor.struct.ProjectEvent;
import br.ufes.inf.nemo.ontouml.editor.struct.ProjectListener;
import br.ufes.inf.nemo.ontouml.editor.ui.model.OntoReferenceElement;
import br.ufes.inf.nemo.ontouml.editor.ui.model.ProjectTree;

@SuppressWarnings("serial")
public class ProjectExplorer extends JScrollPane implements Tool, ProjectListener {

	private ApplicationHandler handler;
	private ProjectTree tree;
		
	public ProjectExplorer()
	{
		super();
		initGUI();
	}
	
	private void initGUI()
	{
		{
			setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		}
	}
	
	public void refreshTree() {
		tree.updateUI();		
		validate();
		repaint();
	}
	
	public JComponent getUIComponent() {
		return this;
	}

	public void registerApplicationHandler(ApplicationHandler handler) {
		this.handler = handler;
	}

	public void setProject(Project project)
	{
		if (tree!=null) remove(tree);
		
		EObject model = project.getModel();
		OntoReferenceElement rootElem = new OntoReferenceElement(model);
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(rootElem);
		
		tree = new ProjectTree(root,1);
		
		setViewportView(tree);
		
		refreshTree();
	}
	
	public void notifyEvent(ProjectEvent projectEvent) 
	{
		Project project = handler.getSelectedProject();
		project.addProjectListener(this);
				
		setProject(project);
	}
}
