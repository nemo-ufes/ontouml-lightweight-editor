package br.ufes.inf.nemo.assistant.wizard.pageassistant;

import java.util.ArrayList;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;

public abstract class WizardPageAssistant extends WizardPage {

	protected WizardPageAssistant(String pageName) {
		super(pageName);
	}

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
	
	/**
	 * Used to update WizardPageAssistant's components to 
	 * current value of the "global" variables.  
	 * */
	public void setVisible(boolean visible){
		super.setVisible(visible);
	}
	
	/**
	 * Used to prevent the back button perform
	 * */
	@Override
	public void setPreviousPage(IWizardPage page) {
		super.setPreviousPage(null);
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
