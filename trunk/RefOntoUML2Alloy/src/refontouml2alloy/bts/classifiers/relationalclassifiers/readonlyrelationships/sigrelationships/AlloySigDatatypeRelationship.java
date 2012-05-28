package refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.sigrelationships;

import java.io.FileWriter;
import java.io.IOException;

import refontouml2alloy.bts.classifiers.atoms.AlloyIsAnAtom;
import refontouml2alloy.bts.classifiers.atoms.AlloySigClassifier;
import refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.AlloyReadOnlyDatatypeRelationship;
import refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.sigrelationships.tosigs.AlloySigDatatypeRelationshipToSig;
import refontouml2alloy.bts.structuralfeature.AlloyAtomProperty;
import RefOntoUML.Association;


//TODO: Adicionar as restrições adicionais de cardinalidade (ambos os lados!!!)
//TODO: Caso ambos os lados sejam frozen, adicionar fact pra restrição
//TODO: criar subclasse invertida
abstract public class AlloySigDatatypeRelationship extends AlloyReadOnlyDatatypeRelationship implements AlloySigRelationship, AlloySigRelationshipParent
{
	public AlloySigDatatypeRelationship(Association rel,
			AlloyAtomProperty alloyProperty, AlloyAtomProperty alloyProperty2) 
	{
		super(rel, alloyProperty, alloyProperty2);
		
	}
	@Override
	public void writeDeclaration(FileWriter out) throws IOException 
	{
		getDelegatee().writeDeclaration(out);
	}
	@Override
	public AlloyAtomProperty getHolderProperty()
	{
		return this.getSource();		
	}
	@Override
    public AlloyAtomProperty getHoldedProperty()
    {
		return this.getTarget();
    }
	@Override
	public AlloySigClassifier getHolderSigClassifier()
	{
		return (AlloySigClassifier) this.getSource().getEndType() ;		
	}
	@Override
    public AlloyIsAnAtom getHoldedClassifier()
    {
		return (AlloySigClassifier) this.getTarget().getEndType() ;
    }
	@Override
    public AlloySigRelationshipImplementation setDelegatee()
	{
		return new AlloySigRelationshipImplementation(this);
	}
	@Override
    public AlloySigRelationshipImplementation getDelegatee()
	{
	    return (AlloySigRelationshipImplementation) super.getDelegatee();
	}
	@Override
	public void writeTemporalCoExistenceConstraint(FileWriter out)
	throws IOException
	{
		//theres no need for temporal co existence constraint for datatype relationships
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
	@Override
    public void writeStateRules(FileWriter out) throws IOException
    {
	    //normal rules are moved to the sigrules and fact declarations for sigrelationship    
    }
	
	//SigDatatypeRelationships are sigrelationships, some of the constraints are embedded in the holder signature
	@Override
    public void writeSigRules(FileWriter out) throws IOException
    {
		//in the sigrules we can only enforce one side of the cardinality constraint. The other side is enforced by a simple fact
	    this.getDelegatee().writeSigRules(out);
	    
    }
	
	static public AlloySigDatatypeRelationship factory(Association rel, AlloyAtomProperty t, AlloyAtomProperty s)
	{
		//source will be the sig class containing the relation
		return new AlloySigDatatypeRelationshipToSig(rel,t,s);	
	}
	
	
	
}