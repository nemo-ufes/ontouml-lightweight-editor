package br.ufes.inf.nemo.common.ontoumlparser;

import java.util.ArrayList;

import RefOntoUML.AggregationKind;
import RefOntoUML.Classifier;
import RefOntoUML.Kind;
import RefOntoUML.LiteralInteger;
import RefOntoUML.LiteralUnlimitedNatural;
import RefOntoUML.Package;
import RefOntoUML.Property;
import RefOntoUML.RefOntoUMLFactory;
import RefOntoUML.componentOf;

public class Derivator {

	private OntoUMLParser parser;
	private RefOntoUMLFactory factory;
	private ArrayList<componentOf> originalCompositions;
	private ArrayList<Kind> kinds;
	private ArrayList<componentOf> generatedCompositions;
	
	public ArrayList<componentOf> getGeneratedCompositions() {
		return generatedCompositions;
	}


	public Derivator (OntoUMLParser parser){
		this.parser = parser;
		this.factory = RefOntoUMLFactory.eINSTANCE;
		
		kinds = new ArrayList<>();
		kinds.addAll(parser.getAllInstances(Kind.class));
		
		originalCompositions = new ArrayList<>();
		originalCompositions.addAll(parser.getAllInstances(componentOf.class));
		
		generatedCompositions = new ArrayList<>();
		
	}
	
	
	public OntoUMLParser createComponentOfPatterns(){
		
		for (Kind k : kinds) {
			
			ArrayList<ArrayList<componentOf>> parts;
			parts = allParts(k,null);
			
			if (parts.size()!=0){
				createComponentOfPatternA(parts);
				createComponentOfPatternB(parts);
				createComponentOfPatternC(parts);
			}	
		}
		if (generatedCompositions.size()>0)
			return new OntoUMLParser(parser.getModel());
		
		return parser;
	}
	
	//get the all direct and indirect parts of a given classifier;
	private ArrayList<ArrayList<componentOf>> allParts (Classifier whole, ArrayList<componentOf> path){
		
		ArrayList<ArrayList<componentOf>> parts = new ArrayList<>();
		
		for (componentOf cp : originalCompositions) {
			if(cp.whole().equals(whole)){
				
				ArrayList<componentOf> newPath = new ArrayList<>();
				
				if (path!=null)
					newPath.addAll(path);
				
				newPath.add(cp);
				
				parts.add(newPath);
				
				parts.addAll(allParts(cp.part(), newPath));
			}
		}
		
		return parts;
	}
	
	//creates derived componentOf relations
	private void createComponentOfPatternA(ArrayList<ArrayList<componentOf>> explicitParts) {
		
		Classifier whole = explicitParts.get(0).get(0).whole();
		
		for (ArrayList<componentOf> a : explicitParts) {
			
			Classifier part = a.get(a.size()-1).part();
			
			//if the current path from the whole to the part has more than one composition and there is no other which has one composition
			//it creates a derived componentOf.
			if(a.size()>1 && !isFunctionalPartOf(whole, part)) {
				
				componentOf comp = assembleComponentOf(whole, part, a);
				((Package) whole.eContainer()).getPackagedElement().add(comp);   
			}
		}
	
	}
	
	private void createComponentOfPatternB(ArrayList<ArrayList<componentOf>> explicitParts) {
		
		Classifier whole = explicitParts.get(0).get(0).whole();
		
		for (ArrayList<componentOf> a : explicitParts) {
			
			Classifier part = a.get(a.size()-1).part();
			
			for (Classifier wholeChild : whole.allChildren()) {
				
				if (!isFunctionalPartOf(wholeChild, part)) {
					componentOf comp = assembleComponentOf(wholeChild, part, a);
					((Package) whole.eContainer()).getPackagedElement().add(comp);
			 	}	
				
			}
				
		}
	
	}
	
	private void createComponentOfPatternC(ArrayList<ArrayList<componentOf>> explicitParts) {
		
		Classifier whole = explicitParts.get(0).get(0).whole();
		
		for (ArrayList<componentOf> a : explicitParts) {
			
			Classifier part = a.get(a.size()-1).part();
		
			for (Classifier partChild : part.allChildren()) {
				
				if (!isFunctionalPartOf(whole, partChild)) {
					componentOf comp = assembleComponentOf(whole, partChild, a);
					((Package) whole.eContainer()).getPackagedElement().add(comp);
				}	
			}
				
		}
	}
	
	//derives both ends cardinalities and meta-properties of the comp with regard to the provided path
	private void deriveCompositionProperties (ArrayList<componentOf> path, componentOf comp){
		
		int partLower, partUpper, wholeLower, wholeUpper, size;
		boolean isShareable, isEssential, isInseparable, isImmutableWhole, isImmutablePart, 
		wholeIsReadOnly, partIsReadOnly, partIsOrdered, wholeIsOrdered, wholeIsComposite;
		
		size = path.size();
		partLower = path.get(0).partEnd().getLower();
		partUpper = path.get(0).partEnd().getUpper();
		wholeLower = path.get(size-1).wholeEnd().getLower();
		wholeUpper = path.get(size-1).wholeEnd().getUpper();
		
		partIsReadOnly = path.get(0).partEnd().isIsReadOnly();
		partIsOrdered = path.get(0).partEnd().isIsOrdered();
		
		wholeIsReadOnly = path.get(0).wholeEnd().isIsReadOnly();
		wholeIsOrdered = path.get(0).wholeEnd().isIsOrdered();
		wholeIsComposite = path.get(0).wholeEnd().isIsComposite();
		
		isShareable = path.get(0).isIsShareable();
		isEssential = path.get(0).isIsEssential();
		isInseparable = path.get(0).isIsInseparable();
		isImmutableWhole = path.get(0).isIsImmutableWhole();
		isImmutablePart = path.get(0).isIsImmutablePart();
		
		//System.out.println("lower: "+partLower+" upper: "+partUpper);
		
		for (int i = 1; i < size; i++) {
			
			Property currentPartEnd = path.get(i).partEnd();
			Property currentWholeEnd = path.get(size-1-i).wholeEnd();
			
			partLower *= currentPartEnd.getLower();
			wholeLower *= currentWholeEnd.getLower();
			
			if(partUpper!=-1 && currentPartEnd.getUpper()!=-1)
				partUpper *= currentPartEnd.getUpper();
			else 
				partUpper = -1;
			
			if(wholeUpper!=-1 && currentWholeEnd.getUpper()!=-1)
				wholeUpper *= currentWholeEnd.getUpper();
			else 
				wholeUpper = -1;
			
			isShareable = isShareable && path.get(i).isIsShareable();
			isEssential = isEssential && path.get(i).isIsEssential();
			isInseparable = isInseparable && path.get(i).isIsEssential();
			isImmutablePart = isImmutablePart && path.get(i).isIsImmutablePart();
			isImmutableWhole = isImmutableWhole && path.get(i).isIsImmutableWhole();
			
			partIsReadOnly = partIsReadOnly && currentPartEnd.isIsReadOnly();
			partIsOrdered = partIsOrdered && currentPartEnd.isIsOrdered();
			
			wholeIsOrdered = wholeIsOrdered && currentWholeEnd.isIsOrdered();
			wholeIsReadOnly = wholeIsReadOnly && currentWholeEnd.isIsReadOnly();
			wholeIsComposite = wholeIsComposite && currentWholeEnd.isIsComposite();
			
			//System.out.println("lower: "+partLower+" upper: "+partUpper);
		}
		
		((LiteralInteger) comp.partEnd().getLowerValue()).setValue(partLower);
		((LiteralUnlimitedNatural) comp.partEnd().getUpperValue()).setValue(partUpper);
        comp.partEnd().setIsReadOnly(partIsReadOnly);
        comp.partEnd().setIsOrdered(partIsOrdered);
        
		((LiteralInteger) comp.wholeEnd().getLowerValue()).setValue(wholeLower);
        ((LiteralUnlimitedNatural)comp.wholeEnd().getUpperValue()).setValue(wholeUpper);
        comp.wholeEnd().setIsReadOnly(wholeIsReadOnly);
        comp.wholeEnd().setIsOrdered(wholeIsOrdered);
        
        if(wholeUpper==1 && wholeIsComposite)
        	comp.wholeEnd().setAggregation(AggregationKind.COMPOSITE);
        else
        	comp.wholeEnd().setAggregation(AggregationKind.SHARED);
        
        comp.setIsShareable(isShareable);
        comp.setIsEssential(isEssential);
        comp.setIsInseparable(isInseparable);
        comp.setIsImmutableWhole(isImmutableWhole);
        comp.setIsImmutablePart(isImmutablePart);
	}
	
	private componentOf assembleComponentOf (Classifier whole, Classifier part, ArrayList<componentOf> path){
		
		componentOf comp = factory.createcomponentOf();
		
		Property partProperty = factory.createProperty();
		Property wholeProperty = factory.createProperty();
		LiteralInteger partLowerValue = factory.createLiteralInteger();
        LiteralUnlimitedNatural partUpperValue = factory.createLiteralUnlimitedNatural();
        LiteralInteger wholeLowerValue = factory.createLiteralInteger();
        LiteralUnlimitedNatural wholeUpperValue = factory.createLiteralUnlimitedNatural();
		
		comp.setIsDerived(true);
		comp.setIsAbstract(false);
		comp.setName("derived_"+whole.getName()+"_"+part.getName());
		comp.getMemberEnd().add(wholeProperty);
		comp.getMemberEnd().add(partProperty);
		comp.getOwnedEnd().add(wholeProperty);
		comp.getOwnedEnd().add(partProperty);
		
		partProperty.setType(part);
		partProperty.setIsDerived(true);
		partProperty.setName(part.getName().toLowerCase());
		partProperty.setLowerValue(partLowerValue);
		partProperty.setUpperValue(partUpperValue);
		
		wholeProperty.setType(whole);
		wholeProperty.setIsDerived(true);
		wholeProperty.setName(whole.getName().toLowerCase());
		wholeProperty.setLowerValue(wholeLowerValue);
		wholeProperty.setUpperValue(wholeUpperValue);
		
		deriveCompositionProperties(path, comp);

        generatedCompositions.add(comp);
        
        return comp;
	}
	
	
	private boolean isFunctionalPartOf (Classifier whole, Classifier part){
		
		for (componentOf cp : originalCompositions){
			if (cp.whole().equals(whole) && cp.part().equals(part))
				return true;
		}
		
		for (componentOf cp : generatedCompositions){
			if (cp.whole().equals(whole) && cp.part().equals(part))
				return true;
		}
						
		return false;
	}
}
