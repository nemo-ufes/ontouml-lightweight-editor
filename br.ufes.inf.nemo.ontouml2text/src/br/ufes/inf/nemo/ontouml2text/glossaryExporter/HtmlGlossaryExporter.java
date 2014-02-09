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
			File general = new File(this.outputDirectory+"/"+this.outputName);
			general.mkdir();
			
			File alphabet = new File(this.outputDirectory+"/"+this.outputName+"/"+"alphabet/");
			alphabet.mkdir();
			
			out = new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+"/"+this.outputName+"/"+"index"+".html")));
			 
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
		"    <link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" />\n"+
		"    </head>\n"+
		"    <body>\n"+
		"        <div class=\"alphabet\"> <a href=" + "letterA.html" + ">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp[A]&nbsp&nbsp</a>"
		+ "<a href=" + "letterB.html" + ">&nbsp&nbsp[B]&nbsp&nbsp</a>"
		+ "<a href=" + "letterC.html" + ">&nbsp&nbsp[C]&nbsp&nbsp</a>"
		+ "<a href=" + "letterD.html" + ">&nbsp&nbsp[D]&nbsp&nbsp</a>"
		+ "<a href=" + "letterE.html" + ">&nbsp&nbsp[E]&nbsp&nbsp</a>"
		+ "<a href=" + "letterF.html" + ">&nbsp&nbsp[F]&nbsp&nbsp</a>"
		+ "<a href=" + "letterG.html" + ">&nbsp&nbsp[G]&nbsp&nbsp</a>"
		+ "<a href=" + "letterH.html" + ">&nbsp&nbsp[H]&nbsp&nbsp</a>"
		+ "<a href=" + "letterI.html" + ">&nbsp&nbsp[I]&nbsp&nbsp</a>"
		+ "<a href=" + "letterJ.html" + ">&nbsp&nbsp[J]&nbsp&nbsp</a>"
		+ "<a href=" + "letterK.html" + ">&nbsp&nbsp[K]&nbsp&nbsp</a>"
		+ "<a href=" + "letterL.html" + ">&nbsp&nbsp[L]&nbsp&nbsp</a>"
		+ "<a href=" + "letterM.html" + ">&nbsp&nbsp[M]&nbsp&nbsp</a>"
		+ "<a href=" + "letterN.html" + ">&nbsp&nbsp[N]&nbsp&nbsp</a>"
		+ "<a href=" + "letterO.html" + ">&nbsp&nbsp[O]&nbsp&nbsp</a>"
		+ "<a href=" + "letterP.html" + ">&nbsp&nbsp[P]&nbsp&nbsp</a>"
		+ "<a href=" + "letterQ.html" + ">&nbsp&nbsp[Q]&nbsp&nbsp</a>"
		+ "<a href=" + "letterR.html" + ">&nbsp&nbsp[R]&nbsp&nbsp</a>"
		+ "<a href=" + "letterS.html" + ">&nbsp&nbsp[S]&nbsp&nbsp</a>"
		+ "<a href=" + "letterT.html" + ">&nbsp&nbsp[T]&nbsp&nbsp</a>"
		+ "<a href=" + "letterU.html" + ">&nbsp&nbsp[U]&nbsp&nbsp</a>"
		+ "<a href=" + "letterV.html" + ">&nbsp&nbsp[V]&nbsp&nbsp</a>"
		+ "<a href=" + "letterW.html" + ">&nbsp&nbsp[W]&nbsp&nbsp</a>"
		+ "<a href=" + "letterX.html" + ">&nbsp&nbsp[X]&nbsp&nbsp</a>"
		+ "<a href=" + "letterY.html" + ">&nbsp&nbsp[Y]&nbsp&nbsp</a>"
		+ "<a href=" + "letterZ.html" + ">&nbsp&nbsp[Z]&nbsp&nbsp</a> </div>\n\n";
		
		return header;
	}
	
	private void createHeader(){
		out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01//EN\">\n");
		out.write("<html>\n");
		out.write("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>\n");
		out.write("    <head>\n");
		out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"alphabet/style.css\" />\n");
		out.write("    <title>"+this.title+"</title>\n");
		out.write("    </head>\n");
		out.write("    <body>\n");	
		
		out.write("        <div class=\"alphabet\"> <a href=" + "alphabet/letterA.html" + ">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp[A]&nbsp&nbsp</a>"
			+ " <a href=" + "alphabet/letterB.html" + ">&nbsp&nbsp[B]&nbsp&nbsp</a>"
			+ " <a href=" + "alphabet/letterC.html" + ">&nbsp&nbsp[C]&nbsp&nbsp</a>"
			+ " <a href=" + "alphabet/letterD.html" + ">&nbsp&nbsp[D]&nbsp&nbsp</a>"
			+ " <a href=" + "alphabet/letterE.html" + ">&nbsp&nbsp[E]&nbsp&nbsp</a>"
			+ " <a href=" + "alphabet/letterF.html" + ">&nbsp&nbsp[F]&nbsp&nbsp</a>"
			+ " <a href=" + "alphabet/letterG.html" + ">&nbsp&nbsp[G]&nbsp&nbsp</a>"
			+ " <a href=" + "alphabet/letterH.html" + ">&nbsp&nbsp[H]&nbsp&nbsp</a>"
			+ " <a href=" + "alphabet/letterI.html" + ">&nbsp&nbsp[I]&nbsp&nbsp</a>"
			+ " <a href=" + "alphabet/letterJ.html" + ">&nbsp&nbsp[J]&nbsp&nbsp</a>"
			+ " <a href=" + "alphabet/letterK.html" + ">&nbsp&nbsp[K]&nbsp&nbsp</a>"
			+ " <a href=" + "alphabet/letterL.html" + ">&nbsp&nbsp[L]&nbsp&nbsp</a>"
			+ " <a href=" + "alphabet/letterM.html" + ">&nbsp&nbsp[M]&nbsp&nbsp</a>"
			+ " <a href=" + "alphabet/letterN.html" + ">&nbsp&nbsp[N]&nbsp&nbsp</a>"
			+ " <a href=" + "alphabet/letterO.html" + ">&nbsp&nbsp[O]&nbsp&nbsp</a>"
			+ " <a href=" + "alphabet/letterP.html" + ">&nbsp&nbsp[P]&nbsp&nbsp</a>"
			+ " <a href=" + "alphabet/letterQ.html" + ">&nbsp&nbsp[Q]&nbsp&nbsp</a>"
			+ " <a href=" + "alphabet/letterR.html" + ">&nbsp&nbsp[R]&nbsp&nbsp</a>"
			+ " <a href=" + "alphabet/letterS.html" + ">&nbsp&nbsp[S]&nbsp&nbsp</a>"
			+ " <a href=" + "alphabet/letterT.html" + ">&nbsp&nbsp[T]&nbsp&nbsp</a>"
			+ " <a href=" + "alphabet/letterU.html" + ">&nbsp&nbsp[U]&nbsp&nbsp</a>"
			+ " <a href=" + "alphabet/letterV.html" + ">&nbsp&nbsp[V]&nbsp&nbsp</a>"
			+ " <a href=" + "alphabet/letterW.html" + ">&nbsp&nbsp[W]&nbsp&nbsp</a>"
			+ " <a href=" + "alphabet/letterX.html" + ">&nbsp&nbsp[X]&nbsp&nbsp</a>"
			+ " <a href=" + "alphabet/letterY.html" + ">&nbsp&nbsp[Y]&nbsp&nbsp</a>"
			+ " <a href=" + "alphabet/letterZ.html" + ">&nbsp&nbsp[Z]&nbsp&nbsp</a> </div>\n\n"
			+ "     </body>\n" + "</html>\n");
			out.close();
		
	}
	/*
	
	public void createAlphabet(){
		out.write("<div> <ahref=" + "alphabet/letterA.html" + ">[A]</a>"
		+ " <ahref=" + "alphabet/letterB.html" + ">[B]</a>"
		+ " <ahref=" + "alphabet/letterC.html" + ">[C]</a>"
		+ " <ahref=" + "alphabet/letterD.html" + ">[D]</a>"
		+ " <ahref=" + "alphabet/letterE.html" + ">[E]</a>"
		+ " <ahref=" + "alphabet/letterF.html" + ">[F]</a>"
		+ " <ahref=" + "alphabet/letterG.html" + ">[G]</a>"
		+ " <ahref=" + "alphabet/letterH.html" + ">[H]</a>"
		+ " <ahref=" + "alphabet/letterI.html" + ">[I]</a>"
		+ " <ahref=" + "alphabet/letterJ.html" + ">[J]</a>"
		+ " <ahref=" + "alphabet/letterK.html" + ">[K]</a>"
		+ " <ahref=" + "alphabet/letterL.html" + ">[L]</a>"
		+ " <ahref=" + "alphabet/letterM.html" + ">[M]</a>"
		+ " <ahref=" + "alphabet/letterN.html" + ">[N]</a>"
		+ " <ahref=" + "alphabet/letterO.html" + ">[O]</a>"
		+ " <ahref=" + "alphabet/letterP.html" + ">[P]</a>"
		+ " <ahref=" + "alphabet/letterQ.html" + ">[Q]</a>"
		+ " <ahref=" + "alphabet/letterR.html" + ">[R]</a>"
		+ " <ahref=" + "alphabet/letterS.html" + ">[S]</a>"
		+ " <ahref=" + "alphabet/letterT.html" + ">[T]</a>"
		+ " <ahref=" + "alphabet/letterU.html" + ">[U]</a>"
		+ " <ahref=" + "alphabet/letterV.html" + ">[V]</a>"
		+ " <ahref=" + "alphabet/letterW.html" + ">[W]</a>"
		+ " <ahref=" + "alphabet/letterX.html" + ">[X]</a>"
		+ " <ahref=" + "alphabet/letterY.html" + ">[Y]</a>"
		+ " <ahref=" + "alphabet/letterZ.html" + ">[Z]</a> </div>\n\n"
		+ "   </body>\n" + "</html>\n");
		out.close();
	}
	*/
	
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
					"}\n"+
					"div.alphabet {\n"+
					"	border:1px solid #4D7010;\n"+
					"	padding:10px 0px;\n"+ 
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
