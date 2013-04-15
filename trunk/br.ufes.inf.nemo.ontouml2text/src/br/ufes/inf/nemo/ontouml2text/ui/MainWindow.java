package br.ufes.inf.nemo.ontouml2text.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.io.File;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import RefOntoUML.Association;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.ontouml2text.core.Writer;

public class MainWindow {

	private JFrame mainframe;
	private JDialog OntoUML2TextDialog;
	private OptionsPanel mainOptions;
	private AssociationsPanel assocOptions;
	private OntoUMLParser ontoParser;
	private String modelDescr;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.mainframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow()
	{
		initGUI();
	}
	
	public MainWindow(JFrame frame, RefOntoUML.Model refmodel)
	{
		this.mainframe = frame;
		this.ontoParser = new OntoUMLParser(refmodel);
		
		initGUI();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initGUI()
	{
		OntoUML2TextDialog = new JDialog(mainframe, true);
		OntoUML2TextDialog.setBounds(100, 100, 650, 500);
		OntoUML2TextDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		OntoUML2TextDialog.getContentPane().setLayout(new BorderLayout());
		{ // ToolBar
			CommandToolBar commandTB = new CommandToolBar(this);
			OntoUML2TextDialog.getContentPane().add(commandTB, BorderLayout.WEST);
		}
		{ // Main Section (Options)
			JTabbedPane optionsPane = new JTabbedPane(JTabbedPane.TOP);
			optionsPane.setPreferredSize(new Dimension(450, 350));
			OntoUML2TextDialog.getContentPane().add(optionsPane, BorderLayout.CENTER);
			{
				mainOptions = new OptionsPanel();
				optionsPane.addTab("Main Options", null, mainOptions, null);
			}
			{
				assocOptions = new AssociationsPanel(this.ontoParser);
				optionsPane.addTab("Associations", null, assocOptions, null);
			}
		}
		{ // Text Sample Bar
			JPanel samplePane = new JPanel();
			OntoUML2TextDialog.getContentPane().add(samplePane, BorderLayout.SOUTH);
			samplePane.setLayout(new GridLayout(0, 1, 0, 0));
			
			JLabel lblSample = new JLabel("Text Sample:");
			samplePane.add(lblSample);
			
			JLabel sample = new JLabel("Homem \u00E9 um tipo de Pessoa. Homem pode ser Crian\u00E7a, Adulto, Idoso. Homem possui um nome e um CPF.");
			samplePane.add(sample);
		}
	}
	
	public JDialog getAppDialog()
	{
		return this.OntoUML2TextDialog;
	}

	public String getModelDescription()
	{
		return this.modelDescr;
	}
	
	public void generateModelDescription()
	{
		Writer writer = new Writer(ontoParser);
		
		Map<Association,Integer> assocMap = this.assocOptions.getAssDirectionMap();
		
		boolean[] optionsList = mainOptions.getOptionsList();
		writer.runDescriptionGeneration(assocMap, optionsList[0], optionsList[1], 
				optionsList[2], optionsList[3], optionsList[4]);
		
		this.modelDescr = writer.getTextDescription();
	}
	
	public void generateCSV()
	{
		Writer writer = new Writer(ontoParser);
		
		Map<Association,Integer> assocMap = this.assocOptions.getAssDirectionMap();
		
		boolean[] optionsList = mainOptions.getOptionsList();
		writer.runDescriptionGeneration(assocMap, optionsList[0], optionsList[1], 
				optionsList[2], optionsList[3], optionsList[4]);
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Select Path to Save File");
//		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV (*.csv)", "csv");
//		fileChooser.addChoosableFileFilter(filter);
//		fileChooser.setFileFilter(filter);
//		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setSelectedFile(new File("fileToSave"));
		if (fileChooser.showSaveDialog(OntoUML2TextDialog) == JFileChooser.APPROVE_OPTION)
		{
			writer.exportToCSV(fileChooser.getSelectedFile().getPath());
		}
		
		this.modelDescr = "CSV";
	}
}
