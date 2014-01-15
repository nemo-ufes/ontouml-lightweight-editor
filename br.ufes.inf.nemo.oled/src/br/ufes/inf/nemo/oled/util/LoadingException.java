/***************************************************************************
 * SWT Autoloader - everything in a jar                                    *
 * Copyright (C) 2005 by Silvio Moioli                                     *
 * silvio@moioli.net                                                       * 
 *                                                                         *
 * A brief tutorial on how to use this class is provided in the readme     *
 * file included in the distribution package.                              *
 * Use of this software is subject to the terms in the LICENSE.txt file    *
 ***************************************************************************/
package br.ufes.inf.nemo.oled.util;

/**
 * Exception thrown by SWTLoader.
 * 
 * @author Silvio Moioli
 */
@SuppressWarnings("serial")
public class LoadingException extends Exception {

	/**
	 * Default constructor.
	 * 
	 * @see java.lang.Exception#Exception()
	 */
	public LoadingException() {
		super();
	}

	/**
	 * @see java.lang.Exception#Exception()
	 */
	public LoadingException(String message) {
		super(message);
	}

	/**
	 * @see java.lang.Exception#Exception()
	 */
	public LoadingException(Throwable cause) {
		super(cause);
	}

	/**
	 * @see java.lang.Exception#Exception()
	 */
	public LoadingException(String message, Throwable cause) {
		super(message, cause);
	}
}
