package br.ufes.inf.nemo.instancevisualizer.util;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import sas.swing.plaf.MultiLineLabelUI;


public class DialogUtil {
	
	/**
	 * Open an error dialog.
	 * @param parent
	 * @param errorTitle
	 * @param errorDescription
	 */
	public static void errorDialog(JFrame parent, String errorTitle, String errorDescription) {
		final JDialog errorDialog = new JDialog(parent, true);
		errorDialog.setResizable(false);
		errorDialog.setTitle(errorTitle);
		
		int lines = 1 + errorDescription.length()/50;
		
		// Setting dimensions:
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Double width = screenSize.getWidth();
		Double height = screenSize.getHeight();
		errorDialog.setBounds(width.intValue()/2-180, height.intValue()/2-50-(16)*lines, 360, 84+16*lines);
		
		// 44 upper case chars
		// 56 lower case chars
		JLabel lblError = new JLabel(errorDescription);
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblError.setBounds(10, 11, 334, 15*lines);
		lblError.setUI(MultiLineLabelUI.labelUI);
		
		//final JDialog errorDialogFinal = errorDialog;
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				errorDialog.dispose();
			}
		});
		btnOk.setBounds(144, 21+16*lines, 65, 23);
		errorDialog.getContentPane().setLayout(null);
		errorDialog.getContentPane().add(lblError);
		errorDialog.getContentPane().add(btnOk);
		
		// Setting visible!
		errorDialog.setVisible(true);
	}
	
	/**
	 * Open a file chooser dialog. Extension filter format: <description>$<extension>.
	 * @param startingDir path to the directory that the dialog shows upon construction.  
	 * @param extensionFilter array of file extensions to filter. The first filter will be the default.
	 * @param exitIfCancel if true, exits the program if you cancel the dialog.
	 * @return A file object that represents the opened file.
	 */
	public static File fileDialog(String approveText, String startingDir, 
			String extensionFilters[], boolean exitIfCancel) {
		JFileChooser fc = new JFileChooser();
		fc.setVisible(true);
		fc.setCurrentDirectory(new File(startingDir));
		for(String filter : extensionFilters) {
			int moneyIndex = filter.indexOf('$');
			String description = filter.substring(0, moneyIndex);
			String estension = filter.substring(moneyIndex+1);
			fc.addChoosableFileFilter(new FileNameExtensionFilter(description, estension));
		}
		
		int returnVal = fc.showDialog(null, approveText);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			return fc.getSelectedFile();
		}
		
		if(exitIfCancel) {
			System.exit(0);
		}
		
		return null;
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void chooseDialog(final Choice choice, JFrame parent, String title, String description, String choice1, String choice2) {
		final JDialog choiceDialog = new JDialog(parent, true);
		choiceDialog.setResizable(false);
		choiceDialog.setTitle(title);
		
		int lines = 1 + description.length()/50;
		
		// Setting dimensions:
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Double width = screenSize.getWidth();
		Double height = screenSize.getHeight();
		choiceDialog.setBounds(width.intValue()/2-180, height.intValue()/2-50-(16)*lines, 360, 84+16*lines);
		
		// 44 upper case chars
		// 56 lower case chars
		JLabel lblError = new JLabel(description);
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblError.setBounds(10, 11, 334, 15*lines);
		lblError.setUI(MultiLineLabelUI.labelUI);
		
		//final JDialog errorDialogFinal = errorDialog;
		JButton btn1 = new JButton(choice1);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				choice.setChoice(true);
				choiceDialog.dispose();
			}
		});
		btn1.setBounds(107, 37, 64, 23);
		choiceDialog.getContentPane().setLayout(null);
		choiceDialog.getContentPane().add(lblError);
		choiceDialog.getContentPane().add(btn1);
		
		JButton btn2 = new JButton(choice2);
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				choice.setChoice(false);
				choiceDialog.dispose();
			}
		});
		btn2.setBounds(181, 37, 64, 23);
		choiceDialog.getContentPane().setLayout(null);
		choiceDialog.getContentPane().add(lblError);
		choiceDialog.getContentPane().add(btn2);
		
		// Setting visible!
		choiceDialog.setVisible(true);
	}
	
}