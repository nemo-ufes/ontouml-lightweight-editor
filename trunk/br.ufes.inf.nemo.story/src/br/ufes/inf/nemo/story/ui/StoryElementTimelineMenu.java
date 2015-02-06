package br.ufes.inf.nemo.story.ui;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class StoryElementTimelineMenu extends Menu {
	final private StoryElementTimeline setml;
	final private Tree tree;
	final private MenuManager menuManager;
	public StoryElementTimelineMenu(StoryElementTimeline storyElementTimeline) {
		super(storyElementTimeline.getTree());
		tree = storyElementTimeline.getTree();
		setml = storyElementTimeline;
		this.addMenuOptions();
		menuManager = new MenuManager(); //http://help.eclipse.org/indigo/index.jsp?topic=%2Forg.eclipse.platform.doc.isv%2Fguide%2Fjface_actions.htm
	}
	
	//only used at the constructor
		private void addMenuOptions() {
			final MenuItem addElementMenuItem = new MenuItem(this, SWT.NONE);
		    addElementMenuItem.addSelectionListener(new SelectionAdapter() {
		      public void widgetSelected(final SelectionEvent e)
		      {
		        TreeItem [] selected = tree.getSelection();
		        TreeItem item ;
		        if (selected.length > 0)
		        {
		          TreeItem parent = selected[0].getParentItem();
		          System.out.println("Insert Before - " + selected[0].getText());
		          
		          if (parent == null)
		          {	        	  
			          int index = tree.indexOf(selected[0]);
			          item = setml.createStoryElement(tree, index+1);
			           
		          }else{	        	  
			          int index = parent.indexOf(selected[0]);
			          item = setml.createStoryElement(parent, index+1);
			          
		          }
		          
		        }
		        else{
		          System.out.println("Insert Before - nothing selected. Insert at beggining");
		          item = setml.createStoryElement(tree, 0);
		      	}
		        item.setText("abv");  
		      }
		      
		    });
		    addElementMenuItem.setText("Add New");
			
		}
}
