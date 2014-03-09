package br.ufes.inf.nemo.tocl.tocl2alloy;

import java.util.ArrayList;

import br.ufes.inf.nemo.ocl.ocl2alloy.OCL2AlloyOption;
import br.ufes.inf.nemo.tocl.parser.TOCLParser;

public class TOCL2AlloyOption extends OCL2AlloyOption {

	public ArrayList<Integer> indexes = new ArrayList<Integer>();
	
	public TOCL2AlloyOption(TOCLParser toclparser)
	{
		super(toclparser);
		indexes=toclparser.getTemporalConstraintsIndexes();
	}

	public TOCL2AlloyOption() 
	{
	
	}
}
