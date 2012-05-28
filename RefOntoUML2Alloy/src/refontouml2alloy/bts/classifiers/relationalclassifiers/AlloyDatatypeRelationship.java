package refontouml2alloy.bts.classifiers.relationalclassifiers;

import java.io.FileWriter;
import java.io.IOException;

import refontouml2alloy.bts.classifiers.AlloyClassifier;
import refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.AlloyReadOnlyDatatypeRelationship;
import refontouml2alloy.bts.structuralfeature.AlloyAtomProperty;
import refontouml2alloy.bts.structuralfeature.AlloyProperty;
import RefOntoUML.Association;

public class AlloyDatatypeRelationship extends AlloyRelationship
{
	private static int unnamedDTRelCounter = 0;
	
	protected final AlloyProperty target;
	protected final AlloyProperty source;

	public AlloyDatatypeRelationship(Association rel, AlloyProperty t, AlloyProperty s) 
	{
		super(rel);
		target = t;
		source = s;
		if (rel.getName() == null) setGenericName();
	}
	
	public AlloyProperty getTarget() {
		return target;
	}
	public AlloyProperty getSource() {
		return source;
	}
	
	@Override
	public String getName() 
	{
		return ((Association)relationship).getName();
	}

	@Override
	protected void setGenericName() 
	{
		((Association) this.relationship).setName("DatatypeRelationship"+unnamedDTRelCounter);
		unnamedDTRelCounter++;		
	}
	
	static public AlloyDatatypeRelationship factory(Association rel, AlloyAtomProperty t, AlloyAtomProperty s)
	{
		//source is not checked, since it is not navigable and thus cannot be readonly
		if(t.isReadOnly())
		{
			return AlloyReadOnlyDatatypeRelationship.factory(rel,t,s);
		}
		else return new AlloyDatatypeRelationship(rel,t,s);
	}

	public boolean sourceIsReadOnly() 
	{
		return source.isReadOnly();
	}

	
	public boolean targetIsReadOnly() 
	{
		return target.isReadOnly();
	}
	
	@Override
	public void writeDeclaration(FileWriter out) throws IOException {
		
		out.write("\t"+this.getName()+" : set ");
		out.write(source.getEndType().getStateName());
		out.write(source.setMultiplicity()+"->"+target.setMultiplicity());
		out.write(target.getEndType().getStateName()+",\n");	
	}

	@Override
	public void writeStateRules(FileWriter out) throws IOException {
		printExtraCardinalityConstraints(out);
	}
	
	//prints extra cardinality constraints in the state signature
	private void printExtraCardinalityConstraints(FileWriter out) throws IOException
	{
		//String t = ":>";
		//String s = "exists";
		
		//printOneSidedConstraint(out,source,target.getEndType(),this.getName()+".x"+t+s);
		//printOneSidedConstraint(out,target,source.getEndType(),s+t+"x."+this.getName());
		
		printOneSidedConstraint(out,source,target.getEndType(),this.getName()+".x");
		printOneSidedConstraint(out,target,source.getEndType(),"x."+this.getName());
	}
	
	private void printOneSidedConstraint(FileWriter out, AlloyProperty prop, AlloyClassifier constrainedClass, String setConstrained) throws IOException
	{
		// first we'll guarantee the cardinality are enforced correctly
		if(prop.getLower() == prop.getUpper())
		{
			out.write("\tall x: "+constrainedClass.getStateName()+" | ");
			if(prop.getLower() == 1)
			{
				out.write(" one "+setConstrained+"\n");
			}
			else
			{
				out.write("#"+setConstrained+"="+prop.getLower()+"\n");
			}
		}
		else 
		{
			//We don't need to add constraints if the cardinality is *
			if(prop.getLower() > 0 || prop.getUpper() !=-1)
			{
				out.write("\tall x: "+constrainedClass.getStateName()+"| ");
				if(prop.getLower() > 0)
				{
					//lower bound is different than 0, we need to add a lower contraint
					if(prop.getLower() == 1)out.write("some "+setConstrained);
					else out.write("#"+setConstrained+" >= "+prop.getLower());
					//-1 represents "many" (aka * )
					//if the upper is -1, theres no need to add a upper constraint
					if(prop.getUpper() != -1)
					{
						out.write(" and #"+setConstrained+" <= "+prop.getUpper());
					}
				}
				else
				{
					//if lower is 0, there's no need to add a lower constraint
					if(prop.getUpper() != -1)
					{
						out.write("#"+setConstrained+" <= "+prop.getUpper());
					}
					
				}
				out.write("\n");
			}
		}
		
	}
}