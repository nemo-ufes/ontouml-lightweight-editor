package br.ufes.inf.nemo.antipattern.wizard.undefformal;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;

import RefOntoUML.parser.OntoUMLNameHelper;
import br.ufes.inf.nemo.antipattern.undefformal.UndefFormalOccurrence;

public class CreateDataTypePage extends UndefFormalPage{

	public Composite parent;
	private DataTypeComposite createDataTypeComposite;
	
	/**
	 * Create the wizard.
	 */
	public CreateDataTypePage(UndefFormalOccurrence uf) 
	{
		super(uf);
		setDescription(	"Formal: "+uf.getFormalName()+
				"\nSource: "+uf.getSource().getName()+", Target: "+uf.getTarget().getName());
	}

	@Override
	public void createControl(Composite parent) 
	{
		this.parent = parent;
		
		Composite container = new Composite(parent, SWT.NONE);		
		setControl(container);
		
		createDataTypeComposite = new DataTypeComposite(container, SWT.NONE, (UndefFormalOccurrence) occurrence);
		
		StyledText questionText = new StyledText(container, SWT.WRAP | SWT.READ_ONLY | SWT.V_SCROLL);
		questionText.setText("If "+OntoUMLNameHelper.getTypeAndName(occurrence.getFormal(), true, true)+" is a Domain Comparative Formal Relation it means that both related ends should own or inherit qualities, " +
							"which is not currently the case for "+OntoUMLNameHelper.getTypeAndName(occurrence.getSource(), true, true)+" and "+OntoUMLNameHelper.getTypeAndName(occurrence.getTarget(), true, true)+". " +
							"\r\n\r\n" +
							"Please create the missing qualities and specify the OCL rule that derives the relation:");
		questionText.setBackground(questionText.getParent().getBackground());
		questionText.setJustify(true);
		questionText.setAlwaysShowScrollBars(false);
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(GroupLayout.TRAILING, gl_container.createSequentialGroup()
					.add(10)
					.add(questionText, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
					.add(10))
				.add(createDataTypeComposite, GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(questionText, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
					.add(6)
					.add(createDataTypeComposite, GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE))
		);
		container.setLayout(gl_container);
	}
	
	@Override
	public IWizardPage getNextPage() 
	{	
		//Action =============================
		UndefFormalAction newAction = new UndefFormalAction(occurrence);
		newAction.setCreateDatatypesAttributesAndRules(occurrence.getFormal(),occurrence.getSource(),occurrence.getTarget(),
			createDataTypeComposite.getSourceMapType(), createDataTypeComposite.getTargetMapType(), createDataTypeComposite.getSourceMapStereo(), 
			createDataTypeComposite.getTargetMapStereo(), createDataTypeComposite.getConstraints());
		getAntipatternWizard().replaceAction(0,newAction);	
		//======================================
		
		return ((UndefFormalWizard)getWizard()).getFinishing();	
	}
}

