package br.ufes.inf.nemo.antipattern;


import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Mediation;
import RefOntoUML.Property;
import RefOntoUML.Relator;
import RefOntoUML.Type;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class TRIAntiPattern extends Antipattern {
	
	private Relator relator;
	private ArrayList<Mediation> mediations;
	private ArrayList<Property> mediatedProperties;
	
	public Relator getRelator(){
		return relator;
	}
	
	public ArrayList<Property> getOtherMediatedProperties() {
		return mediatedProperties;
	}

	/**
	 * Constructor
	 *
	 * @param relator
	 * @param parser
	 * @throws Exception
	 */
	public TRIAntiPattern(Relator relator, OntoUMLParser refparser) throws Exception 
	{
		this.setRelator(relator,refparser);
	}

	/**
	 * Select in the OntoUML model only those elements related with this antipattern...
	 */
	@Override
	public void setSelected(OntoUMLParser parser) {
		ArrayList<EObject> selection = new ArrayList<EObject>();
		
		selection.add(relator);
		for(Mediation m: mediations){
			selection.add(m);
		}
		
		parser.selectThisElements(selection,true);
		parser.autoSelectDependencies(OntoUMLParser.SORTAL_ANCESTORS, false);		
	}
	
	/**
	 * Set AntiPattern data...
	 * 
	 * @param mediation
	 * @throws Exception
	 */
	public void setRelator (Relator relator, OntoUMLParser refparser) throws Exception {
			
		this.relator = (Relator) relator;
		
		mediations = new ArrayList<Mediation>();		
		refparser.getAllMediations(this.relator, mediations);
		
		for(Mediation mediation: mediations){
			if(mediation==null)
				throw new NullPointerException("Association is null");
			if(mediation.getMemberEnd().size()!=2)
				throw new Exception("The number of memberEnds should be 2");
			if(!(mediation.getMemberEnd().get(0).getType() instanceof Type))
				throw new Exception("Source type undefined or null");
			if(!(mediation.getMemberEnd().get(1).getType() instanceof Type))
				throw new Exception("Target type undefined or null");
		}
		
		mediatedProperties = new ArrayList<Property>();
		for(Mediation m: mediations) mediatedProperties.add(m.mediatedEnd());
	}
	
	/**
	 * To String method...
	 * 
	 */
	@Override
	public String toString() {
		
		String result;

		result= "Relator: "+relator.getName()+"\n";
		for(Property p: mediatedProperties){
			result+="Mediations Ends: "+p.getName()+"\n";
		}		
		
		return result;
	}	
}
