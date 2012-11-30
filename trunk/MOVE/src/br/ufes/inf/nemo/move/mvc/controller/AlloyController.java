package br.ufes.inf.nemo.move.mvc.controller;

import br.ufes.inf.nemo.move.mvc.model.AlloyModel;
import br.ufes.inf.nemo.move.mvc.view.AlloyView;

/**
 * @author John Guerson
 */

public class AlloyController {

	@SuppressWarnings("unused")
	private AlloyView alloyview;
	@SuppressWarnings("unused")
	private AlloyModel alloymodel;
	
	public AlloyController(AlloyView alloyview, AlloyModel alloymodel)
	{
		this.alloyview = alloyview;
		this.alloymodel = alloymodel;
	}
}
