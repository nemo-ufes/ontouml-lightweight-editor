package uk.ac.bham.sitra;

import java.util.List;

import uk.ac.bham.sitra.tracing.ITrace;


/**
 * <b>Implementations should use this interface to carry out 
 * the logic of how the transformation rules are used. SiTra currently
 * provides a simple implementation 
 * {@link SimpleTransformerImpl}, which should be enough for the needs of most    
 * transformations.
 * 
 * @author David Akehurst
 * @author Behzad Bordbar
 * @version 0.2
 * @see SimpleTransformerImpl
 *
 */
public interface Transformer {
	/**
	 * <p>Implementation should transform a source Object to a target Object. 
	 * @param object The source object to transform.
	 * @throws RuleNotFoundException An Exception if no rule can be found to transform the 
	 * source object to the target object.
	 * @return The generated target object.
	 */
	Object transform(Object object) throws RuleNotFoundException;
	
	/**
	 * <p> Implementations should transform a list of source objects
	 *  to a list of target objects using the registered transformation rules.
	 *  
	 * @param sourceObjects The list of source objects to transform.
	 * @throws RuleNotFoundException An Exception if no rule can be found to transform the 
	 * source object to the target object.
	 * @return A list of generated target objects.
	 */
	List<? extends Object> transformAll(List<? extends Object> sourceObjects) throws RuleNotFoundException;
	
	/**
	 * <p> A type safe version of the Transform method. It is used 
	 * to transform a <code>source</code> object to a target object
	 * using a specific {@link Rule} implementation.  
	 * 
	 * @param <S> The type of the source object.
	 * @param <T> The type of the target object.
	 * 
	 * @param ruleClass The class of an implementation of the rule used to transform 
	 * the source object to the target object. 
	 * @param source The source object to transform.
	 * @throws RuleNotFoundException An Exception if no rule can be found to transform the 
	 * source object to the target object.
	 * @return The generated target object.
	 */
	<S, T> T transform(Class<? extends Rule<S, T>> ruleClass, S source) throws RuleNotFoundException;
	
	/**
	 * <p>It is used to transform a list of <code>source</code> object
	 * to a list of target objects using a specific {@link Rule} implementation.
	 * 
	 * @param <S>  The type of the source objects.
	 * @param <T> The type of the target objects.
	 * @param ruleClass The class of an implementation of the rule used to transform 
	 * the source objects to the targets object. 
	 * @param element A list of the source objects to transform.
	 * @throws RuleNotFoundException An Exception if no rule can be found to transform the 
	 * source object to the target object.
	 * @return A list of the generated target objects.
	 */
	<S, T> List<? extends T> transformAll(Class<? extends Rule<S, T>> ruleClass, List<? extends S> element) throws RuleNotFoundException;
	
	/**
	 * <p>Used to add a rule implementation in the transformer.
	 * 
	 * @param <S> The type of the source object the rule maps.
	 * @param <T> The type of the target object the rule maps.
	 * @param ruleType The class of the rule.
	 */
	<S, T> void addRuleType(Class<? extends Rule<S,T>> ruleType);
	
	// ------- Tracing -----

	/**
	 * @return The trace information for the execution.
	 */
	ITrace getTraceInformation();
}
