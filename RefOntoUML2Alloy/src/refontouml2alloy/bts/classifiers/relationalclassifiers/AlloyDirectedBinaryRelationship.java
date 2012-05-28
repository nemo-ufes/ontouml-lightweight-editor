package refontouml2alloy.bts.classifiers.relationalclassifiers;

import java.io.FileWriter;
import java.io.IOException;

import refontouml2alloy.bts.structuralfeature.AlloyProperty;
import refontouml2alloy.bts.classifiers.AlloyClassifier;
import RefOntoUML.DirectedBinaryAssociation;

public abstract class AlloyDirectedBinaryRelationship extends AlloyRelationship implements AlloyDirectedBinaryRelationshipInterface
{
	protected final AlloyProperty target;
	protected final AlloyProperty source;
	public AlloyDirectedBinaryRelationship(DirectedBinaryAssociation rel, AlloyProperty t, AlloyProperty s)
	{
		super(rel);
		target = t;
		source = s;
		if(rel.getName() == null) setGenericName();
	}
	public AlloyProperty getTarget() {
		return target;
	}
	public AlloyProperty getSource() {
		return source;
	}
	
	
	public boolean sourceIsReadOnly() 
	{
		return source.isReadOnly();
	}

	
	public boolean targetIsReadOnly() 
	{
		return target.isReadOnly();
	}
	
	public void writeDeclaration(FileWriter out) throws IOException 
	{
		
		out.write("\t"+this.getName()+" : set ");
		out.write(source.getEndType().getStateName());
		out.write(source.setMultiplicity()+"->"+target.setMultiplicity());
		out.write(target.getEndType().getStateName()+",\n");	
	}
	
	//prints extra cardinality constraints in the state signature
	private void printExtraCardinalityConstraints(FileWriter out) throws IOException
	{
		String t = ":>";
		String s = "exists";
		
		printOneSidedConstraint(out,source,target.getEndType(),this.getName()+".x"+t+s);
		printOneSidedConstraint(out,target,source.getEndType(),s+t+"x."+this.getName());
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
	@Override
	public DirectedBinaryAssociation getRelationship()
	{
		return (DirectedBinaryAssociation) super.getRelationship();
	}
	@Override
	public void writeStateRules(FileWriter out) throws IOException
	{
		printExtraCardinalityConstraints(out);
	}
}