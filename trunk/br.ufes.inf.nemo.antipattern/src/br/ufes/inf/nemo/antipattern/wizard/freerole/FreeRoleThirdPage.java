package br.ufes.inf.nemo.antipattern.wizard.freerole;

import java.util.HashSet;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;

import RefOntoUML.Classifier;
import RefOntoUML.Property;
import RefOntoUML.parser.OntoUMLNameHelper;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.freerole.FreeRoleOccurrence;

public class FreeRoleThirdPage extends FreeRolePage {
	
	private StyledText styledText;
	private Button btnParticularDependency;
	private Button btnIndependentDependecy;
	
	public FreeRoleThirdPage(FreeRoleOccurrence freeRole, int freeRoleIndex) 
	{
		super(freeRole);		
		this.index = freeRoleIndex;
		setDescription(	"Defined Role: " +freeRole.getDependentType().getName()+
						"\nCurrent Free Role: "+freeRole.getFreeRoles().get(index).getName());
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		String definedRoleName = OntoUMLNameHelper.getTypeAndName(occurrence.getDependentType(), true, true);
		Composite container = new Composite(parent, SWT.NULL);
	
		setControl(container);
		
		styledText = new StyledText(container, SWT.WRAP | SWT.READ_ONLY | SWT.V_SCROLL);
		styledText.setAlwaysShowScrollBars(false);
		styledText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledText.setText(	"If "+OntoUMLNameHelper.getTypeAndName(occurrence.getFreeRoles().get(index), true, true)+" is indeed a intentional subtype of "+definedRoleName+", " +
							"it must be characterized by another relational dependency, i.e., be related to a «Relator» through a «Mediation». " +
							"Is the new dependency a particular type of the existing one(s), owned or inherited by "+definedRoleName+", or a completely independent one? " +
							"\r\n\r\n" +
							"To help you decide which is your case, consider the following domain of education: " +
							"\r\n\r\n" +
							"There are Univerisities which provide Bachelor, Masters and Doctoral degrees. The general concept of Student is that one has an Enrollment in a University. " +
							"The difference between being a Bachelor, Masters or Doctoral students is the PARTICULAR types of enrollment one can have. " +
							"Tutors, on the other hand, are Students which agree to help others in their academic efforts, and are characterized by an INDEPENDENT dependency, the Tutorship.");
		styledText.setJustify(true);
		
		btnParticularDependency = new Button(container, SWT.RADIO);
		btnParticularDependency.setText("Particular dependency (relator specialization)");
		
		btnIndependentDependecy = new Button(container, SWT.RADIO);
		btnIndependentDependecy.setText("Independent dependecy (new relator)");
		
		setAsEnablingNextPageButton(btnIndependentDependecy);
		setAsEnablingNextPageButton(btnParticularDependency);
		
		Label label = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		
		Label lblCurrentRelatorSubtypes = new Label(container, SWT.NONE);
		lblCurrentRelatorSubtypes.setText("Existing Relators and Their Subtypes:");
		
		List list = new List(container, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		HashSet<Classifier> relators = new HashSet<Classifier>();
		String line;
		
		for (Property relatorEnd : occurrence.getDefiningRelatorEnds()) {	
			
			if(!relators.contains(relatorEnd.getType())){		
				line = OntoUMLNameHelper.getTypeAndName(relatorEnd.getType(), true, false)+" (From: "+OntoUMLNameHelper.getTypeAndName(relatorEnd.getOpposite().getType(), true, false)+")";
				list.add(line);
				relators.add((Classifier) relatorEnd.getType());	
			}
			
			 if(!((Antipattern<FreeRoleOccurrence>)occurrence.getAntipattern()).getParser().allChildrenHash.containsKey(relatorEnd.getType()))
				 continue;
			
			for (Classifier child : ((Antipattern<FreeRoleOccurrence>)occurrence.getAntipattern()).getParser().allChildrenHash.get(relatorEnd.getType())) {
				if(!relators.contains(child)){
					line = OntoUMLNameHelper.getTypeAndName(child, true, false)+" (Parent: "+OntoUMLNameHelper.getTypeAndName(relatorEnd.getType(), true, false)+", From: "+OntoUMLNameHelper.getTypeAndName(relatorEnd.getOpposite().getType(), true, false)+")";
					list.add(line);
					relators.add(child);
				}
			}
		}
		
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
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(label, GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
					.addContainerGap())
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(lblCurrentRelatorSubtypes)
					.addContainerGap(552, Short.MAX_VALUE))
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(list, GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
					.addContainerGap())
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
					.add(18)
					.add(label, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(lblCurrentRelatorSubtypes)
					.add(4)
					.add(list, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))
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
		
