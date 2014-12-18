package br.ufes.inf.nemo.story.ui;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
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
import stories.Story_element;
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
		 
		 
		final ClassEditor linkClassEditor = new AssociationEditor(charEditor, storyElTl, SWT.NONE);
	    final ClassEditor antiRigidClassEditor = new DynamicClassEditor(charEditor, storyElTl, SWT.NONE );
	    final ClassEditor rigidClassEditor = new RigidClassEditor(charEditor, storyElTl, SWT.NONE );
	    	    
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
							rigidClassEditor.setCheckBoxes( storyElTl.getTree().getSelection());
							
						}
						else if (cl == LinkImpl.class){
							stacklayout.topControl = linkClassEditor;
							sc.setMinSize(linkClassEditor.computeSize(SWT.DEFAULT, SWT.DEFAULT));
							linkClassEditor.setCheckBoxes(  storyElTl.getTree().getSelection());
						}
						else if (cl == Node_stateImpl.class){
							stacklayout.topControl = antiRigidClassEditor;
							sc.setMinSize(antiRigidClassEditor.computeSize(SWT.DEFAULT, SWT.DEFAULT));
							antiRigidClassEditor.setCheckBoxes( storyElTl.getTree().getSelection());
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
