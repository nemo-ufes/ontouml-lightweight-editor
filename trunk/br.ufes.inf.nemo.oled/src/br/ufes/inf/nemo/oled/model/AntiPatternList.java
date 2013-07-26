package br.ufes.inf.nemo.oled.model;

import java.util.ArrayList;

import br.ufes.inf.nemo.antipattern.ACAntiPattern;
import br.ufes.inf.nemo.antipattern.IAAntiPattern;
import br.ufes.inf.nemo.antipattern.RBOSAntiPattern;
import br.ufes.inf.nemo.antipattern.RSAntiPattern;
import br.ufes.inf.nemo.antipattern.RWORAntiPattern;
import br.ufes.inf.nemo.antipattern.SSRAntiPattern;
import br.ufes.inf.nemo.antipattern.STRAntiPattern;
import br.ufes.inf.nemo.antipattern.rwrt.RWRTAntiPattern;
import br.ufes.inf.nemo.antipattern.tri.TRIAntiPattern;


/**
 * 
 * This class represents a AntiPattern List Model.
 * 
 * @author John Guerson
 */

public class AntiPatternList {
	
	private ArrayList<ACAntiPattern> acListModel = new ArrayList<ACAntiPattern>();	
	private ArrayList<RBOSAntiPattern> rbosListModel = new ArrayList<RBOSAntiPattern>();	
	private ArrayList<STRAntiPattern> strListModel = new ArrayList<STRAntiPattern>();	
	private ArrayList<RSAntiPattern> rsListModel = new ArrayList<RSAntiPattern>();
	private ArrayList<RWORAntiPattern> rworListModel = new ArrayList<RWORAntiPattern>();	
	private ArrayList<IAAntiPattern> iaListModel = new ArrayList<IAAntiPattern>();
	private ArrayList<SSRAntiPattern> ssrListModel = new ArrayList<SSRAntiPattern>();
	private ArrayList<RWRTAntiPattern> rwrtListModel = new ArrayList<RWRTAntiPattern>();
	private ArrayList<TRIAntiPattern> triListModel = new ArrayList<TRIAntiPattern>();
	
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
	public AntiPatternList (ArrayList<ACAntiPattern> acListModel, ArrayList<RBOSAntiPattern> rbosListModel,
			ArrayList<STRAntiPattern> strListModel, ArrayList<RSAntiPattern> rsListModel,
			ArrayList<RWORAntiPattern> rworListModel, ArrayList<IAAntiPattern> iaListModel, 
			ArrayList<SSRAntiPattern> ssrListModel, ArrayList<RWRTAntiPattern> rwrtListModel,
			ArrayList<TRIAntiPattern> triListModel
	)
	{
		this.acListModel = acListModel;
		this.rbosListModel = rbosListModel;
		this.strListModel = strListModel;
		this.rsListModel = rsListModel;	
		this.rworListModel = rworListModel;
		this.iaListModel = iaListModel;
		this.ssrListModel = ssrListModel;
		this.rwrtListModel = rwrtListModel;
		this.triListModel = triListModel;
	}

	/**
	 * Creates an Empty AntiPattern Model List.
	 */
	public AntiPatternList() { }	
	
	/**
	 * Get AC AntiPattern Model List.
	 * @return
	 */
	public ArrayList<ACAntiPattern> getACListModel() 
	{
		return acListModel;
	}
	
	/**
	 * Get RBOS AntiPattern Model List.
	 * @return
	 */
	public ArrayList<RBOSAntiPattern> getRBOSListModel()
	{
		return rbosListModel;
	}
	
	/**
	 * Get STR AntiPattern Model List.
	 * @return
	 */
	public ArrayList<STRAntiPattern> getSTRListModel()
	{
		return strListModel;
	}
	
	/**
	 * Get RS AntiPattern model List.
	 * @return
	 */
	public ArrayList<RSAntiPattern> getRSListModel()
	{
		return rsListModel;
	}
	
	/**
	 * Get RWOR AntiPattern model List.
	 * @return
	 */
	public ArrayList<RWORAntiPattern> getRWORListModel()
	{
		return rworListModel;
	}
	
	/**
	 * Get IA AntiPattern Model List.
	 * @return
	 */
	public ArrayList<IAAntiPattern> getIAListModel()
	{
		return iaListModel;
	}	

	/**
	 * Get SSR AntiPattern Model List.
	 * @return
	 */
	public ArrayList<SSRAntiPattern> getSSRListModel()
	{
		return ssrListModel;
	}
	
	/**
	 * Get RWRT AntiPattern Model List.
	 * @return
	 */
	public ArrayList<RWRTAntiPattern> getRWRTListModel()
	{
		return rwrtListModel;
	}
	
	/**
	 * Get TRI AntiPattern Model List.
	 * @return
	 */
	public ArrayList<TRIAntiPattern> getTRIListModel()
	{
		return triListModel;
	}
	
	/**
	 * Set AC AntiPattern Model List.
	 * 
	 * @param acListModel
	 */
	public void setACListModel(ArrayList<ACAntiPattern> acListModel)
	{
		this.acListModel = acListModel;
	}
	
	/**
	 * Set RBOS AntiPattern Model List.
	 * 
	 * @param rbosListModel
	 */
	public void setRBOSListModel(ArrayList<RBOSAntiPattern> rbosListModel)
	{
		this.rbosListModel = rbosListModel;
	}
	
	/**
	 * Set STR AntiPAttern Model List.
	 * 
	 * @param strListModel
	 */
	public void setSTRListModel(ArrayList<STRAntiPattern>  strListModel)
	{
		this.strListModel = strListModel;
	}
	
	/**
	 * Set RS AntiPAttern Model List.
	 * 
	 * @param rsListModel
	 */
	public void setRSListModel(ArrayList<RSAntiPattern> rsListModel)
	{
		this.rsListModel = rsListModel;
	}
	
	/**
	 * Set RWOR AntiPattern Model List.
	 * 
	 * @param rworListModel
	 */
	public void setRWORListModel(ArrayList<RWORAntiPattern> rworListModel)
	{
		this.rworListModel = rworListModel;
	}
	
	/**
	 * Set IA AntiPattern Model List.
	 * 
	 * @param iaListModel
	 */
	public void setIAListModel(ArrayList<IAAntiPattern> iaListModel)
	{
		this.iaListModel = iaListModel;
	}
	
	/**
	 * Set SSR AntiPattern Model List.
	 * 
	 * @param ssrListModel
	 */
	public void setSSRListModel(ArrayList<SSRAntiPattern> ssrListModel)
	{
		this.ssrListModel = ssrListModel;
	}
	
	/**
	 * Set RWRT AntiPattern Model List.
	 * 
	 * @param rwrtListModel
	 */
	public void setRWRTListModel(ArrayList<RWRTAntiPattern> rwrtListModel)
	{
		this.rwrtListModel = rwrtListModel;
	}
	
	/**
	 * Set TRI AntiPattern Model List.
	 * 
	 * @param triListModel
	 */
	public void setTRIListModel(ArrayList<TRIAntiPattern> triListModel)
	{
		this.triListModel = triListModel;
	}
	
}
