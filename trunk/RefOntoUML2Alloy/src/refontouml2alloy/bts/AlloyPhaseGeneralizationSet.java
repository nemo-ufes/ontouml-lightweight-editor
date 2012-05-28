package refontouml2alloy.bts;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import refontouml2alloy.bts.AlloyGeneralizationSet;
import refontouml2alloy.bts.classifiers.AlloyClassifier;
import refontouml2alloy.bts.classifiers.atoms.AlloyClass;
import RefOntoUML.Classifier;
import RefOntoUML.GeneralizationSet;

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
	public void writePredicate(FileWriter out) throws IOException
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
