package br.ufes.inf.nemo.antipattern;


import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Classifier;
import RefOntoUML.Mediation;
import RefOntoUML.Relator;
import RefOntoUML.Type;
import br.ufes.inf.nemo.antipattern.util.SourceTargetAssociation;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class RWRTAntiPattern extends Antipattern {
	
	private Relator relator;
	private Classifier rigidtype;
	private Mediation mediation;
	
	public Relator getRelator(){
		return relator;
	}
	
	public Classifier getRigidType(){
		return rigidtype;
	}
	
	public Mediation getMediation(){
		return mediation;
	}
	
	/**
	 * Constructor
	 *
	 * @param relator
	 * @param parser
	 * @throws Exception
	 */
	public RWRTAntiPattern(Mediation mediation) throws Exception 
	{
		this.setMediation(mediation);
	}

	/**
	 * Select in the OntoUML model only those elements related with this antipattern...
	 */
	@Override
	public void setSelected(OntoUMLParser parser) {
		ArrayList<EObject> selection = new ArrayList<EObject>();
		
		selection.add(relator);
		selection.add(rigidtype);
		selection.add(mediation);
		
		parser.selectThisElements(selection,true);
		parser.autoSelectDependencies(OntoUMLParser.SORTAL_ANCESTORS, false);		
	}
	
	/**
	 * Set AntiPattern data...
	 * 
	 * @param mediation
	 * @throws Exception
	 */
	public void setMediation (Mediation mediation) throws Exception {
		
		if(mediation==null)
			throw new NullPointerException("Association is null");
		if(mediation.getMemberEnd().size()!=2)
			throw new Exception("The number of memberEnds should be 2");
		if(!(mediation.getMemberEnd().get(0).getType() instanceof Type))
			throw new Exception("Source type undefined or null");
		if(!(mediation.getMemberEnd().get(1).getType() instanceof Type))
			throw new Exception("Target type undefined or null");
		
		this.mediation = mediation;
		this.relator = (Relator) mediation.relator();		
		this.rigidtype= (Classifier) SourceTargetAssociation.getTargetAlloy(mediation);	
	}
	
	/**
	 * To String method...
	 * 
	 */
	@Override
	public String toString() {
		
		String result;

		result= "Relator: "+relator.getName()+" - "+"Mediation: "+mediation.getName()+" - "+"Rigidtype: "+this.rigidtype.getName();
		
		return result;
	}	
}
