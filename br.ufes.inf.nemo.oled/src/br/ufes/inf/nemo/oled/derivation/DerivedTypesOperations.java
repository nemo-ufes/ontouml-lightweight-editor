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

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer.ClassStereotype;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer.RelationStereotype;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.common.positioning.ClassPosition;
import br.ufes.inf.nemo.derivedtypes.DerivedByExclusion;
import br.ufes.inf.nemo.derivedtypes.DerivedByUnion;
import br.ufes.inf.nemo.oled.DiagramManager;
import br.ufes.inf.nemo.oled.draw.DiagramElement;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.ui.diagram.DiagramEditor;
import br.ufes.inf.nemo.oled.umldraw.structure.AssociationElement;
import br.ufes.inf.nemo.oled.umldraw.structure.ClassElement;
import br.ufes.inf.nemo.oled.umldraw.structure.GeneralizationElement;

/**
 * @author Cássio Reginato
 */
public class DerivedTypesOperations {

	
	static Point2D.Double pointClicked;
	
	public static void setPointClicked(Point2D.Double pointClicked) {
		DerivedTypesOperations.pointClicked = pointClicked;
	}


	@SuppressWarnings("unused")
	private class FeatureElement {
		RefOntoUML.Element element;
		OntoUMLParser ref;
		public FeatureElement(RefOntoUML.Element element, OntoUMLParser refparser) 
		{
			ref= refparser;
			this.element = element;
		}

		public Element getElement() { return element; }

		@Override
		public String toString(){
			String result = new String();

			if (element instanceof RefOntoUML.Property)
			{
				Property p = (Property)element;
				String owner = new String();
				if(p.getAssociation()==null){
					owner = ""+ref.getStereotype(p.eContainer())+" "+((NamedElement)p.eContainer()).getName();
				}else{
					owner = ""+ref.getStereotype(p.getAssociation())+" "+((NamedElement)p.getAssociation()).getName();
				}
				result += "Property "+p.getType().getName()+": ("+p.getName()+") ["+p.getLower()+","+p.getUpper()+"] "+" (owner: "+owner+")";						  				
			}

			return result;
		}
	}

	static OutcomeFixer of;
	static Fix mainfix;
	static DiagramManager dman;
	static boolean gs_with_nonsortal_or_kind=false;

	@SuppressWarnings("unused")
	public static Fix createUnionDerivation(DiagramEditor activeEditor, UmlProject project, DiagramManager dm){

		Fix mainfix = new Fix();
		List<DiagramElement> selected = activeEditor.getSelectedElements();
		ArrayList<RefOntoUML.Element> refontoList = new ArrayList<RefOntoUML.Element>();
		int j=0;
		for (int i = 0; i < selected.size(); i++) {
			if (selected.get(i) instanceof ClassElement) {
				j++;
				ClassElement ce = (ClassElement) selected.get(i);
				refontoList.add(ce.getClassifier());
			}		
		}
		if(selected.size()==2  && selected.get(0) instanceof ClassElement && selected.get(1) instanceof ClassElement ){			
			if(refontoList.size()==2){

				ArrayList<String>stereotypes= DerivedByUnion.getInstance().inferStereotype(refontoList.get(0).eClass().getName(), refontoList.get(1).eClass().getName());
				if(stereotypes!=null)
					if(stereotypes.size()<2){
						String name=DefineNameDerivedType();
						mainfix =createDerivedTypeUnion(stereotypes.get(0), mainfix, selected,name,refontoList,project,dm);			
					}
					else{
						Object[] stereo;
						stereo=  stereotypes.toArray();
						String name=DefineNameDerivedType();
						String stereotype= selectStereotype(stereo);
						mainfix= createDerivedTypeUnion(stereotype, mainfix, selected,name,refontoList,project,dm);
					}
			}

		}
		else{
			String stereotype=null;
			String specialCase=multipleElementsUnionDerivation(selected);
			String specialCaseRelation=tryunionassociationderivation(selected,dm);

			if(specialCase.equals("")){
				wrongSelection("Wrong Selection");
				return null;
			}

			if(specialCaseRelation.equals("union_relation_derivation")){
				return null;
			}

			String name=DefineNameDerivedType();
			if(specialCase=="Rigid+NonRigid"){
				stereotype= "Mixin";
				//mainfix= createDerivedTypeUnion(stereotype, mainfix, selected,name,refontoList,project,dm);
			}else{
				if(specialCase=="SemiRigid+Non-Rigid"){
					Object[] stereo;
					ArrayList<String>stereotypes = new ArrayList<String>();
					stereotypes.add("Category");
					stereotypes.add("RoleMixin");
					stereo=  stereotypes.toArray();
					stereotype= selectStereotype(stereo);
					//mainfix= createDerivedTypeUnion(stereotype, mainfix, selected,name,refontoList,project,dm);
				}
			}
			if(gs_with_nonsortal_or_kind){
				if(specialCase=="AllRigid"){
					stereotype= "Category";
					//mainfix= createDerivedTypeUnion(stereotype, mainfix, selected,name,refontoList,project,dm);
				}else{
					if(specialCase=="AllAntiRigid"){
						stereotype= "RoleMixin";
						//mainfix= createDerivedTypeUnion(stereotype, mainfix, selected,name,refontoList,project,dm);
					}
				}
			}else{
				if(specialCase=="AllRigid"){
					Object[] stereo;
					ArrayList<String>stereotypes = new ArrayList<String>();
					stereotypes.add("Kind");
					stereotypes.add("Collective");
					stereotypes.add("Quantity");
					stereotypes.add("Subkind");
					stereotypes.add("Category");
					stereo=  stereotypes.toArray();
					stereotype= selectStereotype(stereo);
					//mainfix= createDerivedTypeUnion(stereotype, mainfix, selected,name,refontoList,project,dm);
				}else{
					if(specialCase=="AllAntiRigid"){
						Object[] stereo;
						ArrayList<String>stereotypes = new ArrayList<String>();
						stereotypes.add("Kind");
						stereotypes.add("Collective");
						stereotypes.add("Quantity");
						stereotypes.add("Subkind");
						stereotypes.add("Role");
						stereotypes.add("Phase");
						stereotypes.add("RoleMixin");
						stereo=  stereotypes.toArray();
						stereotype= selectStereotype(stereo);
						//mainfix= createDerivedTypeUnion(stereotype, mainfix, selected,name,refontoList,project,dm);
					}
				}
			}
			mainfix= createDerivedTypeUnion(stereotype, mainfix, selected,name,refontoList,project,dm);
			//wrongSelection("Wrong Selection, check the documentation");
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
			if(!(sel instanceof AssociationElement))
				return "";
			associations.add((Association) ((AssociationElement)sel).getRelationship());
		}

		for (Association association : associations) {
			properties.add((Property) association.getMemberEnd().get(0));
			propertiesTarget.add((Property) association.getMemberEnd().get(1));
		}

		Property element = (Property) associations.get(0).getMemberEnd().get(0);		
		OntoUMLParser refparser= diagramManager.getFrame().getBrowserManager().getProjectBrowser().getParser();


		for(RefOntoUML.Property p : refparser.getAllInstances(RefOntoUML.Property.class)) 
		{
			if(!properties.contains(p) && !propertiesTarget.contains(p) && ((Classifier)element.getType()).allParents().contains(p.getType())){
				featureList.add(p);
				options.add(((NamedElement)p.eContainer()).getName());
			}
		}
		String option =selectRelation(options.toArray());
		for (Property feature : featureList) {
			if(((NamedElement)feature.eContainer()).getName().equals(option)){
				for (Property prop : properties) {
					prop.getSubsettedProperty().add(feature);
					prop.setIsDerivedUnion(true);
				}

			}
		}
		return "union_relation_derivation";

	}




	private static String multipleElementsUnionDerivation(
			List<DiagramElement> selected) {
		//String anterior="";
		String specialCase="AllRigid";
		DiagramElement diagramElement=selected.get(0);
		List<DiagramElement> selected2 = new ArrayList<DiagramElement>();
		selected2.addAll(selected);
		selected2.remove(0);
		if(!(diagramElement instanceof ClassElement)){
			return "";
		}else{
			ClassElement ce = (ClassElement) diagramElement;
			if(ce.getClassifier() instanceof RigidMixinClass || ce.getClassifier() instanceof RigidSortalClass){
				for (DiagramElement otherelement : selected2) {
					ce = (ClassElement) otherelement;
					if((ce.getClassifier() instanceof RigidMixinClass || ce.getClassifier() instanceof AntiRigidMixinClass || ce.getClassifier() instanceof SemiRigidMixinClass || ce.getClassifier() instanceof Kind )){
						gs_with_nonsortal_or_kind= true;
					}
					if(!(ce.getClassifier() instanceof RigidMixinClass || ce.getClassifier() instanceof RigidSortalClass)){
						specialCase="Rigid+NonRigid";
						break;
					}
				}	
			}else{
				if(ce.getClassifier() instanceof SemiRigidMixinClass){
					for (DiagramElement otherelement : selected2) {
						ce = (ClassElement) otherelement;
						if((ce.getClassifier() instanceof RigidMixinClass || ce.getClassifier() instanceof AntiRigidMixinClass || ce.getClassifier() instanceof SemiRigidMixinClass || ce.getClassifier() instanceof Kind)){
							gs_with_nonsortal_or_kind= true;
						}
						if((ce.getClassifier() instanceof RigidMixinClass || ce.getClassifier() instanceof RigidSortalClass || ce.getClassifier() instanceof Kind )){
							specialCase="Rigid+NonRigid";
							break;
						}
					}
					specialCase="SemiRigid+Non-Rigid";
				}else{
					for (DiagramElement otherelement : selected2) {
						ce = (ClassElement) otherelement;
						if((ce.getClassifier() instanceof RigidMixinClass || ce.getClassifier() instanceof AntiRigidMixinClass || ce.getClassifier() instanceof SemiRigidMixinClass || ce.getClassifier() instanceof Kind)){
							gs_with_nonsortal_or_kind= true;
						}
						if(ce.getClassifier() instanceof RigidMixinClass || ce.getClassifier() instanceof RigidSortalClass || ce.getClassifier() instanceof Kind ){
							specialCase="Rigid+NonRigid";
							break;
						}
					}
					specialCase="AllAntiRigid";
				}
			}
		}
		return specialCase;
	}


	@SuppressWarnings("unused")
	public static Fix createDerivedTypeUnion(String stereotype, Fix fix, List<DiagramElement> selected, String name, ArrayList<RefOntoUML.Element> refontoList, UmlProject project, DiagramManager dm){
		dman = dm;
		mainfix = fix;
		//UmlProject project = getCurrentEditor().getProject();
		if(selected.size()<3){
			of = new OutcomeFixer(project.getModel());
			
			Point2D.Double firstpoint = new Point2D.Double();
			Point2D.Double secondpoint = new Point2D.Double();
			ClassElement position = (ClassElement) selected.get(0);
			ClassElement position2 = (ClassElement) selected.get(1);
			firstpoint.setLocation(position.getAbsoluteX1(),position.getAbsoluteY1());
			secondpoint.setLocation(position2.getAbsoluteX1(),position2.getAbsoluteY1());
			Point2D.Double newElementPosition= ClassPosition.findPositionGeneralization(firstpoint, secondpoint);
			Classifier newElement = includeElement(newElementPosition, name, stereotype);
			createGeneralization(newElement, (Classifier)refontoList.get(0), (Classifier)refontoList.get(1));
			mainfix.includeAdded(newElement, newElementPosition.getX(),newElementPosition.getY());

		}else{
			of = new OutcomeFixer(project.getModel());
			
			Point2D.Double firstpoint = new Point2D.Double();
			Point2D.Double secondpoint = new Point2D.Double();
			ArrayList<Point2D.Double> positions= new ArrayList<Point2D.Double>();
			for (DiagramElement select : selected) {
				ClassElement position = (ClassElement) select;
				Point2D.Double newElementPosition= new Point2D.Double(position.getAbsoluteX1(), position.getAbsoluteY1());
				positions.add( newElementPosition);
			}
			Point2D.Double newElementPosition= ClassPosition.findPositionGeneralizationMember(positions,2);
			Classifier newElement = includeElement(newElementPosition, name, stereotype);
			ArrayList<Classifier> classifiers= new ArrayList<Classifier>();
			for (Element element : refontoList) {
				classifiers.add((Classifier)element);
			}
			createMultipleGeneralization(newElement, classifiers);
			mainfix.includeAdded(newElement, newElementPosition.getX(),newElementPosition.getY());
		}
		return mainfix;
	}

	private static Fix createDerivedTypeExclusion(String stereotype, Fix mainfix, List<DiagramElement> selected, String name, ClassElement pai, ClassElement filho,GeneralizationElement generalizationElement, UmlProject project){		//UmlProject project = getCurrentEditor().getProject();
		OutcomeFixer of = new OutcomeFixer(project.getModel());
		Classifier newElement = (Classifier)of.createClass( of.getClassStereotype(stereotype));
		newElement.setName(name);
		of.copyContainer(((ClassElement) selected.get(0)).getClassifier(), newElement);
		Fix fix=of.createGeneralization(newElement,pai.getClassifier());
		ArrayList<Generalization> generalizations = new ArrayList<Generalization>();
		generalizations.add((Generalization) fix.getAdded().get(0));
		generalizations.add(generalizationElement.getGeneralization());
		Fix gs =  of.createGeneralizationSet(generalizations,true, true);
		//mainfix.addAll(fixG2);
		mainfix.addAll(fix);
		mainfix.addAll(gs);
		ClassElement position = pai;
		ClassElement position2 = filho;
		Point2D.Double firstpoint = new Point2D.Double();
		Point2D.Double secondpoint = new Point2D.Double();
		firstpoint.setLocation(position.getAbsoluteX1(),position.getAbsoluteY1());
		secondpoint.setLocation(position2.getAbsoluteX1(),position2.getAbsoluteY1());
		Point2D.Double newElementPosition= ClassPosition.findPositionGeneralizationMember(firstpoint, secondpoint);
		mainfix.includeAdded(newElement, newElementPosition.getX(),newElementPosition.getY());
		return mainfix;

	}

	public static Fix createExclusionDerivation(DiagramEditor activeEditor,
			UmlProject project, DiagramManager dm) {
		// TODO Auto-generated method stub
		Fix mainfix = new Fix();
		List<DiagramElement> selected = activeEditor.getSelectedElements();
		List<ClassElement> classList = new ArrayList<ClassElement>();
		List<GeneralizationElement> gen = new ArrayList<GeneralizationElement>();
		ArrayList<RefOntoUML.Element> refontoList = new ArrayList<RefOntoUML.Element>();
		int pos=0;
		int pos2=1;
		ArrayList<String> stereotypes = new ArrayList<String>();
		if(selected.size()==3){
			for (int i = 0; i < selected.size(); i++) {
				if (selected.get(i) instanceof ClassElement) {
					classList.add((ClassElement) selected.get(i));
					refontoList.add((Element) ((ClassElement) selected.get(i)).getClassifier());
				}
				if (selected.get(i) instanceof GeneralizationElement) {
					GeneralizationElement ge = (GeneralizationElement) selected.get(i);
					Element e= ge.getGeneralization().getGeneral();
					gen.add((GeneralizationElement) ge);
					//refontoList.add((Element) selected.get(i));
					if(refontoList.size()>1){
						if(e.equals(refontoList.get(1))){
							pos=1;
							pos2=0;
						}
					}
				}
			}

			if(gen.size()==1 && classList.size()==2){
				if(pos==1){
					stereotypes= DerivedByExclusion.getInstance().inferStereotype(refontoList.get(pos).eClass().getName() , refontoList.get(pos2).eClass().getName());
					pos2=0;
				}else{
					stereotypes= DerivedByExclusion.getInstance().inferStereotype(refontoList.get(pos).eClass().getName() , refontoList.get(pos2).eClass().getName());
					pos2=1;
				}
				if(stereotypes!=null){
					String name = DefineNameDerivedType();
					//DerivedByExclusion.getInstance().createExclusionRule(((Classifier) refontoList.get(pos)).getName(), ((Classifier) refontoList.get(pos2)).getName(), name);
					if(!(refontoList.get(pos2).eClass().getName().equals("Role") && (refontoList.get(pos).eClass().getName().equals("Kind")) ))
					{
						String rule="\n context: _'"+((Classifier) refontoList.get(pos)).getName()+"'\n"+"inv: not oclIsTypeOf(_'"+((Classifier) refontoList.get(pos2)).getName()+"') implies oclIsTypeOf(_'"+name+"')";
						dm.getFrame().getBrowserManager().getProjectBrowser().getOCLDocuments().get(0).addContent(rule);						
					}	
					//String rule="context: "
					if(stereotypes.size()==1)
						return createDerivedTypeExclusion(stereotypes.get(0), mainfix, selected, name, classList.get(pos), classList.get(pos2),gen.get(0), project);
					else{
						Object[] stereo;
						stereo=  stereotypes.toArray();
						String stereotype= selectStereotype(stereo);
						return createDerivedTypeExclusion(stereotype, mainfix, selected, name, classList.get(pos), classList.get(pos2),gen.get(0), project);
					}
				}
			}
			else{
				wrongSelection("Wrong Selection, check the documentation");
			}

		}
		else{
			wrongSelection("Wrong Selection, check the documentation");
		}
		return null;
	}


	@SuppressWarnings("unused")
	public static String DefineNameDerivedType(){

		JFrame frame = new JFrame("InputDialog Example #1");

		// prompt the user to enter their name
		String name="";
		while(name==""){
			name = JOptionPane.showInputDialog(null, "What's the New Type Name", "Name Type", 1);    
		}
		return name;
	}

	public  static  String selectStereotype(Object[] stereo) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("Input Dialog Example 3");
		String stereotype = (String) JOptionPane.showInputDialog(frame, 
				"Choose between the possible options ?",
				"New Type Derived By Union",
				JOptionPane.QUESTION_MESSAGE, 
				null, 
				stereo, 
				stereo[0]);
		return stereotype;

	}

	public  static  String selectRelation(Object[] stereo) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("Input Dialog Example 3");
		String stereotype = (String) JOptionPane.showInputDialog(frame, 
				"Choose between the possible relations ?",
				"Union Derivation Relation",
				JOptionPane.QUESTION_MESSAGE, 
				null, 
				stereo, 
				stereo[0]);
		return stereotype;

	}


	public static void wrongSelection(String message){
		JFrame frame = new JFrame("InputDialog Example #1");
		JOptionPane.showMessageDialog(frame, message);

	}

	public static void UnionPattern(DiagramManager dm, ArrayList<String> values, Point2D.Double location){
		dman=dm;
		of = new OutcomeFixer(dm.getCurrentProject().getModel());
		mainfix = new Fix();
		Point2D.Double[] positions= ClassPosition.GSpositioning(2, location);
		Classifier newElement= includeElement(positions[1], values.get(2), values.get(0));
		Classifier newElement2= includeElement(positions[2], values.get(3), values.get(1));
		ArrayList<String> stereotypes= DerivedByUnion.getInstance().inferStereotype(newElement.eClass().getName() , newElement2.eClass().getName());
		Classifier newElement3=null;
		if(stereotypes!=null){
			if(stereotypes.size()==1){
				newElement3 = includeElement(location, values.get(4), stereotypes.get(0));
			}
			else{
				Object[] stereo;
				stereo=  stereotypes.toArray();
				String stereotype= selectStereotype(stereo);
				newElement3 = includeElement(location, values.get(4), stereotype);
			}
			createGeneralization(newElement3, newElement2, newElement);
			System.out.println(mainfix.toString());
			dm.updateOLED(mainfix);	
		}
	}
	//include an element acoording its position name and category
	public static Classifier includeElement(Point2D.Double position, String name, String stereotype){
		Classifier newElement= (Classifier) of.createClass(of.getClassStereotype(stereotype));
		dman.getCurrentProject().getModel().getPackagedElement().add(newElement);
		newElement.setName(name);
		mainfix.includeAdded(newElement, position.getX(),position.getY());
		return newElement;
	}

	public  static void createGeneralization(Classifier father, Classifier son1, Classifier son2){
		Fix fix=of.createGeneralization(son1, father);
		Fix fixG2=of.createGeneralization(son2, father);
		ArrayList<Generalization> generalizations = new ArrayList<Generalization>();
		generalizations.add((Generalization) fix.getAdded().get(0));
		generalizations.add((Generalization) fixG2.getAdded().get(0));
		Fix gs =  of.createGeneralizationSet(generalizations,true, true);
		mainfix.addAll(fixG2);
		mainfix.addAll(fix);
		mainfix.addAll(gs);
	}

	public  static void createGeneralizationSingle(Classifier father, Classifier son1){
		Fix fix=of.createGeneralization(son1, father);
		mainfix.addAll(fix);

	}

	public  static void createMultipleGeneralization(Classifier father, ArrayList<Classifier> sons){
		ArrayList<Generalization> generalizations = new ArrayList<Generalization>();
		for (Classifier classifier : sons) {
			Fix fix=of.createGeneralization(classifier, father);
			generalizations.add((Generalization) fix.getAdded().get(0));
			mainfix.addAll(fix);
		}

		Fix gs =  of.createGeneralizationSet(generalizations,true, true);
		mainfix.addAll(gs);
	}
	public  static void createMultipleGeneralizationIntersection(Classifier son, ArrayList<Classifier> parents){
		ArrayList<Generalization> generalizations = new ArrayList<Generalization>();
		for (Classifier classifier : parents) {
			Fix fix=of.createGeneralization(son, classifier);
			generalizations.add((Generalization) fix.getAdded().get(0));
			mainfix.addAll(fix);
		}
	}


	public static void exclusionPattern(DiagramManager dman2,
			ArrayList<String> values, Double location) {
		// TODO Auto-generated method stub
		dman=dman2;
		of = new OutcomeFixer(dman2.getCurrentProject().getModel());
		mainfix = new Fix();
		Point2D.Double[] positions= ClassPosition.GSpositioning(2, location);
		Classifier newElement= includeElement(location, values.get(3), values.get(0));
		Classifier newElement2= includeElement(positions[2], values.get(4), values.get(1));
		Classifier newElement3 = includeElement(positions[1], values.get(5), values.get(2));
		createGeneralization(newElement, newElement2, newElement3);
		dman2.updateOLED(mainfix);	

	}


	public static void intersectionPattern(DiagramManager dm, String base_1_name,
			String base_2_name, String derived_name, Double location, String base_1_stereo, String stereo_base_2_stereo, String derived_stereo) {
		// TODO Auto-generated method stub
		  dman= dm;
          of = new OutcomeFixer(dm.getCurrentProject().getModel());
          mainfix = new Fix();
          Point2D.Double[] positions= ClassPosition.GSpositioningDown(2, location);
          Classifier newElement= includeElement(positions[1],base_1_name, base_1_stereo);
          Classifier newElement2= includeElement(positions[2], base_2_name, stereo_base_2_stereo);
          Classifier newElement3 = includeElement(positions[0], derived_name, derived_stereo);
          createGeneralizationSingle(newElement,newElement3);
          createGeneralizationSingle(newElement2,newElement3);
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
		String specialCase="";
		for (DiagramElement element : selected) {
			if (element instanceof ClassElement) {
				classList.add((ClassElement) element);
				refontoList.add((Element) ((ClassElement) element).getClassifier());
			}
		}

		for (ClassElement element : classList) {
			if(element.getClassifier() instanceof AntiRigidSortalClass){
				if(specialCase.equals("Kind")){
					wrongSelection("Kinds intersect only with Mixins");
					return null;
				}
				if(element.getClassifier() instanceof Role){
					specialCase= "Role";
				}else{
					if(specialCase!="Role")
						specialCase= "Phase";
				}
			}else{
				if(element.getClassifier() instanceof RigidSortalClass){
					if(specialCase.equals("Kind")){
						wrongSelection("Kinds intersect only with Mixins");
						return null;
					}
					if(element.getClassifier() instanceof SubKind){
						if(!specialCase.equals("Role") && !specialCase.equals("Phase")){
							if(specialCase.equals("RoleMixin")){
								specialCase="Role";
							}else if(specialCase.equals("Mixin")){
								specialCase="KindMixin";
							} else {
								specialCase="Subkind";
							}
						}

					}else{
						if(specialCase.equals("Kind")|| specialCase.equals("Subkind")|| specialCase.equals("Role") || specialCase.equals("Phase")){
							wrongSelection("Kinds intersect only with Mixins");
							return null;
						}else{
							if(specialCase=="RoleMixin"){
								specialCase="Role";
							}else	if(specialCase=="Mixin"){
								specialCase="KindMixin";
							}else	if(specialCase=="Category"){
								specialCase="Subkind";
							}else{
								specialCase="Kind";
							}
						}
					}
				}else{
					if(element.getClassifier() instanceof RoleMixin){
						if(specialCase.equals("") || specialCase.equals("RoleMixin") || specialCase.equals("Mixin")|| specialCase.equals("Category")){
							specialCase="RoleMixin";
						}else if(specialCase.equals("Kind") || specialCase.equals("Subkind")){
							specialCase="Role";
						}
					} else if(element.getClassifier() instanceof Mixin){
						if(specialCase.equals("") ||  specialCase.equals("Mixin")|| specialCase.equals("Category")){
							specialCase="Mixin";
						} else if(specialCase.equals("Kind") || specialCase.equals("Subkind") ){
							specialCase="KindMixin";
						}
					}
					else if (specialCase.equals("Kind")){
						specialCase="Subkind";
					}else{
						specialCase="Category";
					}
				}
			}
		}

		return createIntersectionDerivedType(specialCase, diagramManager, activeEditor, project);
	}




	@SuppressWarnings("unused")
	private static Fix createIntersectionDerivedType(String stereotype,
			DiagramManager diagramManager, DiagramEditor activeEditor, UmlProject project) {
		dman= diagramManager;
		int j=0;
		ArrayList<RefOntoUML.Element> refontoList = new ArrayList<RefOntoUML.Element>();
		for (int i = 0; i < activeEditor.getSelectedElements().size(); i++) {
			if (activeEditor.getSelectedElements().get(i) instanceof ClassElement) {
				j++;
				ClassElement ce = (ClassElement) activeEditor.getSelectedElements().get(i);
				refontoList.add(ce.getClassifier());
			}		
		}
		mainfix= new Fix();
		String name=DefineNameDerivedType(); 
		of = new OutcomeFixer(project.getModel());

		ArrayList<Point2D.Double> positions= new ArrayList<Point2D.Double>();
		for (DiagramElement select : activeEditor.getSelectedElements()) {
			ClassElement position = (ClassElement) select;
			Point2D.Double newElementPosition= new Point2D.Double(position.getAbsoluteX1(), position.getAbsoluteY1());
			positions.add( newElementPosition);
		}
		Point2D.Double newElementPosition= ClassPosition.findPositionGeneralizationMember(positions,1);
		Classifier newElement = includeElement(newElementPosition, name, stereotype);
		ArrayList<Classifier> classifiers= new ArrayList<Classifier>();
		for (Element element : refontoList) {
			classifiers.add((Classifier)element);
		}
		createMultipleGeneralizationIntersection(newElement,classifiers);
		mainfix.includeAdded(newElement, newElementPosition.getX(),newElementPosition.getY());
		// TODO Auto-generated method stub
		return mainfix;

	}




	@SuppressWarnings("unused")
	public static void createDerivedTypeBySpecialization(String nameBase,
			String nameDerived, String stereotypeBase, String stereotypeDerived, String attribute,
			String typeAttribute, DiagramManager dm, Double location) {
		// TODO Auto-generated method stub
		dman=dm;
		of = new OutcomeFixer(dm.getCurrentProject().getModel());
		mainfix = new Fix();
		Classifier newElement_2= includeElement(location, nameBase, stereotypeBase);
		location.y=location.y+100;
		Classifier newElement= includeElement(location, nameDerived, stereotypeDerived);
		createGeneralizationSingle(newElement_2, newElement);
		RefOntoUML.Class classe = (Class) newElement_2;
		of.createAttribute(newElement_2, attribute, ClassStereotype.PRIMITIVETYPE, typeAttribute);

		//newElement_2.getAllAttributes().add(e)
		dm.updateOLED(mainfix);
	}




	public static Fix createSpecializationDerivation(
			DiagramEditor activeEditor, UmlProject project,
			DiagramManager diagramManager) {
		// TODO Auto-generated method stub
		String stereotype;
		ArrayList<String> values = new ArrayList<String>();
		String name;
		Object[] stereo = null ;
		List<DiagramElement> selected = activeEditor.getSelectedElements();
		if(selected.size()==1 ){
			ClassElement element = (ClassElement) selected.get(0);
			if(element.getClassifier() instanceof Kind || element.getClassifier() instanceof SubKind  ){
				values.add("Subkind");
				values.add("Phase");
			}else if(element.getClassifier() instanceof Role || element.getClassifier() instanceof Phase  ){
				values.add("Phase");
			}else {
				return null;
			}
			
			stereo = values.toArray();
			stereotype= selectStereotype(stereo);
			name=DefineNameDerivedType();
			dman=diagramManager;
			of = new OutcomeFixer(dman.getCurrentProject().getModel());
			mainfix = new Fix();
			Point2D.Double location = new Point2D.Double();
			location.x= element.getAbsoluteX1();
			location.y= element.getAbsoluteY1()+100;
			Classifier newElement= includeElement(location, name, stereotype);
			createGeneralizationSingle(element.getClassifier(), newElement);
			dman.updateOLED(mainfix);
		}

		
		return mainfix;
	}



	@SuppressWarnings("unused")
	public static void createDerivedTypeBySpecialization(String nameBase,
			String nameDerived, String stereotypeBase, String stereotypeDerived, DiagramManager dm, Double location)  {
		// TODO Auto-generated method stub
		dman=dm;
		of = new OutcomeFixer(dm.getCurrentProject().getModel());
		mainfix = new Fix();
		Classifier newElement_2= includeElement(location, nameBase, stereotypeBase);
		location.y=location.y+100;
		Classifier newElement= includeElement(location, nameDerived, stereotypeDerived);
		createGeneralizationSingle(newElement_2, newElement);
		RefOntoUML.Class classe = (Class) newElement_2;

		dm.updateOLED(mainfix);
	}




	public static void createPastSpecializationPattern(DiagramManager dman2,
			Double location, String namesuper, String stereosuper,
			String namespecial, String stereospecial, String namederived,
			String stereoderived, boolean rule) {
		// TODO Auto-generated method stub
		dman=dman2;
		of = new OutcomeFixer(dman2.getCurrentProject().getModel());
		mainfix = new Fix();
		Point2D.Double[] positions= ClassPosition.GSpositioning(2, location);
		Classifier newElement= includeElement(location, namesuper, stereosuper);
		Classifier newElement2= includeElement(positions[2], namespecial, stereospecial);
		Classifier newElement3 = includeElement(positions[1], namederived, stereoderived);
		createGeneralizationSingle(newElement, newElement2);
		createGeneralizationSingle(newElement, newElement3);
		
		if(rule){
			String rule_ocl= "context World \n  temp:_'"+namederived+"'.allInstances(self)->forAll( wk | self.allPrevious()->exists(w | wk.oclIsKindOf(_'"+namespecial+"',w)) and not wk.oclIsKindOf("+namespecial+",self))" ;
			dman.getFrame().getBrowserManager().getProjectBrowser().getOCLDocuments().get(0).addContent(rule_ocl);
		}
		
		dman2.updateOLED(mainfix);	
	}


	public static void createParticipationUni(DiagramManager dm, String name_base, String name_derived, String name_relator, String name_target, String stereo_base, String stereo_target, Double location){
		dman=dm;
		of = new OutcomeFixer(dm.getCurrentProject().getModel());
		mainfix = new Fix();
		
		Classifier newElement= includeElement(location, name_base, stereo_base);
		location.y= location.y +100;
		Classifier newElement_2= null;
		if(newElement instanceof Mixin || newElement instanceof RoleMixin || newElement instanceof RefOntoUML.Category){
			newElement_2= includeElement(location,name_derived, "RoleMixin");
		}else{
			newElement_2= includeElement(location,name_derived, "Role");
		}
		createGeneralizationSingle(newElement, newElement_2);
		location.x= location.x+160;
		Classifier newElement_3= includeElement(location,name_relator, "Relator");
		location.x= location.x+160;
		Classifier newElement_4= includeElement(location,name_target, stereo_target);
		Fix fix=of.createAssociationBetween(RelationStereotype.MEDIATION, "", newElement_2, newElement_3);
		Fix fix2=of.createAssociationBetween(RelationStereotype.MEDIATION, "", newElement_3, newElement_4);
		mainfix.addAll(fix);
		mainfix.addAll(fix2);
		dman.updateOLED(mainfix);
	}




	public static void createParticipationComp(DiagramManager dm, String name_base,
			String name_whole, String name_derived, String stereo_base, String stereo_whole,
			Point2D.Double location) {
		// TODO Auto-generated method stub
		dman=dm;
		of = new OutcomeFixer(dm.getCurrentProject().getModel());
		mainfix = new Fix();
		
		Classifier newElement= includeElement(location, name_base, stereo_base);
		location.y= location.y +100;
		Classifier newElement_2= null;
		if(newElement instanceof Mixin || newElement instanceof RoleMixin || newElement instanceof RefOntoUML.Category){
			newElement_2= includeElement(location,name_derived, "RoleMixin");
		}else{
			newElement_2= includeElement(location,name_derived, "Role");
		}
		createGeneralizationSingle(newElement, newElement_2);
		location.x= location.x+160;
		Classifier newElement_3= includeElement(location,name_whole, stereo_whole);
		Fix fix;
		if(stereo_whole.equals("Collective")){
			fix=of.createAssociationBetween(RelationStereotype.MEMBEROF, "", newElement_3,newElement_2 );
		}else{
			fix=of.createAssociationBetween(RelationStereotype.COMPONENTOF, "", newElement_3,newElement_2 );
		}
		mainfix.addAll(fix);
		dman.updateOLED(mainfix);
	}




	public static void createParticipationBi(DiagramManager dm, String name_base_left,
			String name_base_right, String name_relator, String name_derived_base, String name_derived_right,
			String stereo_base_left, String stereo_base_right, Point2D.Double location) {
		// TODO Auto-generated method stub
		dman=dm;
		of = new OutcomeFixer(dm.getCurrentProject().getModel());
		mainfix = new Fix();
		
		Classifier newElement= includeElement(location, name_base_left, stereo_base_left);
		location.y= location.y +100;
		Classifier newElement_2= null;
		if(newElement instanceof Mixin || newElement instanceof RoleMixin || newElement instanceof RefOntoUML.Category){
			newElement_2= includeElement(location,name_derived_base, "RoleMixin");
		}else{
			newElement_2= includeElement(location,name_derived_base, "Role");
		}
		createGeneralizationSingle(newElement, newElement_2);
		location.x= location.x+160;
		Classifier newElement_3= includeElement(location,name_relator, "Relator");

		location.y= location.y -100;
		location.x= location.x +160;
		Classifier newElement_4= includeElement(location, name_base_right, stereo_base_right);
	
		Classifier newElement_5;
		location.y= location.y +100;
		if(newElement_4 instanceof Mixin || newElement_4 instanceof RoleMixin || newElement_4 instanceof RefOntoUML.Category){
			newElement_5= includeElement(location,name_derived_base, "RoleMixin");
		}else{
			newElement_5= includeElement(location,name_derived_base, "Role");
		}
	
		Fix fix=of.createAssociationBetween(RelationStereotype.MEDIATION, "", newElement_2, newElement_3);
		Fix fix2=of.createAssociationBetween(RelationStereotype.MEDIATION, "", newElement_3, newElement_5);
		mainfix.addAll(fix);
		mainfix.addAll(fix2);
		createGeneralizationSingle(newElement_4, newElement_5);
		dman.updateOLED(mainfix);
	}
	
}
