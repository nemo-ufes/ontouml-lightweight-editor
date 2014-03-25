package br.ufes.inf.nemo.assistant.manager;

import org.eclipse.jface.wizard.IWizardPage;

import RefOntoUML.Classifier;
import br.ufes.inf.nemo.assistant.wizard.pageassistant.NewClass;
import br.ufes.inf.nemo.assistant.wizard.pageassistant.NewGeneralizationSet;
import br.ufes.inf.nemo.assistant.wizard.pageassistant.NewGenericRelation;
import br.ufes.inf.nemo.assistant.wizard.pageassistant.NewPhase;
import br.ufes.inf.nemo.assistant.wizard.pageassistant.NewRelator;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;

public class ManagerPattern {
	private RefOntoUML.Package root;
	private Classifier source;

	//Auxiliary classes
	private PageProcessor pageProcessor;
	private PatternOperator patternOperator;
	private ActionProcessor actionProcessor;

	
	/**
	 * Create a ManagerPattern with a RefOntoUML root
	 * */
	public ManagerPattern(RefOntoUML.Package root) {
		this.root = root;
		patternOperator = new PatternOperator();
		pageProcessor = new PageProcessor(patternOperator);
		actionProcessor = new ActionProcessor(patternOperator);
	}

	/**
	 * Used by Parsers 
	 * */
	public ManagerPattern() {
		patternOperator = new PatternOperator();
		pageProcessor = new PageProcessor(patternOperator);
		actionProcessor = new ActionProcessor(patternOperator);
	}

	public void setClassSource(Classifier src){
		source = src;
	}

	public Classifier getClassSource(){
		return source;
	}
	
	public void setRefOntoUML(RefOntoUML.Package root) {
		this.root = root;
	}
	
	public Fix getFix(){
		return pageProcessor.getFix();
	}

	public void setFix(Fix fix) {
		pageProcessor.setFix(fix);
	}

	/**
	 *  Pages callbacks 
	 *  */
	public void run(IWizardPage page) {
		patternOperator.updateLists(root);
		pageProcessor.setRootPackage(root);
		pageProcessor.setSource(source);
		
		if(page instanceof NewRelator){
			pageProcessor.process((NewRelator)page);
		}else if(page instanceof NewClass){
			pageProcessor.process((NewClass)page);
		}else if(page instanceof NewPhase){
			pageProcessor.process((NewPhase)page);
		}else if(page instanceof NewGenericRelation){
			pageProcessor.process((NewGenericRelation)page);
		}else if(page instanceof NewGeneralizationSet){
			pageProcessor.process((NewGeneralizationSet)page);
		}
	}

	/**
	 * Operations with RefOntoUML from pages 
	 */

	public PatternOperator getPatternOperator(){
		patternOperator.updateLists(root);
		return patternOperator;
	}
	
	/**
	 * For manager pages
	 * */
	public PageProcessor getPageProcessor(){
		return pageProcessor;
	}

	/**
	 *  Actions 
	 *  */
	public ActionProcessor getActionProcessor(){
		patternOperator.updateLists(root);
		return actionProcessor;
	}

	public Classifier getStartedClass() {
		return pageProcessor.getStartedClass();
	}
}