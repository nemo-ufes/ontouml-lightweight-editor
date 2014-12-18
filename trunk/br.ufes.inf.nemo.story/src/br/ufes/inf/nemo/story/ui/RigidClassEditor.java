package br.ufes.inf.nemo.story.ui;

import org.eclipse.emf.common.util.EList;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TreeItem;

import stories.Node;

public class RigidClassEditor extends ClassEditor {

	public RigidClassEditor(Composite parent, StoryElementTimeline storyElTl, int style) {
		super(parent, storyElTl.getModelParser().getRigidClasses(), storyElTl, style);
	}
	
	protected EList<RefOntoUML.Class> unpackElementYesClasses(TreeItem ti){
		return ((Node)ti.getData()).getInstance_of();
		
	}
	protected EList<RefOntoUML.Class> unpackElementNoClasses(TreeItem ti){
		return ((Node)ti.getData()).getNot_instance_of();
		
	}

}
