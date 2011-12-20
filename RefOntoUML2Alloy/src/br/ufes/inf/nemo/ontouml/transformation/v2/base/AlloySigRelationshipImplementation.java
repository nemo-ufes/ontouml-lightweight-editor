package br.ufes.inf.nemo.ontouml.transformation.v2.base;

import java.io.IOException;
import java.io.Writer;

import br.ufes.inf.nemo.ontouml.transformation.v2.classifiers.AlloySigClassifier;

public class AlloySigRelationshipImplementation extends AlloyStaticRelationshipImplementation implements AlloySigRelationship 
{
	public AlloySigRelationshipImplementation(AlloySigRelationshipParent p)
	{
		super(p);
	}
	@Override
	public AlloySigClassifier getHolderSigClassifier() 
	{
		if (((AlloySigRelationshipParent)parent).targetIsReadOnly())return (AlloySigClassifier) parent.getSource().getEndType() ;
		else return (AlloySigClassifier) parent.getTarget().getEndType();
	}

	@Override
	public void writeDeclaration(Writer out) throws IOException 
	{
		out.write("\t"+parent.getName()+" : " + ((AlloySigRelationshipParent)parent).getStaticProperty().setMultiplicity() +
				((AlloySigRelationshipParent)parent).getStaticProperty().getEndType().getName()+",\n");			
	}
	@Override
    public void writeTemporalCoExistenceConstraint(Writer out)
            throws IOException
    {
		((AlloySigRelationshipParent)parent).writeTemporalCoExistenceConstraint(out);
    }
	
	

}
