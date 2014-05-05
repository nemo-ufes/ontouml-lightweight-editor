package br.ufes.inf.nemo.oled.derivation;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import RefOntoUML.AntiRigidMixinClass;
import RefOntoUML.AntiRigidSortalClass;
import RefOntoUML.Classifier;
import RefOntoUML.Element;
import RefOntoUML.Generalization;
import RefOntoUML.Kind;
import RefOntoUML.RigidMixinClass;
import RefOntoUML.RigidSortalClass;
import RefOntoUML.SemiRigidMixinClass;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;
import br.ufes.inf.nemo.common.positioning.ClassPosition;
import br.ufes.inf.nemo.derivedtypes.DerivedByExclusion;
import br.ufes.inf.nemo.derivedtypes.DerivedByUnion;
import br.ufes.inf.nemo.oled.DiagramManager;
import br.ufes.inf.nemo.oled.draw.DiagramElement;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.ui.diagram.DiagramEditor;
import br.ufes.inf.nemo.oled.umldraw.structure.ClassElement;
import br.ufes.inf.nemo.oled.umldraw.structure.GeneralizationElement;

public class DerivedTypesOperations {

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
			specialCase = multipleElementsUnionDerivationRelation();
			if(specialCase.equals("")){
				wrongSelection("Wrong Selection");
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
					stereotypes.add("Role Mixin");
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
						stereotype= "Role Mixin";
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
						stereotypes.add("Role Mixin");
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

	private static String multipleElementsUnionDerivationRelation() {
		// TODO Auto-generated method stub
		
		return null;
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
			ClassElement position = (ClassElement) selected.get(0);
			ClassElement position2 = (ClassElement) selected.get(1);
			firstpoint.setLocation(position.getAbsoluteX1(),position.getAbsoluteY1());
			secondpoint.setLocation(position2.getAbsoluteX1(),position2.getAbsoluteY1());
			Point2D.Double newElementPosition= ClassPosition.findPositionGeneralization(firstpoint, secondpoint);
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
		Fix gs =  of.createGeneralizationSet(generalizations);
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
					if(e.equals(refontoList.get(1))){
						pos=1;
						pos2=0;
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
						String rule="context: "+((Classifier) refontoList.get(pos)).getName()+"\n"+"inv: not oclIsTypeOf(_'"+((Classifier) refontoList.get(pos2)).getName()+"') implies oclIsTypeOf(_'"+name+"')";
						dm.getFrame().getInfoManager().getOcleditor().addText(rule);
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

	public static void wrongSelection(String message){
		JFrame frame = new JFrame("InputDialog Example #1");
		System.out.println("error");
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
		Fix gs =  of.createGeneralizationSet(generalizations);
		mainfix.addAll(fixG2);
		mainfix.addAll(fix);
		mainfix.addAll(gs);
	}

	public  static void createMultipleGeneralization(Classifier father, ArrayList<Classifier> sons){
		ArrayList<Generalization> generalizations = new ArrayList<Generalization>();
		for (Classifier classifier : sons) {
			Fix fix=of.createGeneralization(classifier, father);
			generalizations.add((Generalization) fix.getAdded().get(0));
			mainfix.addAll(fix);
		}

		Fix gs =  of.createGeneralizationSet(generalizations);
		mainfix.addAll(gs);
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

}
