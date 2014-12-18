package br.ufes.inf.nemo.story.ui;

import org.eclipse.emf.common.util.EList;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TreeItem;

import stories.Link;
import RefOntoUML.Association;

public class AssociationEditor extends ClassEditor {

	public AssociationEditor(Composite parent, StoryElementTimeline storyElTl, int style) {
		super(parent,  storyElTl.getModelParser().getAssociations(), storyElTl, style);
	}
	
	protected EList<Association> unpackElementYesClasses(TreeItem ti){
		return ((Link)ti.getData()).getInstance_of();
		
	}
	protected EList<Association> unpackElementNoClasses(TreeItem ti){
		return ((Link)ti.getData()).getNot_instance_of();
		
	}
}
