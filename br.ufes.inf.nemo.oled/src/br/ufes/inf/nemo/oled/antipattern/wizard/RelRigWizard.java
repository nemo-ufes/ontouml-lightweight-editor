package br.ufes.inf.nemo.oled.antipattern.wizard;

import org.eclipse.jface.wizard.Wizard;

import br.ufes.inf.nemo.antipattern.relrig.RelRigAntipattern;
import br.ufes.inf.nemo.antipattern.relrig.RelRigOccurrence;

public class RelRigWizard extends Wizard {

	protected PresentationPage presentation;
	protected RelRigOccurrence ap;
	
	public RelRigWizard(RelRigOccurrence ap) {
		this.ap = ap;
		setWindowTitle(RelRigAntipattern.getAntipatternInfo().name);
	}

	public String[]  divide(String text){		
		String[] array = text.split("\n");
		return array;
	}
	
	@Override
	public void addPages() {		
		presentation = new PresentationPage(
			RelRigAntipattern.getAntipatternInfo().name,
			RelRigAntipattern.getAntipatternInfo().acronym,
			divide(ap.toString())
		);		
		addPage(presentation);
	}

	@Override
	public boolean performFinish() {
		return false;
	}

}
