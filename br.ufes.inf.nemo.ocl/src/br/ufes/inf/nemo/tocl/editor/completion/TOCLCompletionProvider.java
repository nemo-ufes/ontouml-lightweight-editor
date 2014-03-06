package br.ufes.inf.nemo.tocl.editor.completion;

import java.util.ArrayList;

import org.fife.ui.autocomplete.DefaultCompletionProvider;

/**
 * @author John Guerson
 */

public class TOCLCompletionProvider {

	public DefaultCompletionProvider provider;	
	public ArrayList<TOCLTemplateCompletion> objects = new ArrayList<TOCLTemplateCompletion>();
	
	public TOCLCompletionProvider(DefaultCompletionProvider provider)
	{
		this.provider = provider;				
		objects.addAll(createObjectCompletion());
	}		

	public DefaultCompletionProvider getProvider(){ return provider; }
		
	/** Object Completion */
	private ArrayList<TOCLTemplateCompletion> createObjectCompletion()
	{ 
		String description = new String();
		ArrayList<TOCLTemplateCompletion> oclCompletionList = new ArrayList<TOCLTemplateCompletion>();
				
		description = "Operation <b>OclAny::oclIsKindOf(T)(type : AnyClassifier(T), w: World) : Boolean</b><br><br>"+
		"Evaluates to true if the type of self conforms to t in w. That is, self is of type t or a subtype of t in the World w.";
		
		TOCLTemplateCompletion c = new TOCLTemplateCompletion(provider, 
			"oclIsKindOf(w)","oclIsKindOf(w)",
			"oclIsKindOf(${T},${world})${cursor}",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "";
		
		c = new TOCLTemplateCompletion(provider, 
			"allInstances(w)","allInstances(w)",
			"allInstances(${world})${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>OclAny::oclIsTypeOf(T)(type : AnyClassifier(T), w: World) : Boolean</b><br><br>"+
		"Evaluates to true if self is of the type t but not a subtype of t in the World w.";
		
		c = new TOCLTemplateCompletion(provider, 
			"oclIsTypeOf(w)","oclIsTypeOf(w)",
			"oclIsTypeOf(${T},${world})${cursor}",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
				
		return oclCompletionList;
	}	
}
