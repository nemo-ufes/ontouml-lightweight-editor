package uk.ac.bham.sitra;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import uk.ac.bham.sitra.tracing.ITrace;

/**
 * <p>A Simple implementation of a transformer. Users of this class
 * should first register the transformation rule implementations using the 
 * {@link #addRuleType(Class)} method. Once the rule has been registered the 
 * client can use  {@link #transform(Object)} or {@link #transform(Class, Object)} 
 * methods to transform a source model element to a target model element.
 * 
 * 
 *  @author David Akehurst
 *  @author Behzad Bordbar
 *  @author Kyriakos Anastasakis
 *  @version 0.2
 *
 */
public class SimpleTransformerImpl implements Transformer {
	
	public SimpleTransformerImpl(List<Class<? extends Rule>> ruleTypes) {
		this.ruleTypes = ruleTypes;
	}
	
	@SuppressWarnings("unchecked")
	Map<Class<? extends Rule>, Map<Object, Object>> mappings = new HashMap<Class<? extends Rule>, Map<Object, Object>>();
	
	@SuppressWarnings("unchecked")
	<S, T> Map<S, T> getRuleMappings(Class<? extends Rule<S, T>> rule) {
		Map<S, T> ruleMappings = (Map<S, T>) mappings.get(rule);
		if (ruleMappings == null) {
			ruleMappings = new HashMap<S, T>();
			mappings.put(rule, (Map<Object, Object>) ruleMappings);
		}
		return ruleMappings;
	}
	<S, T> void recordMaping(Class<? extends Rule<S, T>> rule, S source, T target) {
		getRuleMappings(rule).put(source, target);
	}
	
	<S, T> T getExistingTargetFor(Class<? extends Rule<S, T>> rule, S source) {
		return getRuleMappings(rule).get(source);
	}
	
	@SuppressWarnings("unchecked")
	<S, T> T applyRule(Rule<S, T> r, S source) throws RuleNotFoundException {
		Class<? extends Rule<S, T>> ruleType = (Class<? extends Rule<S, T>>)r.getClass();
		T target = getExistingTargetFor(ruleType, source);
		if (target == null) {
			target = r.build(source, this);
			recordMaping(ruleType, source, target);
			r.setProperties(target,source,this);
			// Also record trace information
			recordTrace(source, target, r);
		}				
		
		return target;
	}
	
	
	// --- Transformer ---
	@SuppressWarnings("unchecked")
	List<Class<? extends Rule>> ruleTypes;
	@SuppressWarnings("unchecked")
	public List<Class<? extends Rule>> getRuleTypes() {
		if (this.ruleTypes == null) {
			this.ruleTypes = new Vector<Class<? extends Rule>>();
		}
		return this.ruleTypes;
	}
	
	public <S, T> void addRuleType(Class<? extends Rule<S, T>> ruleType) {
		getRuleTypes().add(ruleType);
	}
	
	@SuppressWarnings("unchecked")
	List<Rule> getRules(Class<? extends Rule> ruleType) {
		List<Rule> rules = new Vector<Rule>();
		for (Class<? extends Rule> rt : getRuleTypes()) {
			if (ruleType.isAssignableFrom(rt)) {
				if (!Modifier.isAbstract(rt.getModifiers())) {
					try {
						rules.add(rt.newInstance());
					} catch (InstantiationException e) {
						throw new RuntimeException(e);
					} catch (IllegalAccessException e) {
						throw new RuntimeException(e);
					}
				}
			}
		}
		return rules;
	}
	
	@SuppressWarnings("unchecked")
	public <S, T> T transform(Class<? extends Rule<S, T>> ruleType, S source) throws RuleNotFoundException {
			List<Rule> rules = getRules(ruleType);			
			if (rules.isEmpty()) {
					throw new RuleNotFoundException(source.toString());
			} else {
				int classCastsExcs = 0;
				for (Rule rule : rules) {
					Boolean b = false;
					try {
						b = rule.check(source);
					} catch (ClassCastException e) {
						classCastsExcs++;
					}
					if (b) {
						return applyRule((Rule<S, T>) rule, source);
					}
				}
				if (classCastsExcs == rules.size()) {
					// No rule can be found to be Applied
					// This is an error
					throw new RuleNotFoundException(source.toString());
				}
			}
		return null;
	}
		
	public <S, T> List<? extends T> transformAll(Class<? extends Rule<S, T>> ruleType, List<? extends S> element)
				throws RuleNotFoundException {
		List<T> targets = new Vector<T>();
		for (S s : element) {
			T o = transform(ruleType, s);
			// Please note that the element generated may be null.
			targets.add(o);
		}
		return targets;
	}
	
	@SuppressWarnings("unchecked")
	public Object transform(Object object) throws RuleNotFoundException {
		return transform((Class)Rule.class, object);
	}
	
	@SuppressWarnings("unchecked")
	public List<? extends Object> transformAll(List<? extends Object> sourceObjects)
				throws RuleNotFoundException{
		return transformAll((Class)Rule.class, sourceObjects);
	}
	
	// ------- Tracing -----
	/**
	 * Used to hold  the tracing information
	 */
	private ITrace trace = null;

	/**
	 * Returns the {@link SimpleTransformerImpl#trace}
	 */
	public ITrace getTraceInformation() {
		return trace;
	}
	
	
	/**
	 * @param <S> The type of the source object
	 * @param <T> The type of the target object
	 * @param source An instance of the source object that is being mapped
	 * @param target An instance of the target object to which the source object is being mapped
	 * @param r The {@link Rule} implementation instance 
	 *  responsible for the mapping 
	 */

	private <S, T> void recordTrace(S source, T target, Rule<S, T> r) {
		getTraceInformation().recordMapping(source, target, r);
	}
	
}
