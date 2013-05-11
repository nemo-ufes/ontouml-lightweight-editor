package br.ufes.inf.nemo.antipattern.rwrt;


import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Classifier;
import RefOntoUML.Mediation;
import RefOntoUML.Property;
import RefOntoUML.Relator;
import RefOntoUML.RigidMixinClass;
import RefOntoUML.RigidSortalClass;
import RefOntoUML.Type;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.tri.DistinctRelators;
import br.ufes.inf.nemo.antipattern.tri.DuplicateRelators;
import br.ufes.inf.nemo.antipattern.util.SourceTargetAssociation;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class RWRTAntiPattern extends Antipattern {
	
	private Relator relator;
	private ArrayList<Mediation> allMediations, rigidMediations;
	private ArrayList<Property> allMediatedProperties, rigidMediatedProperties;
	
	
	public ArrayList<Mediation> getAllMediations() {
		return allMediations;
	}

	public ArrayList<Mediation> getRigidMediations() {
		return rigidMediations;
	}

	public ArrayList<Property> getAllMediatedProperties() {
		return allMediatedProperties;
	}

	public ArrayList<Property> getRigidMediatedProperties() {
		return rigidMediatedProperties;
	}

	public Relator getRelator(){
		return relator;
	}

	public RWRTAntiPattern(Relator relator, OntoUMLParser parser) throws Exception 
	{
		this.setRelator(relator, parser);
	}

	/**
	 * Select in the OntoUML model only those elements related with this antipattern...
	 */
	@Override
	public void setSelected(OntoUMLParser parser) {
		ArrayList<EObject> selection = new ArrayList<EObject>();
		
		selection.add(relator);
		selection.addAll(this.rigidMediations);
				
		parser.selectThisElements(selection,true);
		parser.autoSelectDependencies(OntoUMLParser.SORTAL_ANCESTORS, false);		
	}
	
	/**
	 * Set AntiPattern data...
	 * 
	 * @param mediation
	 * @throws Exception
	 */
	public void setRelator (Relator relator, OntoUMLParser parser) throws Exception {
		
		if(relator==null)
			throw new NullPointerException("Relator is null");
		
		this.relator = relator;  
		allMediatedProperties = new ArrayList<>();
		allMediations = new ArrayList<>();
		rigidMediatedProperties = new ArrayList<>();
		rigidMediations = new ArrayList<>();
		
		for (Mediation med : parser.getRelatorsMediations(relator)) {
			if(!parser.getRelator(med).equals(relator))
				throw new Exception("Mediation '"+med.getName()+"' does not belong to Relator '"+relator.getName()+"'");
			
			allMediations.add(med);
			allMediatedProperties.add(parser.getMediatedEnd(med));
			
			if (parser.getMediated(med) instanceof RigidSortalClass || parser.getMediated(med) instanceof RigidMixinClass){
				rigidMediations.add(med);
				rigidMediatedProperties.add(parser.getMediatedEnd(med));
			}	
		}
		
		if(rigidMediations.size()==0)
			throw new Exception("Relator '"+relator.getName()+"' does not mediate any rigid type");
			
	}
	
	/**
	 * To String method...
	 * 
	 */
	@Override
	public String toString() {
		
		String result;

		result= "Relator: "+relator.getName();
		for (Property p : rigidMediatedProperties) {
			result += "\n\t"+p.getAssociation().getName()+" - "+p.getType().getName();
		}
		
		return result;
	}	
}
