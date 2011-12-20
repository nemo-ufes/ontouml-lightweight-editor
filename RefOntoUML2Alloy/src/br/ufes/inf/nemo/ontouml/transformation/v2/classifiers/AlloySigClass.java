package br.ufes.inf.nemo.ontouml.transformation.v2.classifiers;

import java.io.IOException;
import java.io.Writer;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import RefOntoUML.Class;
import RefOntoUML.impl.DirectedBinaryAssociationImpl;
import br.ufes.inf.nemo.ontouml.transformation.v2.base.AlloyDatatypeRelationship;
import br.ufes.inf.nemo.ontouml.transformation.v2.base.AlloyDirectedBinaryRelationship;
import br.ufes.inf.nemo.ontouml.transformation.v2.base.AlloyGeneralizationSet;
import br.ufes.inf.nemo.ontouml.transformation.v2.base.AlloySigDatatypeRelationship;


//The sig classes are the Rigid Sortal Classes and the Moment Classes
public abstract class AlloySigClass extends AlloyClass implements AlloySigClassifier//, AlloySigClassifierOwnerInfo
{
	protected Set<AlloyGeneralizationSet> rigidGSets = null;
	private HashSet<AlloyDirectedBinaryRelationship> existentiallyDependentRelationships;
	//We implement AlloySigClassifier methods by delegation to the AlloySigClassifierImplementation object
	private final AlloySigClassifierImplementation asci;
	public AlloySigClass(Class x) 
	{
		super(x);
		setExistentiallyDependentRelationships(new HashSet<AlloyDirectedBinaryRelationship>());
		asci = new AlloySigClassifierImplementation();
	}
	public void printSigRules(Writer out) throws IOException 
	{
		if(getExistentiallyDependentRelationships().isEmpty())return;
		out.write("\t{\n");
		//In the signature facts we assure the mutiplicity of the relations are respected regarding numbers different from 0, 1 and *
		boolean comentary=false;
		
		for(AlloyDirectedBinaryRelationship r: getExistentiallyDependentRelationships())
		{
			String x = r.getTargetExtraCardinalityConstraints();
			if(x!=null)
			{
				if(comentary == false)
				{
					comentary=true;
					out.write("\t//Cardinality contraints\n");
				}
				out.write(x);				
			}
		}
		comentary = false;
		
		// We must also ensure the existentially dependent objects exist simultaneously
		//the dependent atom exists in a subset of the set of states that the dependee exists.
		//eg: If a Person, who is a Student, exists in a set of states S and his Enrollment exists in a set of States S', S' must be a subset of S.
		//Possibilities for sigclasses existential dependency relationships:
		// - Characterizations for modes
		// - Mediations for relators
		// - Part-whole relations (but we do not implement part whole relations as sig relationships cause it would be problematic to find out if the relation is in the part signature, whole signature or in the state signature, so we move all relations to the state signature)
		// TODO: Assure the classification is enforced. Here we assure the two atoms exist simuntaneously, but we do not assure they are the correct class.
		for(AlloyDirectedBinaryRelationship r: getExistentiallyDependentRelationships())
		{
			if(comentary == false)
			{
				comentary = true;
				out.write("\t//Existential dependency contraints\n");
			}
			
			out.write("\tState:>exists.this in State:>exists."+r.getName()+"\n");
		}
		
		out.write("\t}\n");
	}
		
	public void addEDRelationship(AlloyDirectedBinaryRelationship rigidRel) 
	{
		this.getExistentiallyDependentRelationships().add(rigidRel);
	}
	public HashSet<AlloyDirectedBinaryRelationship> getEDRelationships() 
	{
		return getExistentiallyDependentRelationships();
	}
	abstract protected void printSigHeader(Writer out);
	public void printSig(Writer out)
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
				for(AlloyDirectedBinaryRelationship r: getExistentiallyDependentRelationships())
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
	public void printStateRules(Writer out) 
	{
		try
		{
			//Below, we assure that in every valid state, a sig 'x' exists in the given state iff
			//it's existentially dependent relations exist in that given state.
			//In other words, we guarantee they exist simultaneously 
			Iterator<AlloyDirectedBinaryRelationship> it = getExistentiallyDependentRelationships().iterator();
			if(it.hasNext())
			{
				AlloyDirectedBinaryRelationship rel = it.next();
				out.write("\t all x:"+ontoClass.getName()+":>exists|");
				while(true)
				{
					AlloyClassifier targ = rel.getTarget().getEndType();
					//The existentiallyDependentRelationships are only defined with their sigClasses
					//the below statement guarantees they instantiate the right subclass
					//TODO: if(!datatype)
					out.write("x."+rel.getName()+ " in "+
							//the cast to AlloyIsAnAtom is valid since these existentiallyDependentRelationships are only between classes or datatypes
							//we don't allow this optimization for relationships involving other relationships
							((AlloyClass)targ).getStateName());
					
					if(it.hasNext())
					{
						rel = it.next();
						//FIXME: is this while necessary?
						while(!(rel.getRelationship() instanceof DirectedBinaryAssociationImpl))
						{
							rel = it.next();
						}
						if(rel.getRelationship() instanceof DirectedBinaryAssociationImpl) out.write(" and ");
						else break;
					}
					else break;
					}
				out.write("\n");
			}
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public String getAtomNames() 
	{
		return ontoClass.getName();
	}
	public void addRigidGeneralizationSet(AlloyGeneralizationSet gs)
	{
		if(rigidGSets==null)rigidGSets = new HashSet<AlloyGeneralizationSet>();
		rigidGSets.add(gs);
	}
	//This name was such as "(Person:>exists)" but I've changed to "exists:>Person" to allow the use of @
	public String getStateName() 
	{
		return "exists:>"+this.ontoClass.getName();
	}
	
	
	//we delegate the task to the mixin implementation
	public void addSigDatatypeRelationship(AlloySigDatatypeRelationship r)
	{
		asci.addSigDatatypeRelationship(r);
	}
	public void writeSigDatatypeRelationships(Writer out)
			throws IOException 
	{
		asci.writeSigDatatypeRelationships(out);
		
	}
	public boolean hasSigDatatypeRelationships() 
	{
		return asci.hasSigDatatypeRelationships();
	}
	public HashSet<AlloySigDatatypeRelationship> getSigDatatypeRelationshipHashSet()
	{
		return asci.getSigDatatypeRelationshipHashSet();
	}
	public void setExistentiallyDependentRelationships(
			HashSet<AlloyDirectedBinaryRelationship> existentiallyDependentRelationships) {
		this.existentiallyDependentRelationships = existentiallyDependentRelationships;
	}
	public HashSet<AlloyDirectedBinaryRelationship> getExistentiallyDependentRelationships() {
		return existentiallyDependentRelationships;
	}
}