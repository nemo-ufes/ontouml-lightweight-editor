package br.ufes.inf.nemo.antipattern.freerole;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Classifier;
import RefOntoUML.MaterialAssociation;
import RefOntoUML.Mediation;
import RefOntoUML.ObjectClass;
import RefOntoUML.Property;
import RefOntoUML.Relator;
import RefOntoUML.Role;
import RefOntoUML.RoleMixin;
import RefOntoUML.parser.OntoUMLNameHelper;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;
import br.ufes.inf.nemo.common.ontoumlfixer.ClassStereotype;
import br.ufes.inf.nemo.common.ontoumlfixer.RelationStereotype;

//Undefined Role Specialization
public class FreeRoleOccurrence extends AntipatternOccurrence{

	private Classifier dependentType;
	private ArrayList<Property> directRelators;
	private HashMap<Role,ArrayList<Property>> subRoleHash;
	private ArrayList<Role> freeRoles;
	private ArrayList<Role> definedRoles;
	
	public Classifier getDependentType() {
		return dependentType;
	}

	public ArrayList<Property> getDefiningRelatorEnds() {
		return directRelators;
	}

	public HashMap<Role, ArrayList<Property>> getSubRoleHash() {
		return subRoleHash;
	}

	public ArrayList<Role> getFreeRoles() {
		return freeRoles;
	}

	public ArrayList<Role> getDefinedRoles() {
		return definedRoles;
	}

	public FreeRoleOccurrence(Classifier mediated, ArrayList<Property> directRelators, FreeRoleAntipattern ap) throws Exception {
		super(ap);
		
		if (mediated==null || directRelators==null || parser==null)
			throw new NullPointerException("FreeRole: It is not possible to create an occurence of the anti-pattern if one of the parameters is null");
		
		if (directRelators.size()==0)
			throw new Exception("FreeRole: Defined Type must be connected to at least one mediation!!");
		
		for (Property p : directRelators) {
			if (!(p.getAssociation() instanceof Mediation))
				throw new Exception("FreeRole: Provided properties must be belong to mediations!!");
			if(!(p.getType() instanceof Relator))
				throw new Exception("FreeRole: Provided properties must be connected to relators!!");
		}
		
		dependentType = mediated;
		this.directRelators = directRelators;
				
		subRoleHash = new HashMap<Role, ArrayList<Property>>();
		freeRoles = new ArrayList<Role>(); 
		definedRoles = new ArrayList<Role>(); 
		
		for (Classifier child : ap.getParser().allChildrenHash.get(mediated)) {
			
			if (child instanceof Role) {
				
				boolean hasDependentMiddleParent = false;
				
				for (Classifier middleParent : parser.getAllParents(child)) {
					if(ap.getParser().allChildrenHash.get(mediated).contains(middleParent) && ap.relatorHash.containsKey(middleParent)){
						hasDependentMiddleParent = true;
						break;
					}
				}
				
				if(hasDependentMiddleParent)
					continue;
				
				boolean hasDefinedMixinParent = false;
				for(Classifier parent : parser.getParents(child)){
					if(parent instanceof RoleMixin && ap.relatorHash.containsKey(parent)){
						hasDefinedMixinParent = true;
						break;
					}
				}
				
				if(hasDefinedMixinParent)
					continue;
				
				ArrayList<Property> subRoleMediations = new ArrayList<Property>();
				
				if(ap.relatorHash.containsKey(child))
					subRoleMediations.addAll(ap.relatorHash.get(child));
				
				subRoleHash.put((Role) child, subRoleMediations);
				
				if (subRoleMediations.size()==0)
					freeRoles.add((Role) child);
				else
					definedRoles.add((Role) child);
				
			}
		}
		
		if (this.freeRoles.size()==0)
			throw new Exception("FreeRole: The are no undefined subroles!!");
		
	}

	@Override
	public OntoUMLParser setSelected() {
		ArrayList<EObject> selection = new ArrayList<EObject>();
		
		selection.add(dependentType);
		
		for (Property p : this.directRelators)
			selection.add(p.getAssociation());
		
		selection.addAll(subRoleHash.keySet());
		
		for (ArrayList<Property> subRoleRelatorEnds : subRoleHash.values())
			for (Property p : subRoleRelatorEnds)
				selection.add(p.getAssociation());
						
		parser.select(selection,true);
		parser.autoSelectDependencies(OntoUMLParser.SORTAL_ANCESTORS, false);

		return parser;
	}
	
	
	@Override
	public String toString(){
		
		String result = "Dependent Type: "+ OntoUMLNameHelper.getName(dependentType)+"\n"+
						"Defining Relator(s):";
		
		String direct = "", indirect = "";
		
		for (Property relatorEnd : directRelators) {
			if(dependentType.equals(relatorEnd.getOpposite().getType()))
				direct += "\r\n\t"+OntoUMLNameHelper.getTypeAndName(relatorEnd.getType(), true, false)+" (direct)";
			else
				indirect += "\r\n\t"+OntoUMLNameHelper.getTypeAndName(relatorEnd.getType(), true, false)+" (from: "+OntoUMLNameHelper.getTypeAndName(relatorEnd.getOpposite().getType(), true, false)+")";
		}
		result += (direct+indirect);
		result+="\nFree Role(s):";
		
		for (Role undefinedRole : this.freeRoles) {
			result += "\r\n\t"+OntoUMLNameHelper.getTypeAndName(undefinedRole, true, false);
		}
		
		if(definedRoles.size()==0)
			return result;
		
		result+="\nDefined Sub Roles:";
		
		for (Role defined : this.definedRoles) {
			result += "\r\n\t"+OntoUMLNameHelper.getTypeAndName(defined, true, false);
		}
		
		return result;
		
	}

	@Override
	public String getShortName() {
		return parser.getStringRepresentation(getDependentType());
	}

	// OUTCOMING FIXES ==========================================================
	
	public void createOCLDerivation(String oclDerive) {
		fix.includeRule(oclDerive);
	}

	public void createMediation(Classifier relator, Role role,String relatorEndMultip, String roleEndMultip) {
		fix.addAll(fixer.createAssociationBetween(RelationStereotype.MEDIATION, "", relator, role));
		Mediation med = null;
		
		for(Object obj: fix.getAdded()) { 
			if (obj instanceof Mediation) 
				med = (Mediation)obj; 
		}
		
		fix.addAll(fixer.changePropertyMultiplicity(med.getMemberEnd().get(0), relatorEndMultip));
		fix.addAll(fixer.changePropertyMultiplicity(med.getMemberEnd().get(1), roleEndMultip));
		
		for(Property p: getDefiningRelatorEnds()){
			if (p.getType().equals(relator) || relator.allParents().contains(p.getType())){
				med.getMemberEnd().get(0).getSubsettedProperty().add(p);
			}				
		}		
	}

	public void createSubRelatorWithMediation(Classifier relator, Role role,String subRelatorName,String relatorEndMultip,String roleEndMultip) {
		fix.addAll(fixer.createSubTypeAs(relator, ClassStereotype.RELATOR));
		Relator subrelator = null;
		for(Object obj: fix.getAdded()) { if (obj instanceof Relator) subrelator = (Relator)obj; }
		subrelator.setName(subRelatorName);
		if(subrelator!=null){
			fix.addAll(fixer.createAssociationBetween(RelationStereotype.MEDIATION, "", subrelator, role));
		}
		Mediation med = null;
		for(Object obj: fix.getAdded()) { if (obj instanceof Mediation) med = (Mediation)obj; }
		fix.addAll(fixer.changePropertyMultiplicity(med.getMemberEnd().get(0), relatorEndMultip));
		fix.addAll(fixer.changePropertyMultiplicity(med.getMemberEnd().get(1), roleEndMultip));
		for(Property p: getDefiningRelatorEnds()){
			if (p.getType().equals(relator)){
				med.getMemberEnd().get(0).getSubsettedProperty().add(p);
			}				
		}		
	}

	public static String getStereotype(EObject element)
	{
		String type = element.getClass().toString().replaceAll("class RefOntoUML.impl.","");
	    type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);
	    if (!type.equalsIgnoreCase("association")) type = type.replace("Association","");
	    return type;
	}
		
	
	public void createDependentObjects(Role role, String typeName, String stereoName, String relatorEndMultip, String roleEndMultip, boolean createMaterial) 
	{
		// get the brand new relator created
		RefOntoUML.Class relator = null;
		for(Object obj: fix.getAdded()) { if(obj instanceof Relator) relator = (Relator)obj; }
		
		RefOntoUML.Class type = null;		
		//search if the type already exists...
		for(RefOntoUML.Class c: parser.getAllInstances(RefOntoUML.Class.class)){
			if (getStereotype(c).compareToIgnoreCase(stereoName)==0){
				if(c.getName().trim().compareToIgnoreCase(typeName)==0){
					type = c;
				}
			}			
		}
		
		if (type==null){
			//create new type
			type = (ObjectClass)fixer.createClass(fixer.getClassStereotype(stereoName));
			fixer.copyContainer(role, type);
			type.setName(typeName);
			fix.includeAdded(type);
			fix.includeModified(role.eContainer());
		}
		
		//create new mediation
		fix.addAll(fixer.createAssociationBetween(RelationStereotype.MEDIATION, "", relator, type));
		
		//get the brand new material createad
		RefOntoUML.Mediation mediation = null;
		for(Object obj: fix.getAdded()) { if(obj instanceof Mediation) mediation = (Mediation)obj; }
		
		// change multiplicities...
		fix.addAll(fixer.changePropertyMultiplicity(mediation.getMemberEnd().get(0), relatorEndMultip));
		fix.addAll(fixer.changePropertyMultiplicity(mediation.getMemberEnd().get(1), roleEndMultip));
		
		if(createMaterial){
			//create new material
			fix.addAll(fixer.createAssociationBetween(RelationStereotype.MATERIAL, "", role, type));
			
			//get the brand new material createad
			RefOntoUML.MaterialAssociation material = null;
			for(Object obj: fix.getAdded()) { if(obj instanceof MaterialAssociation) material = (MaterialAssociation)obj; }
			
			//create new derivation		
			fix.addAll(fixer.createAssociationBetween(RelationStereotype.DERIVATION, "", material, relator));
		}
	}

	public void createRelatorWithMediation(Role role, String subRelatorName, String relatorEndMultip, String roleEndMultip) 
	{
		//create new relator
		RefOntoUML.Type relator = (RefOntoUML.Type)fixer.createClass(ClassStereotype.RELATOR);
		fixer.copyContainer(role, relator);
		relator.setName(subRelatorName);
		fix.includeAdded(relator);
		fix.includeModified(role.eContainer());
		//create new mediation
		fix.addAll(fixer.createAssociationBetween(RelationStereotype.MEDIATION, "", relator, role));
		
		//get the brand new material createad
		RefOntoUML.Mediation mediation = null;
		for(Object obj: fix.getAdded()) { if(obj instanceof Mediation) mediation = (Mediation)obj; }
		
		// change multiplicities...
		fix.addAll(fixer.changePropertyMultiplicity(mediation.getMemberEnd().get(0), relatorEndMultip));
		fix.addAll(fixer.changePropertyMultiplicity(mediation.getMemberEnd().get(1), roleEndMultip));
		
	}

	

}


