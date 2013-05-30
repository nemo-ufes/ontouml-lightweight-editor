package br.ufes.inf.nemo.ontouml.editor.ui.model;

import java.util.List;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import br.ufes.inf.nemo.ontouml.editor.adapter.ElementAdapter;
import br.ufes.inf.nemo.ontouml.editor.struct.Artifact;
import br.ufes.inf.nemo.ontouml.editor.struct.Project;


public class ProjectExplorerTreeModel implements TreeModel {

	private Project project;
	private List<Artifact> artifacts;
	private List<ElementAdapter> elementAdapters;
	
	public ProjectExplorerTreeModel(Project project)
	{
		this.project = project;
		updateData();
	}

	public void updateData()
	{
		artifacts = project.getAllArtifacts();
		elementAdapters = project.getModel().getAllElementAdapters();
	}
	
	public Object getChild(Object parent, int index) {
		
		if(parent == project)
		{
			if(index == 1)
				return artifacts;
			else
				return elementAdapters;
		}
		else if(parent == artifacts)
		{
			return artifacts.get(index);
		}
		else if(parent == elementAdapters)
		{
			return elementAdapters.get(index);
		}
		
		return null;
	}

	public int getChildCount(Object parent) {
		
		if(parent == project)
			return 2;
		else if(parent == artifacts)
			return artifacts.size();
		else if(parent == elementAdapters)
			return elementAdapters.size();
			
		return 0;
	}

	public int getIndexOfChild(Object parent, Object child) {
		
		if(parent == project)
		{
			if(child == artifacts)
				return 1;
			else
				return 2;
		}
		else if(parent == artifacts)
		{
			return artifacts.indexOf(child);
		}
		else if(parent == elementAdapters)
		{
			return elementAdapters.indexOf(child);
		}
		
		return -1;
	}

	public Object getRoot() {
		return project;
	}

	public boolean isLeaf(Object object) {
		return object instanceof Artifact || object instanceof ElementAdapter;
	}

	public void removeTreeModelListener(TreeModelListener treeModelListener) {

	}
	
	public void addTreeModelListener(TreeModelListener treeModelListener) {

	}
	
	public void valueForPathChanged(TreePath treePath, Object object) {

	}
}
