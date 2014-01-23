package br.ufes.inf.nemo.oled.assistant.notifier;

import java.util.List;

import br.ufes.inf.nemo.oled.draw.DiagramElement;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification;

public class AssistantNotifier implements DiagramNotification {
	@Override
	public void notifyChange(List<DiagramElement> elements, ChangeType changeType, NotificationType notificationType) {
		/*
		 * Tratar UNDO depois
		 * */
		if(changeType == ChangeType.ELEMENTS_ADDED && notificationType == NotificationType.DO){
			elementAdded();
		}else if(changeType == ChangeType.ELEMENTS_REMOVED && notificationType == NotificationType.DO){
			elementRemoved();
		}else if(changeType == ChangeType.CONNECTION_POINT_EDITED && notificationType == NotificationType.DO){
			associationEdited();
		}
	}

	public void elementAdded(){
		/*
		 * Verificar se foi uma classe ou uma associacao
		 * */
		System.out.println("elementAdded");
	}

	public void elementRemoved(){
		/*
		 * Verificar se removeu soh do diagram ou se foi da tree
		 * */
		System.out.println("elementRemoved");
	}
	
	public void associationEdited(){
		System.out.println("associationEdited");
	}
}
