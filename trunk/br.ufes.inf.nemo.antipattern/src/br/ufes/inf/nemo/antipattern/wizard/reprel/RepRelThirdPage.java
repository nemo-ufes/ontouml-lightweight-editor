package br.ufes.inf.nemo.antipattern.wizard.reprel;

import java.util.ArrayList;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import RefOntoUML.Mediation;
import br.ufes.inf.nemo.antipattern.reprel.RepRelOccurrence;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;
import org.eclipse.swt.widgets.Control;

public class RepRelThirdPage extends RepRelPage {

	public RepRelTable rrtable;
	public Button btnCurrent;
	public Button btnHistorical;
	private Button resetButton;
	private Button removeButton;
	private Button addButton;
	
	private SelectionListener verifyAction = new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			setPageComplete();
		 }
	};
	
	public RepRelThirdPage(RepRelOccurrence rr) 
	{
		super(rr);
	}

	@Override
	public void createControl(Composite parent) {
	
		Composite container = new Composite(parent, SWT.NULL);
		setControl(container);
		
		setPageComplete(false);
		
		rrtable = new RepRelTable (container, SWT.BORDER, occurrence.getProblematicMediations(), verifyAction);
				
		StyledText styledText = new StyledText(container, SWT.WRAP);
		styledText.setText(	"If there is no \"repeated\" instances of the relator or if there is a limited number of them, please use the table below to specify them. " +
							"\r\n\r\n"+
							"For each row of the table below, an OCL invariant will be generated limiting the number of repeated combinations of the related types. " +
							"Please use as many lines as you need.");
		
		styledText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledText.setJustify(true);
		styledText.setAlwaysShowScrollBars(false);
		
		addButton = new Button(container, SWT.NONE);
		addButton.setText("Add");
		
		Label lblDoesIsIntended = new Label(container, SWT.NONE);
		lblDoesIsIntended.setText("Does "+occurrence.getRelator().getName()+" is intended with current or historical semantics?");

		btnCurrent = new Button(container, SWT.RADIO);
		btnCurrent.setText("Current");
		
		btnHistorical = new Button(container, SWT.RADIO);
		btnHistorical.setText("Historical");
		
		removeButton = new Button(container, SWT.NONE);
		removeButton.setText("Remove");
		
		resetButton = new Button(container, SWT.NONE);
		resetButton.setText("Reset");
		
		
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(gl_container.createParallelGroup(GroupLayout.TRAILING)
						.add(gl_container.createSequentialGroup()
							.addContainerGap()
							.add(btnHistorical, GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE))
						.add(gl_container.createSequentialGroup()
							.addContainerGap()
							.add(btnCurrent, GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE))
						.add(gl_container.createSequentialGroup()
							.addContainerGap()
							.add(lblDoesIsIntended, GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE))
						.add(gl_container.createSequentialGroup()
							.add(gl_container.createParallelGroup(GroupLayout.LEADING)
								.add(gl_container.createSequentialGroup()
									.addContainerGap()
									.add(rrtable, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE))
								.add(gl_container.createSequentialGroup()
									.add(10)
									.add(styledText, GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)))
							.add(1))
						.add(gl_container.createSequentialGroup()
							.add(addButton, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.RELATED)
							.add(removeButton)
							.addPreferredGap(LayoutStyle.RELATED)
							.add(resetButton, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(styledText, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(gl_container.createParallelGroup(GroupLayout.BASELINE)
						.add(removeButton)
						.add(addButton, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.add(resetButton))
					.addPreferredGap(LayoutStyle.RELATED)
					.add(rrtable, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
					.addPreferredGap(LayoutStyle.UNRELATED)
					.add(lblDoesIsIntended, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(btnCurrent)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(btnHistorical)
					.add(1))
		);
		gl_container.linkSize(new Control[] {addButton, removeButton, resetButton}, GroupLayout.HORIZONTAL);
		container.setLayout(gl_container);
		
		addButton.addSelectionListener(new SelectionAdapter() {
			 @Override
	            public void widgetSelected(SelectionEvent e) {
				 rrtable.addLine();	
				 setPageComplete();
			 }
		});
		
		removeButton.addSelectionListener(new SelectionAdapter() {
			 @Override
	         public void widgetSelected(SelectionEvent e) {
				 rrtable.removeLine(rrtable.getSelectionIndex(), true);			
				 setPageComplete();
			 }
		});	
		
		resetButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				rrtable.removeLines();
				rrtable.addLine();
				setPageComplete();
			 }
		});		
		
		btnHistorical.addSelectionListener(verifyAction);
		btnCurrent.addSelectionListener(verifyAction);
	}
	
	public void setPageComplete(){
		if(!isComplete()){
			if(isPageComplete())
				setPageComplete(false);
		}
		else{
			if(!isPageComplete())
				setPageComplete(true);
		}
	}
	
	public boolean isComplete(){
		ArrayList<ArrayList<Mediation>> mMatrix = rrtable.getSelections();
		
//		System.out.println("Current: "+btnCurrent.getSelection());
//		System.out.println("Historical: "+btnHistorical.getSelection());
//		System.out.println("Lines: "+mMatrix.size());
		
		if(!btnCurrent.getSelection() && !btnHistorical.getSelection())
			return false;
		
		if(mMatrix.size()==0)
			return false;
		
		for (int i = 0; i < mMatrix.size(); i++) {
			ArrayList<Mediation> line = mMatrix.get(i);
//			System.out.println("Line ("+i+"): "+line.size());
			if(line.size()>=2)
				return true;
		}
		
		return false;
	}
	
	@Override
	public IWizardPage getNextPage() 
	{		
		if(btnCurrent.getSelection()){
			ArrayList<ArrayList<Mediation>> mMatrix = rrtable.getSelections();			 
			ArrayList<Integer> ns = rrtable.getNs();			
			// Action =====================	
			RepRelAction newAction = new RepRelAction(occurrence);
			newAction.setCreateInvariant(mMatrix, ns);
			getAntipatternWizard().replaceAction(occurrence.getProblematicMediations().size(), newAction);
			//=============================
		}
		if(btnHistorical.getSelection()){			
			ArrayList<ArrayList<Mediation>> mMatrix = rrtable.getSelections();
			ArrayList<Integer> ns = rrtable.getNs();			
			// Action =====================	
			RepRelAction newAction = new RepRelAction(occurrence);
			newAction.setCreateInvariantWithQualities(mMatrix,ns);
			getAntipatternWizard().replaceAction(occurrence.getProblematicMediations().size(),newAction);	
			//=============================
		}
		
		if(btnHistorical.getSelection() || btnCurrent.getSelection()) 
			return ((RepRelWizard)getWizard()).getFinishing();
		
		return super.getNextPage();
	}
}
