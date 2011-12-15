package br.inf.ufes.nemo.transformation.ontouml2alloy.v2.base;

import java.io.IOException;
import java.io.Writer;

import RefOntoUML.DirectedBinaryAssociation;
import br.inf.ufes.nemo.transformation.ontouml2alloy.v2.classifiers.AlloyClassifier;


public abstract class AlloyDirectedBinaryRelationship extends AlloyRelationship
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
	public String getTargetExtraCardinalityConstraints()
	{
		return getExtraCardinalityConstraints(target);
	}
	public String getSourceExtraCardinalityConstraints()
	{
		return getExtraCardinalityConstraints(source);
	}
	private String getExtraCardinalityConstraints(AlloyProperty p) 
	{
		String ret=null;
		int lower = p.getLower();
		int upper = p.getUpper();
		if(lower == upper && lower>1)
		{
			ret = "\t#"+this.getStateName()+"="+lower+"\n";
		}
		else 
		{
				if(lower > 1)
				{
					ret = "\t#"+this.getStateName()+">="+lower+"\n";
				}
				//-1 represents "many" (aka * )
				if(upper != -1 && upper!=1)
				{
					if(ret == null)
					{
						ret = "\t#"+this.getStateName()+"<="+upper+"\n";
					}
					else
					{
						ret = ret + "\t#"+this.getStateName()+"<="+upper+"\n";
					}
				}
		}
		return ret;
	}	
	
	public boolean sourceIsReadOnly() 
	{
		return source.isReadOnly();
	}

	
	public boolean targetIsReadOnly() 
	{
		return target.isReadOnly();
	}
	
	public void writeDeclaration(Writer out) throws IOException 
	{
		
		out.write("\t"+this.getName()+" : set ");
		out.write(source.getEndType().getStateName());
		out.write(source.setMultiplicity()+"->"+target.setMultiplicity());
		out.write(target.getEndType().getStateName()+",\n");
	
	}
	public void printExtraConstraints(Writer out) throws IOException
	{
		printOneSidedConstraint(out,source,target.getEndType(),this.getName()+".x");
		printOneSidedConstraint(out,target,source.getEndType(),"x."+this.getName());
	}
	public void printOneSidedConstraint(Writer out, AlloyProperty prop, AlloyClassifier constrainedClass, String setConstrained) throws IOException
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
					else out.write("#"+setConstrained+" > "+prop.getLower());
					//-1 represents "many" (aka * )
					//if the upper is -1, theres no need to add a upper constraint
					if(prop.getUpper() != -1)
					{
						out.write(" and #"+setConstrained+" < "+prop.getUpper());
					}
				}
				else
				{
					//if lower is 0, there's no need to add a lower constraint
					if(prop.getUpper() != -1)
					{
						out.write("#"+setConstrained+" < "+prop.getUpper());
					}
					
				}
				out.write("\n");
			}
		}
		
	}
}
