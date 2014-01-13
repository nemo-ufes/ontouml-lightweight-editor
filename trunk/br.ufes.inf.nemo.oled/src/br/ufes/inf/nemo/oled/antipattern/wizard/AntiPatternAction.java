package br.ufes.inf.nemo.oled.antipattern.wizard;

import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;

public abstract class AntiPatternAction <T extends AntipatternOccurrence> {
	
	public T ap;
	public abstract void run();	

	public AntiPatternAction(T ap)
	{
		this.ap = ap;
	}
	
	public T getAp()
	{
		return ap;
	}
}
