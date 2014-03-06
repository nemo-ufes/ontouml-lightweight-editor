package br.ufes.inf.nemo.tocl.editor.completion;

import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.TemplateCompletion;

public class TModelTemplateCompletion extends TemplateCompletion {
	
	public TModelTemplateCompletion(CompletionProvider provider, String inputText,
			String definitionString, String template, String shortDescription, String summary) 
	{
		super(provider, inputText, definitionString, template, shortDescription,summary);		
		
	}
	
	public TModelTemplateCompletion(CompletionProvider provider, String inputText,
			String definitionString, String template) 
	{
		super(provider, inputText, definitionString, template);
	}	
}

