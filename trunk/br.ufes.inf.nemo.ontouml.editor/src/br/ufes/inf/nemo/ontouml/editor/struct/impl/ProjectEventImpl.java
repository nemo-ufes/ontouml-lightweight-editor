package br.ufes.inf.nemo.ontouml.editor.struct.impl;

import br.ufes.inf.nemo.ontouml.editor.struct.Project;
import br.ufes.inf.nemo.ontouml.editor.struct.ProjectEvent;


public class ProjectEventImpl implements ProjectEvent {

	private String event;
	private Object data;
	private Project source;
	
	public ProjectEventImpl(String event, Object data, Project source) {
		this.event = event;
		this.data = data;
		this.source = source;
	}

	public String getEvent() {
		return event;
	}

	public Object getData() {
		return data;
	}

	public Project getSource() {
		return source;
	}

}
