package br.ufes.inf.nemo.antipattern.wizard.relrig;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;

import RefOntoUML.Classifier;
import RefOntoUML.Property;
import br.ufes.inf.nemo.antipattern.relrig.RelRigAntipattern;
import br.ufes.inf.nemo.antipattern.relrig.RelRigOccurrence;

public abstract class RelRigPage extends WizardPage {

		protected RelRigOccurrence relRig;
		protected int rigid;
		protected Classifier rigidType;
		protected Property rigidEnd;
		
		/**
		 * Create the wizard.
		 */
		public RelRigPage(RelRigOccurrence relRig, int rigid) 
		{
			super("RelRigPage");				
			
			this.relRig = relRig;
			this.rigid = rigid;	
			if(relRig!=null){
				this.rigidEnd = relRig.getRigidMediatedProperties().get(rigid);
				this.rigidType = (Classifier) rigidEnd.getType();
			}
			setTitle(RelRigAntipattern.getAntipatternInfo().getName());		
		}
		
		public RelRigWizard getRelRigWizard(){
			return (RelRigWizard)getWizard();
		}
		
		@Override
		public void createControl(Composite arg0) {
			
		}
	}
