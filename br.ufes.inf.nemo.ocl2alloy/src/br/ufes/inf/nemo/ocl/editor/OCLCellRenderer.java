package br.ufes.inf.nemo.ocl.editor;

import javax.swing.ImageIcon;
import javax.swing.JList;

import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.CompletionCellRenderer;
import org.fife.ui.autocomplete.FunctionCompletion;
import org.fife.ui.autocomplete.MarkupTagCompletion;
import org.fife.ui.autocomplete.TemplateCompletion;
import org.fife.ui.autocomplete.VariableCompletion;

public class OCLCellRenderer extends CompletionCellRenderer {

	private static final long serialVersionUID = 1L;
	
	@Override
	@SuppressWarnings("rawtypes")
	protected void prepareForTemplateCompletion(JList list, TemplateCompletion tc, int index, boolean selected, boolean hasFocus) 
	{
		super.prepareForTemplateCompletion(list, tc, index, selected, hasFocus);
		
		if (tc.getDefinitionString().equals("package")||
			tc.getDefinitionString().equals("invariant")||
			tc.getDefinitionString().equals("derivation")||
			tc.getDefinitionString().equals("if-then-else")||
			tc.getDefinitionString().equals("let-in")
		   ){
			setIcon(new ImageIcon(OCLCellRenderer.class.getResource("/resources/br/ufes/inf/nemo/ocl/editor/ocl_constraint.gif")));
		}
		
		if (tc.getDefinitionString().equals("=")||
			tc.getDefinitionString().equals("<>")||
			tc.getDefinitionString().equals("oclIsKindOf")||
			tc.getDefinitionString().equals("oclIsTypeOf")||
			tc.getDefinitionString().equals("oclAsType")||
			tc.getDefinitionString().equals("oclIsUndefined")||
			tc.getDefinitionString().equals("allInstances")
		   ){
			setIcon(new ImageIcon(OCLCellRenderer.class.getResource("/resources/br/ufes/inf/nemo/ocl/editor/ocl_object.gif")));
		}
		
		if (tc.getDefinitionString().equals("forAll")||
			tc.getDefinitionString().equals("exists")||
			tc.getDefinitionString().equals("select")||
			tc.getDefinitionString().equals("reject")||
			tc.getDefinitionString().equals("collect")||
			tc.getDefinitionString().equals("isUnique")||
			tc.getDefinitionString().equals("closure")||
			tc.getDefinitionString().equals("one")
		   ){
			setIcon(new ImageIcon(OCLCellRenderer.class.getResource("/resources/br/ufes/inf/nemo/ocl/editor/ocl_iterator.gif")));
		}
		
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
			tc.getDefinitionString().equals("intersection")
		   ){
			setIcon(new ImageIcon(OCLCellRenderer.class.getResource("/resources/br/ufes/inf/nemo/ocl/editor/ocl_collection.gif")));
		}
		
		if (tc.getDefinitionString().equals("or")||
			tc.getDefinitionString().equals("and")||
			tc.getDefinitionString().equals("implies")||
			tc.getDefinitionString().equals("xor")||
			tc.getDefinitionString().equals("not")
		   ){
			setIcon(new ImageIcon(OCLCellRenderer.class.getResource("/resources/br/ufes/inf/nemo/ocl/editor/ocl_boolean.gif")));
		}
		
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
			tc.getDefinitionString().equals("subtraction")
		   ){
			setIcon(new ImageIcon(OCLCellRenderer.class.getResource("/resources/br/ufes/inf/nemo/ocl/editor/ocl_integer.gif")));
		}

		
	}

	@Override
	@SuppressWarnings("rawtypes")
	protected void prepareForMarkupTagCompletion(JList list,MarkupTagCompletion mc, int index, boolean selected,boolean hasFocus) 
	{
		super.prepareForMarkupTagCompletion(list, mc, index, selected, hasFocus);		
		//setIcon(new ImageIcon(OCLCellRenderer.class.getResource("/resources/br/ufes/inf/nemo/ocl/editor/class.png")));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("rawtypes")
	@Override
	protected void prepareForOtherCompletion(JList list,Completion c, int index, boolean selected, boolean hasFocus) 
	{
		super.prepareForOtherCompletion(list, c, index, selected, hasFocus);		
		//setIcon(new ImageIcon(OCLCellRenderer.class.getResource("/resources/br/ufes/inf/nemo/ocl/editor/class.png")));
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("rawtypes")
	protected void prepareForVariableCompletion(JList list, VariableCompletion vc, int index, boolean selected, boolean hasFocus) 
	{
		super.prepareForVariableCompletion(list, vc, index, selected, hasFocus);		
		//setIcon(new ImageIcon(OCLCellRenderer.class.getResource("/resources/br/ufes/inf/nemo/ocl/editor/class.png")));
	}


	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("rawtypes")
	protected void prepareForFunctionCompletion(JList list, FunctionCompletion fc, int index, boolean selected, boolean hasFocus) 
	{
		super.prepareForFunctionCompletion(list, fc, index, selected, hasFocus);		
		//setIcon(new ImageIcon(OCLCellRenderer.class.getResource("/resources/br/ufes/inf/nemo/ocl/editor/class.png")));
	}


}