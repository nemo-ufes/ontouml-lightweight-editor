package br.inf.ufes.nemo.transformation.ontouml2alloy.v2.classifiers;

import java.io.IOException;
import java.io.Writer;

import RefOntoUML.Class;
import RefOntoUML.Generalization;
import RefOntoUML.SubKind;
import RefOntoUML.impl.MomentClassImpl;
import RefOntoUML.impl.RigidSortalClassImpl;
import br.inf.ufes.nemo.transformation.ontouml2alloy.util.invalidSubKindException;

public class AlloySubkind extends AlloySigClass
{
	private Class lowestIDPclass = null;
	public AlloySubkind(SubKind x) throws invalidSubKindException 
	{
		super(x);
		//finds the name of the IDPclass
		if(ontoClass.getGeneral().size()>0)
		{
			for(Generalization g : ontoClass.getGeneralization())
			{
				if(g.getGeneral() instanceof RigidSortalClassImpl || g.getGeneral() instanceof MomentClassImpl)
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
	protected void printSigHeader(Writer out)
	{
		try 
		{
			if(ontoClass.isIsAbstract())out.write("abstract ");										
			out.write("sig "+ontoClass.getName()+ " in "+ lowestIDPclass.getName());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}