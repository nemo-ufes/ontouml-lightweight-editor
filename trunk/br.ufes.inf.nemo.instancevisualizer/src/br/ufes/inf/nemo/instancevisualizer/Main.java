package br.ufes.inf.nemo.instancevisualizer;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import edu.mit.csail.sdg.alloy4compiler.translator.A4Solution;
import br.ufes.inf.nemo.instancevisualizer.gui.MainWindow;
import br.ufes.inf.nemo.instancevisualizer.util.DialogUtil;

public class Main {
	
    public static void main(String args[]) {
    	try {
    		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		MainWindow mw = new MainWindow(args);
		mw.setVisible(true);
    }
        
}