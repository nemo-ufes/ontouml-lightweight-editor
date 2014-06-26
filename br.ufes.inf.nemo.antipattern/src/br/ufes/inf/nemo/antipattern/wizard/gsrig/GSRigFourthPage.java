package br.ufes.inf.nemo.antipattern.wizard.gsrig;

import java.util.ArrayList;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import br.ufes.inf.nemo.antipattern.GSRig.GSRigOccurrence;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;

public class GSRigFourthPage extends GSRigPage {
	
	private Button btnAll;
	private Button btnCustom;
	private StyledText styledText;
	private SubTypeComposite rigSubTypeComposite;
	private ArrayList<String> antirigids = new ArrayList<String>();

	public GSRigFourthPage(GSRigOccurrence gsrig) 
	{
		super(gsrig);		
	}
	
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		
		styledText = new StyledText(container, SWT.WRAP);
		styledText.setText("Now we must verify the rigidity of all the subtypes in the generalization set. Remember that a type is:\r\n\r\nRigid: if for every x that instantiates it in a given moment, it must always do so in every possible situation;\r\nAnti-rigid: if for every x that instantiates it in a given moment, there is at least one possible situation in which x does not do so;\r\n\r\nPlease choose the appropriate stereotypes for the subtypes, remembering that Kind, Collective, Quantity, Subkind and Category are rigid types, whilst Role, Phase and RoleMixin are anti-rigid.");
		styledText.setBackground(styledText.getParent().getBackground());
		styledText.setJustify(true);
		
	    SelectionAdapter makeTableVisibleListener = new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent e) {
	    	  if(btnAll.getSelection()){
	    		  rigSubTypeComposite.enable(false);
	    	  }
	    	  if(btnCustom.getSelection()){
	    		  rigSubTypeComposite.enable(true);
	    	  }
	      }
	    };
		    
		btnAll = new Button(container, SWT.RADIO);
		btnAll.setText("All rigidity meta-properties are correct.");
		btnAll.addSelectionListener(makeTableVisibleListener);
		
		btnCustom = new Button(container, SWT.RADIO);
		btnCustom.setText("Fix rigidity meta-properties. (Clicking on this option enables the table)");
		btnCustom.addSelectionListener(makeTableVisibleListener);
		
		rigSubTypeComposite = new SubTypeComposite(container, SWT.NONE, (GSRigOccurrence) gsrig);
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(btnCustom, GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE)
					.addContainerGap())
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(btnAll, GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE)
					.addContainerGap())
				.add(rigSubTypeComposite, GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(styledText, GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(styledText, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
					.add(6)
					.add(btnAll)
					.add(6)
					.add(btnCustom)
					.add(13)
					.add(rigSubTypeComposite, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE))
		);
		container.setLayout(gl_container);
		rigSubTypeComposite.enable(false);
	}
	
	@Override
	public IWizardPage getNextPage() 
	{	
		if(btnAll.getSelection()){
		
			return ((GSRigWizard)getWizard()).getFifthPage();
		}
		
		if(btnCustom.getSelection()){
			
			antirigids.clear();
			
			//Action =============================
			GSRigAction newAction = new GSRigAction(gsrig);
			newAction.setChangeSpecificsStereotypesTo(rigSubTypeComposite.getSubtypeTable().getNewStereotypes()); 
			getGSRigWizard().replaceAction(0,newAction);
			//======================================
			
			antirigids = rigSubTypeComposite.getSubtypeTable().getAntiRigids();
			
			if(!rigSubTypeComposite.getSubtypeTable().isNewStereotypesAllAntiRigid() && !rigSubTypeComposite.getSubtypeTable().isNewStereotypesAllRigid()){
				GSRigFifthPage fifthPage = ((GSRigWizard)getWizard()).getFifthPage();
				fifthPage.setAntirigids(antirigids);
				return fifthPage;
			}
		}
				
		return ((GSRigWizard)getWizard()).getFinishing();
	}
}
