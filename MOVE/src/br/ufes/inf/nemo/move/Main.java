package br.ufes.inf.nemo.move;

import java.awt.EventQueue;

import javax.swing.UIManager;

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
			"MOVE");
		}
		
		//Needed for the embedded SWT Browser in Linux systems
		System.setProperty("sun.awt.xembedserver", "true");
		
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					
					//TheFrame frame = new TheFrame();
					//frame.setVisible(true);
					//frame.setLocationRelativeTo(null);
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
	}
}
