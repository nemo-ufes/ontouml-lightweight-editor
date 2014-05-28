package br.ufes.inf.nemo.ontouml2text.glossaryExporter;

import java.io.*;
import java.util.HashMap;
import java.util.Map.Entry;

import br.ufes.inf.nemo.ontouml2text.descriptionSpace.DescriptionCategory;

public class HtmlGlossaryExporter extends GlossaryExporter {
	private HashMap<String, PrintWriter> alphabetHash = new HashMap<String, PrintWriter>();
	private PrintWriter index;
	private PrintWriter allDescriptionsFile;
	private PrintWriter htmlLetter;
	private String initialLetter;	
	private String outputName;
	private String outputDirectory;
	private String title;
	private String subtitle;

	public HtmlGlossaryExporter(String outputName, String outputDirectory, String title, String subtitle) {
		this.outputName = outputName;
		this.outputDirectory = outputDirectory;
		this.title = title;
		this.subtitle = subtitle;
	}
	
	public void initilizeExportFile(){
		try {
			File general = new File(this.outputDirectory+"/"+this.outputName);
			general.mkdir();
			
			File alphabet = new File(this.outputDirectory+"/"+this.outputName+"/"+"all/");
			alphabet.mkdir();
			
			File css = new File(this.outputDirectory+"/"+this.outputName+"/"+"all/css");
			css.mkdir();
			
			index = new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"this.outputName"+".html")));
			allDescriptionsFile = new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"all/all"+".html")));
			 
			createHeaderFile(index,this.title,"all/","all/css/");		// create alphabet index
			index.write(
					"        <p style=\"font-size:70px; color:#307E06\" align=center>"+this.title+"</p>\n"+
					"        <p style=\"font-size:30px; color:#307E06\" align=center>"+this.subtitle+"</p>\n"
					);
			createFooterFile(index);
			
			createHeaderFile(allDescriptionsFile,this.title,"","css/"); // create all descriptions file
			
			createCssFile();				// create css style file
			populateHashMap(alphabetHash);	// create html's letters
			
		} catch (IOException e) {
			System.out.println("Error: The output file could not be created.");
			e.printStackTrace();
		}
	}
	
	public void populateHashMap(HashMap<String, PrintWriter> alphabetHash){
		try {
			alphabetHash.put("A", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"all/"+"letterA"+".html"))));
			createHeaderFile(alphabetHash.get("A"),"Search letter [ A ]","","css/");
			alphabetHash.put("B", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"all/"+"letterB"+".html"))));
			createHeaderFile(alphabetHash.get("B"),"Search letter [ B ]","","css/");
			alphabetHash.put("C", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"all/"+"letterC"+".html"))));
			createHeaderFile(alphabetHash.get("C"),"Search letter [ C ]","","css/");
			alphabetHash.put("D", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"all/"+"letterD"+".html"))));
			createHeaderFile(alphabetHash.get("D"),"Search letter [ D ]","","css/");
			alphabetHash.put("E", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"all/"+"letterE"+".html"))));
			createHeaderFile(alphabetHash.get("E"),"Search letter [ E ]","","css/");
			alphabetHash.put("F", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"all/"+"letterF"+".html"))));
			createHeaderFile(alphabetHash.get("F"),"Search letter [ F ]","","css/");
			alphabetHash.put("G", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"all/"+"letterG"+".html"))));
			createHeaderFile(alphabetHash.get("G"),"Search letter [ G ]","","css/");
			alphabetHash.put("H", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"all/"+"letterH"+".html"))));
			createHeaderFile(alphabetHash.get("H"),"Search letter [ H ]","","css/");
			alphabetHash.put("I", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"all/"+"letterI"+".html"))));
			createHeaderFile(alphabetHash.get("I"),"Search letter [ I ]","","css/");
			alphabetHash.put("J", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"all/"+"letterJ"+".html"))));
			createHeaderFile(alphabetHash.get("J"),"Search letter [ J ]","","css/");
			alphabetHash.put("K", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"all/"+"letterK"+".html"))));
			createHeaderFile(alphabetHash.get("K"),"Search letter [ K ]","","css/");
			alphabetHash.put("L", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"all/"+"letterL"+".html"))));
			createHeaderFile(alphabetHash.get("L"),"Search letter [ L ]","","css/");
			alphabetHash.put("M", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"all/"+"letterM"+".html"))));
			createHeaderFile(alphabetHash.get("M"),"Search letter [ M ]","","css/");
			alphabetHash.put("N", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"all/"+"letterN"+".html"))));
			createHeaderFile(alphabetHash.get("N"),"Search letter [ N ]","","css/");
			alphabetHash.put("O", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"all/"+"letterO"+".html"))));
			createHeaderFile(alphabetHash.get("O"),"Search letter [ O ]","","css/");
			alphabetHash.put("P", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"all/"+"letterP"+".html"))));
			createHeaderFile(alphabetHash.get("P"),"Search letter [ P ]","","css/");
			alphabetHash.put("Q", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"all/"+"letterQ"+".html"))));
			createHeaderFile(alphabetHash.get("Q"),"Search letter [ Q ]","","css/");
			alphabetHash.put("R", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"all/"+"letterR"+".html"))));
			createHeaderFile(alphabetHash.get("R"),"Search letter [ R ]","","css/");
			alphabetHash.put("S", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"all/"+"letterS"+".html"))));
			createHeaderFile(alphabetHash.get("S"),"Search letter [ S ]","","css/");
			alphabetHash.put("T", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"all/"+"letterT"+".html"))));
			createHeaderFile(alphabetHash.get("T"),"Search letter [ T ]","","css/");
			alphabetHash.put("U", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"all/"+"letterU"+".html"))));
			createHeaderFile(alphabetHash.get("U"),"Search letter [ U ]","","css/");
			alphabetHash.put("V", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"all/"+"letterV"+".html"))));
			createHeaderFile(alphabetHash.get("V"),"Search letter [ V ]","","css/");
			alphabetHash.put("W", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"all/"+"letterW"+".html"))));
			createHeaderFile(alphabetHash.get("W"),"Search letter [ W ]","","css/");
			alphabetHash.put("X", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"all/"+"letterX"+".html"))));
			createHeaderFile(alphabetHash.get("X"),"Search letter [ X ]","","css/");
			alphabetHash.put("Y", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"all/"+"letterY"+".html"))));
			createHeaderFile(alphabetHash.get("Y"),"Search letter [ Y ]","","css/");
			alphabetHash.put("Z", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"all/"+"letterZ"+".html"))));
			createHeaderFile(alphabetHash.get("Z"),"Search letter [ Z ]","","css/");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveDescription(DescriptionCategory category, String description){	
		initialLetter = getInitialLetter(category.getLabel()); 
		
		htmlLetter = findLetter(initialLetter);
		
		if(htmlLetter != null){
			htmlLetter.write("        <div id=\""+category.getLabel().replace(" ", "")+"\">\n");
			htmlLetter.write("            <p><h1>"+category.getLabel()+"</h1><br /><hr />\n");
			htmlLetter.write("            "+description+"</p>\n");
			htmlLetter.write("        </div>\n");
			htmlLetter.write("        <p></p>\n");
		}else{
			System.out.println("Error: Null reference. Category: "+category.getLabel()+".");
		}
		
		if(allDescriptionsFile != null){
			allDescriptionsFile.write("        <div id=\""+category.getLabel().replace(" ", "")+"\">\n");
			allDescriptionsFile.write("            <p><h1>"+category.getLabel()+"</h1><br /><hr />\n");
			allDescriptionsFile.write("            "+description+"</p>\n");
			allDescriptionsFile.write("        </div>\n");
			allDescriptionsFile.write("        <p></p>\n");
		}else{
			System.out.println("Error: Null reference. Category: "+category.getLabel()+".");
		}
	}
	
	public String getInitialLetter(String letter){ 
		int i = 0;
		int j = 1;
		
		while(true){
			
			if(letter.substring(i,j).equals("Á") || letter.substring(i,j).equals("Â") )
				return "A";
			if (letter.substring(i,j).equals("Ô") ||letter.substring(i,j).equals("Ó"))
				return "O";
			if ( letter.substring(i,j).equals("Í") )
				return "I";
			if ( letter.substring(i,j).equals("Ú") )
				return "U";
			
			if(!letter.substring(i,j).equals(" "))
				return letter.substring(i,j);
			i++;
			j++;
		}
	}
	
	public PrintWriter findLetter(String letter){
		for (Entry<String, PrintWriter> l : alphabetHash.entrySet()) {
			if(l.getKey().equals(letter))
				return l.getValue();	
		} 
		return null;
	}
	
	public void finalizeExportFile(){	
		createFooterFile(allDescriptionsFile);
		
		createFooter();
	}
	
//	private String createHeaderAlphabet(String letter){
//		String header = "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01//EN\">\n"
//		+"<html>\n"
//		+"    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>\n"
//		+"    <head>\n"
//		+"    <title>"+"Search letter [ "+letter+" ] </title>\n"
//		+"    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\" />\n"
//		+"    </head>\n"
//		+"    <body>\n"
//		+"	 	  <div class=\"alphabet\"> "
//		+"			  <a href=" + "letterA.html><div class=\"letter\">A</div></a>\n"
//		+" 		  	  <a href=" + "letterB.html><div class=\"letter\">B</div></a>\n"
//		+" 		  	  <a href=" + "letterC.html><div class=\"letter\">C</div></a>\n"
//		+" 		  	  <a href=" + "letterD.html><div class=\"letter\">D</div></a>\n"
//		+" 			  <a href=" + "letterE.html><div class=\"letter\">E</div></a>\n"
//		+" 		  	  <a href=" + "letterF.html><div class=\"letter\">F</div></a>\n"
//		+" 			  <a href=" + "letterG.html><div class=\"letter\">G</div></a>\n"
//		+" 		  	  <a href=" + "letterH.html><div class=\"letter\">H</div></a>\n"
//		+" 			  <a href=" + "letterI.html><div class=\"letter\">I</div></a>\n"
//		+" 		  	  <a href=" + "letterJ.html><div class=\"letter\">J</div></a>\n"
//		+" 		 	  <a href=" + "letterK.html><div class=\"letter\">K</div></a>\n"
//		+" 		  	  <a href=" + "letterL.html><div class=\"letter\">L</div></a>\n"
//		+" 			  <a href=" + "letterM.html><div class=\"letter\">M</div></a>\n"
//		+" 			  <a href=" + "letterN.html><div class=\"letter\">N</div></a>\n"
//		+" 			  <a href=" + "letterO.html><div class=\"letter\">O</div></a>\n"
//		+" 		  	  <a href=" + "letterP.html><div class=\"letter\">P</div></a>\n"
//		+" 		  	  <a href=" + "letterQ.html><div class=\"letter\">Q</div></a>\n"
//		+" 			  <a href=" + "letterR.html><div class=\"letter\">R</div></a>\n"
//		+" 		  	  <a href=" + "letterS.html><div class=\"letter\">S</div></a>\n"
//		+" 			  <a href=" + "letterT.html><div class=\"letter\">T</div></a>\n"
//		+" 			  <a href=" + "letterU.html><div class=\"letter\">U</div></a>\n"
//		+" 		  	  <a href=" + "letterV.html><div class=\"letter\">V</div></a>\n"
//		+" 		  	  <a href=" + "letterW.html><div class=\"letter\">W</div></a>\n"
//		+" 		  	  <a href=" + "letterX.html><div class=\"letter\">X</div></a>\n"
//		+" 		  	  <a href=" + "letterY.html><div class=\"letter\">Y</div></a>\n"
//		+" 		  	  <a href=" + "letterZ.html><div class=\"letter\">Z</div></a>\n"
//		+"	  	  </div>\n\n";
//		
//		return header;
//		}
	
	private void createHeaderFile(PrintWriter file, String title, String baseDirectory, String styleDirectory){
		file.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01//EN\">\n"
		+"<html>\n"
		+"    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>\n"
		+"    <head>\n"
		+"    <link rel=\"stylesheet\" type=\"text/css\" href=\"" + styleDirectory + "style.css\" />\n"
		+"    <title>" + title + "</title>\n"
		+"    </head>\n"
		+"    <body>\n"		
		+"	 	  <div class=\"alphabet\"> "
		+"			  <a href=" + baseDirectory + "letterA.html><div class=\"letter\">A</div></a>\n"
		+" 		  	  <a href=" + baseDirectory + "letterB.html><div class=\"letter\">B</div></a>\n"
		+" 		  	  <a href=" + baseDirectory + "letterC.html><div class=\"letter\">C</div></a>\n"
		+" 		  	  <a href=" + baseDirectory + "letterD.html><div class=\"letter\">D</div></a>\n"
		+" 			  <a href=" + baseDirectory + "letterE.html><div class=\"letter\">E</div></a>\n"
		+" 		  	  <a href=" + baseDirectory + "letterF.html><div class=\"letter\">F</div></a>\n"
		+" 			  <a href=" + baseDirectory + "letterG.html><div class=\"letter\">G</div></a>\n"
		+" 		  	  <a href=" + baseDirectory + "letterH.html><div class=\"letter\">H</div></a>\n"
		+" 			  <a href=" + baseDirectory + "letterI.html><div class=\"letter\">I</div></a>\n"
		+" 		  	  <a href=" + baseDirectory + "letterJ.html><div class=\"letter\">J</div></a>\n"
		+" 		 	  <a href=" + baseDirectory + "letterK.html><div class=\"letter\">K</div></a>\n"
		+" 		  	  <a href=" + baseDirectory + "letterL.html><div class=\"letter\">L</div></a>\n"
		+" 			  <a href=" + baseDirectory + "letterM.html><div class=\"letter\">M</div></a>\n"
		+" 			  <a href=" + baseDirectory + "letterN.html><div class=\"letter\">N</div></a>\n"
		+" 			  <a href=" + baseDirectory + "letterO.html><div class=\"letter\">O</div></a>\n"
		+" 		  	  <a href=" + baseDirectory + "letterP.html><div class=\"letter\">P</div></a>\n"
		+" 		  	  <a href=" + baseDirectory + "letterQ.html><div class=\"letter\">Q</div></a>\n"
		+" 			  <a href=" + baseDirectory + "letterR.html><div class=\"letter\">R</div></a>\n"
		+" 		  	  <a href=" + baseDirectory + "letterS.html><div class=\"letter\">S</div></a>\n"
		+" 			  <a href=" + baseDirectory + "letterT.html><div class=\"letter\">T</div></a>\n"
		+" 			  <a href=" + baseDirectory + "letterU.html><div class=\"letter\">U</div></a>\n"
		+" 		  	  <a href=" + baseDirectory + "letterV.html><div class=\"letter\">V</div></a>\n"
		+" 		  	  <a href=" + baseDirectory + "letterW.html><div class=\"letter\">W</div></a>\n"
		+" 		  	  <a href=" + baseDirectory + "letterX.html><div class=\"letter\">X</div></a>\n"
		+" 		  	  <a href=" + baseDirectory + "letterY.html><div class=\"letter\">Y</div></a>\n"
		+" 		  	  <a href=" + baseDirectory + "letterZ.html><div class=\"letter\">Z</div></a>\n"
		+" 		  	  <a href=" + baseDirectory + "all.html><div class=\"letter\">Todos</div></a>\n"
		+"	  	  </div>\n\n"
		);	
	}
	
	private void createFooterFile(PrintWriter file){
		file.write("    </body>\n");
		file.write("</html>\n");
		file.close();
	}
	
	private void createCssFile(){
		PrintWriter cssFile;
		try {
			cssFile = new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"all/css/"+"style"+".css")));
		
			cssFile.write(			
					"h1 {\n"+
					"    font-family:\"Calibri\";\n"+
					"    font-style:bold;\n"+
					"    color:#4D7008;\n"+
					"    font-size:20px;\n"+
					"    margin-bottom: 0px;\n"+
					"    padding-bottom: 0px;\n"+
					"}\n"+
					"body {\n"+
					"    font-family:\"Calibri\";\n"+
					"    color:#02704F;\n"+
					"    font-size:20px;\n"+
					"    line-height:125%;\n"+
					"}\n"+
					"div {\n"+
					"    border:2px solid #4D7010;\n"+
					"    padding:6px 30px;\n"+
					"    text:#666666\n"+
					"    background:#FFFFFF;\n"+
					"	 background-color:#FAFFFD;\n"+
					"    container:#E3F2D3\n"+
					"    width:700px;\n"+
					"    border-radius:10px;\n"+
					"    box-shadow: 5px 5px 16px 0px rgba(50, 50, 50, 0.31);\n"+
					"    margin-bottom: 20px\n"+
					"}\n"+
					"div.alphabet {\n"+
					"	 text-align: center;\n"+
					"	 border:3px solid #4D7010;\n"+
					"	 padding:10px 0px;\n"+ 
					"	 word-spacing: 0.0em;\n"+
					"	 padding-left: 0%;\n"+
					"	 text:#666666\n"+
					"	 background:#FFFFFF;\n"+
					"	 background-color:#246D2B;\n"+
					"	 container:#E3F2D3\n"+
					"	 width:700px;\n"+
					"    height:30px;\n"+
					"    border-radius:5px;\n"+
					"	 box-shadow: 5px 5px 16px 0px rgba(50, 50, 50, 0.31);\n"+
					"}\n"+		
					"div.letter {\n"+
					"    display:inline-block;\n"+
					" 	 border:1px solid #4D7010;\n"+
					"	 padding:0px 6px;\n"+ 
					" 	 word-spacing: 0.6em;\n"+
					"	 padding-left: 0.4%;\n"+
					"	 text:#666666\n"+
					"	 background:#FFFFFF;\n"+
					"	 background-color:#FFFFFF;\n"+
					"	 container:#E3F2D3\n"+
					"	 width:15px;\n"+
					"    border-radius:4px;\n"+
					"	 box-shadow: 2px 2px 5px 0px rgba(50, 50, 50, 0.31);\n"+
					"}\n"+	
					"hr {\n"+
					"    background: #02704F  no-repeat scroll center;\n"+
					"    height:.01em;\n"+
					"    box-shadow: 1px 1px 3px 0px rgba(50, 50, 50, 0.20);\n"+
					"}\n"+
					"a.categoryReference{\n"+
					"   text-decoration: none;\n"+
					"   color:#0B3D18;\n"+
					"}\n"
					);
			
			cssFile.close();	
		} catch (IOException e) {
			System.out.println("Error: The css file could not be created.");
			e.printStackTrace();
		}
	}

	private void createFooter(){
		
		for (Entry<String, PrintWriter> l : alphabetHash.entrySet()){
			l.getValue().write("    </body>\n");
			l.getValue().write("</html>\n");
			l.getValue().close();
		}
	}
	
}
