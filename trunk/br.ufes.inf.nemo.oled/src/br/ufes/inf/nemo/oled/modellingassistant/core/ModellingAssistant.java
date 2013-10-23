package br.ufes.inf.nemo.oled.modellingassistant.core;

import java.util.List;

import br.ufes.inf.nemo.oled.draw.DiagramElement;
import br.ufes.inf.nemo.oled.ui.diagram.DiagramEditor;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification;

public class ModellingAssistant implements DiagramNotification {
	@SuppressWarnings("unused")
	private ManagerDesignPatter managerdp = new ManagerDesignPatter();
	@SuppressWarnings("unused")
	private DiagramEditor editor;


	public ModellingAssistant(DiagramEditor diagramEditor) {
		editor = diagramEditor;
	}

	@Override
	public void notifyChange(List<DiagramElement> elements, ChangeType changeType, NotificationType notificationType) {
//		if(changeType == ChangeType.ELEMENTS_ADDED && notificationType == NotificationType.DO){
//			//elemento que foi adicionado
//			UmlDiagramElement currentNode = (UmlDiagramElement) elements.get(0);
//
//			//logica do assistante
//			managerdp.startDesignPattern(ModelTree.getParserFor(editor.getDiagram().getProject()), currentNode.getClassifier());
//		}
	}
	
	
	/*
	 * 
	 * if(currentNode.getClassifier() instanceof RefOntoUML.Phase){
				StructureDiagram diagram =(StructureDiagram) elements.get(0).getDiagram();

				//retorno da logica
				//ex.: criar um kind e coloca-lo como pai do elemento adicionado

				ClassElement node = (ClassElement) diagram.getElementFactory().createNode(ElementType.KIND);
				node.getClassifier().setName("a123");

				GeneralizationElement gen = (GeneralizationElement) diagram.getElementFactory().createConnection(RelationType.GENERALIZATION, (UmlNode) currentNode, node);

				AddNodeCommand nodeCommand = new AddNodeCommand(editor, diagram, node, ((UmlNode) currentNode).getAbsoluteX1()+100, ((UmlNode) currentNode).getAbsCenterY()-100, editor.getDiagram().getProject());
				editor.execute(nodeCommand);

				AddConnectionCommand connCommand = new AddConnectionCommand(editor, diagram, (UmlConnection)gen, (Classifier)currentNode.getClassifier(), node.getClassifier(), editor.getDiagram().getProject());
				editor.execute(connCommand);

				 * Alterar AddElementCommand tem uma array de  AddOperation semelhante ao MoveElementCommand
				 * Implementar AddOperation semelhante ao MoveNodeOperation
				 * Notar como é movido cada elemento individualmente pela move node operation
				 * no nosso caso, adicionar elemento individualmente pela add operation (nao importando qual o tipo, se é connection ou node) tratar essa diferença dentro do metodo
				 * implementar DO, UNDO e REDO
				 * Ao invez de criar varios Add Node/Connection command e executar, criar apenas 1
			}
	 * 
	 * */
	
}
