package br.ufes.inf.nemo.common.ontoumlparser;

import java.util.ArrayList;
import java.util.HashMap;

import RefOntoUML.Classifier;
import RefOntoUML.Kind;
import RefOntoUML.LiteralInteger;
import RefOntoUML.LiteralUnlimitedNatural;
import RefOntoUML.Meronymic;
import RefOntoUML.Package;
import RefOntoUML.Property;
import RefOntoUML.RefOntoUMLFactory;
import RefOntoUML.componentOf;

public class Derivator {

	private OntoUMLParser parser;
	private RefOntoUMLFactory factory;
	private ArrayList<componentOf> componentOfList;
	private ArrayList<Kind> kinds;
	
	public Derivator (OntoUMLParser parser){
		this.parser = parser;
		this.factory = RefOntoUMLFactory.eINSTANCE;
		
		kinds = new ArrayList<>();
		kinds.addAll(parser.getAllInstances(Kind.class));
		
		componentOfList = new ArrayList<>();
		componentOfList.addAll(parser.getAllInstances(componentOf.class));
		
	}
	
	
	public void createComponentOfPatterns(){
		
		for (Kind k : kinds) {
			
			ArrayList<ArrayList<componentOf>> parts;
			parts = allParts(k,null);
			
			if (parts.size()!=0){
				
				System.out.println("W: "+parser.getStringRepresentation(parts.get(0).get(0).whole()));
				
				for (ArrayList<componentOf> a : parts) {
					System.out.print("\t");
					
					for (componentOf cp : a) {
						System.out.print(parser.getStringRepresentation(cp.part())+ ", ");
					}
					System.out.println();
				}
				System.out.println();
				
				createComponentOfPatternA(parts);
				createComponentOfPatternB(parts);
				createComponentOfPatternC(parts);
				
				parser = new OntoUMLParser(parser.getModel());
				
				System.out.println("***");
				
				
				System.out.println("*************");
			}
			
		}
	}
	
	//get the all direct and indirect parts of a given classifier;
	private ArrayList<ArrayList<componentOf>> allParts (Classifier whole, ArrayList<componentOf> path){
		
		ArrayList<ArrayList<componentOf>> parts = new ArrayList<>();
		
		for (componentOf cp : componentOfList) {
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
				componentOf comp = createComponentOf(whole, part);
				((Package) whole.eContainer()).getPackagedElement().add(comp);
		        System.out.println("A - componentOf created! "+ comp);
			}
		}
	
	}
	
	private void createComponentOfPatternB(ArrayList<ArrayList<componentOf>> explicitParts) {
		
		Classifier whole = explicitParts.get(0).get(0).whole();
		
		for (ArrayList<componentOf> a : explicitParts) {
			
			Classifier part = a.get(a.size()-1).part();
			
			for (Classifier wholeChild : whole.allChildren()) {
				
				if (!isFunctionalPartOf(wholeChild, part)) {
					componentOf comp = createComponentOf(wholeChild, part);
					((Package) whole.eContainer()).getPackagedElement().add(comp);
			        System.out.println("B - componentOf created! "+ comp);
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
					componentOf comp = createComponentOf(whole, partChild);
					((Package) whole.eContainer()).getPackagedElement().add(comp);
			        System.out.println("C - componentOf created! "+ comp);
				}	
			}
				
		}
	}
	
	private componentOf createComponentOf (Classifier whole, Classifier part){
		
		componentOf comp = factory.createcomponentOf();
		
		Property partProperty = factory.createProperty();
		Property wholeProperty = factory.createProperty();
		LiteralInteger partLowerValue = factory.createLiteralInteger();
        LiteralUnlimitedNatural partUpperValue = factory.createLiteralUnlimitedNatural();
        LiteralInteger wholeLowerValue = factory.createLiteralInteger();
        LiteralUnlimitedNatural wholeUpperValue = factory.createLiteralUnlimitedNatural();
		
		comp.setIsDerived(true);
		comp.setName("derived");
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
		
        partLowerValue.setValue(0);
        partUpperValue.setValue(-1);
        
        wholeLowerValue.setValue(0);
        wholeUpperValue.setValue(-1);
        
        return comp;
	}
	
	
	private boolean isFunctionalPartOf (Classifier whole, Classifier part){
		
		for (componentOf cp : componentOfList){
			if (cp.whole().equals(whole) && cp.part().equals(part))
				return true;
		}
						
		return false;
	}
}
