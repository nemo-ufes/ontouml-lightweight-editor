package br.ufes.inf.nemo.ocl.pivot.ocl2alloy;

import java.util.ArrayList;

public class PivotPrettyPrintAlloyOption {
	
	public static enum ConstraintType { FACT, RUN, CHECK };
	public ConstraintType ctype;
	public int global_scope;
	public int world_scope;
	
	public PivotPrettyPrintAlloyOption (ConstraintType ctype, int global_scope, int world_scope)
	{		
		this.ctype = ctype;
		this.global_scope = global_scope;
		this.world_scope = world_scope;		
	}
	
	public static ArrayList<PivotPrettyPrintAlloyOption> createListOfOptions(int n)
	{
		ArrayList<PivotPrettyPrintAlloyOption> list = new ArrayList<PivotPrettyPrintAlloyOption>();
		for (int i=0;i<n;i++)
		{
			list.add(new PivotPrettyPrintAlloyOption(PivotPrettyPrintAlloyOption.ConstraintType.FACT,10,1));
		}		
		return list;
	}
}
