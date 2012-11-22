package br.ufes.inf.nemo.move.mvc.model.antipattern.list;

import java.util.ArrayList;

import br.ufes.inf.nemo.move.mvc.model.antipattern.ACAntiPatternModel;
import br.ufes.inf.nemo.move.mvc.model.antipattern.IAAntiPatternModel;
import br.ufes.inf.nemo.move.mvc.model.antipattern.RBOSAntiPatternModel;
import br.ufes.inf.nemo.move.mvc.model.antipattern.RSAntiPatternModel;
import br.ufes.inf.nemo.move.mvc.model.antipattern.RWORAntiPatternModel;
import br.ufes.inf.nemo.move.mvc.model.antipattern.STRAntiPatternModel;



/**
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
	 * Constructor.
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
	 * COnstructor.
	 */
	public AntiPatternListModel()
	{
		
	}	
	
	public ArrayList<ACAntiPatternModel> getACListModel()
	{
		return acListModel;
	}
	
	public ArrayList<RBOSAntiPatternModel> getRBOSListModel()
	{
		return rbosListModel;
	}
	
	public ArrayList<STRAntiPatternModel> getSTRListModel()
	{
		return strListModel;
	}
	
	public ArrayList<RSAntiPatternModel> getRSListModel()
	{
		return rsListModel;
	}
	
	public ArrayList<RWORAntiPatternModel> getRWORListModel()
	{
		return rworListModel;
	}
	
	public ArrayList<IAAntiPatternModel> getIAListModel()
	{
		return iaListModel;
	}	
	
	public void setACListModel(ArrayList<ACAntiPatternModel> acListModel)
	{
		this.acListModel = acListModel;
	}
	
	public void setRBOSListModel(ArrayList<RBOSAntiPatternModel> rbosListModel)
	{
		this.rbosListModel = rbosListModel;
	}
	
	public void setSTRListModel(ArrayList<STRAntiPatternModel>  strListModel)
	{
		this.strListModel = strListModel;
	}
	
	public void setRSListModel(ArrayList<RSAntiPatternModel> rsListModel)
	{
		this.rsListModel = rsListModel;
	}
	
	public void setRWORListModel(ArrayList<RWORAntiPatternModel> rworListModel)
	{
		this.rworListModel = rworListModel;
	}
	
	public void setIAListModel(ArrayList<IAAntiPatternModel> iaListModel)
	{
		this.iaListModel = iaListModel;
	}
}
