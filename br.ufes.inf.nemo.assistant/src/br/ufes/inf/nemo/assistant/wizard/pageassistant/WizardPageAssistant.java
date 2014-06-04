package br.ufes.inf.nemo.assistant.wizard.pageassistant;

import java.util.ArrayList;

import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;

public abstract class WizardPageAssistant extends WizardPage {

	protected WizardPageAssistant(String pageName) {
		super(pageName);
	}

	public WizardPageAssistant(){
		super("");
	}

	protected String pageDescription = new String();
	
	@Override
	public void setDescription(String description) {
		pageDescription = description;
		super.setDescription(description);
	}
	
	/**
	 * 
	 */
	protected static final long serialVersionUID = 1L;

	public abstract void init();

	public boolean nextTrue(){
		return false;
	}

	public boolean nextFalse(){
		return false;
	}

	public boolean next(){
		return false;
	}

	/**
	 * Used to validate form and to authorize next button
	 * */
	@Override
	public abstract boolean canFlipToNextPage();

	@Override
	public abstract String toString();

	protected boolean isEndPage = false;

	public void setEndPage(boolean b){
		isEndPage = b;
	}

	protected void enableFinish(boolean b){
		if(isEndPage){
			IWizard w = this.getWizard();
			IWizardPage [] pages = w.getPages();
			for(int i = 0; i < pages.length; i++){
				((WizardPage) pages[i]).setPageComplete(b);
			}
		}
		this.setPageComplete(b);  // this is needed.  
	}

	/**
	 * Used to update WizardPageAssistant's components to 
	 * current value of the "global" variables.  
	 * */
	public void setVisible(boolean visible){
		super.setVisible(visible);
	}

	@Override
	public IWizardPage getPreviousPage() {
		return null;
	}

	/**
	 * Variables used for all pages
	 * Are statics to be used for any page at any time
	 * */

	private static String currentClass = "";
	private static String currentStereotype = "";
	private static ArrayList<String[]> classList;

	public static String getCurrentClass() {
		return currentClass;
	}

	public static void setCurrentClass(String currentClass) {
		WizardPageAssistant.currentClass = currentClass;
	}

	public static String getCurrentStereotype() {
		return currentStereotype;
	}

	public static void setCurrentStereotype(String currentStereotype) {
		WizardPageAssistant.currentStereotype = currentStereotype;
	}

	public static void setClassList(ArrayList<String[]> classList){
		WizardPageAssistant.classList = classList;
	}

	public static ArrayList<String[]> getClassList(){
		return WizardPageAssistant.classList;
	}
}
