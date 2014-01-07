package br.ufes.inf.nemo.oled.antipattern;

import java.util.ArrayList;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;
import br.ufes.inf.nemo.antipattern.relrig.RelRigAntipattern;
import br.ufes.inf.nemo.antipattern.relrig.RelRigOccurrence;
import br.ufes.inf.nemo.oled.antipattern.wizard.relrig.RelRigWizard;

public class AntiPatternResultDialog extends TitleAreaDialog {

	private ArrayList<AntipatternOccurrence> result;
	private TableViewer viewer;
	private AntipatternResultFilter filter;
	
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public AntiPatternResultDialog(Shell parentShell, ArrayList<AntipatternOccurrence> result) 
	{
		super(parentShell);
		this.result = result;				
	}
	
	@Override
	public void create() {
	    super.create();
	    setTitle("Result: List of Occurrences");
	    setMessage("This is the result list of antipatterns found.", IMessageProvider.INFORMATION);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) 
	{
		Composite container = (Composite) super.createDialogArea(parent);		
		createPartControl(container);		
		return container;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) 
	{
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() 
	{
		return new Point(450, 300);
	}
	
	public void createPartControl(Composite parent) 
	{
	    GridLayout layout = new GridLayout(2, false);
	    parent.setLayout(layout);
	    
	    Label searchLabel = new Label(parent, SWT.NONE);
	    searchLabel.setText("Search: ");
	    
	    final Text searchText = new Text(parent, SWT.BORDER | SWT.SEARCH);
	    searchText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
	    
	    createViewer(parent);
	    	    
	    searchText.addKeyListener(new KeyAdapter() {
	    	public void keyReleased(KeyEvent ke) {
	    		filter.setSearchText(searchText.getText());
	    		viewer.refresh();
	    	}
	    });
	    
	    filter = new AntipatternResultFilter();
	    viewer.addFilter(filter);
	  }
	    
	/**
	 * Create Table Viewer
	 * @param parent
	 */
	  private void createViewer(Composite parent) 
	  {
	    viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
	    createColumns(parent, viewer);
	    
	    final Table table = viewer.getTable();
	    table.setHeaderVisible(true);
	    table.setLinesVisible(true);

	    // Get the content for the viewer, setInput will call getElements in the contentProvider
	    viewer.setContentProvider(new ArrayContentProvider());
	    viewer.setInput(result);

	    // Define layout for the viewer
	    GridData gridData = new GridData();
	    gridData.verticalAlignment = GridData.FILL;
	    gridData.horizontalSpan = 2;
	    gridData.grabExcessHorizontalSpace = true;
	    gridData.grabExcessVerticalSpace = true;
	    gridData.horizontalAlignment = GridData.FILL;	    
	    viewer.getControl().setLayoutData(gridData);
	    
	    // Set buttons
	    TableItem[] items = table.getItems();
	    for (int i = 0; i < items.length; i++) 
	    {	     
	      TableEditor editor = new TableEditor(table);	      
	      Button button = new Button(table, SWT.NONE);	   
	      button.setImage(new Image(getShell().getDisplay(),AntiPatternResultDialog.class.getResourceAsStream("/resources/br/ufes/inf/nemo/oled/ui/fix.png")));
	      
	      final AntipatternOccurrence apOccur = result.get(i);
	      
	      Listener listener = new Listener() {
	        public void handleEvent(Event event) {	      
	        	Display.getDefault().syncExec(new Runnable() {
				    public void run() {
				    	
			        	if (apOccur instanceof RelRigOccurrence) {	        		
			        		WizardDialog wizardDialog = new WizardDialog(new Shell(), new RelRigWizard((RelRigOccurrence)apOccur));
			        		wizardDialog.open();
			        	}
				    }
	        	});
	        }
	      };

	      button.addListener(SWT.Selection,listener);

	      button.pack();
	      editor.minimumWidth = button.getSize().x;
	      editor.horizontalAlignment = SWT.LEFT;
	      editor.setEditor(button, items[i], 3);
	    }
	  }

	  public TableViewer getViewer() { return viewer; }

	  /** 
	   * Create the columns for the table 
	   */
	  private void createColumns(final Composite parent, final TableViewer viewer) 
	  {
	    String[] titles = { "Name", "Type", "Status", "Analyze?" };
	    int[] bounds = { 100, 100, 100, 100 };

	    // First column is for a short description of the antipattern
	    TableViewerColumn col = createTableViewerColumn(titles[0], bounds[0], 0);
	    
	    col.setLabelProvider(new ColumnLabelProvider() {
	      @Override
	      public String getText(Object element) {
	    	return ((AntipatternOccurrence)element).getShortName();
	      }
	    });

	    // Sets the type of the antipattern
	    col = createTableViewerColumn(titles[1], bounds[1], 1);
	    
	    col.setLabelProvider(new ColumnLabelProvider() {
		@Override
	      public String getText(Object element) {
	    	  if  (element instanceof RelRigOccurrence) return RelRigAntipattern.getAntipatternInfo().getAcronym();
	    	  return "";
	    	  //return ((AntipatternOccurrence)element).getAntiPatternType().getAntipatternInfo().getAcronym();
	      }
	    });

	    // Set if the occurrence of the antipattern was fixed or not
	    col = createTableViewerColumn(titles[2], bounds[2], 2);
	    
	    col.setLabelProvider(new ColumnLabelProvider() {
	      @Override
	      public String getText(Object element) {
	        return new Boolean(((AntipatternOccurrence) element).isFixed()).toString();
	      }
	    });
	    
	    // Show the button to investigate the occurrence
	    col = createTableViewerColumn(titles[3], bounds[3], 3);
	    
	    col.setLabelProvider(new ColumnLabelProvider() {
	      @Override
	      public String getText(Object element) {
	        return "";
	      }
	    });
	  }

	  private TableViewerColumn createTableViewerColumn(String title, int bound, final int colNumber) 
	  {
	    final TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.NONE);
	    final TableColumn column = viewerColumn.getColumn();
	    column.setText(title);
	    column.setWidth(bound);
	    column.setResizable(true);
	    column.setMoveable(true);
	    return viewerColumn;
	  }

	  public void setFocus() {  viewer.getControl().setFocus();  }
}
