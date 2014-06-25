package br.ufes.inf.nemo.antipattern.wizard.mixiden;

import java.util.ArrayList;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import RefOntoUML.Classifier;
import br.ufes.inf.nemo.antipattern.mixiden.MixIdenAntipattern;
import br.ufes.inf.nemo.antipattern.mixiden.MixIdenOccurrence;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;

public class MixIdenSecondPage  extends MixIdenPage {

	//GUI
	ChangeIdentityProviderComposite changeIdentityComposite;
	private Button btnNo;
	private Button btnYes;	
		
	public MixIdenSecondPage(MixIdenOccurrence mixIdenOccurrence) {
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
		styledText.setText(	"Do all subtypes indeed inherits their identity principle from <"+mixIden.getIdentityProvider().getName()+">? " +
							"If No, use the table below to change the identity principle suppliers.");
		styledText.setJustify(true);
		styledText.setBackground(styledText.getParent().getBackground());
		
		btnYes = new Button(container, SWT.RADIO);
		btnYes.setText("Yes, they all inherit from it");
		btnYes.addSelectionListener(btnYesListener);
		
		btnNo = new Button(container, SWT.RADIO);
		btnNo.setText("No");
		btnNo.addSelectionListener(btnNoListener);
		
		try {
			ArrayList<Classifier> forbbidenTypes = new ArrayList<Classifier>(mixIden.getIdentityProvider().allChildren());
			forbbidenTypes.add(mixIden.getIdentityProvider());
			
			changeIdentityComposite = new ChangeIdentityProviderComposite(container, SWT.BORDER, mixIden,btnNoListener);
			changeIdentityComposite.setAllEnabled(false);
			GroupLayout gl_container = new GroupLayout(container);
			gl_container.setHorizontalGroup(
				gl_container.createParallelGroup(GroupLayout.LEADING)
					.add(gl_container.createSequentialGroup()
						.addContainerGap()
						.add(btnYes, GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
						.add(9))
					.add(GroupLayout.TRAILING, gl_container.createSequentialGroup()
						.add(10)
						.add(gl_container.createParallelGroup(GroupLayout.TRAILING)
							.add(GroupLayout.LEADING, styledText, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
							.add(GroupLayout.LEADING, changeIdentityComposite, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE))
						.add(10))
					.add(gl_container.createSequentialGroup()
						.addContainerGap()
						.add(btnNo, GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
						.add(9))
			);
			gl_container.setVerticalGroup(
				gl_container.createParallelGroup(GroupLayout.LEADING)
					.add(gl_container.createSequentialGroup()
						.add(10)
						.add(styledText, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.RELATED)
						.add(btnYes)
						.addPreferredGap(LayoutStyle.UNRELATED)
						.add(btnNo)
						.add(20)
						.add(changeIdentityComposite, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE))
			);
			container.setLayout(gl_container);

		} catch (Exception e) {
			System.out.println("ERROR!");
		}
		
	}
	
	private SelectionAdapter btnYesListener = new SelectionAdapter() {
		
		public void widgetSelected(SelectionEvent event) {
			if(btnYes.getSelection()){
				if(!isPageComplete()) setPageComplete(true);
				changeIdentityComposite.setAllEnabled(false);
			}
		}
	};
	
	private SelectionAdapter btnNoListener = new SelectionAdapter() {
		
		public void widgetSelected(SelectionEvent event) {
			if(btnNo.getSelection()){
				if(!changeIdentityComposite.isAllEnabled())
					changeIdentityComposite.setAllEnabled(true);
				
				if(changeIdentityComposite.getSubtypesToFix().size()>0)
					setPageComplete(true);
				else
					setPageComplete(false);
				
			}
		}
	};
	
	@Override
	public IWizardPage getNextPage() 
	{	
		getMixIdenWizard().removeAllActions();

		if(btnNo.getSelection()) {
			MixIdenAction action = new MixIdenAction(mixIden);
			action.setChangeSubtypesIdentity(changeIdentityComposite.getSubtypesToFix());
			getMixIdenWizard().addAction(0, action);
			return getMixIdenWizard().getFinishing();
		}
		else if(btnYes.getSelection())
			getMixIdenWizard().getThirdPage();
		
		return super.getNextPage();
	
	}
}
