package br.ufes.inf.nemo.ontouml.editor.struct;

import java.util.EventListener;

public interface ProjectListener extends EventListener {

	void notifyEvent(ProjectEvent projectEvent);
	
}
