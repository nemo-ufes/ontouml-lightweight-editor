package uk.ac.bham.sitra.tracing;

import uk.ac.bham.sitra.Rule;

/**
 * <p> Represents a TraceInstance, i.e. holds the runtime mapping information  
 * between a source an a target object. In essence this is a triple 
 * that holds for each rule that has been executed the 
 * <code>(source object, target object, rule)</code> information.
 *
 * @param <S> The type of the source object. 
 * @param <T> The type of the target object.
 *  
 * @author Kyriakos Anastasakis
 * @since 0.2
 *
 */
public interface TraceInstance<S,T> {
	
	/**
	 * 
	 * @return The {@link Rule} responsible for the mapping
	 */
	Rule<S, T> getRule();
	
	/**
	 * 
	 * @return The source object of the mapping
	 */
	S getSource();
	
	/**
	 * 
	 * @return The target object of the mapping
	 */
	T getTarget();

}
