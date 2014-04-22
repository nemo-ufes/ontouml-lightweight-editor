package br.ufes.inf.nemo.meronymic_validation.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import RefOntoUML.Classifier;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.meronymic_validation.checkers.PreConditionChecker;

import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
//import br.ufes.inf.nemo.oled.AppFrame;

public class MeronymicValidationDialog extends JDialog {

	private static final long serialVersionUID = -7859965827033567316L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
//	private AppFrame frame;

	private static Color GREEN = new Color(50, 205, 50);
	private static Color RED = new Color(255, 0, 0);
	private static Color YELLOW = new Color(255, 165, 0);
	
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_3;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextPane consoleTextPane;
	private JButton btnCheckPreconditions;
	private JTable table;

	private OntoUMLParser parser;
	private PreConditionChecker inferenceMaker;
	/**
	 * Create the dialog.
	 */
	public MeronymicValidationDialog(OntoUMLParser parser/*AppFrame frame*/) {
		super();
//		super(frame);
//		this.frame = frame;
		this.parser = parser;
		inferenceMaker = new PreConditionChecker(parser);
		
		setResizable(false);
		setTitle("Meronymic Transitivity Validation");
		setBounds(100, 100, 780, 726);
		getContentPane().setLayout(new GridLayout(1,1));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		contentPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		contentPanel.setLayout(null);
		
		JLabel lblChooseTheTypes = new JLabel("Choose the types of meronymic relations to infer:");
		lblChooseTheTypes.setBounds(10, 335, 324, 14);
		contentPanel.add(lblChooseTheTypes);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("ComponentOf");
		chckbxNewCheckBox.setBounds(10, 356, 93, 23);
		contentPanel.add(chckbxNewCheckBox);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("MemberOf");
		chckbxNewCheckBox_1.setBounds(10, 382, 75, 23);
		contentPanel.add(chckbxNewCheckBox_1);
		
		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("SubCollectionOf");
		chckbxNewCheckBox_2.setBounds(10, 408, 101, 23);
		contentPanel.add(chckbxNewCheckBox_2);
		
		JButton btnStop = new JButton("Stop");
		btnStop.setBounds(303, 447, 57, 23);
		contentPanel.add(btnStop);
		btnStop.setActionCommand("OK");
		{
			JButton cancelButton = new JButton("Close");
			cancelButton.setBounds(370, 447, 59, 23);
			contentPanel.add(cancelButton);
			cancelButton.setActionCommand("Cancel");
		}
		{
			JButton okButton = new JButton("Infer");
			okButton.setBounds(236, 447, 60, 23);
			contentPanel.add(okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		
		textField = new JTextField();
		textField.setBounds(10, 481, 551, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblProgress = new JLabel("Progress");
		lblProgress.setBounds(10, 456, 324, 14);
		contentPanel.add(lblProgress);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(571, 481, 188, 20);
		contentPanel.add(progressBar);
		
		JLabel lblComponentOfResult = new JLabel("(#)");
		lblComponentOfResult.setBounds(112, 356, 222, 23);
		lblComponentOfResult.setFont(new Font(lblComponentOfResult.getFont().getFontName(), Font.BOLD,lblComponentOfResult.getFont().getSize()));
		contentPanel.add(lblComponentOfResult);
		
		JLabel lblMemberOfResult = new JLabel("(#)");
		lblMemberOfResult.setBounds(91, 382, 243, 23);
		lblMemberOfResult.setFont(new Font(lblMemberOfResult.getFont().getFontName(), Font.BOLD,lblMemberOfResult.getFont().getSize()));
		contentPanel.add(lblMemberOfResult);
		
		JLabel lblSubCollectionOf = new JLabel("(#)");
		lblSubCollectionOf.setBounds(112, 408, 222, 23);
		lblSubCollectionOf.setFont(new Font(lblSubCollectionOf.getFont().getFontName(), Font.BOLD,lblSubCollectionOf.getFont().getSize()));
		contentPanel.add(lblSubCollectionOf);
		
		tabbedPane.addTab("Infer", contentPanel);
		
		btnCheckPreconditions = new JButton("Stop");
		btnCheckPreconditions.setBounds(699, 188, 60, 23);
		btnCheckPreconditions.addActionListener(actionCheck);
		contentPanel.add(btnCheckPreconditions);
		
		consoleTextPane = new JTextPane();
		consoleTextPane.setText("asdasdasd\r\nasd\r\nasd\r\nas\r\nd\r\nasd\r\nas\r\nda\r\nsd\r\nasd\r\na\r\nsd\r\nasd\r\nas\r\ndas");
		consoleTextPane.setBackground(Color.GRAY);
		consoleTextPane.setBounds(10, 556, 419, 102);
		contentPanel.add(consoleTextPane);
		
		JLabel lblToValidateThe = new JLabel("To validate the transitivity of part-whole relations, the model must pass the following tests:");
		lblToValidateThe.setBounds(10, 10, 749, 15);
		contentPanel.add(lblToValidateThe);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel.setBounds(10, 32, 749, 145);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		int STD_X = 10;
		int STD_HEIGHT = 15;
		int STD_WIDTH = 145;
		int STD_X_RESULT = 165;
		int STD_RESULT_WIDTH = 58;
		
		JLabel lblHierarchyCycles = new JLabel("Hierarchy cycles");
		lblHierarchyCycles.setBounds(STD_X, 10, STD_WIDTH, STD_HEIGHT);
		panel.add(lblHierarchyCycles);
		
		JLabel lblValidSpecializations = new JLabel("Valid specializations");
		lblValidSpecializations.setBounds(STD_X, 32, STD_WIDTH, STD_HEIGHT);
		panel.add(lblValidSpecializations);
		
		JLabel lblValidIdentities = new JLabel("Valid identities");
		lblValidIdentities.setBounds(STD_X, 54, STD_WIDTH, STD_HEIGHT);
		panel.add(lblValidIdentities);
		
		JLabel lblAggregationKindsDefined = new JLabel("Aggregation Kinds defined");
		lblAggregationKindsDefined.setBounds(STD_X, 76, STD_WIDTH, STD_HEIGHT);
		panel.add(lblAggregationKindsDefined);
		
		JLabel lblWellFormedPartWhole = new JLabel("Well-formed part-whole relations");
		lblWellFormedPartWhole.setBounds(10, 98, 157, 15);
		panel.add(lblWellFormedPartWhole);
		
		JLabel lblPartwholeCycles = new JLabel("Part-whole cycles");
		lblPartwholeCycles.setBounds(STD_X, 120, STD_WIDTH, STD_HEIGHT);
		panel.add(lblPartwholeCycles);
		
		JLabel lblHierarchyCycleResult = new JLabel("PASSED!");
		lblHierarchyCycleResult.setBounds(240, 10, STD_RESULT_WIDTH, STD_HEIGHT);
		panel.add(lblHierarchyCycleResult);
				
		JLabel lblValidSpecializationsResult = new JLabel("PASSED!");
		lblValidSpecializationsResult.setBounds(240, 32, STD_RESULT_WIDTH, STD_HEIGHT);
		panel.add(lblValidSpecializationsResult);
		
		JLabel lblValidIdentitiesResult = new JLabel("PASSED!");
		lblValidIdentitiesResult.setBounds(240, 54, STD_RESULT_WIDTH, STD_HEIGHT);
		panel.add(lblValidIdentitiesResult);
		
		JLabel lblAggregationKindResult = new JLabel("PASSED");
		lblAggregationKindResult.setForeground(new Color(50, 205, 50));
		lblAggregationKindResult.setBounds(240, 76, STD_RESULT_WIDTH, STD_HEIGHT);
		panel.add(lblAggregationKindResult);
		
		JLabel lblWellFormedPartWholeResult = new JLabel("FAILED");
		lblWellFormedPartWholeResult.setForeground(new Color(255, 0, 0));
		lblWellFormedPartWholeResult.setBounds(240, 98, STD_RESULT_WIDTH, STD_HEIGHT);
		panel.add(lblWellFormedPartWholeResult);
		
		JLabel lblPartWholeCycles = new JLabel("ABORTED");
		lblPartWholeCycles.setForeground(new Color(255, 165, 0));
		lblPartWholeCycles.setBounds(240, 120, STD_RESULT_WIDTH, STD_HEIGHT);
		panel.add(lblPartWholeCycles);
		
		JLabel lblResults = new JLabel("Results");
		lblResults.setBounds(10, 208, 749, 14);
		contentPanel.add(lblResults);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 230, 749, 94);
		contentPanel.add(scrollPane_1);
		
		JButton button = new JButton("Run");
		button.setBounds(629, 188, 60, 23);
		contentPanel.add(button);
		getContentPane().add(tabbedPane);
		
		TitledBorder titleBorderWholeEnd = BorderFactory.createTitledBorder("Functional Whole End");
		
		TitledBorder titleBorderPartEnd = BorderFactory.createTitledBorder("Functional Part End");
		TitledBorder titleBorderMetaProperties = BorderFactory.createTitledBorder("Meta-Properties");
		TitledBorder titleBorderOCL = BorderFactory.createTitledBorder("OCL Constraint");
		
		JPanel memberOfPanel = new JPanel();
		tabbedPane.addTab("MemberOf", memberOfPanel);
		tabbedPane.setEnabledAt(1, false);
		
		JPanel componentOfPanel = new JPanel();
		tabbedPane.addTab("ComponentOf", componentOfPanel);
		tabbedPane.setEnabledAt(2, false);
		componentOfPanel.setLayout(null);
		
		JLabel lblTheFollowingComponentof = new JLabel("The following ComponentOf relations were infered from the model:");
		lblTheFollowingComponentof.setBounds(10, 11, 422, 14);
		componentOfPanel.add(lblTheFollowingComponentof);
		
		JLabel lblName = new JLabel("Name: ");
		lblName.setBounds(10, 325, 46, 20);
		componentOfPanel.add(lblName);
		
		textField_1 = new JTextField();
		textField_1.setBounds(80, 325, 331, 20);
		componentOfPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JPanel functionalWholePanel = new JPanel();
		functionalWholePanel.setBounds(10, 380, 185, 159);
		functionalWholePanel.setBorder(titleBorderWholeEnd);
		componentOfPanel.add(functionalWholePanel);
		functionalWholePanel.setLayout(null);
		
		JLabel lblWhole = new JLabel("Type:");
		lblWhole.setBounds(10, 49, 72, 20);
		functionalWholePanel.add(lblWhole);
		
		textField_2 = new JTextField();
		textField_2.setBounds(78, 49, 97, 20);
		functionalWholePanel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblMultiplicity = new JLabel("Multiplicity: ");
		lblMultiplicity.setBounds(10, 76, 72, 20);
		functionalWholePanel.add(lblMultiplicity);
		
		JLabel lblEndname = new JLabel("Name:");
		lblEndname.setBounds(10, 22, 72, 20);
		functionalWholePanel.add(lblEndname);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(78, 22, 97, 20);
		functionalWholePanel.add(textField_4);
		
		JLabel lblAggregationKind = new JLabel("Aggregation:");
		lblAggregationKind.setBounds(10, 103, 72, 20);
		functionalWholePanel.add(lblAggregationKind);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(78, 76, 97, 20);
		functionalWholePanel.add(textField_3);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(78, 103, 97, 20);
		functionalWholePanel.add(textField_6);
		
		JCheckBox checkBox = new JCheckBox("Derived");
		checkBox.setBounds(10, 130, 63, 23);
		functionalWholePanel.add(checkBox);
		
		JCheckBox chckbxDerived = new JCheckBox("Read Only");
		chckbxDerived.setBounds(78, 130, 80, 23);
		functionalWholePanel.add(chckbxDerived);
		
		JLabel lblStereotype = new JLabel("Stereotype: ");
		lblStereotype.setBounds(10, 352, 61, 20);
		componentOfPanel.add(lblStereotype);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(80, 352, 331, 20);
		componentOfPanel.add(textField_5);
		
		JPanel functionalPartPanel = new JPanel();
		functionalPartPanel.setLayout(null);
		functionalPartPanel.setBounds(205, 380, 206, 159);
		functionalPartPanel.setBorder(titleBorderPartEnd);
		componentOfPanel.add(functionalPartPanel);
		
		JLabel label = new JLabel("Type:");
		label.setBounds(10, 49, 72, 20);
		functionalPartPanel.add(label);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(99, 49, 97, 20);
		functionalPartPanel.add(textField_7);
		
		JLabel label_1 = new JLabel("Multiplicity: ");
		label_1.setBounds(10, 76, 72, 20);
		functionalPartPanel.add(label_1);
		
		JLabel label_2 = new JLabel("Name:");
		label_2.setBounds(10, 22, 72, 20);
		functionalPartPanel.add(label_2);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(99, 22, 97, 20);
		functionalPartPanel.add(textField_8);
		
		JLabel label_3 = new JLabel("Aggregation:");
		label_3.setBounds(10, 103, 72, 20);
		functionalPartPanel.add(label_3);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(99, 76, 97, 20);
		functionalPartPanel.add(textField_9);
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(99, 103, 97, 20);
		functionalPartPanel.add(textField_10);
		
		JCheckBox checkBox_1 = new JCheckBox("Derived");
		checkBox_1.setBounds(10, 130, 63, 23);
		functionalPartPanel.add(checkBox_1);
		
		JCheckBox checkBox_2 = new JCheckBox("Read Only");
		checkBox_2.setBounds(99, 130, 80, 23);
		functionalPartPanel.add(checkBox_2);
		
		JPanel metaPropertyPanel = new JPanel();
		metaPropertyPanel.setBounds(421, 325, 340, 77);
		metaPropertyPanel.setBorder(titleBorderMetaProperties);
		componentOfPanel.add(metaPropertyPanel);
		metaPropertyPanel.setLayout(null);
		
		JCheckBox chckbxEssential = new JCheckBox("Essential");
		chckbxEssential.setBounds(10, 22, 105, 22);
		metaPropertyPanel.add(chckbxEssential);
		
		JCheckBox chckbxInseparable = new JCheckBox("Inseparable");
		chckbxInseparable.setBounds(10, 48, 105, 23);
		metaPropertyPanel.add(chckbxInseparable);
		
		JCheckBox chckbxImmutablepart = new JCheckBox("ImmutablePart");
		chckbxImmutablepart.setBounds(113, 22, 105, 22);
		metaPropertyPanel.add(chckbxImmutablepart);
		
		JCheckBox chckbxImmutablewhole = new JCheckBox("ImmutableWhole");
		chckbxImmutablewhole.setBounds(113, 48, 105, 23);
		metaPropertyPanel.add(chckbxImmutablewhole);
		
		
		JCheckBox chckbxShareable = new JCheckBox("Shareable");
		chckbxShareable.setBounds(236, 22, 98, 22);
		metaPropertyPanel.add(chckbxShareable);
		
		
		
		JPanel OCLPanel = new JPanel();
		OCLPanel.setBounds(424, 413, 337, 126);
		OCLPanel.setBorder(titleBorderOCL);
		componentOfPanel.add(OCLPanel);
		OCLPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		OCLPanel.add(scrollPane);
		
		JTextArea txtrContextWhole = new JTextArea();
		scrollPane.setViewportView(txtrContextWhole);
		txtrContextWhole.setEditable(false);
		//	txtrContextWhole.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		txtrContextWhole.setText("context Whole :: partEnd : Bag(Part)\r\nderive : self.part1.part2.part3");
		txtrContextWhole.setMargin(new Insets(5, 5, 5, 5));
		
		
		
		String[] columnNames = {"Whole","Part","Stereotype", "Transitivity Hold?", "Action", "Open Wizard"};
		Object[][] tableData = {{"Person","Heart","ComponentOf","True","Persist","Open Here!"}};
		
		table = new JTable(tableData, columnNames);
		JScrollPane tableScrollPane = new JScrollPane(table);
		tableScrollPane.setBounds(10, 36, 749, 245);
		componentOfPanel.add(tableScrollPane);
		table.setFillsViewportHeight(true);
		
		JPanel subCollectionOfPanel = new JPanel();
		tabbedPane.addTab("SubCollectionOf", subCollectionOfPanel);
		tabbedPane.setEnabledAt(3, false);
	}
	
	private ActionListener actionCheck = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			consoleTextPane.setText("");
			consoleTextPane.setText("Checking model for hierarchy cycles... ");
			
			boolean hasHierarchyCycle = inferenceMaker.getHierachyCycleChecker().check();
			
			if(hasHierarchyCycle) consoleTextPane.setText(consoleTextPane.getText()+"FAILED!\n");
			else consoleTextPane.setText(consoleTextPane.getText()+"OK!\n");	

			consoleTextPane.setText(consoleTextPane.getText()+"Hierarchy cycles found: "+inferenceMaker.getHierachyCycleChecker().getErrors().size()+"\n");
			if(hasHierarchyCycle){
				for (ArrayList<Classifier> cycle : inferenceMaker.getHierachyCycleChecker().getErrors()) {
					for (Classifier classifier : cycle) {
						consoleTextPane.setText(consoleTextPane.getText()+classifier.getName()+", ");
					}
					consoleTextPane.setText(consoleTextPane.getText()+"\n");
				}
			}


			
		}
	};
	
	public static void setLabelToPassed(JLabel label){
		if(label == null)
			return;
		
		label.setText("PASSED");
		label.setForeground(GREEN);
		label.setVisible(true);
	}
	
	public static void setLabelToFailed(JLabel label){
		if(label == null)
			return;
		
		label.setText("FAILED");
		label.setForeground(RED);
		label.setVisible(true);
	}
	
	public static void setLabelToAborted(JLabel label){
		if(label == null)
			return;
		
		label.setText("ABORTED");
		label.setForeground(YELLOW);
		label.setVisible(true);
	}
	
	
	 /** Open the Dialog.
	 */
	public static void open (OntoUMLParser parser/*AppFrame parent*/)
	{
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			
			MeronymicValidationDialog dialog = new MeronymicValidationDialog( parser/*parent*/);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
//			dialog.setLocationRelativeTo(parent);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
