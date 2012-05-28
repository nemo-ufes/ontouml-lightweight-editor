package refontouml2alloy.bts.classifiers.relationalclassifiers;

import java.io.FileWriter;
import java.io.IOException;
import RefOntoUML.Generalization;

abstract public class AlloyGeneralization extends AlloyRelationship
{
	private String name;
	private static int unnamedGeneralizationRelCounter = 0;
	public AlloyGeneralization(Generalization rel)
    {
	    super(rel);
    }

	@Override
    public String getName()
    {
	    return name;
    }
	
	@Override
	public Generalization getRelationship()
	{
		return (Generalization) super.getRelationship();
	}

	@Override
    protected void setGenericName()
    {
		name = "Generalization"+unnamedGeneralizationRelCounter;
		unnamedGeneralizationRelCounter++;	
    }

	@Override
    public void writeDeclaration(FileWriter out) throws IOException
    {
	    //Generalizations are not declared anywhere in the alloy code, they are merely used to write state rules
    }
	
	public RefOntoUML.Class getSuperClass()
	{
		return (RefOntoUML.Class) this.getRelationship().getGeneral();
	}
	
	public RefOntoUML.Class getSubClass()
	{
		return (RefOntoUML.Class) this.getRelationship().getSpecific();
	}
	
}
