package br.ufes.inf.nemo.antipattern.freerole;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Classifier;
import RefOntoUML.Mediation;
import RefOntoUML.Property;
import RefOntoUML.Relator;
import RefOntoUML.Role;
import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer.ClassStereotype;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer.RelationStereotype;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

//Undefined Role Specialization
public class FreeRoleOccurrence extends AntipatternOccurrence{

	public Role getDefinedRole() {
		return definedRole;
	}

	public ArrayList<Property> getDefiningRelatorEnds() {
		return definingRelatorEnds;
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


	private Role definedRole;
	private ArrayList<Property> definingRelatorEnds;
	private HashMap<Role,ArrayList<Property>> subRoleHash;
	private ArrayList<Role> freeRoles;
	private ArrayList<Role> definedRoles;
	
	public FreeRoleOccurrence(Role role, ArrayList<Property> relatorEnds, FreeRoleAntipattern ap) throws Exception {
		super(ap);
		
		if (role==null || relatorEnds==null || parser==null)
			throw new NullPointerException("FreeRole: It is not possible to create an occurence of the anti-pattern if one of the parameters is null");
		
		if (relatorEnds.size()==0)
			throw new Exception("Defined Role must be connected to at least one mediation!!");
		
		for (Property p : relatorEnds) {
			if (!(p.getAssociation() instanceof Mediation) || !p.getOpposite().getType().equals(role) || !(p.getType() instanceof Relator))
				throw new Exception("Defined Role must be connected to at least one mediation!!");
		}
		
		this.definedRole = role;
		this.definingRelatorEnds = relatorEnds;
				
		this.subRoleHash = new HashMap<Role, ArrayList<Property>>();
		this.freeRoles = new ArrayList<Role>(); 
		this.definedRoles = new ArrayList<Role>(); 
		
		for (Classifier child : parser.getAllChildren(role)) {
			
			if (child instanceof Role) {
				
				ArrayList<Property> subRoleMediations = new ArrayList<Property>();
				
				for (Mediation m : parser.getAllInstances(Mediation.class)) {
					Property source = m.getMemberEnd().get(0);
					Property target = m.getMemberEnd().get(1);
					
					if (source.getType().equals(child))
						subRoleMediations.add(target);
					else if (target.getType().equals(child))
						subRoleMediations.add(source);
				}
				
				this.subRoleHash.put((Role) child, subRoleMediations);
				
				if (subRoleMediations.size()==0)
					this.freeRoles.add((Role) child);
				else
					this.definedRoles.add((Role) child);
				
			}
		}
		
		if (this.freeRoles.size()==0)
			throw new Exception("FreeRole: The are no undefined subroles!!");
		
	}

	@Override
	public OntoUMLParser setSelected() {
		ArrayList<EObject> selection = new ArrayList<EObject>();
		
		selection.add(definedRole);
		
		for (Property p : this.definingRelatorEnds)
			selection.add(p.getAssociation());
		
		selection.addAll(subRoleHash.keySet());
		
		for (ArrayList<Property> subRoleRelatorEnds : subRoleHash.values())
			for (Property p : subRoleRelatorEnds)
				selection.add(p.getAssociation());
						
		parser.selectThisElements(selection,true);
		parser.autoSelectDependencies(OntoUMLParser.SORTAL_ANCESTORS, false);

		return parser;
	}
	
	
	@Override
	public String toString(){
		
		String result = "Defined Role: "+ parser.getStringRepresentation(definedRole)+"\n"+
						"Defining Relator(s): ";
		
		for (int i = 0; i < this.definingRelatorEnds.size(); i++) {
			if(i>0)
				result += ", ";
			result += parser.getStringRepresentation(definingRelatorEnds.get(i).getType());
		}
		
		result+="\nFree Roles: \n";
		
		for (Role undefinedRole : this.freeRoles) {
			result += "\t"+parser.getStringRepresentation(undefinedRole)+"\n";
		}
		
		result+="\nDefined Sub Roles: \n";
		
		for (Role defined : this.definedRoles) {
			result += "\t"+parser.getStringRepresentation(defined)+"\n";
		}
		
		return result;
		
	}

	@Override
	public String getShortName() {
		return parser.getStringRepresentation(getDefinedRole());
	}

	// OUTCOMING FIXES ==========================================================
	
	public void createOCLDerivation(String oclDerive) {
		fix.includeRule(oclDerive);
	}

	public void createNewMediation(Relator relator, Role role,String relatorEndMultip, String roleEndMultip) {
		fix.addAll(fixer.createAssociationBetween(RelationStereotype.MEDIATION, "", relator, role));
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

	public void createSubRelatorWithMediation(Relator relator, Role role,String subRelatorName,String relatorEndMultip,String roleEndMultip) {
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

}


