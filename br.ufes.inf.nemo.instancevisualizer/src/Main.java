import gui.MainWindow;
import gui.OpenXML;

import java.awt.HeadlessException;

public class Main {
	private MainWindow mainWindow;
 
    public static void main(String args[]) throws HeadlessException, InterruptedException {
    	System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        new Main(true);
    }
    
    public Main(boolean closeIfCancel) throws HeadlessException, InterruptedException {
    	mainWindow = new MainWindow();
    	new OpenXML(mainWindow, closeIfCancel);
    	mainWindow.setVisible(true);
    	mainWindow.setScrollPanes();
    }
}