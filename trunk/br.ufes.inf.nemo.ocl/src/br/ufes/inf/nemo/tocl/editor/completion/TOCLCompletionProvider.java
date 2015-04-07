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
						
		description = "<b>context Class/World/Path <br>temp : true</b><br><br>"+
		"Temporal Invariant.";
		
		TOCLTemplateCompletion c = new TOCLTemplateCompletion(provider, 
			"temporal constraint","temp",
			"context ${Class}\ntemp : ${true} ${cursor}\n",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "<b>context Source :: Relationship : Target <br>" +
		"temp : { sourceEnd: [Mult], targetEnd: [Mult] } </b><br><br>"+"Historical Relationship.";
		
		c = new TOCLTemplateCompletion(provider, 
			"historical relationship","hist",
			"context ${Source}::${Relationship}:${Target}\n" +
			"temp : ${srcEnd}: [${0..*}], ${tgtEnd}:[${0..*}] } ${cursor}\n",
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
		
		description = "Operation <b>OclAny::oclIsTypeOf(type : AnyClassifier(T), w: World) : Boolean</b><br><br>"+
		"Evaluates to true if self is of the type t but not a subtype of t in the World w.";
		
		c = new TOCLTemplateCompletion(provider, 
			"oclIsTypeOf(w)","oclIsTypeOf(w)",
			"oclIsTypeOf(${T},${world})${cursor}",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
				
		description = "Operation <b>OclAny::oclAsType(type: AnyClassifier(T), w: World): T</b><br><br>"+
		
		"Evaluates to self, where self is of the type identified by T at world w. The type T may be any classifier " +
		"defined in the OntoUML model; if the actual type of self at evaluation time does not conform to T at w, then " +
		"the oclAsType operation evaluates to invalid."+"<br><br>"+

		"In the case of feature redefinition, casting an object to a supertype of its actual type does not access " +
		"the supertype definition of the feature; according to the semantics of redefinition, the redefined feature " +
		"simply does not exist for the object. However, when casting to a supertype, any features additionally defined " +
		"by the subtype are suppressed.";

		c = new TOCLTemplateCompletion(provider, 
			"oclAsType(w)","oclAsType(w)",
			"oclAsType(${T},${world})${cursor}",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>Class::allInstances(w: World): Class-instances</b<<br><br>" + 
		"Returns the set of all instances of Class T in world w.";
		
		c = new TOCLTemplateCompletion(provider, 
			"allInstances(w)","allInstances(w)",
			"allInstances(${world})${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
				
		description = "Operation <b>Individual::oclIsCreated(w: World) : Boolean</b><br><br>"+
		"Evaluates to true if self exists in w but not in the immediate previous world from w.<br>" +
		"In other words, it checks the object creation in world w.";
		
		c = new TOCLTemplateCompletion(provider, 
				"oclIsCreated","oclIsCreated",
				"oclIsCreated(${world})${cursor}",
				null, description);		
			provider.addCompletion(c);
			oclCompletionList.add(c);
			
			
		description = "Operation <b>Individual::oclIsDeleted(w: World) : Boolean</b><br><br>"+
		"Evaluates to true if self does not exist in w but  exist in the immediate previous world from w.<br>" +
		"In other words, it checks the object deletion in world w.";
		
		c = new TOCLTemplateCompletion(provider, 
				"oclIsDeleted","oclIsDeleted",
				"oclIsDeleted(${world})${cursor}",
				null, description);		
			provider.addCompletion(c);
			oclCompletionList.add(c);
			
		description = "Operation <b>Individual::oclBecomes(type: AnyClassifier(T), w: World) : Boolean</b><br><br>"+
		"Evaluates to true if self instantiates t in w but does not instantiate t in the immediate previous world from w.<br>" +
		"In other words, it checks if the object was classified as t in world w.";
		
		c = new TOCLTemplateCompletion(provider, 
				"oclBecomes","oclBecomes",
				"oclBecomes(${type}, ${world})${cursor}",
				null, description);		
			provider.addCompletion(c);
			oclCompletionList.add(c);
						
		description = "Operation <b>Individual::oclCeasesToBe(type: AnyClassifier(T), w: World) : Boolean</b><br><br>"+
		"Evaluates to true if self does not instantiate t in w but instantiate t in the immediate previous world from w.<br>" +
		"In other words, it checks if the object ceases to be classified as t in world w.";
		
		c = new TOCLTemplateCompletion(provider, 
				"oclCeasesToBe","oclCeasesToBe",
				"oclCeasesToBe(${type}, ${world})${cursor}",
				null, description);		
			provider.addCompletion(c);
			oclCompletionList.add(c);
						
		description = "Operation <b>Individual::existsIn(w: World) : Boolean</b><br><br>"+
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
		
		return oclCompletionList;
	}	
}
