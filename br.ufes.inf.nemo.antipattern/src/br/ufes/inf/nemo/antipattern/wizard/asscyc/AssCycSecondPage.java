package br.ufes.inf.nemo.antipattern.wizard.asscyc;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;

import br.ufes.inf.nemo.antipattern.asscyc.AssCycOccurrence;

public class AssCycSecondPage  extends AssCycPage {
		
	private Button btnAlways;
	private Button btnNever;
	private Button btnPossibly;
	private StyledText introText;

	public AssCycSecondPage(AssCycOccurrence asscyc) 
	{
		super(asscyc);		
	}
	
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		setControl(container);			
		
		introText = new StyledText(container, SWT.WRAP | SWT.V_SCROLL | SWT.READ_ONLY);
		introText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		introText.setJustify(true);
		introText.setAlwaysShowScrollBars(false);
		
		String text = createCycleText();
		text+= "\nIf t1 is connected to t2, t2 is connected to t3, and so on, must t"+occurrence.getCycle().size()+" be connected to t1 ?";		
		introText.setText(text);
		
		btnAlways = new Button(container, SWT.RADIO);
		btnAlways.setText("Always, the cycle is mandatory");
		
		btnNever = new Button(container, SWT.RADIO);
		btnNever.setText("Never, the level cycle is forbidden");
		
		btnPossibly = new Button(container, SWT.RADIO);
		btnPossibly.setText("Possibly, but there is no restriction about it");
		
		setAsEnablingNextPageButton(btnAlways);
		setAsEnablingNextPageButton(btnNever);
		setAsEnablingNextPageButton(btnPossibly);
		
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(introText, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.add(btnAlways, GroupLayout.PREFERRED_SIZE, 554, GroupLayout.PREFERRED_SIZE)
						.add(btnNever, GroupLayout.PREFERRED_SIZE, 554, GroupLayout.PREFERRED_SIZE)
						.add(btnPossibly, GroupLayout.PREFERRED_SIZE, 554, GroupLayout.PREFERRED_SIZE))
					.add(10))
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(introText, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
					.add(6)
					.add(btnAlways)
					.add(6)
					.add(btnNever)
					.add(6)
					.add(btnPossibly))
		);
		container.setLayout(gl_container);
		
		setPageComplete(false);
	}

	private String createCycleText() {
		String text = "Consider the following instances\n\n";
		int i = 0;
		
		for(RefOntoUML.Class c: occurrence.getAssociationClassList()){
			if(i!=occurrence.getCycle().size()-1) 
				text += "- t"+(i+1)+": \""+c.getName()+"\"\n";
			else 
				text+= "- t"+(i+1)+": \""+c.getName()+"\"\n";
			i++;
		}
		return text;
	}
	
	@Override
	public IWizardPage getNextPage() 
	{	
		if(btnAlways.getSelection())
		{
			//Action =============================
			AssCycAction newAction = new AssCycAction(occurrence);
			newAction.setCycleMandatory();
			getAntipatternWizard().replaceAction(0,newAction);	
			//======================================
		}
		if(btnNever.getSelection())
		{
			//Action =============================
			AssCycAction newAction = new AssCycAction(occurrence);
			newAction.setCycleForbidden(); 
			getAntipatternWizard().replaceAction(0,newAction);	
			//======================================
		}
		if(btnPossibly.getSelection())
		{
			getAntipatternWizard().removeAllActions(0);
		}
		
		return getAntipatternWizard().getFinishing();
	}
}