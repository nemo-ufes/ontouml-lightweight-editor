package br.ufes.inf.nemo.oled.antipattern.wizard.relrig;

import org.eclipse.jface.wizard.WizardPage;

import br.ufes.inf.nemo.antipattern.relrig.RelRigAntipattern;
import br.ufes.inf.nemo.antipattern.relrig.RelRigOccurrence;

public abstract class RelRigPage extends WizardPage {

		protected RelRigOccurrence relRig;
		
		/**
		 * Create the wizard.
		 */
		public RelRigPage(RelRigOccurrence relRig) 
		{
			super("RelRigPage");				
			this.relRig = relRig;
					
			setTitle(RelRigAntipattern.getAntipatternInfo().getName());		
		}
		
		public RelRigWizard getRelRigWizard(){
			return (RelRigWizard)getWizard();
		}
	}
