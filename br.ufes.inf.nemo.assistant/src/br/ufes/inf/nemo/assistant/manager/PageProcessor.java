package br.ufes.inf.nemo.assistant.manager;

import java.util.ArrayList;
import java.util.HashMap;

import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.Package;
import br.ufes.inf.nemo.assistant.graph.NodeAssistant;
import br.ufes.inf.nemo.assistant.util.GeneralizationClass;
import br.ufes.inf.nemo.assistant.util.StereotypeOntoUMLEnum;
import br.ufes.inf.nemo.assistant.util.UtilAssistant;
import br.ufes.inf.nemo.assistant.wizard.pageassistant.NewClass;
import br.ufes.inf.nemo.assistant.wizard.pageassistant.NewGeneralizationSet;
import br.ufes.inf.nemo.assistant.wizard.pageassistant.NewGenericRelation;
import br.ufes.inf.nemo.assistant.wizard.pageassistant.NewPhase;
import br.ufes.inf.nemo.assistant.wizard.pageassistant.NewRelator;
import br.ufes.inf.nemo.assistant.wizard.pageassistant.Question;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer.ClassStereotype;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer.RelationStereotype;

public class PageProcessor{
	private OutcomeFixer outcomeFixer;

	private Fix fix = new Fix();
	public Fix getFix(){
		return fix;
	}

	private Classifier source;
	public void setSourceClass(Classifier source) {
		this.source = source; 
	}

	private PatternOperator patternOperator;
	public PageProcessor(PatternOperator operator) {
		patternOperator = operator;
	}

	public void process(NewGenericRelation page) {
		System.out.println(page.toString());
	}

	public void process(NewPhase page) {
		//factory.createComment() 
		
		ArrayList<Generalization> gens = new ArrayList<>();
		ArrayList<String[]> phasesString = page.getPhases();
		for (String[] phaseString : phasesString) {
			//phase = new Phase
			Classifier phase = (Classifier)outcomeFixer.createClass(ClassStereotype.PHASE);
			phase.setName(phaseString[0]);
			
			//phase.container = source.container
			outcomeFixer.copyContainer(source, phase);
			fix.includeAdded(phase);

			//gen = new Gen
			Generalization gen = (Generalization) outcomeFixer.createRelationship(RelationStereotype.GENERALIZATION);
			gen.setGeneral(source);
			gen.setSpecific(phase);
			fix.includeAdded(gen);
			gens.add(gen);
		}
		Fix f = outcomeFixer.createGeneralizationSet(gens, true, true, page.getGeneralizationSetName());
		fix.addAll(f);
	}

	public void process(NewClass page) {
		//Set the new name
		source.setName(page.getClassName());
		if(UtilAssistant.getStereotypeFromClassifier(source) != StereotypeOntoUMLEnum.valueOf(page.getStereotype().toUpperCase())){
			//source.stereotype != page.stereotype
			//change the source stereotype
			fix = outcomeFixer.changeClassStereotypeTo(source, ClassStereotype.valueOf(page.getStereotype().toUpperCase()));	
		}
		//include all modifications
		fix.includeModified(source);
	}

	public void process(NewRelator page){
		System.out.println(page.toString());
	}

	public void process(NewGeneralizationSet page) {
		//source.container = general.container
		RefOntoUML.Classifier general = patternOperator.getClassifierForStringRepresentationClassStereotype(page.getGeneral()); 
		outcomeFixer.copyContainer(general, source);
		fix.includeModified(source);

		//gen.general  = general
		//gen.specific = source
		Generalization gen = (Generalization) outcomeFixer.createRelationship(RelationStereotype.GENERALIZATION);
		gen.setGeneral(general);
		gen.setSpecific(source);
		fix.includeAdded(gen);

		//genSet.add(gen)
		GeneralizationSet genSet = patternOperator.getGeneralizationSetByName(page.getGeneralizationSet());
		if(genSet == null){
			//a new genSet
			ArrayList<Generalization> gens = new ArrayList<>();
			gens.add(gen);
			Fix f = outcomeFixer.createGeneralizationSet(gens, page.getIsDisjoint(), page.getIsComplete(), page.getGeneralizationSet());
			genSet = (GeneralizationSet)f.getAdded().get(0);
			fix.addAll(f);
		}else{
			//modifications in meta properties
			genSet.setIsDisjoint(page.getIsDisjoint());
			genSet.setIsCovering(page.getIsComplete());
		}
		genSet.getGeneralization().add(gen);
		fix.includeModified(genSet);
	}

	/**
	 * Get all Generalization and its metaProperties by each class of each possibleStereotype
	 * */
	public static void treatPage(NodeAssistant node, NewGeneralizationSet page) {
		ManagerPattern mp = node.getGraph().getManagerPattern();
		HashMap<String,ArrayList<GeneralizationClass>> hashGenClassList = new HashMap<>();

		//Get all Generalization and its metaProperties by each class
		for (String stereotype : page.getPossibleStereotypes()) {
			StereotypeOntoUMLEnum stereotypeEnum = StereotypeOntoUMLEnum.valueOf(stereotype.toUpperCase());
			ArrayList<GeneralizationClass> genClassList = mp.getPatternOperator().getMetaPropertiesForAll(stereotypeEnum);
			hashGenClassList.put(stereotype, genClassList);
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

	public void setRootPackage(Package root) {
		if(outcomeFixer == null){
			outcomeFixer = new OutcomeFixer(root);
		}
	}
}
