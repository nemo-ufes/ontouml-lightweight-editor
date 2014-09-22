package br.ufes.inf.nemo.validator.meronymic.checkers;

import java.util.ArrayList;
import java.util.HashSet;

import RefOntoUML.AntiRigidSortalClass;
import RefOntoUML.Classifier;
import RefOntoUML.Collective;
import RefOntoUML.MixinClass;
import RefOntoUML.Quantity;
import RefOntoUML.SubKind;
import RefOntoUML.componentOf;
import RefOntoUML.memberOf;
import RefOntoUML.subCollectionOf;
import RefOntoUML.subQuantityOf;
import RefOntoUML.parser.OntoUMLParser;

public class MeronymicEndsChecker extends Checker<MeronymicEndsError>{
	
	ArrayList<componentOf> componentOfWithError;
	ArrayList<subQuantityOf> subQuantityOfWithError;
	ArrayList<memberOf> memberOfWithError;
	ArrayList<subCollectionOf> subCollectionOfWithError;
	
	public MeronymicEndsChecker(OntoUMLParser parser) {
		super(parser);
		componentOfWithError = null;
		subQuantityOfWithError = null;
		memberOfWithError = null;
		subCollectionOfWithError = null;
	}
	
	@Override
	public boolean check(){
		checkComponentOf();
		checkSubQuantityOf();
		checkMemberOf();
		checkSubCollectionOf();
		
		errors.clear();
		
		for (componentOf cp : componentOfWithError) {
			errors.add(new MeronymicEndsError(parser, cp));
		}
		
		for (memberOf mb : memberOfWithError) {
			errors.add(new MeronymicEndsError(parser, mb));
		}
		
		for (subCollectionOf sc : subCollectionOfWithError) {
			errors.add(new MeronymicEndsError(parser, sc));
		}
		
		for (subQuantityOf sq : subQuantityOfWithError) {
			errors.add(new MeronymicEndsError(parser, sq));
		}
	
		if(errors.size()>0)
			return false;
		
		return true;
	}
	
	public void checkComponentOf(){
		
		if(componentOfWithError==null)
			componentOfWithError = new ArrayList<componentOf>();
		else
			componentOfWithError.clear();
		
		for (componentOf compOf : parser.getAllInstances(componentOf.class)) {
			Classifier whole = (Classifier) OntoUMLParser.getWholeEnd(compOf).getType();
			Classifier part = (Classifier) OntoUMLParser.getPartEnd(compOf).getType();
			
			if(!parser.isFunctionalComplex(whole) || !parser.isFunctionalComplex(part))
				componentOfWithError.add(compOf);
		}
	}
	
	public void checkSubQuantityOf(){
		
		if(subQuantityOfWithError==null)
			subQuantityOfWithError = new ArrayList<subQuantityOf>();
		else
			subQuantityOfWithError.clear();
		
		for (subQuantityOf subQuantOf : parser.getAllInstances(subQuantityOf.class)) {
			Classifier whole = (Classifier) OntoUMLParser.getWholeEnd(subQuantOf).getType();
			Classifier part = (Classifier) OntoUMLParser.getPartEnd(subQuantOf).getType();
			
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
			Classifier whole = (Classifier) OntoUMLParser.getWholeEnd(membOf).getType();
			Classifier part = (Classifier) OntoUMLParser.getPartEnd(membOf).getType();
			
			if(!isCollective(whole) || (!parser.isCollective(part) && !parser.isFunctionalComplex(part)))
				memberOfWithError.add(membOf);
		}
	}

	public void checkSubCollectionOf(){
		
		if(subCollectionOfWithError==null)
			subCollectionOfWithError = new ArrayList<subCollectionOf>();
		else
			subCollectionOfWithError.clear();
		
		for (subCollectionOf subColOf : parser.getAllInstances(subCollectionOf.class)) {
			Classifier whole = (Classifier) OntoUMLParser.getWholeEnd(subColOf).getType();
			Classifier part = (Classifier) OntoUMLParser.getPartEnd(subColOf).getType();
			
			if(!isCollective(whole) || !isCollective(part))
				subCollectionOfWithError.add(subColOf);
		}
		
	}
	
	
	
	public boolean isQuantity(Classifier c){
		
		if(c instanceof Quantity)
			return true;
		
		if(c instanceof SubKind || c instanceof AntiRigidSortalClass){
			HashSet<Classifier> identityProviders = parser.getIdentityProvider(c);
			if(identityProviders.size()==1 && identityProviders.toArray()[0] instanceof Quantity)
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
			HashSet<Classifier> identityProviders = parser.getIdentityProvider(c);
			if(identityProviders.size()==1 && identityProviders.toArray()[0] instanceof Collective)
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

	@Override
	public String checkerName() {
		return "Well-Formed Meronymics";
	}

	
	
	
}
