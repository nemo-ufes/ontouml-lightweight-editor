package br.ufes.inf.nemo.antipattern.depphase;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Classifier;
import RefOntoUML.Mediation;
import RefOntoUML.Phase;
import RefOntoUML.Property;
import RefOntoUML.Relator;
import RefOntoUML.Role;
import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer.ClassStereotype;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

//Relationally Dependent Phase
public class DepPhaseOccurrence extends AntipatternOccurrence{

	private Phase phase;
	private ArrayList<Property> relatorEnds;
	
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
		
	}
	
	public Phase getPhase() {
		return phase;
	}

	public ArrayList<Property> getRelatorEnds() {
		return relatorEnds;
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
		
		parser.selectThisElements(selection,true);
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
		//creates a rule subtype for the phase and reconnected the given property to it
		if(getRelatorEnds().contains(p)){
			fix.addAll(fixer.createSubTypeAsInvolvingLink(phase, ClassStereotype.ROLE, p.getAssociation()));
			fix.getAddedByType(Role.class).get(0).setName("RoleOf"+relatorName);
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
			
			fixes.addAll(fixer.addSuperTypeEnvolvingLink(phase, ClassStereotype.ROLE, p.getAssociation()));
			fixes.getAddedByType(Role.class).get(0).setName("RoleOf"+relatorName);
			
			//creates a generalization from the created role to a parent of the phase
			if (phaseParent instanceof Classifier)
				fixer.createGeneralization(fixes.getAddedByType(Role.class).get(0), phaseParent);
		}
		
		
	}
	
}
