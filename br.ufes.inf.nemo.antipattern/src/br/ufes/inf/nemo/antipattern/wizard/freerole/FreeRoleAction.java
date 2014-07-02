package br.ufes.inf.nemo.antipattern.wizard.freerole;

import RefOntoUML.Classifier;
import RefOntoUML.Role;
import br.ufes.inf.nemo.antipattern.freerole.FreeRoleOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntiPatternAction;

public class FreeRoleAction extends AntiPatternAction<FreeRoleOccurrence>{

	public Role role;
	public String oclDerive;
	public Classifier relator;
	public String subRelatorName;
	public String relatorEndMultip;
	public String roleEndMultip;
	public boolean createMaterial;
	public String typeName;
	public String stereoName;
	
	public FreeRoleAction(FreeRoleOccurrence ap) 
	{
		super(ap);
	}

	public enum Action { CREATE_OCL_DERIVATION, CREATE_MEDIATION, CREATE_SUBRELATOR_WITH_MEDIATION, CREATE_NEW_RELATOR_WITH_MEDIATION, CREATE_DEPENDENT_OBJETCS }
	
	@Override
	public void run()
	{
		if(code == Action.CREATE_OCL_DERIVATION) ap.createOCLDerivation(oclDerive);
		if(code == Action.CREATE_MEDIATION) ap.createMediation(relator, role,relatorEndMultip,roleEndMultip);
		if(code == Action.CREATE_SUBRELATOR_WITH_MEDIATION) ap.createSubRelatorWithMediation(relator,role,subRelatorName,relatorEndMultip,roleEndMultip);
		if(code == Action.CREATE_NEW_RELATOR_WITH_MEDIATION) { ap.createRelatorWithMediation(role,subRelatorName,relatorEndMultip,roleEndMultip); }
		if(code == Action.CREATE_DEPENDENT_OBJETCS) { ap.createDependentObjects(role, typeName,stereoName,relatorEndMultip,roleEndMultip,createMaterial); }
	}
	
	public void setCreateOCLDerivation(Role role, String oclDerive)
	{
		code = Action.CREATE_OCL_DERIVATION;	
		this.oclDerive = oclDerive;
		this.role = role;
	}
	
	public void setCreateMediation(Classifier relator2, Role role, String relatorEndMultip,String roleEndMultip)
	{
		code = Action.CREATE_MEDIATION;
		this.relator=relator2;
		this.role = role;
		this.relatorEndMultip=relatorEndMultip;
		this.roleEndMultip=roleEndMultip;
	}
	
	public void setCreateSubRelatorWithMediation(Classifier relator2, Role role, String subRelatorName, String relatorEndMultip,String roleEndMultip)
	{
		code = Action.CREATE_SUBRELATOR_WITH_MEDIATION;
		this.relator = relator2;
		this.subRelatorName = subRelatorName;
		this.role = role;
		this.relatorEndMultip=relatorEndMultip;
		this.roleEndMultip=roleEndMultip;
	}
	
	public void setCreateNewRelatorWithMediation(Role role, String newRelatorName, String relatorEndMultip, String roleEndMultip)
	{
		code = Action.CREATE_NEW_RELATOR_WITH_MEDIATION;
		this.role = role;
		this.relatorEndMultip=relatorEndMultip;
		this.roleEndMultip=roleEndMultip;
		this.subRelatorName = newRelatorName;
	}
	
	public void setCreateDependentObjects(Role role, String typeName, String stereoName, String relatorMultiplicity, String mediatedMutiplicity, boolean createMaterial)
	{
		code = Action.CREATE_DEPENDENT_OBJETCS;		
		this.role = role;
		this.typeName = typeName;
		this.stereoName = stereoName;
		this.relatorEndMultip = relatorMultiplicity;
		this.roleEndMultip = mediatedMutiplicity;
		this.createMaterial = createMaterial;
	}
		
	@Override
	public String toString()
	{
		String result = new String();
		
		if(code == Action.CREATE_OCL_DERIVATION) {
			result += "Create OCL Derivation rule for <<role>> "+role.getName();
		}
		if(code == Action.CREATE_MEDIATION) {
			result += "Create a <<mediation>> from <<relator>> "+relator.getName()+" to <<role>> "+role.getName();
		}
		if(code == Action.CREATE_SUBRELATOR_WITH_MEDIATION) {
			result += "Create a sub <<relator>> "+relator.getName()+"\n";
			result += "Create a <<mediation>> from the sub <<relator>> "+relator.getName()+" to the <<role>> "+role.getName();
		}
		if(code == Action.CREATE_NEW_RELATOR_WITH_MEDIATION){
			result += "Create a <<relator>> "+subRelatorName+"\n";
			result += "Create a <<mediation>> from the new <<relator>> "+subRelatorName+" to the <<role>> "+role.getName();
		}
		if(code == Action.CREATE_DEPENDENT_OBJETCS){
			result += "Create a <<mediation>> from the new <<relator>> to the dependent object <<"+stereoName+">> "+typeName+"\n";
			if(createMaterial){
				result += "Create a <<material>> (with <<derivation>>) between the <<role>> "+role.getName()+" and the dependent object <<"+stereoName+">> "+typeName+"\n";
			}
		}
		return result;
	}
}
		