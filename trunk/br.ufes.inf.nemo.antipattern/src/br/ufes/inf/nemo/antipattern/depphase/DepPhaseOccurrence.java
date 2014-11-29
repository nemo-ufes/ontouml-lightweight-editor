package br.ufes.inf.nemo.antipattern.depphase;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Classifier;
import RefOntoUML.Derivation;
import RefOntoUML.MaterialAssociation;
import RefOntoUML.Mediation;
import RefOntoUML.Phase;
import RefOntoUML.Property;
import RefOntoUML.Relator;
import RefOntoUML.Role;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;
import br.ufes.inf.nemo.common.ontoumlfixer.ClassStereotype;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;

//Relationally Dependent Phase
public class DepPhaseOccurrence extends AntipatternOccurrence{

	private Phase phase;
	private ArrayList<Property> relatorEnds;
	ArrayList<Relator> relators;
	
	public DepPhaseOccurrence(Phase phase, ArrayList<Property> relatorEnds, DepPhaseAntipattern ap) throws Exception {
		super(ap);
		this.phase = phase;
				
		for (Property p : relatorEnds) {
			//verifies if every property: 1) is an opposite to another whose type is the provided phase; 2) its association is a mediation
			if(!p.getOpposite().getType().equals(phase) || !(p.getAssociation() instanceof Mediation))
				throw new Exception("DepPhase: Problem in creating anti-pattern.");
			
			//verifies if p's type is a relator or if one of its supertypes is.
			boolean hasRelatorType = false;
			ArrayList<Classifier> classifiers = new ArrayList<Classifier>();
			classifiers.addAll(((Classifier)p.getType()).allParents());
			classifiers.add((Classifier) p.getType());
			
			for (Classifier c : classifiers)				
				if (c instanceof Relator) 
					hasRelatorType = true;
			
			if(!hasRelatorType)
				throw new Exception("DepPhase: Phase is not dependent on any relator.");
		}
		
		this.relatorEnds = relatorEnds;
		relators = new ArrayList<Relator>();
		for (Property p : relatorEnds) {
			relators.add((Relator) p.getType());
		}
		
	}
	
	public Phase getPhase() {
		return phase;
	}

	public ArrayList<Property> getRelatorEnds() {
		return relatorEnds;
	}

	public ArrayList<Relator> getRelators(){
		return relators;
	}
	@Override
	public OntoUMLParser setSelected() {
		ArrayList<EObject> selection = new ArrayList<EObject>();
		
		//selects the phase
		selection.add(phase);
		
		for (Property p : relatorEnds) {
			selection.add(p.getAssociation());
			selection.add(p.getType());
		}
		
		parser.select(selection,true);
		parser.autoSelectDependencies(OntoUMLParser.SORTAL_ANCESTORS, false);

		return parser;
	}
	
	
	@Override
	public String toString(){
		String result = parser.getStringRepresentation(phase)+"\n"+
						"Dependencies:";
		
		for (Property p : relatorEnds){
			result += "\n\t"+parser.getStringRepresentation(p);
		}
		
		return result;
		
	}

	@Override
	public String getShortName() {
		return parser.getStringRepresentation(getPhase());
	}
	
	public void changeToRole(){
		fix.addAll(fixer.changeClassStereotypeTo(phase, ClassStereotype.ROLE));
	}
	
	public void separateRelationalDependencyOnSubtype(Property p){
		String relatorName = p.getType().getName();
		Fix fixes = new Fix();
		
		//creates a rule subtype for the phase and reconnected the given property to it
		if(getRelatorEnds().contains(p)){
			fixes = fixer.createSubTypeAsInvolvingLink(phase, ClassStereotype.ROLE, p.getAssociation());
			
			Role createdRole = fixes.getAddedByType(Role.class).get(0);
			createdRole.setName("RoleOf"+relatorName);
			
			for (MaterialAssociation material : getMaterials((Relator) p.getType())) {
				fix.addAll(fixer.changeReferencesInAssociation(material, phase, createdRole));
			}
			
			fix.addAll(fixes);
		}
	}
	
	public void separateRelationalDependencyOnSupertype(Property p){
			
		//creates role supertype for the phase and reconnect the given property to it
		if(getRelatorEnds().contains(p)){
			Fix fixes = new Fix();
			Classifier phaseParent = null;
			String relatorName = p.getType().getName();
			
			if(phase.parents()!=null && phase.parents().size()>0);
				phaseParent = phase.parents().get(0);
			
			fixes.addAll(fixer.createSuperTypeEnvolvingLink(phase, ClassStereotype.ROLE, p.getAssociation()));
			Role createdRole = fixes.getAddedByType(Role.class).get(0);
			createdRole.setName("RoleOf"+relatorName);
			
			//creates a generalization from the created role to a parent of the phase
			if (phaseParent instanceof Classifier)
				fix.addAll(fixer.createGeneralization(createdRole, phaseParent));
			
			for (MaterialAssociation material : getMaterials((Relator) p.getType())) {
				fix.addAll(fixer.changeReferencesInAssociation(material, phase, createdRole));
			}
			
			fix.addAll(fixes);
		}
	}
	
	//get materials derived from relator r
	public ArrayList<MaterialAssociation> getMaterials(Relator r){
		ArrayList<MaterialAssociation> materials = new ArrayList<MaterialAssociation>();
		for (Derivation derivation : parser.getAllInstances(Derivation.class)) {
			MaterialAssociation material = OntoUMLParser.getMaterial(derivation);
			Relator relator = OntoUMLParser.getRelator(derivation);
			if(r.equals(relator) && 
					(material.getMemberEnd().get(0).getType().equals(phase) || material.getMemberEnd().get(1).getType().equals(phase)))
				materials.add(material);
		}
		return materials;
	}
	
	
	
	
	
}
