package br.ufes.inf.nemo.ocl.editor.completion;

import java.util.ArrayList;

import org.fife.ui.autocomplete.DefaultCompletionProvider;

/**
 * @author John Guerson
 */

public class OCLCompletionProvider {

	public DefaultCompletionProvider provider;
	public ArrayList<OCLTemplateCompletion> constraints = new ArrayList<OCLTemplateCompletion>();
	public ArrayList<OCLTemplateCompletion> objects = new ArrayList<OCLTemplateCompletion>();
	public ArrayList<OCLTemplateCompletion> sets = new ArrayList<OCLTemplateCompletion>();
	public ArrayList<OCLTemplateCompletion> iterators = new ArrayList<OCLTemplateCompletion>();
	public ArrayList<OCLTemplateCompletion> expressions = new ArrayList<OCLTemplateCompletion>();
	public ArrayList<OCLTemplateCompletion> integers = new ArrayList<OCLTemplateCompletion>();
	public ArrayList<OCLTemplateCompletion> booleans = new ArrayList<OCLTemplateCompletion>();	
	
	public OCLCompletionProvider(DefaultCompletionProvider provider)
	{	
		this.provider = provider;				
	}

	public void addCompletions()
	{
		constraints.addAll(createConstraintCompletion());		
		objects.addAll(createObjectCompletion());
		sets.addAll(createSetCompletion());
		iterators.addAll(createIteratorCompletion());
		expressions.addAll(createExpressionCompletion());		
		integers.addAll(createIntegerCompletion());	
//		booleans.addAll(createBooleanCompletion());
	}
	
	public DefaultCompletionProvider getProvider(){ return provider; }
	
	/** Constraint Completion*/
	private ArrayList<OCLTemplateCompletion> createConstraintCompletion()
	{
		String description = new String();
		ArrayList<OCLTemplateCompletion> oclCompletionList = new ArrayList<OCLTemplateCompletion>();
		
		description = "<b>context TypeName <br>inv : true </b><br><br>"+
		"Invariant.";
		
		OCLTemplateCompletion c = new OCLTemplateCompletion(provider, 
			"inv","invariant",
			"context ${Type}\ninv : ${true} ${cursor}\n",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "<b>context TypeName :: PropertyName : ResultTypeName <br>derive: ...</b><br><br>"+
		"Derivation constraint.";
		
		c = new OCLTemplateCompletion(provider, 
			"derive","derivation",
			"context ${TypeName}::${PropertyName}:${ResultTypeName}\nderive : ${cursor}\n",
			null,description);		
		provider.addCompletion(c); 
		oclCompletionList.add(c);
		
		description = "<b>context TypeName </b><br><br>"+
		"Context declaration.";
		
		c = new OCLTemplateCompletion(provider, 
			"context","context",
			"context ${Type}\n${cursor}\n",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "<b>context TypeName <br>def: OperationName(p: Param1,..., pn:ParamN) : ResultTypeName = ...</b><br><br>"+
		"Operation Definition.";
		
		c = new OCLTemplateCompletion(provider, 
			"def","definition",
			"context ${TypeName}\ndef: ${OperationName}(${p}: ${ParamTypeName}) : ${ResultTypeName} = ${cursor}\n",
			null,description);		
		provider.addCompletion(c); 
		oclCompletionList.add(c);
		
		return oclCompletionList;
	}
	
	/** Expression Completion*/
	private ArrayList<OCLTemplateCompletion> createExpressionCompletion()
	{
		ArrayList<OCLTemplateCompletion> oclCompletionList = new ArrayList<OCLTemplateCompletion>();
		
		String description = "<b>let var = true <br>in expression</b>";
		
		OCLTemplateCompletion c = new OCLTemplateCompletion(provider, 
			"let","let-in",
			"let ${varName} = ${expression}\nin ${expression}${cursor}",
			null,
			description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "<b>if cond then expression <br>else expression <br>endif</b>";
		
		c = new OCLTemplateCompletion(provider, 
			"if","if-then-else",
			"if ${boolean-expression} then ${expression}\nelse ${expression} endif${cursor}",
			null,
			description);
		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		return oclCompletionList;
	}
	
	/** Object Completion */
	private ArrayList<OCLTemplateCompletion> createObjectCompletion()
	{ 
		ArrayList<OCLTemplateCompletion> oclCompletionList = new ArrayList<OCLTemplateCompletion>();
		
		String description = "Operation <b>OclAny::=(object2: OclSelf): Boolean</b><br><br>"+
		"True if self is the same object as object2. Infix operator.";

		OCLTemplateCompletion c = new OCLTemplateCompletion(provider, 
			"=","=",
			"= ${cursor}",
			null, description);		
		provider.addCompletion(c); 
		oclCompletionList.add(c);
		
		description = "Operation <b>OclAny::&lt&gt(object2: OclSelf): Boolean</b><br><br>"+
		"True if self is a different object from object2. Infix operator.";

		c = new OCLTemplateCompletion(provider, 
			"&lt&gt","<>",
			"<> ${cursor}",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>OclAny::oclAsType(type: AnyClassifier(T)): T</b><br><br>"+
		
		"Evaluates to self, where self is of the type identified by T. The type T may be any classifier " +
		"defined in the OntoUML model; if the actual type of self at evaluation time does not conform to T, then " +
		"the oclAsType operation evaluates to invalid."+"<br><br>"+

		"In the case of feature redefinition, casting an object to a supertype of its actual type does not access " +
		"the supertype definition of the feature; according to the semantics of redefinition, the redefined feature " +
		"simply does not exist for the object. However, when casting to a supertype, any features additionally defined " +
		"by the subtype are suppressed.";

		c = new OCLTemplateCompletion(provider, 
			"oclAsType","oclAsType",
			"oclAsType(${T})${cursor}",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>OclAny::oclIsKindOf(type: AnyClassifier(T)): Boolean</b><br><br>"+
		"Evaluates to true if the type of self conforms to t. That is, self is of type t or a subtype of t.";
		
		c = new OCLTemplateCompletion(provider, 
			"oclIsKindOf","oclIsKindOf",
			"oclIsKindOf(${T})${cursor}",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>Classifier::allInstances(): Classifier-instances</b<<br><br>" +
		"Returns the set of all instances of a Classifier T.";
		
		c = new OCLTemplateCompletion(provider, 
			"allInstances","allInstances",
			"allInstances()${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>OclAny::oclIsTypeOf(type: AnyClassifier(T)): Boolean</b><br><br>"+
		"Evaluates to true if self is of the type t but not a subtype of t.";
		
		c = new OCLTemplateCompletion(provider, 
			"oclIsTypeOf","oclIsTypeOf",
			"oclIsTypeOf(${T})${cursor}",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>OclAny::oclIsUndefined(): Boolean</b><br><br>"+
		"Evaluates to true if self is equal to invalid or equal to null.";

		c = new OCLTemplateCompletion(provider, 
			"oclIsUndefined","oclIsUndefined",
			"oclIsUndefined()${cursor}",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>OclAny::oclIsNew() : Boolean</b><br><br>"+
		"Can only be used in a postcondition. Evaluates to true if the self is created during"+ 
		"performing the operation (for instance, it didn’t exist at precondition time).";

		c = new OCLTemplateCompletion(provider, 
				"oclIsNew","oclIsNew",
				"oclIsNew()${cursor}",
				null, description);		
			provider.addCompletion(c);
			oclCompletionList.add(c);
			
		return oclCompletionList;
	}
	
	/** Collection Completion */
	private ArrayList<OCLTemplateCompletion> createSetCompletion()
	{
		ArrayList<OCLTemplateCompletion> oclCompletionList = new ArrayList<OCLTemplateCompletion>();
		
		String description = "Operation <b>Collection(T)::size(): Integer</b><br><br>"+
		"The number of elements in the collection self.";
				
		OCLTemplateCompletion c = new OCLTemplateCompletion(provider, 
			"size","size",
			"size()${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>Collection(T)::includesAll(T2)(c2: Collection(T2)): Boolean</b><br><br>"+
		"Does self contain all the elements of c2 ?";

		c = new OCLTemplateCompletion(provider, 
			"includesAll","includesAll",
			"includesAll(${Collection(T)})${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>Collection(T)::excludesAll(T2)(c2: Collection(T2)): Boolean</b><br><br>"+
		"Does self contain none of the elements of c2 ?";

		c = new OCLTemplateCompletion(provider, 
			"excludesAll","excludesAll",
			"excludesAll(${Collection(T)})${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>Collection(T)::includes(obj: OclAny): Boolean</b><br><br>"+
		"True if object is an element of self, false otherwise.";

		c = new OCLTemplateCompletion(provider, 
			"includes","includes",
			"includes(${Collection(T))${cursor}",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>Collection(T)::excludes(obj: OclAny): Boolean</b><br><br>"+
		"True if object is not an element of self, false otherwise.";
		
		c = new OCLTemplateCompletion(provider, 
			"excludes","excludes",
			"excludes(${object})${cursor}",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>Collection(T)::isEmpty(): Boolean</b><br><br>"+
		"Is self the empty collection?"+"<br><br>"+ 
		"Note: null->isEmpty() returns true in virtue of the implicit casting from null to Bag{}.";

		c = new OCLTemplateCompletion(provider, 
			"isEmpty","isEmpty",
			"isEmpty()${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>Collection(T)::notEmpty(): Boolean</b><br><br>"+
		"Is self not the empty collection?"+"<br><br>"+ 
		"null->notEmpty() returns false in virtue of the implicit casting from null to Bag{}.";

		c = new OCLTemplateCompletion(provider, 
			"notEmpty","notEmpty",
			"notEmpty()${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>Collection(T)::asSet(): Set(T)</b><br><br>"+
		"The Set containing all the elements from self, with duplicates removed.";

		c = new OCLTemplateCompletion(provider, 
			"asSet","asSet",
			"asSet()${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>Set(T)::union(s: Collection(T)): Set(T)</b><br><br>"+
		"The set consisting of all elements in self and all elements in s.";

		c = new OCLTemplateCompletion(provider, 
			"union","union",
			"union(${Collection(T)})${cursor}",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>Set(T)::intersection(s: Collection(T)): Set(T)</b><br><br>"+
		"The intersection of self and s (i.e., the set of all elements that are in both self and s).";
				
		c = new OCLTemplateCompletion(provider, 
			"intersection","intersection",
			"intersection${Collection(T)})${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>Set(T)::including(obj: T): Set(T)</b><br><br>"+
		"The set containing all elements of self plus object.";

		c = new OCLTemplateCompletion(provider, 
			"including","including",
			"including(${object})${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>Set(T)::excluding(obj: OclAny): Set(T)</b><br><br>"+
		"The set containing all elements of self without object.";

		c = new OCLTemplateCompletion(provider, 
			"excluding","excluding",
			"excluding(${object})${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>UniqueCollection(T)::symmetricDifference(s: UniqueCollection(OclAny)): Set(T)</b><br><br>"+
		"The set containing all the elements that are in self or s, but not in both.";

		c = new OCLTemplateCompletion(provider, 
			"symmetricDifference","symmetricDifference",
			"symmetricDifference(${UniqueCollection(OclAny)})${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>Set(T)::-(s: UniqueCollection(OclAny)): Set(T)</b><br><br>"+
		"The elements of self, which are not in s.";
				
		c = new OCLTemplateCompletion(provider, 
			"-","-",
			"- ${UniqueCollection(OclAny)} ${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>Collection(T)::sum(): T</b><br><br>"+
		
		"The addition of all elements in self. Elements must be of an OclSummable type to provide " +
		"the zero() and sum() operations. The sum operation must be both associative: a.sum(b).sum(c) = " +
		"a.sum(b.sum(c)), and commutative: a.sum(b) = b.sum(a). UnlimitedNatural, Integer and Real fulfill this condition."+"<br><br>"+

		"If the sum operation is not both associative and commutative, the sum expression is not well-formed, which may " +
		"result in unpredictable results during evaluation. If an implementation is able to detect a lack of associativity " +
		"or commutativity, the implementation may bypass the evaluation and return an invalid result.";

		c = new OCLTemplateCompletion(provider, 
			"sum","sum",
			"sum()${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>Collection(T)::product(T2)(c2: Collection(T2)): Set(Tuple(first : T, second : T2))</b><br><br>"+
		"The cartesian product operation of self and c2.";

		c = new OCLTemplateCompletion(provider, 
			"product","product",
			"product(${Collection(T)})${cursor}",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>Set(T)::flatten(): Set(T2)</b><br><br>"+
		"Redefines the Collection operation. If the element type is not a collection type, " +
		"this results in the same set as self. If the element type is a collection type, the result " +
		"is the set containing all the elements of all the recursively flattened elements of self.";

		c = new OCLTemplateCompletion(provider, 
			"flatten","flatten",
			"flatten()${cursor}",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>Collection(T)::count(obj: OclAny): Integer</b><br><br>"+
		"The number of times that object occurs in the collection self.";
		
		c = new OCLTemplateCompletion(provider, 
			"count","count",
			"count(${object})${cursor}",
			null, description);		
		provider.addCompletion(c);		
		oclCompletionList.add(c);
		
		return oclCompletionList;
	}

	/** Iterator Completion */
	private ArrayList<OCLTemplateCompletion> createIteratorCompletion()
	{
		ArrayList<OCLTemplateCompletion> oclCompletionList = new ArrayList<OCLTemplateCompletion>();

		String description = "Iteration <b>Collection(T)::exists(j:T,i:T | body:LambdaT():Boolean): Boolean</b>";
		
		OCLTemplateCompletion  c = new OCLTemplateCompletion(provider, 
			"exists","exists",
			"exists(${i,j:T} | ${body})${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Iteration <b>Collection(T)::forAll(i:T,j:T | body:LambdaT():Boolean): Boolean</b>";
		
		c = new OCLTemplateCompletion(provider, 
			"forAll","forAll",
			"forAll(${i,j:T} | ${body})${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Iteration <b>Collection(T)::one(i:T | body:LambdaT():Boolean): Boolean</b><br><br>"+
		"Results in true if there is exactly one element in the source collection for which body is true.";

		c = new OCLTemplateCompletion(provider, 
			"one","one",
			"one(${i:T} | ${body})${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Iteration <b>Set(T)::select(i:T | body:LmbdaT():Boolean): Set(T)</b><br><br>"+
		"The subset of set for which expr is true.";

		c = new OCLTemplateCompletion(provider, 
			"select","select",
			"select(${i:T} | ${body})${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Iteration <b>Set(T)::reject(i:T | body:LambdaT():Boolean): Set(T)</b><br><br>"+
		"The subset of the source set for which body is false.";

		c = new OCLTemplateCompletion(provider, 
			"reject","reject",
			"reject(${i:T} | ${body})${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Iteration <b>Bag(T)::closure(i:T | body:LambdaT():Set(T)): Set(T)</b><br><br>"+
		"The closure of applying body transitively to every distinct element of the source collection";

		c = new OCLTemplateCompletion(provider, 
			"closure","closure",
			"closure(${i:T} | ${body})${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Iteration <b>Collection(T)::isUnique(i:T | body:LambdaT():OclAny): Boolean</b><br><br>"+
		"Results in true if body evaluates to a different value for each element in the source collection; " +
		"otherwise, result is false.";

		c = new OCLTemplateCompletion(provider, 
			"isUnique","isUnique",
			"isUnique(${i:T} | ${body})${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Iteration <b>Bag(T)::collect(V)(i:T | body:LambdaT():V): Bag(V)</b>";
		
		c = new OCLTemplateCompletion(provider, 
			"collect","collect",
			"collect(${i:T} | ${body})${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Iteration <b>Collection(T)::any(i:T | body:LambdaT():Boolean): T</b><br><br>"+
		"Returns any element in the source collection for which body evaluates to true. If there is more than one " +
		"element for which body is true, one of them is returned. There must be at least one element fulfilling body, " +
		"otherwise the result of this IteratorExp is null.";
		
		c = new OCLTemplateCompletion(provider, 
			"any","any",
			"any(${i:T} | ${body})${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		return oclCompletionList;
	}
	
	/** Integer Completion */
	private ArrayList<OCLTemplateCompletion> createIntegerCompletion()
	{
		ArrayList<OCLTemplateCompletion> oclCompletionList = new ArrayList<OCLTemplateCompletion>();
		String description = new String();
		
		description = "Operation <b>Real::floor(): Integer</b><br><br>"+
		"The largest integer that is less than or equal to self.";

		OCLTemplateCompletion c = new OCLTemplateCompletion(provider, 
			"max","max",
			"max(${Integer})${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>Integer::min(i: OclSelf): Integer</b><br><br>"+
		"The minimum of self an i.";

		c = new OCLTemplateCompletion(provider, 
			"min","min",
			"min(${Integer})${cursor}",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>Integer::abs(): Integer</b><br><br>"+
		"The absolute value of self.";

		c = new OCLTemplateCompletion(provider, 
			"abs","abs",
			"abs()${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>Real::floor(): Integer</b><br><br>"+
		"The largest integer that is less than or equal to self";

		c = new OCLTemplateCompletion(provider, 
			"floor","floor",
			"floor()${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>Real::round(): Integer</b><br><br>"+
		"The integer that is closest to self. When there are two such integers, the largest one.";

		c = new OCLTemplateCompletion(provider, 
			"round","round",
			"round()${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
//		description = "Operation <b>Integer::+(i : OclSelf) : Integer</b><br><br>"+
//		"The value of the addition of self and i.";
//
//		c = new OCLTemplateCompletion(provider, 
//			"+","+",
//			"+ ${<Integer>} ${cursor}",
//			null, description);		
//		provider.addCompletion(c);
//		oclCompletionList.add(c);
//		
//		description = "Operation <b>Integer::*(i : OclSelf) : Integer</b><br><br>"+
//		"The value of the multiplication of self and i.";
//
//		c = new OCLTemplateCompletion(provider, 
//			"*","*",
//			"* ${<Integer>} ${cursor}",
//			null, description);		
//		provider.addCompletion(c);
//		oclCompletionList.add(c);
//		
//		description = "Operation <b>Integer::-() : Integer</b><br><br>"+
//		"The negative value of self.";
//
//		c = new OCLTemplateCompletion(provider, 
//			"-","negative",
//			"- ${<Integer>} ${cursor}",
//			null, description);		
//		provider.addCompletion(c);
//		oclCompletionList.add(c);
//		
//		c = new OCLTemplateCompletion(provider, 
//			">",">",
//			"> ${<Integer>} ${cursor}",
//			null, description);		
//		provider.addCompletion(c);
//		oclCompletionList.add(c);
//		
//		c = new OCLTemplateCompletion(provider, 
//			">=",">=",
//			">= ${<Integer>} ${cursor}",
//			null,
//			description);		
//		provider.addCompletion(c);
//		oclCompletionList.add(c);
//		
//		c = new OCLTemplateCompletion(provider, 
//			"<=","<=",
//			"<= ${<Integer>} ${cursor}",
//			null,description);		
//		provider.addCompletion(c);
//		oclCompletionList.add(c);
//		
//		c = new OCLTemplateCompletion(provider, 
//			"<","<",
//			"< ${<Integer>} ${cursor}",
//			null, description);		
//		provider.addCompletion(c);
//		oclCompletionList.add(c);
//		
//		c = new OCLTemplateCompletion(provider, 
//			"-","subtraction",
//			"- ${<Integer>} ${cursor}",
//			null, description);		
//		provider.addCompletion(c);
//		oclCompletionList.add(c);
		
		return oclCompletionList;
	}
	
	
	/** Boolean Completion */
	@SuppressWarnings("unused")
	private ArrayList<OCLTemplateCompletion> createBooleanCompletion()
	{
		ArrayList<OCLTemplateCompletion> oclCompletionList = new ArrayList<OCLTemplateCompletion>();
		String description = new String();
		
		OCLTemplateCompletion c = new OCLTemplateCompletion(provider, 
			"or","or",
			"or ${OCLExpression} ${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		c = new OCLTemplateCompletion(provider, 
			"and","and",
			"and ${OCLExpression} ${cursor}",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		c = new OCLTemplateCompletion(provider, 
			"not","not",
			"not ${OCLExpression} ${cursor}",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		c = new OCLTemplateCompletion(provider, 
			"implies","implies",
			"implies ${OCLExpression} ${cursor}",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		c = new OCLTemplateCompletion(provider, 
			"xor","xor",
			"xor ${OCLExpression} ${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		return oclCompletionList;
	}	
	
}
