package br.ufes.inf.nemo.antipattern.wizard.reprel;

import java.util.ArrayList;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.wb.swt.SWTResourceManager;

import RefOntoUML.Mediation;
import br.ufes.inf.nemo.antipattern.reprel.RepRelOccurrence;

public class RepRelFirstPage extends RepRelPage{
	
	ArrayList<TimeOptionComposite> typesList = new ArrayList<TimeOptionComposite>();
	public Composite parent;
	
	/**
	 * Create the wizard.
	 */
	public RepRelFirstPage(RepRelOccurrence repRel) 
	{
		super(repRel);
		setDescription("Page 1");
	}

	@Override
	public void createControl(Composite parent) 
	{
		this.parent = parent;
		Composite container = new Composite(parent, SWT.NULL);

		StyledText styledText = new StyledText(container, SWT.WRAP | SWT.V_SCROLL);
		styledText.setMarginColor(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledText.setText("Each instance of the mediated types can be connected to various instances of "+repRel.getRelator().getName()+". " +
				"Do you mean they are connected to various relators at the same time or during its life cycle?\r\n");
		styledText.setEditable(false);
		styledText.setBounds(10, 10, 554, 38);
				
		setPageComplete(true);
		
		setControl(container);
		
		ScrolledComposite sc = new ScrolledComposite(container, SWT.V_SCROLL | SWT.H_SCROLL | SWT.BORDER);
		sc.setBounds(10, 60, 554, 212);
		
		Composite composite = new Composite(sc, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.VERTICAL));
						
		for(Mediation m: repRel.getMediations())
		{			
			TimeOptionComposite optComposite = new TimeOptionComposite(composite,SWT.NONE,repRel,m);
			optComposite.selectSameTime();
			typesList.add(optComposite);
			optComposite.setColor(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		}
		
		sc.setContent(composite);
		sc.setExpandHorizontal(true);
		sc.setExpandVertical(true);
		sc.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));		
	}	
	
	@Override
	public IWizardPage getNextPage() 
	{		
		ArrayList<Integer> upperList = new ArrayList<Integer>();
		((RepRelWizard)getWizard()).removeAllActions();
		
		int i = 0;
		for(TimeOptionComposite timeOpt: typesList)
		{
			if (timeOpt.isLifeTime()) 
			{
				// Action =====================			
				RepRelAction newAction = new RepRelAction(repRel);
				newAction.setChangeUpperMult(repRel.getMediations().get(i),timeOpt.getN());
				getRepRelWizard().replaceAction(i,newAction);				
				//=============================
				upperList.add(timeOpt.getN());
			}
			if (timeOpt.isSame())
			{
				// Action ===================
				
				//============================
				Mediation m = repRel.getMediations().get(i);
				upperList.add(m.getMemberEnd().get(0).getUpper());
			}			
			i++;
		}
		int greater = 0;
		for(int j=0; j<upperList.size();j++){
			if (upperList.get(j)==-1) greater++;
			else if (upperList.get(j)>1) greater++;
		}

		if(upperList.size()>0){
			if(greater>1) {
				// still an antipattern..
				return ((RepRelWizard)getWizard()).getSecondPage();		
			}else {
				//MessageDialog.openInformation(parent.getShell(), "Information", "The occurrence of the anti-pattern did not characterize an error anymore");
				return ((RepRelWizard)getWizard()).getFinishing();				
			}
		}
		return super.getNextPage();
	}
}