package br.inf.ufes.nemo.transformation;

/**
 * Generic transformation exception.
 * 
 * @author Antognoni Albuquerque
 * @version 0.1
 */
public class TransformationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TransformationException() {
		super("AN ERROR HAS OCURRED WHEN TRANSFORMING THE TYPE");
	}

	public TransformationException(String message) {
		super("AN ERROR HAS OCURRED WHEN TRANSFORMING THE TYPE: " +message);
	}
}
