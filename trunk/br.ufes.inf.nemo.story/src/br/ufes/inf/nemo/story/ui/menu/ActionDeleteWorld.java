package br.ufes.inf.nemo.story.ui.menu;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import br.ufes.inf.nemo.story.ui.StoryElementTimeline;

public class ActionDeleteWorld extends Action {
	final private StoryElementTimeline setml;
	private int selectedColumn = -1;
	public ActionDeleteWorld(StoryElementTimeline storyElementTimeline){
		super("Delete World");
		setml = storyElementTimeline;
	}
	public void run(){
		setml.deleteWorldColumn(selectedColumn);		
	}
	public void setSelectedColumn(int selectedColumn) {
		this.selectedColumn = selectedColumn;
		
	}

}
