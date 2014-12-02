package br.ufes.inf.nemo.ontouml2alloy.scenarios;

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;

import br.ufes.inf.nemo.common.ontoumlfixer.ClassStereotype;
import br.ufes.inf.nemo.common.ontoumlfixer.RelationStereotype;
import br.ufes.inf.nemo.common.ontoumlfixer.Stereotype;

import RefOntoUML.Association;
import RefOntoUML.parser.OntoUMLNameHelper;
import RefOntoUML.parser.OntoUMLParser;

public class Segment {
	
	private OntoUMLParser parser;	
	public SegmentType segmentType;

	//class or association that characterize the segment (only for SegmentType=CLASS or ASSCOCIATION)
	EObject classifier;
	//Metatype that characterizes the segment (only for SegmentType=STEREOTYPE_CLASS or STEREOTYPE_ASS)
	Class<? extends EObject> metaType;
	
	public Segment(OntoUMLParser parser) {
		this.parser = parser;
	}
	
	public EObject getClassifier(){
		return classifier;
	}
	
	public Stereotype getStereotype(){
		for (ClassStereotype cs : ClassStereotype.values()) {
			if(cs.getMetaclass().equals(metaType))
				return cs;
		}
		
		for (RelationStereotype rs : RelationStereotype.values()) {
			if(rs.getMetaclass().equals(metaType))
				return rs;
		}
		
		return null;
	}
	
	
	private String getUnionExpression(Class<? extends EObject> metaType){
		
		Iterator<? extends EObject> i = parser.getAllInstances(metaType).iterator();
		String s = "(";
		
		while(i.hasNext()){
			EObject c = i.next();
			s+=getAlias(c);
			if(i.hasNext())
				s+="+";
		}
		
		s+=")";
		return s;	
	}
	
	private String getAlias(EObject c) {
		//TODO: check when relation is ordered or material derived by a relator
		return parser.getAlias(c);
	}
	
	public String getName() {
		switch(segmentType){
			case POPULATION:
				return "exists";
			case OBJECT:
				return "exists:>Object";
			case PROPERTY:
				return "exists:>Property";
			case CLASS:
			case ASSOCIATION:
				return getAlias(classifier);
			case STEREOTYPE:
				return getUnionExpression(metaType);
			default:
				return "UNNAMED_SEGMENT";
		}
	}
	
	public void setAsPopulation(){
		segmentType = SegmentType.POPULATION;
	}
	
	public void setAsObject(){
		segmentType = SegmentType.OBJECT;
	}
	
	public void setAsProperty(){
		segmentType = SegmentType.PROPERTY;
	}
	
	public void setAsClass(RefOntoUML.Class c){
		segmentType = SegmentType.CLASS;
		this.classifier = c;
	}
	
	public void setAsAssociation(Association a){
		segmentType = SegmentType.ASSOCIATION;
		this.classifier = a;
	}
	
	public void setAsStereotype(Class<? extends EObject> metaType){
		segmentType = SegmentType.STEREOTYPE;
		this.metaType = metaType;
	}
	
	@Override
	public String toString(){
		String s = segmentType.toString();
		
		if(segmentType==SegmentType.STEREOTYPE)
			s+=" "+metaType.toString();
		
		if(segmentType==SegmentType.CLASS || segmentType==SegmentType.ASSOCIATION)
			s+=" "+OntoUMLNameHelper.getCommonName(classifier);
		
		return s;
	}

	public SegmentType getType() {
		return segmentType;
	}

	public java.lang.Class<?> getMetaClass() {
		return metaType;
	}
	

}

