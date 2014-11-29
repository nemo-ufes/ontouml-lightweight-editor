package br.ufes.inf.nemo.validator.meronymic.checkers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.JDialog;

import RefOntoUML.Classifier;
import RefOntoUML.Collective;
import RefOntoUML.DataType;
import RefOntoUML.Generalization;
import RefOntoUML.Kind;
import RefOntoUML.Meronymic;
import RefOntoUML.MixinClass;
import RefOntoUML.Mode;
import RefOntoUML.Phase;
import RefOntoUML.Quantity;
import RefOntoUML.Relator;
import RefOntoUML.Role;
import RefOntoUML.SubKind;
import RefOntoUML.SubstanceSortal;
import RefOntoUML.componentOf;
import RefOntoUML.memberOf;
import RefOntoUML.subCollectionOf;
import RefOntoUML.subQuantityOf;
import RefOntoUML.parser.OntoUMLNameHelper;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.common.ontoumlfixer.ClassStereotype;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;
import br.ufes.inf.nemo.common.ontoumlfixer.RelationStereotype;
import br.ufes.inf.nemo.validator.meronymic.checkers.ui.MeronymicEndsDialog;

public class MeronymicEndsError extends MeronymicError<Meronymic> {

	enum Nature {FUNCTIONAL, COLLECTIVE, QUANTITY, MODE, RELATOR, DATATYPE}
	
	private HashMap<Nature,Boolean> wholeNatureHash;
	private HashMap<Nature,Boolean> partNatureHash;
	private HashMap<Nature,Boolean> newWholeNatureHash;
	private HashMap<Nature,Boolean> newPartNatureHash;
	private boolean changeStereotype;
	private RelationStereotype newStereotype;
	
	public MeronymicEndsError(OntoUMLParser parser, Meronymic m) {
		super(parser,m);
		wholeNatureHash = setEndsNature((Classifier) OntoUMLParser.getWholeEnd(m).getType());
		partNatureHash = setEndsNature((Classifier) OntoUMLParser.getPartEnd(m).getType());
	}
	
	@Override
	public String getDescription() {
		return OntoUMLNameHelper.getCompleteName(element);
	}

	@Override
	public String getType() {
		if(element instanceof componentOf)
			return "Invalid ends' stereotypes for ComponentOf";
		if(element instanceof memberOf)
			return "Invalid ends' stereotypes for MemberOf";
		if(element instanceof subCollectionOf)
			return "Invalid ends' stereotypes for SubCollectionOf";
		if(element instanceof subQuantityOf)
			return "Invalid ends' stereotypes for SubQuantityOf";
		return "Invalid Meronymic";
	}

	@Override
	public JDialog createDialog(JDialog parent) {
		return new MeronymicEndsDialog(parent, this);
	}
	
	public void setChangeRelationStereotype(RelationStereotype newStereotype){
		if(fixer.getRelationshipStereotype(element)!=newStereotype){
			changeStereotype = true;
			this.newStereotype = newStereotype;
		}
		else{
			changeStereotype = false;
			this.newStereotype = null;
		}
	}

	@Override
	public Fix fix() {
		if(changeStereotype)
			fix.addAll(fixer.changeRelationStereotypeTo(element, newStereotype));
		
		fixWholeNature();
		fixPartNature();
		
		return fix;
	}
	
	@Override
	public boolean hasAction(){
		
		return changeStereotype || (newPartNatureHash!=null && hasChangeInNature(partNatureHash, newPartNatureHash)) 
				|| (newWholeNatureHash!=null && hasChangeInNature(wholeNatureHash, newWholeNatureHash));
				
	}
	
	public boolean getChangeStereotype(){
		return changeStereotype;
	}
	
	private HashMap<Nature,Boolean> setEndsNature(Classifier c){
		HashMap<Nature,Boolean> natureHash = new HashMap<Nature,Boolean>();
		
		if(c instanceof Relator){
			natureHash.put(Nature.RELATOR, true);
			natureHash.put(Nature.MODE, false);
			natureHash.put(Nature.DATATYPE, false);
			natureHash.put(Nature.FUNCTIONAL, false);
			natureHash.put(Nature.COLLECTIVE, false);
			natureHash.put(Nature.QUANTITY, false);
			
			return natureHash;
		}
		else
			natureHash.put(Nature.RELATOR, false);
		
		if(c instanceof Mode){	
			natureHash.put(Nature.MODE, true );
			natureHash.put(Nature.DATATYPE, false);
			natureHash.put(Nature.FUNCTIONAL, false);
			natureHash.put(Nature.COLLECTIVE, false);
			natureHash.put(Nature.QUANTITY, false);
			
			return natureHash;
		}
		else
			natureHash.put(Nature.MODE, false );
		
		if(c instanceof DataType){
			natureHash.put(Nature.DATATYPE, true);
			natureHash.put(Nature.FUNCTIONAL, false);
			natureHash.put(Nature.COLLECTIVE, false);
			natureHash.put(Nature.QUANTITY, false);
			
			return natureHash;
		}
		else
			natureHash.put(Nature.DATATYPE, false);
		
		if(parser.isFunctionalComplex(c)){
			natureHash.put(Nature.FUNCTIONAL, true);
			natureHash.put(Nature.COLLECTIVE, false);
			natureHash.put(Nature.QUANTITY, false);
			
			return natureHash;
		}
		
		if(parser.isCollective(c)){
			natureHash.put(Nature.FUNCTIONAL, false);
			natureHash.put(Nature.COLLECTIVE, true);
			natureHash.put(Nature.QUANTITY, false);
			
			return natureHash;
		}
		
		else if(parser.isQuantity(c)){
			natureHash.put(Nature.FUNCTIONAL, false);
			natureHash.put(Nature.COLLECTIVE, false);
			natureHash.put(Nature.QUANTITY, true);
			
			return natureHash;
		}
		
		if(c instanceof MixinClass){
			if(parser.hasFunctionalComplexChild((MixinClass) c))
				natureHash.put(Nature.FUNCTIONAL, true);
			else
				natureHash.put(Nature.FUNCTIONAL, false);
			
			if(parser.hasQuantityChild((MixinClass) c))
				natureHash.put(Nature.QUANTITY, true);
			else
				natureHash.put(Nature.QUANTITY, false);
			
			if(parser.hasCollectiveChild((MixinClass) c))
				natureHash.put(Nature.COLLECTIVE, true);
			else
				natureHash.put(Nature.COLLECTIVE, false);
		}
		else{
			natureHash.put(Nature.FUNCTIONAL, false);
			natureHash.put(Nature.COLLECTIVE, false);
			natureHash.put(Nature.QUANTITY, false);
		}
		
		return natureHash;	
	}
	
	public boolean canDefineNature(HashMap<Nature,Boolean> natureHash){
		
		for (Boolean value : natureHash.values()) {
			if(value)
				return true;
		}
		
		return false;
	}
	
	public boolean canDefineWholeNature() {
		return canDefineNature(wholeNatureHash);
	}
	
	public boolean canDefinePartNature() {
		return canDefineNature(partNatureHash);
	}
	
	public String getNatureDescription(HashMap<Nature,Boolean> natureHash){
		ArrayList<String> natures = new ArrayList<String>();
		
		if(natureHash.get(Nature.FUNCTIONAL))
			natures.add("Functional Complex");
		if(natureHash.get(Nature.COLLECTIVE))
			natures.add("Collection");
		if(natureHash.get(Nature.QUANTITY))
			natures.add("Quantity");
		if(natureHash.get(Nature.MODE))
			natures.add("MODE");
		if(natureHash.get(Nature.RELATOR))
			natures.add("Relator");
		if(natureHash.get(Nature.DATATYPE))
			natures.add("Datatype");
		
		if(natures.size()==0)
			return "Undefined";
		
		String result = natures.get(0);
		
		for (int i = 1; i < natures.size(); i++) {
			if(i==natures.size()-1)
				result += " and ";
			else
				result += ", ";
			
			result += natures.get(i);
		}
		
		return result;
	}
	
	public String getWholeNature(){
		return getNatureDescription(wholeNatureHash);
	}

	public String getPartNature(){
		return getNatureDescription(partNatureHash);
	}
	
	public RelationStereotype getStereotype(){
		return fixer.getRelationshipStereotype(element);
	}

	
	public boolean hasChangeInNature(HashMap<Nature,Boolean> hash1, HashMap<Nature,Boolean> hash2){
		return hasChangeInNature(hash1, hash2.get(Nature.FUNCTIONAL), hash2.get(Nature.COLLECTIVE), hash2.get(Nature.QUANTITY), 
				hash2.get(Nature.RELATOR), hash2.get(Nature.MODE), hash2.get(Nature.DATATYPE));
	}
	
	public boolean hasChangeInNature(HashMap<Nature,Boolean> natureHash, boolean isFunctional, boolean isCollection, boolean isQuantity, boolean isRelator, boolean isMode, boolean isDatatype) {
		if(natureHash.get(Nature.FUNCTIONAL)!=isFunctional)
			return true;
		if(natureHash.get(Nature.COLLECTIVE)!=isCollection)
			return true;
		if(natureHash.get(Nature.QUANTITY)!=isQuantity)
			return true;
		if(natureHash.get(Nature.RELATOR)!=isRelator)
			return true;
		if(natureHash.get(Nature.MODE)!=isMode)
			return true;
		if(natureHash.get(Nature.DATATYPE)!=isDatatype)
			return true;
		
		return false;
			
	}
	
	public void fixWholeNature() {
		fixNature((Classifier) OntoUMLParser.getWholeEnd(element).getType(), wholeNatureHash, 
				newWholeNatureHash.get(Nature.FUNCTIONAL), newWholeNatureHash.get(Nature.COLLECTIVE), newWholeNatureHash.get(Nature.QUANTITY), 
				newWholeNatureHash.get(Nature.RELATOR), newWholeNatureHash.get(Nature.MODE), newWholeNatureHash.get(Nature.DATATYPE));
	}
	
	public void fixPartNature() {
		fixNature((Classifier) OntoUMLParser.getPartEnd(element).getType(), partNatureHash, 
				newPartNatureHash.get(Nature.FUNCTIONAL), newPartNatureHash.get(Nature.COLLECTIVE), newPartNatureHash.get(Nature.QUANTITY), 
				newPartNatureHash.get(Nature.RELATOR), newPartNatureHash.get(Nature.MODE), newPartNatureHash.get(Nature.DATATYPE));
	}
	
	public void fixNature(Classifier baseClass, HashMap<Nature,Boolean> natureHash, boolean isFunctional, boolean isCollection, boolean isQuantity, boolean isRelator, boolean isMode, boolean isDatatype) {
		
		if(!hasChangeInNature(natureHash,isFunctional, isCollection, isQuantity, isRelator, isMode, isDatatype));
		
		//change solely to functional
		if(isFunctional && !isCollection && !isQuantity && !isRelator && !isMode && !isDatatype){
			changeToExclusiveSortalNature(baseClass, Kind.class);
		}
		else if(!isFunctional && isCollection && !isQuantity && !isRelator && !isMode && !isDatatype){
			changeToExclusiveSortalNature(baseClass, Collective.class);
		}
		else if(!isFunctional && !isCollection && isQuantity && !isRelator && !isMode && !isDatatype){
			changeToExclusiveSortalNature(baseClass, Quantity.class);
		}
		else if(!isFunctional && !isCollection && !isQuantity && isRelator && !isMode && !isDatatype){
			changeToMomentNature(baseClass, Relator.class);
		}
		else if(!isFunctional && !isCollection && !isQuantity && !isRelator && isMode && !isDatatype){
			changeToMomentNature(baseClass, Mode.class);
		}
		else if(!isFunctional && !isCollection && !isQuantity && !isRelator && !isMode && isDatatype){
			changeToMomentNature(baseClass, DataType.class);
		}
		
	}

	private void changeToMomentNature(Classifier baseClass, Class<?> c){
		
		ClassStereotype stereo = OutcomeFixer.getClassStereotype(c);
		if(c.isInstance(baseClass))
			return;
		
		Iterator<Classifier> iterator = baseClass.allParents().iterator();
		while(iterator.hasNext()){
			Classifier parent = iterator.next();
			if(c.isInstance(parent)){
				iterator.remove();
				fix.addAll(fixer.changeClassStereotypeTo(parent, stereo));
			}
		}
		
		iterator = baseClass.allChildren().iterator();
		while(iterator.hasNext()){
			Classifier child = iterator.next();
			if(c.isInstance(child)){
				iterator.remove();
				fix.addAll(fixer.changeClassStereotypeTo(child, stereo));
			}
		}
		
		fix.addAll(fixer.changeClassStereotypeTo(baseClass, stereo));
		
	}
	
	private void changeToExclusiveSortalNature(Classifier baseClass, Class<?> c) {
		ClassStereotype stereo = OutcomeFixer.getClassStereotype(c);
		
		if(baseClass instanceof SubstanceSortal || baseClass instanceof Relator || baseClass instanceof Mode || baseClass instanceof DataType){
			fix.addAll(fixer.changeClassStereotypeTo(baseClass, stereo));
			
			if(baseClass instanceof Relator || baseClass instanceof Mode || baseClass instanceof DataType){
				Iterator<Generalization> iterator = baseClass.getGeneralization().iterator();
				while(iterator.hasNext()){
					Generalization g = iterator.next();
					if(!(g.getGeneral() instanceof MixinClass)){
						iterator.remove();
						fix.addAll(fixer.deleteElement(g));
					}		
				}
			}
			
		}
		else if (baseClass instanceof Phase || baseClass instanceof Role || baseClass instanceof SubKind){
			Iterator<Classifier> iterator = parser.getIdentityProvider(baseClass).iterator();
			while(iterator.hasNext()){
				Classifier identityProvider = iterator.next();
				if(!c.isInstance(identityProvider)){
					iterator.remove();
					fix.addAll(fixer.changeClassStereotypeTo(identityProvider, stereo));
				}
			}
		}
		else if (baseClass instanceof MixinClass){
			 HashSet<Classifier> identityProviders = new HashSet<Classifier>();
			 for (Classifier child : parser.getAllChildren(baseClass)) {
				if(child instanceof SubstanceSortal)
					identityProviders.add(child);
				if(child instanceof SubKind || child instanceof Phase || child instanceof Role)
					identityProviders.addAll(parser.getIdentityProvider(child));
			 }
			 
			 Iterator<Classifier> iterator = identityProviders.iterator();
			 while(iterator.hasNext()){
				 Classifier identityProvider = iterator.next();
				 if(!c.isInstance(identityProvider)){
					 iterator.remove();
					 fix.addAll(fixer.changeClassStereotypeTo(identityProvider, stereo));
				 }
			 }
		}
	}
	
	public void setChangeWholeNature(boolean isFunctional, boolean isCollection, boolean isQuantity, boolean isRelator, boolean isMode, boolean isDatatype) {
		newWholeNatureHash = setChangeNature(isFunctional, isCollection, isQuantity, isRelator, isMode, isDatatype);
	}
	
	public void setChangePartNature(boolean isFunctional, boolean isCollection, boolean isQuantity, boolean isRelator, boolean isMode, boolean isDatatype) {
		newPartNatureHash = setChangeNature(isFunctional, isCollection, isQuantity, isRelator, isMode, isDatatype);
	}
	
	public HashMap<Nature, Boolean> setChangeNature(boolean isFunctional, boolean isCollection, boolean isQuantity, boolean isRelator, boolean isMode, boolean isDatatype) {
		HashMap<Nature, Boolean> hash = new HashMap<Nature, Boolean>();
		hash.put(Nature.FUNCTIONAL, isFunctional);
		hash.put(Nature.COLLECTIVE, isCollection);
		hash.put(Nature.QUANTITY, isQuantity);
		hash.put(Nature.RELATOR, isRelator);
		hash.put(Nature.MODE, isMode);
		hash.put(Nature.DATATYPE, isDatatype);
		return hash;
	}

	public boolean isWholeSetAsFunctional() {
		if(newWholeNatureHash==null)
			return false;
		return newWholeNatureHash.get(Nature.FUNCTIONAL);
	}

	public boolean isWholeSetAsCollection() {
		if(newWholeNatureHash==null)
			return false;
		return newWholeNatureHash.get(Nature.COLLECTIVE);
	}

	public boolean isWholeSetAsQuantity() {
		if(newWholeNatureHash==null)
			return false;
		return newWholeNatureHash.get(Nature.QUANTITY);
	}	
	
	public boolean isWholeSetAsRelator() {
		if(newWholeNatureHash==null)
			return false;
		return newWholeNatureHash.get(Nature.RELATOR);
	}	
	
	public boolean isWholeSetAsMode() {
		if(newWholeNatureHash==null)
			return false;
		return newWholeNatureHash.get(Nature.MODE);
	}	
	
	public boolean isWholeSetAsDatatype() {
		if(newWholeNatureHash==null)
			return false;
		return newWholeNatureHash.get(Nature.DATATYPE);
	}	
	
	public boolean isPartSetAsFunctional() {
		if(newPartNatureHash==null)
			return false;
		return newPartNatureHash.get(Nature.FUNCTIONAL);
	}

	public boolean isPartSetAsCollection() {
		if(newPartNatureHash==null)
			return false;
		return newPartNatureHash.get(Nature.COLLECTIVE);
	}

	public boolean isPartSetAsQuantity() {
		if(newPartNatureHash==null)
			return false;
		return newPartNatureHash.get(Nature.QUANTITY);
	}	
	
	public boolean isPartSetAsRelator() {
		if(newPartNatureHash==null)
			return false;
		return newPartNatureHash.get(Nature.RELATOR);
	}	
	
	public boolean isPartSetAsMode() {
		if(newPartNatureHash==null)
			return false;
		return newPartNatureHash.get(Nature.MODE);
	}	
	
	public boolean isPartSetAsDatatype() {
		if(newPartNatureHash==null)
			return false;
		return newPartNatureHash.get(Nature.DATATYPE);
	}

	public RelationStereotype getNewStereotype() {
		return newStereotype;
	}

	public boolean isWholeFunctional() {
		return wholeNatureHash.get(Nature.FUNCTIONAL);
	}
	
	public boolean isWholeCollection() {
		return wholeNatureHash.get(Nature.COLLECTIVE);
	}

	public boolean isWholeQuantity() {
		return wholeNatureHash.get(Nature.QUANTITY);
	}	
	
	public boolean isWholeRelator() {
		return wholeNatureHash.get(Nature.RELATOR);
	}	
	
	public boolean isWholeMode() {
		return wholeNatureHash.get(Nature.MODE);
	}	
	
	public boolean isWholeDatatype() {
		return wholeNatureHash.get(Nature.DATATYPE);
	}
	
	public boolean isPartFunctional() {
		return partNatureHash.get(Nature.FUNCTIONAL);
	}
	
	public boolean isPartCollection() {
		return partNatureHash.get(Nature.COLLECTIVE);
	}

	public boolean isPartQuantity() {
		return partNatureHash.get(Nature.QUANTITY);
	}	
	
	public boolean isPartRelator() {
		return partNatureHash.get(Nature.RELATOR);
	}	
	
	public boolean isPartMode() {
		return partNatureHash.get(Nature.MODE);
	}	
	
	public boolean isPartDatatype() {
		return partNatureHash.get(Nature.DATATYPE);
	}	
}
