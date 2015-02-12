package br.ufes.inf.nemo.story.ui.menu;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import stories.Link;
import stories.Node;
import br.ufes.inf.nemo.story.ui.StoryElementTimeline;

public class ActionAddLinkToSelection extends Action {
	final private StoryElementTimeline setml;
	final private Tree tree;
	final private TreeItem[] selection;
	public ActionAddLinkToSelection(StoryElementTimeline storyElementTimeline, TreeItem[] s){
		super("Add Link");
		tree = storyElementTimeline.getTree();
		setml = storyElementTimeline;
		selection = s;
	}
	public void run(){
		TreeItem [] selected = tree.getSelection();
        
        if (selected.length > 0)
        {
          TreeItem parent = selected[0].getParentItem();
          if (parent == null)
          {	        	  
	          int index = tree.indexOf(tree.getSelection()[0]);
	          Link l = (Link) (setml.createLink(tree, index+1)).getData();
	          l.setSource((Node) selection[0].getData());
	          l.setTarget((Node) selection[1].getData());
          }          
        }else{
          //nothing selected. Insert at beggining
          setml.createLink(tree, 0);
          
      	}
		
	}
}
