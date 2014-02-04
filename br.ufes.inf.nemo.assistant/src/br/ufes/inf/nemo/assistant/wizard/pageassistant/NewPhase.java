package br.ufes.inf.nemo.assistant.wizard.pageassistant;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class NewPhase extends WizardPageAssistant {
	private TableViewer table;

	/**
	 * Create the wizard.
	 */
	public NewPhase() {
		super("Create new phase");
		setTitle("Create new OntoUML Phases");
		setDescription("NEWPHASE.description");
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		
		table = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
			      | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
	}

	@Override
	public boolean next() {
		return true;
	}
	
	@Override
	public boolean canFlipToNextPage() {
		if(isEndPage)
			return false;
		
		return false;
	}
}
