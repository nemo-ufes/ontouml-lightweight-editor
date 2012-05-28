package refontouml2alloy.bts.classifiers.atoms;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.MixinClass;
import RefOntoUML.MomentClass;
import RefOntoUML.RigidSortalClass;

public class AlloyNonRigidMixinClass extends AlloyNonRigidClass
{
	public AlloyNonRigidMixinClass(MixinClass x, EList<EObject> list) 
	{
		super(x, list);
		level = findMixinLevel(x,list);
	}

	public void printSubsumersUnion(FileWriter out) 
	{		
		try 
		{
			out.write("\t"+this.getOntoClass().getName()+" = "+this.getSetName());
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
		
	@Override
	protected void setSetName(EList<EObject> list)
    {
		HashSet<Generalization> l = new HashSet<Generalization>();
	    for(EObject e : list)
	    {
	    	if(e instanceof Generalization)
	    	{
	    		Generalization g = (Generalization) e;
	    		if(g.getGeneral() == this.getOntoClass())
	    		{
	    			l.add(g);
	    		}
	    	}
	    }
	    if(!l.isEmpty())
	    {
    	    setName = "(";
    	    Iterator<Generalization> i = l.iterator();
    	    
    	    for(Generalization g = i.next() ; ; g = i.next(), setName = setName+ " + " )
    	    {
    	    	
        		Classifier c =  (Classifier) g.getTarget().get(0);
        		setName = setName + c.getName();
        		if(c instanceof RigidSortalClass || c instanceof MomentClass)
        		{
        			setName = setName + ":>exists";
        		}        		
        		if(!i.hasNext())break;
    	    }
    	    setName = setName + ")";
    	    System.out.println(setName);
    	    
	    }
	    else
	    {
	    	setName = "none";
	    }
    }
}