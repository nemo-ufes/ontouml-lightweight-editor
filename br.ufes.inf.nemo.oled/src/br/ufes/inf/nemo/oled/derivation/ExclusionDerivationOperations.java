package br.ufes.inf.nemo.oled.derivation;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.SortalClass;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;
import br.ufes.inf.nemo.derivedtypes.DerivedByExclusion;
import br.ufes.inf.nemo.oled.DiagramManager;
import br.ufes.inf.nemo.oled.draw.DiagramElement;
import br.ufes.inf.nemo.oled.explorer.ProjectBrowser;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.ui.diagram.DiagramEditor;
import br.ufes.inf.nemo.oled.umldraw.structure.ClassElement;

public class ExclusionDerivationOperations {
	static OutcomeFixer of;
	static Fix mainfix;
	static DiagramManager dman;
	static boolean gs_with_nonsortal_or_kind = false;
	static Point2D.Double pointClicked;
	static HashSet<Classifier> exclusionDerivationList= new HashSet<Classifier>();
	
	public static void createExclusionDerivationSingleSelection(
			DiagramEditor activeEditor, UmlProject project, DiagramManager dm,
			DiagramElement diagramElement, OutcomeFixer outf) {
		boolean has_sortal_superclass = false;
		dman = dm;
		of = outf;
		OntoUMLParser parser = ProjectBrowser.frame.getProjectBrowser()
				.getParser();
		Classifier c = ((ClassElement) diagramElement).getClassifier();
		Fix fix = new Fix();
		mainfix = fix;
		Classifier general;
		Classifier exclusion;
		/*
		 * verify if it already have a exclusion
		 */
		if(isDerivedbyExclusion(c)){
			StereotypeAndNameSelection.wrongSelection("This type already have a exclusion!");
			return;
		}
		
		ArrayList<Classifier> parents = new ArrayList<Classifier>(
				parser.getAllParents(c));

		/*
		 * check if it has a supertype
		 */
		if (parents == null || parents.size() == 0) {
			general=createGeneralElement(has_sortal_superclass, c, diagramElement);
		} else {
			ArrayList<String> exitent_supertypes = new ArrayList<String>();
			for (Classifier parent : parents) {
				if (parent instanceof SortalClass) {
					has_sortal_superclass = true;
				}
				exitent_supertypes.add(parent.getName());
				// stereotypes.add(parent.eClass().getName());
			}
			if (JOptionPane
					.showConfirmDialog(
							null,
							"The Exclusion derivation needs a supertype, Do you want to use a existent supertype of this class?",
							"WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			    String input = (String) JOptionPane.showInputDialog(null, "Choose one of the supertypes",
			        "Supertype of the Exclusion Derivation", JOptionPane.QUESTION_MESSAGE, null, 
			        exitent_supertypes.toArray(), // Array of choices
			        exitent_supertypes.get(0)); // Initial choice
			    	general= parents.get(exitent_supertypes.indexOf(input));
			    
			} else {
					general=createGeneralElement(has_sortal_superclass, c, diagramElement);
					// generalizations.add();
					
			}

		}
		exclusion = createDerivedElement(has_sortal_superclass, c, diagramElement, general);
		createGeneralizations(exclusion, general, c);
		exclusionDerivationList.add(c);
		exclusionDerivationList.add(exclusion);
		dm.updateOLED(mainfix);
	}

	private static boolean isDerivedbyExclusion(Classifier element){
		if(exclusionDerivationList.contains(element)){
			return true;
		}
		return false;
		
	}
	
	private static Classifier createGeneralElement(boolean has_sortal_superclass, Classifier c, DiagramElement diagramElement){
		ArrayList<String> stereotypes = DerivedByExclusion.getInstance()
				.getPossibleGeneralization(c.eClass().getName());
		
		DerivedTypesOperations.setDman(dman);
		DerivedTypesOperations.setMainfix(mainfix);
		
		if (has_sortal_superclass) {
			stereotypes = DerivedTypesOperations.removeSortals(stereotypes);
		}
		Object[] stereo;
		stereo = stereotypes.toArray();
		JPanel panel = StereotypeAndNameSelection.selectStereotype(stereo,
				"Define the Supertype");
		Type supertype = new Type();
		
		supertype.setName(((JTextField) panel.getComponents()[0]).getText());
		supertype.setStereotype(((JComboBox) panel.getComponents()[1])
				.getSelectedItem().toString());
		Classifier pai = DerivedTypesOperations.includeElement(
				new Point2D.Double(
						((ClassElement) diagramElement)
								.getAbsoluteX1() + 100,
						((ClassElement) diagramElement)
								.getAbsoluteY1() - 100), supertype.getName(),
				supertype.getStereotype(), of);
		return pai;
	}
	
	private static Classifier createDerivedElement(boolean has_sortal_superclass, Classifier c, DiagramElement diagramElement, Classifier general){
		ArrayList<String> stereotypes2 = DerivedByExclusion.getInstance()
				.inferStereotype(general.eClass().getName(), c.eClass().getName());
		Object[] stereo2;
		stereo2 = stereotypes2.toArray();
		String name2 = null;
		String stereotype2 = null;
		
		if(stereotypes2.size()>1){
			JPanel panel2 = StereotypeAndNameSelection.selectStereotype(stereo2,
					"Define the Exclusion");
			name2 = ((JTextField) panel2.getComponents()[0])
					.getText();
			stereotype2 = ((JComboBox) panel2.getComponents()[1])
					.getSelectedItem().toString();
		}else{
			stereotype2 = stereotypes2.get(0);
			name2= StereotypeAndNameSelection.DefineNameDerivedType();
		}
		
		Classifier exclusion = DerivedTypesOperations.includeElement(
				new Point2D.Double(
						((ClassElement) diagramElement)
								.getAbsoluteX1() + 200,
						((ClassElement) diagramElement)
								.getAbsoluteY1()), name2,
				stereotype2, of);
		return exclusion;
	}

	private static void createGeneralizations(Classifier exclusion, Classifier general, Classifier base){
		ArrayList<Generalization> generalizations = new ArrayList<Generalization>();
		Fix fix1 = of.createGeneralization(exclusion, general);
		Fix fix2 = of.createGeneralization(base, general);
		generalizations
				.add((Generalization) fix1.getAdded().get(0));
		generalizations
				.add((Generalization) fix2.getAdded().get(0));
		Fix gs = of.createGeneralizationSet(generalizations, true,
				true);
		mainfix.addAll(gs);
		return;
	}
}

	