package ontouml2text.ui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.Dimension;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JCheckBox;

import ontouml2text.core.Writer;

import RefOntoUML.Association;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class Options extends JDialog implements ActionListener, ItemListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2698550872201019269L;
	
	private JTextField filePath, specConnector, genConnector, attConnector;
	private JCheckBox definitionCheckBox, genCheckBox, specCheckBox, attCheckBox, assocCheckBox;
	private JButton selectSTButton, selectTSButton, selectAllButton, selectPathBtn, importTextBtn, exportToCSSBtn;
	private JLabel label, label_1, label_2, label_3, label_4, lblPessoa;
	private AssociationsPanel assocPane;
	private JPanel assocOptions;
	
	RefOntoUML.Model model;
	OntoUMLParser ontoParser;
	String modelDescr;

	public Options(JFrame frame, RefOntoUML.Model refmodel)
	{
		super(frame, true);
		
		initVariables(refmodel);
		
		initGUI();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	private void initVariables(RefOntoUML.Model refmodel)
	{
		model = refmodel;
		ontoParser = new OntoUMLParser(model);
	}

	/**
	 * Create the dialog.
	 */
	public void initGUI() {
		setBounds(100, 100, 500, 400);
		getContentPane().setLayout(new BorderLayout());
		{
			JTabbedPane optionsPane = new JTabbedPane(JTabbedPane.TOP);
			optionsPane.setPreferredSize(new Dimension(500, 400));
			getContentPane().add(optionsPane, BorderLayout.CENTER);
			{
				JPanel mainOptions = new JPanel();
				optionsPane.addTab("Main Options", null, mainOptions, null);
				
				{
					filePath = new JTextField();
					filePath.setAlignmentY(Component.TOP_ALIGNMENT);
					filePath.setAlignmentX(Component.LEFT_ALIGNMENT);
					filePath.setColumns(10);
					
					selectPathBtn = new JButton("Select Path");
					selectPathBtn.addActionListener(this);
				}
				{
					definitionCheckBox = new JCheckBox("Include Classes Definitions");
					definitionCheckBox.addItemListener(this);
				}
				{
					genCheckBox = new JCheckBox("Include Generalizations");
					genCheckBox.setSelected(true);
					genCheckBox.addItemListener(this);
					
					label = new JLabel("Homem");
					
					genConnector = new JTextField();
					genConnector.setText("\u00E9 um tipo de");
					genConnector.setColumns(10);
					
					lblPessoa = new JLabel("Pessoa");
				}
				{
					specCheckBox = new JCheckBox("Include Specializations");
					specCheckBox.setSelected(true);
					specCheckBox.addItemListener(this);
					
					label_1 = new JLabel("Homem");
					
					specConnector = new JTextField();
					specConnector.setText("pode ser");
					specConnector.setColumns(10);
					
					label_3 = new JLabel("Crian\u00E7a, Adulto ou Idoso");
				}
				{
					attCheckBox = new JCheckBox("Include Attributes");
					attCheckBox.setSelected(true);
					attCheckBox.addItemListener(this);
					
					label_2 = new JLabel("Homem");
				
					attConnector = new JTextField();
					attConnector.setText("possui");
					attConnector.setColumns(10);
					
					label_4 = new JLabel("um nome e um CPF");
				}
				{
					assocCheckBox = new JCheckBox("Include Associations");
					assocCheckBox.setSelected(true);
					assocCheckBox.addItemListener(this);
				}
				{
					importTextBtn = new JButton("Import Text");
					importTextBtn.addActionListener(this);
					exportToCSSBtn = new JButton("Export to CSS");
					exportToCSSBtn.addActionListener(this);
				}
				
				GroupLayout gl_mainOptions = new GroupLayout(mainOptions);
				gl_mainOptions.setHorizontalGroup(
					gl_mainOptions.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_mainOptions.createSequentialGroup()
							.addGap(82)
							.addGroup(gl_mainOptions.createParallelGroup(Alignment.LEADING)
								.addComponent(label_2)
								.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
							.addGap(4)
							.addGroup(gl_mainOptions.createParallelGroup(Alignment.LEADING)
								.addComponent(specConnector, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(genConnector, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(attConnector, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_mainOptions.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPessoa)
								.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_4))
							.addGap(97))
						.addGroup(gl_mainOptions.createSequentialGroup()
							.addGap(82)
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(362, Short.MAX_VALUE))
						.addGroup(gl_mainOptions.createSequentialGroup()
							.addContainerGap()
							.addComponent(filePath, GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(selectPathBtn)
							.addGap(18))
						.addGroup(gl_mainOptions.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_mainOptions.createParallelGroup(Alignment.LEADING)
								.addComponent(definitionCheckBox)
								.addComponent(genCheckBox, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
								.addComponent(specCheckBox, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
								.addComponent(attCheckBox, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
								.addComponent(assocCheckBox))
							.addContainerGap(316, Short.MAX_VALUE))
						.addGroup(gl_mainOptions.createSequentialGroup()
							.addGap(78)
							.addComponent(importTextBtn)
							.addGap(39)
							.addComponent(exportToCSSBtn)
							.addContainerGap(184, Short.MAX_VALUE))
				);
				gl_mainOptions.setVerticalGroup(
					gl_mainOptions.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_mainOptions.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_mainOptions.createParallelGroup(Alignment.BASELINE)
								.addComponent(selectPathBtn)
								.addComponent(filePath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(definitionCheckBox)
							.addGap(14)
							.addComponent(genCheckBox)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_mainOptions.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPessoa)
								.addComponent(label)
								.addComponent(genConnector, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(specCheckBox)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_mainOptions.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_3)
								.addComponent(label_1)
								.addComponent(specConnector, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(7)
							.addGroup(gl_mainOptions.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_mainOptions.createSequentialGroup()
									.addComponent(attCheckBox)
									.addGap(8)
									.addComponent(label_2))
								.addGroup(gl_mainOptions.createParallelGroup(Alignment.BASELINE)
									.addComponent(attConnector, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(label_4)))
							.addGap(18)
							.addComponent(assocCheckBox)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_mainOptions.createParallelGroup(Alignment.BASELINE)
								.addComponent(importTextBtn)
								.addComponent(exportToCSSBtn))
							.addGap(8))
				);
				mainOptions.setLayout(gl_mainOptions);
			}
			{
				assocOptions = new JPanel();
				optionsPane.addTab("Associations", null, assocOptions, null);
				{
					assocPane = new AssociationsPanel(ontoParser);
					assocOptions.add(assocPane, BorderLayout.CENTER);
				}
				{
					JPanel buttonPane = new JPanel();
//					buttonPane.setPreferredSize(new Dimension(600, 40));
					buttonPane.setLayout(new GridLayout(0, 3, 0, 0));
					assocOptions.add(buttonPane, BorderLayout.SOUTH);
					{
						selectSTButton = new JButton("Source->Target only");
						selectSTButton.addActionListener(this);
						selectTSButton = new JButton("Target->Source only");
						selectTSButton.addActionListener(this);
						selectAllButton = new JButton("Select All");
						selectAllButton.addActionListener(this);
					}
					buttonPane.add(selectSTButton);
					buttonPane.add(selectTSButton);
					buttonPane.add(selectAllButton);
				}
			}
		}
		{
			JPanel samplePane = new JPanel();
			getContentPane().add(samplePane, BorderLayout.SOUTH);
			samplePane.setLayout(new GridLayout(0, 1, 0, 0));
			
			JLabel lblSample = new JLabel("Text Sample:");
			samplePane.add(lblSample);
			
			JLabel sample = new JLabel("Homem \u00E9 um tipo de Pessoa. Homem pode ser Crian\u00E7a, Adulto, Idoso. Homem possui um nome e um CPF.");
			samplePane.add(sample);
		}
	}

	@Override
	public void actionPerformed(ActionEvent a) {
		if (a.getSource() == selectSTButton)
		{
			for (SingleAssociationPanel assocPanel : assocPane.getAssocPanelList())
			{
				assocPanel.clearTS();
				assocPanel.selectST();
			}
		}
		else if (a.getSource() == selectTSButton)
		{
			for (SingleAssociationPanel assocPanel : assocPane.getAssocPanelList())
			{
				assocPanel.clearST();
				assocPanel.selectTS();
			}
		}
		else if (a.getSource() == selectAllButton)
		{
			for (SingleAssociationPanel assocPanel : assocPane.getAssocPanelList())
			{
				assocPanel.selectST();
				assocPanel.selectTS();
			}
		}
		else if (a.getSource() == selectPathBtn)
		{
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Select File to Export");
			if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				
			}
//			FileNameExtensionFilter filter = new FileNameExtensionFilter("XMI, XML (*.xmi, *.xml)", "xmi", "xml");
//			fileChooser.addChoosableFileFilter(filter);
//			fileChooser.setFileFilter(filter);
//			fileChooser.setAcceptAllFileFilterUsed(false);
		}
		else if (a.getSource() == importTextBtn)
		{
			Writer writer = new Writer(ontoParser);
			
			Map<Association,Integer> assocMap = assocPane.getAssDirectionMap();
			
			writer.runDescriptionGeneration(assocMap, definitionCheckBox.isSelected(), genCheckBox.isSelected(), 
					specCheckBox.isSelected(), attCheckBox.isSelected(), assocCheckBox.isSelected());
			
			modelDescr = writer.getTextDescription();
			this.dispose();
		}
		else if (a.getSource() == exportToCSSBtn)
		{
			Writer writer = new Writer(ontoParser);
			
			Map<Association,Integer> assocMap = assocPane.getAssDirectionMap();
			
			writer.runDescriptionGeneration(assocMap, definitionCheckBox.isSelected(), genCheckBox.isSelected(), 
					specCheckBox.isSelected(), attCheckBox.isSelected(), assocCheckBox.isSelected());
			writer.exportToCSS();
			
			modelDescr = "CSS";
			this.dispose();
		}
		else
			System.out.println("Controle 1");
	}

	@Override
	public void itemStateChanged(ItemEvent i) {
		if (i.getSource() == genCheckBox)
		{
			JCheckBox chckBoxAux = (JCheckBox) i.getSource();
			if (chckBoxAux.isSelected()) {
				label.setEnabled(true);
				genConnector.setEnabled(true);
				lblPessoa.setEnabled(true);
			}
			else
			{
				label.setEnabled(false);
				genConnector.setEnabled(false);
				lblPessoa.setEnabled(false);
			}
		}
		else if (i.getSource() == specCheckBox)
		{
			JCheckBox chckBoxAux = (JCheckBox) i.getSource();
			if (chckBoxAux.isSelected()) {
				label_1.setEnabled(true);
				specConnector.setEnabled(true);
				label_3.setEnabled(true);
			}
			else
			{
				label_1.setEnabled(false);
				specConnector.setEnabled(false);
				label_3.setEnabled(false);
			}
		}
		else if (i.getSource() == attCheckBox)
		{
			JCheckBox chckBoxAux = (JCheckBox) i.getSource();
			if (chckBoxAux.isSelected()) {
				label_2.setEnabled(true);
				attConnector.setEnabled(true);
				label_4.setEnabled(true);
			}
			else
			{
				label_2.setEnabled(false);
				attConnector.setEnabled(false);
				label_4.setEnabled(false);
			}
		}
		else if (i.getSource() == assocCheckBox)
		{
			JCheckBox chckBoxAux = (JCheckBox) i.getSource();
			if (chckBoxAux.isSelected()) {
				assocOptions.setEnabled(true);
			}
			else
				assocOptions.setEnabled(false);
		}
		else
			System.out.println("Controle 2");
	}
	
	public String getModelDescription()
	{
		return modelDescr;
	}
}
