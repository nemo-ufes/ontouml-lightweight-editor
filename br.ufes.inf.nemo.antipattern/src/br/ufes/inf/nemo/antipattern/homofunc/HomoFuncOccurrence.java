package br.ufes.inf.nemo.antipattern.homofunc;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.AntiRigidSortalClass;
import RefOntoUML.Association;
import RefOntoUML.Category;
import RefOntoUML.Class;
import RefOntoUML.Classifier;
import RefOntoUML.Collective;
import RefOntoUML.Meronymic;
import RefOntoUML.Mixin;
import RefOntoUML.Mode;
import RefOntoUML.Property;
import RefOntoUML.Quantity;
import RefOntoUML.Relator;
import RefOntoUML.RoleMixin;
import RefOntoUML.SubKind;
import RefOntoUML.Type;
import RefOntoUML.componentOf;
import RefOntoUML.parser.OntoUMLNameHelper;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;
import br.ufes.inf.nemo.common.ontoumlfixer.ClassStereotype;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.RelationStereotype;

public class HomoFuncOccurrence extends AntipatternOccurrence {
	
	private Classifier whole;
	private Property partEnd;
	private ArrayList<Classifier> wholeIdentityProvidersList;
	private ArrayList<Property> functionalWholes;
	
	
	public HomoFuncOccurrence(Association compOf, ArrayList<Property> functionalWholes, HomoFuncAntipattern ap) throws Exception {
		super(ap);
		
		if (compOf==null)
			throw new NullPointerException("HomoFunc: null inputs!");
		if (compOf instanceof componentOf){
			
			whole = (Classifier) OntoUMLParser.getWholeEnd((Meronymic) compOf).getType();
			partEnd = OntoUMLParser.getPartEnd((Meronymic) compOf);
		
		}else
			throw new Exception("HomoFunc: provided relation must be an instance of componentOf.");
		
		if(whole==null || partEnd==null)
			throw new NullPointerException("HomoFunc: null inputs!");
		
		if(whole instanceof Collective || whole instanceof Quantity || whole instanceof Relator || whole instanceof Mode)
			throw new Exception("HomoFunc: whole type not acceptable. Whole must be a functional complex");
		
		if (!(partEnd.getAssociation() instanceof componentOf))
			throw new Exception("HomoFunc: partEnd must refer to componentOf relation.");
		
		if (!partEnd.getOpposite().getType().equals(whole))
			throw new Exception("HomoFunc: partEnd must refer to componentOf relation that is connected to the provided whole");
		
		setFunctionalWholes(functionalWholes);

	}

	public Classifier getWhole() {
		return whole;
	}
	
	public Classifier getPart(){
		return (Classifier) partEnd.getType();
	}

	public Property getPartEnd() {
		return partEnd;
	}
	
	public ArrayList<Property> getFunctionalWholes() {
		return functionalWholes;
	}

	public void setFunctionalWholes(ArrayList<Property> functionalWholes) {
		this.functionalWholes = functionalWholes;
	}
	
	public ArrayList<Classifier> getWholeIdentityProviders()
	{
		if(wholeIdentityProvidersList==null){
			wholeIdentityProvidersList = new ArrayList<Classifier>();
			wholeIdentityProvidersList.addAll(parser.getIdentityProvider(whole));
		}			
		
		return wholeIdentityProvidersList;
	}
	
	public boolean isCollectionPart(){
		return parser.isCollective((Classifier) partEnd.getType());
	}
	
	@Override
	public OntoUMLParser setSelected() {
		ArrayList<EObject> selection = new ArrayList<EObject>();
		
		selection.add(this.whole);
		selection.add(this.partEnd.getAssociation());
		selection.add(this.partEnd.getType());
				
		parser.select(selection,true);
		parser.autoSelectDependencies(OntoUMLParser.SORTAL_ANCESTORS, false);

		return parser;
	}
	
	@Override
	public String toString(){
		String result = 
				"Functional Complex: "+OntoUMLNameHelper.getTypeAndName(whole, true, true)+"\n" +
				"Part: "+OntoUMLNameHelper.getCommonName(partEnd.getType());
		return result;
	}

	@Override
	public String getShortName() {
		return "Functional Complex: "+OntoUMLNameHelper.getTypeAndName(whole, true, true);
	}

	//====================================
	// OUTCOMING FIXES
	//====================================
	
	public void changeToCollective() {
		fix.addAll(fixer.changeClassStereotypeTo(whole, ClassStereotype.COLLECTIVE));
	}
	
	public void changeNatureToCollection() {
		fix.addAll(fixer.changeNature(whole, new ArrayList<Classifier>(), ClassStereotype.COLLECTIVE));
	}

	public void changeToMemberOf() {
		fix.addAll(fixer.changeRelationStereotypeTo(partEnd.getAssociation(), RelationStereotype.MEMBEROF));		
	}

	public void changeToSubCollectionOf() {
		fix.addAll(fixer.changeRelationStereotypeTo(partEnd.getAssociation(), RelationStereotype.SUBCOLLECTIONOF));		
	}

	public void createNewIdentityProvider() {
		Classifier c = this.whole;
		ClassStereotype stereo = ClassStereotype.COLLECTIVE;
		String name = "NewIdentityProvider";
		
		fix.addAll(fixer.removeIdentity(c, new ArrayList<Classifier>()));
		
		if(c instanceof SubKind || c instanceof AntiRigidSortalClass){
			fix.addAll(fixer.createSuperTypeAs(c, stereo, name));
		}
		else if (c instanceof Category || c instanceof Mixin){
			fix.addAll(fixer.createSubTypeAs(c, stereo, name));
		}
		else if (c instanceof RoleMixin){
			Class idenProv = (Class) fixer.createClass(stereo);
			Class role = (Class) fixer.createClass(ClassStereotype.ROLE);
			
			role.setName("RoleOf_"+name);
			idenProv.setName(name);
			
			fix.includeAdded(idenProv);
			fix.includeAdded(role);
			fix.addAll(fixer.createGeneralization(role, idenProv));
			fix.addAll(fixer.createGeneralization(role, c));
		}
	}


	public void createNewPartToWhole(String partStereotype, String partName, String componentOfName, boolean isEssential, boolean isInseparable, boolean isShareable, boolean isImmutablePart, boolean isImmutableWhole) 
	{
		fix.addAll(fixer.createPartWithComponentOfTo(whole, fixer.getClassStereotype(partStereotype),partName,componentOfName,isEssential,isInseparable,isShareable,isImmutablePart,isImmutableWhole));
	}
	
	public void createNewSubPartToWhole(String partStereotype, String partName, String componentOfName, boolean isEssential, boolean isInseparable, boolean isShareable, boolean isImmutablePart, boolean isImmutableWhole)
	{
		fix.addAll(fixer.createSubPartWithSubComponentOfTo(whole, partEnd, fixer.getClassStereotype(partStereotype),partName,componentOfName,isEssential,isInseparable,isShareable,isImmutablePart,isImmutableWhole));
	}

	public void createSubComponentOfToExistingSubPart(Type subpart, String componentOfName, boolean isEssential, boolean isInseparable, boolean isShareable, boolean isImmutablePart, boolean isImmutableWhole) 
	{
		fix.addAll(fixer.createSubComponentOfToExistingSubPart(whole,partEnd,subpart, componentOfName,isEssential,isInseparable,isShareable,isImmutablePart,isImmutableWhole));
	}
	
	public void createComponentOfToExistingType(Type type, String componentOfName, boolean isEssential, boolean isInseparable, boolean isShareable, boolean isImmutablePart, boolean isImmutableWhole) {
		Fix fixes = fixer.createAssociationBetween(RelationStereotype.COMPONENTOF, componentOfName, whole, type);
		Meronymic m = (Meronymic) fixes.getAdded().get(0);
		
		m.setIsEssential(isEssential);
		m.setIsInseparable(isInseparable);
		m.setIsShareable(isShareable);
		m.setIsImmutablePart(isImmutablePart);
		m.setIsImmutableWhole(isImmutableWhole);
		
		fix.addAll(fixes);
	}

	
	
}
