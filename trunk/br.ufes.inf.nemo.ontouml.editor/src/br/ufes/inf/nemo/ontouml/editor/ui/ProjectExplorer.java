package br.ufes.inf.nemo.ontouml.editor.ui;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTree;

import br.ufes.inf.nemo.ontouml.editor.plugin.ApplicationHandler;
import br.ufes.inf.nemo.ontouml.editor.plugin.Tool;
import br.ufes.inf.nemo.ontouml.editor.struct.Project;
import br.ufes.inf.nemo.ontouml.editor.struct.ProjectEvent;
import br.ufes.inf.nemo.ontouml.editor.struct.ProjectListener;

@SuppressWarnings("serial")
public class ProjectExplorer extends JScrollPane implements Tool, ProjectListener {

	private ApplicationHandler handler;
	private JTree tree;
	//private TreeModel treeModel;
	
	public ProjectExplorer()
	{
		super();
		initGUI();
	}
	
	private void initGUI()
	{
		{
			tree = new JTree();
		    add(tree);
		}
	}
	
	public JComponent getUIComponent() {
		return this;
	}

	public void registerApplicationHandler(ApplicationHandler handler) {
		this.handler = handler;
	}

	public void notifyEvent(ProjectEvent projectEvent) {
		Project project = handler.getSelectedProject();
		project.addProjectListener(this);
		//treeModel = new BaseTreeModel(project);
		//treeModel.updateData();
	}
}
