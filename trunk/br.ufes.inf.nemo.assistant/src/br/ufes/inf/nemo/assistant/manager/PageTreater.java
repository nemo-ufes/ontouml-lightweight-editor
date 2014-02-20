package br.ufes.inf.nemo.assistant.manager;

import java.util.ArrayList;
import java.util.HashMap;

import RefOntoUML.Classifier;
import br.ufes.inf.nemo.assistant.graph.NodeAssistant;
import br.ufes.inf.nemo.assistant.util.GeneralizationClass;
import br.ufes.inf.nemo.assistant.util.StereotypeOntoUMLEnum;
import br.ufes.inf.nemo.assistant.util.UtilAssistant;
import br.ufes.inf.nemo.assistant.wizard.pageassistant.NewGeneralizationSet;
import br.ufes.inf.nemo.assistant.wizard.pageassistant.NewGenericRelation;
import br.ufes.inf.nemo.assistant.wizard.pageassistant.Question;

public class PageTreater {
	/**
	 * Get all Generalization and its metaProperties by each class of each possibleStereotype
	 * */

	public static void treatPage(NodeAssistant node, NewGeneralizationSet page) {
		ManagerPattern mp = node.getGraph().getManagerPattern();
		HashMap<String,ArrayList<GeneralizationClass>> hashGenClassList = new HashMap<>();
		Classifier _source = mp.getClassSource();

		//Get all Generalization and its metaProperties by each possible class
		for (String stereotype : page.getPossibleStereotypes()) {
			StereotypeOntoUMLEnum stereotypeEnum = StereotypeOntoUMLEnum.valueOf(stereotype.toUpperCase());
			ArrayList<? extends Classifier> lst = mp.getPatternOperator().getAllInstancesOf(stereotypeEnum);
			for (Classifier general : lst) {
				if(!general.equals(_source)){
					ArrayList<GeneralizationClass> genSets = mp.getPatternOperator().getGeneralizationByGeneral(general);
					hashGenClassList.put(UtilAssistant.getStringRepresentationClass(general), genSets);
				}
			}
		}
		page.setHashOfClasses(hashGenClassList);
	}

	/**
	 * Block the next by boolean choice if its not possible
	 * */
	public static void treatPage(NodeAssistant node, Question page){
		if(!node.canGoTrue())
			page.setCanGoTrue(false);
		if(!node.canGoFalse())
			page.setCanGoFalse(false);

	}
	
	public static void treatPage(NodeAssistant node, NewGenericRelation page){
		ManagerPattern mp = node.getGraph().getManagerPattern();
		ArrayList<Classifier> classes = new ArrayList<>();
		Classifier _source = mp.getClassSource();

		//Get all classes of these stereotypes
		for (String stereotype : page.getTargetStereptypes()) {
			StereotypeOntoUMLEnum stereotypeEnum = StereotypeOntoUMLEnum.valueOf(stereotype.toUpperCase());
			ArrayList<? extends Classifier> lst = mp.getPatternOperator().getAllInstancesOf(stereotypeEnum);
			classes.addAll(lst);
		}
		
		//the current element was inserted
		String[] classesArray = new String[classes.size()-1];
		int i = 0;
		for (Classifier classe : classes) {
			if(!classe.equals(_source)){
				classesArray[i] = UtilAssistant.getStringRepresentationClass(classe);
				i++;
			}
		}
		
		page.setTargetClasses(classesArray);		
	}
}
