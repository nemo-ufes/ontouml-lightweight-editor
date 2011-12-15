package uk.ac.bham.sitra;

/**
 * Exception if the rule being transformed 
 * can not be found.
 * 
 * @author Kyriakos Anastasakis
 *
 */
public class RuleNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RuleNotFoundException() {
		super("A RULE FOR THE TRANSFORMATION IS MISSING");
	}

	public RuleNotFoundException(String message) {
		super("CAN NOT FIND RULE FOR THE TRANSFORMATION OF THE FOLLOWING TYPE: " +message);
	}
}
