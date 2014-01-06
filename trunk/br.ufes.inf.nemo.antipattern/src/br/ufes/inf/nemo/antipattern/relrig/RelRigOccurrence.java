package br.ufes.inf.nemo.antipattern.relrig;


import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Mediation;
import RefOntoUML.Property;
import RefOntoUML.Relator;
import RefOntoUML.RigidMixinClass;
import RefOntoUML.RigidSortalClass;
import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class RelRigOccurrence extends AntipatternOccurrence {
	
	private Relator relator;
	
	private ArrayList<Mediation> allMediations, rigidMediations;
	private ArrayList<Property> allMediatedProperties, rigidMediatedProperties;
	public ArrayList<Mediation> getAllMediations() {
		return allMediations;
	}
	
	public OntoUMLParser getOntoUMLParser(){ return parser; }

	public ArrayList<Mediation> getRigidMediations() {
		return rigidMediations;
	}

	public ArrayList<Property> getAllMediatedProperties() {
		return allMediatedProperties;
	}

	public ArrayList<Property> getRigidMediatedProperties() {
		return rigidMediatedProperties;
	}

	public CreationalRelator creationalInstantiationPattern;
	public ChangingRelator changingInstantiationPattern;
	
	
	public RelRigOccurrence(Relator relator, RelRigAntipattern ap) throws Exception 
	{
		super(ap);
		
		this.setRelator(relator, parser);
		
		this.changingInstantiationPattern = new ChangingRelator(this);
		this.creationalInstantiationPattern = new CreationalRelator(this);
				
	}
	
	private void setRelator (Relator relator, OntoUMLParser parser) throws Exception {
		
		if(relator==null)
			throw new NullPointerException("RelRigid: Relator is null");
		
		this.relator = relator;  
		allMediatedProperties = new ArrayList<>();
		allMediations = new ArrayList<>();
		rigidMediatedProperties = new ArrayList<>();
		rigidMediations = new ArrayList<>();
		
		for (Mediation med : parser.getRelatorsMediations(relator)) {
			if(!parser.getRelator(med).equals(relator))
				throw new Exception("RelRigid: Mediation '"+med.getName()+"' does not belong to Relator '"+relator.getName()+"'");
			
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
	
	public Relator getRelator() {
		return relator;
	}

	/**
	 * Select in the OntoUML model only those elements related with this antipattern...
	 */
	@Override
	public OntoUMLParser setSelected() {
		ArrayList<EObject> selection = new ArrayList<EObject>();
		
		selection.add(relator);
		selection.addAll(this.rigidMediations);
				
		parser.selectThisElements(selection,true);
		parser.autoSelectDependencies(OntoUMLParser.SORTAL_ANCESTORS, false);
		return parser;
	}
	
	
	/**
	 * To String method...
	 * 
	 */
	@Override
	public String toString() {
		
		String result;

		result = super.parser.getStringRepresentation(this.relator)+
				"\nRigid Mediated Types:";
		
		for (Property p : this.rigidMediatedProperties) {
			result += "\n\t"+super.parser.getStringRepresentation(p);
		}
		
		result += "\nOther Mediated Types:";
		
		for (Property p : this.allMediatedProperties){
			if (!this.rigidMediatedProperties.contains(p))
				result += "\n\t"+super.parser.getStringRepresentation(p);
		}
		
		return result;
	}
	
	public String explanation() {
	
		String expl = "";
		
		expl += "The relator type "+this.relator.getName()+" mediates "+this.rigidMediatedProperties.size()+" rigid type(s):";
		
		for (int i = 0; i < rigidMediatedProperties.size(); i++) {
			Property p = rigidMediatedProperties.get(i);
			
			if(i>0 && i!= rigidMediatedProperties.size()-1)
				expl += ",";
			
			else if (i == rigidMediatedProperties.size()-1)
				expl += " and";
			
			expl += " "+p.getType().getName();
			
		}
		
		return expl;
	}

	@Override
	public String getShortName() {
		return parser.getStringRepresentation(relator);
	}
}


