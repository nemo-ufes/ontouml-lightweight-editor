package br.ufes.inf.nemo.story.ui;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.TreeItem;






import stories.Node;
import RefOntoUML.Classifier;
import RefOntoUML.parser.OntoUMLParser;

public abstract class ClassEditor extends Composite {
	private HashMap<Classifier,CLabel> labelClass = new HashMap<Classifier,CLabel>();
	private final StoryElementTimeline storyElTl;
	public ClassEditor(Composite parent, Set<Classifier> set, StoryElementTimeline stl, int style) {
		super(parent, style);
		this.setLayout(new GridLayout());
		this.storyElTl = stl;
		List<Classifier> classList = new LinkedList<Classifier>(set);
		final OntoUMLParser parser = storyElTl.getModelParser();
	    Collections.sort(classList, new Comparator<Classifier>(){
	    	@Override
	    	public int compare(Classifier c1, Classifier c2){
	    		return (parser.getAlias(c1).compareTo(parser.getAlias(c2)));
	    	}
	    });
	    for(Classifier c : classList){
	    	final CLabel l = new CLabel(this,SWT.NONE);
	    	l.setImage(storyElTl.getImgUnchecked());
	    	l.setText(parser.getAlias(c));
	    	l.setData(c);
	    	labelClass.put(c,l);
	    	l.addListener(SWT.MouseDown,new CheckboxClassListener(storyElTl));	    	
	    }
		
	}
	protected void resetCheckBoxes(){
		for(Control c : this.getChildren()){
			if(c.getClass() == CLabel.class){
				((CLabel)c).setImage(null);
			}
		}
	}
	public void setCheckBoxes(TreeItem[] s) {
		resetCheckBoxes();
		
		CounterHashMap counter = new CounterHashMap();
		
		for(TreeItem ti: s){
			for(RefOntoUML.Classifier c : unpackElementYesClasses(ti)){				
				if(this.getLabel(c) != null){					
					this.setLabelYes(this.getLabel(c));
					counter.increment(this.getLabel(c));
				}
				else
				{
					System.out.println(c+" not found");
				}	
			}
			for(RefOntoUML.Classifier c : unpackElementNoClasses(ti)){				
				if(this.getLabel(c) != null){					
					this.setLabelNo(this.getLabel(c));					
					counter.increment(this.getLabel(c));
				}
				else
				{
					System.out.println(c+" not found");
				}
			}
		}
		//in case a label was set either positive or negative, this checks if there is a conflict with unchecked checkboxes 
		for(Control c : this.getChildren()){
			if(c.getClass() == CLabel.class){
				if(counter.get((CLabel)c) != null && counter.get((CLabel)c) < s.length){
					this.setLabelIndeterminate((CLabel)c);
				}
			}
		}
		fillBlankCheckBoxes();

	}
	
	abstract protected EList<? extends RefOntoUML.Classifier> unpackElementYesClasses(TreeItem ti);
	abstract protected EList<? extends RefOntoUML.Classifier> unpackElementNoClasses(TreeItem ti);
	
	protected CLabel getLabel(Classifier c){
		return labelClass.get(c);
	}

	protected void setLabelYes(CLabel label) {
		if(label.getImage() == null){
			label.setImage(storyElTl.getImgYes());
			return;
		}
		if (label.getImage() != storyElTl.getImgYes()){
			//if it's not a Yes, and it's set, there's an inconsistent state between the selection.
			System.out.println("teste2");
			label.setImage(storyElTl.getImgIndeterminate());
			return;
		}
		
	}
	protected void setLabelNo(CLabel label) {
		if(label.getImage() == null){
			label.setImage(storyElTl.getImgNo());
			return;
		}
		if (label.getImage() != storyElTl.getImgNo()){
			//if it's not a No, and it's set, there's an inconsistent state between the selection.
			System.out.println("teste1");
			label.setImage(storyElTl.getImgIndeterminate());
			return;
		}
		
	}
	protected void setLabelIndeterminate(CLabel c) {
		c.setImage(storyElTl.getImgIndeterminate());
		
	}
	protected void fillBlankCheckBoxes() {
		for(Control c : this.getChildren()){
			if(c.getClass() ==  CLabel.class){
				if(((CLabel)c).getImage() == null)	((CLabel)c).setImage(storyElTl.getImgUnchecked());
			}
		}
	}
	protected class CounterHashMap extends java.util.HashMap<CLabel,java.lang.Integer>{
		public void increment(CLabel label){
			if( this.get(label) != null){
				this.put(label, (this.get(label)) +1);
			}else{
				this.put(label, new Integer(1));
			}
		}
	}
}
