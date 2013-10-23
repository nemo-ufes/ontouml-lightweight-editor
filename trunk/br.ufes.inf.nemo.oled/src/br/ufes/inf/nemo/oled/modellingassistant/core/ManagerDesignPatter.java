package br.ufes.inf.nemo.oled.modellingassistant.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.edit.command.AddCommand;

import RefOntoUML.Classifier;
import RefOntoUML.NamedElement;
import RefOntoUML.RefOntoUMLFactory;
import RefOntoUML.SubKind;
import RefOntoUML.SubstanceSortal;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.oled.draw.DiagramElement;
import br.ufes.inf.nemo.oled.draw.Node;
import br.ufes.inf.nemo.oled.model.ElementType;
import br.ufes.inf.nemo.oled.model.RelationType;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.modellingassistant.ui.ManagerUserInterface;
import br.ufes.inf.nemo.oled.ui.ModelTree;
import br.ufes.inf.nemo.oled.ui.diagram.commands.AddConnectionCommand;
import br.ufes.inf.nemo.oled.ui.diagram.commands.AddNodeCommand;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification.ChangeType;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification.NotificationType;
import br.ufes.inf.nemo.oled.umldraw.shared.UmlConnection;
import br.ufes.inf.nemo.oled.umldraw.shared.UmlNode;
import br.ufes.inf.nemo.oled.umldraw.structure.BaseConnection;
import br.ufes.inf.nemo.oled.umldraw.structure.ClassElement;
import br.ufes.inf.nemo.oled.umldraw.structure.GeneralizationElement;
import br.ufes.inf.nemo.oled.util.AppCommandListener;

public class ManagerDesignPatter {
	private ManagerUserInterface managerui = new ManagerUserInterface();
	private static OntoUMLParser parser;
	public RefOntoUMLFactory factory = RefOntoUMLFactory.eINSTANCE;
	private static NamedElement currentClassifier;
	private static ManagerDesignPatter instance = new ManagerDesignPatter();
	
	public static ManagerDesignPatter getInstance(){
		return instance;
	}

	public void startDesignPattern(OntoUMLParser parser, NamedElement cls){
		ManagerDesignPatter.parser = parser;
		currentClassifier = cls;
		
		if(cls instanceof RefOntoUML.SubKind){
			startSubkindPattern((SubKind)cls);
		}
	}

	private void startSubkindPattern(SubKind sk){
		//show a dialog to select the father of this class
		//if the father has a generalization set, ask to put together
			//ask to put disjoit or complete
		Set<SubstanceSortal> ss = parser.getAllInstances(SubstanceSortal.class);
		if(ss.size() > 0){
			//create a generalization for this subkind
			managerui.showSetFatherOfClass(parser,sk,ss);
		}else{
			//create substancesortais
		}

	}

	
	
	/*
	 * All the next methods are callbacks from the ManagerUserInterface. 
	 * */
	
	public RefOntoUML.Class callBackCreateClass(String ontoType, String clsName){
		try{
			//create the new class
			RefOntoUML.Class cls = Util.createClass(ontoType);
			cls.setName(clsName);
			
			//add new class to the model
			RefOntoUML.Model model = (RefOntoUML.Model)parser.getModel();
			model.getPackagedElement().add(cls);
			
			return cls;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public void callBackCreateGeneralization(String ontoType, String clsName){
//		StructureDiagram diagram =(StructureDiagram) elements.get(0).getDiagram();
//
//		//retorno da logica
//		//ex.: criar um kind e coloca-lo como pai do elemento adicionado
//
//		ClassElement node = (ClassElement) diagram.getElementFactory().createNode(ElementType.KIND);
//		node.getClassifier().setName("a123");
//
//		GeneralizationElement gen = (GeneralizationElement) diagram.getElementFactory().createConnection(RelationType.GENERALIZATION, (UmlNode) currentClassifier, node);
//
//		AddNodeCommand nodeCommand = new AddNodeCommand(editor, diagram, node, ((UmlNode) currentClassifier).getAbsoluteX1()+100, ((UmlNode) currentClassifier).getAbsCenterY()-100, editor.getDiagram().getProject());
//		editor.execute(nodeCommand);
//
//		AddConnectionCommand connCommand = new AddConnectionCommand(editor, diagram, (UmlConnection)gen, (Classifier)currentClassifier.getClassifier(), node.getClassifier(), editor.getDiagram().getProject());
//		editor.execute(connCommand);
		
	}
	
}
