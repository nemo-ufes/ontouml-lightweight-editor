package br.ufes.inf.nemo.oled.antipattern;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;

import br.ufes.inf.nemo.antipattern.Fix;
import br.ufes.inf.nemo.oled.AppFrame;

public class AntiPatternModifDialog extends Dialog {

	public Fix fix;
	
	//GUI
	public AppFrame frame;
	public Label statusLabel;	
	public List modifiedList;			
	public List addedList;			
	public List removedList;	
	public StyledText rulesText;
	
	/**
	 * Create the dialog.
	 * @param parentShell
	 * @wbp.parser.constructor
	 */
	public AntiPatternModifDialog(Shell parentShell) {
		super(parentShell);
	}

	public AntiPatternModifDialog(Shell parentShell, Fix fix, AppFrame frame)
	{
		super(parentShell);
		this.fix = fix;
		this.frame = frame;
		setDefaultImage(new Image(Display.getDefault(),AntiPatternModifDialog.class.getResourceAsStream("/resources/br/ufes/inf/nemo/oled/ui/antipattern-36x36.png")));	
	}
	
	public static int openDialog(final Fix fix, final AppFrame frame)
	{
    	Display display = Display.getDefault();	    	
		Shell shell = display.getActiveShell();			
		AntiPatternModifDialog resultDialog = new AntiPatternModifDialog(shell, fix, frame);				
		resultDialog.create();				
		return resultDialog.open();							
	}
	
	public void addFix()
	{		
		for(Object obj: fix.getModified()) modifiedList.add(obj.toString());
		for(Object obj: fix.getAdded()) addedList.add(obj.toString());
		for(Object obj: fix.getDeleted()) removedList.add(obj.toString());
		rulesText.setText(rulesText.getText()+"\n"+fix.getRulesString());
		
		int qtde= modifiedList.getItemCount()+addedList.getItemCount()+removedList.getItemCount();
		if (!fix.getRulesString().isEmpty()) qtde++;
		if(qtde==0) showStatus(true);
		else showStatus(false);		
	}
	
	public void showStatus (boolean value)
	{
		statusLabel.setVisible(value);		
	}
	
	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		Label introLabel = new Label(container, SWT.NONE);
		introLabel.setText("Congratulations! You successfully verified the antipattern.");
		
		Label modifiedLabel = new Label(container, SWT.NONE);
		modifiedLabel.setText("Modified elements:");
		
		Label addedLabel = new Label(container, SWT.NONE);
		addedLabel.setText("Added elements:");
		
		Label removedLabel = new Label(container, SWT.NONE);
		removedLabel.setText("Removed Elements:");
		
		modifiedList = new List(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
				
		addedList = new List(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		
		removedList = new List(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		
		Label rulesLabel = new Label(container, SWT.NONE);
		rulesLabel.setText("Added OCL rules:");
		
		rulesText = new StyledText(container, SWT.BORDER);
		
		statusLabel = new Label(container, SWT.NONE);
		statusLabel.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		statusLabel.setAlignment(SWT.CENTER);
		statusLabel.setText("This antipattern did not characterized an error.");
				
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(gl_container.createSequentialGroup()
							.add(10)
							.add(introLabel, GroupLayout.PREFERRED_SIZE, 552, GroupLayout.PREFERRED_SIZE))
						.add(gl_container.createSequentialGroup()
							.addContainerGap()
							.add(gl_container.createParallelGroup(GroupLayout.LEADING)
								.add(gl_container.createSequentialGroup()
									.add(gl_container.createParallelGroup(GroupLayout.LEADING, false)
										.add(modifiedLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.add(modifiedList, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(LayoutStyle.RELATED)
									.add(gl_container.createParallelGroup(GroupLayout.LEADING, false)
										.add(GroupLayout.TRAILING, addedLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.add(GroupLayout.TRAILING, addedList, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(LayoutStyle.UNRELATED)
									.add(gl_container.createParallelGroup(GroupLayout.TRAILING, false)
										.add(removedLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.add(removedList, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)))
								.add(gl_container.createParallelGroup(GroupLayout.TRAILING)
									.add(GroupLayout.LEADING, rulesLabel, GroupLayout.PREFERRED_SIZE, 551, GroupLayout.PREFERRED_SIZE)
									.add(GroupLayout.LEADING, rulesText, GroupLayout.PREFERRED_SIZE, 551, GroupLayout.PREFERRED_SIZE))
								.add(statusLabel, GroupLayout.PREFERRED_SIZE, 551, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(introLabel)
					.add(18)
					.add(gl_container.createParallelGroup(GroupLayout.BASELINE)
						.add(modifiedLabel)
						.add(removedLabel)
						.add(addedLabel))
					.addPreferredGap(LayoutStyle.RELATED)
					.add(gl_container.createParallelGroup(GroupLayout.BASELINE, false)
						.add(modifiedList, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
						.add(addedList, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
						.add(removedList, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(LayoutStyle.RELATED)
					.add(rulesLabel)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(rulesText, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(statusLabel)
					.addContainerGap())
		);
		gl_container.linkSize(new Control[] {modifiedList, addedList, removedList}, GroupLayout.VERTICAL);
		container.setLayout(gl_container);

		return container;
	}

	@Override
	public void create() {
	    super.create();
	    setShellStyle(SWT.TITLE);
	    bringToFront(getShell());
	    getShell().setText("Anti-Pattern Modifications");
	    addFix();
	}
	
	public void bringToFront(final Shell shell) {
	    shell.getDisplay().asyncExec(new Runnable() {
	        public void run() {
	            shell.forceActive();
	        }
	    });
	}
	
	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	@Override
	protected boolean isResizable() {	
		return true;
	}
	
	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(588, 409);
	}

}
