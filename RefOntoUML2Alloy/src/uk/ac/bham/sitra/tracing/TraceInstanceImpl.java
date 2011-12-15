package uk.ac.bham.sitra.tracing;

import uk.ac.bham.sitra.Rule;

/**
 * <p>An implementation of a TraceInstance. 
 * 
 * @param <S> The type of the source object. 
 * @param <T> The type of the target object.
 * @author Kyriakos Anastasakis
 * @since 0.2
 */
public class TraceInstanceImpl<S,T> implements TraceInstance<S, T> {

	private S source;
	private T target;
	private Rule<S, T> r = null;
	
	
	
	public TraceInstanceImpl(S source, T target){		
		this.source =source;
		this.target =target;
	}
	
	public TraceInstanceImpl(S source, T target, Rule<S, T> r){		
		this(source,target);
		setRule(r);
	}
	
	public TraceInstanceImpl(Rule<S, T> r){
		this.r = r;
	}
	
	public S getSource(){
		return source;
	}
	
	public T getTarget(){
		return target;
	}
	
	public Rule<S, T> getRule(){
		return r;
	}
	
	public void setRule(Rule<S, T> r){
		this.r = r;
	}
	
	public String toString(){
		return (new StringBuffer("Source: ").append(getSource().toString()).append(" Target:")
			.append(getTarget().toString()).append("\n").toString());
	}
	
}
