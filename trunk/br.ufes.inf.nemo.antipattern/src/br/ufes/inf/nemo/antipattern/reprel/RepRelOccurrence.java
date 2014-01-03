package br.ufes.inf.nemo.antipattern.reprel;


import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Classifier;
import RefOntoUML.Mediation;
import RefOntoUML.Property;
import RefOntoUML.Relator;
import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class RepRelOccurrence extends AntipatternOccurrence {
	
	private Classifier relator;
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

	public Classifier getRelator(){
		return relator;
	}
	
	/**
	 * Constructor
	 *
	 * @param relator
	 * @param parser
	 * @throws Exception
	 */
	public RepRelOccurrence(Relator relator, OntoUMLParser parser) throws Exception 
	{
		super(parser);
		this.setRelator(relator,parser);
		duplicateInstantiationPattern = new DuplicateRelators(this);
		distinctInstantiationPatter = new DistinctRelators(this);
	}

	/**
	 * Select in the OntoUML model only those elements related with this antipattern...
	 */
	@Override
	public OntoUMLParser setSelected() {
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
	public void setRelator (Classifier relator, OntoUMLParser parser) throws Exception {
			
		this.relator = relator;
		
		mediations = new ArrayList<Mediation>();
		problematicMediations = new ArrayList<Mediation>();
		mediatedProperties = new ArrayList<Property>();
		problematicMediatedProperties = new ArrayList<Property>(); 
		
		for (Mediation med : parser.getAllInstances(Mediation.class)) {
			Property sourceEnd = med.getMemberEnd().get(0);
			Property targetEnd = med.getMemberEnd().get(1);
			
			if(sourceEnd.getType().equals(relator) || relator.allParents().contains(sourceEnd.getType()))
				mediatedProperties.add(sourceEnd);
			else if (targetEnd.getType().equals(relator) || relator.allParents().contains(targetEnd.getType()))
				mediatedProperties.add(targetEnd);
		
		}
		System.out.println("Size Mediation: "+mediatedProperties.size());
		
		for (Property end : mediatedProperties) {
			mediations.add((Mediation) end.getAssociation());
			if(end.getUpper()==-1 || end.getUpper()>1){
				problematicMediatedProperties.add(end);
				problematicMediations.add((Mediation) end.getAssociation());
			}
		}
		
		if (problematicMediations.size()<2)
			throw new Exception("==========\n\t\t\tRepRel: The required cardinalities are not met for "+parser.getStringRepresentation(relator));
	}
	
	/**
	 * To String method...
	 * 
	 */
	@Override
	public String toString() {
		
		String result;

		result= super.parser.getStringRepresentation(this.relator)+"\n"+
				"Mediations:";
		
		for(Property p: mediatedProperties)
			result+="\n\t"+super.parser.getStringRepresentation(p.getOpposite())+"  -  "+super.parser.getStringRepresentation(p);
	
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
	
	
		
}
