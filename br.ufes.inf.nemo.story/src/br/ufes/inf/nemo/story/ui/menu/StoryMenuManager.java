package br.ufes.inf.nemo.story.ui.menu;

import org.eclipse.jface.action.MenuManager;

 
public class StoryMenuManager extends MenuManager{
	private int selectedColumn = -1;
	private boolean isHeader = false;
	public int getSelectedColumn(){
		return selectedColumn;
	}
	public int setSelectedColumn(int c){
		return selectedColumn = c;
	}
	public void setHeader(boolean header) {
		isHeader = header;
		
	}
	public boolean isHeader(){
		return isHeader;
	}

}
