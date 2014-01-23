package br.ufes.inf.nemo.antipattern.wizard.reprel;

import java.util.ArrayList;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;

import RefOntoUML.Mediation;
import br.ufes.inf.nemo.antipattern.reprel.RepRelOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.relspec.RelSpecAction;

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
		styledText.setText("Do you mean that each instance of the following Types are connected to various instances of "+repRel.getRelator().getName()+" at the same time or during its life cycle?\r\n");
		styledText.setEditable(false);
		styledText.setBounds(10, 10, 554, 38);
						
		setPageComplete(true);
		
		setControl(container);
		
		ScrolledComposite sc = new ScrolledComposite(container, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		sc.setBounds(10, 60, 554, 212);
		
		Composite composite = new Composite(sc, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.VERTICAL));
						
		for(Mediation m: repRel.getMediations()){			
			typesList.add(new TimeOptionComposite(composite,SWT.NONE,repRel,m));			
		}
		
		sc.setContent(composite);
		sc.setExpandHorizontal(true);
		sc.setExpandVertical(true);
		sc.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));		
	}	
	
}