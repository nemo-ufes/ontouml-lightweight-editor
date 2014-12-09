package br.ufes.inf.nemo.story.ui;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

import RefOntoUML.Classifier;
import RefOntoUML.parser.OntoUMLNameHelper;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.story.OntoUMLStoryCrafter;
import br.ufes.inf.nemo.story.WorldList;
import stories.Individual;
import stories.Link;
import stories.Node;
import stories.Node_state;
import stories.StoriesFactory;
import stories.Story;
import stories.Story_element;
import stories.World;
import stories.impl.LinkImpl;
import stories.impl.NodeImpl;
import stories.impl.Node_stateImpl;
import stories.impl.StoryImpl;

public class StoryElementTimeline {	
	private final Tree tree;
	//images for the world buttons (exists, doen't exist and unspecified)
	private final Image imgYes;
	private final Image imgNo;
	private final Image imgUnchecked;
	//EMF handler
	static StoriesFactory storyFactory = StoriesFactory.eINSTANCE;
	private WorldList world_sequence;
	private final SashForm sashForm;
	
	StoryElementTimeline(OntoUMLParser modelParser, final Composite parent, int style) throws IOException{
		
		Composite btnComp = new Composite(parent, SWT.NONE);
		btnComp.setLayout(new RowLayout(SWT.HORIZONTAL));
		Button btnAdd = new Button(btnComp, SWT.NONE);
		btnAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				try {
					loadStory(parent);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnAdd.setText("Add Story");
		
		Button btnSave = new Button(btnComp, SWT.NONE);
		btnSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				saveStory(parent);				
			}
		});
		btnSave.setText("Save Story");
		
		//this tree holds every story element. The story element is a row and the first column contains the element name. The other columns contain buttons that determine in which worlds the element exists
		sashForm = new SashForm(parent,SWT.SMOOTH | SWT.VERTICAL);
		sashForm.setLayoutData(new GridData(SWT.LEFT,SWT.TOP,true,true,1,1));
		
		
		GridData treegrid = new GridData(SWT.FILL,SWT.FILL,true,true);
		tree = new Tree(sashForm, style);
		tree.setLayoutData(treegrid);
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
	    tree.addListener (SWT.MouseDown, new CheckboxClickListener(tree,world_sequence,imgYes,imgNo,imgUnchecked)  );
	    //workaround for bug on doubleclick. See http://www.eclipse.org/forums/index.php/t/257325/
	    tree.addListener (SWT.MeasureItem, new Listener(){
			@Override
			public void handleEvent(Event event) {}
	    });
	    
	    //The listener below is used to modify the name of the story elements (rows)
	    tree.addListener (SWT.MouseDown, new NameEditorListener(sashForm,tree));
	    
	    
	    
	  
		
		
		
		//TODO: Fazer funcionar o scroll
		
		final ScrolledComposite sc = new ScrolledComposite(sashForm, SWT.H_SCROLL | SWT.V_SCROLL);
		sc.setExpandHorizontal(true);
		sc.setExpandVertical(true);
		
		
		final Composite charEditor = new Composite(sc, SWT.BORDER);
	    final StackLayout stacklayout =new StackLayout( ); 
	    charEditor.setLayout(stacklayout);
	    charEditor.setBackground(new Color(parent.getDisplay(), 240, 240, 240));
	    sc.setContent(charEditor);
	    sc.setMinSize(charEditor.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	    
	    Label lblRigidTypes = new Label(charEditor, SWT.NONE);
		lblRigidTypes.setText("Rigid Types:");
		 
		 
		final Composite linkClassEditor = new Composite(charEditor,  SWT.NONE);
		linkClassEditor.setLayout(new GridLayout());
		
	    HashMap<Classifier,CLabel> LinkClassCheckboxes = new HashMap<Classifier,CLabel>();
	    Set<Classifier> slinks = modelParser.getAssociations();
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
	    	l.setImage(imgUnchecked);
	    	l.setText(c.getName());
	    	l.setData(c);
	    	LinkClassCheckboxes.put(c, l);
	    	l.addListener(SWT.MouseDown,new CheckboxClassListener(this));	    	
	    }
	    		
	    
	    final Composite antiRigidClassEditor = new Composite(charEditor,  SWT.NONE );
	    antiRigidClassEditor.setLayout(new GridLayout());
	    HashMap<Classifier,CLabel> antiRigidClassCheckboxes = new HashMap<Classifier,CLabel>();
	    Set<Classifier> s2 = modelParser.getAntiRigidClasses();
	    List<Classifier> arclassList = new LinkedList<Classifier>(s2);
	    Collections.sort(arclassList, new Comparator<Classifier>(){
	    	@Override
	    	public int compare(Classifier c1, Classifier c2){
	    		return (c1.getName()).compareTo(c2.getName());
	    		
	    	}
	    });
	    for(Classifier c : arclassList){
	    	final CLabel l = new CLabel(antiRigidClassEditor,SWT.NONE);
	    	l.setImage(imgUnchecked);
	    	l.setText(c.getName());
	    	l.setData(c);
	    	antiRigidClassCheckboxes.put(c, l);
	    	l.addListener(SWT.MouseDown,new CheckboxClassListener(this));	    	
	    }
	    
	    final Composite rigidClassEditor = new Composite(charEditor, SWT.NONE );
	    rigidClassEditor.setLayout(new GridLayout());
	    HashMap<Classifier,CLabel> ClassCheckboxes = new HashMap<Classifier,CLabel>();
	    Set<Classifier> s = modelParser.getRigidClasses();
	    List<Classifier> classList = new LinkedList<Classifier>(s);
	    Collections.sort(classList, new Comparator<Classifier>(){
	    	@Override
	    	public int compare(Classifier c1, Classifier c2){
	    		return (c1.getName()).compareTo(c2.getName());
	    	}
	    });
	    for(Classifier c : classList){
	    	final CLabel l = new CLabel(rigidClassEditor,SWT.NONE);
	    	l.setImage(imgUnchecked);
	    	l.setText(c.getName());
	    	l.setData(c);
	    	ClassCheckboxes.put(c, l);
	    	l.addListener(SWT.MouseDown,new CheckboxClassListener(this));	    	
	    }
	    	    
	    stacklayout.topControl = linkClassEditor;
	    //Listener below changes the screen for the class editor
	    tree.addListener (SWT.Selection, new Listener(){

			@Override
			public void handleEvent(Event e) {
				if(tree.getSelection().length == 0){
					//no items selected. Grey out the charEditor area.
				}
				else if (tree.getSelection().length >1){
					//multiple itens selected.
					//check for item kind
					//	if they are of different kinds (node x link x node_state), greyout the char editor area TODO:(maybe give a message in the editor area?)
					//	else, if they are of the same kind, update the conflicting instantiations to the indeterminate state (square).
					
				}
				Object o = e.item.getData();
				if(o.getClass() == NodeImpl.class){
					stacklayout.topControl = rigidClassEditor;
					
				}
				else if (o.getClass() == LinkImpl.class){
					stacklayout.topControl = linkClassEditor;
					
				}
				else if (o.getClass() == Node_stateImpl.class){
					stacklayout.topControl = antiRigidClassEditor;
					
				}
				charEditor.layout();
					
			}
			
	    });   
	    
	   // sashForm.setWeights(new int[]{1,5});//TODO: achar uma forma melhor de fazer o layout
    	
	}

	public TreeItem[] getSelected(){
		return tree.getSelection();
	}
	// ------------------------------------------------------------------------
	// --- This method creates a new World and the Column in the tree		---
	// --- The buttons to check if an element exists at a given world are	---
	// --- added for each row												---
	public void createWorld() {
		World w = storyFactory.createWorld();
		w.setLabel("World "+this.world_sequence.size());
	    addWorld(w);
	}
	public void addWorld(World w) {
		TreeColumn column = new TreeColumn(this.getTree(), SWT.CENTER );		
	    column.setText(w.getLabel());
	    column.setWidth(60);
	    column.setMoveable(true);
	    this.world_sequence.add(w);
	    this.addNewColumnWorldButtons();
	}
	// ------------------------------------------------------------------------
	
	public void loadStory(Composite parent) throws IOException{
		FileDialog dialog = new FileDialog((Shell) parent, SWT.OPEN);
		dialog.setFilterExtensions(new String [] {"*.xmi"});
		dialog.setFilterPath("C:\\Users\\Bernardo\\Documents\\OLED-src\\br.ufes.inf.nemo.story\\test_data\\output");//TODO: botar algo mais geral 
		String result = dialog.open();
		if(result == null) return;
		Resource story = OntoUMLStoryCrafter.loadStory(	result.substring(result.lastIndexOf("\\")+1,result.length()),
										result.substring(0,result.lastIndexOf("\\")+1)
									);
		for(EObject st : story.getContents()){
			if(st.getClass() == StoryImpl.class){
				for(Story_element se : ((Story)st).getElements()){
					
					if("World".equals(se.eClass().getName())){
						addWorld((World)se);
					}
				}
			}
			
		}
		for(EObject st : story.getContents()){
			if(st.getClass() == StoryImpl.class){
				for(Story_element se : ((Story)st).getElements()){
					
					if("Node".equals(se.eClass().getName())){
						System.out.println("Node "+ ((Node)se).getLabel());
						addIndividual(tree,(Node)se,tree.getItemCount());
						
					}
					if("Link".equals(se.eClass().getName())){
						System.out.println("Link "+ ((Link)se).getLabel());
						addIndividual(tree,(Link)se,tree.getItemCount());
					}
					
				}
			}
			
		}
		
		return;
	}

	private void saveStory(Composite parent) {
		FileDialog dialog = new FileDialog((Shell) parent, SWT.SAVE);
		dialog.setFilterExtensions(new String [] {"*.xmi"});
		dialog.setFilterPath("C:\\Users\\Bernardo\\Documents\\OLED-src\\br.ufes.inf.nemo.story\\test_data\\output");
		String result = dialog.open();
		if (result==null)return;
		Story s = StoriesFactory.eINSTANCE.createStory();
		for(TreeItem ti : tree.getItems()){
			s.getElements().add((Story_element) ti.getData());
			
		}
		for(World w : world_sequence){
			s.getElements().add(w);
		}
		OntoUMLStoryCrafter.saveStory(result.substring(result.lastIndexOf("\\")+1,result.length()),
				result.substring(0,result.lastIndexOf("\\")+1), s);
	}
	
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
		
		((Node)parent.getData()).getStates().add(n);
		n.setLabel("State "+ parent.getItemCount());
		item.setData(n);
		return addNewRowWorldButtons(item,"State "+ parent.getItemCount());
	}
	public TreeItem addIndividual(Tree parent, Individual n, int index){
		TreeItem item = new TreeItem(parent, SWT.CENTER, index);
		item.setData(n);
		addNewRowWorldButtons(item,n.getLabel()); //adds a row of buttons 
		yesWorldButtons(item,n.getPresent_in());
		noWorldButtons(item,n.getAbsent_from());
		return item;
	}
	// ------------------------------------------------------------------------

	
	private void noWorldButtons(TreeItem item, EList<World> present_in) {
		for(World w:present_in){
			for(int i=0; i<this.world_sequence.size() ; i++){
				if(w==world_sequence.get(i)){
					item.setImage(i+1,imgNo);				
				}
			}
		}
		
	}

	private void yesWorldButtons(TreeItem item, EList<World> absent_from) {
		for(World w:absent_from){
			for(int i=0; i<this.world_sequence.size() ; i++){
				if(w==world_sequence.get(i)){
					item.setImage(i+1,imgYes);				
				}
			}
		}		
	}

	// -------------------------------------------------------------------------
	// --- Methods to add the images after creating either a row or a column ---
	//assumes the new world is at the end of the list
	public void addNewColumnWorldButtons() {
		int nElements = tree.getItemCount();
		for(int i=0; i<nElements ; i++){
			TreeItem t = tree.getItem(i);
			t.setImage(this.world_sequence.size(),imgUnchecked);
			for(TreeItem state : t.getItems()){
				state.setImage(this.world_sequence.size(),imgUnchecked);
			}
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
	
	public static int getColumn( Point pt, TreeItem item )
	{
		if(item!=null){
		    int columns = item.getParent().getColumnCount();
		    for (int i=0; i<columns; i++)
		    {
		    	Rectangle rect = item.getBounds (i);
		    	if ( pt.x >= rect.x && pt.x < rect.x + rect.width ) return i;
		    }
		}
	    return -1;
	}

	public Image getImgYes(){
		return imgYes;
	}
	public Image getImgNo(){
		return imgNo;
	}
	public Image getImgUnchecked(){
		return imgUnchecked;
	}
}
