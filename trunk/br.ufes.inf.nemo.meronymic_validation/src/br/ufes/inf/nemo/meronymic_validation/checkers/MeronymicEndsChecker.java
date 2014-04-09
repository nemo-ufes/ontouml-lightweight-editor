package br.ufes.inf.nemo.meronymic_validation.checkers;

import java.util.ArrayList;

import RefOntoUML.AntiRigidSortalClass;
import RefOntoUML.Classifier;
import RefOntoUML.Collective;
import RefOntoUML.Kind;
import RefOntoUML.Meronymic;
import RefOntoUML.MixinClass;
import RefOntoUML.Quantity;
import RefOntoUML.SubKind;
import RefOntoUML.componentOf;
import RefOntoUML.memberOf;
import RefOntoUML.subCollectionOf;
import RefOntoUML.subQuantityOf;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class MeronymicEndsChecker {
	OntoUMLParser parser;
	
	ArrayList<componentOf> componentOfWithError;
	ArrayList<subQuantityOf> subQuantityOfWithError;
	ArrayList<memberOf> memberOfWithError;
	ArrayList<subCollectionOf> subCollectionOfWithError;
	
	public MeronymicEndsChecker(OntoUMLParser parser) {
		this.parser = parser;
		componentOfWithError = null;
		subQuantityOfWithError = null;
		memberOfWithError = null;
		subCollectionOfWithError = null;
	}
	
	public void check(){
		checkComponentOf();
		checkSubQuantityOf();
		checkMemberOf();
		checkSubCollectionOf();
	}
	
	public void checkComponentOf(){
		
		if(componentOfWithError==null)
			componentOfWithError = new ArrayList<componentOf>();
		else
			componentOfWithError.clear();
		
		for (componentOf compOf : parser.getAllInstances(componentOf.class)) {
			Classifier whole = (Classifier) parser.getWholeEnd(compOf).getType();
			Classifier part = (Classifier) parser.getPartEnd(compOf).getType();
			
			if(!isFunctionalComplex(whole) || !isFunctionalComplex(part))
				componentOfWithError.add(compOf);
		}
	}
	
	public void checkSubQuantityOf(){
		
		if(subQuantityOfWithError==null)
			subQuantityOfWithError = new ArrayList<subQuantityOf>();
		else
			subQuantityOfWithError.clear();
		
		for (subQuantityOf subQuantOf : parser.getAllInstances(subQuantityOf.class)) {
			Classifier whole = (Classifier) parser.getWholeEnd(subQuantOf).getType();
			Classifier part = (Classifier) parser.getPartEnd(subQuantOf).getType();
			
			if(!isQuantity(whole) || !isQuantity(part))
				subQuantityOfWithError.add(subQuantOf);
		}
		
	}

	public void checkMemberOf(){
		if(memberOfWithError==null)
			memberOfWithError = new ArrayList<memberOf>();
		else
			memberOfWithError.clear();
		
		for (memberOf membOf : parser.getAllInstances(memberOf.class)) {
			Classifier whole = (Classifier) parser.getWholeEnd(membOf).getType();
			Classifier part = (Classifier) parser.getPartEnd(membOf).getType();
			
			if(!isCollective(whole) || (!isCollective(part) && !isFunctionalComplex(part)))
				memberOfWithError.add(membOf);
		}
	}

	public void checkSubCollectionOf(){
		
		if(subCollectionOfWithError==null)
			subCollectionOfWithError = new ArrayList<subCollectionOf>();
		else
			subCollectionOfWithError.clear();
		
		for (subCollectionOf subColOf : parser.getAllInstances(subCollectionOf.class)) {
			Classifier whole = (Classifier) parser.getWholeEnd(subColOf).getType();
			Classifier part = (Classifier) parser.getPartEnd(subColOf).getType();
			
			if(!isCollective(whole) || !isCollective(part))
				subCollectionOfWithError.add(subColOf);
		}
		
	}
	
	public boolean isFunctionalComplex(Classifier c){
		
		if(c instanceof Kind)
			return true;
		
		if(c instanceof SubKind || c instanceof AntiRigidSortalClass){
			ArrayList<Classifier> identityProviders = parser.getIdentityProvider(c);
			if(identityProviders.size()==1 && identityProviders.get(0) instanceof Kind)
				return true;
		}
		
		if(c instanceof MixinClass){
			for (Classifier child : parser.getChildren(c)) {
				if(!isFunctionalComplex(child))
					return false;
			}
			return true;
		}
		
		return false;
	}
	
	public boolean isQuantity(Classifier c){
		
		if(c instanceof Quantity)
			return true;
		
		if(c instanceof SubKind || c instanceof AntiRigidSortalClass){
			ArrayList<Classifier> identityProviders = parser.getIdentityProvider(c);
			if(identityProviders.size()==1 && identityProviders.get(0) instanceof Quantity)
				return true;
		}
		
		if(c instanceof MixinClass){
			for (Classifier child : parser.getChildren(c)) {
				if(!isQuantity(child))
					return false;
			}
			return true;
		}
		
		return false;
	}
	
	public boolean isCollective(Classifier c){
		
		if(c instanceof Collective)
			return true;
		
		if(c instanceof SubKind || c instanceof AntiRigidSortalClass){
			ArrayList<Classifier> identityProviders = parser.getIdentityProvider(c);
			if(identityProviders.size()==1 && identityProviders.get(0) instanceof Collective)
				return true;
		}
		
		if(c instanceof MixinClass){
			for (Classifier child : parser.getChildren(c)) {
				if(!isCollective(child))
					return false;
			}
			return true;
		}
		
		return false;
	}

	public ArrayList<componentOf> getComponentOfWithError() {
		if(componentOfWithError==null)
			checkComponentOf();
		return componentOfWithError;
	}

	public ArrayList<subQuantityOf> getSubQuantityOfWithError() {
		if(subQuantityOfWithError==null)
			checkSubQuantityOf();
		return subQuantityOfWithError;
	}

	public ArrayList<memberOf> getMemberOfWithError() {
		if(memberOfWithError==null)
			checkMemberOf();
		return memberOfWithError;
	}

	public ArrayList<subCollectionOf> getSubCollectionOfWithError() {
		if(subCollectionOfWithError==null)
			checkSubCollectionOf();
		return subCollectionOfWithError;
	}
	
	public ArrayList<Meronymic> getAllMeronymicWithError(){
		ArrayList<Meronymic> meronymics = new ArrayList<Meronymic>();

		meronymics.addAll(getComponentOfWithError());
		meronymics.addAll(getSubCollectionOfWithError());
		meronymics.addAll(getMemberOfWithError());
		meronymics.addAll(getSubQuantityOfWithError());

		return meronymics;
	}
	
}
