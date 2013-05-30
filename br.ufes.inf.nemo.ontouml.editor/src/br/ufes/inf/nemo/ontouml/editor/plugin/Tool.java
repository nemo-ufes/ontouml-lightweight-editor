package br.ufes.inf.nemo.ontouml.editor.plugin;

import javax.swing.JComponent;

public interface Tool extends ApplicationPlugin {

	JComponent getUIComponent();
	
	void registerApplicationHandler(ApplicationHandler handler);

}
