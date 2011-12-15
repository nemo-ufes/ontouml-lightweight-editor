package uk.ac.bham.sitra.tracing;

import java.util.List;

import uk.ac.bham.sitra.Rule;

/**
 * <p>An interface that exposes the methods that can be used for tracing. </p>
 * 
 * 
 * @author Kyriakos Anastasakis
 * @since 0.2
 *
 * @see TraceImpl
 * @see TraceInstanceImpl 
 * 
 */
public interface ITrace {
	
	/**
	 * Records a mapping between a source and a target element for a given rule.
	 */
	public <S,T> TraceInstance recordMapping(S source, T target, Rule<S, T> r);
	public <S,T> void recordMapping(TraceInstance<S,T> ti);
	@SuppressWarnings("unchecked")
	public List<TraceInstance> getTraceInstances();
	public boolean getIsEnabled();
	public void setIsEnabled(boolean enabled);
	
	
	/**
	 * Corresponds to the <b>resolveone</b> of the QVT specification 
	 */
	public <S,T> T resolveone(S src);
	
	/**
	 * Corresponds to the <b>resolveone</b> of the QVT specification
	 * where a condition of a class type is passed as a parameter 
	 */
	public <S,T> T resolveone(S src, Class<T> targetType);
	
	/**
	 * Corresponds to the <b>resolveone</b> of the QVT specification
	 * applied to a list of source objects 
	 */
	public <S,T> T resolveone(List<S> srclst);
	
	/**
	 * Corresponds to the <b>resolveone</b> of the QVT specification
	 * applied to a list of source objects and requiring a condition 
	 */
	public <S,T> T resolveone(List<S> srclst, Class targetType);
	
	/**
	 * Corresponds to the <b>resolve</b> of the QVT specification 
	 */
	public List<Object> resolve(Object src);
	
	/**
	 * Corresponds to the <b>resolveone</b> of the QVT specification
	 * applied to a collection of source objects. 
	 */
	public List<Object> resolve(List<Object> srclist);
	
	/**
	 * Corresponds to the <b>resolve</b> of the QVT specification 
	 * where an extra condition is used to constraint the type 
	 * of target objects returned
	 */
	@SuppressWarnings("unchecked")
	public <S,T> List<T> resolve(S src, Class targetType);
	
	/**
	 * Corresponds to the <b>resolve</b> of the QVT specification
	 * applied to a collection of source objects and 
	 * an extra condition is used to constraint the type 
	 * of target objects returned  
	 */
	@SuppressWarnings("unchecked")
	public <S,T> List<T> resolve (List<S> srclist, Class targetType);

	
	/**
	 * 
	 * <p>Returns a List of the target objects of a specific kinds (<i>targetType</i>)</p>
	 * <p> For example if used like: <br/>
	 * <code>
	 * trace.resolve(Table.class) 
	 * </code>
	 * <br />
	 * it will return target Table objects.  
	 *  </p>
	 * 
	 * @param <T> The type of returned objects
	 * @param targetType The class type 
	 * @return A List of target Objects that conform to the class type passed as a parameter  
	 */
	public <T> List<T> resolve(Class<? extends T> targetType);
	
	/**
	 * <p>Returns a List of the source object of a specific kind (<i>sourceType</i>)</p>
	 * 
	 * <p> For example if used like: <br/>
	 * <code>
	 * trace.resolvein(Person.class) 
	 * </code>
	 * <br />
	 * it will return source Person objects.  
	 *  </p>
	 * 
	 * @param <S> The type of the source object
	 * @param <T> The type of the target objects
	 * @param targetList The list of target objects to match
 	 * @param sourceType Only find traces of sourceType and match them against the targetList. Can be null.
	 * @return A List of source elements that map to the target
	 */
	@SuppressWarnings("unchecked")
	public <S,T> List<S> resolvein (List<T> targetList, Class sourceType);
}
