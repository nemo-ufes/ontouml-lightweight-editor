package br.ufes.inf.nemo.antipattern.wizard.impabs;

import RefOntoUML.Classifier;
import br.ufes.inf.nemo.antipattern.impabs.ImpAbsOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntiPatternAction;

public class ImpAbsAction extends AntiPatternAction<ImpAbsOccurrence>{

	public ImpAbsAction(ImpAbsOccurrence ap) {
		super(ap);
	}
	
	private Classifier source, target;
	private boolean isEssential, isImmutablePart, isInseparable, isImmutableWhole, isShareable, isReadOnlySource, isReadOnlyTarget, isDerivedSource, isDerivedTarget;
	private int lowerSource, lowerTarget, upperSource, upperTarget;
	
	public enum Action {SET_ESSENTIAL, SET_INSEPARABLE, SET_SHAREABLE, SET_READ_ONLY, SET_DERIVED, SET_MULTIPLICITY }

	@Override
	public void run() {
		
		if(code==Action.SET_ESSENTIAL)
			ap.setIsEssential(source, target, isEssential, isImmutablePart);
		else if(code==Action.SET_INSEPARABLE)
			ap.setIsInseparable(source, target, isInseparable, isImmutableWhole);
		else if(code==Action.SET_SHAREABLE)
			ap.setIsShareable(source, target, isShareable);
		else if (code==Action.SET_READ_ONLY)
			ap.setIsReadOnly(source, target, isReadOnlySource, isReadOnlyTarget);
		else if (code==Action.SET_DERIVED)
			ap.setIsDerived(source, target, isDerivedSource, isDerivedTarget);
		else if (code==Action.SET_MULTIPLICITY)
			ap.setMultiplicity(source, target, lowerSource, upperSource, lowerTarget, upperTarget);
	}
	
	public void setIsEssential(Classifier whole, Classifier part, boolean isEssential, boolean isImmutablePart){
		code = Action.SET_ESSENTIAL;
		this.source = whole;
		this.target = part;
		this.isEssential = isEssential;
		this.isImmutablePart = isImmutablePart;
	}
	
	public void setIsInseparable(Classifier whole, Classifier part, boolean isInseparable, boolean isImmutableWhole){
		code = Action.SET_INSEPARABLE;
		this.source = whole;
		this.target = part;
		this.isInseparable = isInseparable;
		this.isImmutableWhole = isImmutableWhole;
	}
	
	public void setIsShareable(Classifier whole, Classifier part, boolean isShareable){
		code = Action.SET_SHAREABLE;
		this.source = whole;
		this.target = part;
		this.isShareable = isShareable;
	}
	
	public void setIsReadOnly(Classifier source, Classifier target, boolean isReadOnlySource, boolean isReadOnlyTarget){
		code = Action.SET_READ_ONLY;
		this.source = source;
		this.target = target;
		this.isReadOnlySource = isReadOnlySource;
		this.isReadOnlyTarget = isReadOnlyTarget;
	}
	
	public void setIsDerived(Classifier source, Classifier target, boolean isDerivedSource, boolean isDerivedTarget){
		code = Action.SET_DERIVED;
		this.source = source;
		this.target = target;
		this.isDerivedSource = isDerivedSource;
		this.isDerivedTarget = isDerivedTarget;
	}
	
	public void setMultiplicity(Classifier source, Classifier target, int lowerSource, int upperSource, int lowerTarget, int upperTarget){
		code = Action.SET_MULTIPLICITY;
		this.source = source;
		this.target = target;
		this.lowerSource = lowerSource;
		this.lowerTarget = lowerTarget;
		this.upperSource = upperSource;
		this.upperTarget = upperTarget;
	}
		
	@Override
	public String toString(){
		String result = new String();
		
		if(code==Action.SET_ESSENTIAL)
			result = "Modify meta-property: isEssential = "+isEssential+", isImmutablePart = "+isImmutablePart+" (Whole: "+ap.getParser().getStringRepresentation(source)+", Part: "+ap.getParser().getStringRepresentation(source)+")";
		else if(code==Action.SET_INSEPARABLE)
			result = "Modify meta-property: isInseparable = "+isInseparable+", isImmutableWhole = "+isImmutableWhole+" (Whole: "+ap.getParser().getStringRepresentation(source)+", Part: "+ap.getParser().getStringRepresentation(source)+")";
		else if(code==Action.SET_SHAREABLE)
			result = "Modify meta-property: isShareable = "+isShareable+" (Whole: "+ap.getParser().getStringRepresentation(source)+", Part: "+ap.getParser().getStringRepresentation(source)+")";
		else if(code==Action.SET_READ_ONLY)	
			result = "Modify meta-property: isReadOnly = "+isReadOnlySource+" ("+ap.getParser().getStringRepresentation(source)+" end) \n"+
					 "Modify meta-property: isReadOnly = "+isReadOnlyTarget+" ("+ap.getParser().getStringRepresentation(target)+" end) \n";
		else if(code==Action.SET_DERIVED)	
			result = "Modify meta-property: isDerived = "+isDerivedSource+" ("+ap.getParser().getStringRepresentation(source)+" end) \n"+
					 "Modify meta-property: isDerived = "+isDerivedTarget+" ("+ap.getParser().getStringRepresentation(target)+" end) \n";
		else if(code==Action.SET_MULTIPLICITY)	
			result = "Modify multiplicity: lower = "+lowerSource+", upper = "+upperSource+" ("+ap.getParser().getStringRepresentation(source)+" end) \n"+
					 "Modify multiplicity: lower = "+lowerTarget+", upper = "+upperTarget+" ("+ap.getParser().getStringRepresentation(target)+" end) \n";
		
		return result; 
	}

}
