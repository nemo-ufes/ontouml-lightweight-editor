package br.ufes.inf.nemo.antipattern.wizard.freerole;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;

import br.ufes.inf.nemo.antipattern.freerole.FreeRoleOccurrence;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLNameHelper;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;

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
		String definedRoleName = OntoUMLNameHelper.getTypeAndName(occurrence.getDefinedRole(), true, true);
		Composite container = new Composite(parent, SWT.NULL);
	
		setControl(container);
		
		styledText = new StyledText(container, SWT.WRAP | SWT.READ_ONLY | SWT.V_SCROLL);
		styledText.setAlwaysShowScrollBars(false);
		styledText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledText.setText(	"If "+OntoUMLNameHelper.getTypeAndName(occurrence.getFreeRoles().get(index), true, true)+" is indeed a intentional subtype of "+definedRoleName+", " +
							"it must be characterized by another relational dependency, i.e., be related to a «relator» through a «mediation». " +
							"Is the new dependency a particular type of the existing one(s), from <"+definedRoleName+">, or a completely independent one? " +
							"\r\n\r\n" +
							"To help you decide which is your case, consider the following domain of education: " +
							"\r\n\r\n" +
							"There are Univerisities which provide Bachelor, Masters and Doctoral degrees. The general concept of Student is that one has an Enrollment in a University" +
							"The difference between being a Bachelor, Masters or Doctoral students is the PARTICULAR types of enrollment one can have. " +
							"Tutors, on the other hand, are Students which agree to help others in their academic efforts, and are characterized by an INDEPENDENT dependency, the Tutorship.");
		styledText.setJustify(true);
		
		btnParticularDependency = new Button(container, SWT.RADIO);
		btnParticularDependency.setText("Particular dependency (relator specialization)");
		
		btnIndependentDependecy = new Button(container, SWT.RADIO);
		btnIndependentDependecy.setText("Independent dependecy (new relator)");
		
		setAsEnablingNextPageButton(btnIndependentDependecy);
		setAsEnablingNextPageButton(btnParticularDependency);
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(styledText, GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
						.add(btnParticularDependency, GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
						.add(btnIndependentDependecy, GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE))
					.add(10))
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(styledText, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
					.add(6)
					.add(btnParticularDependency)
					.add(6)
					.add(btnIndependentDependecy)
					.add(42))
		);
		container.setLayout(gl_container);
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
		
