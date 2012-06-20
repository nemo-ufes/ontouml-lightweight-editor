package br.ufes.inf.nemo.ontouml.xmi2refontouml.ui;

import it.cnr.imaa.essi.lablib.gui.checkboxtree.CheckboxTree;
import it.cnr.imaa.essi.lablib.gui.checkboxtree.TreeCheckingModel;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import br.ufes.inf.nemo.ontouml.xmi2refontouml.transformation.Mediator;
import br.ufes.inf.nemo.ontouml.xmi2refontouml.transformation.RefOntoCreator;
import br.ufes.inf.nemo.ontouml.xmi2refontouml.util.ChckBoxTreeNodeElem;
import br.ufes.inf.nemo.ontouml.xmi2refontouml.util.JTextAreaOutputSteam;


public class AppFrame extends JFrame implements WindowListener, ActionListener, TreeSelectionListener {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton readButton, savetoButton, saveButton, transformButton, validateButton, filterButton, alloyButton;
	private JTextArea log, infoTextArea;
	private JFileChooser readFileChooser, saveFileChooser;
	private JTextField readFilePath, saveFilePath;
	private CheckboxTree modelTree;
	private JScrollPane treeScrollPane;
    
    Mediator TransfManager = new Mediator();

    public AppFrame(String title) {

        super(title);
        setLayout(new FlowLayout());
        //addWindowListener(this);
        this.setSize(600,445);
        
        installFilePanel();
        installCommandButtons();
		installLogPanel();
        installTreePanel();
		
		//Set the 'log' TextArea to be the default output
		OutputStream out = new JTextAreaOutputSteam(log);
		System.setOut(new PrintStream(out, true));
		System.setErr(new PrintStream(out, true));
		
    }
    
    private void installFilePanel() {
    	/* Start to set the File select panel components */
        // XMI file selection
        JLabel lblXmiFile = new JLabel("XMI File");
        readFilePath = new JTextField();
        readFilePath.setColumns(30);
        readFilePath.setEditable(false);
        readButton = new JButton("Select Path");
        readButton.addActionListener(this);
        readFileChooser = new JFileChooser();
        //readFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //readFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        //Set the Layout
        Box readHB = Box.createHorizontalBox();
        readHB.add(lblXmiFile);
        readHB.add(readFilePath);
        readHB.add(readButton);
        
        //Save to selection
        JLabel lblSavePath = new JLabel("Save Path");
        saveFilePath = new JTextField();
        saveFilePath.setColumns(30);
        saveFilePath.setEditable(false);
        savetoButton = new JButton("Select Path");
        savetoButton.addActionListener(this);
        saveFileChooser = new JFileChooser();
        //saveFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //saveFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        //Set the Layout
        Box saveHB = Box.createHorizontalBox();
        saveHB.add(lblSavePath);
        saveHB.add(saveFilePath);
        saveHB.add(savetoButton);
        
        Box filesVerticalBox = Box.createVerticalBox();
        filesVerticalBox.add(readHB);
        filesVerticalBox.add(saveHB);
        
        JPanel FilesPanel = new JPanel();
		FilesPanel.add(filesVerticalBox);
		/* End the File select panel settings*/
		add(FilesPanel, BorderLayout.NORTH);
    }
    
    private void installCommandButtons() {
    	/* Start to set the Command Buttons panel components */
		//Create the command Buttons
		transformButton = new JButton("Parse File");
		transformButton.addActionListener(this);
		validateButton = new JButton("Validade");
		validateButton.addActionListener(this);
		validateButton.setEnabled(false);
		filterButton = new JButton("Delete");
		filterButton.addActionListener(this);
		filterButton.setEnabled(false);
		saveButton = new JButton("Salvar");
		saveButton.addActionListener(this);
		saveButton.setEnabled(false);
		alloyButton = new JButton("2Alloy");
		alloyButton.addActionListener(this);
		alloyButton.setEnabled(false);
		//Layout
		Box buttonsVerticalBox = Box.createVerticalBox();
		buttonsVerticalBox.add(transformButton);
		buttonsVerticalBox.add(validateButton);
		buttonsVerticalBox.add(filterButton);
		buttonsVerticalBox.add(saveButton);
		buttonsVerticalBox.add(alloyButton);
		
		JPanel commandPanel = new JPanel();
		commandPanel.add(buttonsVerticalBox);
		/* End the Command Buttons panel settings*/
		add(commandPanel, BorderLayout.EAST);
    }
    
    private void installLogPanel() {
    	/* Start to set the log Panel */
        log = new JTextArea(6,40);
        log.setMargin(new Insets(5,5,5,5));
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);
        /* End log Panel settings */
        
        /* Start to set the center Panel */
        infoTextArea = new JTextArea(5,20);
        infoTextArea.setMargin(new Insets(5,5,5,5));
        infoTextArea.setEditable(false);
        JPanel infoPanel = new JPanel();
        infoPanel.add(infoTextArea);
        infoPanel.setPreferredSize(new Dimension(250, 200));
        /* End center Panel settings */
        add(infoPanel, BorderLayout.CENTER);
        add(logScrollPane, BorderLayout.SOUTH);
    }
    
    public void installTreePanel() {
    	/* Start to set the tree Panel */		
    	treeScrollPane = new JScrollPane();
    	treeScrollPane.setPreferredSize(new Dimension(250, 200));
    	treeScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    	/* End tree Panel settings */
    	add(treeScrollPane, BorderLayout.WEST);
    }

    public void actionPerformed(ActionEvent e) {
    	//Handle readButton action.
        if (e.getSource() == readButton) {
            int returnVal = readFileChooser.showOpenDialog(AppFrame.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = readFileChooser.getSelectedFile();
                readFilePath.setText(file.getAbsolutePath());
                saveFilePath.setText(file.getAbsolutePath().split("\\.")[0] + ".refontouml");
                TransfManager.READ_FILE_ADDRESS = readFilePath.getText();
                TransfManager.SAVE_FILE_ADDRESS = file.getAbsolutePath().split("\\.")[0] + ".refontouml";
                RefOntoCreator.save_file_address = TransfManager.SAVE_FILE_ADDRESS;
                System.out.println("Source file selected: " + readFilePath.getText() + ".");
                System.out.println("File/Path to save selected: " + file.getAbsolutePath() + ".");
                //Sets the path into alloy transformation
                String dir = readFileChooser.getSelectedFile().getAbsolutePath().replace(readFileChooser.getSelectedFile().getName(),"");
                br.ufes.inf.nemo.ontouml.refontouml2alloy.Launcher.dirPath = dir;
                br.ufes.inf.nemo.ontouml.refontouml2alloy.Launcher.alsPath = file.getAbsolutePath().split("\\.")[0] + ".als";
                br.ufes.inf.nemo.ontouml.refontouml2alloy.Reader.inputPath = file.getAbsolutePath().split("\\.")[0] + ".refontouml";
                
            } else {
                System.out.println("File selection cancelled by user.");
                
            }
            
        //Handle saveButton action.
        } else if (e.getSource() == savetoButton) {
        	int returnVal = saveFileChooser.showSaveDialog(AppFrame.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = saveFileChooser.getSelectedFile();
                saveFilePath.setText(file.getAbsolutePath().split("\\.")[0] + ".refontouml");
                TransfManager.SAVE_FILE_ADDRESS = file.getAbsolutePath().split("\\.")[0] + ".refontouml";
                RefOntoCreator.save_file_address = TransfManager.SAVE_FILE_ADDRESS; 
                System.out.println("File/Path to save selected: " + file.getAbsolutePath() + ".");
                //Sets the path into alloy transformation
                String dir = saveFileChooser.getSelectedFile().getAbsolutePath().replace(saveFileChooser.getSelectedFile().getName(),"");
                br.ufes.inf.nemo.ontouml.refontouml2alloy.Launcher.dirPath = dir;
                br.ufes.inf.nemo.ontouml.refontouml2alloy.Launcher.alsPath = file.getAbsolutePath().split("\\.")[0] + ".als";
                br.ufes.inf.nemo.ontouml.refontouml2alloy.Reader.inputPath = file.getAbsolutePath().split("\\.")[0] + ".refontouml";
                
            } else {
            	System.out.println("File selection cancelled by user.");
                
            }
            
        //Handle transformation button
        } else if (e.getSource() == transformButton) {
        	
        	if (fileIsSelected()) {
        	
	        	DefaultMutableTreeNode root = TransfManager.parse();
	        	if (root != null) {
		        	modelTree = new CheckboxTree(root);
		        	modelTree.getCheckingModel().setCheckingMode(TreeCheckingModel.CheckingMode.PROPAGATE_PRESERVING_CHECK);
		        	modelTree.addTreeSelectionListener(this);
		        	treeScrollPane.getViewport().add(modelTree);
		        	filterButton.setEnabled(true);
		        	saveButton.setEnabled(true);
		        	validateButton.setEnabled(true);
	        	}
        	}
        	
        } else if (e.getSource() == validateButton) {
        	TransfManager.validate();
            
        } else if (e.getSource() == filterButton) {
        	TransfManager.filter(modelTree);
        	modelTree.clearChecking();
        	
        } else if (e.getSource() == saveButton) {
        	
        	if (fileIsSelected()) {
        		TransfManager.save();
        		alloyButton.setEnabled(true);
        	}
        	
        } else if (e.getSource() == alloyButton) {
        	
        	if (fileIsSelected()) {
	        	try {
	        		//Calls the transformation
	        		br.ufes.inf.nemo.ontouml.refontouml2alloy.Launcher.call();
				} catch (Exception e1) {
					System.out.println("An error has occurred during the transformation.");
				}
        	}
        }
    }
    
    public Boolean fileIsSelected() {
    	if (TransfManager.READ_FILE_ADDRESS == null) {
    		System.out.println("Selecione arquivo XMI que será lido.");
    		return false;
    		
    	} else if (TransfManager.SAVE_FILE_ADDRESS == null) {
    		System.out.println("Selecione caminho onde arquivo será salvo.");
    		return false;
    		
    	} else
    		return true;
    }

    public void windowClosing(WindowEvent e) {
            dispose();
            System.exit(0);
    }

    public void windowOpened(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
    public void windowClosed(WindowEvent e) {}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
		ChckBoxTreeNodeElem chckNode = (ChckBoxTreeNodeElem) node.getUserObject();
		String info = chckNode.getInfo();
		infoTextArea.setText(info);
	}
	
}