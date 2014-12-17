package br.ufes.inf.nemo.ootos.ocl2owl_swrl.util;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class SelectReasoner {
	public static SelectedReasoner selectReasoner() {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("Choose the more appropriate reasoner:");
		
		SelectedReasoner selectedReasoner = (SelectedReasoner)JOptionPane.showInputDialog(	frame, 
																							"Choose the more appropriate reasoner:",
																							"Select Reasoner",
																							JOptionPane.QUESTION_MESSAGE, 
																							null, 
																							SelectedReasoner.values(), 
																							SelectedReasoner.Unselected);
		
		return selectedReasoner;
		
	}
}
