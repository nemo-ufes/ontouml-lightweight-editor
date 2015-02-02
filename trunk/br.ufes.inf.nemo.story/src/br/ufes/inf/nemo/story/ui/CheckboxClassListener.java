package br.ufes.inf.nemo.story.ui;

import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TreeItem;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Class;
import stories.Link;
import stories.Node;
import stories.Node_state;
import stories.impl.LinkImpl;
import stories.impl.NodeImpl;
import stories.impl.Node_stateImpl;

public class CheckboxClassListener implements Listener {   
	private final StoryElementTimeline parent;
	
	public CheckboxClassListener(StoryElementTimeline p){		
		this.parent = p;
	}
    	
    	@Override
		public void handleEvent (Event event) {
    		CLabel classCheckbox = (CLabel) event.widget;
    		Image img = classCheckbox.getImage();
    		System.out.println(classCheckbox.getData());
			//The images are used as state identifiers
			if(img == parent.getImgUnchecked()){
				classCheckbox.setImage(parent.getImgYes());
				doYes((Classifier)classCheckbox.getData());
			}else if (img == parent.getImgYes()){
				classCheckbox.setImage(parent.getImgNo());
				doNo((Classifier)classCheckbox.getData());
			}else if (img == parent.getImgNo() || img == parent.getImgIndeterminate()){
				classCheckbox.setImage(parent.getImgUnchecked());
				doUncheck((Classifier)classCheckbox.getData());						
			}
			
		}
		private void doYes(Classifier classifier) {
			for(TreeItem ti : parent.getSelected()){
				Object data = ti.getData();
				if(data.getClass() == NodeImpl.class){
					Node n = (Node)data;
					n.getInstance_of().add((Class)classifier);
				}else if(data.getClass() == LinkImpl.class){
	    			Link l = (Link)data;
	    			l.getInstance_of().add((Association)classifier);
	    		}else if( data.getClass() == Node_stateImpl.class){
	    			Node_state ns = (Node_state)data;
	    			ns.getAntiRigidClasses().add((Class)classifier);
	    		}
			}
		}
		private void doNo(Classifier classifier) {
			for(TreeItem ti : parent.getSelected()){
				Object data = ti.getData();
				if(data.getClass() == NodeImpl.class){
					Node n = (Node)data;
					n.getInstance_of().remove((Class)classifier);
					n.getNot_instance_of().add((Class)classifier);
				}else if(data.getClass() == LinkImpl.class){
	    			Link l = (Link)data;
	    			l.getInstance_of().remove((Association)classifier);
	    			l.getNot_instance_of().add((Association)classifier);
	    		}else if( data.getClass() == Node_stateImpl.class){
	    			Node_state ns = (Node_state)data;
	    			ns.getAntiRigidClasses().remove((Class)classifier);
	    			
	    		}
			}
		}
		private void doUncheck(Classifier classifier) {
			for(TreeItem ti : parent.getSelected()){
				Object data = ti.getData();
				if(data.getClass() == NodeImpl.class){
					Node n = (Node)data;
					n.getNot_instance_of().remove((Class)classifier);
					n.getInstance_of().remove((Class)classifier);
				}else if(data.getClass() == LinkImpl.class){
	    			Link l = (Link)data;
	    			l.getNot_instance_of().remove((Association)classifier);
	    		}else if( data.getClass() == Node_stateImpl.class){
	    			Node_state ns = (Node_state)data;
	    			//ns.getAntiRigidClasses().add((Class)classifier);
	    		}
			}
		}
		
		
		
}
