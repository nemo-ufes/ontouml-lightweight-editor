package refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.sigrelationships.tononrigidclass;

import java.io.FileWriter;
import java.io.IOException;

import refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.sigrelationships.AlloySigRelationshipImplementation;
import refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.sigrelationships.AlloySigRelationshipParent;

public class AlloySigRelationshipToNRCImplementation extends
        AlloySigRelationshipImplementation implements AlloySigRelationshipToNRC
{

	public AlloySigRelationshipToNRCImplementation(AlloySigRelationshipParent p)
    {
	    super(p);
    }
	
	@Override
    public void writeStateRules(FileWriter out) throws IOException
	{
		super.writeStateRules(out);
		this.writeSigRelationshipStateCardinalityConstraints(out);
		this.writeGeneralizationStateRules(out);
	}

	private void writeGeneralizationStateRules(FileWriter out) throws IOException
    {
		out.write("\tall x: exists."+this.getName()+" | x in "+getParent().getHoldedClassifier().getName());
    }

	private void writeSigRelationshipStateCardinalityConstraints(FileWriter out) throws IOException
    {
	    if(getParent().getHolderProperty().getLower() > 0)
	    {
	    	out.write("\tall x: "+getParent().getHoldedClassifier().getName()+" | #"+this.getName()+".x >="+getParent().getHolderProperty().getLower());
	    	if(getParent().getHolderProperty().getUpper() != -1)
	    	{
	    		out.write(" and #"+this.getName()+".x <="+getParent().getHolderProperty().getUpper());	    		
	    	}
	    	out.write("\n");
	    }
	    else
	    {
	    	if(getParent().getHolderProperty().getUpper() != -1)
	    	{
	    		out.write("\tall x: "+getParent().getHoldedClassifier().getName()+" | #"+this.getName()+".x <="+getParent().getHolderProperty().getUpper()+"\n");
	    	}
	    }
    }

}
