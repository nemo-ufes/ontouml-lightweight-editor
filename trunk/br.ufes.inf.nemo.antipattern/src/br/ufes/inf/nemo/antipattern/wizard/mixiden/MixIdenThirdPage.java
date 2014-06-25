package br.ufes.inf.nemo.antipattern.wizard.mixiden;

import java.util.ArrayList;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import RefOntoUML.Classifier;
import br.ufes.inf.nemo.antipattern.mixiden.MixIdenAntipattern;
import br.ufes.inf.nemo.antipattern.mixiden.MixIdenOccurrence;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;

public class MixIdenThirdPage  extends MixIdenPage {

	//GUI
	AddSelectSortalComposite addSelect;
	private Button btnYes;
	private Button btnNo;	
	private Label lblSelectExistingTypes;
		
	public MixIdenThirdPage(MixIdenOccurrence mixIdenOccurrence) {
		super(mixIdenOccurrence);	
	}
	
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		
		setTitle(MixIdenAntipattern.getAntipatternInfo().getName());
		setDescription(	"Mixin: "+mixIden.getMixin().getName()+
						"\r\nSubtypes: "+getSubtypeList(3)+
						"\r\nCommon Identity Provider: "+mixIden.getIdentityProvider().getName());
		
		Composite container = new Composite(parent, SWT.NULL);
		setControl(container);	
		
		setPageComplete(false);
		
		StyledText styledText = new StyledText(container, SWT.READ_ONLY | SWT.WRAP);
		styledText.setText(	"Are any of the types that can specialize <"+getMixIdenWizard().mixinName+"> and " +
							"follow a different identity principle from the one supplied by <"+mixIden.getIdentityProvider().getName()+">, " +
							"in the scope of the ontology?");
		styledText.setJustify(true);
		styledText.setBackground(styledText.getParent().getBackground());
		
		btnNo = new Button(container, SWT.RADIO);
		btnNo.setText("No, they are all out of scope");
		btnNo.addSelectionListener(btnNoListener);
		
		btnYes = new Button(container, SWT.RADIO);
		btnYes.setText("Yes");
		btnYes.addSelectionListener(btnYesListener);
		
		try {
			ArrayList<Classifier> forbbidenTypes = new ArrayList<Classifier>(mixIden.getIdentityProvider().allChildren());
			forbbidenTypes.add(mixIden.getIdentityProvider());
			
			addSelect = new AddSelectSortalComposite(container, SWT.BORDER, mixIden.getParser(), 
					mixIden.allowedSubtypeStereotypes(), mixIden.identityProviderStereotypes(), forbbidenTypes);
			addSelect.setAllEnabled(false);
			
			lblSelectExistingTypes = new Label(container, SWT.NONE);
			lblSelectExistingTypes.setText("If Yes, please select existing types or create new ones using the table below:");
			GroupLayout gl_container = new GroupLayout(container);
			gl_container.setHorizontalGroup(
				gl_container.createParallelGroup(GroupLayout.LEADING)
					.add(GroupLayout.TRAILING, gl_container.createSequentialGroup()
						.add(10)
						.add(gl_container.createParallelGroup(GroupLayout.TRAILING)
							.add(GroupLayout.LEADING, addSelect, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
							.add(GroupLayout.LEADING, styledText, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE))
						.add(10))
					.add(GroupLayout.TRAILING, gl_container.createSequentialGroup()
						.add(9)
						.add(lblSelectExistingTypes, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.addContainerGap())
					.add(gl_container.createSequentialGroup()
						.addContainerGap()
						.add(btnYes, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.add(9))
					.add(gl_container.createSequentialGroup()
						.addContainerGap()
						.add(btnNo, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.add(9))
			);
			gl_container.setVerticalGroup(
				gl_container.createParallelGroup(GroupLayout.LEADING)
					.add(gl_container.createSequentialGroup()
						.add(10)
						.add(styledText, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.add(16)
						.add(btnNo)
						.addPreferredGap(LayoutStyle.RELATED)
						.add(btnYes)
						.add(14)
						.add(lblSelectExistingTypes)
						.addPreferredGap(LayoutStyle.RELATED)
						.add(addSelect, GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
						.add(9))
			);
			container.setLayout(gl_container);
		} catch (Exception e) {
			System.out.println("ERROR!");
		}
		
	}
	
	private SelectionAdapter btnNoListener = new SelectionAdapter() {
		
		public void widgetSelected(SelectionEvent event) {
			if(btnNo.getSelection()){
				if(!isPageComplete()) setPageComplete(true);
				addSelect.setAllEnabled(false);
			}
		}
	};
	
	private SelectionAdapter btnYesListener = new SelectionAdapter() {
		
		public void widgetSelected(SelectionEvent event) {
			if(btnYes.getSelection()){
				if(!isPageComplete()) setPageComplete(true);
				addSelect.setAllEnabled(true);
			}
		}
	};
	
	@Override
	public IWizardPage getNextPage() 
	{	
		getMixIdenWizard().removeAllActions();

		if(btnYes.getSelection()) {
			MixIdenAction action = new MixIdenAction(mixIden);
			System.out.println("ROWS: "+addSelect.getNewSortalSubtypes().size());
			action.setAddSubtypes(addSelect.getNewSortalSubtypes());
			getMixIdenWizard().addAction(0, action);
		}
		
		return getMixIdenWizard().getFinishing();
	
	}
}
