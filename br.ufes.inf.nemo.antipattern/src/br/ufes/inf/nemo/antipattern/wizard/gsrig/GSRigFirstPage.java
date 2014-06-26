package br.ufes.inf.nemo.antipattern.wizard.gsrig;

import java.text.Normalizer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import br.ufes.inf.nemo.antipattern.GSRig.GSRigOccurrence;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;

public class GSRigFirstPage extends GSRigPage {

	private Button btnMultipleCriteria;
	private Button btnNoCommonCriterion;
	private Button btnSameCriterion;
	private StyledText styledText;
	private Label lblNote;
	
	public GSRigFirstPage(GSRigOccurrence gsrig) 
	{
		super(gsrig);		
	}

	public static String getStereotype(EObject element)
	{
		String type = element.getClass().toString().replaceAll("class RefOntoUML.impl.","");
	    type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);
	    if (!type.equalsIgnoreCase("association")) type = type.replace("Association","");
	    return type;
	}
	
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		
		styledText = new StyledText(container, SWT.WRAP| SWT.V_SCROLL);
		styledText.setText(	"A Generalization Set (GS) is a group of Generalizations of a common supertype. It captures a common criterion of specialization for all subtypes and restricts their instantiation. " +
							"\r\n\r\n"+
							"A specialization criterion is what is used to define why an instance of a type becomes an instance of one of its subtypes. " +
							"To clarify, consider the types Person, Man, Woman, Child and Adult. " +
							"Man and Woman are rigid subtypes of Person, while Child and Adult phases of Person. " +
							"An instance of Person is classified as Male or Female according to its gender, whilst it is classified as Child or Adult through the evaluation of its age. " +
							"In this case we have to different specialization criteria: one for gender and one for age." +
							"\r\n\r\n" +
							"The GS <"+gsrig.getGs().getName()+"> of the RIGID super-type <"+gsrig.getGs().getGeneralization().get(0).getGeneral().getName()+">, contains some subtypes that are RIGID and some that are ANTI-RIGID. " +
							"That is a strong suggestion that there is more than one specialization criterion in the same generalization set. Is that the case?");
		styledText.setJustify(true);
		styledText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		
		btnSameCriterion = new Button(container, SWT.RADIO);
		btnSameCriterion.setText("No, all subtypes follow the same criterion");
		btnSameCriterion.addSelectionListener(canGoToNextPageAdapter);
		
		btnMultipleCriteria = new Button(container, SWT.RADIO);
		btnMultipleCriteria.setText("Yes, there are multiple criteria");
		btnMultipleCriteria.addSelectionListener(canGoToNextPageAdapter);
		
		btnNoCommonCriterion = new Button(container, SWT.RADIO);
		btnNoCommonCriterion.setText("No, but in fact there is no common criterion");
		btnNoCommonCriterion.addSelectionListener(canGoToNextPageAdapter);
		
		lblNote = new Label(container, SWT.NONE);
		lblNote.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNote.setAlignment(SWT.RIGHT);
		lblNote.setText("(NOTE that Specialization Criteria and Identity Principle are two completely different definitions)");
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(btnNoCommonCriterion, GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
						.add(btnSameCriterion, GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
						.add(btnMultipleCriteria, GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE))
					.addContainerGap())
				.add(GroupLayout.TRAILING, gl_container.createSequentialGroup()
					.add(100)
					.add(lblNote, GroupLayout.PREFERRED_SIZE, 554, GroupLayout.PREFERRED_SIZE)
					.add(11))
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(styledText, GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
					.add(11))
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(styledText, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(btnSameCriterion)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(btnNoCommonCriterion)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(btnMultipleCriteria)
					.add(44)
					.add(lblNote)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		container.setLayout(gl_container);
		
		setPageComplete(false);
	}
	
	private SelectionAdapter canGoToNextPageAdapter = new SelectionAdapter() {
		
		@Override
		public void widgetSelected(SelectionEvent arg0) {
			if(btnMultipleCriteria.getSelection() || btnNoCommonCriterion.getSelection() || btnSameCriterion.getSelection()){
				if(!isPageComplete())
					setPageComplete(true);
			}
			else {
				if(isPageComplete())
					setPageComplete(false);
			}
			
		}
	};
	
	@Override
	public IWizardPage getNextPage() 
	{	
		if(btnSameCriterion.getSelection()){
			return ((GSRigWizard)getWizard()).getThirdPage();
		}
		
		if(btnMultipleCriteria.getSelection()){
			return ((GSRigWizard)getWizard()).getSecondPage();
		}
		
		if(btnNoCommonCriterion.getSelection())
		{			
			//Action =============================
			GSRigAction newAction = new GSRigAction(gsrig);
			newAction.setDeleteGS(); 
			getGSRigWizard().replaceAction(0,newAction);	
			//======================================	
			return getGSRigWizard().getFinishing();
		}
		
		return super.getNextPage();
	}
}