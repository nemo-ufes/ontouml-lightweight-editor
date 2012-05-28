package refontouml2alloy.bts;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import refontouml2alloy.bts.classifiers.AlloyClassifier;
import refontouml2alloy.bts.classifiers.relationalclassifiers.invalidGeneralizationSetException;

import org.eclipse.emf.common.util.EList;

import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;

//Considerar o email que enviei pra giancarlo/JP
//se houver a classe C, usar ela na defiinção de AlloyGeneralizationSet

public class AlloyGeneralizationSet
{
	private final Set<AlloyClassifier> subsumers;
	private final GeneralizationSet gset;
	//UML specifies that there must be a same general class for all members of the generalizationset.
	private final AlloyClassifier generalClassifier;
	public AlloyGeneralizationSet(GeneralizationSet gset, HashMap<Classifier,AlloyClassifier> cMapping)  throws invalidModelException
	{
		this.gset = gset;
		generalClassifier = defineGeneralClassifier(gset,cMapping);
		subsumers = new HashSet<AlloyClassifier>();
		for(Generalization g:gset.getGeneralization())
		{
			subsumers.add(cMapping.get(g.getSpecific()));
		}
	}
	public boolean isCovering() 
	{
		return gset.isIsCovering();
	}
	public boolean isDisjoint() 
	{
		return gset.isIsDisjoint();
	}
	//  this method defines which classifier is the ultimate in a generalization set
	//  if there is no ultimate classifier, that is, if two generalizations have disjoint superclasses
	// an invalidModelException is thrown
	private AlloyClassifier defineGeneralClassifier(GeneralizationSet gset,HashMap<Classifier,AlloyClassifier> cMapping) throws invalidGeneralizationSetException
	{
		EList<Generalization> gl = gset.getGeneralization();
		Classifier ultimate=null;
		Classifier nextClassifier;
		for(Generalization gen:gl)
		{
			//case for the first generalization in the list
			if(ultimate==null)ultimate = gen.getGeneral();
			else
			{
				nextClassifier = gen.getGeneral();
				if(ultimate.getGeneral().contains(nextClassifier))
				{
					//nextClassifier is superclass for ultimate, so nextClassifier is the new ultimate classe
					ultimate = nextClassifier;
				}
				else
				{
					//here we should check if ultime is in fact ultimate for nextClassifier
					if(!gen.getSpecific().getGeneral().contains(ultimate))
					{
						throw new invalidGeneralizationSetException(gset);
					}
				}
				
			}
		}
		return cMapping.get(ultimate);		
	}
	public Set<AlloyClassifier> getSubsumers()
	{
		return this.subsumers;
	}
	public AlloyClassifier getGeneral()
	{
		return generalClassifier;
	}
	protected GeneralizationSet getGSet()
    {
	    return gset;
    }
}