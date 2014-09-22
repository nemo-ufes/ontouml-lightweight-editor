package br.ufes.inf.nemo.antipattern.wizard.undefphase;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;

import RefOntoUML.Phase;
import RefOntoUML.parser.ParsingElement;
import br.ufes.inf.nemo.antipattern.undefphase.UndefPhaseOccurrence;

public class UndefPhaseFirstPage extends UndefPhasePage{
		
	Composite parent;
	private Button btnYes;
	private Button btnNo;
	private StyledText styledText;
	
	/**
	 * Create the wizard.
	 */
	public UndefPhaseFirstPage(UndefPhaseOccurrence up) 
	{
		super(up);
		setDescription((new ParsingElement(up.getPartition(),true,"")).toString());
	}
	
	@Override
	public void createControl(Composite parent) 
	{
		this.parent = parent;
		Composite container = new Composite(parent, SWT.NULL);
		
		setPageComplete(false);
		
		setControl(container);
		
		styledText = new StyledText(container, SWT.READ_ONLY | SWT.WRAP | SWT.H_SCROLL);
		styledText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledText.setJustify(true);
		String str = "The Phase stereotype is used to characterize anti-rigid types whose instantiation " +
		"depends on a change in intrinsic properties, like qualities (datatypes) and modes. Phases also " +
		"come in partitions, i.e. there are always two or more phases grouped by a disjoint and complete" +
		" generalization set. \r\n\r\nThe phase partition defined by the generalization set "+occurrence.getPartition().getName()+", whose" +
		" common supertype is "+occurrence.getGeneral().getName()+", contains the phases: ";
		int i=0;
		for(Phase p: occurrence.getPhases()){
			if(i==occurrence.getPhases().size()-1) str += p.getName()+".";
			else str += p.getName()+", ";
			i++;
		}
		str += "The problem is that the supertype does not have intrinsic properties which can be used to define " +
		"the phases. \r\n\r\nPhase partitions can be defined using qualities (datatypes), which are used in" +
		" derivation rules for each phase. An example would be a kind Person, which has a partition containing" +
		" Child, Adult and Elder, phases defined using a person's age. Is that the case?\r\n";
		styledText.setText(str);
		
		btnYes = new Button(container, SWT.RADIO);
		btnYes.setText("Yes");
		
		btnNo = new Button(container, SWT.RADIO);
		btnNo.setText("No");
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(styledText, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.add(btnYes, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.add(btnNo, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE))
					.add(10))
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(styledText, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
					.add(10)
					.add(btnYes)
					.add(6)
					.add(btnNo)
					.addContainerGap(45, Short.MAX_VALUE))
		);
		container.setLayout(gl_container);
		
		setAsEnablingNextPageButton(btnYes);
		setAsEnablingNextPageButton(btnNo);
		
	}
	
	@Override
	public IWizardPage getNextPage() {
		if(btnYes.getSelection())
		{
			return getAntipatternWizard().getSecondPage();
		}
		if(btnNo.getSelection())
		{
			return getAntipatternWizard().getFourthPage();
		}
		
		return null;
	}
}
