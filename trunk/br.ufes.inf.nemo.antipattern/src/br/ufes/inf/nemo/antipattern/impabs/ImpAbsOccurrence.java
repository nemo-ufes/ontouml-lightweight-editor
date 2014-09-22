package br.ufes.inf.nemo.antipattern.impabs;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Association;
import RefOntoUML.Characterization;
import RefOntoUML.Classifier;
import RefOntoUML.Mediation;
import RefOntoUML.Meronymic;
import RefOntoUML.Property;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;
import br.ufes.inf.nemo.antipattern.util.AlloyConstructor;
import br.ufes.inf.nemo.antipattern.util.SourceTargetAssociation;
import br.ufes.inf.nemo.common.list.Combination;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer.SpecializationType;

public class ImpAbsOccurrence extends AntipatternOccurrence{
	private Association association;
	private Classifier sourceType, targetType;
	private ArrayList<Classifier> sourceChildren, targetChildren;
	
	private ArrayList<Association> createdAssociations;
	
	public ImpAbsOccurrence(Association a, ImpAbsAntipattern ap) throws Exception {
		super(ap);
		this.setAssociation(a);
		createdAssociations = new ArrayList<Association>();
	}
	 
	public String generateTargetOcl(ArrayList<Classifier> subtypes, OntoUMLParser parser)
	{
		String result;
		String aet_name = association.getMemberEnd().get(1).getName();
		
		result = "context _'"+sourceType.getName()+"'\n"+
				 "inv: ";	
		
		if(subtypes!=null && subtypes.size()>0 && targetChildren.containsAll(subtypes))
		{
			for (int n=0;n<subtypes.size();n++)
			{
				result += "self."+aet_name+"->intersection("+subtypes.get(n).getName()+".allInstances())->size()>0";
				if (n<subtypes.size()-1)
            		result += " and ";
			}
							
			return result;
		}
		
		else
			return null;
		
	}
	
	public String generateSourceOcl(ArrayList<Classifier> subtypes, OntoUMLParser parser)
	{
		String result;
		String aes_name = association.getMemberEnd().get(0).getName();
		
		result = "context _'"+targetType.getName()+"'\n"+
				 "inv: ";	
		
		if(subtypes!=null && subtypes.size()>0 && sourceChildren.containsAll(subtypes)){
			for (int n=0;n<subtypes.size();n++){
				result += "self."+aes_name+"->intersection("+subtypes.get(n).getName()+".allInstances())->size()>0";
				if (n<subtypes.size()-1)
            		result += " and ";
			}
							
			return result;
		}
		
		else
			return null;
		
	}
	
	//generates an alloy predicate whose instances allowed show that the target end of the relation only has the types inputed in the parameter, on the variable subtypes. 
	public String generateTargetPredicate(ArrayList<Classifier> subtypes, OntoUMLParser parser){
		String predicate="", rules, predicateName, sourceName, associationName, childName;
		
		//maps to the names of the objects in alloy
		associationName = parser.getAlias(association);
		sourceName = parser.getAlias(this.sourceType);
		
		//builds the basic structure for the generated predicate
		predicateName = "imprecise_abstraction_"+sourceName+"_"+associationName;
		rules = "all w:World | #w."+sourceName+">=1\n\t";
		rules += "some w:World | all x:w."+sourceName+" | #x.(w."+associationName+")>1 and ";
		
		if(subtypes!=null && subtypes.size()>0 && targetChildren.containsAll(subtypes)){
			for (int n=0;n<targetChildren.size();n++){
				childName = parser.getAlias(targetChildren.get(n));
            	
				if (subtypes.contains(targetChildren.get(n))){
	            	predicateName += "_"+childName;
            		rules += "some ";
            	}
            	else
            		rules += "no ";
            	
            	rules += "x.(w."+associationName+") & w."+childName;
            	if (n<targetChildren.size()-1)
            		rules += " and ";
			}
			predicate += AlloyConstructor.AlloyParagraph(predicateName, rules, AlloyConstructor.PRED);
			predicate += AlloyConstructor.RunCheckCommand(predicateName, "10", "1", AlloyConstructor.PRED)+"\n\n";
			
			return predicate;
		}
		
		else
			return null;
		
	}
	
	//generates an alloy predicate whose instances allowed show that the source end of the relation only has the types inputed in the parameter, on the variable subtypes. 
		public String generateSourcePredicate(ArrayList<Classifier> subtypes, OntoUMLParser parser){
			String predicate="", rules, predicateName, targetName, associationName, childName;
			
			//maps to the names of the objects in alloy
			associationName = parser.getAlias(association);
			targetName = parser.getAlias(this.targetType);
			
			//builds the basic structure for the generated predicate
			predicateName = "imprecise_abstraction_"+targetName+"_"+associationName;
			rules = "all w:World | #w."+targetName+">=1\n\t";
			rules += "some w:World | all x:w."+targetName+" | #(w."+associationName+").x>1 and ";
			
			if(subtypes!=null && subtypes.size()>0 && sourceChildren.containsAll(subtypes)){
				for (int n=0;n<sourceChildren.size();n++){
					childName = parser.getAlias(sourceChildren.get(n));
	            	
					if (subtypes.contains(sourceChildren.get(n))){
		            	predicateName += "_"+childName;
	            		rules += "some ";
	            	}
	            	else
	            		rules += "no ";
	            	
	            	rules += "(w."+associationName+").x & w."+childName;
	            	if (n<sourceChildren.size()-1)
	            		rules += " and ";
				}
				predicate += AlloyConstructor.AlloyParagraph(predicateName, rules, AlloyConstructor.PRED);
				predicate += AlloyConstructor.RunCheckCommand(predicateName, "10", "1", AlloyConstructor.PRED)+"\n\n";
				
				return predicate;
			}
			
			else
				return null;
			
		}
	
	
	public String generateAllImpreciseAbstractionPredicates(OntoUMLParser parser) {
		String predicates="", rules, predicateName;
		Combination comb1;
		ArrayList<Object> saida = new ArrayList<Object>();
		
		String associationName, sourceName, targetName;
		ArrayList<String> sourceChildrenName, targetChildrenName;
		associationName = parser.getAlias(association);
		sourceName = parser.getAlias(this.sourceType);
		targetName = parser.getAlias(this.targetType);
		
		sourceChildrenName = new ArrayList<String>();
		for (Classifier c : this.sourceChildren) {
			sourceChildrenName.add(parser.getAlias(c));
		}
		
		targetChildrenName = new ArrayList<String>();
		for (Classifier c : this.targetChildren) {
			targetChildrenName.add(parser.getAlias(c));
		}
		
		//Check if there are specializations on the source of the relation and also if the source upper cardinality is unlimited or greater then 1
		if ((sourceChildrenName.size()>0) && (SourceTargetAssociation.getUpperSourceCardinality(association)==-1 || SourceTargetAssociation.getUpperSourceCardinality(association)>1)) {
			
			comb1 = new Combination(sourceChildrenName, 0);
			while (comb1.hasNext()) {
				saida=(comb1.next());
				predicateName = "imprecise_abstraction_"+targetName+"_"+associationName;
				rules = "all w:World | #w."+targetName+">=1\n\t";
				rules += "some w:World | all x:w."+targetName+" | #(w."+associationName+").x>1 and ";
				
				for (int n=0;n<this.sourceChildren.size();n++){

		            	if (saida.contains(this.sourceChildren.get(n))){
			            	predicateName += "_"+this.sourceChildren.get(n);
		            		rules += "some ";
		            	}
		            	else
		            		rules += "no ";
		            	rules += "(w."+associationName+").x & w."+this.sourceChildren.get(n);
		            	if (n<this.sourceChildren.size()-1)
		            		rules += " and ";
            	}
				predicates += AlloyConstructor.AlloyParagraph(predicateName, rules, AlloyConstructor.PRED);
        		predicates += AlloyConstructor.RunCheckCommand(predicateName, "10", "1", AlloyConstructor.PRED)+"\n\n";
			}
		}
		
		//Check if there are specializations on the target of the relation and also if the target upper cardinality is unlimited or greater then 1
		if ((targetChildrenName.size()>0) && (SourceTargetAssociation.getUpperTargetCardinality(association)==-1 || SourceTargetAssociation.getUpperTargetCardinality(association)>1)) {
			
			comb1 = new Combination(targetChildrenName, 0);
			while (comb1.hasNext()) {
				saida=(comb1.next());
				predicateName = "imprecise_abstraction_"+sourceName+"_"+associationName;
				rules = "all w:World | #w."+sourceName+">=1\n\t";
				rules += "some w:World | all x:w."+sourceName+" | #x.(w."+associationName+")>1 and ";
				
				for (int n=0;n<targetChildrenName.size();n++){

		            	if (saida.contains(targetChildrenName.get(n))){
			            	predicateName += "_"+targetChildrenName.get(n);
		            		rules += "some ";
		            	}
		            	else
		            		rules += "no ";
		            	rules += "x.(w."+associationName+") & w."+targetChildrenName.get(n);
		            	if (n<targetChildrenName.size()-1)
		            		rules += " and ";
            	}
				predicates += AlloyConstructor.AlloyParagraph(predicateName, rules, AlloyConstructor.PRED);
        		predicates += AlloyConstructor.RunCheckCommand(predicateName, "10", "1", AlloyConstructor.PRED)+"\n\n";
			}
		}
		
		return predicates;
	}
	
	
	public Association getAssociation() {
		return association;
	}

	public void setAssociation(Association association) throws Exception {
		/*TODO check if the association characterizes the antipattern*/
		if(association==null)
			throw new NullPointerException("Association can't be null.");
		
		this.association = association;
		
		
		if(association.getMemberEnd().size()!=2)
			throw new Exception("The provided association must relate exactly two elements. MemberEnd may be null or undefined");
		
		//WARNING: Getting all children from both ends may generate a really big combination in the wizard.
		
		this.sourceType = (Classifier) SourceTargetAssociation.getSourceAlloy(association);
		this.sourceChildren = new ArrayList<Classifier>();
		if(parser.allChildrenHash.containsKey(sourceType))
			this.sourceChildren.addAll(parser.allChildrenHash.get(sourceType));
		//this.sourceChildren.addAll(source.children());
		
		this.targetType = (Classifier) SourceTargetAssociation.getTargetAlloy(association);		
		this.targetChildren = new ArrayList<Classifier>();
		if(parser.allChildrenHash.containsKey(targetType))
			this.targetChildren.addAll(parser.allChildrenHash.get(targetType));

		//this.targetChildren.addAll(target.children());
		
	}

	public Classifier getSource() {
		return sourceType;
	}

	public ArrayList<Classifier> getSourceChildren() {
		return sourceChildren;
	}

	public Classifier getTarget() {
		return targetType;
	}

	public ArrayList<Classifier> getTargetChildren() {
		return targetChildren;
	}
	
	@Override
	public String toString() {
		String result = 
				"Association: "+parser.getStringRepresentation(association)+
				"\nSource: "+parser.getStringRepresentation(sourceType);

		for (Classifier subtype : sourceChildren) {
			result+="\n\t"+parser.getStringRepresentation(subtype);
		}
		
		result+="\nTarget: "+parser.getStringRepresentation(targetType);
		
		for (Classifier subtype : targetChildren) {
			result+="\n\t"+parser.getStringRepresentation(subtype);
		}
		
		return result;
	}


	@Override
	public OntoUMLParser setSelected() {
		ArrayList<EObject> selection = new ArrayList<EObject>();
		
		selection.add(association);
		selection.add(sourceType);
		selection.add(targetType);
				
		parser.select(selection,true);
		parser.autoSelectDependencies(OntoUMLParser.COMPLETE_HIERARCHY, false);
		return parser;
	}

	@Override
	public String getShortName() {
		return parser.getStringRepresentation(association);
	}
	
	
	//******************** FIXES ************************\\
	
	public boolean setIsEssential(Classifier whole, Classifier part, boolean isEssential, boolean isImmutablePart){
		if(association instanceof Meronymic){
			Meronymic meronymic = (Meronymic) getOrCreateRelation(whole,part);
			meronymic.setIsEssential(isEssential);
			if(isEssential)
				isImmutablePart = true;
			meronymic.setIsImmutablePart(isImmutablePart);
			
			if(!createdAssociations.contains(meronymic))
				fix.includeModified(meronymic);
			
			return true;
		}
		return false;
	}
	
	public boolean setIsInseparable(Classifier whole, Classifier part, boolean isInseparable, boolean isImmutableWhole){
		if(association instanceof Meronymic){
			
			Meronymic meronymic = (Meronymic) getOrCreateRelation(whole,part);
			meronymic.setIsInseparable(isInseparable);
			if(isInseparable)
				isImmutableWhole = true;
			meronymic.setIsImmutableWhole(isImmutableWhole);
			
			if(!createdAssociations.contains(meronymic))
				fix.includeModified(meronymic);
			
			return true;
		}
		return false;
	}
	
	public boolean setIsShareable(Classifier whole, Classifier part, boolean isShareable){
		if(association instanceof Meronymic){
			Meronymic meronymic = (Meronymic) getOrCreateRelation(whole,part);
			meronymic.setIsShareable(isShareable);
			Property wholeEnd = OntoUMLParser.getWholeEnd(meronymic);
			
			boolean addToModified = !createdAssociations.contains(meronymic);
			fix.addAll(fixer.changePropertyMultiplicity(wholeEnd, wholeEnd.getLower(), wholeEnd.getUpper(), addToModified));
			
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("static-access")
	public boolean setIsReadOnly(Classifier source, Classifier target, boolean isReadOnlySource, boolean isReadOnlyTarget){
		
		Property pSource, pTarget;
		Association a = getOrCreateRelation(source, target);
		
		if(a instanceof Meronymic){
			pSource=parser.getWholeEnd((Meronymic) a);
			pTarget=parser.getPartEnd((Meronymic) a);
		}
		else if (a instanceof Mediation){
			pSource=parser.getRelatorEnd((Mediation) a);
			pTarget=parser.getMediatedEnd((Mediation) a);
			isReadOnlyTarget=true;
		}
		else if (a instanceof Characterization){
			pSource=parser.getCharacterizingEnd((Characterization) a);
			pTarget=parser.getCharacterizedEnd((Characterization) a);
			isReadOnlyTarget=true;
		}
		else{
			pSource = a.getMemberEnd().get(0);
			pTarget = a.getMemberEnd().get(1);
		}
		
		if(!createdAssociations.contains(a)){
			fix.includeModified(pSource);
			fix.includeModified(pTarget);
		}
		
		pSource.setIsReadOnly(isReadOnlySource);
		pTarget.setIsReadOnly(isReadOnlyTarget);
		
		return true;
	}
	
	@SuppressWarnings("static-access")
	public boolean setIsDerived(Classifier source, Classifier target, boolean isDerivedSource, boolean isDerivedTarget){
		
		Property pSource, pTarget;
		Association a = getOrCreateRelation(source, target);
		
		if(a instanceof Meronymic){
			pSource=parser.getWholeEnd((Meronymic) a);
			pTarget=parser.getPartEnd((Meronymic) a);
		}
		else if (a instanceof Mediation){
			pSource=parser.getRelatorEnd((Mediation) a);
			pTarget=parser.getMediatedEnd((Mediation) a);
		}
		else if (a instanceof Characterization){
			pSource=parser.getCharacterizingEnd((Characterization) a);
			pTarget=parser.getCharacterizedEnd((Characterization) a);
		}
		else{
			pSource = a.getMemberEnd().get(0);
			pTarget = a.getMemberEnd().get(1);
		}
		
		if(!createdAssociations.contains(a)){
			fix.includeModified(pSource);
			fix.includeModified(pTarget);
		}
		
		pSource.setIsDerived(isDerivedSource);
		pTarget.setIsDerived(isDerivedTarget);
		
		return true;
	}
	
	@SuppressWarnings("static-access")
	public boolean setMultiplicity(Classifier source, Classifier target, int lowerSource, int upperSource, int lowerTarget, int upperTarget){
		
		Property pSource, pTarget;
		Association a = getOrCreateRelation(source, target);
		
		if(a instanceof Meronymic){
			pSource=parser.getWholeEnd((Meronymic) a);
			pTarget=parser.getPartEnd((Meronymic) a);
		}
		else if (a instanceof Mediation){
			pSource=parser.getRelatorEnd((Mediation) a);
			pTarget=parser.getMediatedEnd((Mediation) a);
		}
		else if (a instanceof Characterization){
			pSource=parser.getCharacterizingEnd((Characterization) a);
			pTarget=parser.getCharacterizedEnd((Characterization) a);
		}
		else{
			pSource = a.getMemberEnd().get(0);
			pTarget = a.getMemberEnd().get(1);
		}
		
		boolean addToModified = !createdAssociations.contains(a);
		
		fix.addAll(fixer.changePropertyMultiplicity(pSource,lowerSource,upperSource,addToModified));
		fix.addAll(fixer.changePropertyMultiplicity(pTarget,lowerTarget,upperTarget,addToModified));
		
		return true;
	}
		
	private Association getExistingRelation (Classifier source, Classifier target){
		
		for (Association assoc : parser.getAllInstances(association.getClass()))
			if ((assoc.getMemberEnd().get(0).getType().equals(source) && assoc.getMemberEnd().get(1).getType().equals(target)) || (assoc.getMemberEnd().get(0).getType().equals(target) && assoc.getMemberEnd().get(1).getType().equals(source)))
				return assoc;
		
		return null;
	}
	
	private Association getCreatedRelation (Classifier source, Classifier target){
		
		if (!createdAssociations.isEmpty()){
			for (Association created : createdAssociations) {
				if ((created.getMemberEnd().get(0).getType().equals(source) && created.getMemberEnd().get(1).getType().equals(target)) || 
						(created.getMemberEnd().get(0).getType().equals(target) && created.getMemberEnd().get(1).getType().equals(source)))
					return created;
			}
		}
		
		return null;
	}
	
	@SuppressWarnings("static-access")
	private Association createNewRelation(Classifier source, Classifier target){
		Association newAssoc;
		Property generalTarget, specificTarget;
		
		String newName = "new_"+source.getName().trim()+"_"+target.getName().trim();
		newName.replaceAll("\\s+","");
		
		Fix fixAux = fixer.createAssociationBetween(fixer.getRelationshipStereotype(association),newName, source, target);
		newAssoc = fixAux.getAddedByType(Association.class).get(0);
		fixer.copyOnlyMetaProperties(association, newAssoc);
		newAssoc.setName(newName);
		fix.addAll(fixAux);
		createdAssociations.add(newAssoc);
		
		if(newAssoc instanceof Meronymic){
			generalTarget=parser.getPartEnd((Meronymic) association);
			specificTarget=parser.getPartEnd((Meronymic) newAssoc);
		}
		else if (newAssoc instanceof Mediation){
			generalTarget=parser.getMediatedEnd((Mediation) association);
			specificTarget=parser.getMediatedEnd((Mediation) newAssoc);
		}
		else if (newAssoc instanceof Characterization){
			generalTarget=parser.getCharacterizedEnd((Characterization) association);
			specificTarget=parser.getCharacterizedEnd((Characterization) newAssoc);
		}
		else{
			generalTarget = association.getMemberEnd().get(1);
			specificTarget = newAssoc.getMemberEnd().get(1);
		}
		
		fix.addAll(fixer.subsetProperty(generalTarget, specificTarget, SpecializationType.SUBSET, true));
		
		return newAssoc;
	}
	
	private Association getOrCreateRelation(Classifier source, Classifier target){
		Association a = getExistingRelation(source, target);
		if(a!=null)
			return a;
		a = getCreatedRelation(source, target);
		if(a!=null)
			return a;
		return createNewRelation(source, target);
		
	}
	 
}
