package br.ufes.inf.nemo.oled.antipattern;

import java.util.ArrayList;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.window.Window;
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
import br.ufes.inf.nemo.antipattern.GSRig.GSRigAntipattern;
import br.ufes.inf.nemo.antipattern.GSRig.GSRigOccurrence;
import br.ufes.inf.nemo.antipattern.asscyc.AssCycAntipattern;
import br.ufes.inf.nemo.antipattern.asscyc.AssCycOccurrence;
import br.ufes.inf.nemo.antipattern.binover.BinOverAntipattern;
import br.ufes.inf.nemo.antipattern.binover.BinOverOccurrence;
import br.ufes.inf.nemo.antipattern.depphase.DepPhaseAntipattern;
import br.ufes.inf.nemo.antipattern.depphase.DepPhaseOccurrence;
import br.ufes.inf.nemo.antipattern.freerole.FreeRoleAntipattern;
import br.ufes.inf.nemo.antipattern.freerole.FreeRoleOccurrence;
import br.ufes.inf.nemo.antipattern.hetcoll.HetCollAntipattern;
import br.ufes.inf.nemo.antipattern.hetcoll.HetCollOccurrence;
import br.ufes.inf.nemo.antipattern.homofunc.HomoFuncAntipattern;
import br.ufes.inf.nemo.antipattern.homofunc.HomoFuncOccurrence;
import br.ufes.inf.nemo.antipattern.impabs.ImpAbsAntipattern;
import br.ufes.inf.nemo.antipattern.impabs.ImpAbsOccurrence;
import br.ufes.inf.nemo.antipattern.imppart.ImpPartAntipattern;
import br.ufes.inf.nemo.antipattern.imppart.ImpPartOccurrence;
import br.ufes.inf.nemo.antipattern.mixiden.MixIdenAntipattern;
import br.ufes.inf.nemo.antipattern.mixiden.MixIdenOccurrence;
import br.ufes.inf.nemo.antipattern.mixrig.MixRigAntipattern;
import br.ufes.inf.nemo.antipattern.mixrig.MixRigOccurrence;
import br.ufes.inf.nemo.antipattern.multidep.MultiDepAntipattern;
import br.ufes.inf.nemo.antipattern.multidep.MultiDepOccurrence;
import br.ufes.inf.nemo.antipattern.multsort.MultSortAntipattern;
import br.ufes.inf.nemo.antipattern.multsort.MultSortOccurrence;
import br.ufes.inf.nemo.antipattern.partover.PartOverAntipattern;
import br.ufes.inf.nemo.antipattern.partover.PartOverOccurrence;
import br.ufes.inf.nemo.antipattern.relcomp.RelCompAntipattern;
import br.ufes.inf.nemo.antipattern.relcomp.RelCompOccurrence;
import br.ufes.inf.nemo.antipattern.relover.RelOverAntipattern;
import br.ufes.inf.nemo.antipattern.relover.RelOverOccurrence;
import br.ufes.inf.nemo.antipattern.relrig.RelRigAntipattern;
import br.ufes.inf.nemo.antipattern.relrig.RelRigOccurrence;
import br.ufes.inf.nemo.antipattern.relspec.RelSpecAntipattern;
import br.ufes.inf.nemo.antipattern.relspec.RelSpecOccurrence;
import br.ufes.inf.nemo.antipattern.reprel.RepRelAntipattern;
import br.ufes.inf.nemo.antipattern.reprel.RepRelOccurrence;
import br.ufes.inf.nemo.antipattern.undefformal.UndefFormalAntipattern;
import br.ufes.inf.nemo.antipattern.undefformal.UndefFormalOccurrence;
import br.ufes.inf.nemo.antipattern.undefphase.UndefPhaseAntipattern;
import br.ufes.inf.nemo.antipattern.undefphase.UndefPhaseOccurrence;
import br.ufes.inf.nemo.antipattern.wholeover.WholeOverAntipattern;
import br.ufes.inf.nemo.antipattern.wholeover.WholeOverOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.relrig.RelRigWizard;
import br.ufes.inf.nemo.antipattern.wizard.relspec.RelSpecWizard;
import br.ufes.inf.nemo.antipattern.wizard.wholeover.WholeOverWizard;
import br.ufes.inf.nemo.oled.AppFrame;
import br.ufes.inf.nemo.oled.model.AntiPatternList;

/**
 * @author Tiago Sales
 * @author John Guerson
 *
 */
public class AntiPatternResultDialog extends Dialog {

	private AppFrame frame;
	private ArrayList<AntipatternOccurrence> result;
	private TableViewer viewer;
	private AntipatternResultFilter filter;
		
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public AntiPatternResultDialog(Shell parentShell, ArrayList<AntipatternOccurrence> result, AppFrame frame) 
	{
		super(parentShell);		
		this.result = result;
		this.frame = frame;
		setDefaultImage(new Image(Display.getDefault(),AntiPatternResultDialog.class.getResourceAsStream("/resources/br/ufes/inf/nemo/oled/ui/antipattern-36x36.png")));		
	}
	
	@Override
	public void create() {
	    super.create();
	    setShellStyle(SWT.TITLE);
	    bringToFront(getShell());
	    getShell().setText("Anti-Pattern Result");	    
	}

	public void bringToFront(final Shell shell) {
	    shell.getDisplay().asyncExec(new Runnable() {
	        public void run() {
	            shell.forceActive();
	        }
	    });
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

	public static void openDialog(final AntiPatternList apList, final AppFrame frame)
	{			
		if (apList!=null &&  !apList.getAll().isEmpty())
		{
	    	Display.getDefault().syncExec(new Runnable() {
			    public void run() {
			    	Display display = Display.getDefault();	    	
					Shell shell = display.getActiveShell();			
					AntiPatternResultDialog resultDIalog = new AntiPatternResultDialog(shell,apList.getAll(), frame);					
					resultDIalog.create();
					resultDIalog.open();
			    }
	    	});
		}
	}
	
	@Override
	protected void okPressed() {	
		super.okPressed();
	}
	
	@Override
	protected void cancelPressed() {	
		super.cancelPressed();
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
		return new Point(650, 450);
	}
	
	public void createPartControl(Composite parent) 
	{
	    GridLayout layout = new GridLayout(2, false);
	    parent.setLayout(layout);
	    
	    Label searchLabel = new Label(parent, SWT.NONE);
	    searchLabel.setText("Find: ");
	    
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
	      button.addListener(SWT.Selection,new Listener() {
		        public void handleEvent(Event event) {
		        	showWizard(apOccur);
		        }
		      }
	      );
	      button.pack();
	      
	      editor.minimumWidth = button.getSize().x;
	      editor.horizontalAlignment = SWT.CENTER;
	      editor.setEditor(button, items[i], 3);
	    }	    
	  }

	  public TableViewer getViewer() { return viewer; }

	  /** 
	   * Create the columns for the table 
	   */
	  private void createColumns(final Composite parent, final TableViewer viewer) 
	  {
	    String[] titles = { "Name", "Type", "Status", "" };
	    int[] bounds = { 250, 100, 100, 100 };

	    // First column is for a short description of the antipattern
	    TableViewerColumn col = createTableViewerColumn(titles[0], bounds[0], 0);
	    
	    col.setLabelProvider(new ColumnLabelProvider() {
	      @Override
	      public String getText(Object element) {
	    	return ((AntipatternOccurrence)element).getShortName();
	      }
	      @Override
	    	public Image getImage(Object element) {
	    	  	return super.getImage(element);
	    	}
	    });

	    // Sets the type of the antipattern
	    col = createTableViewerColumn(titles[1], bounds[1], 1);
	    
	    col.setLabelProvider(new ColumnLabelProvider() {
		@Override
	      public String getText(Object element) {	    	  
	    	  if  (element instanceof AssCycOccurrence) return AssCycAntipattern.getAntipatternInfo().getAcronym();
	    	  if  (element instanceof BinOverOccurrence) return BinOverAntipattern.getAntipatternInfo().getAcronym();
	    	  if  (element instanceof DepPhaseOccurrence) return DepPhaseAntipattern.getAntipatternInfo().getAcronym();
	    	  if  (element instanceof FreeRoleOccurrence) return FreeRoleAntipattern.getAntipatternInfo().getAcronym();
	    	  if  (element instanceof GSRigOccurrence) return GSRigAntipattern.getAntipatternInfo().getAcronym();
	    	  if  (element instanceof HetCollOccurrence) return HetCollAntipattern.getAntipatternInfo().getAcronym();
	    	  if  (element instanceof HomoFuncOccurrence) return HomoFuncAntipattern.getAntipatternInfo().getAcronym();
	    	  if  (element instanceof ImpAbsOccurrence) return ImpAbsAntipattern.getAntipatternInfo().getAcronym();
	    	  if  (element instanceof ImpPartOccurrence) return ImpPartAntipattern.getAntipatternInfo().getAcronym();
	    	  if  (element instanceof MixIdenOccurrence) return MixIdenAntipattern.getAntipatternInfo().getAcronym();
	    	  if  (element instanceof MixRigOccurrence) return MixRigAntipattern.getAntipatternInfo().getAcronym();
	    	  if  (element instanceof MultiDepOccurrence) return MultiDepAntipattern.getAntipatternInfo().getAcronym();
	    	  if  (element instanceof MultSortOccurrence) return MultSortAntipattern.getAntipatternInfo().getAcronym();
	    	  if  (element instanceof PartOverOccurrence) return PartOverAntipattern.getAntipatternInfo().getAcronym();
	    	  if  (element instanceof RelCompOccurrence) return RelCompAntipattern.getAntipatternInfo().getAcronym();
	    	  if  (element instanceof RelOverOccurrence) return RelOverAntipattern.getAntipatternInfo().getAcronym();
	    	  if  (element instanceof RelRigOccurrence) return RelRigAntipattern.getAntipatternInfo().getAcronym();
	    	  if  (element instanceof RelSpecOccurrence) return RelSpecAntipattern.getAntipatternInfo().getAcronym();
	    	  if  (element instanceof RepRelOccurrence) return RepRelAntipattern.getAntipatternInfo().getAcronym();
	    	  if  (element instanceof UndefFormalOccurrence) return UndefFormalAntipattern.getAntipatternInfo().getAcronym();
	    	  if  (element instanceof UndefPhaseOccurrence) return UndefPhaseAntipattern.getAntipatternInfo().getAcronym();
	    	  if  (element instanceof WholeOverOccurrence) return WholeOverAntipattern.getAntipatternInfo().getAcronym();
	    	  return "<error>";
	      }
	    });

	    // Set if the occurrence of the antipattern was fixed or not
	    col = createTableViewerColumn(titles[2], bounds[2], 2);
	    
	    col.setLabelProvider(new ColumnLabelProvider() {
	      @Override
	      public String getText(Object element) {
	        String value = new Boolean(((AntipatternOccurrence) element).isFixed()).toString();
	        if (value.equals("true")) return "Fixed";
	        else return "Open";
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

	@Override
	protected boolean isResizable() {	
		return true;
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
	
	public void showWizard(final AntipatternOccurrence apOccur)
	{
//    	Display.getDefault().syncExec(new Runnable() {
//		    public void run() {
		    	WizardDialog wizardDialog = null;
		    	
	        	if (apOccur instanceof RelRigOccurrence) 
	        	{	        		
	        		wizardDialog = new WizardDialog(new Shell(), new RelRigWizard((RelRigOccurrence)apOccur));	        		
	        	}
	        	
	        	if (apOccur instanceof RelSpecOccurrence) 
	        	{	        		
	        		wizardDialog = new WizardDialog(new Shell(), new RelSpecWizard((RelSpecOccurrence)apOccur));	        		
	        	}
	        	
	        	if (apOccur instanceof WholeOverOccurrence) 
	        	{	        		
	        		wizardDialog = new WizardDialog(new Shell(), new WholeOverWizard((WholeOverOccurrence)apOccur));	        		
	        	}
	        	
	        	
        		if(wizardDialog.open()==Window.OK){
        			apOccur.setIsFixed(true);
        			if(!apOccur.getFix().isEmpty()){
        				AntiPatternModifDialog.openDialog(apOccur.getFix(), frame);
        				frame.getDiagramManager().updateOLED(apOccur.getFix());        				
        			}
        		}
        		
        		
//		    }
//    	});		
	}
}