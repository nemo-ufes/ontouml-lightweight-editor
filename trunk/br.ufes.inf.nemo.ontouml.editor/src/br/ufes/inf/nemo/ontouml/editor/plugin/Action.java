package br.ufes.inf.nemo.ontouml.editor.plugin;

import javax.swing.JButton;

public interface Action extends ApplicationPlugin {
	
	JButton getToolbarButton();
	
	void registerApplicationHandler(ApplicationHandler handler);
	
}
