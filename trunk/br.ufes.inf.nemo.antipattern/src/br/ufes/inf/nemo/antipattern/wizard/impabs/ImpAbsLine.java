package br.ufes.inf.nemo.antipattern.wizard.impabs;

import RefOntoUML.Classifier;

public abstract class ImpAbsLine {
	
	private Classifier source, target;

	public ImpAbsLine(Classifier source, Classifier target) {
		if(source!=null)
			this.source = source;
		else
			throw new NullPointerException();
		
		if(target!=null)
			this.target = target;
		else
			throw new NullPointerException();
	}
	
	public Classifier getSource() {
		return source;
	}

	public void setSource(Classifier source) {
		this.source = source;
	}

	public Classifier getTarget() {
		return target;
	}

	public void setTarget(Classifier target) {
		this.target = target;
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof MultiplicityLine){
			MultiplicityLine line = (MultiplicityLine)o;
			return (line.getSource().equals(source) && line.getTarget().equals(target));
			
		}
		else
			return false;
	}
}
