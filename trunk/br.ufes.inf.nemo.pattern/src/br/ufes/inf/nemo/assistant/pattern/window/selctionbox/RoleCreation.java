package br.ufes.inf.nemo.assistant.pattern.window.selctionbox;
import javax.swing.JPanel;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Package;
import RefOntoUML.Property;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer.ClassStereotype;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer.RelationStereotype;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class RoleCreation extends ClassSelectionPanel {
//
//	public RoleCreation(String[] substances, String[] roles, String[] relators) {
//		setLayout(null);
//
//		JPanel substanceClasses = createPanel("Substance Sortal", "General", substances, 2);
//		substanceClasses.setBounds(10, 9, 399, 88);
//		add(substanceClasses);
//
//		JPanel roleClasses = createPanel("Role", "Specific", roles, 2);
//		roleClasses.setBounds(10, 106, 399, 83);
//		add(roleClasses);
//
//
//		JPanel relatorClasses = createPanel("Relator", "Relator", relators, 1);
//		relatorClasses.setBounds(10, 198, 399, 61);
//		add(relatorClasses);
//
//	}
//
//	@Override
//	public Fix getFix(Package root, double x, double y) {
//		fix = new Fix();
//		outcomeFixer = new OutcomeFixer(root);
//		
//		//Left side
//		Classifier leftGeneral = createClassifier(root, ClassStereotype.KIND, getClassName("General0"), x, y);
//		Classifier leftSpecific = createClassifier(root, ClassStereotype.ROLE, getClassName("Specific0"), x, y+horizontalDistance);
//
//		fix.addAll(outcomeFixer.createGeneralization(leftSpecific,leftGeneral));
//
//		//Right side
//		Classifier rightGeneral = createClassifier(root, ClassStereotype.KIND, getClassName("General1"), x+verticalDistance, y);
//		Classifier rightSpecific = createClassifier(root, ClassStereotype.ROLE, getClassName("Specific1"), x+verticalDistance, y+horizontalDistance);
//
//		fix.addAll(outcomeFixer.createGeneralization(rightSpecific, rightGeneral));
//
//		//Center side
//		Classifier relator = createClassifier(root, ClassStereotype.RELATOR, getClassName("Relator0"), x+(verticalDistance/2), y+(horizontalDistance/2));
//
//		//Left Mediations
//		Association leftMediation = (Association)outcomeFixer.createAssociationBetween(RelationStereotype.MEDIATION, "", relator, leftSpecific).getAdded().get(0);
//
//		Property srcProperty = outcomeFixer.createProperty(relator, 1, 1);
//		Property tgtProperty = outcomeFixer.createProperty(leftSpecific, 1, 1);
//
//		outcomeFixer.setEnds((Association) leftMediation, srcProperty, tgtProperty);
//		fix.includeAdded(leftMediation);
//
//		//Right Mediations
//		Association rightMediation = (Association)outcomeFixer.createAssociationBetween(RelationStereotype.MEDIATION, "", relator, rightSpecific).getAdded().get(0);
//
//		tgtProperty = outcomeFixer.createProperty(rightSpecific, 1, 1);
//
//		outcomeFixer.setEnds((Association) rightMediation, srcProperty, tgtProperty);
//		fix.includeAdded(rightMediation);
//
//		//Material
//		Association material = (Association)outcomeFixer.createAssociationBetween(RelationStereotype.MATERIAL, "", leftSpecific, rightSpecific).getAdded().get(0);
//
//		srcProperty = outcomeFixer.createProperty(leftSpecific, 1, 1);
//		tgtProperty = outcomeFixer.createProperty(rightSpecific, 1, 1);
//
//		outcomeFixer.setEnds((Association) material, srcProperty, tgtProperty);
//		fix.includeAdded(material);
//
//		return fix;
//
//	}

	@Override
	public void getRunPattern(double x, double y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void getModelValues(OntoUMLParser parser) {
		// TODO Auto-generated method stub
		
	}

}
