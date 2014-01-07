package br.ufes.inf.nemo.oled.modellingassistant.ui;

import java.text.Normalizer;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JPanel;

import RefOntoUML.NamedElement;
import RefOntoUML.SubstanceSortal;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.oled.AppFrame;
import br.ufes.inf.nemo.oled.modellingassistant.core.ManagerDesignPatter;

public class ManagerUserInterface {
	private static MainFrame mainFrame;
	private JPanel currentPanel;
	private static ManagerUserInterface instance = new ManagerUserInterface();
	
	public static ManagerUserInterface getInstance(){
		return instance;
	}
	
	public void showSetFatherOfClass(OntoUMLParser parser, RefOntoUML.Class cls, Set<SubstanceSortal> possibleFathers){
		String[] fathers = new String[possibleFathers.size()];

		Iterator<SubstanceSortal> itr = possibleFathers.iterator();

		//create strings for each possible father
		int i = 0;
		while(itr.hasNext()) {
			RefOntoUML.Class pf_cls = (RefOntoUML.Class) itr.next();
			fathers[i] = parser.getStringRepresentation(pf_cls);
			i++;
		}
		
		//class not yet in the parser, than...
		String type;
		String name;

		name = ((NamedElement)cls).getName();
		
		type = cls.getClass().toString().replaceAll("class RefOntoUML.impl.","");
	    type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);	
	    
		currentPanel = new PanelShowFatherOfClass(type+" "+name, fathers);
		mainFrame = new MainFrame(AppFrame.getInstance());
		mainFrame.putPanel(currentPanel);
		mainFrame.getFrame().pack();
		mainFrame.getFrame().validate();
		mainFrame.getFrame().setTitle("Design Patter for: "+type+" "+name);
		mainFrame.setVisibility(true);
		
	}
	
	public void showCreateNewClass(String cls){
		
	}
	
	public void callBackForSelect(String ontoType, String clsName){
		mainFrame.getFrame().dispose();
		ManagerDesignPatter.getInstance().callBackCreateGeneralization(ontoType, clsName);
	}
	
}
