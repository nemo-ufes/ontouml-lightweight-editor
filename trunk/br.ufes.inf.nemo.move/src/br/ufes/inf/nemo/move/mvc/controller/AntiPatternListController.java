package br.ufes.inf.nemo.move.mvc.controller;

import br.ufes.inf.nemo.move.mvc.model.AntiPatternListModel;
import br.ufes.inf.nemo.move.mvc.view.AntiPatternListView;

/**
 * @author John Guerson
 */

public class AntiPatternListController {

	@SuppressWarnings("unused")
	private AntiPatternListModel antipatterListModel;
	
	@SuppressWarnings("unused")
	private AntiPatternListView antipatternListView;
	
	/**
	 * COnstructor.
	 * 
	 * @param antipatterListModel
	 * @param antipatternListView
	 */
	public AntiPatternListController(AntiPatternListModel antipatterListModel, AntiPatternListView antipatternListView)
	{
		this.antipatternListView = antipatternListView;
		this.antipatterListModel = antipatterListModel;		
	}
}
