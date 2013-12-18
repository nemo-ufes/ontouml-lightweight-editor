package br.ufes.inf.nemo.antipattern.experimental;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Class;
import RefOntoUML.Classifier;
import RefOntoUML.Collective;
import RefOntoUML.Kind;
import RefOntoUML.Quantity;
import RefOntoUML.SortalClass;
import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class MultSortOccurrence extends AntipatternOccurrence {

	Class subtype, identityProvider;
	ArrayList<Class> sortalParents;
	
	
	public MultSortOccurrence(Class subtype, OntoUMLParser parser) throws Exception {
		super(parser);
		
		if(subtype == null)
			throw new Exception(MultSortAntipattern.getAntipatternInfo().acronym+": provided input class is null. Can't create occurrence!");
		else if(!(subtype instanceof SortalClass))
			throw new Exception(MultSortAntipattern.getAntipatternInfo().acronym+": "+parser.getStringRepresentation(subtype)+" is not a Sortal Class. Can't create occurrence!");
		
		this.subtype = subtype;
		this.identityProvider = null;
		this.sortalParents = new ArrayList<Class>();
		
		for (Classifier supertype : subtype.allParents()) {
		
			if (supertype instanceof Kind || supertype instanceof Quantity || supertype instanceof Collective){
				if(identityProvider!=null && !supertype.equals(identityProvider)) 
					throw new Exception(MultSortAntipattern.getAntipatternInfo().acronym+": there is more than one identity provider for "+
									parser.getStringRepresentation(subtype)+". Can't create occurrence!");
				else
					identityProvider = (Class) supertype;
			}
		}
		
		for (Classifier supertype : subtype.parents()) {
			if (supertype instanceof SortalClass)
				sortalParents.add((Class) supertype);
		}
		
		if (sortalParents.size()<2)
			throw new Exception(MultSortAntipattern.getAntipatternInfo().acronym+": "+parser.getStringRepresentation(subtype)+" has less than 2 sortal parents. Can't create occurrence!");
	}

	@Override
	public OntoUMLParser setSelected() {
		ArrayList<EObject> selection = new ArrayList<EObject>();
		
		selection.add(subtype);
		selection.addAll(subtype.allParents());
								
		parser.selectThisElements(selection,true);
		parser.autoSelectDependencies(OntoUMLParser.SORTAL_ANCESTORS, false);

		return parser;
	}
	
	@Override
	public String toString(){
		
		String result = "Subtype: "+ parser.getStringRepresentation(subtype)+"\n"+
						"Identity Provider: "+ parser.getStringRepresentation(identityProvider)+"\n"+
						"Sortal Parents: ";
		
		for (Class sortalParent : this.sortalParents) {
			result += "\n\t"+parser.getStringRepresentation(sortalParent);
		}
		
		return result;
		
	}

}
