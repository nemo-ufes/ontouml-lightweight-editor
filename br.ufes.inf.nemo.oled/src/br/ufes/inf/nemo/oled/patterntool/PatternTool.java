package br.ufes.inf.nemo.oled.patterntool;

import java.util.ArrayList;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
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

	private static Classifier _general;
	
	/**
	 * Public methods 
	 */
	
	public static Fix createSubkindPattern(Package root, double x, double y) {
		fix = new Fix();
		outcomeFixer = new OutcomeFixer(root);

		Classifier general = createClassifier(root, ClassStereotype.KIND, "General", x, y);
		Classifier specific = createClassifier(root, ClassStereotype.SUBKIND, "Specific", x, y+horizontalDistance);

		fix.addAll(outcomeFixer.createGeneralization(specific, general));

		return fix;
	}

	public static Fix createSubkindPartitionPattern(Package root, double x, double y) {
		fix = new Fix();
		outcomeFixer = new OutcomeFixer(root);
		createPartition(root, x, y, ClassStereotype.KIND, ClassStereotype.SUBKIND, 2);
		return fix;
	}

	public static Fix createPhasePartitionPattern(Package root, double x, double y) {
		fix = new Fix();
		outcomeFixer = new OutcomeFixer(root);
		createPartition(root, x, y, ClassStereotype.KIND, ClassStereotype.PHASE, 2);
		outcomeFixer.createAttribute(_general, "attribute", ClassStereotype.PRIMITIVETYPE, "Integer");
		return fix;
	}
	
	public static Fix createRelatorPattern(Package root, double x, double y) {
		fix = new Fix();
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

	public static Fix createRolePattern(Package root, double x, double y) {
		fix = new Fix();
		outcomeFixer = new OutcomeFixer(root);

		Classifier general = createClassifier(root, ClassStereotype.KIND, "General", x, y);
		Classifier specific = createClassifier(root, ClassStereotype.ROLE, "Specific", x, y+horizontalDistance);

		fix.addAll(outcomeFixer.createGeneralization(specific, general));

		return fix;
	}

	/**
	 * Private methods
	 * */
	
	private static Classifier createClassifier(Package root, ClassStereotype stereotype, String name, double x, double y){
		RefOntoUML.Classifier classifier = (Classifier) outcomeFixer.createClass(stereotype);
		classifier.setName(name);
		root.getPackagedElement().add(classifier);
		fix.includeAdded(classifier,x,y);

		return classifier;
	}

	private static void createPartition(Package root, double x, double y,ClassStereotype generalType, ClassStereotype specificsType, int specificQuant){
		outcomeFixer = new OutcomeFixer(root);
		ArrayList<Generalization> generalizationList = new ArrayList<>();

		Classifier general = createClassifier(root, generalType, "General", (x+(specificQuant/2*verticalDistance)/3)-60, y);
		_general = general;
		
		for (int i = 0; i < specificQuant; i++) {
			Classifier specific = createClassifier(root, specificsType, "Specific"+i, x+(i*verticalDistance)/3, y+horizontalDistance);
			Fix _fix = outcomeFixer.createGeneralization(specific, general);
			Generalization generalization = (Generalization) _fix.getAdded().get(_fix.getAdded().size()-1);
			generalizationList.add(generalization);
			
			fix.addAll(_fix);
		}
		fix.addAll(outcomeFixer.createGeneralizationSet(generalizationList, true, true, "partition"));
	}
	
}
