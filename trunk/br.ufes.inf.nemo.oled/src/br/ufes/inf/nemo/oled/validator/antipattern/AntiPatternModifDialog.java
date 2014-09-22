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

import org.eclipse.emf.ecore.EObject;
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

import RefOntoUML.parser.OntoUMLNameHelper;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.oled.AppFrame;

/**
 * @author Tiago Prince
 * @author John Guerson
 */
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
		setDefaultImage(new Image(Display.getDefault(),AntiPatternModifDialog.class.getResourceAsStream("/resources/icons/antipattern36.png")));	
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
		for(Object obj: fix.getModified()) modifiedList.add(OntoUMLNameHelper.getCompleteName((EObject) obj));
		for(Object obj: fix.getAdded()) addedList.add(OntoUMLNameHelper.getCompleteName((EObject) obj));
		for(Object obj: fix.getDeleted()) removedList.add(OntoUMLNameHelper.getCompleteName((EObject) obj));
		rulesText.setText(rulesText.getText()+fix.getRulesString()+"\n\n");
		
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
		
		rulesText = new StyledText(container, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL );
		rulesText.setEditable(false);
		
		statusLabel = new Label(container, SWT.NONE);
		statusLabel.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		statusLabel.setAlignment(SWT.CENTER);
		statusLabel.setText("This antipattern did not characterized an error.");
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(introLabel, GroupLayout.PREFERRED_SIZE, 764, GroupLayout.PREFERRED_SIZE)
						.add(modifiedLabel, GroupLayout.PREFERRED_SIZE, 764, GroupLayout.PREFERRED_SIZE)
						.add(modifiedList, GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
						.add(addedLabel, GroupLayout.PREFERRED_SIZE, 764, GroupLayout.PREFERRED_SIZE)
						.add(addedList, GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
						.add(removedLabel, GroupLayout.PREFERRED_SIZE, 764, GroupLayout.PREFERRED_SIZE)
						.add(removedList, GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
						.add(rulesLabel, GroupLayout.PREFERRED_SIZE, 764, GroupLayout.PREFERRED_SIZE)
						.add(rulesText, GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
						.add(statusLabel, GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE))
					.add(10))
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(introLabel)
					.add(18)
					.add(modifiedLabel)
					.add(8)
					.add(modifiedList, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
					.add(6)
					.add(addedLabel)
					.add(6)
					.add(addedList, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
					.add(10)
					.add(removedLabel)
					.add(6)
					.add(removedList, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
					.add(11)
					.add(rulesLabel)
					.add(6)
					.add(rulesText, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
					.add(43)
					.add(statusLabel)
					.add(21))
		);
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
	        	if(!shell.isDisposed())
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
		return new Point(800, 697);
	}

}
