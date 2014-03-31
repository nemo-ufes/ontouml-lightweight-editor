package br.ufes.inf.nemo.antipattern.wizard.decint;

import java.util.ArrayList;

import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import br.ufes.inf.nemo.antipattern.decint.DecIntOccurrence;
import br.ufes.inf.nemo.antipattern.decint.GeneralizationSetReplica;
import br.ufes.inf.nemo.antipattern.wizard.AntiPatternAction;

public class DecIntAction extends AntiPatternAction<DecIntOccurrence>{

	private Classifier identityProvider;
	private ArrayList<GeneralizationSetReplica> gsReplicas;
	
	
	public DecIntAction(DecIntOccurrence ap) 
	{
		super(ap);
	}

	public enum Action { FIX_IDENTITY_PROVIDER, FIX_GENERALIZATION_SETS, DERIVE_BY_INTERSECTION }
	
	@Override
	public void run()
	{
		if(code==Action.FIX_IDENTITY_PROVIDER)
		{
			ap.fixIdentityProvider(identityProvider);
		}
		
		else if(code==Action.FIX_GENERALIZATION_SETS)
		{
			ap.fixGeneralizationSets(gsReplicas);
		}
		else if(code==Action.DERIVE_BY_INTERSECTION)
		{
			ap.generateIntersectionDerivation();
		}
	}
	
	public void setFixGeneralizationSets(ArrayList<GeneralizationSetReplica> gsReplicas)
	{
		code=Action.FIX_GENERALIZATION_SETS;
		this.gsReplicas = gsReplicas;
		this.identityProvider = null;
	}
	
	public void setFixIdentityProvider(Classifier identityProvider)
	{
		code=Action.FIX_IDENTITY_PROVIDER;
		this.gsReplicas = null;
		this.identityProvider = identityProvider;
	}
	
	public void setDeriveByIntersection()
	{
		code=Action.DERIVE_BY_INTERSECTION;
	}
	
	@Override
	public String toString()
	{
		String result = new String();
		
		if(code==Action.DERIVE_BY_INTERSECTION)
		{
			result += "Create OCL Rule: derivation by intersection for type "+ap.getSubtype().getName();
		}
		
		else if(code==Action.FIX_IDENTITY_PROVIDER)
		{
			for (Generalization g : ap.getGeneralizationsToFixFromIdentityProvider(identityProvider)) {
				result += "Modify Generalization: Specific = "+g.getSpecific().getName()+"; " +
												"Old General = "+g.getGeneral().getName()+"; " +
												"New General = "+identityProvider.getName()+"\n";
			}
		}
		else if(code==Action.FIX_GENERALIZATION_SETS)
		{
			for (GeneralizationSet gs : ap.getDisjointGSList()) {
				boolean hasReplica = false;
				for (GeneralizationSetReplica replica : gsReplicas) {
					if(replica.getOriginal().equals(gs)){
						hasReplica = true;
						result += "Modify: Generalization Set "+replica+"\n";
					}
				}
				if(!hasReplica){
					result += "Remove: "+ap.getParser().getStringRepresentation(gs)+"\n";
				}
				
			}
		}
		return result;
	}
}
		