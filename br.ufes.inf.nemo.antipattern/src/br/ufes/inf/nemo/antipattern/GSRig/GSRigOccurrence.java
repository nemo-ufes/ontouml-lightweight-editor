package br.ufes.inf.nemo.antipattern.GSRig;

import java.text.Normalizer;
import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.AntiRigidSortalClass;
import RefOntoUML.Category;
import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.Mixin;
import RefOntoUML.PackageableElement;
import RefOntoUML.RigidSortalClass;
import RefOntoUML.RoleMixin;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;
import br.ufes.inf.nemo.common.ontoumlfixer.ClassStereotype;
import br.ufes.inf.nemo.common.ontoumlfixer.RelationStereotype;

//Mixed Rigidity in Generalization Set
public class GSRigOccurrence extends AntipatternOccurrence{

	GeneralizationSet gs;
	ArrayList<Classifier> specifics;
	ArrayList<Classifier> rigidSpecifics;
	ArrayList<Classifier> antiRigidSpecifics;
	ArrayList<Classifier> semiRigidSpecifics;
	
	public GeneralizationSet getGs() {
		return gs;
	}
	
	public Classifier getParent(){
		return gs.getGeneralization().get(0).getGeneral();
	}

	public ArrayList<Classifier> getSpecifics() 
	{	
		ArrayList<Classifier> result = new ArrayList<Classifier>();
		for (Generalization g : gs.getGeneralization()) 
		{	
			Classifier specific = g.getSpecific();				
			if(specific!=null) result.add(specific);
		}
		return result;
	}

	public ArrayList<Classifier> getRigidSpecifics() 
	{
		ArrayList<Classifier> result = new ArrayList<Classifier>();
		for (Classifier specific: getSpecifics()) 
		{
			if (specific instanceof RigidSortalClass || specific instanceof Category )
				result.add(specific);			
		}
		return result;
	}

	public ArrayList<Classifier> getAntiRigidSpecifics() {
		ArrayList<Classifier> result = new ArrayList<Classifier>();
		for (Classifier specific: getSpecifics()) 
		{
			if (specific instanceof AntiRigidSortalClass || specific instanceof RoleMixin)
			result.add(specific);
		}
		return result;
	}

	public ArrayList<Classifier> getSemiRigidSpecifics() {
		ArrayList<Classifier> result = new ArrayList<Classifier>();
		for (Classifier specific: getSpecifics()) 
		{
			if (specific instanceof Mixin)
				result.add(specific);
		}
		return result;
	}

	
	
	public GSRigOccurrence(GeneralizationSet gs, GSRigAntipattern ap) throws Exception {
		
		super(ap);
		
		this.gs = gs;
		this.specifics = new ArrayList<Classifier>();
		this.rigidSpecifics = new ArrayList<Classifier>();
		this.antiRigidSpecifics = new ArrayList<Classifier>();
		this.semiRigidSpecifics = new ArrayList<Classifier>();
		
		//separates into different list the rigid, anti-rigid and semi-rigid subtypes
		for (Generalization g : gs.getGeneralization()) {
			//only considers generalizations that: 
			//1) are selected in the parser, 
			//2) the type connected in the supertype is selected, 
			//3) and the type connected as the subtype is selected
			if (parser.isSelected(g) && parser.isSelected(g.getSpecific()) && parser.isSelected(g.getGeneral())){
				
				Classifier specific = g.getSpecific();
				
				if (specific instanceof RigidSortalClass || specific instanceof Category )
					rigidSpecifics.add(specific);
				else if (specific instanceof AntiRigidSortalClass || specific instanceof RoleMixin)
					antiRigidSpecifics.add(specific);
				else if (specific instanceof Mixin)
					semiRigidSpecifics.add(specific);
			}
		}
		
		if (rigidSpecifics.size()==0 && antiRigidSpecifics.size()==0)
			throw new Exception("Only semi rigid specifics.");
		if (rigidSpecifics.size()==0 && semiRigidSpecifics.size()==0)
			throw new Exception("Only anti rigid specifics.");
		if (semiRigidSpecifics.size()==0 && antiRigidSpecifics.size()==0)
			throw new Exception("Only rigid specifics.");
		
		specifics.addAll(antiRigidSpecifics);
		specifics.addAll(rigidSpecifics);
		specifics.addAll(semiRigidSpecifics);
	}

	@Override
	public OntoUMLParser setSelected() {
		ArrayList<EObject> selection = new ArrayList<EObject>();
		
		selection.add(gs);
		for (Generalization g : gs.getGeneralization()) {
			selection.add(g);
			selection.add(g.getGeneral());
			selection.add(g.getSpecific());
		}
		
		parser.select(selection,true);
		parser.autoSelectDependencies(OntoUMLParser.SORTAL_ANCESTORS, false);

		return parser;
	}
	
	@Override
	public String toString(){
		String result = parser.getStringRepresentation(gs)+"\n"+
						"Rigid Supertype: "+parser.getStringRepresentation(gs.getGeneralization().get(0).getGeneral())+"\n";
		
		result+="Rigid Subtypes: ";
		for (Classifier rigid : this.rigidSpecifics)
			result+="\n\t"+parser.getStringRepresentation(rigid);
		
		result+="\nAnti-Rigid Subtypes: ";
		for (Classifier antiRigid : this.antiRigidSpecifics)
			result+="\n\t"+parser.getStringRepresentation(antiRigid);
		
		result+="\nSemi-Rigid Subtypes: ";
		for (Classifier semiRigid : this.semiRigidSpecifics)
			result+="\n\t"+parser.getStringRepresentation(semiRigid);
		
		return result;
		
	}

	@Override
	public String getShortName() {
		return parser.getStringRepresentation(getGs());
	}

	// OUTCOMING FIXES ================================================
	
	public void deleteGenSet() {
		fix.addAll(fixer.deleteElement(getGs()));		
	}

	public void createGenSetForRigids() {
		fix.addAll(fixer.createGeneralizationSet(getGs().getGeneralization().get(0).getGeneral(),getRigidSpecifics(), true, true));				
	}

	public void createGenSetForAntiRigids() {
		fix.addAll(fixer.createGeneralizationSet(getGs().getGeneralization().get(0).getGeneral(),getAntiRigidSpecifics(), true, true));		
	}

	public void createGenSetForBoth() {
		fix.addAll(fixer.createGeneralizationSet(getGs().getGeneralization().get(0).getGeneral(),getAntiRigidSpecifics(), true, true));
		fix.addAll(fixer.createGeneralizationSet(getGs().getGeneralization().get(0).getGeneral(),getRigidSpecifics(), true, true));
	}

	public void changeSuperTypeToMixin() {
		fix.addAll(fixer.changeClassStereotypeTo(getGs().getGeneralization().get(0).getGeneral(), ClassStereotype.MIXIN));
	}

	public void createOclDerivationByNegation() {
		fix.includeRule(
			"context <NewRigidSubtype> :: allInstances() : Set(<NewRigidSubtype>)"+"\n"+
			"body : <RigidSuperType>.allInstances()->select ( x | not (x.oclIsTypeOf(<Rigid-1>) or x.oclIsTypeOf(<Rigid-2>) ) )"		
		);
	}

	public void createRigidSubtypeForAntiRigidsFrom(ArrayList<String> antirigids) 
	{
		ArrayList<Classifier> antirigidList = new ArrayList<Classifier>();
		for(Generalization gen: getGs().getGeneralization())
		{
			String str = getStereotype(gen.getSpecific())+" "+gen.getSpecific().getName();
			for(String str2: antirigids){
				if (str.trim().compareToIgnoreCase(str2.trim())==0) antirigidList.add(gen.getSpecific()); 
			}			
		}
			
		createRigidSubtypeForAntiRigids(antirigidList);	
	}
	
	public void createRigidSubtypeForAntiRigids(ArrayList<Classifier> antirigids)	
	{
		int i=0;
		Classifier supertype = getGs().getGeneralization().get(0).getGeneral();
		
		for(Classifier c: antirigids)
		{
			PackageableElement newrigid = null;
			// create new rigid
			if(supertype instanceof Category) newrigid = fixer.createClass(ClassStereotype.CATEGORY);
			else newrigid = fixer.createClass(ClassStereotype.SUBKIND);
			
			newrigid.setName("Subtype"+i);
			fixer.copyContainer(c, newrigid);
			fix.includeAdded(newrigid);
			fix.includeModified(c.eContainer());
			
			// set general to point to new rigid 
			Generalization generalization = null;
			for(Generalization gen: getGs().getGeneralization()){
				if(gen.getSpecific().equals(c)){
					generalization = gen;
					gen.setGeneral((RefOntoUML.Classifier)newrigid);					
				}
			}
			if(generalization!=null) fix.includeModified(generalization);
			
			//remove it from GS
			if(generalization!=null) { 
				getGs().getGeneralization().remove(generalization);
				fix.includeModified(getGs());
			}
			
			//create new generalization from new rigid to Rigid super type			
			// create generalization
			Generalization newg = (Generalization) fixer.createRelationship(RelationStereotype.GENERALIZATION);
			newg.setGeneral(supertype);
			newg.setSpecific((Classifier)newrigid);
			fix.includeAdded(newg);		
									
			//add created generalization to GS			
			getGs().getGeneralization().add(newg);
			
			i++;
		}
		
	}

	public void createCommonSubtypeForAntiRigidsFrom(ArrayList<String> antirigids) 
	{
		ArrayList<Classifier> antirigidList = new ArrayList<Classifier>();
		for(Generalization gen: getGs().getGeneralization())
		{
			String str = getStereotype(gen.getSpecific())+" "+gen.getSpecific().getName();
			for(String str2: antirigids){
				if (str.trim().compareToIgnoreCase(str2.trim())==0) antirigidList.add(gen.getSpecific()); 
			}			
		}
			
		createCommonSubtypeForAntiRigids(antirigidList);	
	}
	
	public void createCommonSubtypeForAntiRigids(ArrayList<Classifier> antirigids)  
	{		
		Classifier supertype = getGs().getGeneralization().get(0).getGeneral();
			
		PackageableElement newrigid = null;
		// create new rigid
		if(supertype instanceof Category) newrigid = fixer.createClass(ClassStereotype.CATEGORY);
		else newrigid = fixer.createClass(ClassStereotype.SUBKIND);
		newrigid.setName("CommonSubtype");
		fixer.copyContainer(supertype, newrigid);
		fix.includeAdded(newrigid);	
		fix.includeModified(supertype.eContainer());
		
		ArrayList<Generalization> antirigidGens = new ArrayList<Generalization>();
		for(Classifier c: antirigids)
		{	
			// set general to point to new rigid 
			Generalization generalization = null;
			for(Generalization gen: getGs().getGeneralization()){
				if(gen.getSpecific().equals(c)){
					generalization = gen;
					gen.setGeneral((RefOntoUML.Classifier)newrigid);					
				}
			}
			if(generalization!=null) { fix.includeModified(generalization); antirigidGens.add(generalization); }
			
			//remove it from GS
			if(generalization!=null) { 
				getGs().getGeneralization().remove(generalization);
				fix.includeModified(getGs());
			}			
		}
		
		//create a new genset for the antirigids
		fix.addAll(fixer.createGeneralizationSet(antirigidGens,getGs().isIsDisjoint(),getGs().isIsCovering(),getGs().getName()));
		
		//create new generalization from new rigid to Rigid super type			
		// create generalization
		Generalization newg = (Generalization) fixer.createRelationship(RelationStereotype.GENERALIZATION);
		newg.setGeneral(supertype);
		newg.setSpecific((Classifier)newrigid);
		fix.includeAdded(newg);		
								
		//add created generalization to GS			
		getGs().getGeneralization().add(newg);
		
	}

	public static String getStereotype(EObject element)
	{
		String type = element.getClass().toString().replaceAll("class RefOntoUML.impl.","");
	    type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);
	    if (!type.equalsIgnoreCase("association")) type = type.replace("Association","");
	    return type;
	}
	
	
	public void changeSpecificsStereotypes(ArrayList<String> specificsNewStereotypes) 
	{
		if(specificsNewStereotypes.size()!=getSpecifics().size()) return;
		int i=0;
		for(Classifier c: getSpecifics())
		{
			String stereo = specificsNewStereotypes.get(i);
			if(getStereotype(c).compareToIgnoreCase(stereo)!=0){
				fix.addAll(fixer.changeClassStereotypeTo(c, fixer.getClassStereotype(stereo)));	
			}
			i++;
		}
	}

	public void changeSuperTypeAndSpecificsStereotypes(String supertypeStereo, ArrayList<String> specificsNewStereotypes, boolean createNewSuperType) 
	{
		String currentStereo = getStereotype(getGs().getGeneralization().get(0).getGeneral());
		
		//change current supertype stereotype
		fix.addAll(fixer.changeClassStereotypeTo(getGs().getGeneralization().get(0).getGeneral(), fixer.getClassStereotype(supertypeStereo)));
		
		if(createNewSuperType)
		{
			// create new supertype
			PackageableElement newsupertype = fixer.createClass(fixer.getClassStereotype(currentStereo));
			newsupertype.setName("NewSupertype");
			fixer.copyContainer(getGs().getGeneralization().get(0).getSpecific(), newsupertype);
			fix.includeAdded(newsupertype);
			fix.includeModified(newsupertype.eContainer());
			
			//create generalization from old supertype to new supertype
			Generalization newg = (Generalization) fixer.createRelationship(RelationStereotype.GENERALIZATION);
			newg.setGeneral((Classifier)newsupertype);
			newg.setSpecific((Classifier)getGs().getGeneralization().get(0).getGeneral());
			fix.includeAdded(newg);	
		}
		
		//change rigid specifics stereotypes
		if(specificsNewStereotypes.size()!=getRigidSpecifics().size()) return;
		int i=0;
		for(Classifier c: getRigidSpecifics())
		{
			String stereo = specificsNewStereotypes.get(i);
			if(getStereotype(c).compareToIgnoreCase(stereo)!=0){
				fix.addAll(fixer.changeClassStereotypeTo(c, fixer.getClassStereotype(stereo)));	
			}
			i++;
		}
	}

	public void createNewMixinAsSuperType() 
	{
		fix.addAll(fixer.createCommonSuperType(getSpecifics(), ClassStereotype.MIXIN));
		//set the old generalization for these generalizations created
		getGs().getGeneralization().clear();
		for(Object obj: fix.getAdded()){
			if(obj instanceof Generalization){
				Generalization gen = (Generalization)obj;				
				getGs().getGeneralization().add(gen);
			}
		}
	}

}
