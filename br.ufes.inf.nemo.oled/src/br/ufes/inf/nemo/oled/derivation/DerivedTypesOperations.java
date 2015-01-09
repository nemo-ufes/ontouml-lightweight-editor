/**
 * Copyright(C) 2011-2014 by John Guerson, Tiago Prince, Antognoni Albuquerque
 *
 * This file is part of OLED (OntoUML Lightweight BaseEditor).
 * OLED is based on TinyUML and so is distributed under the same
 * license terms.
 *
 * OLED is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * OLED is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OLED; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package br.ufes.inf.nemo.oled.derivation;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import RefOntoUML.AntiRigidMixinClass;
import RefOntoUML.AntiRigidSortalClass;
import RefOntoUML.Association;
import RefOntoUML.Class;
import RefOntoUML.Classifier;
import RefOntoUML.Element;
import RefOntoUML.Generalization;
import RefOntoUML.Kind;
import RefOntoUML.Mixin;
import RefOntoUML.NamedElement;
import RefOntoUML.Phase;
import RefOntoUML.Property;
import RefOntoUML.RigidMixinClass;
import RefOntoUML.RigidSortalClass;
import RefOntoUML.Role;
import RefOntoUML.RoleMixin;
import RefOntoUML.SemiRigidMixinClass;
import RefOntoUML.SubKind;
import RefOntoUML.SubstanceSortal;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.common.ontoumlfixer.ClassStereotype;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;
import br.ufes.inf.nemo.common.ontoumlfixer.RelationStereotype;
import br.ufes.inf.nemo.common.positioning.ClassPosition;
import br.ufes.inf.nemo.derivedtypes.DerivedByUnion;
import br.ufes.inf.nemo.oled.DiagramManager;
import br.ufes.inf.nemo.oled.draw.Connection;
import br.ufes.inf.nemo.oled.draw.DiagramElement;
import br.ufes.inf.nemo.oled.explorer.ProjectBrowser;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.ui.diagram.DiagramEditor;
import br.ufes.inf.nemo.oled.umldraw.structure.AssociationElement;
import br.ufes.inf.nemo.oled.umldraw.structure.ClassElement;
import br.ufes.inf.nemo.oled.umldraw.structure.GeneralizationElement;

/**
 * @author Cássio Reginato
 */
public class DerivedTypesOperations {

	static OutcomeFixer of;
	static Fix mainfix;
	static DiagramManager dman;
	static boolean gs_with_nonsortal_or_kind = false;
	static Point2D.Double pointClicked;
	static HashMap<Classifier, Integer> exclusionDerivationList= new HashMap<Classifier, Integer>();
	static Classifier unionDerived;

	public static void setPointClicked(Point2D.Double pointClicked) {
		DerivedTypesOperations.pointClicked = pointClicked;
	}

	@SuppressWarnings("unused")
	private class FeatureElement {
		RefOntoUML.Element element;
		OntoUMLParser ref;

		public FeatureElement(RefOntoUML.Element element,
				OntoUMLParser refparser) {
			ref = refparser;
			this.element = element;
		}

		public Element getElement() {
			return element;
		}

		@Override
		public String toString() {
			String result = new String();

			if (element instanceof RefOntoUML.Property) {
				Property p = (Property) element;
				String owner = new String();
				if (p.getAssociation() == null) {
					owner = "" + ref.getStereotype(p.eContainer()) + " "
							+ ((NamedElement) p.eContainer()).getName();
				} else {
					owner = "" + ref.getStereotype(p.getAssociation()) + " "
							+ ((NamedElement) p.getAssociation()).getName();
				}
				result += "Property " + p.getType().getName() + ": ("
						+ p.getName() + ") [" + p.getLower() + ","
						+ p.getUpper() + "] " + " (owner: " + owner + ")";
			}

			return result;
		}
	}

	public static Fix createUnionDerivation(DiagramEditor activeEditor,
			UmlProject project, DiagramManager dm) {

		String name = "";
		JPanel panel = new JPanel();

		if(!DerivedTypesOperations.verifyHierarquicalProblem(activeEditor)){
			StereotypeAndNameSelection.wrongSelection("Invalid Selection");
			return null;
		}
		
		Fix mainfix = new Fix();
		List<DiagramElement> selected = activeEditor.getSelectedElements();
		ArrayList<RefOntoUML.Element> refontoList = new ArrayList<RefOntoUML.Element>();

		for (int i = 0; i < selected.size(); i++) {
			if (selected.get(i) instanceof ClassElement) {
				ClassElement ce = (ClassElement) selected.get(i);
				refontoList.add(ce.getClassifier());
			}
		}
		if (selected.size() == 2 && selected.get(0) instanceof ClassElement
				&& selected.get(1) instanceof ClassElement) {
			if (refontoList.size() == 2) {

				ArrayList<String> stereotypes = DerivedByUnion.getInstance()
						.inferStereotype(refontoList.get(0).eClass().getName(),
								refontoList.get(1).eClass().getName());
				if (stereotypes != null)
					if (stereotypes.size() < 2) {
						name = StereotypeAndNameSelection.defineNameDerivedType("Union Drivation");
						if(name==null){
							return null;
						}
						mainfix = createDerivedTypeUnion(stereotypes.get(0),
								mainfix, selected, name, refontoList, project,
								dm);
					} else {
						Object[] stereo;
						stereo = stereotypes.toArray();
						panel = selectStereotype(stereo);
						if(panel==null){
							return null;
						}
						mainfix = createDerivedTypeUnion(
								((JComboBox) panel.getComponents()[1])
										.getSelectedItem().toString(), mainfix,
								selected,
								((JTextField) panel.getComponents()[0])
										.getText(), refontoList, project, dm);
					}
			}
		} else {
			String stereotype = null;
			String specialCase = multipleElementsUnionDerivation(selected);
			String specialCaseRelation = tryunionassociationderivation(
					selected, dm);

			if (specialCaseRelation.equals("union_relation_derivation")) {
				return null;
			}

			if (specialCase.equals("")) {
				wrongSelection("Wrong Selection");
				return null;
			}
			if (specialCase == "Rigid+NonRigid") {
				Object[] stereo;
				ArrayList<String> stereotypes = new ArrayList<String>();
				stereotypes.add("Mixin");
				stereotypes.add("Category");
				stereo = stereotypes.toArray();
				panel = selectStereotype(stereo);
				if(panel==null){
					return null;
				}
				mainfix = createDerivedTypeUnion(
						((JComboBox) panel.getComponents()[1])
								.getSelectedItem().toString(), mainfix,
						selected,
						((JTextField) panel.getComponents()[0]).getText(),
						refontoList, project, dm);
			} else {
				if (specialCase == "SemiRigid+Non-Rigid") {
					Object[] stereo;
					ArrayList<String> stereotypes = new ArrayList<String>();
					stereotypes.add("Category");
					stereotypes.add("RoleMixin");
					stereo = stereotypes.toArray();
					panel = selectStereotype(stereo);
					if(panel==null){
						return null;
					}
					mainfix = createDerivedTypeUnion(
							((JComboBox) panel.getComponents()[1])
									.getSelectedItem().toString(), mainfix,
							selected,
							((JTextField) panel.getComponents()[0]).getText(),
							refontoList, project, dm);
				}
			}
			if (gs_with_nonsortal_or_kind) {
				if (specialCase == "AllRigid") {
					stereotype = "Category";
					name = DefineNameDerivedType();
					if(name==null){
						return null;
					}
					mainfix = createDerivedTypeUnion(stereotype, mainfix,
							selected, name, refontoList, project, dm);
				} else {
					if (specialCase == "AllAntiRigid") {
						stereotype = "RoleMixin";
						name = DefineNameDerivedType();
						if(name==null){
							return null;
						}
						mainfix = createDerivedTypeUnion(stereotype, mainfix,
								selected, name, refontoList, project, dm);
					}
				}
			} else {
				if (specialCase == "AllRigid") {
					Object[] stereo;
					ArrayList<String> stereotypes = new ArrayList<String>();
					stereotypes.add("SubKind");
					stereotypes.add("Category");
					stereo = stereotypes.toArray();
					panel = selectStereotype(stereo);
					if(panel==null){
						return null;
					}
					mainfix = createDerivedTypeUnion(
							((JComboBox) panel.getComponents()[1])
									.getSelectedItem().toString(), mainfix,
							selected,
							((JTextField) panel.getComponents()[0]).getText(),
							refontoList, project, dm);
				} else {
					if (specialCase == "AllAntiRigid") {
						Object[] stereo;
						ArrayList<String> stereotypes = new ArrayList<String>();
						stereotypes.add("Kind");
						stereotypes.add("Collective");
						stereotypes.add("Quantity");
						stereotypes.add("SubKind");
						stereotypes.add("Role");
						stereotypes.add("Phase");
						stereotypes.add("RoleMixin");
						stereo = stereotypes.toArray();
						panel = selectStereotype(stereo);
						if(panel==null){
							return null;
						}
						mainfix = createDerivedTypeUnion(
								((JComboBox) panel.getComponents()[1])
										.getSelectedItem().toString(), mainfix,
								selected,
								((JTextField) panel.getComponents()[0])
										.getText(), refontoList, project, dm);
					}
				}
			}

		}
		return mainfix;
	}

	private static String tryunionassociationderivation(
			List<DiagramElement> selected, DiagramManager diagramManager) {
		// TODO Auto-generated method stub
		ArrayList<Property> featureList = new ArrayList<Property>();
		ArrayList<Association> associations = new ArrayList<Association>();
		ArrayList<Property> properties = new ArrayList<Property>();
		ArrayList<Property> propertiesTarget = new ArrayList<Property>();
		ArrayList<String> options = new ArrayList<String>();

		for (DiagramElement sel : selected) {
			if (!(sel instanceof AssociationElement))
				return "";
			associations.add((Association) ((AssociationElement) sel)
					.getRelationship());
		}

		for (Association association : associations) {
			properties.add((Property) association.getMemberEnd().get(0));
			propertiesTarget.add((Property) association.getMemberEnd().get(1));
		}

		Property element = (Property) associations.get(0).getMemberEnd().get(0);
		OntoUMLParser refparser = diagramManager.getFrame().getBrowserManager()
				.getProjectBrowser().getParser();

		for (RefOntoUML.Property p : refparser
				.getAllInstances(RefOntoUML.Property.class)) {
			if (!properties.contains(p)
					&& !propertiesTarget.contains(p)
					&& ((Classifier) element.getType()).allParents().contains(
							p.getType())) {
				featureList.add(p);
				options.add(((NamedElement) p.eContainer()).getName());
			}
		}
		ArrayList<Property> twoProp = new ArrayList<Property>();
		ArrayList<Property> twoPropTarget = new ArrayList<Property>();
		@SuppressWarnings("unused")
		Property f = null;
		@SuppressWarnings("unused")
		Property f1 = null;
		String option = selectRelation(options.toArray());
		for (Property feature : featureList) {
			if (((NamedElement) feature.eContainer()).getName().equals(option)) {
				for (Property prop : properties) {
					prop.getSubsettedProperty().add(feature);
					// feature.setIsDerivedUnion(true);
					twoProp.add(prop);
					f = feature;
				}
				for (Property prop : propertiesTarget) {
					prop.getSubsettedProperty().add(feature);
					// feature.setIsDerivedUnion(true);
					twoPropTarget.add(prop);
					f1 = feature.getOpposite();
				}
			}
		}
		return "union_relation_derivation";

	}

	private static String multipleElementsUnionDerivation(
			List<DiagramElement> selected) {
		// String anterior="";
		String specialCase = "AllRigid";
		DiagramElement diagramElement = selected.get(0);
		List<DiagramElement> selected2 = new ArrayList<DiagramElement>();
		selected2.addAll(selected);
		selected2.remove(0);
		if (!(diagramElement instanceof ClassElement)) {
			return "";
		} else {
			ClassElement ce = (ClassElement) diagramElement;
			if (ce.getClassifier() instanceof RigidMixinClass
					|| ce.getClassifier() instanceof RigidSortalClass) {
				for (DiagramElement otherelement : selected2) {
					ce = (ClassElement) otherelement;
					if ((ce.getClassifier() instanceof RigidMixinClass
							|| ce.getClassifier() instanceof AntiRigidMixinClass
							|| ce.getClassifier() instanceof SemiRigidMixinClass || ce
								.getClassifier() instanceof Kind)) {
						gs_with_nonsortal_or_kind = true;
					}
					if (!(ce.getClassifier() instanceof RigidMixinClass || ce
							.getClassifier() instanceof RigidSortalClass)) {
						specialCase = "Rigid+NonRigid";
						break;
					}
				}
			} else {
				if (ce.getClassifier() instanceof SemiRigidMixinClass) {
					for (DiagramElement otherelement : selected2) {
						ce = (ClassElement) otherelement;
						if ((ce.getClassifier() instanceof RigidMixinClass
								|| ce.getClassifier() instanceof AntiRigidMixinClass
								|| ce.getClassifier() instanceof SemiRigidMixinClass || ce
									.getClassifier() instanceof Kind)) {
							gs_with_nonsortal_or_kind = true;
						}
						if ((ce.getClassifier() instanceof RigidMixinClass
								|| ce.getClassifier() instanceof RigidSortalClass || ce
									.getClassifier() instanceof Kind)) {
							specialCase = "Rigid+NonRigid";
							break;
						}
					}
					specialCase = "SemiRigid+Non-Rigid";
				} else {
					for (DiagramElement otherelement : selected2) {
						ce = (ClassElement) otherelement;
						if ((ce.getClassifier() instanceof RigidMixinClass
								|| ce.getClassifier() instanceof AntiRigidMixinClass
								|| ce.getClassifier() instanceof SemiRigidMixinClass || ce
									.getClassifier() instanceof Kind)) {
							gs_with_nonsortal_or_kind = true;
						}
						if (ce.getClassifier() instanceof RigidMixinClass
								|| ce.getClassifier() instanceof RigidSortalClass
								|| ce.getClassifier() instanceof Kind) {
							specialCase = "Rigid+NonRigid";
							return specialCase;
						}
					}
					specialCase = "AllAntiRigid";
				}
			}
		}
		return specialCase;
	}

	@SuppressWarnings("unused")
	public static Fix createDerivedTypeUnion(String stereotype, Fix fix,
			List<DiagramElement> selected, String name,
			ArrayList<RefOntoUML.Element> refontoList, UmlProject project,
			DiagramManager dm) {
		dman = dm;
		mainfix = fix;
		Classifier union;
		// UmlProject project = getCurrentEditor().getProject();
		if (selected.size() < 3) {
			of = new OutcomeFixer(project.getModel());

			Point2D.Double firstpoint = new Point2D.Double();
			Point2D.Double secondpoint = new Point2D.Double();
			ClassElement position = (ClassElement) selected.get(0);
			ClassElement position2 = (ClassElement) selected.get(1);
			firstpoint.setLocation(position.getAbsoluteX1(),
					position.getAbsoluteY1());
			secondpoint.setLocation(position2.getAbsoluteX1(),
					position2.getAbsoluteY1());
			Point2D.Double newElementPosition = ClassPosition
					.findPositionGeneralization(firstpoint, secondpoint);
			union = includeElement(newElementPosition, name,
					stereotype);
			createGeneralization(union, (Classifier) refontoList.get(0),
					(Classifier) refontoList.get(1));
			mainfix.includeAdded(union, newElementPosition.getX(),
					newElementPosition.getY());

		} else {
			of = new OutcomeFixer(project.getModel());

			Point2D.Double firstpoint = new Point2D.Double();
			Point2D.Double secondpoint = new Point2D.Double();
			ArrayList<Point2D.Double> positions = new ArrayList<Point2D.Double>();
			for (DiagramElement select : selected) {
				ClassElement position = (ClassElement) select;
				Point2D.Double newElementPosition = new Point2D.Double(
						position.getAbsoluteX1(), position.getAbsoluteY1());
				positions.add(newElementPosition);
			}
			Point2D.Double newElementPosition = ClassPosition
					.findPositionGeneralizationMember(positions, 2);
			union = includeElement(newElementPosition, name,
					stereotype);
			ArrayList<Classifier> classifiers = new ArrayList<Classifier>();
			for (Element element : refontoList) {
				classifiers.add((Classifier) element);
			}
			createMultipleGeneralization(union, classifiers);
		}
		unionDerived=union;
		return mainfix;
	}
	
	
	static ArrayList<String> removeSortals(ArrayList<String> stereotypes) {
		stereotypes.remove("Kind");
		stereotypes.remove("Collective");
		stereotypes.remove("Quantity");
		stereotypes.remove("SubKind");
		stereotypes.remove("Role");
		stereotypes.remove("Phase");
		return stereotypes;
	}

	public static String DefineNameDerivedType() {

		String name = "";
		while (name == "") {
			name = JOptionPane.showInputDialog(null,
					"What's the Name of the New Type", "Name Type", 1);
		}
		return name;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static JPanel selectStereotype(Object[] stereo) {

		JPanel p = new JPanel(new BorderLayout(5, 5));

		JPanel labels = new JPanel(new GridLayout(0, 1, 2, 2));
		labels.add(new JLabel("Name", SwingConstants.RIGHT));
		labels.add(new JLabel("Stereotype", SwingConstants.RIGHT));
		p.add(labels, BorderLayout.WEST);

		JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
		JTextField name = new JTextField("");
		controls.add(name);
		JComboBox combo_stereotype = new JComboBox();
		DefaultComboBoxModel model2 = new DefaultComboBoxModel();
		for (Object string : stereo) {
			model2.addElement(string);
		}
		combo_stereotype.setModel(model2);
		combo_stereotype.getModel().setSelectedItem(model2.getElementAt(0));
		controls.add(combo_stereotype);
		p.add(controls, BorderLayout.CENTER);
		ArrayList<String> values = new ArrayList<String>();
		values.add("OK");
		values.add("Cancel");

		int value = JOptionPane.showOptionDialog(null, p,
				"Name and Stereotype", JOptionPane.OK_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, values.toArray(),
				values.toArray()[0]);
		if (value == -1 || value == 1) {
			return null;
		}
		return controls;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static JPanel selectStereotype(Object[] stereo, String message) {

		JPanel p = new JPanel(new BorderLayout(5, 5));

		JPanel labels = new JPanel(new GridLayout(0, 1, 2, 2));
		labels.add(new JLabel("Name", SwingConstants.RIGHT));
		labels.add(new JLabel("Stereotype", SwingConstants.RIGHT));
		p.add(labels, BorderLayout.WEST);

		JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
		JTextField name = new JTextField("");
		controls.add(name);
		JComboBox combo_stereotype = new JComboBox();
		DefaultComboBoxModel model2 = new DefaultComboBoxModel();
		for (Object string : stereo) {
			model2.addElement(string);
		}
		combo_stereotype.setModel(model2);
		combo_stereotype.getModel().setSelectedItem(model2.getElementAt(0));
		controls.add(combo_stereotype);
		p.add(controls, BorderLayout.CENTER);
		ArrayList<String> values = new ArrayList<String>();
		values.add("OK");
		values.add("Cancel");

		int value = JOptionPane.showOptionDialog(null, p, message,
				JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				values.toArray(), values.toArray()[0]);
		if (value == -1 || value == 1) {
			return null;
		}
		return controls;
	}

	public static String selectRelation(Object[] stereo) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("Input Dialog Example 3");
		String stereotype = (String) JOptionPane.showInputDialog(frame,
				"Choose between the possible relations ?",
				"Union Derivation Relation", JOptionPane.QUESTION_MESSAGE,
				null, stereo, stereo[0]);
		return stereotype;

	}

	public static void wrongSelection(String message) {
		JFrame frame = new JFrame("");
		JOptionPane.showMessageDialog(frame, message);

	}

	public static void UnionPattern(DiagramManager dm,
			ArrayList<String> values, Point2D.Double location) {
		dman = dm;
		of = new OutcomeFixer(dm.getCurrentProject().getModel());
		mainfix = new Fix();
		Point2D.Double[] positions = ClassPosition.GSpositioning(2, location);
		Classifier newElement = includeElement(positions[1], values.get(2),
				values.get(0));
		Classifier newElement2 = includeElement(positions[2], values.get(3),
				values.get(1));
		ArrayList<String> stereotypes = DerivedByUnion.getInstance()
				.inferStereotype(newElement.eClass().getName(),
						newElement2.eClass().getName());
		Classifier newElement3 = null;
		if (stereotypes != null) {
			if (stereotypes.size() == 1) {
				newElement3 = includeElement(location, values.get(4),
						stereotypes.get(0));
			}
			createGeneralization(newElement3, newElement2, newElement);
			dm.updateOLED(mainfix);
		}
	}

	// include an element according its position name and category
	public static Classifier includeElement(Point2D.Double position,
			String name, String stereotype) {
		Classifier newElement = (Classifier) of.createClass(of
				.getClassStereotype(stereotype));
		// Classifier newElement= (Classifier)
		// of.createClassWithoutStereotype();
		dman.getCurrentProject().getModel().getPackagedElement()
				.add(newElement);
		newElement.setName(name);
		mainfix.includeAdded(newElement, position.getX(), position.getY());
		return newElement;
	}
	
	public static Classifier includeElement(Point2D.Double position,
			String name, String stereotype, OutcomeFixer of) {
		Classifier newElement = (Classifier) of.createClass(of
				.getClassStereotype(stereotype));
		// Classifier newElement= (Classifier)
		// of.createClassWithoutStereotype();
		dman.getCurrentProject().getModel().getPackagedElement()
				.add(newElement);
		newElement.setName(name);
		mainfix.includeAdded(newElement, position.getX(), position.getY());
		return newElement;
	}

	public static void createGeneralization(Classifier father, Classifier son1,
			Classifier son2) {
		Fix fix = of.createGeneralization(son1, father);
		Fix fixG2 = of.createGeneralization(son2, father);
		ArrayList<Generalization> generalizations = new ArrayList<Generalization>();
		generalizations.add((Generalization) fix.getAdded().get(0));
		generalizations.add((Generalization) fixG2.getAdded().get(0));
		Fix gs = of.createGeneralizationSet(generalizations, true, true);
		mainfix.addAll(fixG2);
		mainfix.addAll(fix);
		mainfix.addAll(gs);
	}

	public static void createGeneralizationSingle(Classifier father,
			Classifier son1) {
		Fix fix = of.createGeneralization(son1, father);
		mainfix.addAll(fix);
	}

	public static void createMultipleGeneralization(Classifier father,
			ArrayList<Classifier> sons) {
		ArrayList<Generalization> generalizations = new ArrayList<Generalization>();
		for (Classifier classifier : sons) {
			Fix fix = of.createGeneralization(classifier, father);
			generalizations.add((Generalization) fix.getAdded().get(0));
			// mainfix.addAll(fix);
		}

		Fix gs = of.createGeneralizationSet(generalizations, true, true);
		mainfix.addAll(gs);
	}

	public static void createMultipleGeneralizationIntersection(Classifier son,
			ArrayList<Classifier> parents) {
		ArrayList<Generalization> generalizations = new ArrayList<Generalization>();
		for (Classifier classifier : parents) {
			Fix fix = of.createGeneralization(son, classifier);
			generalizations.add((Generalization) fix.getAdded().get(0));
			mainfix.addAll(fix);
		}
	}

	public static void exclusionPattern(DiagramManager dman2,
			ArrayList<String> values, Double location) {
		dman = dman2;
		of = new OutcomeFixer(dman2.getCurrentProject().getModel());
		mainfix = new Fix();
		Point2D.Double[] positions = ClassPosition.GSpositioning(2, location);
		Classifier newElement = includeElement(location, values.get(3),
				values.get(0));
		Classifier newElement2 = includeElement(positions[2], values.get(4),
				values.get(1));
		Classifier newElement3 = includeElement(positions[1], values.get(5),
				values.get(2));
		createGeneralization(newElement, newElement2, newElement3);
		dman2.updateOLED(mainfix);

	}

	public static void intersectionPattern(DiagramManager dm,
			String base_1_name, String base_2_name, String derived_name,
			Double location, String base_1_stereo, String stereo_base_2_stereo,
			String derived_stereo) {
		// TODO Auto-generated method stub
		dman = dm;
		of = new OutcomeFixer(dm.getCurrentProject().getModel());
		mainfix = new Fix();
		Point2D.Double[] positions = ClassPosition.GSpositioningDown(2,
				location);
		Classifier newElement = includeElement(positions[1], base_1_name,
				base_1_stereo);
		Classifier newElement2 = includeElement(positions[2], base_2_name,
				stereo_base_2_stereo);
		Classifier newElement3 = includeElement(positions[0], derived_name,
				derived_stereo);
		createGeneralizationSingle(newElement, newElement3);
		createGeneralizationSingle(newElement2, newElement3);
		dm.updateOLED(mainfix);
	}

	@SuppressWarnings("unused")
	public static Fix createIntersectionDerivation(DiagramEditor activeEditor,
			UmlProject project, DiagramManager diagramManager) {
		// TODO Auto-generated method stub
		Fix mainfix = new Fix();
		List<DiagramElement> selected = activeEditor.getSelectedElements();
		List<ClassElement> classList = new ArrayList<ClassElement>();
		List<GeneralizationElement> gen = new ArrayList<GeneralizationElement>();
		ArrayList<RefOntoUML.Element> refontoList = new ArrayList<RefOntoUML.Element>();
		String specialCase = "";

		if(!DerivedTypesOperations.verifyHierarquicalProblem(activeEditor)){
			StereotypeAndNameSelection.wrongSelection("Invalid Selection");
			return null;
		}
		
		for (DiagramElement element : selected) {
			if (element instanceof ClassElement) {
				classList.add((ClassElement) element);
				refontoList.add((Element) ((ClassElement) element)
						.getClassifier());
			}
		}

		for (ClassElement element : classList) {
			if (element.getClassifier() instanceof AntiRigidSortalClass) {
				if (specialCase.equals("Kind")) {
					wrongSelection("Kinds intersect only with Mixins");
					return null;
				}
				if (element.getClassifier() instanceof Role) {
					specialCase = "Role";
				} else {
					if (specialCase != "Role")
						specialCase = "Phase";
				}
			} else {
				if (element.getClassifier() instanceof RigidSortalClass) {
					if (specialCase.equals("Kind")) {
						wrongSelection("Kinds intersect only with Mixins");
						return null;
					}
					if (element.getClassifier() instanceof SubKind) {
						if (!specialCase.equals("Role")
								&& !specialCase.equals("Phase")) {
							if (specialCase.equals("RoleMixin")) {
								specialCase = "Role";
							} else if (specialCase.equals("Mixin")) {
								specialCase = "KindMixin";
							} else {
								specialCase = "SubKind";
							}
						}

					} else {
						if (specialCase.equals("Kind")
								|| specialCase.equals("SubKind")
								|| specialCase.equals("Role")
								|| specialCase.equals("Phase")) {
							wrongSelection("Kinds intersect only with Mixins");
							return null;
						} else {
							if (specialCase == "RoleMixin") {
								specialCase = "Role";
							} else if (specialCase == "Mixin") {
								specialCase = "KindMixin";
							} else if (specialCase == "Category") {
								specialCase = "SubKind";
							} else {
								specialCase = "Kind";
							}
						}
					}
				} else {
					if (element.getClassifier() instanceof RoleMixin) {
						if (specialCase.equals("")
								|| specialCase.equals("RoleMixin")
								|| specialCase.equals("Mixin")
								|| specialCase.equals("Category")) {
							specialCase = "RoleMixin";
						} else if (specialCase.equals("Kind")
								|| specialCase.equals("SubKind")) {
							specialCase = "Role";
						}
					} else if (element.getClassifier() instanceof Mixin) {
						if (specialCase.equals("")
								|| specialCase.equals("Mixin")
								|| specialCase.equals("Category")) {
							specialCase = "Mixin";
						} else if (specialCase.equals("Kind")
								|| specialCase.equals("SubKind")) {
							specialCase = "KindMixin";
						}
					} else if (specialCase.equals("Kind")) {
						specialCase = "SubKind";
					} else {
						specialCase = "Category";
					}
				}
			}
		}

		return createIntersectionDerivedType(specialCase, diagramManager,
				activeEditor, project);
	}

	@SuppressWarnings({ "unused", "rawtypes" })
	private static Fix createIntersectionDerivedType(String stereotype,
			DiagramManager diagramManager, DiagramEditor activeEditor,
			UmlProject project) {
		dman = diagramManager;

		ClassElement ce1 = null;
		ClassElement ce2 = null;
		ArrayList<String> names = new ArrayList<String>();

		int j = 0;
		ArrayList<RefOntoUML.Element> refontoList = new ArrayList<RefOntoUML.Element>();
		for (int i = 0; i < activeEditor.getSelectedElements().size(); i++) {
			if (activeEditor.getSelectedElements().get(i) instanceof ClassElement) {
				j++;
				ce1 = (ClassElement) activeEditor.getSelectedElements().get(i);
				refontoList.add(ce1.getClassifier());
				names.add(ce1.getClassifier().getName());
			}
		}

		ce1 = (ClassElement) activeEditor.getSelectedElements().get(0);
		ce2 = (ClassElement) activeEditor.getSelectedElements().get(1);

		Collection<? extends Connection> c = ce1.getConnections();
		Collection<? extends Connection> c2 = ce2.getConnections();
		List<GeneralizationElement> gen_1 = new ArrayList<GeneralizationElement>();
		List<GeneralizationElement> gen_2 = new ArrayList<GeneralizationElement>();

		for (Connection connection : c) {
			if (connection instanceof GeneralizationElement) {
				gen_1 = new ArrayList<GeneralizationElement>();
				gen_1.add((GeneralizationElement) connection);
			}
		}

		for (Connection connection : c2) {
			if (connection instanceof GeneralizationElement) {
				gen_2 = new ArrayList<GeneralizationElement>();
				gen_2.add((GeneralizationElement) connection);
			}
		}

		for (GeneralizationElement generalizationElement : gen_1) {
			for (GeneralizationElement generalizationElement2 : gen_2) {
				if (generalizationElement.getSpecific() == generalizationElement2
						.getSpecific()) {
					wrongSelection("Intersection Invalid because these types already have an intersection either parcial or total");
					return null;
				}
			}

		}

		String name;
		mainfix = new Fix();
		of = new OutcomeFixer(project.getModel());
		if (stereotype.equals("KindMixin")) {
			Object[] stereo = null;
			ArrayList<String> values = new ArrayList<String>();
			values.add("Role");
			values.add("Phase");
			values.add("SubKind");
			stereo = values.toArray();
			JPanel panel = selectStereotype(stereo);
			if(panel==null){
				return null;
			}
			name = ((JTextField) panel.getComponents()[0]).getText();
			stereotype = ((JComboBox) panel.getComponents()[1])
					.getSelectedItem().toString();
		} else {
			name = DefineNameDerivedType();
			if(name==null){
				return null;
			}
		}
		ArrayList<Point2D.Double> positions = new ArrayList<Point2D.Double>();
		for (DiagramElement select : activeEditor.getSelectedElements()) {
			ClassElement position = (ClassElement) select;
			Point2D.Double newElementPosition = new Point2D.Double(
					position.getAbsoluteX1(), position.getAbsoluteY1());
			positions.add(newElementPosition);

		}
		Point2D.Double newElementPosition = ClassPosition
				.findPositionGeneralizationMember(positions, 1);
		Classifier newElement = includeElement(newElementPosition, name,
				stereotype);
		ArrayList<Classifier> classifiers = new ArrayList<Classifier>();
		for (Element element : refontoList) {
			classifiers.add((Classifier) element);
		}

		String rule = "\ncontext _'" + names.get(0) + "'\n"
				+ "inv: self.allInstances()->forAll( x |  x.oclIsTypeOf(_'"
				+ names.get(1) + "') implies x.oclIsTypeOf(_'" + name + "'))";
		diagramManager.getFrame().getBrowserManager().getProjectBrowser()
				.getOCLDocuments().get(0).addContent(rule);

		createMultipleGeneralizationIntersection(newElement, classifiers);
		mainfix.includeAdded(newElement, newElementPosition.getX(),
				newElementPosition.getY());
		return mainfix;

	}

	@SuppressWarnings("unused")
	public static void createDerivedTypeBySpecialization(String nameBase,
			String nameDerived, String stereotypeBase,
			String stereotypeDerived, String attribute, String typeAttribute,
			DiagramManager dm, Double location) {
		dman = dm;
		of = new OutcomeFixer(dm.getCurrentProject().getModel());
		mainfix = new Fix();
		Classifier newElement_2 = includeElement(location, nameBase,
				stereotypeBase);
		location.y = location.y + 100;
		Classifier newElement = includeElement(location, nameDerived,
				stereotypeDerived);
		createGeneralizationSingle(newElement_2, newElement);
		RefOntoUML.Class classe = (Class) newElement_2;
		mainfix.addAll(of.createAttribute(newElement_2, attribute,
				ClassStereotype.PRIMITIVETYPE, typeAttribute));
		// newElement_2.getAllAttributes().add(e)
		dm.updateOLED(mainfix);
	}

	@SuppressWarnings({ "rawtypes", "unused" })
	public static Fix createSpecializationDerivation(
			DiagramEditor activeEditor, UmlProject project,
			DiagramManager diagramManager) {
		// TODO Auto-generated method stub
		JPanel panel;
		ArrayList<String> values = new ArrayList<String>();
		String name;
		Object[] stereo = null;
		List<DiagramElement> selected = activeEditor.getSelectedElements();
		if (selected.size() == 1) {

			if (selected.get(0) instanceof ClassElement) {
				ClassElement element = (ClassElement) selected.get(0);
				if (element.getClassifier() instanceof RigidSortalClass) {
					values.add("SubKind");
					values.add("Phase");
				} else if (element.getClassifier() instanceof Role
						|| element.getClassifier() instanceof Phase) {
					values.add("Phase");
				} else {
					return null;
				}

				stereo = values.toArray();
				panel = selectStereotype(stereo);
			
				/*
				 * the user canceled the operation
				 */
				if(panel==null){
					return null;
				}
				dman = diagramManager;
				of = new OutcomeFixer(dman.getCurrentProject().getModel());
				mainfix = new Fix();
				Point2D.Double location = new Point2D.Double();
				location.x = element.getAbsoluteX1();
				location.y = element.getAbsoluteY1() + 100;

				Classifier newElement = includeElement(location,
						((JTextField) panel.getComponents()[0]).getText(),
						((JComboBox) panel.getComponents()[1])
								.getSelectedItem().toString());
				createGeneralizationSingle(element.getClassifier(), newElement);
				dman.updateOLED(mainfix);
			} else if (selected.get(0) instanceof AssociationElement) {
				ArrayList<Property> featureList = new ArrayList<Property>();
				ArrayList<Property> properties = new ArrayList<Property>();
				ArrayList<Property> propertiesTarget = new ArrayList<Property>();
				ArrayList<String> options = new ArrayList<String>();

				Association association = ((Association) ((AssociationElement) selected
						.get(0)).getRelationship());

				properties.add((Property) association.getMemberEnd().get(0));
				propertiesTarget.add((Property) association.getMemberEnd().get(
						1));

				Property element = (Property) association.getMemberEnd().get(0);
				OntoUMLParser refparser = diagramManager.getFrame()
						.getBrowserManager().getProjectBrowser().getParser();

				for (RefOntoUML.Property p : refparser
						.getAllInstances(RefOntoUML.Property.class)) {
					if (!properties.contains(p)
							&& !propertiesTarget.contains(p)
							&& ((Classifier) element.getType()).allChildren()
									.contains(p.getType())) {
						featureList.add(p);
						options.add(((NamedElement) p.eContainer()).getName());
					}
				}
				if (options != null) {
					String option = selectRelation(options.toArray());
					for (Property feature : featureList) {
						if (((NamedElement) feature.eContainer()).getName()
								.equals(option)) {
							for (Property prop : properties) {
								feature.getSubsettedProperty().add(prop);
								if (prop.isIsReadOnly()) {
									feature.setIsReadOnly(true);
								}
							}
							for (Property prop : propertiesTarget) {
								feature.getSubsettedProperty().add(prop);
								if (prop.isIsReadOnly()) {
									feature.getOpposite().setIsReadOnly(true);
								}
							}
						}
					}
				}
			}
		}

		return mainfix;
	}

	@SuppressWarnings("unused")
	public static void createDerivedTypeBySpecialization(String nameBase,
			String nameDerived, String stereotypeBase,
			String stereotypeDerived, DiagramManager dm, Double location) {
		// TODO Auto-generated method stub
		dman = dm;
		of = new OutcomeFixer(dm.getCurrentProject().getModel());
		mainfix = new Fix();
		Classifier newElement_2 = includeElement(location, nameBase,
				stereotypeBase);
		location.y = location.y + 100;
		Classifier newElement = includeElement(location, nameDerived,
				stereotypeDerived);
		createGeneralizationSingle(newElement_2, newElement);
		RefOntoUML.Class classe = (Class) newElement_2;

		dm.updateOLED(mainfix);
	}

	public static void createPastSpecializationPattern(DiagramManager dman2,
			Double location, String namesuper, String stereosuper,
			String namespecial, String stereospecial, String namederived,
			String stereoderived, boolean rule) {
		// TODO Auto-generated method stub
		dman = dman2;
		of = new OutcomeFixer(dman2.getCurrentProject().getModel());
		mainfix = new Fix();
		Point2D.Double[] positions = ClassPosition.GSpositioning(2, location);

		Classifier newElement2 = includeElement(positions[2], namespecial,
				stereospecial);
		Classifier newElement3 = includeElement(positions[1], namederived,
				stereoderived);
		if (stereosuper != null && !stereosuper.equals("")) {
			Classifier newElement = includeElement(location, namesuper,
					stereosuper);
			createGeneralizationSingle(newElement, newElement2);
			createGeneralizationSingle(newElement, newElement3);
		}

		if (rule) {
			String rule_ocl = "\ncontext World \ntemp:_'"
					+ namederived
					+ "'.allInstances(self)->forAll( wk | self.allPrevious()->exists(w | wk.oclIsKindOf(_'"
					+ namespecial + "',w)) and not wk.oclIsKindOf("
					+ namespecial + ",self))";
			dman.getFrame().getBrowserManager().getProjectBrowser()
					.getOCLDocuments().get(0).addContent(rule_ocl);
		}

		dman2.updateOLED(mainfix);
	}

	public static void createParticipationUni(DiagramManager dm,
			String name_base, String name_derived, String name_relator,
			String name_target, String stereo_base, String stereo_target,
			Double location) {
		dman = dm;
		of = new OutcomeFixer(dm.getCurrentProject().getModel());
		mainfix = new Fix();

		Classifier newElement = includeElement(location, name_base, stereo_base);
		location.y = location.y + 100;
		Classifier newElement_2 = null;
		if (newElement instanceof Mixin || newElement instanceof RoleMixin
				|| newElement instanceof RefOntoUML.Category) {
			newElement_2 = includeElement(location, name_derived, "RoleMixin");
		} else {
			newElement_2 = includeElement(location, name_derived, "Role");
		}
		createGeneralizationSingle(newElement, newElement_2);
		location.x = location.x + 160;
		Classifier newElement_3 = includeElement(location, name_relator,
				"Relator");
		location.x = location.x + 160;
		Classifier newElement_4 = includeElement(location, name_target,
				stereo_target);
		Fix fix = of.createAssociationBetween(RelationStereotype.MEDIATION, "",
				newElement_2, newElement_3);
		Fix fix2 = of.createAssociationBetween(RelationStereotype.MEDIATION,
				"", newElement_3, newElement_4);
		mainfix.addAll(fix);
		mainfix.addAll(fix2);
		dman.updateOLED(mainfix);
	}

	public static void createParticipationComp(DiagramManager dm,
			String name_base, String name_whole, String name_derived,
			String stereo_base, String stereo_whole, Point2D.Double location) {
		// TODO Auto-generated method stub
		dman = dm;
		of = new OutcomeFixer(dm.getCurrentProject().getModel());
		mainfix = new Fix();

		Classifier newElement = includeElement(location, name_base, stereo_base);
		location.y = location.y + 100;
		Classifier newElement_2 = null;
		if (newElement instanceof Mixin || newElement instanceof RoleMixin
				|| newElement instanceof RefOntoUML.Category) {
			newElement_2 = includeElement(location, name_derived, "RoleMixin");
		} else {
			newElement_2 = includeElement(location, name_derived, "Role");
		}
		createGeneralizationSingle(newElement, newElement_2);
		location.x = location.x + 160;
		Classifier newElement_3 = includeElement(location, name_whole,
				stereo_whole);
		Fix fix;
		if (stereo_whole.equals("Collective")) {
			fix = of.createAssociationBetween(RelationStereotype.MEMBEROF, "",
					newElement_3, newElement_2);
		} else {
			fix = of.createAssociationBetween(RelationStereotype.COMPONENTOF,
					"", newElement_3, newElement_2);
		}
		mainfix.addAll(fix);
		dman.updateOLED(mainfix);
	}

	public static void createParticipationBi(DiagramManager dm,
			String name_base_left, String name_base_right, String name_relator,
			String name_derived_base, String name_derived_right,
			String stereo_base_left, String stereo_base_right,
			Point2D.Double location) {
		// TODO Auto-generated method stub
		dman = dm;
		of = new OutcomeFixer(dm.getCurrentProject().getModel());
		mainfix = new Fix();

		Classifier newElement = includeElement(location, name_base_left,
				stereo_base_left);
		location.y = location.y + 100;
		Classifier newElement_2 = null;
		if (newElement instanceof Mixin || newElement instanceof RoleMixin
				|| newElement instanceof RefOntoUML.Category) {
			newElement_2 = includeElement(location, name_derived_base,
					"RoleMixin");
		} else {
			newElement_2 = includeElement(location, name_derived_base, "Role");
		}
		createGeneralizationSingle(newElement, newElement_2);
		location.x = location.x + 160;
		Classifier newElement_3 = includeElement(location, name_relator,
				"Relator");

		location.y = location.y - 100;
		location.x = location.x + 160;
		Classifier newElement_4 = includeElement(location, name_base_right,
				stereo_base_right);

		Classifier newElement_5;
		location.y = location.y + 100;
		if (newElement_4 instanceof Mixin || newElement_4 instanceof RoleMixin
				|| newElement_4 instanceof RefOntoUML.Category) {
			newElement_5 = includeElement(location, name_derived_base,
					"RoleMixin");
		} else {
			newElement_5 = includeElement(location, name_derived_base, "Role");
		}

		Fix fix = of.createAssociationBetween(RelationStereotype.MEDIATION, "",
				newElement_2, newElement_3);
		Fix fix2 = of.createAssociationBetween(RelationStereotype.MEDIATION,
				"", newElement_3, newElement_5);
		mainfix.addAll(fix);
		mainfix.addAll(fix2);
		createGeneralizationSingle(newElement_4, newElement_5);
		dman.updateOLED(mainfix);
	}

	public static Fix createPastSpecializationDerivation(
			DiagramEditor activeEditor, UmlProject project,
			DiagramManager diagramManager) {
		mainfix = new Fix();
		dman = diagramManager;
		of = new OutcomeFixer(diagramManager.getCurrentProject().getModel());
		/*
		 * if the element selected it is neither a role or phase it is a wrong
		 * selection
		 */
		ClassElement ce = (ClassElement) activeEditor.getSelectedElements()
				.get(0);
		if (!(ce.getClassifier() instanceof Role
				|| ce.getClassifier() instanceof Phase || ce.getClassifier() instanceof RoleMixin)) {
			wrongSelection("Only Role, Phase and Role Mixin have past specialization!");
		} else {
			if (ce.getClassifier() instanceof RoleMixin) {
				Point2D.Double location = new Point2D.Double();
				location.x = ce.getAbsoluteX1() + 100;
				location.y = ce.getAbsoluteY1();
				String name = DefineNameDerivedType();
				if(name==null){
					return null;
				}
				includeElement(location, name, "RoleMixin");
				String rule_ocl = "\ncontext World \ntemp:_'"
						+ name
						+ "'.allInstances(self)->forAll( wk | self.allPrevious()->exists(w | wk.oclIsKindOf(_'"
						+ ce.getClassifier().eClass().getName()
						+ "',w)) and not wk.oclIsKindOf("
						+ ce.getClassifier().eClass().getName() + ",self))";
				dman.getFrame().getBrowserManager().getProjectBrowser()
						.getOCLDocuments().get(0).addContent(rule_ocl);
			} else {
				JDialog dialog = new PastSpecializationDiagram(ce);
				dialog.setVisible(true);
				diagramManager.setCenterDialog(dialog);
			}
		}

		dman.updateOLED(mainfix);
		return mainfix;
	}

	public static void createPastSpecializationDerivation() {
		Point2D.Double location = new Point2D.Double();
		ClassElement ce = PastSpecializationDiagram.getCe();
		location.x = ce.getAbsoluteX1() + 150;
		location.y = ce.getAbsoluteY1();
		String name = PastSpecializationDiagram.getTxt_past();
		Classifier c = includeElement(location, name, ce.getClassifier()
				.eClass().getName());
		location.x = ce.getAbsoluteX1() + 75;
		location.y = ce.getAbsoluteY1() - 75;
		name = PastSpecializationDiagram.getTxt_super();
		Classifier c2 = includeElement(location, name,
				PastSpecializationDiagram.getCmb_stereotype());
		createGeneralizationSingle(c2, c);
		createGeneralizationSingle(c2, ce.getClassifier());
		String rule_ocl = "\ncontext World \ntemp:_'"
				+ PastSpecializationDiagram.getTxt_past()
				+ "'.allInstances(self)->forAll( wk | self.allPrevious()->exists(w | wk.oclIsKindOf(_'"
				+ ce.getClassifier().eClass().getName()
				+ "',w)) and not wk.oclIsKindOf("
				+ ce.getClassifier().eClass().getName() + ",self))";
		dman.getFrame().getBrowserManager().getProjectBrowser()
				.getOCLDocuments().get(0).addContent(rule_ocl);
		dman.updateOLED(mainfix);

	}
	
	
	

	public static void setOf(OutcomeFixer of) {
		DerivedTypesOperations.of = of;
	}

	public static void setDman(DiagramManager dman) {
		DerivedTypesOperations.dman = dman;
	}

	public static void setMainfix(Fix mainfix) {
		DerivedTypesOperations.mainfix = mainfix;
	}

	public static Classifier getUnionDerived() {
		return unionDerived;
	}

	public static boolean verifyHierarquicalProblem(DiagramEditor activeDiagram){
		OntoUMLParser parser = ProjectBrowser.frame.getProjectBrowser()
				.getParser();
		ArrayList<DiagramElement> list= (ArrayList<DiagramElement>) activeDiagram.getSelectedElements();
		ArrayList<Classifier> list_classifier = new ArrayList<Classifier>();
		
		for (DiagramElement element : list) {
			list_classifier.add(((ClassElement)element).getClassifier());
		}
		
		for (DiagramElement diagramElement : list) {
			Classifier classifier= (((ClassElement)diagramElement).getClassifier());
			ArrayList<Classifier> parents = new ArrayList<Classifier>(
					parser.getAllParents(classifier));
			for (Classifier father : parents) {
				if(list_classifier.contains(father)){
					return false;
				}
			}
		}
		
		return true;
	}

	
	
	
}

	
