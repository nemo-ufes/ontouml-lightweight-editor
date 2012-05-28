package refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.sigrelationships.tosigs;

import java.io.FileWriter;
import java.io.IOException;

import refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.sigrelationships.AlloySigRelationshipImplementation;
import refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.sigrelationships.AlloySigRelationshipParent;

public class AlloySigRelationshipToSigImplementation extends
        AlloySigRelationshipImplementation implements AlloySigRelationshipToSig
{

	public AlloySigRelationshipToSigImplementation(
            AlloySigRelationshipParent p)
    {
	    super(p);
    }
	
	@Override
    public void writeCardinalitiesFact(FileWriter out) throws IOException
    {
		String cardConstraints = this.getFactCardinalityConstraints(this.getParent().getHoldedProperty());
	    if(cardConstraints!=null)
	    {
			out.write("fact "+this.getName()+"_cardinality_constraints\n{\n\t"
					+ cardConstraints +"\n}\n");
			
	    }
	    
    }
}
