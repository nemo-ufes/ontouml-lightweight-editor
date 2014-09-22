package br.ufes.inf.nemo.antipattern.wizard.relrig;

import RefOntoUML.Classifier;
import RefOntoUML.Property;
import RefOntoUML.parser.OntoUMLNameHelper;
import br.ufes.inf.nemo.antipattern.relrig.RelRigAntipattern;
import br.ufes.inf.nemo.antipattern.relrig.RelRigOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntipatternWizardPage;

public abstract class RelRigPage extends AntipatternWizardPage<RelRigOccurrence, RelRigWizard> {

		protected int rigid;
		protected Classifier rigidType;
		protected Property rigidEnd;
		
		/**
		 * Create the wizard.
		 */
		public RelRigPage(RelRigOccurrence relRig, int rigid) 
		{
			super(relRig);				
			
			this.occurrence = relRig;
			this.rigid = rigid;	
			
			if(relRig!=null){
				this.rigidEnd = relRig.getRigidMediatedProperties().get(rigid);
				this.rigidType = (Classifier) rigidEnd.getType();
			}
			
			int n = rigid+1;
			setTitle(RelRigAntipattern.getAntipatternInfo().getName()+" ("+n+" of "+occurrence.getRigidMediatedProperties().size()+")");	
			setDescription("Relator: "+OntoUMLNameHelper.getName(relRig.getRelator()));	
		}
	
	}
