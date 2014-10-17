package br.ufes.inf.nemo.ocl.editor;

import javax.swing.ImageIcon;
import javax.swing.JList;

import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.CompletionCellRenderer;
import org.fife.ui.autocomplete.FunctionCompletion;
import org.fife.ui.autocomplete.MarkupTagCompletion;
import org.fife.ui.autocomplete.TemplateCompletion;
import org.fife.ui.autocomplete.VariableCompletion;

/**
 * @author John Guerson
 */

public class OCLCellRenderer extends CompletionCellRenderer {

	private static final long serialVersionUID = 1L;
	
	@Override
	@SuppressWarnings("rawtypes")
	protected void prepareForTemplateCompletion(JList list, TemplateCompletion tc, int index, boolean selected, boolean hasFocus) 
	{
		super.prepareForTemplateCompletion(list, tc, index, selected, hasFocus);
		
		if (tc.getDefinitionString().equals("invariant")||
			tc.getDefinitionString().equals("derivation")||
			tc.getDefinitionString().equals("definition")||
			tc.getDefinitionString().equals("context")
		   ){
			setIcon(new ImageIcon(OCLCellRenderer.class.getResource("/br/ufes/inf/nemo/ocl/editor/icons/constraint.gif")));
		}else
		
		if (tc.getDefinitionString().equals("if-then-else")||
		    tc.getDefinitionString().equals("let-in"))
		   {
			setIcon(new ImageIcon(OCLCellRenderer.class.getResource("/br/ufes/inf/nemo/ocl/editor/icons/expression.gif")));
		}else
			
		if (tc.getDefinitionString().equals("=")||
			tc.getDefinitionString().equals("<>")||
			tc.getDefinitionString().equals("oclIsKindOf")||			
			tc.getDefinitionString().equals("oclIsNew")||
			tc.getDefinitionString().equals("oclIsTypeOf")||
			tc.getDefinitionString().equals("oclAsType")||
			tc.getDefinitionString().equals("oclIsUndefined")			
		   ){
			setIcon(new ImageIcon(OCLCellRenderer.class.getResource("/br/ufes/inf/nemo/ocl/editor/icons/operation.gif")));
		}else
		
		if (tc.getDefinitionString().equals("allInstances")){
			setIcon(new ImageIcon(OCLCellRenderer.class.getResource("/br/ufes/inf/nemo/ocl/editor/icons/allInstances.gif")));
		} else 
			
		if (tc.getDefinitionString().equals("forAll")||
			tc.getDefinitionString().equals("exists")||
			tc.getDefinitionString().equals("select")||
			tc.getDefinitionString().equals("reject")||
			tc.getDefinitionString().equals("collect")||
			tc.getDefinitionString().equals("isUnique")||
			tc.getDefinitionString().equals("closure")||
			tc.getDefinitionString().equals("one")||
			tc.getDefinitionString().equals("any")
		   ){
			setIcon(new ImageIcon(OCLCellRenderer.class.getResource("/br/ufes/inf/nemo/ocl/editor/icons/operation.gif")));
		}else
		
		if (tc.getDefinitionString().equals("size")||
			tc.getDefinitionString().equals("isEmpty")||
			tc.getDefinitionString().equals("notEmpty")||
			tc.getDefinitionString().equals("includes")||
			tc.getDefinitionString().equals("excludes")||
			tc.getDefinitionString().equals("includesAll")||
			tc.getDefinitionString().equals("excludesAll")||
			tc.getDefinitionString().equals("including")||
			tc.getDefinitionString().equals("excluding")||
			tc.getDefinitionString().equals("difference")||
			tc.getDefinitionString().equals("product")||
			tc.getDefinitionString().equals("sum")||
			tc.getDefinitionString().equals("symmetricDifference")||
			tc.getDefinitionString().equals("asSet")||
			tc.getDefinitionString().equals("union")||
			tc.getDefinitionString().equals("intersection")||
			tc.getDefinitionString().equals("count")||
			tc.getDefinitionString().equals("flatten")||
			tc.getDefinitionString().equals("-")
		   ){
			setIcon(new ImageIcon(OCLCellRenderer.class.getResource("/br/ufes/inf/nemo/ocl/editor/icons/operation.gif")));
		}else
		
		if (tc.getDefinitionString().equals("or")||
			tc.getDefinitionString().equals("and")||
			tc.getDefinitionString().equals("implies")||
			tc.getDefinitionString().equals("xor")||
			tc.getDefinitionString().equals("not")
		   ){
			
		}else
		
		if (tc.getDefinitionString().equals(">")||
			tc.getDefinitionString().equals("<=")||
			tc.getDefinitionString().equals(">=")||
			tc.getDefinitionString().equals("<")||
			tc.getDefinitionString().equals("+")||
			tc.getDefinitionString().equals("*")||
			tc.getDefinitionString().equals("max")||
			tc.getDefinitionString().equals("min")||
			tc.getDefinitionString().equals("abs")||
			tc.getDefinitionString().equals("negative")||
			tc.getDefinitionString().equals("subtraction")||
			tc.getDefinitionString().equals("floor")||
			tc.getDefinitionString().equals("round")
		   ){
			setIcon(new ImageIcon(OCLCellRenderer.class.getResource("/br/ufes/inf/nemo/ocl/editor/icons/operation.gif")));
		}else
		
		if (tc.getDefinitionString().contains("Property")){
			setIcon(new ImageIcon(OCLCellRenderer.class.getResource("/br/ufes/inf/nemo/ocl/editor/icons/property.gif")));
		}else
		
		if ((tc.getDefinitionString().contains("Kind"))||
		   (tc.getDefinitionString().contains("SubKind"))||
		   (tc.getDefinitionString().contains("Collective"))||
		   (tc.getDefinitionString().contains("Quantity"))||
		   (tc.getDefinitionString().contains("Role"))||
		   (tc.getDefinitionString().contains("Phase"))||
		   (tc.getDefinitionString().contains("Relator"))||
		   (tc.getDefinitionString().contains("Mode"))||
		   (tc.getDefinitionString().contains("Mixin"))||
		   (tc.getDefinitionString().contains("RoleMixin"))||
		   (tc.getDefinitionString().contains("Category"))||
		   (tc.getDefinitionString().contains("DataType")))
		{
			setIcon(new ImageIcon(OCLCellRenderer.class.getResource("/br/ufes/inf/nemo/ocl/editor/icons/class.png")));
		}
			
	}

	@Override
	@SuppressWarnings("rawtypes")
	protected void prepareForMarkupTagCompletion(JList list,MarkupTagCompletion mc, int index, boolean selected,boolean hasFocus) 
	{
		super.prepareForMarkupTagCompletion(list, mc, index, selected, hasFocus);		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("rawtypes")
	@Override
	protected void prepareForOtherCompletion(JList list,Completion c, int index, boolean selected, boolean hasFocus) 
	{
		super.prepareForOtherCompletion(list, c, index, selected, hasFocus);		
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("rawtypes")
	protected void prepareForVariableCompletion(JList list, VariableCompletion vc, int index, boolean selected, boolean hasFocus) 
	{
		super.prepareForVariableCompletion(list, vc, index, selected, hasFocus);		
	}


	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("rawtypes")
	protected void prepareForFunctionCompletion(JList list, FunctionCompletion fc, int index, boolean selected, boolean hasFocus) 
	{
		super.prepareForFunctionCompletion(list, fc, index, selected, hasFocus);		
	}


}