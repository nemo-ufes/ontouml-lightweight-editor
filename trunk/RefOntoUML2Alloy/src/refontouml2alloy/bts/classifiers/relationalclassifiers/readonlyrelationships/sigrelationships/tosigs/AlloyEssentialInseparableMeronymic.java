package refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.sigrelationships.tosigs;

import java.io.FileWriter;
import java.io.IOException;

import refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.sigrelationships.AlloyEssentialMeronymic;
import refontouml2alloy.bts.structuralfeature.AlloyAtomProperty;
import RefOntoUML.Meronymic;

public class AlloyEssentialInseparableMeronymic extends AlloyEssentialMeronymic
{

	public AlloyEssentialInseparableMeronymic(Meronymic rel,
            AlloyAtomProperty t, AlloyAtomProperty s)
    {
	    super(rel, t, s);
    }
	
	@Override
	public void writeTemporalCoExistenceConstraint(FileWriter out)
    throws IOException
    {
		out.write("\tall s: State | this in s.exists iff "+this.getName()+" in s."+this.getHoldedClassifier().getStateName()+"\n");	
    }

}
