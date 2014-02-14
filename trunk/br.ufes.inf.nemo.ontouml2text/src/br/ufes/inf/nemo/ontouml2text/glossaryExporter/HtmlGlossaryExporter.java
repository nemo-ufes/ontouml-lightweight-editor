package br.ufes.inf.nemo.ontouml2text.glossaryExporter;

import java.io.*;
import java.util.HashMap;
import java.util.Map.Entry;

import br.ufes.inf.nemo.ontouml2text.descriptionSpace.DescriptionCategory;

public class HtmlGlossaryExporter extends GlossaryExporter {
	private HashMap<String, PrintWriter> alphabetHash = new HashMap<String, PrintWriter>();
	private PrintWriter index;
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
			
			File alphabet = new File(this.outputDirectory+"/"+this.outputName+"/"+"alphabet/");
			alphabet.mkdir();
			
			index = new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"index"+".html")));
			 
			createIndexFile();					// create alphabet index
			createCssFile();				// create css style file
			populateHashMap(alphabetHash);	// create html's letters
			
		} catch (IOException e) {
			System.out.println("Error: The output file could not be created.");
			e.printStackTrace();
		}
	}
	
	public void populateHashMap(HashMap<String, PrintWriter> alphabetHash){
		try {
			alphabetHash.put("A", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"alphabet/"+"letterA"+".html"))));
			alphabetHash.get("A").write(createHeaderAlphabet("A"));
			alphabetHash.put("B", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"alphabet/"+"letterB"+".html"))));
			alphabetHash.get("B").write(createHeaderAlphabet("B"));
			alphabetHash.put("C", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"alphabet/"+"letterC"+".html"))));
			alphabetHash.get("C").write(createHeaderAlphabet("C"));
			alphabetHash.put("D", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"alphabet/"+"letterD"+".html"))));
			alphabetHash.get("D").write(createHeaderAlphabet("D"));
			alphabetHash.put("E", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"alphabet/"+"letterE"+".html"))));
			alphabetHash.get("E").write(createHeaderAlphabet("E"));
			alphabetHash.put("F", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"alphabet/"+"letterF"+".html"))));
			alphabetHash.get("F").write(createHeaderAlphabet("F"));
			alphabetHash.put("G", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"alphabet/"+"letterG"+".html"))));
			alphabetHash.get("G").write(createHeaderAlphabet("F"));
			alphabetHash.put("H", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"alphabet/"+"letterH"+".html"))));
			alphabetHash.get("H").write(createHeaderAlphabet("H"));
			alphabetHash.put("I", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"alphabet/"+"letterI"+".html"))));
			alphabetHash.get("I").write(createHeaderAlphabet("I"));
			alphabetHash.put("J", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"alphabet/"+"letterJ"+".html"))));
			alphabetHash.get("J").write(createHeaderAlphabet("J"));
			alphabetHash.put("K", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"alphabet/"+"letterK"+".html"))));
			alphabetHash.get("K").write(createHeaderAlphabet("K"));
			alphabetHash.put("L", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"alphabet/"+"letterL"+".html"))));
			alphabetHash.get("L").write(createHeaderAlphabet("L"));
			alphabetHash.put("M", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"alphabet/"+"letterM"+".html"))));
			alphabetHash.get("M").write(createHeaderAlphabet("M"));
			alphabetHash.put("N", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"alphabet/"+"letterN"+".html"))));
			alphabetHash.get("N").write(createHeaderAlphabet("N"));
			alphabetHash.put("O", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"alphabet/"+"letterO"+".html"))));
			alphabetHash.get("O").write(createHeaderAlphabet("O"));
			alphabetHash.put("P", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"alphabet/"+"letterP"+".html"))));
			alphabetHash.get("P").write(createHeaderAlphabet("P"));
			alphabetHash.put("Q", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"alphabet/"+"letterQ"+".html"))));
			alphabetHash.get("Q").write(createHeaderAlphabet("Q"));
			alphabetHash.put("R", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"alphabet/"+"letterR"+".html"))));
			alphabetHash.get("R").write(createHeaderAlphabet("R"));
			alphabetHash.put("S", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"alphabet/"+"letterS"+".html"))));
			alphabetHash.get("S").write(createHeaderAlphabet("S"));
			alphabetHash.put("T", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"alphabet/"+"letterT"+".html"))));
			alphabetHash.get("T").write(createHeaderAlphabet("T"));
			alphabetHash.put("U", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"alphabet/"+"letterU"+".html"))));
			alphabetHash.get("U").write(createHeaderAlphabet("U"));
			alphabetHash.put("V", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"alphabet/"+"letterV"+".html"))));
			alphabetHash.get("V").write(createHeaderAlphabet("V"));
			alphabetHash.put("W", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"alphabet/"+"letterW"+".html"))));
			alphabetHash.get("W").write(createHeaderAlphabet("W"));
			alphabetHash.put("X", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"alphabet/"+"letterX"+".html"))));
			alphabetHash.get("X").write(createHeaderAlphabet("X"));
			alphabetHash.put("Y", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"alphabet/"+"letterY"+".html"))));
			alphabetHash.get("Y").write(createHeaderAlphabet("Y"));
			alphabetHash.put("Z", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"alphabet/"+"letterZ"+".html"))));
			alphabetHash.get("Z").write(createHeaderAlphabet("Z"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveDescription(DescriptionCategory category, String description, PrintWriter out){	
		if(out != null){
			out.write("        <div id=\""+category.getLabel().replace(" ", "")+"\">\n");
			out.write("            <p><h1>"+category.getLabel()+"</h1><br /><hr />\n");
			out.write("            "+description+"</p>\n");
			out.write("        </div>\n");
			out.write("        <p></p>\n");
		}else{
			System.out.println("Error: Null reference. Category: "+category.getLabel()+".");
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
		createFooter();
	}
	
	private String createHeaderAlphabet(String letter){
		String header = "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01//EN\">\n"
		+"<html>\n"
		+"    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>\n"
		+"    <head>\n"
		+"    <title>"+"Search letter [ "+letter+" ] </title>\n"
		+"    <link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" />\n"
		+"    </head>\n"
		+"    <body>\n"
		+"	 	  <div class=\"alphabet\"> "
		+"			  <a href=" + "letterA.html><div class=\"letter\">A</div></a>\n"
		+" 		  	  <a href=" + "letterB.html><div class=\"letter\">B</div></a>\n"
		+" 		  	  <a href=" + "letterC.html><div class=\"letter\">C</div></a>\n"
		+" 		  	  <a href=" + "letterD.html><div class=\"letter\">D</div></a>\n"
		+" 			  <a href=" + "letterE.html><div class=\"letter\">E</div></a>\n"
		+" 		  	  <a href=" + "letterF.html><div class=\"letter\">F</div></a>\n"
		+" 			  <a href=" + "letterG.html><div class=\"letter\">G</div></a>\n"
		+" 		  	  <a href=" + "letterH.html><div class=\"letter\">H</div></a>\n"
		+" 			  <a href=" + "letterI.html><div class=\"letter\">I</div></a>\n"
		+" 		  	  <a href=" + "letterJ.html><div class=\"letter\">J</div></a>\n"
		+" 		 	  <a href=" + "letterK.html><div class=\"letter\">K</div></a>\n"
		+" 		  	  <a href=" + "letterL.html><div class=\"letter\">L</div></a>\n"
		+" 			  <a href=" + "letterM.html><div class=\"letter\">M</div></a>\n"
		+" 			  <a href=" + "letterN.html><div class=\"letter\">N</div></a>\n"
		+" 			  <a href=" + "letterO.html><div class=\"letter\">O</div></a>\n"
		+" 		  	  <a href=" + "letterP.html><div class=\"letter\">P</div></a>\n"
		+" 		  	  <a href=" + "letterQ.html><div class=\"letter\">Q</div></a>\n"
		+" 			  <a href=" + "letterR.html><div class=\"letter\">R</div></a>\n"
		+" 		  	  <a href=" + "letterS.html><div class=\"letter\">S</div></a>\n"
		+" 			  <a href=" + "letterT.html><div class=\"letter\">T</div></a>\n"
		+" 			  <a href=" + "letterU.html><div class=\"letter\">U</div></a>\n"
		+" 		  	  <a href=" + "letterV.html><div class=\"letter\">V</div></a>\n"
		+" 		  	  <a href=" + "letterW.html><div class=\"letter\">W</div></a>\n"
		+" 		  	  <a href=" + "letterX.html><div class=\"letter\">X</div></a>\n"
		+" 		  	  <a href=" + "letterY.html><div class=\"letter\">Y</div></a>\n"
		+" 		  	  <a href=" + "letterZ.html><div class=\"letter\">Z</div></a>\n"
		+"	  	  </div>\n\n";
		
		return header;
		}
	
	private void createIndexFile(){
		index.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01//EN\">\n"
		+"<html>\n"
		+"    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>\n"
		+"    <head>\n"
		+"    <link rel=\"stylesheet\" type=\"text/css\" href=\"alphabet/style.css\" />\n"
		+"    <title>"+this.title+"</title>\n"
		+"    </head>\n"
		+"    <body>\n"		
		+"	 	  <div class=\"alphabet\"> "
		+"			  <a href=" + "alphabet/letterA.html><div class=\"letter\">A</div></a>\n"
		+" 		  	  <a href=" + "alphabet/letterB.html><div class=\"letter\">B</div></a>\n"
		+" 		  	  <a href=" + "alphabet/letterC.html><div class=\"letter\">C</div></a>\n"
		+" 		  	  <a href=" + "alphabet/letterD.html><div class=\"letter\">D</div></a>\n"
		+" 			  <a href=" + "alphabet/letterE.html><div class=\"letter\">E</div></a>\n"
		+" 		  	  <a href=" + "alphabet/letterF.html><div class=\"letter\">F</div></a>\n"
		+" 			  <a href=" + "alphabet/letterG.html><div class=\"letter\">G</div></a>\n"
		+" 		  	  <a href=" + "alphabet/letterH.html><div class=\"letter\">H</div></a>\n"
		+" 			  <a href=" + "alphabet/letterI.html><div class=\"letter\">I</div></a>\n"
		+" 		  	  <a href=" + "alphabet/letterJ.html><div class=\"letter\">J</div></a>\n"
		+" 		 	  <a href=" + "alphabet/letterK.html><div class=\"letter\">K</div></a>\n"
		+" 		  	  <a href=" + "alphabet/letterL.html><div class=\"letter\">L</div></a>\n"
		+" 			  <a href=" + "alphabet/letterM.html><div class=\"letter\">M</div></a>\n"
		+" 			  <a href=" + "alphabet/letterN.html><div class=\"letter\">N</div></a>\n"
		+" 			  <a href=" + "alphabet/letterO.html><div class=\"letter\">O</div></a>\n"
		+" 		  	  <a href=" + "alphabet/letterP.html><div class=\"letter\">P</div></a>\n"
		+" 		  	  <a href=" + "alphabet/letterQ.html><div class=\"letter\">Q</div></a>\n"
		+" 			  <a href=" + "alphabet/letterR.html><div class=\"letter\">R</div></a>\n"
		+" 		  	  <a href=" + "alphabet/letterS.html><div class=\"letter\">S</div></a>\n"
		+" 			  <a href=" + "alphabet/letterT.html><div class=\"letter\">T</div></a>\n"
		+" 			  <a href=" + "alphabet/letterU.html><div class=\"letter\">U</div></a>\n"
		+" 		  	  <a href=" + "alphabet/letterV.html><div class=\"letter\">V</div></a>\n"
		+" 		  	  <a href=" + "alphabet/letterW.html><div class=\"letter\">W</div></a>\n"
		+" 		  	  <a href=" + "alphabet/letterX.html><div class=\"letter\">X</div></a>\n"
		+" 		  	  <a href=" + "alphabet/letterY.html><div class=\"letter\">Y</div></a>\n"
		+" 		  	  <a href=" + "alphabet/letterZ.html><div class=\"letter\">Z</div></a>\n"
		+"	  	  </div>\n\n"
		+"        <br>\n"
		+"        <p style=\"font-size:70px; color:#307E06\" align=center>"+this.title+"</p>\n"
		+"        <p style=\"font-size:30px; color:#307E06\" align=center>"+this.subtitle+"</p>\n"
		+"	  </body>\n" 
		+"</html>\n");	
		index.close();
	}
	
	private void createCssFile(){
		PrintWriter cssFile;
		try {
			cssFile = new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"alphabet/"+"style"+".css")));
		
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
