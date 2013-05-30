package br.ufes.inf.nemo.ontouml.editor.plugin;

import java.util.EventListener;

public interface EditorListener extends EventListener {

	void notifyEvent(EditorEvent event);
	
}
