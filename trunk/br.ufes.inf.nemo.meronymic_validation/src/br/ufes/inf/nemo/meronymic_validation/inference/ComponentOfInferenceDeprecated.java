package br.ufes.inf.nemo.meronymic_validation.inference;

/*
 * This class infers componentOf relations from pre-defined structures presented in G. Guizzardi's paper
 * entitled "The Problem of Transitivity of Part-Whole Relations in Conceptual Modeling Revisited"
 * 
 */

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EcoreFactory;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.meronymic_validation.graph.Node;

import RefOntoUML.AggregationKind;
import RefOntoUML.Classifier;
import RefOntoUML.Kind;
import RefOntoUML.LiteralInteger;
import RefOntoUML.LiteralUnlimitedNatural;
import RefOntoUML.Package;
import RefOntoUML.Property;
import RefOntoUML.RefOntoUMLFactory;
import RefOntoUML.Type;
import RefOntoUML.componentOf;

public class ComponentOfInferenceDeprecated {

	private OntoUMLParser parser;
	private RefOntoUMLFactory factory;
	private ArrayList<DerivedMeronymic> inferredCompositions;
	private HashMap<Classifier,ArrayList<Property>> directPartsHash;
	
	public ArrayList<DerivedMeronymic> getInferredCompositions() {
		return inferredCompositions;
	}


	public ComponentOfInferenceDeprecated (OntoUMLParser parser){
//		this.parser = parser;
//		inferredCompositions = new ArrayList<>();
//		directPartsHash = setDirectPartsHash();
		
	}
//	
//	public OntoUMLParser infer(){
//		
//		parser.autoSelectDependencies(OntoUMLParser.COMPLETE_HIERARCHY, true);
//		
//		
//		HashMap<Classifier,ArrayList<PropertyPath>> pathHash = new HashMap<Classifier,ArrayList<PropertyPath>>();
//						
//		for (Classifier whole : directPartsHash.keySet()) {
//			
//			
//			for (Property part : directPartsHash.get(whole)) {
//				PropertyPath path = new PropertyPath(part);
//				boolean nodeAdded = true;
//				
//				while(nodeAdded){
//					for (Classifier wholeAux : directPartsHash.keySet()) {
//						
//						if(whole.equals(wholeAux))
//							continue;
//						
//						for (iterable_type iterable_element : iterable) {
//							
//						}
//						
//						
//					}
//				}
//				
//				if(path.getPath().size()>1){
//					if(!pathHash.containsKey(whole)){
//						ArrayList<PropertyPath> pathList = new ArrayList<PropertyPath>();
//						pathList.add(path);
//						pathHash.put(whole, pathList);
//					}
//					else{
//						pathHash.get(whole).add(path);
//					}
//				}
//					
//			}
//			
//		
//		}
//			
//		
//		return parser;
//	}
//	
//	
//	private void allPathsFrom(Classifier node, ArrayList<PropertyPath> existingPaths){
//		
//		if(node == null || directPartsHash.get(node) == null || directPartsHash.get(node).size()==0 )
//			return;
//		
//		if(existingPaths.size()==0){
//			for (Property p : directPartsHash.get(node)) {
//				existingPaths.add(new PropertyPath(p));
//				allPathsFrom((Classifier) p.getType(),existingPaths);
//			}
//			return;
//		}
//		
//		for (PropertyPath pp : existingPaths) {
//			for (Property p : directPartsHash.get(node)) {
//				PropertyPath newPath = new PropertyPath(pp);
//				newPath.addInPath(p);
//				existingPaths.add(newPath);
//				allPathsFrom((Classifier) p.getType(),existingPaths);
//			}
//		}
//	}
//	
//	private int calculateNumberOfPaths(Classifier node){
//		if(directPartsHash.get(node)==null)
//			return 1;
//		
//		int value;
//		
//		for (Property p : directPartsHash.get(node)) {
//			value += calculateNumberOfPaths(Node);
//		}
//		
//		return value;
//	}
//	
//	
//	//get the all direct and indirect parts of a given classifier;
//	private HashMap<Classifier,ArrayList<Property>> setDirectPartsHash(){
//		
//		HashMap<Classifier,ArrayList<Property>> directPartsHash = new HashMap<Classifier,ArrayList<Property>>();
//		
//		for (componentOf cp : parser.getAllInstances(componentOf.class)) {
//			
//			Property wholeEnd = parser.getWholeEnd(cp);
//			Property partEnd = parser.getPartEnd(cp);
//			
//			//ignores invalid elements
//			if(wholeEnd==null || wholeEnd.getType()==null || partEnd == null || partEnd.getType()==null)
//				continue;
//			
//			Type whole = wholeEnd.getType();
//			
//			if(!directPartsHash.containsKey(whole)){
//				//create the list of all direct parts
//				ArrayList<Property> directParts = new ArrayList<Property>();
//				directParts.add(partEnd);
//				directPartsHash.put((Classifier) whole, directParts);
//			}
//			else{
//				directPartsHash.get(whole).add(partEnd);
//			}
//		}
//		return directPartsHash;
//	}
//	
//	boolean loopInPath (componentOf comp, ArrayList<componentOf> path) {
//		
//		Classifier partToBeAdded = comp.part();
//		Classifier wholeToBeAdded = comp.whole();
//		
//		//verify if the relation to be inserted in the path is a self-type relation
//		if (partToBeAdded.equals(wholeToBeAdded))
//			return true;
//		
//		/*TODO: Verify with Giancarlo if the patterns follow when there are parts which are subtype of others*/
//		//if (whole.allChildren().contains(part) || whole.allParents().contains(part))
//			//return true;
//		
//		if (path!=null){
//			for (int i = 0; i < path.size(); i++) {
//				componentOf compositionInPath = path.get(i);
//				
//				if (compositionInPath.getEndType().contains(partToBeAdded))
//					return true;
//				
//				if(i<path.size()-1 && compositionInPath.getEndType().contains(wholeToBeAdded))
//					return true;
//				
//					
//				if (compositionInPath.whole().allChildren().contains(partToBeAdded) || compositionInPath.whole().allParents().contains(partToBeAdded) || 
//						compositionInPath.part().allChildren().contains(partToBeAdded) || compositionInPath.part().allParents().contains(partToBeAdded))
//					return true;
//			}
//		}
//		
//		return false;
//	}
//	
//	//creates derived componentOf relations
//	private ArrayList<componentOf> inferPatternA(ArrayList<ArrayList<componentOf>> explicitParts) {
//		
//		ArrayList<componentOf> inferredByPatternA = new ArrayList<>();
//		
//		Classifier whole = explicitParts.get(0).get(0).whole();
//		
//		for (ArrayList<componentOf> a : explicitParts) {
//			
//			Classifier part = a.get(a.size()-1).part();
//			
//			//if the current path from the whole to the part has more than one composition and there is no other which has one composition
//			//it creates a derived componentOf.
//			if(a.size()>1 && !isDirectFunctionalPartOf(whole, part)) {
//				
//				componentOf comp = assembleComponentOf(whole, part, a);
//				((Package) whole.eContainer()).getPackagedElement().add(comp);   
//				inferredByPatternA.add(comp);
//				compositions.add(comp);
//				
//			}
//		}
//		
//		return inferredByPatternA;
//	
//	}
//	
//	private ArrayList<componentOf> inferPatternB(ArrayList<ArrayList<componentOf>> explicitParts) {
//		
//		ArrayList<componentOf> inferredByPatternB = new ArrayList<>();
//		Classifier whole = explicitParts.get(0).get(0).whole();
//		
//		for (ArrayList<componentOf> a : explicitParts) {
//			
//			Classifier part = a.get(a.size()-1).part();
//			
//			for (Classifier wholeChild : whole.allChildren()) {
//				
//				if (!isDirectFunctionalPartOf(wholeChild, part)) {
//					componentOf comp = assembleComponentOf(wholeChild, part, a);
//					((Package) whole.eContainer()).getPackagedElement().add(comp);
//					inferredByPatternB.add(comp);
//					compositions.add(comp);
//			 	}	
//				
//			}	
//		}
//		return inferredByPatternB;
//	
//	}
//	
//	private ArrayList<componentOf> inferPatternC(ArrayList<ArrayList<componentOf>> explicitParts) {
//		
//		ArrayList<componentOf> inferredByPatternC = new ArrayList<>();
//		Classifier whole = explicitParts.get(0).get(0).whole();
//		
//		for (ArrayList<componentOf> a : explicitParts) {
//			
//			Classifier part = a.get(a.size()-1).part();
//		
//			for (Classifier partChild : part.allChildren()) {
//				
//				if (!isDirectFunctionalPartOf(whole, partChild)) {
//					componentOf comp = assembleComponentOf(whole, partChild, a);
//					((Package) whole.eContainer()).getPackagedElement().add(comp);
//					inferredByPatternC.add(comp);
//					compositions.add(comp);
//				}	
//			}
//				
//		}
//		return inferredByPatternC;
//	}
//	
//	//derives both ends cardinalities and meta-properties of the comp with regard to the provided path
//	private void inferMetaProperties (ArrayList<componentOf> path, componentOf comp){
//		
//		int partLower, partUpper, wholeLower, wholeUpper, size;
//		boolean isShareable, isEssential, isInseparable, isImmutableWhole, isImmutablePart, 
//		wholeIsReadOnly, partIsReadOnly, partIsOrdered, wholeIsOrdered, wholeIsComposite;
//		
//		size = path.size();
//		partLower = path.get(0).partEnd().getLower();
//		partUpper = path.get(0).partEnd().getUpper();
//		wholeLower = path.get(size-1).wholeEnd().getLower();
//		wholeUpper = path.get(size-1).wholeEnd().getUpper();
//		
//		partIsReadOnly = path.get(0).partEnd().isIsReadOnly();
//		partIsOrdered = path.get(0).partEnd().isIsOrdered();
//		
//		wholeIsReadOnly = path.get(0).wholeEnd().isIsReadOnly();
//		wholeIsOrdered = path.get(0).wholeEnd().isIsOrdered();
//		wholeIsComposite = path.get(0).wholeEnd().isIsComposite();
//		
//		isShareable = path.get(0).isIsShareable();
//		isEssential = path.get(0).isIsEssential();
//		isInseparable = path.get(0).isIsInseparable();
//		isImmutableWhole = path.get(0).isIsImmutableWhole();
//		isImmutablePart = path.get(0).isIsImmutablePart();
//		
//		//System.out.println("lower: "+partLower+" upper: "+partUpper);
//		
//		for (int i = 1; i < size; i++) {
//			
//			Property currentPartEnd = path.get(i).partEnd();
//			Property currentWholeEnd = path.get(size-1-i).wholeEnd();
//			
//			partLower *= currentPartEnd.getLower();
//			wholeLower *= currentWholeEnd.getLower();
//			
//			if(partUpper!=-1 && currentPartEnd.getUpper()!=-1)
//				partUpper *= currentPartEnd.getUpper();
//			else 
//				partUpper = -1;
//			
//			if(wholeUpper!=-1 && currentWholeEnd.getUpper()!=-1)
//				wholeUpper *= currentWholeEnd.getUpper();
//			else 
//				wholeUpper = -1;
//			
//			isShareable = isShareable && path.get(i).isIsShareable();
//			isEssential = isEssential && path.get(i).isIsEssential();
//			isInseparable = isInseparable && path.get(i).isIsEssential();
//			isImmutablePart = isImmutablePart && path.get(i).isIsImmutablePart();
//			isImmutableWhole = isImmutableWhole && path.get(i).isIsImmutableWhole();
//			
//			partIsReadOnly = partIsReadOnly && currentPartEnd.isIsReadOnly();
//			partIsOrdered = partIsOrdered && currentPartEnd.isIsOrdered();
//			
//			wholeIsOrdered = wholeIsOrdered && currentWholeEnd.isIsOrdered();
//			wholeIsReadOnly = wholeIsReadOnly && currentWholeEnd.isIsReadOnly();
//			wholeIsComposite = wholeIsComposite && currentWholeEnd.isIsComposite();
//			
//			//System.out.println("lower: "+partLower+" upper: "+partUpper);
//		}
//		
//		//if pattern B, the lower value on the part side will always be 0
//		if (comp.part().allParents().contains(path.get(size-1).part())) {
//			partLower = 0;
//			
//		}
//		
//		//if pattern C, the lower value on the whole side will always be 0
//		if (comp.whole().allParents().contains(path.get(0).whole()))
//			wholeLower = 0;
//				
//		
//		((LiteralInteger) comp.partEnd().getLowerValue()).setValue(partLower);
//		((LiteralUnlimitedNatural) comp.partEnd().getUpperValue()).setValue(partUpper);
//        comp.partEnd().setIsReadOnly(partIsReadOnly);
//        comp.partEnd().setIsOrdered(partIsOrdered);
//        
//		((LiteralInteger) comp.wholeEnd().getLowerValue()).setValue(wholeLower);
//        ((LiteralUnlimitedNatural)comp.wholeEnd().getUpperValue()).setValue(wholeUpper);
//        comp.wholeEnd().setIsReadOnly(wholeIsReadOnly);
//        comp.wholeEnd().setIsOrdered(wholeIsOrdered);
//        
//        if(wholeUpper==1 && wholeIsComposite)
//        	comp.wholeEnd().setAggregation(AggregationKind.COMPOSITE);
//        else
//        	comp.wholeEnd().setAggregation(AggregationKind.SHARED);
//        
//        comp.setIsShareable(isShareable);
//        comp.setIsEssential(isEssential);
//        comp.setIsInseparable(isInseparable);
//        comp.setIsImmutableWhole(isImmutableWhole);
//        comp.setIsImmutablePart(isImmutablePart);
//	}
//	
//	private componentOf assembleComponentOf (Classifier whole, Classifier part, ArrayList<componentOf> path){
//		
//		componentOf comp = factory.createcomponentOf();
//		
//		Property partProperty = factory.createProperty();
//		Property wholeProperty = factory.createProperty();
//		LiteralInteger partLowerValue = factory.createLiteralInteger();
//        LiteralUnlimitedNatural partUpperValue = factory.createLiteralUnlimitedNatural();
//        LiteralInteger wholeLowerValue = factory.createLiteralInteger();
//        LiteralUnlimitedNatural wholeUpperValue = factory.createLiteralUnlimitedNatural();
//		
//		comp.setIsDerived(true);
//		comp.setIsAbstract(false);
//		comp.setName("derived_"+whole.getName()+"_"+part.getName());
//		comp.getMemberEnd().add(wholeProperty);
//		comp.getMemberEnd().add(partProperty);
//		comp.getOwnedEnd().add(wholeProperty);
//		comp.getOwnedEnd().add(partProperty);
//		
//		partProperty.setType(part);
//		partProperty.setIsDerived(true);
//		partProperty.setName(part.getName().toLowerCase());
//		partProperty.setLowerValue(partLowerValue);
//		partProperty.setUpperValue(partUpperValue);
//		
//		wholeProperty.setType(whole);
//		wholeProperty.setIsDerived(true);
//		wholeProperty.setName(whole.getName().toLowerCase());
//		wholeProperty.setLowerValue(wholeLowerValue);
//		wholeProperty.setUpperValue(wholeUpperValue);
//		
//		inferMetaProperties(path, comp);
//		
//		try {
//			comp.getEAnnotations().add(generateOCLDerivationRule(comp, path));
//		} catch (Exception e) {
//			/*TODO
//			 * Decide what to do when there is no name in the association ends in the paths used to generate
//			 * the ocl derivation rule 
//			 */
//			 System.out.println(e);
//			 
//		}
//        
//        return comp;
//	}
//	
//	private EAnnotation generateOCLDerivationRule (componentOf comp, ArrayList<componentOf> path) throws Exception{
//		
//		String typeName, propertyName, propertyTypeName, expression, rule;
//		
//		EcoreFactory ecoreFactory = EcoreFactory.eINSTANCE;
//		EAnnotation annotation = ecoreFactory.createEAnnotation();
//		
//		/*
//		 * context Typename::propertyName: Type
//		 * derive: expression representing the initial value
//		 */
//		
//		typeName = comp.whole().getName(); 
//		propertyName = comp.partEnd().getName();
//		propertyTypeName = comp.part().getName();
//		
//		typeName = addQuotes(typeName);
//		propertyName = addQuotes(propertyName);
//		propertyTypeName = addQuotes(propertyTypeName);
//		
//		expression = "self";
//		
//				
//		//if pattern C, a upcast to the supertype is needed
//		if (comp.whole().allParents().contains(path.get(0).whole()))
//			expression += ".oclAsType("+addQuotes(path.get(0).whole().getName())+")";
//		
//		for (componentOf cp : path) {
//			String propertyInPathName = cp.partEnd().getName();
//		
//			if (propertyInPathName.trim() == null || propertyInPathName.trim()=="")
//				throw new Exception("In order to generate the OCL derivation, all association ends must be unambigously named");
//		
//			else
//				expression += "."+propertyInPathName;
//		}
//		
//
//		//if pattern B, a downcast is needed
//		if (comp.part().allParents().contains(path.get(path.size()-1).part()))
//			expression += "->selectByType("+propertyTypeName+")";
//		
//		rule = 	"context "+typeName+"::"+propertyName+" : "+propertyTypeName+"\n"+
//				"derive : "+expression;
//		/*TODO: Verify how to build an annotation properly. It will depend on how it is used in OLED.
//		 * */				
//		annotation.setSource(rule);
//		
//		return annotation;
//	}
//	
//	private String addQuotes (String name){
//		return "_'"+name+"'";
//	}
//	
//	private boolean isDirectFunctionalPartOf (Classifier whole, Classifier part){
//		
//		for (componentOf cp : compositions){
//			if (cp.getEndType().contains(whole) && cp.getEndType().contains(part))
//				return true;
//		}
//		
//		for (componentOf cp : inferredCompositions){
//			if (cp.getEndType().contains(whole) && cp.getEndType().contains(part))
//				return true;
//		}
//						
//		return false;
//	}
}
