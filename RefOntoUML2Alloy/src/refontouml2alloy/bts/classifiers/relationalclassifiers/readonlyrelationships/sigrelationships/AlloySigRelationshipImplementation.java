package refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.sigrelationships;
import java.io.FileWriter;
import java.io.IOException;

import refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.AlloyReadOnlyRelationshipImplementation;
import refontouml2alloy.bts.structuralfeature.AlloyAtomProperty;

public class AlloySigRelationshipImplementation extends AlloyReadOnlyRelationshipImplementation implements AlloySigRelationship 
{
	public AlloySigRelationshipImplementation(AlloySigRelationshipParent p)
	{
		super(p);
	}
	
	@Override
	protected AlloySigRelationshipParent getParent()
    {
	    return (AlloySigRelationshipParent) super.getParent();
    }
	
	//TODO: Datatype não é class, classifier n tem getAtomNames... Criar uma classe isABox pra class e datatype, talvez...
	@Override
	public void writeDeclaration(FileWriter out) throws IOException 
	{
		//edit history: alterei getHolderProperty para HoldedProperty pra ver se corrige o bug do datatype
		//pode ser que cause um outro bug qualquer
		out.write("\t"+getParent().getName()+" : " + getParent().getHoldedProperty().setMultiplicity() +
				getParent().getHoldedProperty().getEndType().getAtomNames()+",\n");			
	}
	//This method is used to write sigrules here, but in the superclass it is used to write state rules
	//In the superclass, this method was valid for both sigmeronymics and sigdatatypes, but here, it is no longer so. Thus, we delegate the implementation to the parent class.
	@Override
    public void writeTemporalCoExistenceConstraint(FileWriter out)
            throws IOException
    {
		getParent().writeTemporalCoExistenceConstraint(out);		
    }
	@Override
	public String getTargetSigCardinalityConstraints()
	{
		return getExtraSigCardinalityConstraints(this.getTarget());
	}
	@Override
	public String getSourceSigCardinalityConstraints()
	{
		return getExtraSigCardinalityConstraints(this.getSource());
	}
	
	private String getExtraSigCardinalityConstraints(AlloyAtomProperty p) 
	{
		String ret=null;
		int lower = p.getLower();
		int upper = p.getUpper();
		if(lower == upper && lower>1)
		{
			ret = "\t#"+this.getName()+"="+lower+"\n";
		}
		else 
		{
				if(lower > 1)
				{
					ret = "\t#"+this.getName()+">="+lower+"\n";
				}
				//-1 represents "many" (aka * )
				if(upper != -1 && upper!=1)
				{
					if(ret == null)
					{
						ret = "\t#"+this.getName()+"<="+upper+"\n";
					}
					else
					{
						ret = ret + "\t#"+this.getName()+"<="+upper+"\n";
					}
				}
		}
		return ret;
	}
	
	protected String getFactCardinalityConstraints(AlloyAtomProperty p) 
	{
		String ret=null;
		int lower = p.getLower();
		int upper = p.getUpper();
		System.out.println(this.getName()+" "+lower+" "+upper);
		if(lower == upper)
		{
			ret = "\tall x: "+ getParent().getHoldedClassifier().getAtomNames() + " | #"+this.getName()+".x = "+lower+"\n";
		}
		else 
		{
				if(lower > 0)
				{
					ret = "\tall x: "+ getParent().getHoldedClassifier().getAtomNames()+ " | #"+this.getName()+".x >="+lower;
				}
				//-1 represents "many" (aka * )
				if(upper != -1)
				{
					if(ret == null)
					{
						ret = "\tall x: "+ getParent().getHoldedClassifier().getAtomNames() + " | #"+this.getName()+".x <="+upper+"\n";
					}
					else
					{
						ret = ret + " and #"+this.getName()+".x <= "+upper+"\n";
					}
				}
		}
		return ret;
	}
	
	@Override
    public void writeSigRules(FileWriter out) throws IOException
    {
		String cardConstraints = this.getExtraSigCardinalityConstraints(this.getParent().getHolderProperty());
		if(cardConstraints != null)
		{
			out.write("\t//Extra cardinality constraints for the "+this.getName()+" relation\n\t"
					+ cardConstraints + "\n");
		}
		
    }
	@Override
	public void writeStateRules(FileWriter out) throws IOException
	{
		super.writeStateRules(out);
		writeSigRelAtomDynamicClass(out);
	}
	
	//Cardinality
	private void writeSigRelAtomDynamicClass(FileWriter out) throws IOException
    {
		//below, we guarantee the relationship atoms to belong to the right class 
		out.write("\t"+getParent().getHolderProperty().getEndType().getStateName()+ " = exists."+this.getName()+"\n");//here I was using exists:>Name but alloy interprets as exists:>(Name.prop)
	    
    }
	
	
}
