package br.ufes.inf.nemo.antipattern.wizard.freerole;

import java.text.Normalizer;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Relator;
import RefOntoUML.Role;
import br.ufes.inf.nemo.antipattern.freerole.FreeRoleOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntiPatternAction;

public class FreeRoleAction extends AntiPatternAction<FreeRoleOccurrence>{

	public Role role;
	public String oclDerive;
	public Relator relator;
	public String subRelatorName;
	public String relatorEndMultip;
	public String roleEndMultip;
	
	public FreeRoleAction(FreeRoleOccurrence ap) 
	{
		super(ap);
	}

	public enum Action { CREATE_OCL_DERIVATION, CREATE_NEW_MEDIATION, CREATE_SUBRELATOR_WITH_MEDIATION }
	
	@Override
	public void run()
	{
		if(code == Action.CREATE_OCL_DERIVATION) ap.createOCLDerivation(oclDerive);
		if(code == Action.CREATE_NEW_MEDIATION) ap.createNewMediation(relator, role,relatorEndMultip,roleEndMultip);
		if(code == Action.CREATE_SUBRELATOR_WITH_MEDIATION) ap.createSubRelatorWithMediation(relator,role,subRelatorName,relatorEndMultip,roleEndMultip);
	}
	
	public void setCreateOCLDerivation(Role role, String oclDerive)
	{
		code = Action.CREATE_OCL_DERIVATION;	
		this.oclDerive = oclDerive;
		this.role = role;
	}
	
	public void setCreateMediation(Relator relator, Role role, String relatorEndMultip,String roleEndMultip)
	{
		code = Action.CREATE_NEW_MEDIATION;
		this.relator=relator;
		this.role = role;
		this.relatorEndMultip=relatorEndMultip;
		this.roleEndMultip=roleEndMultip;
	}
	
	public void setCreateSubRelatorWithMediation(Relator relator, Role role, String subRelatorName, String relatorEndMultip,String roleEndMultip)
	{
		code = Action.CREATE_SUBRELATOR_WITH_MEDIATION;
		this.relator = relator;
		this.subRelatorName = subRelatorName;
		this.role = role;
		this.relatorEndMultip=relatorEndMultip;
		this.roleEndMultip=roleEndMultip;
	}
	
	public static String getStereotype(EObject element)
	{
		String type = element.getClass().toString().replaceAll("class RefOntoUML.impl.","");
	    type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);
	    if (!type.equalsIgnoreCase("association")) type = type.replace("Association","");
	    return type;
	}
		
	@Override
	public String toString()
	{
		String result = new String();
		
		if(code == Action.CREATE_OCL_DERIVATION) {
			result += "Create OCL Derivation rule for "+getStereotype(role)+" "+role.getName();
		}
		if(code == Action.CREATE_NEW_MEDIATION) {
			result += "Create new <<mediation>> from "+getStereotype(relator)+" "+relator.getName()+" to "+getStereotype(role)+" "+role.getName();
		}
		if(code == Action.CREATE_SUBRELATOR_WITH_MEDIATION) {
			result += "Create a Sub-"+getStereotype(relator)+" "+relator.getName()+"\n";
			result += "Create new <<mediation>> from the Sub-"+getStereotype(relator)+" "+relator.getName()+" to "+getStereotype(role)+" "+role.getName();
		}
		return result;
	}
}
		