package br.ufes.inf.nemo.antipattern.wizard.mixrig;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import br.ufes.inf.nemo.antipattern.mixrig.MixRigAntipattern;
import br.ufes.inf.nemo.antipattern.mixrig.MixRigOccurrence;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;

public class MixRigSecondPage extends MixRigPage{

	//GUI
	private Button btnYes;
	private Button btnNo;
	private ChangeSubtypeRigidityComposite tableComposite;
	
	private SelectionAdapter listener = new SelectionAdapter() {
		
		public void widgetSelected(SelectionEvent event) {
						
			if (btnYes.getSelection()){
				tableComposite.setEnabledToAllContents(false);
				setPageComplete(true);
			}
			if(btnNo.getSelection()){
				tableComposite.setEnabledToAllContents(true);
				if(tableComposite.getModifiedSubtypes().size()>0)
					setPageComplete(true);
				else
					setPageComplete(false);
			}
		}
	};
	
	private SelectionListener comboListener = new SelectionAdapter(){
	
		@Override
		public void widgetSelected(SelectionEvent event) {
        	System.out.println("Called composite listener");
        	if(btnNo.getSelection()){
        		System.out.println("No is Selected!");
        		System.out.println("Modif. Subt: "+tableComposite.getModifiedSubtypes().size());
        		if(tableComposite.getModifiedSubtypes().size()>0)
        			setPageComplete(true);
    			else
    				setPageComplete(false);			
        	}
				
		}
	};
	
	public MixRigSecondPage(MixRigOccurrence mixRig) {
		super(mixRig);	
	}
		
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		
		
		setTitle(MixRigAntipattern.getAntipatternInfo().getName());
		setDescription("Mixin: "+mixRig.getMixin().getName()+"\r\nSubtypes: "+getSubtypeList(3));
		
		Composite container = new Composite(parent, SWT.NULL);
		setControl(container);
		
		setPageComplete(false);
		
		StyledText styledText = new StyledText(container, SWT.READ_ONLY | SWT.WRAP);
		styledText.setText(	"Is the rigidity meta-property of all subtypes of "+mixRig.getMixin().getName()+" correct? If not, use the table below to change the stereotype:");
		styledText.setJustify(true);
		styledText.setBackground(styledText.getParent().getBackground());
		
		btnYes = new Button(container, SWT.RADIO);
		btnYes.setText("Yes");
		btnYes.addSelectionListener(listener);
		
		btnNo = new Button(container, SWT.RADIO);
		btnNo.setText("No");
		btnNo.addSelectionListener(listener);
		
		tableComposite = new ChangeSubtypeRigidityComposite(container, SWT.NONE, mixRig, getMixRigWizard().allowedStereotypes(), comboListener);
		tableComposite.setEnabledToAllContents(false);
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(gl_container.createSequentialGroup()
							.add(styledText, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
							.add(15))
						.add(gl_container.createSequentialGroup()
							.add(btnYes, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
							.add(15))
						.add(gl_container.createSequentialGroup()
							.add(btnNo, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
							.add(15))
						.add(tableComposite, GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE))
					.add(10))
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(styledText, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.add(6)
					.add(btnYes)
					.add(6)
					.add(btnNo)
					.add(14)
					.add(tableComposite, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
					.add(10))
		);
		container.setLayout(gl_container);
	}
	
	@Override
	public IWizardPage getNextPage() 
	{	
		getMixRigWizard().removeAllActions();
		
		if(btnYes.getSelection())
			return getMixRigWizard().getThirdPage();
		if(btnNo.getSelection()) {
			MixRigAction action = new MixRigAction(mixRig);
			action.setChangeSubtypesStereotype(tableComposite.getModifiedSubtypes());
			getMixRigWizard().addAction(0, action);
			return getMixRigWizard().getFinishing();
		}
		
		return super.getNextPage();
	}
}
