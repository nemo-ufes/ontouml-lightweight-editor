package br.ufes.inf.nemo.pattern.dynamic.ui;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

/*
 * TODO LIST
 * 	- Implement reuse of classes used in the current table
 * */


/**
 * @author Victor Amorim
 */
public class DynamicWindow extends Dialog {
	private Composite container;	
	private String title = new String();
	private HashMap<String,ArrayList<Object[]>> hashTable = null;
	public HashMap<String, ArrayList<Object[]>> getHashTable(){
		return hashTable;
	}

	public DynamicWindow(Shell parentShell, String title, String imagePath) 
	{
		super(parentShell);		
		this.title=title;
		currentImage = SWTResourceManager.getImage(DynamicWindow.class, imagePath);
		setDefaultImage(new Image(Display.getDefault(),DynamicWindow.class.getResourceAsStream("/resources/icons/x16/sitemap.png")));		
	}
	
	public static DynamicWindow createDialog(String title, String imagePath)
	{			
		Display display = Display.getDefault();	    	
		Shell shell = display.getActiveShell();		
		DynamicWindow resultDIalog = new DynamicWindow(shell,title,imagePath);
		resultDIalog.create();
		return resultDIalog;
	}
	
	@Override
	public void create() {
		 super.create();	    	  
		 setShellStyle(SWT.TITLE);
		 bringToFront(getShell());
		 getShell().setText(title);	      
	}
	
	public void bringToFront(final Shell shell) {
	    shell.getDisplay().asyncExec(new Runnable() {
	        public void run() {
	            shell.forceActive();
	        }
	    });
	}
	
	private Table table;
	public Table getTable() {
		return table;
	}

	private Button btnAddNewLine;
	private Button btnRemoveLine;
	public Button getBtnAddNewLine() {
		return btnAddNewLine;
	}

	private Image currentImage;
	
	protected Button createButton(Composite arg0, int arg1, String arg2, boolean arg3) 
	{
		//Retrun null so that no default buttons like 'OK' and 'Cancel' will be created
		return null;
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) 
	{
		container = (Composite) super.createDialogArea(parent);	
		container.setLayout(new FormLayout());
				
		Composite composite = new Composite(container, SWT.BORDER);
		FormData fd_composite = new FormData();
		fd_composite.top = new FormAttachment(0, 10);
		fd_composite.right = new FormAttachment(0, 895);
		fd_composite.left = new FormAttachment(0, 494);
		composite.setLayoutData(fd_composite);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		

		Label imgLabel = new Label(composite, SWT.HORIZONTAL | SWT.CENTER);
		imgLabel.setAlignment(SWT.CENTER);
		imgLabel.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
		
		//Calculating the new Image size holding the aspect ratio
		Double w = (401.0/currentImage.getBounds().width);
		Double h = (220.0/currentImage.getBounds().height);

		double scale = Math.min(w, h);

		w = currentImage.getBounds().width * scale;
		h = currentImage.getBounds().height * scale;

		imgLabel.setImage(new Image(Display.getDefault(),currentImage.getImageData().scaledTo(w.intValue(),h.intValue())));
		imgLabel.pack();
		composite.pack();

		this.table = new Table(container, SWT.BORDER | SWT.V_SCROLL | SWT.FULL_SELECTION);
		FormData fd_table = new FormData();
		fd_table.bottom = new FormAttachment(0, 230);
		fd_table.right = new FormAttachment(0, 477);
		fd_table.top = new FormAttachment(0, 10);
		fd_table.left = new FormAttachment(0, 10);
		table.setLayoutData(fd_table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		Button btnPressMe = new Button (container, SWT.NONE);
		FormData fd_btnPressMe = new FormData();
		fd_btnPressMe.top = new FormAttachment(table, 6);
		fd_btnPressMe.right = new FormAttachment(table, 0, SWT.RIGHT);
		btnPressMe.setLayoutData(fd_btnPressMe);
		btnPressMe.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				hashTable = new HashMap<String, ArrayList<Object[]>>();
				TableItem[] tis = table.getItems();
				Object ob;

				for(TableItem ti : tis){
					for(String field : dataFields){
						ob = ti.getData("text_"+field);
						if(ob != null){
							boolean reuse = ((Button)ti.getData("reuse_"+field)).getSelection(); 
							String text = ((Text)ti.getData("text_"+field)).getText();
							String stereotype = ((CCombo)ti.getData("stereotype_"+field)).getItem(((CCombo)ti.getData("stereotype_"+field)).getSelectionIndex());
							boolean active = ((Button)ti.getData("active_"+field)).getSelection(); 
							if(!hashTable.containsKey(field))
								hashTable.put(field,new ArrayList<Object[]>());
							hashTable.get(field).add(new Object[]{reuse,text,stereotype,active});
						}						
					}
				}
				close();
			}
		});
		btnPressMe.setText("Create classes");
		btnPressMe.pack ();

		btnAddNewLine = new Button(container, SWT.NONE);
		btnAddNewLine.setVisible(false);
		FormData fd_btnAddNewLine = new FormData();
		fd_btnAddNewLine.top = new FormAttachment(table, 6);
		btnAddNewLine.setLayoutData(fd_btnAddNewLine);
		btnAddNewLine.setText("Add new line");

		btnRemoveLine = new Button(container, SWT.NONE);
		fd_btnAddNewLine.right = new FormAttachment(btnRemoveLine, -6);
		FormData fd_btnRemoveLine = new FormData();
		fd_btnRemoveLine.right = new FormAttachment(btnPressMe, -24);
		fd_btnRemoveLine.left = new FormAttachment(0, 233);
		fd_btnRemoveLine.top = new FormAttachment(table, 6);
		btnRemoveLine.setLayoutData(fd_btnRemoveLine);
		btnRemoveLine.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent evt) {
				if(table.getItemCount() > initialItemCount){
					int i = table.getItemCount()-1;
					TableItem ti = table.getItem(i);
					for(TableEditor te : tableEditorHash.get(ti)){
						te.getEditor().dispose();
					}
					table.remove(i);
					if(currentImage != null)
						currentImage.dispose();
				}else{
					MessageBox dialog = new MessageBox(getShell(), SWT.ICON_INFORMATION | SWT.OK );
					dialog.setText("Invalid action");
					dialog.setMessage("Insert some line before delete.");
					dialog.open(); 					
				}
			}
		});
		btnRemoveLine.setText("Remove Line");
		btnRemoveLine.setVisible(false);
		Button btnNewButton = new Button(container, SWT.NONE);
		fd_composite.bottom = new FormAttachment(btnNewButton, -6);
		btnNewButton.setTouchEnabled(true);
		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.left = new FormAttachment(btnPressMe, 324);
		fd_btnNewButton.right = new FormAttachment(100, -11);
		fd_btnNewButton.top = new FormAttachment(0, 236);
		btnNewButton.setLayoutData(fd_btnNewButton);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				ImagePreview imgPre = ImagePreview.createDialog(currentImage);
				imgPre.open();
			}
		});
		btnNewButton.setText("Show Image");
		
		Button btnHelp = new Button(container, SWT.FLAT | SWT.CENTER);
		fd_btnAddNewLine.left = new FormAttachment(btnHelp, 48);
		FormData fd_btnHelp = new FormData();
		fd_btnHelp.left = new FormAttachment(0, 20);
		fd_btnHelp.top = new FormAttachment(0, 236);
		btnHelp.setLayoutData(fd_btnHelp);
		btnHelp.setImage(SWTResourceManager.getImage(DynamicWindow.class,"/resources/icons/x16/help.png"));

		btnHelp.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent evt) {
				Helper h = Helper.createDialog(usedStereotypes);
				h.open();
			}
		});
		
		return container;
	}

	
	private int initialItemCount = 0;
	public void setInitialItemCount(int initialValue) {
		initialItemCount = initialValue;
	}

	public Button getBtnDeleteLine() {
		return btnRemoveLine;
	}

	private HashMap<TableItem, ArrayList<TableEditor>> tableEditorHash = new HashMap<>();
	public void addTableEditor(TableItem tableItem, ArrayList<TableEditor> editors){
		tableEditorHash.put(tableItem, editors);
	}

	private ArrayList<String> dataFields = new ArrayList<>();
	public void addDataField(String field){
		if(!dataFields.contains(field))
			dataFields.add(field);
	}
	
	private ArrayList<String> usedStereotypes = new ArrayList<>();
	public void addUsedStereotypes(String[] stereotypes) {
		for (String stereotype : stereotypes) {
			if(!usedStereotypes.contains(stereotype))
				usedStereotypes.add(stereotype);
		}
	}
}