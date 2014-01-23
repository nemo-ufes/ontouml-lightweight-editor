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
import RefOntoUML.Type;
import br.ufes.inf.nemo.antipattern.reprel.RepRelOccurrence;

public class RepRelFirstPage extends RepRelPage{
	
	ArrayList<TimeOptionComposite> typesList = new ArrayList<TimeOptionComposite>();
	
	/**
	 * Create the wizard.
	 */
	public RepRelFirstPage(RepRelOccurrence repRel) 
	{
		super(repRel);		
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		StyledText styledText = new StyledText(container, SWT.WRAP);
		styledText.setMarginColor(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledText.setText("Each instance of the mediated types are connected to various instances of "+repRel.getRelator().getName()+". But do you mean at the same time or during its life cycle?\r\n");
		styledText.setEditable(false);
		styledText.setBounds(10, 10, 554, 38);
				
		setPageComplete(true);
		
		setControl(container);
		
		ScrolledComposite sc = new ScrolledComposite(container, SWT.V_SCROLL | SWT.BORDER);
		sc.setBounds(10, 60, 554, 212);
		
		Composite composite = new Composite(sc, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.VERTICAL));
						
		//int i=1;
		for(Mediation m: repRel.getMediations()){			
			TimeOptionComposite optComposite = new TimeOptionComposite(composite,SWT.NONE,repRel,m);
			typesList.add(optComposite);
			//if(i % 2 == 0) optComposite.setColor(new Color(Display.getCurrent(),235,235,235));
			optComposite.setColor(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
			//i++;
		}
		
		sc.setContent(composite);
		sc.setExpandHorizontal(true);
		sc.setExpandVertical(true);
		sc.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));		
	}	
	
	
	@Override
	public IWizardPage getNextPage() 
	{
		boolean needToGoToSecondPage = false;
		ArrayList<Type> rigids = new ArrayList<Type>();
		for(TimeOptionComposite timeOpt: typesList)
		{
			if (timeOpt.isLifeTime()) 
			{
				// Action =====================			
				RepRelAction newAction = new RepRelAction(repRel);
				newAction.setChangeUpperMult(timeOpt.getN());
				//getRepRelWizard().addAction(newAction);				
				//=============================
			}
			if (timeOpt.isSame() && timeOpt.isYes())
			{
				//do nothing
			}
			if(timeOpt.isSame() && timeOpt.isNo()){
				needToGoToSecondPage = true;				
				((RepRelWizard)getWizard()).getSecondPage().setRigids(rigids);
			}
		}
		if(needToGoToSecondPage){
			return ((RepRelWizard)getWizard()).getSecondPage();
		}else{
			return ((RepRelWizard)getWizard()).getFinishing();
		}		
	}
}