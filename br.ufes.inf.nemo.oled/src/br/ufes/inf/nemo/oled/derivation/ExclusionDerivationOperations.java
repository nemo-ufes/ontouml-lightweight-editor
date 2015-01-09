package br.ufes.inf.nemo.oled.derivation;

import java.awt.Component;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.query.statements.UPDATE;
import org.eclipse.swt.internal.ole.win32.COMObject;

import com.sun.xml.internal.ws.api.server.Container;

import sun.reflect.generics.tree.BaseType;
import RefOntoUML.Classifier;
import RefOntoUML.Element;
import RefOntoUML.Generalization;
import RefOntoUML.PackageableElement;
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
import br.ufes.inf.nemo.oled.util.ModelHelper;

public class ExclusionDerivationOperations {
	static OutcomeFixer of;
	static Fix mainfix;
	static DiagramManager dman;
	static boolean gs_with_nonsortal_or_kind = false;
	static Point2D.Double pointClicked;
	static boolean general_existent=false;
	static HashSet<Classifier> exclusionDerivationList= new HashSet<Classifier>();
	
	public static void createExclusionDerivation(
			DiagramEditor activeEditor, UmlProject project, DiagramManager dm,
			List<DiagramElement> list, OutcomeFixer outf) {
		
		Collection<DiagramElement> exclusionElements= project.getExclusionDerivationList();
		if(!DerivedTypesOperations.verifyHierarquicalProblem(activeEditor)){
			StereotypeAndNameSelection.wrongSelection("Invalid Selection");
			return;
		}
		
		if(exclusionElements!=null){
			
			for (DiagramElement classElement : exclusionElements) {
				exclusionDerivationList.add(((ClassElement)classElement).getClassifier());
			}
		}
		dman = dm;
		of = outf;
		Fix fix = new Fix();
		mainfix = fix;
		DerivedTypesOperations.setDman(dm);
		DerivedTypesOperations.setMainfix(mainfix);
		/*
		 * verify if it is a right selection
		 * 
		 */
		for (DiagramElement diagramElement : list) {
			if(!(diagramElement instanceof ClassElement)){
				StereotypeAndNameSelection.wrongSelection("Wrong Selection, You must select only Object Types");
				return;
			}
		}
		
		/* 
		 * verify the amount of elements selected and then call the right method
		 */
		
		if(list.size()>1){
			deriveByMultipleSelection(list, activeEditor, project, dm);
		}else{
			deriveBySingleSelection( ((ClassElement)list.get(0)).getClassifier(), new Point2D.Double(((ClassElement)list.get(0)).getAbsoluteX1(), ((ClassElement)list.get(0)).getAbsoluteY1()));
		}
		ArrayList<Element> elements = new ArrayList<Element>();
		elements.addAll(exclusionDerivationList);
		Collection<DiagramElement> col= ModelHelper.getDiagramElements(elements);
		if(col!=null){
			project.setExclusionDerivationList(col);
		}
	}

	
	private static void deriveBySingleSelection(Classifier c, Point2D.Double point){
		Classifier general=null;
		Classifier exclusion;
		boolean has_sortal_superclass = false;
		OntoUMLParser parser = ProjectBrowser.frame.getProjectBrowser()
				.getParser();
		
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
			general=createGeneralElement(has_sortal_superclass, c, point);
			if(general==null){
				return;
			}
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
							"The Exclusion derivation needs a supertype, do you want to use a existent supertype of this class?",
							"WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			    String input = (String) JOptionPane.showInputDialog(null, "Choose one of the supertypes",
			        "Supertype of the Exclusion Derivation", JOptionPane.QUESTION_MESSAGE, null, 
			        exitent_supertypes.toArray(), // Array of choices
			        exitent_supertypes.get(0)); // Initial choice
			    	
			    	if(input!=null){
			    		general= parents.get(exitent_supertypes.indexOf(input));
			    		general_existent=true;
			    	}
			    
			} else {
					general=createGeneralElement(has_sortal_superclass, c, point);
					// generalizations.add();
			}
		}
		// user canceled
		if(general==null){
			return;
		}
		exclusion = createDerivedElement(has_sortal_superclass, c,point , general);
		if(exclusion==null){
			return;
		}
		createGeneralizations(exclusion, general, c);
		
		String rule="\ncontext: _'"+general.getName().toString()+"'\n"+"inv: not oclIsTypeOf(_'"+c.getName().toString()+"') implies oclIsTypeOf(_'"+exclusion.getName().toString()+"')";
		dman.getFrame().getBrowserManager().getProjectBrowser().getOCLDocuments().get(0).addContent(rule);
		exclusionDerivationList.add(c);
		exclusionDerivationList.add(exclusion);
	
		dman.updateOLED(mainfix);
		
	}
	
	private static void deriveByMultipleSelection(List<DiagramElement> elements,DiagramEditor activeEditor, UmlProject project, DiagramManager dm){
		OntoUMLParser parser = ProjectBrowser.frame.getProjectBrowser()
				.getParser();
		boolean common_super=false;
		Classifier common_father= null;
		
		HashMap<Classifier, HashSet<Classifier>> filho_pais = new HashMap<Classifier, HashSet<Classifier>>();
		ArrayList<Classifier> types= new ArrayList<Classifier>();
		DiagramElement father = null;
		
		/*
		 * verify if it has a common supertype
		 */
		
		for (DiagramElement diagramElement : elements) {
			 types.add(((ClassElement) diagramElement).getClassifier());
		}
		
		for (Classifier classifier : types) {
			HashSet<Classifier> parents = new HashSet<Classifier>(parser.getAllParents(classifier));
			filho_pais.put(classifier, parents);
		}
		
		HashSet<Classifier> parents = filho_pais.get(types.get(0));
		for (Classifier parent : parents) {
			int occurrency=1;
			for (Classifier type : types) {
				if(type!= types.get(0)){
					if(filho_pais.get(type).contains(parent)){
						occurrency++;
					}
				}
				
			}
			if(occurrency==types.size()){
				common_father = parent;
				common_super=true;
			}
			
		}
		
		father=getClassElementFromClassifier(common_father, activeEditor);
		
		if(common_super){
			StereotypeAndNameSelection.wrongSelection("The derivation by exclusion of n types is actually the exclusion of the union of the types selected");
			deriveBySingleSelection(common_father, new Point2D.Double(((ClassElement) father).getAbsoluteX1(), ((ClassElement) father).getAbsoluteY1()));
		}else{
			StereotypeAndNameSelection.wrongSelection("The derivation by exclusion of n types is actually the exclusion of the union of the types selected, you need to create the derivation by union");
			Fix fix=DerivedTypesOperations.createUnionDerivation(activeEditor, project, dm);
			common_father=DerivedTypesOperations.getUnionDerived();
			dm.updateOLED(fix);
			father= getClassElementFromClassifier(common_father, activeEditor);
			deriveBySingleSelection(common_father, new Point2D.Double(((ClassElement) father).getAbsoluteX1(), ((ClassElement) father).getAbsoluteY1()));
			
		}
		
	}
	
	public  static ClassElement getClassElementFromClassifier(Classifier classifier, DiagramEditor activeEditor){
		for(DiagramElement dElem: activeEditor.getDiagram().getChildren()){
			if(dElem instanceof ClassElement){
				Classifier c2= ((ClassElement)dElem).getClassifier();
				if(c2==classifier){
					return (ClassElement) dElem;
				}
			}
		}
		return null;
	}
	
	private static boolean isDerivedbyExclusion(Classifier element){
		if(exclusionDerivationList.contains(element)){
			return true;
		}
		return false;
		
	}
	
	private static Classifier createGeneralElement(boolean has_sortal_superclass, Classifier c, Point2D.Double point){
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
		
		if(panel==null){
			return null;
		}
		Type supertype = new Type();
		
		supertype.setName(((JTextField) panel.getComponents()[0]).getText());
		supertype.setStereotype(((JComboBox) panel.getComponents()[1])
				.getSelectedItem().toString());
		Classifier pai = DerivedTypesOperations.includeElement(
				new Point2D.Double(point.getX() + 100,
						point.getY() - 100), supertype.getName(),
				supertype.getStereotype(), of);
		return pai;
	}
	
	private static Classifier createDerivedElement(boolean has_sortal_superclass, Classifier c, Point2D.Double point, Classifier general){
		ArrayList<String> stereotypes2 = DerivedByExclusion.getInstance()
				.inferStereotype(general.eClass().getName(), c.eClass().getName());
		
		Object[] stereo2;
		stereo2 = stereotypes2.toArray();
		String name2 = null;
		String stereotype2 = null;
		
		if(stereotypes2.size()>1){
			JPanel panel2 = StereotypeAndNameSelection.selectStereotype(stereo2,
					"Define the Exclusion");
			if(panel2==null){
				return null;
			}
			name2 = ((JTextField) panel2.getComponents()[0])
					.getText();
			stereotype2 = ((JComboBox) panel2.getComponents()[1])
					.getSelectedItem().toString();
		}else{
			stereotype2 = stereotypes2.get(0);
			name2= StereotypeAndNameSelection.defineNameDerivedType();
		}
		
		Classifier exclusion = DerivedTypesOperations.includeElement(
				(new Point2D.Double(point.getX() + 200,point.getY())), name2,
				stereotype2, of);
		return exclusion;
	}

	private static void createGeneralizations(Classifier exclusion, Classifier general, Classifier base){
		ArrayList<Generalization> generalizations = new ArrayList<Generalization>();
		Fix fix1 = of.createGeneralization(exclusion, general);
		generalizations
		.add((Generalization) fix1.getAdded().get(0));
		if(!general_existent){
			Fix fix2 = of.createGeneralization(base, general);
			generalizations
					.add((Generalization) fix2.getAdded().get(0));
		}
		Fix gs = of.createGeneralizationSet(generalizations, true,
				true);
		mainfix.addAll(gs);
		return;
	}
}

	