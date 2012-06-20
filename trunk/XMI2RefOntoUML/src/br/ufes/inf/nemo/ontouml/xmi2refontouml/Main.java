package br.ufes.inf.nemo.ontouml.xmi2refontouml;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import br.ufes.inf.nemo.ontouml.xmi2refontouml.transformation.Mediator;
import br.ufes.inf.nemo.ontouml.xmi2refontouml.ui.AppFrame;


public final class Main {
	
	final static String READ_FILE_ADDRESS = "C:\\Users\\Vinicius\\Documents\\ANTT\\XMItoOntoRef\\sampleAstah.xml";
	final static String SAVE_FILE_ADDRESS = "teste.refontouml";

	/**
	 * Private constructor.
	 */
	private Main() { }

	/**
	 * The start method for this application.
	 * @param args the command line parameters
	 */
	public static void main(String[] args) {
		
//		SwingUtilities.invokeLater(new Runnable() {
//			/**
//			 * {@inheritDoc}
//			 */
//			public void run() {
//				try {
//					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//					UIManager.put("TabbedPane.focus", new Color(0, 0, 0, 0));
//					JFrame frame = new AppFrame("XMI2RefOntoUML");
//					frame.setVisible(true);
//					frame.setLocationRelativeTo(null);
//				} catch (Exception ex) {
//					ex.printStackTrace();
//				}
//			}
//		});
		
		Mediator transfManager = new Mediator();
		transfManager.READ_FILE_ADDRESS = READ_FILE_ADDRESS;
		transfManager.SAVE_FILE_ADDRESS = SAVE_FILE_ADDRESS;
		
		transfManager.parse();
		
		transfManager.validate();
		
		transfManager.save();
		
	}
	
}
