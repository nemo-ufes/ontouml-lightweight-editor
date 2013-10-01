package br.ufes.inf.nemo.ocl2alloy.pivot;

import java.util.ArrayList;

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
	
	public static ArrayList<PrettyPrintAlloyOption> createListOfOptions(int n)
	{
		ArrayList<PrettyPrintAlloyOption> list = new ArrayList<PrettyPrintAlloyOption>();
		for (int i=0;i<n;i++)
		{
			list.add(new PrettyPrintAlloyOption(PrettyPrintAlloyOption.ConstraintType.FACT,10,1));
		}
		return list;
	}
}
