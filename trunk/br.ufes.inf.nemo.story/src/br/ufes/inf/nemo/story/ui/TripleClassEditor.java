package br.ufes.inf.nemo.story.ui;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TreeItem;

import stories.Individual;
import stories.impl.LinkImpl;
import stories.impl.NodeImpl;
import stories.impl.Node_stateImpl;
import RefOntoUML.Classifier;
import RefOntoUML.parser.OntoUMLNameHelper;

//TODO: create a abstract class for the three classes used below
public class TripleClassEditor {
	TripleClassEditor(Composite sashForm, final StoryElementTimeline storyElTl){
		final ScrolledComposite sc = new ScrolledComposite(sashForm, SWT.H_SCROLL | SWT.V_SCROLL);
		sc.setExpandHorizontal(true);
		sc.setExpandVertical(true);
		
		
		final Composite charEditor = new Composite(sc, SWT.BORDER);
	    final StackLayout stacklayout =new StackLayout( ); 
	    charEditor.setLayout(stacklayout);
	    charEditor.setBackground(new Color(sashForm.getDisplay(), 240, 240, 240));
	    
	    Label lblRigidTypes = new Label(charEditor, SWT.NONE);
		lblRigidTypes.setText("Rigid Types:");
		 
		 
		final Composite linkClassEditor = new Composite(charEditor,  SWT.NONE);
		linkClassEditor.setLayout(new GridLayout());
		
	    HashMap<Classifier,CLabel> LinkClassCheckboxes = new HashMap<Classifier,CLabel>();
	    Set<Classifier> slinks = storyElTl.getModelParser().getAssociations();
	    for(Classifier c:slinks){
	    	//if (c.getName() == null){
	    		c.setName(OntoUMLNameHelper.getCompleteName(c));
	    //}
	    }
	    List<Classifier> linkclassList = new LinkedList<Classifier>(slinks);
	    Collections.sort(linkclassList, new Comparator<Classifier>(){
	    	@Override
	    	public int compare(Classifier c1, Classifier c2){
	    		return (c1.getName().compareTo(c2.getName()));
	    	}
	    });
	    for(Classifier c : linkclassList){
	    	final CLabel l = new CLabel(linkClassEditor,SWT.NONE);
	    	l.setImage(storyElTl.getImgUnchecked());
	    	l.setText(c.getName());
	    	l.setData(c);
	    	LinkClassCheckboxes.put(c, l);
	    	l.addListener(SWT.MouseDown,new CheckboxClassListener(storyElTl));	    	
	    }
	    		
	    
	    final Composite antiRigidClassEditor = new Composite(charEditor,  SWT.NONE );
	    antiRigidClassEditor.setLayout(new GridLayout());
	    HashMap<Classifier,CLabel> antiRigidClassCheckboxes = new HashMap<Classifier,CLabel>();
	    Set<Classifier> s2 = storyElTl.getModelParser().getAntiRigidClasses();
	    List<Classifier> arclassList = new LinkedList<Classifier>(s2);
	    Collections.sort(arclassList, new Comparator<Classifier>(){
	    	@Override
	    	public int compare(Classifier c1, Classifier c2){
	    		return (c1.getName()).compareTo(c2.getName());
	    		
	    	}
	    });
	    for(Classifier c : arclassList){
	    	final CLabel l = new CLabel(antiRigidClassEditor,SWT.NONE);
	    	l.setImage(storyElTl.getImgUnchecked());
	    	l.setText(c.getName());
	    	l.setData(c);
	    	antiRigidClassCheckboxes.put(c, l);
	    	l.addListener(SWT.MouseDown,new CheckboxClassListener(storyElTl));	    	
	    }
	    
	    final Composite rigidClassEditor = new Composite(charEditor, SWT.NONE );
	    rigidClassEditor.setLayout(new GridLayout());
	    HashMap<Classifier,CLabel> ClassCheckboxes = new HashMap<Classifier,CLabel>();
	    Set<Classifier> s = storyElTl.getModelParser().getRigidClasses();
	    List<Classifier> classList = new LinkedList<Classifier>(s);
	    Collections.sort(classList, new Comparator<Classifier>(){
	    	@Override
	    	public int compare(Classifier c1, Classifier c2){
	    		return (c1.getName()).compareTo(c2.getName());
	    	}
	    });
	    for(Classifier c : classList){
	    	final CLabel l = new CLabel(rigidClassEditor,SWT.NONE);
	    	l.setImage(storyElTl.getImgUnchecked());
	    	l.setText(c.getName());
	    	l.setData(c);
	    	ClassCheckboxes.put(c, l);
	    	l.addListener(SWT.MouseDown,new CheckboxClassListener(storyElTl));	    	
	    }
	    	    
	    stacklayout.topControl = linkClassEditor;
	    sc.setContent(charEditor);
	    sc.setMinSize(linkClassEditor.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	    //Listener below changes the screen for the class editor
	    storyElTl.getTree().addListener (SWT.Selection, new Listener(){

			@Override
			public void handleEvent(Event e) {
				if(storyElTl.getTree().getSelection().length == 0){
					//no items selected. Grey out the charEditor area.
				}
				else if (storyElTl.getTree().getSelection().length >0){
					//multiple itens selected.
					//check for item kind
					//	if they are of different kinds (node x link x node_state), greyout the char editor area TODO:(maybe give a message in the editor area?)
					//	else, if they are of the same kind, update the conflicting instantiations to the indeterminate state (square).
					
					//the first item is considered the standard.
					Class<? extends Object> cl = storyElTl.getTree().getSelection()[0].getData().getClass();
					for(TreeItem o : storyElTl.getTree().getSelection()){
						if(cl != o.getData().getClass()){
							cl = null; //mark the selection as inconsistent
							System.out.println("bad selection");
							break;
						}
					}
					if(cl!=null){
						System.out.println("Good selection = "+ cl);
						//Good selection. The instantiated classes will be checked and the checkboxes updated.
						//in case of inconsitent checkbox states, a box will be used.
						//any change, including boxes, affect the whole selection.
						if(cl == NodeImpl.class){
							stacklayout.topControl = rigidClassEditor;
							sc.setMinSize(rigidClassEditor.computeSize(SWT.DEFAULT, SWT.DEFAULT));
							
						}
						else if (cl == LinkImpl.class){
							stacklayout.topControl = linkClassEditor;
							sc.setMinSize(linkClassEditor.computeSize(SWT.DEFAULT, SWT.DEFAULT));
							
						}
						else if (cl == Node_stateImpl.class){
							stacklayout.topControl = antiRigidClassEditor;
							sc.setMinSize(antiRigidClassEditor.computeSize(SWT.DEFAULT, SWT.DEFAULT));
							
						}
					}else{
						System.out.println("Grey out!");
					}
				}
				 
				charEditor.layout();
					
			}
			
	    });   
	}
}
