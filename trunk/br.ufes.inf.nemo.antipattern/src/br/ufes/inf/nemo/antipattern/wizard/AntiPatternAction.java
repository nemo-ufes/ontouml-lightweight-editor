package br.ufes.inf.nemo.antipattern.wizard;

import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;

public abstract class AntiPatternAction <T extends AntipatternOccurrence> {
	
	protected T ap;
	protected Enum<?> code;
	
	public abstract void run();	

	public AntiPatternAction(T ap)
	{
		this.ap = ap;
	}
	
	public T getAp()
	{
		return ap;
	}

	public Enum<?> getCode(){
		return code;
	}
}
