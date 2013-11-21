package br.ufes.inf.nemo.antipattern.reprel;


import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Mediation;
import RefOntoUML.Property;
import RefOntoUML.Relator;
import RefOntoUML.Type;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class RepRelAntipattern extends Antipattern {
	
	private Relator relator;
	private ArrayList<Mediation> mediations, problematicMediations;
	private ArrayList<Property> mediatedProperties, problematicMediatedProperties;
	public DuplicateRelators duplicateInstantiationPattern;
	public DistinctRelators distinctInstantiationPatter;
	
	public ArrayList<Mediation> getMediations() {
		return mediations;
	}

	public ArrayList<Mediation> getProblematicMediations() {
		return problematicMediations;
	}

	public ArrayList<Property> getMediatedProperties() {
		return mediatedProperties;
	}

	public ArrayList<Property> getProblematicMediatedProperties() {
		return problematicMediatedProperties;
	}

	public Relator getRelator(){
		return relator;
	}
	
	/**
	 * Constructor
	 *
	 * @param relator
	 * @param parser
	 * @throws Exception
	 */
	public RepRelAntipattern(Relator relator, OntoUMLParser refparser) throws Exception 
	{
		this.setRelator(relator,refparser);
		duplicateInstantiationPattern = new DuplicateRelators(this);
		distinctInstantiationPatter = new DistinctRelators(this);
	}

	/**
	 * Select in the OntoUML model only those elements related with this antipattern...
	 */
	@Override
	public OntoUMLParser setSelected(OntoUMLParser parser) {
		ArrayList<EObject> selection = new ArrayList<EObject>();
		
		selection.add(relator);
		for(Mediation m: mediations){
			selection.add(m);
		}
		
		parser.selectThisElements(selection,true);
		parser.autoSelectDependencies(OntoUMLParser.SORTAL_ANCESTORS, false);
		return parser;
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
		problematicMediations = new ArrayList<Mediation>();
		
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
		problematicMediatedProperties = new ArrayList<Property>();
		
		for(Mediation m: mediations) {
			mediatedProperties.add(refparser.getMediatedEnd(m));
			
			if(refparser.getRelatorEnd(m).getUpper()==-1 || refparser.getRelatorEnd(m).getUpper()>1){
				problematicMediatedProperties.add(refparser.getMediatedEnd(m));
				problematicMediations.add(m);
			}
		}
		
		if (problematicMediations.size()<2)
			throw new Exception("The relator does not characterize the TRI antipattern!");
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
	
	public String explanation(OntoUMLParser parser) throws Exception{
		String expl = 	"The relator \'"+relator.getName()+"\' mediates "+mediations.size()+" types: ";
						
		for (int i = 0; i < mediatedProperties.size(); i++) {
			if (i==mediatedProperties.size()-1)
				expl += " and ";
			else if (i!=0)
				expl += ", ";
			
			expl+="'"+mediatedProperties.get(i).getType().getName()+"'";
		}
		
		expl += ".\n";
		
		if(mediations.size()==problematicMediations.size())
			expl+="All";
		else
			expl += "Exactly "+problematicMediations.size();
		
		expl += " of them are connected to '"+relator.getName()+"\' through mediations which have an upper bound multiplicity in the '"+relator.getName()+"\' side greater than one. They are:";
		
		for (Mediation m : problematicMediations) {
			expl+="\n"+(parser.getMediated(m).getName())+" ";
			
			Property relatorEnd = parser.getRelatorEnd(m);
			Property mediatedEnd = parser.getMediatedEnd(m);
			
			if (mediatedEnd.getLower()==-1)
				expl+="0";
			else expl+= mediatedEnd.getLower();
			
			expl+="..";
			
			if (mediatedEnd.getUpper()==-1)
				expl+="*";
			else expl+= mediatedEnd.getUpper();
			
			expl+=" "+m.getName()+" ";
			
			if (relatorEnd.getLower()==-1)
				expl+="0";
			else expl+= relatorEnd.getLower();
			
			expl+="..";
			
			if (relatorEnd.getUpper()==-1)
				expl+="*";
			else expl+= relatorEnd.getUpper();
		
			expl += " "+relator.getName();
		}
			
		return expl;
	}
	
	public static String generalDescription (){
		return 	"The Twin Relator Instances (TRI) antipattern is defined by a relator type "+
				"which is connected to two or more mediations whose upper bound cardinality in the relator end is greater than one.";
	}
	
	
	
}
