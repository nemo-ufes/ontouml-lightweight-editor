package refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.sigrelationships;

import java.io.FileWriter;
import java.io.IOException;

import refontouml2alloy.bts.classifiers.atoms.AlloyIsAnAtom;
import refontouml2alloy.bts.classifiers.atoms.AlloySigClassifier;
import refontouml2alloy.bts.classifiers.relationalclassifiers.AlloyDirectedBinaryRelationship;
import refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.sigrelationships.tononrigidclass.AlloyDependencyRelationshipToNRC;
import refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.sigrelationships.tosigs.AlloyDependencyRelationshipToSig;
import refontouml2alloy.bts.structuralfeature.AlloyAtomProperty;
import RefOntoUML.DependencyRelationship;
import RefOntoUML.RigidSortalClass;

abstract public class AlloyDependencyRelationship extends AlloyDirectedBinaryRelationship implements AlloySigRelationshipParent
{
	private static int unnamedDepRelCounter = 0;
	final AlloySigRelationship delegatee;
	public AlloyDependencyRelationship(DependencyRelationship rel, AlloyAtomProperty t, AlloyAtomProperty s) 
	{
		super(rel,t,s);
		if (rel.getName() == null) setGenericName();
		delegatee = new AlloySigRelationshipImplementation(this);
	}

	protected void setGenericName() 
	{
		((DependencyRelationship) this.relationship).setName("dependencyRelation"+unnamedDepRelCounter);
		unnamedDepRelCounter++;		
	}
	public String getName() 
	{
		return this.getRelationship().getName();
	}
	@Override
	public void writeDeclaration(FileWriter out) throws IOException 
	{
    	//writes the name of the relation.
    	out.write("\t"+this.getName()+":");
    	//we need now to write the mutiplicity of the relation.
    	out.write(" "+this.getTarget().setMultiplicity()+" "+
    			//the cast to AlloyIsAnAtom is valid since these existentiallyDependentRelationships are only between classes or datatypes
    			//we don't allow this optimization for relationships involving other relationships
    			((AlloyIsAnAtom)this.getTarget().getEndType()).getAtomNames() + ",\n");
    	//If it's not an Alloy Primitive, we'll need to write some State facts (using the # operator),
    	//to assure that other side of the multiplicity is treated correctly.
	}

	@Override
    public AlloySigClassifier getHolderSigClassifier()
    {
	    return (AlloySigClassifier) this.getSource().getEndType();
    }
	
	@Override
    public AlloyIsAnAtom getHoldedClassifier()
    {
	    return this.getTarget().getEndType();
    }
	
	@Override
    public void writeTemporalCoExistenceConstraint(FileWriter out)
            throws IOException
    {
		//The below statement guarantees that any Relator that exists has existent mediated atoms.
		out.write("\tall x : "+this.getHolderSigClassifier().getAtomNames()+":>exists | x."+this.getName()+" in "+this.getHolderProperty().getEndType().getStateName()+"\n");
		
    }
	@Override
	public AlloyAtomProperty getSource()
	{
		return (AlloyAtomProperty) super.getSource();
	}
	@Override
	public AlloyAtomProperty getTarget()
	{
		return (AlloyAtomProperty) super.getTarget();
	}

	@Override
    public String getSourceSigCardinalityConstraints()
    {
	    return getDelegatee().getSourceSigCardinalityConstraints();
    }

	@Override
    public String getTargetSigCardinalityConstraints()
    {
	    return getDelegatee().getTargetSigCardinalityConstraints();
    }

	public AlloySigRelationshipImplementation getDelegatee()
    {
	    return (AlloySigRelationshipImplementation) delegatee;
    }
	
	public AlloySigRelationshipImplementation setDelegatee()
	{
		return new AlloySigRelationshipImplementation(this);
	}

	@Override
    public AlloyAtomProperty getHolderProperty()
    {
		return this.getTarget();
    }
	@Override
    public AlloyAtomProperty getHoldedProperty()
    {
		return this.getSource();
    }

	
	//Dependency Relationships are sigrelationships, some of the constraints are embedded in the holder signature
	@Override
    public void writeStateRules(FileWriter out) throws IOException
    {
		super.writeStateRules(out); //TODO: revise : this is confusing. What are temporal co existence constraints? Do they include the cardinality constraints? 
	    this.writeTemporalCoExistenceConstraint(out);	    
    }
	@Override
    public void writeSigRules(FileWriter out) throws IOException
    {
		this.getDelegatee().writeSigRules(out);	    
    }

	public static AlloyDependencyRelationship factory(DependencyRelationship object,
            AlloyAtomProperty t,
            AlloyAtomProperty s)
    {
	    if(t.getEndType() instanceof RigidSortalClass)
	    {
	    	return new AlloyDependencyRelationshipToSig(object, t, s);
	    }
	    else return new AlloyDependencyRelationshipToNRC(object, t, s);
    }

		

	
}