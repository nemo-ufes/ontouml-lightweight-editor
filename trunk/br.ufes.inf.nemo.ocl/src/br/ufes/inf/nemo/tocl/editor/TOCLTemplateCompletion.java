package br.ufes.inf.nemo.tocl.editor;

import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.TemplateCompletion;

/**
 * @author John Guerson
 */

public class TOCLTemplateCompletion extends TemplateCompletion {

	
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