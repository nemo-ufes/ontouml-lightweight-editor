package br.ufes.inf.nemo.antipattern.wizard.freerole;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;

import br.ufes.inf.nemo.antipattern.freerole.FreeRoleOccurrence;

public class FreeRoleThirdPage extends FreeRolePage {
	
	private int index = -1;
	private StyledText styledText;
	private Button btnParticularDependency;
	private Button btnIndependentDependecy;
	
	public FreeRoleThirdPage(FreeRoleOccurrence freeRole, int freeRoleIndex) 
	{
		super(freeRole);		
		this.index = freeRoleIndex;
		setDescription("Role "+freeRole.getFreeRoles().get(index).getName());
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
	
		setControl(container);
		
		styledText = new StyledText(container, SWT.WRAP);
		styledText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledText.setText("If "+freeRole.getFreeRoles().get(index).getName()+" is indeed a intentional subtype of "+freeRole.getDefinedRole().getName()+", as your previous answer " +
		"suggests, it must be characterized by another relational dependency (formalized in the model by a <<mediation>> association). \r\n\r\nIs the new dependency a particular type " +
		"of the existing one(s), from "+freeRole.getDefinedRole().getName()+", or a completely independent one? \r\n\r\nTo help you decide which is your case, consider the following " +
		"domain of education: There are Educational Institutions which provide Bachelor, Masters and Doctoral degrees. The general concept of Student is that one has an Enrollment in" +
		" such Educational Institutions. The difference between being a Bachelors, Masters or Doctoral student are the PARTICULAR types of enrollment one can have. Tutors, on the other " +
		"hand, are Students which agree to help others in their academic efforts, and are characterized by an INDEPENDENT dependency, the Tutorship. \r\n");
	
		styledText.setBounds(10, 10, 554, 208);
		
		btnParticularDependency = new Button(container, SWT.RADIO);
		btnParticularDependency.setBounds(10, 218, 554, 16);
		btnParticularDependency.setText("Particular dependency");
		
		btnIndependentDependecy = new Button(container, SWT.RADIO);
		btnIndependentDependecy.setBounds(10, 240, 554, 16);
		btnIndependentDependecy.setText("Independent dependecy");
	}
	
	@Override
	public IWizardPage getNextPage() 
	{	
		if (btnParticularDependency.getSelection()) {			
			FreeRoleFifthPage fifthPage = ((FreeRoleWizard)getWizard()).getFifthPage(index);			
			return fifthPage;
		}
		if(btnIndependentDependecy.getSelection()){
			FreeRoleFourthPage fourthPage = ((FreeRoleWizard)getWizard()).getFourthPage(index);			
			return fourthPage;
		}
		return super.getNextPage();
	}
}
		
