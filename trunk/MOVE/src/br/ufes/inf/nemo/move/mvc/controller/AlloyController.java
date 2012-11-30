package br.ufes.inf.nemo.move.mvc.controller;

import br.ufes.inf.nemo.move.mvc.model.AlloyModel;
import br.ufes.inf.nemo.move.mvc.model.OCLModel;
import br.ufes.inf.nemo.move.mvc.view.AlloyView;
import br.ufes.inf.nemo.move.mvc.view.OCLView;

/**
 * @author John Guerson
 */

public class AlloyController {

	private AlloyView alloyview;
	private AlloyModel alloymodel;
	
	public AlloyController(AlloyView alloyview, AlloyModel alloymodel)
	{
		this.alloyview = alloyview;
		this.alloymodel = alloymodel;
	}
}
