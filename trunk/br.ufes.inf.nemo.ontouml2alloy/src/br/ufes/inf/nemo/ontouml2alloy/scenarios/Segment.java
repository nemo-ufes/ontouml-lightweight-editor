package br.ufes.inf.nemo.ontouml2alloy.scenarios;

import java.util.Iterator;

import RefOntoUML.Classifier;
import RefOntoUML.parser.OntoUMLParser;

enum SegmentType {POPULATION, OBJECT, PROPERTY, STEREOTYPE, CLASSIFIER}

public class Segment {
	
	private OntoUMLParser parser;	
	public SegmentType seg;

	//class or association that characterize the segment (only for SegmentType=CLASS or ASSCOCIATION)
	Classifier classifier;
	//Metatype that characterizes the segment (only for SegmentType=STEREOTYPE_CLASS or STEREOTYPE_ASS)
	Class<? extends Classifier> metaType;
	
	public Segment(OntoUMLParser parser) {
		this.parser = parser;
	}
	
	private String getUnionExpression(Class<? extends Classifier> metaType){
		
		Iterator<? extends Classifier> i = parser.getAllInstances(metaType).iterator();
		String s = "(";
		
		while(i.hasNext()){
			Classifier c = i.next();
			s+=getAlias(c);
			if(i.hasNext())
				s+="+";
		}
		
		s+=")";
		return s;	
	}
	
	private String getAlias(Classifier c) {
		//TODO: check when relation is ordered or material derived by a relator
		return parser.getAlias(c);
	}
	
	public String getName() {
		switch(seg){
			case POPULATION:
				return "exists";
			case OBJECT:
				return "exists:>Object";
			case PROPERTY:
				return "exists:>Property";
			case CLASSIFIER:
				return getAlias(classifier);
			case STEREOTYPE:
				return getUnionExpression(metaType);
			default:
				return "UNNAMED_SEGMENT";
		}
	}
	
	public void setAsPopulation(){
		seg = SegmentType.POPULATION;
	}
	
	public void setAsObject(){
		seg = SegmentType.OBJECT;
	}
	
	public void setAsProperty(){
		seg = SegmentType.PROPERTY;
	}
	
	public void setAsClassifier(Classifier classifier){
		seg = SegmentType.CLASSIFIER;
		this.classifier = classifier;
	}
	
	public void setAsStereotype(Class<? extends Classifier> metaType){
		seg = SegmentType.STEREOTYPE;
		this.metaType = metaType;
	}
}

