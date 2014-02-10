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

	public HtmlGlossaryExporter(String outputName, String outputDirectory, String title) {
		this.outputName = outputName;
		this.outputDirectory = outputDirectory;
		this.title = title;
	}
	
	public void initilizeExportFile(){
		try {
			File general = new File(this.outputDirectory+"/"+this.outputName);
			general.mkdir();
			
			File alphabet = new File(this.outputDirectory+"/"+this.outputName+"/"+"alphabet/");
			alphabet.mkdir();
			
			index = new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"index"+".html")));
			 
			createHeader();					// create alphabet index
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
			out.write("        <div>\n");
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
		String header = "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01//EN\">\n"+
		"<html>\n"+
		"    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>\n"+
		"    <head>\n"+
		"    <title>"+"Search letter [ "+letter+" ] </title>\n"+
		"    <link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" />\n"+
		"    </head>\n"+
		"    <body>\n"+
		"    <div class=\"alphabet\"> <a href=" + "letterA.html" + " style=\"text-decoration:none;\" >[A]&nbsp</a>"
		+ "<a href=" + "letterB.html" + " style=\"text-decoration:none;\">[B]&nbsp</a>"
		+ "<a href=" + "letterC.html" + " style=\"text-decoration:none;\">[C]&nbsp</a>"
		+ "<a href=" + "letterD.html" + " style=\"text-decoration:none;\">[D]&nbsp</a>"
		+ "<a href=" + "letterE.html" + " style=\"text-decoration:none;\">[E]&nbsp</a>"
		+ "<a href=" + "letterF.html" + " style=\"text-decoration:none;\">[F]&nbsp</a>"
		+ "<a href=" + "letterG.html" + " style=\"text-decoration:none;\">[G]&nbsp</a>"
		+ "<a href=" + "letterH.html" + " style=\"text-decoration:none;\">[H]&nbsp</a>"
		+ "<a href=" + "letterI.html" + " style=\"text-decoration:none;\">[I]&nbsp</a>"
		+ "<a href=" + "letterJ.html" + " style=\"text-decoration:none;\">[J]&nbsp</a>"
		+ "<a href=" + "letterK.html" + " style=\"text-decoration:none;\">[K]&nbsp</a>"
		+ "<a href=" + "letterL.html" + " style=\"text-decoration:none;\">[L]&nbsp</a>"
		+ "<a href=" + "letterM.html" + " style=\"text-decoration:none;\">[M]&nbsp</a>"
		+ "<a href=" + "letterN.html" + " style=\"text-decoration:none;\">[N]&nbsp</a>"
		+ "<a href=" + "letterO.html" + " style=\"text-decoration:none;\">[O]&nbsp</a>"
		+ "<a href=" + "letterP.html" + " style=\"text-decoration:none;\">[P]&nbsp</a>"
		+ "<a href=" + "letterQ.html" + " style=\"text-decoration:none;\">[Q]&nbsp</a>"
		+ "<a href=" + "letterR.html" + " style=\"text-decoration:none;\">[R]&nbsp</a>"
		+ "<a href=" + "letterS.html" + " style=\"text-decoration:none;\">[S]&nbsp</a>"
		+ "<a href=" + "letterT.html" + " style=\"text-decoration:none;\">[T]&nbsp</a>"
		+ "<a href=" + "letterU.html" + " style=\"text-decoration:none;\">[U]&nbsp</a>"
		+ "<a href=" + "letterV.html" + " style=\"text-decoration:none;\">[V]&nbsp</a>"
		+ "<a href=" + "letterW.html" + " style=\"text-decoration:none;\">[W]&nbsp</a>"
		+ "<a href=" + "letterX.html" + " style=\"text-decoration:none;\">[X]&nbsp</a>"
		+ "<a href=" + "letterY.html" + " style=\"text-decoration:none;\">[Y]&nbsp</a>"
		+ "<a href=" + "letterZ.html" + " style=\"text-decoration:none;\">[Z]&nbsp</a> </div>\n\n";
		return header;
		}
	
	private void createHeader(){
		index.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01//EN\">\n");
		index.write("<html>\n");
		index.write("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>\n");
		index.write("    <head>\n");
		index.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"alphabet/style.css\" />\n");
		index.write("    <title>"+this.title+"</title>\n");
		index.write("    </head>\n");
		index.write("    <body>\n");	
		
		index.write("	 <div class=\"alphabet\"> <a href=" + "alphabet/letterA.html" + "  style=\"text-decoration:none;\" >[A]</a>"
			+ " <a href=" + "alphabet/letterB.html" + " style=\"text-decoration:none;\">[B]</a>"
			+ " <a href=" + "alphabet/letterC.html" + " style=\"text-decoration:none;\">[C]</a>"
			+ " <a href=" + "alphabet/letterD.html" + " style=\"text-decoration:none;\">[D]</a>"
			+ " <a href=" + "alphabet/letterE.html" + " style=\"text-decoration:none;\">[E]</a>"
			+ " <a href=" + "alphabet/letterF.html" + " style=\"text-decoration:none;\">[F]</a>"
			+ " <a href=" + "alphabet/letterG.html" + " style=\"text-decoration:none;\">[G]</a>"
			+ " <a href=" + "alphabet/letterH.html" + " style=\"text-decoration:none;\">[H]</a>"
			+ " <a href=" + "alphabet/letterI.html" + " style=\"text-decoration:none;\">[I]</a>"
			+ " <a href=" + "alphabet/letterJ.html" + " style=\"text-decoration:none;\">[J]</a>"
			+ " <a href=" + "alphabet/letterK.html" + " style=\"text-decoration:none;\">[K]</a>"
			+ " <a href=" + "alphabet/letterL.html" + " style=\"text-decoration:none;\">[L]</a>"
			+ " <a href=" + "alphabet/letterM.html" + " style=\"text-decoration:none;\">[M]</a>"
			+ " <a href=" + "alphabet/letterN.html" + " style=\"text-decoration:none;\">[N]</a>"
			+ " <a href=" + "alphabet/letterO.html" + " style=\"text-decoration:none;\">[O]</a>"
			+ " <a href=" + "alphabet/letterP.html" + " style=\"text-decoration:none;\">[P]</a>"
			+ " <a href=" + "alphabet/letterQ.html" + " style=\"text-decoration:none;\">[Q]</a>"
			+ " <a href=" + "alphabet/letterR.html" + " style=\"text-decoration:none;\">[R]</a>"
			+ " <a href=" + "alphabet/letterS.html" + " style=\"text-decoration:none;\">[S]</a>"
			+ " <a href=" + "alphabet/letterT.html" + " style=\"text-decoration:none;\">[T]</a>"
			+ " <a href=" + "alphabet/letterU.html" + " style=\"text-decoration:none;\">[U]</a>"
			+ " <a href=" + "alphabet/letterV.html" + " style=\"text-decoration:none;\">[V]</a>"
			+ " <a href=" + "alphabet/letterW.html" + " style=\"text-decoration:none;\">[W]</a>"
			+ " <a href=" + "alphabet/letterX.html" + " style=\"text-decoration:none;\">[X]</a>"
			+ " <a href=" + "alphabet/letterY.html" + " style=\"text-decoration:none;\">[Y]</a>"
			+ " <a href=" + "alphabet/letterZ.html" + " style=\"text-decoration:none;\">[Z]</a> </div>\n\n"
			+ "     </body>\n" + "</html>\n");	
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
					"    padding:6px 30px; \n"+
					"    text:#666666\n"+
					"    background:#FFFFFF;\n"+
					"    container:#E3F2D3\n"+
					"    width:700px;\n"+
					"    border-radius:10px;\n"+
					"    box-shadow: 5px 5px 16px 0px rgba(50, 50, 50, 0.31);\n"+
					"	 margin-bottom: 20px"+
					"}\n"+
					"div.alphabet {\n"+
					"	border:1px solid #4D7010;\n"+
					"	padding:10px 0px;\n"+ 
					"	word-spacing: 1.0em;"+
					"	padding-left: 11%;"+
					"	text:#666666\n"+
					"	background:#FFFFFF;\n"+
					"	container:#E3F2D3\n"+
					"	width:700px;\n"+
					"	box-shadow: 5px 5px 16px 0px rgba(50, 50, 50, 0.31);\n"+
					"}"+			
					"hr {\n"+
					"    background: #02704F  no-repeat scroll center;\n"+
					"    height:.01em;\n"+
					"    box-shadow: 1px 1px 3px 0px rgba(50, 50, 50, 0.20);\n"+
					"}\n"+
					"a{\n"+
					    "word-spacing: 1.0em;"+
					"}"
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
