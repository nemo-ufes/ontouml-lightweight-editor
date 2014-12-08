package br.ufes.inf.nemo.pattern.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.Package;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.assistant.util.UtilAssistant;
import br.ufes.inf.nemo.common.ontoumlfixer.ClassStereotype;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;
import br.ufes.inf.nemo.pattern.dynamic.ui.DynamicWindow;
import br.ufes.inf.nemo.pattern.ui.manager.DynamicManagerWindow;

public abstract class AbstractPattern {

	protected  String imagePath;	
	protected  OutcomeFixer outcomeFixer;
	protected  Fix fix = null;

	protected static final int horizontalDistance = 150;
	protected static final int verticalDistance = 330;
	
	protected Classifier getClassifier(Object[] param, double px, double py){
		Classifier classifier = null;

		if(!(boolean)param[3]){//not_activate = false
			if((boolean)param[0]){ //isReuse?
				classifier = reuseClassifier((String)param[1], (String)param[2]);
			}else{
				classifier = createClassifier((String)param[1], (String)param[2], px, py);
			}
		}

		return classifier;
	}

	protected Classifier createClassifier(String name, String stereotype, double x, double y){
		ClassStereotype enumStereotype = ClassStereotype.valueOf(stereotype.toUpperCase());
		
		RefOntoUML.Classifier classifier = (Classifier) outcomeFixer.createClass(enumStereotype);
		classifier.setName(name);
		parser.getModel().getPackagedElement().add(classifier);

		fix.includeAdded(classifier,x,y);

		return classifier;
	}
	
	protected Classifier reuseClassifier(String name, String stereotype){
		Set<RefOntoUML.Class> setClasses = parser.getAllInstances(RefOntoUML.Class.class);
		Iterator<RefOntoUML.Class> clsIte = setClasses.iterator();

		while (clsIte.hasNext()) {
			RefOntoUML.Class cls = clsIte.next();
			if(UtilAssistant.getStringRepresentationClass(cls).equals(name)){
				fix.includeAdded(cls);
				return cls;
			}
		}
		return null;
	}
	
	protected Fix getPartitionFix(){
		try{
			Package root = parser.getModel();
			outcomeFixer = new OutcomeFixer(root);
			fix = new Fix();
			Fix _fix = null;

			//General
			ArrayList<Object[]> generals = dym.getRowsOf("general");
			Classifier general = getClassifier(generals.get(0), x, y);

			//Specifics
			ArrayList<Generalization> generalizationList = new ArrayList<>();
			ArrayList<Object[]> specifics = dym.getRowsOf("specific");
			int i = 0;
			Classifier specific;
			for(Object[] row : specifics){
				specific = getClassifier(row, x+(i*verticalDistance)/3, y+horizontalDistance);

				if(specific != null){
					if(general != null){
						_fix = outcomeFixer.createGeneralization(specific, general);
						fix.addAll(_fix);
						Generalization generalization = (Generalization) _fix.getAdded().get(_fix.getAdded().size()-1);
						generalizationList.add(generalization);
					}
					i++;
				}
			}

			if(general != null){//not_activate = false
				fix.addAll(outcomeFixer.createGeneralizationSet(generalizationList, true, true, "partition"+UtilAssistant.getCont()));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return fix;
	}
	
	protected OntoUMLParser parser;
	protected double x;
	protected double y;
	protected DynamicWindow dm;
	protected DynamicManagerWindow dym;
	
	protected AbstractPattern(OntoUMLParser parser, double x, double y, String imagePath, final String title) {
		this.parser = parser;
		this.x = x;
		this.y = y;
		this.imagePath = imagePath;
		
    	dm = DynamicWindow.createDialog(title, imagePath);
		dym = new DynamicManagerWindow(dm);
	}
	
	public abstract void runPattern();
	public abstract Fix getFix();

	public boolean canGetFix() {
		return dym.wasPerformed();
	}
}
