package br.ufes.inf.nemo.ontouml2text.glossaryExporter;

import java.io.*;
import java.util.HashMap;
import java.util.Map.Entry;

import br.ufes.inf.nemo.ontouml2text.descriptionSpace.DescriptionCategory;

public class HtmlGlossaryExporter extends GlossaryExporter {
	private HashMap<String, PrintWriter> alphabetHash = new HashMap<String, PrintWriter>();
	private PrintWriter out;
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
			
			String fullPath = this.outputDirectory+"/"+this.outputName+".html";
			System.out.println(fullPath);
			out = new PrintWriter(new BufferedWriter(new FileWriter(fullPath)));
			 
			createHeader();					// create alphabet index
			populateHashMap(alphabetHash);	// create html's letters
			
		} catch (IOException e) {
			System.out.println("Error: The output file could not be created.");
			e.printStackTrace();
		}
	}
	
	public void populateHashMap(HashMap<String, PrintWriter> alphabetHash){
		try {
			alphabetHash.put("A", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+"alphabet/"+"letterA"+".html"))));
			alphabetHash.get("A").write(createHeaderAlphabet("A"));
			alphabetHash.put("B", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+"alphabet/"+"letterB"+".html"))));
			alphabetHash.get("B").write(createHeaderAlphabet("B"));
			alphabetHash.put("C", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+"alphabet/"+"letterC"+".html"))));
			alphabetHash.get("C").write(createHeaderAlphabet("C"));
			alphabetHash.put("D", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+"alphabet/"+"letterD"+".html"))));
			alphabetHash.get("D").write(createHeaderAlphabet("D"));
			alphabetHash.put("E", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+"alphabet/"+"letterE"+".html"))));
			alphabetHash.get("E").write(createHeaderAlphabet("E"));
			alphabetHash.put("F", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+"alphabet/"+"letterF"+".html"))));
			alphabetHash.get("F").write(createHeaderAlphabet("F"));
			alphabetHash.put("G", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+"alphabet/"+"letterG"+".html"))));
			alphabetHash.get("G").write(createHeaderAlphabet("F"));
			alphabetHash.put("H", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+"alphabet/"+"letterH"+".html"))));
			alphabetHash.get("H").write(createHeaderAlphabet("H"));
			alphabetHash.put("I", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+"alphabet/"+"letterI"+".html"))));
			alphabetHash.get("I").write(createHeaderAlphabet("I"));
			alphabetHash.put("J", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+"alphabet/"+"letterJ"+".html"))));
			alphabetHash.get("J").write(createHeaderAlphabet("J"));
			alphabetHash.put("K", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+"alphabet/"+"letterK"+".html"))));
			alphabetHash.get("K").write(createHeaderAlphabet("K"));
			alphabetHash.put("L", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+"alphabet/"+"letterL"+".html"))));
			alphabetHash.get("L").write(createHeaderAlphabet("L"));
			alphabetHash.put("M", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+"alphabet/"+"letterM"+".html"))));
			alphabetHash.get("M").write(createHeaderAlphabet("M"));
			alphabetHash.put("N", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+"alphabet/"+"letterN"+".html"))));
			alphabetHash.get("N").write(createHeaderAlphabet("N"));
			alphabetHash.put("O", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+"alphabet/"+"letterO"+".html"))));
			alphabetHash.get("O").write(createHeaderAlphabet("O"));
			alphabetHash.put("P", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+"alphabet/"+"letterP"+".html"))));
			alphabetHash.get("P").write(createHeaderAlphabet("P"));
			alphabetHash.put("Q", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+"alphabet/"+"letterQ"+".html"))));
			alphabetHash.get("Q").write(createHeaderAlphabet("Q"));
			alphabetHash.put("R", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+"alphabet/"+"letterR"+".html"))));
			alphabetHash.get("R").write(createHeaderAlphabet("R"));
			alphabetHash.put("S", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+"alphabet/"+"letterS"+".html"))));
			alphabetHash.get("S").write(createHeaderAlphabet("S"));
			alphabetHash.put("T", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+"alphabet/"+"letterT"+".html"))));
			alphabetHash.get("T").write(createHeaderAlphabet("T"));
			alphabetHash.put("U", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+"alphabet/"+"letterU"+".html"))));
			alphabetHash.get("U").write(createHeaderAlphabet("U"));
			alphabetHash.put("V", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+"alphabet/"+"letterV"+".html"))));
			alphabetHash.get("V").write(createHeaderAlphabet("V"));
			alphabetHash.put("W", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+"alphabet/"+"letterW"+".html"))));
			alphabetHash.get("W").write(createHeaderAlphabet("W"));
			alphabetHash.put("X", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+"alphabet/"+"letterX"+".html"))));
			alphabetHash.get("X").write(createHeaderAlphabet("X"));
			alphabetHash.put("Y", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+"alphabet/"+"letterY"+".html"))));
			alphabetHash.get("Y").write(createHeaderAlphabet("Y"));
			alphabetHash.put("Z", new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+"alphabet/"+"letterZ"+".html"))));
			alphabetHash.get("Z").write(createHeaderAlphabet("Z"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void saveDescription(DescriptionCategory category, String description, PrintWriter out){	
		out.write("        <div>\n");
		out.write("            <p><h1>"+category.getLabel()+"</h1><br /><hr />\n");
		out.write("            "+description+"</p>\n");
		out.write("        </div>\n");
		out.write("        <p></p>\n");
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
		"    <style>\n"+
		"    h1 {\n"+
		"        font-family:\"Calibri\";\n"+
		"        font-style:bold;\n"+
		"        color:#4D7008;\n"+
		"        font-size:20px;\n"+
		"        margin-bottom: 0px;\n"+
		"        padding-bottom: 0px;\n"+
		"    }\n"+
		"    body {\n"+
		"        font-family:\"Calibri\";\n"+
		"        color:#02704F;\n"+
		"        font-size:16px;\n"+
		"        line-height:125%;\n"+
		"    }\n"+
		"    div {\n"+
		"        border:2px solid #4D7010;\n"+
		"        padding:6px 30px; \n"+
		"        text:#666666\n"+
		"        background:#FFFFFF;\n"+
		"        container:#E3F2D3\n"+
		"        width:700px;\n"+
		"        border-radius:10px;\n"+
		"        box-shadow: 5px 5px 16px 0px rgba(50, 50, 50, 0.31);\n"+
		"    }\n"+
		"    hr {\n"+
		"        background: #02704F  no-repeat scroll center;\n"+
		"        height:.01em;\n"+
		"        box-shadow: 1px 1px 3px 0px rgba(50, 50, 50, 0.20);\n"+
		"    }\n"+
		"    </style>\n"+
		"    </head>\n"+
		"    <body>\n"+
		"<div> <a href=" + "letterA.html" + ">[A]</a>"
		+ " <a href=" + "letterB.html" + ">[B]</a>"
		+ " <a href=" + "letterC.html" + ">[C]</a>"
		+ " <a href=" + "letterD.html" + ">[D]</a>"
		+ " <a href=" + "letterE.html" + ">[E]</a>"
		+ " <a href=" + "letterF.html" + ">[F]</a>"
		+ " <a href=" + "letterG.html" + ">[G]</a>"
		+ " <a href=" + "letterH.html" + ">[H]</a>"
		+ " <a href=" + "letterI.html" + ">[I]</a>"
		+ " <a href=" + "letterJ.html" + ">[J]</a>"
		+ " <a href=" + "letterK.html" + ">[K]</a>"
		+ " <a href=" + "letterL.html" + ">[L]</a>"
		+ " <a href=" + "letterM.html" + ">[M]</a>"
		+ " <a href=" + "letterN.html" + ">[N]</a>"
		+ " <a href=" + "letterO.html" + ">[O]</a>"
		+ " <a href=" + "letterP.html" + ">[P]</a>"
		+ " <a href=" + "letterQ.html" + ">[Q]</a>"
		+ " <a href=" + "letterR.html" + ">[R]</a>"
		+ " <a href=" + "letterS.html" + ">[S]</a>"
		+ " <a href=" + "letterT.html" + ">[T]</a>"
		+ " <a href=" + "letterU.html" + ">[U]</a>"
		+ " <a href=" + "letterV.html" + ">[V]</a>"
		+ " <a href=" + "letterW.html" + ">[W]</a>"
		+ " <a href=" + "letterX.html" + ">[X]</a>"
		+ " <a href=" + "letterY.html" + ">[Y]</a>"
		+ " <a href=" + "letterZ.html" + ">[Z]</a> </div>\n\n";
		
		return header;
	}
	
	private void createHeader(){
		out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01//EN\">\n");
		out.write("<html>\n");
		out.write("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>\n");
		out.write("    <head>\n");
		out.write("    <title>"+this.title+"</title>\n");
		out.write("    <style>\n");
		out.write("    h1 {\n");
		out.write("        font-family:\"Calibri\";\n");
		out.write("        font-style:bold;\n");
		out.write("        color:#4D7008;\n");
		out.write("        font-size:20px;\n");
		out.write("        margin-bottom: 0px;\n");
		out.write("        padding-bottom: 0px;\n");
		out.write("    }\n");
		out.write("    body {\n");
		out.write("        font-family:\"Calibri\";\n");
		out.write("        color:#02704F;\n");
		out.write("        font-size:16px;\n");
		out.write("        line-height:125%;\n");
		out.write("    }\n");
		out.write("    div {\n");
		out.write("        border:2px solid #4D7010;\n");
		out.write("        padding:6px 30px; \n");
		out.write("        text:#666666\n");
		out.write("        background:#FFFFFF;\n");
		out.write("        container:#E3F2D3\n");
		out.write("        width:700px;\n");
		out.write("        border-radius:10px;\n");
		out.write("        box-shadow: 5px 5px 16px 0px rgba(50, 50, 50, 0.31);\n");
		out.write("    }\n");
		out.write("    hr {\n");
		out.write("        background: #02704F  no-repeat scroll center;\n");
		out.write("        height:.01em;\n");
		out.write("        box-shadow: 1px 1px 3px 0px rgba(50, 50, 50, 0.20);\n");
		out.write("    }\n");
		out.write("    </style>\n");
		out.write("    </head>\n");
		out.write("    <body>\n");	
		
		out.write("<div> <a href=" + "alphabet/letterA.html" + ">[A]</a>"
				+ " <a href=" + "alphabet/letterB.html" + ">[B]</a>"
				+ " <a href=" + "alphabet/letterC.html" + ">[C]</a>"
				+ " <a href=" + "alphabet/letterD.html" + ">[D]</a>"
				+ " <a href=" + "alphabet/letterE.html" + ">[E]</a>"
				+ " <a href=" + "alphabet/letterF.html" + ">[F]</a>"
				+ " <a href=" + "alphabet/letterG.html" + ">[G]</a>"
				+ " <a href=" + "alphabet/letterH.html" + ">[H]</a>"
				+ " <a href=" + "alphabet/letterI.html" + ">[I]</a>"
				+ " <a href=" + "alphabet/letterJ.html" + ">[J]</a>"
				+ " <a href=" + "alphabet/letterK.html" + ">[K]</a>"
				+ " <a href=" + "alphabet/letterL.html" + ">[L]</a>"
				+ " <a href=" + "alphabet/letterM.html" + ">[M]</a>"
				+ " <a href=" + "alphabet/letterN.html" + ">[N]</a>"
				+ " <a href=" + "alphabet/letterO.html" + ">[O]</a>"
				+ " <a href=" + "alphabet/letterP.html" + ">[P]</a>"
				+ " <a href=" + "alphabet/letterQ.html" + ">[Q]</a>"
				+ " <a href=" + "alphabet/letterR.html" + ">[R]</a>"
				+ " <a href=" + "alphabet/letterS.html" + ">[S]</a>"
				+ " <a href=" + "alphabet/letterT.html" + ">[T]</a>"
				+ " <a href=" + "alphabet/letterU.html" + ">[U]</a>"
				+ " <a href=" + "alphabet/letterV.html" + ">[V]</a>"
				+ " <a href=" + "alphabet/letterW.html" + ">[W]</a>"
				+ " <a href=" + "alphabet/letterX.html" + ">[X]</a>"
				+ " <a href=" + "alphabet/letterY.html" + ">[Y]</a>"
				+ " <a href=" + "alphabet/letterZ.html" + ">[Z]</a> </div>\n\n"
				+ "    </body>\n" + "</html>\n");
			out.close();
		
	}
	
	public void createAlphabet(){
		System.out.println("criei o alfabeto");
		out.write("<div> <a href=" + "alphabet/letterA.html" + ">[A]</a>"
			+ " <a href=" + "alphabet/letterB.html" + ">[B]</a>"
			+ " <a href=" + "alphabet/letterC.html" + ">[C]</a>"
			+ " <a href=" + "alphabet/letterD.html" + ">[D]</a>"
			+ " <a href=" + "alphabet/letterE.html" + ">[E]</a>"
			+ " <a href=" + "alphabet/letterF.html" + ">[F]</a>"
			+ " <a href=" + "alphabet/letterG.html" + ">[G]</a>"
			+ " <a href=" + "alphabet/letterH.html" + ">[H]</a>"
			+ " <a href=" + "alphabet/letterI.html" + ">[I]</a>"
			+ " <a href=" + "alphabet/letterJ.html" + ">[J]</a>"
			+ " <a href=" + "alphabet/letterK.html" + ">[K]</a>"
			+ " <a href=" + "alphabet/letterL.html" + ">[L]</a>"
			+ " <a href=" + "alphabet/letterM.html" + ">[M]</a>"
			+ " <a href=" + "alphabet/letterN.html" + ">[N]</a>"
			+ " <a href=" + "alphabet/letterO.html" + ">[O]</a>"
			+ " <a href=" + "alphabet/letterP.html" + ">[P]</a>"
			+ " <a href=" + "alphabet/letterQ.html" + ">[Q]</a>"
			+ " <a href=" + "alphabet/letterR.html" + ">[R]</a>"
			+ " <a href=" + "alphabet/letterS.html" + ">[S]</a>"
			+ " <a href=" + "alphabet/letterT.html" + ">[T]</a>"
			+ " <a href=" + "alphabet/letterU.html" + ">[U]</a>"
			+ " <a href=" + "alphabet/letterV.html" + ">[V]</a>"
			+ " <a href=" + "alphabet/letterW.html" + ">[W]</a>"
			+ " <a href=" + "alphabet/letterX.html" + ">[X]</a>"
			+ " <a href=" + "alphabet/letterY.html" + ">[Y]</a>"
			+ " <a href=" + "alphabet/letterZ.html" + ">[Z]</a> </div>\n\n"
			+ "    </body>\n" + "</html>\n");
		out.close();
	}
	
	private void createFooter(){
		
		for (Entry<String, PrintWriter> l : alphabetHash.entrySet()){
			l.getValue().write("    </body>\n");
			l.getValue().write("</html>\n");
			l.getValue().close();
		}
	}
	
}
