package br.ufes.inf.nemo.antipattern.wizard.undefformal;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import br.ufes.inf.nemo.antipattern.undefformal.UndefFormalOccurrence;

public class UndefFormalFourthPage extends UndefFormalPage{

	public Composite parent;
	
	/**
	 * Create the wizard.
	 */
	public UndefFormalFourthPage(UndefFormalOccurrence uf) 
	{
		super(uf);
		setDescription("Source: "+uf.getSource().getName()+", Target: "+uf.getTarget().getName());
	}

	@Override
	public void createControl(Composite parent) 
	{
		this.parent = parent;
		Composite container = new Composite(parent, SWT.NULL);
		
		setControl(container);
	}
}