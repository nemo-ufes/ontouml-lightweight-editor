package br.ufes.inf.nemo.story.ui.menu;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import br.ufes.inf.nemo.story.ui.StoryElementTimeline;

public class ActionDeleteSelection extends Action {
	final private StoryElementTimeline setml;
	final private Tree tree;
	public ActionDeleteSelection(StoryElementTimeline storyElementTimeline){
		super("Delete Selection");
		tree = storyElementTimeline.getTree();
		setml = storyElementTimeline;
	}
	public void run(){
		TreeItem [] selected = tree.getSelection();
		tree.deselectAll();
        if (selected.length > 0)
        {
          for( TreeItem i : selected){
        	  setml.deleteStoryElement(i);
          }
        }
        Event event = new Event();
        event.widget = tree;
        event.display = tree.getDisplay();
        event.type = SWT.Selection;
        tree.notifyListeners(SWT.Selection, event);
        //tree.//.deselectAll();// SWT.Selection
	}
}
