package br.ufes.inf.nemo.story.ui;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TreeItem;

import stories.Node_state;

public class DynamicClassEditor extends ClassEditor {

	public DynamicClassEditor(Composite parent, StoryElementTimeline storyElTl, int style) {
		super(parent,  storyElTl.getModelParser().getAntiRigidClasses() , storyElTl, style);
		// TODO Auto-generated constructor stub
	}
	
	protected EList<RefOntoUML.Class> unpackElementYesClasses(TreeItem ti){
		return ((Node_state)ti.getData()).getAntiRigidClasses();
		
	}
	protected EList<RefOntoUML.Class> unpackElementNoClasses(TreeItem ti){
		return new BasicEList<RefOntoUML.Class>();
		
	}

}
