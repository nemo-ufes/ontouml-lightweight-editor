package br.ufes.inf.nemo.antipattern.wizard.freerole;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;

import br.ufes.inf.nemo.antipattern.freerole.FreeRoleOccurrence;

public class FreeRoleThirdPage extends FreeRolePage {
	
	private StyledText styledText;
	private Button btnParticularDependency;
	private Button btnIndependentDependecy;
	
	public FreeRoleThirdPage(FreeRoleOccurrence freeRole, int freeRoleIndex) 
	{
		super(freeRole);		
		this.index = freeRoleIndex;
		setDescription(	"Defined Role: " +freeRole.getDefinedRole().getName()+
						"\nCurrent Free Role: "+freeRole.getFreeRoles().get(index).getName());
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
	
		setControl(container);
		
		styledText = new StyledText(container, SWT.WRAP | SWT.READ_ONLY | SWT.V_SCROLL);
		styledText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledText.setText(	"If <"+occurrence.getFreeRoles().get(index).getName()+"> is indeed a intentional subtype of <"+occurrence.getDefinedRole().getName()+">, " +
							"it must be characterized by another relational dependency, i.e., be related to a «relator» through a «mediation». " +
							"Is the new dependency a particular type of the existing one(s), from <"+occurrence.getDefinedRole().getName()+">, or a completely independent one? " +
							"\r\n\r\n" +
							"To help you decide which is your case, consider the following domain of education: " +
							"\r\n\r\n" +
							"There are Univerisities which provide Bachelor, Masters and Doctoral degrees. The general concept of Student is that one has an Enrollment in a University" +
							"The difference between being a Bachelor, Masters or Doctoral students is the PARTICULAR types of enrollment one can have. " +
							"Tutors, on the other hand, are Students which agree to help others in their academic efforts, and are characterized by an INDEPENDENT dependency, the Tutorship.");
	
		styledText.setBounds(10, 10, 554, 185);
		styledText.setJustify(true);
		
		btnParticularDependency = new Button(container, SWT.RADIO);
		btnParticularDependency.setBounds(10, 201, 554, 16);
		btnParticularDependency.setText("Particular dependency (relator specialization)");
		
		btnIndependentDependecy = new Button(container, SWT.RADIO);
		btnIndependentDependecy.setBounds(10, 223, 554, 16);
		btnIndependentDependecy.setText("Independent dependecy (new relator)");
		
		setAsEnablingNextPageButton(btnIndependentDependecy);
		setAsEnablingNextPageButton(btnParticularDependency);
		setPageComplete(false);
	}
	
	@Override
	public IWizardPage getNextPage() 
	{	
		if (btnParticularDependency.getSelection()) {			
			FreeRolePage fifthPage = ((FreeRoleWizard)getWizard()).getFifthPage(index);			
			return fifthPage;
		}
		if(btnIndependentDependecy.getSelection()){
			FreeRoleFourthPage fourthPage = ((FreeRoleWizard)getWizard()).getFourthPage(index);			
			return fourthPage;
		}
		return super.getNextPage();
	}
}
		
