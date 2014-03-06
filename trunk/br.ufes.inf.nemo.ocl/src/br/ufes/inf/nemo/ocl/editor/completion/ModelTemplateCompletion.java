package br.ufes.inf.nemo.ocl.editor.completion;

import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.TemplateCompletion;

/**
 * @author John Guerson
 */

public class ModelTemplateCompletion extends TemplateCompletion {
	
	public ModelTemplateCompletion(CompletionProvider provider, String inputText,
			String definitionString, String template, String shortDescription, String summary) 
	{
		super(provider, inputText, definitionString, template, shortDescription,summary);		
		
	}
	
	public ModelTemplateCompletion(CompletionProvider provider, String inputText,
			String definitionString, String template) 
	{
		super(provider, inputText, definitionString, template);
	}
}
