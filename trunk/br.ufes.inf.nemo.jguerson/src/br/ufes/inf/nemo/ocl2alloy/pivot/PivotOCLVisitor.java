package br.ufes.inf.nemo.ocl2alloy.pivot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.pivot.AnyType;
import org.eclipse.ocl.examples.pivot.AssociationClassCallExp;
import org.eclipse.ocl.examples.pivot.BooleanLiteralExp;
import org.eclipse.ocl.examples.pivot.CollectionItem;
import org.eclipse.ocl.examples.pivot.CollectionLiteralExp;
import org.eclipse.ocl.examples.pivot.CollectionRange;
import org.eclipse.ocl.examples.pivot.CollectionType;
import org.eclipse.ocl.examples.pivot.Constraint;
import org.eclipse.ocl.examples.pivot.ConstructorExp;
import org.eclipse.ocl.examples.pivot.ConstructorPart;
import org.eclipse.ocl.examples.pivot.Element;
import org.eclipse.ocl.examples.pivot.ElementExtension;
import org.eclipse.ocl.examples.pivot.EnumLiteralExp;
import org.eclipse.ocl.examples.pivot.EnumerationLiteral;
import org.eclipse.ocl.examples.pivot.ExpressionInOCL;
import org.eclipse.ocl.examples.pivot.IfExp;
import org.eclipse.ocl.examples.pivot.Import;
import org.eclipse.ocl.examples.pivot.IntegerLiteralExp;
import org.eclipse.ocl.examples.pivot.InvalidLiteralExp;
import org.eclipse.ocl.examples.pivot.InvalidType;
import org.eclipse.ocl.examples.pivot.IterateExp;
import org.eclipse.ocl.examples.pivot.Iteration;
import org.eclipse.ocl.examples.pivot.IteratorExp;
import org.eclipse.ocl.examples.pivot.LambdaType;
import org.eclipse.ocl.examples.pivot.LetExp;
import org.eclipse.ocl.examples.pivot.MessageExp;
import org.eclipse.ocl.examples.pivot.Metaclass;
import org.eclipse.ocl.examples.pivot.NamedElement;
import org.eclipse.ocl.examples.pivot.NullLiteralExp;
import org.eclipse.ocl.examples.pivot.OCLExpression;
import org.eclipse.ocl.examples.pivot.OpaqueExpression;
import org.eclipse.ocl.examples.pivot.Operation;
import org.eclipse.ocl.examples.pivot.OperationCallExp;
import org.eclipse.ocl.examples.pivot.Parameter;
import org.eclipse.ocl.examples.pivot.ParserException;
import org.eclipse.ocl.examples.pivot.Precedence;
import org.eclipse.ocl.examples.pivot.PrimitiveType;
import org.eclipse.ocl.examples.pivot.Property;
import org.eclipse.ocl.examples.pivot.PropertyCallExp;
import org.eclipse.ocl.examples.pivot.RealLiteralExp;
import org.eclipse.ocl.examples.pivot.Root;
import org.eclipse.ocl.examples.pivot.StateExp;
import org.eclipse.ocl.examples.pivot.StringLiteralExp;
import org.eclipse.ocl.examples.pivot.TemplateBinding;
import org.eclipse.ocl.examples.pivot.TemplateParameter;
import org.eclipse.ocl.examples.pivot.TemplateParameterSubstitution;
import org.eclipse.ocl.examples.pivot.TemplateSignature;
import org.eclipse.ocl.examples.pivot.TupleLiteralExp;
import org.eclipse.ocl.examples.pivot.TupleLiteralPart;
import org.eclipse.ocl.examples.pivot.TupleType;
import org.eclipse.ocl.examples.pivot.Type;
import org.eclipse.ocl.examples.pivot.TypeExp;
import org.eclipse.ocl.examples.pivot.UMLReflection;
import org.eclipse.ocl.examples.pivot.UnlimitedNaturalLiteralExp;
import org.eclipse.ocl.examples.pivot.UnspecifiedType;
import org.eclipse.ocl.examples.pivot.UnspecifiedValueExp;
import org.eclipse.ocl.examples.pivot.Variable;
import org.eclipse.ocl.examples.pivot.VariableExp;
import org.eclipse.ocl.examples.pivot.VoidType;
import org.eclipse.ocl.examples.pivot.util.AbstractExtendingVisitor;
import org.eclipse.ocl.examples.pivot.util.Visitable;
import org.eclipse.ocl.examples.pivot.utilities.PivotUtil;

public class PivotOCLVisitor extends AbstractExtendingVisitor<String, Object> {
		
	private PivotOCLParser oclparser;
	private PrettyPrintAlloyOption opt;
	private int constraint_count = 0;	
	
	protected StringBuilder result = new StringBuilder();
	
	/**
	 * Indicates where a required element in the AST was <code>null</code>, so
	 * that it is evident that something was missing. We don't
	 * want just <code>"null"</code> because that would look like the OclVoid
	 * literal.	
	 */
	protected static String NULL_PLACEHOLDER = "\"<null>\""; 
		
	//========================= Constructors 
	
	protected PivotOCLVisitor() { super(Object.class); /* Useless dummy object as context*/ } 
	
	protected PivotOCLVisitor(PivotOCLParser parser)
	{
		super(Object.class);
		oclparser = parser;	
		opt =  new PrettyPrintAlloyOption(PrettyPrintAlloyOption.ConstraintType.FACT,10,1);
	}	
	
	protected PivotOCLVisitor(PivotOCLParser parser, PrettyPrintAlloyOption option)
	{
		super(Object.class);
		oclparser = parser;	
		opt =  option;
	}
	
	//========================= toString()
	
	@Override
	public String toString() { return result.toString(); }

	//========================= PrettyPrint()
	
	public String prettyPrintAlloy (Constraint element) 
	{		 
		opt =  new PrettyPrintAlloyOption(PrettyPrintAlloyOption.ConstraintType.FACT,10,1);
		return prettyPrintAlloy(element,opt);
	}

	public String prettyPrintAlloy (Constraint element, PrettyPrintAlloyOption option) 
	{
		this.opt = option;
		this.result = new StringBuilder(); // empties result builder before printing this constraint
        try {
        	safeVisit(element);
            return this.toString();
        }
        catch (Exception e) {
        	e.printStackTrace();
        	return toString() + " ... " + e.getClass().getName() + " - " + e.getLocalizedMessage();
        }
	}
	
	public String prettyPrintAlloy (ArrayList<Constraint> constraints, ArrayList<PrettyPrintAlloyOption> options)
	{
		try {
			if (constraints.size() <= options.size())
			{
				int i=0;
				for(Constraint ct: constraints)
				{
					this.opt = options.get(i);
					safeVisit(ct);			        
					i++;
				}
			}
			return this.toString();
		} catch (Exception e) {
        	e.printStackTrace();
        	return toString() + " ... " + e.getClass().getName() + " - " + e.getLocalizedMessage();
        }		
	}

	//========================= append()
	
	protected void append(Number number) 
	{
		if (number != null) result.append(number.toString());
		else result.append(NULL_PLACEHOLDER);		
	}

	protected void append(String string) 
	{
		if (string != null) result.append(string);
		else result.append(NULL_PLACEHOLDER);		
	}
	
	protected void appendName(NamedElement object) 
	{
		if (object == null) result.append(NULL_PLACEHOLDER);
		else result.append(object.getName());		
	}	
					 	
	public void appendConstraintName (Constraint constraint)
	{
		if (constraint.getName()==null || constraint.getName().isEmpty())
		{			
			constraint.setName("constraint"+constraint_count);
			result.append("constraint"+constraint_count);			
		}else 
			result.append(constraint.getName());		
	}
	
	public void appendContext (Element context, String stereotype)
	{
		RefOntoUML.Element ontoContext = getOntoUMLOfPivot(context);
		String aliasContext = oclparser.getOntoUMLParser().getAlias(ontoContext);
		
		if (UMLReflection.INVARIANT.equals(stereotype)) 
		{						
			if (context instanceof Type)
			{
				result.append("all self: ");				
				if (ontoContext instanceof RefOntoUML.DataType) result.append(aliasContext+" | "); 
				else result.append("w."+aliasContext+" | ");
			}
		} 
		
		else if (UMLReflection.DERIVATION.equals(stereotype)) 
		{
			if (context instanceof Property)
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
		    	
		    	 // derive an assoc end or and attribute
		    	if (ontoProperty.getAssociation()!=null){ 
		    		src_type = ontoProperty.getAssociation().getMemberEnd().get(0).getType();
		    		tgt_type = ontoProperty.getAssociation().getMemberEnd().get(1).getType();
		    	}else {
		    		src_type = (RefOntoUML.Type)ontoProperty.getOwner();
		    		tgt_type = ontoProperty.getType();
		    	}
		    	String src_name = oclparser.getOntoUMLParser().getAlias(src_type);                	
		    	String tgt_name = oclparser.getOntoUMLParser().getAlias(tgt_type);
		    	                	                	                	
		   	    // this piece of code considers the direction...
		    	if(typename.equals(src_name)) result.append("w."+tgt_name+" | ");                	
		    	if(typename.equals(tgt_name)) result.append("w."+src_name+" | ");
				                	
		    	if(ontoProperty.getAssociation()!=null) result.append("self."+aliasContext+"[w] = ");
		    	else result.append("self.(w."+aliasContext+") = ");
			}
		}		
	}
	
	//========================= visit()
	
	@Override
	public String visitConstraint (Constraint constraint)
	{
		Element context = constraint.getConstrainedElement().get(0);
		OCLExpression oclExpression = ((ExpressionInOCL)constraint.getSpecification()).getBodyExpression();		
		String stereotype = PivotUtil.getStereotype(constraint); 				
		constraint_count++;
		
		if (UMLReflection.INVARIANT.equals(stereotype) || UMLReflection.DERIVATION.equals(stereotype))
		{
			if(opt.ctype.equals(PrettyPrintAlloyOption.ConstraintType.CHECK)) result.append("assert ");		
			else if(opt.ctype.equals(PrettyPrintAlloyOption.ConstraintType.RUN)) result.append("pred ");		
			else if(opt.ctype.equals(PrettyPrintAlloyOption.ConstraintType.FACT)) result.append("fact ");
			
			appendConstraintName(constraint);
			result.append(" {\n").append("\tall w: World | ");			
			
			if (oclExpression.toString().contains("self")) 
			{
				if (context instanceof Type) appendContext(context,stereotype);
				else if (context instanceof Operation) appendContext(context,stereotype);
				else if (context instanceof Property) appendContext(context,stereotype);
			}			
					
			result.append("\n\t");
			
			try 
			{
				ExpressionInOCL expressionInOCL = oclparser.getOCL().getSpecification(constraint);			
				if (expressionInOCL != null) safeVisit(expressionInOCL);				
			} 
			catch (ParserException e) { e.printStackTrace(); } 
		    
		    result.append("\n}\n\n");
		    
		    if(opt.ctype.equals(PrettyPrintAlloyOption.ConstraintType.CHECK)) result.append("check ");		
			else if(opt.ctype.equals(PrettyPrintAlloyOption.ConstraintType.RUN)) result.append("run ");
		    
		    if (!opt.ctype.equals(PrettyPrintAlloyOption.ConstraintType.FACT))
		    {
		    	appendConstraintName(constraint);
		    	result.append(" for "+opt.global_scope+" but "+opt.world_scope+" World, 7 Int\n\n");
		    }
		}

		if (UMLReflection.DEFINITION.equals(stereotype));		
		if (UMLReflection.INITIAL.equals(stereotype));		
		if (UMLReflection.BODY.equals(stereotype));
		if (UMLReflection.PRECONDITION.equals(stereotype)); 
		if (UMLReflection.POSTCONDITION.equals(stereotype)); 
		
		return null; 
	}	

	@Override
	public String visitExpressionInOCL(@NonNull ExpressionInOCL expression) 
	{
		OCLExpression bodyExpression = expression.getBodyExpression();
		if (bodyExpression != null)  return safeVisit(bodyExpression);
		else return NULL_PLACEHOLDER;
	}
		
   	@Override
	public String visitAnyType(@NonNull AnyType object) { return null; }
	@Override
	public String visitAssociationClassCallExp(@NonNull AssociationClassCallExp ac) { return null;}
	@Override
	public String visitBooleanLiteralExp(@NonNull BooleanLiteralExp bl) { return null; }
	@Override
	public String visitClass(@NonNull org.eclipse.ocl.examples.pivot.Class cls) { return null; }
	@Override
	public String visitCollectionItem(@NonNull CollectionItem item) 
	{
		safeVisit(item.getItem());  
		return null; 
	}
	@Override
	public String visitCollectionLiteralExp(@NonNull CollectionLiteralExp cl) { return null; }	
	@Override
	public String visitCollectionRange(@NonNull CollectionRange range) { return null; }
	@Override
	public String visitCollectionType(@NonNull CollectionType object) { return null; }
	@Override
	public String visitConstructorExp(@NonNull ConstructorExp constructorExp) { return null; }
	@Override
	public String visitConstructorPart(@NonNull ConstructorPart part) { return null; }
	@Override
	public String visitElementExtension(@NonNull ElementExtension as) { return null; }
	@Override
	public String visitEnumLiteralExp(@NonNull EnumLiteralExp el) { return null; }	
	@Override 
	public String visitIfExp(@NonNull IfExp ifExp) { return null; }
	@Override
	public String visitEnumerationLiteral(@NonNull EnumerationLiteral object) { return null; }	
	@Override 
	public @Nullable String visitImport(@NonNull Import object) { return null; }
	@Override
	public String visitIntegerLiteralExp(@NonNull IntegerLiteralExp il) { return null; }
	@Override
	public String visitInvalidLiteralExp(@NonNull InvalidLiteralExp il) { return null; }
	@Override
	public String visitInvalidType(@NonNull InvalidType object)  { return null; }
	@Override
	public String visitIterateExp(@NonNull IterateExp callExp)  { return null; }
	@Override
	public String visitIteration(@NonNull Iteration iteration)  { return null; }
	@Override
	public String visitIteratorExp(@NonNull IteratorExp callExp)  { return null; }
	@Override
	public String visitLambdaType(@NonNull LambdaType lambda)  { return null; }
	@Override
	public String visitLetExp(@NonNull LetExp letExp)  { return null; }
	@Override
	public String visitMessageExp(@NonNull MessageExp messageExp)  { return null; }
	@Override
	public String visitMetaclass(@NonNull Metaclass object)  { return null; }
	@Override
	public String visitNullLiteralExp(@NonNull NullLiteralExp il)  { return null; }
	@Override
	public String visitOpaqueExpression(@NonNull OpaqueExpression object)  { return null; }
	@Override
	public String visitOperation(@NonNull Operation operation)  { return null; }
	@Override
	public String visitOperationCallExp(@NonNull OperationCallExp operCallExp)  
	{ 
		Operation oper = operCallExp.getReferredOperation(); 
		String operTypeResult = oper.getType().getName();
		String operName = oper.getName();
		OCLExpression srcExpression = operCallExp.getSource();
		String srcResult = safeVisit(srcExpression);
		List<OCLExpression> arguments = operCallExp.getArgument();
		    	
		if(operName.equals("allInstances")) {append(srcResult); }		
		if(operName.equals("size")) {  append("("+"#" + srcResult+ ")"); }		
		if(operName.equals("isEmpty")) {  append("("+"no " + srcResult + ")"); }		
		if(operName.equals("notEmpty")) {  append("("+"some " + srcResult+ ")"); }		
		if(operName.equals("not")) {  append("("+"not " + srcResult+ ")"); }			
		if(operName.equals("oclIsUndefined")) { append("("+"#" + srcResult + " = 0"+ ")"); }		
		if(operName.equals("abs")) {  append("abs[" + srcResult + "]"); }		
        if(operName.equals("sum")) {  append("(sum " + srcResult + ")"); }        
        if(operName.equals("asSet")) {  append(srcResult); }        
		if(operName.equals("oclAsType")) {  append(srcResult); }
		
		if(operName.equals("-")) 
		{
			java.util.Iterator<OCLExpression> iter = arguments.iterator();			
			if(!iter.hasNext()) { append("negate[" + srcResult +"]"); }			
			else if( iter.hasNext() && operTypeResult.equals("Set(T)")) { append("("+srcResult + " - " + iter.next()+ ")"); }			
			else if( iter.hasNext() && operTypeResult.equals("Real")){ append("("+srcResult+").minus["+iter.next()+"]"); }
		}
		
        for (java.util.Iterator<OCLExpression> iter = arguments.iterator(); iter.hasNext();) 
        {
        	OCLExpression argumentExpression = iter.next();
        	String argument = safeVisit(argumentExpression);
        	
			if(operName.equals("intersection")) { append("("+srcResult + " & " + argument+")"); }				
			if(operName.equals("union")) { append("("+srcResult + " + " + argument+")"); }				
			if(operName.equals("including")) { append("("+srcResult + " + " + argument+")"); }			
			if(operName.equals("excluding")) { append("("+srcResult + " - " + argument+")"); }			
			if(operName.equals("includes")) { append("("+argument + " in " + srcResult+")"); }			
			if(operName.equals("excludes")) { append("("+argument + " !in " + srcResult+")"); }				
			if(operName.equals("includesAll")) { append("("+argument + " in " + srcResult+")"); }			
			if(operName.equals("excludesAll")) { append("("+"#"+argument + " & " + srcResult+" = 0)"); }			
			if(operName.equals("product")) { append("("+srcResult + " -> " + argument+")"); }			
			if(operName.equals("=")) { append("("+srcResult + " = " + argument+")"); }				
			if(operName.equals("<>")) { append("("+srcResult + " != " + argument+")"); }				
			if(operName.equals("oclIsKindOf"))	{ append("("+srcResult + " in " + argument+")"); }			
			if(operName.equals("oclAsType")) { append(""+srcResult+""); }			
			
			//if(operName.equals("oclIsTypeOf"))	{  result.append(generateOclIsTypeOfMapping(srcResult,argument)); }
							
			if(operName.equals("<")) { append("("+srcResult + " < " + argument + ")"); }				
			if(operName.equals(">")) { append("("+srcResult + " > " + argument + ")"); }				
			if(operName.equals("<=")) { append("("+srcResult + " <= " + argument + ")"); }			
			if(operName.equals(">=")) { append("("+srcResult + " >= " + argument + ")"); }			
			if(operName.equals("and")) { append("("+srcResult + " and " + argument+")"); }			
			if(operName.equals("or")) { append("("+srcResult + " or " + argument+")"); }				
			if(operName.equals("implies")) { append("("+srcResult + " implies " + argument+")"); }			
			if(operName.equals("xor")) { append("("+"("+srcResult +" or "+argument+ ")"+" and not "+"("+srcResult+" and "+ argument+")"+")"); }			
			if(operName.equals("max")) { append("max["+srcResult +","+ argument + "]"); }			
			if(operName.equals("min")) { append("min["+srcResult +"," + argument + "]"); }			
			if(operName.equals("+")) { append("(" + srcResult +").plus["+argument+"]"); }			
			if(operName.equals("*")) { append("(" + srcResult +").mul["+argument+"]"); }			
			if(operName.equals("symmetricDifference")) { append("("+"("+srcResult + " + " + argument+") - ("+srcResult + " & " + argument+")"+")"); }	        	
						
			if (iter.hasNext()) ; // no more arguments 
        }		
		return null; 
	}
	@Override
	public String visitPackage(@NonNull org.eclipse.ocl.examples.pivot.Package pkg)  { return null; }
	@Override
	public String visitParameter(@NonNull Parameter parameter)  { return null; }
	@Override
	public String visitPrecedence(@NonNull Precedence precedence)  { return null; }
	@Override
	public String visitPrimitiveType(@NonNull PrimitiveType object)  { return null; }
	@Override
	public String visitProperty(@NonNull Property property)  { return null; }
	@Override
	public String visitPropertyCallExp(@NonNull PropertyCallExp pc)  { return null; }
	@Override
	public String visitRealLiteralExp(@NonNull RealLiteralExp rl)  { return null; }
	@Override
	public String visitRoot(@NonNull Root root)  { return null; }
	@Override
	public String visitStateExp(@NonNull StateExp s)  { return null; }
	@Override
	public String visitStringLiteralExp(@NonNull StringLiteralExp sl)  { return null; }
	@Override
	public String visitTemplateBinding(@NonNull TemplateBinding object)  { return null; }
	@Override
	public String visitTemplateParameter(@NonNull TemplateParameter object)  { return null; }
	@Override
	public String visitTemplateParameterSubstitution(@NonNull TemplateParameterSubstitution object)  { return null; }
	@Override
	public String visitTemplateSignature(@NonNull TemplateSignature object)  { return null; }
	@Override
	public String visitTupleLiteralExp(@NonNull TupleLiteralExp literalExp)  { return null; }
	@Override
	public String visitTupleLiteralPart(@NonNull TupleLiteralPart part)  { return null; }
	@Override
	public String visitTupleType(@NonNull TupleType object)  { return null; }
	@Override
	public String visitTypeExp(@NonNull TypeExp t) { return null; }
	@Override
	public String visitUnlimitedNaturalLiteralExp(@NonNull UnlimitedNaturalLiteralExp unl) { return null; }
	@Override
	public String visitUnspecifiedType(@NonNull UnspecifiedType object) { return null; }
	@Override
	public String visitUnspecifiedValueExp(@NonNull UnspecifiedValueExp uv) { return null; }
	@Override
	public String visitVariable(@NonNull Variable variable) 
	{
		appendName(variable);
		Type type = variable.getType();
		if (type != null) {
			append(" : ");
//			appendElementType(variable);
		}
		OCLExpression initExpression = variable.getInitExpression();
		if (initExpression != null) {
			append(" = ");
			safeVisit(initExpression);
		}		
		return null; 
	}
	@Override
	public String visitVariableExp(@NonNull VariableExp v) { return null; }
	@Override
	public String visitVoidType(@NonNull VoidType object) { return null; }
	@Override
	public String visiting(@NonNull Visitable visitable) { return null; }
	
	/** 
	 * Typed Element.
	 * 
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
	
    /** Used for a Test */	
	public static void main (String[]args)
	 {		 				
		 String oclPath = "C:\\Users\\Guerson\\SVN\\OLED-SVN\\br.ufes.inf.nemo.jguerson\\model\\project.ocl";
		 String refPath = "C:\\Users\\Guerson\\SVN\\OLED-SVN\\br.ufes.inf.nemo.jguerson\\model\\project.refontouml";
		 
		 String tempPath = "C:\\Users\\Guerson\\SVN\\OLED-SVN\\br.ufes.inf.nemo.jguerson\\temp\\";
			
		 try {
			
			PivotOCLParser parser = new PivotOCLParser(refPath,tempPath);
			String oclContent = PivotOCL2AlloyUtil.readFile(oclPath);
			parser.parse(oclContent);
			
			System.out.println("OCL parsed succesfully.");

			PivotOCLVisitor visitor = new PivotOCLVisitor(parser);
			
			System.out.println(
				visitor.prettyPrintAlloy(
					parser.getConstraints(),
					PrettyPrintAlloyOption.createListOfOptions(parser.getConstraints().size())
				)
			);
						
			System.out.println("OCL visited succesfully.");
			
		 } catch (IOException e) {
			e.printStackTrace();
			
		 } catch (ParserException e) {
			e.printStackTrace();
		 }
		
		
	}

	}
