package br.ufes.inf.nemo.antipattern.wizard.undefphase;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import br.ufes.inf.nemo.antipattern.undefphase.UndefPhaseOccurrence;

public class UndefPhaseFirstPage extends UndefPhasePage{
		
	Composite parent;
	
	/**
	 * Create the wizard.
	 */
	public UndefPhaseFirstPage(UndefPhaseOccurrence up) 
	{
		super(up);
//		setDescription("Source: "+uf.getSource().getName()+", Target: "+uf.getTarget().getName());
	}
	
	@Override
	public void createControl(Composite parent) 
	{
		this.parent = parent;
		Composite container = new Composite(parent, SWT.NULL);
		
		setControl(container);
	}
}
