package br.ufes.inf.nemo.antipattern.GSRig;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.AntiRigidSortalClass;
import RefOntoUML.Category;
import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.Mixin;
import RefOntoUML.RigidSortalClass;
import RefOntoUML.RoleMixin;
import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

//Mixed Rigidity in Generalization Set
public class GSRigOccurrence extends AntipatternOccurrence{

	public GeneralizationSet getGs() {
		return gs;
	}

	public ArrayList<Classifier> getSpecifics() {
		return specifics;
	}

	public ArrayList<Classifier> getRigidSpecifics() {
		return rigidSpecifics;
	}

	public ArrayList<Classifier> getAntiRigidSpecifics() {
		return antiRigidSpecifics;
	}

	public ArrayList<Classifier> getSemiRigidSpecifics() {
		return semiRigidSpecifics;
	}

	GeneralizationSet gs;
	ArrayList<Classifier> specifics;
	ArrayList<Classifier> rigidSpecifics;
	ArrayList<Classifier> antiRigidSpecifics;
	ArrayList<Classifier> semiRigidSpecifics;
	
	public GSRigOccurrence(GeneralizationSet gs, GSRigAntipattern ap) throws Exception {
		
		super(ap);
		
		this.gs = gs;
		this.specifics = new ArrayList<Classifier>();
		this.rigidSpecifics = new ArrayList<Classifier>();
		this.antiRigidSpecifics = new ArrayList<Classifier>();
		this.semiRigidSpecifics = new ArrayList<Classifier>();
		
		//separates into different list the rigid, anti-rigid and semi-rigid subtypes
		for (Generalization g : gs.getGeneralization()) {
			//only considers generalizations that: 
			//1) are selected in the parser, 
			//2) the type connected in the supertype is selected, 
			//3) and the type connected as the subtype is selected
			if (parser.isSelected(g) && parser.isSelected(g.getSpecific()) && parser.isSelected(g.getGeneral())){
				
				Classifier specific = g.getSpecific();
				
				if (specific instanceof RigidSortalClass || specific instanceof Category )
					rigidSpecifics.add(specific);
				else if (specific instanceof AntiRigidSortalClass || specific instanceof RoleMixin)
					antiRigidSpecifics.add(specific);
				else if (specific instanceof Mixin)
					semiRigidSpecifics.add(specific);
			}
		}
		
		if (rigidSpecifics.size()==0 && antiRigidSpecifics.size()==0)
			throw new Exception("Only semi rigid specifics.");
		if (rigidSpecifics.size()==0 && semiRigidSpecifics.size()==0)
			throw new Exception("Only anti rigid specifics.");
		if (semiRigidSpecifics.size()==0 && antiRigidSpecifics.size()==0)
			throw new Exception("Only rigid specifics.");
		
		specifics.addAll(antiRigidSpecifics);
		specifics.addAll(rigidSpecifics);
		specifics.addAll(semiRigidSpecifics);
	}

	@Override
	public OntoUMLParser setSelected() {
		ArrayList<EObject> selection = new ArrayList<EObject>();
		
		selection.add(gs);
		for (Generalization g : gs.getGeneralization()) {
			selection.add(g);
			selection.add(g.getGeneral());
			selection.add(g.getSpecific());
		}
		
		parser.selectThisElements(selection,true);
		parser.autoSelectDependencies(OntoUMLParser.SORTAL_ANCESTORS, false);

		return parser;
	}
	
	@Override
	public String toString(){
		String result = parser.getStringRepresentation(gs)+"\n"+
						"Rigid Supertype: "+parser.getStringRepresentation(gs.getGeneralization().get(0).getGeneral())+"\n";
		
		result+="Rigid Subtypes: ";
		for (Classifier rigid : this.rigidSpecifics)
			result+="\n\t"+parser.getStringRepresentation(rigid);
		
		result+="\nAnti-Rigid Subtypes: ";
		for (Classifier antiRigid : this.antiRigidSpecifics)
			result+="\n\t"+parser.getStringRepresentation(antiRigid);
		
		result+="\nSemi-Rigid Subtypes: ";
		for (Classifier semiRigid : this.semiRigidSpecifics)
			result+="\n\t"+parser.getStringRepresentation(semiRigid);
		
		return result;
		
	}

	@Override
	public String getShortName() {
		return parser.getStringRepresentation(getGs());
	}

	// OUTCOMING FIXES ================================================
	
	public void deleteGenSet() {
		fix.addAll(fixer.deleteElement(getGs()));		
	}

	public void createGenSetForRigids() {
		fix.addAll(fixer.createGeneralizationSet(getGs().getGeneralization().get(0).getGeneral(),getRigidSpecifics()));				
	}

	public void createGenSetForAntiRigids() {
		fix.addAll(fixer.createGeneralizationSet(getGs().getGeneralization().get(0).getGeneral(),getAntiRigidSpecifics()));		
	}

	public void createGenSetForBoth() {
		fix.addAll(fixer.createGeneralizationSet(getGs().getGeneralization().get(0).getGeneral(),getAntiRigidSpecifics()));
		fix.addAll(fixer.createGeneralizationSet(getGs().getGeneralization().get(0).getGeneral(),getRigidSpecifics()));
	}

}
