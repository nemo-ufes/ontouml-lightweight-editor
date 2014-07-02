/**
 * Copyright(C) 2011-2014 by John Guerson, Tiago Prince, Antognoni Albuquerque
 *
 * This file is part of OLED (OntoUML Lightweight BaseEditor).
 * OLED is based on TinyUML and so is distributed under the same
 * license terms.
 *
 * OLED is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * OLED is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OLED; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package br.ufes.inf.nemo.oled.validator.antipattern;

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
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;

import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;
import br.ufes.inf.nemo.antipattern.GSRig.GSRigAntipattern;
import br.ufes.inf.nemo.antipattern.GSRig.GSRigOccurrence;
import br.ufes.inf.nemo.antipattern.asscyc.AssCycAntipattern;
import br.ufes.inf.nemo.antipattern.asscyc.AssCycOccurrence;
import br.ufes.inf.nemo.antipattern.binover.BinOverAntipattern;
import br.ufes.inf.nemo.antipattern.binover.BinOverOccurrence;
import br.ufes.inf.nemo.antipattern.decint.DecIntAntipattern;
import br.ufes.inf.nemo.antipattern.decint.DecIntOccurrence;
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
import br.ufes.inf.nemo.antipattern.mixiden.MixIdenAntipattern;
import br.ufes.inf.nemo.antipattern.mixiden.MixIdenOccurrence;
import br.ufes.inf.nemo.antipattern.mixrig.MixRigAntipattern;
import br.ufes.inf.nemo.antipattern.mixrig.MixRigOccurrence;
import br.ufes.inf.nemo.antipattern.multidep.MultiDepAntipattern;
import br.ufes.inf.nemo.antipattern.multidep.MultiDepOccurrence;
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
import br.ufes.inf.nemo.antipattern.wizard.asscyc.AssCycWizard;
import br.ufes.inf.nemo.antipattern.wizard.binover.BinOverWizard;
import br.ufes.inf.nemo.antipattern.wizard.decint.DecIntWizard;
import br.ufes.inf.nemo.antipattern.wizard.depphase.DepPhaseWizard;
import br.ufes.inf.nemo.antipattern.wizard.freerole.FreeRoleWizard;
import br.ufes.inf.nemo.antipattern.wizard.gsrig.GSRigWizard;
import br.ufes.inf.nemo.antipattern.wizard.hetcoll.HetCollWizard;
import br.ufes.inf.nemo.antipattern.wizard.homofunc.HomoFuncWizard;
import br.ufes.inf.nemo.antipattern.wizard.impabs.ImpAbsWizard;
import br.ufes.inf.nemo.antipattern.wizard.mixiden.MixIdenWizard;
import br.ufes.inf.nemo.antipattern.wizard.mixrig.MixRigWizard;
import br.ufes.inf.nemo.antipattern.wizard.multidep.MultiDepWizard;
import br.ufes.inf.nemo.antipattern.wizard.overlapping.OverlappingWizard;
import br.ufes.inf.nemo.antipattern.wizard.relcomp.RelCompWizard;
import br.ufes.inf.nemo.antipattern.wizard.relrig.RelRigWizard;
import br.ufes.inf.nemo.antipattern.wizard.relspec.RelSpecWizard;
import br.ufes.inf.nemo.antipattern.wizard.reprel.RepRelWizard;
import br.ufes.inf.nemo.antipattern.wizard.undefformal.UndefFormalWizard;
import br.ufes.inf.nemo.antipattern.wizard.undefphase.UndefPhaseWizard;
import br.ufes.inf.nemo.oled.AppFrame;
import br.ufes.inf.nemo.oled.model.AntiPatternList;

/**
 * @author Tiago Sales
 * @author John Guerson
 *
 */
public class AntiPatternResultDialog extends Dialog {

	private AppFrame frame;
	private ArrayList<AntipatternOccurrence> allOccurrences;
	private ArrayList<AntipatternOccurrence> result;
	private static TableViewer viewer;
	private AntipatternResultFilter filter;
	private Composite container;
	private Label searchLabel;
	private Text searchText;
	private Button btnAnalyze;
	private Button btnRemove;
	private Table table;
	private Button btnReset;
	private Label feedBackLabel;
		
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public AntiPatternResultDialog(Shell parentShell, ArrayList<AntipatternOccurrence> result, AppFrame frame) 
	{
		super(parentShell);		
		this.result = new ArrayList<AntipatternOccurrence>(result);
		this.allOccurrences = new ArrayList<AntipatternOccurrence>(result);
		this.frame = frame;
		setDefaultImage(new Image(Display.getDefault(),AntiPatternResultDialog.class.getResourceAsStream("/resources/icons/antipattern36.png")));		
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
		container = (Composite) super.createDialogArea(parent);		
		
		createPartControl(container);

		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(gl_container.createSequentialGroup()
							.add(searchLabel)
							.addPreferredGap(LayoutStyle.RELATED)
							.add(searchText, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
							.add(66)
							.add(btnAnalyze, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.RELATED)
							.add(btnRemove, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.RELATED)
							.add(btnReset, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
						.add(table, GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
						.add(feedBackLabel, GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(gl_container.createParallelGroup(GroupLayout.LEADING, false)
						.add(searchLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.add(gl_container.createParallelGroup(GroupLayout.BASELINE)
							.add(searchText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.add(btnReset)
							.add(btnRemove, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.add(btnAnalyze, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addPreferredGap(LayoutStyle.UNRELATED)
					.add(table, GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(feedBackLabel)
					.add(15))
		);
		container.setLayout(gl_container);
		
		return container;
	}

	public static void openDialog(final AntiPatternList apList, final AppFrame frame)
	{			
		if (apList!=null &&  !apList.getAll().isEmpty())
		{
			Display display = Display.getDefault();	    	
			Shell shell = display.getActiveShell();			
			AntiPatternResultDialog resultDIalog = new AntiPatternResultDialog(shell,apList.getAll(), frame);					
			resultDIalog.create();
			resultDIalog.open();	
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
		return new Point(690, 518);
	}
	
	public void createPartControl(Composite parent) 
	{
	    
	    searchLabel = new Label(parent, SWT.NONE);
	    searchLabel.setText("Find: ");
	    
	    searchText = new Text(parent, SWT.BORDER | SWT.SEARCH);
	    
	    btnAnalyze = new Button(container, SWT.NONE);
    	btnAnalyze.setText("Analyze");
    	btnAnalyze.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if(table.getSelectionIndices().length==0){
					feedBackLabel.setVisible(false);
					feedBackLabel.setText("Can't open Anti-pattern Wizard! Please select a line in the table above.");
					feedBackLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
					feedBackLabel.setVisible(true);
				}
					
				else if(table.getSelectionIndices().length>1){
					feedBackLabel.setVisible(false);
					feedBackLabel.setText("Can't open Anti-pattern Wizard! Please select only ONE line in the table above.");
					feedBackLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
					feedBackLabel.setVisible(true);
				}
				else if (((AntipatternOccurrence) viewer.getElementAt(table.getSelectionIndex())).isFixed()){
					feedBackLabel.setVisible(false);
					feedBackLabel.setText("Can't open Anti-pattern Wizard! Occurrence already analyzed.");
					feedBackLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
					feedBackLabel.setVisible(true);
				}
				else {
					feedBackLabel.setVisible(false);
					feedBackLabel.setText("Anti-pattern Wizard Opened!");
					feedBackLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
					feedBackLabel.setVisible(true);
					showWizard((AntipatternOccurrence) viewer.getElementAt(table.getSelectionIndex()));
				}
			}
		});   
    	btnRemove = new Button(container, SWT.NONE);
    	btnRemove.setText("Remove");
	    btnRemove.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
				if(table.getSelectionIndices().length==0){
					feedBackLabel.setVisible(false);
					feedBackLabel.setText("Can't remove anti-pattern occurrence from Table! Please select at least one line in the table above.");
					feedBackLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
					feedBackLabel.setVisible(true);
				}
				else {
					feedBackLabel.setVisible(false);
					feedBackLabel.setText(table.getSelectionIndices().length+" anti-pattern occurrence(s) successfully removed!");
					feedBackLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
					feedBackLabel.setVisible(true);
					
					for (int index : table.getSelectionIndices()) {
						result.remove(viewer.getElementAt(index));
					}
						
					viewer.refresh();
				}
			}
		});
	    
	    btnReset = new Button(container, SWT.NONE);
		btnReset.setText("Reset");
		btnReset.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					result.clear();
					result.addAll(allOccurrences);
					viewer.refresh();
					feedBackLabel.setVisible(false);
					feedBackLabel.setText("Anti-pattern occurrence table restored!");
					feedBackLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
					feedBackLabel.setVisible(true);
				}
			});
	    
	    createViewer(parent);
	    	    
	    searchText.addKeyListener(new KeyAdapter() {
	    	public void keyReleased(KeyEvent ke) {
	    		filter.setSearchText(searchText.getText());
	    		viewer.refresh();
	    		feedBackLabel.setVisible(false);
	    		feedBackLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
	    		feedBackLabel.setText(table.getItemCount()+" results were found");
	    		feedBackLabel.setVisible(true);	    		
	    		}
	    });
	    
	    feedBackLabel = new Label(container, SWT.NONE);
		feedBackLabel.setAlignment(SWT.RIGHT);
		feedBackLabel.setVisible(false);
		
		
	    
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
	    
	    table = viewer.getTable();
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
	    
	  }
		
	  public TableViewer getViewer() { return viewer; }

	  /** 
	   * Create the columns for the table 
	   */
	  private void createColumns(final Composite parent, final TableViewer viewer) 
	  {
	    String[] titles = { "Name", "Type", "Status"};
	    int[] bounds = { 350, 140, 140 };

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
	    	  if  (element instanceof MixIdenOccurrence) return MixIdenAntipattern.getAntipatternInfo().getAcronym();
	    	  if  (element instanceof MixRigOccurrence) return MixRigAntipattern.getAntipatternInfo().getAcronym();
	    	  if  (element instanceof MultiDepOccurrence) return MultiDepAntipattern.getAntipatternInfo().getAcronym();
	    	  if  (element instanceof DecIntOccurrence) return DecIntAntipattern.getAntipatternInfo().getAcronym();
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
	        else return "Opened";
	      }
	      @Override
	    	public Color getForeground(Object element) {
	    	  String value = new Boolean(((AntipatternOccurrence) element).isFixed()).toString();
		      if (value.equals("true")) return Display.getDefault().getSystemColor(SWT.COLOR_DARK_GREEN);
		      else return Display.getDefault().getSystemColor(SWT.COLOR_DARK_RED);
	    	}
	    });
	    
//	    // Show the button to investigate the occurrence
//	    col = createTableViewerColumn(titles[3], bounds[3], 3);
//	    
//	    col.setLabelProvider(new ColumnLabelProvider() {
//	      @Override
//	      public String getText(Object element) {
//	        return "";
//	      }
//	    });
//	    
//	    // Show the button to remove the occurrence
//	    col = createTableViewerColumn(titles[4], bounds[4], 4);
//	    
//	    col.setLabelProvider(new ColumnLabelProvider() {
//	      @Override
//	      public String getText(Object element) {
//	        return "";
//	      }
//	    });
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
	
	public static void refresh()
	{
    	viewer.refresh();
	}
	
	public WizardDialog getWizardDialog(final AntipatternOccurrence apOccur)
	{
    	WizardDialog wizardDialog = null;    	

    	Display d = Display.getDefault();
    	if (apOccur instanceof RelRigOccurrence) 
    		wizardDialog = new WizardDialog(new Shell(d), new RelRigWizard((RelRigOccurrence)apOccur));
    	if (apOccur instanceof RelSpecOccurrence) 
    		wizardDialog = new WizardDialog(new Shell(d), new RelSpecWizard((RelSpecOccurrence)apOccur));	        		
    	if (apOccur instanceof WholeOverOccurrence) 
    		wizardDialog = new WizardDialog(new Shell(d), new OverlappingWizard((WholeOverOccurrence)apOccur,"WholeOverWizard",WholeOverAntipattern.getAntipatternInfo()));
    	if (apOccur instanceof PartOverOccurrence) 
    		wizardDialog = new WizardDialog(new Shell(d), new OverlappingWizard((PartOverOccurrence)apOccur,"PartOverWizard",PartOverAntipattern.getAntipatternInfo()));	
    	if (apOccur instanceof RelOverOccurrence) 
    		wizardDialog = new WizardDialog(new Shell(d), new OverlappingWizard((RelOverOccurrence)apOccur,"RelOverWizard",RelOverAntipattern.getAntipatternInfo()));	
    	if (apOccur instanceof RepRelOccurrence) 
    		wizardDialog = new WizardDialog(new Shell(d), new RepRelWizard((RepRelOccurrence)apOccur));
    	if (apOccur instanceof MultiDepOccurrence) 
    		wizardDialog = new WizardDialog(new Shell(d), new MultiDepWizard((MultiDepOccurrence)apOccur));
    	if (apOccur instanceof RelCompOccurrence) 
    		wizardDialog = new WizardDialog(new Shell(d), new RelCompWizard((RelCompOccurrence)apOccur));
    	if (apOccur instanceof ImpAbsOccurrence) 
    		wizardDialog = new WizardDialog(new Shell(d), new ImpAbsWizard((ImpAbsOccurrence)apOccur));
    	if (apOccur instanceof UndefFormalOccurrence) 
    		wizardDialog = new WizardDialog(new Shell(d), new UndefFormalWizard((UndefFormalOccurrence)apOccur));
    	if (apOccur instanceof HetCollOccurrence) 
    		wizardDialog = new WizardDialog(new Shell(d), new HetCollWizard((HetCollOccurrence)apOccur));
    	if (apOccur instanceof HomoFuncOccurrence) 
    		wizardDialog = new WizardDialog(new Shell(d), new HomoFuncWizard((HomoFuncOccurrence)apOccur));
    	if (apOccur instanceof AssCycOccurrence) 
    		wizardDialog = new WizardDialog(new Shell(d), new AssCycWizard((AssCycOccurrence)apOccur));
    	if (apOccur instanceof BinOverOccurrence) 
    		wizardDialog = new WizardDialog(new Shell(d), new BinOverWizard((BinOverOccurrence)apOccur));
    	if (apOccur instanceof DepPhaseOccurrence) 
    		wizardDialog = new WizardDialog(new Shell(d), new DepPhaseWizard((DepPhaseOccurrence)apOccur));
    	if (apOccur instanceof FreeRoleOccurrence) 
    		wizardDialog = new WizardDialog(new Shell(d), new FreeRoleWizard((FreeRoleOccurrence)apOccur));
    	if (apOccur instanceof GSRigOccurrence) 
    		wizardDialog = new WizardDialog(new Shell(d), new GSRigWizard((GSRigOccurrence)apOccur));
    	if (apOccur instanceof MixRigOccurrence) 
    		wizardDialog = new WizardDialog(new Shell(d), new MixRigWizard((MixRigOccurrence)apOccur));
    	if (apOccur instanceof MixIdenOccurrence) 
    		wizardDialog = new WizardDialog(new Shell(d), new MixIdenWizard((MixIdenOccurrence)apOccur));
    	if (apOccur instanceof UndefPhaseOccurrence) 
    		wizardDialog = new WizardDialog(new Shell(d), new UndefPhaseWizard((UndefPhaseOccurrence)apOccur));
    	if (apOccur instanceof DecIntOccurrence) 
    		wizardDialog = new WizardDialog(new Shell(d), new DecIntWizard((DecIntOccurrence)apOccur));
    	
    	return wizardDialog;
	}
	
	public void showWizard(final AntipatternOccurrence apOccur)
	{
		
		WizardDialog wizardDialog = getWizardDialog(apOccur);		
		
		if(wizardDialog!=null && wizardDialog.open()==Window.OK){			
			if(!apOccur.getFix().isEmpty()){
				if(AntiPatternModifDialog.openDialog(apOccur.getFix(), frame)==Window.OK){					
					frame.getDiagramManager().updateOLED(apOccur.getFix());					
				}
			}
			refresh();
		}
	}		
}
