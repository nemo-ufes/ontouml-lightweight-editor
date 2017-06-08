package br.ufes.inf.nemo.pattern.impl.other;

import java.util.ArrayList;
import java.util.Arrays;

import RefOntoUML.Association;
import RefOntoUML.Category;
import RefOntoUML.Classifier;
import RefOntoUML.Collective;
import RefOntoUML.Kind;
import RefOntoUML.Mixin;
import RefOntoUML.Mode;
import RefOntoUML.Package;
import RefOntoUML.Phase;
import RefOntoUML.Property;
import RefOntoUML.Quantity;
import RefOntoUML.Relator;
import RefOntoUML.Role;
import RefOntoUML.RoleMixin;
import RefOntoUML.SubKind;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.assistant.util.UtilAssistant;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;
import br.ufes.inf.nemo.common.ontoumlfixer.RelationStereotype;
import br.ufes.inf.nemo.pattern.impl.AbstractPattern;

public class FOP_MODE extends AbstractPattern{

	public FOP_MODE(OntoUMLParser parser, double x, double y) {
		super(parser, x, y, "/resource/MODE_FOP.png", "Mode Dependent Pattern");
	}

	Classifier c;
	public FOP_MODE(OntoUMLParser parser, Classifier c, double x, double y) {
		super(parser, x, y, "/resource/MODE_FOP.png", "Mode Dependent Pattern");
		this.c = c;
	}

	@Override
	public void runPattern() {
		dym.addHashTree(fillouthashTree(Arrays.asList(new Class[]{Mode.class, Kind.class, Quantity.class, Collective.class, SubKind.class, RoleMixin.class, Relator.class, Role.class, Phase.class, RoleMixin.class, Category.class, Mixin.class})));

		if(c instanceof Mode){
			dym.addTableRigidLine("mode", UtilAssistant.getStringRepresentationClass(c), new String[] {"RoleMixin"});	
		}else{
			dym.addTableLine("mode", "Mode", new String[] {"Mode"});
		}
		
		dym.addTableLine("dependent", "Dependent N", new String[] {"Kind","Collective", "Quantity", "Subkind", "Phase", "Role", "Category", "Mixin", "RoleMixin", "Relator", "Mode"});

		dym.addTableLine("endurant", "Inhered", new String[] {"Kind","Collective", "Quantity", "Subkind", "Phase", "Role", "Category", "Mixin", "RoleMixin", "Relator", "Mode"});
		
		dm.open();
	}

	@Override
	public Fix getSpecificFix(){
		Package root = parser.getModel();
		outcomeFixer = new OutcomeFixer(root);
		fix = new Fix();

		ArrayList<Object[]> dependents = dym.getRowsOf("dependent");
		ArrayList<Object[]> endurants = dym.getRowsOf("endurant");
		ArrayList<Object[]> modes = dym.getRowsOf("mode");

		if(endurants == null || dependents == null || modes == null)
			return null;
		
		Classifier dependent = getClassifier(dependents.get(0),x-71,y+140);
		Classifier endurant = getClassifier(endurants.get(0),x+150, y+28);
		Classifier mode = getClassifier(modes.get(0),x-71, y+28);

		Association inherisIn = null;
		Association externallyDependent = null;

		if(mode != null && dependent != null && endurant != null){
			inherisIn = (Association) outcomeFixer.createAssociationBetween(RelationStereotype.FORMAL, "inheresIn", mode, endurant).getAdded().get(0);
			outcomeFixer.changePropertyMultiplicity((Property) inherisIn.getMemberEnd().get(1), "1..1");
			outcomeFixer.changePropertyMultiplicity((Property) inherisIn.getMemberEnd().get(0), "0..*");
			fix.includeAdded(inherisIn);
			
			externallyDependent = (Association) outcomeFixer.createAssociationBetween(RelationStereotype.FORMAL, "externallyDependent", mode, dependent).getAdded().get(0);
			outcomeFixer.changePropertyMultiplicity((Property) externallyDependent.getMemberEnd().get(1), "1..1");
			outcomeFixer.changePropertyMultiplicity((Property) externallyDependent.getMemberEnd().get(0), "0..*");
			fix.includeAdded(externallyDependent);
		}

		return fix;
	}

}
