package br.ufes.inf.nemo.pattern.dynamic.ui;

import java.util.ArrayList;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import br.ufes.inf.nemo.pattern.util.UtilPattern;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;

public class DROPDescription extends Dialog{
	private static Shell shell;
	String description;
	ArrayList<String> cqs;
	ArrayList<String> ocls;
	protected DROPDescription(Shell parentShell, String desc, ArrayList<String> cqs, ArrayList<String> ocls){
		super(parentShell);
		setDefaultImage(SWTResourceManager.getImage(DynamicWindow.class,"/resources/icons/x16/sitemap.png"));
		this.description = desc;
		this.cqs = cqs;
		this.ocls = ocls;
	}
	
	public static DROPDescription createDialog(String description, ArrayList<String> cqs, ArrayList<String> ocls) {
		Display display = Display.getDefault();	
		shell = display.getActiveShell();	
		if(shell == null){
			shell = new Shell(display);
		}
		UtilPattern.centralizeShell(display, shell);
		DROPDescription resultDIalog = new DROPDescription(shell, description, cqs, ocls);
		
		resultDIalog.create();
		
		return resultDIalog;
	}
	
	@Override
	public void create() {
	    super.create();
	    setShellStyle(SWT.TITLE);
	    UtilPattern.bringToFront(shell);
	    getShell().setText("DROP Description");	    
	}
	
	@Override
	protected Button createButton(Composite parent, int id, String label, boolean defaultButton) {
	    return null;
	}
	private Composite composite;
	private Text descr;
	
	@Override
	protected Control createDialogArea(Composite parent){
		composite = (Composite) super.createDialogArea(parent);
		composite.setBounds(0, 0, 596, 260);
		composite.setLayout(null);
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.setBounds(254, 355, 75, 25);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				close();
			}
		});
		btnNewButton.setText("Close");
		
		descr = new Text(composite, SWT.BORDER | SWT.READ_ONLY | SWT.V_SCROLL | SWT.MULTI);
		descr.setEditable(false);
		descr.setBounds(10, 27, 572, 85);
		descr.setText(description);
		
		Label lblDescription = new Label(composite, SWT.NONE);
		lblDescription.setBounds(10, 6, 141, 15);
		lblDescription.setText("Description:");
		
		List listCQs = new List(composite, SWT.BORDER);
		listCQs.setBounds(10, 149, 572, 94);
		
		for(String cq : cqs){
			listCQs.add(cq);
		}
		
		Label lblCompetenceQuestion = new Label(composite, SWT.NONE);
		lblCompetenceQuestion.setBounds(10, 128, 157, 15);
		lblCompetenceQuestion.setText("Competence Question: ");
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setBounds(10, 249, 319, 15);
		lblNewLabel.setText("Object Constraint Language (OCL):");
		
		List listOCLs = new List(composite, SWT.BORDER);
		listOCLs.setBounds(10, 273, 572, 68);
		
		for(String ocl : ocls){
			listOCLs.add(ocl);
		}
		
		return composite;
	}
}