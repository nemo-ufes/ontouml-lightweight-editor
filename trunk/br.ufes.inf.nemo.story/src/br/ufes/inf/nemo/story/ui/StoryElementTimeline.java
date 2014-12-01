package br.ufes.inf.nemo.story.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

import br.ufes.inf.nemo.story.CheckboxClickListener;
import br.ufes.inf.nemo.story.WorldList;
import stories.Node;
import stories.Node_state;
import stories.StoriesFactory;
import stories.World;

public class StoryElementTimeline {	
	private final Tree tree;
	//images for the world buttons (exists, doen't exist and unspecified)
	final Image imgYes;
	final Image imgNo;
	final Image imgUnchecked;
	//EMF handler
	static StoriesFactory storyFactory = StoriesFactory.eINSTANCE;
	private WorldList world_sequence;
	
	StoryElementTimeline(Composite parent, int style){
		//this tree holds every story element. The story element is a row and the first column contains the element name. The other columns contain buttons that determine in which worlds the element exists
		tree = new Tree(parent, style);
		tree.setHeaderVisible(true);
		//this column holds the name of the story elements
		TreeColumn column = new TreeColumn(tree, SWT.CENTER);
	    column.setText("Story Elements");
	    column.setWidth(100);
	    //images for the world buttons (exists, doen't exist and unspecified)
	    imgYes = new Image(parent.getDisplay(), "resources/yes.png");
	    imgNo = new Image(parent.getDisplay(), "resources/no.png");
	    imgUnchecked = new Image(parent.getDisplay(), "resources/unckeched.png");
	    
	    final Menu menu = new Menu(tree);
	    tree.setMenu(menu);
	    addMenuOptions(menu);
	        
	    
	    //EMF world list
	    world_sequence = new WorldList();	    
	    

	    //mouse events for the 3 state checkboxes. 
	    tree.addListener (SWT.MouseDown,new CheckboxClickListener(tree,world_sequence,imgYes,imgNo,imgUnchecked)  );
	    
	}
	
	// ------------------------------------------------------------------------
	// --- This method creates a new World and the Column in the tree		---
	// --- The buttons to check if an element exists at a given world are	---
	// --- added for each row												---
	public void createWorld() {
		TreeColumn column = new TreeColumn(this.getTree(), SWT.CENTER );		
	    column.setText("World "+this.world_sequence.size());
	    column.setWidth(60);
	    column.setMoveable(true);
	    World w = storyFactory.createWorld();
	    this.world_sequence.add(w);
	    this.addNewColumnWorldButtons();
	}
	// ------------------------------------------------------------------------
	
	

	// ------------------------------------------------------------------------
	// --- Methods to create story elements (Rows in the tree)				---
	// --- the ones that receive Trees as parameters are Nodes or Links 	---
	// --- the ones that receive TreeItens are node_states 					---
	// --- choose either to add at the end of the list or at an index		---	
	public TreeItem createStoryElement(Tree parent){
		
		return createStoryElement(parent, parent.getItemCount());	
	}
	public TreeItem createStoryElement(Tree parent, int index){
		TreeItem item = new TreeItem(parent, SWT.CENTER, index);
		Node n = storyFactory.createNode();
		n.setLabel("Node "+parent.getItemCount());
		item.setData(n);
		return addNewRowWorldButtons(item,"Node "+parent.getItemCount());
	}
	public TreeItem createStoryElement(TreeItem parent){
		return createStoryElement(parent, parent.getItemCount());
	}
	public TreeItem createStoryElement(TreeItem parent, int index){
		TreeItem item = new TreeItem(parent, SWT.CENTER, index);
		Node_state n = storyFactory.createNode_state();
		n.setLabel("State "+ parent.getItemCount());
		item.setData(n);
		return addNewRowWorldButtons(item,"State "+ parent.getItemCount());
	}
	// ------------------------------------------------------------------------

	
	// -------------------------------------------------------------------------
	// --- Methods to add the images after creating either a row or a column ---
	//assumes the new world is at the end of the list
	public void addNewColumnWorldButtons() {
		int nElements = tree.getItemCount();
		for(int i=0; i<nElements ; i++){
			tree.getItem(i).setImage(this.world_sequence.size(),imgUnchecked);			
		}
		
	}
	private TreeItem addNewRowWorldButtons(TreeItem item, String name){
		item.setText(0,name);
		for(int i=1; i<=this.world_sequence.size() ; i++){
			item.setImage(i,imgUnchecked);			
		}
		return item;
	}
	// ------------------------------------------------------------------------
	
	
	
	public Tree getTree() {
		return tree;
	}
	

	//only used at the constructor
	private void addMenuOptions(Menu menu) {
		final MenuItem insertBeforeMenuItem = new MenuItem(menu, SWT.NONE);
	    insertBeforeMenuItem.addSelectionListener(new SelectionAdapter() {
	      public void widgetSelected(final SelectionEvent e)
	      {
	        TreeItem [] selected = tree.getSelection();

	        if (selected.length > 0)
	        {
	          TreeItem parent = selected[0].getParentItem();
	          System.out.println("Insert Before - " + selected[0].getText());
	          TreeItem item ;
	          if (parent == null)
	          {	        	  
		          int index = tree.indexOf(selected[0]);
		          item = createStoryElement(tree, index);
		           
	          }else{	        	  
		          int index = parent.indexOf(selected[0]);
		          item = createStoryElement(parent, index);
		          
	          }
	          item.setText("abv");  
	        }
	        else
	          System.out.println("Insert Before - nothing selected.");
	      }
	    });
	    insertBeforeMenuItem.setText("Insert Before");
		
	}

}
