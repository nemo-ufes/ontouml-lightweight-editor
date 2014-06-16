package br.ufes.inf.nemo.oled.meronymicvalidation;

import java.util.ArrayList;

import javax.swing.JPanel;

import br.ufes.inf.nemo.common.ontoumlfixer.Fix;

public abstract class ValidationPanel<T> extends JPanel {

	private static final long serialVersionUID = -510586972834941043L;

	public abstract ArrayList<T> getTableResults();
	public abstract Fix runFixes();
	public abstract void clearTable();
	
}
