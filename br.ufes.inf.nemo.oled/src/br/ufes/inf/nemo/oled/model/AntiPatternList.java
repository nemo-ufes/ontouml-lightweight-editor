package br.ufes.inf.nemo.oled.model;

import java.util.ArrayList;

import br.ufes.inf.nemo.antipattern.asscyc.AssCycAntipattern;
import br.ufes.inf.nemo.antipattern.binover.BinOverAntipattern;
import br.ufes.inf.nemo.antipattern.impabs.ImpAbsAntipattern;
import br.ufes.inf.nemo.antipattern.rbos.RBOSAntiPattern;
import br.ufes.inf.nemo.antipattern.relcomp.RelCompAntipattern;
import br.ufes.inf.nemo.antipattern.relover.RelOverAntipattern;
import br.ufes.inf.nemo.antipattern.relrig.RelRigAntipattern;
import br.ufes.inf.nemo.antipattern.relspec.RSAntiPattern;
import br.ufes.inf.nemo.antipattern.reprel.RepRelAntipattern;


/**
 * 
 * This class represents a AntiPattern List Model.
 * 
 * @author John Guerson
 */

public class AntiPatternList {
	
	private ArrayList<AssCycAntipattern> acListModel = new ArrayList<AssCycAntipattern>();	
	private ArrayList<RBOSAntiPattern> rbosListModel = new ArrayList<RBOSAntiPattern>();	
	private ArrayList<BinOverAntipattern> strListModel = new ArrayList<BinOverAntipattern>();	
	private ArrayList<RSAntiPattern> rsListModel = new ArrayList<RSAntiPattern>();
	private ArrayList<RelOverAntipattern> rworListModel = new ArrayList<RelOverAntipattern>();	
	private ArrayList<ImpAbsAntipattern> iaListModel = new ArrayList<ImpAbsAntipattern>();
	private ArrayList<RelCompAntipattern> ssrListModel = new ArrayList<RelCompAntipattern>();
	private ArrayList<RelRigAntipattern> rwrtListModel = new ArrayList<RelRigAntipattern>();
	private ArrayList<RepRelAntipattern> triListModel = new ArrayList<RepRelAntipattern>();
	
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
	public AntiPatternList (ArrayList<AssCycAntipattern> acListModel, ArrayList<RBOSAntiPattern> rbosListModel,
			ArrayList<BinOverAntipattern> strListModel, ArrayList<RSAntiPattern> rsListModel,
			ArrayList<RelOverAntipattern> rworListModel, ArrayList<ImpAbsAntipattern> iaListModel, 
			ArrayList<RelCompAntipattern> ssrListModel, ArrayList<RelRigAntipattern> rwrtListModel,
			ArrayList<RepRelAntipattern> triListModel
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
	public ArrayList<AssCycAntipattern> getACListModel() 
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
	public ArrayList<BinOverAntipattern> getSTRListModel()
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
	public ArrayList<RelOverAntipattern> getRWORListModel()
	{
		return rworListModel;
	}
	
	/**
	 * Get IA AntiPattern Model List.
	 * @return
	 */
	public ArrayList<ImpAbsAntipattern> getIAListModel()
	{
		return iaListModel;
	}	

	/**
	 * Get SSR AntiPattern Model List.
	 * @return
	 */
	public ArrayList<RelCompAntipattern> getSSRListModel()
	{
		return ssrListModel;
	}
	
	/**
	 * Get RWRT AntiPattern Model List.
	 * @return
	 */
	public ArrayList<RelRigAntipattern> getRWRTListModel()
	{
		return rwrtListModel;
	}
	
	/**
	 * Get TRI AntiPattern Model List.
	 * @return
	 */
	public ArrayList<RepRelAntipattern> getTRIListModel()
	{
		return triListModel;
	}
	
	/**
	 * Set AC AntiPattern Model List.
	 * 
	 * @param acListModel
	 */
	public void setACListModel(ArrayList<AssCycAntipattern> acListModel)
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
	public void setSTRListModel(ArrayList<BinOverAntipattern>  strListModel)
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
	public void setRWORListModel(ArrayList<RelOverAntipattern> rworListModel)
	{
		this.rworListModel = rworListModel;
	}
	
	/**
	 * Set IA AntiPattern Model List.
	 * 
	 * @param iaListModel
	 */
	public void setIAListModel(ArrayList<ImpAbsAntipattern> iaListModel)
	{
		this.iaListModel = iaListModel;
	}
	
	/**
	 * Set SSR AntiPattern Model List.
	 * 
	 * @param ssrListModel
	 */
	public void setSSRListModel(ArrayList<RelCompAntipattern> ssrListModel)
	{
		this.ssrListModel = ssrListModel;
	}
	
	/**
	 * Set RWRT AntiPattern Model List.
	 * 
	 * @param rwrtListModel
	 */
	public void setRWRTListModel(ArrayList<RelRigAntipattern> rwrtListModel)
	{
		this.rwrtListModel = rwrtListModel;
	}
	
	/**
	 * Set TRI AntiPattern Model List.
	 * 
	 * @param triListModel
	 */
	public void setTRIListModel(ArrayList<RepRelAntipattern> triListModel)
	{
		this.triListModel = triListModel;
	}
	
}
