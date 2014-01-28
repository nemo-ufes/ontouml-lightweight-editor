package br.ufes.inf.nemo.ontouml2text.glossaryExporter;

import java.io.*;

import br.ufes.inf.nemo.ontouml2text.descriptionSpace.DescriptionCategory;

public class HtmlGlossaryExporter extends GlossaryExporter {
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
			out = new PrintWriter(new BufferedWriter(new FileWriter(this.outputDirectory+this.outputName+".html")));
			
			createHeader();
		} catch (IOException e) {
			System.out.println("Error: The output file could not be created.");
			e.printStackTrace();
		}
	}
	
	public void saveDescription(DescriptionCategory category, String description){
		out.write("        <section>\n");
		out.write("            <p><strong>"+category.getLabel()+"</strong><br />\n");
		out.write("            "+description+"</p>\n");
		out.write("        </section>\n");
	}
	
	public void finalizeExportFile(){
		createFooter();
	}
	
	private void createHeader(){
		out.write("<!DOCTYPE html>\n");
		out.write("<html>\n");
		out.write("    <head>\n");
		out.write("    <title>"+this.title+"</title>\n");
		out.write("    </head>\n");
		out.write("    <body>\n");
	}
	
	private void createFooter(){
		out.write("    </body>\n");
		out.write("</html>\n");
		
		out.close();
	}

}
