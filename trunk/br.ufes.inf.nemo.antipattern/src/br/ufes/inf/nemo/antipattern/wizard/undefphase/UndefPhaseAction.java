package br.ufes.inf.nemo.antipattern.wizard.undefphase;

import java.util.ArrayList;

import RefOntoUML.Classifier;
import br.ufes.inf.nemo.antipattern.undefphase.UndefPhaseOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntiPatternAction;

public class UndefPhaseAction extends AntiPatternAction< UndefPhaseOccurrence>{
	
	//attributes
	ArrayList<String> names = new ArrayList<String>();
	ArrayList<String> type = new ArrayList<String>();
	ArrayList<String> stereotype = new ArrayList<String>();
	ArrayList<String> cardinalities = new ArrayList<String>();
	
	//OCL
	String text = new String();
	
	//modes
	ArrayList<Classifier> phases = new ArrayList<Classifier>();
	
	public UndefPhaseAction( UndefPhaseOccurrence ap) {
		super(ap);
	}

	public enum Action { CREATE_ATTRIBUTES, CREATE_OCL_DERIVATION_RULES, CREATE_MODES, CHANGE_PHASES_STEREOTYPES }
	
	@Override
	public void run() {
		if(code == Action.CREATE_ATTRIBUTES) ap.createAttributes(names,type,stereotype,cardinalities);
		if(code == Action.CREATE_OCL_DERIVATION_RULES) ap.createOclDerivationRules(text);
		if(code == Action.CREATE_MODES) ap.createModes(names,cardinalities,phases);
		if(code == Action.CHANGE_PHASES_STEREOTYPES) ap.changeStereotypes(stereotype);
	}
	
	public void setCreateAttributes(ArrayList<String> names, ArrayList<String> type, ArrayList<String> stereotype, ArrayList<String> cardinalities)
	{
		code = Action.CREATE_ATTRIBUTES;
		this.names=names;
		this.type=type;
		this.stereotype=stereotype;
		this.cardinalities=cardinalities;
	}
	
	public void setCreateOclDerivationRules(String text)
	{
		code = Action.CREATE_OCL_DERIVATION_RULES;
		this.text=text;
	}
	
	public void setCreateModes(ArrayList<String> names, ArrayList<String> cardinalities, ArrayList<Classifier> phases)
	{
		code = Action.CREATE_MODES;
		this.names =names;
		this.cardinalities=cardinalities;
		this.phases=phases;
	}
	
	public void setChangeStereotypes(ArrayList<String> stereotypes)
	{
		code = Action.CHANGE_PHASES_STEREOTYPES;
		this.stereotype = stereotypes;
	}
	
	@Override
	public String toString(){
		String result = new String();				
		if(code == Action.CREATE_ATTRIBUTES)
		{
			int i=0;
			for(String str: names){
				result+="Create attribute "+str+": "+type.get(i)+" ("+stereotype.get(i)+") ["+cardinalities.get(i)+"]"+"\n";	
				i++;
			}			
		}
		if(code == Action.CREATE_OCL_DERIVATION_RULES)
		{
			result += "Create OCL derivations for each phase";
		}
		if(code == Action.CREATE_MODES)
		{
			int i=0;
			for(String str: names){
				result+="Phase "+phases.get(i).getName()+" characterized by "+cardinalities.get(i)+" <<mode>> "+str+"\n";	
				i++;
			}
			if(!phases.containsAll(ap.getPhases())){
				for(Classifier c: ap.getPhases()){
					if(!phases.contains(c)){
						result += "Generating OCL derivation by exclusion for <<phase>> "+c.getName()+"\n";		
					}
				}				
			}
		}
		if(code == Action.CHANGE_PHASES_STEREOTYPES)
		{
			int i=0;
			for(String str: stereotype){
				if(AttrTable.getStereotype(ap.getPhases().get(i)).compareToIgnoreCase(str)!=0){
					result+="Change <<phase>> "+ap.getPhases().get(i).getName()+" to <<"+str+">>\n";
				}
				i++;
			}
		}
		return result; 
	}
}