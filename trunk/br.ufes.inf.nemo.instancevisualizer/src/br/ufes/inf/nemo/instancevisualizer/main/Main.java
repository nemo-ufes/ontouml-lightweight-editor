package br.ufes.inf.nemo.instancevisualizer.main;

import java.io.File;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import br.ufes.inf.nemo.instancevisualizer.apl.AplMainWindow;
import br.ufes.inf.nemo.instancevisualizer.gui.MainWindow;
import br.ufes.inf.nemo.instancevisualizer.util.DialogUtil;

public class Main {
	
    public static void main(String args[]) {
    	try {
    		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e1) {
			DialogUtil.errorDialog(null, DialogUtil.ATTENTION, "Couldn't load System look and feel.", "Will be loading default look and feel...");
		}
    	System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		MainWindow mw = new MainWindow(args);
		mw.setVisible(true);
		AplMainWindow.loadFile(new File(".//examples//open2.xml"), new File(".//examples//open2.refontouml"));
    }
    
}