/**
 * Copyright 2007 Wei-ju Wu
 *
 * This file is part of TinyUML.
 *
 * TinyUML is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * TinyUML is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with TinyUML; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package br.ufes.inf.nemo.oled.util;

import java.util.ResourceBundle;

public final class VerificationResources {

	private ResourceBundle validationResources =
		ResourceBundle.getBundle("resources.br.ufes.inf.nemo.oled.ui.ontouml-constraints");

	private static VerificationResources instance = new VerificationResources();

	/**
	 * Private constructor to enforce Singleton.
	 */
	private VerificationResources() { }

	/**
	 * Returns the singleton instance.
	 * @return the singleton instance
	 */
	public static VerificationResources getInstance() { return instance; }

	/**
	 * Returns the value of the specified property.
	 * @param property the property name
	 * @return the value
	 */
	public String getString(String property) {
		try {
			return validationResources.getString(property);
		} catch (Exception ex) {
		}
		
		return null;
	}


	/**
	 * Returns the first character of a resource property.
	 * @param property the property to retrieve
	 * @return the first character as an int
	 */
	public int getChar(String property) {
		String str = getString(property);
		return str == null ? 0 : str.charAt(0);
	}
}
