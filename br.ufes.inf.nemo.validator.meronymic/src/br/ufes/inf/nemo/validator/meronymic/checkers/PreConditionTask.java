package br.ufes.inf.nemo.validator.meronymic.checkers;

import java.util.List;

import javax.swing.SwingWorker;

import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.validator.meronymic.checkers.ui.CheckerTableModel;

public class PreConditionTask extends SwingWorker<Boolean, MeronymicError<?>>{
	OntoUMLParser parser;
	
	CheckerTableModel tableModel;
	
	boolean checkHierarchyCycle, checkGeneralization, checkIdentity, checkAggregation, checkEnds, checkMeronymicCycle;
	public boolean canGoToNextStep;
	
	HierarchyCycleChecker hierachyCycleChecker;
	GeneralizationChecker generalizationChecker;
	IdentityChecker identityChecker;
	AggregationKindChecker aggregationChecker;
	MeronymicEndsChecker meronymicEndsChecker;
	MeronymicCycleChecker meronymicCycleChecker;
	
	public PreConditionTask(OntoUMLParser parser, CheckerTableModel tableModel) {
		this.parser = parser;
		this.tableModel = tableModel;
		this.canGoToNextStep = false;
		
		hierachyCycleChecker = new HierarchyCycleChecker(parser);
		generalizationChecker = new GeneralizationChecker(parser);
		identityChecker = new IdentityChecker(parser);
		aggregationChecker = new AggregationKindChecker(parser);
		meronymicEndsChecker = new MeronymicEndsChecker(parser);
		meronymicCycleChecker = new MeronymicCycleChecker(parser);
	}

	public boolean isCheckHierarchyCycle() {
		return checkHierarchyCycle;
	}

	public void setCheckHierarchyCycle(boolean checkHierarchyCycle) {
		this.checkHierarchyCycle = checkHierarchyCycle;
	}

	public boolean isCheckGeneralization() {
		return checkGeneralization;
	}

	public void setCheckGeneralization(boolean checkGeneralization) {
		this.checkGeneralization = checkGeneralization;
	}

	public boolean isCheckIdentity() {
		return checkIdentity;
	}

	public void setCheckIdentity(boolean checkIdentity) {
		this.checkIdentity = checkIdentity;
	}

	public boolean isCheckAggregation() {
		return checkAggregation;
	}

	public void setCheckAggregation(boolean checkAggregation) {
		this.checkAggregation = checkAggregation;
	}

	public boolean isCheckEnds() {
		return checkEnds;
	}

	public void setCheckEnds(boolean checkEnds) {
		this.checkEnds = checkEnds;
	}

	public boolean isCheckMeronymicCycle() {
		return checkMeronymicCycle;
	}

	public void setCheckMeronymicCycle(boolean checkMeronymicCycle) {
		this.checkMeronymicCycle = checkMeronymicCycle;
	}
	
	private int runChecker(Checker<?> checker, int progress){
		System.out.println(checker.checkerName()+": checking model...");
		checker.check();
		System.out.println(checker.checkerName()+": "+checker.getErrors().size()+" error(s) found");
		
		for (MeronymicError<?> error : checker.getErrors()) {
			publish(error);
		}
		
		setProgress(progress);
		return checker.getErrors().size();
	}
	
	@Override
	protected Boolean doInBackground() throws Exception {
		int currentProgress = 0;
		int enabledTests = getNumberOfEnabledTests();
		int stepSize = 0;
		int totalErrorsFound = 0;
		
		System.out.println("Tests to perform: "+enabledTests);
		
		if(enabledTests!=0)
			stepSize = 100/enabledTests;
		
		setProgress(1);
		
		if(checkHierarchyCycle){
			currentProgress+=stepSize;
			totalErrorsFound+=runChecker(hierachyCycleChecker, currentProgress);
		}
		
		if(checkGeneralization){
			currentProgress+=stepSize;
			totalErrorsFound+=runChecker(generalizationChecker, currentProgress);
		}
		
		if(checkIdentity){
			currentProgress+=stepSize;
			totalErrorsFound+=runChecker(identityChecker, currentProgress);
		}
		
		if(checkAggregation){
			currentProgress+=stepSize;
			totalErrorsFound+=runChecker(aggregationChecker, currentProgress);
		}
		
		if(checkEnds){
			currentProgress+=stepSize;
			totalErrorsFound+=runChecker(meronymicEndsChecker, currentProgress);
		}
		
		if(checkMeronymicCycle){
			currentProgress+=stepSize;
			totalErrorsFound+=runChecker(meronymicCycleChecker, currentProgress);
		}
		
		System.out.println("Tests perfomed: "+enabledTests);
		System.out.println("Total of errors found: "+totalErrorsFound);
		
		setProgress(100);
		
		if(totalErrorsFound!=0){
			System.out.println("Please fix your model to continue the part-whole validation...");
			canGoToNextStep = false;
		}
		if(totalErrorsFound==0 && enabledTests<6){
			System.out.println("Please check your model agaist all tests to be able to continue the validation of part-whole relations");
			canGoToNextStep = false;
		}
		if(totalErrorsFound==0 && enabledTests==6){
			System.out.println("Please go to tab 'Forbbiden' to continue with the validation of part-whole relations");
			canGoToNextStep = true;
		}	
		return true;
	}
	
	public int getNumberOfEnabledTests(){
		int total = 0;
		
		if(checkHierarchyCycle) total++;
		if(checkGeneralization) total++;
		if(checkIdentity) total++;
		if(checkAggregation) total++;
		if(checkEnds) total++;
		if(checkMeronymicCycle) total++;
		
		return total;
	}
	
	@Override
	protected void process(final List<MeronymicError<?>> result) {
		for (MeronymicError<?> error : result) {
			tableModel.addRow(error);
		}
	}	
	
}
