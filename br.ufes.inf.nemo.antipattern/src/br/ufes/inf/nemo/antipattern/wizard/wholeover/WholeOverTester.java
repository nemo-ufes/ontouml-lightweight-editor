package br.ufes.inf.nemo.antipattern.wizard.wholeover;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.wholeover.WholeOverAntipattern;
import br.ufes.inf.nemo.antipattern.wholeover.WholeOverOccurrence;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;


public class WholeOverTester {

	public static void main(String[] args) throws Exception {
			
		FilenameFilter filter = new FilenameFilter() {
		    public boolean accept(File dir, String name) {
		        return name.endsWith(".refontouml");
		    }
		};
	
		String folderPath = "models\\refontouml\\";
		
		System.out.println("("+getCurrentDateAndTime() +") Opening folder '"+folderPath+"'");
		File folder = new File(folderPath);
		
		System.out.println("("+getCurrentDateAndTime() +") Valid refontouml files in folder: "+folder.listFiles(filter).length);
		
//		ArrayList<WholeOverOccurrence> 	var0 = new ArrayList<WholeOverOccurrence>(); 
//										var1 = new ArrayList<RelSpecOccurrence>(), 
//										var2 = new ArrayList<RelSpecOccurrence>(), 
//										var3 = new ArrayList<RelSpecOccurrence>(), 
//										var4 = new ArrayList<RelSpecOccurrence>(), 
//										var5 = new ArrayList<RelSpecOccurrence>(), 
//										var6 = new ArrayList<RelSpecOccurrence>(), 
//										var7 = new ArrayList<RelSpecOccurrence>();
		
		for(File file : folder.listFiles(filter)) {
			String antiPatternName;
			String model = file.getName();
			WholeOverAntipattern ap;
			
			System.out.print("("+getCurrentDateAndTime() + ") " + model + ": ");
			System.out.println("Loading model...");
			
			OntoUMLParser parser = new OntoUMLParser(folderPath+model);
			
			System.out.print("("+getCurrentDateAndTime() + ") " + model + ": ");
			System.out.println("Model loaded!");
		
			//WHOLEOVER
			antiPatternName = WholeOverAntipattern.getAntipatternInfo().getAcronym();
			ap = new WholeOverAntipattern(parser);
			runAntipattern(antiPatternName, model, ap);
			
			for (WholeOverOccurrence wholeOverOccurrence : ap.getOccurrences()) {
				WizardDialog wizardDialog = new WizardDialog(new Shell(SWT.ON_TOP),new WholeOverWizard(wholeOverOccurrence));
				if (wizardDialog.open() == Window.OK) {
					System.out.println("Ok pressed");
				} 
				else {
				    System.out.println("Cancel pressed");
				}
			}
			
			
		 
		}
		
	}
	
	@SuppressWarnings("rawtypes")
	public static int runAntipattern (String antiPatternAcronym, String model,Antipattern ap) throws IOException {
		System.out.print("("+getCurrentDateAndTime() + ") " + model + ": ");
		System.out.println(antiPatternAcronym+": Analyzing model...");
				
		ap.identify();
		int occurrences = ap.getOccurrences().size();
		
		System.out.print("("+getCurrentDateAndTime() + ") " + model + ": ");
		System.out.println(antiPatternAcronym+": "+occurrences+" occurences found!");
				
		return occurrences;
	}
	
	
	public static String getCurrentDateAndTime()
	{
		String result = new String();
	   
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	    //get current date time with Date()
	    Date date = new Date();
	    result += dateFormat.format(date);
	 			   
	    return result;
	}

}
