package br.ufes.inf.nemo.story.ui.menu;


public class SelectedColumnHolder{
	private int selectedColumn = -1;
	public SelectedColumnHolder() {
		
	}
	
	public int getSelectedColumn(){
		return selectedColumn;
	}
	public int setSelectedColumn(int c){
		return selectedColumn = c;
	}

}
