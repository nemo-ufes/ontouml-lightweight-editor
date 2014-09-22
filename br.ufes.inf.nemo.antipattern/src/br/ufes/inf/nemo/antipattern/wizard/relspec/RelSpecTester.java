package br.ufes.inf.nemo.antipattern.wizard.relspec;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.relspec.RelSpecAntipattern;
import br.ufes.inf.nemo.antipattern.relspec.RelSpecOccurrence;


public class RelSpecTester {

	public static void main(String[] args) throws Exception {
			
		FilenameFilter filter = new FilenameFilter() {
		    public boolean accept(File dir, String name) {
		        return name.endsWith(".refontouml");
		    }
		};
	
		String folderPath = "RefOntoUMLModels/";
		
		System.out.println("("+getCurrentDateAndTime() +") Opening folder '"+folderPath+"'");
		File folder = new File(folderPath);
		
		System.out.println("("+getCurrentDateAndTime() +") Valid refontouml files in folder: "+folder.listFiles(filter).length);
		
		ArrayList<RelSpecOccurrence> 	var0 = new ArrayList<RelSpecOccurrence>(), 
										var1 = new ArrayList<RelSpecOccurrence>(), 
										var2 = new ArrayList<RelSpecOccurrence>(), 
										var3 = new ArrayList<RelSpecOccurrence>(), 
										var4 = new ArrayList<RelSpecOccurrence>(), 
										var5 = new ArrayList<RelSpecOccurrence>(), 
										var6 = new ArrayList<RelSpecOccurrence>(), 
										var7 = new ArrayList<RelSpecOccurrence>();
		
		for(File file : folder.listFiles(filter)) {
			String antiPatternName;
			String model = file.getName();
			RelSpecAntipattern ap;
			
			System.out.print("("+getCurrentDateAndTime() + ") " + model + ": ");
			System.out.println("Loading model...");
			
			OntoUMLParser parser = new OntoUMLParser(folderPath+model);
			
			System.out.print("("+getCurrentDateAndTime() + ") " + model + ": ");
			System.out.println("Model loaded!");
		
			//RELSPEC: Relation Specialization
			antiPatternName = RelSpecAntipattern.getAntipatternInfo().getAcronym();
			ap = new RelSpecAntipattern(parser);
			runAntipattern(antiPatternName, model, ap);
			
			int i = 1;
			for (RelSpecOccurrence rs : ap.getOccurrences()) {
				System.out.print(i+": ");
				if(rs.isVariation1()){
					System.out.println("Variation 1");
					var1.add(rs);
				}
				if(rs.isVariation2()){
					System.out.println("Variation 2");
					var2.add(rs);
				}
				if(rs.isVariation3()){
					System.out.println("Variation 3");
					var3.add(rs);
				}
				if(rs.isVariation4()){
					System.out.println("Variation 4");
					var4.add(rs);
				}
				if(rs.isVariation5()){
					System.out.println("Variation 5");
					var5.add(rs);
				}
				if(rs.isVariation6()){
					System.out.println("Variation 6");
					var6.add(rs);
				}
				if(rs.isVariation7()){
					System.out.println("Variation 7");
					var7.add(rs);
				}
				
				if (!rs.isVariation1() && !rs.isVariation2() && !rs.isVariation3() && !rs.isVariation4() && !rs.isVariation5() &&!rs.isVariation6() && !rs.isVariation7()) {
					System.out.println("NO VARIATION!============================");
					var0.add(rs);
				}
				
				i++;
			}
		}
		
		System.out.println("#VAR1: "+var1.size()+"\n#VAR2: "+var2.size()+"\n#VAR3: "+var3.size()+"\n#VAR4: "+var4.size()+"\n#VAR5: "+var5.size()+"\n#VAR6: "+var6.size()+"\n#VAR7: "+var7.size()+"\n#NO VAR: "+var0.size());
		
		/*for (RelSpecOccurrence rs : var0) {
			System.out.println("G SOURCE: "+rs.getGeneralSource());
			System.out.println("S SOURCE: "+rs.getSpecificSource());
			System.out.println("G TARGET: "+rs.getGeneralTarget());
			System.out.println("S TARGET: "+rs.getSpecificTarget());
			System.out.println();
		}*/
		if(var1.size()>0) {
			System.out.println("VAR 1");
			var1.get(0).generateOCL(RelSpecOccurrence.OperationType.SUBSET);
			var1.get(0).generateOCL(RelSpecOccurrence.OperationType.REDEFINE);
			var1.get(0).generateOCL(RelSpecOccurrence.OperationType.DISJOINT);
			System.out.println(var1.get(0).getFix().getRulesString());
		} 
		
		if(var2.size()>0) {
			System.out.println("VAR 2");
		var2.get(0).generateOCL(RelSpecOccurrence.OperationType.SUBSET);
		var2.get(0).generateOCL(RelSpecOccurrence.OperationType.REDEFINE);
		var2.get(0).generateOCL(RelSpecOccurrence.OperationType.DISJOINT);
		System.out.println(var2.get(0).getFix().getRulesString());
		}
		
		if(var3.size()>0) {
			System.out.println("VAR 3");
		var3.get(0).generateOCL(RelSpecOccurrence.OperationType.SUBSET);
		var3.get(0).generateOCL(RelSpecOccurrence.OperationType.REDEFINE);
		var3.get(0).generateOCL(RelSpecOccurrence.OperationType.DISJOINT);
		System.out.println(var3.get(0).getFix().getRulesString());
		}
		
		if(var4.size()>0) {
			System.out.println("VAR 4");
		var4.get(0).generateOCL(RelSpecOccurrence.OperationType.SUBSET);
		var4.get(0).generateOCL(RelSpecOccurrence.OperationType.REDEFINE);
		var4.get(0).generateOCL(RelSpecOccurrence.OperationType.DISJOINT);
		System.out.println(var4.get(0).getFix().getRulesString());
		}
		
		if(var5.size()>0) {
			System.out.println("VAR 5");
		var5.get(0).generateOCL(RelSpecOccurrence.OperationType.SUBSET);
		var5.get(0).generateOCL(RelSpecOccurrence.OperationType.REDEFINE);
		var5.get(0).generateOCL(RelSpecOccurrence.OperationType.DISJOINT);
		System.out.println(var5.get(0).getFix().getRulesString());
		}
		
		if(var6.size()>0) {
			System.out.println("VAR 6");
		var6.get(0).generateOCL(RelSpecOccurrence.OperationType.SUBSET);
		var6.get(0).generateOCL(RelSpecOccurrence.OperationType.REDEFINE);
		var6.get(0).generateOCL(RelSpecOccurrence.OperationType.DISJOINT);
		}
		
		if(var7.size()>0) {
			System.out.println("VAR 7");
		var7.get(0).generateOCL(RelSpecOccurrence.OperationType.SUBSET);
		var7.get(0).generateOCL(RelSpecOccurrence.OperationType.REDEFINE);
		var7.get(0).generateOCL(RelSpecOccurrence.OperationType.DISJOINT);
		}
		WizardDialog wizardDialog = new WizardDialog(new Shell(SWT.ON_TOP),
			    new RelSpecWizard(var1.get(5)));
			    if (wizardDialog.open() == Window.OK) {
			      System.out.println("Ok pressed");
			    } else {
			      System.out.println("Cancel pressed");
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
