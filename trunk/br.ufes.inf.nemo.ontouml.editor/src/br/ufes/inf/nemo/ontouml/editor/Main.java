package br.ufes.inf.nemo.ontouml.editor;

import java.awt.Color;
import java.awt.EventQueue;
import java.io.File;

import javax.swing.UIManager;

import net.xeoh.plugins.base.PluginManager;
import net.xeoh.plugins.base.impl.PluginManagerFactory;
import br.ufes.inf.nemo.ontouml.editor.ui.MainFrame;
import br.ufes.inf.nemo.ontouml.editor.util.Logger;

public class Main {

	/**
	 * Application entry point
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Mac OS X settings
		if (System.getProperty("mrj.version") != null) {
			System.setProperty("apple.laf.useScreenMenuBar", "true");
			System.setProperty("com.apple.eawt.CocoaComponent.CompatibilityMode", "false");
			System.setProperty("com.apple.mrj.application.apple.menu.about.name", "OntoUML Editor");
		}
		
		//Needed for the embedded SWT Browser in Linux systems
		System.setProperty("sun.awt.xembedserver", "true");
		
		//Builds the application frame
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
										
					//Loads plugins from the plugins directory
					//TESTME Remove the plugins directory and see what happens...
					PluginManager pm = PluginManagerFactory.createPluginManager();
					pm.addPluginsFrom(new File("plugins/").toURI());
					//pm.addPluginsFrom(new File("bin/").toURI()); internal plugins 
						
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					UIManager.put("TabbedPane.focus", new Color(0, 0, 0, 0));
					
					MainFrame frame = new MainFrame(pm);
					frame.loadPlugableComponents();
					
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					
				} catch (Exception ex) {
					Logger.logException(ex);
				}
			}
		});
	}
}
