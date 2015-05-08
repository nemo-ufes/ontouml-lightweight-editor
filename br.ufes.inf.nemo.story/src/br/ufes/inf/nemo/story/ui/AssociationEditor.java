package br.ufes.inf.nemo.story.ui;

import org.eclipse.emf.common.util.EList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TreeItem;

import stories.Link;
import RefOntoUML.Association;

public class AssociationEditor extends ClassEditor {
	private CLabel sourceLabel;
	private CLabel targetLabel;
	public AssociationEditor(Composite parent, StoryElementTimeline storyElTl, int style) {
		super(parent,  storyElTl.getModelParser().getAssociations(), storyElTl, style);
		sourceLabel = new CLabel(this,SWT.NONE);
		targetLabel = new CLabel(this,SWT.NONE);
		sourceLabel.setText("Source :");
		targetLabel.setText("Target :");
		targetLabel.moveAbove(this.getChildren()[0]);
		sourceLabel.moveAbove(this.getChildren()[0]);
	}
	
	protected EList<Association> unpackElementYesClasses(TreeItem ti){
		return ((Link)ti.getData()).getInstance_of();
		
	}
	protected EList<Association> unpackElementNoClasses(TreeItem ti){
		return ((Link)ti.getData()).getNot_instance_of();
		
	}

	public void setSourceTarget(TreeItem[] selection) {
		//called to update the Editor Screen w/ the source and target of the selection.
		if(selection.length == 1){
			Link l = (Link)selection[0].getData() ;
			if ( l != null){
				if( l.getSource() != null){
					sourceLabel.setText("Source : " + ((Link)selection[0].getData()).getSource().getLabel());
				}else{
					sourceLabel.setText("Source : unspecified");
				}
				if ( l.getTarget() != null){
					targetLabel.setText("Target : " + ((Link)selection[0].getData()).getTarget().getLabel());
				}else{
					
				}
				
			}else{
				sourceLabel.setText("Source : unspecified");
				targetLabel.setText("Target: unspecified");
			}
			sourceLabel.pack();
			targetLabel.pack();
		}
		
	}
}
