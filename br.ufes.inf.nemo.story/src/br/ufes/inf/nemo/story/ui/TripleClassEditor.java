package br.ufes.inf.nemo.story.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TreeItem;

import stories.impl.LinkImpl;
import stories.impl.NodeImpl;
import stories.impl.Node_stateImpl;

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
	    final CLabel badSelection = new CLabel(charEditor, SWT.NONE);
	    badSelection.setText("Selection includes elements of different types. Select only Nodes or Links or States");
	    final CLabel noSelection = new CLabel(charEditor, SWT.NONE);
	    noSelection.setText("Select items on the tree above and edit their classification here");
	    stacklayout.topControl = noSelection;
	    sc.setContent(charEditor);
	    sc.setMinSize(stacklayout.topControl.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	    charEditor.layout();
	    //Listener below changes the screen for the class editor
	    storyElTl.getTree().addListener (SWT.Selection, new Listener(){
			@Override
			public void handleEvent(Event e) {
				if(storyElTl.getTree().getSelection().length == 0){
					//no items selected. Grey out the charEditor area.
					stacklayout.topControl = noSelection;
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
							break;
						}
					}
					if(cl!=null){
						
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
						
						stacklayout.topControl = badSelection;
						/*Solution below not effective since CLabels do not behave as expected when setEnabled(false), they do not grey out.
						 * Additionally, we would need to grey out the checkboxes too so going with the topControl replacement seems better right now
						 * stacklayout.topControl.setEnabled(false);
						for (Control l :((Composite)(stacklayout.topControl)).getChildren()){
							System.out.println(l);
							l.setEnabled(false);
						}
						*/
					}
				}
				 
				charEditor.layout();
					
			}
			
	    });   
	}
}
