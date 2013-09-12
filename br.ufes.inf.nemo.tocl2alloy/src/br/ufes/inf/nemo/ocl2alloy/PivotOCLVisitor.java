package br.ufes.inf.nemo.ocl2alloy;

import java.io.IOException;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.examples.pivot.Constraint;
import org.eclipse.ocl.examples.pivot.ExpressionInOCL;
import org.eclipse.ocl.examples.pivot.NamedElement;
import org.eclipse.ocl.examples.pivot.Namespace;
import org.eclipse.ocl.examples.pivot.OCLExpression;
import org.eclipse.ocl.examples.pivot.ParserException;
import org.eclipse.ocl.examples.pivot.UMLReflection;
import org.eclipse.ocl.examples.pivot.prettyprint.PrettyPrintOptions;
import org.eclipse.ocl.examples.pivot.util.AbstractExtendingVisitor;
import org.eclipse.ocl.examples.pivot.util.Visitable;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class PivotOCLVisitor extends AbstractExtendingVisitor<String,PrettyPrintOptions> {
		
	private PivotOCLParser oclparser;
	private OntoUMLParser refparser;
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
		this.refparser = new OntoUMLParser(oclparser.getRefModel());
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
	
	/** Constraint */
	@Override
	public String visitConstraint (Constraint constraint)
	{
		StringBuffer result = new StringBuffer();
		String stereotype = constraint.getStereotype();
		NamedElement context = constraint.getContext();
		constraint_count++;
		
		if (UMLReflection.INVARIANT.equals(stereotype)) 
		{	
			if(opt.ctype.equals(PrettyPrintAlloyOption.ConstraintType.CHECK)) result.append("assert ");		
			else if(opt.ctype.equals(PrettyPrintAlloyOption.ConstraintType.RUN)) result.append("pred ");		
			else if(opt.ctype.equals(PrettyPrintAlloyOption.ConstraintType.FACT)) result.append("fact ");
			
			// constraint name
			if (constraint.getName()==null || constraint.getName().isEmpty())
			{
				String ctname = "constraint"+constraint_count;
				result.append(ctname).append(" {\n");
				constraint.setName(ctname);
			} else {
				result.append(constraint.getName()).append(" {\n");
			}
			
			result.append("\tall w: World | ");
				
			System.out.println(context);
			/*RefOntoUML.PackageableElement ontoClassifier = (RefOntoUML.PackageableElement)oclparser.getKey(context);
			String nameClassifier = refparser.getAlias(ontoClassifier);
			 	
			if (expr.getBodyExpression().toString().contains("self")){
				if (ontoClassifier instanceof RefOntoUML.DataType) result.append("all self: "+nameClassifier+" | "); 
				else result.append("all self: w."+nameClassifier+" | ");
			}else {
				//...
			}*/
			
			// expression
			try {
				ExpressionInOCL expressionInOCL = oclparser.getOCLFacade().getSpecification(constraint);			
				if (expressionInOCL != null) {
					//result.append(visit(expressionInOCL));		
				}
			} 
			catch (ParserException e) {		
				e.printStackTrace();
			} 
		    
		    result.append("\n}\n\n");                 
		    
		    if(opt.ctype.equals(PrettyPrintAlloyOption.ConstraintType.CHECK)) result.append("check ");		
			else if(opt.ctype.equals(PrettyPrintAlloyOption.ConstraintType.RUN)) result.append("run ");		
						
		    result.append(constraint.getName()+" for "+opt.global_scope+" but "+opt.world_scope+" World, 7 Int\n\n");		    
		}
		
		if (UMLReflection.DERIVATION.equals(stereotype)) 
		{
			
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
	
	
	//===================================	
	public static void main (String[]args)
	 {
		 //String oclPath = "C:\\Users\\Guerson\\SVN\\OLED-SVN\\br.ufes.inf.nemo.tocl2alloy\\model\\project.ocl";				
		 //String refPath = "C:\\Users\\Guerson\\SVN\\OLED-SVN\\br.ufes.inf.nemo.tocl2alloy\\model\\project.refontouml";
				
		 String oclPath = "C:\\Users\\John\\SVNs\\SVN-OLED\\br.ufes.inf.nemo.tocl2alloy\\model\\project.ocl";
		 String refPath = "C:\\Users\\John\\SVNs\\SVN-OLED\\br.ufes.inf.nemo.tocl2alloy\\model\\project.refontouml";
		 
		 try {
			
			PivotOCLParser parser = new PivotOCLParser(oclPath,refPath);
			parser.parse();
			System.out.println("OCL parsed.");

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
