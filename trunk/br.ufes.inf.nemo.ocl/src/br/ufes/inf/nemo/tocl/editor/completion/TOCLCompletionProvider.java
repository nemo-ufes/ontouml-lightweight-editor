package br.ufes.inf.nemo.tocl.editor.completion;

import java.util.ArrayList;

import org.fife.ui.autocomplete.DefaultCompletionProvider;

/**
 * @author John Guerson
 */

public class TOCLCompletionProvider {

	public DefaultCompletionProvider provider;	
	public ArrayList<TOCLTemplateCompletion> objects = new ArrayList<TOCLTemplateCompletion>();
	public ArrayList<TOCLTemplateCompletion> constraints = new ArrayList<TOCLTemplateCompletion>();
	
	public TOCLCompletionProvider(DefaultCompletionProvider provider)
	{
		this.provider = provider;		
	}		

	public void addCompletions()
	{
		objects.addAll(createObjectCompletion());
		constraints.addAll(createConstraintCompletion());	
	}
	
	public DefaultCompletionProvider getProvider(){ return provider; }
		
	private ArrayList<TOCLTemplateCompletion> createConstraintCompletion()
	{ 
		String description = new String();
		ArrayList<TOCLTemplateCompletion> oclCompletionList = new ArrayList<TOCLTemplateCompletion>();
						
		description = "<b>context TypeName <br>temp : true</b><br><br>"+
		"Temporal constraint.";
		
		TOCLTemplateCompletion c = new TOCLTemplateCompletion(provider, 
			"temp","temporal",
			"context ${TypeName}\ntemp : ${true} ${cursor}\n",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		return oclCompletionList;
	}
	
	/** Object Completion */
	private ArrayList<TOCLTemplateCompletion> createObjectCompletion()
	{ 
		String description = new String();
		ArrayList<TOCLTemplateCompletion> oclCompletionList = new ArrayList<TOCLTemplateCompletion>();
				
		description = "Operation <b>OclAny::oclIsKindOf(type : AnyClassifier(T), w: World) : Boolean</b><br><br>"+
		"Evaluates to true if the type of self conforms to t in w. That is, self is of type t or a subtype of t in the World w.";
		
		TOCLTemplateCompletion c = new TOCLTemplateCompletion(provider, 
			"oclIsKindOf(w)","oclIsKindOf(w)",
			"oclIsKindOf(${T},${world})${cursor}",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>Classifier::allInstances(w: World): Classifier-instances</b<<br><br>" + 
		"Returns the set of all instances of Classifier T in world w.";
		
		c = new TOCLTemplateCompletion(provider, 
			"allInstances(w)","allInstances(w)",
			"allInstances(${world})${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>OclAny::oclIsTypeOf(type : AnyClassifier(T), w: World) : Boolean</b><br><br>"+
		"Evaluates to true if self is of the type t but not a subtype of t in the World w.";
		
		c = new TOCLTemplateCompletion(provider, 
			"oclIsTypeOf(w)","oclIsTypeOf(w)",
			"oclIsTypeOf(${T},${world})${cursor}",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
				
		description = "Operation <b>OclAny::oclIsNew(w: World) : Boolean</b><br><br>"+
		"Evaluates to true if self exists in w but not in the immediate previous world from w.<br>" +
		"In other words, it checks the object creation in world w.";
		
		c = new TOCLTemplateCompletion(provider, 
				"oclIsNew(w)","oclIsNew(w)",
				"oclIsNew(${world})${cursor}",
				null, description);		
			provider.addCompletion(c);
			oclCompletionList.add(c);
			
		description = "Operation <b>OclAny::existsIn(w: World) : Boolean</b><br><br>"+
		"Evaluates to true if self exists in the World w.";
		
		c = new TOCLTemplateCompletion(provider, 
			"existsIn","existsIn",
			"existsIn(${world})${cursor}",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>World::previous() : World</b><br><br>"+
		"Returns the immediate previous world from the world self.";
		
		c = new TOCLTemplateCompletion(provider, 
			"previous","previous",
			"previous()${cursor}",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
				
		description = "Operation <b>World::next() : Set(World)</b><br><br>"+
		"Returns the immediate next world(s) from the world self.";
		
		c = new TOCLTemplateCompletion(provider, 
			"next","next",
			"next()${cursor}",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);

		description = "Operation <b>World::allNext() : Set(World)</b><br><br>"+
		"Returns all the worlds after the world self.";
		
		c = new TOCLTemplateCompletion(provider, 
			"allNext","allNext",
			"allNext()${cursor}",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>World::allNext(w: World) : Set(World)</b><br><br>"+
		"Returns all the worlds after the world self until w";
		
		c = new TOCLTemplateCompletion(provider, 
			"allNext(w)","allNext(w)",
			"allNext(${world})${cursor}",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>World::allNext(p: Path) : Set(World)</b><br><br>"+
		"Returns all the worlds after the world self in the path p";
		
		c = new TOCLTemplateCompletion(provider, 
			"allNext(p)","allNext(p)",
			"allNext(${path})${cursor}",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
				
		description = "Operation <b>World::allPrevious() : Set(World)</b><br><br>"+
		"Returns all the worlds before the world self.";
		
		c = new TOCLTemplateCompletion(provider, 
			"allPrevious","allPrevious",
			"allPrevious()${cursor}",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);

		description = "Operation <b>World::allPrevious(w: World) : Set(World)</b><br><br>"+
		"Returns all the worlds before the world self until w";
		
		c = new TOCLTemplateCompletion(provider, 
			"allPrevious(w)","allPrevious(w)",
			"allPrevious(${world})${cursor}",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>World::hasPrevious() : Boolean</b><br><br>"+
		"Evaluates to true if the world self has an immediate previous world.";
		
		c = new TOCLTemplateCompletion(provider, 
			"hasPrevious","hasPrevious",
			"hasPrevious()${cursor}",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>World::hasNext() : Boolean</b><br><br>"+
		"Evaluates to true if the world self has at least one immediate next world.";
		
		c = new TOCLTemplateCompletion(provider, 
			"hasNext","hasNext",
			"hasNext()${cursor}",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);

		description = "Operation <b>World::paths() : Set(Path)</b><br><br>"+
		"Returns all paths in which self is at.";
		
		c = new TOCLTemplateCompletion(provider, 
			"paths","paths",
			"paths()${cursor}",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>Path::worlds() : Set(World)</b><br><br>"+
		"Returns all worlds contained in the path self.";
		
		c = new TOCLTemplateCompletion(provider, 
			"worlds","worlds",
			"worlds()${cursor}",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);

		description = "Operation <b>World::allIndividuals() : Set(Individual)</b><br><br>"+
		"Returns all individuals contained in the world self.";
		
		c = new TOCLTemplateCompletion(provider, 
			"allIndividuals","allIndividuals",
			"allIndividuals()${cursor}",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>World::isOrigin() : Boolean</b><br><br>"+
		"Verifies if the world self is the first world of the structure.";
		
		c = new TOCLTemplateCompletion(provider, 
			"isOrigin","isOrigin",
			"isOrigin()${cursor}",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
				
		description = "Operation <b>World::isTerminal() : Boolean</b><br><br>"+
		"Verifies if the world self is the last world of the structure.";
		
		c = new TOCLTemplateCompletion(provider, 
			"isTerminal","isTerminal",
			"isTerminal()${cursor}",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
				
		return oclCompletionList;
	}	
}
