package br.ufes.inf.nemo.antipattern.multidep;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.Mediation;
import RefOntoUML.ObjectClass;
import RefOntoUML.Property;
import RefOntoUML.Relator;
import RefOntoUML.Type;
import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

//Multiple Relational Dependency
public class MultiDepOccurrence extends AntipatternOccurrence{

	Classifier type;
	ArrayList<Property> relatorEnds;

	public Classifier getType() {
		return type;
	}

	public ArrayList<Property> getRelatorEnds() {
		return relatorEnds;
	}

	public MultiDepOccurrence (Classifier type, MultiDepAntipattern ap) throws Exception{
		super(ap);
		
		if(type==null)
			throw new NullPointerException("MultiDep: type is null");
		
		if(!(type instanceof ObjectClass))
			throw new Exception("MultiDep: type must be an ObjectClass");
		
		this.type = type;
		this.relatorEnds = new ArrayList<Property>();
		
		for (Mediation m : parser.getAllInstances(Mediation.class)) {
			
			Property source = m.getMemberEnd().get(0);
			Property target = m.getMemberEnd().get(1);
			
			if(source.getType().equals(type) && target.getType() instanceof Relator)
				relatorEnds.add(target);
			if(target.getType().equals(type) && source.getType() instanceof Relator)
				relatorEnds.add(source);	
		}
		
		if (relatorEnds.size()<2){
			throw new Exception("MultiDep: Classifier doesn't depend on multiple types.");
			
		}
	}
	
	@Override
	public OntoUMLParser setSelected() {
		ArrayList<EObject> selection = new ArrayList<EObject>();
		
		selection.add(type);
		selection.addAll(relatorEnds);
		
		parser.selectThisElements(selection,true);
		parser.autoSelectDependencies(OntoUMLParser.SORTAL_ANCESTORS, false);

		return parser;
		
	}
	
	@Override
	public String toString(){
		String result = "Type: "+super.parser.getStringRepresentation(this.type)+"\n"+
						"Relators: ";
		
		for (Property p : relatorEnds){
			result += "\n\t"+super.parser.getStringRepresentation(p);
		}
		
		return result;
		
	}
	
	@Override
	public String getShortName() {
		return parser.getStringRepresentation(this.type);
	}

	
	// OUTCOMING FIXER =====================================================
	//======================================================================
		
	public void createFormalAssociations(ArrayList<ArrayList<Property>> binFormalCombo) {
		
	}

	public void addSubTypeInvolvingMediation(ArrayList<Property> properties) {
		
	}

	public void addSubTypeWithIntermediate(ArrayList<Property> properties) {
		
	}

	public void createGenSet(ArrayList<Generalization> genList) {
		
	}
}
