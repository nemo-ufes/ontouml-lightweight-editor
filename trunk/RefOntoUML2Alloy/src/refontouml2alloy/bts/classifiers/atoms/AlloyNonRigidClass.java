package refontouml2alloy.bts.classifiers.atoms;

import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import RefOntoUML.Class;

public abstract class AlloyNonRigidClass extends AlloyClass
{	
	protected String setName;
	protected String atomNames;
	public AlloyNonRigidClass(Class x, EList<EObject> list) 
	{
		super(x);
		this.atomNames = AlloyClass.extractAtomNames(x, list);
		System.out.println(atomNames);
		this.setSetName(list);		
	}
	
	public String getStateName() 
	{
		return getOntoClass().getName();
	}
	public void printClassDeclaration(FileWriter out) throws IOException
	{
		out.write("\t"+getOntoClass().getName()+": set "+this.getSetName()+",\n");		
	}
	
	public String getSetName()
    {
	    return setName;
    }

	public String getAtomNames() 
	{
		return this.atomNames;
	}
	
	abstract protected void setSetName(EList<EObject> list);
	
	/*private String extractSetName(Class x, EList<EObject> list)
    {	
		//We look for this classe's generalizations to find which classes it specializes
		String setName = null;
	    for(EObject o: list)
	    {
	    	if(o instanceof Generalization)
	    	{
	    		if(((Generalization) o).getSource().get(0) instanceof Classifier)
	    		{
		    		
	    			Classifier c = (Classifier) ((Generalization) o).getTarget().get(0);
	    			if(c == this.getOntoClass())
	    			{
	    				Class superclass = (Class) ((Generalization) o).getSource().get(0);
	    				String supername = superclass.getName();
	    				if(superclass instanceof RigidSortalClass || superclass instanceof Category)
	    				{
	    					supername = supername+":>exists";
	    				}
	    				if(setName == null)
	    				{
	    					setName = supername;
	    				}
	    				else
	    				{
	    					setName = setName + " & " + supername;
	    				}
	    			}
	    		}
	    	}
	    }
	    return setName;
    }*/
	
}