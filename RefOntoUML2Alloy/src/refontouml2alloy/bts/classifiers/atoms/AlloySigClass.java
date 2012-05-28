package refontouml2alloy.bts.classifiers.atoms;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import refontouml2alloy.bts.AlloyGeneralizationSet;
import refontouml2alloy.bts.classifiers.relationalclassifiers.AlloyDatatypeRelationship;
import refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.sigrelationships.AlloySigDatatypeRelationship;
import refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.sigrelationships.AlloySigRelationship;
import RefOntoUML.Class;


//The sig classes are the Rigid Sortal Classes and the Moment Classes
public abstract class AlloySigClass extends AlloyClass implements AlloySigClassifier, AlloySigClassifierParent
{
	protected Set<AlloyGeneralizationSet> rigidGSets = null;
	private HashSet<AlloySigRelationship> existentiallyDependentRelationships;
	//We implement AlloySigClassifier methods by delegation to the AlloySigClassifierImplementation object
	private final AlloySigClassifierImplementation delegatee;
	public AlloySigClass(Class x) 
	{
		super(x);
		setExistentiallyDependentRelationships(new HashSet<AlloySigRelationship>());
		delegatee = new AlloySigClassifierImplementation(this);
	}
	public void writeSigRules(FileWriter out) throws IOException 
	{
		//boolean openedBrackets = false;
		//In the signature facts we assure the mutiplicity of the relations are respected regarding numbers different from 0, 1 and *
		//boolean comentary=false;
		out.write("\t{\n");
		for(AlloySigRelationship r: getExistentiallyDependentRelationships())
		{
			r.writeSigRules(out);
			/*String x = r.getTargetSigCardinalityConstraints();
			if(x!=null)
			{
				if(openedBrackets == false)
				{
					
					openedBrackets = true;
				}	 
				if(comentary == false)
				{
					comentary=true;
					out.write("\t//Cardinality contraints\n");
				}
				out.write(x);				
			}*/
		}
		out.write("\t}\n");
	}
		
	public void addEDRelationship(AlloySigRelationship rigidRel) 
	{
		this.getExistentiallyDependentRelationships().add(rigidRel);
	}
	public HashSet<AlloySigRelationship> getEDRelationships() 
	{
		return getExistentiallyDependentRelationships();
	}
	abstract protected void printSigHeader(FileWriter out);
	public void printSig(FileWriter out)
	{
		
		try 
		{
			printSigHeader(out);
			if(getExistentiallyDependentRelationships().isEmpty() && this.getSigDatatypeRelationshipHashSet().isEmpty() )
			{
				out.write("{}\n");				
			}
			else
			{
				out.write("\n{\n");
				for(AlloySigRelationship r: getExistentiallyDependentRelationships())
				{
					r.writeDeclaration(out);				
				}
				//any readonly datatype relationships are printed here
				for(AlloyDatatypeRelationship r : this.getSigDatatypeRelationshipHashSet())
				{
					r.writeDeclaration(out);
				}
				//Closes the signature declaration and opens the sigfact declaration
				out.write("}\n");
			}
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	public void printStateRules(FileWriter out) throws IOException
	{
		//Below, we assure that in every valid state, a sig 'x' exists in the given state iff
		//it's existentially dependent relations exist in that given state.
		//In other words, we guarantee they exist simultaneously 
		for(AlloySigRelationship sigrel: getExistentiallyDependentRelationships())
		{
			sigrel.writeTemporalCoExistenceConstraint(out);
		}			
	
	}
	public String getAtomNames() 
	{
		return getOntoClass().getName();
	}
	public void addRigidGeneralizationSet(AlloyGeneralizationSet gs)
	{
		if(rigidGSets==null)rigidGSets = new HashSet<AlloyGeneralizationSet>();
		rigidGSets.add(gs);
	}
	//This name was such as "(Person:>exists)" but I've changed to "exists:>Person" to allow the use of @
	public String getStateName() 
	{
		return "exists:>"+this.getOntoClass().getName();
	}
	
	
	//we delegate the task to the mixin implementation
	public void addSigDatatypeRelationship(AlloySigDatatypeRelationship r)
	{
		getDelegatee().addSigDatatypeRelationship(r);
	}
	private AlloySigClassifier getDelegatee()
    {
	    return delegatee;
    }
	public void writeSigDatatypeRelationships(FileWriter out)
			throws IOException 
	{
		getDelegatee().writeSigDatatypeRelationships(out);
		
	}
	public boolean hasSigDatatypeRelationships() 
	{
		return getDelegatee().hasSigDatatypeRelationships();
	}
	public HashSet<AlloySigDatatypeRelationship> getSigDatatypeRelationshipHashSet()
	{
		return getDelegatee().getSigDatatypeRelationshipHashSet();
	}
	public void setExistentiallyDependentRelationships(
            HashSet<AlloySigRelationship> hashSet)
    {
	    this.existentiallyDependentRelationships = hashSet;
    }
	public HashSet<AlloySigRelationship> getExistentiallyDependentRelationships()
    {
	    return existentiallyDependentRelationships;
    }
}