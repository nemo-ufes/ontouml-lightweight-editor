package br.ufes.inf.nemo.ontouml.transformation.v2.base;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;

import RefOntoUML.Classifier;
import RefOntoUML.GeneralizationSet;
import br.inf.ufes.nemo.transformation.ontouml2alloy.v2.classifiers.AlloyClass;
import br.inf.ufes.nemo.transformation.ontouml2alloy.v2.classifiers.AlloyClassifier;
import br.inf.ufes.nemo.transformation.ontouml2alloy.v2.util.invalidModelException;

public class AlloyPhaseGeneralizationSet extends AlloyGeneralizationSet
{
	public AlloyPhaseGeneralizationSet(GeneralizationSet gset,
          HashMap<Classifier, AlloyClassifier> mapping)
          throws invalidModelException
  {
	    super(gset, mapping);
	    if(getGSet().getName()==null)this.setGenericName();
  }
	static int unnamedGeneralizationSet = 0;
	public AlloyClass getGeneral()
	{
		return getGeneral();
	}
	//this predicate attempts to select an indivudual an instance of the model in which such individual cicles through the phases
	public void writePredicate(Writer out) throws IOException
	{
		out.write("pred "+this.getGSet().getName()+"\n{\n\tone x: "+this.getGeneral().getAtomNames()+" | ");
		Iterator<AlloyClassifier> i;
		AlloyClassifier ph;
		for(i = getSubsumers().iterator(), ph=i.next();i.hasNext();ph=i.next())
		{
			out.write("x in State."+ph.getName());
			if(i.hasNext())out.write(" and ");
		}
	}
	protected void setGenericName() 
	{
		getGSet().setName("Gset"+unnamedGeneralizationSet);
		unnamedGeneralizationSet++;		
	}
	
}
