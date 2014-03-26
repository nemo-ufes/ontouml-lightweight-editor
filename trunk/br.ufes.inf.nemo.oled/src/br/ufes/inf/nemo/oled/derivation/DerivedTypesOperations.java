package br.ufes.inf.nemo.oled.derivation;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import RefOntoUML.Classifier;
import RefOntoUML.Element;
import RefOntoUML.Generalization;
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



	@SuppressWarnings("unused")
	public static Fix createUnionDerivation(DiagramEditor activeEditor, UmlProject project){

		Fix mainfix = new Fix();
		List<DiagramElement> selected = activeEditor.getSelectedElements();
		if(selected.size()==2  && selected.get(0) instanceof ClassElement && selected.get(1) instanceof ClassElement ){
			int j=0;
			ArrayList<RefOntoUML.Element> refontoList = new ArrayList<RefOntoUML.Element>();
			for (int i = 0; i < selected.size(); i++) {
				if (selected.get(i) instanceof ClassElement) {
					j++;
					ClassElement ce = (ClassElement) selected.get(i);
					refontoList.add(ce.getClassifier());
				}		
			}
			if(refontoList.size()==2){

				ArrayList<String>stereotypes= DerivedByUnion.getInstance().inferStereotype(refontoList.get(0).eClass().getName(), refontoList.get(1).eClass().getName());
				if(stereotypes!=null)
					if(stereotypes.size()<2){
						String name=DefineNameDerivedType();
						mainfix =createDerivedTypeUnion(stereotypes.get(0), mainfix, selected,name,refontoList,project);			
					}
					else{
						Object[] stereo;
						stereo=  stereotypes.toArray();
						String name=DefineNameDerivedType();
						String stereotype= selectStereotype(stereo);
						mainfix= createDerivedTypeUnion(stereotype, mainfix, selected,name,refontoList,project);
					}
			}
			
		}
		else{
			WrongSelection();
		}
		return mainfix;
	}

	public static Fix createDerivedTypeUnion(String stereotype, Fix mainfix, List<DiagramElement> selected, String name, ArrayList<RefOntoUML.Element> refontoList, UmlProject project){

		//UmlProject project = getCurrentEditor().getProject();
		OutcomeFixer of = new OutcomeFixer(project.getModel());
		Classifier newElement = (Classifier)of.createClass( of.getClassStereotype(stereotype));
		newElement.setName(name);
		of.copyContainer(((ClassElement) selected.get(0)).getClassifier(), newElement);
		Fix fix=of.createGeneralization((Classifier)refontoList.get(0), newElement);
		Fix fixG2=of.createGeneralization((Classifier)refontoList.get(1), newElement);
		ArrayList<Generalization> generalizations = new ArrayList<Generalization>();
		generalizations.add((Generalization) fix.getAdded().get(0));
		generalizations.add((Generalization) fixG2.getAdded().get(0));
		Fix gs =  of.createGeneralizationSet(generalizations);
		mainfix.addAll(fixG2);
		mainfix.addAll(fix);
		mainfix.addAll(gs);
		ClassElement position = (ClassElement) selected.get(0);
		ClassElement position2 = (ClassElement) selected.get(1);
		Point2D.Double firstpoint = new Point2D.Double();
		Point2D.Double secondpoint = new Point2D.Double();
		firstpoint.setLocation(position.getAbsoluteX1(),position.getAbsoluteY1());
		secondpoint.setLocation(position2.getAbsoluteX1(),position2.getAbsoluteY1());
		Point2D.Double newElementPosition= ClassPosition.findPositionGeneralization(firstpoint, secondpoint);
		mainfix.includeAdded(newElement, newElementPosition.getX(),newElementPosition.getY());
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
					if(refontoList.get(pos2).eClass().getName().equals("SubKind"))
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
				WrongSelection();
			}

		}
		else{
			WrongSelection();
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

	@SuppressWarnings("unused")
	public static void WrongSelection(){
		JFrame frame = new JFrame("InputDialog Example #1");
		System.out.println("error");
		JOptionPane.showMessageDialog(frame, "Wrong Selection, please check the documentation!");

	}
	
	public static void UnionPattern(DiagramManager dm, ArrayList<String> values){
		OutcomeFixer of = new OutcomeFixer(dm.getCurrentProject().getModel());
		Fix mainfix = new Fix();
		//of.factory;
		Classifier newElement= (Classifier) of.createClass(of.getClassStereotype(values.get(0)));
		//of.copyContainer((dm.getCurrentProject().getElements().get(0)), newElement);
		mainfix.includeAdded(newElement);
		dm.updateOLED(mainfix);
		//newElement.setName(values.get(2));
		//dm.updateOLED(mainfix);
		/*
		Classifier newElement2 = (Classifier)of.createClass( of.getClassStereotype(values.get(1)));
		newElement.setName(values.get(3));
		ArrayList<String> stereotypes= DerivedByExclusion.getInstance().inferStereotype(newElement.eClass().getName() , newElement2.eClass().getName());
		
		if(stereotypes!=null){
			if(stereotypes.size()==1){
				Classifier newElement3 = (Classifier)of.createClass( of.getClassStereotype(stereotypes.get(0)));
			}else{
				Object[] stereo;
				stereo=  stereotypes.toArray();
				String name=DefineNameDerivedType();
				String stereotype= selectStereotype(stereo);
				Classifier newElement3 = (Classifier)of.createClass( of.getClassStereotype(stereotype));
				
			}
			newElement.setName(values.get(4));
			
		}*/
	}

}
