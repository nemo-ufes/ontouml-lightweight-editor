package br.ufes.inf.nemo.antipattern.wizard.undefphase;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;

import RefOntoUML.Phase;
import br.ufes.inf.nemo.antipattern.undefphase.UndefPhaseOccurrence;
import br.ufes.inf.nemo.common.ontoumlparser.ParsingElement;

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
		
		setControl(container);
		
		styledText = new StyledText(container, SWT.BORDER | SWT.WRAP);
		styledText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledText.setJustify(true);
		String str = "The Phase stereotype is used to characterize anti-rigid types whose instantiation " +
		"depends on a change in intrinsic properties, like qualities (datatypes) and modes. Phases also " +
		"come in partitions, i.e. there are always two or more phases grouped by a disjoint and complete" +
		" generalization set. \r\n\r\nThe phase partition defined by the generalization set "+up.getPartition().getName()+", whose" +
		" common supertype is "+up.getGeneral().getName()+", contains the phases: ";
		int i=0;
		for(Phase p: up.getPhases()){
			if(i==up.getPhases().size()-1) str += p.getName()+".";
			else str += p.getName()+",";
			i++;
		}
		str += "The problem is that the supertype does not have intrinsic properties which can be used to define " +
		"the phases. \r\n\r\nPhase partitions can be defined using qualities (datatypes), which are used in" +
		" derivation rules for each phase. An example would be a kind Person, which has a partition containing" +
		" Child, Adult and Elder, phases defined using a person's age. Is that the case?\r\n";
		styledText.setBounds(10, 10, 554, 178);
		styledText.setText(str);
		
		btnYes = new Button(container, SWT.RADIO);
		btnYes.setBounds(10, 198, 554, 16);
		btnYes.setText("Yes");
		
		btnNo = new Button(container, SWT.RADIO);
		btnNo.setBounds(10, 220, 554, 16);
		btnNo.setText("No");
	}
	
	@Override
	public IWizardPage getNextPage() {
		if(btnYes.getSelection())
		{
			return getUndefPhaseWizard().getSecondPage();
		}
		if(btnNo.getSelection())
		{
			return getUndefPhaseWizard().getFourthPage();
		}
		
		return getUndefPhaseWizard().getFinishing();
	}
}
