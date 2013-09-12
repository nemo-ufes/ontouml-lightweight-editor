package br.ufes.inf.nemo.ocl2alloy;

public class PrettyPrintAlloyOption {
	
	public static enum ConstraintType { FACT, RUN, CHECK };
	public ConstraintType ctype;
	public int global_scope;
	public int world_scope;
	
	public PrettyPrintAlloyOption (ConstraintType ctype, int global_scope, int world_scope)
	{
		this.ctype = ctype;
		this.global_scope = global_scope;
		this.world_scope = world_scope;
	}
}
