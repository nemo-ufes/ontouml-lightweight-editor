package br.ufes.inf.nemo.pattern.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
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
			
			if(generals == null || specifics == null)
				return null;
			
			int contSpecifics = 0;
			Classifier specific;
			for(Object[] row : specifics){
				specific = getClassifier(row, x+(contSpecifics*verticalDistance)/3, y+horizontalDistance);

				if(specific != null){
					if(general != null){
						_fix = outcomeFixer.createGeneralization(specific, general);
						fix.addAll(_fix);
						Generalization generalization = (Generalization) _fix.getAdded().get(_fix.getAdded().size()-1);
						generalizationList.add(generalization);
					}
					contSpecifics++;
				}
			}

			if(general != null && contSpecifics != 0){//not_activate = false
				fix.addAll(createGeneralizationSet(generalizationList, true, true, dym.getGeneralizationSetName()));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return fix;
	}
	
	protected Fix getGeneralizationFix(){
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
			
			if(generals == null || specifics == null)
				return null;
			
			int contSpecifics = 0;
			Classifier specific;
			for(Object[] row : specifics){
				specific = getClassifier(row, x+(contSpecifics*verticalDistance)/3, y+horizontalDistance);

				if(specific != null){
					if(general != null){
						_fix = outcomeFixer.createGeneralization(specific, general);
						fix.addAll(_fix);
						Generalization generalization = (Generalization) _fix.getAdded().get(_fix.getAdded().size()-1);
						generalizationList.add(generalization);
					}
					contSpecifics++;
				}
			}

			if(general != null && contSpecifics != 0){//not_activate = false
				fix.addAll(createGeneralizationSet(generalizationList, false, false, dym.getGeneralizationSetName()));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return fix;
	}


	protected Fix createGeneralizationSet(ArrayList<Generalization> generalizationList, boolean b, boolean c, String generalizationSetName) {
		Set<GeneralizationSet> generalizationSets = parser.getAllInstances(GeneralizationSet.class);
		Fix f = new Fix();

		if(dym.isGSReuse()){
			for(GeneralizationSet gs : generalizationSets){
				if(gs.getName().equals(generalizationSetName) && gs.getGeneralization().get(0).getGeneral().equals(generalizationList.get(0).getGeneral())){
					generalizationList.addAll(gs.getGeneralization());
					f.addAll(outcomeFixer.deleteElement(gs));
					break;
				}
			}
		}

		f.addAll(outcomeFixer.createGeneralizationSet(generalizationList, b, c, generalizationSetName));

		return f;
	}

	protected OntoUMLParser parser;
	protected double x;
	protected double y;
	protected DynamicWindow dm;
	protected DynamicManagerWindow dym;
	protected String title;

	protected AbstractPattern(OntoUMLParser parser, double x, double y, String imagePath, String title) {
		this.parser = parser;
		this.x = x;
		this.y = y;
		this.imagePath = imagePath;
		this.title = title;

		dm = DynamicWindow.createDialog(title, imagePath);
		dym = new DynamicManagerWindow(dm);
	}

	public abstract void runPattern();

	public Fix getFix(){
		if(dm.isForcedCloed()){
			return null;
		}

		try{
			Fix fix = getSpecificFix();
			return fix;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	protected abstract Fix getSpecificFix();

	public boolean canGetFix() {
		return dym.wasPerformed();
	}
	
	public ArrayList<String> getActionsPerformed() {
		ArrayList<String> actions = new ArrayList<String>();
		actions.add("Applied "+title);
		actions.addAll(dym.getStatus());
		return actions;
	}

	public String getTitle() {
		return title;
	}

	protected HashMap<String,String[]> fillouthashTree(List<Class> classes) {
		HashMap<String, String[]> hashTree = new HashMap<>();

		for(Class cls : classes){
			Set<? extends Classifier> set = parser.getAllInstances(cls);
			if(!set.isEmpty())
				hashTree.put(cls.getSimpleName(), UtilAssistant.getStringRepresentationClass(set));
		}

		return hashTree;
	}

	private Set<GeneralizationSet> genSets = new HashSet<>();
	protected Set<GeneralizationSet> getGenSets() {
		return genSets;
	}

	private HashMap<String, ArrayList<String>> hashGS = new HashMap<>();
	protected HashMap<String, ArrayList<String>> getHashGS() {
		return hashGS;
	}

	protected void isPartitionPattern(List<Class> possibleGenerals, List<Class> possibleSpecifics){
		Set<GeneralizationSet> gs = parser.getAllInstances(GeneralizationSet.class);

		for(GeneralizationSet g : gs){
			if(g.isIsCovering() && g.isIsDisjoint()){
				for(Class c : g.getGeneralization().get(0).getGeneral().getClass().getInterfaces()){
					if(possibleGenerals.contains(c)){
						for(Generalization gg : g.getGeneralization()){
							for(Class cs : gg.getSpecific().getClass().getInterfaces()){
								if(possibleSpecifics.contains(cs)){
									if(!hashGS.containsKey(c.getSimpleName()))
										hashGS.put(c.getSimpleName(), new ArrayList<String>());
									String s = parser.getStringRepresentation(g).replace("GeneralizationSet ", "");
									if(!hashGS.get(c.getSimpleName()).contains(s))
										hashGS.get(c.getSimpleName()).add(s);
									genSets.add(g);
									break;
								}
							}
						}
						break;
					}
				}
			}
		}
		
		dym.addHashGS(getHashGS());
		dym.isPartitionPattern(getGenSets());
	}

	protected void reuseGeneralizationSet(List<Class> possibleGenerals, List<Class> possibleSpecifics){
		Set<GeneralizationSet> gs = parser.getAllInstances(GeneralizationSet.class);

		for(GeneralizationSet g : gs){
			if(g.isIsCovering() && g.isIsDisjoint()){
				for(Class c : g.getGeneralization().get(0).getGeneral().getClass().getInterfaces()){
					if(possibleGenerals.contains(c)){
						for(Generalization gg : g.getGeneralization()){
							for(Class cs : gg.getSpecific().getClass().getInterfaces()){
								if(possibleSpecifics.contains(cs)){
									if(!hashGS.containsKey(c.getSimpleName()))
										hashGS.put(c.getSimpleName(), new ArrayList<String>());
									String s = parser.getStringRepresentation(g).replace("GeneralizationSet ", "");
									if(!hashGS.get(c.getSimpleName()).contains(s))
										hashGS.get(c.getSimpleName()).add(s);
									genSets.add(g);
									break;
								}
							}
						}
						break;
					}
				}
			}
		}
		
		dym.addHashGS(getHashGS());
		dym.reuseGeneralizationSetPattern(getGenSets());
	}
	
}
