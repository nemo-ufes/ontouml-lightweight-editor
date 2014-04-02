package br.ufes.inf.nemo.oled.ontoumlpattern;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Package;
import RefOntoUML.Property;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer.ClassStereotype;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer.RelationStereotype;

public class PatternTool {
	private static OutcomeFixer outcomeFixer;
	private static Fix fix = new Fix();

	private static final int horizontalDistance = 150;
	private static final int verticalDistance = 330;

	public static Fix createSubkindPattern(Package root, double x, double y) {
		outcomeFixer = new OutcomeFixer(root);

		Classifier general = createClassifier(root, ClassStereotype.KIND, "General", x, y);
		Classifier specific = createClassifier(root, ClassStereotype.SUBKIND, "Specific", x, y+horizontalDistance);

		fix.addAll(outcomeFixer.createGeneralization(specific, general));

		return fix;
	}

	public static Fix createRelatorPattern(Package root, double x, double y) {
		outcomeFixer = new OutcomeFixer(root);

		Classifier leftGeneral = createClassifier(root, ClassStereotype.KIND, "General0", x, y);
		Classifier leftSpecific = createClassifier(root, ClassStereotype.ROLE, "Specific0", x, y+horizontalDistance);

		fix.addAll(outcomeFixer.createGeneralization(leftSpecific,leftGeneral));
		
		Classifier rightGeneral = createClassifier(root, ClassStereotype.KIND, "General1", x+verticalDistance, y);
		Classifier rightSpecific = createClassifier(root, ClassStereotype.ROLE, "Specific1", x+verticalDistance, y+horizontalDistance);

		fix.addAll(outcomeFixer.createGeneralization(rightSpecific, rightGeneral));
		
		Classifier relator = createClassifier(root, ClassStereotype.RELATOR, "Relator0", x+(verticalDistance/2), y+(horizontalDistance/2));

		Association leftMediation = (Association)outcomeFixer.createAssociationBetween(RelationStereotype.MEDIATION, "", relator, leftSpecific).getAdded().get(0);
		
		Property srcProperty = outcomeFixer.createProperty(relator, 1, 1);
		Property tgtProperty = outcomeFixer.createProperty(leftSpecific, 1, 1);

		outcomeFixer.setEnds((Association) leftMediation, srcProperty, tgtProperty);
		fix.includeAdded(leftMediation);
		
		Association rightMediation = (Association)outcomeFixer.createAssociationBetween(RelationStereotype.MEDIATION, "", relator, rightSpecific).getAdded().get(0);
		
		tgtProperty = outcomeFixer.createProperty(rightSpecific, 1, 1);

		outcomeFixer.setEnds((Association) rightMediation, srcProperty, tgtProperty);
		fix.includeAdded(rightMediation);
		
		Association material = (Association)outcomeFixer.createAssociationBetween(RelationStereotype.MATERIAL, "", leftSpecific, rightSpecific).getAdded().get(0);
		
		srcProperty = outcomeFixer.createProperty(leftSpecific, 1, 1);
		tgtProperty = outcomeFixer.createProperty(rightSpecific, 1, 1);

		outcomeFixer.setEnds((Association) material, srcProperty, tgtProperty);
		fix.includeAdded(material);
		
		
		return fix;
	}


	private static Classifier createClassifier(Package root, ClassStereotype stereotype, String name, double x, double y){
		RefOntoUML.Classifier classifier = (Classifier) outcomeFixer.createClass(stereotype);
		classifier.setName(name);
		root.getPackagedElement().add(classifier);
		fix.includeAdded(classifier,x,y);
		
		return classifier;
	}

	public static Fix createRolePattern(Package root, double x, double y) {
		outcomeFixer = new OutcomeFixer(root);

		Classifier general = createClassifier(root, ClassStereotype.KIND, "General", x, y);
		Classifier specific = createClassifier(root, ClassStereotype.ROLE, "Specific", x, y+horizontalDistance);

		fix.addAll(outcomeFixer.createGeneralization(specific, general));

		return fix;
	}
}
