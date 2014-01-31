package br.ufes.inf.nemo.antipattern.multidep;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Category;
import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.Mediation;
import RefOntoUML.ObjectClass;
import RefOntoUML.Property;
import RefOntoUML.Relator;
import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer.ClassStereotype;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer.RelationStereotype;
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
		
	public void createFormalAssociations(ArrayList<ArrayList<Property>> matrix) 
	{
		for(ArrayList<Property> list: matrix){
			if (list.size()==2){
				fix.addAll( fixer.createAssociationBetween(RelationStereotype.FORMAL, "", list.get(0).getType(), list.get(1).getType()) );
			}
		}		
	}

	public void addSubTypeInvolvingMediation(ArrayList<Property> properties) 
	{
		ArrayList<Generalization> genList = new ArrayList<Generalization>();
		for(Property p : properties)
		{
			Fix partial = new Fix();
			if(getType() instanceof Category){
				partial = fixer.createSubTypeAsInvolvingLink(getType(), ClassStereotype.ROLEMIXIN, p.getAssociation());
			}else{
				partial = fixer.createSubTypeAsInvolvingLink(getType(), ClassStereotype.ROLE, p.getAssociation());
			}
			for(Object obj: fix.getAdded()) { if (obj instanceof Generalization) { genList.add((Generalization)obj); }}
			
			fix.addAll(partial);
		}
		if (genList.size()>1) fix.addAll(fixer.createGeneralizationSet(genList, false, true, ""));
	}

	public void addSubTypeWithIntermediate(ArrayList<Property> properties) {
		ArrayList<Generalization> genList = new ArrayList<Generalization>();
		for(Property p : properties)
		{
			Fix partial = new Fix();
			if(getType() instanceof Category){
				partial = fixer.createSubSubTypeAsInvolvingLink(getType(), ClassStereotype.ROLEMIXIN, ClassStereotype.ROLEMIXIN, p.getAssociation());
			}else{
				partial = fixer.createSubSubTypeAsInvolvingLink(getType(), ClassStereotype.ROLE, ClassStereotype.ROLE, p.getAssociation());
			}
			
			for(Object obj: fix.getAdded()) { if (obj instanceof Generalization && ((Generalization)obj).getGeneral().equals(getType())) genList.add((Generalization)obj); }
			
			fix.addAll(partial);
		}		
		if (genList.size()>1) fix.addAll(fixer.createGeneralizationSet(genList, false, true, ""));
	}
}
