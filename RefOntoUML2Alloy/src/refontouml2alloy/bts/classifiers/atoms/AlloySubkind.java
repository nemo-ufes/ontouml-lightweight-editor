package refontouml2alloy.bts.classifiers.atoms;

import java.io.FileWriter;
import java.io.IOException;

import RefOntoUML.Class;
import RefOntoUML.Generalization;
import RefOntoUML.MomentClass;
import RefOntoUML.RigidSortalClass;
import RefOntoUML.SubKind;

public class AlloySubkind extends AlloySigClass
{
	private Class lowestIDPclass = null;
	public AlloySubkind(SubKind x) throws invalidSubKindException 
	{
		super(x);
		//finds the name of the IDPclass
		if(getOntoClass().getGeneral().size()>0)
		{
			for(Generalization g : getOntoClass().getGeneralization())
			{
				if(g.getGeneral() instanceof RigidSortalClass || g.getGeneral() instanceof MomentClass)
				{
					lowestIDPclass = (Class)g.getGeneral();					
					break;
				}
			}
			if(lowestIDPclass == null)throw new invalidSubKindException("subKind "+x.getName()+ " does not specialize an indentity provider class");
		}
		else
		{
			throw new invalidSubKindException("subKind "+x.getName()+ " without a generalization");
		}
		this.level = findSortalLevel(x);
	}
	protected void printSigHeader(FileWriter out)
	{
		try 
		{
			if(getOntoClass().isIsAbstract())out.write("abstract ");										
			out.write("sig "+getOntoClass().getName()+ " in "+ lowestIDPclass.getName());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public Class getLowestIdentityProviderClass()
	{
		return lowestIDPclass;
	}
	
}