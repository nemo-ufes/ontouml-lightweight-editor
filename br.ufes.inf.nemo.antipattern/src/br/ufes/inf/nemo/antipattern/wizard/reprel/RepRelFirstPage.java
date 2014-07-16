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
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;

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
	}

	@Override
	public void createControl(Composite parent) 
	{
		Composite container = new Composite(parent, SWT.NULL);
		setControl(container);
		
		this.parent = parent;
		

		StyledText styledText = new StyledText(container, SWT.READ_ONLY | SWT.WRAP);
		styledText.setAlwaysShowScrollBars(false);
		styledText.setJustify(false);
		styledText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledText.setText(	"The overall goal of this anti-pattern is to evaluate wheter or not multiple instances of the "+relator+" can mediate the very same individuals. " +
							"This particular relator was selected because it is connected to <"+occurrence.getProblematicMediations().size()+"> mediations whose upper cardinality on the relator's end is equal or greater than 2." +
							"\r\n\r\n"+
							"To start the analysis, we need to recover the reasoning employed when choosing the cardinalities for the relator's mediations. "+
							"Did you mean that an instance of a given mediated type is connected to various instances of the relator at the same time or various instances during its life cycle?");
				
		ScrolledComposite sc = new ScrolledComposite(container, SWT.V_SCROLL | SWT.H_SCROLL | SWT.BORDER);
		
		Composite composite = new Composite(sc, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.VERTICAL));

		for(Mediation m: occurrence.getProblematicMediations())
		{			
			TimeOptionComposite optComposite = new TimeOptionComposite(composite,SWT.NONE,occurrence,m);
			optComposite.selectSameTime();
			typesList.add(optComposite);
			optComposite.setColor(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		}
		
		sc.setContent(composite);
		sc.setExpandHorizontal(true);
		sc.setExpandVertical(true);
		sc.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));		
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.TRAILING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(gl_container.createParallelGroup(GroupLayout.TRAILING)
						.add(GroupLayout.LEADING, sc, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.add(GroupLayout.LEADING, styledText, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE))
					.add(10))
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(styledText, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(sc, GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE))
		);
		container.setLayout(gl_container);
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
				RepRelAction newAction = new RepRelAction(occurrence);
				newAction.setChangeUpperMult(occurrence.getProblematicMediations().get(i),timeOpt.getN());
				getAntipatternWizard().replaceAction(i,newAction);				
				//=============================
				upperList.add(timeOpt.getN());
			}
			if (timeOpt.isSame())
			{
				// Action ===================
				
				//============================
				Mediation m = occurrence.getProblematicMediations().get(i);
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
				return ((RepRelWizard)getWizard()).getFinishing();				
			}
		}
		return super.getNextPage();
	}
}