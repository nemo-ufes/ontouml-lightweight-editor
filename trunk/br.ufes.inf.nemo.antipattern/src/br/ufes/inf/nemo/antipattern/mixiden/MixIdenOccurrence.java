package br.ufes.inf.nemo.antipattern.mixiden;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Classifier;
import RefOntoUML.MixinClass;
import RefOntoUML.Phase;
import RefOntoUML.Role;
import RefOntoUML.SubKind;
import RefOntoUML.SubstanceSortal;
import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer.ClassStereotype;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class MixIdenOccurrence extends AntipatternOccurrence {
	
	MixinClass mixin;
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
				
		parser.selectThisElements(selection,true);
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
		return parser.getStringRepresentation(mixin);
	}
	
	public void changeMixinStereotype(){
		fix.addAll(fixer.createGeneralization(mixin, identityProvider));
		
		if(hasAntiRigid && !hasRigid)
				fix.addAll(fixer.changeClassStereotypeTo(mixin, ClassStereotype.ROLE));
		else
			fix.addAll(fixer.changeClassStereotypeTo(mixin, ClassStereotype.SUBKIND));
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
					createdClassifiers.add(newIdentityProvider);
					fix.includeAdded(newIdentityProvider);
				}
			}
			
			if(!(newSortal instanceof SubstanceSortal) && !newSortal.allParents().contains(newIdentityProvider)){
				fix.addAll(fixer.createGeneralization(newSortal, newIdentityProvider));
			}
		}
		
	}

}
