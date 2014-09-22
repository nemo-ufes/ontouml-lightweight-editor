package br.ufes.inf.nemo.antipattern.wizard.decint;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;

import RefOntoUML.Classifier;
import RefOntoUML.parser.OntoUMLNameHelper;
import br.ufes.inf.nemo.antipattern.decint.DecIntOccurrence;

public class IntentionalDerivedPage  extends DecIntPage {

	private Button btnYes;
	private Button btnNo;
	
	public IntentionalDerivedPage(DecIntOccurrence decint) 
	{
		super(decint);		
	}
	
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		String subtypeName = decint.getSubtype().getName();
		
		Composite container = new Composite(parent, SWT.NONE);
		setControl(container);			
		
		StyledText questionText = new StyledText(container, SWT.READ_ONLY | SWT.WRAP);
		questionText.setBackground(questionText.getParent().getBackground());
		questionText.setText(	"Is it possible for an individual to be an instance of all direct concrete supertypes of "+OntoUMLNameHelper.getTypeAndName(decint.getSubtype(), true, true)+
								" but not be an instance of the type itself?");
		questionText.setJustify(true);
		
		btnYes = new Button(container, SWT.RADIO);
		btnYes.setText("Yes, <"+subtypeName+"> is an intentional subtype");
		btnYes.addSelectionListener(canGoToNextPageListener);
		
		btnNo = new Button(container, SWT.RADIO);
		btnNo.setText("No, <"+subtypeName+"> is derived by the intersection of its supertypes");
		
		label = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		
		lblDirectSupertypes = new Label(container, SWT.NONE);
		lblDirectSupertypes.setText("Concrete direct super-types:");
		
		list = new List(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		for (Classifier c : decint.getRelevantParents()) {
			list.add(OntoUMLNameHelper.getTypeAndName(c, true, true));
		}
		
		lblNewLabel = new Label(container, SWT.WRAP | SWT.RIGHT);
		lblNewLabel.setText("Concrete type: a type that has the isAbstract meta attributte set to false and does not participate (as the parent type) in a complete generalization set");
		
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(lblNewLabel, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
					.addContainerGap())
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(list, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
					.addContainerGap())
				.add(GroupLayout.TRAILING, gl_container.createSequentialGroup()
					.addContainerGap()
					.add(lblDirectSupertypes, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
					.addContainerGap())
				.add(GroupLayout.TRAILING, gl_container.createSequentialGroup()
					.addContainerGap()
					.add(label, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
					.addContainerGap())
				.add(GroupLayout.TRAILING, gl_container.createSequentialGroup()
					.add(9)
					.add(btnNo, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
					.addContainerGap())
				.add(GroupLayout.TRAILING, gl_container.createSequentialGroup()
					.add(9)
					.add(btnYes, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
					.addContainerGap())
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(questionText, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
					.add(10))
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(questionText, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(btnYes)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(btnNo)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(label, GroupLayout.PREFERRED_SIZE, 7, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.UNRELATED)
					.add(lblDirectSupertypes)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(list, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(lblNewLabel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
		);
		container.setLayout(gl_container);
		btnNo.addSelectionListener(canGoToNextPageListener);
	
		setPageComplete(false);

	}
	
	private SelectionListener canGoToNextPageListener = new SelectionAdapter() {
		
		@Override
		public void widgetSelected(SelectionEvent arg0) {
			
			if(btnNo.getSelection() || btnYes.getSelection()){
				if(!isPageComplete())
					setPageComplete(true);
			}
			else{
				if(isPageComplete())
					setPageComplete(false);
			}
			
		}
	};
	private Label label;
	private Label lblDirectSupertypes;
	private List list;
	private Label lblNewLabel;
	
	@Override
	public IWizardPage getNextPage() 
	{	
		if(btnNo.getSelection()){
			DecIntAction action = new DecIntAction(decint);
			action.setDeriveByIntersection();
			getDecIntWizard().replaceAction(2, action);
			return getDecIntWizard().getFinishing();
		}
		else if (btnYes.getSelection()){
			getDecIntWizard().removeAllActions(2);
			return getDecIntWizard().getFinishing();
		}
		
		return super.getNextPage();
	}
}

