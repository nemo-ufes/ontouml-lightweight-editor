package br.ufes.inf.nemo.ontouml2alloy;

/**
 * Copyright 2011 NEMO (http://nemo.inf.ufes.br/en)
 *
 * This file is part of OntoUML2Alloy (OntoUML to Alloy Transformation).
 *
 * OntoUML2Alloy is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * OntoUML2Alloy is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OntoUML2Alloy; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */

import java.awt.EventQueue;

import javax.swing.UIManager;

import br.ufes.inf.nemo.ontouml2alloy.ui.TheFrame;

public final class Main {

	/**
	 * Private constructor.
	 */
	private Main() { }

	/**
	 * The start method for this application.
	 * @param args the command line parameters
	 */
	public static void main(String[] args) {

		// Mac OS X settings
		if (System.getProperty("mrj.version") != null) {
			System.setProperty("apple.laf.useScreenMenuBar", "true");
			System.setProperty("com.apple.eawt.CocoaComponent.CompatibilityMode",
			"false");
			System.setProperty("com.apple.mrj.application.apple.menu.about.name",
			"OntoUML2Alloy");
		}
		
		//Needed for the embedded SWT Browser in Linux systems
		System.setProperty("sun.awt.xembedserver", "true");
		
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					
					TheFrame frame = new TheFrame();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
	}
}
