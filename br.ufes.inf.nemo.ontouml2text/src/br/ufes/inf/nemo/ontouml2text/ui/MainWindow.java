/*package br.ufes.inf.nemo.ontouml2text.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.Property;
import RefOntoUML.RefOntoUMLPackage;
import RefOntoUML.Type;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.ontouml2text.core.Writer;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.Category;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.Characterization;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.Collective;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.ComponentOf;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.DescriptionCategory;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.DescriptionFunction;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.DescriptionSpace;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.Formal;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.Kind;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.Material;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.Mediation;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.MemberOf;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.Mixin;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.Mode;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.Quantity;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.Relator;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.Role;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.RoleMixin;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.SubcollectiveOf;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.SubquantityOf;

public class MainWindow {

	private JFrame mainframe;
	private JDialog OntoUML2TextDialog;
	private OptionsPanel mainOptions;
	private AssociationsPanel assocOptions;
	private OntoUMLParser ontoParser;
	private String modelDescr;

	/**
	 * Launch the application.
	 
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
	 
	public MainWindow(){
		initGUI();
	}
	
	public MainWindow(JFrame frame, RefOntoUML.Package refmodel)
	{
		this.mainframe = frame;
		this.ontoParser = new OntoUMLParser(refmodel);
		initGUI();
	}

	/**
	 * Initialize the contents of the frame.
	 
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
			/*{
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
			
			/*JLabel lblSample = new JLabel("Text Sample:");
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
	
	public void generateModelDescription(){
		//Writer writer = new Writer(ontoParser);
		
		
		// ------------ INÍCIO ----------\\
		Set <Classifier> classfSet = ontoParser.getAllInstances(Classifier.class);	
		Set<Generalization> gen = ontoParser.getAllInstances(Generalization.class);

		//Create the Description Space object
		DescriptionSpace descSpace =  new DescriptionSpace();
		
		int sourceLower,sourceUpper,targetLower,targetUpper;
		DescriptionCategory source;
		DescriptionCategory target;
		
		//Populate the Lists (Categories and Functions)
		for (Classifier classf : classfSet){
			
			if(classf instanceof RefOntoUML.Association){
				System.out.println("Association");
				source = FindCategory(((Association) classf).getEndType().get(0));
				target = FindCategory(((Association) classf).getEndType().get(1));
				
				//Find Multiplicity
				sourceLower = FindLowerMultiplicity(((Association) classf).getMemberEnd().get(0));
				sourceUpper = FindUpperMultiplicity(((Association) classf).getMemberEnd().get(0));

				targetLower = FindLowerMultiplicity(((Association) classf).getMemberEnd().get(1));
				targetUpper = FindUpperMultiplicity(((Association) classf).getMemberEnd().get(1));
				
				if(classf instanceof RefOntoUML.Characterization){	
					DescriptionFunction mat = new Characterization(classf.getName(),source,target, sourceLower, sourceUpper, targetLower, targetUpper);
					descSpace.getFunctions().add(mat);
				}
				if(classf instanceof RefOntoUML.componentOf){
					DescriptionFunction mat = new ComponentOf(classf.getName(), source, target, sourceLower, sourceUpper, targetLower, targetUpper, false, false, false);
					descSpace.getFunctions().add(mat);
				}
				if(classf instanceof RefOntoUML.FormalAssociation){
					DescriptionFunction mat = new Formal(classf.getName(), source, target, sourceLower,sourceUpper, targetLower, targetUpper);
					descSpace.getFunctions().add(mat);
				}
				if(classf instanceof RefOntoUML.MaterialAssociation){
					DescriptionFunction mat = new Material(classf.getName(), source,target, sourceLower, sourceUpper,targetLower, targetUpper);
					descSpace.getFunctions().add(mat);
				}
				if(classf instanceof RefOntoUML.Mediation){			
					DescriptionFunction mat = new Mediation(classf.getName(), source, target, sourceLower, sourceUpper, targetLower,targetUpper);
					descSpace.getFunctions().add(mat);
				}
				if(classf instanceof RefOntoUML.memberOf){
					DescriptionFunction mat = new MemberOf(classf.getName(), source, target, sourceLower, sourceUpper, targetLower,targetUpper, false, false, false);
					descSpace.getFunctions().add(mat);
				}
				if(classf instanceof RefOntoUML.subCollectionOf){
					DescriptionFunction mat = new SubcollectiveOf(classf.getName(), source, target, sourceLower, sourceUpper, targetLower,targetUpper, false, false, false);
					descSpace.getFunctions().add(mat);
				}
				if(classf instanceof RefOntoUML.subQuantityOf){
					DescriptionFunction mat = new SubquantityOf(classf.getName(), source, target, sourceLower, sourceUpper,targetLower,targetUpper, false, false, false);
					descSpace.getFunctions().add(mat);
				}

				//	if(classf instanceof RefOntoUML.partof){}
					
			} else {
			
			if(classf instanceof RefOntoUML.Category){
				DescriptionCategory mat = new Category(classf.getName());
				descSpace.getCategories().add(mat);
			}
			
			if(classf instanceof RefOntoUML.Collective){
				DescriptionCategory mat = new Collective(classf.getName());
				descSpace.getCategories().add(mat);
			}
			if(classf instanceof RefOntoUML.Kind){
				DescriptionCategory mat = new Kind(classf.getName());
				descSpace.getCategories().add(mat);
			}
			if(classf instanceof RefOntoUML.Mixin){
				DescriptionCategory mat = new Mixin(classf.getName());
				descSpace.getCategories().add(mat);
			}
			if(classf instanceof RefOntoUML.Mode){
				DescriptionCategory mat = new Mode(classf.getName());
				descSpace.getCategories().add(mat);
			}			
			if(classf instanceof RefOntoUML.Quantity){
				DescriptionCategory mat = new Quantity(classf.getName());
				descSpace.getCategories().add(mat);
			}
			if(classf instanceof RefOntoUML.Relator){
				DescriptionCategory mat = new Relator(classf.getName());
				descSpace.getCategories().add(mat);
			}
			if(classf instanceof RefOntoUML.Role){
				DescriptionCategory mat = new Role(classf.getName());
				descSpace.getCategories().add(mat);
			}
			if(classf instanceof RefOntoUML.RoleMixin){
				DescriptionCategory mat = new RoleMixin(classf.getName());
				descSpace.getCategories().add(mat);
			}
			}
		}
		
		//Populate the GeneralizationList 
		for(Generalization g : gen){
			DescriptionFunction gn = new br.ufes.inf.nemo.ontouml2text.descriptionSpace.Generalization(null, null, null, 0, 0, 0, 0);
			descSpace.getFunctions().add(gn);
		}

		/*
		Map<Association,Integer> assocMap = this.assocOptions.getAssDirectionMap();
		
		boolean[] optionsList = mainOptions.getOptionsList();
		writer.runDescriptionGeneration(assocMap, optionsList[0], optionsList[1], 
				optionsList[2], optionsList[3], optionsList[4]);
		
		this.modelDescr = writer.getTextDescription();
	}
	
	public DescriptionCategory FindCategory(Type type){
		if(type instanceof RefOntoUML.Category){
			DescriptionCategory mat = new Category(type.getName());
			return mat;
		}
		if(type instanceof RefOntoUML.Collective){
			DescriptionCategory mat = new Collective(type.getName());
			return mat;
		}	
		if(type instanceof RefOntoUML.Kind){
			DescriptionCategory mat = new Kind(type.getName());
			return mat;
		}
		if(type instanceof RefOntoUML.Mixin){
			DescriptionCategory mat = new Mixin(type.getName());
			return mat;
		}
		if(type instanceof RefOntoUML.Mode){
			DescriptionCategory mat = new Mode(type.getName());
			return mat;
		}
	//	if(obj instanceof RefOntoUML.partof){}
		if(type instanceof RefOntoUML.Quantity){
			DescriptionCategory mat = new Quantity(type.getName());
			return mat;
		}
		if(type instanceof RefOntoUML.Relator){
			DescriptionCategory mat = new Relator(type.getName());
			return mat;
		}
		if(type instanceof RefOntoUML.Role){
			DescriptionCategory mat = new Role(type.getName());
			return mat;
		}
		if(type instanceof RefOntoUML.RoleMixin){
			DescriptionCategory mat = new RoleMixin(type.getName());
			return mat;
		}
		return null;
	}
	
	public int FindLowerMultiplicity(Property p){
		return p.getLower();

	}
	
	public int FindUpperMultiplicity(Property p){
		return p.getUpper();
	}
	
	
	// ---------- FIM -------- \\
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
}*/
