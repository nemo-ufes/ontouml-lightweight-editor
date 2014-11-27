package br.ufes.inf.nemo.ontouml2alloy.scenarios.ui;

import java.util.ArrayList;

import javax.swing.JDialog;

import RefOntoUML.Association;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.common.file.TimeHelper;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.StoryScenario;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.StoryType;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.StoryScenario.Limit;

public class UITester {

	public UITester() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		OntoUMLParser parser;
		String fileName = "model/OntoBio.refontouml";
		System.out.println(TimeHelper.getTime()+" - "+fileName+": Loading parser...");
		
		try {
			parser = new OntoUMLParser(fileName);
			
		}catch(Exception e){
			System.out.println(TimeHelper.getTime()+" - "+fileName+": Parser not loaded!");
			return;
		}
		
		System.out.println(TimeHelper.getTime()+" - "+fileName+": Parser loaded!");
		
		try {
			ScenarioDialog dialog = new ScenarioDialog(parser);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
