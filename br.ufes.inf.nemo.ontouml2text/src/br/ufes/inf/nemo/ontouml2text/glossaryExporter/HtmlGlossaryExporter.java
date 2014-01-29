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
			String fullPath = this.outputDirectory+"/"+this.outputName+".html";
			System.out.println(fullPath);
			out = new PrintWriter(new BufferedWriter(new FileWriter(fullPath)));
			
			createHeader();
		} catch (IOException e) {
			System.out.println("Error: The output file could not be created.");
			e.printStackTrace();
		}
	}
	
	public void saveDescription(DescriptionCategory category, String description){	
		out.write("        <div>\n");
		out.write("            <p><h1>"+category.getLabel()+"</h1><br /><hr />\n");
		out.write("            "+description+"</p>\n");
		out.write("        </div>\n");
		out.write("        <p></p>\n");
	}
	
	public void finalizeExportFile(){
		createFooter();
	}
	
	private void createHeader(){
		out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01//EN\">\n");
		out.write("<html>\n");
		out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>");
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
		out.write("        box-shadow: 7px 7px 20px 0px rgba(50, 50, 50, 0.31);\n");
		out.write("    }\n");
		out.write("    hr {\n");
		out.write("        background: #02704F  no-repeat scroll center;\n");
		out.write("        height:.05em;\n");
		out.write("        box-shadow: 2px 2px 5px 0px rgba(50, 50, 50, 0.20);\n");
		out.write("    }\n");
		out.write("    </style>\n");
		out.write("    </head>\n");
		out.write("    <body>\n");
	}
	
	private void createFooter(){
		out.write("    </body>\n");
		out.write("</html>\n");
		
		out.close();
	}

}
