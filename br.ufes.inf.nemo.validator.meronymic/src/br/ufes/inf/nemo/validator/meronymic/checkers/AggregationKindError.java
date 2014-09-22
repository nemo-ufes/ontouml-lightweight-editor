package br.ufes.inf.nemo.validator.meronymic.checkers;

import javax.swing.JDialog;

import RefOntoUML.AggregationKind;
import RefOntoUML.Association;
import RefOntoUML.Meronymic;
import RefOntoUML.Property;
import RefOntoUML.parser.OntoUMLNameHelper;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.validator.meronymic.checkers.ui.AggregationDialog;

public class AggregationKindError extends MeronymicError<Meronymic> {
	boolean isVerified;
	boolean isSourceWhole;
	private boolean isComposite;
	
	public AggregationKindError(OntoUMLParser parser, Meronymic meronymic) {
		super(parser,meronymic);
		isVerified = false;
	}

	public static boolean isAggregationKindSet(Meronymic m){
		//both ends are NONE
		if(bothEndsAreNone(m))
			return false;
		
		//both ends are SET
		if(bothEndsAreSet(m))
			return false;
		
		return true;
	}

	public static boolean bothEndsAreSet(Meronymic m) {
		return m.getMemberEnd().get(0).getAggregation()!=AggregationKind.NONE && m.getMemberEnd().get(1).getAggregation()!=AggregationKind.NONE;
	}
	
	public boolean bothEndsAreSet() {
		return bothEndsAreSet(element);
	}

	static boolean bothEndsAreNone(Meronymic m) {
		return m.getMemberEnd().get(0).getAggregation()==AggregationKind.NONE && m.getMemberEnd().get(1).getAggregation()==AggregationKind.NONE;
	}
	
	public boolean bothEndsAreNone(){
		return bothEndsAreNone(element);
	}

	@Override
	public String getDescription() {
		return OntoUMLNameHelper.getCompleteName(element);
	}

	@Override
	public String getType() {
		String description = "";
		
		if(bothEndsAreNone(element))
			description += "Aggregation NONE in both ends";
		else
			description += "Aggregation SETTED in both ends";
		
		return description;
	}

	@Override
	public JDialog createDialog(JDialog parent) {
		return new AggregationDialog(parent, this);
	}

	@Override
	public Fix fix() {
		Property sourceEnd, targetEnd;
		
		if(!isVerified) return fix;
		
		if(isSourceWhole){
			sourceEnd = element.getMemberEnd().get(0);
			targetEnd = element.getMemberEnd().get(1);
			fix.includeModified(sourceEnd);
			fix.includeModified(targetEnd);
			fix.includeModified(element);
		}
		else{
			Fix newFix = fixer.invertEnds(element);
			sourceEnd = ((Association) newFix.getModified().get(0)).getMemberEnd().get(0);
			targetEnd = ((Association) newFix.getModified().get(0)).getMemberEnd().get(1);
			fix.addAll(newFix);
		}
		
		if(isComposite){
			sourceEnd.setAggregation(AggregationKind.COMPOSITE);	
		}
		else{
			sourceEnd.setAggregation(AggregationKind.SHARED);
		}
		targetEnd.setAggregation(AggregationKind.NONE);
			
		return fix;
	}
	
	@Override
	public boolean hasAction(){
		return isVerified;
	}

	public void setSourceAsWhole(boolean isComposite) {
		isSourceWhole = true;
		this.isComposite = isComposite;
		this.isVerified = true;
	}

	public void setTargetAsWhole(boolean isComposite) {
		isSourceWhole = false;
		this.isComposite = isComposite;
		this.isVerified = true;
	}

}
