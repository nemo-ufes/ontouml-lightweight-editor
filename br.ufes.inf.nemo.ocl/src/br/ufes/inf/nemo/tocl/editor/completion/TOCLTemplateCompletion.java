package br.ufes.inf.nemo.tocl.editor.completion;

import org.fife.ui.autocomplete.CompletionProvider;

import br.ufes.inf.nemo.ocl.editor.completion.OCLTemplateCompletion;

/**
 * @author John Guerson
 */

public class TOCLTemplateCompletion extends OCLTemplateCompletion {
	
	public TOCLTemplateCompletion(CompletionProvider provider, String inputText,
			String definitionString, String template, String shortDescription, String summary) 
	{
		super(provider, inputText, definitionString, template, shortDescription,summary);		
		
	}
	
	public TOCLTemplateCompletion(CompletionProvider provider, String inputText,
			String definitionString, String template) 
	{
		super(provider, inputText, definitionString, template);
	}
}