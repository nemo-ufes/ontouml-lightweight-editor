package br.ufes.inf.nemo.move.mvc.model.antipattern.list;

import java.util.ArrayList;

import br.ufes.inf.nemo.move.mvc.model.antipattern.ACAntiPatternModel;
import br.ufes.inf.nemo.move.mvc.model.antipattern.IAAntiPatternModel;
import br.ufes.inf.nemo.move.mvc.model.antipattern.RBOSAntiPatternModel;
import br.ufes.inf.nemo.move.mvc.model.antipattern.RSAntiPatternModel;
import br.ufes.inf.nemo.move.mvc.model.antipattern.RWORAntiPatternModel;
import br.ufes.inf.nemo.move.mvc.model.antipattern.STRAntiPatternModel;

/**
 * 
 * This class represents a AntiPattern List Model.
 * 
 * @author John Guerson
 */

public class AntiPatternListModel {
	
	private ArrayList<ACAntiPatternModel> acListModel = new ArrayList<ACAntiPatternModel>();	
	private ArrayList<RBOSAntiPatternModel> rbosListModel = new ArrayList<RBOSAntiPatternModel>();	
	private ArrayList<STRAntiPatternModel> strListModel = new ArrayList<STRAntiPatternModel>();	
	private ArrayList<RSAntiPatternModel> rsListModel = new ArrayList<RSAntiPatternModel>();
	private ArrayList<RWORAntiPatternModel> rworListModel = new ArrayList<RWORAntiPatternModel>();	
	private ArrayList<IAAntiPatternModel> iaListModel = new ArrayList<IAAntiPatternModel>();
	
	/**
	 * Creates a AntiPattern Model List from a list of AntiPatterns Models.
	 * 
	 * @param acListModel
	 * @param rbosListModel
	 * @param strListModel
	 * @param rsListModel
	 * @param rworListModel
	 * @param iaListModel
	 */
	public AntiPatternListModel (ArrayList<ACAntiPatternModel> acListModel, ArrayList<RBOSAntiPatternModel> rbosListModel,
			ArrayList<STRAntiPatternModel> strListModel, ArrayList<RSAntiPatternModel> rsListModel,
			ArrayList<RWORAntiPatternModel> rworListModel, ArrayList<IAAntiPatternModel> iaListModel
	)
	{
		this.acListModel = acListModel;
		this.rbosListModel = rbosListModel;
		this.strListModel = strListModel;
		this.rsListModel = rsListModel;	
		this.rworListModel = rworListModel;
		this.iaListModel = iaListModel;
	}

	/**
	 * Creates an Empty AntiPattern Model List.
	 */
	public AntiPatternListModel() { }	
	
	/**
	 * Get AC AntiPattern Model List.
	 * @return
	 */
	public ArrayList<ACAntiPatternModel> getACListModel() 
	{
		return acListModel;
	}
	
	/**
	 * Get RBOS AntiPattern Model List.
	 * @return
	 */
	public ArrayList<RBOSAntiPatternModel> getRBOSListModel()
	{
		return rbosListModel;
	}
	
	/**
	 * Get STR AntiPattern Model List.
	 * @return
	 */
	public ArrayList<STRAntiPatternModel> getSTRListModel()
	{
		return strListModel;
	}
	
	/**
	 * Get RS AntiPattern model List.
	 * @return
	 */
	public ArrayList<RSAntiPatternModel> getRSListModel()
	{
		return rsListModel;
	}
	
	/**
	 * Get RWOR AntiPattern model List.
	 * @return
	 */
	public ArrayList<RWORAntiPatternModel> getRWORListModel()
	{
		return rworListModel;
	}
	
	/**
	 * Get IA AntiPattern Model List.
	 * @return
	 */
	public ArrayList<IAAntiPatternModel> getIAListModel()
	{
		return iaListModel;
	}	
	
	/**
	 * Set AC AntiPattern Model List.
	 * 
	 * @param acListModel
	 */
	public void setACListModel(ArrayList<ACAntiPatternModel> acListModel)
	{
		this.acListModel = acListModel;
	}
	
	/**
	 * Set RBOS AntiPattern Model List.
	 * 
	 * @param rbosListModel
	 */
	public void setRBOSListModel(ArrayList<RBOSAntiPatternModel> rbosListModel)
	{
		this.rbosListModel = rbosListModel;
	}
	
	/**
	 * Set STR AntiPAttern Model List.
	 * 
	 * @param strListModel
	 */
	public void setSTRListModel(ArrayList<STRAntiPatternModel>  strListModel)
	{
		this.strListModel = strListModel;
	}
	
	/**
	 * Set RS AntiPAttern Model List.
	 * 
	 * @param rsListModel
	 */
	public void setRSListModel(ArrayList<RSAntiPatternModel> rsListModel)
	{
		this.rsListModel = rsListModel;
	}
	
	/**
	 * Set RWOR AntiPattern Model List.
	 * 
	 * @param rworListModel
	 */
	public void setRWORListModel(ArrayList<RWORAntiPatternModel> rworListModel)
	{
		this.rworListModel = rworListModel;
	}
	
	/**
	 * Set IA AntiPattern Model List.
	 * 
	 * @param iaListModel
	 */
	public void setIAListModel(ArrayList<IAAntiPatternModel> iaListModel)
	{
		this.iaListModel = iaListModel;
	}
}
