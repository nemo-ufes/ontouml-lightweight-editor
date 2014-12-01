package br.ufes.inf.nemo.story;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import stories.Individual;
import stories.Node_state;
import stories.World;

public class CheckboxClickListener implements Listener {   
	private final WorldList world_sequence;
	private final Image imgYes;
	private final Image imgNo;
	private final Image imgUnchecked;
	private final Tree tree;
	
	public CheckboxClickListener(Tree tree, WorldList world_sequence, Image imgYes,Image imgNo, Image imgUnchecked){
		this.world_sequence = world_sequence;
		this.imgYes = imgYes;
		this.imgNo = imgNo;
		this.imgUnchecked = imgUnchecked;
		this.tree = tree;
	}
    	//these methods change the existance state for the nodes and node_states but they are dependent on the order of the checkbox clicking.
    	protected void yesWorld(Object data, int world_index) {
    		
    		World w = world_sequence.get(world_index);
    		if(data.getClass() == Individual.class){
    			Individual i = (Individual)data;
    			i.getPresent_in().add(w);
    		}else if( data.getClass() == Node_state.class){
    			Node_state i = (Node_state)data;
    			i.getClassified_in().add(w);
    		}
    	}
    	
    	protected void noWorld(Object data, int world_index) {
    		
    		World w = world_sequence.get(world_index);
    		if(data.getClass() == Individual.class){
    			Individual i = (Individual)data;
    			i.getPresent_in().remove(w);
    			i.getAbsent_from().add(w);
    		}else if( data.getClass() == Node_state.class){
    			Node_state i = (Node_state)data;
    			i.getClassified_in().remove(w);
    			i.getNot_classified_in().add(w);
    		}
    	}
    	
    	protected void undefWorld(Object data, int world_index) {
    		
    		World w = world_sequence.get(world_index);
    		if(data.getClass() == Individual.class){
    			Individual i = (Individual)data;
    			i.getAbsent_from().remove(w);
    		}else if( data.getClass() == Node_state.class){
    			Node_state i = (Node_state)data;
    			i.getNot_classified_in().remove(w);
    		}
    	}
    	
    	@Override
		public void handleEvent (Event event) {
			Point point = new Point (event.x, event.y);
			TreeItem item = tree.getItem (point);
			if (item != null) {
				int column = getColumn(point,item);
				Image img = item.getImage(column);
				//The images are used as state identifiers
				if(img == imgUnchecked){
					item.setImage(column,imgYes);
					yesWorld(item.getData(),column-1);
				}else if (img == imgYes){
					item.setImage(column,imgNo);
					noWorld(item.getData(),column-1);
				}else if (img == imgNo){
					item.setImage(column,imgUnchecked);
					undefWorld(item.getData(),column-1);						
				}
				
			}
		}
	public static int getColumn( Point pt, TreeItem item )
	{
	    int columns = item.getParent().getColumnCount();
	    for (int i=0; i<columns; i++)
	    {
	    	Rectangle rect = item.getBounds (i);
	    	if ( pt.x >= rect.x && pt.x < rect.x + rect.width ) return i;
	    }
	    return -1;
	}
}
