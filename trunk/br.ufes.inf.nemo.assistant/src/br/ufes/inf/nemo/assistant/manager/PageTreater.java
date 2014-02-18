package br.ufes.inf.nemo.assistant.manager;

import java.util.ArrayList;
import java.util.HashMap;

import RefOntoUML.Classifier;
import br.ufes.inf.nemo.assistant.graph.NodeAssistant;
import br.ufes.inf.nemo.assistant.util.GeneralizationClass;
import br.ufes.inf.nemo.assistant.util.StereotypeOntoUMLEnum;
import br.ufes.inf.nemo.assistant.util.UtilAssistant;
import br.ufes.inf.nemo.assistant.wizard.pageassistant.NewGeneralizationSet;
import br.ufes.inf.nemo.assistant.wizard.pageassistant.Question;

public class PageTreater {
	/**
	 * Get all Generalization and its metaProperties by each class of each possibleStereotype
	 * */
	
	public static void treatPage(NodeAssistant node, NewGeneralizationSet page) {
		ManagerPattern mp = node.getGraph().getManagerPattern();
		HashMap<String,ArrayList<GeneralizationClass>> hashGenClassList = new HashMap<>();

		//Get all Generalization and its metaProperties by each class
		for (String stereotype : page.getPossibleStereotypes()) {
			StereotypeOntoUMLEnum stereotypeEnum = StereotypeOntoUMLEnum.valueOf(stereotype.toUpperCase());
			ArrayList<? extends Classifier> lst = mp.getPatternOperator().getAllInstancesOf(stereotypeEnum);
			for (Classifier general : lst) {
				 ArrayList<GeneralizationClass> genSets = mp.getPatternOperator().getGeneralizationByGeneral(general);
				 hashGenClassList.put(UtilAssistant.getStringRepresentationClass(general), genSets);	 
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
}
