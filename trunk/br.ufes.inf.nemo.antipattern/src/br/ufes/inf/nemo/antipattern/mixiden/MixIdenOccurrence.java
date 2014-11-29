package br.ufes.inf.nemo.antipattern.mixiden;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Category;
import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.Mixin;
import RefOntoUML.MixinClass;
import RefOntoUML.Phase;
import RefOntoUML.Role;
import RefOntoUML.RoleMixin;
import RefOntoUML.SubKind;
import RefOntoUML.SubstanceSortal;
import RefOntoUML.impl.CollectiveImpl;
import RefOntoUML.impl.KindImpl;
import RefOntoUML.impl.PhaseImpl;
import RefOntoUML.impl.QuantityImpl;
import RefOntoUML.impl.RoleImpl;
import RefOntoUML.impl.SubKindImpl;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;
import br.ufes.inf.nemo.common.ontoumlfixer.ClassStereotype;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;

public class MixIdenOccurrence extends AntipatternOccurrence {
	
	MixinClass mixin;
	String shortName = new String();	
	ArrayList<Classifier> subtypes;
	Classifier identityProvider;
	private boolean hasRigid;
	private boolean hasAntiRigid;
	private boolean hasBoth; 
	
	public MixIdenOccurrence(MixinClass mixin, MixIdenAntipattern ap) throws Exception {
		super(ap);
		
		if(mixin==null)
			throw new NullPointerException("MixIden: null mixin provided.");
		
		if(!(mixin instanceof MixinClass))
			throw new NullPointerException("MixIden: mixin must be an instance of Mixin.");
		
		this.mixin = mixin;
		this.shortName = parser.getStringRepresentation(mixin);
		
		this.subtypes = new ArrayList<Classifier>();
		this.subtypes.addAll(mixin.children());
		
		if (this.subtypes.size()==0)
			throw new Exception("MixIden: Mixin has no subtypes!");
		
		identityProvider = null;
		
		for (Classifier child : subtypes) {
			for (Classifier parent: child.allParents()) {
				if(parent instanceof SubstanceSortal){
					if(identityProvider==null)
						identityProvider=parent;
					else if (!identityProvider.equals(parent))
						throw new Exception("MixIden: More than one identity provider!");
				}
			}
		}
		
		if (identityProvider == null)
			throw new Exception("MixIden: No identity provider for subtypes.");
		
		for (Classifier child : subtypes) {	
			if (child instanceof SubstanceSortal || child instanceof SubKind)
				hasRigid = true;
			else if( child instanceof Role || child instanceof Phase)
				hasAntiRigid = true;
		}
		
		hasBoth = hasRigid && hasAntiRigid;
		
	}

	public MixinClass getMixin() {
		return mixin;
	}

	public ArrayList<Classifier> getSubtypes() {
		return subtypes;
	}

	public Classifier getIdentityProvider() {
		return identityProvider;
	}
	
	public boolean isHasRigid() {
		return hasRigid;
	}

	public boolean isHasAntiRigid() {
		return hasAntiRigid;
	}

	public boolean isHasBoth() {
		return hasBoth;
	}

	@Override
	public OntoUMLParser setSelected() {
		ArrayList<EObject> selection = new ArrayList<EObject>();
		
		selection.add(this.mixin);
		selection.addAll(subtypes);
				
		parser.select(selection,true);
		parser.autoSelectDependencies(OntoUMLParser.SORTAL_ANCESTORS, false);

		return parser;
	}
	
	@Override
	public String toString(){
		String result = 
				"MixinClass: "+super.parser.getStringRepresentation(this.mixin) + "\n" +
				"Common Identity Provider: "+super.parser.getStringRepresentation(this.identityProvider) + "\n" +
				"Subtypes: ";
		
		for (Classifier subtype : this.subtypes) {
			result+="\n\t"+super.parser.getStringRepresentation(subtype);
		}
		return result;
	}

	@Override
	public String getShortName() {
		return shortName;
	}
	
	public void changeMixinStereotype(){
		fix.addAll(fixer.createGeneralization(mixin, identityProvider));
		
		if(hasAntiRigid && !hasRigid)
				fix.addAll(fixer.changeClassStereotypeTo(mixin, ClassStereotype.ROLE));
		else
			fix.addAll(fixer.changeClassStereotypeTo(mixin, ClassStereotype.SUBKIND));
	}
	
	public void changeIdentityProviders(ArrayList<SortalToAdd> list){
		ArrayList<Classifier> createdClassifiers = new ArrayList<Classifier>();
		
		//REMOVE GENERALIZATIONS THAT LEAD TO IDENTITY PROVIDER
		for (SortalToAdd sta : list) {
			
			if(sta.getSortal()==null || !subtypes.contains(sta.getSortal()) )
				continue;
			
			Iterator<Generalization> iterator = sta.getSortal().getGeneralization().iterator();
			
			while(iterator.hasNext()){
				Generalization g = iterator.next();
				if(g.getGeneral().equals(identityProvider) || g.getGeneral().allParents().contains(identityProvider)){
					iterator.remove();
					fix.addAll(fixer.deleteElement(g));
				}
			}			
		}
		
		//SETS NEW IDENTITY PROVIDERS
		for (SortalToAdd sta : list) {	
			
			Classifier subtype = null, identityProvider = null;
			
			if(sta.getSortal()==null || !subtypes.contains(sta.getSortal()) )
				continue;
			
			if(sta.getSortal().equals(sta.getIdentityProvider())){
				if(sta.isSortalStereotypeChange()){
					Fix currentFix = fixer.changeClassStereotypeTo(sta.getSortal(), OutcomeFixer.getClassStereotype(sta.getSortalStereotype()));
					subtype = currentFix.getAddedByType(Classifier.class).get(0);
					fix.addAll(currentFix);
				}
				continue;
			}
			
			if(sta.isSortalStereotypeChange()){
				Fix currentFix = fixer.changeClassStereotypeTo(sta.getSortal(), OutcomeFixer.getClassStereotype(sta.getSortalStereotype()));
				subtype = currentFix.getAddedByType(Classifier.class).get(0);
				fix.addAll(currentFix);
			}
			else
				subtype = sta.getSortal();
			
			if(sta.getIdentityProvider()==null || fix.getDeleted().contains(sta.getIdentityProvider())){
				
				for (Classifier classifier : createdClassifiers) {
					if(classifier.getName().compareTo(sta.getIdentityProviderName())==0 && classifier.getClass().equals(sta.getIdentityProviderStereotype()))
						identityProvider = classifier;
				}
				
				if(identityProvider==null){
					identityProvider = (Classifier) fixer.createClass(OutcomeFixer.getClassStereotype(sta.getIdentityProviderStereotype()));
					identityProvider.setName(sta.identityProviderName);
					fixer.copyContainer(subtype, identityProvider);
					createdClassifiers.add(identityProvider);
					fix.includeAdded(identityProvider);
				}
			}
			else{
				if(sta.isIdentityProvideStereotypeChange()){
					Fix currentFix = fixer.changeClassStereotypeTo(sta.getIdentityProvider(), OutcomeFixer.getClassStereotype(sta.getIdentityProviderStereotype()));
					identityProvider = currentFix.getAddedByType(Classifier.class).get(0);
					fix.addAll(currentFix);
				}
				else
					identityProvider = sta.getIdentityProvider();
			}
			
			fix.addAll(fixer.createGeneralization(subtype, identityProvider));
			
			
			
		}
	}
	
	public void addSortals(ArrayList<SortalToAdd> list){
		ArrayList<Classifier> createdClassifiers = new ArrayList<Classifier>();
		
		for (SortalToAdd sta : list) {
			Classifier newSortal = null;
			Classifier newIdentityProvider = null;
			//sortal already exists
			if(sta.existingSortal()){
				fix.addAll(fixer.createGeneralization(sta.getSortal(), mixin));
				newSortal = sta.getSortal();
			} //new sortal
			else{
				//already created in previous iteration
				for (Classifier classifier : createdClassifiers) {
					if(classifier.getName().compareTo(sta.getSortalName())==0 && classifier.getClass().equals(sta.getSortalStereotype())){
						newSortal = classifier;
						break;
					}
				}
				//creating now
				if(newSortal==null){
					Fix currentFix = fixer.createSubTypeAs(mixin, OutcomeFixer.getClassStereotype(sta.getSortalStereotype()), sta.getSortalName());
					newSortal = currentFix.getAddedByType(Classifier.class).get(0);
					createdClassifiers.add(newSortal);
					fix.addAll(currentFix);
				}				
			}
			
			//identity provider already exists
			if(sta.existingIdentityProvider()){
				newIdentityProvider = sta.getIdentityProvider();
			}//creating identity provider now
			else {
				for (Classifier classifier : createdClassifiers) {
					if(classifier.getName().compareTo(sta.getIdentityProviderName())==0 && classifier.getClass().equals(sta.getIdentityProviderStereotype())){
						newIdentityProvider = classifier;
						break;
					}
				}
				
				if(newIdentityProvider==null){
					newIdentityProvider = (Classifier) fixer.createClass(OutcomeFixer.getClassStereotype(sta.getIdentityProviderStereotype()));
					newIdentityProvider.setName(sta.getIdentityProviderName());
					fixer.copyContainer(identityProvider, newIdentityProvider);
					createdClassifiers.add(newIdentityProvider);
					fix.includeAdded(newIdentityProvider);
				}
			}
			
			if(!(newSortal instanceof SubstanceSortal) && !newSortal.allParents().contains(newIdentityProvider)){
				fix.addAll(fixer.createGeneralization(newSortal, newIdentityProvider));
			}
		}
		
	}
	
	public ArrayList<Class<?>> allowedSubtypeStereotypes(){
		ArrayList<Class<?>> allowedStereotypes = new ArrayList<Class<?>>();
		
		if(mixin instanceof RoleMixin || mixin instanceof Mixin){
			allowedStereotypes.add(RoleImpl.class);
			allowedStereotypes.add(PhaseImpl.class);
		}
		
		if(mixin instanceof Category || mixin instanceof Mixin){
			allowedStereotypes.add(KindImpl.class);
			allowedStereotypes.add(CollectiveImpl.class);
			allowedStereotypes.add(QuantityImpl.class);
			allowedStereotypes.add(SubKindImpl.class);
		}
		
		return allowedStereotypes;
	}
	
	public ArrayList<Class<?>> identityProviderStereotypes(){
		ArrayList<Class<?>> identityProviderStereotypes = new ArrayList<Class<?>>();

		identityProviderStereotypes.add(KindImpl.class);
		identityProviderStereotypes.add(CollectiveImpl.class);
		identityProviderStereotypes.add(QuantityImpl.class);
		
		return identityProviderStereotypes;
	}

}
