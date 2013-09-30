package br.ufes.inf.nemo.ocl2alloy.pivot;

import java.io.IOException;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.ocl.examples.pivot.Constraint;
import org.eclipse.ocl.examples.pivot.Element;
import org.eclipse.ocl.examples.pivot.ExpressionInOCL;
import org.eclipse.ocl.examples.pivot.Namespace;
import org.eclipse.ocl.examples.pivot.ParserException;
import org.eclipse.ocl.examples.pivot.UMLReflection;
import org.eclipse.ocl.examples.pivot.prettyprint.PrettyPrintOptions;
import org.eclipse.ocl.examples.pivot.util.AbstractExtendingVisitor;
import org.eclipse.ocl.examples.pivot.util.Visitable;
import org.eclipse.ocl.examples.pivot.utilities.PivotUtil;

public class PivotOCLVisitor extends AbstractExtendingVisitor<String,PrettyPrintOptions> {
		
	private PivotOCLParser oclparser;
	private PrettyPrintAlloyOption opt;
	private int constraint_count = 0;	
	
	protected PivotOCLVisitor(PrettyPrintOptions options) 
	{
		super(options);
    }	
	
	protected PivotOCLVisitor(PivotOCLParser parser)
	{
		this(createOptions(null));
		this.oclparser = parser;		
	}
	
	private static PrettyPrintOptions.Global createOptions(Namespace scope) 
	{
		PrettyPrintOptions.Global options = new PrettyPrintOptions.Global(scope);         
        return options;
	}
	
	@Override
	public String visiting(Visitable arg0) 
	{
		return (arg0 == null)? null : (String) arg0.accept(this);
	}
	
	public String prettyPrintAlloy (Constraint element)
	{		 
		opt =  new PrettyPrintAlloyOption(PrettyPrintAlloyOption.ConstraintType.FACT,10,1);
		return prettyPrintAlloy(element,opt);
	}
	
	public String prettyPrintAlloy (Constraint element, PrettyPrintAlloyOption option) 
	{
		this.opt = option;
        try {
        	safeVisit(element);
            return visitConstraint((Constraint)element);
        }
        catch (Exception e) {
        	e.printStackTrace();
        	return toString() + " ... " + e.getClass().getName() + " - " + e.getLocalizedMessage();
        }
	}

	/** 
	 * This method returns the OntoUML element of the Pivot.
	 * The Match occurs through the attribute "name" due to the follow reasons:
	 * 
	 * 1. In Juno, we don't have getEcoreofPivot(). Also getETarget() doesn't work appropriately.
	 * 2. Even in Kepler (which uses getEcoreOfPivot()) the match can not be done since the ecore 
	 *    element returned isn't equal to the one in the OntoUML2Ecore Map.
	 *    
	 * Therewith, it's not possible to have two model elements with the same name in the model.
	 * This is obvious a current limitation.
	 */
	public RefOntoUML.Element getOntoUMLOfPivot (org.eclipse.ocl.examples.pivot.Element pivotElem)
	{
		// ecore model element of pivot
		EModelElement eElem = oclparser.getMetamodelManager().getEcoreOfPivot(EModelElement.class, pivotElem);

		for (Entry<RefOntoUML.Element,EModelElement> entry : oclparser.getOntoUML2EcoreMap().entrySet()) 
	    {
			// if the the ecore elements are equal..  
            if (eElem.equals(entry.getValue())) return entry.getKey();                
            else { 
            	// match through "name" attribute 
        		if (eElem instanceof ENamedElement)
        		{
        			String ecoreName = ((ENamedElement)eElem).getName().trim().toLowerCase();
        			String entryName = ((ENamedElement)entry.getValue()).getName().trim().toLowerCase();
        			if (ecoreName.equals(entryName)) return entry.getKey();
        		}
            }
	    }
		return null;
	} 	
	 	
	/** Constraint */
	@Override
	public String visitConstraint (Constraint constraint)
	{
		StringBuffer result = new StringBuffer();		
		
		Element context = constraint.getConstrainedElement().get(0);
		RefOntoUML.Element ontoContext = getOntoUMLOfPivot(context);
		String aliasContext = oclparser.getOntoUMLParser().getAlias(ontoContext);

		String stereotype = PivotUtil.getStereotype(constraint); // use this in Kepler
//		String stereotype = constraint.getStereotype(); // use this in Juno
				
		constraint_count++;
		
		if (UMLReflection.INVARIANT.equals(stereotype) || UMLReflection.DERIVATION.equals(stereotype))
		{
			//option
			if(opt.ctype.equals(PrettyPrintAlloyOption.ConstraintType.CHECK)) result.append("assert ");		
			else if(opt.ctype.equals(PrettyPrintAlloyOption.ConstraintType.RUN)) result.append("pred ");		
			else if(opt.ctype.equals(PrettyPrintAlloyOption.ConstraintType.FACT)) result.append("fact ");
			
			//constraint name
			if (constraint.getName()==null || constraint.getName().isEmpty())
			{				 
				result.append("constraint"+constraint_count).append(" {\n");
				constraint.setName("constraint"+constraint_count);
				
			} else result.append(constraint.getName()).append(" {\n");			
			
			// for every world...
			result.append("\tall w: World | ");

			// for every instance self
			if (((ExpressionInOCL)constraint.getSpecification()).getBodyExpression().toString().contains("self"))
			{				
				result.append("all self: ");
			}		

			// context for invariants
			if (UMLReflection.INVARIANT.equals(stereotype)) 
			{			
				if (((ExpressionInOCL)constraint.getSpecification()).getBodyExpression().toString().contains("self"))
				{				
					if (ontoContext instanceof RefOntoUML.DataType) result.append(aliasContext+" | "); 
					else result.append("w."+aliasContext+" | ");				
			    }
			}
			
			// context for derivations
			if (UMLReflection.DERIVATION.equals(stereotype)) 
			{	
				if (((ExpressionInOCL)constraint.getSpecification()).getBodyExpression().toString().contains("self"))
				{
					RefOntoUML.Type ontoContextType = ((RefOntoUML.Property)ontoContext).getType();
					String aliasType = oclparser.getOntoUMLParser().getAlias(ontoContextType);
					
					// the aliasType is a string in the form "Set(TypeName)"
			    	// we only need the "Typename"					
			    	String typename = new String();
			    	if(aliasType.contains("Set")) typename = aliasType.substring(4,aliasType.length()-1);
			    	else typename = aliasType;					
				
					RefOntoUML.Property ontoProperty = (RefOntoUML.Property)ontoContext;			
			    	RefOntoUML.Type src_type;
			    	RefOntoUML.Type tgt_type;
			    	
			    	if (ontoProperty.getAssociation()!=null){ 
			    		// derive an assoc end...
			    		src_type = ontoProperty.getAssociation().getMemberEnd().get(0).getType();
			    		tgt_type = ontoProperty.getAssociation().getMemberEnd().get(1).getType();
			    	}else { 
			    		// derive an attribute...
			    		src_type = (RefOntoUML.Type)ontoProperty.getOwner();
			    		tgt_type = ontoProperty.getType();
			    	}
			    	String src_name = oclparser.getOntoUMLParser().getAlias(src_type);                	
			    	String tgt_name = oclparser.getOntoUMLParser().getAlias(tgt_type);
			    	                	                	                	
			   	    // this piece of code considers the direction...
			    	if(typename.equals(src_name)) result.append("w."+tgt_name+" | ");                	
			    	if(typename.equals(tgt_name)) result.append("w."+src_name+" | ");
					                	
			    	if(ontoProperty.getAssociation()!=null){
			    		result.append("self."+aliasContext+"[w] = ");
			    	}else{
			    		result.append("self.(w."+aliasContext+") = ");
			    	}
				}
			}		

			// visit OCLExpression
			try {
				ExpressionInOCL expressionInOCL = oclparser.getOCL().getSpecification(constraint);			
				if (expressionInOCL != null) visit(expressionInOCL);
				
			} catch (ParserException e) {		
				e.printStackTrace();
			} 
		    
		    result.append("\n}\n\n");      
		    
		    // commands
		    if(opt.ctype.equals(PrettyPrintAlloyOption.ConstraintType.CHECK)) result.append("check ");		
			else if(opt.ctype.equals(PrettyPrintAlloyOption.ConstraintType.RUN)) result.append("run ");						
		    if (!opt.ctype.equals(PrettyPrintAlloyOption.ConstraintType.FACT))
		    {
		    	result.append(constraint.getName()+" for "+opt.global_scope+" but "+opt.world_scope+" World, 7 Int\n\n");
		    }
		}

		if (UMLReflection.DEFINITION.equals(stereotype)) 
		{
			
		}
		if (UMLReflection.INITIAL.equals(stereotype)) 
		{
			
		}
		if (UMLReflection.BODY.equals(stereotype)) 
		{
			
		}
		if (UMLReflection.PRECONDITION.equals(stereotype)) 
		{
			
		}
		if (UMLReflection.POSTCONDITION.equals(stereotype)) 
		{
			
		}
		return result.toString(); 
	}	
	
	/** ExpressionInOCL. */	
    @Override
    public String visitExpressionInOCL (ExpressionInOCL expression) 
    {
		return expression.getBodyExpression().accept(this);
	}
	

    /** Used for a Test */	
	public static void main (String[]args)
	 {		 				
		 String oclPath = "C:\\Users\\John\\SVNs\\SVN-OLED\\br.ufes.inf.nemo.jguerson\\model\\project.ocl";
		 String refPath = "C:\\Users\\John\\SVNs\\SVN-OLED\\br.ufes.inf.nemo.jguerson\\model\\project.refontouml";
		 
		 String tempPath = "C:\\Users\\John\\SVNs\\SVN-OLED\\br.ufes.inf.nemo.jguerson\\temp\\";
			
		 try {
			
			PivotOCLParser parser = new PivotOCLParser(refPath,tempPath);
			String oclContent = PivotOCL2AlloyUtil.readFile(oclPath);
			parser.parse(oclContent);
			
			System.out.println("OCL parsed succesfully.");

			PivotOCLVisitor visitor = new PivotOCLVisitor(parser);
			
			for(Constraint ct: parser.getConstraints())
			{
				System.out.println(visitor.prettyPrintAlloy(ct));
			}
			
		 } catch (IOException e) {
			e.printStackTrace();
			
		 } catch (ParserException e) {
			e.printStackTrace();
		 }
		
		
	}

	
}
